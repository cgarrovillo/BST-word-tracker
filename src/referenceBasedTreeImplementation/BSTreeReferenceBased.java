package referenceBasedTreeImplementation;

import exceptions.TreeException;
import utility.BSTreeADT;
import utility.Iterator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * ******************************************** **
 * BSTreeReferenceBased - referenceBasedTreeImplementation.BSTreeReferenceBased
 * Reference Based Implementation of a Binary Search Tree
 * References used:
 *      Y Daniel Liang - Introduction to Java Programming and Data Structures, Comprehensive Version-Pearson (2017)
 *      https://www.geeksforgeeks.org/iterative-postorder-traversal-using-stack/
 * @author Christian Garrovillo
 * Information and Communications Technologies
 * Software Development
 * * ********************************************* **
 */
public class BSTreeReferenceBased<E extends Comparable<E>> implements BSTreeADT, Serializable {

    private static final long serialVersionUID = 1447983620054387927L;
    private BSTreeNode root;
    private int size = 0;

    public BSTreeReferenceBased() {
        root = null;
    }
    public BSTreeReferenceBased(E e) {
        this.root = new BSTreeNode(e);
    }

    /**
     * The node at the root of the Binary Search Tree will be returned.
     *
     * @return node stored at the root of tree is returned
     * @throws TreeException if the root is empty.
     */
    @Override
    public BSTreeNode getRoot() throws TreeException {
        return root;
    }

    /**
     * Determines the row height of the tree and returns that value as an
     * integer value.
     *
     * @return the height of the tree.
     */
    @Override
    public int getHeight() {
        return heightRecursive(root);
    }

    public int heightRecursive(BSTreeNode<E> parent) {
        if (parent == null)
            return -1;
        BSTreeNode<E> root = parent;

        return Math.max(heightRecursive(root.getLeftChild()), heightRecursive(root.getRightChild())) + 1;
    }

    /**
     * The number of elements currently stored in the tree is counted and
     * the value is returned.
     *
     * @return number of elements currently stored in tree.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Checks if the tree is currently empty.
     *
     * @return returns boolean true if the tree is empty otherwise false.
     */
    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    /**
     * Clears all elements currently stored in tree and makes the tree empty.
     */
    @Override
    public void clear() {
        root.setLeftChild(null);
        root.setRightChild(null);
        root = null;
        size = 0;
    }

    /**
     * Checks the current tree to see if the element passed in is stored in
     * the tree. If the element is found in the tree the method returns true
     * and if the element is not in the tree the method returns false.
     *
     * @param entry the element to find in the tree
     * @return returns boolean true if element is currently in the tree and
     * false if the element is not found in the tree
     * @throws TreeException if the tree is empty.
     */
    @Override
    public boolean contains(Comparable entry) throws TreeException {
        BSTreeNode<E> current = root;

        while (current != null) {
            if (entry.compareTo(current.getElement()) < 0 )
                current = current.getLeftChild();
            else if (entry.compareTo(current.getElement()) > 0)
                current = current.getRightChild();
            else
                return true;
        }
        return false;
    }

    /**
     * Retrieves a node from the tree given the object to search for.
     *
     * @param entry element object being searched
     * @return the node with the element located in tree, null if not found
     * @throws TreeException if the tree is empty
     */
    @Override
    public BSTreeNode search(Comparable entry) throws TreeException {
        BSTreeNode<E> current = root;

        while (current != null) {
            if (entry.compareTo(current.getElement()) < 0)
                current = current.getLeftChild();
            else if (entry.compareTo(current.getElement()) > 0)
                current = current.getRightChild();
            else
                return current;
        }
        return null;
    }

    /**
     * Adds a new element to the tree according to the natural ordering
     * established by the Comparable implementation.
     *
     * @param newEntry the element being added to the tree
     * @return a boolean true if the element is added successfully else false
     * @throws NullPointerException if the element being added is null
     */
    @Override
    public boolean add(Comparable newEntry) throws NullPointerException {
        if (isEmpty())
            root = new BSTreeNode(newEntry);
        else {
            BSTreeNode<E> parent = null;
            BSTreeNode<E> current = root;
            while (current != null) {
                if (newEntry.compareTo(current.getElement()) < 0) {
                    parent = current;
                    current = current.getLeftChild();
                }
                else if (newEntry.compareTo(current.getElement()) > 0) {
                    parent = current;
                    current = current.getRightChild();
                }
                else
                    return false;
            }

            if (newEntry.compareTo(parent.getElement()) < 0)
                parent.setLeftChild(new BSTreeNode(newEntry));
            else
                parent.setRightChild(new BSTreeNode(newEntry));
        }

        size++;
        return true;
    }

