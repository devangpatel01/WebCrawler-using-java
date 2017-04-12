import java.util.ArrayList;
import java.util.List;
 
public class Node {
 private String id;
 private final List<Node> children = new ArrayList<>();
 private final Node parent;
 
 public Node(Node parent) {
  this.parent = parent;
 }
 
 public String getId() {
  return id;
 }
 
 public void setId(String id) {
  this.id = id;
 }
 
 public List<Node> getChildren() {
  return children;
 }
 
 public Node getParent() {
  return parent;
 }
 
 public static Node addChild(Node parent, String id) { 
	   Node node = new Node(parent);
	   node.setId(id);
	   parent.getChildren().add(node);
	   return node;
 }
 
 public static void printTree(Node node) {
	 		if(node.getParent() != null)
	 			//System.out.println(node.getId()+" "+node.getParent().getId());
	 			System.out.println(node.getId());
	 		else
	 			System.out.println(node.getId());
	   for (Node each : node.getChildren()) {
	       printTree(each);
	   }
 }
 
 public static int isRepeatedNode(Node node, String id) {
			if(node.getId().equals(id)){
				return 0;
			}
			else	
			{
				for (Node each : node.getChildren()) {
					return 1*isRepeatedNode(each,id);
				}
			}
		return 1;
  }
 
//Utility function that will return the depth
//of the tree
public static int depthOfTree(Node node)
{
  // Base case
  if (node == null)
      return 0;

  int maxdepth = 0;

  // Check for all children and find
  // the maximum depth
  for (Node each : node.getChildren())
	  maxdepth = Math.max(maxdepth , depthOfTree(each));

  return maxdepth + 1;
}

//Function to calculate the diameter
//of the tree
public static int diameter(Node node)
{
  // Base case
  if (node == null)
      return 0;

  // Find top two highest children
  int max1 = 0, max2 = 0;
  for (Node each : node.getChildren())
  {
      int h = depthOfTree(each);
      if (h > max1){
    	  max2 = max1;
       	  max1 = h;
      }
      else if (h > max2)
         max2 = h;
  }

  // Iterate over each child for diameter
  int maxChildDia = 0;
  for (Node each : node.getChildren())
      maxChildDia = Math.max(maxChildDia, diameter(each));

  return Math.max(maxChildDia, max1 + max2 + 1);
}
 
 
}
