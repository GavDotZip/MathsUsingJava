import javax.swing.JOptionPane;

public class stackArray implements postfixStack{
    //basic data declarations for variables
	protected Object S[];
	protected static final int CAPACITY = 1000;
	protected int capacity;
	protected int top = -1;
	
    //constructor
	public stackArray() {
		this(CAPACITY);
	}
	
    //constructor to set capacity for stack
	public stackArray(int cap) {
		capacity = (cap > 0) ? cap : CAPACITY;
		S = new Object[capacity];
	}
	
    //override methods from interface
	public boolean isEmpty() {
		return (top < 0);
	}
	
	public boolean isFull() {
		return (top == capacity-1);
	}
	
	public int size() {
		return (top + 1);
	}
	
	public Object pop() {
		Object element;
		if (isEmpty()) {
			JOptionPane.showMessageDialog(null, "ERROR: Stack is Empty");
			return null;
		}
		element = S[top];
		S[top] = null;
		top --;
		return element;
	}
	
	public void push(Object element) {
		if(isFull()) {
			JOptionPane.showMessageDialog(null, "ERROR: Stack is Full.");
			return;
		}
		top++;
		S[top] = element;
	}
	
	public Object top() {
		if (isEmpty()) {
			JOptionPane.showMessageDialog(null, "ERROR: Stack is Empty");
			return null;
		}
		return S[top];
	}
	

}
