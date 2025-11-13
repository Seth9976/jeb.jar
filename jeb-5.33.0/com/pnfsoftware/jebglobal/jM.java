package com.pnfsoftware.jebglobal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import java.util.ArrayList;
import java.util.List;

public class jM extends KD {
   @JsonProperty(value = "results", required = true)
   @JsonPropertyDescription("A list of results")
   public List kS = new ArrayList();
}
