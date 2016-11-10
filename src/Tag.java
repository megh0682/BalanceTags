import java.lang.reflect.Array;
import java.security.KeyStore.Entry;
import java.util.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;

public class Tag {
	private static final Logger log = Logger.getLogger(Tag.class.getName());
	private String tagString;
	StringBuilder strbldr;
	char[] charArray;
	Queue<String[]> queue;
	Map<Integer,Character> map ;
	Stack stack;
	public Tag(String tagString){
		log.info("Constructor for Tag class");
		this.tagString = tagString;
	}
	
	//convert the string into character array
	public char[] toCharArray(){
	charArray = tagString.toCharArray();
	log.info("Input string converted to a char array");
	return charArray;
	}
	
	//Store the opening tag character index and corresponding character in map as a key-value pair
	public void storeOpenTagsAndIndex(){
	queue = new LinkedList<String[]>();
	map = new HashMap<Integer,Character>();
	stack= new Stack();
	log.info("Loop through character array and store opening brackets and corresponding indexes to an array");
	log.info("Store the array in Stack");
	for(int i= 0; i < charArray.length; i++){
	   if((charArray[i]==('[')) || (charArray[i]==('(')) || (charArray[i]==('{'))){
		   Integer index = i;
		   String[] strArray = new String[2];
		   strArray[0] = index.toString();
		   //System.out.println(strArray[0]);
		   strArray[1] = ((Character)(charArray[i])).toString();
		   charArray[index]='$';
		   //System.out.println(strArray[1]);
		   //map.put(index,c);
		   queue.add(strArray);
		   stack.push(strArray);
		}
	 }
	 }
	 
	 public Boolean isBalanced(){
		 //Traverse through map and process the open tag with corresponding index
		 strbldr = new StringBuilder();
		 strbldr.append(tagString);
		 Boolean isvalid = false;
		 Boolean flag = true;
		 for(Map.Entry<Integer, Character> entry : map.entrySet()){
			 Integer index = entry.getKey();
			 Character openChar = entry.getValue();
			 Character closingChar = getClosingTag(openChar);
			 isvalid=isEndTagValid(index,openChar,closingChar);
			 if(isvalid != true){
			   flag = false;
			   break;
			 }
		  }
		 
	     return flag;
	 }
	 
	 public Boolean isBalancedUsingQueue(){
		 //Traverse through map and process the open tag with corresponding index
		 strbldr = new StringBuilder();
		 strbldr.append(tagString);
		 Boolean isvalid = false;
		 Boolean flag = true;
		 while(!queue.isEmpty()){
			 String[] strArray = (String[]) queue.remove();
			 Integer index = Integer.parseInt(strArray[0]);
			 Character openChar =strArray[1].toCharArray()[0];
			 Character closingChar = getClosingTag(openChar);
			 isvalid=isEndTagValid(index,openChar,closingChar);
			 if(isvalid != true){
			   flag = false;
			   break;
			 }
		  }
		 
	     return flag;
	 }
	 
	 public Boolean isBalancedUsingStack(){
		 //Traverse through map and process the open tag with corresponding index
		 log.info("StringBuilder object created");
		 strbldr = new StringBuilder();
		 log.info("String pattern appended to StringBuilder object");
		 strbldr.append(tagString);
		 Boolean isvalid = false;
		 Boolean flag1=true,flag2 = true;
		 log.info("Traverse the stack");
		 while(!stack.isEmpty()){
			 log.info("Get last opening bracket and corresponding index");
			 String[] strArray = (String[]) stack.pop();
			 Integer index = Integer.parseInt(strArray[0]);
			 Character openChar =strArray[1].toCharArray()[0];
			 log.info("Get closing character corresponding to opening character");
			 Character closingChar = getClosingTag(openChar);
			 log.info("validate last opening bracket has closing bracket");
			 isvalid=isEndTagValid(index,openChar,closingChar);
			 if(isvalid != true){
			   flag1 = false;
			   log.info("last opening bracket does not have closing bracket");
			   break;
			 }else{
			   log.info("last opening bracket has closing bracket");
			 }
		  }
		 
		 //
		 char[] charArray = strbldr.toString().toCharArray();
		for(Character c : charArray){
			if( c.equals(']')|| c.equals(')')|| c.equals('}')|| c.equals('[')|| c.equals('(')|| c.equals('{')){
			flag2 = false;
		    }
		}		 
	     return (flag1&&flag2);
	 }
	 
	 public Character getClosingTag(Character openChar) {
		switch(openChar){
		case '[' : return ']'; 
		case '(' : return ')'; 
		case '{' : return '}';
		default : return null;
		}
	 }

	public Boolean isEndTagValid(Integer index, Character openChar,Character closingChar){
		 //Get a substring from index to end of the string and validate next closing tag corresponds to an opening tag 
		 String substr = strbldr.substring(index);
		 char[] array = substr.toCharArray();
		 //System.out.println(array);
		 boolean flag = false;
		 int y =0;
		 for(int i = 0 ; i < array.length; i++){
			if(((Character)array[i]).equals(closingChar)){
				 flag = true;
				 int index2 = index+y ;
				 //System.out.println("index"+ index +"index2" +index2);
                 log.info("replace opening char and closing char with $");
				 strbldr.setCharAt(index, '$');
				 strbldr.setCharAt(index2, '$');
				 break;
			 }else if( ((Character)array[i]).equals(']')|| ((Character)array[i]).equals(')')|| ((Character)array[i]).equals('}')){
				 flag = false;
				 break;
			 }else{
				 flag = false;
			 }
			
			y = y +1;
		 }
		 return flag;
	 }
	 
	 
}