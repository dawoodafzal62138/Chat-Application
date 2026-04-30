import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

public class gui {

    static BufferedReader in;
    static PrintWriter out;
    static String Myname ;
    static String Myusername ;
    static JTextArea chatArea;
    static JTextField textField;
    static JPanel chatbubbles;
    static JScrollPane scrollpane3;
    static JPanel contactContainer;
    static String currentacitvechatusername;
    static ContactRow profilebanner;
    static boolean isclickonsidechat;
    static JPanel leftpanel;
    static JPanel profileCard;
    static JPanel deleteCard;
    static JPanel chatCard;
    static JPanel toppanel;
    static JPanel welcomepanel;
    static JPanel rightpanel;
    static String namefrombanner;
    static String usernamefrombanner;
    static JPanel othersprofileCard;
    static JTextField othersnameEditField;
    static JLabel othersusernameLabelProfile;
    static JTextArea othersaboutArea;
    static JPanel otheravatarPanel;
    static JButton sendbutton;

    public static void startchat(){
        

// =====================================================================================
// ============================= BASE FRAME ============================================
// =====================================================================================




        JFrame frame = new JFrame("Chat Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1470,800));
        frame.setResizable(false);
        frame.setLayout(null);
        
        // left panel
        leftpanel = new JPanel();
        leftpanel.setBackground(new Color(30, 31, 32));
        leftpanel.setBounds(70,0,300,800);
        leftpanel.setOpaque(true);
        leftpanel.setLayout(null);
        
        // right panel
        rightpanel = new JPanel();
        rightpanel.setBackground(new Color(19, 19, 20));
        rightpanel.setBounds(370,0,1100,800);
        rightpanel.setOpaque(true);
        rightpanel.setLayout(null);
        
        // top panel
        toppanel = new JPanel();
        toppanel.setBackground(new Color(30, 31, 32));
        toppanel.setBounds(0,0,1100,80);
        toppanel.setOpaque(true);
        toppanel.setLayout(null);
        
        // message bar
        RoundedPanel messagebar = new RoundedPanel(30,new Color(30, 31, 32));
        messagebar.setBounds(150,670,790,60);
        messagebar.setLayout(null);

        // textfield
        textField = new JTextField("");
        textField.setBounds(10,3,670,50);
        textField.setBackground(new Color(30,31,32));
        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.setForeground(new Color(181,184,189));
        textField.setCaretColor(new Color(181,184,189));
        Font font = new Font("Senserif",Font.PLAIN,16);
        textField.setFont(font);
        Color normal_color = new Color(245, 245, 245);
        Color hover = new Color(210, 210, 210);
        Color foregroundColor = new Color(19, 19, 20);
        
        sendbutton = new RoundedButton("Send", normal_color, hover, 25, foregroundColor);
        sendbutton.setBounds(690, 8, 90, 40);
       
        textField.addActionListener(e -> {
            String message = textField.getText();
            if (!message.isEmpty() && out != null) {
                out.println("@" + currentacitvechatusername + ":" + message);
                
                chatbubbles.add(new ContactRow(Myname, message, time(), true));
                chatbubbles.revalidate();
                chatbubbles.repaint();
                scrollToBottom();
                textField.setText("");
            }
        });
        sendbutton.addActionListener(e -> {
            String message = textField.getText();
            if (!message.isEmpty() && out != null) {
                out.println("@" + currentacitvechatusername + ":" + message);

                chatbubbles.add(new ContactRow(Myname, message, time(), true));
                chatbubbles.revalidate();
                chatbubbles.repaint();
                scrollToBottom();
                textField.setText("");
            }
        });


        chatCard = new JPanel(null);
        chatCard.setBackground(new Color(30, 31, 32));
        chatCard.setBounds(0, 0, 300, 800);

        // label
        JLabel chatlabel = new JLabel();
        Font font2 = new Font("Senserif",Font.BOLD,30);
        chatlabel.setFont(font2);
        chatlabel.setText("Chats");
        chatlabel.setForeground(new Color(220,220,220));
        chatlabel.setBounds(15,0,100,100);
        
        // Jpanel for serach area
        RoundedPanel searchbar = new RoundedPanel(30,new Color(60,60,60));
        searchbar.setBounds(25,90,250,40);
        searchbar.setLayout(null);      
        
        // textfield
        JTextField textFieldforsearchbar = new JTextField("Search ");
        textFieldforsearchbar.setBounds(50,0,180,36);
        textFieldforsearchbar.setBackground(new Color(60,60,60));
        textFieldforsearchbar.setBorder(BorderFactory.createEmptyBorder());
        textFieldforsearchbar.setForeground(new Color(181,184,189));
        Font font3 = new Font("Senserif",Font.PLAIN,20);
        textFieldforsearchbar.setFont(font3);

        // Serach icon
        ImageIcon icon = new ImageIcon("logos/logo.png");
        Image scaledImage = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon finalLogo = new ImageIcon(scaledImage);
        JLabel iconlabel = new JLabel(finalLogo);
        iconlabel.setBackground(new Color(60,60,60));
        iconlabel.setBounds(5,2,40,40);


        messagebar.add(textField);
        messagebar.add(sendbutton);
        searchbar.add(textFieldforsearchbar);
        searchbar.add(iconlabel);

        // 1. Create a container for the list
        contactContainer = new JPanel();
        contactContainer.setBackground(new Color(30, 31, 32));
        // BoxLayout automatically stacks our ContactRows vertically!
        contactContainer.setLayout(new BoxLayout(contactContainer, BoxLayout.Y_AXIS));

        // 3. Wrap the container in a Scroll Pane
        JScrollPane scrollPane = new JScrollPane(contactContainer);
        // Position it below the search bar (y=150) and fill the rest of the height
        scrollPane.setBounds(0, 150, 300, 650); 
        scrollPane.setBorder(null); // Remove the ugly default Swing border
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0)); // Make scrollbar thin
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // 4. Add the scroll pane to the left panel
        chatCard.add(chatlabel);
        chatCard.add(searchbar);
        chatCard.add(scrollPane);
        // profile bar
        profilebanner = new ContactRow(Myname, currentacitvechatusername,"Online", 28);
        profilebanner.setBounds(50,5,600,80);

