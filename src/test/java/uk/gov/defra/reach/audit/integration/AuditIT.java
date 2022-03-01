package uk.gov.defra.reach.audit.integration;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import uk.gov.defra.reach.audit.dto.AuditEvent;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AuditIT {


  /**
   * JWT for industry1@email.com valid until December 2024.
   */
  private static final String JWT_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJhYWFhYWFhYS0wMDAwLTAwMDEtZmZmZi1mZmZmZmZmZmZmZmYiLCJjb250YWN0SWQiOiJjY2NjY2NjYy0wMDAwLTAwMDEtZmZmZi1mZmZmZmZmZmZmZmYiLCJsZWdhbEVudGl0eUlkIjpudWxsLCJhY2NvdW50SWQiOiI2MWM0NTA0ZC1lODliLTEyZDMtYTQ1Ni0xMTExMTExMTExMTEiLCJsZWdhbEVudGl0eSI6IlJpY2htb25kIENoZW1pY2FscyIsImNvbXBhbnlUeXBlIjoiTGltaXRlZCBjb21wYW55IiwibGVnYWxFbnRpdHlSb2xlIjoiUkVBQ0ggTWFuYWdlciIsImdyb3VwcyI6bnVsbCwic291cmNlIjoiQjJDIiwicm9sZSI6IklORFVTVFJZX1VTRVIiLCJlbWFpbCI6ImluZHVzdHJ5MUBlbWFpbC5jb20iLCJpYXQiOjE2MDg2NDIzMDksImV4cCI6MTY3MTc1NzUwOX0.rjuZZ9c5EbTdrYkdHRF0JsOKfZy019no2LAEM2QEtIo";

  private static final String AUDIT_SERVICE_URL = System.getProperty("AUDIT_SERVICE_URL","http://localhost:8094");

  private static final RestTemplate REST_TEMPLATE = new RestTemplate();

  @Test
  void shouldSubmitAuditEvent() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setBearerAuth(JWT_TOKEN);
    AuditEvent auditEvent = AuditEvent.builder().action("createSubmission").actionParams("actionParams").role("REACH_MANAGER").httpCode(200).userId(UUID.fromString("AAAAAAAA-0000-0100-FFFF-FFFFFFFFFFFF")).networkAddress("127.0.0.1").build();
    HttpEntity<AuditEvent> request = new HttpEntity<>(auditEvent, headers);

    ResponseEntity<Void> response = REST_TEMPLATE.postForEntity(AUDIT_SERVICE_URL + "/audit", request, Void.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
  }

  @Test
  void shouldFailIfParametersAreMissing() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setBearerAuth(JWT_TOKEN);
    AuditEvent auditEvent = AuditEvent.builder().actionParams("actionParams").role("REACH_MANAGER").httpCode(200).userId(UUID.fromString("AAAAAAAA-0000-0100-FFFF-FFFFFFFFFFFF")).networkAddress("127.0.0.1").build();
    HttpEntity<AuditEvent> request = new HttpEntity<>(auditEvent, headers);

    assertThatThrownBy(() -> REST_TEMPLATE.postForEntity(AUDIT_SERVICE_URL + "/audit", request, Void.class))
            .isInstanceOf(HttpServerErrorException.class)
            .matches(e -> (((HttpServerErrorException) e).getStatusCode()) == HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
