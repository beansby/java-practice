package StringSort;

import StringSort.SortClass;

public class Example {
    public static void main(String[] args) {
        String[] data = {"abc","bca", " a","ccc","aer", null, "age"};
//        String[] data = {"abc","bca", "ccc","aer", null, "age"};
//        String[] data = null;
        SortClass sortClass = new SortClass();

        String[] newData = sortClass.sort(data);

        for(int i=0; i<newData.length; i++){
            System.out.println(newData[i]);
        }
    }
}
