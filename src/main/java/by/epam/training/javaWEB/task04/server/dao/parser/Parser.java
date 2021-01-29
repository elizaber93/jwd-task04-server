package by.epam.training.javaWEB.task04.server.dao.parser;

import by.epam.training.javaWEB.task04.server.entity.Book;

import java.io.IOException;

public interface Parser {
    Book parse() throws IOException;
}
