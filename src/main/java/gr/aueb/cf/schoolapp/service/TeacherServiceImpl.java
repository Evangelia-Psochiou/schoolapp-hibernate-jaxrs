package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.schoolapp.service.exceptions.EntityNotFoundException;
import gr.aueb.cf.schoolapp.service.util.JPAHelper;
import gr.aueb.cf.schoolapp.service.util.LoggerUtil;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.ext.Provider;
import java.util.List;

/**
 * Implementation class for managing teacher-related operations in a school application.
 */
@Provider
@RequestScoped
public class TeacherServiceImpl implements ITeacherService {
    @Inject
    private ITeacherDAO teacherDAO;

    /**
     * Inserts a new teacher into the system.
     *
     * @param teacherDTO The TeacherDTO object containing information about the teacher to be inserted.
     * @return The inserted Teacher object.
     * @throws EntityAlreadyExistsException If a teacher with the same ID already exists in the system.
     */
    @Override
    public Teacher insertTeacher(TeacherDTO teacherDTO) throws EntityAlreadyExistsException {
        Teacher teacher = null;
        try {
            JPAHelper.beginTransaction();
            teacher = map(teacherDTO);
            if (teacherDTO.getId() == null) {
                teacher = teacherDAO.insert(teacher);
            } else {
                throw new EntityAlreadyExistsException(Teacher.class, teacher.getId());
            }
            JPAHelper.commitTransaction();
        } catch (EntityAlreadyExistsException e) {
            JPAHelper.rollbackTransaction();
            LoggerUtil.getCurrentLogger().warning("Insert teacher - " +
                    "rollback - entity already exists");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
        return teacher;
    }

    /**
     * Updates an existing teacher's information in the system.
     *
     * @param teacherDTO The TeacherDTO object containing updated information about the teacher.
     * @return The updated Teacher object.
     * @throws EntityNotFoundException If the specified teacher is not found in the system.
     */
    @Override
    public Teacher updateTeacher(TeacherDTO teacherDTO) throws EntityNotFoundException {
        Teacher teacherToUpdate;
        try {
            JPAHelper.beginTransaction();
            teacherToUpdate = map(teacherDTO);
            if (teacherDAO.getById(teacherToUpdate.getId()) == null) {
                throw new EntityNotFoundException(Teacher.class, teacherToUpdate.getId());
            }
            teacherDAO.update(teacherToUpdate);
            JPAHelper.commitTransaction();
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            LoggerUtil.getCurrentLogger().warning("Update rollback - Entity not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
        return teacherToUpdate;
    }

    /**
     * Deletes a teacher from the system by their ID.
     *
     * @param id The ID of the teacher to be deleted.
     * @throws EntityNotFoundException If the specified teacher is not found in the system.
     */
    @Override
    public void deleteTeacher(Long id) throws EntityNotFoundException {
        try {
            JPAHelper.beginTransaction();
            if (teacherDAO.getById(id) == null) {
                throw new EntityNotFoundException(Teacher.class, id);
            }
            teacherDAO.delete(id);
            JPAHelper.commitTransaction();
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            LoggerUtil.getCurrentLogger().warning("Delete rollback");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
    }

    /**
     * Retrieves a list of teachers by their last name.
     *
     * @param lastname The last name of teachers to search for.
     * @return A list of Teacher objects matching the specified last name.
     * @throws EntityNotFoundException If no teachers with the specified last name are found.
     */
    @Override
    public List<Teacher> getTeacherByLastname(String lastname) throws EntityNotFoundException {
        List<Teacher> teachers;
        try {
            JPAHelper.beginTransaction();
            teachers = teacherDAO.getByLastName(lastname);
            if (teachers.size() == 0) {
                throw new EntityNotFoundException(List.class, 0L);
            }
            JPAHelper.commitTransaction();
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            LoggerUtil.getCurrentLogger().warning("Get Teacher rollback " +
                    "- Teacher not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
        return teachers;
    }

    /**
     * Retrieves information about a specific teacher by their ID.
     *
     * @param id The ID of the teacher to retrieve.
     * @return The Teacher object with the specified ID.
     * @throws EntityNotFoundException If the specified teacher is not found in the system.
     */
    @Override
    public Teacher getTeacherById(Long id) throws EntityNotFoundException {
        Teacher teacher;
        try {
            JPAHelper.beginTransaction();
            teacher = teacherDAO.getById(id);
            if (teacher == null) {
                throw new EntityNotFoundException(Teacher.class, id);
            }
            JPAHelper.commitTransaction();
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            LoggerUtil.getCurrentLogger().warning("Get teacher by id rollback " +
                    "- Teacher not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
        return teacher;
    }

    private Teacher map(TeacherDTO dto) {
        Teacher teacher = new Teacher();
        teacher.setId(dto.getId());
        teacher.setFirstname(dto.getFirstname());
        teacher.setLastname(dto.getLastname());
        return teacher;
    }
}
