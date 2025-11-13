package com.pnfsoftware.jeb.corei.parsers.asm.type.axgen;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class DH {
   private List pC;
   private int A;
   private String kS;
   private int wS;
   private Map UT = new HashMap();

   public DH(File var1) throws IOException {
      if (var1 != null && var1.canRead()) {
         this.pC = IO.readLines(var1);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public Map pC() {
      return this.UT;
   }

   public void A() {
      this.UT.clear();
      boolean var1 = false;

      for (String var3 : this.pC) {
         this.A++;
         int var4 = var3.indexOf(35);
         if (var4 >= 0) {
            var3 = var3.substring(0, var4);
         }

         var3 = var3.stripTrailing();
         this.wS = 0;
         if (var1) {
            this.kS = this.kS.substring(0, this.kS.length() - 1) + var3;
         } else {
            this.kS = var3;
         }

         if (var3.endsWith("\\")) {
            var1 = true;
         } else {
            var1 = false;
            Av var5 = null;
            int var6 = 0;

            while (true) {
               this.kS();
               if (this.wS >= this.kS.length()) {
                  if (var5 != null && !var5.kS()) {
                     Av var13 = (Av)this.UT.put(var5.pC, var5);
                     this.pC(var13 == null, "Duplicate entry for function: " + var5.pC);
                  }
                  break;
               }

               if (++var6 == 1) {
                  if (pC(this.kS, this.wS, ':') < 0) {
                     break;
                  }

                  String var7 = this.pC(':', false);
                  var5 = new Av(var7);
               } else {
                  String var12 = this.pC(',', true);
                  int var8 = var12.indexOf(61);
                  this.pC(var8 >= 0, "Missing value in key-value pair (= not found)");
                  String var9 = var12.substring(0, var8).trim();
                  String var10 = var12.substring(var8 + 1).trim();
                  this.pC(var9, var10, var5);
               }
            }
         }
      }
   }

   void pC(String var1) {
      throw new RuntimeException(Strings.ff("Parsing failed: %s:\n%s\n%s^", var1, this.pC.get(this.A - 1), Strings.generate(' ', this.wS)));
   }

   void pC(boolean var1) {
      if (!var1) {
         throw new RuntimeException(Strings.ff("Parsing failed:\n%s\n%s^", this.pC.get(this.A - 1), Strings.generate(' ', this.wS)));
      }
   }

   void pC(boolean var1, String var2) {
      if (!var1) {
         throw new RuntimeException(Strings.ff("Parsing failed: %s:\n%s\n%s^", var2, this.pC.get(this.A - 1), Strings.generate(' ', this.wS)));
      }
   }

   void kS() {
      int var1 = pC(this.kS, this.wS);
      this.wS = var1;
   }

   String pC(char var1, boolean var2) {
      int var3 = pC(this.kS, this.wS, var1);
      if (var3 < 0 && var2) {
         var3 = this.kS.length();
      }

      this.pC(var3 >= 0, "Missing separator");
      String var4 = this.kS.substring(this.wS, var3);
      this.wS = var3 < this.kS.length() ? var3 + 1 : var3;
      return var4;
   }

   void pC(String var1, String var2, Av var3) {
      if (var1.equals("rn")) {
         var3.A = var2;
      } else if (var1.equals("rv")) {
         var3.kS = this.pC(0, var2);
      } else {
         if (!var1.startsWith("a")) {
            throw new RuntimeException("Unknown key: " + var1);
         }

         char var4 = var1.charAt(var1.length() - 1);
         if (Character.isDigit(var4)) {
            var4 = 0;
         }

         int var5;
         if (var4 == 0) {
            var5 = this.UT(var1.substring(1));
         } else {
            var5 = this.UT(var1.substring(1, var1.length() - 1));
         }

         cq var6 = var3.A(var5);
         switch (var4) {
            case 'm':
               var6.kS = this.A(var5, var2);
               break;
            case 'n':
               var6.pC = var2;
               break;
            case 'v':
            default:
               var6.A = this.pC(var5, var2);
         }
      }
   }

   zp pC(int var1, String var2) {
      LinkedHashSet var5 = null;
      long var3;
      if (!var2.startsWith("anyof(") && !var2.startsWith("mixof(")) {
         if (var2.startsWith("sameas(")) {
            this.pC(var2.charAt(var2.length() - 1) == ')');
            String[] var11 = var2.substring(var2.indexOf(40) + 1, var2.length() - 1).split(";");
            this.pC(var11.length == 1 || var11.length == 2, "sameas() must have 1 or 2 arguments");
            String var12 = var11[0];
            int var13 = var1;
            if (var11.length == 2) {
               this.pC(var11[1].startsWith("a"));
               var13 = this.UT(var11[1].substring(1));
            }

            if (this.UT.get(var12) == null
               || ((Av)this.UT.get(var12)).wS == null
               || ((Av)this.UT.get(var12)).wS.get(var13) == null
               || ((cq)((Av)this.UT.get(var12)).wS.get(var13)).A == null) {
               this.pC(Strings.ff("missing entry referenced by: routine:'%s'(a%d)", var12, var13));
            }

            return ((cq)((Av)this.UT.get(var12)).wS.get(var13)).A;
         }

         var3 = this.kS(var2);
      } else {
         var3 = var2.startsWith("anyof(") ? 3L : 4L;
         this.pC(var2.charAt(var2.length() - 1) == ')');
         String[] var6 = var2.substring(var2.indexOf(40) + 1, var2.length() - 1).split(";");
         var5 = new LinkedHashSet();

         for (String var10 : var6) {
            var10 = var10.trim();
            this.sY(var10);
            if (var10.length() > 0 && !var5.add(var10)) {
               this.pC("Duplicate value in set: " + var10);
            }
         }

         this.pC(!var5.isEmpty(), "A set of litterals cannot be empty");
      }

      return new zp(var3, var5);
   }

   private void sY(String var1) {
      for (int var2 = 0; var2 < var1.length(); var2++) {
         char var3 = var1.charAt(var2);
         if (var3 != '_' && (var3 < 'a' || var3 > 'z') && (var3 < 'A' || var3 > 'Z') && (var2 < 1 || var3 < '0' || var3 > '9')) {
            throw new RuntimeException(Strings.ff("Illegal literal name: '%s':\n%s\n%s^", var1, this.pC.get(this.A - 1), Strings.generate(' ', this.wS)));
         }
      }
   }

   bO A(int var1, String var2) {
      String[] var3 = var2.split(";");
      int var4;
      int var5;
      int var6;
      if (var3.length == 1) {
         int[] var7 = new int[1];
         var4 = this.pC(var2, var7);
         String var8 = var2.substring(var7[0]);
         if (Character.isDigit(var8.charAt(0))) {
            var5 = this.kS(var8);
            var6 = this.kS(Strings.ff("sizeofpt(a%d)", var1));
         } else if (var8.equals("str")) {
            var5 = this.kS(Strings.ff("strlenz(a%d)", var1));
            var6 = this.kS("1");
         } else if (var8.equals("ustr")) {
            var5 = this.kS(Strings.ff("ustrlenz(a%d)", var1));
            var6 = this.kS("2");
         } else {
            if (!var8.equals("func")) {
               this.pC("Illegal mem-spec: " + var2);
               throw new RuntimeException();
            }

            var5 = this.kS("1");
            var6 = this.kS("1");
         }
      } else if (var3.length == 2) {
         var4 = this.A(var3[0]);
         var5 = this.kS(var3[1]);
         var6 = this.kS("1");
      } else {
         if (var3.length != 3) {
            this.pC("Illegal mem-spec: " + var2);
            throw new RuntimeException();
         }

         var4 = this.A(var3[0]);
         var5 = this.kS(var3[1]);
         var6 = this.kS(var3[2]);
      }

      long var9 = var4 | (long)var5 << 16 | (long)var6 << 32;
      return new bO(var9);
   }

   int A(String var1) {
      int[] var2 = new int[1];
      int var3 = this.pC(var1, var2);
      this.pC(var2[0] == var1.length());
      return var3;
   }

   int pC(String var1, int[] var2) {
      if (var1.startsWith("rw")) {
         var2[0] = 2;
         return 3;
      } else if (var1.startsWith("r")) {
         var2[0] = 1;
         return 1;
      } else if (var1.startsWith("w")) {
         var2[0] = 1;
         return 2;
      } else {
         this.pC("Invalid access type");
         throw new RuntimeException();
      }
   }

   int kS(String var1) {
      char var2 = var1.charAt(0);
      if (Character.isDigit((int)var2)) {
         int var9 = this.UT(var1);
         this.pC(var9 < 8192);
         return 0 | var9 << 3;
      } else if (var2 == 'a' && Character.isDigit(var1.charAt(1))) {
         int var8 = this.wS(var1);
         this.pC(var8 < 32);
         return 1 | var8 << 3;
      } else {
         int var5 = var1.indexOf(40);
         int var3;
         int var4;
         if (var5 >= 0) {
            this.pC(var1.charAt(var1.length() - 1) == ')');
            String var6 = var1.substring(0, var5);
            var3 = this.E(var6);
            String var7 = var1.substring(var5 + 1, var1.length() - 1);
            var4 = this.wS(var7);
         } else {
            var3 = this.E(var1);
            var4 = 0;
         }

         return 2 | var4 << 3 | var3 << 8;
      }
   }

   int wS(String var1) {
      this.pC(var1.charAt(0) == 'a');
      int var2 = this.UT(var1.substring(1));
      this.pC(var2 < 32);
      return var2;
   }

   int UT(String var1) {
      try {
         return Integer.parseInt(var1);
      } catch (NumberFormatException var2) {
         this.pC("Not a number: " + var1);
         throw new RuntimeException();
      }
   }

   int E(String var1) {
      switch (var1) {
         case "sizeofpt":
            return 1;
         case "sizeof_int":
            return 4;
         case "sizeof_long":
            return 5;
         case "sizeof_longlong":
            return 6;
         case "strlen":
            return 2;
         case "strlenz":
            return 3;
         case "deref":
            return 7;
         case "ustrlen":
            return 8;
         case "ustrlenz":
            return 9;
         default:
            this.pC("Unknown helper: " + var1);
            throw new RuntimeException();
      }
   }

   static int pC(String var0, int var1) {
      while (var1 < var0.length()) {
         char var2 = var0.charAt(var1);
         if (Character.isWhitespace(var2)) {
            var1++;
            continue;
         }
         break;
      }

      return var1;
   }

   static int pC(String var0, int var1, char var2) {
      while (var1 < var0.length()) {
         if (var0.charAt(var1) == var2) {
            return var1;
         }

         var1++;
      }

      return -1;
   }
}
