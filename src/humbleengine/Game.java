/*******************************************************************************************************************************************************
    Copyright 2017 Grant Alexander

    Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"),
    to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
    and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
    IN THE SOFTWARE.
********************************************************************************************************************************************************/
package humbleengine;
import java.io.File;
import java.util.ArrayList;
/**
 * Class that manages the overall game
 * @author Grant
 */
public class Game {
    /* List of all player names */
    private ArrayList<String> players;
    /* List of all zone names */
    private Zones zones;
    private int zoneIndex;
    private int monsterIndex;
    
    public Game(){
        this.players = this.extractPlayersFromFile();
        this.zones = this.loadZones();
    }
    
    public void beginAttackSequence(Player player, Monster monster) {
        
    }
    
    public void handleZone(Zone zone, Player player) {
        for(int x = 0; x < zone.getLength(); x++) {
            String line = zone.getLine(x);
            
            if(line.contains("$ATTACK")) {
                //this.beginAttackSequence();
            }
        }
    }
    
    /**
     * Method to load the zones from the zone file, and make a LinkedList of all the zone objects.
     * @return the LinkedList of zone objects
     */
    private Zones loadZones() {
        /* Read the zone names from the zone file */
        File zonesListFile = new File("zones.txt");
        ArrayList<String> zonesList = Util.readFromFile(zonesListFile);
        
        /* Initialize a local variable to hold the LinkedList */
        Zones theZones = new Zones();
        
        /* For each zone in the file */
        for(int x = 0; x < zonesList.size(); x++) {
            /* Read its file */
            File zoneFile = new File(zonesList.get(x) + ".txt");
            ArrayList<String> zoneLines = Util.readFromFile(zoneFile);
            
            /* Make a zone object for it and add the zone to the LinkedList */
            Zone aZone = new Zone(null, zoneLines);
            theZones.addZone(aZone);
        }
        
        return theZones;
    }
    
    /**
     * Fairly trivial function that points Util.readFromFile to players.txt to grab the list of player names
     * @return The full ArrayList of player names
     */
    private ArrayList<String> extractPlayersFromFile() {
        File playerListFile = new File("players.txt");
        return Util.readFromFile(playerListFile);
    }
    
    /**
     * Checks to make sure the player is in the list of players and gets it from file if it is
     * @param playerName The name of the player's character
     * @return A player object which is a fully initialized player object if it's in the record, or null if it's not
     */
    public Player checkAndGetPlayerFromFile(String playerName) {
        /* Variable we'll use to check for the player name existing */
        boolean isThere = false;
        
        /* Player object to be returned */
        Player player;
        
        /* Check against each name in the list. If it's there, flag that it's in the record */
        for(int x = 0; x < this.players.size(); x++) {
            if(this.players.get(x) == playerName) {
                isThere = true;
            }
        }
        
        if(isThere) {
            /* If it's in the record, it's presumed safe to extract it from file */
            File playerFile = new File(playerName + ".txt");
            ArrayList<String> playerInfo = Util.readFromFile(playerFile);
            
            /* public Player(float health, int level, int attack, int defense, float specialChance, String specialAttack, String name, int experience) { */
            float health = Float.parseFloat(playerInfo.get(0));
            int level = Integer.parseInt(playerInfo.get(1));
            int attack = Integer.parseInt(playerInfo.get(2));
            int defense = Integer.parseInt(playerInfo.get(3));
            float specialChance = Float.parseFloat(playerInfo.get(4));
            String specialAttack = playerInfo.get(5);
            String name = playerInfo.get(6);
            int experience = Integer.parseInt(playerInfo.get(7));
            
            /* Initialize the player object */
            player = new Player(health, level, attack, defense, specialChance, specialAttack, name, experience);
        }
        else {
            /* Only runs if it's not in the record */
            player = null;
        }
        
        return player;
    }
}
