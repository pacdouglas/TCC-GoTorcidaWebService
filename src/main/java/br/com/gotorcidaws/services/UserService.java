package br.com.gotorcidaws.services;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.gotorcidaws.dao.UserDAO;
import br.com.gotorcidaws.model.User;
import br.com.gotorcidaws.utils.JSONConverter;

@Path("user")
public class UserService extends GoTorcidaService {

	private final UserDAO dao = new UserDAO();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(String content) {
		
		User user = JSONConverter.toInstanceOf(User.class, content);
		try {
			dao.save(user);
			message.setResponse(200, "Voc� se cadastrou com sucesso!");
			message.addData("usuario", user.toString());
		} catch (Exception ex) {
			message.setResponse(401, "Nome de usu�rio j� em uso.");
		}
		
	    return Response.ok(message.toJSON(), MediaType.APPLICATION_JSON).build();
	}
}
