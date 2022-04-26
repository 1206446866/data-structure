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
//	先初始化一个头结点，头结点不动
	private HeroNode head = new HeroNode(0, "", "");
	
//		添加结点到单向链表
//	1.找到当前链表的最后结点
//	2.将最后这个结点的next 指向新的结点
	public void add(HeroNode heroNode) {
//		需要一个辅助遍历temp
		HeroNode temp = head;
//		遍历链表，找到最后
		while(true) {
//			找链表的最后
			if(temp.next == null) {
				break;
			}
//			若没找到最后，将temp后移
			temp = temp.next;
		}
//		当退出while循环是，temp就指向了链表的最后
//		将最后这个节点的next指向新节点
		temp.next = heroNode;
		
	}
	
	public void list() {
		if(head.next == null){
			System.out.println("链表为空");
		}
		
//	头结点不动，需要辅助变量来遍历
		HeroNode temp = head.next;
		while(true) {
//		判断是否到链表最后
			if(temp == null) {
				break;
			}
//		输出节点的信息
			System.out.println(temp);
//		将temp后移
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