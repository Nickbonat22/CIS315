// Nicholas Bonat
//Assignment0

import java.util.Scanner;                         //import Scanner package

public class Assignment0 {
  public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    int numProblems = scanner.nextInt();          //retrieve the number of lines
  
    for(int i = 0; i < numProblems; ++i){
      int x = scanner.nextInt();                  //retrieve the first integer
      int y = scanner.nextInt();                  //retrieve the second integer
 
      int a = add(x,y);
      int m = multiply(x,y);
 
      System.out.println(a + " " + m);
    }

    scanner.close();
 }

 public static int add(int x, int y){
    return x + y;
 }

 public static int multiply(int x, int y){
  return x * y;
 }
 
}// end class Assignment0