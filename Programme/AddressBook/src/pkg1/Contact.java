import com.opencsv.bean.CsvBindByPosition;

import java.util.Arrays;
import java.util.Objects;

public class Contact implements Comparable {
    //NAME, PHONE, EMAIL
    @CsvBindByPosition(position = 0)
    String name;
    @CsvBindByPosition(position = 1)
    String phone;
    @CsvBindByPosition(position = 2)
    String email;

    public Contact() {
    }

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

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

    /**
     * 오름차순 정렬
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Object o) {
        Contact c = (Contact) o;
        int num = name.compareToIgnoreCase(c.name);

        if(num == 0){   //번호 정렬
            if(phone.compareTo(c.phone) < 0){
                return -1;
            } else if (phone.compareTo(c.phone) > 0) {
                return 1;
            } else {
                if (email.compareToIgnoreCase(c.email) < 0){
                    return -1;
                } else if (email.compareToIgnoreCase(c.email) > 0) {
                    return 1;
                } else return 0;
            }
        } else if (num < 0){
            return -1;
        } else
            return 1;
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
        return "[ 이름 : " + name + "\t 전화번호 : " + phone + "\t\t 이메일 : " + email + "]";
//        return stringBuilder();
    }

    public String stringBuilder() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"name\":").append(name).append(",");
        sb.append("\"phone\":").append(phone).append(",");
        sb.append("\"email\":").append(email);
        sb.append("}");
        return sb.toString();
    }
}