        chatbubbles = new JPanel();
        chatbubbles.setLayout(new BoxLayout(chatbubbles, BoxLayout.Y_AXIS));
        chatbubbles.setBackground(new Color(19, 19, 20));
        chatbubbles.setOpaque(true);

        scrollpane3 = new JScrollPane(chatbubbles);
        scrollpane3.setBounds(10, 90, 1065, 570); 
        scrollpane3.setBorder(null); // Remove the ugly default Swing border
        scrollpane3.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0)); // Make scrollbar thin
        scrollpane3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane3.getViewport().setBackground(new Color(19,19,20));
        scrollpane3.getViewport().setOpaque(true);
        
// ===============================================================
// =============== Welcome Panel ================================
// ===============================================================
        welcomepanel = new JPanel();
        welcomepanel.setBackground(new Color(19, 19, 20));
        welcomepanel.setBounds(370,0,1100,800);
        welcomepanel.setOpaque(true);
        welcomepanel.setLayout(null);

        JLabel welcomelabe = new JLabel("Welcome "+Myname+" !");
        Font fontOfWelcomelabel=  new Font("Georgia", Font.BOLD, 60);
        welcomelabe.setFont(fontOfWelcomelabel);
        welcomelabe.setBounds(0, 300, 1100, 50);
        welcomelabe.setBackground(new Color(30,31,32));
        welcomelabe.setForeground(new Color(240, 240, 240  ));
        welcomelabe.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        JLabel greetinglabel = new JLabel("Nice to see you Here !");
        Font fontfontOfGreetinglabel2=  new Font("Georgia", Font.PLAIN, 40);
        greetinglabel.setFont(fontfontOfGreetinglabel2);
        greetinglabel.setBounds(0, 370, 1100, 50);
        greetinglabel.setBackground(new Color(150, 150, 150));
        greetinglabel.setForeground(new Color(230,230,230));
        greetinglabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        


