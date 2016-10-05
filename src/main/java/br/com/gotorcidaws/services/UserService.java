package br.com.gotorcidaws.services;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.gotorcidaws.dao.DAOManager;
import br.com.gotorcidaws.dao.UserDAO;
import br.com.gotorcidaws.model.User;
import br.com.gotorcidaws.utils.JSONConverter;
import br.com.gotorcidaws.utils.ServiceLogger;

@Path("user")
public class UserService extends GoTorcidaService {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(String content) {
		UserDAO userDAO = DAOManager.getUserDAO();
		
		ServiceLogger.received(content);
		
		User user = JSONConverter.toInstanceOf(User.class, content);
		user.setFirstAccess("S");
		
		if (userDAO.findByEmail(user.getEmailAddress()) != null){
			message.setResponse(500, "E-mail já cadastrado.");
		} else {
			try {
				userDAO.save(user);
				message.setResponse(200, "Você se cadastrou com sucesso!");
				message.addData("usuario", user.toString());
			} catch (Exception ex) {
				message.setResponse(401, "Ocorreu algum erro.");
				ex.printStackTrace();
			}
		}
		
		ServiceLogger.sent(message.toJSON());
		
		return Response.ok(message.toJSON(), MediaType.APPLICATION_JSON).build();
	}
}
