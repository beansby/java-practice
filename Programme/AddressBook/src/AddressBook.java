import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class AddressBook {
    List<Contact> contacts = new ArrayList<>();
    int cnt;

    File file = null;
    BufferedWriter bw = null;

    //사용자 입력값
    Scanner sc = new Scanner(System.in);

    /**
     * 연락처 저장
     */
    public void save(String filePath){
        System.out.println("[연락처 저장]");
        System.out.print("이름 : ");
        String name = sc.nextLine();
        String phone = null, email = null;
        if(name == null) {
            System.out.println("이름을 입력해주세요.");
        } else {
            System.out.print("전화번호 : ");
            phone = sc.nextLine();
            System.out.print("이메일 : ");
            email = sc.nextLine();
        }

        String param = name +","+ phone +","+ email;

        try {
            file = new File(filePath);
            bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(param);
            bw.newLine();   //개행
            bw.flush();
            bw.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        contacts.add(new Contact(name, phone, email));
        Collections.sort(contacts);
        System.out.println("연락처가 저장되었습니다.");
    }

    /**
     * 연락처 수정
     */
    public void update(){
        int[] idx = findByName();

        for(int i=0; i<cnt; i++){
            System.out.println("번호 : "+idx[i]+"\t"+contacts.get(idx[i]));
        }

        System.out.print("수정할 번호 >> ");
        int num = Integer.parseInt(sc.nextLine());

        while(true){
            System.out.println("[변경할 내용]");
            System.out.println(" 1. 이름 | 2. 전화번호 | 3. 이메일 | 0. 저장 ");
            System.out.print("선택 >> ");
            int sel = Integer.parseInt(sc.nextLine());
            if(sel == 0) break;
            switch (sel) {
                case 1:
                    System.out.println("기존 이름 : "+contacts.get(num).getName());
                    System.out.print("변경할 이름 : ");
                    String name = sc.nextLine();
                    contacts.get(num).setName(name);
                    System.out.println(contacts.get(num).getName()+"으로 변경이 완료되었습니다.");
                    break;
                case 2:
                    System.out.println("기존 번호 : "+contacts.get(num).getPhone());
                    System.out.print("변경할 번호 : ");
                    String phone = sc.nextLine();
                    contacts.get(num).setPhone(phone);
                    System.out.println(contacts.get(num).getPhone()+"으로 변경이 완료되었습니다.");
                    break;
                case 3:
                    System.out.println("기존 이메일 : "+contacts.get(num).getEmail());
                    System.out.print("변경할 이메일 : ");
                    String email = sc.nextLine();
                    contacts.get(num).setEmail(email);
                    System.out.println(contacts.get(num).getEmail()+"으로 변경이 완료되었습니다.");
                    break;
                default: break;
            }
        }
    }

    public void delete(){
        int[] idx = findByName();

        for(int i=0; i<cnt; i++){
            System.out.println("번호 : "+idx[i]+"\t"+contacts.get(idx[i]));
        }

        System.out.print("삭제할 번호 >> ");
        int num = Integer.parseInt(sc.nextLine());

        contacts.remove(num);
        Collections.sort(contacts);
        System.out.println("삭제가 완료되었습니다.");
    }

    /**
     * 이름으로 연락처 검색하기
     */
    public int[] findByName(){
        int[] idx = new int[10];
        cnt = 0;

        System.out.print("이름 검색 >> ");
        String name = sc.nextLine();

        for(Contact contact : contacts){
            if(contact.getName().equals(name)){
//                System.out.print(contact);
                System.out.println("\t 인덱스 : "+contacts.indexOf(contact));
                idx[cnt++] = contacts.indexOf(contact);
            }
        }
        System.out.println();
        return idx;
    }

    /**
     * 전화번호로 연락처 검색
     */
    public void findByPhone(){

    }

    /**
     * 연락처 전체 조회 (이름순 정렬)
     */
    public void allInfo() {
//        contacts.sort(Comparator.naturalOrder());
        System.out.println("[전체 목록]");
        Collections.sort(contacts);
        for(Contact contact : contacts){
            System.out.println(contact);
        }
    }
}
