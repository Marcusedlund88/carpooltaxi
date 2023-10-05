import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import io.restassured.RestAssured;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = ConnectionControllerSystemTest.class)
public class ConnectionControllerSystemTest {

    private JSONObject json1;
    private JSONObject json2;
    @BeforeEach
    public void setBaseUri(){

        json1 = new JSONObject();
        json1.put("idSender","1");
        json1.put("idReceiver","2");

        json2 = new JSONObject();
        json2.put("idSender","2");
        json2.put("idReceiver","1");


        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test
    void getAllEstablishedConnections() throws Exception {
        given()
                .when()
                .get("/connection/established/all")
                .then()
                .statusCode(200);
    }

    @Test
    void getAllRequestedConnections() throws Exception {
        given()
                .when()
                .get("/connection/requested/all")
                .then()
                .statusCode(200);
    }

    @Test
    void sendConnectionRequest() throws Exception {
        given()
                .when()
                .contentType("application/json")
                .when()
                .body(json1.toString())
                .post("/connection/request/send")
                .then()
                .statusCode(200);
    }

    @Test
    void grantConnectionRequest() throws Exception {
        given()
                .when()
                .contentType("application/json")
                .when()
                .body(json1.toString())
                .post("/connection/request/grant")
                .then()
                .statusCode(200);
    }
}
