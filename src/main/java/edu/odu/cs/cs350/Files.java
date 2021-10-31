package edu.odu.cs.cs350;


public class Files {
	
	private String path;
	
	public Files() {
	}
	
	public Files(String path) {
		this.path = path;
	}
	
	public int hashCode() {
		return path.hashCode() * 27 << 3;
	}
	
	public boolean equals(Object obj) {
		if (obj.hashCode() == this.hashCode())
		{		
			return true;
		}
		
	   	return false;
	}
	
}
