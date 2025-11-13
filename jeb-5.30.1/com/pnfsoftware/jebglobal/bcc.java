package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class bcc {
   static Object q(bcc.eo var0, Object var1, Object var2) {
      if (var1 instanceof Boolean) {
         var1 = (Boolean)var1 ? 1 : 0;
      }

      if (var2 instanceof Boolean) {
         var2 = (Boolean)var2 ? 1 : 0;
      }

      if (var1 instanceof Integer && var2 instanceof Integer) {
         switch (var0) {
            case q:
               return (Integer)var1 + (Integer)var2;
            case RF:
               return (Integer)var1 - (Integer)var2;
            case xK:
               return (Integer)var1 * (Integer)var2;
            case Dw:
               return (Integer)var1 / (Integer)var2;
            case Uv:
               return (Integer)var1 % (Integer)var2;
            case oW:
               return (Integer)var1 << (Integer)var2;
            case gO:
               return (Integer)var1 >> (Integer)var2;
         }
      }

      throw new RuntimeException(Strings.ff("TBI: %s, %s, %s", var0, var1, var2));
   }

   static enum eo {
      q,
      RF,
      xK,
      Dw,
      Uv,
      oW,
      gO;
   }
}
