package edu.sdccd.cisc191.template;

import javafx.collections.ListChangeListener;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

import com.sun.javafx.scene.control.skin.TableViewSkin;

import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;

public class Expenses {
    BorderPane eMenu;
    TableColumn<ExpenseObject, String> date;
    TableColumn<ExpenseObject, String> description;
    TableColumn<ExpenseObject, Integer> amount;
    HBox userEnter;
    DatePicker datep;
    TextField descriptionEnter;
    TextField amountEnter;
    Button addNewRow ;

    TableView table;
    TableColumn Date;
    TableColumn Description;
    TableColumn Amount;
    Label l;
    int total = 0;
    DataWriter data = new DataWriter();

    public static ArrayList<String> expenseList = new ArrayList<String>();
    public Expenses(BorderPane root) {

        BorderPane eMenu = new BorderPane();

        l = new Label("The total amount is: " + total);


        //TEXT FIELDS
        userEnter = new HBox();
        datep = new DatePicker();
        descriptionEnter = new TextField("Enter Description of Expense");
        amountEnter = new TextField("Enter Numeric Value of Expense");

        userEnter.getChildren().add(datep);
        userEnter.getChildren().add(descriptionEnter);
        userEnter.getChildren().add(amountEnter);
        userEnter.getChildren().add(l);
        root.setTop(userEnter);

        //TABLE
        addNewRow = new Button("Add Values");
        root.setBottom(addNewRow);
        table = new TableView();
        table.setEditable(false);

        Date = new TableColumn("Date");
        Date.setCellValueFactory(new PropertyValueFactory<>("Date"));

        Description = new TableColumn("Description");
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));

        Amount = new TableColumn("Amount");
        Amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));

        table.getColumns().addAll(Date, Description, Amount);

        table.setPrefSize(200, 200);
        table.setFixedCellSize(75);
        eMenu.getChildren().add(table);
        root.setCenter(table);
        autoFitTable(table);
        addNewRow.setOnAction(e -> {

            try {
                addRecord();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

            updateTotal();
            l.setText("The total amount is: " + total);
            System.out.println("Total Balance: " + total);


        });

    }
        public String getDate(){
           return datep.getValue().toString();
        }
        public String getDesc(){
            return descriptionEnter.getText();
        }
        public Double getAmount(){
            return Double.valueOf(amountEnter.getText());
        }
        public ExpenseObject createNew(String dat, String desc, Double i){
           return new ExpenseObject( getDate(), getDesc(), getAmount());
        }

        public String condenseText(ExpenseObject Expense){

        ExpenseObject expense = Expense;

            String s = "Date: " + Expense.getDate() + " " +  " Description of Cost: "  + Expense.getDescription() +  " Amount: " + Expense.getAmount() + ", ";

        return s;

        }

        public void addRecord() throws Exception {

            ExpenseObject expenses = new ExpenseObject( datep.getValue().toString(), descriptionEnter.getText(), Double.valueOf(amountEnter.getText()));
            table.getItems().add(expenses);
            String str = condenseText(expenses);
            expenseList.add(str);

            data.writeText();
            System.out.println("Reading data.txt " + data.readTextData());
    }
    public static ArrayList<String> getList(){
        return expenseList;
    }
        public int updateTotal() {
            if(amountEnter.getText().equals("Enter Numeric Value of Expense")){
                amountEnter.setText("Please enter a number!");
            }else {
                Double amountChange = parseDouble(amountEnter.getText());
                //System.out.println(amountChange);
                total += amountChange;
            }
            return total;
        }

    private static Method columnToFitMethod;

    static {
        try {
            columnToFitMethod = TableViewSkin.class.getDeclaredMethod("resizeColumnToFitContent", TableColumn.class, int.class);
            columnToFitMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static void autoFitTable(TableView tableView) {
        tableView.getItems().addListener(new ListChangeListener<Object>() {
            public void onChanged(Change<?> c) {
                for (Object column : tableView.getColumns()) {
                    try {
                        columnToFitMethod.invoke(tableView.getSkin(), column, -1);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


}
