package pkg2;

import java.util.Scanner;

/**
 * 주소록 실행.
 * - start() : 주소록 메뉴 출력
 */
public class AddressBookMain {
    public static void main(String[] args) {
        //사용자 입력
        Scanner sc = new Scanner(System.in);
        AddressBook ab = new AddressBook();
        ab.loadFile();

        while(true){
            start();
            try {
                int num = Integer.parseInt(sc.nextLine());
                if(num == 0) {
                    System.out.println("종료되었습니다.");
                    break;
                }
                switch (num){
                    case 1: ab.save(); break;
                    case 2: ab.update(); break;
                    case 3: ab.delete(); break;
                    case 4: ab.searchBy(); break;
                    case 5: ab.allInfo(); break;
                    default: throw new Exception("선택하신 번호는 없습니다. 다시 선택해주세요.");
                }
                System.out.println();
            } catch (NumberFormatException e){
                System.out.println("잘못된 입력 형식입니다. 숫자를 입력해주세요.");
            } catch (Exception e){
                System.out.println(e.getMessage());
            } finally {
                System.out.println();
            }
        }

        ab.saveFile();
    }

    public static void start(){
        System.out.println("*****************************************************");
        System.out.println("\t\t\t\t [   주 소 록   ]");
        System.out.println("\t\t\t\t  1. 연락처 저장 ");
        System.out.println("\t\t\t\t  2. 연락처 수정 ");
        System.out.println("\t\t\t\t  3. 연락처 삭제 ");
        System.out.println("\t\t\t\t  4. 연락처 검색 ");
        System.out.println("\t\t\t\t  5. 연락처 목록 ");
        System.out.println("\t\t\t\t  0. 종료 ");
        System.out.print("\t\t\t\t  선택 >> ");
    }
}
