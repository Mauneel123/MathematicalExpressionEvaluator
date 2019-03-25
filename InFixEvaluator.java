import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class InFixEvaluator {
    public double evaluator(String str) throws Exception {

        Stack<String> c = new Stack<>(str.length());

        String[] lba = str.split(" ");
        for(String qwerty : lba){
            if(qwerty.equals("(") || qwerty.equals("{") || qwerty.equals("[")){
                c.push(qwerty);
            }else if(qwerty.equals(")") || qwerty.equals("}") || qwerty.equals("]")){
                if((qwerty.equals(")") && c.pop().equals("(")) || (qwerty.equals("}") && c.pop().equals("{")) || (qwerty.equals("]") && c.pop().equals("["))){
                    continue;
                }else throw new Exception("Invalid Expression");
            }
        }

        for(int al=0;al<lba.length-1;al++){
            if((lba[al].equals("(") && lba[al+1].equals(")")) || (lba[al].equals("{") && lba[al+1].equals("}")) || (lba[al].equals("[") && lba[al+1].equals("]"))){
                throw new Exception("Invalid Expression");
            }
        }
        Stack<Double> number = new Stack<Double>(str.length());
        Stack<String> operator = new Stack<String>(str.length());

        StringTokenizer st = new StringTokenizer(str);
        int ln = 0;

        while (st.hasMoreTokens()) {
            String arr = "";

            arr = st.nextToken();
           
            if (arr.equalsIgnoreCase("cos") || arr.equalsIgnoreCase("log") || arr.equalsIgnoreCase("sin")) {
                operator.push(arr);

            } else if (arr.equalsIgnoreCase("+") || arr.equalsIgnoreCase("-") || arr.equalsIgnoreCase("*") || arr.equalsIgnoreCase("/") || arr.equalsIgnoreCase("^") || arr.equalsIgnoreCase("{") || arr.equalsIgnoreCase("(") || arr.equalsIgnoreCase("[")) {
                operator.push(arr);

            } else if (arr.equalsIgnoreCase(")") || arr.equalsIgnoreCase("]") || arr.equalsIgnoreCase("}")) {

                String s = operator.pop();


                if (s.equalsIgnoreCase("+") || s.equalsIgnoreCase("-") || s.equalsIgnoreCase("*") || s.equalsIgnoreCase("/") || s.equalsIgnoreCase("^")) {
                    double n1 = number.pop();

                    double n2 = number.pop();

                    if (s.equalsIgnoreCase("+")) {
                        number.push(n1 + n2);
                    } else if (s.equalsIgnoreCase("-")) {
                        number.push(n2 - n1);
                    } else if (s.equalsIgnoreCase("*")) {
                        number.push(n1 * n2);
                    } else if (s.equalsIgnoreCase("/")) {
                        number.push(n2 / n1);
                    } else if (s.equalsIgnoreCase("^")) {
                        number.push(Math.pow(n2, n1));
                    }
                    operator.pop();

                } else if (s.equalsIgnoreCase("{") || s.equalsIgnoreCase("[") || s.equalsIgnoreCase("(")) {
                    double n11 = number.pop();

                    number.push(n11);

                }

                else if(s.equalsIgnoreCase("log") || s.equalsIgnoreCase("sin") || s.equalsIgnoreCase("cos"))
                {
                    double n22=number.pop();
                    if(s.equalsIgnoreCase("log"))
                    {number.push(Math.log(n22));}
                    else if(s.equalsIgnoreCase("sin"))
                    {number.push(Math.sin(n22));}
                    else if(s.equalsIgnoreCase("cos"))
                    {number.push(Math.cos(n22));}
                    operator.pop();
                }


            } else {
                try {
                    double d = Double.parseDouble(arr);
                    number.push(d);

                } catch (NumberFormatException e) {
                    throw new Exception("Invalid Expression");
                }
            }


        }
        if(number.size()!=1){ throw new Exception("Invalid Expression");}
        return number.pop();  

    public static void main(String[] args) throws IOException {
        InFixEvaluator i = new InFixEvaluator();
        try {
            System.out.println(i.evaluator(args[0]));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}