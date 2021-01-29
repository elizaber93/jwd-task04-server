package by.epam.training.javaWEB.task04.server.entity;

import java.io.Serializable;
import java.util.Objects;

public class CodeBlock implements BookElement, Serializable {
    private String content;

    public CodeBlock() {
    }

    public CodeBlock(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CodeBlock)) return false;
        CodeBlock codeBlock = (CodeBlock) o;
        return content.equals(codeBlock.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    @Override
    public String toString() {
        return content;
    }
}
