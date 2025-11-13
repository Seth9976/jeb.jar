package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.encoding.Conversion;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ob {
   private Map q = new LinkedHashMap();

   public static ob q(oH var0) throws IOException {
      try {
         String var1 = var0.xK("qProcessInfo");
         ob var2 = new ob();
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

   public long RF() {
      return Conversion.stringToLong((String)this.q.get("pid"), 0L, 16);
   }

   public long xK() {
      return Conversion.stringToLong((String)this.q.get("parent-pid"), 0L, 16);
   }

   @Override
   public String toString() {
      return this.q.toString();
   }
}
