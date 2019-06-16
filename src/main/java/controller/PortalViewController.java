package controller;

import java.util.HashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.ViewAndModel;

@ApplicationScoped
@Path("auth/portal")
public class PortalViewController {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public ViewAndModel portalView() {
		HashMap<String, Object> model = new HashMap<>();
		model.put("label", "this is test label.");

		ViewAndModel vm = new ViewAndModel("portal", model);
		return vm;
	}

}
