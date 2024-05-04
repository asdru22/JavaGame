package entity;

public class Stats {
    public int health;
    public int maxHealth;
    public int damage;
    public int defense;

    public Stats(int maxHealth, int damage, int defense) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.damage = damage;
        this.defense = defense;
    }
    public String getHealth(){
        return "Health: "+health+"/"+maxHealth;
    }
    public String getOthers(){
        return "Damage: "+damage+", Defense: "+ defense;
    }
}
