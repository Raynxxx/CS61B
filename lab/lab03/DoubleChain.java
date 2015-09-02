
public class DoubleChain {
	
	private DNode head;
	
	public DoubleChain(double val) {
		/* your code here. */
		head = new DNode(val); 
	}

	public DNode getFront() {
		return head;
	}

	/** Returns the last item in the DoubleChain. */		
	public DNode getBack() {
		/* your code here */
        if (head.prev == null) {
            return head;
        }
		return head.prev;
	}
	
	/** Adds D to the front of the DoubleChain. */	
	public void insertFront(double d) {
		/* your code here */
        if (head.prev == null && head.next == null) {
            DNode cur = new DNode(head, d, head);
            head.next = cur;
            head.prev = cur;
            head = cur;
        } else {
            DNode back = head.prev;
            DNode cur = new DNode(back, d, head);
            back.next = cur;
            head.prev = cur;
            head = cur;
        }
	}
	
	/** Adds D to the back of the DoubleChain. */	
	public void insertBack(double d) {
		/* your code here */
        if (head.prev == null && head.next == null) {
            DNode cur = new DNode(head, d, head);
            head.next = cur;
            head.prev = cur;
        } else {
            DNode back = head.prev;
            DNode cur = new DNode(back, d, head);
            back.next = cur;
            head.prev = cur;
        }
	}
	
	/** Removes the last item in the DoubleChain and returns it. 
	  * This is an extra challenge problem. */
	public DNode deleteBack() {
		/* your code here */
        DNode back = null;
        if (head.prev == null) {
            back = head;
            head = null;
        } else if (head.prev.prev == head) {
            back = head.prev;
            head.prev = null;
            head.next = null;
        } else {
            back = head.prev;
            back.prev.next = head;
            head.prev = back.prev;
        }
        return back;
	}
	
	/** Returns a string representation of the DoubleChain. 
	  * This is an extra challenge problem. */
	public String toString() {
		/* your code here */		
        String ret = "<[";
        DNode process = head;
        while (process != head.prev) {
            ret += process.val + ", ";
            process = process.next;
        }
        ret += process.val + "]>";
		return ret;
	}

	public static class DNode {
		public DNode prev;
		public DNode next;
		public double val;
		
		private DNode(double val) {
			this(null, val, null);
		}
		
		private DNode(DNode prev, double val, DNode next) {
			this.prev = prev;
			this.val = val;
			this.next =next;
		}
	}
	
}
