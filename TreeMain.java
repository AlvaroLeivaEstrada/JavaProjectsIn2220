import java.util.Arrays;
class TreeMain{

  public static void main(String[] args) {
    BSTree my_tree = new BSTree();
    //my_tree.add(5);
    int[] listNum = {89,100,7,56,2,12,43,23,41,5,1,34,12,45,90};
    my_tree.addAll(listNum);
    System.out.println(Arrays.toString(my_tree.sortedArray()));
    System.out.println(Arrays.toString(my_tree.findInRange(2,5)));
    my_tree.remove(2);
    String debugg = (my_tree.existsInTree(2)) ? "Number 2 is still found " :"Number 2 removed from binary";
    my_tree.remove(56);
    String debugg2 = (my_tree.existsInTree(56)) ? "Number 56 is still found " :"Number 56 removed from binary";
    my_tree.remove(41);
    String debugg3 = (my_tree.existsInTree(41)) ? "Number 41 is still found " :"Number 41 removed from binary";
    System.out.println(debugg);
    System.out.println(debugg2);
    System.out.println(debugg3);
    System.out.println(Arrays.toString(my_tree.sortedArray()));
    System.out.println(my_tree.findNearestSmallerThan(89));
    System.out.println(my_tree.findNearestSmallerThan(7));
  }

}
