package by.epam.training.javaWEB.task04.server.dao.reader;

import by.epam.training.javaWEB.task04.server.exception.DataAccessException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface Reader {
    String read(String fileName) throws IOException;
}
