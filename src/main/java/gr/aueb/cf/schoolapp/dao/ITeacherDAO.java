package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.model.Teacher;

import java.util.List;
/**
 * This interface defines methods for managing teacher data in the data store.
 */
public interface ITeacherDAO {
    /**
     * Inserts a new teacher into the data store.
     *
     * @param teacher The teacher object to be inserted.
     * @return The teacher object that has been inserted.
     */
    Teacher insert(Teacher teacher);

    /**
     * Updates an existing teacher in the data store.
     *
     * @param teacher The teacher object to be updated.
     * @return The updated teacher object.
     */
    Teacher update(Teacher teacher);

    /**
     * Deletes a teacher from the data store by their ID.
     *
     * @param id The ID of the teacher to be deleted.
     */
    void delete(Long id);

    /**
     * Retrieves a list of teachers based on their last name.
     *
     * @param lastname The last name to search for.
     * @return A list of teachers with the provided last name.
     */
    List<Teacher> getByLastName(String lastname);

    /**
     * Retrieves a teacher from the data store by their ID.
     *
     * @param id The ID of the teacher to retrieve.
     * @return The teacher object corresponding to the provided ID.
     */
    Teacher getById(Long id);
}
