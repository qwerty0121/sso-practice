package controller;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("auth")
public class AuthController {

	@GET
	@Path("success")
	@Produces(MediaType.TEXT_PLAIN)
	public String success(@Context HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();

		return "Success Authentication!! Welcome " + principal.getName() + "!!";
	}

	@GET
	@Path("logout")
	@Produces(MediaType.TEXT_PLAIN)
	public Response logout(@Context HttpServletRequest request) throws UnsupportedEncodingException, ServletException {
		request.logout();

		return Response.seeOther(URI.create("http://localhost:9080/keycloak-practice/keycloak/public/hello")).build();

		// もしくは
		/*
		request.getSession().invalidate();

		String realm = "demo-api";
		String encodedRedirectUri = URLEncoder.encode("http://localhost:8080/rest/public/hello", "UTF-8");
		URI uri = URI
		        .create(String.format("http://172.17.0.2:8080/auth/realms/%s/protocol/openid-connect/logout?redirect_uri=%s", realm, encodedRedirectUri));
		return Response.seeOther(uri).build();
		*/
	}

}
