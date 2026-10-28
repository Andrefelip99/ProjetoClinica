package projeto_clinica.com;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@org.springframework.test.context.TestPropertySource(properties = {
		"spring.datasource.url=jdbc:h2:mem:clinic-test;DB_CLOSE_DELAY=-1",
		"spring.datasource.driver-class-name=org.h2.Driver",
		"spring.datasource.username=sa",
		"spring.datasource.password=",
		"spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
		"security.client-secret=test-secret"
})
class ComApplicationTests {

	@Test
	void contextLoads() {
	}

}
