import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
 
public class Main {
	public static DB db = new DB();
	public static Node root;
	public static void main(String[] args) throws SQLException, IOException {
		db.runSql2("TRUNCATE Record;");
		Node treeRootNode = new Node(null);
		treeRootNode.setId("http://www.uta.edu");
		root = treeRootNode;
		processPage("http://cse.uta.edu",treeRootNode);
		Node.printTree(root);
	}
 
	public static void processPage(String URL,Node parent) throws SQLException, IOException{
		//check if the given URL is already in database
		String sql = "select * from Record where URL = '"+URL+"'";
		ResultSet rs = db.runSql(sql);
		//System.out.println("URL"+URL);
		if(rs.next()){
			//System.out.println("If URL---> "+URL);
		}else{
			//System.out.println("else URL-->"+URL);
			//store the URL to database to avoid parsing again
			sql = "INSERT INTO  `Crawler`.`Record` " + "(`URL`) VALUES " + "(?);";
			PreparedStatement stmt = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, URL);
			stmt.execute();
 
			//get useful information
			Document doc = Jsoup.connect(URL).get();
 
			/*if(doc.text().contains("research")){
				System.out.println(URL);
			}*/
			
			
 
			//get all links and recursively call the processPage method (BFS traversal)
			Elements questions = doc.select("a[href]");
			for(Element link: questions){
				if(link.attr("href").contains("faculty") && !link.attr("href").contains("www.uta") && !link.attr("href").contains("mailto:")
						&& !link.attr("href").contains(".pdf") && link.attr("abs:href").length()<150
						&& !link.attr("href").contains(".mp4")){
					//goes to only those links that have faculty and doesn't start with mailto: and length<150 and is not a pdf link
					//System.out.println(link.attr("abs:href"));
					try {
						if(Node.isRepeatedNode(root,link.attr("abs:href").toString()) == 0){
							continue;
						}else{
							Node n;
							n = Node.addChild(parent, link.attr("abs:href"));
							processPage(link.attr("abs:href"),n);
						}
					} catch (Exception e) {
						//System.out.println(link.attr("abs:href")+" is not reachable 404 not found"); //catches links that are not reachable
					}
				}
					
			}
		}
	}
}

/* 
 References: 
 http://techieme.in/tree-diameter/
 http://programtalk.com/java/java-tree-implementation/
 */
 
