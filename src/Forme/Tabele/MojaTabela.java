/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forme.Tabele;

import Class.DAO.BrokerDAO;
import Forme.FormForme;
import Forme.Konstante.Boje;
import Forme.Polja.Listeneri.ActionListenerTable;
import Forme.Polja.Prikazi.PoljaEnabDisab;
import Forme.Polja.Prikazi.PoljaIzTabeleDefinicija;
import Forme.Ispis.Formatiraj;
import Sistem.OsnovneDefinicije.Alignment;
import Sistem.OsnovneDefinicije.RezolucijaEkrana;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.TableRowSorter;

public class MojaTabela extends JPanel implements KeyListener, ActionListener {

    //public int sirinaTbl;
    FormForme koZove;
    JTable table;
    JLabel l1;
    MyTableModel model;
    List myDataList = null;
    String[] zaglTabele = null;
    ResultSetMetaData metaData = null;
    int[] kljucevi = null;
    int[] poljaDisabled = null;
    List data;
    int widthTbl;
    int heightTbl;
    public JScrollPane scrPane;
    PoljaIzTabeleDefinicija poljaIzTabele;

    JTextField[] texts;
    JComboBox[] combos;
    String[] radioComboCheck;

    //Sort
    private TableRowSorter<MyTableModel> sorter;
    JTextField filterText;
    public String[] TxtCmb = null;
    PoljaEnabDisab poljaEnabDisab;
    BrokerDAO brokerDAO;
    String kojiUpis;

    //Koji je tip varijable, koliko znakova ima i koliko decimala ima u polju 
    String[] tipVar;
    int[] decVar;
    int[] duzinaVar;

    public java.awt.Frame KoZove;

    public MojaTabela(List l, String z, ResultSetMetaData md, int[] k, BrokerDAO b, int[] pD, FormForme kZ) throws SQLException {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        myDataList = l;
        zaglTabele = z.split(",");
        kljucevi = k;
        metaData = md;
        brokerDAO = b;
        poljaDisabled = pD;
        koZove = kZ;

        l1 = new JLabel("Filter:", SwingConstants.TRAILING);
        filterText = new JTextField();
    }

    public void NapuniTabelu() throws SQLException {
        int i;
        //table.setShowGrid(true);
        int sirinaTbl = 0;

        tipVar = new String[zaglTabele.length];
        decVar = new int[zaglTabele.length];
        duzinaVar = new int[zaglTabele.length];
        for (i = 0; i < zaglTabele.length; i++) {
            tipVar[i] = metaData.getColumnTypeName(i + 1);
            /*if (tipVar[i] == "DOUBLE"){decVar[i] = -5;}
             else{decVar[i] = metaData.getScale(i+1);} */
            decVar[i] = metaData.getScale(i + 1);
            duzinaVar[i] = metaData.getPrecision(i + 1);
        }

        // !!!!!!!!! PUNJENJE TABELE  !!!!!!!!!!!!!
        //List el=null;
        i = 1;
        //data = new Object[myDataList.size()][zaglTabele.length];
        data = new ArrayList();
        Formatiraj dpt = new Formatiraj();
        String[] alignment = new String[zaglTabele.length];
        for (Object category : myDataList) {
            List element = (List) category;
            for (int j = 0; j < element.size(); j++) {
                dpt.setTipVar(tipVar[j]);
                dpt.setDuzinaVar(duzinaVar[j]);
                dpt.setDecVar(decVar[j]);
                dpt.setVrednostVar(element.get(j));
                dpt.setKojaVrsta("Tabela");
                //data[i-1][j] = dpt.formatirajVar();
                boolean kljuc = false;
                for (int k = 0; k <= kljucevi.length - 1; k++) {
                    if (k == j) {
                        kljuc = true;
                    }
                }
                dpt.setKljuc(kljuc);

                category = dpt.formatirajVar();
                element.set(j, category);
                alignment[j] = dpt.getAlignment();
            }
            data.add(element);
            i++;
        }

        //Parametri Tabele
        model = new MyTableModel(tipVar, duzinaVar, decVar);
        model.data = data;
        model.columnNames = zaglTabele;

        table = new JTable(model);
        model.table = table;
        table.setPreferredScrollableViewportSize(new Dimension(getWidth(), getHeight()));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);

