/*
Author -> Praveen Kumar
 */

package helpers;

public enum APIResources {

    ADDPLACEAPI("/add/json"),
    GETPLACEAPI("/get/json"),
    DELETEPLACEAPI("/delete/json"),
    PUTPLACEAPI("/update/json");

    private final String url;

    APIResources(String url){
        this.url = url;
    }

    public String getUrl(){
        return this.url;
    }
}
