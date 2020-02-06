package utility;

import exceptions.TreeException;

import java.io.Serializable;

/**
 * ******************************************** **
 * CGBSTreeADT - utility.CGBSTreeADT
 * Implementation of a Binary Search Tree Interface for CPRG-311
 * @author Christian Garrovillo
 * Information and Communications Technologies
 * Software Development
 * <p>
 * * ********************************************* **
 */
public interface CGBSTreeADT<E> extends Serializable {

    /**
     * Returns the root node of the binary search tree, which is the node at height level 1.
     * @return the root node of the binary search tree
     */
    public E getRoot() throws TreeException;

    /**
     * Returns the height of the binary search tree.
     * @return the height of the binary search tree
     */
    public int getHeight();

    /**
     * Returns the node count of the binary search tree.
     * @return the amount of nodes in the binary search tree
     */
    public int size();

    /**
     * Returns true if the binary search tree has no root node.
     * @return true if the binary search tree has no root node.
     */
    public boolean isEmpty();

    /**
     * Removes all nodes from the binary search tree. This binary search tree will be empty
     * after this call returns.
     */
    public void clear();

    /**
     * Returns true if and only if a specified element is contained in the binary search tree at least once.
     * @param e The element whose existence is to be tested in the binary search tree
     * @return true if the binary search tree contains the element specified
     * @throws NullPointerException
     *          If the specified element is null and the tree implementation does not
     *          support having null elements.
     */
    public boolean contains(E e) throws NullPointerException;

    /**
     * Adds a specified element to the binary search tree in an appropriate spot
     * in order to maintain the tree's integrity.
     * Should the specified element be < or = to the root node,
     * the element is placed in the left subtree of the root.
     * Should the specified element be > the root node,
     * the element is placed in the right subtree of the root.
     * If the tree is empty (no root node), the specified element
     * is added as the root node.
     * @param e the element to be added to the tree
     * @return true if the specified element to be added is successfully added to the tree
     * @throws NullPointerException
     *          If the element specified is null and the tree implementation does not
     *          support having null elements.
     */
    public boolean add(E e) throws NullPointerException;

    /**
     * Returns an iterator over the elements in the tree, in a depth/height first
     * pre-order traversal sequence. (Visit-Left-Right)
     * @return An iterator over the elements in the tree, in pre-order traversal sequence.
     *          The return is not of type java.util.Iterator but instead a custom
     *          interface used in CPRG-311.
     */
    public Iterator<E> preOrderIterator();

    /**
     * Returns an iterator over the elements in the tree, in a depth/height first
     * in-order traversal sequence. (Left-Visit-Right)
     * @return An iterator over the elements in the tree, in in-order traversal sequence.
     *          The return is not of type java.util.Iterator but instead a custom
     *          interface used in CPRG-311.
     */
    public Iterator<E> inOrderIterator();

    /**
     * Returns an iterator over the elements in the tree, in a depth/height first
     * post-order traversal sequence. (Left-Right-Visit)
     * @return An iterator over the elements in the tree, in post-order traversal sequence.
     *          The return is not of type java.util.Iterator but instead a custom
     *          interface used in CPRG-311.
     */
    public Iterator<E> postOrderIterator();
}
