package graph;

public class ArrayQueue {

	private int maxSize; // ��ʾ������������
	private int front; // ����ͷ
	private int rear; // ����β
	private int[] arr; // ���������ڴ�����ݣ�ģ�����

	// �������еĹ�����
	public ArrayQueue(int arrmaxSize) {
		maxSize = arrmaxSize;
		arr = new int[maxSize];
		front = -1;
		rear = -1;

	}

//�ж϶����Ƿ���
	public boolean isFull() {
		return rear == maxSize - 1;
	}

//�ж϶����Ƿ�Ϊ��
	public boolean isEmpty() {
		return front == rear;
	}
         //����������Ԫ��
	public void addQueue(int n) {
		if(isFull()) {
			//System.out.println("�����������޷����Ԫ��");
			return;
		}
		rear++;
		arr[rear] = n;
	}
	     //�Ӷ�����ȡ������
	public int getQueue() {
		if(isEmpty()) {
			throw new RuntimeException("���пգ�����ȡ������");
		   
		}
		front++;
		return arr[front];
	}
	    //��ʾ�����е�����
	 public void showQueue() {
		 if(isEmpty()) {
			 System.out.println("����Ϊ�գ�û������");
			 return;
		 }
		 for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]+" ");
		}
	 }
	     //��ʾ���е�ͷ����
	 public int headQueue() {
		 if(isEmpty()) {
			 throw new RuntimeException("����Ϊ�գ�û������");
		 }
		 return arr[front+1];
	 }

}
