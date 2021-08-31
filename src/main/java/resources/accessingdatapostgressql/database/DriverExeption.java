package resources.accessingdatapostgressql.database;

public class DriverExeption extends RuntimeException{
    DriverExeption(int id) {
        super("Could not find employee " + id);
    }
}
