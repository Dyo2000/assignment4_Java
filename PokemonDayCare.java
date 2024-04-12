package socket.programming;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import utitilies.Pokemon;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * This is the pokemondaycare server class that handles multiple clients
 * This class sets up a scene with a scrollpane that just prints information
 */
public class PokemonDayCare extends Application{
    private Socket socket;
    private TextArea ta = new TextArea();
    private int connection = 0;
    @Override
    public void start(Stage primaryStage){
        Scene scene = new Scene(new ScrollPane(ta), 360, 200);
        primaryStage.setTitle("Pokemon Day Care");
        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread(()-> {
            try {
                ServerSocket serverSocket = new ServerSocket(8000);

                Platform.runLater(() ->
                        ta.appendText("Server started at " + new Date() + '\n'));

                //listen to the client
                Socket connectToClient = serverSocket.accept();

                //print a connected message
                Platform.runLater(() -> ta.appendText("Connected to client" + " at " + new Date() + '\n'));

                //setup input streams
                DataInputStream isFromClient = new DataInputStream(connectToClient.getInputStream());

                //setup output streams
                DataOutputStream isToClient = new DataOutputStream(connectToClient.getOutputStream());

                while (true) {
                    //these two read statements read from the client
                    String pokemonType = isFromClient.readUTF();
                    String pokemonName = isFromClient.readUTF();
                    InetAddress IP = InetAddress.getLocalHost();
                    Platform.runLater(() -> ta.appendText("Trainers IP Address is: " + IP + '\n'));
                    Platform.runLater(() -> ta.appendText(pokemonName + " has checked in." + '\n'));
                    Platform.runLater(() -> ta.appendText("New Connection Made with trainer. Total connections: " + connection + '\n'));
                    connection++;
                    new Pokemon(pokemonType, pokemonName);

                    //i wanteed to set up an if statement to check if the pokemon was checked in but
                    //methods werent working
                    Platform.runLater(() -> ta.appendText(pokemonName + " has checked out." + '\n'));
                }


            } catch (IOException ex) {
                System.err.println(ex);
            }
        }).start();
}

    /**
     * This is the main method that launches args
     * @param args
     */
    public static void main(String args[]){
        launch(args);
    }
}