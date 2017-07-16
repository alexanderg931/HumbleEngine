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
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Utility methods for the program
 * @author Grant
 */
public class Util {
    
    private Util() {
        /* Static class */
    }
    
    /**
     * Method to read either zones or monsters/players from file
     * @param file The file to be written to
     * @return An ArrayList of Strings where each line is an element. Parsing must be done by calling method.
     */
    public static ArrayList<String> readFromFile(File file) {
        /* Make an array list to store the output */
        ArrayList<String> output = new ArrayList<>();
        
        /* Open a scanner to read the file and attempt to read each line */
        try {
            Scanner read = new Scanner(file);
            while(read.hasNextLine()) {
               output.add(read.nextLine());
            }
        } catch (Exception e)
        {
            /* In the event the file is not found, this exception is thrown and the program exits */
            System.err.println("FATAL ERROR: COULD NOT LOAD " + file.getPath());
            System.exit(1);
        }
        
        return output;
    }
    
    /**
     * Writes data to file for monsters and players.
     * Note that the file stores each attribute on its own line.
     * @param file The file to be written to
     * @param data The data to be written to file. Basically, each attribute in a separate element.
     */
    public static void writeToFile(File file, String[] data) {
        try {
            /* If the file exists, delete it */
            if (file.exists()) 
                file.delete();
            
            /* Replace the file */
            file.createNewFile();
            
            /* Write to file */
            FileWriter fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);
            for(int x = 0; x < data.length; x++)
            {
                writer.write(data[x] + "\n");
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (Exception e)
        {
            /* Inform the user if save operation could not be performed */
            System.err.println("ERROR: COULD NOT SAVE");
        }
    }
}
