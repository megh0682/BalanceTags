import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestTag {
	
	private static Scanner reader;
	private static final Logger log = Logger.getLogger(TestTag.class.getName());
    
public static void main(String[] args){
	log.setLevel(Level.INFO);
	reader = new Scanner(System.in);
	log.info("String pattern input using {,},(,)[,]");
	String string_pattern = reader.next();
	log.info("Tag object being instantiated");
	Tag tagString = new Tag(string_pattern);
	tagString.toCharArray();
	log.info("opening brackets and their corresponding indexes stored in an array");
	log.info("add the array to stack");
	tagString.storeOpenTagsAndIndex();
	log.info("process the balance of brackets");
	boolean isbalanced = tagString.isBalancedUsingStack();
	System.out.println(isbalanced);
			
}

}
