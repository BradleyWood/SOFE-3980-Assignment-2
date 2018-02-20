package org.bradleywood.spgame;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Utility {

    public static List<BufferedImage> imageSplit(final BufferedImage image, int d) {
        List<BufferedImage> images = new ArrayList<>();
        int width = image.getWidth() / d;
        int height = image.getHeight() / d;

        for (int x = 0; x < image.getWidth(); x += width) {
            for (int y = 0; y < image.getWidth(); y += height) {
                images.add(image.getSubimage(x, y, width, height));
            }
        }

        return images;
    }
}
