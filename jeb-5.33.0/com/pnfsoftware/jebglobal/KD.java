package com.pnfsoftware.jebglobal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public abstract class KD {
   @JsonProperty("error")
   @JsonPropertyDescription("Indicates whether an error occurred")
   public boolean pC = false;
   @JsonProperty("error_message")
   @JsonPropertyDescription("An optional error message")
   public String A = "";
}
