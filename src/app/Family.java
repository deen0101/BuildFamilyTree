package app;

import java.util.List;

public class Family {
	
	protected int id, parentId;
	protected String name;
	protected List<Family> children;
    
    
    public Family(String parentId, String id,  String name ) {    
	        this.id = Integer.parseInt(id);	        
	        this.name = name;
	        this.parentId = Integer.parseInt(parentId);
    }

	
	

}
