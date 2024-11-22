package org.hospital.Main;


import java.util.Scanner;
import static java.lang.System.*;
import org.hospital.dao.DBinteractions;

public class HospitalManagementSystem 
{
	public static void main(String[] args)
	{
		DBinteractions dao = new DBinteractions();
		Scanner scan = new Scanner(System.in);
		int appid = 0;
		int patientid =0;
		int doctorid =0;
		String appointmentDate=null;
		String description = null;
		String confirm = null;
		do 
		{
			out.println(".......... Hospital Management System ...........");
			out.println("1. getappointmentbyId");
			out.println("2. getappointmentbypatientId");
			out.println("3. getappointmentbydoctorId");
			out.println("4. scheduleappointment");
			out.println("5. updateappointment");
			out.println("6. cancelappointment");
			out.println("7. Show all entries");
			out.print("Choose Option(1/2/3/4/5/6/7):\t");
			int opt=scan.nextInt();
			switch (opt) 
			{
			case 1:
				out.print("Enter Appointment id:\t");
				appid=scan.nextInt();
				dao.getappointmentbyId(appid);
				break;
		case 2:
				out.print("Enter Patient Id :\t");
				patientid=scan.nextInt();
				dao.getappointmentbypatientId(patientid);
			break;
		case 3:
			out.print("Enter Doctor Id :\t");
			doctorid=scan.nextInt();
			dao.getappointmentbydoctorId(doctorid);
			break;
		case 4:
		    out.print("Enter appointment id to create:\t");
		    appid = scan.nextInt();
		    out.print("Enter Patient Id :\t");
		    patientid = scan.nextInt();
		    out.print("Enter Doctor Id :\t");
		    doctorid = scan.nextInt();
		    out.print("Enter the date :\t");
		    scan.nextLine(); // Consume the leftover newline
		    appointmentDate = scan.nextLine(); // Read the actual date input
		    out.print("Enter the Description :\t");
		    description = scan.nextLine(); // Now correctly reads the description
		    dao.scheduleappointment(appid, patientid, doctorid, appointmentDate, description);
		    break;

		case 5:
			out.print("Enter appointment id to Update:\t");
			appid = scan.nextInt();
			scan.nextLine(); // Consume the leftover newline
			out.print("Enter the date :\t");
			appointmentDate = scan.nextLine(); // Properly read the date and time input
			dao.updateappointment(appid, appointmentDate);
			break;

		case 6:
			out.print("Enter appointment id to delete:\t");
			appid = scan.nextInt();
			dao.cancelappointment(appid);
			break;
		case 7:
			dao.showall();
			break;
			default:
				err.println("Wrong Option");
}
out.print("\n do you wonna continue(yes/no)");
confirm=scan.next();
}while(confirm.equals("yes"));		
		}
	}
