package entity;

import Effects.Effect;
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
        if(!target.getEffects().isEmpty()) onHitEffects(target);  

        target.receiveDamage(stats.damage);
    }

    public void dealDamage(EntityStatsHandler target, int amount) {
        if(!target.getEffects().isEmpty()) onHitEffects(target);  

        target.receiveDamage(amount);
    }

    public void receiveDamage(int damage) {
        if(!getEffects().isEmpty()) whenHitEffects();

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
        this.getEffects().clear();
    }

    public static void applyEffect(Effect e) {
        Playable o = e.owner;
        if (o.hasEffect(e)) o.removeEffect(e);
        o.getEffects().add(e);
        e.apply();
    }

    public void removeEffect(Effect e) {
        Effect effect;
        Iterator<Effect> iterator = activeEffects.iterator();
        while (iterator.hasNext()) {
            effect = iterator.next();
            if (Objects.equals(e, effect)) {
                iterator.remove();
                e.expire();
            }
        }
    }

    public boolean hasEffect(Effect e) {
        List<Effect> effects = getEffects();
        for (Effect effect : effects) {
            if (Objects.equals(effect.getName(), e.getName())) return true;
        }
        return false;
    }

    public boolean hasEffect(String id) {
        List<Effect> effects = getEffects();
        for (Effect effect : effects) {
            if (Objects.equals(effect.getName(), id)) return true;
        }
        return false;
    }

    public int getEffectLevel(Effect e) {
        List<Effect> effects = getEffects();
        if (hasEffect(e)) {
            for (Effect effect : effects) {
                if (Objects.equals(effect.getName(), e.getName())) return e.level;
            }
        }
        return -1;
    }

    public int getEffectLevel(String id) {
        List<Effect> effects = getEffects();
        if (hasEffect(id)) {
            for (Effect effect : effects) {
                if (Objects.equals(effect.getName(), id)) return effect.level;
            }
        }
        return -1;
    }

    public int getEffectDuration(Effect e) {
        List<Effect> effects = getEffects();
        if (hasEffect(e)) {
            for (Effect effect : effects) {
                if (Objects.equals(effect.getName(), e.getName())) return e.duration;
            }
        }
        return -1;
    }

    public List<Effect> getEffects() {
        return activeEffects;
    }

    public String effectsToString() {
        StringBuilder r = new StringBuilder("Active Effects:\n");
        for (Effect e : activeEffects) {
            r.append("- ").append(e).append("\n");
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

    public Effect getEffect(String id) {
        List<Effect> effects = getEffects();
        if (hasEffect(id)) {
            for (Effect effect : effects) {
                if (Objects.equals(effect.getName(), id)) return effect;
            }
        }
        return null;
    }

    public void onHitEffects(EntityStatsHandler target){
        if(target.hasEffect("Thorns")){
            int damage = target.getEffect("Thorns").level;
            receiveDamage(damage);
        }    
    };

    public void whenHitEffects(){

    };

}
