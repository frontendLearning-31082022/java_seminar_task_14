package test.java;

import main.java.TreeMultiVar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class TreeMultiVar_StringTest {

    @Test
    void add() {
        TreeMultiVar tree = new TreeMultiVar();

        Assertions.assertTrue(tree.add("Orange"));
        Assertions.assertTrue(tree.add("Apple"));
        Assertions.assertTrue(tree.add("Grass"));
        Assertions.assertTrue(tree.add("Banana"));

        Assertions.assertTrue(tree.add("Potato"));
        Assertions.assertFalse(tree.add("Potato"));
        Assertions.assertTrue(tree.add("Berries"));
        Assertions.assertTrue(tree.add("Chocolate"));
    }

    @Test
    void contains() {
        TreeMultiVar tree = new TreeMultiVar();

        tree.add("Orange");
        tree.add("Apple");
        tree.add("Grass");
        tree.add("Banana");
        tree.add("Potato");
        tree.add("Berries");
        tree.add("Chocolate");


        Assertions.assertTrue(tree.contains("Orange")); // true
        Assertions.assertTrue(tree.contains("Apple")); // true
        Assertions.assertTrue(tree.contains("Grass")); // true
        Assertions.assertTrue(tree.contains("Banana")); // true

        Assertions.assertFalse(tree.contains("Marshmallow")); // false
        Assertions.assertFalse(tree.contains("Worms")); // false
        Assertions.assertFalse(tree.contains("Pythons"));
    }

    @Test
    void dfs() {
        TreeMultiVar tree = new TreeMultiVar();

        tree.add("Orange");
        tree.add("Apple");
        tree.add("Grass");
        tree.add("Banana");
        tree.add("Potato");
        tree.add("Berries");
        tree.add("Chocolate");

        Assertions.assertEquals("[Apple, Banana, Berries, Chocolate, Grass, Orange, Potato]", tree.dfs().toString());
    }

    @Test
    void bfs() {
        TreeMultiVar tree = new TreeMultiVar();

        tree.add("Orange");
        tree.add("Apple");
        tree.add("Grass");
        tree.add("Banana");
        tree.add("Potato");
        tree.add("Berries");
        tree.add("Chocolate");

        Assertions.assertEquals("[Orange, Apple, Potato, Grass, Banana, Berries, Chocolate]", tree.bfs().toString());
    }

    @Test
    void print() throws IOException {
        TreeMultiVar tree = new TreeMultiVar();
        tree.add("Orange");
        tree.add("Apple");
        tree.add("Grass");
        tree.add("Banana");
        tree.add("Potato");
        tree.add("Berries");
        tree.add("Chocolate");

        tree.print();
    }

}