package entity.characters;

import entity.Playable;
import entity.Stats;
import entity.effects.Charge;
import entity.effects.Resistance;
import main.GamePanel;

public class Brute extends Playable {

    public Brute(GamePanel gamePanel) {
        super(gamePanel, "brute", new Stats(
                70, 5, 5));

        active = "Slam";
        passive = "Charge";
    }

    @Override
    public void active(Playable target) {
        String id = "Charge";
        if (hasEffect(id)) {
            Charge c = (Charge) getEffect("Charge");
            int charge = c.get();
            if (charge < 2) {
                dealDamage(target);
            } else {
                dealDamage(target, stats.damage * 5);
                removeEffect(c);
            }
        } else dealDamage(target);
    }

    @Override
    public void passive(Playable target) {
        Playable receiver = this;
        if (receiver.hasEffect("Charge")) {
            Charge c = (Charge) receiver.getEffect("Charge");
            c.increase();
        } else applyEffect(new Charge(receiver));
    }
}
