package MedicalClinicDB;


import .*;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ben
 */
public class MedicalDBUser 
{
    
   public static void main(String[] args) throws SQLException
   {
        MedicalClinicDB medicalClinicDB = new MedicalClinicDB();
        MedicalDBUI medicalDBUI = new MedicalDBUI(medicalClinicDB);
//        medicalClinicDB.connectToDatabase();
   }
}
