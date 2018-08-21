package com.company.protobuf;

import com.company.common.TicketContent;
import org.junit.After;
import org.junit.Test;

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
  public void givenTicket_whenCreateJavaInstance_shouldMatchValues() {
    //given
    String id = "Elephant";
    String displayId = "Elephant-1";

    TicketContent.Builder builder = TicketContent.newBuilder()
      .setId(id)
      .setDisplayId(displayId);
    TicketContent ticket = builder.build();

    //then
    assertEquals(ticket.getId(), id);
    assertEquals(ticket.getDisplayId(), displayId);
  }
}
