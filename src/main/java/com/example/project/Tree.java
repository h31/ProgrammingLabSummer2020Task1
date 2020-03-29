package com.example.project;

import java.util.ArrayList;
import java.util.Objects;

public class Tree {

    private ArrayList<Node> tree = new ArrayList<>();
    private Node root;

    public boolean contains(int key) {// Оставил public для теста
        Node unit = new Node();// Я могу оставить unit или стоит назвать node?
        unit.key = key;
        return tree.contains(unit);
    }

    public Node findNode(int key) {
        if (tree.isEmpty() || !contains(key)) throw new IllegalArgumentException();
        Node unit = root;
        while (unit != null) {
            if (unit.key == key) return unit;
            unit = unit.key < key ? (unit.rightChild): unit.leftChild;
        }
        return null;
    }

    public void insertNode(int key) {
        if (contains(key)) throw new IllegalArgumentException();

        Node unit = new Node();
        unit.key = key;

        if (tree.isEmpty()){
            tree.add(unit);
            root = unit;
        }
        else {
            Node parent = root;
            while (true) {
                if (unit.key < parent.key) {
                    if (parent.leftChild == null) {
                        unit.parent = parent;
                        parent.leftChild = unit;
                        tree.add(unit);
                        break;
                    }
                    parent = parent.leftChild;
                }
                if (unit.key > parent.key) {
                    if (parent.rightChild == null) {
                        unit.parent = parent;
                        parent.rightChild = unit;
                        tree.add(unit);
                        break;
                    }
                    parent = parent.rightChild;
                }
            }
        }
    }

    public void removeNode(int key) {
        if (!contains(key)) throw new IllegalArgumentException();

        Node unit = findNode(key);
        // Ни одного потомка
        if (unit.rightChild == null && unit.leftChild == null) {
            changeChild(unit, null);
        }//1 левый потомок
        else if (unit.rightChild == null) {
            changeChild(unit, unit.leftChild);
            unit.leftChild.parent = unit.parent;
        }//1 правый потомок
        else if (unit.leftChild == null){
            changeChild(unit, unit.rightChild);
            unit.rightChild.parent = unit.parent;
        } else {// оба потомка 1 случай
            if (unit.rightChild.leftChild == null) {
                unit.rightChild.leftChild = unit.leftChild;
                unit.rightChild.parent = unit.parent;
                unit.leftChild.parent = unit.rightChild;
                changeChild(unit, unit.rightChild);
            } else {//оба потомка 2 случай
                Node leftEdge = unit.rightChild;
                while (leftEdge.leftChild != null) leftEdge = leftEdge.leftChild;
                leftEdge.parent.leftChild = null;
                leftEdge.parent = unit.parent;
                leftEdge.leftChild = unit.leftChild;
                leftEdge.rightChild = unit.rightChild;
                unit.leftChild.parent = leftEdge;
                unit.rightChild.parent = leftEdge;
                changeChild(unit, leftEdge);
            }
        }
        tree.remove(unit);
    }

    private void changeChild(Node unit, Node newValue) {
        if (!unit.equals(root)) {
            if (unit.parent.leftChild == unit) unit.parent.leftChild = newValue;
            else unit.parent.rightChild = newValue;
        } else {
            root = newValue;
        }
    }

    public Node getInfo(int key, String who) {// Лучше оставить так или созадть 3 отдельных get'а?
        Node unit = findNode(key);
        switch (who.toLowerCase()) {
            case "parent":
                return unit.parent;
            case "rightchild":
                return unit.rightChild;
            case "leftchild":
                return unit.leftChild;
            case "root":
                return root;
            default: throw new IllegalArgumentException();
        }
    }

//    private Node getRightChild(int key){
//
//        return findNode(key).rightChild;
//    }
//
//    private Node getLeftChild(int key){
//        return findNode(key).leftChild;
//    }
//
//    private Node getParent(int key){
//        return findNode(key).parent;
//    }
//
//    public void info(int key) {
//        Node a = findNode(key);
//        System.out.print(a.parent + " ");
//        System.out.print(a.leftChild + " ");
//        System.out.println(a.rightChild + " ");
//    }

    public Node getNodeByIndex(int index) {
        return tree.get(index);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if(!(obj instanceof Tree)) return false;
        Tree oTree = (Tree) obj;
        if(oTree.tree.size() != tree.size()) return false;

        try {
        for (Node unit: this.tree) {
            if (!oTree.findNode(unit.key).equals(unit)) return false;
        }
        return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return tree.size() + tree.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(tree.size());
    }
}