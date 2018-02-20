package org.bradleywood.spgame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Game implements MouseListener {

    private final GameView view;
    private final GameModel model;

    public Game(final GameView view, final GameModel model) {
        this.view = view;
        this.model = model;
        view.addMouseListener(this);
    }

    public GameModel getModel() {
        return model;
    }

    public GameView getView() {
        return view;
    }

    private int getEmptyPos() {
        int[] pieces = model.getPiecePositions();
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i] == pieces.length - 1) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() != MouseEvent.BUTTON1) {
            return;
        }

        int[] positions = model.getPiecePositions();
        synchronized (positions) {
            int emptyIdx = getEmptyPos();
            if (emptyIdx < 0) {
                System.err.println("Invalid game state!");
                return;
            }
            int sideLen = (int) Math.sqrt(model.numPieces());

            // calculate which tile the user clicked on
            int idx = Utility.screenToTileIndex(e.getX(), e.getY(), model.getWidth(), model.getHeight(), sideLen);

            if (idx < 0 || idx >= positions.length || !Utility.isTouching(idx, emptyIdx, sideLen))
                return;

            int tmp = positions[idx];
            positions[idx] = positions[emptyIdx];
            positions[emptyIdx] = tmp;
            view.repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
