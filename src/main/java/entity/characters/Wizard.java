package entity.characters;

import entity.Playable;
import entity.Stats;
import Effects.effects.Regeneration;
import main.GamePanel;

public class Wizard extends Playable {
    public Wizard(GamePanel gamePanel) {
        super(gamePanel, "wizard", new Stats(
                40, 15, 0));
        active = "Zap";
        passive = "Heal";
    }

    @Override
    public void active(Playable target) {
        dealDamage(target);
    }

    @Override
    public void passive(Playable target) {
        applyEffect(new Regeneration(target, 4, 10));
    }

}
