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
 * Generic class for player and monster objects alike.
 * @author Grant
 * @see Player
 * @see Monster
 */
public class Sentient {
    /**
     * The object's health
     */
    private float health;
    /**
     * the object's level
     */
    private int level;
    /**
     * the object's attack
     */
    private int attack;
    /**
     * the object's defense (0-65, ideally)
     */
    private int defense;
    /**
     * the 0-1.0 chance for the  object's special to hit
     */
    private float specialChance;
    /**
     * The name of the special attack
     */
    private String specialAttack;
    /**
     * The name of the object
     */
    private String name;
    
    /**
     * Constructor that populates all attributes
     * @param health The object's health
     * @param level The object's level
     * @param attack The object's attack
     * @param defense The object's defense
     * @param specialChance The object's special attack chance
     * @param specialAttack The object's special attack name
     * @param name The object's name
     */
    public Sentient(float health, int level, int attack, int defense, float specialChance, String specialAttack, String name) {
        this.health = health;
        this.level = level;
        this.attack = attack;
        this.defense = defense;
        this.specialChance = specialChance;
        this.specialAttack = specialAttack;
        this.name = name;
    }
    
    /**
     * Rolls to see whether or not the object's special attack fires
     * @return boolean value as to whether or not it will fire
     */
    private boolean rollForSpecial() {
        boolean happens = false;
        
        /* Grab system time in milliseconds */
        long milliseconds = System.currentTimeMillis();
        
        /* Shave it down to 0-9, cast to int for space concerns */
        int randomNumber = (int) (milliseconds%10L);
        
        /* If the random number is less than or equal to the special attack percentage * 10 (i.e. its chance out of 10) then it happens */
        if(randomNumber <= ((int) (10f*specialChance))) {
            happens = true;
        }
        
        return happens;
    }
    
    /**
     * Method to attack. Rolls for the special attack and acts accordingly
     * @param target The object to be targeted
     * @return Whether or not the special attack went off, for UI purposes
     */
    public boolean attack(Sentient target) {
        
        /* Roll for special attack, assign whether or not roll was successful to boolean variable */
        boolean specialHappened = rollForSpecial();
        if(specialHappened) {
            /* Multiply standard attack by 2 if special went off */
            target.takeDamage(this.attack*2);
        }
        else {
            /* Take normal attack damage if special didn't go off */
            target.takeDamage(this.attack);
        }
        
        return specialHappened;
    }
    
    /**
     * Method to take damage from another object. Takes defense stat into consideration
     * @param damage the damage taken
     */
    public void takeDamage(int damage) {
        /* Subtract the health by the damage less the amount of damage negated by defense */
        this.health -= (float) damage - (((float) damage) * (((float) defense)/100f));
    }
    
    /* Gets and Sets */
    
    public float getHealth() {
        return this.health;
    }
    
    public void setHealth(float health) {
        this.health = health;
    }
    
    public int getAttack() {
        return this.attack;
    }
    
    public void setAttack(int attack) {
        this.attack = attack;
    }
    
    public int getDefense() {
        return this.defense;
    }
    
    public void setDefense(int defense) {
        this.defense = defense;
    }
    
    public int getLevel() {
        return this.level;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }
    
    public float getSpecialAttackChance() {
        return this.specialChance;
    }
    
    public void setSpecialAttackChance(float specialChance) {
        this.specialChance = specialChance;
    }
    
    public String getSpecialAttack() {
        return this.specialAttack;
    }
    
    public void setSpecialAttack(String specialAttack) {
        this.specialAttack = specialAttack;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
