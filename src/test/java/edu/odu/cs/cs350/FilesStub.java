package edu.odu.cs.cs350;

import java.util.ArrayList;
import java.util.List;

public class FilesStub extends Files {

	public List<String> getFileExtensions() {
		List<String> extensions = new ArrayList<String>();
		extensions.add(".cpp");
		extensions.add(".h");
		extensions.add(".c");
		extensions.add(".hpp");
		extensions.add(".H");
		
		return extensions;
	}
	
}
