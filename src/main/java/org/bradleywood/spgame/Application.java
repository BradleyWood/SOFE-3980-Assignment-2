package org.bradleywood.spgame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Application {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sliding Puzzle");

        try {
            BufferedImage image = ImageIO.read(new File("steam.png"));
            GameModel model = new GameModel(Utility.imageSplit(image, 4), image.getWidth(), image.getHeight());
            GameView view = new GameView(model);
            Game game = new Game(view, model);
            frame.add(game.getView());
            game.getModel().randomize();
        } catch (IOException e) {
            e.printStackTrace();
        }

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(515, 540);
        frame.setVisible(true);
    }
}
