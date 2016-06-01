package br.com.gotorcidaws.services;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;

import br.com.gotorcida.fw.Message;
import br.com.gotorcidaws.dao.UserDAO;
import br.com.gotorcidaws.model.User;

@Path("user")
public class UserService {

	private final UserDAO dao = new UserDAO();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String save(String content) {
		User user = (User) new Gson().fromJson(content, User.class);
		Message message = new Message();

		try {
			dao.save(user);
			message.addSystem("code", "200");
			message.addSystem("message", "Você se cadastrou com sucesso!");
			message.addData("usuario", user.toJSON());
		} catch (Exception ex) {
			message.addSystem("code", "401");
			message.addSystem("message", "Nome de usuário já em uso.");
		}

		return message.toJSON();
	}
}
