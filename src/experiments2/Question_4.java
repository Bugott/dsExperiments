package experiments2;

public class Question_4 {
	public static int fibonacci(int n)
    {
        if (n<0)
            throw new java.lang.IllegalArgumentException("n="+n);
        if (n==0 || n==1)
            return n;
        return fibonacci(n-2)+fibonacci(n-1);
    }
	public static void main(String args[])
    {
        for (int i=0; i<=50; i++){
            System.out.print(fibonacci(i)+" ");
            if(i%10==0)
            	System.out.println();
        }
    }

}
