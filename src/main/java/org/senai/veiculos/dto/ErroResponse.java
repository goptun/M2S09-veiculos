package org.senai.veiculos.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErroResponse {

  private String title;

  private String message;

  private Map<String, String> details;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime timestamp;

  public ErroResponse(String title, String message, Map details) {
    this.title = title;
    this.message = message;
    this.details = details;
    this.timestamp = LocalDateTime.now();
  }

  public ErroResponse(String title, String message) {
    this(title, message, null);
  }

  public ErroResponse(String title) {
    this(title, null);
  }

}
