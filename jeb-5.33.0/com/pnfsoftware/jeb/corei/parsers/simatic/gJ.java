package com.pnfsoftware.jeb.corei.parsers.simatic;

import com.pnfsoftware.jeb.core.units.code.simatic.S7;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class gJ {
   private static volatile gJ pC;
   private Map A = new HashMap();

   public static gJ pC() {
      if (pC == null) {
         synchronized (gJ.class) {
            if (pC == null) {
               try (InputStream var1 = S7Identifier.class.getResourceAsStream("assets/simatic-s7-stl-stdlibs.txt")) {
                  String var2 = Strings.decodeUTF8(IO.readInputStream(var1));
                  pC = new gJ(var2);
               } catch (IOException var7) {
                  pC = new gJ();
               }
            }
         }
      }

      return pC;
   }

   private gJ() {
   }

   private gJ(String var1) {
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
                     gJ.Av var11 = new gJ.Av(var9, var10);
                     this.A.put(var9, var11);
                  } catch (Exception var12) {
                  }
               }
            }
         }
      }
   }

   public String pC(S7.BlockType var1, int var2) {
      String var3 = "" + var1 + var2;
      gJ.Av var4 = (gJ.Av)this.A.get(var3);
      return var4 == null ? null : var4.wS;
   }

   static class Av {
      S7.BlockType pC;
      int A;
      String kS;
      String wS;

      public Av(String var1, String var2) {
         if (var1 != null && var2 != null) {
            this.kS = var1;
            this.wS = var2;
            if (var1.startsWith("FC")) {
               this.pC = S7.BlockType.FC;
               this.A = Integer.parseInt(var1.substring(2));
            } else if (var1.startsWith("SFC")) {
               this.pC = S7.BlockType.SFC;
               this.A = Integer.parseInt(var1.substring(3));
            } else {
               if (!var1.startsWith("SFB")) {
                  throw new IllegalArgumentException();
               }

               this.pC = S7.BlockType.SFB;
               this.A = Integer.parseInt(var1.substring(3));
            }
         } else {
            throw new IllegalArgumentException();
         }
      }
   }
}
