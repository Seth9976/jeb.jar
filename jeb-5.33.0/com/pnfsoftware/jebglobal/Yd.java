package com.pnfsoftware.jebglobal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import java.util.ArrayList;
import java.util.List;

public class Yd {
   @JsonProperty(value = "unit_path", required = false)
   @JsonPropertyDescription(
      "The dex unit path such as one provided by get_project_information. If none is provided, the first dex unit found in the project will be acted upon."
   )
   public String pC;
   @JsonProperty(value = "items_and_names", required = true)
   @JsonPropertyDescription("The list of items to be renamed, including the item address and the desired new name")
   public List A = new ArrayList();

   public static class Av {
      @JsonProperty(value = "current_item_address", required = true)
      @JsonPropertyDescription(
         "The address of the item to be renamed:\n- A dex class address is its type descriptor (JVM internal class name).\n- A dex method address is: 'class_descriptor->method_descriptor'. The method_descriptor is: 'METHOD_NAME(argtypes_descriptors)rettype_descriptor'.\n- A dex field address is: 'class_descriptor->field_descriptor'. The field_descriptor is: 'FIELD_NAME:fieldtype_descriptor'.\n"
      )
      public String pC;
      @JsonProperty(value = "new_simple_name", required = true)
      @JsonPropertyDescription("The new simple name to be set for the item")
      public String A;
   }
}
