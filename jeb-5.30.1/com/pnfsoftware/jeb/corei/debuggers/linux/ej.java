package com.pnfsoftware.jeb.corei.debuggers.linux;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ej {
   public static final int q = 4;
   public static final int RF = 2;
   public static final int xK = 1;
   public static final int Dw = 16;
   private static Pattern JY = Pattern.compile("([0-9a-f]*)\\-([0-9a-f]*)\\s+(....)\\s+([0-9a-f]*)\\s+([0-9a-f]*):([0-9a-f]*)\\s+([0-9a-f]*)(.*)", 2);
   public long Uv;
   public long oW;
   public int gO;
   public long nf;
   public int gP;
   public int za;
   public int lm;
   public String zz;

   public static ej q(String var0) {
      Matcher var1 = JY.matcher(var0.trim());
      if (!var1.matches()) {
         return null;
      } else {
         ej var2 = new ej();

         try {
            var2.Uv = Long.parseLong(var1.group(1), 16);
            var2.oW = Long.parseLong(var1.group(2), 16);
            if (var2.Uv >= var2.oW) {
               return null;
            } else {
               String var3 = var1.group(3);
               if (var3.charAt(0) == 'r') {
                  var2.gO |= 4;
               }

               if (var3.charAt(1) == 'w') {
                  var2.gO |= 2;
               }

               if (var3.charAt(2) == 'x') {
                  var2.gO |= 1;
               }

               if (var3.charAt(3) == 's') {
                  var2.gO |= 16;
               }

               var2.nf = Long.parseLong(var1.group(4), 16);
               var2.gP = Integer.parseInt(var1.group(5), 16);
               var2.za = Integer.parseInt(var1.group(6), 16);
               var2.lm = Integer.parseInt(var1.group(7), 16);
               String var4 = var1.group(8).trim();
               if (!var4.isEmpty()) {
                  var2.zz = var4;
               }

               return var2;
            }
         } catch (NumberFormatException var5) {
            return null;
         }
      }
   }

   public static List RF(String var0) {
      String[] var1 = Strings.splitLines(var0);
      ArrayList var2 = new ArrayList(var1.length);

      for (String var6 : var1) {
         ej var7 = q(var6);
         if (var7 != null) {
            var2.add(var7);
         }
      }

      return var2;
   }

   public static String q(int var0) {
      String var1 = "";
      var1 = var1 + ((var0 & 4) != 0 ? 114 : 45);
      var1 = var1 + ((var0 & 2) != 0 ? 119 : 45);
      var1 = var1 + ((var0 & 1) != 0 ? 120 : 45);
      return var1 + ((var0 & 16) != 0 ? 115 : 112);
   }

   public long q() {
      return this.Uv;
   }

   public long RF() {
      return this.oW;
   }

   public int xK() {
      return this.gO;
   }

   public Long Dw() {
      return this.nf;
   }

   public String Uv() {
      return this.zz;
   }

   @Override
   public String toString() {
      String var1 = Strings.ff("%x-%x %s %x %x:%x %x", this.Uv, this.oW, q(this.gO), this.nf, this.gP, this.za, this.lm);
      if (this.zz != null) {
         var1 = var1 + " " + this.zz;
      }

      return var1;
   }
}
