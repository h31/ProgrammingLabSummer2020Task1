package com.example.project;

import java.util.Objects;

public class Node {
    Integer key;
    Node parent;
    Node leftChild;
    Node rightChild;

    Node(int keyValue) {
        this.key = keyValue;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        Node node = (Node) obj;
        return node.key.equals(this.key);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" key = ").append(this.key);
        if (this.parent != null) sb.append(" parent ").append(this.parent.key);
        if (this.leftChild != null) sb.append(" leftChild ").append(this.leftChild.key);
        if (this.rightChild != null) sb.append(" rightChild ").append(this.rightChild.key);
        return sb.toString();
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.key * ((this.parent != null) ? this.parent.key : 1)  *
                ((this.leftChild != null) ? this.leftChild.key : 1) *
                ((this.rightChild != null) ? this.rightChild.key : 1));
    }
}
