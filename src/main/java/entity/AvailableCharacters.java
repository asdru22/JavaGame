package entity;

// Enum for character types
public enum AvailableCharacters {
    ASSASSIN(new AssassinFactory()),
    WIZARD(new WizardFactory()),
    BRUTE(new BruteFactory()),
    SPIKE(new SpikeFactory()),
    SNAKEOILSALESMAN(new SnakeOilSalesmanFactory()),
    ECLIPSE(new EclipseFactory());


    private final PlayableFactory factory;

    AvailableCharacters(PlayableFactory factory) {
        this.factory = factory;
    }

    public PlayableFactory getFactory() {
        return factory;
    }
}
