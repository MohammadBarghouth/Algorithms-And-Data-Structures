import java.util.LinkedList;
import java.util.function.Consumer;

public class Tree<T> {
    private T value;
    private Tree<T> left, right;

    public static void main(String[] args) {
        class MyInt implements Comparable{
            final int value;

            public MyInt(int value) {
                this.value = value;
            }

            @Override
            public boolean isGreaterThen(Comparable c) {
                return c instanceof MyInt && ((MyInt) c).value < this.value;
            }

            @Override
            public String toString(){
                return value +"";
            }

            @Override
            public boolean equals(Object obj){
               if(!(obj instanceof  MyInt)) return false;
               return this.isEqualTo((MyInt) obj);
            }


        }

        Tree<MyInt> t = new Tree<MyInt>(
                new MyInt(5),
                new Tree <MyInt> (
                        new MyInt(2),
                        new Tree <MyInt> (new MyInt(1)),
                        new Tree <MyInt> (new MyInt(3))
                ),
                new Tree <MyInt> (
                        new MyInt(10),
                        new Tree <MyInt> (
                                new MyInt(7),
                                new Tree <MyInt> (new MyInt(6)),
                                new Tree<MyInt>( new MyInt(8))
                        ),
                        new Tree <MyInt> (
                                new MyInt(15),
                                null,
                                new Tree <MyInt> (new MyInt(18))
                        )
                )
        );
        Tree<MyInt> t2 = new Tree<MyInt>(
                new MyInt(5),
                new Tree <MyInt> (
                        new MyInt(2),
                        new Tree <MyInt> (new MyInt(1)),
                        new Tree <MyInt> (new MyInt(3))
                ),
                new Tree <MyInt> (
                        new MyInt(10),
                        new Tree <MyInt> (
                                new MyInt(7),
                                new Tree <MyInt> (new MyInt(6)),
                                new Tree<MyInt>( new MyInt(8))
                        ),
                        new Tree <MyInt> (
                                new MyInt(15),
                                null,
                                new Tree <MyInt> (new MyInt(18))
                        )
                )
        );

        // System.out.println(isBinaryTree(t));
        // System.out.println(binarySearch(t, new MyInt(1)));
        // System.out.println(addToNewBinaryTree(t, new MyInt(14)));
        // addToBinaryTree(t, new MyInt(4)); System.out.println(t);
        // System.out.println(copy(t));
        // System.out.println(removeFromNewBinrayTree(t, new MyInt(15)));
        // System.out.println(toSortedList(t));
        // System.out.println(depth(t));
        // leftView(t, System.out::println);
        // System.out.println(t.equals(t2));
        // System.out.println(t); mirror(t); System.out.println(t);
        // System.out.println(copyAndMirror(t));
        // System.out.println(copyAndMirror(copyAndMirror(t)));
        // System.out.println(t.isSymmetricTo(t));
        // System.out.println( new Tree <MyInt> (new MyInt(7), new Tree <MyInt> (new MyInt(6)), new Tree<MyInt>( new MyInt(8))).isSupTreeOf(t));
        // System.out.println(t.findAncestor(new Tree <MyInt> (new MyInt(7), new Tree <MyInt> (new MyInt(6)), new Tree<MyInt>( new MyInt(8)))));
        // System.out.println(countLeaves(t));
        // System.out.println(size(t));
        // System.out.println(t.kthAncestor(new Tree<MyInt>( new MyInt(8)), 1));
    }

    // CONSTRUCTORS

    public Tree(T value) {
        this.value = value;
    }

