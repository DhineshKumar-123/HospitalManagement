package org.hospital.dao;

public interface ServiceProvider {
	void getappointmentbyId(int id);
	void getappointmentbypatientId(int pid);
	void getappointmentbydoctorId(int docid);
	boolean scheduleappointment(int appointmentId,int patientId,int doctorId,String appointmentDate,String description);
	boolean updateappointment(int appointmentId,String appointmentDate);
	boolean cancelappointment(int appointmentId);

}
