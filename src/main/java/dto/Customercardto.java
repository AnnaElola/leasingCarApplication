package dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//Frontend

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Customercardto {

	@XmlElement
	private int ccid;
	
	@XmlElement
	private int customerid;
	
	@XmlElement
	private String licensenumber;
	
	public Customercardto() {}

	public Customercardto(int ccid, int customerid, String licensenumber) {
		this.ccid = ccid;
		this.customerid = customerid;
		this.licensenumber = licensenumber;
	}
	
	public Customercardto(int customerid, String licensenumber) {
		this.customerid = customerid;
		this.licensenumber = licensenumber;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public String getLicensenumber() {
		return licensenumber;
	}

	public void setLicensenumber(String licensenumber) {
		this.licensenumber = licensenumber;
	}

	public int getCcid() {
		return ccid;
	}
	
	
}

