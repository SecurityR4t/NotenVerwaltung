import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.File;
import java.util.*;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.AbstractButton;

/**
 * 
 * Die Benutzeroberfläche des Notenrechners
 * 
 * @version 1.0 vom 18.08.2011
 * @author Tenbusch
 */

public class NotenverwaltungGUI extends JFrame {  //<ahmet> alle Variablennamen auf konvention angepasst
  // Anfang Attribute
  //Farbkonstanten
  // start attributes
  private final Color FARBEFACH     = Color.BLACK;
  private final Color FARBEFACHDAZU = Color.BLACK;
  private final Color FARBENOTE     = Color.BLACK;
  private final Color FARBENOTEDAZU = Color.BLACK;
  //(oder = new Color(R, G, B); und RGB durch Zahlen bis 255 ersetzen
  private Notenverwaltung nv;
  
  private File datei;
  private JPanel pnl_Faecher = new JPanel(null);
    private JLabel lblFaecher = new JLabel();
    private java.util.List<JRadioButton> faecher = new Vector<JRadioButton>();
    private ButtonGroup buttongroup = new ButtonGroup();
    private JLabel lblWNote = new JLabel();
    private java.util.List<JLabel> wunschnoten = new Vector<JLabel>();
  private JPanel pnl_Fach_hinzufuegen = new JPanel(null);
    private JLabel lblFachHinzufuegen = new JLabel();
    private JLabel lblFachname = new JLabel();
    private JTextField tfd_fachname = new JTextField();
    private JLabel lblWunschnote = new JLabel();
    private JNumberField nfd_wunschnote = new JNumberField();
  private JButton btn_Fach_hinzufuegen = new JButton();
    private JPanel pnl_Noten = new JPanel(null);
    private JLabel lblNpGewichtungLeistung = new JLabel();
    private Vector<JLabel> notenliste = new Vector<JLabel>();
    private JButton btn_Datei_waehlen = new JButton();
    private JButton btnFachLoeschen = new JButton();
  private JPanel pnl_Note_hinzufuegen = new JPanel(null);
    private JLabel lblNoteHinzufuegen = new JLabel();
    private JLabel lblNP = new JLabel();
    private JNumberField nfd_notenpunkte = new JNumberField();
    private JLabel lblLeistung = new JLabel();
    private JTextField tfd_leistung = new JTextField();
    private JLabel lblGewichtung = new JLabel();
    private JNumberField nfd_gewichtung = new JNumberField();
    private JCheckBox cbx_klausur = new JCheckBox();
    private JLabel lblFach = new JLabel();
    private String[] cbx_FachnameData = {};
    private JButton btn_Note_hinzufuegen = new JButton();
    private JComboBox ddMenuFach = new JComboBox(cbx_FachnameData); //<ahmet> dd für dropdown Menu
    private java.util.List<JLabel> notenNummer = new Vector<JLabel>();
  private JButton btn_Note_loeschen = new JButton();
    private JLabel lblNoteLoeschen = new JLabel();
    private JNumberField nfd_indexNummer = new JNumberField();
  private JButton btn_Note_aendern = new JButton();
    private JLabel lblNoteaendern = new JLabel();
  private JButton btn_File_save = new JButton();
  // end attributes
    private JFileChooser jfco = new JFileChooser();

  // Ende Attribute

