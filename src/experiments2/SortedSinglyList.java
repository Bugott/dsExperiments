package experiments2;

import experiments1.Node;
import experiments1.SinglyList;


//排序单链表类（升序），T或T的某个祖先类实现Comparable<T>接口；继承单链表类
public class SortedSinglyList<T extends Comparable<? super T>> extends SinglyList<T>
{    
    public SortedSinglyList()                              //构造空排序单链表
    {
        super();                                           //默认调用父类构造方法SinglyList()
    }
    
    public SortedSinglyList(T[] values)                    //构造，按值插入values数组元素。直接插入排序算法
    {
        super();                                           //构造空单链表，默认调用SinglyList()
        for (int i=0;  i<values.length;  i++)              //直接插入排序，每趟插入1个元素
            this.insert(values[i]);                        //排序单链表按值插入
    }

    //由单链表list构造（深拷贝），重载构造方法。直接插入排序
    public SortedSinglyList(SinglyList<T> list)
    {
        super();                                           //构造空单链表
        for (Node<T> p=list.head.next;  p!=null;  p=p.next)//直接插入排序，每趟插入1个元素
            this.insert(p.data);                           //排序单链表按值插入
    }
    
    //不支持父类的以下两个方法，将其覆盖并抛出异常。方法体同排序顺序表
    public void set(int i, T x) 
    {
        throw new UnsupportedOperationException("set(int i, T x)");
    }
    @Override
    public Node<T> insert(int i, T x)
    {
        throw new UnsupportedOperationException("insert(int i, T x)");
    }
    
    //插入x，x!=null，根据x对象大小顺序查找确定插入位置，插入在等值结点之前。
    //返回插入结点。O(n)。覆盖父类的insert(x)方法
    @Override
    public Node<T> insert(T x)
    {
        Node<T> front=this.head, p=front.next;             //front指向p的前驱结点
        while (p!=null && x.compareTo(p.data)>0)           //寻找插入位置
        {
            front = p;
            p = p.next;
        }
        front.next = new Node<T>(x, p);                    //在front之后、p之前插入值为x结点
        return front.next;                                 //返回插入结点
    }
    
    //顺序查找首个与key相等元素，返回结点，若查找不成功返回null，O(n)。覆盖。    //用于5.2.2节稀疏矩阵行的单链表
    @Override
    public Node<T> search(T key)
    {
        for (Node<T> p=this.head.next;  p!=null && key.compareTo(p.data)>=0;  p=p.next)
        {
//            System.out.print(p.data+"？");
            if (key.compareTo(p.data)==0)                  //由compareTo()提供比较对象大小和相等的依据
                return p;
        }
        return null; 
    }
    
    //插入不重复元素x，返回x结点；覆盖。//5.2.2节矩阵行的单链表可用，第4版教材没有写
    public Node<T> insertDifferent(T x)
    {
        Node<T> front=this.head, p=front.next;             //front指向p的前驱结点
        while (p!=null && x.compareTo(p.data)>0)           //寻找插入位置
        {
            front = p;
            p = p.next;
        }
        if (p!=null && x.compareTo(p.data)==0)
            return p;                                      //查找成功，元素重复，不插入，返回该结点
        return front.next = new Node<T>(x, p);             //在front之后、p之前插入值为x结点，返回插入结点
    }

    //删除首个与key相等元素结点，返回被删除元素，查找不成功返回null。O(n)。覆盖
    public T remove(T key)
    {
        Node<T> front=this.head, p=front.next;             //front是p的前驱结点
        while (p!=null && key.compareTo(p.data)>0)         //查找与key相等元素结点（p指向）
        {
            front = p;
            p = p.next;
        }
        if (p!=null && key.compareTo(p.data)==0)           //若查找成功，删除p结点，返回被删除元素
        {
            front.next = p.next;                           //删除p结点
            return p.data;
        }
        return null;
    }
 
    public void concat(SinglyList<T> list)                 //覆盖，不支持直接合并连接，抛出异常
    {
        throw new UnsupportedOperationException("concat(SinglyList<T> list)");
    }
    //复制list所有元素按值插入到this排序单链表，不改变list，集合并。
    //覆盖，功能与父类相同，算法实现不同，每次查找全部元素后再插入，O(n*n)
    public void addAll(SinglyList<T> list)
    {
        for (Node<T> p=list.head.next;  p!=null;  p=p.next)//直接插入排序，每趟插入1个元素
            this.insert(p.data);                           //排序单链表按值插入
    }    
    
    //返回复制this和list所有结点的排序单链表，并集，不改变this和list。
    //覆盖，返回子类实例。覆盖方法的返回值类型必须与父类方法赋值相容
    public SortedSinglyList<T> union(SinglyList<T> list)
    {
        SortedSinglyList<T> result = new SortedSinglyList<T>(this);   //排序单链表深拷贝
        result.addAll(list);
        return result;
    }
    
    //9.5.2算法描述、程序
    public void merge(SortedSinglyList<T> list)            //将list所有结点归并到this中，归并后设置list为空
    {
        Node<T> front=this.head, p=front.next;             //p遍历this单链表，front是p的前驱
        Node<T> q=list.head.next;                          //q遍历list单链表
        while (p!=null && q!=null)                         //遍历两条排序单链表
            if ((p.data).compareTo(q.data)<0)              //若p结点值小，则p继续前进
            {
                front = p;
                p = p.next;
            }
            else
            {                                              //否则，将q结点插入到front结点之后
                front.next = q;
                q = q.next;
                front = front.next;
                front.next = p;
            }
        if (q!=null)                                       //将list中剩余结点并入this尾
            front.next=q;
        list.head.next=null;                               //设置list为空单链表
    }
    
    //【实验题9-3】 
    //返回this和list归并后的排序单链表，不改变this和list。效率高，简化
    public SortedSinglyList<T> mergeWith(SortedSinglyList<T> list)  
    {
        Node<T> p=this.head.next, q=list.head.next;        //p、q分别遍历this、list单链表
        SortedSinglyList<T> result = new SortedSinglyList<T>();
        Node<T> rear = result.head;                        //rear指向result单链表尾，准备插入
        while (p!=null || q!=null)                         //p、q分别遍历this、list单链表
            if (p!=null && (q!=null && (p.data).compareTo(q.data)<=0 || q==null))
            {                                              //复制this结点到result，若p结点值小，或q已结束
                rear.next = new Node<T>(p.data,null);
                rear = rear.next;
                p = p.next;
            }
            else if (q!=null && (p!=null && (p.data).compareTo(q.data)>0 || p==null))
            {                                              //否则，复制list结点到result，若q结点值小，或p已结束
                rear.next = new Node<T>(q.data,null);
                rear = rear.next;
                q = q.next;
            }
        return result;
    }
}