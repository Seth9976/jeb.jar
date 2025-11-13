package com.pnfsoftware.jebglobal;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class DL {
   private Map q = new LinkedHashMap();

   public static DL q(oH var0) throws IOException {
      try {
         String var1 = var0.xK("qGDBServerVersion");
         DL var2 = new DL();
         var2.q = MO.Uv(var1);
         return var2;
      } catch (WI var3) {
         return null;
      }
   }

   public boolean q() {
      return this.q.isEmpty();
   }

   public String q(String var1) {
      return (String)this.q.get(var1);
   }

   public String RF() {
      return MO.gO((String)this.q.get("name"));
   }

   public String xK() {
      return MO.gO((String)this.q.get("version"));
   }

   @Override
   public String toString() {
      return this.q.toString();
   }
}
