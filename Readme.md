# Java Socket Chat Application

A sleek, dark-themed, real-time desktop chat application built entirely in Java using Swing for the graphical user interface and the Java Socket API for networking. 

This project demonstrates a multi-client, multithreaded client-server architecture, complete with user authentication, persistent chat history, and dynamic user profiles.

## ✨ Features

*   **Custom Dark Theme UI:** Designed from scratch using Java Swing, featuring custom-painted rounded buttons (`RoundedButton`), rounded panels (`RoundedPanel`), and dynamic message bubbles (`ContactRow`).
*   **User Authentication:** Fully functional Sign-Up and Login system that reads and writes user credentials to a local flat-file database.
*   **Real-Time Messaging:** Instant message routing powered by a multithreaded Server (`servergui`) and Client Handlers (`ClientHandler`). 
*   **Persistent Chat History:** Messages are logged and saved locally. When you reopen a chat with a user, your entire conversation history is seamlessly loaded.
*   **Dynamic User Profiles:** Users can view their profiles, set custom bios (saved persistently), and view other users' profiles.
*   **Live Online List:** The server actively broadcasts the list of currently connected participants to all active clients.
*   **Account Management:** Users can permanently delete their accounts and associated data (secured via password confirmation).

## 🛠️ Tech Stack

*   **Language:** Java (JDK 8+)
*   **UI Framework:** Java Swing & AWT (Abstract Window Toolkit)
*   **Networking:** Java `java.net.Socket` and `java.net.ServerSocket`
*   **Concurrency:** Java `Thread` and `Runnable`
*   **Data Storage:** Local File I/O (`BufferedReader`, `FileWriter`)

## 📁 Project Structure

This application is split into two main logical components:

1.  **The Server (`servergui.java`, `ClientHandler.java`)**
    *   Listens on port `5000` for incoming connections.
    *   Spawns a new background thread (`ClientHandler`) for every user that logs in.
    *   Routes private messages using the `@username:message` format.
    *   Logs server activity and saves chat transcripts into the `chats/` directory.

2.  **The Client (`loginpage.java`, `gui.java`)**
    *   **`loginpage.java`:** The entry point for the user. Handles authentication and launches the main GUI upon success.
    *   **`gui.java`:** The main chat interface. Connects to `localhost:5000` and contains a background thread to continuously listen for incoming messages.
    *   **Custom Components:** `ContactRow.java` (chat bubbles/sidebar items), `RoundedButton.java`, and `RoundedPanel.java`.

## 🚀 Getting Started

### Prerequisites
*   Java Development Kit (JDK) installed on your machine.

### Required Directory Structure
Because this application uses a local flat-file database system, **you must create the following empty folders** in the root directory of your project before running the application, otherwise, it may crash when trying to save files:
```text
/your-project-folder
 ├── /bio        (Stores user bios)
 ├── /chats      (Stores chat histories)
 ├── /data       (Create a blank 'database.txt' inside this folder)
 └── /logos      (Contains UI icons: chat.png, user.png, delete.png, logout.png, logo.png)