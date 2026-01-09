package schulbibliothek;

import javax.swing.*;

public class Schulbibliothek extends JFrame {
    private JPanel hauptPanel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel autorLabel;
    private JTextField autorTextField;
    private JLabel fachLabel;
    private JTextField fachTextField;
    private JLabel verlagLabel;
    private JTextField verlagTextField;
    private JLabel isbnLabel;
    private JTextField isbnTextField;
    private JLabel buchartLabel;
    private JComboBox buchartComboBox;
    private JLabel vorahndenLabel;
    private JCheckBox vorhandenCheckBox;
    private JButton speicherButton;
    private JButton ausgebenButton;
    private JTextArea listeTextArea;
    private JScrollPane listeScrollPanel;
    private JComboBox filterComboBox;
    private JButton filterButton;
    private JLabel leihfristLabel;
    private JTextField leihfristTextField;

    //Main zum Fenster ausführen über FormMain generiert
    public static void main(String[] args) {
        JFrame frame = new JFrame("Schulbibliothek");
        frame.setContentPane(new Schulbibliothek().hauptPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}


