package chapter_10;

import ADT.*;



public class ExpressionTreeOp 
{
	/**
	 * 1 = operator
	 * 2 = value
	 * 3 = variable
	 */
    private int termType;
    private char operator;
    private int value;
    private char variable;

    public ExpressionTreeOp(int type, char op, int val, char var) 
    {
        termType = type;
        operator = op;
        value = val;
        variable = var;
    }

    public boolean isOperator() 
    {
        return (termType == 1);
    }
    
    public boolean isVariable()
    {
    	return (termType == 3);
    }
    
    public char getOperator() 
    {
        return operator;
    }

    public int getValue() 
    {
        return value;
    }
    
    public char getVariable()
    {
    	return variable;
    }
    
    public void setValue(int val)
    {
    	value = val;
    }
    
    public void setOperator(char op)
    {
    	operator = op;
    }
    
    public void setVariable(char var)
    {
    	variable = var;
    }
    
    public String toString()
    {
        if (termType == 1) 
            return operator + "";
        else if (termType == 2)
            return value + "";
        else
			return variable + "";
    }
}
    

