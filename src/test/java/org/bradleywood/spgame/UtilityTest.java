package org.bradleywood.spgame;

import org.sqtf.annotations.Test;
import org.sqtf.assertions.Assert;

import java.awt.image.BufferedImage;
import java.util.List;

public class UtilityTest {


    /**
     * Method: imageSplit(final BufferedImage image, final int d)
     */
    @Test
    public void testImageSplit() {
        BufferedImage img = new BufferedImage(256, 256, BufferedImage.TYPE_4BYTE_ABGR);
        List<BufferedImage> imageList = Utility.imageSplit(img, 4);
        Assert.assertEquals(4 * 4, imageList.size());
        Assert.assertEquals(256 / 4, imageList.get(0).getWidth());
        Assert.assertEquals(256 / 4, imageList.get(0).getHeight());
    }

    /**
     * Method: isTouching(final int idxA, final int idxB, final int sideLen)
     */
    @Test
    public void testIsTouching() {
        int sideLen = 4;
        Assert.assertTrue(Utility.isTouching(0, 1, sideLen));
        Assert.assertTrue(Utility.isTouching(0, 4, sideLen));
        Assert.assertTrue(Utility.isTouching(2, 3, sideLen));

        Assert.assertFalse(Utility.isTouching(1, 3, sideLen));
        Assert.assertFalse(Utility.isTouching(3, 4, sideLen));
        Assert.assertFalse(Utility.isTouching(0, 5, sideLen));

        Assert.assertFalse(Utility.isTouching(-1, 0, sideLen));
        Assert.assertFalse(Utility.isTouching(sideLen * sideLen, sideLen * sideLen - 1, sideLen));
    }

    /**
     * Method: screenToTileCoord(final int screenCoord, final int length, final int sideLen)
     */
    @Test
    public void testScreenToTileCoord() {
        Assert.assertEquals(0, Utility.screenToTileCoord(0, 256, 4));
        Assert.assertEquals(0, Utility.screenToTileCoord(63, 256, 4));
        Assert.assertEquals(1, Utility.screenToTileCoord(64, 256, 4));
        Assert.assertEquals(1, Utility.screenToTileCoord(127, 256, 4));
        Assert.assertEquals(2, Utility.screenToTileCoord(191, 256, 4));
        Assert.assertEquals(3, Utility.screenToTileCoord(192, 256, 4));
        Assert.assertEquals(3, Utility.screenToTileCoord(255, 256, 4));

        Assert.assertEquals(0, Utility.screenToTileCoord(0, 100, 5));
        Assert.assertEquals(0, Utility.screenToTileCoord(19, 100, 5));
        Assert.assertEquals(1, Utility.screenToTileCoord(20, 100, 5));
    }

    /**
     * Method: screenToTileIndex(final int xCoord, final int yCoord, final int width, final int height, final int sideLen)
     */
    @Test
    public void testScreenToTileIndex() {
        Assert.assertEquals(15, Utility.screenToTileIndex(255, 255,256, 256, 4));
        Assert.assertEquals(0, Utility.screenToTileIndex(0, 0,256, 256, 4));
        Assert.assertEquals(3, Utility.screenToTileIndex(255, 0,256, 256, 4));
        Assert.assertEquals(12, Utility.screenToTileIndex(0, 255,256, 256, 4));
    }

    /**
     * Method: getIdx(final int x, final int y, final int sideLen)
     */
    @Test
    public void testGetIdx() {
        Assert.assertEquals(4 * 4 - 1, Utility.getIdx(3, 3, 4));

        Assert.assertEquals(8 * 8 - 1, Utility.getIdx(7, 7, 8));

        Assert.assertEquals(4 * 4 - 4, Utility.getIdx(0, 3, 4));
        Assert.assertEquals(4 * 4 - 3, Utility.getIdx(1, 3, 4));
        Assert.assertEquals(4 * 4 - 2, Utility.getIdx(2, 3, 4));
        Assert.assertEquals(4 * 4 - 1, Utility.getIdx(3, 3, 4));
    }

    /**
     * Method: getX(final int idx, final int sideLen)
     */
    @Test
    public void testGetX() {
        Assert.assertEquals(3, Utility.getX(3, 4));
        Assert.assertEquals(0, Utility.getX(4, 4));
        Assert.assertEquals(2, Utility.getX(6, 4));
    }

    /**
     * Method: getY(final int idx, final int sideLen)
     */
    @Test
    public void testGetY() {
        Assert.assertEquals(0, Utility.getY(0, 4));
        Assert.assertEquals(0, Utility.getY(3, 4));
        Assert.assertEquals(1, Utility.getY(4, 4));
        Assert.assertEquals(1, Utility.getY(7, 4));
        Assert.assertEquals(2, Utility.getY(8, 4));
    }

} 
