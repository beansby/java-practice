package pkg2;

import java.util.Objects;

/**
 * 연락처.
 * - 이름
 * - 전화번호
 * - 이메일
 */
public class Contact implements Comparable<Contact> {
    String name;
    String phone;
    String email;

    //constructor
    public Contact() {}

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    //getter, setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int compareTo(Contact o) {
        // 정렬 1) 이름순
        int result = name.compareToIgnoreCase(o.name);

        if(result > 0){ return 1; }
        else if (result == 0){
            // 정렬 2) 번호순
            if(phone.compareTo(o.phone) > 0 ){ return  1; }
            else if(phone.compareTo(o.phone) < 0) {return  -1;}
            else {
                // 정렬 3) 이메일순
                if(email.compareToIgnoreCase(o.email) < 0) { return -1; }
                else if (email.compareToIgnoreCase(o.email) > 0 ) { return  1;}
                else return 0;
            }
        } else //(result < 0)
            return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return name.equals(contact.name) && Objects.equals(phone, contact.phone) && Objects.equals(email, contact.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, email);
    }

    @Override
    public String toString() {
        return "[ "+ name +" \t "+ phone +" \t "+ email +" ]";
    }
}
