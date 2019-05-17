package error;

public class FileProcessingError extends RuntimeException {

    public FileProcessingError(String message) {
        super(message);
    }

    public FileProcessingError(String message, Throwable e) {
        super(message, e);
    }

    public FileProcessingError(String message, String fileName, Throwable e) {
        super(String.format("%s File name: %s", message, fileName), e);
    }
}
