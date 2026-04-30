import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.*;

import org.w3c.dom.events.MouseEvent;
public class loginpage extends JFrame {
    
    static String [] credentials;
    public loginpage(){
       
//====================================================================    
// =================== Frame =========================================
//====================================================================    
        setTitle("Getting Started");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1400,800));
        setResizable(false);
        setLayout(null);

        

//=======================================================================    
// ====== right panel , left panel (welcome label and two buttons) =======
//=======================================================================    

        JPanel leftpanel = new JPanel();
        leftpanel.setLayout(null);
        leftpanel.setBounds(0,0,400,800);
        leftpanel.setBackground(new Color(30,31,32));
        
        
        JPanel rightpanel = new JPanel();
        rightpanel.setLayout(null);
        rightpanel.setBounds(400,0,1000,800);
        rightpanel.setBackground(new Color(19, 19, 20));

        JLabel label1 = new JLabel("Welcome");
        Font font1=  new Font("Georgia", Font.BOLD, 60);
        label1.setFont(font1);
        label1.setBounds(50,50,300,300);
        label1.setBackground(new Color(30,31,32));
        label1.setForeground(new Color(230,230,230));

        
        JLabel label2 =  new JLabel(" \" Good to see you again \" ");
        Font font2 =  new Font("Georgia", Font.BOLD, 20);
        label2.setFont(font2);
        label2.setBounds(65,120,300,300);
        label2.setBackground(new Color(30,31,32));
        label2.setForeground(new Color(200,200,200));
        
        
        Color normal_color = new Color(245, 245, 245);
        Color hover = new Color(210, 210, 210);
        Color foregroundColor = new Color(19, 19, 20);

        RoundedButton loginbtn= new RoundedButton("Login",normal_color,hover,30 ,foregroundColor);
        loginbtn.setBounds(75,380,250,50);
        
        Color normal_color2 = new Color(60, 62, 65);
        Color hover2 = new Color(80, 82, 85);
        Color foregroundColor2 = new Color(240, 240, 240);

        RoundedButton signupbtn= new RoundedButton("Sign up",normal_color2,hover2,30 ,foregroundColor2);
        signupbtn.setBounds(75,470,250,50);
        

        Color redNormal = new Color(200, 50, 50);
        Color redHover = new Color(220, 70, 70);
        RoundedButton Exitbtn= new RoundedButton("Exit",redNormal,redHover,30 ,foregroundColor2);
        Exitbtn.setBounds(75,650,250,50);
// ===========================================================================
// ====================== Sign up  Card===========================================
// ===========================================================================
        RoundedPanel signup_panel =  new RoundedPanel(30,new Color(30, 31, 32  ));
        signup_panel.setLayout(null);
        signup_panel.setBounds(250, 125, 500, 550);

        Font labelFont = new Font("Segoe UI", Font.BOLD, 18);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 16);
        Color labelColor = new Color(180, 180, 180);
        Color fieldBgcolor = new Color(19, 19, 20); 
        Color fieldTextcolor = new Color(230, 230, 230);
        
        JLabel signupLabel = new JLabel("Sign up");
        signupLabel.setBounds(165,20,200,100);
        signupLabel.setFont(new Font("Georgia", Font.BOLD, 45));
        signupLabel.setForeground(new Color (230,230,230));
        
        
        javax.swing.border.Border Border = BorderFactory.createLineBorder(new Color(180,180,180), 1);
        javax.swing.border.Border paddingBorder = BorderFactory.createEmptyBorder(10, 15, 10, 15);
        javax.swing.border.Border combineborder = BorderFactory.createCompoundBorder(Border, paddingBorder);


        JLabel NameLabel = new JLabel("Name");
        NameLabel.setBounds(40, 130, 175, 20);
        NameLabel.setFont(labelFont);
        NameLabel.setForeground(labelColor);
        
        JTextField Namefield = new JTextField();
        Namefield.setBounds(40, 165, 175, 45); 
        Namefield.setBackground(fieldBgcolor);
        Namefield.setForeground(fieldTextcolor);
        Namefield.setCaretColor(fieldTextcolor);
        Namefield.setFont(fieldFont);
        Namefield.setBorder(combineborder);

       
        JLabel username = new JLabel("Username ");
        username.setBounds(255, 130, 175, 20);
        username.setFont(labelFont);
        username.setForeground(labelColor);
        
        JTextField usernameField = new JTextField();
        usernameField.setBounds(255, 165, 195, 45);
        usernameField.setBackground(fieldBgcolor);
        usernameField.setForeground(fieldTextcolor);
        usernameField.setCaretColor(fieldTextcolor);
        usernameField.setFont(fieldFont);
        usernameField.setBorder(combineborder);

  
        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(40, 227, 370, 20);
        passLabel.setFont(labelFont);
        passLabel.setForeground(labelColor);
        
        JPasswordField passField = new JPasswordField();
        passField.setBounds(40, 262, 405, 45); 
        passField.setBackground(fieldBgcolor);
        passField.setForeground(fieldTextcolor);
        passField.setCaretColor(fieldTextcolor);
        passField.setFont(fieldFont);
        passField.setBorder(combineborder);
        
        JLabel passLabel_ = new JLabel("Confirm Password");
        passLabel_.setBounds(40, 327, 370, 20);
        passLabel_.setFont(labelFont);
        passLabel_.setForeground(labelColor);
        
        JPasswordField passField_ = new JPasswordField();
        passField_.setBounds(40, 362, 405, 45); 
        passField_.setBackground(fieldBgcolor);
        passField_.setForeground(fieldTextcolor);
        passField_.setCaretColor(fieldTextcolor);
        passField_.setFont(fieldFont);
        passField_.setBorder(combineborder);
        
        
        RoundedButton signupbtn2= new RoundedButton("Sign up",normal_color,30 ,foregroundColor);
        signupbtn2.setBounds(125,435,250,50);

        signup_panel.add(signupLabel);
        signup_panel.add(NameLabel);
        signup_panel.add(Namefield);
        signup_panel.add(username);
        signup_panel.add(usernameField);
        signup_panel.add(passLabel);
        signup_panel.add(passLabel_);
        signup_panel.add(passField);
        signup_panel.add(passField_);
        signup_panel.add(signupbtn2);
        
        
        
