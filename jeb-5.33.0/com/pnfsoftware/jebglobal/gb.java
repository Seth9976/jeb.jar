package com.pnfsoftware.jebglobal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public class gb {
   @JsonProperty(value = "unit_path", required = true)
   @JsonPropertyDescription("The path of a code unit (dex, pe, elf, etc.) such as one provided by get_project_information")
   public String pC;
   @JsonProperty(value = "address", required = true)
   @JsonPropertyDescription("The address for which cross-references to are to be found")
   public String A;
   @JsonProperty(value = "index", required = true)
   @JsonPropertyDescription("The desired start index for which cross-references should be gathered")
   public int kS;
   @JsonProperty(value = "count", required = true)
   @JsonPropertyDescription("The desired count of cross-references to be gathered (maximum 100)")
   public int wS;
}
