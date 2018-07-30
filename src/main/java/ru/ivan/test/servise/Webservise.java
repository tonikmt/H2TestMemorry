package ru.ivan.test.servise;




import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import ru.ivan.test.Model.TableEntity;

@Slf4j
@RestController
@RequestMapping("/H2")

public class Webservise {
	@Autowired
	DataServise data;

	@RequestMapping (method=RequestMethod.GET, value="/")
	public String getH2Index () {
		return "Hi";
	}
	@RequestMapping (method=RequestMethod.GET, value="/set")
	public ResponseEntity<String> setData (@RequestParam (value = "file", required = true) String file) throws FileNotFoundException {
		if (file.isEmpty() && file.equals("")) {
			return new ResponseEntity<>("File Exists!", HttpStatus.BAD_REQUEST);
		}
		
		try (Stream<String> stream = Files.lines(Paths.get(file))) {
			ArrayList <String> list = (ArrayList<String>) stream.collect(Collectors.toList());
			ArrayList<TableEntity> tableList = new ArrayList<>();
			for (String str : list) {
				String [] mstr = str.split(",");
				tableList.add(new TableEntity(Long.valueOf(mstr[0]), mstr[1], mstr[2], Long.valueOf(mstr[3]), Long.valueOf(mstr[4])));
			}
			data.setAll(tableList);
			
			System.out.println(data.getAll());
			return new ResponseEntity<String>(list.toString(), HttpStatus.OK);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			
			
		return new ResponseEntity<> ("list", HttpStatus.OK);
		
	}
	
	
}
