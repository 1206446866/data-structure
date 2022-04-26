package graph;

import matrix.LinkedMatrix;
import matrix.Triple;
import queue.Queue;
import queue.SeqQueue;
import singlyList.Node;
import singlyList.SortedSinglyList;

public class AdjListGraph<T> extends AbstractGraph<T>{
	protected LinkedMatrix linkmat;
	public AdjListGraph() {
		super();
		this.linkmat = new LinkedMatrix(0,0);
	}
	
	public AdjListGraph(T[] vertexes) {
		this();
		for (int i = 0; i < vertexes.length; i++)
			this.insert(vertexes[i]);
	}
	
	public AdjListGraph(T[] vertexes,Triple[] edges) {
		this(vertexes);
		for (int j = 0; j < edges.length; j++)
			this.insert(edges[j]);
	}
	
	public AdjListGraph(T[] vertexes,String edges) {
		super();
		this.linkmat = new LinkedMatrix(vertexes.length,vertexes.length,edges);
	}
	
	@Override
	public String toString() {
		return super.toString()+"出边表：\n"+this.linkmat.toString();
	}
	
	@Override
	public int insert(T x) {
		this.vertexlist.insert(x);
		int i = this.vertexlist.size()-1;
		if(i >= this.linkmat.getRows())
			this.linkmat.setRowColumns(i+1, i+1);
		return i;
	}
	
	@Override
	public void insert(int i, int j, int w) {
		if(i!=j) {
			if(w<0 || w>=MAX_WEIGHT)
				w=0;
			this.linkmat.set(i,j,w);
		}else throw new IllegalArgumentException("不能插入自身环，i="+i+",j="+j);
	}
	
	public void insert(Triple edge) {
		this.insert(edge.row, edge.column, edge.value);
	}
	
	public void remove(Triple edge) {
		this.remove(edge.row, edge.column);
	}
	
	@Override
	public T remove(int i) {
		int n = this.vertexCount();
		if(i>=0 && i<n) {
			T x = this.vertexlist.get(n-1);
			this.vertexlist.set(i, x);
			x = this.vertexlist.remove(n-1);
			SortedSinglyList<Triple> link = this.linkmat.rowlist.get(i);
			for(Node<Triple> p = link.head.next;p!=null;p=p.next)
				this.remove(p.data.toSymmetry());
			link = this.linkmat.rowlist.get(n-1);
			for(Node<Triple> p = link.head.next;p!=null;p=p.next) {
				Triple edge = p.data.toSymmetry();
				this.remove(edge);
				edge.column = i;
				this.insert(edge);
				p.data.row = i;
			}
			this.linkmat.rowlist.set(i, this.linkmat.rowlist.get(n-1));
			this.linkmat.rowlist.remove(n-1);
			this.linkmat.setRowColumns(n-1, n-1);
			return x;
		}else throw new IndexOutOfBoundsException("i="+i);
	}
	
	@Override
	public void remove(int i, int j) {
		if(i!=j)
			this.linkmat.set(new Triple(i,j,0));
	}
	
	@Override
	public int weight(int i, int j) {
		if(i==j)
			return 0;
		int w = this.linkmat.get(i, j);
		return w!=0? w : MAX_WEIGHT;
	}
	
	@Override
	public void depthfs(int i, boolean[] visited) {
		System.out.println(this.get(i)+" ");
		visited[i] = true;
		for(int j=next(i,-1);j!=-1;j=next(i,j))
			if(!visited[j])
				depthfs(j,visited);
	}
	
	@Override
	public void DFSTraverse(int i) {
		if(i<0 || i>=this.vertexCount())
			return ;
		boolean[] visited = new boolean[this.vertexCount()];
		int j = i;
		do {
			if(!visited[j]) {
				System.out.println("{");
				this.depthfs(j, visited);
				System.out.println("}");
			}
			j = (j+1)%this.vertexCount();
		}while(j!=i);
		System.out.println();
	}
	
	public void breadthfs(int i, boolean[] visited, Queue<Integer> que) {
		System.out.println(this.get(i)+" ");
		visited[i] = true;
		que.add(i);
		while(!que.isEmpty()) {
			i = (int) que.poll();
			for(int j=next(i,-1);j!=-1;j=next(i,j)) {
				if(!visited[j]) {
					System.out.println(this.get(j)+" ");
					visited[j] = true;
					que.add(j);
				}
			}
		}
	}
	
	@Override
	public void BFSTraverse(int i) {
		if(i<0 || i>=this.vertexCount())
			return ;
		boolean[] visited = new boolean[this.vertexCount()];
		Queue<Integer> que = new SeqQueue<Integer>(this.vertexCount()-1);
		int j = i;
		do {
			if(!visited[j]) {
				System.out.println("{");
				this.breadthfs(j, visited,que);
				System.out.println("}");
			}
				j = (j+1)%this.vertexCount();
		}while(j!=i);
		System.out.println();
	}
	@Override
	protected int next(int i, int j) {
		int n = this.vertexCount();
		if(i>=0 && i<n && j>=-1 && i!=j) {
			SortedSinglyList<Triple> link = this.linkmat.rowlist.get(i);
			Node<Triple> find = link.head.next;
			if(j==-1)
				return find!=null? find.data.column : -1;
			find = link.search(new Triple(i,j,0));
			if(find!=null &&(find=find.next)!=null)
				return find.data.column;
		}
		return -1;
	}

	@Override
	public void breadthfs(int i, boolean[] visited, ArrayQueue que) {
		// TODO Auto-generated method stub
		
	}
}
