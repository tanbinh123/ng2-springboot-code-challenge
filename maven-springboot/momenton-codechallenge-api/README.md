## Maven + SpringBoot + JDBI + Flyway + H2 + Swagger BackEnd Setup

## Run the project
- `mvn clean install`
- Open project with IntelliJ or Eclipse
- Run `Main` class in `momenton-codechallenge-db-migration` module
- This will run all sql scripts in directory `momenton-codechallenge-db-migration\src\main\resources\db\migration`
- The database is a file based h2. Check the database files in `your_home_dir/h2/codechallenge_db.mv` and `your_home_dir/h2/codechallenge_db.trace`
- Now run `CodeChallengeBootApplication` class in `momenton-rest-api`

### Changing the Database Settings

- To point to any other database refer to `momenton-rest-api\src\main\resources\application.properties` and `momenton-codechallenge-db-migration\src\main\resources\scripts.properties`
- Change sql syntax in scripts in directory `momenton-codechallenge-db-migration\src\main\resources\db\migration` accordingly.

### Navigate to port 8080

- `http://localhost:8000/swagger-ui.html`
