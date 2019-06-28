import java.util.ArrayList;
import java.lang.*;

public class BSTree implements BSToper{
  protected Node root = null;
  protected int counSize = 0;
  protected ArrayList<Integer> valueStorage;
  protected int[] inOrder = null;

  private class Node{
    Node left,right,parent;
    int value;

    Node(){

    }

    Node(int v){
      value = v;
    }
    public int getValue(){
      return value;
    }
    public void setParent(Node p){
      parent = p;
    }
    public Node getParent(){
      return parent;
    }
  }

  BSTree(){
    valueStorage = new ArrayList<Integer>();
  }

  private Node findParent(Node n){
    Node parent = null;
    if (n.getParent()==null) {
      parent = n;
    }else {
      parent = n.getParent();
    }
    return  parent;
  }
  private Node findGrandparent(Node n){
    Node parent = findParent(n);
    Node grandparent = findParent(parent);
    return grandparent;
  }
  private Node find(int value,Node node){
    Node result = null;
    if (node.getValue() == value) {
      result = node;
    }
    else{
      if (node.getValue() > value) {
        if (node.left != null ) {
          Node left = find(value,node.left);
          result = left;
        }
      }else {
        if (node.right != null) {
          Node right = find(value,node.right);
          result = right;
        }
      }
    }
    return result;
  }

  public void add(int value){
    Node newLeaf = new Node(value);
    Node currentRoot = root;

    while (true){
      if (root == null){
        root = newLeaf;
        valueStorage.add(value);
        root.setParent(null);
        counSize++;
        break;
      }
      else if (currentRoot.getValue() > newLeaf.getValue()) {
        if (currentRoot.left == null) {

          currentRoot.left = newLeaf;
          currentRoot.left.setParent(currentRoot);
          valueStorage.add(value);
          counSize++;
          break;
        }else{
          currentRoot = currentRoot.left;
          continue;
        }
      }
      else if (currentRoot.getValue() < newLeaf.getValue()) {
        if (currentRoot.right == null) {
          currentRoot.right = newLeaf;
          currentRoot.right.setParent(currentRoot);
          valueStorage.add(value);
          counSize++;
          break;
        }else{
          currentRoot = currentRoot.right;
          continue;
        }
      }
      else{
        System.out.println("Number exist alredy in the tree");
        break;
      }
    }
  }
  /*
    This remove method onøy works for binary search,
    that means a complete or full tree.
  */

  public boolean remove(int value){
    Node nodeToWiped = find(value,root);
    Node nytnode =  null;

    if (existsInTree(value)) {
      counSize--;
      if (nodeToWiped.left == null && nodeToWiped.right==null) {
        Node parent = findParent(nodeToWiped);
        int checkPositionOfNodeToWiped = (parent.getValue()>nodeToWiped.getValue()) ? 0 : 1;
        if (checkPositionOfNodeToWiped == 0) {
          parent.left = null;
        }else {
          parent.right = null;
        }
        return true;
      }
      else if (nodeToWiped.getValue() == root.getValue()) {

        Node maxnode = getMax(nodeToWiped.left);
        Node parent = findParent(maxnode);
        parent.right = null;
        maxnode.left = root.left;
        maxnode.left.setParent(maxnode);
        maxnode.right = root.right;
        maxnode.right.setParent(maxnode);
        root = maxnode;
        return true;
      }
      else if (nodeToWiped.left != null && nodeToWiped.right != null) {
        Node parent = findParent(nodeToWiped);
        int checkPositionOfNodeToWiped = (parent.getValue()>nodeToWiped.getValue()) ? 0 : 1;

        if (checkPositionOfNodeToWiped == 0) {
          nytnode = nodeToWiped.left;
          parent.left = nytnode;
          nytnode.right = nodeToWiped.right;
          nytnode.right.setParent(nytnode);
          nytnode.setParent(parent);

        }else{
          nytnode = nodeToWiped.left;
          parent.right = nytnode;
          nytnode.right = nodeToWiped.right;
          nytnode.right.setParent(nytnode);
          nytnode.setParent(parent);
        }
        return true;
      }else if (nodeToWiped.left != null) {
        Node parent = findParent(nodeToWiped);
        int checkPositionOfNodeToWiped = (parent.getValue()>nodeToWiped.getValue()) ? 0 : 1;
        if (checkPositionOfNodeToWiped == 0) {
          nytnode=nodeToWiped.left;
          parent.left = nytnode;
          nytnode.setParent(parent);
        }else{
          nytnode = nodeToWiped.left;
          parent.right = nytnode;
          nytnode.setParent(parent);
        }
        return true;
      }else{
        Node parent = findParent(nodeToWiped);
        int checkPositionOfNodeToWiped = (parent.getValue()>nodeToWiped.getValue()) ? 0 : 1;
        if (checkPositionOfNodeToWiped == 0) {
          nytnode=nodeToWiped.right;
          parent.left = nytnode;
          nytnode.setParent(parent);
        }else{
          nytnode = nodeToWiped.right;
          parent.right = nytnode;
          nytnode.setParent(parent);
        }

        return true;
      }
    }else{
      return  false;
    }

  }
  private Node getMax(Node n){
    if (n.right == null) {
      return n;
    }else {
      return getMax(n.right);
    }
  }
  public int size(){
    return counSize;
  }
  private Node getRoot(){
    return root;
  }

  public boolean existsInTree(int value){

    if (find(value,root) != null) {
      return true;
    }else {
      return false;
    }

  }
  public int findNearestSmallerThan( int value ){
    Node smallerThan = null;
    int returnValue;
    if (existsInTree(value)) {
      smallerThan  = find(value,root);
      if (smallerThan.left != null) {
        returnValue= smallerThan.left.getValue();
      }else{
        System.out.println("There is not a lesser number that "+value+" therfore the method just return back the value sent in");
        returnValue = value;
      }

    }else{
      System.out.println("The number tasted in was not found in the tree: The retun value will therfore be the same");
      returnValue = value;
    }
    return returnValue;
  }

  public void addAll(int[] integers){
    for (int i = 0; i < integers.length ; i++) {
      this.add(integers[i]);
    }
  }
  private ArrayList<Integer> getcChild(Node parent){
     ArrayList<Integer> thisSide = new ArrayList<Integer>();

    if (parent.left == null) {
      thisSide.add(parent.getValue());
      if (parent.right != null) {
        thisSide.addAll(getcChild(parent.right));
      }
      return thisSide;
    }else{
      thisSide.addAll(getcChild(parent.left));
      thisSide.add(parent.getValue());
      if (parent.right != null) {
        thisSide.addAll(getcChild(parent.right));
      }
      return thisSide;
    }
  }
  public int[] sortedArray(){
    int[] sorterarray = new int[counSize];

    ArrayList<Integer> leftSide = getcChild(root.left);
    ArrayList<Integer>  rightSide = getcChild(root.right);

    int[] left = new int[leftSide.size()];
    int[] right = new int[rightSide.size()];
    int rightSideCounter = left.length +1;

    for (int i =0; i < leftSide.size(); i++) {
      sorterarray[i] = leftSide.get(i);
    }
    sorterarray[left.length] = root.getValue();

    for (int i = 0; i < rightSide.size(); i++) {
      sorterarray[rightSideCounter] = rightSide.get(i);
      rightSideCounter++;
    }
    inOrder = sorterarray;

    return inOrder;
  }
  public int[] getArray(){
    return inOrder;
  }
  public int[] findInRange(int low, int high){
    int[] newArray = new int[high-low+1];
    System.arraycopy(inOrder,low,newArray,0,high-low+1);
    return newArray;
  }

}
