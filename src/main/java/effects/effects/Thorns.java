package effects.effects;

import effects.Effect;
import entity.Playable;

public class Thorns extends Effect {
    public Thorns(Playable owner, int duration, int level) {
        super(owner, duration, level, true);
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
