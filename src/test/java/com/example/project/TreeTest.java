package com.example.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {

    @Test
    void findNode() {
        Tree x = new Tree();
        x.insertNode(21);
        x.insertNode(32);
        x.insertNode(27);
        assertEquals(
                x.findNode(21),
                x.tree.get(0)
        );
        assertEquals(
                x.findNode(27),
                x.tree.get(2)
        );
        assertThrows(
          IllegalArgumentException.class, () -> x.findNode(0)
        );
    }

    @Test
    void insertNode() {
        Tree x = new Tree();
        x.insertNode(21);
        x.insertNode(32);
        assertTrue(x.tree.contains(x.findNode(21)));
        assertTrue(x.tree.contains(x.findNode(32)));
    }
// https://studref.com/htm/img/15/6352/107.png
    @Test
    void removeNode() {
        Tree x = new Tree();
        x.insertNode(21);
        x.insertNode(32);
        x.insertNode(27);
        x.insertNode(25);
        x.insertNode(30);
        x.insertNode(24);
        x.insertNode(37);
        x.insertNode(34);
        x.insertNode(33);
        x.insertNode(39);
        x.insertNode(7);
        x.insertNode(5);
        x.insertNode(4);
        x.insertNode(2);
        x.insertNode(6);
        x.insertNode(14);
        x.insertNode(12);
        x.insertNode(18);
        x.insertNode(9);
        x.insertNode(3);
        x.removeNode(32);
        x.removeNode(9);

        assertThrows(
                IllegalArgumentException.class, () -> x.findNode(32)
        );
        assertThrows(
                IllegalArgumentException.class, () -> x.findNode(9)
        );
        assertSame(x.findNode(33).leftChild, x.findNode(27));
        assertSame(x.findNode(33).rightChild, x.findNode(37));
        assertSame(x.findNode(33).parent, x.findNode(21));
        assertSame(x.findNode(12).leftChild, null);
    }

    @Test
    void getRightChild() {
        Tree x = new Tree();
        x.insertNode(21);
        x.insertNode(32);
        x.insertNode(27);
        x.insertNode(25);
        x.insertNode(30);
        x.insertNode(24);
        x.insertNode(37);
        x.insertNode(34);
        x.insertNode(33);
        x.insertNode(39);
        x.insertNode(7);
        x.insertNode(5);
        x.insertNode(4);
        x.insertNode(2);
        x.insertNode(6);
        x.insertNode(14);
        x.insertNode(12);
        x.insertNode(18);
        x.insertNode(9);
        x.insertNode(3);

        assertSame(x.findNode(33), x.getLeftChild(34));
        assertNotSame(x.findNode(25), x.getLeftChild(32));
        assertSame(x.findNode(37), x.getRightChild(32));
        assertNotSame(x.findNode(7), x.getRightChild(32));
        assertSame(x.findNode(32), x.getParent(27));
        assertNotSame(x.findNode(27), x.getParent(24));
    }

    @Test
    void getLeftChild() {
        //Написал выше
    }

    @Test
    void getParent() {
        //Написал выше
    }
}