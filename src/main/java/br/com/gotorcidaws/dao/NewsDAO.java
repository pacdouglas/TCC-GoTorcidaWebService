package br.com.gotorcidaws.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.gotorcidaws.model.News;

public class NewsDAO extends GenericDAO<News> {

	public NewsDAO() {
		super();
	}

	public void save(News news) {
		save(news);
	}

	public void update(News news) {
		update(news);
	}

	public void delete(int id) {
		News s = findById(id);
		delete(s);
	}

	public News findByID(int id) {
		return super.findById(id);
	}

	@SuppressWarnings("unchecked")
	public List<News> findLastNews() {
		Calendar currentDate = Calendar.getInstance();
		Calendar minimumDate = Calendar.getInstance();
		minimumDate.add(Calendar.DAY_OF_MONTH, -5);
		
		Criteria crit = getSession().createCriteria(News.class).add(Restrictions.between("date", currentDate, minimumDate)).setMaxResults(10);
		crit.addOrder(Order.desc("date"));
		
		return (List<News>) crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<News> findNewsByTeam(int teamId) {
		Criteria crit = getSession().createCriteria(News.class).add(Restrictions.eq("teamId", teamId)).setMaxResults(10);
		crit.addOrder(Order.desc("date"));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<News> findNewsByEvent(int id) {
		Criteria crit = getSession().createCriteria(News.class).add(Restrictions.eq("event.id", id).ignoreCase()).setMaxResults(10);
		crit.addOrder(Order.desc("date"));
		return crit.list();
	}
	
}
