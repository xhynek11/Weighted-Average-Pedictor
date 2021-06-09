package cz.hynek.prumer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class IndexPredmetu implements Comparable<IndexPredmetu> {
    private Double average;
    private int count;
    private ArrayList<Predmet> subjects = new ArrayList<Predmet>();

    public IndexPredmetu(){
        this.average=0D;
        this.count=0;
    }

    public void addSubject(String name,int weight){
        if(subjects.isEmpty()){
            subjects.add(new Predmet(name,weight));
            count++;
        }else{
            for (Predmet p:subjects) {
                if(p.getNazev()==name){
                    return;
                }
            }
            subjects.add(new Predmet(name,weight));
            count++;
        }
    }

    public void addSubjectAll(String name,int weight, Double grade){
        if(subjects.isEmpty()){
            subjects.add(new Predmet(name,weight,grade));
            count++;
        }else{
            for (Predmet p:subjects) {
                if(p.getNazev()==name){
                    return;
                }
            }
            subjects.add(new Predmet(name,weight,grade));
            count++;
        }
    }

    public void addSubject(String name,int weight, Double grade,boolean wantChange){
        if(subjects.isEmpty()){
            subjects.add(new Predmet(name,weight,grade,wantChange));
            count++;
        }else{
            for (Predmet p:subjects) {
                if(p.getNazev()==name){
                    return;
                }
            }
            subjects.add(new Predmet(name,weight,grade,wantChange));
            count++;
        }
    }

    public double calculateAverage(){
        double multiply=0;
        double add=0;
        for (Predmet p:subjects) {
            if(p.getZnamka()==42){
                continue;
            }
            multiply+=p.getZnamka()*p.getVaha();
            add+= p.getVaha();
        }
        average=multiply/add;
        return average;
    }

    public Predmet findByName(String name){
        for (Predmet p:subjects) {
            if (p.getNazev().equals(name)){
                return p;
            }
        }
        return null;
    }

    public void changeGrade(String name,Double grade){
        subjects.get(subjects.indexOf(findByName(name))).setZnamka(grade);
    }

    @Override
    public String toString() {
        String toReturn;
        toReturn = "average=" + average +" => ";
        for (Predmet p:subjects) {
            toReturn+= p.getNazev()+"["+p.getZnamkaString()+","+p.getVaha()+"], ";
        }
        return toReturn;
    }

    public boolean readFile(String file){
        try {
            File myObj = new File("src/main/resources/"+file);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] cutData = data.split(",");
                subjects.add(new Predmet(cutData[0],cutData[1],cutData[2],cutData[3]));
            }
            myReader.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
    }

    public IndexPredmetu getCopy(){
        IndexPredmetu ip = new IndexPredmetu();
        ip.setAverage(this.average);
        ip.setCount(this.count);
        for (Predmet p:subjects) {
           ip.subjects.add(new Predmet(p.getNazev(),p.getVaha(),p.getZnamka(),p.isWantChange()));
        }
        return ip;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<Predmet> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Predmet> subjects) {
        this.subjects = subjects;
    }

    public boolean isValid(Double averageEdge){
        boolean isNull=false;
        if(calculateAverage()<averageEdge){
            isNull=true;
            for (Predmet subject: subjects) {
                if(subject.getZnamka()==4){
                    isNull=false;
                }
            }
        }
        return isNull?true:false;
    }

    public IndexPredmetu getCopyWithNewGrade(String name){
        IndexPredmetu ip = new IndexPredmetu();
        ip.setAverage(this.average);
        ip.setCount(this.count);
        for (Predmet p:subjects) {
            ip.subjects.add(new Predmet(p.getNazev(),p.getVaha(),p.getZnamka(),p.isWantChange()));
        }
        ip.findByName(name).setZnamka(ip.findByName(name).getZnamka()-0.5);
        return ip;
    }


    @Override
    public int compareTo(IndexPredmetu indexPredmetu) {
        Double number1 = calculateAverage();
        Double number2 = indexPredmetu.calculateAverage();
        return number1.compareTo(number2);
    }

}
