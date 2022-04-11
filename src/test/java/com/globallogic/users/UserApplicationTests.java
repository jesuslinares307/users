package com.globallogic.users;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest
@TestPropertySource({"classpath:application.properties"})
public class UserApplicationTests {

	   @Test
	    public void init() {
	        // default init test
	    }

}



