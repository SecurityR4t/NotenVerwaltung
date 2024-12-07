import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.File;
import java.util.*;

/**
 * 
 * Die Benutzeroberfläche des Notenrechners
 * 
 * @version 1.0 vom 18.08.2011
 * @author Tenbusch
 */

public class NotenverwaltungGUI extends JFrame {
  // Anfang Attribute
  //Farbkonstanten
  private final Color FARBEFACH     = Color.BLACK;
  private final Color FARBEFACHDAZU = Color.BLACK;
  private final Color FARBENOTE     = Color.BLACK;
  private final Color FARBENOTEDAZU = Color.BLACK;
  //(oder = new Color(R, G, B); und RGB durch Zahlen bis 255 ersetzen
  private Notenverwaltung nv;
  private File datei;
  private JPanel pnl_Faecher = new JPanel(null);
    private JLabel jLabel1 = new JLabel();
    private java.util.List<JRadioButton> faecher = new Vector<JRadioButton>();
    private ButtonGroup buttongroup = new ButtonGroup();
    private JLabel jLabel10 = new JLabel();
    private java.util.List<JLabel> wunschnoten = new Vector<JLabel>();
  private JPanel pnl_Fach_hinzufuegen = new JPanel(null);
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JTextField tfd_fachname = new JTextField();
    private JLabel jLabel4 = new JLabel();
    private JNumberField nfd_wunschnote = new JNumberField();
  private JButton btn_Fach_hinzufuegen = new JButton();
    private JPanel pnl_Noten = new JPanel(null);
    private JLabel jLabel11 = new JLabel();
    private Vector<JLabel> notenliste = new Vector<JLabel>();
    private JButton btn_File_waehlen = new JButton();
  private JPanel pnl_Note_hinzufuegen = new JPanel(null);
    private JLabel jLabel5 = new JLabel();
    private JLabel jLabel6 = new JLabel();
    private JNumberField nfd_notenpunkte = new JNumberField();
    private JLabel jLabel7 = new JLabel();
    private JTextField tfd_leistung = new JTextField();
    private JLabel jLabel8 = new JLabel();
    private JNumberField nfd_gewichtung = new JNumberField();
    private JCheckBox cbx_klausur = new JCheckBox();
    private JLabel jLabel9 = new JLabel();
    private String[] cbx_FachnameData = {};
    private JButton btn_Note_hinzufuegen = new JButton();
    private JComboBox cbx_Fachname = new JComboBox(cbx_FachnameData);
    private JFileChooser jfco = new JFileChooser();

  // Ende Attribute

