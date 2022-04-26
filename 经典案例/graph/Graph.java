package graph;

//§7.1.2   图抽象数据类型
//图接口
public interface Graph<T>                        //图接口，表示图抽象数据类型，T指定顶点元素类型
{
    int vertexCount();                           //返回顶点数
    T get(int i);                                //返回顶点vi元素
    void set(int i, T x);                        //设置顶点vi元素为x
    int insert(T x);                             //插入元素值为x的顶点，返回顶点序号
    void insert(int i, int j, int w);            //插入边〈vi,vj〉，权值为w
    T remove(int i);                             //删除顶点vi及其关联的边
    void remove(int i, int j);                   //删除边〈vi,vj〉
    int search(T key);                           //查找并返回首个与key相等元素的顶点序号
    T remove(T key);                             //查找并删除首个与key相等元素顶点及其关联的边
    int weight(int i, int j);                    //返回〈vi,vj〉边的权值
    void depthfs(int i,boolean[] visited);
    void DFSTraverse(int i);
    void BFSTraverse(int i);
	void breadthfs(int i, boolean[] visited, ArrayQueue que);
    
    
}
