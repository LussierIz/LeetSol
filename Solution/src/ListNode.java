public class ListNode {
    
    public int val;
    public ListNode next;

    // Default constructor
    public ListNode() {
        this.val = 0;
        this.next = null;
    }

    // Constructor with initial value
    public ListNode(int value) {
        this.val = value;
        this.next = null;
    }

    // Constructor with initial value and ListNode
    public ListNode(int value, ListNode node) {
        this.val = value;
        this.next = node;
    }

    // Getter and setter for value
    public int getVal() {
        return val;
    }

    public void setVal(int value) {
        this.val = value;
    }

    // Getter and setter for next node
    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    // Override toString method for better display
    @Override
    public String toString() {
        return "Node value: " + val;
    }
}
