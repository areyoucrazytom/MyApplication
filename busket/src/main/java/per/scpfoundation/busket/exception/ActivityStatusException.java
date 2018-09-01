package per.scpfoundation.busket.exception;


public class ActivityStatusException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Activiry root views status error";
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
