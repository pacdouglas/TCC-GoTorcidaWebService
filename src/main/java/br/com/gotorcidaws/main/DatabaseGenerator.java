package br.com.gotorcidaws.main;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class DatabaseGenerator {

	public static void main(String[] args) {
		createDatabase();
	}

	public static void createDatabase() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SchemaExport se = new SchemaExport(cfg);
		se.create(true, true);
	}

}
