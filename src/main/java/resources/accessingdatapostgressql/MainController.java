package resources.accessingdatapostgressql;

import com.uber.h3core.H3Core;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import resources.accessingdatapostgressql.database.LocationRepository;
import resources.accessingdatapostgressql.database.driverlocation;
import resources.accessingdatapostgressql.help.H3help;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private LocationRepository locationRepository;
    private final H3Core h3 = H3Core.newInstance();
    private int res;

    public MainController() throws IOException {
    }

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam Integer Driverid
            , @RequestParam double longtitude, @RequestParam double latitude) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        driverlocation n = new driverlocation();
        n.setDriverid(Driverid);
        n.setLongitude(longtitude);
        n.setLatitude(latitude);

        this.res = 9;
        long h = h3.geoToH3(longtitude, latitude, res);
        n.setH3index("'/x" + String.valueOf(h) + "',");
        locationRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<driverlocation> getAllUsers() {
        // This returns a JSON or XML with the users
        return locationRepository.findAll();
    }
    @GetMapping(path="/driver/{id}")
    public @ResponseBody ArrayList<driverlocation> one(@PathVariable int id) {

        return locationRepository.findUserUsingId(id);
    }

    @GetMapping(path="/driverId/{lat}/{lotitude}")
    public @ResponseBody ArrayList<driverlocation> List(@PathVariable float lat, @PathVariable float lotitude) throws IOException {
        H3help temp = new H3help();
        System.out.println(lotitude);
        System.out.println(lat);
        System.out.println(temp.h3.geoToH3(lat,lotitude, 9));
//        long para = temp.h3.geoToH3(lotitude,lat,9);
//        System.out.println(para);

//        System.out.println(para);
//        final String a = "/x618144247449124863";
//        List<String> newList = new ArrayList<>(para.size());
//        for (Long myInt : para) {
//            newList.add("/x" + String.valueOf(myInt));
//        }
//        System.out.println(para);
        ArrayList<driverlocation> result = new ArrayList<driverlocation>();
//        while (result.size() < 5) {
            List<Long> para = temp.queryVal(temp.h3.geoToH3(lat,lotitude, 9));
            for (Long i : para) {
//                System.out.println(Arrays.toString(temp.longToBytes(i)));
                result.addAll(locationRepository.findUserUsingLongLat(temp.longToBytes(i)));
            }
//        }
        return result;
    }
}