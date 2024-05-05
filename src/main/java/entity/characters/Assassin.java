package entity.characters;

import entity.Playable;
import entity.PlayerParty;
import entity.Stats;
import Effects.effects.Resistance;
import main.GamePanel;

public class Assassin extends Playable {

    public Assassin(GamePanel gamePanel) {
        super(gamePanel, "assassin", new Stats(
                50, 100, 0));

        active = new Ability("Stab","Deal "+stats.damage+" damage x3 to the target");
        passive = new Ability("Shield","Apply Resistance (5) for 3 turns to the target");
    }

    @Override
    public void active(Playable target) {
        dealDamage(target);
        dealDamage(target);
        dealDamage(target);
    }

    @Override
    public void passive(Playable target) {
        applyEffect(new Resistance(target, 3, 5));
    }
}
