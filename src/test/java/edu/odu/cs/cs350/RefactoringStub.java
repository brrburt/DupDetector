package edu.odu.cs.cs350;



public class RefactoringStub {

	private int improvement;
	private int refactorRow;
	private int refactorColumn;
	private String fileName; 
	
	RefactoringStub() {
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
