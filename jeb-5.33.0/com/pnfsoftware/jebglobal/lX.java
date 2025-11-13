package com.pnfsoftware.jebglobal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import java.util.ArrayList;
import java.util.List;

public class lX extends KD {
   @JsonProperty(value = "count_items_renamed", required = true)
   @JsonPropertyDescription("The count of items that were successfully renamed")
   public int kS;
   @JsonProperty(value = "count_failures", required = true)
   @JsonPropertyDescription("The number of failures")
   public int wS;
   @JsonProperty(value = "failures", required = true)
   @JsonPropertyDescription("The list of renaming actions that failed. On a fully successful call, this list will be empty.")
   public List UT = new ArrayList();

   public static class Av {
      @JsonProperty(value = "local_name", required = true)
      @JsonPropertyDescription("The name of the local variable or parameter for which renaming failed")
      public String pC;
      @JsonProperty(value = "failure_reason", required = true)
      @JsonPropertyDescription("The reason why renaming failed")
      public String A;

      public Av() {
      }

      public Av(String var1, String var2) {
         this.pC = var1;
         this.A = var2;
      }
   }
}
