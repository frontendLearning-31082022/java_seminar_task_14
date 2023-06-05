package test.java;

import main.java.TreeMultiVar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class TreeMultiVar_IntegersTest {

    @Test
    void add() {
        TreeMultiVar tree = new TreeMultiVar();

        Assertions.assertTrue(tree.add(8));
        Assertions.assertTrue(tree.add(2));
        Assertions.assertTrue(tree.add(3));
        Assertions.assertTrue(tree.add(12));

        Assertions.assertFalse(tree.add(12));
        Assertions.assertTrue(tree.add(1));
        Assertions.assertTrue(tree.add(14));
    }

    @Test
    void contains() {
        TreeMultiVar tree = new TreeMultiVar();

        Assertions.assertTrue(tree.add(8));
        Assertions.assertTrue(tree.add(3));
        Assertions.assertTrue(tree.add(12));

        Assertions.assertFalse(tree.add(12));
        Assertions.assertTrue(tree.add(1));

        Assertions.assertTrue(tree.contains(8)); // true
        Assertions.assertTrue(tree.contains(1)); // true
        Assertions.assertTrue(tree.contains(3)); // true
        Assertions.assertTrue(tree.contains(12)); // true

        Assertions.assertFalse(tree.contains(4)); // false
        Assertions.assertFalse(tree.contains(2)); // false
        Assertions.assertFalse(tree.contains(14));
    }

    @Test
    void dfs() {
        TreeMultiVar tree = new TreeMultiVar();
        tree.add(8);
        tree.add(2);
        tree.add(3);
        tree.add(12);
        tree.add(12);
        tree.add(1);
        tree.add(14);

        Assertions.assertEquals("[1, 2, 3, 8, 12, 14]", tree.dfs().toString());
    }

    @Test
    void bfs() {
        TreeMultiVar tree = new TreeMultiVar();
        tree.add(8);
        tree.add(2);
        tree.add(3);
        tree.add(12);
        tree.add(12);
        tree.add(1);
        tree.add(14);

        Assertions.assertEquals("[8, 2, 12, 1, 3, 14]", tree.bfs().toString());
    }
    @Test
    void remove() {
        TreeMultiVar tree = new TreeMultiVar();
        tree.add(8);
        tree.add(2);
        tree.add(3);

        tree.remove(2);
        Assertions.assertFalse(tree.contains(2));

    }
    @Test
    void findFirst() {
        TreeMultiVar tree = new TreeMultiVar();
        tree.add(8);
        tree.add(2);
        tree.add(16);
        tree.add(200);
        tree.add(162);
        tree.add(1116);
        tree.add(3);

        Assertions.assertTrue(tree.findFirst().equals(2));
    }



    @Test
    void print() throws IOException {
        TreeMultiVar tree = new TreeMultiVar();
        tree.add(8);
        tree.add(2);
        tree.add(3);
        tree.add(12);
        tree.add(12);
        tree.add(1);
        tree.add(14);

        tree.print();
    }

}