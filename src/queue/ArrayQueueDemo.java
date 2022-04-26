package queue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class ArrayQueueDemo {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> c = Class.forName("ArrayQueue");
		Constructor<?> con = c.getConstructor();
		con.newInstance(3);
		ArrayQueue queue = new ArrayQueue(3);
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
				break;
			default:
				break;
			}
		}
		scanner.close();
	}

}
class ArrayQueue{
	private int maxSize;//	表示数组最大容量
	private int front;//队列头
	private int rear;//队列尾
	private int[] arr;// 该数组用于存放数据，模拟队列
	
	public ArrayQueue(int arrMaxxSize) {
		maxSize = arrMaxxSize;
		arr = new int[maxSize];
		front = -1;//指向队列头部，分析出front是指向队列偷的前一个位置。
		rear = -1;//指向队列尾，指向队列尾的数据(即就是队列最后一个数据)
	}
	
	public boolean isFull() {
		return rear == maxSize - 1;
	}
	
	public boolean isEmpty() {
		return rear == front;
	}
	
	public void addQueue(int n) {
		
		if(isFull()) {
			System.out.println("队列满，不能加入数据。");
			return;
		}
		rear++;
		arr[rear] = n;
	}
	
	public int getQueue() {
		
		if(isEmpty()) {
			throw new RuntimeException("队列空，无数据可读");
		}
		front++;
		return arr[front];
	}
	
	public void showQueue() {
		if(isEmpty()) {
			System.out.println("队列空，无数据");
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.printf("arr[%d]=%d\n", i,arr[i]);
		}
	}
	
	public int headQueue() {
		if(isEmpty()) {
			throw new RuntimeException("队列空，无数据");
		}
		return arr[front+1];
	}
	
}