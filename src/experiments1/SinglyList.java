package experiments1;

/**
 * @author Bugott
 * 简单实现链表
 */
public class SinglyList<T> {
    public Node<T> head;

    public SinglyList() {
        this.head = new Node<T>();
    }

    public SinglyList(T[] values) {
        this();
        Node<T> rear = this.head;
        for (int i = 0; i < values.length; i++) {
            if (values[i] != null) {
                rear.next = new Node<T>(values[i], null);
                rear = rear.next;
            }
        }
    }

    public boolean isEmpty() {
        return this.head.next == null;
    }

    public T get(int i) {
        Node<T> p = this.head.next;
        for (int j = 0; p != null && j < i; j++) {
            p = p.next;
        }
        return (i >= 0 && p != null) ? p.data : null;
    }

    @Override
    public String toString() {
        String str = this.getClass().getName() + "(";
        for (Node<T> p = this.head.next; p != null; p = p.next) {
            str += p.data.toString();
            if (p.next != null) {
                str += ",";
            }
        }
        return str + ")";
    }

    public Node<T> insert(int i, T x) {
        if (x == null) {
            throw new NullPointerException("x==null");
        }
        Node<T> front = this.head;
        for (int j = 0; front.next != null && j < i; j++) {
            front = front.next;
        }
        front.next = new Node<T>(x, front.next);
        return front.next;
    }

    public Node<T> insert(T x) {
        if (x == null) {
            throw new NullPointerException("x==null");
        }
        Node<T> front = this.head;
        for (int j = 0; front.next != null; j++) {
            front = front.next;
        }
        front.next = new Node<T>(x, front.next);
        return front.next;
    }

    public T remove(int i) {
        Node<T> front = this.head;
        for (int j = 0; front.next != null && j < i; j++) {
            front = front.next;
        }
        if (i >= 0 && front.next != null) {
            T old = front.next.data;
            front.next = front.next.next;
            return old;
        }
        return null;
    }

    public Node<T> search(T key) {
        for (Node<T> p = this.head.next; p != null; p = p.next) {
            if (key.equals(p.data)) {
                return p;
            }
        }
        return null;
    }

    public void replaceAll(T key, T x) {
        Node<T> find = this.search(x);
        while (find != null) {
            find.data = x;
            find = this.search(x);
        }
    }

    public int size() {
        int i = 0;
        for (Node<T> p = this.head.next; p != null; p = p.next) {
            i++;
        }
        return i;
    }

    public void replaceAll1(T key, T x) {
        for (Node<T> p = this.head.next; p != null; p = p.next) {
            if (key.equals(p.data)) {
                p.data = x;
            }
        }
    }
}