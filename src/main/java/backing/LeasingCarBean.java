package backing;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

import dto.Cardto;
import dto.Customercardto;
import dto.Customerdto;


@Named
@ViewScoped
public class LeasingCarBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Cardto newCar;
	private Customerdto newCustomer;
	
	private WebTarget leasingCarTarget = ClientBuilder.newClient().target("http://localhost:8080/leasingcarbackend/leasingcar");
	
	public LeasingCarBean() {}
	
	@PostConstruct
	public void init() {
	    newCar = new Cardto();
	    newCustomer = new Customerdto();
	}
	
	public List<Cardto> getCarList() {
		return leasingCarTarget.path("/carList").request(MediaType.APPLICATION_JSON).get(new GenericType<List<Cardto>>() {});
	}

	public List<Customerdto> getCustomerList() {
		return leasingCarTarget.path("/customerList").request(MediaType.APPLICATION_JSON).get(new GenericType<List<Customerdto>>() {});
	}

	public List<Cardto> getAvailableCarsList() {
		return leasingCarTarget.path("/availableOrNotCarList/{isleased}").resolveTemplate("isleased", false).request(MediaType.APPLICATION_JSON).get(new GenericType<List<Cardto>>() {});	
	}
	
	public List<Cardto> getCustomersCarsList(int id) {
		return leasingCarTarget.path("/customerCars/{id}").resolveTemplate("id", id).request(MediaType.APPLICATION_JSON).get(new GenericType<List<Cardto>>() {});	
	}
	
	public Customerdto getCustomerByUsername(String username) {
		
		return leasingCarTarget.path("/customerByUsername/{username}").resolveTemplate("username", username).request(MediaType.APPLICATION_JSON).get(new GenericType<Customerdto>() {});	
	}
	
	public void updateCustomer(String username) {
		Customerdto customer = getCustomerByUsername(username);
		leasingCarTarget.path("/updateCustomer").request(MediaType.APPLICATION_JSON).post(Entity.json(customer));
	}
	
	//Delete
	
	public void deleteCustomer(int id) {
		leasingCarTarget.path("/deleteCustomer/{id}").resolveTemplate("id", id).request(MediaType.APPLICATION_JSON).delete();
	}
	
	public void deleteCar(String licensenumber) {
		leasingCarTarget.path("/deleteCar/{licensenumber}").resolveTemplate("licensenumber", licensenumber).request(MediaType.APPLICATION_JSON).delete();
	}
	
	//Lease
	
	public void leaseCar(int customerid, String licensenumber) {
		Customercardto ccdto = new Customercardto(customerid, licensenumber);
		leasingCarTarget.path("/newLease").request(MediaType.APPLICATION_JSON).post(Entity.json(ccdto));
	}
	
	public void stopLease(String licensenumber) {
		leasingCarTarget.path("/deleteLease/{licensenumber}").resolveTemplate("licensenumber", licensenumber).request(MediaType.APPLICATION_JSON).delete();
	}
	
	//New car
	
	public void postNewCar() {
		leasingCarTarget.path("/newCar").request(MediaType.APPLICATION_JSON).post(Entity.json(newCar));
		newCar = new Cardto();
	}
	
	public void createNewCar() {
		newCar = new Cardto();
	}

	public Cardto getNewCar() {
		return newCar;
	}

	
	//New customer
	
	public void postNewCustomer() {
		leasingCarTarget.path("/newCustomer").request(MediaType.APPLICATION_JSON).post(Entity.json(newCustomer));
		newCustomer = new Customerdto();
	}
	
	public void createNewCustomer() {
		newCustomer = new Customerdto();
	}

	public Customerdto getNewCustomer() {
		return newCustomer;
	}
	
}
