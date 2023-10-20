package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.util.JPAHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;

import javax.inject.Named;
import javax.ws.rs.ext.Provider;
import java.util.List;

/**
 * This class provides an implementation of the ITeacherDAO interface
 * for managing teacher data in the data store.
 */
@Provider
@Named("teacherDAOImpl")
public class TeacherDAOImpl implements ITeacherDAO {

    /**
     * Inserts a new teacher into the data store.
     *
     * @param teacher The teacher object to be inserted.
     * @return The teacher object that has been inserted.
     */
    @Override
    public Teacher insert(Teacher teacher) {
        EntityManager em = getEntityManager();
        em.persist(teacher);
        return teacher;
    }

    /**
     * Updates an existing teacher in the data store.
     *
     * @param teacher The teacher object to be updated.
     * @return The updated teacher object.
     */
    @Override
    public Teacher update(Teacher teacher) {
        getEntityManager().merge(teacher);
        return teacher;

    }

    /**
     * Deletes a teacher from the data store by their ID.
     *
     * @param id The ID of the teacher to be deleted.
     */
    @Override
    public void delete(Long id) {
        EntityManager em = getEntityManager();
        Teacher teacherToDelete = em.find(Teacher.class, id);
        em.remove(teacherToDelete);
    }

    /**
     * Retrieves a list of teachers based on their last name.
     *
     * @param lastname The last name to search for.
     * @return A list of teachers with the provided last name.
     */
    @Override
    public List<Teacher> getByLastName(String lastname) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Teacher> selectQuery = builder.createQuery(Teacher.class);
        Root<Teacher> root = selectQuery.from(Teacher.class);

        ParameterExpression<String> tLastname = builder.parameter(String.class);
        selectQuery.select(root).where((builder.like(root.get("lastname"), tLastname)));

        TypedQuery<Teacher> query = getEntityManager().createQuery(selectQuery);
        query.setParameter(tLastname, lastname + "%");
        return query.getResultList();
    }

    /**
     * Retrieves a teacher from the data store by their ID.
     *
     * @param id The ID of the teacher to retrieve.
     * @return The teacher object corresponding to the provided ID.
     */
    @Override
    public Teacher getById(Long id) {
        EntityManager em = getEntityManager();
        return em.find(Teacher.class, id);
    }

    private  EntityManager getEntityManager() {
        return JPAHelper.getEntityManager();
    }
}
