package com.pnfsoftware.jebglobal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import java.util.ArrayList;
import java.util.List;

public class Sf extends KD {
   @JsonProperty(value = "units", required = true)
   @JsonPropertyDescription("The list of retrieved units")
   public List kS = new ArrayList();
}
