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

public class ServiceCatalogCategoryUnitTest {
  private final String filePath = "service_catalog";

  @After
  public void cleanup() throws IOException {
    Files.deleteIfExists(Paths.get(filePath));
  }

  @Test
  public void givenServiceCatalog_whenCreateJavaInstance_shouldMatchValues() {
    //given
    UUID sysId = UUID.randomUUID();
    String title = "IT Support";

    ServiceCatalog.Category.Builder builder = ServiceCatalog.Category.newBuilder()
      .setSysId(sysId.toString())
      .setTitle(title);
    ServiceCatalog.Category category = builder.build();

    //then
    assertEquals(category.getSysId(), sysId.toString());
    assertEquals(category.getTitle(), title);
  }

  @Test
  public void givenServiceCategory_whenSaveAsAFile_shouldLoadFromFileToJavaClass() throws IOException {
    //given
    UUID sysId = UUID.randomUUID();
    String title = "IT Support";

    ServiceCatalog.Category.Builder builder = ServiceCatalog.Category.newBuilder()
      .setSysId(sysId.toString())
      .setTitle(title);
    ServiceCatalog.Category category = builder.build();

    //when
    FileOutputStream fos = new FileOutputStream(filePath);
    category.writeTo(fos);
    fos.close();

    //then
    FileInputStream fis = new FileInputStream(filePath);
    ServiceCatalog.Category deserialized = ServiceCatalog.Category.newBuilder().mergeFrom(fis).build();
    fis.close();

    assertEquals(deserialized.getSysId(), sysId.toString());
    assertEquals(deserialized.getTitle(), title);
  }
}
