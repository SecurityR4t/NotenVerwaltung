/**
  *
  * Startet den Notenrechner
  *
  * @version 1.0 vom 15.07.2011
  * @author Tenbusch
  */

public class NotenverwaltungStart {

  public static void main(String[] args) {
    new NotenverwaltungGUI("Notenverwaltung", new java.io.File("default.xml"));

  }
}
