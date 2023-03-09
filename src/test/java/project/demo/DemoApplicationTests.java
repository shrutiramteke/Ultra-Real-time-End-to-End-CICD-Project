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
		System.out.println("Welcome");
		return "welcome to mr.dvops.youtube.channel";
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplicationTests.class, args)	;	
	}
}
