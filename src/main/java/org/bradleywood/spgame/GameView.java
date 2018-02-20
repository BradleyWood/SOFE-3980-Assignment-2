package org.bradleywood.spgame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class GameView extends JPanel {

    private final GameModel model;

    GameView(final GameModel model) {
        this.model = model;
    }

    public GameModel getModel() {
        return model;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.GRAY);
        g2d.clearRect(0, 0, getWidth(), getHeight());

        List<BufferedImage> images = model.getImages();
        int[] piecePositions = model.getPiecePositions();
        synchronized (piecePositions) {
            synchronized (images) {
                int idx = 0;
                for (int pos : piecePositions) {
                    BufferedImage img = images.get(pos);
                    int x = idx % (int)Math.sqrt(images.size()) * img.getWidth();
                    int y = idx / (int)Math.sqrt(images.size()) * img.getHeight();
                    idx++;
                    if (pos == images.size() - 1) {
                        g2d.setColor(Color.GRAY);
                        g2d.fillRect(x, y, img.getWidth(), img.getHeight());
                        continue;
                    }
                    g2d.drawImage(img, x, y, img.getWidth(), img.getHeight(), this);
                }
            }
        }
    }
}
