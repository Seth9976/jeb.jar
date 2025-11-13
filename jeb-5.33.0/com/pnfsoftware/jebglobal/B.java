package com.pnfsoftware.jebglobal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public class B {
   @JsonProperty(value = "unit_path", required = false)
   @JsonPropertyDescription(
      "The dex unit path such as one provided by get_project_information. If none is provided, the first dex unit found in the project will be acted upon."
   )
   public String pC;
   @JsonProperty(value = "source_type_descriptor", required = true)
   @JsonPropertyDescription("The type descriptor of the class to be moved")
   public String A;
   @JsonProperty(value = "destination_package_fully_qualified_name", required = true)
   @JsonPropertyDescription("The fully-qualified dot-separated package name of where the class should be moved")
   public String kS;
}
