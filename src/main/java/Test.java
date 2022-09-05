import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

@Slf4j
public class Test {

    public static void readFileBy6() throws IOException {
        String path = "src/main/resources/context.txt";

        Scanner scanner = new Scanner(new FileReader(path));
        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }
        log.info("=======Scanner:=========");

        Stream<String> lines = Files.lines(Paths.get(path));
        System.out.println(lines);
        log.info(" ========Files.lines========");

        List<String> strings = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        System.out.println(strings.toString());
        log.info(" ========Files.readAllLines========");

        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String s = bufferedReader.readLine();
        System.out.println(s);
    }

    public static void main(String[] args) throws IOException {
        readFileBy6();
    }
}
