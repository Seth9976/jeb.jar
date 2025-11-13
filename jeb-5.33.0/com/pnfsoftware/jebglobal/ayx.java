package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public abstract class ayx extends aye {
   @SerId(1)
   String A;

   protected ayx(ayy var1, String var2, String var3) {
      super(var1, var2);
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else if (var3.endsWith("*")) {
         throw new RuntimeException("Unexpected reference type");
      } else {
         this.E = var2;
         this.sY = var2;
         this.A = var3;
      }
   }

   @Override
   public String pC(boolean var1, String var2) {
      String var3;
      if (!var1) {
         var3 = this.A;
      } else {
         this.vP();
         Object var4 = this;
         String var5 = "";

         while (true) {
            ayp var6 = this.kS().kS((auo)var4);
            if (var6 == null || var6.isRootPackage()) {
               var3 = var5 + this.getName(var1);
               break;
            }

            var5 = var6.getName(var1) + "::" + var5;
            var4 = var6;
         }
      }

      if (var2 != null && var2.length() != 0) {
         char var7 = var2.charAt(0);
         return var7 != '*' && var7 != '[' ? var3 + " " + var2 : var3 + var2;
      } else {
         return var3;
      }
   }
}
