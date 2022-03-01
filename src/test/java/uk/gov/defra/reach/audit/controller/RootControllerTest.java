package uk.gov.defra.reach.audit.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class RootControllerTest {

  @InjectMocks
  RootController rootController;

  @Test
  void root_returnsOk() {
    assertThat(rootController.root()).isEqualTo("ok");
  }
}
