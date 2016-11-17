package br.com.gotorcidaws.dao;

import java.util.List;
import org.hibernate.criterion.Restrictions;
import br.com.gotorcidaws.model.Position;
import br.com.gotorcidaws.model.Sport;

public class PositionDAO extends GenericDAO<Position>{

	@SuppressWarnings("unchecked")
	public List<Position> listBySport(Sport sport) {
		return getSession().createCriteria(Position.class).add(Restrictions.eq("sport.id", sport.getId())).list();
	}
	
}
