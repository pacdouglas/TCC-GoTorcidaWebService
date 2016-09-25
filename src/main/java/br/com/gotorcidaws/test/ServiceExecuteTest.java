package br.com.gotorcidaws.test;

import org.codehaus.jackson.map.ObjectMapper;
import br.com.gotorcidaws.model.Athlete;
import br.com.gotorcidaws.model.League;
import br.com.gotorcidaws.model.Sport;
import br.com.gotorcidaws.model.Team;
import br.com.gotorcidaws.model.User;
import br.com.gotorcidaws.utils.JSONConverter;
import br.com.gotorcidaws.utils.JSONParser;
import br.com.gotorcidaws.utils.Message;
import br.com.gotorcidaws.utils.json.JSONArray;
import br.com.gotorcidaws.utils.json.JSONObject;

public class ServiceExecuteTest {

	public static void main(String[] args) {
		login();
		listSports();
		listLeagues();
		listTeams();
		dashboardConfiguration();
		findLeague();
		findTeam(); 
		listAthletesFromTeam();
		findAthlete();
	}

	public static void login() {
		System.out.println("Starting tests : http://localhost:8080/gotorcidaws/login/) + params");
		String[] params = { "linkinbr", "992200" };

		String paramms = "";

		final JSONParser jParser = new JSONParser();

		for (int i = 0; i <= params.length - 1; i++) {
			paramms += params[i] + "/";
		}
		paramms = paramms.substring(0, paramms.length() - 1);

		try {
			JSONObject result = jParser.getJSONFromUrl("http://localhost:8080/gotorcidaws/login/" + paramms);
			Message resultJSON = JSONConverter.toInstanceOf(Message.class, result.toString());
			System.out.println(resultJSON.toJSON());

			JSONObject response = resultJSON.getResponse();
			System.out.println("Code: " + response.getInt("code"));
			System.out.println("Message: " + response.getString("message"));

			if (response.getInt("code") == 200) {
				User user = JSONConverter.toInstanceOf(User.class, resultJSON.getData("user"));
				System.out.println(user.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Finished tests\n\n");
	}

	public static void listSports() {
		System.out.println("Starting tests : http://localhost:8080/gotorcidaws/sport/)");
		final JSONParser jParser = new JSONParser();

		ObjectMapper mapper = new ObjectMapper();
		try {
			JSONObject result = jParser.getJSONFromUrl("http://localhost:8080/gotorcidaws/sport/");
			Message resultJSON = mapper.readValue(result.toString(), Message.class);
			System.out.println(resultJSON.toJSON());

			JSONArray sports = resultJSON.getData().getJSONArray("sports");

			for (int i = 0; i < sports.length(); i++) {
				Sport sport = mapper.readValue(sports.getJSONObject(i).toString(), Sport.class);
				System.out.println(sport.toString());

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Finished tests\n\n");
	}

	private static void listLeagues() {
		System.out.println("Starting tests : http://localhost:8080/gotorcidaws/league/ + params");

		final JSONParser jParser = new JSONParser();

		JSONArray jsonArray = new JSONArray();
		jsonArray.put("1");
		jsonArray.put("2");

		String userID = "1";

		ObjectMapper mapper = new ObjectMapper();
		try {
			JSONObject result = jParser
					.getJSONFromUrl("http://localhost:8080/gotorcidaws/league/" + userID + "/" + jsonArray.toString());
			Message resultJSON = mapper.readValue(result.toString(), Message.class);
			System.out.println(resultJSON.toJSON());

			JSONArray leagues = resultJSON.getData().getJSONArray("leagues");

			for (int i = 0; i < leagues.length(); i++) {
				JSONArray leaguesFromSport = leagues.getJSONArray(i);

				for (int j = 0; j < leaguesFromSport.length(); j++) {
					League league = mapper.readValue(leaguesFromSport.getJSONObject(j).toString(), League.class);
					System.out.println(league.toString());
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		System.out.println("Finished tests\n\n");
	}
	
	private static void listTeams() {
		System.out.println("Starting tests : http://localhost:8080/gotorcidaws/team/ + params");

		final JSONParser jParser = new JSONParser();

		JSONArray jsonArray = new JSONArray();
		jsonArray.put("1");
		jsonArray.put("2");

		String userID = "1";

		try {
			JSONObject result = jParser
					.getJSONFromUrl("http://localhost:8080/gotorcidaws/team/" + userID + "/" + jsonArray.toString());
			Message resultJSON = JSONConverter.toInstanceOf(Message.class, result.toString());
			System.out.println(resultJSON.toJSON());

			JSONArray teams = resultJSON.getData().getJSONArray("teams");

			for (int i = 0; i < teams.length(); i++) {
				JSONArray teamsFromSport = teams.getJSONArray(i);

				for (int j = 0; j < teamsFromSport.length(); j++) {
					Team team = JSONConverter.toInstanceOf(Team.class, teamsFromSport.getJSONObject(j).toString());
					System.out.println(team.toString());
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		System.out.println("Finished tests\n\n");
	}

	private static void dashboardConfiguration() {
		System.out.println("Starting tests : http://localhost:8080/gotorcidaws/dashboardConfig/ + params");

		final JSONParser jParser = new JSONParser();

		JSONArray jsonSports = new JSONArray();
		jsonSports.put("1");
		jsonSports.put("2");

		JSONArray jsonLeagues = new JSONArray();
		jsonLeagues.put("1");
		jsonLeagues.put("2");

		JSONArray jsonTeams = new JSONArray();

		ObjectMapper mapper = new ObjectMapper();
		try {
			JSONObject result = jParser.getJSONFromUrl("http://localhost:8080/gotorcidaws/dashboardConfig/" + "1" + "/"
					+ jsonSports.toString() + "/" + jsonLeagues.toString() + "/" + jsonTeams.toString());
			Message resultJSON = mapper.readValue(result.toString(), Message.class);
			System.out.println(resultJSON.toJSON());

			System.out.println("Code: " + resultJSON.getResponse().getInt("code"));
			System.out.println("Message: " + resultJSON.getResponse().getString("message"));
			if (resultJSON.getResponse().getInt("code") == 500) {
				System.out.println("Error: " + resultJSON.getData("error"));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		System.out.println("Finished tests\n\n");
	}
	
	private static void findLeague() {
		System.out.println("Starting tests : http://localhost:8080/gotorcidaws/league/ + leagueID");

		final JSONParser jParser = new JSONParser();
		final String leagueID = "1";
		
		try {
			JSONObject result = jParser.getJSONFromUrl("http://localhost:8080/gotorcidaws/league/" + leagueID);
			Message resultJSON = JSONConverter.toInstanceOf(Message.class, result.toString());
			System.out.println(resultJSON.toJSON());

			System.out.println("Code: " + resultJSON.getResponse().getInt("code"));
			System.out.println("Message: " + resultJSON.getResponse().getString("message"));
			if (resultJSON.getResponse().getInt("code") == 500) {
				System.out.println("Error: " + resultJSON.getData("error"));
			} else {
				League league = JSONConverter.toInstanceOf(League.class, resultJSON.getData("league"));
				System.out.println(league.toString());
			}
			

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		System.out.println("Finished tests\n\n");
	}
	
	private static void findTeam() {
		System.out.println("Starting tests : http://localhost:8080/gotorcidaws/team/ + teamID");

		final JSONParser jParser = new JSONParser();
		final String teamID = "1";
		
		try {
			JSONObject result = jParser.getJSONFromUrl("http://localhost:8080/gotorcidaws/team/" + teamID);
			Message resultJSON = JSONConverter.toInstanceOf(Message.class, result.toString());
			System.out.println(resultJSON.toJSON());

			System.out.println("Code: " + resultJSON.getResponse().getInt("code"));
			System.out.println("Message: " + resultJSON.getResponse().getString("message"));
			if (resultJSON.getResponse().getInt("code") == 500) {
				System.out.println("Error: " + resultJSON.getData("error"));
			} else {
				Team team = JSONConverter.toInstanceOf(Team.class, resultJSON.getData("team"));
				System.out.println(team.toString());
			}
			

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		System.out.println("Finished tests\n\n");
	}
	
	private static void listAthletesFromTeam() {
		System.out.println("Starting tests : http://localhost:8080/gotorcidaws/athlete/ + params");

		final JSONParser jParser = new JSONParser();

		String teamID = "2";
		String userID = "1";
		
		try {
			JSONObject result = jParser.getJSONFromUrl("http://localhost:8080/gotorcidaws/athlete/" + userID + "/" + teamID);
			Message resultJSON = JSONConverter.toInstanceOf(Message.class, result.toString());
			System.out.println(resultJSON.toJSON());

			JSONArray athletes = resultJSON.getData().getJSONArray("athletes");

			for (int i = 0; i < athletes.length(); i++) {
				Athlete athlete = JSONConverter.toInstanceOf(Athlete.class, athletes.get(i).toString());
				System.out.println(athlete.toString());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		System.out.println("Finished tests\n\n");
	}
	
	private static void findAthlete() {
		System.out.println("Starting tests : http://localhost:8080/gotorcidaws/athlete/ + athleteID");

		final JSONParser jParser = new JSONParser();
		final String athleteID = "1";
		
		try {
			JSONObject result = jParser.getJSONFromUrl("http://localhost:8080/gotorcidaws/athlete/" + athleteID);
			Message resultJSON = JSONConverter.toInstanceOf(Message.class, result.toString());
			System.out.println(resultJSON.toJSON());

			System.out.println("Code: " + resultJSON.getResponse().getInt("code"));
			System.out.println("Message: " + resultJSON.getResponse().getString("message"));
			if (resultJSON.getResponse().getInt("code") == 500) {
				System.out.println("Error: " + resultJSON.getData("error"));
			} else {
				Athlete athlete = JSONConverter.toInstanceOf(Athlete.class, resultJSON.getData("athlete"));
				System.out.println(athlete.toString());
			}
			

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		System.out.println("Finished tests\n\n");
	}
	
}
