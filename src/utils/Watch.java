package src.utils;

import src.overrides.MenuButton;
import src.overrides.Label;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;

public class Watch extends JPanel {
    private boolean play;
    private final Label time; // Показать метку
    private final MenuButton startButton;    // Кнопка запуска
    private final MenuButton stopButton;     // кнопка остановки
    private final MenuButton resetButton;    // кнопка сброса
    private long countMis, countSec, countMin, countHour;// временная переменная
    private final DecimalFormat textFormat = new DecimalFormat("00");// форматированный вывод
    private final Timer timer = new Timer(10, new TestActionListener());// единица времени 10мс

    public Watch() {
        this.setLayout(null);
        this.setLocation(10, 10);
        this.setSize(700, 60);
        this.setBackground(Color.gray);

        time = new Label(480, 5, 200, 50, " ", new Fonts().Timer());
        time.setLabel();

        TestActionListener actionListener = new TestActionListener();

        startButton = new MenuButton("START", 50, 5, 80, 50);
        startButton.setParamsText();

        stopButton = new MenuButton("STOP", 180, 5, 80, 50);
        stopButton.setParamsText();

        resetButton = new MenuButton("RESET", 310, 5, 80, 50);
        resetButton.setParamsText();


        stopButton.addActionListener(actionListener);
        startButton.addActionListener(actionListener);
        resetButton.addActionListener(actionListener);

        this.add(time);
        this.add(stopButton);
        this.add(startButton);
        this.add(resetButton);
    }

    // Обработка связанных событий
    class TestActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startButton) {
                ClickStartBtn();
            } else if (e.getSource() == stopButton) {
                ClickStopBtn();
            } else if (e.getSource() == resetButton) {
                try {
                    ClickResetBtn();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                countMis++;
                if (countMis >= 99) {
                    countSec++;
                    countMis = 0;
                    if (countSec >= 59) {
                        countMin++;
                        countSec = 0;
                        if (countMin >= 59) {
                            countHour++;
                            countMin = 0;
                        }
                    }
                }

            }
        }
    }
    public void ClickStartBtn(){
        timer.start();
        play = true;
        startButton.setEnabled(false);
    }
    public void ClickStopBtn(){
        timer.stop();
        play = false;
        startButton.setEnabled(true);
    }
    public void ClickResetBtn() throws IOException {
        play = false;
        countHour = 0;
        countMin = 0;
        countSec = 0;
        countMis = 0;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        time.setText(textFormat.format(countHour) + ":" + textFormat.format(countMin) +
                ":" + textFormat.format(countSec) + ":" + textFormat.format(countMis));
        repaint();
    }

    public boolean getPlay() {
        return this.play;
    }
}
