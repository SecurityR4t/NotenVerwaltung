import java.util.*;
/**
 * 
 * Datenklasse zu einem Fach
 * 
 * @version 1.0 vom 15.07.2011
 * @author Tenbusch
 */

public class Fach {

  // Attribute
  private String fachname; // Fachname - Kurzbezeichnung
  private int notenziel; // Wunschnote im Fach
  private List<Note> noten; // Liste der Noten des Fachs

  // Konstruktor
  public Fach(String fachname, int notenziel) {
    this.fachname = fachname;
    this.notenziel = notenziel;
    this.noten = new Vector<Note>();
  }

  // Getter & Setter
  public int getAnzahlNoten() {
    return noten.size();
  }

  public Note getNote(int stelle) {
    return noten.get(stelle);
  }

  public String getFachname() {
    return this.fachname;
  }

  public int getNotenziel() {
    return notenziel;
  }

  public void setNotenziel(int notenziel) {
    this.notenziel = notenziel;
  }

  // Methoden
  // fügt eine neue Note ein
  public void neueNote(Note note) {
    noten.add(note);
  }

  // ändert die Note an der angegebenen Stelle in der Liste (fängt mit 0 an!)
  public void aenderNote(Note note, int stelle) {
    // Fehlerabfrage, wenn der Benutzer mist macht
    if (stelle <= this.noten.size())
      noten.remove(stelle);
      noten.add(note);
  }

  // löscht eine Note aus der Liste und lässt die Leerstelle in der Liste
  // verschwinden
  public void loescheNote(int stelle) {
    if (stelle <= this.noten.size()) {
      // löscht Note an ausgwählter stelle
      noten.remove(stelle);
    }
  }

  // berechnet die aktuelle Zeugnisnote
  public String getZeugnisnote() {
    float muendlich = 0F, schriftlich = 0F, durchschnitt = 0F;
    int anzahlKlausuren = 0, anzahlBesondereNoten = 0;
    int gewichtung = 100;

    // Abfangen, fall für eine leere Liste die Note berechnet wird
    // das Schlüsselwort return beendet die ganze Methode
    if (noten.size() == 0)
      return "o.B."; // also "ohne Bewertung"

    // Berechnung des schriftlichen Teil
    // Notenliste durchgehen und nach Klausuren suchen
    for (int i = 0; i < this.noten.size(); i++) {
      if (noten.get(i).isKlausur()) {
        anzahlKlausuren++; // Klausurzähler +1
        schriftlich += noten.get(i).getNotenpunkte();// Klausurnote
        // aufaddieren
      }
    }
    // Durchschnitt der Klausuren berechnen
    schriftlich = schriftlich / anzahlKlausuren;

    // Berechnung mündlicher Teil
    // Notenliste durchgehen und nach besonder Benotung suchen
    for (int i = 0; i < this.noten.size(); i++) {
      if (noten.get(i).getGewichtung() != 0 && !noten.get(i).isKlausur()) {
        anzahlBesondereNoten++;// Notenanzahl merken
        // Gewichtung abziehen
        gewichtung -= noten.get(i).getGewichtung();
        muendlich += noten.get(i).getNotenpunkte() // Notenpunkt
                   * noten.get(i).getGewichtung(); // mal Gewichtung
      }
    }

    // Notenliste restliche Noten
    for (int i = 0; i < this.noten.size(); i++) {
      if (noten.get(i).getGewichtung() == 0 && !noten.get(i).isKlausur()) {
        muendlich += noten.get(i).getNotenpunkte() // Notenpunkt
            * (float) gewichtung
            / (this.noten.size() - anzahlKlausuren - anzahlBesondereNoten); // mal
        // Gewichtung
      }
    }
    // Durchschnitt der mündlichen Note ausrechnen
    muendlich /= 100.0F;

    // Klausuren können unterschiedlich in die Endnote einfließen
    // Berechnung des mathematischen Durchschnitts
    if (anzahlKlausuren == 0) {
      durchschnitt = muendlich;
    } else if (anzahlKlausuren == 1) {
      durchschnitt = (muendlich * 2 + schriftlich) / 3.0F;
    } else {
      durchschnitt = (muendlich + schriftlich) / 2.0F;
    }
    // Berechnung der Endnote
    return Math.round(durchschnitt) + "";
  }
  
  public List<Note> getNoten() {
    return noten;
  }
  
  // gibt die Liste als Zeichenkette zurück
  public String toString() {
    // Fachname
    String notenliste = this.fachname + ": ";
    // Noten nacheinander in die Zeichenkette schreiben
    for (int i = 0; i < this.noten.size(); i++)
      // Notenpunkt aus dem Notenobjekt auslesen
      notenliste += noten.get(i).getNotenpunkte() + ", ";
    notenliste = notenliste.substring(0, notenliste.length()-2);
    return notenliste;
  }

  
}
