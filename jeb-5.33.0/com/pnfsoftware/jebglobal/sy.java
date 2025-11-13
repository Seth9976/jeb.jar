package com.pnfsoftware.jebglobal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public class sy {
   @JsonProperty(value = "unit_path", required = true)
   @JsonPropertyDescription("The path of a code unit (dex, pe, elf, etc.) such as one provided by get_project_information")
   public String pC;
   @JsonProperty(value = "item_address", required = true)
   @JsonPropertyDescription("The address of the item to be decompiled, such as one provided by list_internal_types or list_internal_methods")
   public String A;
}
