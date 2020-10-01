package com.example.project;

import java.util.Objects;

public class Tree {

    private Node root;

    public boolean contains(int key) {
        return containsR(root, key);
    }

    private boolean containsR(Node node, int key) {
        if (node == null) return false;
        if (node.key == key) return true;
        return key < node.key
                ?containsR(node.leftChild, key)
                :containsR(node.rightChild, key);
    }

    public Node findNode(int key) {
        if(!contains(key)) throw new IllegalArgumentException();
        return findNodeR(root, key);
    }

    private Node findNodeR(Node node, int key) {
        if (node.key == key) return node;
        return key < node.key
                ?findNodeR(node.leftChild, key)
                :findNodeR(node.rightChild, key);
    }


    public void insertNode(int key) {
        if (contains(key)) throw new IllegalArgumentException();

        Node unit = new Node(key);

        if (root == null){
            root = unit;
        }
        else {
            Node parent = root;
            while (true) {
                if (unit.key < parent.key) {
                    if (parent.leftChild == null) {
                        unit.parent = parent;
                        parent.leftChild = unit;
                        break;
                    }
                    parent = parent.leftChild;
                }
                if (unit.key > parent.key) {
                    if (parent.rightChild == null) {
                        unit.parent = parent;
                        parent.rightChild = unit;
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
                if (leftEdge.rightChild != null) {
                    leftEdge.rightChild.parent = leftEdge.parent;
                    changeChild(leftEdge, leftEdge.rightChild);
                } else {
                    leftEdge.parent.leftChild = null;
                }
                leftEdge.parent = unit.parent;
                leftEdge.leftChild = unit.leftChild;
                leftEdge.rightChild = unit.rightChild;
                unit.leftChild.parent = leftEdge;
                unit.rightChild.parent = leftEdge;
                changeChild(unit, leftEdge);
            }
        }
    }

    /**
     * Меняет потомка у unit.parent
     * @param unit Предыдущий потомок
     * @param newValue Новый потомок
     */
    private void changeChild(Node unit, Node newValue) {
        if (!unit.equals(root)) {
            if (unit.parent.leftChild == unit) unit.parent.leftChild = newValue;
            else unit.parent.rightChild = newValue;
        } else {
            root = newValue;
        }
    }

    public Node getLeftChild(int key){
        return findNode(key).leftChild;
    }

    public Node getRightChild(int key){
        return findNode(key).rightChild;
    }

    public Node getParent(int key){
        return findNode(key).parent;
    }

    public Node getRoot(){
        return this.root;
    }

    public String getTree() {
        StringBuilder sb = new StringBuilder();
        return getTree(root, sb);
    }

    private String getTree(Node node, StringBuilder sb) {
        if (node != null) {
            sb.append(node.key).append(" ");
            getTree(node.leftChild, sb);
            getTree(node.rightChild, sb);
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Tree oTree = (Tree) obj;
        return oTree.getTree().equals(this.getTree());
    }

    @Override
    public String toString() {
        return this.getTree();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.root.key * this.getTree().length());
    }
}