  public NotenverwaltungGUI(String title, File datei) {
    // Frame-Initialisierung
    super(title);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 600; 
    int frameHeight = 500;
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
    pnl_Faecher.setBounds(0, 0, 193, 305);
    //TODO Farbe des Panels hinzufügen
    cp.add(pnl_Faecher);
    pnl_Fach_hinzufuegen.setBounds(0, 304, 185, 161);
    //TODO Farbe des Panels hinzufügen
    cp.add(pnl_Fach_hinzufuegen);
    pnl_Noten.setBounds(192, 0, 393, 305);
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
    
    btn_Datei_waehlen.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btn_Datei_waehlen_ActionPerformed(evt);
      }
    });
    
    btn_Fach_hinzufuegen.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btn_Fach_hinzufuegen_ActionPerformed(evt);
      }
    });
    
    btn_Note_loeschen.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btn_Note_loeschen_ActionPerformed(evt);
      }
    });
    
    btn_Note_aendern.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btn_Note_aendern_ActionPerformed(evt);
      }
    });
    
    nfd_indexNummer.addMouseListener(new MouseAdapter() { 
      public void mouseEntered(MouseEvent evt) { 
        nfd_indexNummer_MouseEntered(evt);
      }
    });
      
    btn_File_save.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        btn_File_save_ActionPerformed(evt);
      }
    });
    
    btnFachLoeschen.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        btnFachLoeschen_ActionPerformed(evt);
      }
    });
    
    // start components
    // end components
  // start methods
  }

  /*
   * aktualisiert alle Fach- und Notendaten nach der XML-Datei
   */
  
   
      
  public void aktualisiereDaten(File datei) {
    //Wenn die Datei nicht die Standarddatei ist
    wunschnoten.clear();
    faecher.clear();
    buttongroup.clearSelection();
    for (AbstractButton button : java.util.Collections.list(buttongroup.getElements())) {
      buttongroup.remove(button); 
    } // end of for
    pnl_Faecher.removeAll();
    
    if (!datei.getName().equals("default.xml")){
      pnl_Faecher.removeAll();
      pnl_Fach_hinzufuegen.removeAll();
      pnl_Noten.removeAll();
      pnl_Note_hinzufuegen.removeAll();
      nv = new Notenverwaltung(datei);
      System.out.println(datei);
    }
    
    //Elemente der graphischen Benutzeroberfläche einstellen
    //Elemente der Fächerliste
    lblFaecher.setBounds(24, 8, 44, 20);
    lblFaecher.setForeground(FARBEFACH);
    lblFaecher.setText("Fächer");
    lblFaecher.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Faecher.add(lblFaecher);
    lblWNote.setBounds(90, 8, 95, 20);
    lblWNote.setHorizontalAlignment(SwingConstants.RIGHT);
    lblWNote.setText("(Wunsch-) Note");
    lblWNote.setForeground(FARBEFACH);
    lblWNote.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Faecher.add(lblWNote);
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
    lblFachHinzufuegen.setBounds(40, 8, 99, 20);
    lblFachHinzufuegen.setForeground(FARBEFACHDAZU);
    lblFachHinzufuegen.setText("Fach hinzufügen");
    lblFachHinzufuegen.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Fach_hinzufuegen.add(lblFachHinzufuegen);
    lblFachname.setBounds(8, 32, 65, 20);
    lblFachname.setForeground(FARBEFACHDAZU);
    lblFachname.setText("Fachname");
    lblFachname.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Fach_hinzufuegen.add(lblFachname);
    tfd_fachname.setBounds(8, 48, 169, 24);
    tfd_fachname.setText("");
    tfd_fachname.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Fach_hinzufuegen.add(tfd_fachname);
    lblWunschnote.setBounds(8, 80, 77, 20);
    lblWunschnote.setForeground(FARBEFACHDAZU);
    lblWunschnote.setText("Wunschnote");
    lblWunschnote.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Fach_hinzufuegen.add(lblWunschnote);
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
    lblNoteHinzufuegen.setBounds(8, 8, 97, 20);
    lblNoteHinzufuegen.setForeground(FARBENOTEDAZU);
    lblNoteHinzufuegen.setText("Note hinzufügen");
    lblNoteHinzufuegen.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Note_hinzufuegen.add(lblNoteHinzufuegen);
    lblNP.setBounds(8, 40, 77, 20);
    lblNP.setForeground(FARBENOTEDAZU);
    lblNP.setText("Notenpunkte");
    lblNP.setFont(new Font("Dialog", Font.PLAIN, 13));
    lblNP.setHorizontalAlignment(SwingConstants.RIGHT);
    pnl_Note_hinzufuegen.add(lblNP);
    nfd_notenpunkte.setBounds(112, 32, 145, 24);
    nfd_notenpunkte.setText("");
    nfd_notenpunkte.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Note_hinzufuegen.add(nfd_notenpunkte);
    lblLeistung.setBounds(8, 64, 53, 20);
    lblLeistung.setForeground(FARBENOTEDAZU);
    lblLeistung.setText("Leistung");
    lblLeistung.setFont(new Font("Dialog", Font.PLAIN, 13));
    lblLeistung.setHorizontalAlignment(SwingConstants.RIGHT);
    pnl_Note_hinzufuegen.add(lblLeistung);
    tfd_leistung.setBounds(112, 56, 145, 24);
    tfd_leistung.setText("");
    tfd_leistung.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Note_hinzufuegen.add(tfd_leistung);
    lblGewichtung.setBounds(8, 88, 72, 20);
    lblGewichtung.setForeground(FARBENOTEDAZU);
    lblGewichtung.setText("Gewichtung");
    lblGewichtung.setFont(new Font("Dialog", Font.PLAIN, 13));
    lblGewichtung.setHorizontalAlignment(SwingConstants.RIGHT);
    pnl_Note_hinzufuegen.add(lblGewichtung);
    nfd_gewichtung.setBounds(112, 80, 145, 24);
    nfd_gewichtung.setText("0");
    nfd_gewichtung.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Note_hinzufuegen.add(nfd_gewichtung);
    cbx_klausur.setBounds(112, 104, 97, 17);
    cbx_klausur.setForeground(FARBENOTEDAZU);
    cbx_klausur.setText("Klausur");
    cbx_klausur.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Note_hinzufuegen.add(cbx_klausur);
    lblFach.setBounds(8, 136, 33, 20);
    lblFach.setForeground(FARBENOTEDAZU);
    lblFach.setText("Fach");
    lblFach.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Note_hinzufuegen.add(lblFach);
    
    ddMenuFach.removeAllItems();                             // !! <Tom> Ergänzung: leerung der cbx vor erneuter Ergänzung 
    ddMenuFach.setBounds(112, 128, 145, 24);
    for (int i = 0; i < nv.getAnzahlFaecher(); i++) {
      ddMenuFach.addItem(nv.getFachname(i));
    }
    ddMenuFach.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Note_hinzufuegen.add(ddMenuFach);
   
    btn_Note_hinzufuegen.setBounds(264, 32, 129, 121);
    btn_Note_hinzufuegen.setText("Note eintragen");
    btn_Note_hinzufuegen.setForeground(FARBENOTEDAZU);
    btn_Note_hinzufuegen.setMargin(new Insets(2, 2, 2, 2));
    btn_Note_hinzufuegen.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Note_hinzufuegen.add(btn_Note_hinzufuegen);
    
    //Elemente des Panel Note
    lblNpGewichtungLeistung.setBounds(8, 8, 155, 20);
    lblNpGewichtungLeistung.setForeground(FARBENOTE);
    lblNpGewichtungLeistung.setText("  NP Gewichtung Leistung");
    lblNpGewichtungLeistung.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Noten.add(lblNpGewichtungLeistung);

    btn_Datei_waehlen.setBounds(304, 0, 91, 25);
    btn_Datei_waehlen.setForeground(FARBENOTE);
    btn_Datei_waehlen.setText("Datei öffnen");
    btn_Datei_waehlen.setMargin(new Insets(2, 2, 2, 2));
    
    btn_Datei_waehlen.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Noten.add(btn_Datei_waehlen);
    
    nfd_indexNummer.setBounds(280, 200, 91, 25);
    nfd_indexNummer.setText("Index");
    nfd_indexNummer.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Noten.add(nfd_indexNummer);
    
    lblNoteaendern.setBounds(280, 220, 110, 25);
    lblNoteaendern.setText("Note ändern");
    lblNoteaendern.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Noten.add(lblNoteaendern);
    
    btn_Note_aendern.setBounds(280, 240, 91, 25);
    btn_Note_aendern.setForeground(FARBENOTE);
    btn_Note_aendern.setText("Note ändern");
    btn_Note_aendern.setMargin(new Insets(2, 2, 2, 2));
    btn_Note_aendern.setToolTipText("Felder für neue Note mit neuer Note ausfüllen und Index von zu ersetzender Note eingeben");
    
    btn_Note_aendern.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Noten.add(btn_Note_aendern);
    
    lblNoteLoeschen.setBounds(280, 260, 110, 25);
    lblNoteLoeschen.setText("Note Löschen");
    lblNoteLoeschen.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Noten.add(lblNoteLoeschen);
    
    btn_Note_loeschen.setBounds(280, 280, 91, 25);
    btn_Note_loeschen.setForeground(FARBENOTE);
    btn_Note_loeschen.setText("Note Löschen");
    btn_Note_loeschen.setMargin(new Insets(2, 2, 2, 2));
    
    btn_Note_loeschen.setFont(new Font("Dialog", Font.PLAIN, 13));
    pnl_Noten.add(btn_Note_loeschen);
    
    btn_File_save.setBounds(296, 24, 105, 25);
    btn_File_save.setForeground(FARBENOTE);
    btn_File_save.setText("Datei speichern");
    btn_File_save.setMargin(new Insets(2, 2, 2, 2));
    btn_File_save.setText("Name ändern");
    btn_File_save.setToolTipText("Datei Namen ändern und Pfad ändern");
    pnl_Noten.add(btn_File_save);
    
    btnFachLoeschen.setBounds(96, 184, 123, 41);
    btnFachLoeschen.setText("Fach loeschen");
    btnFachLoeschen.setMargin(new Insets(2, 2, 2, 2));
    btnFachLoeschen.setFont(new Font("Dialog", Font.BOLD, 13));
    pnl_Noten.add(btnFachLoeschen);
    
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
  
  public String ifcoSaveFilename() {
    jfco.setDialogTitle("Speichere Datei");
    if (jfco.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
      return jfco.getSelectedFile() + ".xml";
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

  private void btn_Note_loeschen_ActionPerformed(ActionEvent evt) {
    // lässt die Note löschen
    nv.noteLoeschen(nfd_indexNummer.getInt(), getSelectedRadioButton(buttongroup));
    // lässt das ausgewählte Fach neu darstellen
    cbx_Fach_ausgewaehlt_ActionPerformed(new ActionEvent(this, 56465, getSelectedRadioButton(buttongroup)));
    nfd_indexNummer.setText("Index");
    this.aktualisiereDaten(datei);
  }

  public void btn_Note_aendern_ActionPerformed(ActionEvent evt) {
    int notenpunkte = 0; 
    String leistung = "";
    int gewichtung = 0;
    boolean klausur = false;
    String fachname = "";
    
    if (nfd_notenpunkte.isNumeric() && nfd_notenpunkte.getInt() <= 15 && nfd_gewichtung.isNumeric() && nfd_gewichtung.getInt() <= 100){
  
      notenpunkte = this.nfd_notenpunkte.getInt();
      gewichtung = this.nfd_gewichtung.getInt();
      leistung = this.tfd_leistung.getText();
      fachname = getSelectedRadioButton(buttongroup);
      klausur = this.cbx_klausur.isSelected();
      
      Note neue_Note;  //Variable für Note deklarieren
      if (klausur)
        neue_Note = new Note(notenpunkte, leistung, klausur);
      else
        neue_Note = new Note(notenpunkte, leistung, gewichtung);
        
      nv.noteAendern(neue_Note, fachname, nfd_indexNummer.getInt());
      // Notenliste aktualisieren
      cbx_Fach_ausgewaehlt_ActionPerformed(new ActionEvent(this, 56465, fachname));
      this.aktualisiereDaten(datei);
    }
    
    this.nfd_notenpunkte.clear();
    this.nfd_gewichtung.setInt(0);
    this.tfd_leistung.setText("");
    this.ddMenuFach.setSelectedIndex(0);
    this.cbx_klausur.setSelected(false);
  } 

  private void cbx_Fach_ausgewaehlt_ActionPerformed(ActionEvent evt) {
    // Auslösende Checkbox identifizieren
    String fachname = evt.getActionCommand();
    // Noten zum Fach aus der Notenliste holen
    java.util.List<Note> noten = nv.getNoten(fachname);
    pnl_Noten.removeAll();
    pnl_Noten.revalidate();
    pnl_Noten.add(lblNpGewichtungLeistung);
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
      // cbx
      notenNummer.add(new JLabel());
      notenNummer.get(j).setBounds(0, 25 + j * 17, 113, 16);
      notenNummer.get(j).setFont(new Font("MS Sans Serif", Font.PLAIN, 13));
      notenNummer.get(j).setText(j + "");
      pnl_Noten.add(notenNummer.get(j));
      
    }
    pnl_Noten.add(btn_Datei_waehlen);
    pnl_Noten.add(btn_File_save);
    pnl_Noten.add(btn_Note_loeschen);
    pnl_Noten.add(nfd_indexNummer);
    pnl_Noten.add(lblNoteLoeschen);
    pnl_Noten.add(btn_Note_aendern);
    pnl_Noten.add(btnFachLoeschen);
    pnl_Noten.add(lblNoteaendern);
    pnl_Noten.revalidate();
    pnl_Noten.repaint();
    pnl_Noten.setVisible(true);
  }

  public void btn_Fach_hinzufuegen_ActionPerformed(ActionEvent evt) {
    int i = nv.getAnzahlFaecher();
    
    // <ahmet> Ergänzung das Fächer keine Wunschnote über ( <Tom>15 ) haben darf und keine doppelten Namen
    //// 
    String[] vorhandeneFaecher = nv.getFaecherListe();    
    String neuesFach = tfd_fachname.getText().toLowerCase();
    

    if (nfd_wunschnote.getInt() > 15) {
      return;
    }
    
    //Kontrolle ob das fach vorher schon erstellt wurde.
    for (int it = 0; it < vorhandeneFaecher.length; it++) { 
      if(vorhandeneFaecher[it].equalsIgnoreCase(neuesFach)){
        return; //wenn es schon erstellt wurde nicht hinzufügen und funktion beenden.
      }
    } 
    ////
    
    nv.neuesFach(new Fach(this.tfd_fachname.getText(), this.nfd_wunschnote.getInt()));    // Fach in der Notenverwaltung eintragen
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
    ddMenuFach.addItem(this.tfd_fachname.getText());
    // Eingabe löschen
    this.tfd_fachname.setText("");
    this.nfd_wunschnote.clear();
    this.aktualisiereDaten(datei);        // !! <Tom> aktualisiere Datei damit man nicht die Noten des Letzten Faches sieht nach erstellung
  } 

  public void btn_Note_hinzufuegen_ActionPerformed(ActionEvent evt) {  //<ahmet> ganze funktion neu geschrieben damit es leserlicher ist.
    
    int notenpunkte = 0; 
    String leistung = "";
    int gewichtung = 0;
    boolean klausur = false;
    String fachname = "";
    
    // !! <Tom> Fehler behoben, abfrage isNumeric hat gefehlt 
    // <ahmet> note über 15 muss kontrolliert werden. verzweigungen zusammengefasst / verkürzt.
    // !! <Tom> Alle Fehler behoben und unnötiges entfernt, funktioniert alles
    if (nfd_notenpunkte.isNumeric() && nfd_notenpunkte.getInt() <= 15 && nfd_gewichtung.isNumeric() && nfd_gewichtung.getInt() <= 100){
  
      notenpunkte = this.nfd_notenpunkte.getInt();
      gewichtung = this.nfd_gewichtung.getInt();
      leistung = this.tfd_leistung.getText();
      fachname = (String) this.ddMenuFach.getSelectedItem();
      klausur = this.cbx_klausur.isSelected();
      
      
      
      //<ahmet> nur ausführen wenn die Eingaben korrekt sind.
      Note neue_Note;  //Variable für Note deklarieren
      if (klausur)
        neue_Note = new Note(notenpunkte, leistung, klausur);
      else
        neue_Note = new Note(notenpunkte, leistung, gewichtung);
        
       nv.neueNote(neue_Note, fachname);
      // Notenliste aktualisieren
      cbx_Fach_ausgewaehlt_ActionPerformed(new ActionEvent(this, 56465, fachname));
      this.aktualisiereDaten(datei);
    }
    
    //<ahmet> alles am ende löschen (clear)
    this.nfd_notenpunkte.clear();
    this.nfd_gewichtung.setInt(0);
    this.tfd_leistung.setText("");
    this.ddMenuFach.setSelectedIndex(0);
    this.cbx_klausur.setSelected(false);   
  }
  
  // setzt die Datei auf die im Filechooser gewählte Datei
  public void btn_Datei_waehlen_ActionPerformed(ActionEvent evt) {
    this.aktualisiereDaten(new File(jfcoOpenFilename()));
  }
  
  // gibt die aktuelle Datei zum speichern weiter    
  public void btn_File_save_ActionPerformed(ActionEvent evt) {
    nv.umwegZuSpeichern(new File(ifcoSaveFilename()));
  } // end of btn_File_save_ActionPerformed    
  
  //<ahmet>
  public void btnFachLoeschen_ActionPerformed(ActionEvent evt) {
    nv.FachLoeschen(getSelectedRadioButton(buttongroup)); 
    int i = 0;
    for (java.util.Enumeration<AbstractButton> e = buttongroup.getElements(); e.hasMoreElements();) {
      AbstractButton b = e.nextElement();
      
      if (b.isSelected()) {
        pnl_Faecher.remove(b);
        pnl_Faecher.remove(wunschnoten.get(i));
        pnl_Faecher.revalidate();
        pnl_Faecher.repaint();
        this.aktualisiereDaten(datei);
        return;
      }
      i++;
    }
  }  
  // löscht den Hinweis beim mit dem Mauszeiger über dem nfd Schweben
  public void nfd_indexNummer_MouseEntered(MouseEvent evt) {
    nfd_indexNummer.setText("");
  } // end of nfd_indexNummer_MouseEntered

  // Ende Methoden

} 
