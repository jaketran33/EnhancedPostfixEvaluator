package ADT;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class LinkedBinaryTree<T> implements BinaryTreeADT<T>, Iterable<T>
{
    protected BinaryTreeNode<T> root; 
    protected int modCount;
    
    public LinkedBinaryTree() 
    {
        root = null;
    }

    public LinkedBinaryTree(T element) 
    {
        root = new BinaryTreeNode<T>(element);
    }
    
    public LinkedBinaryTree(BinaryTreeNode<T> r) 
    {
        root = r;
    }
    
   public LinkedBinaryTree(T element, LinkedBinaryTree<T> left, 
                            LinkedBinaryTree<T> right) 
    {
        root = new BinaryTreeNode<T>(element);
        root.setLeft(left.root);
        root.setRight(right.root);
    }
    
    public T getRootElement() throws EmptyCollectionException
    {
        if (root != null) 
        	return root.getElement();
  
        throw new EmptyCollectionException("LinkedBinaryTree");
    }
    
    protected BinaryTreeNode<T> getRootNode() throws EmptyCollectionException
    {
        if (root != null) 
        	return root;
  
        throw new EmptyCollectionException("LinkedBinaryTree");
    }
    
    public LinkedBinaryTree<T> getLeft()
    {
    	if (root.getLeft() != null)
    		return new LinkedBinaryTree<T>(root.getLeft());
    	else
    		return new LinkedBinaryTree<T>();
    }
    
    public LinkedBinaryTree<T> getRight()
    {
    	if (root.getRight() != null)
    		return new LinkedBinaryTree<T>(root.getRight());
    	else
    		return new LinkedBinaryTree<T>();
    }
    
    public boolean isEmpty() 
    {
        return (root == null);
    }

    public int size() 
    {
    	int s = 0;
    	
    	if (root!=null)
    		s = root.numChildren() + 1;
    	
    	return s;
    }
    
    public int getHeight()
    {
    	int h = 0;
    	
    	if (root!=null)
    		h = root.getHeight();
    	
    	return h;
    }
    
    private int height(BinaryTreeNode<T> node) 
    {
    	int h = 0;
    	
    	if (node!=null)
    		h = node.getHeight();
    	
    	return h;
    }
    
    public boolean contains(T targetElement) throws ElementNotFoundException 
    {
    	return find(targetElement) != null;
    }
    
    public T find(T targetElement) throws ElementNotFoundException
    {
        BinaryTreeNode<T> current = findNode(targetElement, root);
        
        if (current == null)
            throw new ElementNotFoundException("LinkedBinaryTree");
        
        return (current.getElement());
    }

    private BinaryTreeNode<T> findNode(T targetElement, 
                                        BinaryTreeNode<T> next)
    {
        if (next == null)
            return null;
        
        if (next.getElement().equals(targetElement))
            return next;
        
        BinaryTreeNode<T> temp = findNode(targetElement, next.getLeft());
        
        if (temp == null)
            temp = findNode(targetElement, next.getRight());
        
        return temp;
    }
    
    public String toString() 
    {
        // To be completed as a Programming Project
    	
    	return "";
    }

    public Iterator<T> iterator()
    {
        return iteratorInOrder();
    }
    
    public Iterator<T> iteratorInOrder()
    {
        List<T> tempList = new ArrayList<T>();
        inOrder(root, tempList);
        
        return new TreeIterator(tempList.iterator());
    }

    protected void inOrder(BinaryTreeNode<T> node, 
                           List<T> tempList) 
    {
        if (node != null)
        {
            inOrder(node.getLeft(), tempList);
            tempList.add(node.getElement());
            inOrder(node.getRight(), tempList);
        }
    }

    public Iterator<T> iteratorPreOrder() 
    {
        List<T> tempList = new ArrayList<T>();
        preOrder(root, tempList);
        
        return new TreeIterator(tempList.iterator());
    }

    protected void preOrder(BinaryTreeNode<T> node, 
                            List<T> tempList) 
    {
        if (node != null)
        {
            tempList.add(node.getElement());
            inOrder(node.getLeft(), tempList);
            inOrder(node.getRight(), tempList);
        }
    }

    public Iterator<T> iteratorPostOrder() 
    {
        List<T> tempList = new ArrayList<T>();
        postOrder(root, tempList);
        
        return new TreeIterator(tempList.iterator());
    }

    protected void postOrder(BinaryTreeNode<T> node, 
                             List<T> tempList) 
    {
        if (node != null)
        {
            inOrder(node.getLeft(), tempList);
            inOrder(node.getRight(), tempList);
            tempList.add(node.getElement());
        }
    }

    public Iterator<T> iteratorLevelOrder() 
    {
        List<BinaryTreeNode<T>> nodes = 
                              new ArrayList<BinaryTreeNode<T>>();
        List<T> tempList = new ArrayList<T>();
        BinaryTreeNode<T> current;

        nodes.add(root);
        
        while (!nodes.isEmpty()) 
        {
            current = nodes.remove(0);
            
            if (current != null)
            {
                tempList.add(current.getElement());
                if (current.getLeft() != null)
                    nodes.add(current.getLeft());
                if (current.getRight() != null)
                    nodes.add(current.getRight());
            }
            else
                tempList.add(null);
        }
        
        return new TreeIterator(tempList.iterator());
    }
    
    private class TreeIterator implements Iterator<T>
    {
        private int expectedModCount;
        private Iterator<T> iter;
        
        public TreeIterator(Iterator<T> iter)
        {
            this.iter = iter;
            expectedModCount = modCount;
        }
        
        public boolean hasNext() throws ConcurrentModificationException
        {
            if (!(modCount == expectedModCount))
                throw new ConcurrentModificationException();
            
            return (iter.hasNext());
        }
        
        public T next() throws NoSuchElementException
        {
            if (hasNext())
                return (iter.next());
            else 
                throw new NoSuchElementException();
        }
        
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }
}

