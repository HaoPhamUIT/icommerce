package vn.phh.commons.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.domain.Page;
import vn.phh.commons.calendar.DateTimeUtils;
import vn.phh.commons.exception.GenericException;
import vn.phh.commons.model.enums.ErrorMessage;

import java.util.UUID;

public class Response {

    private String serverDateTime = DateTimeUtils.now().toString();
    private static final String SUCCESSFUL = "Successful";
    private static final String UNSUCCESSFUL = "UnSuccessful";
    private int status = 200;
    private String message = SUCCESSFUL;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String exception;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String traceId;
    private Object data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PagedResult pagedResult;

    public Response() {
    }

    public Response(Object data) {
        if (data instanceof Page) {
            Page<Object> objectPage = (Page<Object>) data;
            this.data = objectPage.getContent();
            this.pagedResult = new PagedResult(objectPage.getNumber() + 1, objectPage.getSize(),
                    objectPage.getTotalElements(), objectPage.getTotalPages());
        } else this.data = data;
    }

    public Response(GenericException genericException) {
        this.status = genericException.getStatus();
        this.message = UNSUCCESSFUL;
        this.exception = genericException.getMessage();
        this.traceId = UUID.randomUUID().toString();
    }

    public Response(int status, String exception) {
        this.status = status;
        this.message = UNSUCCESSFUL;
        this.exception = exception;
        this.traceId = UUID.randomUUID().toString();
    }

    public Response(ErrorMessage errorMessage) {
        this.status = errorMessage.getCode();
        this.message = UNSUCCESSFUL;
        this.exception = errorMessage.getMessage();
        this.traceId = UUID.randomUUID().toString();
    }

    public Response(Object data, PagedResult pagedResult) {
        this.data = data;
        this.pagedResult = pagedResult;
    }

    public String getServerDateTime() {
        return serverDateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public Object getData() {
        return data;
    }

    public Response setData(Object data) {
        this.data = data;
        return this;
    }

    public PagedResult getPagedResult() {
        return pagedResult;
    }

    public Response setPagedResult(PagedResult pagedResult) {
        this.pagedResult = pagedResult;
        return this;
    }
}