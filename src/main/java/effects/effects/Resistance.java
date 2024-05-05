package effects.effects;

import effects.Effect;
import entity.Playable;

public class Resistance extends Effect {
    public Resistance(Playable owner, int duration, int level) {
        super(owner, duration, level);
    }

    @Override
    public void apply() {
        owner.stats.defense += level;
    }

    @Override
    public void effect() {
    }

    @Override
    public void expire() {
        owner.stats.defense -= level;
    }
}
