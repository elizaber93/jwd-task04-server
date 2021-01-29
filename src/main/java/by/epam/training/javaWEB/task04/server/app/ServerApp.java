package by.epam.training.javaWEB.task04.server.app;

import by.epam.training.javaWEB.task04.server.exception.Log;

import java.io.*;

public class ServerApp {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        try {
            SocketServer server = new SocketServer();
            server.runServer();
        } catch (IOException e) {
            Log.getLogger().info(e.getMessage());
        }
    }

}
