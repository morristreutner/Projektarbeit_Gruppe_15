package schulbibliothek;

public class Buch {
    // Variablen für Bücher
    private String buchname;
    private String autor;
    private String fach;
    private String verlag;
    private long isbn;
    private String buchart;
    private boolean vorhanden;
    private String leihfrist;

    // Konstruktor um Objekte "Buch" zu erstellen
    public Buch(String buchname, String autor, String fach, String verlag, String buchart, long isbn, boolean vorhanden, String leihfrist) {
        this.buchname = buchname;
        this.autor = autor;
        this.fach = fach;
        this.verlag = verlag;
        this.buchart = buchart;
        this.isbn = isbn;
        this.vorhanden = vorhanden;
        this.leihfrist = leihfrist;
    }
    // toString Methode, um die Bücher in der Klasse Bibliothek in der JList lesbar auszugeben
    public String toString() {
        return "<html>"
                + "Name: " + buchname + "<br>"
                + "Autor: " + autor + "<br>"
                + "Verlag: " + verlag + "<br>"
                + "Fach: " + fach + "<br>"
                + "ISBN: " + isbn + "<br>"
                + "Buchart: " + buchart + "<br>"
                + "Vorhanden: " + ausleihbar() + "<br>"
                + "Leihfrist: " + leihfrist + "<br>"
                + "------------------------------"
                + "</html>";


}
    //Methode für den JUnit Test und Ausgabe in der toString Methode darüber
    public String ausleihbar() {
        if (vorhanden) {
            return "Das Buch ist vorhanden.";
        } else {
            return "Das Buch ist nicht vorhanden.";
        }
}

// Getter Methode ist Notwendig, damit man in der Klasse Bibliothek die Objekte erstellen kann
public String getBuchname() {
    return buchname;
}
public String getAutor() {
    return autor;
}
public String getVerlag() {
    return verlag;
}
public String getFach() {
    return fach;
}
public long getIsbn() {
    return isbn;
}
public String getBuchart() {
    return buchart;
}
public boolean isVorhanden() {
    return vorhanden;
}
public String getLeihfrist() {
    return leihfrist;
}
}

