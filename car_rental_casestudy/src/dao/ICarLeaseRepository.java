package dao;

import entity.Vehicle;
import entity.Customer;
import entity.Lease;
import entity.Payment;
import java.util.Date;
import java.util.List;
import exception.CarNotFoundException;
import exception.CustomerNotFoundException;
import exception.LeaseNotFoundException;

public interface ICarLeaseRepository {

    // Car Management
    void addCar(Vehicle car);
    void removeCar(int carID);
    List<Vehicle> listAvailableCars();
    List<Vehicle> listRentedCars();
    Vehicle findCarById(int carID) throws CarNotFoundException;

    // Customer Management
    void addCustomer(Customer customer);
    void removeCustomer(int customerID);
    List<Customer> listCustomers();
    Customer findCustomerById(int customerID) throws CustomerNotFoundException;

    // Lease Management
    Lease createLease(int customerID, int carID, Date startDate, Date endDate);
    void returnCar(int leaseID) throws LeaseNotFoundException;
    List<Lease> listActiveLeases();
    List<Lease> listLeaseHistory();

    // Payment Handling
    void recordPayment(Lease lease, double amount);
}
