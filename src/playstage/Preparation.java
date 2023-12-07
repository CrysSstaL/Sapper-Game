package src.playstage;


import src.main;
import src.overrides.Frame;
import src.overrides.JPanelWithBackground;
import src.overrides.Label;
import src.overrides.MenuButton;
import src.overrides.Panel;
import src.utils.Fonts;
import src.utils.Media;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Preparation extends JPanel {

    public Preparation(Frame frame, Media media) throws IOException {
        JPanelWithBackground PreparePlay = new JPanelWithBackground(media.getDir() + "/background.jpg",
                0, 0, frame.getWidth(), frame.getHeight());
        PreparePlay.setLayout(null);

        Label msg = new Label(frame.getWidth() / 2 - 350, 30,
                700, 50,
                media.getStartPlay(),
                new Fonts().Title()
        );
        msg.setLabel();
        PreparePlay.add(msg);

//////////////////////////////////

        Panel easyPanel = new Panel(frame.getWidth() / 30, frame.getHeight() / 8,
                350, frame.getHeight() - 150,
                new Color(0, 255, 165),
                new LineBorder(new Color(0, 0, 0))
        );
        easyPanel.setPanel();

        easyPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                frame.remove(PreparePlay);
                frame.setPlayState("easyMan");
                try {
                    main.SwitchF(frame);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        easyPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                easyPanel.setBackground(new Color(10, 255, 30));
                easyPanel.setLocation(frame.getWidth() / 30 - 10, frame.getHeight() / 8 - 10);
                easyPanel.setSize(370, frame.getHeight() - 130);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                easyPanel.setBackground(new Color(0, 255, 165));
                easyPanel.setLocation(frame.getWidth() / 30, frame.getHeight() / 8);
                easyPanel.setSize(350, frame.getHeight() - 150);
            }
        });

        Label easyMan = new Label(
                easyPanel.getWidth() / 2 - 75, easyPanel.getHeight() / 15,
                150, 50,
                "EasyMAN \uD83D\uDE05",
                new Fonts().Smile()
        );
        easyMan.setLabel();

        easyMan.setOpaque(true);
        easyMan.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()
                )
        );
        easyPanel.add(easyMan);


        Label easyManDiscription = new Label(easyPanel.getWidth() / 15, easyPanel.getHeight() / 15 * 3,
                easyPanel.getWidth() - 2 * easyPanel.getHeight() / 15, easyPanel.getHeight() / 15 * 10,
                media.getText(media.getEasyManDiscription()),
                new Fonts().Text()
        );
        easyManDiscription.setLabel();
        easyPanel.add(easyManDiscription);
        PreparePlay.add(easyPanel);

//////////////////////////////////
        Panel middlePanel = new Panel(frame.getWidth() / 30 * 2 + easyPanel.getWidth(), frame.getHeight() / 8,
                350, frame.getHeight() - 150,
                new Color(244, 169, 0),
                new LineBorder(new Color(0, 0, 0))
        );
        middlePanel.setPanel();

        middlePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                frame.remove(PreparePlay);
                frame.setPlayState("middleMan");
                try {
                    main.SwitchF(frame);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        middlePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                middlePanel.setBackground(new Color(244, 200, 0));
                middlePanel.setLocation(frame.getWidth() / 30 * 2 + easyPanel.getWidth() - 10, frame.getHeight() / 8 - 10);
                middlePanel.setSize(370, frame.getHeight() - 130);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                middlePanel.setBackground(new Color(244, 169, 0));
                middlePanel.setLocation(frame.getWidth() / 30 * 2 + easyPanel.getWidth(), frame.getHeight() / 8);
                middlePanel.setSize(350, frame.getHeight() - 150);
            }
        });

        Label middleMan = new Label(middlePanel.getWidth() / 2 - 75, middlePanel.getHeight() / 15,
                150, 50,
                "MiddleMAN \uD83E\uDDD0",
                new Fonts().Smile()
        );
        middleMan.setLabel();

        middleMan.setOpaque(true);
        middleMan.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()
                )
        );
        middlePanel.add(middleMan);
        Label middleManDiscription = new Label(middlePanel.getWidth() / 15, middlePanel.getHeight() / 15 * 3,
                middlePanel.getWidth() - 2 * middlePanel.getHeight() / 15, middlePanel.getHeight() / 15 * 9 + 15,
                media.getText(media.getMiddleManDiscription()),
                new Fonts().Text()
        );
        middleManDiscription.setLabel();

        middlePanel.add(middleManDiscription);
        PreparePlay.add(middlePanel);
///////////////////////////

        Panel hardPanel = new Panel(frame.getWidth() / 30 * 3 + easyPanel.getWidth() + middlePanel.getWidth(), frame.getHeight() / 8,
                350, frame.getHeight() - 150,
                new Color(196, 60, 58),
                new LineBorder(new Color(0, 0, 0))
        );
        hardPanel.setPanel();

        ImageIcon icon = new ImageIcon(media.getDir() + "/death.jpg");
        Image tempI = icon.getImage();
        tempI = tempI.getScaledInstance(100, 100, 10);

        Label death = new Label(hardPanel.getWidth() - 100, hardPanel.getHeight() - 100,
                100, 100,
                null,
                null
        );
        death.setLabel();

        death.setIcon(new ImageIcon(tempI));

        hardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                frame.remove(PreparePlay);
                try {
                    frame.setPlayState("hardMan");
                    main.SwitchF(frame);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        hardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                hardPanel.setBackground(new Color(206, 30, 18));
                hardPanel.setLocation(frame.getWidth() / 30 * 3 + easyPanel.getWidth() + middlePanel.getWidth() - 10, frame.getHeight() / 8 - 10);
                hardPanel.setSize(370, frame.getHeight() - 130);
                hardPanel.add(death);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                hardPanel.setBackground(new Color(196, 60, 58));
                hardPanel.setLocation(frame.getWidth() / 30 * 3 + easyPanel.getWidth() + middlePanel.getWidth(), frame.getHeight() / 8);
                hardPanel.setSize(350, frame.getHeight() - 150);
                hardPanel.remove(death);
            }
        });

        Label hardMan = new Label(hardPanel.getWidth() / 2 - 75, hardPanel.getHeight() / 15,
                150, 50,
                "HARDMan \uD83D\uDE35",
                new Fonts().Smile()
        );
        hardMan.setLabel();

        hardMan.setOpaque(true);
        hardMan.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()
                )
        );
        hardPanel.add(hardMan);

        Label hardManDiscription = new Label(hardPanel.getWidth() / 15, hardPanel.getHeight() / 15 * 3,
                hardPanel.getWidth() - 2 * hardPanel.getHeight() / 15, hardPanel.getHeight() / 15 * 10,
                media.getText(media.getHardManDiscription()),
                new Fonts().Text()
        );
        hardManDiscription.setLabel();
        hardPanel.add(hardManDiscription);
        PreparePlay.add(hardPanel);


///////////////////////////////

        MenuButton Scarry = new MenuButton("Боюсь \uD83D\uDE31!", (frame.getWidth() - 300) / 3 + 600, 30,
                150, 50);
        Scarry.setParamsSmile();
        PreparePlay.add(Scarry);
        Scarry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(PreparePlay);
                try {
                    frame.setPlayState("MainMenu");
                    main.SwitchF(frame);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        frame.getContentPane().add(PreparePlay);
        frame.setFrame();
    }
}
