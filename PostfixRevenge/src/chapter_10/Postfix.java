package chapter_10;

import java.util.Iterator;
import java.util.Scanner;

public class Postfix   
{
    public static void main(String[] args)
    {
        String expression, code, again;
        ExpressionTree tree;
        int result;
    
        Scanner in = new Scanner(System.in);
      
        System.out.println("Postfix: Jake Tran\n");
        
        do
        {  
            PostfixEvaluator evaluator = new PostfixEvaluator();
            
            System.out.println("Enter a valid post-fix expression one token " +
                               "at a time with a space between each token (e.g. 5 4 + 3 2 1 - + *)");
            System.out.println("Each token must be an integer, operator (+,-,*,/), or variable");
            expression = in.nextLine();

            tree   = evaluator.parse(expression);
            code   = tree.generateCode();
			result = tree.evaluate();
            
            System.out.println();
            System.out.println("Code: \n" + code);

            System.out.println("The Infix expression is: " + tree);
            System.out.println("Expression value is: " + result);
            System.out.println();

            System.out.print("Evaluate another expression [Y/N]? ");
            again = in.nextLine();
            System.out.println();
        }
        while (again.equalsIgnoreCase("y"));
   }
}
