package com.searchengine.datastructures;

public class Trie<T> extends TrieNode<T> implements ISearchDataStructure {

    public Trie() {
    }

    public boolean isEmpty() {
        return this.getChildren().isEmpty();
    }

    public void insert(String key, T data) {
        TrieNode<T> referenceNode;
        char firstChar = TrieKey.getFirstChar(key);
        if (this.getChildren().containsKey(firstChar)) {
            referenceNode = this.getChildren().get(firstChar);
        }
        else {
            referenceNode = new TrieNode<T>();
            this.getChildren().put(firstChar, referenceNode);
        }

        this.insertNode(referenceNode, TrieKey.getSubKey(key), data);
    }

    public boolean containsKey(String key) {
        char[] characters = key.toCharArray();
        TrieNode<T> currentNode = this;
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
        return found && currentNode.getChildren().isEmpty();
    }

    public T find(String key) throws ArrayIndexOutOfBoundsException {
        char[] characters = key.toCharArray();
        TrieNode<T> currentNode = this;
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
        if (key.isEmpty() || key.isBlank()) {
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
