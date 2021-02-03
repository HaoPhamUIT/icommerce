package vn.phh.commons.exception;

public class ValidationException extends GenericException {

    private static final long serialVersionUID = 11391777061680414L;
    /**
     * Instance with message
     * @param message
     */
    public ValidationException(String message) {
        super(message);
        this.status = 400;
    }
    /**
     * Instance with code and message
     * @param code
     * @param message
     */
    public ValidationException(String code, String message) {
        super(code, message, 400);
    }
}