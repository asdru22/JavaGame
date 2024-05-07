package entity.characters;

import entity.Playable;
import entity.Stats;
import effects.effects.Regeneration;
import main.GamePanel;

import java.util.List;

public class Wizard extends Playable {
    public Wizard(GamePanel gamePanel) {
        super(gamePanel, "wizard", new Stats(
                40, 7, 0));
        active = new Ability("Zap","Deal "+stats.damage+" damage to all party members");
        passive = new Ability("Heal","Apply Regeneration (10) for 4 turns to target");
    }

    @Override
    public void active(Playable target) {
        List<Playable> p = target.getParty().characters;
        for(Playable c : p){
            dealDamage(c);

        }
    }

    @Override
    public void passive(Playable target) {
        applyEffect(new Regeneration(target, 4, 10));
    }

    @Override
    public void deathEffect() {

    }

}
