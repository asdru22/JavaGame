package entity;

import java.util.List;

public abstract class Effect {
    public int duration;
    public Playable owner;
    public int level;

    public Effect(Playable owner, int duration, int level) {
        this.duration = duration;
        this.owner = owner;
        this.level = level;
    }

    public abstract void effect();

    public abstract void expire();

    public void tick(List<Effect> effects) {
        effect();
        System.out.println(this);
        duration--;
        if (duration == 0) {
            expire();
            effects.remove(this);
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " (" + duration + ")";
    }
}
