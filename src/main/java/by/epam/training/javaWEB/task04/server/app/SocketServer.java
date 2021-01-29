package by.epam.training.javaWEB.task04.server.app;

import by.epam.training.javaWEB.task04.server.exception.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    int portNumber = 4999;
    ServerSocket server = null;

    public void runServer() throws IOException {
        try {
            server = new ServerSocket(portNumber);

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }

        Socket client = null;

        while (true) {
            Thread thread;
            try {
                client = server.accept();
                thread = new Thread(new ServerRunnable(client));
                thread.start();
                thread.join();

            } catch (IOException | InterruptedException e) {
                throw new IOException(e.getMessage());
            }
        }
    }
}
