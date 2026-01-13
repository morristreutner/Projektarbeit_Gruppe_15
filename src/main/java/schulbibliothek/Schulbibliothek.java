package schulbibliothek;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Schulbibliothek extends JFrame {
    private JPanel hauptPanel;
    private JLabel nameLabel;
    private JTextField buchnameTextField;
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
    private JLabel wunschlisteLabel;
    private JLabel kriterienLabel;
    private ArrayList<Buch> buch = new ArrayList<>();
    DefaultListModel<String> myList = new DefaultListModel<>();


    public Schulbibliothek() {

        //Damit leihfristTextField schon bei Start den passenden Wert zur ComboBox hat
        leihfristTextField.setText("4 Wochen");

        liste.setModel(myList);

        // Methodenaufruf für die Beispieldaten
        initObjekte();

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
            /*Werte aus den TextFields,der ComboBox und der CheckBox holen und in diesen Variablen speichern
            mit Exception Handeling für eine Benutzerfreundliche Oberfläche
             */
        try {
            //Exception sodass die Felder nicht leer sein dürfen
            if (buchnameTextField.getText().trim().isEmpty() ||
                    autorTextField.getText().trim().isEmpty() ||
                    fachTextField.getText().trim().isEmpty() ||
                    verlagTextField.getText().trim().isEmpty() ||
                    isbnTextField.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Leer");
            }

            String buchname = buchnameTextField.getText();
            String autor = autorTextField.getText().trim();

            //Exception damit der Autorname nur aus Buchstaben besteht, Vor- und Nachname aber möglich sind
            if (!autor.matches("[a-zA-ZäöüÄÖÜß]{2,}(\\s[a-zA-ZäöüÄÖÜß]{2,})*")) { //Regex mithilfe von KI
                throw new IllegalArgumentException("Autor");
            }

            String fach = fachTextField.getText();
            String verlag = verlagTextField.getText();

            //Exception um nur Buchstaben zu erlauben
            if (!verlag.matches("[a-zA-ZäöüÄÖÜß]+")) { //Regex mithilfe von KI
                throw new IllegalArgumentException();
            }

            long isbn = Long.parseLong(isbnTextField.getText());
            //Exception dass die isbn immer 13 Zahlen haben muss (nur Zahlen sind in NumberFormatException mitinbegriffen)
            if (isbnTextField.getText().length() != 13 || !isbnTextField.getText().startsWith("978")) {
                throw new NumberFormatException();
            }

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
            Buch b = new Buch(buchname, autor, fach, verlag, buchart, isbn, vorhanden, leihfrist);
            buch.add(b);

            //Nach dem speichern die Textfelder leeren(bzw leihfrist wieder auf 4 Wochen setzten) um erneut reinschreiben zu können
            buchnameTextField.setText("");
            autorTextField.setText("");
            fachTextField.setText("");
            verlagTextField.setText("");
            isbnTextField.setText("");
            buchartComboBox.setSelectedIndex(0);
            vorhandenCheckBox.setSelected(false);
            leihfristTextField.setText("4 Wochen");

        //catch Block für die geworfenen Exceptions um Fenster mit der jeweiligen Fehlermeldung erscheinen zu lassen
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Die ISBN muss mit '978' anfangen und" +
                                                                            " darf nur aus genau 13 Zahlen bestehen");
        } catch (IllegalArgumentException e) {
            if ("Leer".equals(e.getMessage())) {
                JOptionPane.showMessageDialog(null, "Die Felder dürfen nicht leer sein");
            } else if ("Autor".equals(e.getMessage())) {
                JOptionPane.showMessageDialog(null, "Der Name des Autors darf nur aus Buchstaben bestehen");
            } else {
                JOptionPane.showMessageDialog(null,"Ungültiger Verlag! Nur Buchstaben eingeben!");
            }
        }


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
        frame.setSize(600, 350);
        frame.setContentPane(new Schulbibliothek().hauptPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    // Methode die 3 Objekte an der JUnit List hinzufügt und diese direkt am anfang anzeigt
    public void initObjekte() {
        Buch taschenbuch = new Buch("Statistik 1", "Luis Schmidt", "Mathematik",
                "Lügge", "Taschenbuch", 9783157783629L, true, "4 Wochen");

        Buch hardcover = new Buch("Business Result", "Rebecca Turner", "Englisch",
                "Oxford", "Hardcover", 9783194738965L, false, "3 Wochen");

        Buch eBook = new Buch("BWL 2", "Franziskus Eberhardt", "Wirtschaft",
                "Carlsen", "E-Book", 9783225743366L, true, "unbegrenzt");


        // Die drei Objekte wurden zu der ArrayList hinzugefügt
        buch.add(taschenbuch);
        buch.add(hardcover);
        buch.add(eBook);
    }
}


