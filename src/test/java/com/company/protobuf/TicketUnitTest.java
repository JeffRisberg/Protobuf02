package com.company.protobuf;

import com.company.CommDefs.TicketProtos;
import org.junit.After;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class TicketUnitTest {
  private final String filePath = "tickets";

  @After
  public void cleanup() throws IOException {
    Files.deleteIfExists(Paths.get(filePath));
  }

  @Test
  public void givenTicketWithOnePerson_whenCreateJavaInstance_shouldMatchValues() {
    //given
    int id = new Random().nextInt();
    String firstName = "Michael";
    String lastName = "Elephant";
    String email = "elephant@gmail.com";
    String number = "01234567890";

    TicketProtos.Person.Builder builder = TicketProtos.Person.newBuilder()
      .setId(id)
      .setFirstName(firstName)
      .setLastName(lastName)
      .setEmail(email)
      .addNumbers(number);
    TicketProtos.Person person = builder.build();

    //then
    assertEquals(person.getEmail(), email);
    assertEquals(person.getId(), id);
    assertEquals(person.getFirstName(), firstName);
    assertEquals(person.getLastName(), lastName);
    assertEquals(person.getNumbers(0), number);
  }

  @Test
  public void givenTicketWithOnePerson_whenSaveAsAFile_shouldLoadFromFileToJavaClass() throws IOException {
    //given
    int id = new Random().nextInt();
    String firstName = "Michael";
    String lastName = "Zebra";
    String email = "zebra@gmail.com";
    String number = "01234567890";

    TicketProtos.Person.Builder builder = TicketProtos.Person.newBuilder()
        .setId(id)
        .setFirstName(firstName)
        .setLastName(lastName)
        .setEmail(email)
        .addNumbers(number);
    TicketProtos.Person person = builder.build();

    //when
    TicketProtos.Ticket Ticket = TicketProtos.Ticket.newBuilder().addPeople(person).build();
    FileOutputStream fos = new FileOutputStream(filePath);
    Ticket.writeTo(fos);
    fos.close();

    //then
    FileInputStream fis = new FileInputStream(filePath);
    TicketProtos.Ticket deserialized =
      TicketProtos.Ticket.newBuilder().mergeFrom(fis).build();
    fis.close();

    assertEquals(deserialized.getPeople(0).getEmail(), email);
    assertEquals(deserialized.getPeople(0).getId(), id);
    assertEquals(deserialized.getPeople(0).getFirstName(), firstName);
    assertEquals(deserialized.getPeople(0).getLastName(), lastName);
    assertEquals(deserialized.getPeople(0).getNumbers(0), number);
  }
}
