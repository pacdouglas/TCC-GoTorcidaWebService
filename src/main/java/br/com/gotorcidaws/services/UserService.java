package br.com.gotorcidaws.services;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import br.com.gotorcidaws.dao.UserDAO;
import br.com.gotorcidaws.model.User;

@Path("user")
public class UserService extends GoTorcidaService {

	private final UserDAO dao = new UserDAO();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(String content) {
		
		ObjectMapper mapper = new ObjectMapper();
		User user = null;
		try {
			user = mapper.readValue(content, User.class);
		} catch (Exception ex) {
			
		}
		
		try {
			dao.save(user);
			message.setResponse(200, "Você se cadastrou com sucesso!");
			message.addData("usuario", user.toString());
		} catch (Exception ex) {
			message.setResponse(401, "Nome de usuário já em uso.");
		}
		
	    return Response.ok(message.toJSON(), MediaType.APPLICATION_JSON).build();
	}
}
