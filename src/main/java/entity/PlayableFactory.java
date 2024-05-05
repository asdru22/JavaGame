package entity;

import entity.characters.Assassin;
import entity.characters.Brute;
import entity.characters.Spike;
import entity.characters.Wizard;
import main.GamePanel;

// Define the PlayableFactory interface
public interface PlayableFactory {
    Playable createPlayable(GamePanel gamePanel);
}

// Implement the factory for each character type
class AssassinFactory implements PlayableFactory {
    @Override
    public Playable createPlayable(GamePanel gamePanel) {
        return new Assassin(gamePanel);
    }
}

class WizardFactory implements PlayableFactory {
    @Override
    public Playable createPlayable(GamePanel gamePanel) {
        return new Wizard(gamePanel);
    }
}

class BruteFactory implements PlayableFactory {
    @Override
    public Playable createPlayable(GamePanel gamePanel) {
        return new Brute(gamePanel);
    }
}

class SpikeFactory implements PlayableFactory {
    @Override
    public Playable createPlayable(GamePanel gamePanel) {
        return new Spike(gamePanel);
    }
}

