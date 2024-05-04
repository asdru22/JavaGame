package entity;

import main.GamePanel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class EntityStatsHandler extends PlayableEntity {

    private final List<Effect> activeEffects = new ArrayList<>();

    public EntityStatsHandler(GamePanel gamePanel, String pathName, Stats stats) {
        super(gamePanel, pathName, stats);
    }

    public void dealDamage(EntityStatsHandler target) {
        target.receiveDamage(stats.damage);
    }

    public void receiveDamage(int damage) {
        damage = Math.min(0, damage - stats.defense);
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
        e.owner.getEffects().add(e);
    }

    public List<Effect> getEffects() {
        return activeEffects;
    }

    public void effectTick() {
        Effect e;
        Iterator<Effect> iterator = activeEffects.iterator();
        while (iterator.hasNext()) {
            e = iterator.next();
            e.tick(activeEffects);
        }
    }
}
