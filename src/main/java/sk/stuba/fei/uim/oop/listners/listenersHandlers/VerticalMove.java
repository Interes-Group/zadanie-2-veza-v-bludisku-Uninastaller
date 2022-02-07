package sk.stuba.fei.uim.oop.listners.listenersHandlers;

import sk.stuba.fei.uim.oop.maze.Player;

public class VerticalMove extends Move {

    public VerticalMove(int index, Player player) {
        super(index, player);
    }

    @Override
    public void move(int x, int y) {

        player.canMoveVertically(x, y, INDEX);

    }

}
