package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class FamilyTree {
	
	private Map<Integer, Family> members = new HashMap<>(); 
	private Family root;
	
	public static void main(String[] args) {		
		
		String input = "null,0,grandpa|0,1,son|0,2,daugther|1,3,grandkid|"
				+ "1,4,grandkid|2,5,grandkid|5,6,greatgrandkid";
		
		ArrayList<String> family_member = new ArrayList<>();
		String[] inputValues = new String[family_member.size()];
		
		//step 1 split at |	and ,	
		String [] family_split = input.split("\\|");
		for (String a: family_split) {
			String [] member = a.split("\\,");
			family_member.add(member[0]+ " " +  member[1]+ " " + member[2]);
		}
		
		//step 2 convert arraylist to array
		inputValues = family_member.toArray(inputValues);
		//System.out.println("from array" + Arrays.toString(inputValues));
		
		
		FamilyTree tree = new FamilyTree();
		//step 3 create Map 
	     tree.createMap(inputValues);
	     
	   //step 4 build tree
	     tree.buildHierarchyTree(tree.root);
	     
	   //step 5 print tree
	     tree.printHierarchyTree(tree.root, 0);
		
	}
	
	
	public void createMap(String[] lines)  {        		
		Family familyMember = null;
		Random random = new Random();
		int x = random.nextInt(900) + 100;
		
		for (String strLine : lines) { 			
			String[] values = strLine.split(" ");
			if (values[0].equals("null"))
				values[0]= String.valueOf(x);
			
			familyMember = new Family(values[0], values[1], values[2]);
			members.put(familyMember.id, familyMember);
			
			if (familyMember.parentId == x) 
				root = familyMember;
		}
	}
	
	

	 
	 public void buildHierarchyTree(Family root) {
		 Family family = root;
		 List<Family> children = getChildren(family.id);
		 family.children = children;
		 if (children.size() == 0)
			 return;
		 for (Family ch : children) 
	    	buildHierarchyTree(ch);
	 }	 
	 
	
	private List<Family> getChildren(int parentId) {
		 List<Family> children = new ArrayList<Family>();
		 for (Family ch : members.values()) {
			 if (ch.parentId == parentId) 
				 children.add(ch);
		 }
		 return children;
	}
	

		public void printHierarchyTree(Family root, int level) {
			for (int i = 0; i < level; i++) 
				System.out.print("\t");	 
			System.out.println(root.name);		 
			List<Family> children = root.children;
			for (Family ch : children) 
				printHierarchyTree(ch, level+1);
		}

}
