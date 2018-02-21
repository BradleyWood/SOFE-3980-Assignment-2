package org.bradleywood.spgame;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Utility {

    /**
     * Split and image into d by d sub images
     * @param image The original image
     * @param d The dimensions
     * @return The list of (d by d) sub images
     */
    public static List<BufferedImage> imageSplit(final BufferedImage image, final int d) {
        List<BufferedImage> images = new ArrayList<>();
        int width = image.getWidth() / d;
        int height = image.getHeight() / d;

        for (int x = 0; x + width <= image.getWidth(); x += width) {
            for (int y = 0; y + height <= image.getWidth(); y += height) {
                images.add(image.getSubimage(x, y, width, height));
            }
        }

        return images;
    }

    /**
     * Weather two tiles directly touch
     * @param idxA The first tile index
     * @param idxB The second tile index
     * @param sideLen The number tiles on each side
     * @return
     */
    public static boolean isTouching(final int idxA, final int idxB, final int sideLen) {
        if (idxA < 0 || idxB < 0 || idxA >= sideLen * sideLen || idxB > sideLen * sideLen)
            return false;
        int x1 = getX(idxA, sideLen);
        int y1 = getY(idxA, sideLen);
        int x2 = getX(idxB, sideLen);
        int y2 = getY(idxB, sideLen);
        return Math.ceil(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2))) <= 1;
    }

    /**
     * Find what tile (x or y) coordinate is represented by the location on screen
     * @param screenCoord The (x or y) position in pixels
     * @param length The width or height of game image
     * @return The tile coord
     */
    public static int screenToTileCoord(final int screenCoord, final int length, final int sideLen) {
        return sideLen * screenCoord / length;
    }

    public static int screenToTileIndex(final int xCoord, final int yCoord, final int width,
                                        final int height, final int sideLen) {
        return getIdx(screenToTileCoord(xCoord, width, sideLen), screenToTileCoord(yCoord, height, sideLen), sideLen);
    }

    /**
     * Calculates the tile index based on its coordinates
     *
     * @param x X tile coordinate
     * @param y Y tile coordinate
     * @param sideLen The number of tiles on each side
     * @return The tile index
     */
    public static int getIdx(final int x, final int y, final int sideLen) {
        return y * sideLen + x;
    }


    /**
     * Calculate the tile X coordinate given its tile index
     * @param idx The tile index
     * @param sideLen The number of tiles on each side
     * @return The x coord
     */
    public static int getX(final int idx, final int sideLen) {
        return idx % sideLen;
    }

    /**
     * Calculate the tile Y coordinate given its tile index
     * @param idx The tile index
     * @param sideLen The number of tiles on each side
     * @return The y coord
     */
    public static int getY(final int idx, final int sideLen) {
        return idx / sideLen;
    }
}
