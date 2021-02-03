package vn.phh.commons.exception;

public class InternalServerException extends GenericException {

    private static final long serialVersionUID = 5565257094464136011L;

    public InternalServerException(Exception e) {
        super(e);
        this.status = 500;
    }

    public InternalServerException(String message) {
        super(message);
        this.status = 500;
    }

    public InternalServerException(String code, String message) {
        super(code, message, 500);
    }
}
