package homeWork;
public class BinarySearchTree {

   private Node root;
   private int size; // Size of tree would be the # of unique words 
   private int totalWordCount; // total words in file 

   /**
    * If a WordFrequency object already exists
    * in the tree for the parameter word,
    * then increase the count of that WordFrequency
    * object. Otherwise, add a WordFrequency
    * object for the word to the binary search tree.
    * @param word
    */
   public void put(String word) {
      if(root == null) {
         root = new Node(new WordFrequency(word));
      }
      else {
         put(root, word);
      }
      size();
      getTotalWordCount();
   }

   private void put(Node root, String word) {
      if(root.item.getWord().equals(word)){
         root.item.increaseCount();
      }
      else if(word.compareTo(root.item.getWord()) < 0) {
         if(root.left == null) {
            root.left = new Node(new WordFrequency(word));
            return;
         }
         put(root.left, word);
      }
      else if(word.compareTo(root.item.getWord()) > 0) {
         if(root.right == null) {
            root.right = new Node(new WordFrequency(word));
            return;
         }
         put(root.right, word);
      }
   }


   /**
    * If a WordFrequency object with the
    * given word exists in the tree, return
    * the frequency of the word.
    * Otherwise, 0 is returned.
    * @param word
    * @return the frequency of the word
    */
   public int getFrequency(String word) {
      return getFrequency(root, word);     
   }

   private int getFrequency(Node root, String word) {
      if(root == null) {
         return 0;
      }
      else if(root.item.getWord().equals(word)){
         return root.item.getCount();
      }
      else if(word.compareTo(root.item.getWord()) < 0) {
         return getFrequency(root.left, word);
      }
      return getFrequency(root.right, word); 
   }


   /**
    * Prints all of the WordFrequency objects
    * in the tree that fall between
    * startWord (inclusive) and endWord
    * (not including endWord).
    * Prints one per line.
    * @param startWord the first word in the range
    * @param endWord the last word in the range
    */
   public void printRange(String startWord, String endWord) {
      printRange(root, startWord, endWord); //*** Recursive call.  this is helper
   }

   // Recursive method to print range.
   public void printRange(Node root, String startWord, String endWord) {
      //stop
      if(root == null) {
         return;
      }

      //print condition
      if(startWord.compareTo(root.item.getWord()) <= 0 && endWord.compareTo(root.item.getWord()) > 0){
         System.out.println(root.item.getWord());
      }   

      printRange(root.right, startWord, endWord);
      printRange(root.left, startWord, endWord);

   }    
   /**
    * Returns the number of WordFrequency objects in the tree.
    * @return the number of WordFrequency objects in the tree
    */
   public int size() {
      size = 0;
      size = size(root);
      return size;
   }

   private int size(Node root) {
      if(root == null) {
         return 0;
      }
      return 1 + size(root.right) + size(root.left);
   }

   /**
    * Returns the sum of all the word frequencies
    * @return the sum of all the word frequencies
    */
   public int getTotalWordCount() {
      totalWordCount = 0;
      totalWordCount = getTotalWordCount(root);
      return totalWordCount;
   }

   private int getTotalWordCount(Node root) {
      if(root == null) {
         return 0;
      }
      return root.item.getCount() + getTotalWordCount(root.right) + getTotalWordCount(root.left);
   }

   private static class Node {
      public WordFrequency item;
      public Node left;
      public Node right;

      public Node(WordFrequency item) {
         this.item = item;
      }

   }
}