//============================================================================
// ====================== LOGIN  Card ============================================== 
//============================================================================ 
       
        RoundedPanel login_panel =  new RoundedPanel(30,new Color(30, 31, 32  ));
        login_panel.setLayout(null);
        login_panel.setBounds(250, 125, 500, 550);

        
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setBounds(185,20,200,100);
        loginLabel.setFont(new Font("Georgia", Font.BOLD, 45));
        loginLabel.setForeground(new Color (230,230,230));
        

       
        JLabel username1 = new JLabel("Username ");
        username1.setBounds(40, 150, 175, 20);
        username1.setFont(labelFont);
        username1.setForeground(labelColor);
        
        JTextField usernameField1 = new JTextField();
        usernameField1.setBounds(40, 185, 405, 45);
        usernameField1.setBackground(fieldBgcolor);
        usernameField1.setForeground(fieldTextcolor);
        usernameField1.setCaretColor(fieldTextcolor);
        usernameField1.setFont(fieldFont);
        usernameField1.setBorder(combineborder);

  
         JLabel passLabel1 = new JLabel("Password");
        passLabel1.setBounds(40, 260, 370, 20);
        passLabel1.setFont(labelFont);
        passLabel1.setForeground(labelColor);
        
        JPasswordField passField1 = new JPasswordField();
        passField1.setBounds(40, 295, 405, 45); 
        passField1.setBackground(fieldBgcolor);
        passField1.setForeground(fieldTextcolor);
        passField1.setCaretColor(fieldTextcolor);
        passField1.setFont(fieldFont);
        passField1.setBorder(combineborder);

        
        
        RoundedButton loginbtn2= new RoundedButton("Login",normal_color,30 ,foregroundColor);
        loginbtn2.setBounds(125,385,250,50);
        
        login_panel.add(loginLabel);
        login_panel.add(username1);
        login_panel.add(usernameField1);
        login_panel.add(passLabel1);
        login_panel.add(passField1);
        login_panel.add(loginbtn2);
        
        
        
// =======================================================================================
// ====================== Red Error Bars =================================================
// =======================================================================================


        String error1= "\"Please fill out all fields to create your account.\"";
        String error2= "\"Usernames cannot contain spaces. Please use one continuous word.\"";
        String error3= "\"For your security, passwords must be at least 6 characters long.\"";
        String error4= "\"That username is already taken. Please choose another one.\"";
        String error5= "\"Username or Password is Incorrect\"";
        String error6= "\"Passwords doesn't match.\"";

        RoundedPanel errorpanel1 = new RoundedPanel(30, new Color(180, 10, 10));
        errorpanel1.setBounds(26, 465, 445, 30);
        errorpanel1.setLayout(null);

        JLabel errorlabel = new JLabel(error1);
        errorlabel.setBounds(0, 0, 445, 30);
        errorlabel.setForeground(new Color(255,255,255));
        errorlabel.setHorizontalAlignment(SwingConstants.CENTER);

        errorpanel1.add(errorlabel);
        errorpanel1.setVisible(false);
        
        
        RoundedPanel errorpanel2 = new RoundedPanel(30, new Color(180, 10, 10));
        errorpanel2.setBounds(26, 495, 445, 30);
        errorpanel2.setLayout(null);

        JLabel errorlabel2 = new JLabel(error1);
        errorlabel2.setBounds(0, 0, 445, 30);
        errorlabel2.setForeground(new Color(255,255,255));
        errorlabel2.setHorizontalAlignment(SwingConstants.CENTER);

        errorpanel2.add(errorlabel2);
        errorpanel2.setVisible(false);


// =======================================================================================
// ====================== Events for button at left panel =================================
// =======================================================================================

        loginbtn.addActionListener(e->{

            if (login_panel!=null){
            rightpanel.removeAll();
        }  
        rightpanel.add(login_panel);
        rightpanel.revalidate();
        rightpanel.repaint();


        });
        signupbtn.addActionListener(e->{

            if (signup_panel!=null){
                rightpanel.removeAll();
            }  
            rightpanel.add(signup_panel);
            rightpanel.revalidate();
            rightpanel.repaint();


        });

        Exitbtn.addActionListener(e->{
            dispose();
        });
        
