package chapter_10;

import ADT.*;
import java.util.*;
import java.io.*;

public class PostfixEvaluator    
{
    private String expression;
    private Stack<ExpressionTree> treeStack;
    
    public PostfixEvaluator()
    {
        treeStack = new Stack<ExpressionTree>();
    }

    private ExpressionTree getOperand(Stack<ExpressionTree> treeStack)
    {
        ExpressionTree temp;
        temp = treeStack.pop();
        
        return temp;
    }
    
    public ExpressionTree parse(String expression)
    {
        ExpressionTree operand1, operand2;
        char operator;
        char variable;
        String tempToken;

        Scanner parser = new Scanner(expression);
        
        while (parser.hasNext()) 
        {
            tempToken = parser.next();
            operator = tempToken.charAt(0);
            
            if ((operator == '+') || (operator == '-') || (operator == '*') || 
                 (operator == '/'))
            {
                operand1 = getOperand(treeStack);
                operand2 = getOperand(treeStack);
                treeStack.push(new ExpressionTree 
                                  (new ExpressionTreeOp(1,operator,0,' '), operand2, operand1));
            } else if (isVariable(tempToken))
            {
            	treeStack.push(new ExpressionTree(new ExpressionTreeOp
                      (3,' ',' ',tempToken.toCharArray()[0]), null, null));
            }
            else
            {
                treeStack.push(new ExpressionTree(new ExpressionTreeOp
                                    (2,' ',Integer.parseInt(tempToken), ' '), null, null));
            }
            
        }
        return treeStack.peek();        
    }
    
    public String getTree()
    {
        return (treeStack.peek()).printTree();
    }
    
    private boolean isVariable(String token)
    {
    	switch (token) {
    		case "a":
    			return true;
    		case "b":
    			return true;
    		case "c":
    			return true;
    		case "d":
    			return true;
    		case "e":
    			return true;
    		case "f":
    			return true;
    		case "g":
    			return true;
    		case "h":
    			return true;
    		case "i":
    			return true;
    		case "j":
    			return true;
    		case "k":
    			return true;
    		case "l":
    			return true;
    		case "m":
    			return true;
    		case "n":
    			return true;
    		case "o":
    			return true;
    		case "p":
    			return true;
    		case "q":
    			return true;
    		case "r":
    			return true;
    		case "s":
    			return true;
    		case "t":
    			return true;
    		case "u":
    			return true;
    		case "v":
    			return true;
    		case "w":
    			return true;
    		case "x":
    			return true;
    		case "y":
    			return true;
    		case "z":
    			return true;
			default:
				return false;

    	}
    }
}
