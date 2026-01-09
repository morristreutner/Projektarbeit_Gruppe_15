package schulbibliothek;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private JList liste;
    private ArrayList<Buch> buch = new ArrayList<>();
    DefaultListModel<String> myList = new DefaultListModel<>();


    public Schulbibliothek() {
        liste.setModel(myList);
        speicherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                speichern();
            }
        });
        buchartComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Combobox ActionListener um im Leihfrist Textfeld den Wert anzuzeigen
                String ausgewaehlt = buchartComboBox.getSelectedItem().toString();
                    if (ausgewaehlt.equals("Taschenbuch")) {
                        leihfristTextField.setText("4 Wochen");
                    } else if (ausgewaehlt.equals("Hardcover")) {
                        leihfristTextField.setText("3 Wochen");
                    } else if (ausgewaehlt.equals("Broschur")) {
                        leihfristTextField.setText("2 Wochen");
                    } else
                        leihfristTextField.setText("unbegrenzt");

            }
        });
        ausgebenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ausgeben();
            }
        });

        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtern();
            }
        });
    }
    public void speichern() {
            //Werte aus den TextFields,der ComboBox und der CheckBox holen und in diesen Variablen speichern
            String name = nameTextField.getText();
            String autor = autorTextField.getText();
            String fach = fachTextField.getText();
            String verlag = verlagTextField.getText();
            long isbn = Long.parseLong(isbnTextField.getText());
            String buchart = buchartComboBox.getSelectedItem().toString();
            boolean vorhanden = vorhandenCheckBox.isSelected();
            String leihfrist;
            if (buchart.equals("Taschenbuch")) {
                leihfrist = "4 Wochen";
            } else if (buchart.equals("Hardcover")) {
                leihfrist = "3 Wochen";
            } else if (buchart.equals("Broschur")) {
                leihfrist = "2 Wochen";
            } else
                leihfrist = "unbegrenzt";

            //Objekt erstellen und in der ArrayList speichern
            Buch b = new Buch(name,autor,fach,verlag,buchart,isbn,vorhanden,leihfrist);
            buch.add(b);

            //Nach dem speichern die Textfelder leeren um erneut reinschreiben zu können
            nameTextField.setText("");
            autorTextField.setText("");
            fachTextField.setText("");
            verlagTextField.setText("");
            isbnTextField.setText("");
            buchartComboBox.setSelectedIndex(0);
            vorhandenCheckBox.setSelected(false);
            leihfristTextField.setText("");

    }
    // Methode ausgeben erstellen und die angegebenen Bücher in der JList anzeigen
    public void ausgeben() {
        myList.clear();
        for(Buch c : buch) {
            myList.addElement(c.toString());
        }
    }
    /* Bei Methode filtern kann man genaue Buchart wählen und die in der JList anzeigen.
    Wenn ALLE ausgewählt wird, werden alle Bücher angezeigt */
    public void filtern() {
        String filtern = filterComboBox.getSelectedItem().toString();
        myList.clear();
        for(Buch d : buch) {
            if (filtern.equals("Alle") || d.getBuchart().equals(filtern)) {
                myList.addElement(d.toString());
            }


        }

    }

    //Main zum Fenster ausführen über FormMain generiert
    public static void main(String[] args) {
        JFrame frame = new JFrame("Schulbibliothek");
        frame.setContentPane(new Schulbibliothek().hauptPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}


