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
	@Path("{username}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public String login(@PathParam("username") String username, @PathParam("password") String password) {
		System.out.println("Parâmetro recebido [username]: " + username);
		System.out.println("Parâmetro recebido [password]: " + password);
		
		User user = userDAO.findByUsername(username);
		
		Message message = new Message();

		if (user != null) {
			message.addSystem("code", "200");
			message.addSystem("message", "Login realizado com sucesso.");
			message.addData("usuario", user.toJSON());
		} else {
			message.addSystem("code", "401");
			message.addSystem("message", "Usuário e/ou senha incorreto(s).");
		}

		return message.toJSON();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String loginTeste() {
		Message message = new Message();
		message.addSystem("code", "666");
		message.addSystem("message", "Lucifer approves.");
		return message.toJSON();
	}
}
