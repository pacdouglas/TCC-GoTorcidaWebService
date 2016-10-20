package br.com.gotorcidaws.dao;

import java.util.ArrayList;
import java.util.List;

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

	@SuppressWarnings("unchecked")
	public List<Event> listByLocation(double latitude, double longitude) {
		List<Event> events = getSession().createCriteria(Event.class).list();
		List<Event> nearbyEvents = new ArrayList<Event>();

		for (int i = 0; i < events.size(); i++) {
			Event event = events.get(i);

			if (getDistanceInKilometers(event, latitude, longitude) <= 1) {
				nearbyEvents.add(event);
			}
		}

		return nearbyEvents;
	}

	private double getDistanceInKilometers(Event event, double latitude, double longitude) {
		double dLat = Math.toRadians(latitude - event.getLatitude());
		double dLon = Math.toRadians(longitude - event.getLongitude());
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(event.getLatitude()))
				* Math.cos(Math.toRadians(latitude)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		double c = 2 * Math.asin(Math.sqrt(a));
		return (6366000 * c) / 1000;
	}
}
