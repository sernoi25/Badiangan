package evacuation;

import disaster.DisasterModel;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import map.MapPanel;
import net.proteanit.sql.DbUtils;
import util.Alter;
import util.MyPrinter;
import util.SearchModel;

public class EvacuationController 
{
    EvacuationPanel evacp;
    MapPanel mpp;
    ArrayList<Integer> availableSites;
    ArrayList<Integer> affectedBene;
    public EvacuationController(ArrayList<Integer> affectedBene, ArrayList<Integer> availableSites, MapPanel mpp)
    {
        this.affectedBene = affectedBene;
        this.availableSites = availableSites;
        this.mpp = mpp;
        System.out.println(affectedBene);
        System.out.println(availableSites);
        
        //checks when evacuation exists in DB using disaster id
        if(EvacuationModel.isDisasterExist(Alter.toInt(this.mpp.idLbl.getText())))
        {
            EvacuationModel.deleteExistingEvac(Alter.toInt(this.mpp.idLbl.getText()));
        }
        
        if(affectedBene.size() <= 0)
        {
            JOptionPane.showMessageDialog(null, "No evacuation needed since\n no beneficiary is affected");
        }
        else
        {
            for(int x = 0 ; x < affectedBene.size() ; x++)
            {
                EvacuationModel.saveEvacuation(EvacuationModel.getNearestSite(affectedBene.get(x), availableSites), 
                        Alter.toInt(this.mpp.idLbl.getText()), affectedBene.get(x));
            }
        }
    }
    
    public EvacuationController(EvacuationPanel evacp)
    {
        this.evacp = evacp;
        
        this.evacp.allListener(new Action(), new PopUp(), new Mouse());
        
        displayAllEvacuations();
    }
    
    void displayAllEvacuations()
    {
        ResultSet rs = EvacuationModel.getAllEvacuations();
        evacp.table.setModel(DbUtils.resultSetToTableModel(rs));
        new SearchModel(evacp, evacp.table, evacp.searchTF, rs);
    }
    
    void displayEvacInfo()
    {
        int total = 0;
        this.evacp.infoTA.setText("");
        int dataRow = evacp.table.getSelectedRow();
        if(dataRow >= 0)
        {
            ArrayList<String> list = EvacuationModel.getEvacuationInfo(
                    Alter.toInt(evacp.table.getValueAt(dataRow,1).toString()));
            
            for(int x = 0 ; x < list.size() ; x = x + 3)
            {
                total = total + 1;
                this.evacp.infoTA.append("Beneficiary, " + list.get(x) + ", ");
                this.evacp.infoTA.append("with " + list.get(x + 1) + " family members ");
                this.evacp.infoTA.append("evacuated to " + list.get(x + 2) + ".\n");
                total = total + Alter.toInt(list.get(x + 1));
            }
            this.evacp.infoTA.append("Total number of evacuees: " + total);
            //TODO check if correct ang hatag ya nga list
           // evacp.viewDialog.setTitle("Evacuation Info");
            evacp.viewDialog.setModal(true);
            evacp.viewDialog.pack();
            evacp.viewDialog.setLocationRelativeTo(null);
            evacp.viewDialog.setVisible(true);
        }
        else
        {
            JOptionPane.showMessageDialog(evacp, "Please select an evacuation to view.");
        }
    }
    
    class Action implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == evacp.printBtn)
            {
                MyPrinter.printNow(evacp.table, "Evacuation");
            }
            if(e.getSource() == evacp.viewMenuItem)
            {
                displayEvacInfo();
            }
        }
    }
    
    class Mouse extends MouseAdapter
    {
        @Override
        public void mouseReleased(MouseEvent e) 
        {      
            int r = evacp.table.rowAtPoint(e.getPoint());
            if (r >= 0 && r < evacp.table.getRowCount()) {
                evacp.table.setRowSelectionInterval(r, r);
            } else {
                evacp.table.clearSelection();
            }

            int rowindex = evacp.table.getSelectedRow();
            if (rowindex < 0)
                return;
            if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
                JPopupMenu popup = new JPopupMenu();
                popup.show(evacp, e.getX(), e.getY());
                evacp.table.setComponentPopupMenu(evacp.popUpMenu);
            }
        }
    }
    
    class PopUp implements PopupMenuListener
    {
        @Override
        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            SwingUtilities.invokeLater(() -> {
                int rowAtPoint = evacp.table.rowAtPoint(SwingUtilities.
                        convertPoint(evacp.popUpMenu, new Point(0, 0), evacp.table));
                if (rowAtPoint > -1) {
                    evacp.table.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                }
            });
        }
        
        @Override
        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            
        }
        
        @Override
        public void popupMenuCanceled(PopupMenuEvent e) {
            
        }
    }
}
