package entity.characters;

import entity.PlayableEntity;
import entity.Stats;
import entity.entityStatsHandler;
import main.GamePanel;

public class Wizard extends entityStatsHandler {
    public Wizard(GamePanel gamePanel) {
        super(gamePanel, "wizard", new Stats(10,10));
    }

    @Override
    public void active(PlayableEntity target) {
        dealDamage((entityStatsHandler) target);
    }

    @Override
    public void passive(PlayableEntity target) {
        heal((entityStatsHandler) target,6);
    }
}
