package main.java;

import main.java.Node;
import main.java.TreeMultiVar;

import java.io.IOException;
import java.io.Writer;

public class TreePrinter<T extends Comparable<T>> {
    private Node<T> root;
    Writer writer;

    TreePrinter(Node<T> root,Writer out) {
        this.root = root;
        this.writer=out;
    }

    public void printTree() throws IOException {
        if (root.right != null) {
            printTree(true, "", root.right);
        }
        printNodeValue(root);
        if (root.left != null) {
            printTree( false, "", root.left);
        }
        writer.flush();
    }

    private void printNodeValue( Node<T> node) throws IOException {
        if (node.value == null) {
            writer.write("<null>");
        } else {
            writer.write(node.value.toString());
        }
        writer.write('\n');
    }

    // use string and not stringbuffer on purpose as we need to change the indent at each recursion
    private void printTree( boolean isRight, String indent, Node node) throws IOException {
        if (node.right != null) {
            printTree( true, indent + (isRight ? "        " : " |      "), node.right);
        }
        writer.write(indent);
        if (isRight) {
            writer.write(" /");
        } else {
            writer.write(" \\");
        }
        writer.write("----- ");
        printNodeValue( node);
        if (node.left != null) {
            printTree( false, indent + (isRight ? " |      " : "        "), node.left);
        }
    }

}