package src.playstage;


import src.main;
import src.overrides.JPanelWithBackground;
import src.overrides.MenuButton;
import src.overrides.PlayButton;
import src.utils.Fonts;
import src.utils.Media;
import src.utils.Watch;
import src.overrides.Frame;
import src.overrides.Label;
import src.overrides.Panel;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

public class Play extends JPanel {
    private final PlayButton[][] fieldMas;
    private boolean ClickOnBomb;
    private boolean firstClick = true;
    private int leftbuttons, numberMines;
    private final Watch watch;
    private final Media media;
    private final Frame frame;
    private Panel mainPanel;
    private boolean mode;
    public Play(Frame frame, Media media) throws IOException {
        this.media = media;
        this.frame = frame;
        this.mode = true;

        int SizeButton = 35;
        int rowField = 0;
        int colField = 0;
        int xBorder = 0;
        int yBorder = 100;
        this.ClickOnBomb = false;
        switch (frame.getPlayState()) {
            case "easyMan": {
                numberMines = 15;
                rowField = 10;
                colField = 10;
                xBorder = (frame.getWidth() - SizeButton * rowField) / 2;
                leftbuttons = rowField * colField - numberMines - 1;
                break;
            }
            case "middleMan": {
                numberMines = 40;
                rowField = 16;
                colField = 16;
                xBorder = (frame.getWidth() - SizeButton * rowField) / 2;
                leftbuttons = rowField * colField - numberMines - 1;
                break;
            }
            case "hardMan": {
                numberMines = 99;
                rowField = 30;
                colField = 16;
                xBorder = (frame.getWidth() - SizeButton * rowField) / 2;
                leftbuttons = rowField * colField - numberMines - 1;
                break;
            }
            default:
                frame.setPlayState("Preparation");
                main.SwitchF(frame);
        }

        fieldMas = new PlayButton[rowField][colField];

        mainPanel = new Panel(0, 0,
                frame.getWidth(), frame.getHeight(),
                null,
                null
        );
        mainPanel.setPanel();

        JPanelWithBackground playPanel = new JPanelWithBackground(media.getDir() + "/background.jpg",
                0, 0, frame.getWidth(), frame.getHeight() - 100);
        playPanel.setLayout(null);
        playPanel.setLocation(0, 100);
        playPanel.setBorder(new LineBorder(new Color(0, 0, 0, 200)));
        playPanel.setSize(frame.getWidth(), frame.getHeight() - 100);
        mainPanel.add(playPanel);

        Label HelpLabel = new Label(375,10, 450,90, "Для начала игры нажмите 'START'!", new Fonts().Title());
        HelpLabel.setLabel();
        playPanel.add(HelpLabel);


        JPanelWithBackground BackgroundOptionPanel = new JPanelWithBackground(media.getDir() + "/background.jpg",
                0, 0, frame.getWidth(), 100);
        BackgroundOptionPanel.setLayout(null);
        BackgroundOptionPanel.setLocation(0, 0);
        BackgroundOptionPanel.setSize(frame.getWidth(), 100);

        Panel OptionPanel = new Panel(10,10, frame.getWidth() - 20 , 80, Color.gray,
                BorderFactory.createCompoundBorder(
                    BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()
                )
        );
        OptionPanel.setPanel();


        MenuButton backButton = new MenuButton("Назад", 1040, 15, 80, 50);
        backButton.setParamsText();

        MenuButton changeMode = new MenuButton(null, 780, 15, 80,50);
        changeMode.setParamsSmile();
        changeMode.setImage(media.getDir() + "/SwapDig1.png", 50,50);
        OptionPanel.add(changeMode);

        changeMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mode){
                    mode = false;
                    changeMode.setImage(media.getDir() + "/flaggg.png", 50,50);
                }
                else {
                    mode = true;
                    changeMode.setImage(media.getDir() + "/SwapDig1.png", 50,50);
                }
                repaint();
            }
        });

        MenuButton rePlay = new MenuButton("Заново", 910,15,80,50);
        rePlay.setParamsText();
        rePlay.setFont(new Fonts().ButtonsText());
        rePlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(mainPanel);
                firstClick = true;
                try {
                    main.SwitchF(frame);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        watch = new Watch();
        OptionPanel.add(watch);
        OptionPanel.add(rePlay);
        OptionPanel.add(backButton);
        BackgroundOptionPanel.add(OptionPanel);

        mainPanel.add(BackgroundOptionPanel);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(mainPanel);
                try {
                    BackgroundOptionPanel.remove(watch);
                    frame.setPlayState("Preparation");
                    main.SwitchF(frame);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        CreatePlayButtons(playPanel, xBorder, yBorder);

        for (int i = 0; i < rowField; i++) {
            for (int j = 0; j < colField; j++) {
                PlayButton button = fieldMas[i][j];
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            if (watch.getPlay()) {
                                if (mode) {
                                    playPanel.remove(HelpLabel);
                                    buttonClicked(playPanel, button);
                                } else {
                                    playPanel.remove(HelpLabel);
                                    addFlag(button);
                                }
                            }
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
            }
        }


        frame.getContentPane().add(mainPanel);
        frame.setFrame();
    }

    private void addFlag(PlayButton button) {
        if (button.getModeButton()){
            button.setImage(media.getDir() + "/iconButton.png");
            button.setModeButton(false);
        }
        else{
            button.setImage(media.getDir() + "/SwapFlag.png");
            button.setModeButton(true);
        }
        repaint();
    }

    private void CreatePlayButtons(JPanelWithBackground playPanel, int xBorder, int yBorder) {
        for (int i = 0; i < fieldMas.length; i++) {
            for (int j = 0; j < fieldMas[i].length; j++) {
                PlayButton button = new PlayButton(xBorder + i * 35, yBorder + j * 35, i, j);
                button.setBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()
                        )
                );
                button.setParams();
                button.setImage(media.getDir() + "/iconButton.png");
                fieldMas[i][j] = button;
                playPanel.add(button);
            }
        }
    }

    private void setGame(int numberMines, int pressI, int pressJ) {
        while (numberMines > 0) {
            int rand_i = new Random().nextInt(fieldMas.length);
            int rand_j = new Random().nextInt(fieldMas[0].length);
            if ((rand_i >= pressI - 1)&&(rand_i <= pressI + 1) &&
                    (rand_j <= pressJ + 1) && (rand_j >= pressJ - 1)
                    && (pressI - 1 >= 0) && (pressI + 1 < fieldMas.length)
                    && (pressJ - 1 >= 0) && (pressJ + 1 < fieldMas[0].length))
            {}
            else {
                if (!fieldMas[rand_i][rand_j].getBomb()) {
                    fieldMas[rand_i][rand_j].setBomb(true);
                    numberMines -= 1;
                }
            }
        }
        for (int i = 0; i < fieldMas.length; i++) {
            for (int j = 0; j < fieldMas[i].length; j++) {
                if (!fieldMas[i][j].getBomb()) {
                    int minesAround = getInfo(i, j);
                    fieldMas[i][j].setIcon(media.getDir() + "/open.jpg");
                    fieldMas[i][j].setMinesAround(minesAround);
                } else {
                    fieldMas[i][j].setIcon(media.getDir() + "/bomb.png");
                    fieldMas[i][j].setMinesAround(9);
                }
            }
        }
        firstClick = false;
    }

    public int getInfo(int i, int j) {
        int minesAround = 0;
        boolean left = true;
        boolean right = true;
        boolean top = true;
        boolean bottom = true;
        try {
            // проверка на выход за границы доски(существование 8 соседей!)
            if (j - 1 == -1)
                top = false;
            if (j + 1 == fieldMas[0].length)
                bottom = false;
            if (i - 1 == -1)
                left = false;
            if (i + 1 == fieldMas.length)
                right = false;
            // счет соседей
            if (top)
                if (left)
                    if (fieldMas[i - 1][j - 1].getBomb()) {
                        minesAround += 1;
                    }
            if (top)
                if (fieldMas[i][j - 1].getBomb())
                    minesAround += 1;
            if (top)
                if (right)
                    if (fieldMas[i + 1][j - 1].getBomb())
                        minesAround += 1;
            if (left)
                if (fieldMas[i - 1][j].getBomb())
                    minesAround += 1;
            if (right)
                if (fieldMas[i + 1][j].getBomb())
                    minesAround += 1;
            if (bottom)
                if (left)
                    if (fieldMas[i - 1][j + 1].getBomb())
                        minesAround += 1;
            if (bottom)
                if (fieldMas[i][j + 1].getBomb())
                    minesAround += 1;
            if (bottom)
                if (right)
                    if (fieldMas[i + 1][j + 1].getBomb())
                        minesAround += 1;
            return minesAround;
        } catch (Exception ignored) {
        }

        return minesAround;
    }

    public void buttonClicked(JPanelWithBackground playPanel, PlayButton button) throws IOException {
        if (!button.getModeButton()) {
            if (firstClick) {
                setGame(numberMines, button.getI(), button.getJ());
            }
            playPanel.remove(button);
            String value = button.getDirIcon();

            Label label = new Label(button.getX(), button.getY(),
                    button.getSize().width, button.getSize().height,
                    null,
                    null
            );
            label.setLabel();

            ImageIcon icon = new ImageIcon(value);
            Image tempI = icon.getImage();
            tempI = tempI.getScaledInstance(button.getSize().width, button.getSize().height, 10);

            if (leftbuttons == 0) {
                leftbuttons = -1;
                this.ClickOnBomb = true;
                watch.ClickStopBtn();

                showAll(playPanel);
                endGame();
            }

            if (button.getBomb() && !this.ClickOnBomb) {
                leftbuttons = -1;
                this.ClickOnBomb = true;
                label.setLayout(null);
                label.setIcon(new ImageIcon(tempI));
                fieldMas[button.getI()][button.getJ()] = null;
                playPanel.add(label);
                watch.ClickStopBtn();

                showAll(playPanel);
                endGame();
            } else if (button.getBomb()) {
                label.setLayout(null);
                label.setIcon(new ImageIcon(tempI));
                fieldMas[button.getI()][button.getJ()] = null;
                playPanel.add(label);
            } else {
                if (button.getMinesAround() == 0) {
                    label.setLayout(null);
                    label.setIcon(new ImageIcon(tempI));
                    playPanel.add(label);
                    leftbuttons -= 1;
                    deliteOtherEmptyFields(playPanel, fieldMas, button);
                } else {
                    leftbuttons -= 1;
                    fieldMas[button.getI()][button.getJ()] = null;
                    label.setBorder(new LineBorder(new Color(0, 0, 0)));
                    label.setLayout(new BorderLayout());
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    label.setFont(new Fonts().Title());
                    label.setIcon(new ImageIcon(tempI));

                    JLabel textLabel = new JLabel();
                    textLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    textLabel.setFont(new Fonts().Title());
                    textLabel.setText("<html>" + "<span style='color: " + media.getColors()[button.getMinesAround()] + "'>"
                            + String.valueOf(button.getMinesAround()) + "</span>" + "</html>");
                    textLabel.setLayout(null);
                    textLabel.setSize(button.getSize().width, button.getSize().height);
                    textLabel.setLocation(0, 0);
                    label.add(textLabel);
                    playPanel.add(label);
                }
            }
            playPanel.repaint();
        }
    }

    private void deliteOtherEmptyFields(JPanelWithBackground playPanel, PlayButton[][] fields, PlayButton button) {
        int xLocation = button.getI();
        int yLocation = button.getJ();
        delete(playPanel, fields, xLocation, yLocation);
    }

    private void showAll(JPanelWithBackground playPanel) throws IOException {
        for (PlayButton[] fieldRow : fieldMas) {
            for (PlayButton playButton : fieldRow) {
                if (playButton != null) {
                    if (playButton.getBomb() && playButton.getModeButton()){
                        playButton.setModeButton(true);
                        playButton.setImage(media.getDir() + "/SwapFlag.png");
                    }
                    else if (!playButton.getBomb() && playButton.getModeButton()){
                        playButton.setModeButton(true);
                        playButton.setImage(media.getDir() + "/NoFlag1.png");
                    }
                    else {
                        playButton.setModeButton(false);
                    }
                    buttonClicked(playPanel, playButton);
                }
            }
        }
        // проверка очистки массива
//        for (int i = 0; i < fieldMas.length; i++){
//            for (int j = 0; j < fieldMas[i].length; j++){
//                System.out.print(fieldMas[i][j] + " ");
//            }
//            System.out.println();
//        }
    }

    public void delete(JPanelWithBackground playPanel, PlayButton[][] fields, int i, int j) {
        if (fields[i][j] == null) {
            return;
        }
        fields[i][j] = null;
        try {

            buttonClicked(playPanel, fields[i - 1][j]);
        } catch (Exception ignored) {
        }
        try {

            buttonClicked(playPanel, fields[i][j - 1]);
        } catch (Exception ignored) {
        }
        try {
            buttonClicked(playPanel, fields[i + 1][j]);
        } catch (Exception ignored) {
        }
        try {
            buttonClicked(playPanel, fields[i][j + 1]);
        } catch (Exception ignored) {
        }
        try {
            buttonClicked(playPanel, fields[i - 1][j - 1]);
        } catch (Exception ignored) {
        }
        try {
            buttonClicked(playPanel, fields[i - 1][j + 1]);
        } catch (Exception ignored) {
        }
        try {
            buttonClicked(playPanel, fields[i + 1][j - 1]);
        } catch (Exception ignored) {
        }
        try {
            buttonClicked(playPanel, fields[i + 1][j + 1]);
        } catch (Exception ignored) {
        }
    }
    private void endGame() throws IOException {
        Frame exit = new Frame("Победа!", 500,200);

        JPanelWithBackground exitPanel = new JPanelWithBackground(media.getDir() + "/background.png",
                0, 0, exit.getWidth(), exit.getHeight());
        exitPanel.setLayout(null);
        exit.add(exitPanel);

        JLabel lastMsg = new JLabel("<html>" + media.getEndPlayText().replaceAll("\n", "<br/>") + "</html>", SwingConstants.CENTER);
        lastMsg.setFont(new Fonts().Smile());
        lastMsg.setLayout(null);
        lastMsg.setLocation(50,20);
        lastMsg.setSize(400,70);
        exitPanel.add(lastMsg);

        MenuButton yesButton = new MenuButton("Заново", 50,100, 100, 40);
        yesButton.setLayout(null);
        yesButton.setParamsText();
        exitPanel.add(yesButton);
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(mainPanel);
                firstClick = true;
                exit.dispose();
                try {
                    frame.setPlayState(frame.getPlayState());
                    main.SwitchF(frame);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        MenuButton noButton = new MenuButton( "Сложность", 200,100, 100, 40);
        noButton.setLayout(null);
        noButton.setParamsText();
        exitPanel.add(noButton);
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(mainPanel);
                exit.dispose();
                frame.setPlayState("Preparation");
                try {
                    main.SwitchF(frame);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        MenuButton toMainMenu = new MenuButton("Выйти", 350,100, 100, 40);
        toMainMenu.setLayout(null);
        toMainMenu.setParamsText();
        exitPanel.add(toMainMenu);
        toMainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(mainPanel);
                exit.dispose();
                try {
                    frame.setPlayState("MainMenu");
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
