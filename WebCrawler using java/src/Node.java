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
 
 
 
}