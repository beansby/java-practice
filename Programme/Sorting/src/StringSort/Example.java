package StringSort;

import StringSort.SortClass;

public class Example {
    public static void main(String[] args) {
        String[] data = {"abc","bca", " a","ccc","aer", null, "age"};

        System.out.println("[Before]");
        for(int i=0; i<data.length; i++){
            System.out.print(data[i]+"\t");
        }
        System.out.println();

        System.out.println("[After]");
        data = SortClass.sort(data);
        for(int i=0; i<data.length; i++){
            System.out.print(data[i]+"\t");
        }
    }
}
