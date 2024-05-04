package entity;

import main.GamePanel;

public abstract class Playable extends EntityStatsHandler {
    public Playable(GamePanel gamePanel, String pathName, Stats stats) {
        super(gamePanel, pathName, stats);
    }
}
