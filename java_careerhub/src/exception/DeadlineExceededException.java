package exception;

public class DeadlineExceededException extends Exception {
    public DeadlineExceededException(String message) {
        super(message);
    }
}
