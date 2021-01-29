package by.epam.training.javaWEB.task04.server.entity;

import java.io.Serializable;
import java.util.Objects;

public class PunctuationMark implements SentenceElement, Serializable {
    private String mark;

    public PunctuationMark() {
    }

    public PunctuationMark(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PunctuationMark)) return false;
        PunctuationMark that = (PunctuationMark) o;
        return mark.equals(that.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mark);
    }

    @Override
    public String toString() {
        return mark;
    }
}
