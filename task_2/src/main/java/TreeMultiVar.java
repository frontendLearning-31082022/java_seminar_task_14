package main.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class TreeMultiVar<T extends Comparable<T>> {
    private Node root;
    TreePrinter treePrinter;

    public boolean add(T value) {
        if (root != null) return addNode(root, value);
        root = new Node(value);
        return true;
    }

    public TreeMultiVar() {
        this.root = null;
    }

    private int comp(T val1, T val2) {
        try {val1.compareTo(val2); } catch (ClassCastException ca) {
            return val1.toString().compareTo(val2.toString());}

        return val1.compareTo(val2);
    }

    public boolean addNode(Node<T> current, T value) {
        if (comp(value, current.value) == 0) return false;

        if (comp(value, current.value) < 0 && current.left != null) return addNode(current.left, value);
        if (comp(value, current.value) < 0 && current.left == null) {
            current.left = new Node(value);
            return true;
        }

        if (comp(value, current.value) > 0 && current.right != null) return addNode(current.right, value);
        if (comp(value, current.value) > 0 && current.right == null) {
            current.right = new Node(value);
            return true;
        }

        throw new IllegalStateException("Прорехи");
    }

    public boolean contains(T value) {
        return findNode(root, value) != null;
    }

    public Node findNode(Node<T> current, T value) {
        if (current == null) return null;

        if (comp(value, current.value) == 0) return current;
        if (comp(value, current.value) < 0) return findNode(current.left, value);
        if (comp(value, current.value) > 0) return findNode(current.right, value);

        throw new IllegalStateException("Прорехи");
    }

    public void remove(T value) {
        root = removeNode(root, value);
    }

    private Node removeNode(Node<T> current, T value) {
        if (current == null) return null;

        if (comp(value, current.value) < 0) {
            current.left = removeNode(current.left, value);
            return current;
        } else if (comp(value, current.value) > 0) {
            current.right = removeNode(current.right, value);
            return current;
        }

        if (current.left == null && current.right == null) return null;
        if (current.left == null && current.right != null) return current.right;
        if (current.left != null && current.right == null) return current.left;


        Node<T> smallestNodeOnTheRight = findFirst(current.right);
        T smallestValueOnTheRight = smallestNodeOnTheRight.value;
        current.value = smallestValueOnTheRight;
        current.right = removeNode(current.right, smallestValueOnTheRight);
        return current;
    }

    public T findFirst() {
        if (root == null) throw new NoSuchElementException();
        return (T) findFirst(root).value;
    }

    public Node<T> findFirst(Node<T> current) {
        if (current.left == null) return current;
        return findFirst(current.left);
    }


    public List<T> dfs() {
        if (root == null) return List.of();

        List<T> list = new ArrayList<>();
        dfs(root, list);
        return list;
    }

    public void dfs(Node<T> current, List<T> result) {
        // In-order
        if (current.left != null) dfs(current.left, result);

        result.add(current.value);
        if (current.right != null) dfs(current.right, result);
    }

    public List<T> bfs() {
        if (root == null) return List.of();

        List<T> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node<T> next = queue.poll();
            result.add(next.value);

            if (next.left != null) queue.add(next.left);
            if (next.right != null) queue.add(next.right);
        }
        return result;
    }

    public void print() throws IOException {
        PrintWriter writer = new PrintWriter(System.out);

        treePrinter = new TreePrinter( root,writer);
        treePrinter.printTree();
    }


}
