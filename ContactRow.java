import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.MatteBorder;

public class ContactRow extends JPanel {

    private Color normalColor = new Color(30, 31, 32); 
    private Color hoverColor = new Color(42, 43, 44);  
    public String name,username, lastMessage, time, initial;
    private int fontsize;
    
// =========================================================================================
// ORIGINAL 1st Constructor (For the Left Sidebar)
// =========================================================================================
    public ContactRow(String name, String username) {
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        this.username= username;

        this.initial = name.substring(0, 1).toUpperCase();
        
        // 1. Setup Panel
        setPreferredSize(new Dimension(600, 75));
        setMaximumSize(new Dimension(600, 75)); 
        setBackground(normalColor);
        setLayout(null);
        
        setBorder(new MatteBorder(0, 0, 1, 0, new Color(45, 45, 45)));

        

        JLabel nameLabel = new JLabel(this.name);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        nameLabel.setForeground(new Color(230, 230, 230));
        nameLabel.setBounds(75, 21, 600, 20);

        JLabel timeLabel = new JLabel(time);
        timeLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        timeLabel.setForeground(new Color(150, 150, 150));
        timeLabel.setBounds(230, 15, 70, 20);

        JLabel usernameLabel = new JLabel("@" + this.username);
        usernameLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        usernameLabel.setForeground(new Color(120, 120, 120)); 
        usernameLabel.setBounds(75, 43, 200, 20); 
        
        
        add(usernameLabel);
        add(nameLabel);
        add(timeLabel);

        // 3. Hover & Click Effect
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverColor);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(normalColor);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // Send the name and username to the method below in gui.java
                gui.openChat(ContactRow.this.username,ContactRow.this.name);
            }
        });
    }


// =========================================================================================
// 2nd Constructor (For the Top Profile Banner)
// =========================================================================================


    public ContactRow(String name, String username , String time, int fontsize) {
        this.name = name;
        this.username = username;
        this.time = time;
        this.initial = name.substring(0, 1).toUpperCase();
        this.fontsize = fontsize;
        
        // 1. Setup Panel
        setPreferredSize(new Dimension(600, 75));
        setMaximumSize(new Dimension(600, 75)); 
        setBackground(normalColor);
        setLayout(null);
        
        setBorder(new MatteBorder(0, 0, 1, 0, new Color(45, 45, 45)));


        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, fontsize));
        nameLabel.setForeground(new Color(230, 230, 230));
        nameLabel.setBounds(80, 15, 600, 30);

        JLabel timeLabel = new JLabel(time);
        timeLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        timeLabel.setForeground(new Color(150, 150, 150));
        timeLabel.setBounds(85, 45, 70, 20);

        add(nameLabel);
        add(timeLabel);

        // 3. Hover Effect
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverColor);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(normalColor);
            }
             @Override
            public void mouseClicked(MouseEvent e) {
                // Send the name and username to the method below in gui.java
                gui.click(ContactRow.this.name, ContactRow.this.username);
            }



        });
    }
    
    
// =========================================================================================
// Third constructor ( for message bubble )
// =========================================================================================
   // third constructor for message bubble
    public ContactRow(String name, String message, String timestamp, boolean isyour) {
        setOpaque(false);
        setLayout(null);
        
        Color bubbleColor;
        if (isyour) {
            bubbleColor = new Color(0, 92, 75); // Green for me
        } else {
            bubbleColor = new Color(45, 45, 48); // Dark gray for them
        }
        
        JTextArea messageArea = new JTextArea(message);
        messageArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
        messageArea.setForeground(new Color(230, 230, 230));
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setEditable(false);
        messageArea.setFocusable(false);
        messageArea.setOpaque(false);
        messageArea.setSize(350, Short.MAX_VALUE);
        
        int requiredHeight = messageArea.getPreferredSize().height;
        
        // 1. Create the Timestamp Label
        JLabel timeLabel = new JLabel(timestamp != null ? timestamp : "");
        timeLabel.setFont(new Font("SansSerif", Font.ITALIC, 11));
        timeLabel.setForeground(new Color(170, 170, 170));
        timeLabel.setSize(350, 15);
        timeLabel.setHorizontalAlignment(SwingConstants.RIGHT); // Align to the right
        
        // 2. Expand bubble height to fit the time label
        int bubbleHeight = requiredHeight + 30; 
        int rowHeight = bubbleHeight + 10;
        
        setPreferredSize(new Dimension(1000, rowHeight));
        setMaximumSize(new Dimension(1000, rowHeight));
        setMinimumSize(new Dimension(1000, rowHeight));
        
        RoundedPanel roundedPanel = new RoundedPanel(30, bubbleColor);
        roundedPanel.setLayout(null);
        
        // 3. Position the text and the time
        messageArea.setBounds(15, 10, 350, requiredHeight);
        timeLabel.setBounds(15, requiredHeight + 10, 350, 15);
        
        roundedPanel.add(messageArea);
        roundedPanel.add(timeLabel);
        
        if (!isyour) {
            roundedPanel.setBounds(20, 5, 380, bubbleHeight);
        } else {
            roundedPanel.setBounds(600, 5, 380, bubbleHeight);
        }
        add(roundedPanel);
    }
    // 4. Paint the circle for initials
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (initial != null && !initial.isEmpty()) {
            
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw the background circle
            g2.setColor(new Color(80, 100, 120));
            g2.fillOval(15, 12, 50, 50);

            // Draw the Initial letter perfectly centered inside the circle
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("SansSerif", Font.BOLD, 22));
            FontMetrics fm = g2.getFontMetrics();
            int x = 15 + (50 - fm.stringWidth(initial)) / 2;
            int y = 12 + ((50 - fm.getHeight()) / 2) + fm.getAscent();
            g2.drawString(initial, x, y);

            g2.dispose();
        }
    }
}