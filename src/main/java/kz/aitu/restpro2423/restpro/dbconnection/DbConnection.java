package kz.aitu.restpro2423.restpro.dbconnection;

import kz.aitu.restpro2423.restpro.enteties.Patient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbConnection {
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
    private static final String password = "12345678";

    public Connection connect() throws SQLException
    {
        Connection con = DriverManager.getConnection(url, user, password);
        System.out.println("Connection established successfully.");
        return con;
    }

    public int closeConnection(Connection con) throws SQLException
    {
        if (con != null)
        {
            con.close();
            System.out.println("Connection closed.");
            return 0;
        }
        System.out.println("Connection is already closed.");
        return 1;
    }
//--------------
public ArrayList<Patient> getPatients(Connection con) throws SQLException {
    String query = "select * from patient";
    ArrayList<Patient> patients = new ArrayList<>();
    PreparedStatement st = con.prepareStatement(query);

    ResultSet rs = st.executeQuery();

    while (rs.next())
    {
        Patient patient = new Patient();
        patient.setId(rs.getInt("id"));
        patient.setName(rs.getString("name"));
        patient.setLastname(rs.getString("lastname"));
        patient.setAge(rs.getInt("age"));
        patient.setGender(rs.getString("gender"));
        patient.setEmail(rs.getString("email"));
        patient.setDiagnoses(rs.getString("diagnoses"));
        patient.setBloodType(rs.getString("bloodtype"));
        patients.add(patient);
    }

    st.close();
    return patients;
}




    //-----------
    public Patient getPatientsById(Connection con,int id) throws SQLException {
        String query = "select * from patient where id = ?";
        Patient patient = new Patient();
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();

        rs.next();

            patient.setId(rs.getInt("id"));
            patient.setName(rs.getString("name"));
            patient.setLastname(rs.getString("lastname"));
            patient.setAge(rs.getInt("age"));
            patient.setGender(rs.getString("gender"));
            patient.setEmail(rs.getString("email"));
            patient.setDiagnoses(rs.getString("diagnoses"));
            patient.setBloodType(rs.getString("bloodtype"));



        st.close();
        return patient;
    }


    public Patient getPatientsByEmail(Connection con,String email) throws SQLException {
        String query = "select * from patient where email = ?";
        Patient patient = new Patient();
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, email);
        ResultSet rs = st.executeQuery();

        if(rs.next()){
            Patient patient1=new Patient();
            patient1.setId(rs.getInt("id"));
            patient1.setName(rs.getString("name"));
            patient1.setLastname(rs.getString("lastname"));
            patient1.setAge(rs.getInt("age"));
            patient1.setGender(rs.getString("gender"));
            patient1.setEmail(rs.getString("email"));
            patient1.setDiagnoses(rs.getString("diagnoses"));
            patient1.setBloodType(rs.getString("bloodtype"));

            return patient1;
        }
        st.close();
        return patient;
    }




    public void addPatient(Connection con, Patient patient) throws SQLException {
        String query = "insert into public.patient (id, name, lastname, age, gender, email, diagnoses, bloodtype) values (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, patient.getId());
            pst.setString(2, patient.getName());
            pst.setString(3, patient.getLastname());
            pst.setInt(4, patient.getAge());
            pst.setString(5, patient.getGender());
            pst.setString(6, patient.getEmail());
            pst.setString(7, patient.getDiagnoses());
            pst.setString(8, patient.getBloodType());

            pst.executeUpdate();
            System.out.println("Patient added successfully");
        }
    }

//    public Patient updatePatient(Connection con, Patient patient, int id) throws SQLException {
//        String checkQuery = "select * from public.patient where id = ?";
//        try (PreparedStatement checkPst = con.prepareStatement(checkQuery)) {
//            checkPst.setInt(1, patient.getId());
//            ResultSet rs = checkPst.executeQuery();
//
//            if (rs.next()) {
//
//                String query = "update public.patient set name = ?, lastname = ?, age = ?, gender = ?, diagnoses = ?, bloodtype = ? where id = ?";
//                try (PreparedStatement pst = con.prepareStatement(query)) {
//                    Patient patient1=new Patient();
//                    pst.setString(1, patient.getName());
//                    pst.setString(2, patient.getLastname());
//                    pst.setInt(3, patient.getAge());
//                    pst.setString(4, patient.getGender());
//                    pst.setString(5, patient.getDiagnoses());
//                    pst.setString(6, patient.getBloodType());
//                    pst.setInt(7, patient.getId());  // Using id for patient identification
//
//                    pst.executeUpdate();
//                    System.out.println("Patient updated successfully");
//                }
//            } else {
//                System.out.println("Patient with id " + patient.getId() + " not found.");
//            }
//        }
//        return
//    }

    public Patient updatePatient(Connection con, Patient patient, int id) throws SQLException
    {
        String query = "update public.patient set name = ?, lastname = ?, age = ?, gender = ?, diagnoses = ?, bloodtype = ? where id = ?";
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, patient.getName());
        st.setString(2, patient.getLastname());
        st.setInt(3, patient.getAge());
        st.setString(4, patient.getGender());
        st.setString(5, patient.getDiagnoses());
        st.setString(6, patient.getBloodType());
        st.setInt(7, id);

        int success = st.executeUpdate();
        st.close();
        if(success > 0)
        {
            System.out.println("Patient with id " + id + " updated successfully.");
            return patient;
        }
        return null;
    }

    public Patient deletePatient(Connection con, Patient patient) throws SQLException
    {
        String query = "delete from public.patient where id = ?";
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, patient.getId());

        int success = st.executeUpdate();
        st.close();
        if(success > 0)
        {
            System.out.println("Patient with id " + patient.getId() + " deleted successfully.");
            return patient;
        }
        return null;
    }
}
