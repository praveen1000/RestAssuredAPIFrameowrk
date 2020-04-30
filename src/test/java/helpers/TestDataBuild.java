/*
Author -> Praveen Kumar
 */

package helpers;

import pojo.AddPlace;
import pojo.DeletePlace;
import pojo.Location;
import pojo.UpdatePlace;

import java.util.Arrays;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacePayload(String name, String language, String address, double latitude, double longitude,
                                    String phNumber, String types){

        String[] type = types.split(",");
        List<String> typeList = Arrays.asList(type);

        AddPlace p = new AddPlace();
        p.setLocation(new Location());
        p.getLocation().setLat(latitude);
        p.getLocation().setLng(longitude);
        p.setAccuracy(50);
        p.setName(name);
        p.setPhone_number(phNumber);
        p.setAddress(address);
        p.setTypes(typeList);
        p.setWebsite("http://google.com");
        p.setLanguage(language);

        return p;
    }

    public DeletePlace deletePlacePayload(String placeId){

        DeletePlace dp = new DeletePlace();
        dp.setPlaceId(placeId);
        return dp;
    }

    public UpdatePlace UpdatePlacePayload(String placeId, String address, String key){

        UpdatePlace up = new UpdatePlace();
        up.setPlaceId(placeId);
        up.setAddress(address);
        up.setKey(key);
        return up;
    }
}
