package effects.effects;

import effects.Effect;
import entity.Playable;

public class Poison extends Effect {
    public Poison(Playable owner, int duration, int level) {
        super(owner, duration, level);
    }

    @Override
    public void apply() {

    }

    @Override
    public void effect() {
        owner.dealDamage(owner,level);
    }

    @Override
    public void expire() {

    }
}
