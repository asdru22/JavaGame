package entity.characters;

import entity.PlayableEntity;
import entity.Stats;
import entity.entityStatsHandler;
import main.GamePanel;

public class Assassin extends entityStatsHandler {
    public Assassin(GamePanel gamePanel) {
        super(gamePanel, "assassin", new Stats(50,7));
    }

    @Override
    public void active(PlayableEntity target) {
        dealDamage((entityStatsHandler) target);
    }

    @Override
    public void passive(PlayableEntity target) {
        System.out.println("passive");
    }
}
