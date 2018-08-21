package com.company.protobuf;

import com.company.common.TicketContent;
import com.company.common.TicketContentOrBuilder;

import java.net.InetAddress;
import java.util.UUID;

public class Main {

  public static void main(String[] args) {
    UUID sysId = java.util.UUID.randomUUID();
    String name = "Technical Catalog";

    TicketContent.Builder builder = TicketContent.newBuilder();
    builder.setId("ddd");
    builder.setDisplayId("ddd");

    TicketContent ticket = builder.build();

    // serialize
    System.out.println("serializing");
  }
}
