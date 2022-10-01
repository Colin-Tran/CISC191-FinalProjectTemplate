package edu.sdccd.cisc191.template;

import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.BorderPane;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ExpensesTest {
    @Test
    void testExpensesInitalizeTable(){
        JFXPanel panel = new JFXPanel();
        BorderPane root = new BorderPane();
        Expenses test = new Expenses(root);


        //tests to make sure it correctly created the window with the starting values in the date and
        assertEquals("Enter Numeric Value of Expense", test.amountEnter.getText());
        assertEquals("Enter Description of Expense", test.descriptionEnter.getText());
        assertEquals(null, test.datep.getValue());
    }

    @Test
    void testExpenseObjectCreation(){
        ExpenseObject expen = new ExpenseObject("2020-0-11", "Buying mother a gift", 2000);
        assertEquals("2020-0-11", expen.getDate());
        assertEquals("Buying mother a gift", expen.getDescription());
        assertEquals(2000, expen.getAmount());
    }
}
