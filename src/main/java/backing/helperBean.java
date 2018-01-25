package backing;

import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class helperBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public helperBean() {}
	
	public String logout() {
	     FacesContext facesContext = FacesContext.getCurrentInstance();
	     ExternalContext externalContext = facesContext.getExternalContext();
	     externalContext.invalidateSession();
	     return "/login.xhtml?faces-redirect=true";
	}

}
