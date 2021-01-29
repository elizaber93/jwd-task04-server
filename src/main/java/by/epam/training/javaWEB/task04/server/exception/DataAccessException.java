package by.epam.training.javaWEB.task04.server.exception;

import java.io.IOException;

public class DataAccessException extends IOException {
    public DataAccessException() {
    }

    public DataAccessException(String message) {
        super(message);
    }
}
