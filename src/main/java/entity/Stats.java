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
        if(health>=1) return "Health: "+health+"/"+maxHealth;
        else return "Dead";
    }
    public String getOthers(){
        return "Damage: "+damage+", Defense: "+ defense;
    }
}
