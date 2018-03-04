package org.bradleywood.spgame;


import org.junit.Assert;
import org.sqtf.annotations.Before;
import org.sqtf.annotations.Test;

import java.awt.image.BufferedImage;

public class GameTest {

    private GameModel model;
    private Game game;

    @Before
    public void setupGame() {
        BufferedImage img = new BufferedImage(256, 256, BufferedImage.TYPE_4BYTE_ABGR);
        model = new GameModel(Utility.imageSplit(img, 4), img.getWidth(), img.getHeight());
        game = new Game(new GameView(model), model);
    }

    @Test
    public void testCheckWin() {
        // game is initialized in winning state
        Assert.assertTrue(game.checkWin());
        model.randomize();
        Assert.assertFalse(game.checkWin());
    }
}
