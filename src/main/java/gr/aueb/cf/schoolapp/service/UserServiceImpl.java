package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.IUserDAO;

import gr.aueb.cf.schoolapp.dto.UserCredentialsDTO;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.schoolapp.service.exceptions.EntityNotFoundException;
import gr.aueb.cf.schoolapp.service.util.JPAHelper;
import gr.aueb.cf.schoolapp.service.util.LoggerUtil;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.ext.Provider;
import java.util.List;

/**
 * Implementation class for managing user-related operations in a school application.
 */
@Provider
@RequestScoped
public class UserServiceImpl implements IUserService{

    @Inject
    private IUserDAO userDAO;

    /**
     * Inserts a new user into the system.
     *
     * @param userCredentialsDTO The UserCredentialsDTO object containing information about the user to be inserted.
     * @return The inserted User object.
     * @throws EntityAlreadyExistsException If a user with the same ID already exists in the system.
     */
    @Override
    public User insertUser(UserCredentialsDTO userCredentialsDTO) throws EntityAlreadyExistsException {
        User user = null;
        try {
            JPAHelper.beginTransaction();
            user = map(userCredentialsDTO);
            if (userCredentialsDTO.getId() == null) {
                user = userDAO.insert(user);
            } else {
                throw new EntityAlreadyExistsException(User.class, user.getId());
            }
            JPAHelper.commitTransaction();
        } catch (EntityAlreadyExistsException e) {
            JPAHelper.rollbackTransaction();
            LoggerUtil.getCurrentLogger().warning("Insert user - " +
                    "rollback - entity already exists");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }

        return user;

    }

    /**
     * Updates an existing user's information in the system.
     *
     * @param userCredentialsDTO The UserCredentialsDTO object containing updated information about the user.
     * @return The updated User object.
     * @throws EntityNotFoundException If the specified user is not found in the system.
     */
    @Override
    public User updateUser(UserCredentialsDTO userCredentialsDTO) throws EntityNotFoundException {
        User userToUpdate;
        try {
            JPAHelper.beginTransaction();
            userToUpdate = map(userCredentialsDTO);
            if (userDAO.getById(userToUpdate.getId()) == null) {
                throw new EntityNotFoundException(User.class, userToUpdate.getId());
            }
            userDAO.update(userToUpdate);
            JPAHelper.commitTransaction();
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            LoggerUtil.getCurrentLogger().warning("Update rollback - Entity not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }

        return userToUpdate;
    }

    /**
     * Deletes a user from the system by their ID.
     *
     * @param id The ID of the user to be deleted.
     * @throws EntityNotFoundException If the specified user is not found in the system.
     */
    @Override
    public void deleteUser(Long id) throws EntityNotFoundException {
        try {
            JPAHelper.beginTransaction();
            if (userDAO.getById(id) == null) {
                throw new EntityNotFoundException(User.class, id);
            }
            userDAO.delete(id);
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
     * Retrieves a list of users by their username.
     *
     * @param username The username of users to search for.
     * @return A list of User objects matching the specified username.
     * @throws EntityNotFoundException If no users with the specified username are found.
     */
    @Override
    public List<User> getUserByUsername(String username) throws EntityNotFoundException {
        List<User> users;
        try {
            JPAHelper.beginTransaction();
            users = userDAO.getByUsername(username);
            if (users.size() == 0) {
                throw new EntityNotFoundException(List.class, 0L);
            }
            JPAHelper.commitTransaction();
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            LoggerUtil.getCurrentLogger().warning("Get User rollback " +
                    "- User not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
        return users;
    }

    /**
     * Retrieves information about a specific user by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The User object with the specified ID.
     * @throws EntityNotFoundException If the specified user is not found in the system.
     */
    @Override
    public User getUserById(Long id) throws EntityNotFoundException {
        User user;
        try {
            JPAHelper.beginTransaction();
            user = userDAO.getById(id);
            if (user == null) {
                throw new EntityNotFoundException(User.class, id);
            }
            JPAHelper.commitTransaction();
        } catch (EntityNotFoundException e) {
            JPAHelper.rollbackTransaction();
            LoggerUtil.getCurrentLogger().warning("Get user by id rollback " +
                    "- User not found");
            throw e;
        } finally {
            JPAHelper.closeEntityManager();
        }
        return user;
    }

    private User map(UserCredentialsDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }
}
