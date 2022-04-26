package graph;

import matrix.Matrix;
import matrix.Triple;

public class MatrixGraph<T> extends AbstractGraph<T> // ��ȫ����
{
	protected Matrix matrix;

	public MatrixGraph() {
		super();
		this.matrix = new Matrix(0, 0);
	}

	public MatrixGraph(T[] vertexes) {
		this();
		for (int i = 0; i < vertexes.length; i++)
			this.insert(vertexes[i]);
	}

	public MatrixGraph(T[] vertexes, Triple[] edges) {
		this(vertexes);
		for (int j = 0; j < edges.length; j++)
			this.insert(edges[j]);
	}

	public int insert(T x) {
		this.vertexlist.insert(x);
		int i = this.vertexlist.size() - 1;
		if (i >= this.matrix.getRows())
			this.matrix.setRowsColumns(i + 1, i + 1);
		for (int j = 0; j < i; j++) {
			this.matrix.set(i, j, MAX_WEIGHT);
			this.matrix.set(j, i, MAX_WEIGHT);
		}
		return i;
	}

	public void insert(int i, int j, int w) {
		if (i != j) {
			if (w <= 0 || w > MAX_WEIGHT)
				w = MAX_WEIGHT;
			this.matrix.set(i, j, w);
		} else
			throw new IllegalArgumentException("���ܲ�������,i=" + i + ",j=" + j);
	}

	public void insert(Triple edge) {
		this.insert(edge.row, edge.column, edge.value);
	}

	public T remove(int i) {
		int n = this.vertexCount();
		if (i >= 0 && i < n) {
			T x = this.vertexlist.get(n - 1);
			this.vertexlist.set(i, x);
			x = this.vertexlist.remove(n - 1);
			for (int j = 0; j < n; j++)
				this.matrix.set(i, j, this.matrix.get(n - 1, j));
			for (int j = 0; j < n; j++)
				this.matrix.set(j, i, this.matrix.get(j, n - 1));
			this.matrix.setRowsColumns(n - 1, n - 1);
			return x;
		} else
			throw new IndexOutOfBoundsException("i=" + i);
	}

	public void remove(int i, int j) {
		if (i != j)
			this.matrix.set(i, j, MAX_WEIGHT);
	}

	public void remove(Triple edge) {
		this.remove(edge.row, edge.column);
	}

	public int weight(int i, int j) {
		return this.matrix.get(i, j);
	}

	protected int next(int i, int j) {
		int n = this.vertexCount();
		if (i >= 0 && i < n && j >= -1 && j < n && i != j)
			for (int k = j + 1; k < n; k++)
				if (this.matrix.get(i, k) > 0 && this.matrix.get(i, k) < MAX_WEIGHT)
					return k;
		return -1;

	}

	@Override
	public void depthfs(int i, boolean[] visited) {
		System.out.print(this.get(i)+" ");
		visited[i] = true;
		for(int j = next(i, -1);j!=-1;j=next(i, j)) {
			if(!visited[i]) {
				depthfs(j, visited);
			}
		}
		
	}

	@Override
	public void DFSTraverse(int i) {
		if(i<0 || i>=this.vertexCount())
			return;
		boolean[] visited = new boolean[this.vertexCount()];
		int j = i;
		System.out.print("{");
		do {
			if(!visited[j]) {
				this.depthfs(j, visited);
			}
			j = (j+1) % this.vertexCount();
		}while(j!=i) ;
		System.out.print("}");
		System.out.println();
		
	}

	@Override
	public void breadthfs(int i, boolean[] visited, ArrayQueue que) {
		System.out.print(this.get(i)+" ");
		visited[i] = true;
		que.addQueue(i);
		while(!que.isEmpty()) {
			i = que.getQueue();
			for(int j = next(i, -1);j!=-1;j=next(i, j)) {
				if(!visited[j]) {
					System.out.print(this.get(j)+" ");
					visited[j] = true;
					que.addQueue(j);
				}
			}
		}
		
	}

	@Override
	public void BFSTraverse(int i) {
		if(i<0 || i>=this.vertexCount())
			return;
		boolean[] visited = new boolean[this.vertexCount()];
		ArrayQueue que = new ArrayQueue(this.vertexCount()-1);
		int j = i;
		do {
			if(!visited[j]) {
				System.out.print("{");
				breadthfs(j, visited, que);
				System.out.print("}");
			}
			j = (j+1) % this.vertexCount();
		}while(j!=i);
		System.out.println();
		
	}

	public String toString() {
		String str = super.toString() + "�ڽӾ���:  \n";
		int n = this.vertexCount();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (this.matrix.get(i, j) == MAX_WEIGHT)
					str += "     ��";
				else
					str += String.format("%6d", this.matrix.get(i, j));
	
			}
			str += "\n";
		}
		return str;
	}
}