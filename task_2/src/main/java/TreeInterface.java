package main.java;

import main.java.Node;

import java.util.List;

public interface TreeInterface {
    boolean add(int value);

    boolean addNode(Node current, int value);

    boolean contains(int value);

    Node findNode(Node current, int value);

    void remove(int value);

    Node removeNode(Node current, int value);

    int findFirst();

    Node findFirst(Node current);

    List<Integer> dfs();

    void dfs(Node current, List<Integer> result);

    List<Integer> bfs();
}
