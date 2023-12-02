package src.playstage;



import src.main;
import src.overrides.Frame;
import src.overrides.JPanelWithBackground;
import src.overrides.Label;
import src.overrides.MenuButton;
import src.utils.Fonts;
import src.utils.Media;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ExitPanel extends JPanel {
    public ExitPanel(Frame frame, Media media) throws IOException {
        Frame exit = new Frame("Exit", 300, 200);

        JPanelWithBackground exitPanel = new JPanelWithBackground(media.getDir() + "/background.jpg",
                0, 0, exit.getWidth(), exit.getHeight());
        exitPanel.setLayout(null);
        exit.add(exitPanel);

        Label lastMsg = new Label(40, 30,
                220, 50,
                media.getEndText(),
                new Fonts().Text()
        );
        lastMsg.setLabel();

        exitPanel.add(lastMsg);

        MenuButton yesButton = new MenuButton("Да", 50, 100, 50, 25);
        yesButton.setLayout(null);
        yesButton.setParamsText();
        exitPanel.add(yesButton);
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit.remove(exitPanel);
                exit.dispose();
                frame.setPlayState("Exit");
                try {
                    main.SwitchF(frame);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        MenuButton noButton = new MenuButton("Нет", 200, 100, 50, 25);
        noButton.setLayout(null);
        noButton.setParamsText();
        exitPanel.add(noButton);
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit.remove(exitPanel);
                exit.dispose();
                frame.setPlayState("MainMenu");
                try {
                    main.SwitchF(frame);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        exit.getContentPane().add(exitPanel);
        exit.setFrame();
        exit.setDefaultCloseOperation(Frame.HIDE_ON_CLOSE);
    }
}