// ==============================================================
// ================= Sign up button ===============================
// ==============================================================

        signupbtn2.addActionListener(e->{

            String username__ = usernameField.getText().strip();
            String name__ = Namefield.getText().strip();
            String password__ = new String(passField.getPassword()).strip();
            String password2__ = new String(passField_.getPassword()).strip();
            if (!(password__.equals(password2__))){
                errorlabel2.setText(error6);
                errorpanel2.setVisible(true);
                passField.setText("");
                passField_.setText("");
                return;
            }

            if (username__.isEmpty() || password__.isEmpty() || name__.isEmpty() ){
                errorlabel2.setText(error1);
                errorpanel2.setVisible(true);
                return;
            }
            if (username__.contains(" ")){
                errorlabel2.setText(error2);
                errorpanel2.setVisible(true);
                usernameField.setText("");
                return;
                
                
            }if(password__.length() < 6){
                errorlabel2.setText(error3);
                errorpanel2.setVisible(true);
                passField.setText("");
                return;
            }    
            if (username__.contains(",") || password__.contains(",") || name__.contains(",")) {
            errorlabel2.setText("Commas ( , ) are not allowed!");
            errorpanel2.setVisible(true);
            return;
            }
            boolean usernametaken= false;
            
            try(BufferedReader reader = new BufferedReader(new FileReader("data/database.txt"))){
                String line;
                while((line= reader.readLine()) != null){
                    String [] data = line.split(",");
                    if ((data.length>= 2) && (data[1].equals(username__))){
                        usernametaken=true;
                        break;
                    }
                    
                }
            } catch (Exception ee){
                System.out.println("Error: "+ee);
            }
            
            if (usernametaken == true) {
                errorlabel2.setText(error4);
                errorpanel2.setVisible(true);
                usernameField.setText("");
                return;
            }
            Namefield.setText("");
            usernameField.setText("");
            passField.setText("");
            passField_.setText("");
            try(FileWriter writer = new FileWriter("data/database.txt",true)){
                errorpanel2.setVisible(false);

                writer.write(name__+","+username__+","+password__+"\n");
                signupbtn2.setText("Account Created!");
                signupbtn2.setBackground(new Color(35, 134, 54));
                
                
                // 2. Create a Background Timer for 2000 milliseconds (2 seconds)
                Timer timer = new Timer(1500, event -> {
                    // 3. This code runs exactly 2 seconds later!
                    signupbtn2.setText("Sign up");
                    signupbtn2.setBackground(normal_color);
                    
                    

                    if (login_panel!=null){
                    rightpanel.removeAll();
                    }  
                    rightpanel.add(login_panel);
                    rightpanel.revalidate();
                    rightpanel.repaint();
                });
                timer.setRepeats(false); 
                timer.start();


            }catch(Exception ex){
                System.out.println("Error: "+ex);
            }
    });


// ==============================================================
// ================= login button ===============================
// ==============================================================

    loginbtn2.addActionListener(e ->{

        String username__ = usernameField1.getText().strip();
        String password__ = new String(passField1.getPassword());


        if (username__.isEmpty() || password__.isEmpty() ){
                errorlabel.setText("\"Please fill out all fields.\"");
                errorpanel1.setVisible(true);
                return;
            }
        
        boolean match = false;
        try(BufferedReader reader = new BufferedReader(new FileReader("data/database.txt"))){
            String line;
            while( (line= reader.readLine()) != null){
                credentials= line.split(",");
                if((credentials.length >= 3)&&(credentials[1].equals(username__)) && (credentials[2].equals(password__))){
                    match = true;
                    break;

                }

            }
        }catch (Exception eee){
            System.out.println("Error: "+eee);
        }
        
        if (match == false){
            errorlabel.setText(error5);
            errorpanel1.setVisible(true);
            passField1.setText("");
            usernameField1.setText("");
        }else{
            errorpanel1.setVisible(false);
            passField1.setText("");
            usernameField1.setText("");
            String rawName = credentials[0];
            String Myname = rawName.substring(0, 1).toUpperCase() + rawName.substring(1).toLowerCase();
             // send the details to gui.java to begin 
            gui.Myname=Myname;
            gui.Myusername=credentials[1];
            dispose();
            gui.startchat();
        }
        });


// ====================================================================
// ======================Adding Panels and buttons===================
// ====================================================================


        login_panel.add(errorpanel1);
        signup_panel.add(errorpanel2);
        rightpanel.add(login_panel);
        leftpanel.add(loginbtn);
        leftpanel.add(signupbtn);
        leftpanel.add(Exitbtn);
        leftpanel.add(label1);
        leftpanel.add(label2);
        add(leftpanel);
        add(rightpanel);
        setVisible(true);
        
    }


// ====================================================================
// ================== Main Method (starts the application)=============
// ====================================================================

public static void main(String[] args) {
    new loginpage();
}

}
