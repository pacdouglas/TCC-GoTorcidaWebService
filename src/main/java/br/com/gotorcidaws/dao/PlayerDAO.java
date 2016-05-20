package br.com.gotorcidaws.dao;

import br.com.gotorcidaws.model.Player;

public class PlayerDAO extends GenericDAO<Player> {

	public PlayerDAO() {
		super();
	}

	public void save(Player player) {
		save(player);
	}

	public void update(Player player) {
		update(player);
	}

	public void delete(long id) {
		Player p = findById(id);
		delete(p);
	}

	public Player findByName(String name) {
		return super.findByUsername(name);
	}

	public Player findByID(long id) {
		return super.findById(id);
	}
}