        for (int j = 0; j < zaglTabele.length; j++) {
            Alignment alig = new Alignment();            
            try{
                switch (alignment[j]) {
                    case "Right":
                        table.getColumnModel().getColumn(j).setCellRenderer(alig.getTblRight());
                        break;
                    case "Left":
                        table.getColumnModel().getColumn(j).setCellRenderer(alig.getTblLeft());
                        break;
                    case "Center":
                        table.getColumnModel().getColumn(j).setCellRenderer(alig.getTblCenter());
                        break;
                }
                //Ako je prazna tabela (prvi unos po deafoltu sve desno)
            }catch(Exception e){table.getColumnModel().getColumn(j).setCellRenderer(alig.getTblRight());}            
            int sirinaCell = (duzinaVar[j] + decVar[j]) * 9;
            table.getColumnModel().getColumn(j).setPreferredWidth(sirinaCell);
            sirinaTbl += sirinaCell;
        }


        //Sort ze ubacuje u napunjenu tabelu
        //sorter = new TableRowSorter<MyTableModel>(model);          
        //table.setRowSorter(sorter);    
        //!!!!!!!!!!! POLJA ZA UNOS FILTERA !!!!!!!!!!        
        Dimension b = filterText.getPreferredSize();
        filterText.setPreferredSize(new Dimension(200, b.height));
        l1.setLabelFor(filterText);
        table.setOpaque(false);
        Boje boje = new Boje();
        Color c = boje.getColorTableBackGround();
        table.setBackground(c);

        //Resize table
        if (sirinaTbl < this.getWidthTbl()) {
            setWidthTbl(sirinaTbl + 25);
            table.setPreferredScrollableViewportSize(new Dimension(this.getWidthTbl(), this.getHeightTbl()));
        }

        RezolucijaEkrana re = new RezolucijaEkrana();
        Dimension fullScr = re.FullScreen();
        scrPane = new JScrollPane(table);
        scrPane.setSize(getWidthTbl(), getHeightTbl());
        scrPane.setLocation((fullScr.width - getWidthTbl()) / 2, l1.getHeight());
        scrPane.getViewport().setBackground(Color.WHITE);

        //Dodavanje Action-ih Listener na tabelu 
        table.addKeyListener(this);
    }

    public void newFilter() {
        RowFilter<MyTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(filterText.getText(), 0);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        //sorter.setRowFilter(rf);
    }

    public void SetMyDataList(List myDataList) {
        this.myDataList = myDataList;
    }
    /*public void SetDodajUMyDataList(List dodajUMyDataList){
     this.myDataList.add(dodajUMyDataList);
     }*/

    public void setWidthTbl(int widthTbl) {
        this.widthTbl = widthTbl;
    }

    public int getWidthTbl() {
        return widthTbl;
    }

    public void setHeightTbl(int heightTbl) {
        this.heightTbl = heightTbl;
    }

    public int getHeightTbl() {
        return heightTbl;
    }

    public int getMaxLength(int i) {
        return (decVar[i] + duzinaVar[i]);
    }

    public PoljaIzTabeleDefinicija getPoljaIzTabele() {
        return poljaIzTabele;
    }

    public void setPoljaIzTabele(PoljaIzTabeleDefinicija poljaIzTabele) {
        this.poljaIzTabele = poljaIzTabele;
    }

    public JTable getTable() {
        return table;
    }

    public MyTableModel getModel() {
        return model;
    }

    public JTextField getFilterText() {
        return filterText;
    }

    public List getData() {
        return data;
    }

    //---------------- Listeneri -------------------------------------
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        ActionListenerTable actionListenerTable = new ActionListenerTable(poljaIzTabele, this, koZove);
        try {
            actionListenerTable.keyPressed(e, brokerDAO, kljucevi, KoZove, poljaDisabled);
        } catch (ParseException ex) {
            Logger.getLogger(MojaTabela.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MojaTabela.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
    //----------------Dovde Listeneri ---------------------------------------

}
