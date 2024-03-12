package exception;

public class InvalidBankAccountException extends RuntimeException {
    public InvalidBankAccountException() {
    }

    public InvalidBankAccountException(String message) {
        super(message);
    }
}
