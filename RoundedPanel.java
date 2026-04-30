import java.awt.*;
import javax.swing.*;
//================================================================= 
// This class overrides the paint component() and made custom JPANEL
//=================================================================


public class RoundedPanel extends JPanel {
    private int cornerRadius;
    private Color backgroundColor;

    
    public RoundedPanel(int radius, Color bgColor) {
    this.cornerRadius = radius;
    this.backgroundColor = bgColor;
    
    setOpaque(false); 
}

    @Override
protected void paintComponent(Graphics g) {
    // 1. Let the parent class handle the standard painting first
    super.paintComponent(g);

    // 2. CREATE A CLONE: This protects the global graphics state
    Graphics2D g2 = (Graphics2D) g.create();

    // 3. APPLY SMOOTHING: Turn on maximum quality Anti-Aliasing and Rendering
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

    // 4. DRAW: Paint the rounded background
    int width = getWidth();
    int height = getHeight();
    g2.setColor(backgroundColor);
    
    // Using cornerRadius directly is cleaner than making a Dimension object
    g2.fillRoundRect(0, 0, width - 1, height - 1, cornerRadius, cornerRadius);

    // 5. CLEAN UP: Destroy the clone to free up system memory
    g2.dispose();
}
}