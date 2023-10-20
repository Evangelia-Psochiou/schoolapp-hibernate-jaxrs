package gr.aueb.cf.schoolapp.rest;

import gr.aueb.cf.schoolapp.dto.UserCredentialsDTO;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.IUserService;
import gr.aueb.cf.schoolapp.service.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.schoolapp.service.exceptions.EntityNotFoundException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class serves as a RESTful web service for managing user information.
 */
@Path("/users")
public class UserRestController {

    @Inject
    IUserService userService; // Injecting the user service for handling user-related operations.

    /**
     * Retrieves a list of users by their username.
     *
     * @param username The username to search for.
     * @return A JSON response containing a list of UserCredentialsDTO objects.
     */
    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersByUsername(@QueryParam("username") String username) {
        List<User> users;
        try {
            users = userService.getUserByUsername(username);
            List<UserCredentialsDTO> userCredentialsDTO = new ArrayList<>();
            for (User user : users) {
                UserCredentialsDTO dto = new UserCredentialsDTO(user.getId(),
                        user.getUsername(), user.getPassword());
            }
            return Response.status(Response.Status.OK).entity(userCredentialsDTO).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("NOT FOUND").build();
        }
    }

    /**
     * Retrieves information about a specific user by their ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return A JSON response containing a UserCredentialsDTO object.
     */
    @Path("/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("userId") Long userId) {
        User user;
        try {
            user = userService.getUserById(userId);
            UserCredentialsDTO dto = new UserCredentialsDTO(user.getId(),
                    user.getUsername(), user.getPassword());
            return Response.status(Response.Status.OK).entity(dto).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("NOT FOUND").build();
        }
    }

    /**
     * Adds a new user to the system.
     *
     * @param dto     The UserCredentialsDTO object containing user information to be added.
     * @param uriInfo Information about the request URI.
     * @return A JSON response containing the added UserCredentialsDTO object.
     */
    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(UserCredentialsDTO dto, @Context UriInfo uriInfo) {
        try {
            User user = userService.insertUser(dto);
            UserCredentialsDTO userDTO = map(user);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            return Response.created(uriBuilder.path(Long.toString(userDTO.getId())).build())
                    .entity(userDTO).build();
        } catch (EntityAlreadyExistsException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("User already exists")
                    .build();
        }
    }

    /**
     * Deletes a user from the system by their ID.
     *
     * @param userId The ID of the user to be deleted.
     * @return A JSON response containing the deleted UserCredentialsDTO object.
     */
    @Path("/{userId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("userId") Long userId) {
        try {
            User user = userService.getUserById(userId);
            userService.deleteUser(userId);
            UserCredentialsDTO userCredentialsDTO = map(user);
            return Response.status(Response.Status.OK).entity(userCredentialsDTO).build();
        } catch (EntityNotFoundException e1) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("User Not Found")
                    .build();
        }
    }

    /**
     * Updates the information of an existing user.
     *
     * @param userId The ID of the user to be updated.
     * @param dto    The UserCredentialsDTO object containing updated user information.
     * @return A JSON response containing the updated UserCredentialsDTO object.
     */
    @Path("/{userId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("userId") Long userId, UserCredentialsDTO dto) {
        try {
            dto.setId(userId);
            User user = userService.updateUser(dto);
            UserCredentialsDTO userCredentialsDTO = map(user);
            return Response.status(Response.Status.OK).entity(userCredentialsDTO).build();
        } catch (EntityNotFoundException e1) {
            return Response.status(Response.Status.NOT_FOUND).entity("User Not Found").build();
        }
    }

    /**
     * Maps a User object to a UserCredentialsDTO object.
     *
     * @param user The User object to be mapped.
     * @return A mapped UserCredentialsDTO object.
     */
    private UserCredentialsDTO map(User user) {
        UserCredentialsDTO userCredentialsDTO = new UserCredentialsDTO();
        userCredentialsDTO.setId(user.getId());
        userCredentialsDTO.setUsername(user.getUsername());
        userCredentialsDTO.setPassword(user.getPassword());
        return userCredentialsDTO;
    }
}
