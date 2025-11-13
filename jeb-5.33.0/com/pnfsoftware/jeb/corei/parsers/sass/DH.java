package com.pnfsoftware.jeb.corei.parsers.sass;

import com.pnfsoftware.jeb.core.units.IAddressableUnit;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.util.base.TypedContent;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jebglobal.Bv;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DH extends Bv {
   private Map pC;

   public DH(INativeCodeUnit var1) {
      super(var1, "SASS Assembly Contribution");
      vi var2 = (vi)var1.getProcessor();
      this.pC = this.pC(var2.A());
   }

   private Map pC(String var1) {
      HashMap var2 = new HashMap();

      try {
         String var3 = uX.A(var1);

         try (InputStream var4 = SassPlugin.class.getResourceAsStream("opcodes/public_opcodes_" + var3 + ".txt")) {
            for (String var6 : IO.readLines(var4)) {
               var6 = var6.trim();
               if (!var6.startsWith("#")) {
                  int var7 = var6.indexOf(44);
                  if (var7 >= 0) {
                     String var8 = var6.substring(0, var7).trim().toUpperCase();
                     String var9 = var6.substring(var7 + 1).trim();
                     var2.put(var8, var9);
                  }
               }
            }
         }
      } catch (Exception var12) {
      }

      return var2;
   }

   @Override
   public TypedContent getItemInformation(IAddressableUnit var1, long var2, String var4, List var5) {
      if (var1 != this.getPrimaryTarget()) {
         return null;
      } else {
         long var6 = var2 & -72057594037927936L;
         if (var6 == -9007199254740992000L && var4 != null) {
            String var8 = var4;
            int var9 = var4.indexOf(46);
            if (var9 >= 0) {
               var8 = var4.substring(0, var9);
            }

            String var10 = (String)this.pC.get(var8);
            if (var10 == null) {
               var10 = "???";
            }

            return this.sY(var8 + "\n" + var10);
         } else {
            return null;
         }
      }
   }
}
