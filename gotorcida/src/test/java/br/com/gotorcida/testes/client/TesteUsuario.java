package br.com.gotorcida.testes.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import br.com.gotorcida.modelo.Usuario;
import br.com.gotorcida.servidor.Servidor;

public class TesteUsuario {

	private HttpServer server;

	@Before
	public void before() {
	    this.server = Servidor.inicializaServidor();
	}
	
	@After
    public void desligaServidor() {
        server.stop();
    }
	
    @Test
    public void testaConexao() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080");
        String conteudo = target.path("/usuarios/2").request().get(String.class);
        System.out.println(conteudo);
        Usuario usuario = (Usuario) new Gson().fromJson(conteudo, Usuario.class);
        System.out.println(usuario.toString());
    }
}
