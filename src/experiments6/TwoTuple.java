package experiments6;

public class TwoTuple<A, B> {
    private A first;
    private B second;
     
    public TwoTuple(A a, B b) {
        this.setFirst(a);
        this.setSecond(b);
    }
	
    public TwoTuple() {
		
	}

    public String toString() {
  		return "(" + getFirst() + "," + getSecond() +")";
  	}
    
    public TwoTuple<A, B> set(A first, B second){
    	TwoTuple<A, B> twoTuple = new TwoTuple<>();
    	twoTuple.first = first;
    	twoTuple.second = second;
		return twoTuple;
    }
    
	public A getFirst() {
		return first;
	}

	public void setFirst(A first) {
		this.first = first;
	}

	public B getSecond() {
		return second;
	}

	public void setSecond(B second) {
		this.second = second;
	}
}