// ===============================================================
// =============== Left most panel ================================
// ===============================================================

        

        
        JPanel navpanel = new JPanel();
        navpanel.setBackground(new Color(22, 23, 24)); // Slightly darker than leftpanel for a sleek 3D effect
        navpanel.setBounds(0, 0, 70, 800);
        navpanel.setLayout(null);

        // 1. Chat Icon
        ImageIcon chatIconRaw = new ImageIcon("logos/chat.png"); 
        
        JLabel chatIcon = new JLabel(chatIconRaw); // THE FIX: Just pass the variable directly!
        chatIcon.setBounds(20, 120, 32, 32); // Perfect 32x32 bounds
        chatIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // 2. Profile Icon
        ImageIcon profileIconRaw = new ImageIcon("logos/user.png"); 
        JLabel profileIcon = new JLabel(profileIconRaw); // THE FIX
        profileIcon.setBounds(20, 50, 32, 32); // THE FIX: Changed from 30x30 to 32x32
        profileIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // 3. Settings Icon 
        ImageIcon deleteIconRaw = new ImageIcon("logos/delete.png"); 
        JLabel deleteIcon = new JLabel(deleteIconRaw); // THE FIX
        deleteIcon.setBounds(20, 630, 32, 32); // THE FIX: Changed from 30x30 to 32x32
        deleteIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // 3. logout Icon 
        ImageIcon logoutIconRaw = new ImageIcon("logos/logout.png"); 
        JLabel logoutIcon = new JLabel(logoutIconRaw); // THE FIX
        logoutIcon.setBounds(20, 700, 32, 32); // THE FIX: Changed from 30x30 to 32x32
        logoutIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
       
        navpanel.add(chatIcon);
        navpanel.add(profileIcon);
        navpanel.add(deleteIcon);
        navpanel.add(logoutIcon);


// ===============================================================
// =============== CARD 2: THE PROFILE VIEW ======================
// ===============================================================


        profileCard = new JPanel(null);
        profileCard.setBackground(new Color(30, 31, 32));
        profileCard.setBounds(0,0,300,800);

        // 1. Top Header
        JLabel profileTitle = new JLabel("My Profile");
        profileTitle.setFont(new Font("Georgia", Font.BOLD, 32));
        profileTitle.setHorizontalAlignment(SwingConstants.CENTER);
        profileTitle.setForeground(new Color(240, 240, 240));
        profileTitle.setBounds(0, 70, 300, 40);
        profileCard.add(profileTitle);

        // 2. The Big Avatar (Custom painted circle with Initial)
        JPanel avatarPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Draw circle
                g2.setColor(new Color(80, 100, 120)); // Muted blue-gray
                g2.fillOval(0, 0, 100, 100);

                // Draw initial perfectly centered
                if (Myname != null && !Myname.isEmpty()) {
                    String initial = Myname.substring(0, 1).toUpperCase();
                    g2.setColor(Color.WHITE);
                    g2.setFont(new Font("SansSerif", Font.BOLD, 45));
                    FontMetrics fm = g2.getFontMetrics();
                    int x = (100 - fm.stringWidth(initial)) / 2;
                    int y = ((100 - fm.getHeight()) / 2) + fm.getAscent();
                    g2.drawString(initial, x, y);
                }
                g2.dispose();
            }
        };
        avatarPanel.setOpaque(false);
        // Center the 100x100 avatar in the 300px panel: (300 - 100) / 2 = 100
        avatarPanel.setBounds(100, 150, 100, 100); 
        profileCard.add(avatarPanel);

        // 3. Display Name Field (Editable)
        JTextField nameEditField = new JTextField(Myname);
        nameEditField.setFont(new Font("Segoe UI", Font.BOLD, 22));
        nameEditField.setForeground(new Color(240, 240, 240));
        nameEditField.setBackground(new Color(40, 41, 42)); // Slightly lighter box to show it's editable
        nameEditField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        nameEditField.setHorizontalAlignment(SwingConstants.CENTER);
        nameEditField.setEditable(false);
        nameEditField.setBounds(40, 280, 220, 40);
        profileCard.add(nameEditField);

       

        // 4. Username Label (Read-Only)
        JLabel usernameLabelProfile = new JLabel("@" + Myusername);
        usernameLabelProfile.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        usernameLabelProfile.setForeground(new Color(150, 150, 150));
        usernameLabelProfile.setHorizontalAlignment(SwingConstants.CENTER);
        usernameLabelProfile.setBounds(0, 330, 300, 20);
        profileCard.add(usernameLabelProfile);
        File biopath = new File("bio/"+Myusername+".txt");
        String bio2="";
        if (biopath.exists()){
            try(BufferedReader read = new BufferedReader(new FileReader(biopath))){
                String line;
                while((line=read.readLine()) != null){
                    bio2 += line + "\n"; // Added newline!
                } 
            }catch(Exception q){
                System.out.println("Error: "+q);
            }
        }
        if (bio2.isEmpty()) { 
            bio2 = "Available"; 
        } // Fallback if file is empty


        // 5. "About" Section
        JLabel aboutTitle = new JLabel("ABOUT");
        aboutTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        aboutTitle.setForeground(new Color(120, 120, 120));
        aboutTitle.setBounds(20, 380, 100, 20);
        profileCard.add(aboutTitle);

        JTextArea aboutArea = new JTextArea(bio2);
        aboutArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        aboutArea.setForeground(new Color(220, 220, 220));
        aboutArea.setBackground(new Color(40, 41, 42));
        aboutArea.setLineWrap(true);
        aboutArea.setWrapStyleWord(true);
        aboutArea.setCaretColor(Color.WHITE);
        aboutArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JScrollPane aboutScroll = new JScrollPane(aboutArea);
        aboutScroll.setBorder(null);
        aboutScroll.setBounds(20, 410, 260, 120);
        profileCard.add(aboutScroll);

        // 6. Save Button (Using your custom RoundedButton class!)
        Color normalGreen = new Color(35, 134, 54);
        Color hoverGreen = new Color(46, 160, 67);
        RoundedButton saveProfileBtn = new RoundedButton("Save Changes", normalGreen, hoverGreen, 30, Color.WHITE);
        saveProfileBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        saveProfileBtn.setBounds(40, 560, 220, 45);
        profileCard.add(saveProfileBtn);

        
        

        // Add a click event to the save button
        saveProfileBtn.addActionListener(e -> {
            String description = aboutArea.getText().trim();
            if(!description.isEmpty()) {
              try(FileWriter writer = new FileWriter("bio/"+Myusername+".txt")){
                writer.write(description);
                saveProfileBtn.setText("Updated!");
                
                // 2. Create a Background Timer for 2000 milliseconds (2 seconds)
                Timer timer = new Timer(2000, event -> {
                    // 3. This code runs exactly 2 seconds later!
                    saveProfileBtn.setText("Save Changes");
                });
                timer.setRepeats(false); 
                timer.start();
              }catch (Exception abc){
                System.out.println("Error: "+abc);
              }
         }
        });    
        
        
        
        
