package com.company.protobuf;

import com.company.common.CustomField;
import com.company.common.TicketContent;
import com.company.common.TicketContentOrBuilder;

import java.net.InetAddress;
import java.util.UUID;

public class Main {

  public Main() {
    UUID sysId = java.util.UUID.randomUUID();
    String name = "Technical Catalog";

    TicketContent.Builder builder = TicketContent.newBuilder();
    builder.setId(sysId.toString());
    builder.setDisplayId(name);

    TicketContent ticket = builder.build();

    // serialize
    System.out.println("serializing");
    System.out.println(ticket);
  }

  public void customTest() {
    UUID sysId = java.util.UUID.randomUUID();
    String name = "Technical Catalog";

    TicketContent.Builder ticketBuilder = TicketContent.newBuilder();
    ticketBuilder.setId(sysId.toString());
    ticketBuilder.setDisplayId(name);

    CustomField.Builder cfBuilder = CustomField.newBuilder();
    cfBuilder.setName("category");
    cfBuilder.setValue("alpha");
    ticketBuilder.addCustomFields(cfBuilder.build());

    TicketContent ticket = ticketBuilder.build();

    // serialize
    System.out.println("serializing");
    System.out.println(ticket);
  }

  public static void main(String[] args) {
    Main main = new Main();

    main.customTest();
  }
}
