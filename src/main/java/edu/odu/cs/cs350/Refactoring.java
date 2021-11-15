package edu.odu.cs.cs350;

public class Refactoring {
	
	private int improvement;
	private int refactorRow;
	private int refactorColumn;
	private String fileName; 
	
	Refactoring() {
		improvement = 0;
		refactorRow = 0;
		refactorColumn = 0;
		fileName = new String();
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
	 * Renders the Refactoring object as a string containing all fields
	 * 
	 */
	@Override
	public String toString() {
		return "RefactoringStub [Opportunity for Improvement=" + improvement + " Refactor Row =" 
				+ refactorRow + "," + " Refactor Column=" + refactorColumn + "]" + " File Name = "
				+ fileName;
	}
	
	/**
	 * Returns the hashCode for this Refactoring
	 * 
	 * @return hashCode integer for Refactoring
	 */
	@Override
	public int hashCode() {
		int sourceFileHash = 0;
		
		sourceFileHash = improvement * 71 + refactorRow * 13 + refactorColumn * 57 + fileName.hashCode();
		sourceFileHash = sourceFileHash << 2;
		
		return sourceFileHash;
	}
}