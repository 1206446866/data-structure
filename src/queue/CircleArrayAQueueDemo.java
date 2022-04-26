package queue;

import java.util.Scanner;

public class CircleArrayAQueueDemo {
	public static void main(String[] args) {
		System.out.println("ģ�⻷�ζ���");
		CircleArray queue = new CircleArray(4);
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
	private int maxSize;//	��ʾ�����������
	/* front ָ����еĵ�һ��Ԫ�أ�Ҳ����arr[front]
	 * front �ĳ�ʼֵ =  0
	 */
	private int front;
	/* rear ָ����е����һ��Ԫ�صĺ�һ��λ�ã���Ϊϣ���ճ�һ���ռ���ΪԼ��
	 * rear�ĳ�ʼֵ = 0
	 */
	private int rear;//����β
	private int[] arr;// ���������ڴ�����ݣ�ģ�����
	
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
			System.out.println("�����������ܼ������ݡ�");
			return;
		}
//			ֱ�Ӽ�������
		arr[rear] = n;
//			��rear���ƣ�������뿼��ȡģ
		rear = (rear + 1 ) % maxSize;
	}
	
	public int getQueue() {
		if(isEmpty()) {
			throw new RuntimeException("���пգ������ݿɶ�");
		}
		/* ��Ҫ������front��ָ����еĵ�һ��Ԫ��
		 * 1.�Ȱ�front ��Ӧ��ֵ������һ����ʱ����
		 * 2.��front����
		 * 3.����ʱ����ı�������
		 */
		int value = arr[front];
		front = (front + 1) % maxSize;
		return value;
	}
	
	public void showQueue() {
		if(isEmpty()) {
			System.out.println("���пգ�������");
			return;
		}
//			˼·:��front��ʼ�������������ٸ�Ԫ��
		for (int i = front; i < front + size(); i++) {
			System.out.printf("arr[%d]=%d\n", i % maxSize,arr[i % maxSize]);
		}
	}
	
	public int size() {
		return (rear + maxSize - front) % maxSize;
	}
	
	public int headQueue() {
		if(isEmpty()) {
			throw new RuntimeException("���пգ�������");
		}
		return arr[front];
	}
	
}
