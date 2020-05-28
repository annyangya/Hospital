package entity;

public class Patient {
    private String Pno;
    private String Pname;
    private String Page;
    private String Psex;
    private String Pid;
    private String Ptel;
    private String Pdoc;
    private String Pward;
    private String Pbed;
    private String Penter;
    private String Pout;

    public Patient(){

    }

    public Patient(String pno, String pname, String page, String psex, String pid, String ptel, String pdoc, String pward, String pbed, String penter, String pout) {
        Pno = pno;
        Pname = pname;
        Page = page;
        Psex = psex;
        Pid = pid;
        Ptel = ptel;
        Pdoc = pdoc;
        Pward = pward;
        Pbed = pbed;
        Penter = penter;
        Pout = pout;
    }

    public String getPno() {
        return Pno;
    }

    public void setPno(String pno) {
        Pno = pno;
    }

    public String getPname() {
        return Pname;
    }

    public void setPname(String pname) {
        Pname = pname;
    }

    public String getPage() {
        return Page;
    }

    public void setPage(String page) {
        Page = page;
    }

    public String getPsex() {
        return Psex;
    }

    public void setPsex(String psex) {
        Psex = psex;
    }

    public String getPid() {
        return Pid;
    }

    public void setPid(String pid) {
        Pid = pid;
    }

    public String getPtel() {
        return Ptel;
    }

    public void setPtel(String ptel) {
        Ptel = ptel;
    }

    public String getPdoc() {
        return Pdoc;
    }

    public void setPdoc(String pdoc) {
        Pdoc = pdoc;
    }

    public String getPward() {
        return Pward;
    }

    public void setPward(String pward) {
        Pward = pward;
    }

    public String getPbed() {
        return Pbed;
    }

    public void setPbed(String pbed) {
        Pbed = pbed;
    }

    public String getPenter() {
        return Penter;
    }

    public void setPenter(String penter) {
        Penter = penter;
    }

    public String getPout() {
        return Pout;
    }

    public void setPout(String pout) {
        Pout = pout;
    }
}
