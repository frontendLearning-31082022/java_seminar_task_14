package test.java;

import main.java.Inputer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

class InputerTest {

    private ByteArrayOutputStream outContent;
    private ByteArrayOutputStream errContent;

    @BeforeEach
    public void setUpStreams() {
        this.outContent = new ByteArrayOutputStream();
        this.errContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(this.outContent));
        System.setErr(new PrintStream(this.errContent));
    }


    public void simulateUserInput(String input, Map<String,Object>... params) {
        try (ByteArrayInputStream in = new ByteArrayInputStream(
                input.getBytes())) {
            System.setIn(in);

            Inputer inputer = new Inputer();
            if (params.length>0 && params[0].containsKey("Inputer"))inputer= (Inputer) params[0].get("Inputer");
            inputer.inputData();

        } catch (NoSuchElementException e) {
            if (!e.getMessage().equals("No line found")) throw e;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void niceInput() throws IOException {
        Files.deleteIfExists(Path.of( new Inputer().filesPath+"\\Иванов"));
        simulateUserInput("Иванов Иван Иванович 30.01.1993 89264449274 m");
        Assertions.assertEquals("", errContent.toString());
        Assertions.assertEquals(askInput(1)+"Успешно записано\r\n"
                +askInput(1), outContent.toString());
    }

    @Test
    void inputData_count_need_less() {
        simulateUserInput("sdfg sdfg safs sd fsad fas dfasd fas fdsa f");
        Assertions.assertEquals("введено больше элементов чем требуется\r\n", errContent.toString());
        Assertions.assertEquals(askInput(2), outContent.toString());

    }

    @Test
    void inputData_count_need_more() {
        simulateUserInput("sdfg sdfg");
        Assertions.assertEquals("введено меньше элементов чем требуется\r\n", errContent.toString());
        Assertions.assertEquals(askInput(2), outContent.toString());
    }

    @Test
    void inputData_wrongDate() {
        simulateUserInput("Иванов Иван Иванович 37.01.1993 89264449274 m");
        Assertions.assertTrue(errContent.toString().indexOf("Unparseable date") > -1);
        Assertions.assertEquals(askInput(2), outContent.toString());
    }

    @Test
    void inputData_wrongPhone() {
        simulateUserInput("Иванов Иван Иванович 20.01.1993 tgopa m");
        Assertions.assertEquals("Принимаются номера моб.телефонов в формате 11 цифр\r\n",
                errContent.toString());
        Assertions.assertEquals(askInput(2), outContent.toString());
    }

    @Test
    void inputData_wrongPhone_count() {
        simulateUserInput("Иванов Иван Иванович 20.01.1993 8449274 m");
        Assertions.assertEquals("Принимаются номера моб.телефонов в формате 11 цифр\r\n",
                errContent.toString());
        Assertions.assertEquals(askInput(2), outContent.toString());
    }

    @Test
    void inputData_wrongPhone_count1() {
        simulateUserInput("Иванов Иван Иванович 20.01.1993 89264449274 erert");
        Assertions.assertEquals("Пол возможен только f или m\r\n", errContent.toString());
        Assertions.assertEquals(askInput(2), outContent.toString());
    }

    @Test
    void inputData_badSurName() {
        simulateUserInput("И_ванов Иван Иванович 30.01.1993 89264449274 m");
        Assertions.assertEquals("Система принимает фамилии в формате только букв\r\n",
                errContent.toString());
        Assertions.assertEquals(askInput(2), outContent.toString());
    }

    @Test
    void writeToFile() throws IOException {
        String line = "Иванов Иван Иванович 30.01.1993 89268449274 m";
        String lineFile=toFileFormat(line);

        simulateUserInput(line);
        List<String> lines = Files.readAllLines(new File(new Inputer().filesPath + "\\Иванов").toPath());
        Assertions.assertTrue(lines.stream().anyMatch(x -> x.equals(lineFile)));
    }

    @Test
    void writeToFile_duplicates() throws IOException {
        Files.deleteIfExists(Path.of( new Inputer().filesPath+"\\Сергиенко"));
        simulateUserInput("Сергиенко Иван Иванович 30.01.1993 89264449274 m");
        simulateUserInput("Сергиенко Иван Иванович 30.01.1993 89264449274 m");

        Assertions.assertEquals("Данный пользователь уже существует в файле\r\n", errContent.toString());
        Assertions.assertEquals(askInput(1)+"Успешно записано\r\n"
                + askInput(3), outContent.toString());
    }
    @Test
    void writeToFile_sameSurnameUsers() throws IOException {
        String user1 = "Смирнов Иван Иванович 30.01.1993 89264449274 m";
        String user2 = "Смирнов Василий Иванович 30.01.1993 89264449274 m";

        Files.deleteIfExists(Path.of( new Inputer().filesPath+"\\Смирнов"));
        simulateUserInput(user1);
        simulateUserInput(user2);

        String lineFile1=toFileFormat(user1);
        String lineFile2=toFileFormat(user2);

        List<String> lines = Files.readAllLines(new File(new Inputer().filesPath + "\\Смирнов").toPath());
        Assertions.assertTrue(lines.stream().anyMatch(x -> x.equals(lineFile1)));
        Assertions.assertTrue(lines.stream().anyMatch(x -> x.equals(lineFile2)));

        Assertions.assertEquals(askInput(1)+"Успешно записано\r\n"
                + askInput(2)+"Успешно записано\r\n"+ askInput(1), outContent.toString());
    }


    private String toFileFormat(String line) {
        line=line.replaceAll(" ","><");
        line="<"+line+">";
        line=line.substring(0,line.length()-16)+" "+line.substring(line.length()-16,line.length());
        return line;
    }

    String askInput(int repeat) {
        return ("Введите свои данные в форматеФамилия Имя Отчество датарождения(dd.mm.yyyy) " +
                "номертелефона(беззнаковое число без форматирования) пол(f или m). Для выхода введите ex\r\n"
                        ).repeat(repeat);
    }

}