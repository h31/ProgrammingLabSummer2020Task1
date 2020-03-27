package com.example.project;

import java.util.Objects;

public class Node {
    Integer key;
    Node parent;
    Node leftChild;
    Node rightChild;

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(!(obj instanceof Node)) return false;
        Node node = (Node) obj;
        return node.key.equals(key);
    }

    @Override
    public String toString() {
        return key.toString();
    }

    @Override
    public int hashCode(){
        return Objects.hash(key);
    }
}
