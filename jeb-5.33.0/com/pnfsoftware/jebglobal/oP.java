package com.pnfsoftware.jebglobal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public class oP {
   @JsonProperty(value = "unit_path", required = false)
   @JsonPropertyDescription(
      "The apk unit path such as one provided by get_project_information. If none is provided, the first apk unit found in the project will be acted upon."
   )
   public String pC;
   @JsonProperty(value = "library_name", required = true)
   @JsonPropertyDescription(
      "The simple name of the dynamic library code unit to be retrieved, such as one provided to a call to System.loadLibrary(). It does not contain any platform specific prefix, file extension or path."
   )
   public String A;
}
