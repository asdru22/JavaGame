package effects.effects;

import effects.InfiniteEffect;
import entity.Playable;

public class Charge extends InfiniteEffect {
    int charge = 1;
    public Charge(Playable owner) {
        super(owner);
    }

    public void increase(){
        charge++;
        charge = Math.min(charge,3);
    }

    public int get(){
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
    public String toString(){
        return getClass().getSimpleName() + ": " + charge+"/3";
    }
}
