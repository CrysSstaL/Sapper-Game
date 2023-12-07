package src.overrides;

import javax.swing.*;

public class Frame extends JFrame {
    private final String name;
    private final int XSIZE;
    private final int YSIZE;
    public String state;

    public Frame(String name, int XSIZE, int YSIZE) {
        this.name = name;
        this.XSIZE = XSIZE;
        this.YSIZE = YSIZE;
        this.state = "MainMenu";
    }
    public void setFrame(){
        this.setName(name);
        this.setSize(XSIZE, YSIZE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public int getWidth(){
        return this.XSIZE;
    }
    public int getHeight(){
        return this.YSIZE;
    }

    public void setPlayState(String state){
        this.state = state;
    }

    public String getPlayState(){
        return this.state;
    }
}