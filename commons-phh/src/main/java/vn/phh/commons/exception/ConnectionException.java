package vn.phh.commons.exception;

public class ConnectionException extends GenericException {

    private static final long serialVersionUID = -3106916062300908701L;

    public ConnectionException(Exception e) {
        super(e);
        this.status = 504;
    }

    /**
     * Instance with message
     * @param message
     */
    public ConnectionException(String message) {
        super(message);
        this.status = 504;
    }

    public ConnectionException(String code, String message) {
        super(code, message, 504);
    }
}