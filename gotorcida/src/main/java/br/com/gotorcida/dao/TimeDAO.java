package br.com.gotorcida.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import br.com.gotorcida.modelo.Atleta;
import br.com.gotorcida.modelo.Time;

public class TimeDAO {
	private static Map<Integer, Time> banco = new HashMap<Integer, Time>();
	private static AtomicLong contador = new AtomicLong(1);

	static {
		Time time1 = new Time();
		time1.setCodigo(1);
		time1.setNome("Mavericks");
		time1.adicionaAtleta(new Atleta(1, "Jos� Ricardo Zanardo Junior", 23));
		
		Time time2 = new Time();
		time2.setCodigo(2);
		time2.setNome("Ponte Preta");
		time2.adicionaAtleta(new Atleta(2, "Rhamah Nemezio", 29));
		time2.adicionaAtleta(new Atleta(3, "Rafael Anjos", 32));
		
		banco.put(1, time1);
		banco.put(2, time2);
		
	}

	public void adiciona(Time Time) {
		int id = (int) contador.incrementAndGet();
		Time.setCodigo(id);
		banco.put(id, Time);
	}

	public Time busca(int id) {
		return banco.get(id);
	}

	public Time remove(int id) {
		return banco.remove(id);
	}
}
