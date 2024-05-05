package entity.characters;

import Effects.effects.Resistance;
import Effects.effects.Thorns;
import entity.Playable;
import entity.Stats;
import main.GamePanel;

public class Spike extends Playable {

    public Spike(GamePanel gamePanel) {
        super(gamePanel, "spike", new Stats(
                50, 10, 0));

        active = new Ability("Needle Throw", "Deal " + stats.damage + " damage to the target and the characters above and below them");
        passive = new Ability("Desert's Blessing", "Apply Thorns (20) for 3 turns to the target");
    }

    @Override
    public void active(Playable target) {
        dealDamage(target);
        Playable p;

        p = target.getAbove();
        if(p!=null) dealDamage(p);

        p = target.getBelow();
        if(p!=null) dealDamage(p);
    }

    @Override
    public void passive(Playable target) {
        applyEffect(new Thorns(target, 3, 100));
    }
}
