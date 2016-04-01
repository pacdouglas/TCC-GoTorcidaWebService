package br.com.gotorcida.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import br.com.gotorcida.dao.UsuarioDAO;
import br.com.gotorcida.modelo.Usuario;

@Path("usuarios")
public class UsuarioResource {

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String busca(@PathParam ("id") int id){
		Usuario usuario = new UsuarioDAO().busca(id);
		return usuario.toJSON();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String adiciona(String conteudo){
		Usuario usuario = (Usuario) new Gson().fromJson(conteudo, Usuario.class);
		new UsuarioDAO().adiciona(usuario);
		return "Sucesso";
	}

}
