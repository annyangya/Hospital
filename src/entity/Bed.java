package entity;

public class Bed {

    private String Wno;
    private String Bno;
    private String Boffice;
    private String Binform;

    public Bed(){

    }

    public Bed(String wno, String bno, String boffice, String binform) {
        Wno = wno;
        Bno = bno;
        Boffice = boffice;
        Binform = binform;
    }

    public String getWno() {
        return Wno;
    }

    public void setWno(String wno) {
        Wno = wno;
    }

    public String getBno() {
        return Bno;
    }

    public void setBno(String bno) {
        Bno = bno;
    }

    public String getBoffice() {
        return Boffice;
    }

    public void setBoffice(String boffice) {
        Boffice = boffice;
    }

    public String getBinform() {
        return Binform;
    }

    public void setBinform(String binform) {
        Binform = binform;
    }


    public static class BedBuilder{
        private Bed bed;

        public BedBuilder() {
            bed=new Bed();
        }

        public BedBuilder addBedWno(String bedWno){
            bed.setWno(bedWno);
            return this;
        }

        public BedBuilder addBedBno(String bedBno){
            bed.setBno(bedBno);
            return this;
        }

        public BedBuilder addBedBinform(String bedBinform){
            bed.setBinform(bedBinform);
            return this;
        }

        public BedBuilder addBedBoffice(String bedBoffice){
            bed.setBoffice(bedBoffice);
            return this;
        }

        public Bed createBed(){
            return this.bed;
        }
    }
}
