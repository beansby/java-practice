import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //데이터를 저장할 파일 생성
//        String path = "/Users/ebina/Java/program-ex/Programme/AddressBook/";
//        File abData = new File(path,"addressbook.txt");   //file instance 생성
//        abData.createNewFile();   //addressbook.txt 파일 생성

        //try-catch
//        FileReader fr = new FileReader("addressbook.txt");
//        FileWriter fw = new FileWriter("addressbook.txt", false); //기존 파일 덮어쓰기
//        try(FileReader fr = new FileReader("addressbook.txt");  //파일 열기 : 파일 못 찾았을 때 예외처리 필요
//            FileWriter fw = new FileWriter("addressbook.txt", false);
//        ) {
//        } catch (Exception e) {}

//        FileWriter fw = null;
//        try {
//            fw = new FileWriter("addressbook.txt");
//        }

        //파일 오픈 -> 읽기 -> 닫기
//        FileReader fr = null;
//        try{
//            fr = new FileReader("addressbook.txt"); //열기
//            while(true){    //읽기
//                int data = fr.read();
//                if(data == -1) break;
//                char ch = (char) data;
//                System.out.println(ch);
//            }
//        } catch (FileNotFoundException fnfe){
//            fnfe.printStackTrace();
//        } catch (IOException ie){
//            System.out.println("파일을 읽을 수 없습니다.");
//            ie.printStackTrace();
//        } finally {
//            try {
//                fr.close(); //닫기
//            } catch (Exception e) {
//                System.out.println("파일을 찾을 수 없습니다.");
//            }
//        }

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
                    ab.save();
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
