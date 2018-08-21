package com.company.protobuf;

import com.company.serviceCatalog.ServiceCatalog;
import org.junit.After;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class ServiceCatalogCatalogUnitTest {
  private final String filePath = "service_catalog";

  @After
  public void cleanup() throws IOException {
    Files.deleteIfExists(Paths.get(filePath));
  }

  @Test
  public void givenServiceCatalog_whenCreateJavaInstance_shouldMatchValues() {
    //given
    UUID sysId = java.util.UUID.randomUUID();
    String name = "Technical Catalog";

    ServiceCatalog.Catalog.Builder builder = ServiceCatalog.Catalog.newBuilder()
      .setSysId(sysId.toString())
      .setName(name);
    ServiceCatalog.Catalog catalog = builder.build();

    //then
    assertEquals(catalog.getSysId(), sysId.toString());
    assertEquals(catalog.getName(), name);
  }

  @Test
  public void givenServiceCatalog_whenSaveAsAFile_shouldLoadFromFileToJavaClass() throws IOException {
    //given
    UUID sysId = java.util.UUID.randomUUID();
    String name = "Technical Catalog";

    ServiceCatalog.Catalog.Builder builder = ServiceCatalog.Catalog.newBuilder()
      .setSysId(sysId.toString())
      .setName(name);
    ServiceCatalog.Catalog catalog = builder.build();

    //when
    FileOutputStream fos = new FileOutputStream(filePath);
    catalog.writeTo(fos);
    fos.close();

    //then
    FileInputStream fis = new FileInputStream(filePath);
    ServiceCatalog.Catalog deserialized = ServiceCatalog.Catalog.newBuilder().mergeFrom(fis).build();
    fis.close();

    assertEquals(deserialized.getSysId(), sysId.toString());
    assertEquals(deserialized.getName(), name);
  }
}
