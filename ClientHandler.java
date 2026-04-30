import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClientHandler implements Runnable {

    private Socket socket;
    private servergui gui;
    private BufferedReader in;
    private PrintWriter out;
    private String clientname;
    private String username;
// =====================================================
// ================= CONSTRUCTOR  ======================
// =====================================================
    public ClientHandler(Socket socket, servergui gui) {
        this.socket = socket;
        this.gui = gui; // Save the reference to the server
    }


// ======================================================================================
// This method broadcast the names to every client listed in the arraylist in Server.gui
// ========================================================================================

    
    public void broadcastActiveparticepents() {
        StringBuilder userlist = new StringBuilder("List Updated:");
        
        // 1. Build the full string first
        for (ClientHandler client : servergui.clients) {
            if (client.username != null) {
                userlist.append(client.username).append(">>>>").append(client.clientname).append(",");
            }
        }
        
        // 2. Broadcast the completed string to everyone
        for (ClientHandler client : servergui.clients) {
            client.out.println(userlist.toString());
        }
    }

// ===========================================================================================================================
// The run() method comes from Java's built-in Runnable interface, 
// which requires this method so the server can execute each ClientHandler at same time on its own separate background thread.
// =========================================================================================================================

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            
            String [] firstmessageofclient = in.readLine().split(">>>>");
            username= firstmessageofclient[1];
            clientname= firstmessageofclient[0];
            
            gui.log(username + " has joined the chat!");
            broadcastActiveparticepents();
            
            String ClientMessage;
            while ((ClientMessage = in.readLine()) != null) {
                
               
                
                if (ClientMessage.startsWith("@")) {
                    int colonIndex = ClientMessage.indexOf(":");
                    
                    if (colonIndex != -1) {
                        String targetName = ClientMessage.substring(1, colonIndex); 
                        String actualMessage = ClientMessage.substring(colonIndex + 1); 

                        LocalDateTime now = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");
                        String timestamp = now.format(formatter);


                        gui.log("[ROUTER] " + this.username + " -> " + targetName + " : " + actualMessage);
                        gui.savechat(actualMessage, this.username, targetName, timestamp);
                        
                        boolean userFound = false;
                        for (ClientHandler targetClient : servergui.clients) {
                            if (targetClient.username != null && targetClient.username.equals(targetName)) {
                                targetClient.out.println(this.username + ":[" + timestamp + "]" + actualMessage);
                                userFound = true;
                                break; 
                            }
                        }
                        
                        if (!userFound) {
                            this.out.println("❌ Server: User '" + targetName + "' is offline.");
                        }
                    } else {
                        this.out.println("❌ Server: Invalid format. Use @username:message");
                    }
                } else {
                    this.out.println("🔒 Server: Global chat disabled. Use @username:message");
                }
            }
            
        } catch (Exception e) {
            gui.log("Error with client " + username + ": " + e.getMessage());
        } finally {
            try {
                servergui.clients.remove(this);
                if (username != null) {
                    gui.log(username + " has left the chat.");
                    broadcastActiveparticepents();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception e) {
                gui.log("Error closing socket: " + e);
            }
        }
    }
}