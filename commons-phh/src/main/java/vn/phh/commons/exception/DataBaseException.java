package vn.phh.commons.exception;

public class DataBaseException extends GenericException {

    private static final long serialVersionUID = 4459005915588272427L;

    public DataBaseException(Exception e) {
        super(e);
        this.status = 512;
    }

    /**
     * Instance with message
     * @param message
     */
    public DataBaseException(String message) {
        super(message);
        this.status = 512;
    }

    public DataBaseException(String code, String message) {
        super(code, message, 512);
    }
}
