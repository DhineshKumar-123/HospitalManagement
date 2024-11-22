package org.hospital.dao;
//import org.hexaware.util.DBConnUtil;
import org.hospital.exception.AppointmentNotFoundException;
//import org.hexaware.util.DBConnUtil;
import org.hospital.exception.*;
import org.hospital.exception.DuplicateValueInsertionException;
//import org.hexaware.util.DBConnUtil;
import org.hospital.util.*;

//import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.IllegalFormatConversionException;

//import org.hexaware.util.DBConnUtil;

public class DBinteractions implements ServiceProvider 
{
	@Override
	public void getappointmentbyId(int id)
	{
		Connection con = DBConnUtil.establishconnection("null");
		try {
			PreparedStatement psm = con.prepareStatement("select * from appointment where appointmentId=?");
			psm.setInt(1, id);
		    ResultSet rs = psm.executeQuery();
		    if(rs.next())
		    {
		    	System.out.printf("%-15d %-15d %-15d %-10s %-15s" ,rs.getInt("appointmentId"),rs.getInt("patientId"), rs.getInt("doctorId"),rs.getString("appointmentDate"),rs.getString("description"));
		    }
		    else {
		    	System.out.println("No Objects on Appointment is there in the id "+id);
		    }
		    con.close();
		}catch(SQLException ex)
		{
			System.out.println("Error while finding");
			ex.printStackTrace();
		}catch(IllegalFormatConversionException ex) {
			ex.printStackTrace();
		}
	}
	@Override
	public void getappointmentbypatientId(int pid)
	{
		Connection con = DBConnUtil.establishconnection("null");
		try {
			PreparedStatement psm = con.prepareStatement("select * from appointment where patientId=?");
			psm.setInt(1, pid);
		    ResultSet rs = psm.executeQuery();
		    if(rs.next())
		    {
		    	System.out.printf("\n%-15d %-15d %-15d %-10s %-15s" ,rs.getInt("appointmentId"),rs.getInt("patientId"), rs.getInt("doctorId"),rs.getString("appointmentDate"),rs.getString("description"));
		    }
		    else 
		    	{
		    	 throw new PatientNumberNotFoundException("No Patient is on the Number "+pid);
//		    		System.out.println("No Appointment Object in the Patientid is there on"+pid);
		    	}
		    con.close();
		}catch(SQLException | PatientNumberNotFoundException ex)
		{
			System.out.println("Error while finding !!! =>> "+ ex.getMessage());
			ex.printStackTrace();
		}catch(IllegalFormatConversionException ex) {
			ex.printStackTrace();
		}
	}
	@Override
	public void getappointmentbydoctorId(int pid)
	{
		Connection con = DBConnUtil.establishconnection("null");
		try {
			PreparedStatement psm = con.prepareStatement("select * from appointment where doctorId=?");
			psm.setInt(1, pid);
		    ResultSet rs = psm.executeQuery();
		    if(rs.next())
		    {
		    	System.out.printf("\n%-15d %-15d %-15d %-10s %-15s" ,rs.getInt("appointmentId"),rs.getInt("patientId"), rs.getInt("doctorId"),rs.getString("appointmentDate"),rs.getString("description"));
		    }
		    else 
		    	{
		    	 throw new AppointmentNotFoundException("No Appointment Object in the Doctorid is there on "+pid);
//		    		System.out.println("No Appointment Object in the Patientid is there on"+pid);
		    	}
		    con.close();
		}catch(SQLException | AppointmentNotFoundException ex)
		{
			System.out.println("Error while finding !!! =>> "+ ex.getMessage());
			ex.printStackTrace();
		}catch(IllegalFormatConversionException ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public boolean scheduleappointment(int appointmentId,int patientId,int doctorId,String appointmentDate,String description)
	{
		try {
			Connection con = DBConnUtil.establishconnection("null");
			PreparedStatement pstmt= con.prepareStatement("insert into appointment values(?,?,?,?,?)");
			pstmt.setInt(1, appointmentId);
			pstmt.setInt(2, patientId);
			pstmt.setInt(3, doctorId);
			pstmt.setString(4, appointmentDate);
			pstmt.setString(5, description);
			int affected = pstmt.executeUpdate();
			if(affected>0)
			{
				System.out.println("...............Inserted successfully................");
				return true;
			}
			con.close();
		}catch (SQLException e) {
			System.out.println("Error while insertion");
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean updateappointment(int appointmentId,String appointmentDate)
	{
		try {
			Connection con = DBConnUtil.establishconnection("null");
			PreparedStatement pstmt= con.prepareStatement("update appointment set appointmentDate=? where appointmentId=?");
			pstmt.setInt(2, appointmentId);
			pstmt.setString(1, appointmentDate);
			int affected = pstmt.executeUpdate();
			if(affected>0)
			{
				System.out.println("...............Updated successfully................");
				return true;
			}
			con.close();
		}catch (SQLException e) {
			System.out.println("Error while Updating the Appointment : "+appointmentId);
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean cancelappointment(int appointmentId)
	{
		try {
			Connection con = DBConnUtil.establishconnection("null");
			PreparedStatement pstmt= con.prepareStatement("delete from appointment where appointmentId=?");
			pstmt.setInt(1, appointmentId);
			int affected = pstmt.executeUpdate();
			if(affected>0)
			{
				System.out.println("...............Deleted successfully................");
				return true;
			}
		}catch (SQLException e) {
			System.out.println("There is an Error and No Id is Found as : "+appointmentId);
			e.printStackTrace();
		}
		return false;
	}
	
	
	public void showall()
	{
		Connection con = DBConnUtil.establishconnection("null");
		try {
			PreparedStatement pstmt = con.prepareStatement("null");
			ResultSet rs = pstmt.executeQuery("select * from appointment");
//			pstmt.setString(1, status);
//			pstmt.execute();
			System.out.println(">>>>>> Here are the List of Appointments <<<<<<");
			
			System.out.println("------------------------------------------------------------------------------------------------------------------");
			while(rs.next())
			{
				System.out.printf("\n%-15d %-15d %-15d %-10s %-15s" ,rs.getInt("appointmentId"),rs.getInt("patientId"), rs.getInt("doctorId"),rs.getString("appointmentDate"),rs.getString("description"));
			}
			con.close();
		}catch(SQLException ex)
		{
			System.out.println("Error while Showing");
			ex.printStackTrace();
		}catch(IllegalFormatConversionException ex) {
			ex.printStackTrace();
		}
	}
	
	}
	
//	 public static void main(String[] args) {
//		 DBinteractions dddd = new DBinteractions();
////		 dddd.getappointmentbyId(5);
//		 dddd.getappointmentbypatientId(1);
////		 dddd.getappointmentbypatientId(3);
////		 dddd.getappointmentbypatientId(5);
////		 dddd.getappointmentbydoctorId(4);
////		 dddd.scheduleappointment(4, 4, 2, "2024-10-20","Severe Diahorea and Vomitting");//Want to update with the method for the date and time
////		 dddd.scheduleappointment(6, 1, 1, "2024-05-20", "Cold and Fever");
////		 dddd.updateappointment(4, "2020-05-14 09:45:10");
////		 dddd.cancelappointment(5);
//		
//	}


