

class RedBlackTree{
    class Node{
        Node left;
        Node right;
        Node parent;
        char color;
        int value;

        public Node(int key){
            this.left=null;
            this.right=null;
            this.parent=null;
            this.color='R';
            this.value=key;
        }
    }

    public Node root;

    public boolean ll =false;
    public boolean rr=false;
    public boolean lr=false;
    public boolean rl = false;



    public RedBlackTree(){ // will be called only one time at the time of tree object creation
         root=null;
    }

    Node roatateLeft(Node node){
        Node x = node.left;
        Node y = x.right;
        node.parent=x;
        x.right=node;
        node.left=y;
        if(y!=null){
            y.parent=node;
        }

        return x;

    }

    Node rotateRight(Node node){
        Node x = node.right;
        Node y = x.left;
        node.parent=x;
        x.left=node;
        node.right=y;

        if(y!=null){
            y.parent=node;
        }

        return x;
    }

    Node insertNode(Node root, int key){
        boolean conflict=false;
        if(root==null){
            return new Node(key);
        }
        else if(key<root.value){
            // enter left subtree
            root.left = insertNode(root.left, key);
            // set parent for inserted node
            root.left.parent=root;
            // check for conflict

            if(root !=this.root){
                if(root.color == 'R' && root.left.color == 'R'){
                    conflict = true;
                }
            }
        }else{
            // enter right subtree
            root.right=insertNode(root.right,key);
            // set parent of inserted node
            root.right.parent = root;

            // check for conflict
            if(root !=this.root){
                if(root.color=='R' && root.right.color=='R'){
                    conflict=true;
                }
            }
        }

        if(ll){
            // perform left rotation
            root = roatateLeft(root);
            root.color='B';
            root.left.color='R';
            this.ll=false;
        }
        else if(rr){
            root = rotateRight(root);
            root.color='B';
            root.right.color='B';
            this.rr=false;
        }
        else if(lr){
            root.left = roatateLeft(root.left);
            root.left.parent=root;
            root=rotateRight(root);
            root.color='B';
            root.right.color='R';
            this.lr=false;

        }else if(rl){
            root.right =  rotateRight(root.right);
            root.right.parent=root;
            root=roatateLeft(root);
            root.color='B';
            root.left.color='R';
            this.rl=false;
        }


        // Now check if it is RR conflict or RL or LR or LL

        if(conflict){
            if(root.parent.right == root){
                // it is RR or RL conflict
                if(root.parent.left==null || root.parent.left.color=='B'){
                    // when uncle is either black or null
                    if(root.left != null && root.left.color=='R'){
                        this.rl=true;
                    }else if(root.right !=null && root.right.color == 'R'){
                        this.ll=true; //rr conflict is there we will perform left rotation
                    }
                }else{
                    // if uncle is red then we simply recolor
                    root.parent.left.color='B';
                    root.color='B';
                    if(root.parent !=this.root){
                        root.parent.color='R';
                    }
                }
            }else{
                //it is LL or LR conflict
                if(root.parent.right==null || root.parent.right.color=='B'){
                    // unclie is either black or null
                    if(root.right !=null && root.right.color=='R'){
                        this.lr=true;
                    }else if(root.right !=null && root.left.color=='R'){
                        this.rr=true; //LL conflict is there we will perform right rotation
                    }
                }else{
                    // is uncle is red we simply recolor
                    root.parent.right.color='B';
                    root.color='B';
                    if(root.parent != this.root){
                        root.parent.color='R';     
                    }
                }
            }

     
        }
        return root;
    }


    public void insert(int data){
        if(this.root==null){
            this.root=new Node(data);
            this.root.color='B';
        }else{
            this.root = insertNode(this.root,data);
        }
    }


    void inorderTraversalHelper(Node node){
        if(node!=null){
            inorderTraversalHelper(node.left);
            System.out.printf("%d ", node.value);
            inorderTraversalHelper(node.right);
        }
    }
    
    
    public void inorderTraversal(){
        inorderTraversalHelper(this.root);
    }
    
    void printTreeHelper(Node root, int space)
      {
          int i;
          if(root != null)
          {
              space = space + 10;
              printTreeHelper(root.right, space); 
              System.out.printf("\n"); 
              for ( i = 10; i < space; i++) 
              {
                  System.out.printf(" ");
              } 
              System.out.printf("%d %c", root.value , root.color);
              System.out.printf("\n");
              printTreeHelper(root.left, space); 
          }
      }

      public void printTree()
      {
          printTreeHelper(this.root, 0);
      }
    


}


public class Insertion {

    public static void main(String[] args) {
        RedBlackTree t = new RedBlackTree();
        int[] arr = {1,4,6,3,5,7,8,2,9};
        for(int i=0;i<9;i++)
        {
            t.insert(arr[i]);
            System.out.println();
            t.inorderTraversal();
        }
        // you can check colour of any node by with its attribute node.colour
        t.printTree();
        
    }
    
}
