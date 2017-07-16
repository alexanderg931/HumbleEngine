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

import java.util.Scanner;

/**
 * View object for the game engine. Handles all user input/output independently of model.
 * @author Grant
 */
public class UI {
    private String welcomeMessage;
    private String gameName;
    private String[] menu;
    
    /**
     * Constructor that assigns all three attributes their values
     * 
     * @param gameName The name of the game
     * @param welcomeMessage The welcome message you'd like to use (could be an introduction!)
     * @param choices An array of menu choices, as strings
     * @see buildMenu
     */
    public UI(String gameName, String welcomeMessage, String[] choices) {
        this.welcomeMessage = welcomeMessage;
        this.gameName = gameName;
        buildMenu(choices);
    }
    
    /**
     * Method to send custom text to the user.
     * @param text The text the game designer wishes for the user to see
     */
    public void sendText(String text) {
        System.out.println(text);
    }
    
    /**
     * Method to get text from the user
     * @return The user's input string
     */
    public String recieveText() {
        /* For anti-spaghettification purposes: called internally by:
            1. showMenu() 
        */
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        return input;
    }
    
    /**
     * Shows the menu to the user, using a loop that repeats if the user's input
     * is either invalid or not in the list.
     * @return the user's valid selection
     */
    public int showMenu() {
        /* Default return value if something goes horribly ary and the loop
           manages to complete without proper error checking having worked */
        int value = -1;
        
        boolean sentinel = true;
        do {
            /* Print out the menu */
            System.out.println("MENU:\n");
            for(int x = 0; x < menu.length; x++) {
                System.out.println(menu[x]);
            }
            
            /* Listen for the user's selection */
            String input = recieveText();
            
            /* Variable to hold the string after being parsed into an int */
            int inputAsNumber = 0;
            
            /* Variable to flag whether or not the parsing went through correctly */
            boolean isValid = true;
            
            /* Try to parse the string into an integer, set isValid flag to false on failure */
            try {
                inputAsNumber = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                isValid = false;
            }
            
            /* Check that the parsing went through correctly before going forward */
            if(isValid) {
                /* Check to see if the integer is within the menu's range of options */
                boolean isInMenu = false;
                for(int x = 1; x < (menu.length+1); x++){
                    if(inputAsNumber == x) {
                        isInMenu = true;
                    }
                }
                
                /* If it is both a valid integer, and within the list, terminate the loop */
                if(isInMenu == true) {
                    value = inputAsNumber;
                    sentinel = false;
                }
            }
        } while(sentinel == true);
        
        /* -1 if things went horribly wrong */
        return value;
    }
    
    /**
     * Builds a menu for the user, prefixing each input in the choices array with a tab character and number
     * @param choices The string array of menu options
     */
    private void buildMenu(String[] choices) {
        
        /* Make the menu fit the number of choices */
        menu = new String[choices.length];
        
        /* Format the choices with numbers and a prefixed tab character for visual purposes */
        for(int x = 0; x < choices.length; x++) {
            menu[x] = "\t" + (x+1) + ". " + choices[x] + "\n";
        }
    }
}
