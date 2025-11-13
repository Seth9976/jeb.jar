package com.pnfsoftware.jebglobal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public class Hv {
   @JsonProperty(value = "index", required = true)
   @JsonPropertyDescription("The desired start index for which units should be gathered")
   public int pC;
   @JsonProperty(value = "count", required = true)
   @JsonPropertyDescription("The desired count of units to be gathered (maximum 100)")
   public int A;
   @JsonProperty("filter")
   @JsonPropertyDescription("An optional search filter applied on the unit paths. The star wildcard character is allowed.")
   public String kS = "";
   @JsonProperty("parent_unit_path")
   @JsonPropertyDescription("If specified, only the children units of this unit will be retrieved")
   public String wS;
}
