package br.com.gotorcidaws.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.gotorcida.fw.Message;
import br.com.gotorcidaws.dao.UserDAO;
import br.com.gotorcidaws.model.User;

@Path("login")
public class LoginService {

	private UserDAO userDAO = new UserDAO();

	@GET
	@Path("{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public String login(@PathParam("username") String username) {
		User user = userDAO.findByUsername(username);

		Message message = new Message();
		
		if (user != null) {
			message.setSystem("code", "200");
			message.setSystem("message", "Login realizado com sucesso.");
			message.addData("usuario", user.toJSON());
		} else {
			message.setSystem("code", "401");
			message.setSystem("message", "Usuário e/ou senha incorreto(s).");
		}
		
		return message.toJSON();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String loginTeste() {
		Message message = new Message();
		message.setSystem("code", "666");
		message.setSystem("message", "Lucifer approves.");
		return message.toJSON();
	}
}
