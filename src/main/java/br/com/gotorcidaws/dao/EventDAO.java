package br.com.gotorcidaws.dao;

import br.com.gotorcidaws.model.Event;

public class EventDAO extends GenericDAO<Event> {

	public EventDAO() {
		super();
	}

	public void save(Event event) {
		save(event);
	}

	public void update(Event event) {
		update(event);
	}

	public void delete(int id) {
		Event s = findById(id);
		delete(s);
	}

	public Event findByID(int id) {
		return super.findById(id);
	}
}
