package backing;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.client.ClientBuilder;

import dto.Cardto;
import dto.Customerdto;


@Named
@ViewScoped
public class LeasingCarBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private WebTarget leasingCarTarget = ClientBuilder.newClient().target("http://localhost:8080/leasingcarbackend/leasingcar");
	
	public LeasingCarBean() {}

	public List<Cardto> getCarList() {
		return leasingCarTarget.path("/carList").request(MediaType.APPLICATION_JSON).get(new GenericType<List<Cardto>>() {});
	}

	public List<Customerdto> getCustomerList() {
		return leasingCarTarget.path("/customerList").request(MediaType.APPLICATION_JSON).get(new GenericType<List<Customerdto>>() {});
	}

	public List<Cardto> getAvailableCarsList() {
		return leasingCarTarget.path("/availableOrNotCarList/{isleased}").resolveTemplate("isleased", false).request(MediaType.APPLICATION_JSON).get(new GenericType<List<Cardto>>() {});
		
	}
}
