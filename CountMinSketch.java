import java.util.Arrays;

public class CountMinSketch {
    static int noOfHashFunctions = 3;
    static int maxValueOfHashFunctions = 7;

    static int [] [] freqMatrix = new int[noOfHashFunctions][maxValueOfHashFunctions];

    static int h1(char ch){
        int hash = 0;
        hash =  hash + ch-'a';
        hash = hash % maxValueOfHashFunctions;
        return hash;
    }

    static int h2(char ch){
        int hash = 1;
        hash =  hash * (ch-'a');
        hash = hash % maxValueOfHashFunctions;
        return hash;
    }

    static int h3(char ch){
        int hash = 2;
        hash =  (hash * 2)  + (ch-'a');
        hash = hash % maxValueOfHashFunctions;
        return hash;
    }


    static void store(String s){
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            int h1 =  h1(ch);
            int h2 = h2(ch);
            int h3 = h3(ch);

            freqMatrix[0][h1] +=1 ;
            freqMatrix[1][h2] +=1 ;
            freqMatrix[2][h3] +=1 ;
        }
    }

    static int findProbablity(char ch){
        int h1 = h1(ch);
        int h2 = h2(ch);
        int h3 = h3(ch);

        return Math.min(freqMatrix[0][h1],Math.min(freqMatrix[1][h2],freqMatrix[2][h3]));

    }


    public static void main(String[] args) {
        String s = "ababacctdsa";
        store(s);

        for(int i=0;i<freqMatrix.length;i++){
            System.out.println(Arrays.toString(freqMatrix[i]));
        }

       System.out.println("might have appeared: " +  findProbablity('c') + " times") ;

    }
    
}
