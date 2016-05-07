package br.com.gotorcidaws.services;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import br.com.gotorcidaws.dao.UserDAO;
import br.com.gotorcidaws.model.User;

@Path("user")
public class UserService {
	
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String save(String content) {
        User user = (User) new Gson().fromJson(content, User.class);
        new UserDAO().save(user);
        return "";
    }
}
