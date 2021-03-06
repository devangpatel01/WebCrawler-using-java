# WebCrawler-using-java

## This web crawler crawls the cse.uta.edu website and does the following:  
1. Gets all links and recursively call the processPage method (BFS traversal)
2. Goes to only those links that have faculty and doesn't start with mailto: and length<150 and is not a pdf link. It ignores already visited link. 
3. Stores unique traversed links in database and constructs an N-ary tree from the links traversed. Then diameter of the N-ary tree is calculated.   
4. It does not crawl to the www.uta.edu as a requirement. www.uta.edu is the parent of cse.uta.edu    
5. The diameter of a tree (sometimes called the width) is the number of nodes on the longest path between two leaves in the tree.
 

 References:   
 http://techieme.in/tree-diameter/  
 http://programtalk.com/java/java-tree-implementation/  
 http://www.geeksforgeeks.org/diameter-n-ary-tree/
