package gr.aueb.cf.schoolapp.dto;

/**
 * This class represents a Data Transfer Object (DTO) for teacher-related data.
 * It encapsulates information about a teacher, including their ID, first name, and last name.
 */
public class TeacherDTO {
    private Long id;
    private String firstname;
    private String lastname;

    /**
     * Default constructor for creating an empty TeacherDTO object.
     */
    public TeacherDTO() {
    }

    /**
     * Parameterized constructor for creating a TeacherDTO object with specific data.
     *
     * @param id        The unique identifier for the teacher.
     * @param firstname The first name of the teacher.
     * @param lastname  The last name of the teacher.
     */
    public TeacherDTO(Long id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

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
     * Generate a string representation of the TeacherDTO object.
     *
     * @return A string containing the teacher's ID, first name, and last name.
     */
    @Override
    public String toString() {
        return "TeacherDTO{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
