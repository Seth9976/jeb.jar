package com.pnfsoftware.jebglobal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import java.util.ArrayList;
import java.util.List;

public class ma extends KD {
   @JsonProperty(value = "super_types", required = true)
   @JsonPropertyDescription("A list of super types")
   public List kS = new ArrayList();
   @JsonProperty(value = "implemented_interfaces", required = true)
   @JsonPropertyDescription("A list of implemented interfaces")
   public List wS = new ArrayList();
}
