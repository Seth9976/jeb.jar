package com.pnfsoftware.jebglobal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import java.util.ArrayList;
import java.util.List;

public class Pj extends KD {
   @JsonProperty(value = "resource_paths", required = true)
   @JsonPropertyDescription("A list of resource paths")
   public List kS = new ArrayList();
}
