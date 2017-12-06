package chapter_10;

import ADT.*;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class ExpressionTree extends LinkedBinaryTree<ExpressionTreeOp>
{
    ArrayList<ExpressionTreeOp> variableList = new ArrayList<>(); // used to store variables
	
    public ExpressionTree() 
    {
        super();
    }

    public ExpressionTree(ExpressionTreeOp element,
                    ExpressionTree leftSubtree, ExpressionTree rightSubtree) 
    {
        root = new BinaryTreeNode<ExpressionTreeOp>(element, leftSubtree, rightSubtree);
    }
    
    public int evaluate() 
    {
        return evaluateNode(root);
    }

    private int evaluateNode(BinaryTreeNode root) 
    {
        int result = 0, operand1, operand2;
        ExpressionTreeOp temp;
        
        if (root==null)
            result = 0;
        else
        {
            temp = (ExpressionTreeOp)root.getElement();
            
            if (temp.isOperator())
            {
                operand1 = evaluateNode(root.getLeft());
                operand2 = evaluateNode(root.getRight());
                result = computeTerm(temp.getOperator(), operand1, operand2);
            } else if (temp.isVariable())
            {
            	boolean exist = false;
            	ExpressionTreeOp existingVariable = new ExpressionTreeOp(3, ' ', ' ', ' ');
            	
            	// checks for any existing variables already in expression
            	for (ExpressionTreeOp op : variableList)
            		if (op.getVariable() == temp.toString().charAt(0))
            		{
            			exist = true;
            			existingVariable.setValue(op.getValue());
            			existingVariable.setVariable(op.getVariable());
            		}
            	
            	// if there are no existing variables
            	if (!exist)
            	{
	            	String answer;
	            	System.out.print("What is the value of " + temp + "? ");
	            	Scanner kb = new Scanner(System.in);
	            	answer = kb.nextLine();
	            	
	            	// adds this variable to the list of existing variables
	            	temp.setValue(Integer.parseInt(answer));
	            	variableList.add(temp);
	            	
	            	result = temp.getValue();
            	} else
            		result = existingVariable.getValue();

            }
            else
                result = temp.getValue();
        }
        
        return result;
    }

    private static int computeTerm(char operator, int operand1, int operand2)
    {
        int result=0;
        
        if (operator == '+')
            result = operand1 + operand2;    
        else if (operator == '-')
            result = operand1 - operand2;
        else if (operator == '*')
            result = operand1 * operand2;
        else 
            result = operand1 / operand2;

        return result;
    }
    
    public String printTree() 
    {
        List<BinaryTreeNode<ExpressionTreeOp>> nodes = 
            new ArrayList<BinaryTreeNode<ExpressionTreeOp>>();
        List<Integer> levelList = 
            new ArrayList<Integer>();
        BinaryTreeNode<ExpressionTreeOp> current;
        String result = "";
        int printDepth = this.getHeight();
        int possibleNodes = (int)Math.pow(2, printDepth + 1);
        int countNodes = 0;
        
        nodes.add(root);
        Integer currentLevel = 0;
        Integer previousLevel = -1;
        levelList.add(currentLevel);
        
        while (countNodes < possibleNodes) 
        {
            countNodes = countNodes + 1;
            current = nodes.remove(0);
            currentLevel = levelList.remove(0);
            if (currentLevel > previousLevel)
            {
                result = result + "\n\n";
                previousLevel = currentLevel;
                for (int j = 0; j < ((Math.pow(2, (printDepth - currentLevel))) - 1); j++)
                    result = result + " ";
            }
            else
            {
                for (int i = 0; i < ((Math.pow(2, (printDepth - currentLevel + 1)) - 1)) ; i++) 
                {
                    result = result + " ";
                }
            }
            if (current != null)
            {
                result = result + (current.getElement()).toString();
                nodes.add(current.getLeft());
                levelList.add(currentLevel + 1);
                nodes.add(current.getRight());
                levelList.add(currentLevel + 1);
            }
            else {
                nodes.add(null);
                levelList.add(currentLevel + 1);
                nodes.add(null);
                levelList.add(currentLevel + 1);
                result = result + " ";
            }
        }
        
        return result;
    }
    
    public String generateCode() 
    {
    	String code = "";
    	
    	String v = getVar();
        code = generateCodeNode(root, v) + "Result in " + v;
        putVar(v);
        
        return code;
    }

    private String generateCodeNode(BinaryTreeNode root, String v) 
    {
        String code, operand1Code, operand2Code;
        ExpressionTreeOp temp;
        
        if (root==null)
            code = "";
        else
        {
            temp = (ExpressionTreeOp)root.getElement();
            
            if (temp.isOperator())
            {
                operand1Code = generateCodeNode(root.getLeft(), v);

                String vr = getVar();       		
                operand2Code = generateCodeNode(root.getRight(), vr);
                
                code = operand1Code + operand2Code + 
                		getOperation(temp.getOperator()) + " " + v + ", " + v + ", " +  vr + "\n";
                putVar(vr);
            } else if (temp.isVariable())
            {
            	code = "LA $AT" + ", " + temp.getVariable() + "\n"
            			+ "LW " + v + ", 0($AT)\n";
            }
            else
                code = "LI " + v + ", " + temp.getValue() + "\n";
        }
        
        return code;
    }
        
    private static String getOperation(char operator)
    {
        String result = "";
        
        if (operator == '+')
            result = "ADD";    
        else if (operator == '-')
            result = "SUB";
        else if (operator == '*')
            result = "MUL";
        else 
            result = "DIV";

        return result;
    }
    
    private static Stack<String> vars;
    static {
        vars = new Stack<String>();
        vars.push("$V1");
        vars.push("$V0");
        vars.push("$A3");
        vars.push("$A2");
        vars.push("$A1");
        vars.push("$A0");
        vars.push("$S7");
        vars.push("$S6");
        vars.push("$S5");
        vars.push("$S4");
        vars.push("$S3");
        vars.push("$S2");
        vars.push("$S1");
        vars.push("$S0");
        vars.push("$T9");
        vars.push("$T8");
        vars.push("$T7");
        vars.push("$T6");
        vars.push("$T5");
        vars.push("$T4");
        vars.push("$T3");
        vars.push("$T2");
        vars.push("$T1");
        vars.push("$T0");
    }
   
    private String getVar() {
    	return vars.pop();
    }
    
	private void putVar(String v) {
		vars.push(v);
	}
	
	public String toString() {
		return printInfix(root, "");
	}

	private String printInfix(BinaryTreeNode root, String r)
	{
		String operand1, operand2;
		char operator;
		ExpressionTreeOp temp;
		String result = r;
		
		if (root == null)
			result = "";
		else {
			
			temp = (ExpressionTreeOp) root.getElement();
			
			if (temp.isOperator())
			{
				operand1 = printInfix(root.getLeft(), result);
				operand2 = printInfix(root.getRight(), result);
				result += "(" + operand1 + root.getElement() + operand2 + ")";
			} else
				result += temp.toString() + "";
			
		}
		
		return result;
	}
}

