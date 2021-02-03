package vn.phh.commons.exception;

public class AccessDeniedException extends GenericException {

    private static final long serialVersionUID = 7551452985720285675L;

    public AccessDeniedException(Exception e) {
        super(e);
        this.status = 401;
    }

    /**
     * Instance with message
     * @param message
     */
    public AccessDeniedException(String message) {
        super(message);
        this.status = 401;
    }

    public AccessDeniedException(String code, String message) {
        super(code, message, 401);
    }
        
}
