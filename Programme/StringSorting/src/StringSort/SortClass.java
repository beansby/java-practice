package StringSort;

import java.util.Arrays;

/**
 * Array에 담긴 string을 데이터 sorting
 * String[] sort(String[] data)
 * alphabetical order로 sorting
 */
public class SortClass {
    public String[] data;

    public String[] sort(String[] data){
        this.data = data;
        int result;

        for(int i=0; i<data.length-1; i++){
            for(int j=i+1; j<data.length; j++){
                result = data[i].compareToIgnoreCase(data[j]);

                if(result > 0){
                    //바꾸기
                    String temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                } else {
                    //순서 안 바꾸기
                    continue;
                }
            }
        }
        return data;
    };
}
