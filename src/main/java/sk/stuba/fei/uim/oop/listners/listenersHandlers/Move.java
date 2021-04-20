package sk.stuba.fei.uim.oop.listners.listenersHandlers;

import sk.stuba.fei.uim.oop.maze.Player;

public abstract class Move {

    protected final int INDEX;
    Player player;

    protected Move(int index, Player player) {

        INDEX = index;
        this.player = player;

    }

    public abstract void move(int x, int y);

}
