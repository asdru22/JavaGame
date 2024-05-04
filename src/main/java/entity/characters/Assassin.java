package entity.characters;

import entity.Playable;
import entity.Stats;
import entity.effects.Resistance;
import main.GamePanel;

public class Assassin extends Playable {
    public Assassin(GamePanel gamePanel) {
        super(gamePanel, "assassin", new Stats(
                100, 90, 0));
    }

    @Override
    public void active(Playable target) {
        dealDamage(target);
    }

    @Override
    public void passive(Playable target) {
        applyEffect(new Resistance(target, 2, 10));
    }
}
