# How to Run the Java Socket Chat App

This guide explains how to compile the code, start the chat server, and connect clients so you can begin chatting. 

*(Note: The application is configured to automatically create any necessary database and chat history folders the first time it runs!)*

---

## 1. Compiling the Code

Open your terminal (or command prompt) and navigate to your main project folder where all the `.java` files are located.

Compile all Java files by running:

```bash
javac *.java
```


## 2. Starting the Server
The server MUST be running before any client can connect.

In your terminal, run the following command:
```
java servergui
```
You should see a message confirming the server has started:

`[12:00:00 PM] Server is running on port 5000 and waiting...`

Keep this terminal window open.


## 3. Starting a Client (User)
To open the chat application, you need to launch a client.

Open a NEW terminal window (leave the server running in the first one), navigate to your project folder, and run:

```Bash
java loginpage
```
This will open the Login/Sign-Up window.

## 4. Testing with Multiple Users
To chat back and forth, you need at least two clients running on your computer.

#### 1. Open yet another (third) terminal window.

#### 2. Navigate to your project folder.

#### 3. Run java loginpage again.

#### 4. You now have two separate chat windows open side-by-side.

#### 5.Sign up with a different username in each window, log in, and start chatting!