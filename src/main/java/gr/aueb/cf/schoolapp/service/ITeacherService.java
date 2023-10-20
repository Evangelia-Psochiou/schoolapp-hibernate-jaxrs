package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.schoolapp.service.exceptions.EntityNotFoundException;

import java.util.List;
/**
 * Service interface for managing teacher-related operations in a school application.
 */
public interface ITeacherService {
    /**
     * Inserts a new teacher into the system.
     *
     * @param teacherDTO The TeacherDTO object containing information about the teacher to be inserted.
     * @return The inserted Teacher object.
     * @throws EntityAlreadyExistsException If a teacher with the same ID already exists in the system.
     */
    Teacher insertTeacher(TeacherDTO teacherDTO) throws EntityAlreadyExistsException;

    /**
     * Updates an existing teacher's information in the system.
     *
     * @param teacherDTO The TeacherDTO object containing updated information about the teacher.
     * @return The updated Teacher object.
     * @throws EntityNotFoundException If the specified teacher is not found in the system.
     */
    Teacher updateTeacher(TeacherDTO teacherDTO) throws EntityNotFoundException;

    /**
     * Deletes a teacher from the system by their ID.
     *
     * @param id The ID of the teacher to be deleted.
     * @throws EntityNotFoundException If the specified teacher is not found in the system.
     */
    void deleteTeacher(Long id) throws EntityNotFoundException;

    /**
     * Retrieves a list of teachers by their last name.
     *
     * @param lastname The last name of teachers to search for.
     * @return A list of Teacher objects matching the specified last name.
     * @throws EntityNotFoundException If no teachers with the specified last name are found.
     */
    List<Teacher> getTeacherByLastname(String lastname) throws EntityNotFoundException;

    /**
     * Retrieves information about a specific teacher by their ID.
     *
     * @param id The ID of the teacher to retrieve.
     * @return The Teacher object with the specified ID.
     * @throws EntityNotFoundException If the specified teacher is not found in the system.
     */
    Teacher getTeacherById(Long id) throws EntityNotFoundException;
}
