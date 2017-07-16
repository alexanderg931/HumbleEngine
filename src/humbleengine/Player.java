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
 * The player class. An extension of Sentient
 * @author Grant
 * @see Sentient
 */
public class Player extends Sentient {
    
    /**
     * The player's experience from 0-100
     */
    private int experience;
    
    /**
     * The constructor for the player class. Has all the same attributes as the Sentient class it extends as well as an experience attribute
     * @param health The player's health
     * @param level The player's level (usually 1 since we're initializing a player for the first time)
     * @param attack The player's attack
     * @param defense The player's defense (0-65, ideally)
     * @param specialChance The player's special attack chance from 0-1.0
     * @param specialAttack The player's special attack name
     * @param name The player's name
     * @param experience The player's experience, from 0-100, recommended to be initialized to 0
     */
    public Player(float health, int level, int attack, int defense, float specialChance, String specialAttack, String name, int experience) {
        super(health, level, attack, defense, specialChance, specialAttack, name);
        this.experience = experience;
    }
    
    /**
     * Used to check at routine intervals if the player has enough experience to level up. Scales up player stats, as well.
     * @return boolean value which describes whether or not the player leveled up
     */
    public boolean checkExperience() {
        boolean didLevel = false;
        if (this.experience > 100) {
            didLevel = true;
            this.experience = 0;
            this.setLevel(this.getLevel()+1);
            this.setAttack(this.getAttack()+3);
            this.setDefense(this.getDefense()+2);
            this.setHealth(this.getHealth()+10);
            this.setSpecialAttackChance(this.getSpecialAttackChance()+0.01f);
        }
        
        return didLevel;
    }
    
    public int getExperience()
    {
        return this.experience;
    }
    
    public void setExperience(int experience)
    {
        this.experience = experience;
    }
    
}
