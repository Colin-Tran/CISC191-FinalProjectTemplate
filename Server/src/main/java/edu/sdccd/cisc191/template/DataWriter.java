package edu.sdccd.cisc191.template;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class DataWriter <T> {

    static ArrayList<String> expensesList = Expenses.getList();


    public void writeText(){
        File newData = new File("data.txt" + "");
        try {
                    FileWriter fw = new FileWriter(newData, false);
                    BufferedWriter writer = new BufferedWriter(fw);
                    for (String str : expensesList) {
                        writer.write("\n" + str);
                        writer.flush();
                    }
                } catch (IOException e) {
                    System.out.println("Failed to append");
                }

            }

    public String readTextData() {
        String result = "";
        File data = new File("data.txt");
        try {
            Scanner sc = new Scanner(data);
            while (sc.hasNextLine()) {
                result += sc.nextLine();
            }
            return result;
        }
        catch (FileNotFoundException p) {
            return "";
        }
    }

}
