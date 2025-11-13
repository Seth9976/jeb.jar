package com.pnfsoftware.jebglobal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public class p {
   @JsonProperty(value = "unit_path", required = false)
   @JsonPropertyDescription(
      "The apk unit path such as one provided by get_project_information. If none is provided, the first apk unit found in the project will be acted upon."
   )
   public String pC;
}
