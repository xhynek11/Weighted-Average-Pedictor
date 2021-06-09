package cz.hynek.prumer;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.util.Scanner;

import static cz.hynek.prumer.Service.clearConsole;

public class Main {
    public static void main(String[] args) {

        VazenyPrumerPredictor vpp = new VazenyPrumerPredictor();
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        int fromUser;
        Double averageEdge = 1.5;


        while(running) {
            System.out.println("[1] Read input from file\n"+
                    "[2] Insert input by console\n"+
                    "[3] Insert edge of average\n"+
                    "[4] Find all possibilities\n"+
                    "[0] Exit program");
            fromUser = sc.nextInt();
            switch (fromUser) {
                case 1:
                    if(vpp.readInputFromFile("grades.txt")){
                        System.out.println("INFO: Data from file loaded");
                    }
                    break;
                case 2:
                    vpp.insertInputByConsole();
                    break;
                case 3:
                    averageEdge = sc.nextDouble();
                    break;
                case 4:
                    vpp.findAllPosiabilities(averageEdge);
                    vpp.print();
                    System.out.println("Press ENTER to continue");
                    String stop = sc.nextLine();
                    break;
                case 0:
                    break;
            }
        }

    }
}
