package com.pnfsoftware.jeb.util.format;

import java.math.BigInteger;
import java.util.Collection;

public class BigIntegers {
   public static BigInteger max(Collection var0) {
      BigInteger var1 = null;

      for (BigInteger var3 : var0) {
         if (var1 == null || var3.compareTo(var1) > 0) {
            var1 = var3;
         }
      }

      return var1;
   }

   public static BigInteger min(Collection var0) {
      BigInteger var1 = null;

      for (BigInteger var3 : var0) {
         if (var1 == null || var3.compareTo(var1) < 0) {
            var1 = var3;
         }
      }

      return var1;
   }
}
