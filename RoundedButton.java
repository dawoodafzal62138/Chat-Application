import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

//================================================================= 
// This class overrides the paint component() and made custom JBUTTONS
//=================================================================




public class RoundedButton extends JButton {
    private Color normalColor;
    private Color hoverColor;
    private int radius;
    private Color foregroundcolor;
    public RoundedButton(String text, Color normalColor, Color hoverColor, int radius, Color foregroundcolor) {
        super(text);
        this.normalColor = normalColor;
        this.hoverColor = hoverColor;
        this.radius = radius;
        this.foregroundcolor=foregroundcolor;
            
        

        // 1. Strip away all the ugly default Swing styling
        setContentAreaFilled(false);
        setFocusPainted(false); // Removes the dotted line when clicked
        setBorderPainted(false); // Removes the ugly 3D border
        setOpaque(false);
        
   
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 20));
        setCursor(new Cursor(Cursor.HAND_CURSOR)); // Turns mouse into a pointer!
        setBackground(normalColor);
        setForeground(foregroundcolor);

        // 3. Add the modern Hover Effect
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverColor); // Lighten up when hovered
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(normalColor); // Go back to normal
            }



        });


    }
    public RoundedButton(String text, Color normalColor, int radius, Color foregroundcolor) {
        super(text);
        this.normalColor = normalColor;
        this.radius = radius;
        this.foregroundcolor=foregroundcolor;
            
        

        // 1. Strip away all the ugly default Swing styling
        setContentAreaFilled(false);
        setFocusPainted(false); // Removes the dotted line when clicked
        setBorderPainted(false); // Removes the ugly 3D border
        setOpaque(false);
        
   
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 20));
        setCursor(new Cursor(Cursor.HAND_CURSOR)); // Turns mouse into a pointer!
        setBackground(normalColor);
        setForeground(foregroundcolor);

        



    
    }

 
    // 4. Paint the custom rounded shape and perfectly centered text
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the rounded background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        // Draw the text perfectly in the center
        FontMetrics metrics = g2.getFontMetrics(getFont());
        int x = (getWidth() - metrics.stringWidth(getText())) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        
        g2.setColor(getForeground());
        g2.drawString(getText(), x, y);

        g2.dispose();
    }
}