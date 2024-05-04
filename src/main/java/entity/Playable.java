package entity;

import main.GamePanel;

public abstract class Playable extends EntityStatsHandler {
    protected String active = "";
    protected String passive = "";

    public Playable(GamePanel gamePanel, String pathName, Stats stats) {
        super(gamePanel, pathName, stats);
    }
    public String getInfo(){
        String r = "";
        r += getName()+"\n";
        r+= stats.getHealth()+"\n";
        r+= stats.getOthers()+"\n\n";
        r+="Active: "+active+"\n";
        r+="Passive: "+passive+"\n";
        r+=effectsToString();

        return r;
    }
}
