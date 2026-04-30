import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class servergui {
    
    // Thelist of all connected clients
    public static ArrayList<ClientHandler> clients = new ArrayList<>();
// =====================================================================
// This background thread runs the while(true) loop for serversocket.accept(). 
// Its only job is to sit and wait for new users to connect to Port 5000 without blocking the rest of the server application.
// =====================================================================


public servergui() {
        new Thread(() -> Startserver()).start();
    }
  
    
// ==============================================================================
//  logs the message login and logout of the client and the messages 
// ==============================================================================

    public void log(String Message) {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");
        String timestamp = now.format(formatter);
        String logMessage = "[" + timestamp + "] " + Message;
        
        System.out.println(logMessage);
        
        try (FileWriter writer = new FileWriter("logs.txt", true)) {
            writer.append(logMessage + "\n");
        } catch(Exception e) {
            System.out.println("Error writing log: " + e);
        }
    }

// ==============================================================================
// save the chat in a txt file so it can easily read after application boots up
// =============================================================================

    public void savechat(String message,String sender,String reciever,String timestamp){
        String chatFileName;
        if (sender.compareTo(reciever) < 0) {
            chatFileName = sender + "_" + reciever + ".txt";
        } else {
            chatFileName = reciever + "_" + sender + ".txt";
        }
        
        // Format the message
        String formattedMessage = sender + " to " + reciever + " [" + timestamp + "] :: " + message;
        try(FileWriter writer = new FileWriter("chats/"+chatFileName,true)){
            writer.append(formattedMessage+"\n");
        }catch (Exception e){
            System.out.println("Error: "+e);
        }
}
//============================================================= 
// Core method that starts the server on a specific port
//============================================================= 

    public void Startserver() {
        try (ServerSocket serversocket = new ServerSocket(5000)) {
            log("Server is running on port 5000 and waiting...");
            
            while (true) {
                Socket socket = serversocket.accept();
                ClientHandler clientthread = new ClientHandler(socket, this);
                clients.add(clientthread);
                
                
                
                // Every single time a new user logs in, the server creates a brand new thread.
                //  This thread runs the ClientHandler.java  which constantly listens for that specific user's incoming messages.
                
                new Thread(clientthread).start();
            }
       } catch (IOException e) {
            log("Server Error: " + e);
       }
    }
    
    public static void main(String[] args) {
        new servergui();
    }
}