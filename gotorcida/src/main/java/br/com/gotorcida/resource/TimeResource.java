package br.com.gotorcida.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.gotorcida.dao.TimeDAO;
import br.com.gotorcida.modelo.Time;

@Path("times")
public class TimeResource {
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String busca(@PathParam ("id") int id){
		Time time = new TimeDAO().busca(id);
		return time.toJSON();
	}
}
