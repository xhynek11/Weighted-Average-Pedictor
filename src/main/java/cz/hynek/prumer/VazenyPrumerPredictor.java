package cz.hynek.prumer;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeSet;

public class VazenyPrumerPredictor {

    TreeSet<IndexPredmetu> indexes = new TreeSet<IndexPredmetu>();
    PriorityQueue<IndexPredmetu> priorityQueue = new PriorityQueue<IndexPredmetu>();
    private IndexPredmetu ip = new IndexPredmetu();

    public void findAllPosiabilities(Double averageEdge){

        priorityQueue.add(ip);
        IndexPredmetu copy;
        while (true){
            if(priorityQueue.isEmpty()){
                return;
            }
            if (priorityQueue.peek().calculateAverage()<averageEdge){
                indexes.add(priorityQueue.peek());
            }
            copy = priorityQueue.poll().getCopy();
            for (Predmet subject: copy.getSubjects()) {
                if (subject.isWantChange() && subject.getZnamka()>1) {
                    priorityQueue.add(copy.getCopyWithNewGrade(subject.getNazev()));
                    if(priorityQueue.peek().calculateAverage()<averageEdge && !indexes.contains(priorityQueue.peek()))
                    {
                        indexes.add(priorityQueue.peek());
                    }
                    if (copy.calculateAverage()==1){
                        return;
                    }
                }
            }
        }

    }


    public void print() {
        for (IndexPredmetu indexPredmetu : indexes) {
            System.out.println(indexPredmetu);
        }
    }
    public void printPriQue(){
        for (IndexPredmetu indexPredmetu : priorityQueue) {
            System.out.println(indexPredmetu);
        }
    }

    public boolean readInputFromFile(String file){
        return ip.readFile(file);
    }

    public void insertInputByConsole() {
        Scanner sc = new Scanner(System.in);
        String name;
        int weight;
        Double grade;
        boolean wantChange,wantContinue=true;
        while(wantContinue) {
            System.out.println("ADD NEW SUBJECT");
            System.out.println("Name");
            name = sc.nextLine();
            System.out.println("Weight");
            weight = sc.nextInt();
            System.out.println("Grade");
            grade = Double.valueOf(sc.nextInt());
            System.out.println("Want Change[T/F]");
            if(sc.nextLine().equals("T")||sc.nextLine().equals("t")){
                wantChange=true;
            }else{
                wantChange=false;
            }
            ip.addSubject(name,weight,grade,wantChange);
            System.out.println("Do you want add another subject?[Y/N]");
            if(sc.nextLine().equals("Y")||sc.nextLine().equals("y")){
                wantContinue=true;
            }else{
                wantContinue=false;
            }
        }
    }
}
