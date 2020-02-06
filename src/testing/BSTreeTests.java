/**
 * 
 */
package testing;

import static org.junit.Assert.*;

import exceptions.TreeException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import referenceBasedTreeImplementation.BSTreeNode;
import referenceBasedTreeImplementation.BSTreeReferenceBased;
import utility.Iterator;

/**
 * @author 785444
 *
 */
public class BSTreeTests {

//	private int i1, i2, i3, i4, i5, i6, i7 , i8, i9, i10;
	private String sA, sB, sC, sD, sE, sF, sG, sH, sI, sJ;
	private static BSTreeReferenceBased<String> tree;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		tree = new BSTreeReferenceBased<>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		tree = null;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
//		i1 = 1;
//		i2 = 2;
//		i3 = 3;
//		i4 = 4;
//		i5 = 5;
//		i6 = 6;
//		i7 = 7;
//		i8 = 8;
//		i9 = 9;
//		i10 = 10;
		sA = "Alpha";
		sB = "Bravo";
		sC = "Charlie";
		sD = "Delta";
		sE = "Echo";
		sF = "Foxtrot";
		sG = "Golf";
		sH = "Hotel";
		sI = "India";
		sJ = "Juliet";
		tree = new BSTreeReferenceBased<>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
//		i1 = 0;
//		i2 = 0;
//		i3 = 0;
//		i4 = 0;
//		i5 = 0;
//		i6 = 0;
//		i7 = 0;
//		i8 = 0;
//		i9 = 0;
//		i10 = 0;
		sA = null;
		sB = null;
		sC = null;
		sD = null;
		sE = null;
		sF = null;
		sG = null;
		sH = null;
		sI = null;
		sJ = null;
		tree = null;
	}

	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTreeReferenceBased#getRoot()}.
	 */
	@Test
	public void testGetRoot() throws TreeException {
		tree.add(sA);
		assertEquals(sA, tree.getRoot().getElement());
	}

	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTreeReferenceBased#getHeight()}.
	 */
	@Test
	public void testGetHeight() {
		tree.add(sB);
		assertEquals(0, tree.getHeight());
		tree.add(sA);
		assertEquals(1, tree.getHeight());
		tree.add(sC);
		assertEquals(1, tree.getHeight());
	}

	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTreeReferenceBased#size()}.
	 */
	@Test
	public void testSize() {
		tree.add(sB);
		assertEquals(1, tree.size());
	}

	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTreeReferenceBased#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(tree.isEmpty());
		tree.add(sC);
		assertFalse(tree.isEmpty());
	}

	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTreeReferenceBased#clear()}.
	 */
	@Test
	public void testClear() {
		tree.add(sD);
		tree.add(sE);
		assertFalse(tree.isEmpty());
		tree.clear();
		assertTrue(tree.isEmpty());
	}

	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTreeReferenceBased#contains(java.lang.Comparable)}.
	 */
	@Test
	public void testContains() throws TreeException {
		tree.add(sA);
		assertTrue(tree.contains(sA));
		tree.add(sB);
		assertTrue(tree.contains(sB));
		assertTrue(tree.contains("Alpha"));
	}

	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTreeReferenceBased#search(java.lang.Comparable)}.
	 */
	@Test
	public void testSearch() throws TreeException {
		assertEquals(null, tree.search(sC));
		tree.add(sC);
		assertEquals(sC, tree.search(sC).getElement());
	}

	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTreeReferenceBased#add(java.lang.Comparable)}.
	 */
	@Test
	public void testAdd() throws TreeException {
		tree.add(sD);
		assertEquals(sD, tree.getRoot().getElement());
		tree.search(sD).setElement(sC);
	}

	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTreeReferenceBased#inorderIterator()}.
	 */
	@Test
	public void testInorderIterator() {
		tree.add(sD);
		tree.add(sB);
		tree.add(sA);
		tree.add(sC);
		tree.add(sF);
		tree.add(sI);
		tree.add(sE);

		Iterator<String> iterator = tree.inorderIterator();
		assertTrue(iterator.hasNext());
		assertEquals(sA, iterator.next());
		assertEquals(sB, iterator.next());
		assertEquals(sC, iterator.next());
		assertEquals(sD, iterator.next());
		assertEquals(sE, iterator.next());
		assertEquals(sF, iterator.next());
		assertEquals(sI, iterator.next());
	}

	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTreeReferenceBased#preorderIterator()}.
	 */
	@Test
	public void testPreorderIterator() {
		tree.add(sD);
		tree.add(sB);
		tree.add(sA);
		tree.add(sC);
		tree.add(sF);
		tree.add(sI);
		tree.add(sE);

		Iterator<String> iterator = tree.preorderIterator();
		assertTrue(iterator.hasNext());
		assertEquals(sD, iterator.next());
		assertEquals(sB, iterator.next());
		assertEquals(sA, iterator.next());
		assertEquals(sC, iterator.next());
		assertEquals(sF, iterator.next());
		assertEquals(sE, iterator.next());
		assertEquals(sI, iterator.next());
	}

	/**
	 * Test method for {@link referenceBasedTreeImplementation.BSTreeReferenceBased#postorderIterator()}.
	 */
	@Test
	public void testPostorderIterator() {
		tree.add(sD);
		tree.add(sB);
		tree.add(sA);
		tree.add(sC);
		tree.add(sF);
		tree.add(sI);
		tree.add(sE);

		Iterator<String> iterator = tree.postorderIterator();
		assertTrue(iterator.hasNext());
		assertEquals(sA, iterator.next());
		assertEquals(sC, iterator.next());
		assertEquals(sB, iterator.next());
		assertEquals(sE, iterator.next());
		assertEquals(sI, iterator.next());
		assertEquals(sF, iterator.next());
		assertEquals(sD, iterator.next());
	}

}
