package vn.phh.commons.exception;

public class IllegalServiceException extends GenericException {

    private static final long serialVersionUID = 6718521108737948229L;

    public IllegalServiceException(Exception e) {
        super(e);
        this.status = 430;
    }

    /**
     * Instance with message
     * @param message
     */
    public IllegalServiceException(String message) {
        super(message);
        this.status = 430;
    }

    public IllegalServiceException(String code, String message) {
        super(code, message, 430);
    }

}
