package by.epam.training.javaWEB.task04.server.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Objects;

public class Book implements Serializable {
    private LinkedList<BookElement> elements = new LinkedList<BookElement>();

    public Book() {
    }

    public Book(LinkedList<BookElement> elements) {
        this.elements = elements;
    }

    public LinkedList<BookElement> getElements() {
        return elements;
    }

    public void setElements(LinkedList<BookElement> elements) {
        this.elements = elements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return elements.equals(book.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elements);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (BookElement element : elements) {
            result.append(element);
        }
        return result.toString();
    }

    public boolean addElement(BookElement element) {
        return this.elements.add(element);
    }
}
