package cz.hynek.prumer;

public class Predmet {

    private String nazev;
    private Integer vaha;
    private Double znamka;
    private boolean wantChange;

    public Predmet(String nazev, Integer vaha) {
        this.nazev = nazev;
        this.vaha = vaha;
        this.wantChange = true;
    }

    public Predmet(String nazev, Integer vaha, Double znamka) {
        this.nazev = nazev;
        this.vaha = vaha;
        this.znamka = znamka;
        this.wantChange = true;
    }

    public Predmet(String nazev, String znamka , String vaha, String wantChange) {
        this.nazev = nazev;
        this.vaha = Integer.parseInt(vaha);
        setGradeString(znamka);
        if (wantChange.equals("T")){
            this.wantChange = true;
        }else {
            this.wantChange = false;
        }

    }

    public Predmet(String nazev, Integer vaha, Double znamka, boolean wantChange) {
        this.nazev = nazev;
        this.vaha = vaha;
        this.znamka = znamka;
        this.wantChange = wantChange;
    }

    public Double getZnamka() {
        return znamka;
    }

    public void setZnamka(Double znamka) {
        this.znamka = znamka;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public Integer getVaha() {
        return vaha;
    }

    public void setVaha(Integer vaha) {
        this.vaha = vaha;
    }

    public String getZnamkaString(){
        if (znamka==1){
            return "A";
        }if (znamka==1.5){
            return "B";
        }if (znamka==2){
            return "C";
        }if (znamka==2.5){
            return "D";
        }if (znamka==3){
            return "E";
        }if (znamka==4){
            return "N";
        }
        return "F";
    }

    public void setGradeString(String grade){
        if(grade.equals("A")){
            this.znamka = 1D;
        }else if(grade.equals("B")){
            this.znamka = 1.5D;
        }else if(grade.equals("C")) {
            this.znamka = 2D;
        }else if(grade.equals("D")){
            this.znamka = 2.5D;
        }else if(grade.equals("E")){
            this.znamka = 3D;
        }else if (grade.equals("F")){
            this.znamka = 3.5D;
        }else{
        this.znamka =4D;
        }
    }
    public boolean isWantChange() {
        return wantChange;
    }

    public void setWantChange(boolean wantChange) {
        this.wantChange = wantChange;
    }

    @Override
    public String toString() {
        return "Predmet{" +
                "nazev='" + nazev + '\'' +
                ", vaha=" + vaha +
                ", znamka=" + znamka +
                '}';
    }

}
