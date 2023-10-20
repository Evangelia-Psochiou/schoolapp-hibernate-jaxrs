package gr.aueb.cf.schoolapp.service.exceptions;
/**
 * Custom exception class to represent the situation where an entity already exists.
 */
public class EntityAlreadyExistsException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs an exception with a message indicating that an entity of a specified class and ID already exists.
     *
     * @param entityClass The class of the entity for which the duplication is detected.
     * @param id          The unique identifier of the duplicate entity.
     */
    public EntityAlreadyExistsException(Class<?> entityClass, Long id) {
        super("Entity " + entityClass.getSimpleName() + " with id " + id + " already exists.");
    }
}
