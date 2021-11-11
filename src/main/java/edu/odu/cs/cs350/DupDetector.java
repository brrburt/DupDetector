package edu.odu.cs.cs350;

import java.io.BufferedReader;
/*import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Random;
import java.util.Scanner;*/

public class DupDetector {

    public static void main(String[] args) throws Exception
    {
        /*CountTokens t = new CountTokens();
        t.Counting();
        t.Display();*/

<<<<<<< HEAD
        /**
         * Check for errors before going further
         * 
         * 1. Check if a help flag was used
         * 2. Ensure two or more parameters were given
         * 3. Ensure that args[0] is an int (nSuggestions)
         */
    	if(args[0].equals("-h") || args[0].equals("--help")) {
    		PrintUsage();
    	}
    	else if(args.length < 2) {
    		System.out.println("DupDetector: None or too few arguments");
    		System.out.println("Type 'DupDetector --help' for usage information.");
    	}
    	else if(!TryStringToInt(args[0])) {
    		System.out.println("DupDetector: nSuggestions must be an integer");
    		System.out.println("Type 'DupDetector --help' for usage information.");
    	}
    	else {
    		CommandLine cli = new CommandLine();
    		cli.parseParameters(args);
    	}
    }
 
    
    /**
     * Called when a help flag is given to the program; displays purpose 
     * and command line arguments
     * 
     * @post usage information will be printed to the command line 
     */
    public static void PrintUsage() 
    {
    	System.out.println("Usage: java -jar DupDetector.jar nSuggestions [ properties ] path1 [ path2 ... ]\n"
    					+  "Search C++ files for duplicate code and receive refactoring suggestions\n"
    					+  "\n"
    					+  "  nSuggestions\tmaximum number of suggested refactorings to be printed\n"
    					+  "  properties\toptional path to a .ini properties file\n"
    					+  "  path1\t\tpath to a C++ file or a directory containing C++ files\n"
    					+  "  path2 ...\tadditional paths to C++ files/directories\n"
    					+  "\n"
    					+  "DupDetector, CS350, Old Dominion Univ., Fall 2021\n"
    					+  "Team Project T7-1");
    }
    
   
    /**
     * Check if a number can be converted into an integer
     * 
     * @parameter number a string to convert into an integer
     * @returns true if string represents an integer
     */
    public static boolean TryStringToInt(String number) 
    {
    	try {
    		Integer.parseInt(number);
    		return true;	
    	}
    	catch (NumberFormatException e) {
    		return false;
    	}
=======

        

>>>>>>> c6a7331 (tokenName)
    }
    

}