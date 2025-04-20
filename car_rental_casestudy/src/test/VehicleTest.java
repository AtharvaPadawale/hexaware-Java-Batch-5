package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;

import dao.ICarLeaseRepositoryImpl;
import entity.Vehicle;
import exception.CarNotFoundException;

public class VehicleTest {

    @Test
    public void testCarCreation() throws ClassNotFoundException, SQLException, IOException, CarNotFoundException {
        ICarLeaseRepositoryImpl repo = new ICarLeaseRepositoryImpl();
        
        // Create a car object
        Vehicle car = new Vehicle(0, "Toyota", "Corolla", 2022, 50.0, "available", 5, 1.8);
        
        // Add car to the repository
        repo.addCar(car);
        
        // Fetch the car back 
        Vehicle fetchedCar = repo.findCarById(car.getVehicleID());
        
        
        assertNotNull(fetchedCar);
        assertEquals("Toyota", fetchedCar.getMake());
        assertEquals("Corolla", fetchedCar.getModel());
        assertEquals(2022, fetchedCar.getYear());
    }
}
