package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.model.User;
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
 * This class provides an implementation of the IUserDAO interface
 * for managing user data in the data store.
 */
@Provider
@Named("userDAOImpl")
public class UserDAOImpl implements IUserDAO {

    /**
     * Inserts a new user into the data store.
     *
     * @param user The user object to be inserted.
     * @return The user object that has been inserted.
     */
    @Override
    public User insert(User user) {
        EntityManager em = getEntityManager();
        em.persist(user);
        return user;
    }

    /**
     * Updates an existing user in the data store.
     *
     * @param user The user object to be updated.
     * @return The updated user object.
     */
    @Override
    public User update(User user) {
        getEntityManager().merge(user);
        return user;
    }

    /**
     * Deletes a user from the data store by their ID.
     *
     * @param id The ID of the user to be deleted.
     */
    @Override
    public void delete(Long id) {
        EntityManager em = getEntityManager();
        User userToDelete = em.find(User.class, id);
        em.remove(userToDelete);
    }

    /**
     * Retrieves a list of users based on their username.
     *
     * @param username The username to search for.
     * @return A list of users with the provided username.
     */
    @Override
    public List<User> getByUsername(String username) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> selectQuery = builder.createQuery(User.class);
        Root<User> root = selectQuery.from(User.class);

        ParameterExpression<String> tUsername = builder.parameter(String.class);
        selectQuery.select(root).where((builder.like(root.get("username"), tUsername)));

        TypedQuery<User> query = getEntityManager().createQuery(selectQuery);
        query.setParameter(tUsername, username + "%");
        return query.getResultList();
    }

    /**
     * Retrieves a user from the data store by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The user object corresponding to the provided ID.
     */
    @Override
    public User getById(Long id) {
        EntityManager em = getEntityManager();
        return em.find(User.class, id);
    }

    private  EntityManager getEntityManager() {
        return JPAHelper.getEntityManager();
    }
}
