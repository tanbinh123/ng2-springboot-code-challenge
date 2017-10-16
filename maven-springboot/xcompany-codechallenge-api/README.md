## Maven + SpringBoot + JDBI + Flyway + H2 + Swagger BackEnd Setup

## Run the project
- `mvn clean install`
- Open project with IntelliJ or Eclipse
- Run `main()` method of `au.com.xcompany.codechallenge.db.scripts.DataMigrator` class in `xcompany-codechallenge-db-migration` module
- This will run all sql scripts in directory `xcompany-codechallenge-db-migration\src\main\resources\db\migration`
- The database is a file based h2. Check for the database files in `your_home_dir/h2/codechallenge_db.*`
- Now run `main()` method of `au.com.xcompany.codechallenge.main.CodeChallengeBootApplication` class in `xcompany-codechallenge-rest-api`

### Changing the Database Settings

- To point to any other database refer to `xcompany-codechallenge-rest-api\src\main\resources\application.properties` and `xcompany-codechallenge-db-migration\src\main\resources\scripts.properties`
- Change sql syntax in scripts in directory `xcompany-codechallenge-db-migration\src\main\resources\db\migration` accordingly.

### Navigate to port 8080

- `http://localhost:8080/swagger-ui.html`
