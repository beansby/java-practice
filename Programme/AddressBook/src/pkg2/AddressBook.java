package pkg2;

import java.io.*;
import java.util.*;

/**
 * 주소록(연락처 목록).
 * - save() : 연락처 저장
 * - update() : 연락처 수정
 *        -> searchBy()
 * - delete() : 연락처 삭제
 *        -> searchBy()
 * - searchBy() : 연락처 검색
 *      -> searchByName() 이름, searchByPhone() 연락처
 * - allInfo() : 전체 연락처 조회
 *
 * - loadFile() : file 읽어오기
 * - saveFile() : file 덮어쓰기
 */
public class AddressBook {
    private List<Contact> contacts = new ArrayList<>();

    //사용자 입력
    Scanner sc = new Scanner(System.in);

    //file and stream
    String path = "/Users/ebina/Java/program-ex/Programme/AddressBook/addressbook.csv";
    File file = new File(path);
    //load
    FileReader fr = null;
    BufferedWriter bw = null;
    //save
    FileWriter fw = null;
    BufferedReader br = null;


    /**
     * 연락처 저장.
     * @throws Exception
     */
    public void save() throws Exception {
        System.out.println("=====================================================");
        System.out.println("\t\t\t\t [  연 락 처 저 장  ]");
        System.out.print("- 이      름 : ");
        String name = sc.nextLine();
        String phone = null, email = null;

        if(name.length() == 0){
            throw new Exception("이름은 필수입력 항목입니다. 이름을 입력해주세요.");
        } else {
            System.out.print("- 전 화 번 호 : ");
            phone = sc.nextLine();
            if(phone.length() == 0) phone = " ";

            System.out.print("- 이  메  일 : ");
            email = sc.nextLine();
            if(email.length() == 0) email = " ";
        }

        contacts.add(new Contact(name, phone, email));
        Collections.sort(contacts);
        saveFile();
        System.out.println("연락처가 저장되었습니다.");
        System.out.println("=====================================================");
    }


    /**
     * 연락처 수정.
     * @throws Exception
     */
    public void update() throws Exception {
        HashMap<Integer, Contact> res = searchBy();
        String name, phone, email;

        System.out.print("수 정 할 번 호 >> ");
        int sel = Integer.parseInt(sc.nextLine());
        MOD: while(true){
            if((!res.containsKey(sel))){
                System.out.println("잘못된 번호입니다.");
                break;
            } else {
                System.out.println("[  변 경 할 내 용   ]");
                System.out.println(" 1. 이름 | 2. 전화번호 | 3. 이메일 | 0. 저장 ");
                System.out.print("선택 >> ");
                int num = Integer.parseInt(sc.nextLine());
                if(num == 0) {
                    System.out.println("변경 내용이 저장되었습니다.");
                    break;
                }

                switch (num){
                    case 1:
                        System.out.println("기 존 이 름 : "+contacts.get(sel).getName());
                        System.out.print("변 경 할 이 름 : ");
                        name = sc.nextLine();
                        contacts.get(sel).setName(name);
                        System.out.println(contacts.get(sel).getName()+"으로 변경이 완료되었습니다.");
                        break MOD;
                    case 2:
                        System.out.println("기 존 번 호 : "+contacts.get(sel).getPhone());
                        System.out.print("변 경 할 번 호 : ");
                        phone = sc.nextLine();
                        contacts.get(sel).setPhone(phone);
                        System.out.println(contacts.get(sel).getPhone()+"으로 변경이 완료되었습니다.");
                        break MOD;
                    case 3:
                        System.out.println("기 존 이 메 일 : "+contacts.get(sel).getEmail());
                        System.out.print("변 경 할 이 메 일 : ");
                        email = sc.nextLine();
                        contacts.get(sel).setEmail(email);
                        System.out.println(contacts.get(sel).getEmail()+"으로 변경이 완료되었습니다.");
                        break MOD;
                    default:
                        System.out.println("잘못된 선택입니다. 1~3번 사이의 번호를 입력해주세요.");
                        break;
                }
            }
        }
        Collections.sort(contacts);
        saveFile();
    }

