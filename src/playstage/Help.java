package src.playstage;


import src.main;
import src.overrides.Frame;
import src.overrides.MenuButton;
import src.overrides.JPanelWithBackground;
import src.utils.Fonts;
import src.utils.Media;
import src.overrides.Label;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Help extends JPanel {
    public Help(Frame frame, Media media) throws IOException {
        JPanelWithBackground helpPanel = new JPanelWithBackground(media.getDir() + "/background.jpg",
                0, 0, frame.getWidth(), frame.getHeight());
        helpPanel.setLayout(null);

        Label msg = new Label(frame.getWidth() / 100 * 10, -10,
                frame.getWidth() / 100 * 60, frame.getHeight(),
                media.getText(media.getHelpText()),
                new Fonts().Text()
        );
        msg.setLabel();

        helpPanel.add(msg);

        Label imageLabel = new Label(frame.getWidth() / 100 * 81, frame.getHeight() / 10,
                180, 300, null, null
        );
        imageLabel.setLabel();

        ImageIcon image = new ImageIcon(media.getDir() + "/helpImage.png");
        Image resImage = image.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), 10);
        imageLabel.setIcon(new ImageIcon(resImage));

        helpPanel.add(imageLabel);

        MenuButton buttonBack = new MenuButton("Назад", frame.getWidth() / 100 * 85, frame.getHeight() / 10 * 8, 100, 50);
        buttonBack.setParamsText();
        helpPanel.add(buttonBack);
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(helpPanel);
                try {
                    frame.setPlayState("MainMenu");
                    main.SwitchF(frame);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        frame.getContentPane().add(helpPanel);
        frame.setFrame();
    }
}
