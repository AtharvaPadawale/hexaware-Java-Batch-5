//package test;
//
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//import dao.ICarLeaseRepositoryImpl;
//import entity.Customer;
//import entity.Vehicle;
//import exception.CarNotFoundException;
//import exception.CustomerNotFoundException;
//import exception.LeaseNotFoundException;
//
//public class NotFoundExceptionTest {
//
//    @Test(expected = CarNotFoundException.class)
//    public void testCarNotFoundException() throws Exception {
//        // Setup
//        ICarLeaseRepositoryImpl repo = new ICarLeaseRepositoryImpl();
//        
//     
//        repo.findCarById(999); 
//    }
//
//    @Test(expected = CustomerNotFoundException.class)
//    public void testCustomerNotFoundException() throws Exception {
//        // Setup
//        ICarLeaseRepositoryImpl repo = new ICarLeaseRepositoryImpl();
//        
//       
//        repo.findCustomerById(999);  
//    }
//
//    @Test(expected = LeaseNotFoundException.class)
//    public void testLeaseNotFoundException() throws Exception {
//        // Setup
//        ICarLeaseRepositoryImpl repo = new ICarLeaseRepositoryImpl();
//        
//        
//        repo.findLeaseById(999);  
//    }
//}



package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import dao.ICarLeaseRepositoryImpl;
import entity.Customer;
import entity.Vehicle;
import exception.CarNotFoundException;
import exception.CustomerNotFoundException;
import exception.LeaseNotFoundException;
import java.sql.*;

import java.io.IOException;

public class NotFoundExceptionTest {

    @Test
    public void testCarNotFoundException() throws ClassNotFoundException, IOException, SQLException{
        // Setup
        ICarLeaseRepositoryImpl repo = new ICarLeaseRepositoryImpl();
        
        // Assert + Act
        assertThrows(CarNotFoundException.class, () -> {
            repo.findCarById(999);
        });
    }
}
