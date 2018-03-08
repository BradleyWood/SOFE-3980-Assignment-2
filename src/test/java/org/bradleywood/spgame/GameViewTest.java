package org.bradleywood.spgame;

import org.sqtf.annotations.Before;
import org.sqtf.annotations.Test;
import org.sqtf.assertions.Assert;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class GameViewTest {

    private List<BufferedImage> images;
    private BufferedImage image;
    private GameView gameView;
    private GameModel model;
    private int WIDTH = 256;
    private int HEIGHT = 256;
    private int d = 4;
    private int tileWidth;
    private int tileHeight;

    @Before
    public void setup() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);

        Graphics g = image.getGraphics();

        tileWidth = image.getWidth() / d;
        tileHeight = image.getHeight() / d;

        int colorCounter = 0;

        for (int y = 0; y + tileHeight <= image.getHeight(); y += tileHeight) {
            for (int x = 0; x + tileWidth <= image.getWidth(); x += tileWidth) {
                g.setColor(new Color(colorCounter++));
                g.fillRect(x, y, tileWidth, tileHeight);
            }
        }

        images = Utility.imageSplit(image, d);
        model = new GameModel(images, WIDTH, HEIGHT);
        model.randomize();
        gameView = new GameView(model);
    }

    /**
     * Tests that the tile is displayed in the correct position by checking color at given coordinates
     */
    @Test
    public void testTileDisplay() {
        BufferedImage img = new BufferedImage(WIDTH, HEIGHT, image.getType());
        gameView.paintComponent(img.getGraphics());

        int[] piecePositions = model.getPiecePositions();
        int counter = 0;

        for (int y = 0; y + tileHeight <= image.getHeight(); y += tileHeight) {
            for (int x = 0; x + tileWidth <= image.getWidth(); x += tileWidth) {
                int[] pixels = img.getRGB(x, y, tileWidth, tileHeight, null, 0, tileWidth);
                Assert.assertEquals(tileWidth * tileHeight, pixels.length);
                int expectedColor = 0xff000000 | piecePositions[counter];
                if (piecePositions[counter] == piecePositions.length - 1)
                    expectedColor = Color.GRAY.getRGB();

                for (int pixel : pixels) {
                    if (pixel != expectedColor) {
                        Assert.fail("Incorrect color for given tile");
                    }
                }
                counter++;
            }
        }
    }
}
