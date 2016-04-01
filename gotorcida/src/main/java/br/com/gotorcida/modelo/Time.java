package br.com.gotorcida.modelo;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class Time {
	private int codigo;
	private String nome;
	private List<Atleta> atletas;

	public Time() {
		this.atletas = new ArrayList<Atleta>();
	}

	public void adicionaAtleta(Atleta atleta) {
		this.atletas.add(atleta);
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Atleta> getAtletas() {
		return atletas;
	}

	public void setAtletas(List<Atleta> atletas) {
		this.atletas = atletas;
	}
	
	public String toJSON() {
		return new Gson().toJson(this);
	}

	@Override
	public String toString() {
		return "Time [codigo=" + codigo + ", nome=" + nome + ", atletas=" + atletas + "]";
	}
	
	

}
