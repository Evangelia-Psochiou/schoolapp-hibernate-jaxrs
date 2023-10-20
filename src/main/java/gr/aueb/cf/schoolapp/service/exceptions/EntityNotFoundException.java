package gr.aueb.cf.schoolapp.service.exceptions;
/**
 * Custom exception class to represent the situation where an entity is not found.
 */
public class EntityNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs an exception with a message indicating that an entity of a specified class and ID was not found.
     *
     * @param entityClass The class of the entity that was not found.
     * @param id          The unique identifier of the entity that was not found.
     */
    public EntityNotFoundException(Class<?> entityClass, Long id) {
        super("Entity " + entityClass.getSimpleName() + " with id " + id + " does not exist");
    }
}
