package edu.sdccd.cisc191.template;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.BorderPane;


public class ExpenseObject {
    private SimpleStringProperty date;
    private SimpleStringProperty description;
    private SimpleDoubleProperty amount;



    public ExpenseObject(String date, String description, Double amount){


        this.date = new SimpleStringProperty(date);
        this.description = new SimpleStringProperty(description);
        this.amount = new SimpleDoubleProperty(amount);

    }



    public String getDate(){
        return date.get();
    }
    public Double getAmount(){
       return amount.get();
    }
    public String getDescription(){
        return description.get();
    }



}
