package ca.bytetube._19_amazongo;

public class PaymentResult {
    PaymentStatus status;
    String authorizationId;
    String captureId;
    String message;


    public PaymentResult(PaymentStatus status, String authorizationId, String captureId, String message) {
        this.status = status;
        this.authorizationId = authorizationId;
        this.captureId = captureId;
        this.message = message;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public String getAuthorizationId() {
        return authorizationId;
    }

    public void setAuthorizationId(String authorizationId) {
        this.authorizationId = authorizationId;
    }

    public String getCaptureId() {
        return captureId;
    }

    public void setCaptureId(String captureId) {
        this.captureId = captureId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
