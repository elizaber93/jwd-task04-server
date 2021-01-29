package by.epam.training.javaWEB.task04.server.dao.reader.implementation;

import by.epam.training.javaWEB.task04.server.dao.reader.Reader;
import by.epam.training.javaWEB.task04.server.exception.DataAccessException;

import java.io.*;

public class TXTFileReader implements Reader {

    @Override
    public String read(String fileName) throws IOException {
        StringBuilder result = new StringBuilder();
        File file = new File(fileName);
        if (!file.exists()) {
            throw new DataAccessException("File does not exist");
        }
        InputStream inputStream = new FileInputStream(file);
        InputStreamReader in = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader reader = new BufferedReader(in);
        String readString = reader.readLine();
        while (readString != null) {
            result.append(readString+"\n");
            readString = reader.readLine();
        }
        return result.toString();
    }
}
