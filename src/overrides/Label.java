package src.overrides;

import javax.swing.*;
import java.awt.*;

public class Label extends JLabel {
    int xPos;
    int yPos;
    int xSize;
    int ySize;
    String text;
    Font font;

    public Label(int xPos, int yPos, int xSize, int ySize, String text, Font font){
        this.xPos = xPos;
        this.yPos = yPos;
        this.xSize = xSize;
        this.ySize = ySize;
        this.text = text;
        this.font = font;
    }

    public void setLabel(){
        this.setLayout(null);
        this.setLocation(xPos, yPos);
        this.setSize(xSize, ySize);
        this.setHorizontalAlignment(CENTER);
        this.setText(text);
        this.setFont(font);
    }
}