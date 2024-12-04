/**
 *  Datenklasse zu einer Note
 *
 *  @version 1.0 vom 15.07.2011
 *  @author Tenbusch
 */

public class Note{

  //Attribute
  private int notenpunkte;          //die Note an sich
  private String erbrachteLeistung; //Beschreibung der Leistung
  private int gewichtung;           //Gewichtung der Note, wenn vorhanden z.B. 10%
  private boolean isKlausur;        //Kenntlichmachung als Klausur
  
  //Konstruktoren
  public Note(){}
  //Notenkonstruktor z.B. new Note(13, Test, false)
  public Note(int np, String eL, boolean isKlausur){
    this.notenpunkte = np;
    this.erbrachteLeistung = eL;
    this.gewichtung = 0;  //0 ist die Kennzeichung für gleich wichtig
    this.isKlausur = isKlausur;//Kennzeichnung als Klausur zur späteren Endnotenberechung
  }
  //Notenkonstruktor mit besonderer Gewichtung z.B. new Note(8, Referat, 15)
  public Note(int np, String eL, int gewichtung){
    this.notenpunkte = np;
    this.erbrachteLeistung = eL;
    this.gewichtung = gewichtung;
    this.isKlausur = false;
  }
  
  //Getter & Setter
  public void setNotenpunkte(int notenpunkte){
    this.notenpunkte = notenpunkte;
  }
  
  public int getNotenpunkte(){
    return this.notenpunkte;
  }
  
  public void setErbrachteLeistung (String eL){
    this.erbrachteLeistung = eL;
  }
  
  public String getErbrachteLeistung (){
    return this.erbrachteLeistung;
  }
  
  public void setGewichtung(int gewichtung){
    this.gewichtung = gewichtung;
  }
  
  public int getGewichtung(){
    return this.gewichtung;
  }
  
  public void setIsKlausur(boolean klausur){
    this.isKlausur = klausur;
  }
  
  public boolean isKlausur(){
    return isKlausur;
  }
  
  //Methoden
  //diese Methode errechnet aus den NP die Note nach altem Notensystem z.B. 3+
  //das Schlüsselwort static kennzeichnet die Methode als Objektunabhängig
  public static String errechneNote(int notenpunkte){
    //Leeren String erzeugen
    String note = "";
    //die Note ohne Tendenz wird errechnet
    note += 6 - (notenpunkte + 2) / 3;
    //Errechnung der Tendenz
    if (notenpunkte != 0 && notenpunkte % 3 == 0) note += "+";
    if (notenpunkte % 3 == 1) note += "-";
    return note;
  }
}