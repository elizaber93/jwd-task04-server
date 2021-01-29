package by.epam.training.javaWEB.task04.server.entity;

import java.io.Serializable;
import java.util.Objects;

public class Word implements SentenceElement, Serializable {
    private String wordContent;

    public Word() {
    }

    public Word(String wordContent) {
        this.wordContent = wordContent;
    }

    public String getWordContent() {
        return wordContent;
    }

    public void setWordContent(String wordContent) {
        this.wordContent = wordContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;
        Word word = (Word) o;
        return wordContent.equals(word.wordContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wordContent);
    }

    @Override
    public String toString() {
        return wordContent;
    }
}
