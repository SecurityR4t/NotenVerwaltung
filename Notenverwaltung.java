import java.io.File;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

/**
 * 
 * Verwaltet die Noten und stellt Listen f�r die GUI zusammen
 * 
 * @version 1.0 vom 15.08.2011
 * @author Tenbusch
 */

public class Notenverwaltung {

  private List<Fach> faecher = new Vector<Fach>();
  private File datei;

  // Einlesen der Daten - dem Konstruktor wird die Datei �bergeben
  public Notenverwaltung(File datei) {
    this.datei = datei;
    // Datei existiert noch nicht
    if (datei.exists() && datei.length() != 0) {
      //Dateiinhalt laden
      laden();
    }else{ //Datei exisitert nicht
      try {
        // Anlegen der Datei
        this.datei.createNewFile();
      } catch (Exception e) {
        System.out.println("Dokument konnte nicht erzeugt werden.");
      }
    }
  }
  
  //Methoden
  
  //gibt den Fachnamen nach der Nummer in der Liste zur�ck
  public String getFachname(int index){
    return faecher.get(index).getFachname();
  }
  
  //gibt die Wunschnote nach der Nummer in der Liste zur�ck
  public int getWunschnote(int index){
    return faecher.get(index).getNotenziel();
  }
  
  //gibt den aktuellen Notenstand nach der Nummer in der Liste zur�ck
  public String getNotenstand(int index){
    return faecher.get(index).getZeugnisnote();
  }
  
  //gibt den Fachnamen nach der Nummer in der Liste zur�ck
  public int getAnzahlFaecher() {
    return faecher.size();
  }
  
  //gibt die Namen der F�cher als Liste zur�ck
  public String[] getFaecherListe(){
    String[] faechernamen = new String[faecher.size()];
    for(int i = 0; i < faecher.size(); i++){
      faechernamen[i] = faecher.get(i).getFachname();
    }
    return faechernamen;
  }
  
  public List<Note> getNoten(String fachname){
    for(int i = 0; i < this.faecher.size(); i++){
      if (faecher.get(i).getFachname().equals(fachname))
        return faecher.get(i).getNoten();
    }
    return null;
  }
  
  //neue Note hinzuf�gen
  public void neuesFach(Fach fach) {
    faecher.add(fach);
    speichern();
  }
  
  //neues Fach hinzuf�gen
  public void neueNote(Note note, String fachname) {
    for (Fach fach : faecher) {
      if (fach.getFachname().equals(fachname)) {
        fach.neueNote(note);
        speichern();
        return;
      }
    }
  }
  
  //l�d die gespeicherten Noten- und Fachdaten aus der XML-Datei
  private void laden() {
    try{
      //Auslesen vorbereiten
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      //Datei ausw�hlen
      Document doc = dBuilder.parse(this.datei);
      doc.getDocumentElement().normalize();
      
      //Auslesen
      NodeList nList = doc.getChildNodes();
      nList = nList.item(0).getChildNodes();//Root-Knoten zu F�chern
      
      //F�r alle F�cher
      for(int i = 0; i < nList.getLength(); i++){
        Node n = nList.item(i);
        Fach augelesenesFach = new Fach(n.getNodeName(), 
                         Integer.parseInt(n.getAttributes().item(0).getNodeValue()));
        
        //F�r alle Noten
        for(int j = 0; j < nList.item(i).getChildNodes().getLength(); j++){
          NodeList note = nList.item(i).getChildNodes();//F�cher zu Noten
          //F�r alle Attribute der Note
          NodeList tmp = note.item(j).getChildNodes();//Noten zu Attributen
          
          //Notenattribute auslesen
          int np = Integer.parseInt(tmp.item(0).getTextContent());
          String el = tmp.item(1).getTextContent();
          int ge = Integer.parseInt(tmp.item(2).getTextContent());
          boolean kl = Boolean.parseBoolean(tmp.item(3).getTextContent());
          
          Note ausgeleseneNote;
          //Konstruktor ausw�hlen
          if(kl) ausgeleseneNote = new Note(np, el, kl);
          else ausgeleseneNote = new Note(np, el, ge); 
          
          augelesenesFach.neueNote(ausgeleseneNote);
        }
        faecher.add(augelesenesFach);
      }   
    }catch(Exception e){
      System.out.println("Lesen der Datei fehlgeschlagen!");
    }
  }
  
  // speichert alle Nutzereingaben in eine XML-Datei
  private void speichern() {
    try {
      //XML-Dokument vorbereiten
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      Document doc = docBuilder.newDocument();
      
      //XML-Dokument mit Daten f�llen
      Element schueler = doc.createElement(datei.getName());
      doc.appendChild(schueler);
      
      //f�r alle F�cher
      for (Fach fach : faecher) {
        // Fachelemente
        Element f = doc.createElement(fach.getFachname());
        f.setAttribute("notenwunsch", fach.getNotenziel() + "");
        schueler.appendChild(f);
        
        //f�r alle Noten
        for (int i = 0; i < fach.getAnzahlNoten(); i++) {
          Element note = doc.createElement("Note");
          f.appendChild(note);
          Element np = doc.createElement("Notenpunkt");
          np.appendChild(doc.createTextNode(fach.getNote(i).getNotenpunkte() + ""));
          note.appendChild(np);
          Element el = doc.createElement("Leistung");
          el.appendChild(doc.createTextNode((fach.getNote(i).getErbrachteLeistung())));
          note.appendChild(el);
          Element ge = doc.createElement("Gewichtung");
          ge.appendChild(doc.createTextNode((fach.getNote(i).getGewichtung() + "")));
          note.appendChild(ge);
          Element kl = doc.createElement("Klausur");
          kl.appendChild(doc.createTextNode((fach.getNote(i).isKlausur() + "")));
          note.appendChild(kl);
        }
      }

      // XML-File schreiben vorbereiten
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(doc);
      //Ziel setzen
      StreamResult result = new StreamResult(this.datei);
      //Datei schreiben
      transformer.transform(source, result);
      
    } catch (Exception e) {
      System.out.println("Datei konnte nicht gespeichert werden!");
    }
  }
}
