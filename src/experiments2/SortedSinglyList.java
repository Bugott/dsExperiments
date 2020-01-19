package experiments2;

import experiments1.Node;
import experiments1.SinglyList;


//���������ࣨ���򣩣�T��T��ĳ��������ʵ��Comparable<T>�ӿڣ��̳е�������
public class SortedSinglyList<T extends Comparable<? super T>> extends SinglyList<T>
{    
    public SortedSinglyList()                              //�������������
    {
        super();                                           //Ĭ�ϵ��ø��๹�췽��SinglyList()
    }
    
    public SortedSinglyList(T[] values)                    //���죬��ֵ����values����Ԫ�ء�ֱ�Ӳ��������㷨
    {
        super();                                           //����յ�����Ĭ�ϵ���SinglyList()
        for (int i=0;  i<values.length;  i++)              //ֱ�Ӳ�������ÿ�˲���1��Ԫ��
            this.insert(values[i]);                        //��������ֵ����
    }

    //�ɵ�����list���죨����������ع��췽����ֱ�Ӳ�������
    public SortedSinglyList(SinglyList<T> list)
    {
        super();                                           //����յ�����
        for (Node<T> p=list.head.next;  p!=null;  p=p.next)//ֱ�Ӳ�������ÿ�˲���1��Ԫ��
            this.insert(p.data);                           //��������ֵ����
    }
    
    //��֧�ָ���������������������串�ǲ��׳��쳣��������ͬ����˳���
    public void set(int i, T x) 
    {
        throw new UnsupportedOperationException("set(int i, T x)");
    }
    @Override
    public Node<T> insert(int i, T x)
    {
        throw new UnsupportedOperationException("insert(int i, T x)");
    }
    
    //����x��x!=null������x�����С˳�����ȷ������λ�ã������ڵ�ֵ���֮ǰ��
    //���ز����㡣O(n)�����Ǹ����insert(x)����
    @Override
    public Node<T> insert(T x)
    {
        Node<T> front=this.head, p=front.next;             //frontָ��p��ǰ�����
        while (p!=null && x.compareTo(p.data)>0)           //Ѱ�Ҳ���λ��
        {
            front = p;
            p = p.next;
        }
        front.next = new Node<T>(x, p);                    //��front֮��p֮ǰ����ֵΪx���
        return front.next;                                 //���ز�����
    }
    
    //˳������׸���key���Ԫ�أ����ؽ�㣬�����Ҳ��ɹ�����null��O(n)�����ǡ�    //����5.2.2��ϡ������еĵ�����
    @Override
    public Node<T> search(T key)
    {
        for (Node<T> p=this.head.next;  p!=null && key.compareTo(p.data)>=0;  p=p.next)
        {
//            System.out.print(p.data+"��");
            if (key.compareTo(p.data)==0)                  //��compareTo()�ṩ�Ƚ϶����С����ȵ�����
                return p;
        }
        return null; 
    }
    
    //���벻�ظ�Ԫ��x������x��㣻���ǡ�//5.2.2�ھ����еĵ�������ã���4��̲�û��д
    public Node<T> insertDifferent(T x)
    {
        Node<T> front=this.head, p=front.next;             //frontָ��p��ǰ�����
        while (p!=null && x.compareTo(p.data)>0)           //Ѱ�Ҳ���λ��
        {
            front = p;
            p = p.next;
        }
        if (p!=null && x.compareTo(p.data)==0)
            return p;                                      //���ҳɹ���Ԫ���ظ��������룬���ظý��
        return front.next = new Node<T>(x, p);             //��front֮��p֮ǰ����ֵΪx��㣬���ز�����
    }

    //ɾ���׸���key���Ԫ�ؽ�㣬���ر�ɾ��Ԫ�أ����Ҳ��ɹ�����null��O(n)������
    public T remove(T key)
    {
        Node<T> front=this.head, p=front.next;             //front��p��ǰ�����
        while (p!=null && key.compareTo(p.data)>0)         //������key���Ԫ�ؽ�㣨pָ��
        {
            front = p;
            p = p.next;
        }
        if (p!=null && key.compareTo(p.data)==0)           //�����ҳɹ���ɾ��p��㣬���ر�ɾ��Ԫ��
        {
            front.next = p.next;                           //ɾ��p���
            return p.data;
        }
        return null;
    }
 
    public void concat(SinglyList<T> list)                 //���ǣ���֧��ֱ�Ӻϲ����ӣ��׳��쳣
    {
        throw new UnsupportedOperationException("concat(SinglyList<T> list)");
    }
    //����list����Ԫ�ذ�ֵ���뵽this�����������ı�list�����ϲ���
    //���ǣ������븸����ͬ���㷨ʵ�ֲ�ͬ��ÿ�β���ȫ��Ԫ�غ��ٲ��룬O(n*n)
    public void addAll(SinglyList<T> list)
    {
        for (Node<T> p=list.head.next;  p!=null;  p=p.next)//ֱ�Ӳ�������ÿ�˲���1��Ԫ��
            this.insert(p.data);                           //��������ֵ����
    }    
    
    //���ظ���this��list���н��������������������ı�this��list��
    //���ǣ���������ʵ�������Ƿ����ķ���ֵ���ͱ����븸�෽����ֵ����
    public SortedSinglyList<T> union(SinglyList<T> list)
    {
        SortedSinglyList<T> result = new SortedSinglyList<T>(this);   //�����������
        result.addAll(list);
        return result;
    }
    
    //9.5.2�㷨����������
    public void merge(SortedSinglyList<T> list)            //��list���н��鲢��this�У��鲢������listΪ��
    {
        Node<T> front=this.head, p=front.next;             //p����this������front��p��ǰ��
        Node<T> q=list.head.next;                          //q����list������
        while (p!=null && q!=null)                         //����������������
            if ((p.data).compareTo(q.data)<0)              //��p���ֵС����p����ǰ��
            {
                front = p;
                p = p.next;
            }
            else
            {                                              //���򣬽�q�����뵽front���֮��
                front.next = q;
                q = q.next;
                front = front.next;
                front.next = p;
            }
        if (q!=null)                                       //��list��ʣ���㲢��thisβ
            front.next=q;
        list.head.next=null;                               //����listΪ�յ�����
    }
    
    //��ʵ����9-3�� 
    //����this��list�鲢��������������ı�this��list��Ч�ʸߣ���
    public SortedSinglyList<T> mergeWith(SortedSinglyList<T> list)  
    {
        Node<T> p=this.head.next, q=list.head.next;        //p��q�ֱ����this��list������
        SortedSinglyList<T> result = new SortedSinglyList<T>();
        Node<T> rear = result.head;                        //rearָ��result������β��׼������
        while (p!=null || q!=null)                         //p��q�ֱ����this��list������
            if (p!=null && (q!=null && (p.data).compareTo(q.data)<=0 || q==null))
            {                                              //����this��㵽result����p���ֵС����q�ѽ���
                rear.next = new Node<T>(p.data,null);
                rear = rear.next;
                p = p.next;
            }
            else if (q!=null && (p!=null && (p.data).compareTo(q.data)>0 || p==null))
            {                                              //���򣬸���list��㵽result����q���ֵС����p�ѽ���
                rear.next = new Node<T>(q.data,null);
                rear = rear.next;
                q = q.next;
            }
        return result;
    }
}