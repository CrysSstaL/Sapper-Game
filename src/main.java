package src;

import src.overrides.Frame;
import src.playstage.*;
import src.utils.Media;

import java.io.IOException;


public class main {
    private static final Frame frame = new Frame("Sapper", 1200, 800);
    private static final Media media = new Media();
    public static void main(String[] args) throws IOException {
        SwitchF(frame);
    }
    public static void SwitchF(Frame frame) throws IOException {
        switch (frame.getPlayState()) {
            case "Preparation" : {
                new Preparation(frame, media);
                break;
            }
            case "easyMan", "hardMan", "middleMan" : {
                new Play(frame, media);
                break;
            }
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