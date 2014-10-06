package com.mp.prototype.rest;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Stateless
@Path("test")
@Produces("text/plain")
@Consumes("text/plain")
public class HelloServiceFacade {

	private static final Logger logger = Logger.getLogger(HelloServiceFacade.class.getName());

	@GET
	@Produces("text/plain")
	public String nothing() {
		return "ok!";
	}

	@GET
	@Path("{id1}/something/{id2}")
	public Response sayHello( //
			@Context HttpServletRequest request, //
			@PathParam("id1") String id1, //
			@PathParam("id2") String id2) {
		String result = String.format("called method with parameters %s %s", id1, id2);
		logger.info(result);
		return Response.ok().entity(result).build();
	}

}
