package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import dao.ICarLeaseRepositoryImpl;
import entity.Lease;
import entity.Customer;
import entity.Vehicle;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LeaseTest {
	
	//Lease Creation Test
    @Test
    public void testLeaseCreation() throws Exception {
        // Setup
        ICarLeaseRepositoryImpl repo = new ICarLeaseRepositoryImpl();
        Customer customer = new Customer(0, "John", "Doe", "johndoe@example.com", "1234567890");
        Vehicle car = new Vehicle(0, "Honda", "Civic", 2022, 120.0, "available", 4, 1.8);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse("2025-05-01");
        Date endDate = sdf.parse("2025-05-10");

        // Action
        Lease lease = repo.createLease(customer.getCustomerID(), car.getVehicleID(), startDate, endDate);

        // Assert
        assertNotNull(lease);
        assertEquals(customer.getCustomerID(), lease.getCustomerID());
        assertEquals(car.getVehicleID(), lease.getVehicleID());
        assertTrue(lease.getStartDate().before(lease.getEndDate()));
    }

    @Test
    public void testLeaseRetrieval() throws Exception {
        // Setup
        ICarLeaseRepositoryImpl repo = new ICarLeaseRepositoryImpl();
        
        // Assuming lease ID 1 exists in the database
        int leaseId = 1; 
        
        // Action
        Lease lease = repo.findLeaseById(leaseId);
        
        // Assert
        assertNotNull(lease);
        assertEquals(leaseId, lease.getLeaseID());
    }
}
