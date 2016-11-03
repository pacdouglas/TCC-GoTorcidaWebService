package br.com.gotorcidaws.dao;

import org.hibernate.criterion.Restrictions;
import br.com.gotorcidaws.model.Event;
import br.com.gotorcidaws.model.EventResult;

public class EventResultDAO extends GenericDAO<EventResult> {

	public EventResultDAO() {
		super();
	}

	public void save(EventResult eventResult) {
		save(eventResult);
	}

	public void update(EventResult eventResult) {
		update(eventResult);
	}

	public void delete(int id) {
		EventResult s = findById(id);
		delete(s);
	}

	public EventResult findByID(int id) {
		return super.findById(id);
	}
	
	public EventResult findByEventID(Event event) {
		return (EventResult) getSession().createCriteria(EventResult.class).add(Restrictions.eq("event.id", event.getId())).uniqueResult();	
	}
	
}
