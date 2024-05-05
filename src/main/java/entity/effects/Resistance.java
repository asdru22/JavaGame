package entity.effects;

import entity.Effect;
import entity.Playable;

public class Resistance extends Effect {
    public Resistance(Playable owner, int duration, int level) {
        super(owner, duration, level);
    }

    @Override
    public void effect() {
        //owner.stats.defense += level;
    }

    @Override
    public void expire() {
        //owner.stats.defense -= level;
    }
}
