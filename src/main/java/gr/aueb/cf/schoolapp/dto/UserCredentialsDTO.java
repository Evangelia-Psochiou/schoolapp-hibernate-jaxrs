package gr.aueb.cf.schoolapp.dto;

/**
 * This class represents a Data Transfer Object (DTO) for user credentials data.
 * It encapsulates information about a user's credentials, including their ID, username, and password.
 */
public class UserCredentialsDTO {
    private Long id;
    private String username;
    private String password;

    /**
     * Default constructor for creating an empty UserCredentialsDTO object.
     */
    public UserCredentialsDTO() {
    }

    /**
     * Parameterized constructor for creating a UserCredentialsDTO object with specific data.
     *
     * @param id       The unique identifier for the user.
     * @param username The username of the user.
     * @param password The password associated with the user's account.
     */
    public UserCredentialsDTO(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    /**
     * Get the unique identifier of the user.
     *
     * @return The user's ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the unique identifier of the user.
     *
     * @param id The user's ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the username of the user.
     *
     * @return The user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the username of the user.
     *
     * @param username The user's username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the password associated with the user's account.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password associated with the user's account.
     *
     * @param password The user's password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Generate a string representation of the UserCredentialsDTO object.
     *
     * @return A string containing the user's ID, username, and password.
     */
    @Override
    public String toString() {
        return "UserCredentialsDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
