package com.example.project;

import java.util.*;

public class Trie {
    private static class Node {
        char symbol;
        Map<Character, Node> children;
        boolean isWord;
        Node parent;

        Node(char symbol, Node parent) {
            this.symbol = symbol;
            children = new HashMap<>();
            this.parent = parent;
        }
    }

    private final Node root;

    public Trie() {
        root = new Node('\0', null);
    }

    public boolean addWord(String word) {
        boolean added = false;
        Node node = root;
        for (char ch : word.toLowerCase().toCharArray()) {
            if (!node.children.containsKey(ch)) {
                node.children.put(ch, new Node(ch, node));
                added = true;
            }
            node = node.children.get(ch);
        }
        node.isWord = true;
        return added;
    }

    private Node findNode(String word) {
        Node node = root;
        for (char ch : word.toLowerCase().toCharArray()) {
            if (!node.children.containsKey(ch)) {
                return null;
            }
            node = node.children.get(ch);
        }
        return node;
    }

    public void deleteWord(String word) {
        Node node = findNode(word);
        if (node != null) {
            Node parent = node.parent;
            node.isWord = false;
            while (node.children.size() == 0 && !node.isWord) {
                parent.children.remove(node.symbol, node);
                node = parent;
                parent = node.parent;
            }
        }
    }

    public boolean findWord(String word) {
        Node found = findNode(word);
        return found != null && found.isWord;
    }

    private List<String> depthFirstSearch(Node node, StringBuilder prefix, List<String> used, List<String> words) {
        used.add(prefix.toString());
        if (node.isWord) {
            words.add(prefix.toString());
        }
        for (Node next : node.children.values()) {
            prefix.append(next.symbol);
            if (!used.contains(prefix.toString())) {
                depthFirstSearch(next, prefix, used, words);
            }
            prefix.deleteCharAt(prefix.length() - 1);
        }
        return words;
    }

    private List<String> getWordsByPrefix(Node prefixNode) {
        Node node = prefixNode;
        StringBuilder prefix = new StringBuilder();
        while (node.parent != null) {
            prefix.insert(0, node.symbol);
            node = node.parent;
        }
        return depthFirstSearch(prefixNode, prefix, new ArrayList<>(), new ArrayList<>());
    }

    public List<String> findByPrefix(String word) {
        Node node = findNode(word);
        if (node == null) {
            return Collections.emptyList();
        }
        return getWordsByPrefix(node);
    }

    @Override
    public String toString() {
        return getWordsByPrefix(root).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trie trie = (Trie) o;
        return getWordsByPrefix(this.root).equals(getWordsByPrefix(trie.root));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWordsByPrefix(root));
    }
}