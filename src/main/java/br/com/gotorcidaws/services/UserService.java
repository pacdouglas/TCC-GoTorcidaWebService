package br.com.gotorcidaws.services;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.gotorcidaws.dao.DAOManager;
import br.com.gotorcidaws.dao.UserDAO;
import br.com.gotorcidaws.model.User;
import br.com.gotorcidaws.model.UserType;
import br.com.gotorcidaws.utils.JSONConverter;
import br.com.gotorcidaws.utils.ServiceLogger;
import br.com.gotorcidaws.utils.json.JSONObject;

@Path("user")
public class UserService extends GoTorcidaService {

	@GET
	@Path("{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String findEvent(@PathParam("userId") String userId) {
		UserDAO userDAO = DAOManager.getUserDAO();
		ServiceLogger.received(userId);
		
		try {
			User user = userDAO.findById(Integer.parseInt(userId));
			message.setResponse(200, "Ok");
			message.addData("user", new JSONObject(user));
		} catch (Exception ex) {
			message.setResponse(500, "Ocorreu algum erro interno no servidor.");
			ex.printStackTrace();
		}
		
		ServiceLogger.sent(message.toJSON());
		return message.toJSON();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(String content) {
		UserDAO userDAO = DAOManager.getUserDAO();
		
		ServiceLogger.received(content);
		
		User user = JSONConverter.toInstanceOf(User.class, content);
		user.setFirstAccess("S");
		user.setUserType(UserType.Normal);
		
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
