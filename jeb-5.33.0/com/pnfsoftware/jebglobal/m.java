package com.pnfsoftware.jebglobal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public class m {
   @JsonProperty(value = "unit_path", required = true)
   @JsonPropertyDescription("The path of a code unit (dex, pe, elf, etc.) such as one provided by get_project_information")
   public String pC;
   @JsonProperty(value = "index", required = true)
   @JsonPropertyDescription("The desired start index for which methods should be gathered")
   public int A;
   @JsonProperty(value = "count", required = true)
   @JsonPropertyDescription("The desired count of methods to be gathered (maximum 100)")
   public int kS;
   @JsonProperty("filter")
   @JsonPropertyDescription("An optional search filter applied on the method address. The wildcard character is allowed.")
   public String wS = "";
}
