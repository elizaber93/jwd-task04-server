package by.epam.training.javaWEB.task04.server.app;

import java.io.*;
import java.net.Socket;
public class ServerRunnable  implements Runnable{

    protected Socket client = null;

    public ServerRunnable(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {

        try {
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            String request;
            request = (String)in.readObject();
            ServerApp.log.info("request: " + request);
            if (request.equals("0")) {
                client.close();
                ServerApp.log.info("client: Connection closed");
            } else {
                ServerAnswer serverAnswer = new ServerAnswer();
                out.writeObject(serverAnswer.getAnswer(Integer.parseInt(request)));
            }
        } catch (IOException | ClassNotFoundException e) {
            ServerApp.log.info(e.getMessage());
        }
    }
}
