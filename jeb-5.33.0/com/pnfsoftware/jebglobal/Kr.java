package com.pnfsoftware.jebglobal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import java.util.ArrayList;
import java.util.List;

public class Kr extends KD {
   @JsonProperty(value = "xref_addresses", required = true)
   @JsonPropertyDescription("A list of cross-references")
   public List kS = new ArrayList();
}
