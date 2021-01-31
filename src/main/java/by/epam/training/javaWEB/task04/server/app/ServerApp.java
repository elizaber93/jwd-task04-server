package by.epam.training.javaWEB.task04.server.app;

import org.apache.log4j.Logger;

import java.io.IOException;

public class ServerApp {

    final static Logger log = Logger.getLogger(ServerApp.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        try {
            SocketServer server = new SocketServer();
            server.runServer();
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }

}
