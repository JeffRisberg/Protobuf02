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

public class ServiceCatalogVariableUnitTest {
  private final String filePath = "service_catalog";

  @After
  public void cleanup() throws IOException {
    Files.deleteIfExists(Paths.get(filePath));
  }

  @Test
  public void givenServiceCatalog_whenCreateJavaInstance_shouldMatchValues() {
    //given
    UUID sysId = UUID.randomUUID();
    String name = "new_password";
    String label = "New Password";

    ServiceCatalog.Variable.Builder builder = ServiceCatalog.Variable.newBuilder()
      .setSysId(sysId.toString())
      .setName(name);
    ServiceCatalog.Variable variable = builder.build();

    //then
    assertEquals(variable.getSysId(), sysId.toString());
    assertEquals(variable.getName(), name);
  }
}
