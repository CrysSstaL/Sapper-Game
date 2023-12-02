package src.overrides;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Panel extends JPanel {
    private int xPos;
    private int yPos;
    private int xSize;
    private int ySize;
    private final Color color;
    private final Border border;
    public Panel(int xPos, int yPos, int xSize, int ySize, Color color, Border border){
        this.xPos = xPos;
        this.yPos = yPos;
        this.xSize = xSize;
        this.ySize = ySize;
        this.color = color;
        this.border = border;
    }

    public void setPanel(){
        this.setLayout(null);
        this.setLocation(xPos, yPos);
        this.setSize(xSize, ySize);
        this.setBackground(color);
        this.setBorder(border);
    }
}
