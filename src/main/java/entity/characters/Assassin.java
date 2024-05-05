package entity.characters;

import entity.Playable;
import entity.Stats;
import Effects.effects.Resistance;
import main.GamePanel;

public class Assassin extends Playable {

    public Assassin(GamePanel gamePanel) {
        super(gamePanel, "assassin", new Stats(
                50, 10, 0));

        active = "Punch";
        passive = "Shield";
    }

    @Override
    public void active(Playable target) {
        dealDamage(target);
    }

    @Override
    public void passive(Playable target) {
        applyEffect(new Resistance(target, 3, 10));
    }


}
