import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import io.restassured.RestAssured;
import org.springframework.test.context.ContextConfiguration;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = UserControllerSystemTest.class)
public class UserControllerSystemTest {

        @BeforeEach
        public void setBaseUri(){
            RestAssured.baseURI = "http://localhost:8080";
        }

        @Test
        public void testConnectionEndPointGetAllExpectOK(){
           String response =  given()
                    .when()
                    .get("/user")
                    .then()
                    .statusCode(200)
                    .extract().asString();

           assertEquals(response, "Hello");
        }

}
