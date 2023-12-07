package src.overrides;


import src.utils.Media;

import javax.swing.*;
import java.awt.*;

public class PlayButton extends JButton{
    private int x,y,i,j;
    private int size;
    private boolean bomb;

    private String icon;
    private int minesAround;
    public boolean modeButton;
    public PlayButton(int x, int y, int i, int j) {
        this.x = x;
        this.y = y;
        this.i = i;
        this.j = j;
        this.size = 35;
        this.bomb = false;
        this.icon = new Media().getDir();
        this.minesAround = 0;
        this.modeButton = false;  // false - копаем, true - флаг
    }
    public void setParams() {
        this.setLayout(null);
        this.setLocation(this.x, this.y);
        this.setSize(size, size);
    }
    public void setImage(String directory){
        ImageIcon icon = new ImageIcon(directory);
        Image im = icon.getImage();
        Image im2 = im.getScaledInstance(this.getWidth(), this.getHeight(), 10);
        this.setIcon(new ImageIcon(im2));
    }
    public void setMinesAround(int minesAround){
        this.minesAround = minesAround;
    }
    public int getMinesAround(){
        return this.minesAround;
    }
    public void setIcon(String dir){
        this.icon = dir;
    }

    public String getDirIcon(){
        return this.icon;
    }
    public void setBomb(boolean state){
        this.bomb = state;
    }
    public boolean getBomb(){
        return this.bomb;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public int getI(){
        return this.i;
    }
    public int getJ(){
        return this.j;
    }

    public boolean getModeButton(){
        return this.modeButton;
    }

    public void setModeButton(boolean mode){
        this.modeButton = mode;
    }
}
