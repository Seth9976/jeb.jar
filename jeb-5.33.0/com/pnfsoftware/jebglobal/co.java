package com.pnfsoftware.jebglobal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public class co {
   @JsonProperty(value = "unit_path", required = false)
   @JsonPropertyDescription(
      "The dex unit path such as one provided by get_project_information. If none is provided, the first dex unit found in the project will be acted upon."
   )
   public String pC;
   @JsonProperty(value = "source_package_fully_qualified_name", required = true)
   @JsonPropertyDescription("The fully-qualified dot-separated name of the package to be moved")
   public String A;
   @JsonProperty(value = "destination_package_fully_qualified_name", required = true)
   @JsonPropertyDescription("The fully-qualified dot-separated name of the destination package")
   public String kS;
}
