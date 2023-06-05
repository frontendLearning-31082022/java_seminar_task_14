package main.java;

class Node<T extends Comparable<T>> {
    T value;
    Node left;
    Node right;
    Node(T value) {
        this.value = value;
    }
}