// ===============================================================
// =============== CARD 4: DELETE ACCOUNT VIEW ===================
// ===============================================================
       


        deleteCard = new JPanel(null);
        deleteCard.setBackground(new Color(30, 31, 30));
        deleteCard.setBounds(0, 0, 300, 800);

        // 1. Danger Header
        JLabel deleteTitle = new JLabel("Delete Account");
        deleteTitle.setFont(new Font("Georgia", Font.BOLD, 28));
        deleteTitle.setForeground(new Color(235, 87, 87)); // Warning Red
        deleteTitle.setBounds(20, 70, 260, 30);
        deleteCard.add(deleteTitle);

        // 2. Warning Text
        JLabel warningLabel = new JLabel("<html>This action is permanent and cannot be undone. All your data will be lost.</html>");
        warningLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        warningLabel.setForeground(new Color(180, 180, 180));
        warningLabel.setBounds(20, 120, 260, 40);
        deleteCard.add(warningLabel);

        // 3. Username Display
        JLabel userLabel = new JLabel("Deleting:" );
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        userLabel.setForeground(new Color(240, 240, 240));
        userLabel.setBounds(20, 200, 260, 20);
        deleteCard.add(userLabel);
        
        JLabel nameLabel = new JLabel("Name : "+Myname );
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        nameLabel.setForeground(new Color(240, 240, 240));
        nameLabel.setBounds(20, 225, 260, 20);
        deleteCard.add(nameLabel);

        JLabel usernameLabel = new JLabel("Username :  @"+Myusername );
        usernameLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        usernameLabel.setForeground(new Color(240, 240, 240));
        usernameLabel.setBounds(20, 245, 260, 20);
        deleteCard.add(usernameLabel);

        // 4. Password Field 1
        JLabel passLabel1 = new JLabel("Enter Password");
        passLabel1.setFont(new Font("Segoe UI", Font.BOLD, 12));
        passLabel1.setForeground(new Color(120, 120, 120));
        passLabel1.setBounds(20, 285, 260, 20);
        deleteCard.add(passLabel1);

        JPasswordField passField1 = new JPasswordField();
        passField1.setBackground(new Color(40, 41, 42));
        passField1.setForeground(Color.WHITE);
        passField1.setCaretColor(Color.WHITE);
        passField1.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        passField1.setBounds(20, 305, 260, 40);
        deleteCard.add(passField1);

        // 5. Password Field 2 (Confirmation)
        JLabel passLabel2 = new JLabel("Confirm Password");
        passLabel2.setFont(new Font("Segoe UI", Font.BOLD, 12));
        passLabel2.setForeground(new Color(120, 120, 120));
        passLabel2.setBounds(20, 365, 260, 20);
        deleteCard.add(passLabel2);

        JPasswordField passField2 = new JPasswordField();
        passField2.setBackground(new Color(40, 41, 42));
        passField2.setForeground(Color.WHITE);
        passField2.setCaretColor(Color.WHITE);
        passField2.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        passField2.setBounds(20, 390, 260, 40);
        deleteCard.add(passField2);

        // 6. Error Message Label (Hidden by default)
        JLabel errorLabel = new JLabel("");
        errorLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        errorLabel.setForeground(new Color(235, 87, 87)); // Red text
        errorLabel.setBounds(20, 440, 260, 20);
        deleteCard.add(errorLabel);
        
        // 7. Delete Button
        Color redNormal = new Color(200, 50, 50);
        Color redHover = new Color(220, 70, 70);
        RoundedButton confirmDeleteBtn = new RoundedButton("Permanently Delete", redNormal, redHover, 30, Color.WHITE);
        confirmDeleteBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        confirmDeleteBtn.setBounds(30, 475, 240, 45);
        deleteCard.add(confirmDeleteBtn);

        
        // Error panel
        
        RoundedPanel errorpanel1 = new RoundedPanel(30, new Color(220,50,50));
        errorpanel1.setBounds(20,550,260,30);
        errorpanel1.setLayout(null);

        JLabel errorlabel = new JLabel();
        errorlabel.setBounds(0, 0, 260, 30);
        errorlabel.setForeground(new Color(255,255,255));
        errorlabel.setHorizontalAlignment(SwingConstants.CENTER);

        errorpanel1.add(errorlabel);
        deleteCard.add(errorpanel1);
        errorpanel1.setVisible(false);


        confirmDeleteBtn.addActionListener(e->{
            File inputfile = new File("data/database.txt"); 
            File tempfile = new File("data/temp.txt"); 
            String password1=new String (passField1.getPassword()); 
            String password2=new String (passField2.getPassword()); 
            Boolean passwoordcorrect = false;

            if (password1.isEmpty() || password2.isEmpty() ){
                errorlabel.setText("Please fill out all fields.");
                errorpanel1.setVisible(true);
                return;
            }
            if (!(password1.equals(password2)) ){
                errorlabel.setText("Passwords doesn't match!");
                errorpanel1.setVisible(true);
                return;
            }

            try(BufferedReader reader = new BufferedReader(new FileReader(inputfile));
                FileWriter writer = new FileWriter(tempfile)){
                    String currentline;
                    while ((currentline = reader.readLine()) != null){
                        String [] data = currentline.split(","); 
                        if ((data.length>=3) && data[1].equals(Myusername)){
                            if((password1.equals(data[2])) && (password2.equals(data[2]))){
                                passwoordcorrect=true;
                                continue;
                            }else{
                                writer.write(currentline+"\n");
                            }
                        }else{
                            writer.write(currentline+"\n");
                        }
                    }


            }catch(Exception xyz){
                System.out.println("Error: " + xyz);
            }



            if (passwoordcorrect){
                inputfile.delete();
                tempfile.renameTo(inputfile);
                errorpanel1.setVisible(false);
                frame.dispose();
                new loginpage();
            }else{
                tempfile.delete();
                errorlabel.setText("Account deletion Failed, Incorrect Password.");
                errorpanel1.setVisible(true);
            }

        });
    



