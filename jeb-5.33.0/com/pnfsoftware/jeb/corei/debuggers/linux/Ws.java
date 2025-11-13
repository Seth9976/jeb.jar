package com.pnfsoftware.jeb.corei.debuggers.linux;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ws {
   private static Pattern ld = Pattern.compile("([0-9a-f]*)\\-([0-9a-f]*)\\s+(....)\\s+([0-9a-f]*)\\s+([0-9a-f]*):([0-9a-f]*)\\s+([0-9a-f]*)(.*)", 2);
   public long pC;
   public long A;
   public int kS;
   public long wS;
   public int UT;
   public int E;
   public int sY;
   public String ys;

   public static Ws pC(String var0) {
      Matcher var1 = ld.matcher(var0.trim());
      if (!var1.matches()) {
         return null;
      } else {
         Ws var2 = new Ws();

         try {
            var2.pC = Long.parseLong(var1.group(1), 16);
            var2.A = Long.parseLong(var1.group(2), 16);
            if (var2.pC >= var2.A) {
               return null;
            } else {
               String var3 = var1.group(3);
               if (var3.charAt(0) == 'r') {
                  var2.kS |= 4;
               }

               if (var3.charAt(1) == 'w') {
                  var2.kS |= 2;
               }

               if (var3.charAt(2) == 'x') {
                  var2.kS |= 1;
               }

               if (var3.charAt(3) == 's') {
                  var2.kS |= 16;
               }

               var2.wS = Long.parseLong(var1.group(4), 16);
               var2.UT = Integer.parseInt(var1.group(5), 16);
               var2.E = Integer.parseInt(var1.group(6), 16);
               var2.sY = Integer.parseInt(var1.group(7), 16);
               String var4 = var1.group(8).trim();
               if (!var4.isEmpty()) {
                  var2.ys = var4;
               }

               return var2;
            }
         } catch (NumberFormatException var5) {
            return null;
         }
      }
   }

   public static String pC(int var0) {
      String var1 = "";
      var1 = var1 + ((var0 & 4) != 0 ? 114 : 45);
      var1 = var1 + ((var0 & 2) != 0 ? 119 : 45);
      var1 = var1 + ((var0 & 1) != 0 ? 120 : 45);
      return var1 + ((var0 & 16) != 0 ? 115 : 112);
   }

   @Override
   public String toString() {
      String var1 = Strings.ff("%x-%x %s %x %x:%x %x", this.pC, this.A, pC(this.kS), this.wS, this.UT, this.E, this.sY);
      if (this.ys != null) {
         var1 = var1 + " " + this.ys;
      }

      return var1;
   }
}
