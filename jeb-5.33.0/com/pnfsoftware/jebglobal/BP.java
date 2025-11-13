package com.pnfsoftware.jebglobal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public class BP {
   @JsonProperty(value = "unit_path", required = false)
   @JsonPropertyDescription(
      "The dex unit path such as one provided by get_project_information. If none is provided, the first dex unit found in the project will be acted upon."
   )
   public String pC;
   @JsonProperty(value = "current_fully_qualified_name", required = true)
   @JsonPropertyDescription("The fully-qualified name of the package to be renamed")
   public String A;
   @JsonProperty(value = "new_simple_name", required = true)
   @JsonPropertyDescription("The new simple name to be set for the package")
   public String kS;
}
