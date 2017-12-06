package ADT;

public class BinaryTreeNode<T>
{
    protected T element;
    protected BinaryTreeNode<T> left, right;

    public BinaryTreeNode(T obj) 
    {
        element = obj;
        left = null;
        right = null;
    }

    public BinaryTreeNode(T obj, LinkedBinaryTree<T> left, LinkedBinaryTree<T> right) 
    {
        element = obj;
        if (left == null)
            this.left = null;
		else
			try
			{
				this.left = left.getRootNode();
			} catch (EmptyCollectionException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
         if (right == null)
            this.right = null;
		else
			try
			{
				this.right = right.getRootNode();
			} catch (EmptyCollectionException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    
    public int numChildren() 
    {
        int children = 0;

        if (left != null)
            children = 1 + left.numChildren();

        if (right != null)
            children = children + 1 + right.numChildren();

        return children;
    }
    
    public int getHeight() 
    {
        int height = -1;

        if (left != null)
            height = Math.max(left.getHeight(), height);

        if (right != null)
        	height = Math.max(right.getHeight(), height);

        return height + 1;
    }
    
   public T getElement() 
    {
        return element;
    }
    
    public BinaryTreeNode<T> getRight() 
    {
        return right;
    }
    
    public void setRight(BinaryTreeNode<T> node) 
    {
        right = node;
    }
    
    public BinaryTreeNode<T> getLeft() 
    {
        return left;
    }
    
    public void setLeft(BinaryTreeNode<T> node) 
    {
        left = node;
    }
    
    public String toString()
    {
    	return element.toString();
    }
}

