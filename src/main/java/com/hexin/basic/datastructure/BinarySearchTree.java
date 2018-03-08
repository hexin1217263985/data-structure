package com.hexin.basic.datastructure;


/**
 * created on 2018/3/8
 *
 * @author hexin
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {

    private static class BinaryNode<AnyType extends Comparable<? super AnyType>> {
        public BinaryNode(AnyType value) {
            this(value, null, null);
        }
        public BinaryNode(AnyType value, BinaryNode<AnyType> left, BinaryNode<AnyType> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
        private AnyType value;
        private BinaryNode<AnyType> left;
        private BinaryNode<AnyType> right;
    }

    private BinaryNode<AnyType> root;

    public BinarySearchTree() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }
    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(AnyType x) {
        return contains(x, root);
    }
    public AnyType findMax() {
        if (isEmpty())
            return null;
        return findMax(root).value;
    }
    public AnyType findMin() {
        if (isEmpty())
            return null;
        return findMin(root).value;
    }
    public void insert(AnyType value) {
        if (value == null) {
            throw new IllegalArgumentException("Insert null value");
        }
        insert(value, root);
    }
    public void remove(AnyType value) {

    }
    public void printTree() {
        printTree(root);
    }

    /**
     * 内部在一个子树中寻找是否存在该对象的方法
     * （该方法是一个尾递归方法，可以很容易的使用while循环代替，
     * 但是尾递归在这里使用是合理的，因为算法的简明性是以速度降低为代价，
     * 这里使用的栈空间的量也不过是O(logN) ）
     * @param x 待搜索的对象
     * @param node 被搜索的子树
     * @return 存在返回true，不存在或者子树为空返回false
     */
    private boolean contains(AnyType x, BinaryNode<AnyType> node) {
        if (node == null)
            return false;
        int comparedValue = x.compareTo(node.value);
        if (comparedValue == 0)
            return true;
        else if (comparedValue > 0)
            return contains(x, node.right);
        else
            return contains(x, node.left);
    }
    private  BinaryNode<AnyType> findMax(BinaryNode<AnyType> node) {
        if (node.right == null)
            return node;
        return findMax(node.right);
    }

    /**
     * while实现找到最大值
     * @param node
     * @return
     */
    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> node) {
        while (node.right != null){
            node = node.right;
        }
        return node;
/*        if (node.left == null)
            return node;
        return findMin(node);*/
    }
    private void insert(AnyType x, BinaryNode<AnyType> node) {
        if (node == null) {
            node = new BinaryNode<AnyType>(x);
            return;
        }

        int comparedValue = x.compareTo(node.value);
        if (comparedValue > 0) {
            if (node.right == null)
                node.right = new BinaryNode<AnyType>(x);
            else
                insert(x, node.right);
        } else if (comparedValue < 0) {
            if (node.left == null)
                node.left = new BinaryNode<AnyType>(x);
            else
                insert(x, node.left);
        } else {
            //equals do nothing
        }
    }

    /**
     * 移除操作是这类数据结构最复杂的，特别是对于被删除的节点两个子节点不为空时
     * 可以采用替换法，即左子树的最大值或者右子树的最小值替换被删除的节点
     * 不过这种方法的效率不是很高，会遍历两次，一次找最大/小值，一次遍历替换
     * @param x
     * @param node
     */
    private void remove(AnyType x, BinaryNode<AnyType> node) {
        if (node == null) {
            return; //do nothing
        }
        int comparedValue = x.compareTo(node.value);
        if (comparedValue > 0) {
            remove(x, node.right);
        } else if (comparedValue < 0) {
            remove(x, node.right);
        } else if (node.right != null && node.right != null) {
            node.value = findMin(node.right).value;
            remove(node.value, node.right);
        }  else {
          node = (node.left == null) ? node.right : node.left;
        }
    }
    private void printTree(BinaryNode<AnyType> node) {
        if (node == null)
            return;
        System.out.print(node.toString() + " ");
        printTree(node.left);
        printTree(node.right);
    }
}
