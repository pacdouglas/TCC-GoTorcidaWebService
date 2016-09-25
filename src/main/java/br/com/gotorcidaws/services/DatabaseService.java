package br.com.gotorcidaws.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import br.com.gotorcidaws.main.DatabaseGenerator;
import br.com.gotorcidaws.utils.Message;

@Path("database")
public class DatabaseService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String create() {

		Message message = new Message();
		try {
			DatabaseGenerator.createDatabase();
			message.setResponse(200, "Database criado com sucesso.");
		} catch (Exception e) {
			message.setResponse(500, "Erro ao criar banco de dados.");
			message.addData("errorMessage", e.getMessage());
		}

		return message.toJSON();
	}
}
