package linkedlist;

public class SingleLinkedListDemo {
	public static void main(String[] args) {
		HeroNode hero1 = new HeroNode(1,"ppgp", "yyy");
		SingleLinkedList singleLinkedlList = new SingleLinkedList();
		singleLinkedlList.add(hero1);
		singleLinkedlList.list();
	}
}
class SingleLinkedList{
//	�ȳ�ʼ��һ��ͷ��㣬ͷ��㲻��
	private HeroNode head = new HeroNode(0, "", "");
	
//		��ӽ�㵽��������
//	1.�ҵ���ǰ����������
//	2.������������next ָ���µĽ��
	public void add(HeroNode heroNode) {
//		��Ҫһ����������temp
		HeroNode temp = head;
//		���������ҵ����
		while(true) {
//			����������
			if(temp.next == null) {
				break;
			}
//			��û�ҵ���󣬽�temp����
			temp = temp.next;
		}
//		���˳�whileѭ���ǣ�temp��ָ������������
//		���������ڵ��nextָ���½ڵ�
		temp.next = heroNode;
		
	}
	
	public void list() {
		if(head.next == null){
			System.out.println("����Ϊ��");
		}
		
//	ͷ��㲻������Ҫ��������������
		HeroNode temp = head.next;
		while(true) {
//		�ж��Ƿ��������
			if(temp == null) {
				break;
			}
//		����ڵ����Ϣ
			System.out.println(temp);
//		��temp����
			temp = temp.next;
		}
	}
	
}

class HeroNode{
	public int no;
	public String name;
	public String nickname;
	public HeroNode next;
	
	public HeroNode(int no,String name,String nickname) {
		this.no = no;
		this.name = name;
		this.nickname = nickname;
	}
	
	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
	}
}