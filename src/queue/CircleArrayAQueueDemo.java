package queue;

import java.util.Scanner;

public class CircleArrayAQueueDemo {
	public static void main(String[] args) {
		System.out.println("模拟环形队列");
		CircleArray queue = new CircleArray(4);
		char key = ' ';
		Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		//输出一个菜单
		while(loop) {
			System.out.println("s(show):显示队列");
			System.out.println("e(exit):退出程序");
			System.out.println("a(add):添加数据到队列");
			System.out.println("g(get):从队列取出数据");
			System.out.println("h(head):查看队列头数据");
			key = scanner.next().charAt(0);//接受一个
			switch(key) {
			case 's':
				queue.showQueue();
				break;
			case 'a':
				System.out.println("输出一个数");
				int value = scanner.nextInt();
				queue.addQueue(value);
				break;
			case 'g':
				try {
					int res = queue.getQueue();
					System.out.println("取出的数据是"+res);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 'h':
				try {
					int res = queue.headQueue();
					System.out.println("队列头的数据是"+res);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 'e':
				loop = false;
				System.out.println("Terminal");
				break;
			default:
				break;
			}
		}
		scanner.close();
	}
}
class CircleArray{
	private int maxSize;//	表示数组最大容量
	/* front 指向队列的第一个元素，也就是arr[front]
	 * front 的初始值 =  0
	 */
	private int front;
	/* rear 指向队列的最后一个元素的后一个位置，因为希望空出一个空间作为约定
	 * rear的初始值 = 0
	 */
	private int rear;//队列尾
	private int[] arr;// 该数组用于存放数据，模拟队列
	
	public CircleArray(int arrMaxxSize) {
		maxSize = arrMaxxSize;
		arr = new int[maxSize];
	}
	
	public boolean isFull() {
		return (rear + 1) % maxSize == front;
	}
	
	public boolean isEmpty() {
		return rear == front;
	}
	
	public void addQueue(int n) {
		if(isFull()) {
			System.out.println("队列满，不能加入数据。");
			return;
		}
//			直接加入数据
		arr[rear] = n;
//			将rear后移，这里必须考虑取模
		rear = (rear + 1 ) % maxSize;
	}
	
	public int getQueue() {
		if(isEmpty()) {
			throw new RuntimeException("队列空，无数据可读");
		}
		/* 需要分析出front是指向队列的第一个元素
		 * 1.先把front 对应的值保留到一个临时变量
		 * 2.将front后移
		 * 3.将临时保存的变量返回
		 */
		int value = arr[front];
		front = (front + 1) % maxSize;
		return value;
	}
	
	public void showQueue() {
		if(isEmpty()) {
			System.out.println("队列空，无数据");
			return;
		}
//			思路:从front开始遍历，遍历多少个元素
		for (int i = front; i < front + size(); i++) {
			System.out.printf("arr[%d]=%d\n", i % maxSize,arr[i % maxSize]);
		}
	}
	
	public int size() {
		return (rear + maxSize - front) % maxSize;
	}
	
	public int headQueue() {
		if(isEmpty()) {
			throw new RuntimeException("队列空，无数据");
		}
		return arr[front];
	}
	
}
