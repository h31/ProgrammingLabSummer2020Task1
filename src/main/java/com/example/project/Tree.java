package com.example.project;

import java.util.ArrayList;
import java.util.Objects;

public class Tree {

    public ArrayList<Node> tree = new ArrayList<>();

    private boolean contains(int key) {
        Node node = new Node();
        node.key = key;
        return tree.contains(node);
    }

    public Node findNode(int key) {
        Node unit = tree.get(0);
        while (unit != null) {
            if (unit.key == key) return unit;
            unit = unit.key < key ? (unit.rightChild): unit.leftChild;
        }
        throw new IllegalArgumentException("");
    }

    public void insertNode(int key) {
        if (contains(key)) throw new IllegalArgumentException("");

        Node unit = new Node();
        unit.key = key;

        if (tree.isEmpty()) tree.add(unit);
        else {
            Node parent = tree.get(0);
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

    public void removeNode(int key){
        if (!contains(key)) throw new IllegalArgumentException("");

        Node unit = findNode(key);
        // Ни одного потомка
        if (unit.rightChild == null && unit.leftChild == null) {
            if (unit.parent.leftChild == unit) unit.parent.leftChild = null;
            else unit.parent.rightChild = null;
        }//1 левый потомок
        else if (unit.rightChild == null) {
            findChild(unit, unit.leftChild);
            unit.leftChild.parent = unit.parent;
        }//1 правый потомок
        else if (unit.leftChild == null){
            findChild(unit, unit.rightChild);
            unit.rightChild.parent = unit.parent;
        } else {// оба потомка 1 случай
            if (unit.rightChild.leftChild == null) {
                unit.rightChild.leftChild = unit.leftChild;
                unit.rightChild.parent = unit.parent;
                unit.leftChild.parent = unit.rightChild;
                findChild(unit, unit.rightChild);
            } else {//оба потомка 2 случай
                Node leftEdge = unit.rightChild;
                while (leftEdge.leftChild != null) leftEdge = leftEdge.leftChild;
                leftEdge.parent.leftChild = null;
                leftEdge.parent = unit.parent;
                leftEdge.leftChild = unit.leftChild;
                leftEdge.rightChild = unit.rightChild;
                unit.leftChild.parent = leftEdge;
                unit.rightChild.parent = leftEdge;
                findChild(unit, leftEdge);
            }
        }
        tree.remove(unit);
    }

    private void findChild(Node unit, Node newValue){
        if (unit.parent.leftChild == unit) unit.parent.leftChild = newValue;
        else unit.parent.rightChild = newValue;
    }

    public Node getRightChild(int key){

        return findNode(key).rightChild;
    }

    public Node getLeftChild(int key){
        return findNode(key).leftChild;
    }

    public Node getParent(int key){
        return findNode(key).parent;
    }

    public void info(int key){
        Node a = findNode(key);
        System.out.print(a.parent + " ");
        System.out.print(a.leftChild + " ");
        System.out.println(a.rightChild + " ");
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        Tree oTree = (Tree) obj;
        for (Node unit: this.tree) {
            if (!oTree.findNode(unit.key).equals(unit)) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Size = " + tree.size() + " elements: " + tree.toString();
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.tree.get(0).key);
    }

}


