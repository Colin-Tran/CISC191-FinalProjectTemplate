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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import com.sun.javafx.scene.control.skin.TableViewSkin;
import javafx.scene.layout.VBox;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Expenses implements Getters{
    BorderPane eMenu;
    TableColumn<ExpenseObject, String> date;
    TableColumn<ExpenseObject, String> description;
    TableColumn<ExpenseObject, Integer> amount;
    HBox userEnter;
    HBox bottomButtons;
    DatePicker datep;
    TextField descriptionEnter;
    TextField amountEnter;

    TextField investmentAmount;
    TextField years;
    TextField interestRate;

    Button addNewRow ;
    Button exitButton;
    TableView table;
    TableColumn Date;
    TableColumn Description;
    TableColumn Amount;
    Label l;
    int total = 0;

    double investment;
    double timeInYears;
    double rateBack;

    public Expenses(BorderPane root) {

        BorderPane eMenu = new BorderPane();

        l = new Label("The total amount is: " + total);


        //TEXT FIELDS
        userEnter = new HBox();
        datep = new DatePicker();
        descriptionEnter = new TextField("Enter Description of Expense");
        amountEnter = new TextField("Enter Numeric Value of Expense");

        Label investmentAmountLabel = new Label("Investment Amount:");
        Label NumberOfYearsLabel = new Label("Number Of Years:");
        Label monthlyInterestRateLabel = new Label("Monthly Interest Rate:");

        investmentAmount = new TextField("Enter Numeric Value of the amount you are going to invest");
        years = new TextField("Enter Numeric Value of the Number of Years planned on investing");
        interestRate = new TextField("Enter the Percentage Value of the monthly interest rate on your investment");

        userEnter.getChildren().add(datep);
        userEnter.getChildren().add(descriptionEnter);
        userEnter.getChildren().add(amountEnter);
        userEnter.getChildren().add(l);
        root.setTop(userEnter);

        //TABLE and Buttons
        VBox interestRateCalculator = new VBox();
        Button CalculateButton = new Button("Calculate");

        interestRateCalculator.getChildren().add(investmentAmountLabel);
        interestRateCalculator.getChildren().add(investmentAmount);
        interestRateCalculator.getChildren().add(NumberOfYearsLabel);
        interestRateCalculator.getChildren().add(years);
        interestRateCalculator.getChildren().add(monthlyInterestRateLabel);
        interestRateCalculator.getChildren().add(interestRate);
        interestRateCalculator.getChildren().add(CalculateButton);

        CalculateButton.setOnAction(e -> {
            computeCapital(getInvestment(),getTimeInYears(),getRateBack());
        });

        root.setRight(interestRateCalculator);

        bottomButtons = new HBox();

        addNewRow = new Button("Add Values");
        exitButton = new Button("Exit");

        exitButton.setOnAction(e -> {
            System.exit(0);
        });

        bottomButtons.getChildren().add(exitButton);
        bottomButtons.getChildren().add(addNewRow);
        root.setBottom(bottomButtons);

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
            addRecord();
            updateTotal();
            l.setText("The total amount is: " + total);
            System.out.println(total);
        });


    }

        public double getInvestment(){
           return investment = parseDouble(investmentAmount.getText());
    }
        public double getTimeInYears(){
           return timeInYears = parseDouble(years.getText());
        }
        public double getRateBack(){
            return rateBack = parseDouble(interestRate.getText())  / 100;
        }

        public String getDate(){
           return datep.getValue().toString();
        }
        public String getDescription(){
            return descriptionEnter.getText();
        }
        public int getAmount(){
            return Integer.valueOf(amountEnter.getText());
        }

        public ExpenseObject createNew(String dat, String desc, Integer i){
           return new ExpenseObject( getDate(), getDescription(), getAmount());
        }


        public void addRecord(){
            ExpenseObject expenses = new ExpenseObject( datep.getValue().toString(), descriptionEnter.getText(), Integer.valueOf(amountEnter.getText()));
            table.getItems().add(expenses);
        }

        public int updateTotal() {
            if(amountEnter.getText().equals("Enter Numeric Value of Expense")){
                amountEnter.setText("Please enter a number!");
            }else {
                int amountChange = parseInt(amountEnter.getText());
                System.out.println(amountChange);
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
    public double computeCapital(double capital, double year, double interestRates) {

        if (year == 0) {
            System.out.println("Your invested amount after "+ getTimeInYears() + "is " + capital);
            return capital;
        } else {
             capital += (capital * interestRates);

            System.out.println("Your invested amount after "+ getTimeInYears() + "is " + capital);
            return computeCapital(capital, year-1, interestRates);
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
