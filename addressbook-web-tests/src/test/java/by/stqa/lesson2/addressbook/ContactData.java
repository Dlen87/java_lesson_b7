package by.stqa.lesson2.addressbook;

public class ContactData {
    private final String firstname;
    private final String middlename;
    private final String lastname;
    private final String nickname;
    private final String company;
    private final String address;
    private final String homephone;
    private final String mobile;
    private final String email;
    private final String address2;
    private final String phone2;
    private final String bday;
    private final String bmonth;
    private final String byear;
    private final String new_group;

    public ContactData(String firstname, String middlename, String lastname, String nickname, String company, String address, String homephone, String mobile, String email, String address2, String phone2, String bday, String bmonth, String byear, String new_group) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.company = company;
        this.address = address;
        this.homephone = homephone;
        this.mobile = mobile;
        this.email = email;
        this.address2 = address2;
        this.phone2 = phone2;
        this.bday = bday;
        this.bmonth = bmonth;
        this.byear = byear;
        this.new_group = new_group;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHomephone() {
        return homephone;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress2() {
        return address2;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getBday() {
        return bday;
    }

    public String getBmonth() {
        return bmonth;
    }

    public String getByear() {
        return byear;
    }

    public String getNew_group() {
        return new_group;
    }
}
