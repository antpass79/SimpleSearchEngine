package com.searchengine.datastructures;

public class Trie<T> implements ISearchDataStructure {
    TrieNode<T> root = new TrieNode<>();

    public Trie() {
    }

    public boolean isEmpty() {
        return this.root.getChildren().isEmpty();
    }

    public void insert(String key, T data) {
        TrieNode referenceNode;
        char firstChar = TrieKey.getFirstChar(key);
        if (this.root.getChildren().containsKey(firstChar)) {
            referenceNode = this.root.getChildren().get(firstChar);
        }
        else {
            referenceNode = new TrieNode<T>();
            this.root.getChildren().put(firstChar, referenceNode);
        }

        this.insertNode(referenceNode, TrieKey.getSubKey(key), data);
    }

    public boolean containsKey(String key) {
        char[] characters = key.toCharArray();
        TrieNode<T> currentNode = this.root;
        boolean found = true;

        for (char ch: characters) {
            if (!currentNode.getChildren().containsKey(ch)) {
                found = false;
                break;
            }
            else {
                currentNode = currentNode.getChildren().get(ch);
            }
        }
        return found && currentNode.getData() != null;
    }

    public T find(String key) throws ArrayIndexOutOfBoundsException {
        char[] characters = key.toCharArray();
        TrieNode<T> currentNode = this.root;
        boolean found = true;

        for (char ch: characters) {
            if (!currentNode.getChildren().containsKey(ch)) {
                found = false;
                break;
            }
            else {
                currentNode = currentNode.getChildren().get(ch);
            }
        }
        if (found) {
            return currentNode.getData();
        }

        throw new ArrayIndexOutOfBoundsException("key");
    }

    private void insertNode(TrieNode<T> referenceNode, String key, T data) {
        if (key.isEmpty()) {
            this.addDataToNode(referenceNode, data);
            return;
        }

        Character character = TrieKey.getFirstChar(key);
        final String subKey = TrieKey.getSubKey(key);

        if (referenceNode.getChildren().containsKey(character)) {
            TrieNode node = referenceNode.getChildren().get(character);
            if (key.length() == 1) {
                this.addDataToNode(node, data);
            }
            else {
                this.insertNode(node, subKey, data);
            }
        }
        else {
            TrieNode<T> newNode = new TrieNode<T>();
            referenceNode.getChildren().put(character, newNode);
            this.insertNode(newNode, subKey, data);
        }
    }

    private void addDataToNode(TrieNode<T> node, T data) {
        node.setData(data);
    }
}
