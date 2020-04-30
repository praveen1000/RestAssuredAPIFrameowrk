/*
Author -> Praveen Kumar
 */

package helpers;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.exception.JsonPathException;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    static RequestSpecification requestSpec;
    static ResponseSpecification responseSpec;
    static InputStream inputStream;
    final static Properties prop = new Properties();

    public RequestSpecification requestSpecification() throws IOException {

        if(requestSpec==null){
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            requestSpec = new RequestSpecBuilder().setBaseUri(getGlobalProperties("baseUrl")).setBasePath("/maps/api/place")
                    .setContentType(ContentType.JSON)
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log, LogDetail.ALL))
                    .build();
            return requestSpec;
        }

        return requestSpec;
    }

    public ResponseSpecification responseSpecification(){

            responseSpec = new ResponseSpecBuilder()
                    .expectContentType(ContentType.JSON)
                    .build();
            return responseSpec;
    }

    public String getGlobalProperties(String key) throws IOException {
        try{
            if(inputStream==null){
                ClassLoader classLoader = getClass().getClassLoader();
                File file = new File(classLoader.getResource("global.properties").getFile());
                inputStream = new FileInputStream(file);
            }

            prop.load(inputStream);
            String value = prop.getProperty(key);

            if (value==null){
                throw new NullPointerException();
            }
            return value;
        }
        catch (IOException e){
            System.out.println("Properties file not found");
        }
        catch (NullPointerException e){
            System.out.println(key + " key not found in properties file.");
        }

        return null;
    }

    public String  getResourceURL(String resource){
        try{
            APIResources res = APIResources.valueOf(resource.toUpperCase());
            return res.getUrl();
        }
        catch(IllegalArgumentException e){
            System.out.println(resource +" resource not available. Please check spelling and provide correct one.");
        }
        return null;
    }

    public Object getJsonpath(Response response, String attribute){

        try{
            JsonPath js = new JsonPath(response.asString());
            Object ob = js.get(attribute);
            return ob;
        }
        catch (JsonPathException e){

            System.out.println(attribute +" not found in the response. Please verify spelling is correct.");
        }
        return null;
    }


}
