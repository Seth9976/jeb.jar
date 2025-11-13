package com.pnfsoftware.jebglobal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public class gJ {
   @JsonProperty(value = "unit_path", required = false)
   @JsonPropertyDescription(
      "The apk unit path such as one provided by get_project_information. If none is provided, the first apk unit found in the project will be acted upon."
   )
   public String pC;
   @JsonProperty(value = "index", required = true)
   @JsonPropertyDescription("The desired start index for which resources should be gathered")
   public int A;
   @JsonProperty(value = "count", required = true)
   @JsonPropertyDescription("The desired count of resources to be gathered (maximum 100)")
   public int kS;
   @JsonProperty("filter")
   @JsonPropertyDescription(
      "An optional filter string applied on a resource path. The wildcard character is allowed. Example: 'pub*.xml' would match on 'values/public.xml'"
   )
   public String wS = "";
}
