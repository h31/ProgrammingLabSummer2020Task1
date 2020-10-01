package com.example.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {

    @Test
    void findNode() {
        Tree x = new Tree();
        assertThrows(IllegalArgumentException.class, () -> x.findNode(0));
        x.insertNode(21);
        x.insertNode(32);
        x.insertNode(27);
        assertSame(x.findNode(21), x.getRoot());
        assertSame(x.findNode(32), x.getRoot().rightChild);
        assertSame(x.findNode(27), x.getRoot().rightChild.leftChild);
    }

    @Test
    void insertNode() {
        Tree x = new Tree();
        x.insertNode(21);
        x.insertNode(32);
        assertTrue(x.contains(21));
        assertTrue(x.contains(32));
        //Дерево не содержит одно и то же число
        assertThrows(IllegalArgumentException.class, () -> x.insertNode(21));
    }

    // https://studref.com/htm/img/15/6352/107.png - это дерево, которое находится ниже

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
        x.insertNode(3);// правый потомок 2

        x.removeNode(24);//У удаляемого узла нет потомков
        assertThrows(IllegalArgumentException.class, () -> x.findNode(24));
        assertSame(x.getLeftChild(25), null);

        x.removeNode(12);//У удаляемого узла есть только левый потомок
        assertThrows(IllegalArgumentException.class, () -> x.findNode(12));
        assertSame(x.getLeftChild(14).key, 9);
        assertSame(x.getParent(9).key, 14);

        x.removeNode(2);//У удаляемого узла есть только правый потомок
        assertThrows(IllegalArgumentException.class, () -> x.findNode(2));
        assertSame(x.getParent(3).key, 4);
        assertSame(x.getLeftChild(4).key, 3);

        x.removeNode(27);//Есть оба потомка, 1 случай
        assertThrows(IllegalArgumentException.class, () -> x.findNode(27));
        assertSame(x.getLeftChild(32).key, 30);
        assertSame(x.getParent(30).key, 32);
        assertSame(x.getParent(25).key, 30);
        assertSame(x.getLeftChild(30).key, 25);

        x.removeNode(32);//Есть оба потомка, 2 случай(Вместо 27 теперь стоит 30)
        assertThrows(IllegalArgumentException.class, () -> x.findNode(32));
        assertSame(x.getRightChild(21).key, 33);
        assertSame(x.getParent(33).key, 21);
        assertSame(x.getParent(30).key, 33);
        assertSame(x.getLeftChild(33).key, 30);
        assertSame(x.getParent(37).key, 33);
        assertSame(x.getRightChild(33).key, 37);
        assertSame(x.getLeftChild(34), null);
    }
}