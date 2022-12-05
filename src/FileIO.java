//Marilyn Ruptash
//ID: 261126626
//Assignment 4 is a battle game that uses file reading to build objects and file writing to record wins of the battles conducted.

import java.io.*;
import java.util.ArrayList;

public class FileIO {

    //Method that reads file containing character attributes and generates a Character object
    public static Character readCharacter(String fileName) throws IOException {     //IOException in whole method instead of try-for-resources
        Character charUnit;
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader(fileName));    //Use BufferedReader to read lines from file
            ArrayList<String> listOfAtt = new ArrayList<>();        //Set each line into Array List
            String line = bufReader.readLine();
            while (line != null) {
                listOfAtt.add(line);
                line = bufReader.readLine();
            }
            bufReader.close();                  //Close BufferedReader
            charUnit = new Character(listOfAtt.get(0), Double.parseDouble(listOfAtt.get(1)), Double.parseDouble(listOfAtt.get(2)),
                    Integer.parseInt(listOfAtt.get(3)));    //Character object creation using Array list content
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No file found.");   //Catching FileNotFound
        }

        return charUnit;    //Returns Character object
    }

    //Method to read file containing attributes of spells and returns an Array List containing available spells
    public static ArrayList<Spell> readSpells(String spellFile) {
        ArrayList<Spell> listOfSpells = new ArrayList<>();      //Array List of spells to be filled out
        try {
                BufferedReader spellReader = new BufferedReader(new FileReader(spellFile));     //Using Buffered Reader to read through file
                String line;
                    while ((line = spellReader.readLine()) != null) {
                        String[] constSpell = line.split("\\t");        //Splitting elements of each line per tab characters
                        Spell newSpell = new Spell(constSpell[0], Double.parseDouble(constSpell[1]),    //Each line put in the Array List creates a new Spell object
                                Double.parseDouble(constSpell[2]), Double.parseDouble(constSpell[3]));
                        listOfSpells.add(newSpell);
                    }
                spellReader.close();    //Close Buffered reader
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No file found.");   //Catch FileNotFound
        } catch (IOException e) {
            throw new RuntimeException(e);      //Catch IOException
        }
        return listOfSpells;            //Returns array list of all available spells
    }

    //Method that writes character's attributes in a file and return said file
    public static File writeCharacter(Character playerName, String writeCharFile) {
        BufferedWriter bw = null;       //Using Buffered writer
        File file = null;               //Creating file
        try {
            String myContent = playerName.getName() + System.lineSeparator() + playerName.getAttackValue() + System.lineSeparator() + playerName.getMaxHealth() +
                    System.lineSeparator() + playerName.getNumWins();           //Attributes of character object passed as argument
            file = new File(writeCharFile);
            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(myContent);                //Writes content to the file
        } catch (IOException ioe) {         //Catch IOException
            ioe.printStackTrace();
        } finally {
            try {
                if (bw != null)             //Close writer once done
                    bw.close();
            } catch (Exception ex) {        //Catch closing error if the case exists
                System.out.println("Error in closing the BufferedWriter" + ex);
            }
        }
        return file;            //Returns file containing character attributes (Name, attack value, max health, number of wins)
    }


    //TESTING METHODS//
//    public static void main(String[] args) throws IOException {

//        Character myChar = readCharacter("D:\\\\UNIVERSITY\\\\Programming Tech 1\\\\Assignment 4\\\\files\\player.txt");
//        Character salem = new Character("Salem", 39.0, 50.00,0);
//        String writeCharFile = "D:\\UNIVERSITY\\Programming Tech 1\\Assignment 4\\files\\fileplayer.txt";
//        ArrayList<Spell> randomAssArrays = readSpells("D:\\UNIVERSITY\\Programming Tech 1\\Assignment 4\\files\\spells.txt");
//        for(Spell spell :  randomAssArrays){
//            System.out.println(spell.getName());
//        }
//        writeCharacter(myChar,writeCharFile);

    }



