package graph;

//��7.1.2   ͼ������������
//ͼ�ӿ�
public interface Graph<T>                        //ͼ�ӿڣ���ʾͼ�����������ͣ�Tָ������Ԫ������
{
    int vertexCount();                           //���ض�����
    T get(int i);                                //���ض���viԪ��
    void set(int i, T x);                        //���ö���viԪ��Ϊx
    int insert(T x);                             //����Ԫ��ֵΪx�Ķ��㣬���ض������
    void insert(int i, int j, int w);            //����ߡ�vi,vj����ȨֵΪw
    T remove(int i);                             //ɾ������vi��������ı�
    void remove(int i, int j);                   //ɾ���ߡ�vi,vj��
    int search(T key);                           //���Ҳ������׸���key���Ԫ�صĶ������
    T remove(T key);                             //���Ҳ�ɾ���׸���key���Ԫ�ض��㼰������ı�
    int weight(int i, int j);                    //���ء�vi,vj���ߵ�Ȩֵ
    void depthfs(int i,boolean[] visited);
    void DFSTraverse(int i);
    void BFSTraverse(int i);
	void breadthfs(int i, boolean[] visited, ArrayQueue que);
    
    
}
