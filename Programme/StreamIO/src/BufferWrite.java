import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * csv 파일에 데이터 입력하기
 */
public class BufferWrite {
    public static void main(String[] args) {
        String path = "/Users/ebina/Java/program-ex/Programme/AddressBook/buffertest.csv";

        File file = null;
        BufferedWriter bw = null;
        String NEWLINE = System.lineSeparator(); //줄바꿈

        try {
            file = new File(path);
            bw = new BufferedWriter(new FileWriter(file, true));

            bw.write("홍길동,,");
            bw.write(NEWLINE);

            bw.write("홍길동, 01012345678,");
            bw.write(NEWLINE);

            bw.write("김길동, 0101111111,");
            bw.write(NEWLINE);

            bw.flush();
            bw.close();

        } catch (Exception e){
            e.printStackTrace();
        }


    }
}
