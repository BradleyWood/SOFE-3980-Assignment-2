package org.bradleywood.spgame;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

public class GameModel {

    private final Random random = new Random();

    private final List<BufferedImage> images;
    private final int[] piecePositions;
    private int width;
    private int height;

    public GameModel(final List<BufferedImage> images, final int width, final int height) {
        this.images = images;
        this.width = width;
        this.height = height;
        piecePositions = new int[images.size()];
        for (int i = 0; i < piecePositions.length; i++) {
            piecePositions[i] = i;
        }
    }

    public void update(final List<BufferedImage> images, final int width, final int height) {
        synchronized (images) {
            this.images.clear();
            this.images.addAll(images);
            this.width = width;
            this.height = height;
            randomize();
        }
    }

    public void randomize() {
        synchronized (piecePositions) {
            for (int i = 0; i < piecePositions.length; i++) {
                int c = i + random.nextInt(piecePositions.length - i);
                int tmp = piecePositions[i];
                piecePositions[i] = piecePositions[c];
                piecePositions[c] = tmp;
            }
        }
    }

    public List<BufferedImage> getImages() {
        return images;
    }

    public int[] getPiecePositions() {
        return piecePositions;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int numPieces() {
        synchronized (images) {
            return images.size();
        }
    }
}
