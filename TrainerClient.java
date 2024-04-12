package socket.programming;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import utitilies.Pokemon;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * This is the TrainerClient method that extends applicaiton. This creates a gridpane with some buttons
 * It also contains a textbox for the user to type information.
 * Once the buttons are pressed it sends information to the server
 */
public class TrainerClient extends Application {
    private Button send = new Button("Send to Day Care");
    private Button pickUp = new Button("Pick Up From Day Care");
    private TextField tf = new TextField();
    ComboBox combobox = new ComboBox();
    DataOutputStream isToServer = null;
    DataInputStream isFromServer = null;

    @Override
    public void start(Stage primaryStage){
        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Choose a Pokemon Type: "), 0, 0);
        gridPane.add(new Label("Pokemon's Name: "), 0, 1);

        combobox.getItems().addAll("Bulbasaur", "Charmander", "Squirtle", "Pikachu");

        gridPane.add(combobox, 1, 0);
        gridPane.add(tf, 1, 1);
        gridPane.add(send, 0, 2);
        gridPane.add(pickUp, 1, 2);

        BorderPane pane = new BorderPane();
        pane.setCenter(gridPane);

        pickUp.setDisable(true);

        //when the send to day care button is pressed this disables the combobox name box and send button
        //also enables the pick up from day care button
        send.setOnAction(e->{
            connectToServer();
            combobox.setDisable(true);
            tf.setDisable(true);
            send.setDisable(true);
            pickUp.setDisable(false);

            String name = tf.getText().toString();
            new Pokemon(combobox.getValue().toString(), name);


        });

        //when the pick up from day care button is pressed this enables the combobox, name, and send button
        //this also disables the pick up from day care button
        pickUp.setOnAction(e->{
            combobox.setDisable(false);
            tf.setDisable(false);
            send.setDisable(false);
            pickUp.setDisable(true);


        });




        //create scene and show stage
        Scene scene = new Scene(pane, 300, 80);
        primaryStage.setTitle("Pokemon Trainer");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * This is the connecttoserver method. This creates a socket with port 8000
     * this also sends the combobox and textbox values to the server.
     */
    public void connectToServer(){
        try{

            Socket socket = new Socket("localhost", 8000);

            // Create an input stream to receive data from the server
            isFromServer = new DataInputStream(socket.getInputStream());

            // Create an output stream to send data to the server
            isToServer = new DataOutputStream(socket.getOutputStream());

            //writes the strings to the server and flushes it
            isToServer.writeUTF(combobox.getValue().toString());
            isToServer.writeUTF(tf.getText().toString().trim());

            isToServer.flush();


        }
        catch(IOException ex){
            System.err.println(ex);
        }
    }

    /**
     * This is the main method that launches args
     * @param args
     */
    public static void main(String args[]){
        launch(args);
    }
}
