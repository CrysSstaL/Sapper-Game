package src.overrides;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class JPanelWithBackground extends JPanel {
    private Image backgroundImage;
    private int x, y, xsize, ysize;

    // Some code to initialize the background image.
    // Here, we use the constructor to load the image. This
    // can vary depending on the use case of the panel.
    public JPanelWithBackground(String fileName, int x, int y, int xsize, int ysize) throws IOException {
        this.x = x;
        this.y = y;
        this.xsize = xsize;
        this.ysize = ysize;
        backgroundImage = ImageIO.read(new File(fileName));
        backgroundImage = backgroundImage.getScaledInstance(xsize,ysize,10);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, this.x, this.y, this);
    }
}