





public class IntervalTress {
    static class Interval{
        int low;
        int high;
    
        public Interval(int low, int high) {
            this.low = low;
            this.high = high;
        }
    
        public String toString(){
            return "["+this.low + "," + this.high + "]";
        }
    }

    static class Node{
        Interval range;
        Node left;
        Node right;
        int max;
        
        public Node(IntervalTress.Interval range, int max) {
            this.range = range;
            this.max = max;
        }

        public String toString(){
            return "[ " + this.range.low + "," + this.range.high + " max= " + max + " ]";
        }
    }

    public static Node insertInerval( Node root,Interval range){
        if(root==null){
            return new Node(range, range.high);
        }
        if(range.low<root.range.low){
            root.left=insertInerval(root.left,range );
        }else{
            root.right=insertInerval(root.right,range);
        }

        if(root.max<root.range.high){
            root.max=root.range.high;
        }

        return root;
    }

    public static boolean isOverlapping(Node root, Interval x){
        if(root==null){
            return false;
        }

        //   4     7 root  
        // 3     8   X

        if((x.low>root.range.low && x.high < root.range.high) || (x.high>root.range.low && x.high<root.range.high) ){
            return true;
        }

        else if(root.left !=null){
            if(root.left.max > root.max){
                return isOverlapping(root.left, x);
            }
        }
            return isOverlapping(root.right, x);
        
    }



     static void inOrder(Node root)
    {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.println(root);
        inOrder(root.right);

        
    }
 



    public static void main(String[] args) {
        
        Node root = insertInerval(null, new Interval(15, 20));
        root = insertInerval(root, new Interval(10, 30));
        root = insertInerval(root, new Interval(17, 19));
        root = insertInerval(root, new Interval(5, 20));
        root = insertInerval(root, new Interval(12, 15));
        root = insertInerval(root, new Interval(30, 40));

        inOrder(root);

        System.out.println(isOverlapping(root, new Interval(14, 16)));
    }
    
}
