import java.util.ArrayList;

 
public class Stack<T>
{
    int len;
    T[] stack;
    int top;

    public Stack(int capacity)
    {
        this.len=capacity;
        this.stack=(T[]) new Object[len];
        this.top=-1;

    }

    public boolean IsEmpty(){
  
        if(this.top==-1)
        {return true;}
        else {
            return false;           
        }
    }

    public boolean push(T val){
]


        if(top==(len-1))
        {return false;}

        else
        {
            this.stack[++top]=val;
            return true;
        }
    }

    public T pop() throws IndexOutOfBoundsException{
      ]
        if(this.IsEmpty())
        {
            throw new IndexOutOfBoundsException("Empty Stack");
        }
            T p=this.stack[top];--top;
            return p;


    }

    public int size()
    {
 

        int l=top;
        return (l+1);         


    public String toString() {
        String s = "";
        for(T i:stack){
            s +=i+",";
        }
        return s+" "+size();
    }
}
