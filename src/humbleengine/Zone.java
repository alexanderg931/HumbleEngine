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
import java.util.ArrayList;
import java.io.File;

public class Zone {
   private Zone next;
   private String[] lines;
   private Monster[] monsters;
   
   /**
    * Constructor that additional converts the ArrayList of Strings extracted from file into an array of strings for memory concerns
    * @param next Next node in the LinkedList
    * @param lines The ArrayList of lines in the file
    */
   public Zone(Zone next, ArrayList<String> lines) {
       this.next = next;
       
       if(lines != null) {
           this.lines = new String[lines.size()];
       
           for(int x = 0; x < lines.size(); x++) {
               this.lines[x] = lines.get(x);
           }
       
           this.monsters = this.buildMonsters();
       }
   }
   
   public Monster getMonster(int position)
   {
       return monsters[position];
   }
   public String getLine(int position)
   {
       return lines[position];
   }
   
   public int getLength()
   {
       return lines.length;
   }
   
   public Zone getNext() {
       return this.next;
   }
   
   public void setNext(Zone next) {
       this.next = next;
   }
   
   /**
    * Builds the monster array from the last line in lines.
    * @return An array of all monsters for this zone.
    */
   private Monster[] buildMonsters()
   {
       /* Parse the final line which is each mosnter name separated by a carrot */
       String[] monsterNames = lines[lines.length-1].split("^");
       
       /* Change the final line to END for parsing reasons elsewhere */
       lines[lines.length-1] = "END";
       
       /* Since it's unknowable how many monsters will be in a zone, an array list is made */
       ArrayList<Monster> monsterList = new ArrayList<>();
       
       /* For each monster whose name we have, find the file and read from it */
       for (int x = 0; x < monsterNames.length; x++)
       {
           /* Method header for reference:
              public Monster(float health, int level, int attack, int defense, float specialChance, String specialAttack, String name, String description) */
           
           /* Read the file for each monster. */
           ArrayList<String> monsterAttr = Util.readFromFile(new File(monsterNames[x] + ".txt"));
           
           /* Parse each line of the file */
           float health = Float.parseFloat(monsterAttr.get(0));
           int level = Integer.parseInt(monsterAttr.get(1));
           int attack = Integer.parseInt(monsterAttr.get(2));
           int defense = Integer.parseInt(monsterAttr.get(3));
           float specialChance = Float.parseFloat(monsterAttr.get(4));
           String specialAttack = monsterAttr.get(5);
           String name = monsterAttr.get(6);
           String description = monsterAttr.get(7);
           
           /* Create monster object */
           Monster aMonster = new Monster(health, level, attack, defense, specialChance, specialAttack, name, description);
           
           /* Add to array list */
           monsterList.add(aMonster);
       }
       
       /* For memory concerns, make a monster array */
       Monster[] monsterArray = new Monster[monsterList.size()];
       
       /* Transfer contents of arrayList to array */
       for(int x = 0; x < monsterList.size(); x++)
       {
           monsterArray[x] = monsterList.get(x);
       }
       
       /* Return the final array */
       return monsterArray;
   }
}
