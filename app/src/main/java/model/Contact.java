package model;

/**
 * Created by Kenny on 2015/9/21.
 */
public class Contact {

    private String name;
    private String pinyin;
    private String number;
    private String address;

    public void setName(String name) { this.name = name; }
    public void setPinyin(String pinyin) { this.pinyin = pinyin; }
    public void setnumber(String number) { this.number = number; }
    public void setAddress(String address) { this.address = address; }

    public String getName() { return name; }
    public String getPinyin() { return pinyin; }
    public String getNumber() { return number; }
    public String getAddress() { return address; }

}
