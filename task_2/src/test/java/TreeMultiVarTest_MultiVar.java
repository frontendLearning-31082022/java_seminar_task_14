package test.java;

import main.java.TreeMultiVar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class TreeMultiVarTest_MultiVar {

    @Test
    void add() {
        TreeMultiVar tree = new TreeMultiVar();

        Assertions.assertTrue(tree.add(8));
        Assertions.assertTrue(tree.add("Test"));
        Assertions.assertTrue(tree.add(3));
        Assertions.assertTrue(tree.add("None"));

        Assertions.assertTrue(tree.add(12));
        Assertions.assertFalse(tree.add("None"));
        Assertions.assertTrue(tree.add(14));
    }

    @Test
    void contains() {
        TreeMultiVar tree = new TreeMultiVar();

        tree.add(8);
        tree.add("Test");

        Assertions.assertTrue(tree.contains(8));
        Assertions.assertTrue(tree.contains("Test"));
        Assertions.assertFalse(tree.contains(3));
    }

    @Test
    void dfs() {
        TreeMultiVar tree = new TreeMultiVar();
        tree.add(8);
        tree.add("Test");
        tree.add("Apple");
        tree.add(3);
        tree.add("None");

        tree.add(12);
        tree.add("None");
        tree.add(14);
        tree.add(11);

        Assertions.assertEquals("[3, 8, 11, 12, 14, Apple, None, Test]", tree.dfs().toString());
    }

    @Test
    void bfs() {
        TreeMultiVar tree = new TreeMultiVar();
        tree.add(8);
        tree.add("Test");
        tree.add("Apple");
        tree.add(3);
        tree.add("None");

        tree.add(12);
        tree.add("None");
        tree.add(14);
        tree.add(11);


        Assertions.assertEquals("[8, 3, Test, Apple, 12, None, 11, 14]", tree.bfs().toString());
    }

    @Test
    void print() throws IOException {
        TreeMultiVar tree = new TreeMultiVar();
        tree.add(8);
        tree.add("Test");
        tree.add("Apple");
        tree.add(3);
        tree.add("None");

        tree.add(12);
        tree.add("None");
        tree.add(14);
        tree.add(11);


        tree.print();
    }

}