package com.pnfsoftware.jeb.corei.parsers.simatic;

import com.pnfsoftware.jeb.core.units.code.simatic.S7;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class KZ {
   private static volatile KZ q;
   private Map RF = new HashMap();

   public static KZ q() {
      if (q == null) {
         synchronized (KZ.class) {
            if (q == null) {
               try (InputStream var1 = S7Identifier.class.getResourceAsStream("assets/simatic-s7-stl-stdlibs.txt")) {
                  String var2 = Strings.decodeUTF8(IO.readInputStream(var1));
                  q = new KZ(var2);
               } catch (IOException var7) {
                  q = new KZ();
               }
            }
         }
      }

      return q;
   }

   private KZ() {
   }

   private KZ(String var1) {
      for (String var5 : Strings.splitLines(var1)) {
         var5 = var5.trim();
         if (!var5.isEmpty() && !var5.startsWith("#")) {
            int var6 = var5.indexOf(58);
            if (var6 >= 0) {
               String var7 = var5.substring(0, var6);
               int var8 = var7.indexOf(32);
               if (var8 >= 0) {
                  String var9 = var7.substring(0, var8);
                  String var10 = var7.substring(var8 + 1);

                  try {
                     KZ.eo var11 = new KZ.eo(var9, var10);
                     this.RF.put(var9, var11);
                  } catch (Exception var12) {
                  }
               }
            }
         }
      }
   }

   public String q(S7.BlockType var1, int var2) {
      String var3 = "" + var1 + var2;
      KZ.eo var4 = (KZ.eo)this.RF.get(var3);
      return var4 == null ? null : var4.Dw;
   }

   static class eo {
      S7.BlockType q;
      int RF;
      String xK;
      String Dw;

      public eo(String var1, String var2) {
         if (var1 != null && var2 != null) {
            this.xK = var1;
            this.Dw = var2;
            if (var1.startsWith("FC")) {
               this.q = S7.BlockType.FC;
               this.RF = Integer.parseInt(var1.substring(2));
            } else if (var1.startsWith("SFC")) {
               this.q = S7.BlockType.SFC;
               this.RF = Integer.parseInt(var1.substring(3));
            } else {
               if (!var1.startsWith("SFB")) {
                  throw new IllegalArgumentException();
               }

               this.q = S7.BlockType.SFB;
               this.RF = Integer.parseInt(var1.substring(3));
            }
         } else {
            throw new IllegalArgumentException();
         }
      }
   }
}
