package CSV;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader {
    public static void main(String[] args) {
        CSVReader csvReader = new CSVReader();
        csvReader.readCSV();

    }

    String path = "/Users/ebina/Java/program-ex/Programme/AddressBook/buffertest.csv";

    /**
     * csv 파일 읽기
     * @return
     */
    public List<List<?>> readCSV(){
        List<List<?>> csvList = new ArrayList<List<?>>();
        File csv = new File(path);
        BufferedReader br = null;
        String line = "";

        try {
            br = new BufferedReader(new FileReader(csv));
            while((line = br.readLine()) != null) { //개행된 한줄의 데이터 읽기
                List<String> aLine = new ArrayList<String>();
                String[] lineArr = line.split(","); //한줄을 , 로 나누어 배열에 저장
                aLine = Arrays.asList(lineArr); //,로 나누어진 배열을 리스트로 변환
                csvList.add(aLine);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br != null){
                    br.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return  csvList;
    }

//    public void writeCSV() {
//        File csv = new File(path);
//        BufferedWriter bw = null;
//        List<List<String>> dataList = readCSV();
//
//        try {
//            bw = new BufferedWriter(new FileWriter(csv, false)); //기존 데이터 덮어쓰기
//            for(int i=0; i<dataList.size(); i++){
//                String[] data = dataList.get(i);
//                String aData = "";
//                aData = data[0]
//            }
//
//        } catch (FileNotFoundException e){
//            e.printStackTrace();
//        } catch (IOException e){
//            e.printStackTrace();
//        } finally {
//
//        }
//    }





}
