package src.overrides;

import src.utils.Fonts;

import javax.swing.*;
import java.awt.*;

public class MenuButton extends JButton{
    String buttonName;
    int xPos, yPos, xSize, ySize;
    public MenuButton(String buttonName, int xPos, int yPos, int xSize, int ySize){
        this.xPos = xPos;
        this.yPos = yPos;
        this.xSize = xSize;
        this.ySize = ySize;
        this.buttonName = buttonName;
    }

    public void setParamsText(){
        this.setLayout(null);
        this.setFont(new Fonts().ButtonsText());
        this.setText(this.buttonName);
        this.setLocation(this.xPos, this.yPos);
        this.setSize(this.xSize, this.ySize);
    }
    public void setParamsSmile(){
        this.setFont(new Fonts().Smile());
        this.setText(this.buttonName);
        this.setLocation(this.xPos, this.yPos);
        this.setSize(this.xSize, this.ySize);
    }
    public void setImage(String directory, int widthI, int heightI){
        ImageIcon icon = new ImageIcon(directory);
        Image im = icon.getImage();
        Image im2 = im.getScaledInstance(widthI, heightI, 10);
        this.setIcon(new ImageIcon(im2));
    }
}