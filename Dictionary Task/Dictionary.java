import java.util.*;

public class Dictionary { 

    //Global trie
    static Node root;
      
    //Each trie object
    static class Node{ 
        ArrayList<Node> children = new ArrayList<Node>();
        char letter;
        String definition;
          
        Node(char letter){
            this.letter = letter;
            this.definition = null;
        } 
    };
    
    /*  Insert method
    *   Inserts the word into the node trie
    */
    static void insert(String word, String definition){
        Node myNode = root; 
       
        for(int position = 0; position < word.length(); position++) {
            Node nextNode = findNodeByChar(myNode.children, word.charAt(position));

            if(nextNode != null){
                //Move to next position
                myNode = nextNode;
            }else{
                Node newNode = new Node(word.charAt(position));
                myNode.children.add(newNode);
                myNode = newNode;
            }
        }
       
        myNode.definition = definition;
    }

    /*  Helper method for insert
    *   Finds the node for a particular character if it exists
    */
    static Node findNodeByChar(ArrayList<Node> children, char letter){
        for(Node node : children) {
            if(node.letter == letter) {
                return node;
            }
        }

        return null;
    }

    /*  Definition method
    *   Finds definition and returns it, error message if not found.
    *   I would have liked to implement some exception or error throwing if I had more time.
    */
    static String getDefinition(String word){
        Node myNode = root;

        for(int position = 0; position < word.length(); position++){
            Node nextNode = findNodeByChar(myNode.children, word.charAt(position));

            if(nextNode != null){
                myNode = nextNode;
            }else{
                return "Word cannot be found";
            }
        }

        if(myNode.definition != null){
            return myNode.definition;
        }else{
            return "Word cannot be found";
        }
    }

    /*  Delete word method
    *   
    *   I check to see if a word exists by if the object has a definition attached to it.
    *   With my delete implementation I just remove the definition from the Node object and keep the trie in place.
    */
    static void deleteWord(String word){
        Node myNode = root;

        for(int position = 0; position < word.length(); position++){
            Node nextNode = findNodeByChar(myNode.children, word.charAt(position));

            if(nextNode != null){
                myNode = nextNode;
            }
        }

        if(myNode.definition != null){
            myNode.definition = null;
        }
    }

    /* prefix method for returning list of all words
    *   return empty list if no words match
    */
    static ArrayList<String> getPrefix(String prefix){
        Node myNode = root;
        ArrayList<String> prefixList = new ArrayList<String>();

        for(int position = 0; position < prefix.length(); position++){
            Node nextNode = findNodeByChar(myNode.children, prefix.charAt(position));

            if(nextNode != null){
                myNode = nextNode;
            }else{
                return prefixList;
            }
        }

        //Add prefix if it itself is a word
        if(myNode.definition != null){
            prefixList.add(prefix);
        }
        
        //Dispatcher for recursion
        for (Node node : myNode.children) {
            prefixList.addAll(findWordChildren(node, prefix));
        }

        return prefixList;
    }

    /* 
    *   Recursive word finder
    */
    static ArrayList<String> findWordChildren(Node thisNode, String prefix){
        ArrayList<String> words = new ArrayList<String>();

        if(thisNode.definition != null){
            words.add(prefix + thisNode.letter);
        }

        for (Node node : thisNode.children) {
            words.addAll(findWordChildren(node, prefix + thisNode.letter));
        }

        return words;
    }
    




    public static void main(String args[])  {
        root = new Node(' ');  
    
        testInsert();
        testFindWithInsert();
        testFindWithoutWord();
        testDelete();
        testPrefix1();
        testPrefix2();
        testPrefix3();
        testPrefix4();
        

        System.out.println("End Main");
    }





    //Tests
    /*
        Tests basic insert function. Always returns ok unless something goes wrong.
    */
    public static void testInsert(){
        root = new Node(' ');  
        System.out.println("-----Test Insert-----");

        insert("trumpet", "A trumpet is a brass instrument commonly used in classical and jazz ensembles.");

        System.out.println("ok");
        System.out.println("-----End Test-----\n");
    }


