package com.pnfsoftware.jebglobal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import java.util.ArrayList;
import java.util.List;

public class uX extends KD {
   @JsonProperty(value = "name", required = true)
   @JsonPropertyDescription("The name of the project")
   public String kS;
   @JsonProperty(value = "creation_datetime", required = true)
   @JsonPropertyDescription("The local date and time at which the project was created")
   public String wS;
   @JsonProperty(value = "input_files", required = true)
   @JsonPropertyDescription("The list of top-level artifact files that make up this project")
   public List UT = new ArrayList();
   @JsonProperty(value = "units", required = true)
   @JsonPropertyDescription("The list of high-level units in the project, such as apk apps, dex bytecode, x86 code, etc.")
   public List E = new ArrayList();

   public static class Av {
      @JsonProperty(value = "file_name", required = true)
      @JsonPropertyDescription("The name of the file")
      public String pC;
      @JsonProperty(value = "file_size", required = true)
      @JsonPropertyDescription("The file size in bytes")
      public long A;
      @JsonProperty(value = "contents_sha256_hash", required = true)
      @JsonPropertyDescription("The SHA-256 hash of the file contents")
      public String kS;

      public Av() {
      }

      public Av(String var1, long var2, String var4) {
         this.pC = var1;
         this.A = var2;
         this.kS = var4;
      }
   }

   public static class Sv {
      @JsonProperty(value = "unit_type", required = true)
      @JsonPropertyDescription("The unit type, a short descriptive name such as: apk, dex, pe, elf, x86, arm64, xml, txt, etc.")
      public String pC;
      @JsonProperty(value = "unit_path", required = true)
      @JsonPropertyDescription("The unit path, required by most tool requests that query or perform an action on a unit")
      public String A;

      public Sv() {
      }

      public Sv(String var1, String var2) {
         this.pC = var1;
         this.A = var2;
      }
   }
}
