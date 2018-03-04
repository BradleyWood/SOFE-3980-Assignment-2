package org.bradleywood.spgame;

import org.sqtf.annotations.Before;
import org.sqtf.annotations.Test;
import org.sqtf.assertions.Assert;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GameModelTest {

    private BufferedImage image;
    private List<BufferedImage> images;

    @Before
    public void setupImages() throws IOException {
        image = new BufferedImage(256, 256, BufferedImage.TYPE_4BYTE_ABGR);
        images = Utility.imageSplit(image, 4);
    }

    @Test
    public void testRandomize() {
        GameModel model = new GameModel(images, image.getWidth(), image.getHeight());

        for (int i = 0; i < 200; i++) {
            model.randomize();

            int[] piecePositions = model.getPiecePositions();

            Assert.assertEquals(images.size(), piecePositions.length);

            // check that puzzle contains all unique pieces (no duplicates)
            int[] count = new int[model.getPiecePositions().length];

            for (int j = 0; j < piecePositions.length; j++) {
                count[piecePositions[j]]++;
            }

            Assert.assertTrue(Arrays.stream(count).filter(c -> c == 1).count() == count.length);

            // puzzle is solvable if the number of inversions is even
            boolean validPuzzle = countInversions(piecePositions) % 2 == 0;
            Assert.assertTrue(validPuzzle);

        }
    }

    private int countInversions(int[] array) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    count++;
                }
            }
        }
        return count;
    }
}
