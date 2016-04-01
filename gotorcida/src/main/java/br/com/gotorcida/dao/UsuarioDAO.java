package br.com.gotorcida.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import br.com.gotorcida.modelo.Usuario;

public class UsuarioDAO {

	private static Map<Integer, Usuario> banco = new HashMap<Integer, Usuario>();
	private static AtomicLong contador = new AtomicLong(1);

	static {
		Usuario usuario = new Usuario();
		usuario.setCodigo(1);
		usuario.setEmail("teste@hotmail.com");
		usuario.setLogin("loginTeste");
		usuario.setSenha("senha");
		banco.put(1, usuario);
		
		Usuario usuario2 = new Usuario();
		usuario2.setCodigo(2);
		usuario2.setEmail("meupau");
		usuario2.setLogin("minharola");
		usuario2.setSenha("aaaa");
		banco.put(2, usuario2);
	}

	public void adiciona(Usuario Usuario) {
		int id = (int) contador.incrementAndGet();
		Usuario.setCodigo(id);
		banco.put(id, Usuario);
	}

	public Usuario busca(int id) {
		return banco.get(id);
	}

	public Usuario remove(int id) {
		return banco.remove(id);
	}

}
