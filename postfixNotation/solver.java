import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class solver {
	public static void main(String args[]) {
		String expressions = getExpression();
		String postfix = getPostfix(expressions);
		double result = evaluatePostfix(postfix);
		
        //will be resulting pane when user inputs values and ntations calculated
		JOptionPane.showMessageDialog(null,
	            "The result of the expression is:\n" +
	            "Infix: "   + expressions    + "\n" +
	            "Postfix: " + postfix       + "\n" +
	            "Result: "  + result);
	}
	
    //prompt user to input values for expressions
	public static String getExpression() {
		String str = JOptionPane.showInputDialog(null, "Please enter numerical infix expression 3 and 20 characters: ");
		boolean valid = validateInfixExpression(str);
		
		if (!valid) {
			JFrame error = new JFrame();
			JOptionPane.showMessageDialog(error, "Only the following characters are valid: + - * / ^ (, ) and numbers 0-9");
			return getExpression();
		}
		
		return str;
	}
	
    //set order of precedence as per BOMDAS rule
	public static int precedence(char operator){
        switch (operator){
            case '^':
                return 3;
            case '/':
            case '*':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return 0;
        }
    }
	
    //use ReGex for validation of characters and check that characters match rulings
	public static boolean validateInfixExpression(String expressions) {
		boolean chars = Pattern.compile("^[\\d+*\\/\\^(\\)\\-]*$").matcher(expressions).matches();
		boolean length = expressions.length() >= 3 && expressions.length() <= 20;
		
		int left = 0, right = 0;
		for(int i = 0; i<expressions.length(); i++) {
			char c = expressions.charAt(i);
			if(c == '(') left++;
			else if (c == ')') right++;
		}
		
		char initial = expressions.charAt(0);
		if(initial == '-') return false;
		
		for(int i=1; i<expressions.length(); i++) {
			char c = expressions.charAt(i);
			if(Character.isDigit(initial) && Character.isDigit(c)) return false;
			if(Character.isDigit(initial) && c == '(' ||
					initial == ')' && c == '(' ||
					initial == '-' && c == '-' ||
					precedence(initial) != 0 && c == '-'
					) return false;
			initial = c;
		}
		if (chars && length && left == right) return true;
		return false;
		
	}
	
    //method to decide whether character should be pushed to which side of stack
	public static String getPostfix(String infix) {
		StringBuffer postfix = new StringBuffer(infix.length());
		postfixStack stack = new stackArray();
		
		for(int i=0; i<infix.length(); i++) {
			char c = infix.charAt(i);
			
			if(Character.isLetterOrDigit(c)) {
				postfix.append(String.valueOf(c));
			}
			
			else if(c == ')') {
				stack.push((Object) c);
			}
			
			else if(c == ')') {
				while (!stack.isEmpty() && (char) stack.top() != '(') {
					postfix.append(String.valueOf(stack.pop()));
				}
				stack.pop();
			}
			
			else {
				while (!stack.isEmpty() && precedence(c) <= precedence((char) stack.top())) {
					postfix.append(String.valueOf(stack.pop()));
				}
				stack.push((Object) c);
 			}
		}
		while (!stack.isEmpty()) {
			postfix.append(String.valueOf(stack.pop()));
		}
		return postfix.toString();
	}
	
    //fruther evaluation of expression and calculation rulings
	public static double evaluatePostfix(String postfix) {
		double x, y, result = 0;
		postfixStack stack = new stackArray();
		
		for(int i=0; i<postfix.length(); i++) {
			char c = postfix.charAt(i);
			
			if (Character.isLetterOrDigit(c)) {
				stack.push((Object) c);
			}
			else {
				y = Double.parseDouble(String.valueOf(stack.pop()));
				if (stack.isEmpty()) break;
				x = Double.parseDouble(String.valueOf(stack.pop()));
				
				switch(c) {
				case '*':
					result = x * y;
					System.out.printf("%.1f * %.1f = %.1f\n", x, y, result);
                    stack.push(result);
                    break;
                case '+':
                    result = x + y;
                    System.out.printf("%.1f + %.1f = %.1f\n", x, y, result);
                    stack.push(result);
                    break;
                case '-':
                    result = x - y;
                    System.out.printf("%.1f - %.1f = %.1f\n", x, y, result);
                    stack.push(result);
                    break;
                case '/':
                    result = x / y;
                    System.out.printf("%.1f / %.1f = %.1f\n", x, y, result);
                    stack.push(result);
                    break;
                case '^':
                    result = Math.pow(x, y);
                    System.out.printf("%.1f ^ %.1f = %.1f\n", x, y, result);
                    stack.push(result);
                    break;
                default:
                    break;
				}
			}
		}
		return result;
	}
	

}
