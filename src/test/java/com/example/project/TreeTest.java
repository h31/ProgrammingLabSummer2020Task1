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
        assertSame(x.findNode(21), x.tree.get(0));
        assertSame(x.findNode(27), x.tree.get(2));
        assertSame(x.findNode(32), x.tree.get(1));
        assertThrows(IllegalArgumentException.class, () -> x.findNode(0));
    }

    @Test
    void insertNode() {
        Tree x = new Tree();
        x.insertNode(21);
        x.insertNode(32);
        assertTrue(x.tree.contains(x.findNode(21)));
        assertTrue(x.tree.contains(x.findNode(32)));
        //Дерево не содержит одно и то же число более 1 раза
        assertThrows(IllegalArgumentException.class, () -> x.insertNode(21));
    }

    // https://studref.com/htm/img/15/6352/107.png - это дерево, которое находится ниже

    @Test
    void getInfo() {
        Tree x = new Tree();
        x.insertNode(21);
        x.insertNode(32);
        x.insertNode(7);
        assertSame(x.findNode(21).leftChild, x.getInfo(21, "leftChild"));
        assertSame(x.findNode(21).rightChild, x.getInfo(21, "rightChild"));
        assertSame(x.findNode(7).parent, x.getInfo(7, "parent"));
        assertThrows(IllegalArgumentException.class, () -> x.getInfo(0, "parent"));
    }

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
        assertSame(x.getInfo(25, "leftChild"), null);

        x.removeNode(12);//У удаляемого узла есть только левый потомок
        assertThrows(IllegalArgumentException.class, () -> x.findNode(12));
        assertSame(x.getInfo(14, "leftChild").key, 9);
        assertSame(x.getInfo(9, "parent").key, 14);

        x.removeNode(2);//У удаляемого узла есть только правый потомок(На картинке нет, но я добавил 3)
        assertThrows(IllegalArgumentException.class, () -> x.findNode(2));
        assertSame(x.getInfo(3, "parent").key, 4);
        assertSame(x.getInfo(4, "leftChild").key, 3);

        x.removeNode(27);//Есть оба потомка, 1 случай
        assertThrows(IllegalArgumentException.class, () -> x.findNode(27));
        assertSame(x.getInfo(32, "leftChild").key, 30);
        assertSame(x.getInfo(30, "parent").key, 32);
        assertSame(x.getInfo(25, "parent").key, 30);
        assertSame(x.getInfo(30, "leftChild").key, 25);

        x.removeNode(32);//Есть оба потомка, 2 случай(Вместо 27 теперь стоит 30)
        assertThrows(IllegalArgumentException.class, () -> x.findNode(32));
        assertSame(x.getInfo(21, "rightChild").key, 33);
        assertSame(x.getInfo(33, "parent").key, 21);
        assertSame(x.getInfo(30, "parent").key, 33);
        assertSame(x.getInfo(33, "leftChild").key, 30);
        assertSame(x.getInfo(37, "parent").key, 33);
        assertSame(x.getInfo(33, "rightChild").key, 37);
        assertSame(x.getInfo(34, "leftChild"), null);
    }
}