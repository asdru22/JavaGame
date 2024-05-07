package effects;

import entity.Playable;

import java.util.Iterator;
import java.util.Objects;

public abstract class Effect {
    public int duration;
    public Playable owner;
    public int level;

    public boolean positive;

    public Effect(Playable owner, int duration, int level, boolean positive) {
        this.duration = duration;
        this.owner = owner;
        this.level = level;
        this.positive = positive;
    }

    public Effect(Playable owner, int duration, boolean positive) {
        this.duration = duration;
        this.owner = owner;
        this.level = 0;
        this.positive = positive;
    }

    public abstract void apply();
    public abstract void effect();
    public abstract void expire();

    public void tick(Iterator<Effect> effect) {
        effect();
        if(!(this instanceof InfiniteEffect)){
            duration--;
            if (duration == 0) {
                expire();
                effect.remove();
            }
        }

    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " (" + duration + ")";
    }

    public String getName(){
        return getClass().getSimpleName();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Effect other = (Effect) obj;
        return Objects.equals(getName(), other.getName());
    }

}
