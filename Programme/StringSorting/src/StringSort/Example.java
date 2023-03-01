package StringSort;

import StringSort.SortClass;

public class Example {
    public static void main(String[] args) {
        String[] data = {"abc","bca","ccc","aer"};
        SortClass sortClass = new SortClass();

        String[] newData = sortClass.sort(data);

        for(int i=0; i<newData.length; i++){
            System.out.println(newData[i]);
        }
    }
}
