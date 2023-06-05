package main.java.task_description;

import java.util.*;

public class Tree /* <T extends Comparable<T>> */ {

    // Нужно реализовать структуру, которая умеет делать следующее:
    // 1. Вставка значения
    // 2. Поиск значения (проверка, есть значение внутри или нет)
    // 3. Удаление значения
    // DFS Depth-first-search (поиск в глубину)
    // BFS Breath-first-search (поиск в ширину)

    // FIXME: 29.05.2023 Домашнее задание
    //  Сделать класс Node параметризованным типом T
    //  Переписать все упоминания int value на T value
    //  Заменить все проверки > < == на вызовы compareTo


    private class Node{
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    private Node root;

    public boolean add(int value) {
        if (root == null) {
            root = new Node(value);
            return true;
        }

        return addNode(root, value);
    }

    public boolean addNode(Node current, int value) {
        // value < current.value ~ value.compareTo(current.value) == 0
        if (value == current.value) {
            return false;

        // value < current.value ~ value.compareTo(current.value) < 0
        } else if (value < current.value) {
            // Вставялем в левое поддерево
            if (current.left == null) {
                current.left = new Node(value);
                return true;
            } else {
                return addNode(current.left, value);
            }
            // value > current.value ~ value.compareTo(current.value) > 0
        } else { // value > root.value
            // Вставляем в правое поддерево
            if (current.right == null) {
                current.right = new Node(value);
                return true;
            } else {
                return addNode(current.right, value);
            }
        }
    }

    public boolean contains(int value) {
        return findNode(root, value) != null;
    }

    public Node findNode(Node current, int value) {
        if (current == null) {
            return null;
        }

        // найди узел в дереве current, значение которого равно value
        if (value == current.value) {
            return current;
        } else if (value < current.value) {
            return findNode(current.left, value);
        } else { // value > current.value
            return findNode(current.right, value);
        }
    }

    public void remove(int value) {
        root = removeNode(root, value);
    }

    public Node removeNode(Node current, int value) {
        if (current == null) {
            return null;
        }

        // value = 8, current = 6
        // 6.right = 7

        // value = 8, current = 8
        if (value < current.value) {
            // нужно удалить в левом поддереве
            current.left = removeNode(current.left, value);
            return current;
        } else if (value > current.value) {
            // нужно удалить в правом поддереве
            current.right = removeNode(current.right, value);
            return current;
        }

        // Нужно удалить узел current
        // Есть 3 случая:
        // 1. Дочерних узлов нет
        if (current.left == null && current.right == null) {
            return null;
        }

        // 2. Есть только один дочерний узел
        if (current.left == null && current.right != null) {
            return current.right;
        }
        if (current.left != null && current.right == null) {
            return current.left;
        }

        // 3. Есть оба дочерних узла
        // Нужно найти минимальный элемент справа
        Node smallestNodeOnTheRight = findFirst(current.right);
        int smallestValueOnTheRight = smallestNodeOnTheRight.value;
        current.value = smallestValueOnTheRight;
        current.right = removeNode(current.right, smallestValueOnTheRight);
        return current;
    }

    public int findFirst() {
        if (root == null) {
            throw new NoSuchElementException();
        }
        return findFirst(root).value;
    }

    public Node findFirst(Node current) {
        if (current.left == null) {
            return current;
        }
        return findFirst(current.left);
    }

    public List<Integer> dfs() {
        if (root == null) {
            return List.of();
        }

        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        return list;
    }

    public void dfs(Node current, List<Integer> result) {
        // In-order
        if (current.left != null) {
            dfs(current.left, result);
        }
        result.add(current.value);
        if (current.right != null) {
            dfs(current.right, result);
        }
    }

    public List<Integer> bfs() {
        if (root == null) {
            return List.of();
        }

        List<Integer> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node next = queue.poll();
            result.add(next.value);

            if (next.left != null) {
                queue.add(next.left);
            }
            if (next.right != null) {
                queue.add(next.right);
            }
        }
        return result;
    }

}
