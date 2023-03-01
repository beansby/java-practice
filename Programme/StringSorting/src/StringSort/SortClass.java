package StringSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Array에 담긴 string을 데이터 sorting
 * String[] sort(String[] data)
 * alphabetical order로 sorting
 */
public class SortClass {
    public String[] data;

    //NullPointerException 예외처리
    public String[] sort(String[] data){
        this.data = data;
        int result;

        List<String> removeNull = Arrays.asList(data);
        String[] newData = removeNull.stream().filter(t->(t!=null && t.length() > 0)).toArray(String[]::new);

        for(int i=0; i<newData.length-1; i++){
            for(int j=i+1; j<newData.length; j++){
                result = newData[i].compareToIgnoreCase(newData[j]);

                if(result > 0){
                    //바꾸기
                    String temp = newData[i];
                    newData[i] = newData[j];
                    newData[j] = temp;
                } else {
                    //순서 안 바꾸기
                    continue;
                }
            }
        }
        return newData;
    };
}
