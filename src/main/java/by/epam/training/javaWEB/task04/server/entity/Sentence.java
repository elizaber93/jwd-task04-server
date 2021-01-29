package by.epam.training.javaWEB.task04.server.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Objects;

public class Sentence implements BookElement, Serializable {
    private LinkedList<SentenceElement> elements = new LinkedList<SentenceElement>();
    private String endSymbol;

    public Sentence() {
    }

    public Sentence(LinkedList<SentenceElement> elements) {
        this.elements = elements;
    }

    public Sentence(LinkedList<SentenceElement> elements, String endSymbol) {
        this.elements = elements;
        this.endSymbol = endSymbol;
    }

    public LinkedList<SentenceElement> getElements() {
        return elements;
    }

    public void setElements(LinkedList<SentenceElement> elements) {
        this.elements = elements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sentence)) return false;
        Sentence sentence = (Sentence) o;
        return elements.equals(sentence.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elements);
    }

    @Override
    public String toString() {
        StringBuilder sentence = new StringBuilder();
        for (int i = 0; i < elements.size(); i++) {
            if (i!=0 && elements.get(i).getClass().getSimpleName().equals("Word")) {
                sentence.append(" ").append(elements.get(i));
            } else {
                sentence.append(elements.get(i));
            }
        }
        return sentence.toString();
    }

    public boolean addElement(SentenceElement element) {
        return this.elements.add(element);
    }
}
