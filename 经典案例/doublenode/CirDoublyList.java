package doublenode;

public class CirDoublyList<T>{

	public DoubleNode<T> head;
	public CirDoublyList() {
		this.head = new DoubleNode<T>();
		this.head.prev = this.head;
		this.head.next = this.head;
	}
	
	public boolean isEmpty() {
		return this.head.next == this.head;
	}
	
	public int size() {
		int i = 0;
		for(DoubleNode<T> q = this.head.next;q != null ;q = q.next) {
			i++;
		}
		return i;
	}
	
	public T remove(T key) {
//		TODO
		return key;
	}
	
	public T get(int i) {
		DoubleNode<T> p = this.head.next;
		if(i <= this.size() / 2) {
			for(int j = 0;p != null && j<i;j++) {
				p = p.next;
			}
		}
		else {
			for(int j = this.size();p != null && i<j;j--) {
				p = p.prev;
			}
		}
		return (p != null && i>=0)? p.data:null;
	}
	
	public void set(int i,T x) {
//		TODO-set
		if(x == null)
			throw new NullPointerException("NullpointException");
		else {
			DoubleNode<T> p = this.head.next;
			for(int j = 0;p != null && j<i;j++) {
				p = p.next;
			}
			if(i >= 0 && x != null)
				p.data = x;
			 else throw new IndexOutOfBoundsException(i+"");
		}
	}
	
	public DoubleNode<T> insert(int i,T x){
		if(x==null)
			return null;
		DoubleNode<T> front = this.head;
		for(int j = 0; front.next!=this.head && j<i; j++)
			front = front.next;
		DoubleNode<T> q = new DoubleNode<T>(x, front, front.next);
		front.next.prev = q;
		front.next = q;
		return q;
	}
	
	public DoubleNode<T> insert(T x){
		if(x == null)
			return null;
		DoubleNode<T> q = new DoubleNode<T>(x,head.prev,head);
		head.prev.next = q;
		head.prev = q ;
		return q;
	}
	
	public void clear()                          
	{
	    this.head.next = null;
    }
	
	public String toPreviousString() {
//		TODO-toPreviousString
		
		return null;
	}

}
