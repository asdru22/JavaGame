package entity;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

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

    public void tick(Iterator<Effect> effect) {
        effect();
        System.out.println(this);
        duration--;
        if (duration == 0) {
            expire();
            effect.remove();
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
