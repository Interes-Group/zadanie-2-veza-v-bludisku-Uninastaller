package sk.stuba.fei.uim.oop.listners.listenersHandlers;

import sk.stuba.fei.uim.oop.maze.Player;

public class HorizontalMove extends Move {

    public HorizontalMove(int index, Player player) {
        super(index, player);
    }

    @Override
    public void move(int x, int y) {

        player.canMoveHorizontally(x, y, INDEX);

    }

}
