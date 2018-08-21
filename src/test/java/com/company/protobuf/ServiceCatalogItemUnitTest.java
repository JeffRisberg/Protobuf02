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

public class ServiceCatalogItemUnitTest {
  private final String filePath = "service_catalog";

  @After
  public void cleanup() throws IOException {
    Files.deleteIfExists(Paths.get(filePath));
  }

  @Test
  public void givenServiceCatalog_whenCreateJavaInstance_shouldMatchValues() {
    //given
    UUID sysId = UUID.randomUUID();
    String name = "Change of Password";

    ServiceCatalog.Item.Builder builder = ServiceCatalog.Item.newBuilder()
      .setSysId(sysId.toString())
      .setName(name);
    ServiceCatalog.Item item = builder.build();

    //then
    assertEquals(item.getSysId(), sysId.toString());
    assertEquals(item.getName(), name);
  }

  @Test
  public void givenServiceCatalogItem_whenSaveAsAFile_shouldLoadFromFileToJavaClass() throws IOException {
    //given
    UUID sysId = UUID.randomUUID();
    String name = "Change password";

    ServiceCatalog.Item.Builder builder = ServiceCatalog.Item.newBuilder()
      .setSysId(sysId.toString())
      .setName(name);
    ServiceCatalog.Item item = builder.build();

    //when
    FileOutputStream fos = new FileOutputStream(filePath);
    item.writeTo(fos);
    fos.close();

    //then
    FileInputStream fis = new FileInputStream(filePath);
    ServiceCatalog.Item deserialized = ServiceCatalog.Item.newBuilder().mergeFrom(fis).build();
    fis.close();

    assertEquals(deserialized.getSysId(), sysId.toString());
    assertEquals(deserialized.getName(), name);
  }
}
