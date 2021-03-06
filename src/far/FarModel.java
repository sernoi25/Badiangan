package far;

import db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class FarModel 
{
    public static void deleteFar(String id)
    {
        Connection conn = null;
        try {
            conn = DB.getConnection();
            String sql = "Delete from far where far_id = '"+id+"'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FarModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        finally
        {
            try {
                conn.close();
            } catch (SQLException ex)
            {
                JOptionPane.showMessageDialog(null, "Cannot close connection to DB!");
                Logger.getLogger(FarModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public static ResultSet getAllFar()
    {
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = DB.getConnection();
            String sql = "SELECT far.far_id as 'ID', "
                    + "CONCAT_WS(' ', beneficiary.fname, beneficiary.mname, beneficiary.lname) as 'Beneficiary', "
                    + "far.fk_dis_id_far as 'Disaster ID', "
                    + "far.during as 'During', "
                    + "far.date as 'Date', "
                    + "far.type as 'Type', "
                    + "far.qty as 'Quantity', "
                    + "far.cost as 'Cost', "
                    + "far.provider as 'Provider' "
                    + "from far, beneficiary where "
                    + "far.fk_bene_id_far = beneficiary.bene_id ";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(FarModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
        return rs;
    }
    
    public static void saveFar(int beneID, Object disID, String during,
            String date, String type, int qty, double cost, String provider)
    {
        
        Connection conn = null;
        try {
            conn = DB.getConnection();
            String sql = "Insert into far values (0,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, beneID);
            if(disID == null)
            {
                stmt.setObject(2, disID);
            }
            else
            {
                stmt.setInt(2,(int) disID);
            }
            stmt.setString(3, during);
            stmt.setString(4, date);
            stmt.setString(5, type);
            stmt.setInt(6, qty);
            stmt.setDouble(7, cost);
            stmt.setString(8, provider);
            stmt.execute();
        }catch (SQLException ex) {
            Logger.getLogger(FarModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Please check inputs" + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            try {
                conn.close();
            } catch (SQLException ex) 
            {
                JOptionPane.showMessageDialog(null, "Cannot close connection to DB!");
                Logger.getLogger(FarModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void updateFar(int id, int bene_id, String ls, 
            String cl, int heads, int age, String exp, String remarks)
    {
        Connection conn = null;
        try {
            conn = DB.getConnection();
            String sql = "Update far set fk_bene_id_far = ? , far_raised = ? , "
                    + "classification = ? , heads = ? , age = ?, exp = ?, "
                    + "remarks = ? where ls_id = '"+id+"'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bene_id);
            stmt.setString(2, ls);
            stmt.setString(3, cl);
            stmt.setInt(4, heads);
            stmt.setInt(5, age);
            stmt.setString(6, exp);
            stmt.setString(7, remarks);
            stmt.execute();
        }
        catch (SQLException ex) {
            Logger.getLogger(FarModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        finally
        {
            try {
                conn.close();
            } catch (SQLException ex) 
            {
                JOptionPane.showMessageDialog(null, "Cannot close connection of DB!");
                Logger.getLogger(FarModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
