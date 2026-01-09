package schulbibliothek;

public class Buch {
    // Variablen für Bücher
    private String name;
    private String autor;
    private String fach;
    private String verlag;
    private int isbn;
    private String buchart;
    private boolean vorhanden;
    private String leihfrist;

    // Konstruktor um Objekte "Buch" zu erstellen
    public Buch(String name, String autor, String fach, String verlag, String buchart, int isbn, boolean vorhanden, String leihfrist) {
        this.name = name;
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
                + "Name: " + name + "<br>"
                + "Autor: " + autor + "<br>"
                + "Verlag: " + verlag + "<br>"
                + "Fach: " + fach + "<br>"
                + "ISBN: " + isbn + "<br>"
                + "Buchart: " + buchart + "<br>"
                + "Vorhanden: " + (vorhanden ? "Ja" : "Nein") + "<br>"
                + "Leihfrist: " + leihfrist + "<br>"
                + "------------------------------"
                + "</html>";


}

// Getter Methode ist Notwendig, damit man in der Klasse Bibliothek die Objekte erstellen kann
public String getName() {
    return name;
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
public int getIsbn() {
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

