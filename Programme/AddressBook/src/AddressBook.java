import java.io.*;
import java.util.*;

public class AddressBook {
    List<Contact> contacts = new ArrayList<>();
    int cnt;
    int keyCode = 0;

    //csv file i/o stream
    File file = null;
    BufferedWriter bw = null;
    BufferedReader br = null;
    Scanner reader = null;

    //사용자 입력값
    Scanner sc = new Scanner(System.in);



    /**
     * 연락처 저장
     * bufferedWriter -> csv 파일 저장 (true, 이어쓰기)
     * @param filePath
     */
    public void save(String filePath) throws IOException {
        System.out.println("[연락처 저장]");
        System.out.print("이름 : ");
        String name = sc.nextLine();
        String phone = null, email = null;
        if(name == null) {
            System.out.println("이름을 입력해주세요.");
        } else {
            System.out.print("전화번호 : ");
            phone = sc.nextLine();
            //공백일 때 Contact 에 데이터 넣는 것 해결 필요
            if(phone.length() == 0) phone = " ";
            System.out.print("이메일 : ");
            email = sc.nextLine();
            if(email.length() == 0) email = " ";

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

//        contacts.add(new Contact(name, phone, email));
//        Collections.sort(contacts);
        System.out.println("연락처가 저장되었습니다.");
    }


    /**
     * 연락처 수정
     * Arraylist 수정 -> 수정된 Arraylist -> csv file save (false, 덮어쓰기)
     * @param filePath
     * @throws IOException
     */
    public void update(String filePath) throws IOException {
        int[] idx = findByName(filePath);
        String name, phone, email;

        for(int i=0; i<cnt; i++){
            System.out.println("\t 번호 : "+idx[i]+"\t"+contacts.get(idx[i]));
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
                    name = sc.nextLine();
                    contacts.get(num).setName(name);
                    System.out.println(contacts.get(num).getName()+"으로 변경이 완료되었습니다.");
                    break;
                case 2:
                    System.out.println("기존 번호 : "+contacts.get(num).getPhone());
                    System.out.print("변경할 번호 : ");
                    phone = sc.nextLine();

                    contacts.get(num).setPhone(phone);
                    System.out.println(contacts.get(num).getPhone()+"으로 변경이 완료되었습니다.");
                    break;
                case 3:
                    System.out.println("기존 이메일 : "+contacts.get(num).getEmail());
                    System.out.print("변경할 이메일 : ");
                    email = sc.nextLine();

                    contacts.get(num).setEmail(email);
                    System.out.println(contacts.get(num).getEmail()+"으로 변경이 완료되었습니다.");
                    break;
                default: break;
            }
        }
        Collections.sort(contacts);

        // Arraylist to csv file
        try {
            file = new File(filePath);
            bw = new BufferedWriter(new FileWriter(file, false));

            for(Contact contact : contacts){
                String param = contact.getName()+","+contact.getPhone()+","+contact.getEmail();
                bw.write(param);
                bw.newLine();   //개행
            }

            bw.flush();
            bw.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void delete(String filePath){
        int[] idx = findByName(filePath);

        for(int i=0; i<cnt; i++){
            System.out.println("\t 번호 : "+idx[i]+"\t"+contacts.get(idx[i]));
        }

        System.out.print("삭제할 번호 >> ");
        int num = Integer.parseInt(sc.nextLine());

        contacts.remove(num);
        Collections.sort(contacts);

        // Arraylist to csv file
        try {
            file = new File(filePath);
            bw = new BufferedWriter(new FileWriter(file, false));

            for(Contact contact : contacts){
                String param = contact.getName()+","+contact.getPhone()+","+contact.getEmail();
                bw.write(param);
                bw.newLine();   //개행
            }

            bw.flush();
            bw.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("삭제가 완료되었습니다.");
    }


    /**
     * 이름으로 연락처 검색하기
     * @param filePath
     * @return
     */
    public int[] findByName(String filePath){
        contacts = convertData(filePath);
        int[] idx = new int[10];
        cnt = 0;

        System.out.print("이름 검색 >> ");
        String name = sc.nextLine();

        for(Contact contact : contacts){
            if(contact.getName().equals(name)){
//                System.out.print(contact);
//                System.out.print("\t 번호 : "+contacts.indexOf(contact));
//                System.out.println("\t"+contact);
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
     * @param filePath
     */
    public void allInfo(String filePath) {
        contacts = convertData(filePath);

        System.out.println("[전체 목록]");
//        Collections.sort(contacts);
        for(Contact contact : contacts){
            System.out.println(contact);
        }
    }


    /**
     * csv file to ArrayList
     * @param filePath
     * @return List<Contact>
     */
    public List<Contact> convertData(String filePath){
        try {
            contacts.clear();
            file = new File(filePath);
            //reader = new Scanner(new FileInputStream("addressbook.csv"));
            br = new BufferedReader(new FileReader(file));

            //ArrayList에 한줄씩 Contact 인스턴스 추가
            String line = "";
            while((line = br.readLine()) != null) {
                String[] contactArr = line.split(",");
                String name = contactArr[0];
                String phone = contactArr[1];
                String email = contactArr[2];
                contacts.add(new Contact(name, phone, email));
            }
        } catch (FileNotFoundException e){
            System.out.println("파일을 찾을 수 없습니다.");
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        Collections.sort(contacts); //이름순 정렬
        return contacts;
    }

}