  public NotenverwaltungGUI(String title, File datei) {
    // Frame-Initialisierung
    super(title);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 588; 
    int frameHeight = 489;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    Container cp = getContentPane();
    cp.setLayout(null);

    nv = new Notenverwaltung(datei);
    this.datei = datei;
    // Anfang Komponenten

    // Panel hinzufügen
    pnl_Faecher.setBounds(0, 0, 185, 305);
    //TODO Farbe des Panels hinzufügen
    cp.add(pnl_Faecher);
    pnl_Fach_hinzufuegen.setBounds(0, 304, 185, 161);
    //TODO Farbe des Panels hinzufügen
    cp.add(pnl_Fach_hinzufuegen);
    pnl_Noten.setBounds(184, 0, 401, 305);
    //TODO Farbe des Panels hinzufügen
    cp.add(pnl_Noten);
    pnl_Note_hinzufuegen.setBounds(184, 304, 401, 161);
    //TODO Farbe des Panels hinzufügen
    cp.add(pnl_Note_hinzufuegen);
    this.aktualisiereDaten(datei);
    
    // Ende Komponenten
    setResizable(false);
    setVisible(true);
                                                              // !! <Tom> code umgeschoben da er unendlich action listeners erstellt hat
    
    btn_Note_hinzufuegen.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btn_Note_hinzufuegen_ActionPerformed(evt);
      }
    });
    
    btn_File_waehlen.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btn_File_waehlen_ActionPerformed(evt);
      }
    });
    
    btn_Fach_hinzufuegen.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btn_Fach_hinzufuegen_ActionPerformed(evt);
      }
    });
    
  }

  /*
   * aktualisiert alle Fach- und Notendaten nach der XML-Datei
   */
  
   
      
  public void aktualisiereDaten(File datei) {
    //Wenn die Datei nicht die Standarddatei ist
    if (!datei.getName().equals("default.xml")){
      //AML auslesen und alle alten Elemente löschen
      nv = new Notenverwaltung(datei);
      pnl_Faecher.removeAll();
      pnl_Fach_hinzufuegen.removeAll();
      pnl_Noten.removeAll();
      pnl_Note_hinzufuegen.removeAll();
    }
    
    //Elemente der graphischen Benutzeroberfläche einstellen
    //Elemente der Fächerliste
    jLabel1.setBounds(24, 8, 44, 20);
    jLabel1.setForeground(FARBEFACH);
    jLabel1.setText("Fächer");
    jLabel1.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Faecher.add(jLabel1);
    jLabel10.setBounds(90, 8, 95, 20);
    jLabel10.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel10.setText("(Wunsch-) Note");
    jLabel10.setForeground(FARBEFACH);
    jLabel10.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Faecher.add(jLabel10);
    //Checkboxen mit Fächern
    for (int i = 0; i < nv.getAnzahlFaecher(); i++) {
      // Checkbox mit Fachnamen
      faecher.add(new JRadioButton());
      faecher.get(i).setBounds(8, 25 * (i + 1), 113, 20);
      faecher.get(i).setForeground(FARBEFACH);
      faecher.get(i).setText(nv.getFachname(i));
      faecher.get(i).addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          cbx_Fach_ausgewaehlt_ActionPerformed(evt);
        }
      });
      buttongroup.add(faecher.get(i));
      pnl_Faecher.add(faecher.get(i));
      // Label mit Wunschnote
      wunschnoten.add(new JLabel());
      wunschnoten.get(i).setBounds(125, 25 * (i + 1), 50, 20);
      wunschnoten.get(i).setHorizontalAlignment(SwingConstants.RIGHT);
      int notenwunsch = nv.getWunschnote(i);
      
      if(nv.getNotenstand(i).equals("o.B.")){
        wunschnoten.get(i).setText("(" + notenwunsch + ") o.B.");
      }
      else {
        int notenstand  = Integer.parseInt(nv.getNotenstand(i));
        if (notenstand == notenwunsch)
          wunschnoten.get(i).setForeground(Color.BLUE);
        else if (notenwunsch < notenstand)
          wunschnoten.get(i).setForeground(Color.GREEN);
        else                        
          wunschnoten.get(i).setForeground(Color.RED);    
        wunschnoten.get(i).setText("(" + notenwunsch + ") " + notenstand);
      }
      pnl_Faecher.add(wunschnoten.get(i));
    }
    pnl_Faecher.updateUI();                  // !! <Tom> UpdateUI damit die neue Gesamtnote dargestellt wird, ohne dieses = Fehler
    
    //Elemente des Panel Fach hinzufügen
    jLabel2.setBounds(40, 8, 99, 20);
    jLabel2.setForeground(FARBEFACHDAZU);
    jLabel2.setText("Fach hinzufügen");
    jLabel2.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Fach_hinzufuegen.add(jLabel2);
    jLabel3.setBounds(8, 32, 65, 20);
    jLabel3.setForeground(FARBEFACHDAZU);
    jLabel3.setText("Fachname");
    jLabel3.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Fach_hinzufuegen.add(jLabel3);
    tfd_fachname.setBounds(8, 48, 169, 24);
    tfd_fachname.setText("");
    tfd_fachname.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Fach_hinzufuegen.add(tfd_fachname);
    jLabel4.setBounds(8, 80, 77, 20);
    jLabel4.setForeground(FARBEFACHDAZU);
    jLabel4.setText("Wunschnote");
    jLabel4.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Fach_hinzufuegen.add(jLabel4);
    nfd_wunschnote.setBounds(8, 96, 169, 24);
    nfd_wunschnote.setText("");
    nfd_wunschnote.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Fach_hinzufuegen.add(nfd_wunschnote);
    btn_Fach_hinzufuegen.setBounds(8, 128, 169, 25);
    btn_Fach_hinzufuegen.setText("Fach hinzufügen");
    btn_Fach_hinzufuegen.setForeground(FARBEFACHDAZU);
    btn_Fach_hinzufuegen.setMargin(new Insets(2, 2, 2, 2));
    
    btn_Fach_hinzufuegen.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Fach_hinzufuegen.add(btn_Fach_hinzufuegen);
    
    //Elemente des Panel Note hinzufügen
    jLabel5.setBounds(8, 8, 97, 20);
    jLabel5.setForeground(FARBENOTEDAZU);
    jLabel5.setText("Note hinzufügen");
    jLabel5.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Note_hinzufuegen.add(jLabel5);
    jLabel6.setBounds(8, 40, 77, 20);
    jLabel6.setForeground(FARBENOTEDAZU);
    jLabel6.setText("Notenpunkte");
    jLabel6.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Note_hinzufuegen.add(jLabel6);
    nfd_notenpunkte.setBounds(112, 32, 145, 24);
    nfd_notenpunkte.setText("");
    nfd_notenpunkte.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Note_hinzufuegen.add(nfd_notenpunkte);
    jLabel7.setBounds(8, 64, 53, 20);
    jLabel7.setForeground(FARBENOTEDAZU);
    jLabel7.setText("Leistung");
    jLabel7.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Note_hinzufuegen.add(jLabel7);
    tfd_leistung.setBounds(112, 56, 145, 24);
    tfd_leistung.setText("");
    tfd_leistung.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Note_hinzufuegen.add(tfd_leistung);
    jLabel8.setBounds(8, 88, 72, 20);
    jLabel8.setForeground(FARBENOTEDAZU);
    jLabel8.setText("Gewichtung");
    jLabel8.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Note_hinzufuegen.add(jLabel8);
    nfd_gewichtung.setBounds(112, 80, 145, 24);
    nfd_gewichtung.setText("0");
    nfd_gewichtung.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Note_hinzufuegen.add(nfd_gewichtung);
    cbx_klausur.setBounds(112, 104, 97, 17);
    cbx_klausur.setForeground(FARBENOTEDAZU);
    cbx_klausur.setText("Klausur");
    cbx_klausur.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Note_hinzufuegen.add(cbx_klausur);
    jLabel9.setBounds(8, 136, 33, 20);
    jLabel9.setForeground(FARBENOTEDAZU);
    jLabel9.setText("Fach");
    jLabel9.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Note_hinzufuegen.add(jLabel9);
    
    cbx_Fachname.removeAllItems();                             // !! <Tom> Ergänzung: leerung der cbx vor erneuter Ergänzung 
    
    cbx_Fachname.setBounds(112, 128, 145, 24);
    for (int i = 0; i < nv.getAnzahlFaecher(); i++) {
      cbx_Fachname.addItem(nv.getFachname(i));
      
    }
    cbx_Fachname.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Note_hinzufuegen.add(cbx_Fachname);
   
    btn_Note_hinzufuegen.setBounds(264, 32, 129, 121);
    btn_Note_hinzufuegen.setText("Note eintragen");
    btn_Note_hinzufuegen.setForeground(FARBENOTEDAZU);
    btn_Note_hinzufuegen.setMargin(new Insets(2, 2, 2, 2));
    btn_Note_hinzufuegen.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Note_hinzufuegen.add(btn_Note_hinzufuegen);
    
    //Elemente des Panel Note
    jLabel11.setBounds(8, 8, 155, 20);
    jLabel11.setForeground(FARBENOTE);
    jLabel11.setText("  NP Gewichtung Leistung");
    jLabel11.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Noten.add(jLabel11);

    btn_File_waehlen.setBounds(304, 0, 91, 25);
    btn_File_waehlen.setForeground(FARBENOTE);
    btn_File_waehlen.setText("Datei öffnen");
    btn_File_waehlen.setMargin(new Insets(2, 2, 2, 2));
    
    btn_File_waehlen.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Noten.add(btn_File_waehlen);

    
    if (!datei.getName().equals("default.xml")){
      pnl_Faecher.repaint();
      pnl_Fach_hinzufuegen.repaint();
      pnl_Noten.repaint();
      pnl_Note_hinzufuegen.repaint();
    }

  }

  // Anfang Methoden
  public String jfcoOpenFilename() {
    jfco.setDialogTitle("Öffne Datei");
    if (jfco.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
      return jfco.getSelectedFile().getPath();
    } else {
      return null;
    }
  }

  public String getSelectedRadioButton(ButtonGroup bg) {
    for (java.util.Enumeration<AbstractButton> e = bg.getElements(); e
        .hasMoreElements();) {
      AbstractButton b = e.nextElement();
      if (b.isSelected())
        return b.getText();
    }
    return null;
  }

  private void cbx_Fach_ausgewaehlt_ActionPerformed(ActionEvent evt) {
    // Auslösende Checkbox identifizieren
    String fachname = evt.getActionCommand();
    // Noten zum Fach aus der Notenliste holen
    java.util.List<Note> noten = nv.getNoten(fachname);
    pnl_Noten.removeAll();
    pnl_Noten.revalidate();
    pnl_Noten.add(jLabel11);
    pnl_Noten.revalidate();
    pnl_Noten.repaint();

    for (int i = 0, j = 0; j < noten.size(); j++) {
      // Notenpunkte
      notenliste.add(new JLabel());
      notenliste.get(i).setBounds(8, 25 + j * 17, 23, 16);
      notenliste.get(i).setHorizontalAlignment(SwingConstants.RIGHT);
      notenliste.get(i)
          .setFont(new Font("MS Sans Serif", Font.PLAIN, 13));
      notenliste.get(i).setText(noten.get(j).getNotenpunkte() + "");
      pnl_Noten.add(notenliste.get(i));
      notenliste.get(i++).setVisible(true);
      // Gewichtung
      notenliste.add(new JLabel());
      notenliste.get(i).setBounds(40, 25 + j * 17, 50, 16);
      notenliste.get(i).setHorizontalAlignment(SwingConstants.RIGHT);
      notenliste.get(i)
          .setFont(new Font("MS Sans Serif", Font.PLAIN, 13));
      if (noten.get(j).isKlausur())
        notenliste.get(i).setText("Klausur");
      else if (noten.get(j).getGewichtung() == 0)
        notenliste.get(i).setText("----");
      else
        notenliste.get(i).setText(noten.get(j).getGewichtung() + " %");
      pnl_Noten.add(notenliste.get(i));
      notenliste.get(i++).setVisible(true);
      // Leistung
      notenliste.add(new JLabel());
      notenliste.get(i).setBounds(115, 25 + j * 17, 200, 16);
      // notenliste.get(i).setHorizontalAlignment(SwingConstants.LEFT);
      notenliste.get(i)
          .setFont(new Font("MS Sans Serif", Font.PLAIN, 13));
      notenliste.get(i).setText(noten.get(j).getErbrachteLeistung());
      pnl_Noten.add(notenliste.get(i));
      notenliste.get(i++).setVisible(true);
    }
    pnl_Noten.add(btn_File_waehlen);
    pnl_Noten.revalidate();
    pnl_Noten.repaint();
    pnl_Noten.setVisible(true);
  }

  public void btn_Fach_hinzufuegen_ActionPerformed(ActionEvent evt) {
    int i = nv.getAnzahlFaecher();
    // Fach in der Notenverwaltung eintragen
    nv.neuesFach(new Fach(this.tfd_fachname.getText(), this.nfd_wunschnote.getInt()));
    // Neue Fachauswahl erstellen
    faecher.add(new JRadioButton());
    faecher.get(i).setBounds(8, 25 * (i + 1), 113, 20);
    faecher.get(i).setText(nv.getFachname(i));
    buttongroup.add(faecher.get(i));
    pnl_Faecher.add(faecher.get(i));
    // Ansicht aktualisieren
    pnl_Faecher.repaint(pnl_Faecher.getBounds());
    setVisible(true);
    // Neues Fach der ComboBox bei Note hinzufügen bekannt machen
    cbx_Fachname.addItem(this.tfd_fachname.getText());
    // Eingabe löschen
    this.tfd_fachname.setText("");
    this.nfd_wunschnote.clear();
    this.aktualisiereDaten(datei);        // !! <Tom> aktualisiere Datei damit man nicht die Noten des Letzten Faches sieht nach erstellung
  }

  public void btn_Note_hinzufuegen_ActionPerformed(ActionEvent evt) {
    // Werte der Felder auslesen und die Eingabefelder zurücksetzen
    int notenpunkte = 0;
    if (nfd_notenpunkte.isNumeric())                     // !! <Tom> Fehler behoben, abfrage isNumeric hat gefehlt
      notenpunkte = this.nfd_notenpunkte.getInt();
    this.nfd_notenpunkte.clear();
    String leistung = this.tfd_leistung.getText();
    this.tfd_leistung.setText("");
    int gewichtung = 0;
    if (nfd_gewichtung.isNumeric())
      gewichtung = this.nfd_gewichtung.getInt();
    this.nfd_gewichtung.setInt(0);
    boolean klausur = this.cbx_klausur.isSelected();
    this.cbx_klausur.setSelected(false);
    String fachname = (String) this.cbx_Fachname.getSelectedItem();
    this.cbx_Fachname.setSelectedIndex(0);
    // Note in der Notenverwaltung hinzufügen
    // Unterscheidung welcher Konstruktor verwendet wird
    Note neue_Note;
    if (klausur)
      neue_Note = new Note(notenpunkte, leistung, klausur);
    else
      neue_Note = new Note(notenpunkte, leistung, gewichtung);
    nv.neueNote(neue_Note, fachname);
    // Notenliste aktualisieren
    cbx_Fach_ausgewaehlt_ActionPerformed(new ActionEvent(this, 56465,
        fachname));
    this.aktualisiereDaten(datei);
  }

  public void btn_File_waehlen_ActionPerformed(ActionEvent evt) {
    this.aktualisiereDaten(new File(jfcoOpenFilename()));
  }

  // Ende Methoden

} 
