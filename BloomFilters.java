public class BloomFilters {


    static int h1(String s,int length){
        int hash = 0 ;
        for(int i=0;i<s.length();i++){
            hash =  hash + (int)s.charAt(i);
            hash = hash%length;
        }
        return hash;
    }


    static int h2(String s,int length){
        int hash = 1 ;
        for(int i=0;i<s.length();i++){
            hash =  hash + (int)Math.pow(2,i) *(int)s.charAt(i);
            hash = hash%length;
        }
        return hash;
    }

    static int h3(String s,int length){
        int hash = 2 ;
        for(int i=0;i<s.length();i++){
            hash =  hash*2 + (int)s.charAt(i);
            hash = hash%length;
        }
        return hash;
    }

    static int h4(String s,int length){
        int hash = 7;
        for(int i=0;i<s.length();i++){
            hash =  hash + 7+ 2*(int)s.charAt(i);
            hash = hash%length;
        }
        return hash;
    }



    static boolean lookup(boolean[] bitArray , String s){
        int a = h1(s, bitArray.length);
        int b = h2(s,bitArray.length);
        int c = h3(s,bitArray.length);
        int d = h4(s,bitArray.length);

        if(bitArray[a] && bitArray[b] && bitArray[c] && bitArray[d]){
            System.out.println(a  + " " + b + " "  + c + " " + d);
            return true;
        }
        // System.out.println("does not exist");
        return false;
    } 

    static void insert(boolean [] bitArray, String s){

        if(lookup(bitArray,s)){
            System.out.println("probably exist already");
        }else{

            int a = h1(s, bitArray.length);
            int b = h2(s,bitArray.length);
            int c = h3(s,bitArray.length);
            int d = h4(s,bitArray.length);
            System.out.println(a  + " " + b + " "  + c + " " + d);
            bitArray[a]=true;
            bitArray[b]=true;
            bitArray[c]=true;
            bitArray[d]=true;
        }

    }

    public static void main(String[] args) {

        boolean[] bitArray = new boolean[100];
        String[] array = { "abound", "abounds", "abundance", "abundant", "accessible", "bloom",
                "blossom", "bolster", "bonny", "bonus", "bonuses", "coherent", "cohesive", "colorful", "comely",
                "comfort", "gems", "generosity", "generous", "generously", "genial", "bluff", "cheater", "hate", "war",
                "humanity", "racism", "hurt", "nuke", "gloomy", "facebook", "geeksforgeeks", "twitter" };

        for(int i=0;i<array.length;i++){
            insert(bitArray,array[i]);
        }


        lookup(bitArray, "bhavesh");

    }
}
