package entity;

public class Doctor {
    private String Dno;
    private String Dname;
    private String Dage;
    private String Dsex;
    private String Dtel;
    private String Doffice;

    public Doctor(){

    }

    public Doctor(String dno, String dname, String dage, String dsex, String dtel, String doffice) {
        Dno = dno;
        Dname = dname;
        Dage = dage;
        Dsex = dsex;
        Dtel = dtel;
        Doffice = doffice;
    }

    public String getDno() {
        return Dno;
    }

    public void setDno(String dno) {
        Dno = dno;
    }

    public String getDname() {
        return Dname;
    }

    public void setDname(String dname) {
        Dname = dname;
    }

    public String getDage() {
        return Dage;
    }

    public void setDage(String dage) {
        Dage = dage;
    }

    public String getDsex() {
        return Dsex;
    }

    public void setDsex(String dsex) {
        Dsex = dsex;
    }

    public String getDtel() {
        return Dtel;
    }

    public void setDtel(String dtel) {
        Dtel = dtel;
    }

    public String getDoffice() {
        return Doffice;
    }

    public void setDoffice(String doffice) {
        Doffice = doffice;
    }
}
