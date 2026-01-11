package schulbibliothek;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuchTest {

   @Test
    //Test Methode mit einem erzeugten Objekt das Prüft ob es vorhanden ist.
    void testAusleihbarVorhandenTrue() {
       Buch b1 = new Buch("Elemente der Chemie","Martin Müller", "Chemie", "Carlsen",
               "Hardcover", 9783642865139L, true, "3 Wochen");

       //Prüft ob vorhanden True ist indem der Text in expected mit dem in der Methode ausleihbar() verglichen wird.
       assertEquals("Das Buch ist vorhanden.", b1.ausleihbar());
    }

    @Test
    //Test Methode mit einem erzeugten Objekt das Prüft ob es nicht vorhanden ist.
    void testAusleihbarVorhandenFalse() {
       Buch b2 = new Buch("Java Programmieren","Michael Kofler", "Informatik","BMU Verlag",
               "Taschenbuch",9783836283922L,false,"4 Wochen");

       //Prüft ob vorhanden False ist indem der Text in expected mit dem in der Methode ausleihbar() verglichen wird.
       assertEquals("Das Buch ist nicht vorhanden.",b2.ausleihbar());
    }
}