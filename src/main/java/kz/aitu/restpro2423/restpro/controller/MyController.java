package kz.aitu.restpro2423.restpro.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.aitu.restpro2423.restpro.dbconnection.DbConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import kz.aitu.restpro2423.restpro.enteties.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


@RestController
public class MyController {
    @Autowired
    private ObjectMapper oMapper;//=new ObjectMapper();

    @GetMapping("/get_patients")
    public String getPatients() {
        DbConnection db = new DbConnection();
        Connection con = null;
        ArrayList<Patient> patients = new ArrayList<>();

        try {
            con = db.connect();
            patients = db.getPatients(con);
            db.closeConnection(con);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } {
           // finally {
                }
           // } catch (SQLException e) {
             //   System.out.println("Error: " + e.toString());
           // }
        //}

        String jsonText = null;
        try {
            jsonText = oMapper.writeValueAsString(patients);
        } catch (JsonProcessingException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return jsonText;
    }

    @GetMapping("/get_patient_by_id")
    public String getPatient(@RequestParam int id) {
        DbConnection db = new DbConnection();
        Connection con = null;
        Patient patient = new Patient();
        String jsonText = null;

        try {
            con = db.connect();
            patient = db.getPatientsById(con, id);
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }  {
            try {
                db.closeConnection(con);
            } catch (SQLException e) {
                System.out.println("Error: " + e.toString());
            }
        }
        String jsonText2 = null;

        try {
            jsonText = oMapper.writeValueAsString(patient);
        } catch (JsonProcessingException e) {
            System.out.println("Error: " + e.toString());
        }
        return jsonText;
    }

    @GetMapping("/get_patients_by_email")
    public String getPatientsByEmail(@RequestParam String email) {
        DbConnection db = new DbConnection();
        Connection con = null;
        Patient patients = new Patient();

        try {
            con = db.connect();
            patients = db.getPatientsByEmail(con, email);
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }  {
            try {
                db.closeConnection(con);
            } catch (SQLException e) {
                System.out.println("Error: " + e.toString());
            }
        }


        String jsonText = null;
        try {
            jsonText = oMapper.writeValueAsString(patients);
        } catch (JsonProcessingException e) {
            System.out.println("Error: " + e.toString());
        }
        return jsonText;


    }


    @PostMapping("/add_patient")
    public String addPatient(@RequestParam int id, @RequestParam String name,@RequestParam String lastname, @RequestParam int age, @RequestParam String gender,@RequestParam String email,@RequestParam String diagnoses,@RequestParam String bloodtype)
    {
        DbConnection db = new DbConnection();
        Connection con = null;
        Patient patient = new Patient(name, lastname,age,gender,email,diagnoses,id,bloodtype);
        System.out.println(patient);

        try
        {
            con = db.connect();
            db.addPatient(con, patient);
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.toString());
        }
        {
            try { db.closeConnection(con); }
            catch (SQLException e) { System.out.println("Error: " + e.toString()); }
        }

        String jsonData = null;
        try
        {
            jsonData = oMapper.writeValueAsString(patient);
        }
        catch (JsonProcessingException e)
        {
            System.out.println("Error: " + e.toString());
        }
        return jsonData;
    }

    @PostMapping("/update_patient")
    public ResponseEntity<String> updatePatient(@RequestParam int id, @RequestParam String name,@RequestParam String lastname, @RequestParam int age, @RequestParam String gender,@RequestParam String email,@RequestParam String diagnoses,@RequestParam String bloodtype) {
        DbConnection db = new DbConnection();
        Connection con = null;
        Patient patient = new Patient(name, lastname,age,gender,email,diagnoses,id,bloodtype);
        System.out.println(patient);


        try {
            con = db.connect();
            patient=db.updatePatient(con, patient,id);
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
            return ResponseEntity.ok("Database error occurred.");
        }  {
            try {
                db.closeConnection(con);
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.toString());
            }
        }

        return ResponseEntity.ok("Patient updated successfully.");
    }

    @PostMapping("/delete_patient")
    public String deletePatient(@RequestParam int id)
    {
        DbConnection db = new DbConnection();
        Connection con = null;
        Patient patient = null;

        try
        {
            con = db.connect();
            patient= db.getPatientsById(con, id);
            System.out.println("Deleting patient: " + patient);
            db.deletePatient(con,patient);
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.toString());
        }
        finally
        {
            try { db.closeConnection(con); }
            catch (SQLException e) { System.out.println("Error: " + e.toString()); }
        }

        String jsonText = null;
        try
        {
            jsonText = oMapper.writeValueAsString(patient);
        }
        catch (JsonProcessingException e)
        {
            System.out.println("Error: " + e.toString());
        }
        return jsonText;
    }
}
