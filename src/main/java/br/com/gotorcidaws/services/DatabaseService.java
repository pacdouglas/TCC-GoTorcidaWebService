package br.com.gotorcidaws.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import br.com.gotorcida.fw.Message;
import br.com.gotorcidaws.main.DatabaseGenerator;

@Path("database")
public class DatabaseService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String create() {

		Message message = new Message();
		try {
			DatabaseGenerator.createDatabase();
			message.addSystem("code", "200");
			message.addSystem("message", "Database criado com sucesso.");
		} catch (Exception e) {
			message.addSystem("code", "500");
			message.addSystem("message", "Erro ao criar banco de dados.");
			message.addData("errorMessage", e.getMessage());
		}

		return message.toJSON();
	}
}
