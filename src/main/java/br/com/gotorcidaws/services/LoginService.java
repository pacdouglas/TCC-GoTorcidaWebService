package br.com.gotorcidaws.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import br.com.gotorcidaws.dao.UserDAO;
import br.com.gotorcidaws.model.User;
import br.com.gotorcidaws.utils.Message;
import br.com.gotorcidaws.utils.ServiceLogger;
import br.com.gotorcidaws.utils.json.JSONObject;

@Path("login")
public class LoginService {

	private UserDAO userDAO = new UserDAO();

	@GET
	@Path("{email}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public String login(@PathParam("email") String email, @PathParam("password") String password) {
		User user = userDAO.findByEmail(email);
		
		Message message = new Message();

		if (user != null) {
			
			if (user.getPassword().equals(password)) {
				message.setResponse(200, "Login realizado com sucesso.");
				JSONObject userJSON = new JSONObject(user);
				message.addData("user", userJSON.toString());
			} else {
				message.setResponse(401, "Usuário e/ou senha incorreto(s).");	
			}
		} else {
			message.setResponse(401, "Usuário e/ou senha incorreto(s).");
		}

		ServiceLogger.sent(message.toJSON());
		return message.toJSON();
	}
	
}
