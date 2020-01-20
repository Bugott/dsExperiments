package experiments1;

/**
 * @author Bugott
 * 简单实现顺序表
 */
public class SeqList<E> extends Object {
    protected Object[] element;
    public int n;

    public SeqList(int length) {
        this.element = new Object[length];
        this.n = 0;
    }

    public SeqList() {
        this(64);
    }

    public SeqList(E[] values) {
        this(values.length);
        for (int i = 0; i < values.length; i++)
            this.element[i] = values[i];
        this.n = element.length;
    }

    public boolean isEmpty() {
        return this.n == 0;
    }

    public int size() {
        return this.n;
    }

    public E get(int i) {
        if (i >= 0 && i < this.n)
            return (E) this.element[i];
        return null;
    }

    public void set(int i, E x) {
        if (x == null)
            throw new NullPointerException("x==null");
        if (i >= 0 && i < this.n)
            this.element[i] = x;
        else
            throw new java.lang.IndexOutOfBoundsException(i + "");
    }

    public String toString() {
        String str = this.getClass().getName() + "(";
        if (this.n > 0)
            str += this.element[0].toString();
        for (int i = 1; i < this.n; i++) {
            str += "," + this.element[i].toString();
            if (i % 4 == 0)
                str += "\n";
        }

        return str + ")";
    }

    public E remove(int i) {
        if (this.n > 0 && i >= 0 && i < this.n) {
            E old = ((E) this.element[i]);
            for (int j = i; j < this.n - 1; j++)
                this.element[j] = this.element[j + 1];
            this.element[this.n - 1] = null;
            this.n--;
            return old;
        }
        return null;
    }

    public int insert(int i, E x) {
        if (x == null)
            throw new NullPointerException("x==null");
        if (i < 0)
            i = 0;
        if (i > this.n)
            i = this.n;
        Object[] source = this.element;
        if (this.n == element.length) {
            this.element = new Object[source.length * 2];
            for (int j = 0; j < i; j++)
                this.element[j] = source[j];
        }
        for (int j = this.n - 1; j >= i; j--)
            this.element[j + 1] = source[j];
        this.element[i] = x;
        this.n++;
        return i;
    }

    public int insert(E x) {
        return this.insert(this.n, x);
    }

    public void append(E element) {
        insert(n, element);
    }

    public void removeAll(E key) {
        int i = search(key);
        if (i != -1)
            for (int j = i + 1; j < n; j++) {
                if (!element[i].equals(key)) {
                    element[i++] = element[j];
                }
            }
    }

    public int search(E key) {
        for (int i = 0; i < this.n; i++) {
            if (key.equals(this.element[i]))
                return i;
        }
        return -1;
    }

    public void replaceFirst(E key, E x) {
        if (key != null && x != null) {
            int i = this.search(key);
            this.element[i] = x;
        }
    }

    public void replaceAll(E key, E x) {
        if (key != null && x != null)
            for (int i = 0; i < this.n; i++)
                if (key.equals(this.element[i]))
                    this.element[i] = x;
    }

    public int searchLast(E key) {
        for (int i = n - 1; i >= 0; i--)
            if (key.equals(this.element[i]))
                return i;
        return -1;
    }

    public void removeLast(E key) {
        int i = this.searchLast(key);
        this.remove(i);
    }

    public void replaceLast(E key, E x) {
        if (key != null && x != null) {
            int i = this.searchLast(key);
            this.element[i] = x;
        }
    }
}
