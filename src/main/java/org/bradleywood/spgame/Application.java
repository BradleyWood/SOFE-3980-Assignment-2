package org.bradleywood.spgame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Application {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sliding Puzzle");

        try {
            BufferedImage image = ImageIO.read(Application.class.getClassLoader().getResourceAsStream("numbers.png"));
            GameModel model = new GameModel(Utility.imageSplit(image, 4), image.getWidth(), image.getHeight());
            GameView view = new GameView(model);
            Game game = new Game(view, model);
            frame.add(game.getView());
            game.getModel().randomize();
        } catch (IOException e) {
            System.err.println("Failed to find puzzle image!");
            System.exit(1);
        }

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(215, 235);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
