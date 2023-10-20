package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.model.User;

import java.util.List;
/**
 * This interface defines methods for managing user data in the data store.
 */
public interface IUserDAO {
    /**
     * Inserts a new user into the data store.
     *
     * @param user The user object to be inserted.
     * @return The user object that has been inserted.
     */
    User insert (User user);

    /**
     * Updates an existing user in the data store.
     *
     * @param user The user object to be updated.
     * @return The updated user object.
     */
    User update (User user);

    /**
     * Deletes a user from the data store by their ID.
     *
     * @param id The ID of the user to be deleted.
     */
    void delete(Long id);

    /**
     * Retrieves a list of users based on their username.
     *
     * @param username The username to search for.
     * @return A list of users matching the provided username.
     */
    List<User> getByUsername(String username);

    /**
     * Retrieves a user from the data store by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The user object corresponding to the provided ID.
     */
    User getById(Long id);
}
