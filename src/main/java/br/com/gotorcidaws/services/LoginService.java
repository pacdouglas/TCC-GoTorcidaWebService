package br.com.gotorcidaws.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import br.com.gotorcidaws.dao.UserDAO;
import br.com.gotorcidaws.model.User;
import br.com.gotorcidaws.utils.Message;
import br.com.gotorcidaws.utils.json.JSONObject;

@Path("login")
public class LoginService {

	private UserDAO userDAO = new UserDAO();

	@GET
	@Path("{username}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public String login(@PathParam("username") String username, @PathParam("password") String password) {
		User user = userDAO.findByUsername(username);
		
		Message message = new Message();

		if (user != null) {
			message.setResponse(200, "Login realizado com sucesso.");
			JSONObject userJSON = new JSONObject(user);
			message.addData("user", userJSON.toString());
		} else {
			message.setResponse(401, "Usuário e/ou senha incorreto(s).");
		}

		return message.toJSON();
	}
	
}
