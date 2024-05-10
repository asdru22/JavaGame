package entity.characters;

import effects.Effect;
import effects.InfiniteEffect;
import effects.effects.Charge;
import entity.EntityStatsHandler;
import entity.Playable;
import entity.Stats;
import main.GamePanel;

public class Eclipse extends Playable {
    private static class FirstContact extends InfiniteEffect {
        int charge = 1;

        public FirstContact(Playable owner) {
            super(owner, true);
        }

        public void increase() {
            charge++;
            charge = Math.min(charge, 2);
        }

        public int get() {
            return charge;
        }

        @Override
        public void apply() {
        }

        @Override
        public void effect() {
        }

        @Override
        public void expire() {
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + ": " + charge + "/2";
        }
    }

    private static class Darkness extends Effect {

        public Darkness(Playable owner) {
            super(owner, 5, false);
        }

        @Override
        public void apply() {
        }

        @Override
        public void effect() {
        }

        @Override
        public void expire() {
        }
    }

    private static class Totality extends Effect {

        public Totality(Playable owner) {
            super(owner, 4, true);
        }

        @Override
        public void apply() {
            owner.stats.damage += 20;
        }

        @Override
        public void effect() {
        }

        @Override
        public void expire() {
            owner.stats.damage -= 20;
        }
    }


    public Eclipse(GamePanel gamePanel) {
        super(gamePanel, "eclipse", new Stats(
                50, 8, 0));

        active = new Ability("Totality",
                "If First Contact=2 and doesn't have Totality,\n apply Darkness to target for 5 turns. If target Darkness<=3, apply Totality to herself\n for 3 turns. Otherwise, deal "+stats.damage+" damage.");
        passive = new Ability("First Contact",
                "If she doesn't have Totality, charge First Contact.\nIf she does, remove Totality and gain 10 health.");
        info = "Cannot be healed by party members besides herself";

    }

    @Override
    public void active(Playable target) {
        if (!hasEffect("Totality")) {

            if (!target.hasEffect("Darkness")) dealDamage(target);
            else {
                int turns = target.getEffect("Darkness").duration;
                if (turns <= 3) {
                    target.removeEffect(target.getEffect("Darkness"));
                    applyEffect(new Totality(this));
                }
            }
        } else {
            int heal = dealDamage(target);
            heal((int)(heal*0.25));
        }
    }

    @Override
    public void passive(Playable target) {
        if (hasEffect("Totality")) {
            heal(10);
            removeEffect(getEffect("Totality"));
        } else {
            if (hasEffect("FirstContact")) {
                FirstContact c = (FirstContact) getEffect("FirstContact");
                c.increase();
            } else applyEffect(new FirstContact(this));
        }
    }

    @Override
    public void deathEffect() {

    }

    public void chargeCheck(EntityStatsHandler target) {
        FirstContact fc = (FirstContact) getEffect("FirstContact");
        int charge = fc.get();

        if (charge >= 2) {
            removeEffect(fc);
            applyEffect(new Darkness((Playable) target));
        }
    }

}
