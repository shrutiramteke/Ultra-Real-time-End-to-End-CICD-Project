package project.demo;

// import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

public class DemoApplicationTests {
	@GetMapping
	public String message() {
<<<<<<< HEAD

=======
>>>>>>> 4541ad16ac649f142797606e4d26138e72fa048b;
		return "welcome to our project";
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplicationTests.class, args)	;	
	}
}
