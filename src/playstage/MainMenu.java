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

public class MainMenu extends JPanel {
    public MainMenu(Frame frame, Media media) throws IOException {
        int XBUTTONSIZE = frame.getWidth() / 5;
        int YBUTTONSIZE = frame.getHeight() / 10;

        JPanelWithBackground menuPanel = new JPanelWithBackground(media.getDir() + "/background.jpg",
                0, 0, frame.getWidth(), frame.getHeight());
        menuPanel.setLayout(null);

        Label title = new Label(frame.getWidth() / 100 * 15, 100,
                frame.getWidth() / 3, frame.getHeight() / 10,
                media.getText(media.getMenuTitle()),
                new Fonts().Title()
        );
        title.setLabel();

        menuPanel.add(title);

        Label msg = new Label(frame.getWidth() / 100 * 15, 150,
                frame.getWidth() / 3, frame.getHeight() / 2,
                media.getText(media.getMenuText()),
                new Fonts().Text()
        );
        msg.setLabel();

        menuPanel.add(msg);


        MenuButton buttonPlay = new MenuButton("Играть", (frame.getWidth() / 8) * 5, (frame.getHeight()) / 6,
                XBUTTONSIZE, YBUTTONSIZE);
        buttonPlay.setParamsText();
        menuPanel.add(buttonPlay);

        buttonPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(menuPanel);
                try {
                    frame.setPlayState("Preparation");
                    main.SwitchF(frame);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        MenuButton buttonHelp = new MenuButton("Помощь", (frame.getWidth() / 8) * 5, (frame.getHeight()) / 6 + 2 * YBUTTONSIZE,
                XBUTTONSIZE, YBUTTONSIZE);
        buttonHelp.setParamsText();

        menuPanel.add(buttonHelp); // Добавляем кнопку на Frame
        buttonHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(menuPanel);
                try {
                    frame.setPlayState("Help");
                    main.SwitchF(frame);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        MenuButton buttonExit = new MenuButton("Выход", (frame.getWidth() / 8) * 5, (frame.getHeight()) / 6 + 4 * YBUTTONSIZE,
                XBUTTONSIZE, YBUTTONSIZE);
        buttonExit.setParamsText();
        menuPanel.add(buttonExit); // Добавляем кнопку на Frame
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frame.setPlayState("ExitPanel");
                    main.SwitchF(frame);
                    frame.remove(menuPanel);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        frame.getContentPane().add(menuPanel);
        frame.setFrame();
    }
}
