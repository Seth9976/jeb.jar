package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class azc {
   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   static Object pC(azc.Av var0, Object var1, Object var2) {
      if (var1 instanceof Boolean) {
         var1 = (Boolean)var1 ? 1 : 0;
      }

      if (var2 instanceof Boolean) {
         var2 = (Boolean)var2 ? 1 : 0;
      }

      if (var1 instanceof Integer && var2 instanceof Integer) {
         switch (var0) {
            case pC:
               return (Integer)var1 + (Integer)var2;
            case A:
               return (Integer)var1 - (Integer)var2;
            case kS:
               return (Integer)var1 * (Integer)var2;
            case wS:
               return (Integer)var1 / (Integer)var2;
            case UT:
               return (Integer)var1 % (Integer)var2;
            case E:
               return (Integer)var1 << (Integer)var2;
            case sY:
               return (Integer)var1 >> (Integer)var2;
         }
      }

      throw new RuntimeException(Strings.ff("TBI: %s, %s, %s", var0, var1, var2));
   }

   static enum Av {
      pC,
      A,
      kS,
      wS,
      UT,
      E,
      sY;
   }
}