// =====================================================================================
// ======================== ICONS ACTIONS LISTENERS=====================================
// =====================================================================================

        profileIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                chatCard.setVisible(false);    // Hide Chat
                deleteCard.setVisible(false);  // Hide Delete
                profileCard.setVisible(true);
                rightpanel.setVisible(false);
                welcomepanel.setVisible(true);
                leftpanel.revalidate();
                leftpanel.repaint();
            }
        });
        
        chatIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                profileCard.setVisible(false); // Hide Profile
                deleteCard.setVisible(false);  // Hide Delete
                chatCard.setVisible(true);
                rightpanel.setVisible(false);
                welcomepanel.setVisible(true);
                leftpanel.revalidate();
                leftpanel.repaint();
            }
        });
        logoutIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new loginpage();
            }
        });
        
        deleteIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                chatCard.setVisible(false);    // Hide Chat
                profileCard.setVisible(false); // Hide Profile
                deleteCard.setVisible(true);
                rightpanel.setVisible(false);
                welcomepanel.setVisible(true);
                leftpanel.revalidate();
                leftpanel.repaint();
            }
         });


// ===============================================================
// =============== CARD 2: THE OTHER PROFILE VIEW =================
// ===============================================================
        

        othersprofileCard = new JPanel(null);
        othersprofileCard.setBackground(new Color(30, 31, 32));
        othersprofileCard.setBounds(0,0,300,800);

        // 1. Top Header
        JLabel othersprofileTitle = new JLabel("Profile");
        othersprofileTitle.setFont(new Font("Georgia", Font.BOLD, 32));
        othersprofileTitle.setHorizontalAlignment(SwingConstants.CENTER);
        othersprofileTitle.setForeground(new Color(240, 240, 240));
        othersprofileTitle.setBounds(0, 70, 300, 40);
        othersprofileCard.add(othersprofileTitle);

        // 2. The Big Avatar (Custom painted circle with Initial)
        otheravatarPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Draw circle
                g2.setColor(new Color(80, 100, 120)); // Muted blue-gray
                g2.fillOval(0, 0, 100, 100);

                // Draw initial perfectly centered
                if (namefrombanner != null && !namefrombanner.isEmpty()) {
                    String initial = namefrombanner.substring(0, 1).toUpperCase();
                    g2.setColor(Color.WHITE);
                    g2.setFont(new Font("SansSerif", Font.BOLD, 45));
                    FontMetrics fm = g2.getFontMetrics();
                    int x = (100 - fm.stringWidth(initial)) / 2;
                    int y = ((100 - fm.getHeight()) / 2) + fm.getAscent();
                    g2.drawString(initial, x, y);
                }
                g2.dispose();
            }
        };
        otheravatarPanel.setOpaque(false);
        // Center the 100x100 avatar in the 300px panel: (300 - 100) / 2 = 100
        otheravatarPanel.setBounds(100, 150, 100, 100); 
        othersprofileCard.add(otheravatarPanel);

        // 3. Display Name Field (Editable)
        othersnameEditField = new JTextField(namefrombanner);
        othersnameEditField.setFont(new Font("Segoe UI", Font.BOLD, 22));
        othersnameEditField.setForeground(new Color(240, 240, 240));
        othersnameEditField.setBackground(new Color(40, 41, 42)); // Slightly lighter box to show it's editable
        othersnameEditField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        othersnameEditField.setHorizontalAlignment(SwingConstants.CENTER);
        othersnameEditField.setEditable(false);
        othersnameEditField.setBounds(40, 280, 220, 40);
        othersprofileCard.add(othersnameEditField);

       

        // 4. Username Label (Read-Only)
        othersusernameLabelProfile = new JLabel("@" + usernamefrombanner);
        othersusernameLabelProfile.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        othersusernameLabelProfile.setForeground(new Color(150, 150, 150));
        othersusernameLabelProfile.setHorizontalAlignment(SwingConstants.CENTER);
        othersusernameLabelProfile.setBounds(0, 330, 300, 20);
        othersprofileCard.add(othersusernameLabelProfile);
        File biopath2 = new File("bio/"+usernamefrombanner+".txt");
        String bio2_="";
        if (biopath2.exists()){
        try(BufferedReader read = new BufferedReader(new FileReader(biopath2))){
            String line_;
            while((line_=read.readLine()) != null){
                bio2_+=line_+"\n";
            } 


        }catch(Exception q){
            System.out.println("Error: "+q);
        }}else{
            bio2_="Available";
        }



        // 5. "About" Section
        JLabel otheraboutTitle = new JLabel("ABOUT");
        otheraboutTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        otheraboutTitle.setForeground(new Color(120, 120, 120));
        otheraboutTitle.setBounds(20, 380, 100, 20);
        othersprofileCard.add(otheraboutTitle);

        othersaboutArea = new JTextArea(bio2_);
        othersaboutArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        othersaboutArea.setForeground(new Color(220, 220, 220));
        othersaboutArea.setBackground(new Color(40, 41, 42));
        othersaboutArea.setLineWrap(true);
        othersaboutArea.setWrapStyleWord(true);
        othersaboutArea.setEditable(false);
        othersaboutArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JScrollPane otheraboutScroll = new JScrollPane(othersaboutArea);
        otheraboutScroll.setBorder(null);
        otheraboutScroll.setBounds(20, 410, 260, 120);
        othersprofileCard.add(otheraboutScroll);

         
        
