package edu.sdccd.cisc191.template;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.BorderPane;


public class ExpenseObject implements Getters{
    private SimpleStringProperty date;
    private SimpleStringProperty description;
    private SimpleIntegerProperty amount;



    public ExpenseObject(String date, String description, int amount){


        this.date = new SimpleStringProperty(date);
        this.description = new SimpleStringProperty(description);
        this.amount = new SimpleIntegerProperty(amount);

    }



    public String getDate(){
        return date.get();
    }
    public int getAmount(){
       return amount.get();
    }
    public String getDescription(){
        return description.get();
    }
    public void setDate(SimpleStringProperty date){
        this.date = date;
    }
    public void setDescription(SimpleStringProperty description){
        this.description = description;
    }
    public void setAmount(SimpleIntegerProperty amount){
        this.amount = amount;
    }



}
