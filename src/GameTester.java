//Marilyn Ruptash
//ID: 261126626
//Assignment 4 is a battle game that uses file reading to build objects and file writing to record wins of the battles conducted.

import java.io.IOException;

public class GameTester {
    //Main method pointing files and using playGame method
    public static void main(String[] args) throws IOException {
        //REPLACE FILE PATHS WITH ONES FOR YOUR COMPUTER
        String playerFile = "D:\\UNIVERSITY\\Programming Tech 1\\Assignment 4\\files\\player.txt";
        String monsterFile = "D:\\UNIVERSITY\\Programming Tech 1\\Assignment 4\\files\\monster.txt";
        String spellFile = "D:\\UNIVERSITY\\Programming Tech 1\\Assignment 4\\files\\spells.txt";

        new BattleGame(playerFile,monsterFile,spellFile);
    }
}
