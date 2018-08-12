package ru.ivan.test.servise;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.SocketUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.ivan.test.Model.TableEntity;
import ru.ivan.test.Model.UralModel;
import ru.ivan.test.csvUtils.CSVLoder;

@RestController
@RequestMapping("/H2")
public class Webservise {

    @Autowired
    private DataServise data;
    @Autowired
    private RepositoryUral ural;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String getH2Index() {
        return "Hi";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/set")
    public ResponseEntity<String> setData(@RequestParam(value = "file", required = true) String file) throws FileNotFoundException {
        if (file.isEmpty() && file.equals("")) {
            return new ResponseEntity<>("File Exists!", HttpStatus.BAD_REQUEST);
        }

        try (Stream<String> stream = Files.lines(Paths.get(file))) {
            ArrayList<String> list = (ArrayList<String>) stream.collect(Collectors.toList());
            ArrayList<TableEntity> tableList = new ArrayList<>();
            for (String str : list) {
                String[] mstr = str.split(",");
                tableList.add(new TableEntity(Long.valueOf(mstr[0]), mstr[1], mstr[2], Long.valueOf(mstr[3]), Long.valueOf(mstr[4])));
            }
            data.setAll(tableList);

            System.out.println(data.getAll());
            return new ResponseEntity<String>(list.toString(), HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("list", HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/autoset")
    public ResponseEntity<String> setAutoGenerate() {
        ArrayList<TableEntity> arrayList = new ArrayList<>();
        for (Long i = 0L; i < 1000000; i++) {
            arrayList.add(new TableEntity(i, "qweqweasdsdwdqwdqwdqwdqwd", "asdcadfqawdfasfasfasfafa", new Long("12345678"), new Long("12345678")));
        }
        data.setAll(arrayList);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getall")
    public ResponseEntity<String> getall() {
        String s = LocalDateTime.now().toString();

        data.getAll().toString();
        String s2 = LocalDateTime.now().toString();
        return new ResponseEntity<String>(s + "/n" + s2, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/csv")
    public ResponseEntity<String> setFileCSV(@RequestParam(value = "file", required = true) String file) throws FileNotFoundException, InterruptedException {
        if (file.isEmpty() && file.equals("")) {
            return new ResponseEntity<>("File Exists!", HttpStatus.BAD_REQUEST);
        }
        CSVLoder csvLoder = new CSVLoder(ural);
        LocalDateTime start = LocalDateTime.now();
        csvLoder.loadObjectList(file);
        LocalDateTime end = LocalDateTime.now();
        int finish = end.getNano() - start.getNano();
        
        System.out.println("start: " + start);
        System.out.println("end: " + end);
        System.out.println("finish: " + finish / 1000000);
        
        
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUral")
    public ResponseEntity<String> getUral(@RequestParam(value = "id", required = true) long id) {

        Optional<UralModel> model = ural.findById(id);

        return new ResponseEntity<String>((model.isPresent()) ? model.get().toString() : "Запись не найдена!", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUralOrder")
    public ResponseEntity<String> getUralOrder() {

        List<UralModel> model = ural.findAllByOrderByDateAscCorrespondentINNAsc();
        for (UralModel model2 : model) {
            System.out.println(model2.toString());
        }
        List<UralModel> model2 = ural.findAllByOrderByCorrespondentINNAsc();
        for (UralModel model3 : model2) {
            System.out.println(model3.toString());
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/testS")
    public ResponseEntity<String> testSelect() {

        LocalDateTime start = LocalDateTime.now();
        List<List<String>> list = data.testSelect();
        LocalDateTime end = LocalDateTime.now();
        int finish = end.getNano() - start.getNano();

        System.out.println("start: " + start);
        System.out.println("end: " + end);
        System.out.println("finish: " + finish / 1000000);

        /*for (List<String> l: list ) {
			for (String str: l) {
				System.out.print(str + " --- ");
			}
			System.out.println("\n");
		}*/
        return new ResponseEntity<String>("" + finish / 1000000, HttpStatus.OK);
    }

}