    public Tree(T value, Tree<T> left, Tree<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public Tree(Tree<T> toCopy) {
        this.value = toCopy.value;
        this.left = toCopy.left;
        this.right = toCopy.right;
    }

    // NORMAL BINARY TREES

    public static <T> void postorder(Tree<T> tree, Consumer<T> consumer){
        if(tree == null) return;
        postorder(tree.left, consumer);
        postorder(tree.right, consumer);
        consumer.accept(tree.value);
    }

    public static <T> void preorder(Tree<T> tree, Consumer<T> consumer){
        if(tree == null) return;
        consumer.accept(tree.value);
        preorder(tree.left, consumer);
        preorder(tree.right, consumer);
    }

    public static <T> void leftView(Tree<T> tree, Consumer<T> consumer){
        if(tree == null) return;
        consumer.accept(tree.value);
        leftView(tree.left, consumer);
    }

    public static <T> void inorder(Tree<T> tree, Consumer<T> consumer){
        if(tree == null) return;
        inorder(tree.left, consumer);
        consumer.accept(tree.value);
        inorder(tree.right, consumer);
    }

    public static int depth(Tree tree){
        if(tree == null) return 0;
        return Math.max(depth(tree.left), depth(tree.right)) + 1;
    }

    public static int minDepth(Tree tree){
        if(tree == null) return 0;
        return Math.min(depth(tree.left), depth(tree.right)) + 1;
    }

    public static boolean isBalanced(Tree tree){
        if(tree == null) return true;
        return Math.abs(depth(tree.left) - depth(tree.right)) <= 1
                && isBalanced(tree.left) && isBalanced(tree.right);
    }

    boolean isFullTree(Tree node) {
        if(node == null) return true;
        if(node.left == null && node.right == null) return true;

        return (node.left != null && node.right != null)
                && isFullTree(node.left) && isFullTree(node.right);
    }

    private static boolean isComplete(Tree node, int i, int size){
        if(node == null) return true;
        if(i >= size) return false;
        return isComplete(node.left, i*2 +1, size) && isComplete(node.left, i*2 +2, size);
    }

    public boolean isSymmetricTo(Tree<T> tree){
        if(tree == null) return false;
        return  this.value.equals(tree.value)
                && (left == null || left.isSymmetricTo(tree.right))
                && (right == null || right.isSymmetricTo(tree.left));
    }

    public boolean isSupTreeOf(Tree<T> tree){
        if(tree == null) return false;
        return this.equals(tree)
                || this.isSupTreeOf(tree.right)
                || this.isSupTreeOf(tree.left);
    }

    public static int countLeaves(Tree tree){
        if(tree == null) return 0;
        if(tree.left == null && tree.right == null) return 1;
        return countLeaves(tree.left) + countLeaves(tree.right);
    }

    public static <T> int size(Tree<T> tree){
        if(tree == null) return 0;
        return 1 + size(tree.left) + size(tree.right);
    }

    public static <T> Tree<T> copy(Tree<T> tree){
        if(tree == null) return null;
        return new Tree<>(
                tree.value,
                copy(tree.left),
                copy(tree.right)
        );
    }

    public Tree<T> findAncestor(Tree<T> subTree){
        Tree<T> ancestorInLeft = null, ancestorInRight = null;

        if(this.left != null){
            if(this.left.equals(subTree)) return this;
            ancestorInLeft = this.left.findAncestor(subTree);
        }
        if(this.right != null){
            if(this.right.equals(subTree)) return this;
            ancestorInRight = this.right.findAncestor(subTree);
        }

        return ancestorInLeft != null? ancestorInLeft : ancestorInRight;
    }

    public static <T> boolean findInDepth(Tree<T> tree, Tree<T> subtree, int depth){
        if(tree == null || depth < 0) return false;
        if(depth == 0) return tree.equals(subtree);
        return findInDepth(tree.left, subtree, depth - 1) || findInDepth(tree.right, subtree, depth - 1);


    }

    public Tree<T> kthAncestor(Tree<T> subtTree, int k){
        if(findInDepth(this, subtTree, k)) return this;
        Tree <T> leftKthAncestor = this.left != null? left.kthAncestor(subtTree, k) : null;
        Tree <T> rightKthAncestor = this.right != null? right.kthAncestor(subtTree, k) : null;

        return leftKthAncestor != null? leftKthAncestor : rightKthAncestor;
    }

    public static <T> void mirror(Tree<T> tree){
        if(tree == null) return;
        Tree <T> right = tree.right;
        tree.right = tree.left;
        tree.left = right;
        mirror(tree.left);
        mirror(tree.right);
    }

    public static <T> Tree<T> copyAndMirror(Tree<T> tree){
        if(tree == null) return null;
        return new Tree<>(
                tree.value,
                copyAndMirror(tree.right),
                copyAndMirror(tree.left)
        );
    }



    // BINARY SEARCH TREES ----------------------------------------------------------------------------------------

    public static <T extends Comparable> Tree<T> maxInBinaryTree(Tree<T> tree){
        if(tree.right == null) return tree;
        return maxInBinaryTree(tree.right);
    }

    public static <T extends Comparable> Tree<T> minInBinaryTree(Tree<T> tree){
        if(tree.left == null) return tree;
        return maxInBinaryTree(tree.left);
    }

    public static <T extends Comparable> boolean isBinaryTree(Tree<T> tree){
        return tree == null
                ||((tree.right == null || minInBinaryTree(tree.right).value.isGreaterThen(tree.value))
                && (tree.left == null || !maxInBinaryTree(tree.left).value.isGreaterThen(tree.value))
                && isBinaryTree(tree.right) && isBinaryTree(tree.left));
    }

    public static <T extends Comparable> Tree<T> binarySearch(Tree<T> tree, T value){
        if (tree == null) return null;
        if (tree.value.isEqualTo(value)) return tree;
        if (value.isGreaterThen(tree.value)) return binarySearch(tree.right, value);
        return binarySearch(tree.left, value);
    }

    public static <T extends Comparable> LinkedList<T> toSortedList(Tree<T> tree){
        LinkedList<T> sortedList = new LinkedList<>();
        inorder(tree, sortedList::add);
        return sortedList;
    }

    public static <T extends Comparable> Tree<T> addToNewBinaryTree(Tree<T> tree, T value){
        if (tree == null) return new Tree<>(value);

        Tree <T> newTree = new Tree<>(tree.value, tree.left, tree.right);

        if (value.isGreaterThen(newTree.value)) {
            newTree.right = addToNewBinaryTree(tree.right, value);
        }else{
            newTree.left = addToNewBinaryTree(tree.left, value);
        }

        return newTree;
    }

    public static <T extends Comparable> void addToBinaryTree(Tree<T> tree, T value){
        if (tree == null) return;

        if (value.isGreaterThen(tree.value)) {
            if(tree.right == null) tree.right = new Tree<>(value);
            else addToBinaryTree(tree.right, value);
        }else{
            if(tree.left == null) tree.left = new Tree<>(value);
            else addToBinaryTree(tree.left, value);
        }
    }

    public static <T extends Comparable> Tree<T> removeFromNewBinrayTree(Tree<T> tree, T value){
        if(tree == null) return null;

        final Tree <T> leftTree, rightTree;
        T newValue = tree.value;

        if (tree.value.isEqualTo(value)) {
            if(tree.left == null && tree.right == null) return null;
            if(tree.left == null) return copy(tree.right);
            if(tree.right == null) return copy(tree.left);

            Tree<T> maxTree = minInBinaryTree(tree);   // always: left <-> max
            newValue = maxTree.value;
            leftTree = removeFromNewBinrayTree(tree.left, maxTree.value);
            rightTree = copy(tree.right);
        } else if (value.isGreaterThen(tree.value)) {
            leftTree = copy(tree.left);
            rightTree = removeFromNewBinrayTree(tree.right, value);
        } else {
            leftTree = removeFromNewBinrayTree(tree.left, value);
            rightTree = copy(tree.right);
        }

        return new Tree<>(newValue, leftTree, rightTree);
    }

    // OVERRIDDEN METHODS

    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(!(obj instanceof Tree)) return false;
        Tree<T> tree = (Tree<T>) obj;

        return value.equals(tree.value)
                && ((left == null && tree.left == null) || (left != null && left.equals(tree.left)))
                && ((right == null && tree.right == null) || (right != null &&right.equals(tree.right)));
    }

    @Override
    public String toString(){
        return toString(1);
    }

    private String toString(int i){
        if(this.left == null && this.right == null) return "Tree ("+this.value+")";
        return "Tree ("+this.value+"){ \n\r"
                + repeatString("    ",i) + (this.left != null? this.left.toString(i+1) : "empty") + ",\n\r"
                + repeatString("    ",i) + (this.right != null? this.right.toString(i+1) : "empty") + "\n\r"
                + repeatString("    ",i - 1) + "}";
    }

    private static String repeatString(String str, int i){
        return String.valueOf(str).repeat(i);
    }

    // GETTERS AND SETTERS

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Tree<T> getLeft() {
        return left;
    }

    public void setLeft(Tree<T> left) {
        this.left = left;
    }

    public Tree<T> getRight() {
        return right;
    }

    public void setRight(Tree<T> right) {
        this.right = right;
    }
}



