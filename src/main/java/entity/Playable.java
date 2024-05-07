package entity;

import main.GamePanel;

public abstract class Playable extends EntityStatsHandler {

    public Ability active;
    public Ability passive;
    public String info = null;

    public static class Ability {
        String name;
        String description;
        private PlayerParty playerParty = null;

        public Ability(String name, String description){
            this.name = name;
            this.description = description;
        }

        @Override
        public String toString(){
            return name+": "+ description;
        }

        public String getName(){
            return name;
        }
    }



    public Playable(GamePanel gamePanel, String pathName, Stats stats) {
        super(gamePanel, pathName, stats);
    }
    public String getInfo(){
        String r = "";
        r += getName()+"\n";
        r+= stats.getHealth()+"\n";
        r+= stats.getOthers()+"\n\n";
        r+="A> "+ active+"\n";
        r+="P> "+passive+"\n";
        if(info!=null) r+= "E> "+ info+"\n";
        if(!getEffects().isEmpty()) r+="\n"+effectsToString();
        return r;
    }

    public static Playable get(AvailableCharacters c, GamePanel gamePanel) {
        return c.getFactory().createPlayable(gamePanel);
    }

    public void setupEffects(){
        if(getParty().hasCharacter("Snake Oil Salesman")){
            increaseMaxHealth(5);
        }
    }


}
