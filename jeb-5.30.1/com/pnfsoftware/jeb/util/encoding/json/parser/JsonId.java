package com.pnfsoftware.jeb.util.encoding.json.parser;

public final class JsonId {
   private final String identifier;

   public JsonId(String var1) {
      this.identifier = var1;
   }

   public String getIdentifier() {
      return this.identifier;
   }

   @Override
   public String toString() {
      return this.identifier;
   }
}
