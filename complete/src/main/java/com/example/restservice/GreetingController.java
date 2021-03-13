package com.example.restservice;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import com.opencsv.CSVReader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting("Hola", String.format(template, name));
	}

	@GetMapping("/parseTxt")
	public Greeting parseTxt() {
		String id = "0";
		String name = "";
		try (CSVReader reader = new CSVReader(new FileReader("Names.csv"))) {
		      List<String[]> r = reader.readAll();
		      id =   r.get(0)[0];
		      name = r.get(0)[1];
		      r.forEach(x -> System.out.println(Arrays.toString(x)));
		  }catch(Exception e) {
			  System.out.println(e.getMessage());
		  }
		return new Greeting(id, String.format(template, name));
	}
}