//======================================================================================= 
//===================================ADDING ELEMENTS===================================== 
//======================================================================================= 
        
        welcomepanel.add(greetinglabel);
        welcomepanel.add(welcomelabe);
        welcomepanel.setVisible(true);
        
        
        // Left panel
        leftpanel.add(profileCard);
        leftpanel.add(deleteCard);
        leftpanel.add(chatCard);
        leftpanel.add(othersprofileCard);
        
        deleteCard.setVisible(false);
        profileCard.setVisible(false);
        othersprofileCard.setVisible(false);
        
        // Right panel 
        rightpanel.add(scrollpane3);
        rightpanel.add(toppanel);
        rightpanel.add(messagebar);
        rightpanel.setVisible(false);
        
        // adding elements
        toppanel.add(profilebanner);
        frame.add(navpanel);
        frame.add(leftpanel);
        frame.add(welcomepanel);
        frame.add(rightpanel);
        frame.setVisible(true);
        connectToServer();
    }

    // THE BULLETPROOF AUTO-SCROLL
    public static void scrollToBottom() {
        chatbubbles.revalidate();
        chatbubbles.repaint();
        
        // The "Double InvokeLater" trick guarantees the math is finished before scrolling
        SwingUtilities.invokeLater(() -> {
            SwingUtilities.invokeLater(() -> {
                JScrollBar verticalBar = scrollpane3.getVerticalScrollBar();
                verticalBar.setValue(verticalBar.getMaximum());
            });
        });
    }



