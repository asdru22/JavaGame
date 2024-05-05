package entity.effects;

import entity.Effect;
import entity.Playable;

public class Charge extends Effect {
    int charge = 1;
    public Charge(Playable owner) {
        super(owner, 999);
    }

    public void increase(){
        charge++;
        charge = Math.min(charge,3);
    }

    public int get(){
        return charge;
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
