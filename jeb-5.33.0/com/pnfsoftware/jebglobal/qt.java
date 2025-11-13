package com.pnfsoftware.jebglobal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public class qt {
   @JsonProperty(value = "unit_path", required = false)
   @JsonPropertyDescription(
      "The dex unit path such as one provided by get_project_information. If none is provided, the first dex unit found in the project will be acted upon."
   )
   public String pC;
   @JsonProperty(value = "type_descriptor", required = true)
   @JsonPropertyDescription("The type descriptor of the target class")
   public String A;
   @JsonProperty(value = "method_descriptor", required = true)
   @JsonPropertyDescription("The method descriptor of the method to be decompiled in the class")
   public String kS;
}
