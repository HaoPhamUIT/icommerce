package vn.phh.commons.exception;

public class UnknownException extends GenericException {

    private static final long serialVersionUID = -2347800984470081642L;

    /**
     *
     * @param e
     */
    public UnknownException(Exception e) {
        super(e);
        this.status = 513;
    }

    /**
     * Instance with message
     *
     * @param message
     */
    public UnknownException(String message) {
        super(message);
        this.status = 513;
    }

    public UnknownException(String code, String message) {
        super(code, message, 513);
    }
}
