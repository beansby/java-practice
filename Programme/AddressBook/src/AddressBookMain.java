import java.io.*;
import java.util.Scanner;

public class AddressBookMain {
    public static void main(String[] args) throws IOException {
        //데이터를 저장할 파일 생성
        String folderPath = "/Users/ebina/Java/program-ex/Programme/AddressBook/";
        File abData = new File(folderPath,"addressbook.csv");   //file instance 생성
        abData.createNewFile();   //addressbook.csv 파일 생성

        String path = "/Users/ebina/Java/program-ex/Programme/AddressBook/addressbook.csv";

        //테스트용 리스트
        AddressBook ab = new AddressBook();
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("[ AddressBook ]");
            System.out.println(" 1. 연락처 저장 ");
            System.out.println(" 2. 연락처 수정 ");
            System.out.println(" 3. 연락처 삭제 ");
            System.out.println(" 4. 연락처 검색 ");
            System.out.println(" 5. 연락처 목록 ");
            System.out.print("선택 >> ");
            int sel = Integer.parseInt(sc.nextLine());
            if(sel == 0) break;
            System.out.println();

            switch (sel){
                case 1:
                    ab.save(path);
                    System.out.println();
                    break;
                case 2:
                    ab.update();
                    System.out.println();
                    break;
                case 3:
                    ab.delete();
                    System.out.println();
                    break;
                case 4:
                    ab.findByName();
                    System.out.println();
                    break;
                case 5:
                    ab.allInfo();
                    System.out.println();
                    break;
                default: break;
            }
        }
    }
}