    /**
     * 연락처 삭제.
     * @throws Exception
     */
    public void delete() throws Exception {
        HashMap<Integer, Contact> res = searchBy();
        String name, phone, email;

        System.out.print("삭 제 할 번 호 >> ");
        int sel = Integer.parseInt(sc.nextLine());

        if((!res.containsKey(sel))){
            System.out.println("잘못된 번호입니다.");
        } else contacts.remove(sel);

        Collections.sort(contacts);
        saveFile();
        System.out.println("연락처가 삭제되었습니다.");
    }

    /**
     * 검색 기준 선택.
     * @throws Exception
     */
    public HashMap<Integer, Contact> searchBy() throws Exception {
        System.out.println();
        System.out.println("=====================================================");
        System.out.println("\t\t\t\t [  연 락 처 검 색  ]");
        HashMap<Integer, Contact> res = null;

        TEL: while(true) {
            System.out.println("1. 이름 검색 | 2. 번호 검색 | 0. 종료");
            System.out.print("선 택 >> ");

            int num = Integer.parseInt(sc.nextLine());
            if(num == 0) {
                System.out.println("검색 종료.");
                break;
            }

            switch (num){
                case 1: res = findByName(); break TEL;
                case 2: res = findByPhone();break TEL;
                default:
                    System.out.println("없는 번호입니다. 1 혹은 2 를 입력해주세요."); break;
//                    throw new Exception("없는 번호입니다. 1 혹은 2 를 입력해주세요.");
            }
            System.out.println();
        }
        return res;
    }

    public HashMap<Integer, Contact> findByName() throws Exception {
        System.out.print("검 색 할 이 름 >> ");
        String name = sc.nextLine();
//        ArrayList<Contact> res = new ArrayList<>();
        HashMap<Integer, Contact> res = new HashMap<>();

        for (Contact contact : contacts){
            if(contact.getName().equals(name)){
                System.out.println("[번호 : "+ contacts.indexOf(contact) +"] \t"+ contact);
//                res.add(contact);
                res.put(contacts.indexOf(contact), contact);
            }
        }
        if (res.size() == 0){
            System.out.println("일치하는 연락처가 없습니다.");
//            throw new Exception("일치하는 연락처가 없습니다.");
        }
        return res;
    }
    public HashMap<Integer, Contact> findByPhone() throws Exception {
        System.out.print("검 색 할 번 호 >> ");
        String phone = sc.nextLine();
//        ArrayList<Contact> res = new ArrayList<>();
        HashMap<Integer, Contact> res = new HashMap<>();

        for (Contact contact : contacts){
            if(contact.getPhone().equals(phone)){
                System.out.println("[번호 : "+ contacts.indexOf(contact) +"] \t"+ contact);
//                res.add(contact);
                res.put(contacts.indexOf(contact), contact);
            }
        }
        if (res.size() == 0){
            System.out.println("일치하는 연락처가 없습니다.");      //반복
//            throw new Exception("일치하는 연락처가 없습니다.");   //중단
        }

        return res;
    }

    /**
     * 연락처 전체 목록 조회.
     */
    public void allInfo(){
        System.out.println();
        System.out.println("=====================================================");
        System.out.println("\t\t\t\t [  전 체 목 록  ]");
        for (Contact contact : contacts){
            System.out.println(contact);
        }
        System.out.println("=====================================================");
    }

    /**
     * csv 파일 로드 후 리스트 정렬.
     * 프로그램 시작시 호출.
     */
    public void loadFile() {
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String line = "";
            while((line = br.readLine()) != null){
                String[] lineArr = line.split(",");
                String name = lineArr[0];
                String phone = lineArr[1];
                String email = lineArr[2];
                contacts.add(new Contact(name, phone, email));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br != null) br.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        Collections.sort(contacts);
    }

    /**
     * csv 파일 저장.
     * 프로그램 종료시 호출.
     */
    public void saveFile(){
        try {
            fw = new FileWriter(file, false);
            bw = new BufferedWriter(fw);

            for(Contact c : contacts) {
                String param = c.getName()+","+c.getPhone()+","+c.getEmail();
                bw.write(param);
                bw.newLine(); //개행
            }

            bw.flush();
            bw.close();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                if(bw != null) bw.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
