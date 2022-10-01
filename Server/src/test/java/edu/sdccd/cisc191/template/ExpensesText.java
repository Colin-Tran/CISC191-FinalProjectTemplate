package edu.sdccd.cisc191.template;

import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.BorderPane;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ExpensesText {
    @Test
    void testExpensesandObject(){
        JFXPanel panel = new JFXPanel();
        BorderPane root = new BorderPane();
        Expenses test = new Expenses(root);


        //tests to make sure it correctly created the window with the starting values in the date and
        assertEquals("Enter Numeric Value of Expense", test.amountEnter.getText());
        assertEquals("Enter Description of Expense", test.descriptionEnter.getText());
        assertEquals(null, test.datep.getValue());
    }
}
