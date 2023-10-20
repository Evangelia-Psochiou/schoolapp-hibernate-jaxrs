package gr.aueb.cf.schoolapp.service;


import gr.aueb.cf.schoolapp.dto.UserCredentialsDTO;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.schoolapp.service.exceptions.EntityNotFoundException;

import java.util.List;

/**
 * Service interface for managing user-related operations in a school application.
 */
public interface IUserService {
    /**
     * Inserts a new user into the system.
     *
     * @param userCredentialsDTO The UserCredentialsDTO object containing information about the user to be inserted.
     * @return The inserted User object.
     * @throws EntityAlreadyExistsException If a user with the same ID already exists in the system.
     */
    User insertUser(UserCredentialsDTO userCredentialsDTO) throws EntityAlreadyExistsException;

    /**
     * Updates an existing user's information in the system.
     *
     * @param userCredentialsDTO The UserCredentialsDTO object containing updated information about the user.
     * @return The updated User object.
     * @throws EntityNotFoundException If the specified user is not found in the system.
     */
    User updateUser(UserCredentialsDTO userCredentialsDTO) throws EntityNotFoundException;

    /**
     * Deletes a user from the system by their ID.
     *
     * @param id The ID of the user to be deleted.
     * @throws EntityNotFoundException If the specified user is not found in the system.
     */
    void deleteUser(Long id) throws EntityNotFoundException;

    /**
     * Retrieves a list of users by their username.
     *
     * @param username The username of users to search for.
     * @return A list of User objects matching the specified username.
     * @throws EntityNotFoundException If no users with the specified username are found.
     */
    List<User> getUserByUsername(String username) throws EntityNotFoundException;

    /**
     * Retrieves information about a specific user by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The User object with the specified ID.
     * @throws EntityNotFoundException If the specified user is not found in the system.
     */
    User getUserById(Long id) throws EntityNotFoundException;
}
