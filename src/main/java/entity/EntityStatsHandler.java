package entity;

import main.GamePanel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public abstract class EntityStatsHandler extends PlayableEntity {

    private final List<Effect> activeEffects = new ArrayList<>();

    public EntityStatsHandler(GamePanel gamePanel, String pathName, Stats stats) {
        super(gamePanel, pathName, stats);
    }

    public void dealDamage(EntityStatsHandler target) {
        target.receiveDamage(stats.damage);
    }

    public void receiveDamage(int damage) {
        damage = Math.max(0, damage - stats.defense);
        stats.health -= damage;
        if (stats.health <= 0) {
            onDeath();
        }
    }

    public void heal(EntityStatsHandler target, int amount) {
        target.stats.health += amount;
        if (target.stats.health >= target.stats.maxHealth) target.stats.health = target.stats.maxHealth;
    }

    public void heal(int amount) {
        heal(this, amount);
    }

    public void onDeath() {
        stats.health = 0;
        this.setDead();
    }

    public static void applyEffect(Effect e) {
        Playable o = e.owner;
        if(o.hasEffect(e)) o.removeEffect(e);
        o.getEffects().add(e);
    }

    public void removeEffect(Effect e){
        Effect effect;
        Iterator<Effect> iterator = activeEffects.iterator();
        while (iterator.hasNext()) {
            effect = iterator.next();
            if(Objects.equals(e,effect)){
                iterator.remove();
                System.out.println("effect removed");
            }
        }
    }

    public boolean hasEffect(Effect e){
        List<Effect> effects = e.owner.getEffects();
        for(Effect effect : effects){
            if(Objects.equals(effect.getName(), e.getName())) return true;
        }
        return false;
    }

    public List<Effect> getEffects() {
        return activeEffects;
    }

    public String effectsToString() {
        StringBuilder r = new StringBuilder("Effects:\n");
        for(Effect e : activeEffects){
            r.append(e).append("\n");
        }
        return r.toString();
    }

    public void effectTick() {
        Effect e;
        Iterator<Effect> iterator = activeEffects.iterator();
        while (iterator.hasNext()) {
            e = iterator.next();
            e.tick(iterator);
        }
    }
}
