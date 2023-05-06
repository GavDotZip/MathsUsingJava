import javax.swing.JOptionPane;

//testing class to ensure proper use of stack rulings
public class stackTester {
	public static void main(String[] args) {
		postfixStack testStack = new stackArray(20);
		
		JOptionPane.showMessageDialog(null, "Pushing words: \nShow Me The Money");
		
		testStack.push("Show");
		testStack.push("Me");
		testStack.push("The");
		testStack.push("Money");

		JOptionPane.showMessageDialog(null, "About to pop from stack");
		while(! testStack.isEmpty()) {
			String word = (String)testStack.pop();
			JOptionPane.showMessageDialog(null, "Popped: " + word);
		}
		
		System.exit(0);
			
	}

}
