package by.epam.training.javaWEB.task04.server.app;

import by.epam.training.javaWEB.task04.server.util.TextProcess;

import java.io.IOException;

public class ServerAnswer {
    int request;

    public ServerAnswer() {

    }

    public Object getAnswer(int request) throws IOException {
        this.request = request;
        switch (request) {
            case 1: return TextProcess.getBookString();
            case 2: return TextProcess.sortSentences().toString();
            case 3: return TextProcess.swapWords().toString();
            case 4: return TextProcess.getWordsByOrder().toString();
            case 5: return TextProcess.vowelWordsByOrder().toString();
            default: return("Unknown request");
        }
    }
}
