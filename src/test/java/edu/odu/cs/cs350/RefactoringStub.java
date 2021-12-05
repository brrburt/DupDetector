package edu.odu.cs.cs350;

import java.io.File;

public class RefactoringStub {

	private Files sourceFile;
	private int improvement;
	private TokenStream tokens;
	private int refactorRow;
	private int refactorColumn;
	private String fileName; 
	
	RefactoringStub() {
		improvement = 0;
		refactorRow = 0;
		refactorColumn = 0;
		fileName = new String();
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
	 * Renders the RefactoringStub object as a string containing all fields
	 * 
	 */
	@Override
	public String toString() {
		return "RefactoringStub [Opportunity for Improvement=" + improvement + " Refactor Row =" 
				+ refactorRow + "," + " Refactor Column=" + refactorColumn + "]" + " File Name = "
				+ fileName;
	}
	
	/**
	 * Returns the hashCode for this RefactoringStub
	 * 
	 * @returns hashCode integer for RefactoringStub
	 */
	@Override
	public int hashCode() {
		int sourceFileHash = 0;
		
		sourceFileHash = improvement * 71 + refactorRow * 13 + refactorColumn * 57 + fileName.hashCode();
		sourceFileHash = sourceFileHash << 2;
		
		return sourceFileHash;
	}
}
