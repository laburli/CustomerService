package com.tek.trp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
    public void test()
    {
        Application.main(new String[]{
                "--spring.main.web-environment=false",
                "--spring.autoconfigure.exclude=trp",
                // Override any other environment properties according to your needs
        });
    }

}
