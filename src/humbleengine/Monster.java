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
/**
 * Generic class for monsters in the game
 * @author Grant
 */
public class Monster extends Sentient {
    /**
     * The description of the monster, because this is an rpg!
     */
    private String description;
    
    /**
     * Constructor for the Monster class. Has the same attributes as the class Sentient which it extends, plus a description of the monster
     * @param health The monster's health
     * @param level The monster's level
     * @param attack The monster's attack
     * @param defense The monster's defense (0-65, ideally)
     * @param specialChance The monster's special attack chance from 0-1.0
     * @param specialAttack The monster's special attack
     * @param name The monster's name
     * @param description A description of the monster
     */
    public Monster(float health, int level, int attack, int defense, float specialChance, String specialAttack, String name, String description) {
        super(health, level, attack, defense, specialChance, specialAttack, name);
        this.description = description;
    }
    
    /* Gets and sets */
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
