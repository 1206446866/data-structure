package graph;

import matrix.Triple;
import seqlist.SeqList;

public abstract class AbstractGraph<T> implements Graph<T> 
{
    protected static final int MAX_WEIGHT=0x00ffffff; //���Ȩֵ����ʾ�����ޣ�
                             ////ע�⣺������Integer.MAX_VALUE����Ϊ���·��Ҫ�ӣ�������ɸ���
    //public 
    protected SeqList<T> vertexlist;             //����˳����洢ͼ�Ķ��㼯��
    
    public AbstractGraph()                       //�����ͼ��������Ϊ0
    {
        this.vertexlist = new SeqList<T>();      //�����˳���Ĭ������
    }
    
    public int vertexCount()                     //����ͼ�Ķ�����
    {
        return this.vertexlist.size();           //���ض���˳����Ԫ�ظ���
    }

    public String toString()                     //����ͼ�Ķ��㼯�������ַ���
    {
        return "���㼯�ϣ�"+this.vertexlist.toString()+"\n";
    }

    public T get(int i)                          //���ض���viԪ�أ���iԽ�磬�򷵻�null
    {
        return this.vertexlist.get(i);
    }////������

    public void set(int i, T x)                  //���ö���viԪ��Ϊx
    {
        this.vertexlist.set(i,x);                //��iԽ�磬���׳��쳣
    }

    public int search(T key)                     //���Ҳ������׸���key���Ԫ�صĶ������
    {
    	return this.vertexlist.search(key);
    }
    
    public T remove(T key)                       //���Ҳ�ɾ���׸���key���Ԫ�ض��㼰������ı�
    {
        return this.remove(this.search(key));    //ɾ������vi��������ıߣ����󷽷���������ʵ��
    } 
    
    //����vi��vj��ĺ���ڽӶ�����ţ���j=-1������vi�ĵ�0���ڽӶ�����ţ��������ں���ڽӶ��㣬����-1
    protected abstract int next(int i, int j);
    
	@SuppressWarnings("unused")
	private static String toString(int[] value)  //�������ֵ����ʾ�м���
    {
        if(value!=null && value.length>0)
        {
            String str="{";
            int i=0;
            for(i=0; i<value.length-1; i++)
                str += (value[i]==MAX_WEIGHT ? "��" : value[i])+",";
            return str+(value[i]==MAX_WEIGHT ? "��" : value[i])+"}";
        }
        return null;        
    }
    public static String toString(int[][] value) 
    {
        String str="";
        for(int i=0; i<value.length; i++) 
        {
            for(int j=0; j<value[i].length; j++) 
                str += value[i][j]==MAX_WEIGHT ? "     ��" : String.format("%6d",value[i][j]);
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
    	System.out.println("\n��С�������ı߼���:");
    	int mincost = 0;
    	for(int i = 0;i < mst.length;i++) {
    		System.out.print(mst[i]+" ");
    		mincost += mst[i].value;
    	}
    	System.out.println(",��С����Ϊ" + mincost);
    }
}