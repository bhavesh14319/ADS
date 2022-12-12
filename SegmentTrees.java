import java.util.*;

public class SegmentTrees {
    static int [] array = new int[10000];
    static int [] segment = new int[4*array.length];


    static void buildSegementTree(int index, int low, int high){
        if(low==high){
            segment[index]=array[low];
            return;
        }

        int mid = low + (high-low)/2;

        buildSegementTree(2*index+1, low, mid);
        buildSegementTree(2*index+2, mid+1, high);
        segment[index] = segment[2*index+1] + segment[2*index+2];
    }

    static void inorderTraversal(int index, int low, int high){
        if (low == high){
            System.out.println(segment[index] + " ["  + low + " , " + high +  " ]");
            return;
        }
        int mid  = low  + (high-low)/2;
        inorderTraversal(2*index+1, low, mid);
        System.out.println(segment[index] + " ["  + low + " , " + high +  " ]");
        inorderTraversal(2*index+2, mid+1, high);
    }

    static int query(int index, int low, int high, int queryLow, int queryHigh){
        // check if range lises completely in query range
        //     queryLow                queryHigh
        //              low    high               => complete lies 
        if(queryLow<=low && queryHigh>=high){
            return segment[index];
        }

         // check if range does not lie  in query range
        //                  queryLow    queryHigh             queryLow    queryHigh
        //      low    high                                                          low    high      => does not lies
            if(queryLow>high || queryHigh<low){
                return 0;
            }
            
            int mid  = low + (high-low)/2;
            int leftSum = query(2*index+1, low,mid, queryLow, queryHigh);
            int rightSum= query(2*index+2, mid+1, high, queryLow, queryHigh);

            return leftSum+rightSum;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i = 0;i<n;i++ ){
            array[i]=sc.nextInt();
        }
        buildSegementTree(0, 0, n-1);
        for(int i = 0;i<4*n;i++ ){
            System.out.print(segment[i] + " ");
        }
        System.out.println();
        inorderTraversal(0, 0, n-1);
        sc.close();
        
        int low=2;
        int high=4;

        System.out.println(query(0,0,n-1,low,high));


        
    }
}