    /*
        Tests an insert and getting the definition from the insertion
    */
    public static void testFindWithInsert(){
        root = new Node(' ');  
        System.out.println("-----Test Insert and Find-----");

        insert("trumpet", "A trumpet is a brass instrument commonly used in classical and jazz ensembles.");
        String trumpetDef = getDefinition("trumpet");

        if(trumpetDef == "A trumpet is a brass instrument commonly used in classical and jazz ensembles."){
            System.out.println("ok");
        }else{
            System.out.println("FAIL");
        }

        System.out.println("-----End Test-----\n");
    }


    /*
        Tests finding definition without insertion
    */
    public static void testFindWithoutWord(){
        root = new Node(' ');  
        System.out.println("-----Test find without word-----");

        String def = getDefinition("chair");

        if(def == "Word cannot be found"){
            System.out.println("ok");
        }else{
            System.out.println("FAIL");
        }

        System.out.println("-----End Test-----\n");
    }


    /*
        Tests deletion returns correct error message
    */
    public static void testDelete(){
        root = new Node(' ');  
        System.out.println("-----Test delete-----");
        deleteWord("trumpet");
        String def = getDefinition("trumpet");

        if(def == "Word cannot be found"){
            System.out.println("ok");
        }else{
            System.out.println("FAIL");
        }

        System.out.println("-----End Test-----\n");
    }


    /*
        Tests prefix, single char
    */
    public static void testPrefix1(){
        root = new Node(' ');  
        System.out.println("-----Test Prefix1-----");
        insert("unbroken", "unbroken");
        insert("uncertain", "uncertain");
        insert("uncommon", "uncommon");
        insert("untied", "untied");

        ArrayList<String> words = getPrefix("u");
        ArrayList<String> correct = new ArrayList<String>(Arrays.asList(new String[]{"unbroken", "uncertain","uncommon", "untied"}));

        if(correct.equals(words)){
            System.out.println("ok");
        }else{
            System.out.println("FAIL");
        }

        System.out.println("-----End Test-----\n");
    }


    /*
        Tests prefix, multiple char
    */
    public static void testPrefix2(){
        root = new Node(' ');  
        System.out.println("-----Test Prefix2-----");
        insert("unbroken", "unbroken");
        insert("uncertain", "uncertain");
        insert("uncommon", "uncommon");
        insert("untied", "untied");

        ArrayList<String> words = getPrefix("unc");
        ArrayList<String> correct = new ArrayList<String>(Arrays.asList(new String[]{ "uncertain","uncommon"}));

        if(correct.equals(words)){
            System.out.println("ok");
        }else{
            System.out.println("FAIL");
        }

        System.out.println("-----End Test-----\n");
    }

    
     /*
        Tests prefix, no words
    */
    public static void testPrefix3(){
        root = new Node(' ');  
        System.out.println("-----Test Prefix3-----");
        insert("unbroken", "unbroken");
        insert("uncertain", "uncertain");
        insert("uncommon", "uncommon");
        insert("untied", "untied");

        ArrayList<String> words = getPrefix("pre");
        ArrayList<String> correct = new ArrayList<String>();

        if(correct.equals(words)){
            System.out.println("ok");
        }else{
            System.out.println("FAIL");
        }

        System.out.println("-----End Test-----\n");
    }


     /*
        Tests prefix, makes sure that prefix can also be a word itself
    */
    public static void testPrefix4(){
        root = new Node(' ');  
        System.out.println("-----Test Prefix4-----");
        insert("unbroken", "unbroken");
        insert("uncertain", "uncertain");
        insert("uncommon", "uncommon");
        insert("untied", "untied");
        insert("un", "un");

        ArrayList<String> words = getPrefix("un");
        ArrayList<String> correct = new ArrayList<String>(Arrays.asList(new String[]{"un", "unbroken", "uncertain","uncommon", "untied"}));

        if(correct.equals(words)){
            System.out.println("ok");
        }else{
            System.out.println("FAIL");
        }

        System.out.println("-----End Test-----\n");
    }

    //End tests





} 