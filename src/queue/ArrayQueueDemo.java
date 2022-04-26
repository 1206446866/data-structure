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
		//���һ���˵�
		while(loop) {
			System.out.println("s(show):��ʾ����");
			System.out.println("e(exit):�˳�����");
			System.out.println("a(add):������ݵ�����");
			System.out.println("g(get):�Ӷ���ȡ������");
			System.out.println("h(head):�鿴����ͷ����");
			key = scanner.next().charAt(0);//����һ��
			switch(key) {
			case 's':
				queue.showQueue();
				break;
			case 'a':
				System.out.println("���һ����");
				int value = scanner.nextInt();
				queue.addQueue(value);
				break;
			case 'g':
				try {
					int res = queue.getQueue();
					System.out.println("ȡ����������"+res);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 'h':
				try {
					int res = queue.headQueue();
					System.out.println("����ͷ��������"+res);
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
	private int maxSize;//	��ʾ�����������
	private int front;//����ͷ
	private int rear;//����β
	private int[] arr;// ���������ڴ�����ݣ�ģ�����
	
	public ArrayQueue(int arrMaxxSize) {
		maxSize = arrMaxxSize;
		arr = new int[maxSize];
		front = -1;//ָ�����ͷ����������front��ָ�����͵��ǰһ��λ�á�
		rear = -1;//ָ�����β��ָ�����β������(�����Ƕ������һ������)
	}
	
	public boolean isFull() {
		return rear == maxSize - 1;
	}
	
	public boolean isEmpty() {
		return rear == front;
	}
	
	public void addQueue(int n) {
		
		if(isFull()) {
			System.out.println("�����������ܼ������ݡ�");
			return;
		}
		rear++;
		arr[rear] = n;
	}
	
	public int getQueue() {
		
		if(isEmpty()) {
			throw new RuntimeException("���пգ������ݿɶ�");
		}
		front++;
		return arr[front];
	}
	
	public void showQueue() {
		if(isEmpty()) {
			System.out.println("���пգ�������");
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.printf("arr[%d]=%d\n", i,arr[i]);
		}
	}
	
	public int headQueue() {
		if(isEmpty()) {
			throw new RuntimeException("���пգ�������");
		}
		return arr[front+1];
	}
	
}