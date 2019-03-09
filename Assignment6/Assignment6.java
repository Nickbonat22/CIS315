import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Arrays;

public class Assignment6 {
    static Set<String> diction10k = new HashSet<String>();
    static int num;

    // bottum up technique
    public static boolean iterative(String stringLine) {
        int n = stringLine.length();
        // init boolean and int array
        boolean[] isWord = new boolean[n + 1];
        int[] index = new int[n + 1];
        
        isWord[n] = true;
        index[n] = stringLine.length();
        String possibleWord;
        
        for(int i = n; i > -1; i--) {
            for(int j = i; j < n; j++){ 
                possibleWord = stringLine.substring(i, j + 1);
                //if the word is in our dictionary
                if(diction10k.contains(possibleWord) && isWord[j + 1]) {
                    isWord[i] = true;
                    index[i] = j;
                } 
            }
        }
        // can't be split
        if(isWord[0] != true) {
            System.out.println("NO, cannot be split");
            System.out.println(stringLine + "\n");
            return false;
        }
        //can be split
        System.out.println("YES, can be split");
        int k = 0;

        while(k < stringLine.length()) {
            possibleWord = stringLine.substring(k, index[k] + 1);
            System.out.print(possibleWord + " ");
            k = index[k]+1;
        }
        System.out.println("\n");
        return true;  
    }
    
    public static void recursive(String stringLine) {
        int n = stringLine.length();
        int[] isWord = new int[n + 1];
        int[] index = new int[n + 1];

        // array.fill found: https://stackoverflow.com/questions/9128737/fastest-way-to-set-all-values-of-an-array
        Arrays.fill(isWord, -1);

        isWord[n] = 1;
        index[n] = n;
        int recursive = implementMemoized(isWord, index, stringLine, 0);

        if(recursive != 1) {
            System.out.println("NO, cannot be split");
            System.out.println(stringLine + "\n");
            return;
        }

        System.out.println("YES, can be split");
        int k = 0;

        String possibleWord;
        while(k < n) {
            possibleWord = stringLine.substring(k, index[k] + 1);
            System.out.print(possibleWord + " ");
            k = index[k] + 1;
            }
        System.out.println("\n");
    }

     public static int implementMemoized(int[] isWord, int[] index, String stringLine, int i) {   
    	 String possibleWord;
    	 if(isWord[i] == -1){
    		 isWord[i] = 0;
    		 for(int j = i; j < stringLine.length(); j++) {
    			 possibleWord = stringLine.substring(i, j + 1);
                 //if the word is in our dictionary
    			 if(diction10k.contains(possibleWord) && implementMemoized(isWord, index, stringLine, j + 1) == 1) {
    				 isWord[i] = 1;
    				 index[i] = j;
    			 }
    		 }
    	 }
         return isWord[i];
     }

     private static void addDict(String file) {
        File dictFile = new File(file);

        // try statment so a warning doesn't pop up
        try {
            Scanner scan = new Scanner(dictFile);
            String stringLine;
            while (scan.hasNext()) {
                stringLine = scan.next();
                diction10k.add(stringLine.trim());
            }
            scan.close();
        } 

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }       

    public static void main(String[] args){
        String filename = "diction10k.txt";
        addDict(filename);
        Scanner scanner = new Scanner(System.in);
        int numProblems = scanner.nextInt();
        int phraseNumber = 1;
        scanner.nextLine();

        String stringLine;
        for(int i = 0; i < numProblems; ++i) {
            stringLine = scanner.nextLine();
            System.out.println("phrase number: " + phraseNumber);
            System.out.println(stringLine+ "\n");
            System.out.println("iterative attempt:");
            iterative(stringLine);
            System.out.println("memoized attempt:");
            recursive(stringLine);
            phraseNumber++;
        }
        scanner.close();
    }
}