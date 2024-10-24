/****************************************************************************
 @file: BST.java
 @description: This program implements the BST Class, with all the methods of insertion, removal, and searching for Nodes.
 Additionally, it contains an Iterator Class to iterate through the BST tree in-order.
 @author: Yulanda Zheng
 @date: October 24, 2024
 ****************************************************************************/
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;

class BST<E extends Comparable<? super E>> {

    private Node<E> root;
    private int size;

    // Implement the constructor
    public BST() {
        root = null;
        size = 0;
    }

    // Implement the clear method
    public void clear() {
        this.root = null;
    }

    // Implement the size method
    public int size() {
        return size;
    }


    // Implement the insert method
    public void insert(E key) {  //calls inserthelper and increases BST size count
        root = inserthelp(root,key);
        size++;
    }

    //Helper method for the insert
    private Node<E> inserthelp(Node<E> rt, E key) {
        if (rt == null) {   //node is null, so create a new node
            return rt = new Node<>(key);
        }
        if(rt.getElement().compareTo(key) >0){ //search to the left
            rt.setLeft(inserthelp( rt.getLeft(), key));
        }
        else{
            rt.setRight(inserthelp( rt.getRight(), key)); //search to the right
        }
        return rt;
    }

    // Implement the remove method
    public E remove(E key) {
        E temp = search(key);
        if(temp != null){
            root = removehelp(root, temp);
            size--;
        }
        return temp;
    }

    //Helper method for the remove
    private Node<E> removehelp(Node<E> rt, E key) {
        if(rt == null){ return null;} //tree is empty
        else if(rt.getElement().compareTo(key) > 0){  //recursive to the left
            rt.setLeft(removehelp(rt.getLeft(), key));
        }
        else if(rt.getElement().compareTo(key) < 0){  //recursive to the right
            rt.setRight(removehelp(rt.getRight(), key));
        }
        else{ // found the Node, only one child
            if(rt.getLeft() == null){
                return rt.getRight();
            }
            else if(rt.getRight() == null){
                return rt.getLeft();
            }
            else{ //Node has two children

                rt.setElement(findMin(rt.getRight())); //call the min method to find the smallest node
                rt.setRight(removehelp(rt.getRight(), rt.getElement())); // recursively delete Node
            }
        }
        return rt;
    }

    //Method to find the min value in the subtree, used for two children removal
    private E findMin(Node<E> rt) {
        while (rt.getLeft() != null) {
            rt = rt.getLeft();
        }
        return rt.getElement();
    }

    // Implement the search method
    public E search( E key){
        return searchhelp(root, key);
    }

    private E searchhelp(Node<E> rt, E key) {
        if(rt == null){
            return null;

        }
        if(rt.getElement().compareTo(key) > 0){ //search to the left
            return searchhelp(rt.getLeft(), key);

        }
        else if(rt.getElement().compareTo(key) == 0){ //found the value
            return rt.getElement();

        }
        else return searchhelp(rt.getRight(), key); // search to the right
    }


    // Implement the iterator method
    public String print() {

        BSTIterator it = new BSTIterator();
        String s = "";

        while(it.hasNext()){
            s += (it.next() + " ");
        }

        return s;

    }

    public Node<E> getRoot() {
        return root;
    }


    // Implement the BSTIterator class for in-order iteration
    public class BSTIterator implements Iterator<E>{
        LinkedList<Node<E>> stack = new LinkedList<>();

        public BSTIterator() {
            Node<E> root = getRoot();
            pushLeft(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() {

            Node<E> node = stack.pop(); //Get next node
            pushLeft(node.getRight()); //push all the left children of the node

            return node.getElement();
        }

        //Method to push the left children of a node
        public void pushLeft(Node<E> curr){
            while(curr !=null){
                stack.push(curr);
                curr = curr.getLeft(); // push left children
            }
        }

    }



}