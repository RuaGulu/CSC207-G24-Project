package view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;

public class LabelTextPanelTest {

    private JLabel testLabel;
    private JTextField testTextField;
    private LabelTextPanel testPanel;

    @BeforeEach
    public void setUp() {
        testLabel = new JLabel("Test Label");
        testTextField = new JTextField(10);
        testPanel = new LabelTextPanel(testLabel, testTextField);
    }

    @Test
    public void testConstructor() {
        assertNotNull(testPanel);
    }

    @Test
    public void testComponentsAdded() {
        // Check if label and textField are added to the panel
        assertEquals(2, testPanel.getComponentCount());
        assertEquals(testLabel, testPanel.getComponent(0));
        assertEquals(testTextField, testPanel.getComponent(1));
    }

}
