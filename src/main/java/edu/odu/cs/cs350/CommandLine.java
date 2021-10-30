package edu.odu.cs.cs350;

import java.util.List;


/*
// author Andrew Lee 
// cs_alee035
*/

public class CommandLine {
	
	private List<Files> files;
	private Refactoring refactor;
	
	/* Getters and Setters */
	
	// returns a list of files
	public List<Files> getFiles() {
		return files;
	}
	
	// Add file to list
	public void addFile() {
		
	}
	
	// returns the refactoring object
	public Refactoring getRefactor( ) {
		return refactor;
	}
	
	
	/**
	 * Will print sections 1 and 2 of refactoring suggestions
	 * to the command line
	 * 
	 * @parameter Refactoring object
	 * 
	 * @post Refactoring suggestions printed to command line
	 */
	public void printSuggestions() {
		
	}
	
	/**
	 * Read the properties file for key-value pairs, and update 
	 * appropriate variable values within Refactoring.
	 * 
	 * @parameter pFile properties file
	 * @post Refactoring variables updated
	 */
	public void readPropertiesFile(/* parameter */) { 
		
	}
	
	/**
	 * Read command line arguments and create Files types from
	 * arguments.
	 *  
	 * 
	 * @post File objects added to files list from command line
	 * arguments
	 */
	public void parseParameters() {
		
	}
	
		
	

}