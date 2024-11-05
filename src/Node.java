/****************************************************************************
 @file: Node.java
 @description: This program implements the Node class
 @author: Yulanda Zheng
 @date: October 24, 2024
 ****************************************************************************/

public class Node<E> {
    private E element;
    private Node<E> right;
    private Node<E> left;

    // Implement the constructor
    public Node(E element) {
        this.element = element;
        this.right = null;
        this.left = null;
    }

    //Implement the getElement method
    public E getElement() {
        return element;
    }

    // Implement the setElement method
    public void setElement(E element) {
        this.element = element;
    }

    // Implement the setLeft method
    public void setLeft(Node<E> left) {
        this.left = left;
    }

    // Implement the setRight method
    public void setRight(Node<E> right) {
        this.right = right;
    }

    // implement getRight method
    public Node<E> getRight(){
        return this.right;
    }

    //implement getLeft method
    public Node<E> getLeft(){
        return this.left;
    }

    // Implement the isLeaf method
    public boolean isLeaf(){
        return this.left == null && this.right == null;
    }
}