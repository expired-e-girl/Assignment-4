//Marilyn Ruptash
//ID: 261126626
//Assignment 4 is a battle game that uses file reading to build objects and file writing to record wins of the battles conducted.

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class BattleGame {

    BattleGame(String playerFile, String monsterFile, String spellFile) throws IOException {
        playGame(playerFile, monsterFile, spellFile);           //Constructor for BattleGame takes in files for player, monster and spell attributes
    }

    //Method to print statistics of player and monster before game starts
    private static void printStats(Character player, Character monster){
        System.out.println("Name: " + player.getName());
        System.out.println("Health: " + player.getMaxHealth());
        System.out.println("Attack: " + player.getAttackValue());
        System.out.println("Number of Wins: " + player.getNumWins());
        System.out.println(" ");
        System.out.println("Name: " + monster.getName());
        System.out.println("Health: " + monster.getMaxHealth());
        System.out.println("Attack: " + monster.getAttackValue());
        System.out.println("Number of Wins: " + monster.getNumWins());
        System.out.println(" ");
    }

    //Method to start the battle game
    public static void playGame(String playerFile, String monsterFile, String spellFile) throws IOException { //Takes a file with player info, monster info and spell info
        Scanner userInput = new Scanner(System.in);         //Scanner initialization

        Character player = FileIO.readCharacter(playerFile);
        Character monster = FileIO.readCharacter(monsterFile);  //Creates monster and player Character objects using their appropriate files
        player.setSpells(FileIO.readSpells(spellFile));         //Set the spells available to the player by reading the spell file

        System.out.println("Welcome to the battle game! Here are the stats for player and monster!");
        System.out.println(" ");
        printStats(player, monster);    //Print default set stats for monster and player before start of game

        System.out.println(" ");
        System.out.println("Here are the available spells:");   //Shows available spells by using the displaySpell method
        player.displaySpells();
        System.out.println(" ");

        boolean fainted = false;        //Boolean used to determine if continuing game or not
        do {
            System.out.println("Enter a command: ");        //Ask for a command
            String answer = userInput.nextLine();               //Use scanner to store answer
            switch (answer) {
                case "attack" -> {                          //Attack command action taken
                    int aSeed = (int) (Math.random() * 100);
                    Random number = new Random(aSeed);          //Generating a random integer using a randomized seed
                    int x = number.nextInt();
                    System.out.println(" ");                    //Player attacks first
                    System.out.println(player.getName() + " attacks for " + String.format("%1$.2f",player.getAttackDamage(x)) + " damage!");
                    monster.takeDamage(player.getAttackDamage(x));      //Monster takes damage
                    System.out.println(monster.status());               //Health stat for monster
                    System.out.println(" ");                    //Monster attacks player
                    System.out.println(monster.getName() + " attacks for " + String.format("%1$.2f",monster.getAttackDamage(x)) + " damage!");
                    player.takeDamage(monster.getAttackDamage(x));      //Player takes damage
                    System.out.println(player.status());                //Health stat for player
                    System.out.println(" ");
                }
                case "quit" -> {
                    fainted = true;                 //Quit command action sets boolean to true to break loop
                    System.out.println(" ");
                    System.out.println("Thank you for playing! Goodbye!");      //Show goodbye message
                }
                default -> {                        //Assume any other command is to cast spell
                    int aSeed = (int) (Math.random() * 100);
                    Random number = new Random(aSeed);          //Generating a random integer using a randomized seed
                    int z = number.nextInt();
                    Double castDmg = player.castSpell(answer, z);   //Player spell's damage variable
                    Double monsDmg = monster.getAttackDamage(z);    //Monster attack damage variable
                    if(castDmg == -1.0){
                        System.out.println(" ");                    //If the cast returns -1 spell has no effect
                        System.out.println(player.getName() + " tried to cast " + answer + " but they don't know that spell.");
                        System.out.println(monster.status());
                        System.out.println(" ");
                    } else {                                        //If the cast returns damage higher than 0
                        monster.takeDamage(castDmg);                //Monster takes damage from spell
                        System.out.println(monster.status());       //Return monster's new health status
                    }
                    System.out.println(" ");            //Monster attacks back, player takes damage and return player's new health status
                    System.out.println(monster.getName() + " attacks for " + String.format("%1$.2f",monsDmg) + " damage!");
                    player.takeDamage(monsDmg);
                    System.out.println(player.status());
                    System.out.println(" ");
                    }
                }
            if (player.getCurrHealth() <= 0) {      //If player's health is lower than 0 loop is broken
                fainted = true;
                System.out.println(player.getName() + " has fainted. You lost!");   //Lost game message
                monster.increaseWins();                             //Monster gets +1 win
                FileIO.writeCharacter(monster, monsterFile);        //Writes win to monster's file
            } else if (monster.getCurrHealth() <= 0){   //If monster's health is lower than 0 loop is broken
                fainted = true;
                System.out.println(monster.getName() + " has fainted. Congrats! VICTORY!"); //Won game message
                player.increaseWins();                          //Player gets +1 win
                FileIO.writeCharacter(player, playerFile);      //Writes win to player's file
            }
        } while (!fainted);
    }
}
