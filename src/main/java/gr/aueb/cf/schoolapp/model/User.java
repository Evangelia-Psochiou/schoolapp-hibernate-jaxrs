package gr.aueb.cf.schoolapp.model;

import jakarta.persistence.*;
/**
 * This class represents a model for storing information about users.
 */
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // The unique identifier for the user.

    @Column(name = "USERNAME", length = 50, nullable = true, unique = false)
    private String username; // The username of the user.

    @Column(name = "PASSWORD", length = 50, nullable = true, unique = false)
    private String password; // The password associated with the user's account.

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
     * Generate a string representation of the User object.
     *
     * @return A string containing the user's ID, username, and password.
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
