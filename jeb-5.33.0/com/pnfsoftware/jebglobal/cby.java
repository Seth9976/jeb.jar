package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.ArrayList1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class cby extends bwj {
   private int A;
   static Object[] pC = new Object[]{
      "($0 & $1) + ((~ $1) & $0)",
      "$0",
      1,
      "($0 & $1) + ($0 ^ $1)",
      "$0 | $1",
      0,
      "(($0 & $1) << 1) + ($0 ^ $1)",
      "$0 + $1",
      0,
      "($0 & $1) + ($0 | $1)",
      "$0 + $1",
      0,
      "($0 & $1) - (~($0 | $1))",
      "($0 + $1) + 1",
      0,
      "(($0 & $1) << 1) - (~($0 ^ $1))",
      "($0 + $1) + 1",
      0,
      "($0 & $1) - ((~ $1) | $0)",
      "$1 + 1",
      1,
      "(~ (($0 & $1) << 1)) - ((~($0 & $1)) << 1)",
      "1",
      1,
      "(~ (($0 & $1) << 1)) + (~ ((~($0 & $1)) << 1))",
      "0",
      1,
      "(((~ $1) & $0) << 1) - ($0 ^ $1)",
      "$0 - $1",
      0,
      "(((~ $1) & $0) << 1) + (~($0 ^ $1))",
      "($0 - $1) - 1",
      0,
      "((~ $1) & $0) + ((~ $1) | $0)",
      "($0 - $1) - 1",
      0,
      "((~ $1) & $0) - (~($0 & $1))",
      "$0 + 1",
      1,
      "($0 ^ $1) - (~ (($0 & $1) << 1))",
      "($0 + $1) + 1",
      0,
      "(~ (($0 ^ $1) << 1)) - ((~($0 ^ $1)) << 1)",
      "1",
      1,
      "(~ (($0 ^ $1) << 1)) + (~ ((~($0 ^ $1)) << 1))",
      "0",
      1,
      "($0 ^ $1) - (~ (((~ $1) | $0) << 1))",
      "($0 - $1) - 1",
      0,
      "($0 ^ $1) + (~ ((~($0 & $1)) << 1))",
      "($0 + $1) + 1",
      0,
      "($0 | $1) - ($0 & $1)",
      "$0 ^ $1",
      0,
      "($0 | $1) - ((~ $1) & $0)",
      "$1",
      1,
      "($0 | $1) - ($0 ^ $1)",
      "$0 & $1",
      0,
      "(($0 | $1) << 1) - ($0 ^ $1)",
      "$0 + $1",
      0,
      "(~ (($0 | $1) << 1)) - ((~($0 | $1)) << 1)",
      "1",
      1,
      "(~ (($0 | $1) << 1)) + (~ ((~($0 | $1)) << 1))",
      "0",
      1,
      "(($0 | $1) << 1) + (~($0 ^ $1))",
      "($0 + $1) - 1",
      0,
      "($0 | $1) + ((~ $1) | $0)",
      "$0 - 1",
      1,
      "($0 | $1) - (~($0 & $1))",
      "($0 + $1) + 1",
      0,
      "(~ ((~($0 | $1)) << 1)) - ($0 ^ $1)",
      "($0 + $1) + 1",
      0,
      "(~($0 | $1)) - (~ (($0 | $1) << 1))",
      "$0 | $1",
      0,
      "(~ ((~($0 | $1)) << 1)) - (($0 | $1) << 1)",
      "1",
      1,
      "(~ ((~($0 | $1)) << 1)) + (~($0 ^ $1))",
      "$0 + $1",
      0,
      "(~($0 ^ $1)) - (~ (((~ $1) & $0) << 1))",
      "$0 - $1",
      0,
      "(~($0 ^ $1)) - (~ (($0 ^ $1) << 1))",
      "$0 ^ $1",
      0,
      "(~ ((~($0 ^ $1)) << 1)) - (($0 ^ $1) << 1)",
      "1",
      1,
      "(~($0 ^ $1)) - (~ (($0 | $1) << 1))",
      "$0 + $1",
      0,
      "(~($0 ^ $1)) - (~($0 | $1))",
      "$0 & $1",
      0,
      "(~($0 ^ $1)) - ((~($0 | $1)) << 1)",
      "($0 + $1) + 1",
      0,
      "((~ $1) | $0) - (~($0 | $1))",
      "$0",
      1,
      "(((~ $1) | $0) << 1) - (~($0 ^ $1))",
      "($0 - $1) - 1",
      0,
      "(~($0 & $1)) - (~ (($0 & $1) << 1))",
      "$0 & $1",
      0,
      "(~ ((~($0 & $1)) << 1)) - (($0 & $1) << 1)",
      "1",
      1,
      "(~($0 & $1)) - (~($0 | $1))",
      "$0 ^ $1",
      0
   };
   private static final List kS = A();

   public cby() {
      this(null);
      this.addTag("slow");
   }

   @Override
   public int perform() {
      return super.perform();
   }

   public cby(String var1) {
      this.addTag("deobfuscator");
      bpl.wS(this);
      if (var1 == null) {
         this.A = -1;
      } else {
         String var2 = var1.replace(" ", "");
         byte var3 = 0;

         for (int var4 = 0; var3 < pC.length; var4++) {
            if (var2.equals(((String)pC[var3]).replace(" ", ""))) {
               this.A = var4;
               return;
            }

            var3 += 3;
         }

         throw new RuntimeException();
      }
   }

   @Override
   public Collection pC() {
      return (Collection)(this.A >= 0 ? new ArrayList1((bso.Av)kS.get(this.A)) : kS);
   }

   private static List A() {
      ArrayList var0 = new ArrayList();
      byte var1 = 0;

      for (int var2 = 0; var1 < pC.length; var2++) {
         String var3 = (String)pC[var1];
         String var4 = (String)pC[var1 + 1];
         boolean var5 = ((Integer)pC[var1 + 2] & 1) != 0;
         var0.add(pC(var2, var3, var4, var5));
         var1 += 3;
      }

      return var0;
   }

   private static bso.Av pC(int var0, String var1, String var2, boolean var3) {
      short var4 = 2;
      if (var3) {
         var4 |= 256;
      }

      return bso.Av.pC("#" + var0).pC(var1).A(var2).A(var4);
   }
}
