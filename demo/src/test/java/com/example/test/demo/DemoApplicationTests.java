package com.example.test.demo;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.testng.AssertJUnit.assertEquals;
@SpringBootTest
class DemoApplicationTests {
	@Test
	void contextLoads() throws JSONException {
		assertEquals(true,true);
	}

}
