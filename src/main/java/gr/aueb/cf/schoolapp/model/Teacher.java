package gr.aueb.cf.schoolapp.model;

import jakarta.persistence.*;

/**
 * This class represents a model for storing information about teachers.
 */
@Entity
@Table(name = "TEACHERS")
public class Teacher {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // The unique identifier for the teacher.

    @Column(name = "FIRSTNAME", length = 50, nullable = true, unique = false)
    private String firstname; // The first name of the teacher.

    @Column(name = "LASTNAME", length = 50, nullable = true, unique = false)
    private String lastname; // The last name of the teacher.

    /**
     * Get the unique identifier of the teacher.
     *
     * @return The teacher's ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the unique identifier of the teacher.
     *
     * @param id The teacher's ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the first name of the teacher.
     *
     * @return The teacher's first name.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Set the first name of the teacher.
     *
     * @param firstname The teacher's first name to set.
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Get the last name of the teacher.
     *
     * @return The teacher's last name.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Set the last name of the teacher.
     *
     * @param lastname The teacher's last name to set.
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Generate a string representation of the Teacher object.
     *
     * @return A string containing the teacher's ID, first name, and last name.
     */
    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}

