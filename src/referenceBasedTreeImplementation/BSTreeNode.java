package referenceBasedTreeImplementation;

import java.io.Serializable;

/**
 * ******************************************** **
 * BSTreeNode - referenceBasedTreeImplementation.BSTreeNode
 * Implementation of a Binary Search Tree node to be used in a BinarySearchTree
 * @author Christian Garrovillo
 * Information and Communications Technologies
 * Software Development
 * * ********************************************* **
 */
public class BSTreeNode<E> implements Serializable {
    private static final long serialVersionUID = -1150412773856693111L;

    private E element;
    private BSTreeNode<E> leftChild;
    private BSTreeNode<E> rightChild;

    /**
     * Constructor for the node
     * @param e the element this node will hold
     */
    public BSTreeNode(E e) {
        this.element = e;
        leftChild = null;
        rightChild = null;
    }

    /**
     * Constructor for the node
     * @param e the element this node will hold
     * @param left the left child of this node
     * @param right the right child of this node
     */
    public BSTreeNode(E e, BSTreeNode left, BSTreeNode right) {
        setElement(e);
        setLeftChild(left);
        setRightChild(right);

    }

    /**
     * Returns true if this node does not have any children
     * @return true if this node does not have any children
     */
    public boolean isLeaf() {
        boolean leaf = false;
        if (!hasLeftchild())
            leaf = true;
        if (!hasRightChild())
            leaf = true;

        return leaf;
    }

    /**
     * Returns true if this node has a left child
     * @return true if this node has a left child
     */
    public boolean hasLeftchild() {
        return this.getLeftChild() != null;
    }

    /**
     * Returns true if this node has a right child
     * @return true if this node has a right child
     */
    public boolean hasRightChild() {
        return this.getRightChild() != null;
    }

    /**
     * Returns the element that this node holds
     * @return the element that this node holds
     */
    public E getElement() {
        return element;
    }

    /**
     * Sets the element that this node holds
     * @param element the element that this node will hold
     */
    public void setElement(E element) {
        this.element = element;
    }

    /**
     * Returns the right child of this node
     * @return the right child of this node
     */
    public BSTreeNode<E> getRightChild() {
        return rightChild;
    }

    /**
     * Sets the right child of this node
     * @param rightChild the node that will be the right child of this node
     */
    public void setRightChild(BSTreeNode<E> rightChild) {
        this.rightChild = rightChild;
    }

    /**
     * Returns the left child of this node
     * @return the left child of this node
     */
    public BSTreeNode<E> getLeftChild() {
        return leftChild;
    }

    /**
     * Sets the left child of this node
     * @param leftChild the node that will be the right child of this node
     */
    public void setLeftChild(BSTreeNode<E> leftChild) {
        this.leftChild = leftChild;
    }

}
