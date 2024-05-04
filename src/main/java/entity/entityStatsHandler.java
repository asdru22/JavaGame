package entity;

import main.GamePanel;

public abstract class entityStatsHandler extends PlayableEntity {

    public entityStatsHandler(GamePanel gamePanel, String pathName, Stats stats) {
        super(gamePanel, pathName, stats);
    }

    public void dealDamage(entityStatsHandler target){
        target.receiveDamage(stats.damage);
    }

    public void receiveDamage(int damage){
        stats.health -= damage;
        if(stats.health <= 0){
            onDeath();
        }
    }

    public void heal(entityStatsHandler target,int amount){
        target.stats.health += amount;
        if(target.stats.health>=target.stats.maxHealth) target.stats.health = target.stats.maxHealth;
    }

    public void onDeath(){
        stats.health = 0;
        this.setDead();
        System.out.println("dead");
    }
}
