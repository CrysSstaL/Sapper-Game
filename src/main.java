package src;

import src.overrides.Frame;
import src.playstage.ExitPanel;
import src.playstage.Help;
import src.playstage.MainMenu;
import src.utils.Media;

import javax.swing.*;
import java.io.IOException;


public class main {
    private static final Frame frame = new Frame("Sapper", 1200, 800);
    private static final Media media = new Media();
    public static void main(String[] args) throws IOException {
        frame.setFrame();
        SwitchF(frame);
    }
    public static void SwitchF(Frame frame) throws IOException {
        switch (frame.getPlayState()) {
            case "MainMenu": {
                new MainMenu(frame, media);
                break;
            }
            case "Help": {
                new Help(frame, media);
                break;
            }
            case "ExitPanel": {
                new ExitPanel(frame, media);
                break;
            }
            case "Exit": {
                frame.dispose();
                break;
            }
        }
    }
}