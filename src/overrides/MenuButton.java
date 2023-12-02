package src.overrides;

import src.utils.Fonts;

import javax.swing.*;

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
}