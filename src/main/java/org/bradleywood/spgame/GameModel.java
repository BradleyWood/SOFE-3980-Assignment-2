package org.bradleywood.spgame;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
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
            int emptyIdx = -1;
            for (int i = 0; i < piecePositions.length; i++) {
                if (piecePositions[i] == piecePositions.length - 1) {
                    emptyIdx = i;
                }
            }
            int sideLen = (int) Math.sqrt(numPieces());
            int emptyX = Utility.getX(emptyIdx, sideLen);
            int emptyY = Utility.getY(emptyIdx, sideLen);
            for (int i = 0; i < 200; i++) {
                List<Integer> indicies = new ArrayList<>();
                if (emptyX == 0) {
                    indicies.add(Utility.getIdx(emptyX + 1, emptyY, sideLen));
                } else if (emptyX == sideLen - 1) {
                    indicies.add(Utility.getIdx(emptyX - 1, emptyY, sideLen));
                } else {
                    indicies.add(Utility.getIdx(emptyX + 1, emptyY, sideLen));
                    indicies.add(Utility.getIdx(emptyX - 1, emptyY, sideLen));
                }
                if (emptyY == 0) {
                    indicies.add(Utility.getIdx(emptyX, emptyY + 1, sideLen));
                } else if (emptyY == sideLen - 1) {
                    indicies.add(Utility.getIdx(emptyX, emptyY - 1, sideLen));
                } else {
                    indicies.add(Utility.getIdx(emptyX, emptyY + 1, sideLen));
                    indicies.add(Utility.getIdx(emptyX, emptyY - 1, sideLen));
                }
                int spIdx = indicies.get(random.nextInt(indicies.size()));
                int tmp = piecePositions[emptyIdx];
                piecePositions[emptyIdx] = piecePositions[spIdx];
                piecePositions[spIdx] = tmp;
                emptyIdx = spIdx;
                emptyX = Utility.getX(emptyIdx, sideLen);
                emptyY = Utility.getY(emptyIdx, sideLen);
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
