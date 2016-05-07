package br.com.gotorcidaws.main;

import java.sql.Connection;

public final class Database {

	private Connection connection;

	public Database(final Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		return connection;
	}

	public void close() {
		try {
			connection.close();
		} catch (Exception e) {
			System.err.print("Error while trying to close db connection.");
			e.printStackTrace();
		}
	}
}
