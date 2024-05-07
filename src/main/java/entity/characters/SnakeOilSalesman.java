package entity.characters;

import effects.effects.Poison;
import effects.effects.Regeneration;
import entity.Playable;
import entity.PlayerParty;
import entity.Stats;
import main.GamePanel;

import java.util.List;

public class SnakeOilSalesman extends Playable {
    public SnakeOilSalesman(GamePanel gamePanel) {
        super(gamePanel, "snake_oil_salesman", new Stats(
                40, 7, 0));
        active = new Ability("Snake Oil", "Apply Poison (" + stats.damage + ") for 3 turns to target");
        passive = new Ability("Heal", "If target's health is greater then 20% it will heal 13 health, otherwise deal 10 damage");
        info = "Increases max health of all characters on field by 5";
    }

    @Override
    public void active(Playable target) {
        applyEffect(new Poison(target, 3, stats.damage));
    }

    @Override
    public void passive(Playable target) {
        double hp = target.getHealthPercentage();
        if (hp >= 20) target.heal(13);
        else target.onDeath();
    }

    @Override
    public void deathEffect() {
        for (Playable p : getParty().characters) {
            if(p.isAlive()) p.decreaseMaxHealth(5);
        }

    }

}
