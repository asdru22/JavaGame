package entity;

public class Stats {
    public int health;
    public int maxHealth;
    public int damage;

    public Stats(int maxHealth, int damage){
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.damage = damage;
    }
}
