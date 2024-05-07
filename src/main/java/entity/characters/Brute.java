package entity.characters;

import entity.Playable;
import entity.Stats;
import effects.effects.Charge;
import main.GamePanel;

public class Brute extends Playable {

    public Brute(GamePanel gamePanel) {
        super(gamePanel, "brute", new Stats(
                70, 5, 5));

        active = new Ability("Slam","Deal "+stats.damage+" damage to the target. At full charge will deal 500% more damage");
        passive = new Ability("Charge","Charge own attack");
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

    @Override
    public void deathEffect() {

    }
}
