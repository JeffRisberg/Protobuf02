package com.company.protobuf;

import com.company.serviceCatalog.ServiceCatalog;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.util.UUID;

public class Main {

  public static void main(String[] args) {
    UUID sysId = java.util.UUID.randomUUID();
    String name = "Technical Catalog";

    ServiceCatalog.Catalog.Builder builder = ServiceCatalog.Catalog.newBuilder()
      .setSysId(sysId.toString())
      .setName(name);
    ServiceCatalog.Catalog catalog = builder.build();

    // serialize
    System.out.println("serializing");

    // write to Elasticsearch
    System.out.println("writing to Elasticsearch");
    try {
      TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
        .addTransportAddress(new TransportAddress(InetAddress.getByName("host1"), 9300))
        .addTransportAddress(new TransportAddress(InetAddress.getByName("host2"), 9300));

      /*
      Map<IndexMetaData> indices = client.admin().cluster()
        .prepareState().get().getState()
        .getMetaData().getIndices();
      */

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
