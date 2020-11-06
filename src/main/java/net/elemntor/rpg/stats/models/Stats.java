package net.elemntor.rpg.stats.models;

public class Stats {
    private int intelligence;
    private int dexterity;
    private int straight;
    private int charisma;
    private int constitution;
    private int wisdom;
    private int luck;
    public Stats(){
        intelligence = 0;
        dexterity = 0;
        straight = 0;
        charisma = 0;
        constitution = 0;
        wisdom = 0;
        luck = 0;
    }


    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getStraight() {
        return straight;
    }

    public void setStraight(int straight) {
        this.straight = straight;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
}
