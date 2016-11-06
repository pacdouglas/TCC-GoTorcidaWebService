package br.com.gotorcidaws.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.gotorcidaws.dao.DAOManager;
import br.com.gotorcidaws.dao.NewsDAO;
import br.com.gotorcidaws.dao.UserDAO;
import br.com.gotorcidaws.model.News;
import br.com.gotorcidaws.model.Team;
import br.com.gotorcidaws.utils.JSONConverter;
import br.com.gotorcidaws.utils.ServiceLogger;
import br.com.gotorcidaws.utils.json.JSONArray;
import br.com.gotorcidaws.utils.json.JSONObject;

@Path("news")
public class NewsService extends GoTorcidaService {

	
	@GET
	@Path("find/{type}/{targetId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listNews(@PathParam("type") String type, @PathParam("targetId") String targetId) {
		ServiceLogger.received(type + " / " + targetId);
		
		try {
			NewsDAO newsDAO = DAOManager.getNewsDAO();
			List<News> newsList = null;
			
			switch (type) {
				case "all":
					newsList = newsDAO.findLastNews();
				break;
				
				case "team":
					Team team = DAOManager.getTeamDAO().findByID(Integer.parseInt(targetId));
					newsList = newsDAO.findNewsByTeam(team.getId());
				break;
				
				case "event":
					newsList = newsDAO.findNewsByEvent(Integer.parseInt(targetId));
				break;
			}
			
			newsList = updateNewsDate(newsList);
			
			JSONArray newsArray = new JSONArray();
			for (int i = 0; i < newsList.size(); i++) {
				newsArray.put(new JSONObject(newsList.get(i)));
			}
		
			message.setResponse(200, "Ok.");
			message.addData("newsList", newsArray);
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplicação");
			ex.printStackTrace();
		}
		
		ServiceLogger.sent(message.toJSON());
		return message.toJSON();
	}
	
	@GET
	@Path("find/{newsId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String findNews(@PathParam("newsId") String newsId) {
		ServiceLogger.received(newsId);
		
		try {
			NewsDAO newsDAO = DAOManager.getNewsDAO();
			News news = updateNewsDate(newsDAO.findByID(Integer.parseInt(newsId)));
			
			message.setResponse(200,  "Ok.");
			message.addData("news", new JSONObject(news));
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplicação");
			ex.printStackTrace();
		}
		
		ServiceLogger.sent(message.toJSON());
		return message.toJSON();
	}
	
	private News updateNewsDate(News news) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		news.setFormatedRegistrationDate(dateFormat.format(news.getDate().getTime()));
		return news;
	}
	
	private List<News> updateNewsDate(List<News> news) {
		for (int i = 0; i < news.size(); i++) {
			News newz = news.get(i);
			newz = updateNewsDate(newz);
		}
		
		return news;
	}
	
	@POST
	@Path("update/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(String content) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		NewsDAO newsDAO = DAOManager.getNewsDAO();
		ServiceLogger.received(content);

		JSONObject newsJSON = new JSONObject(content);
		
		News news = newsDAO.findByID(newsJSON.getInt("id"));
		news.setDescription(newsJSON.getString("description"));
		news.setTitle(newsJSON.getString("title"));

		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(dateFormat.parse(newsJSON.getString("date")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		news.setDate(calendar);
		
		try {
			newsDAO.update(news);
			message.setResponse(200, "Notícia criada com sucesso!");
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplicação.");
			ex.printStackTrace();
		}

		ServiceLogger.sent(message.toJSON());
		return Response.ok(message.toJSON(), MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("save/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(String content) {
		NewsDAO newsDAO = DAOManager.getNewsDAO();
		UserDAO userDAO = DAOManager.getUserDAO();
		
		ServiceLogger.received(content);

		JSONObject newsJSON = new JSONObject(content);
		News news = JSONConverter.toInstanceOf(News.class, content);
		news.setUser(userDAO.findById(Integer.parseInt(newsJSON.getString("user"))));
		
		try {
			newsDAO.save(news);
			message.setResponse(200, "Notícia criada com sucesso!");
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplicação.");
			ex.printStackTrace();
		}

		ServiceLogger.sent(message.toJSON());
		return Response.ok(message.toJSON(), MediaType.APPLICATION_JSON).build();
	}

}
