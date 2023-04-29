package fitness.exception;

public class EntityNotFoundException extends Exception {

    final Class clazz;

    public EntityNotFoundException(final Class clazz, final String message) {
        super(message);
        this.clazz = clazz;
    }
}
