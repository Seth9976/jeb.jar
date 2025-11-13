package com.pnfsoftware.jebglobal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public class Mh {
   @JsonProperty(value = "unit_path", required = true)
   @JsonPropertyDescription("The path of a code unit (dex, pe, elf, etc.) such as one provided by get_project_information")
   public String pC;
   @JsonProperty(value = "address", required = true)
   @JsonPropertyDescription("The address where we want to retrieve disassembly code")
   public String A;
   @JsonProperty(value = "line_count_after", required = true)
   @JsonPropertyDescription(
      "A hint holding the number of assembly lines to be retrieved at the provided address. It should be a small number, typically less than 30."
   )
   public int kS;
   @JsonProperty(value = "line_count_before", required = true)
   @JsonPropertyDescription(
      "A hint holding the number of assembly lines to be retrieved before the provided address. It should be a small number, typically less than 20."
   )
   public int wS;
}
