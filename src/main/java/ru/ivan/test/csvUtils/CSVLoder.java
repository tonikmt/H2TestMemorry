package ru.ivan.test.csvUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import javassist.bytecode.stackmap.BasicBlock.Catch;
import ru.ivan.test.Model.UralModel;
import ru.ivan.test.servise.RepositoryUral;

public class CSVLoder {

    
    private RepositoryUral ural;
    private final BlockingQueue<String> linesStringFile;
    private final String end = "end";

    public CSVLoder(RepositoryUral ural) {
        this.ural = ural;
        this.linesStringFile = new ArrayBlockingQueue<>(100, true);
    }

    private UralModel createUralModel(String[] str) {
        UralModel u;
        if (str != null && str.length != 0) {
            LocalDateTime date = setDate(str[0]);
            u = new UralModel(date, (!str[1].isEmpty()) ? str[1] : "", (!str[2].isEmpty()) ? str[2] : "",
                    (!str[3].isEmpty()) ? str[3] : "", (!str[4].isEmpty()) ? str[4] : "", generateDouble(),
                    (!str[6].isEmpty()) ? str[6] : "", (!str[7].isEmpty()) ? str[7] : "",
                    (!str[8].isEmpty()) ? str[8] : "", (!str[9].isEmpty()) ? str[9] : "",
                    (!str[10].isEmpty()) ? str[10] : " ", (!str[11].isEmpty()) ? str[11] : " ",
                    String.valueOf(new Random().nextLong()).substring(0, 10).replace("-", ""),
                    // (!str[12].isEmpty()) ? str[12] : " ",
                    (!str[13].isEmpty()) ? str[13] : " ",
                    (str.length == 15) ? ((!str[14].isEmpty()) ? str[14] : " ") : " ",
                    (!str[15].isEmpty()) ? str[15] : " ", (!str[16].isEmpty()) ? str[16] : " ",
                    (!str[17].isEmpty()) ? str[17] : " ", (!str[18].isEmpty()) ? str[18] : " ",
                    (!str[19].isEmpty()) ? str[19] : " ", (!str[20].isEmpty()) ? str[20] : " ",
                    (!str[21].isEmpty()) ? str[21] : " ", (!str[22].isEmpty()) ? str[22] : " ",
                    (!str[23].isEmpty()) ? str[23] : " ",
                    (str.length == 25) ? ((!str[24].isEmpty()) ? str[24] : " ") : " ",
                    (str.length == 26) ? ((!str[25].isEmpty()) ? str[25] : " ") : " ",
                    ((str.length == 27) ? ((!str[26].isEmpty()) ? str[26] : " ") : " "));
            return u;
        }
        return null;
    }

    public boolean loadObjectList(String fileName) {
        try {
            System.out.println("start");
            System.out.println("file: " + fileName);
            Producer producer = new Producer(fileName);
            Thread prod = new Thread(producer);
            
            prod.start();
            
            Consumer consumer = new Consumer();
            Thread cons = new Thread(consumer);
            
            cons.start();
            cons.join();
           
            
        } catch (Exception e) {
            System.out.println("Ошибка");
            return false;
        }
        return true;
    }

    private LocalDateTime setDate(String date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

        if (date.length() == 18) {
            format = DateTimeFormatter.ofPattern("d.MM.yyyy HH:mm:ss");
        }
        return LocalDateTime.parse(date, format);
    }

    private Double generateDouble() {
        double leftLimit = 1D;
        double rightLimit = 100D;
        return leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
    }

    class Consumer implements Runnable {

        @Override
        public void run() {

            System.out.println("Consumer run");
            try {
                //Thread.sleep(2000);
                String str = null;
                while (!(str = linesStringFile.poll(1, TimeUnit.SECONDS)).equals(end)) {
                    //System.out.println("Consumer цикл while get str: " + str);
                        ural.save(createUralModel(str.split("~")));
                }
                System.out.println("Consumer stop");
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
        }

    }

    class Producer implements Runnable {

        private final String filename;

        public Producer(String filename) {
            this.filename = filename;
        }

        @Override
        public void run() {
        	System.out.println("Producer run");
            System.out.println("run Producer, file: " + filename);
            try {
                for (String str : Files.readAllLines(Paths.get(this.filename), StandardCharsets.UTF_8)) {
                    //System.out.println("Producer for, linesStringFile put str: " + str);
                    linesStringFile.put(str);
                    //System.out.println("Producer for, linesStringFile put str: " + str);
                }
                linesStringFile.put(end);
                System.out.println("Producer stop");
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(CSVLoder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
