package com.pnfsoftware.jebglobal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import java.util.ArrayList;
import java.util.List;

public class Sb extends KD {
   @JsonProperty(value = "strings", required = true)
   @JsonPropertyDescription("A list of strings")
   public List kS = new ArrayList();
}
