//Marilyn Ruptash
//ID: 261126626
//Assignment 4 is a battle game that uses file reading to build objects and file writing to record wins of the battles conducted.

import java.text.NumberFormat;
import java.util.Random;

public class Spell {
    private String name;
    private double minDmg;      //Private spell attributes
    private double maxDmg;
    private double chance;

    //Constructor method for Spell object
    Spell(String name, double minDmg, double maxDmg, double chance){
        this.name = name;
        this.minDmg = minDmg;   //Assigning argument values to object values
        this.maxDmg = maxDmg;
        this.chance = chance;
        try {
            if(chance < 0 || chance > 1){       //Chance double must be between 0 to 1
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e){
            System.out.println("Chance must be between 0 and 1.");
        }
    }

    public String getName() {       //Getter method for spell name
        return this.name;
    }

    public double getMagicDamage(int seed){     //Getter method for spell damage returns a double of the damage done
        Random randomDmg = new Random(seed);
        double genRandNum = randomDmg.nextDouble(0, 1);
        if(genRandNum > this.chance){
            return 0;
        } else if(genRandNum < this.chance){
        } return randomDmg.nextDouble(this.minDmg, this.maxDmg);
    }

    public String stats(){                  //Getter method for the spell statistics
        NumberFormat percentageFormat = NumberFormat.getPercentInstance();
        percentageFormat.setMinimumFractionDigits(2);               //Formatted string shows spell name, minimum damage, maximum damage and chance of success
        return getName() + " has a minimum damage of " + this.minDmg + ", a maximum damage of " + this.maxDmg +
                " and a success rate of " + percentageFormat.format(this.chance) + ".";
    }

//
//    //TESTING METHODS//
//    public static void main(String[] args) {
//        Spell iceBoy = new Spell("Ice tornado", 9.2, 16.84, 0.92);
//        System.out.println(iceBoy.getName());
//        System.out.println(iceBoy.getMagicDamage(111));
//        System.out.println(iceBoy.stats());
//    }


}
