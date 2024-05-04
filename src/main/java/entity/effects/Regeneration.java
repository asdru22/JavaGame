package entity.effects;

import entity.Effect;
import entity.Playable;

public class Regeneration extends Effect {
    public Regeneration(Playable owner, int duration, int level) {
        super(owner, duration, level);
    }

    @Override
    public void effect() {
        owner.heal(owner, level);
    }

    @Override
    public void expire() {

    }
}
