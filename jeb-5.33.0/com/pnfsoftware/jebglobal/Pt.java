package com.pnfsoftware.jebglobal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import java.util.ArrayList;
import java.util.List;

public class Pt {
   @JsonProperty(value = "unit_path", required = true)
   @JsonPropertyDescription("The path of a code unit (dex, pe, elf, etc.) such as one provided by get_project_information")
   public String pC;
   @JsonProperty(value = "method_address", required = true)
   @JsonPropertyDescription(
      "The address of a previously decompiled method. It may be a symbol name or a memory address. For a dex method, a method address is as such: 'type_descriptor->method_descriptor', example: 'Lcom/abc;->someFunction([I)V'\n"
   )
   public String A;
   @JsonProperty(value = "items_and_names", required = true)
   @JsonPropertyDescription("The list of method's locals to be renamed, including the current variable name and the desired new name")
   public List kS = new ArrayList();

   public static class Av {
      @JsonProperty(value = "current_local_name", required = true)
      @JsonPropertyDescription("The current name of the local variable or parameter to be renamed")
      public String pC;
      @JsonProperty(value = "new_local_name", required = true)
      @JsonPropertyDescription("The desired new name for the local variable or parameter")
      public String A;
   }
}
