package graph;

import matrix.Triple;
import seqlist.SeqList;

public abstract class AbstractGraph<T> implements Graph<T> 
{
    protected static final int MAX_WEIGHT=0x00ffffff; //最大权值（表示无穷大∞）
                             ////注意：不能用Integer.MAX_VALUE，因为最短路径要加，会溢出成负数
    //public 
    protected SeqList<T> vertexlist;             //顶点顺序表，存储图的顶点集合
    
    public AbstractGraph()                       //构造空图，顶点数为0
    {
        this.vertexlist = new SeqList<T>();      //构造空顺序表，默认容量
    }
    
    public int vertexCount()                     //返回图的顶点数
    {
        return this.vertexlist.size();           //返回顶点顺序表的元素个数
    }

    public String toString()                     //返回图的顶点集合描述字符串
    {
        return "顶点集合："+this.vertexlist.toString()+"\n";
    }

    public T get(int i)                          //返回顶点vi元素；若i越界，则返回null
    {
        return this.vertexlist.get(i);
    }////遍历用

    public void set(int i, T x)                  //设置顶点vi元素为x
    {
        this.vertexlist.set(i,x);                //若i越界，则抛出异常
    }

    public int search(T key)                     //查找并返回首个与key相等元素的顶点序号
    {
    	return this.vertexlist.search(key);
    }
    
    public T remove(T key)                       //查找并删除首个与key相等元素顶点及其关联的边
    {
        return this.remove(this.search(key));    //删除顶点vi及其关联的边，抽象方法，待子类实现
    } 
    
    //返回vi在vj后的后继邻接顶点序号；若j=-1，返回vi的第0个邻接顶点序号；若不存在后继邻接顶点，返回-1
    protected abstract int next(int i, int j);
    
	@SuppressWarnings("unused")
	private static String toString(int[] value)  //输出数组值，显示中间结果
    {
        if(value!=null && value.length>0)
        {
            String str="{";
            int i=0;
            for(i=0; i<value.length-1; i++)
                str += (value[i]==MAX_WEIGHT ? "∞" : value[i])+",";
            return str+(value[i]==MAX_WEIGHT ? "∞" : value[i])+"}";
        }
        return null;        
    }
    public static String toString(int[][] value) 
    {
        String str="";
        for(int i=0; i<value.length; i++) 
        {
            for(int j=0; j<value[i].length; j++) 
                str += value[i][j]==MAX_WEIGHT ? "     ∞" : String.format("%6d",value[i][j]);
            str+="\n";
        }
        return str;
    }    
    
    public void minSpanTree() {
    	Triple[] mst = new Triple[vertexCount()-1];
    	for(int i =0;i<mst.length;i++)
    		mst[i] = new Triple(0,i+1,this.weight(0, i+1));
    	for(int i = 0;i<mst.length;i++) {
    		int min = i;
    		for(int j = i+1;j<mst.length;j++)
    			if(mst[j].value < mst[min].value)
    				min = j;
    		Triple edge = mst[min];
    		if(min!=i) {
    			mst[min] = mst[i];
    			mst[i] = edge;
    		}
    		int tv = edge.column;
    		for(int j=i+1;j<mst.length;j++) {
    			int v = mst[j].column,w;
    			if((w=weight(tv, v)) < mst[j].value)
    				mst[j] = new Triple(tv,v,w);
    		}
    	}
    	System.out.println("\n最小生成树的边集合:");
    	int mincost = 0;
    	for(int i = 0;i < mst.length;i++) {
    		System.out.print(mst[i]+" ");
    		mincost += mst[i].value;
    	}
    	System.out.println(",最小代价为" + mincost);
    }
}