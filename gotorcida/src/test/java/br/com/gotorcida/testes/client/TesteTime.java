package br.com.gotorcida.testes.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import br.com.gotorcida.modelo.Time;
import br.com.gotorcida.modelo.Usuario;
import br.com.gotorcida.servidor.Servidor;

public class TesteTime {
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
        String conteudo = target.path("/times/2").request().get(String.class);
        System.out.println(conteudo);
        Time time = (Time) new Gson().fromJson(conteudo, Time.class);
		System.out.println(time.toString());
	}
}