// ==========================================
// Chat histor loader from chat/ 
// ==========================================
 public static void loadChatHistory(String targetUsername) {
        
        // Calculate the exact alphabetical filename the server uses
        String chatFileName;
        if (Myusername.compareTo(targetUsername) < 0) {
            chatFileName = Myusername + "_" + targetUsername + ".txt";
        } else {
            chatFileName = targetUsername + "_" + Myusername + ".txt";
        }
        
        File historyFile = new File("chats/" + chatFileName);
        
        if (!historyFile.exists()) {
            return; 
        }

        try (BufferedReader br = new BufferedReader(new FileReader(historyFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                // NEW FORMAT: "dawood12 to hishu12 [30 Apr 2026, 12:51 PM] :: hi"
                int toindex = line.indexOf(" to ");
                int colonsindex = line.indexOf(" :: ");
                
                if (toindex != -1 && colonsindex != -1) {
                    // Extract the sender
                    String msgSender = line.substring(0, toindex).trim();
                    
                    // Extract the middle part which now contains: "hishu12 [time]"
                    String midPart = line.substring(toindex + 4, colonsindex).trim();
                    String timestamp = "";
                    
                    // Look for the brackets to pull out the exact time
                    int bracketOpen = midPart.indexOf("[");
                    int bracketClose = midPart.indexOf("]");
                    
                    if (bracketOpen != -1 && bracketClose != -1) {
                        timestamp = midPart.substring(bracketOpen + 1, bracketClose);
                    }
                    
                    // Extract the actual message text
                    String msgBody = line.substring(colonsindex + 4).trim();
                    
                    if (msgSender.equals(Myusername)) {
                        chatbubbles.add(new ContactRow(Myname, msgBody, timestamp, true)); 
                    } else {
                        chatbubbles.add(new ContactRow(targetUsername, msgBody, timestamp, false)); 
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading chat history: " + e);
        }
    }


    public static void click(String targetName, String targetUsername) {
        
        namefrombanner = targetName;
        usernamefrombanner = targetUsername;
        
        othersnameEditField.setText(targetName);
        othersusernameLabelProfile.setText("@" + targetUsername);
        
        otheravatarPanel.repaint();

        // 3. Read their specific bio file 
        File biopath2 = new File("bio/" + targetUsername + ".txt");
        String bio2 = ""; 
        if (biopath2.exists()){
            try (BufferedReader read = new BufferedReader(new FileReader(biopath2))) {
                String line;
                while ((line = read.readLine()) != null) {
                    bio2 += line + "\n"; // Added newline!
                }
            } catch(Exception q) {
                System.out.println("Error: " + q);
            }
        }
        if (bio2.isEmpty()) { 
            bio2 = "Available"; 
        } // Fallback
        othersaboutArea.setText(bio2);
        
        // 4. Swap the cards visually
        deleteCard.setVisible(false);
        profileCard.setVisible(false);
        chatCard.setVisible(false);
        othersprofileCard.setVisible(true);

        leftpanel.revalidate();
        leftpanel.repaint();
    }






    // ContactRow.java will call this when clicked!
    public static void openChat(String username,String name) {
        currentacitvechatusername = username;
        String currentactivename = name;

        rightpanel.setVisible(true);
        welcomepanel.setVisible(false);

        if (profilebanner != null){
            toppanel.remove(profilebanner);
        }        

        profilebanner = new ContactRow(currentactivename,currentacitvechatusername, "Online", 28);
        profilebanner.setBounds(50,5,600,80);
        toppanel.add(profilebanner);
        toppanel.revalidate();
        toppanel.repaint();
        
        
        chatbubbles.removeAll();
        loadChatHistory(currentacitvechatusername); 
        scrollToBottom(); 
        
        System.out.println("Now chatting with: " + currentacitvechatusername);
    }

public static String time(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm:ss a");
        String timestamp = now.format(formatter);
        return timestamp;
}


// ====================================================================================
// ==============================SERVER CONNECTIONS===================================
// ====================================================================================
   public static void connectToServer() {
        try {
            Socket socket = new Socket("localhost", 5000);
            chatbubbles.add(new ContactRow("System","Connected to the server!",time(),false));
            chatbubbles.revalidate();
            chatbubbles.repaint();

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            // Tell the server who we are (ORIGINAL: Just Myname)
            out.println(Myname+">>>>"+Myusername);

            // Start a BACKGROUND THREAD to listen for messages
            // This background thread running behind the scenes. It constantly listens to the network socket. When your friend sends a message, this thread catches it instantly.
            new Thread(() -> {
                try {
                    String serverResponse;
                    while ((serverResponse = in.readLine()) != null) {
                        final String finalMsg = serverResponse;
                        SwingUtilities.invokeLater(() -> {
                            if (finalMsg.startsWith("List Updated:")) {
                                
                               String namesonly= finalMsg.substring(13);
                               String [] namesfromupdatedlist = namesonly.split(",");
                                contactContainer.removeAll();
                                
                                for (String name_username: namesfromupdatedlist){
                                    String username = name_username.substring(0,name_username.indexOf(">>>>"));
                                    String name = name_username.substring(name_username.indexOf(">>>>")+4);
                                    if (username.equalsIgnoreCase(Myusername)){
                                        continue;
                                    }
                                    if (!username.trim().isEmpty()) {
                                        // Uses your 1st Constructor (Sidebar Row)
                                        contactContainer.add(new ContactRow( name, username)); 
                                    }
                                }
                                contactContainer.revalidate();
                                contactContainer.repaint();
                        
                            } else {
                                    int colonIndex = finalMsg.indexOf(":");
                                    if (colonIndex != -1) {
                                        String senderUsername = finalMsg.substring(0, colonIndex);
                                        String Msg = finalMsg.substring(colonIndex + 1);
                                        
                                        String timestamp = "";
                                        String formattedmsg = Msg;
                                        
                                        // Extract the timestamp out of the hidden brackets
                                        if (Msg.startsWith("[")) {
                                            int closeBracket = Msg.indexOf("]");
                                            if (closeBracket != -1) {
                                                timestamp = Msg.substring(1, closeBracket);
                                                formattedmsg = Msg.substring(closeBracket + 1);
                                            }
                                        }
                                        
                                        if (senderUsername.equals(currentacitvechatusername) || senderUsername.contains("Server")) {
                                            // Pass the extracted timestamp to the bubble!
                                            chatbubbles.add(new ContactRow(senderUsername, formattedmsg, timestamp, false));
                                            chatbubbles.revalidate();
                                            chatbubbles.repaint();
                                            scrollToBottom();
                                        } else {
                                            System.out.println("Background message received from: " + senderUsername);
                                        }
                                    }
                                }
                        });
                    }
                } catch (Exception e) {
                    SwingUtilities.invokeLater(() -> chatbubbles.add(new ContactRow("System","Disconnected from server.\n",time(),false)));
                    chatbubbles.revalidate();
                    chatbubbles.repaint();
                }
            }).start();

        } catch (Exception e) {
            chatbubbles.add(new ContactRow("System", "Could not connect to server. Is it running?\n",time(),false));
            chatbubbles.revalidate();
            chatbubbles.repaint();
        }}}