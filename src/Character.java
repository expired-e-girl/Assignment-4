//Marilyn Ruptash
//ID: 261126626
//Assignment 4 is a battle game that uses file reading to build objects and file writing to record wins of the battles conducted.

import java.util.ArrayList;
import java.util.Random;

public class Character {
    private String name;
    private double attackValue;         //Private attributes of character
    private double maxHealth;
    private double currHealth;
    private int numWins;
    private ArrayList <Spell> spells;

    //Constructor for Character class
    Character(String name, double attackValue, double maxHealth, int numWins){
        this.name = name;
        this.attackValue = attackValue;         //Assign values passed as arguments to character
        this.maxHealth = maxHealth;
        this.numWins = numWins;
        this.currHealth = maxHealth;
    }

    public String getName(){        //Name getter method
        return this.name;
    }

    public double getAttackValue(){
        return this.attackValue;
    }   //Attack value getter method

    public double getMaxHealth(){
        return this.maxHealth;
    }       //Max health getter method

    public double getCurrHealth(){
        return this.currHealth;
    }      //Current health getter method

    public void setSpells(ArrayList<Spell> listSpells){            //Setter method for Spells
         spells = listSpells;
    }
    public void displaySpells() {                   //Getter method to display Spells
        String statSpell = null;
        for (Spell spell : spells) {
            statSpell = spell.stats();
            System.out.println(statSpell);
        }
    }

    //Method to cast spell
    public Double castSpell(String spellName, int x ) {     //Arguments are the spell name and a random integer
        if(spellName == null){      //Checks that spell name is not null
            return -1.0;
        }
        for (Spell spell : spells) {            //Checks the spell array to see if the passed argument spell is available
            if (spell.getName().equalsIgnoreCase(spellName)) {      //If available casts the spell
                if(spell.getMagicDamage(x) > 0) {                   //If cast is less than greater than 0 show damage caused
                    System.out.println(this.name + " casts " + spellName + " for " + String.format("%,1.2f",spell.getMagicDamage(x)) + " damage.");
                } else {                                            //If cast is less than 0 show no effect message
                    System.out.println(this.name + " casts " + spellName + " but it has no effect.");
                }
                return spell.getMagicDamage(x);     //If available returns double of the spell damage
            }
        }
        return -1.0;          //If ineffective returns -1.0
    }

    //Method to get the current health of character
    public String status(){
        double hp = getCurrHealth();
        String healthStat =  String.format("%1$.2f", hp);
        return this.name + " currently has " + healthStat + " HP.";
    }

    //Method to get damage caused by attack
    public double getAttackDamage(int seed){
        Random attackDmg = new Random(seed);
        double range = attackDmg.nextDouble(0.7,1.0);   //Generates random double between 0.7 and 1
        return (getAttackValue() * range);              //Returns damage caused
    }

    //Method that adjusts healths according to damage caused
    public double takeDamage(double damage){
        double newHealth = getCurrHealth() - damage;
        this.currHealth = newHealth;
        return newHealth;               //Update and return health after deducting damage
    }

    public void increaseWins(){     //Method to increase the amounts of win of the character
        this.numWins++;
    }

    public int getNumWins(){        //Getter method for number of wins
        return this.numWins;
    }


    //TESTING METHODS//
//    public static void main(String[] args) {
//        Character salem = new Character("Salem", 39.0, 50.00,0);
//        System.out.println(salem.getCurrHealth());
//        System.out.println(salem.getAttackValue());
//        System.out.println(salem.getMaxHealth());
//        System.out.println(salem.getAttackDamage(444));

//        salem.takeDamage(2.00);
//        System.out.println(salem.status());
//        salem.setSpells(FileIO.readSpells("D:\\UNIVERSITY\\Programming Tech 1\\Assignment 4\\files\\spells.txt"));
//        salem.displaySpells();
//        System.out.println(salem.castSpell("icestorm", 111));
//    }
}
