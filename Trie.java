

public class Trie{
   static class Node{
        Node [] children ;
        boolean end;

        public Node(){
            children=new Node[26];
            for(int i=0;i<26;i++){
                children[i]=null;
            }
            this.end=false;
        }
    }
    static Node mainRoot = new Node();


    static boolean search(String str){
        Node root = mainRoot;
        for(int i=0;i<str.length();i++){
            int idx=str.charAt(i)-'a';
            if(root.children[idx]==null){
                System.out.println("Does not exist");
                return true;
                // break;
            }
            if(i==str.length()-1){
                if(root.children[idx].end){
                    System.out.println("Exists");
                    return true;
                    // break;
                }else{
                    System.out.println("Does not exist");
                    return false;
                    // break;
                }
            }
            root=root.children[idx];

        }
        return false;
    }
    
    static void insert(String str){
        Node root = mainRoot;
        for(int i=0;i<str.length();i++){
            int idx = str.charAt(i) - 'a';
            if(root.children[idx]==null){
                // create new node
                root.children[idx]=new Node();  
            }
            if(i==str.length()-1){
                root.children[idx].end=true;
            }
                root = root.children[idx];
            
        }
    }

    static boolean isEmpty(Node root){
        for(int i=0;i<26;i++){
            if(root.children[i]!=null){
                return false;
            }
        }
        return true;
    }

    static Node delete(Node root, String str, int depth ){
        if(root==null){
            return null;
        }

        if(depth==str.length()){
            if(root.end){
                root.end=false;
            }
            if(isEmpty(root)){
                root=null;
            }
            return root;
        }

        int index = str.charAt(depth)-'a';
        root.children[index]=delete(root.children[index],str, depth+1);


        if(isEmpty(root) && root.end==false){
            root=null;
        }

        return root;
    }
    
    public static void main(String[] args) {

        // String words[] = {"a","the","there","their","any"};

       String [ ]words = {"i","like","sam","samsung","mobile","ice"};
       
        for(int i=0;i<words.length;i++){
            insert(words[i]);
        }

        search("like");

        delete(mainRoot, "like", 0);

        search("like");

        






        
    }
}