    /**
     * Generates an in-order iteration over the contents of the tree. Elements
     * are in their natural order.
     *
     * @return an iterator with the elements in the natural order
     */
    @Override
    public Iterator<E> inorderIterator() {
        return new InorderIterator();
    }

    private class InorderIterator implements Iterator<E> {
        List<BSTreeNode> list = new ArrayList<>();
        int position = 0;

        public InorderIterator() {
            inorder();
        }

        private void inorder() {
            inorderRecursive(root);
        }

        private void inorderRecursive(BSTreeNode<E> parent) {
            if (parent==null)
                return;

            inorderRecursive(parent.getLeftChild());
            list.add(parent);
            inorderRecursive(parent.getRightChild());
        }


        @Override
        public boolean hasNext() {
            return position < list.size();
        }

        @Override
        public E next() throws NoSuchElementException {
            if (this.hasNext())
                return (E) list.get(position++).getElement();
            else
                throw new NoSuchElementException();
        }
    }

    /**
     * Generates a pre-order iteration over the contents of the tree. Elements
     * are order in such a way as the root element is first.
     *
     * @return an iterator with the elements in a root element first order
     */
    @Override
    public Iterator<E> preorderIterator() {
        return new PreorderIterator();
    }

    private class PreorderIterator implements Iterator<E> {
        BSTreeNode<E> current = root;
        int position = 0;
        Stack<BSTreeNode> stack = new Stack<>();
        List<BSTreeNode> list = new ArrayList<>();

        public PreorderIterator() {
            stack.push(current);
            while (!stack.isEmpty()) {
                current = stack.pop();
                list.add(current);
                if (current.getRightChild()!= null)
                    stack.push(current.getRightChild());
                if (current.getLeftChild() != null)
                    stack.push(current.getLeftChild());
            }
        }

        @Override
        public boolean hasNext() {
            return position < list.size();
        }

        @Override
        public E next() throws NoSuchElementException {
            if (this.hasNext()) {
                return (E) list.get(position++).getElement();
            }
            else
                throw new NoSuchElementException();
        }
    }

    /**
     * Generates a post-order iteration over the contents of the tree. Elements
     * are order in such a way as the root element is last.
     *
     * @return an iterator with the elements in a root element last order
     */
    @Override
    public Iterator<E> postorderIterator() {
        return new PostorderIterator();
    }

    private class PostorderIterator implements Iterator {
        BSTreeNode<E> current = root;
        int position = 0;
        Stack<BSTreeNode> stack = new Stack<>();
        List<BSTreeNode> list = new ArrayList<>();

        public PostorderIterator() {    //breakpoint here
            do {
                while (current != null) {
                    if (current.getRightChild() != null){
                        stack.push(current.getRightChild());    //push right child of root
                    }
                    stack.push(current);    //also push the root
                    current = current.getLeftChild();   //set root to current root's left child
                }
                current = stack.pop();  //set root to top of stack
                if (!stack.isEmpty()) {
                    //if root has right child AND equal to top of stack
                    if (current.getRightChild() != null && current.getRightChild() == stack.peek()) {
                        stack.pop();    //pop stack
                        stack.push(current);    //push current root
                        current = current.getRightChild();  //set root to current root's right child
                    } else {
                        list.add(current);
                        current = null;     //set root to null, go back up to while(), proceeds to pop stack
                    }
                } else {
                    list.add(current);
                }
            } while (!stack.isEmpty());
        }

        @Override
        public boolean hasNext() {
            return position < list.size();
        }

        @Override
        public Object next() throws NoSuchElementException {
            if (this.hasNext())
                return (E) list.get(position++).getElement();
            else
                throw new NoSuchElementException();
        }
    }
}
