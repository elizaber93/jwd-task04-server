package by.epam.training.javaWEB.task04.server.app;

import by.epam.training.javaWEB.task04.server.exception.Log;

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
            if (request.equals("0")) {
                client.close();
                System.out.println("client: Connection closed");
            } else {
                ServerAnswer serverAnswer = new ServerAnswer();
                System.out.println("client: " + request);
                out.writeObject(serverAnswer.getAnswer(Integer.parseInt(request)));
            }
        } catch (IOException | ClassNotFoundException e) {
            Log.getLogger().info(e.getMessage());
        }
    }
}
