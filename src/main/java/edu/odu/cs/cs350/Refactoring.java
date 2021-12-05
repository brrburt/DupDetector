package edu.odu.cs.cs350;

import java.io.File;

public class Refactoring {

	private File sourceFile;
	private int improvement;
	private TokenStream tokens;
	private int tokenCount;
	private int refactorRow;
	private int refactorColumn;
	private String fileName; 
	
	Refactoring() {
		improvement = 0;
		refactorRow = 0;
		refactorColumn = 0;
		tokenCount = 0;
		fileName = new String();
	}
	
	Refactoring(File file) {
		improvement = 0;
		refactorRow = 0;
		refactorColumn = 0;
		fileName = new String();
		sourceFile = file;
	}
	
	/** 
	 * 
	 * @return int number of tokens in source file
	 */
	public int getTokenCount() {
		return tokenCount;
	}
	
	/**
	 * 
	 * sets tokenCount to number of tokens in source file
	 */
	public void setTokenCount(int tokens) {
		tokenCount = tokens;
	}
	
	/**
	 * 
	 * 
	 * @return int Strating row of refactor suggestion
	 */
	public int getRefactorRow() {
		return refactorRow;
	}
	
	/**
	 * 
	 * 
	 * @return int Starting Column of refactor suggestion
	 */
	public int getRefactorColumn() {
		return refactorColumn;
	}
	
	/**
	 * 
	 * Set Starting row for refactoring suggestion
	 */
	public void setRefactorRow(int startRow) {
		refactorRow = startRow;
	}
	
	/**
	 * 
	 * Set starting column for refactoring suggestion
	 */
	public void setRefactorColumn(int startCol) {
		refactorColumn = startCol;
	}
	
	/**
	 * 
	 * @return TokenStream list of found tokens for source file
	 */
	public TokenStream getTokens() {
		return tokens;
	}
	
	/**
	 * 
	 * Set List of tokens for source file
	 */
	public void setTokens(TokenStream t) {
		tokens = t;
	}
	
	@Override 
	public boolean equals(Object obj) {
		if (obj.hashCode() == this.hashCode())
		{		
			return true;
		}
		
	   	return false;
	}
	
	/**
	 * 
	 * @return File source file
	 */
	public File getSourceFile() {
		return sourceFile;
	}
	
	/**
	 * 
	 * Set source file
	 */
	public void setSourceFile(File file) {
		sourceFile = file;
	}
	
	/**
	 * Renders the Refactoring object as a string containing all fields
	 * 
	 */
	@Override
	public String toString() {
		return "Refactoring [Opportunity for Improvement=" + improvement + " Refactor Row =" 
				+ refactorRow + "," + " Refactor Column=" + refactorColumn + " File Name = "
				+ fileName + "source file= " + sourceFile + "]";
	}
	
	/**
	 * Returns the hashCode for this Refactoring
	 * 
	 * @returns hashCode integer for Refactoring
	 */
	@Override
	public int hashCode() {
		int sourceFileHash = 0;
		
		sourceFileHash = improvement * 71 + refactorRow * 13 + refactorColumn * 57 + fileName.hashCode();
		sourceFileHash = sourceFileHash << 2;
		
		return sourceFileHash;
	}
}
