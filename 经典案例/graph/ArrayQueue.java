package graph;

public class ArrayQueue {

	private int maxSize; // 表示数组的最大容量
	private int front; // 队列头
	private int rear; // 队列尾
	private int[] arr; // 该数组用于存放数据，模拟队列

	// 创建队列的构造器
	public ArrayQueue(int arrmaxSize) {
		maxSize = arrmaxSize;
		arr = new int[maxSize];
		front = -1;
		rear = -1;

	}

//判断队列是否满
	public boolean isFull() {
		return rear == maxSize - 1;
	}

//判断队列是否为空
	public boolean isEmpty() {
		return front == rear;
	}
         //向队列中添加元素
	public void addQueue(int n) {
		if(isFull()) {
			//System.out.println("队列已满，无法添加元素");
			return;
		}
		rear++;
		arr[rear] = n;
	}
	     //从队列中取出数据
	public int getQueue() {
		if(isEmpty()) {
			throw new RuntimeException("队列空，不能取出数据");
		   
		}
		front++;
		return arr[front];
	}
	    //显示队列中的数据
	 public void showQueue() {
		 if(isEmpty()) {
			 System.out.println("队列为空，没有数据");
			 return;
		 }
		 for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]+" ");
		}
	 }
	     //显示队列的头数据
	 public int headQueue() {
		 if(isEmpty()) {
			 throw new RuntimeException("队列为空，没有数据");
		 }
		 return arr[front+1];
	 }

}
