package entity.characters;

import entity.Playable;
import entity.PlayerParty;
import entity.Stats;
import Effects.effects.Resistance;
import main.GamePanel;

public class Assassin extends Playable {

    public Assassin(GamePanel gamePanel) {
        super(gamePanel, "assassin", new Stats(
                50, 10, 0));

        active = new Ability("Stab","Deal "+stats.damage+" damage to the target's party");
        passive = new Ability("Shield","Apply Resistance (5) for 3 turns to the target");
    }

    @Override
    public void active(Playable target) {
        PlayerParty p = target.getParty();
        dealDamage(target);
    }

    @Override
    public void passive(Playable target) {
        applyEffect(new Resistance(target, 3, 5));
    }


}
