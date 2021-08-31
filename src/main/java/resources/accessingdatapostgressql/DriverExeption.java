package resources.accessingdatapostgressql;

public class DriverExeption extends RuntimeException{
    DriverExeption(int id) {
        super("Could not find employee " + id);
    }
}
