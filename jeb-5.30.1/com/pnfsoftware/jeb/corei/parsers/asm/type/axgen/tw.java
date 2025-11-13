package com.pnfsoftware.jeb.corei.parsers.asm.type.axgen;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class tw {
   private List q;
   private int RF;
   private String xK;
   private int Dw;
   private Map Uv = new HashMap();

   public tw(String var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.q = new ArrayList(Arrays.asList(Strings.splitLines(var1)));
      }
   }

   public tw(File var1) throws IOException {
      if (var1 != null && var1.canRead()) {
         this.q = IO.readLines(var1);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public Map q() {
      return this.Uv;
   }

   public void RF() {
      this.Uv.clear();
      boolean var1 = false;

      for (String var3 : this.q) {
         this.RF++;
         int var4 = var3.indexOf(35);
         if (var4 >= 0) {
            var3 = var3.substring(0, var4);
         }

         var3 = var3.stripTrailing();
         this.Dw = 0;
         if (var1) {
            this.xK = this.xK.substring(0, this.xK.length() - 1) + var3;
         } else {
            this.xK = var3;
         }

         if (var3.endsWith("\\")) {
            var1 = true;
         } else {
            var1 = false;
            CU var5 = null;
            int var6 = 0;

            while (true) {
               this.xK();
               if (this.Dw >= this.xK.length()) {
                  if (var5 != null && !var5.Uv()) {
                     CU var13 = (CU)this.Uv.put(var5.q, var5);
                     this.q(var13 == null, "Duplicate entry for function: " + var5.q);
                  }
                  break;
               }

               if (++var6 == 1) {
                  if (q(this.xK, this.Dw, ':') < 0) {
                     break;
                  }

                  String var7 = this.q(':', false);
                  var5 = new CU(var7);
               } else {
                  String var12 = this.q(',', true);
                  int var8 = var12.indexOf(61);
                  this.q(var8 >= 0, "Missing value in key-value pair (= not found)");
                  String var9 = var12.substring(0, var8).trim();
                  String var10 = var12.substring(var8 + 1).trim();
                  this.q(var9, var10, var5);
               }
            }
         }
      }
   }

   void q(String var1) {
      throw new RuntimeException(Strings.ff("Parsing failed: %s:\n%s\n%s^", var1, this.q.get(this.RF - 1), Strings.generate(' ', this.Dw)));
   }

   void q(boolean var1) {
      if (!var1) {
         throw new RuntimeException(Strings.ff("Parsing failed:\n%s\n%s^", this.q.get(this.RF - 1), Strings.generate(' ', this.Dw)));
      }
   }

   void q(boolean var1, String var2) {
      if (!var1) {
         throw new RuntimeException(Strings.ff("Parsing failed: %s:\n%s\n%s^", var2, this.q.get(this.RF - 1), Strings.generate(' ', this.Dw)));
      }
   }

   void xK() {
      int var1 = q(this.xK, this.Dw);
      this.Dw = var1;
   }

   String q(char var1, boolean var2) {
      int var3 = q(this.xK, this.Dw, var1);
      if (var3 < 0 && var2) {
         var3 = this.xK.length();
      }

      this.q(var3 >= 0, "Missing separator");
      String var4 = this.xK.substring(this.Dw, var3);
      this.Dw = var3 < this.xK.length() ? var3 + 1 : var3;
      return var4;
   }

   void q(String var1, String var2, CU var3) {
      if (var1.equals("rn")) {
         var3.RF = var2;
      } else if (var1.equals("rv")) {
         var3.xK = this.q(0, var2);
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
            var5 = this.Uv(var1.substring(1));
         } else {
            var5 = this.Uv(var1.substring(1, var1.length() - 1));
         }

         iZ var6 = var3.RF(var5);
         switch (var4) {
            case 'm':
               var6.xK = this.RF(var5, var2);
               break;
            case 'n':
               var6.q = var2;
               break;
            case 'v':
            default:
               var6.RF = this.q(var5, var2);
         }
      }
   }

   qV q(int var1, String var2) {
      LinkedHashSet var5 = null;
      long var3;
      if (!var2.startsWith("anyof(") && !var2.startsWith("mixof(")) {
         if (var2.startsWith("sameas(")) {
            this.q(var2.charAt(var2.length() - 1) == ')');
            String[] var11 = var2.substring(var2.indexOf(40) + 1, var2.length() - 1).split(";");
            this.q(var11.length == 1 || var11.length == 2, "sameas() must have 1 or 2 arguments");
            String var12 = var11[0];
            int var13 = var1;
            if (var11.length == 2) {
               this.q(var11[1].startsWith("a"));
               var13 = this.Uv(var11[1].substring(1));
            }

            if (this.Uv.get(var12) == null
               || ((CU)this.Uv.get(var12)).Dw == null
               || ((CU)this.Uv.get(var12)).Dw.get(var13) == null
               || ((iZ)((CU)this.Uv.get(var12)).Dw.get(var13)).RF == null) {
               this.q(Strings.ff("missing entry referenced by: routine:'%s'(a%d)", var12, var13));
            }

            return ((iZ)((CU)this.Uv.get(var12)).Dw.get(var13)).RF;
         }

         var3 = this.xK(var2);
      } else {
         var3 = var2.startsWith("anyof(") ? 3L : 4L;
         this.q(var2.charAt(var2.length() - 1) == ')');
         String[] var6 = var2.substring(var2.indexOf(40) + 1, var2.length() - 1).split(";");
         var5 = new LinkedHashSet();

         for (String var10 : var6) {
            var10 = var10.trim();
            this.gO(var10);
            if (var10.length() > 0 && !var5.add(var10)) {
               this.q("Duplicate value in set: " + var10);
            }
         }

         this.q(!var5.isEmpty(), "A set of litterals cannot be empty");
      }

      return new qV(var3, var5);
   }

   private void gO(String var1) {
      for (int var2 = 0; var2 < var1.length(); var2++) {
         char var3 = var1.charAt(var2);
         if (var3 != '_' && (var3 < 'a' || var3 > 'z') && (var3 < 'A' || var3 > 'Z') && (var2 < 1 || var3 < '0' || var3 > '9')) {
            throw new RuntimeException(Strings.ff("Illegal literal name: '%s':\n%s\n%s^", var1, this.q.get(this.RF - 1), Strings.generate(' ', this.Dw)));
         }
      }
   }

   Nt RF(int var1, String var2) {
      String[] var3 = var2.split(";");
      int var4;
      int var5;
      int var6;
      if (var3.length == 1) {
         int[] var7 = new int[1];
         var4 = this.q(var2, var7);
         String var8 = var2.substring(var7[0]);
         if (Character.isDigit(var8.charAt(0))) {
            var5 = this.xK(var8);
            var6 = this.xK(Strings.ff("sizeofpt(a%d)", var1));
         } else if (var8.equals("str")) {
            var5 = this.xK(Strings.ff("strlenz(a%d)", var1));
            var6 = this.xK("1");
         } else if (var8.equals("ustr")) {
            var5 = this.xK(Strings.ff("ustrlenz(a%d)", var1));
            var6 = this.xK("2");
         } else {
            if (!var8.equals("func")) {
               this.q("Illegal mem-spec: " + var2);
               throw new RuntimeException();
            }

            var5 = this.xK("1");
            var6 = this.xK("1");
         }
      } else if (var3.length == 2) {
         var4 = this.RF(var3[0]);
         var5 = this.xK(var3[1]);
         var6 = this.xK("1");
      } else {
         if (var3.length != 3) {
            this.q("Illegal mem-spec: " + var2);
            throw new RuntimeException();
         }

         var4 = this.RF(var3[0]);
         var5 = this.xK(var3[1]);
         var6 = this.xK(var3[2]);
      }

      long var9 = var4 | (long)var5 << 16 | (long)var6 << 32;
      return new Nt(var9);
   }

   int RF(String var1) {
      int[] var2 = new int[1];
      int var3 = this.q(var1, var2);
      this.q(var2[0] == var1.length());
      return var3;
   }

   int q(String var1, int[] var2) {
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
         this.q("Invalid access type");
         throw new RuntimeException();
      }
   }

   int xK(String var1) {
      char var2 = var1.charAt(0);
      if (Character.isDigit((int)var2)) {
         int var9 = this.Uv(var1);
         this.q(var9 < 8192);
         return 0 | var9 << 3;
      } else if (var2 == 'a' && Character.isDigit(var1.charAt(1))) {
         int var8 = this.Dw(var1);
         this.q(var8 < 32);
         return 1 | var8 << 3;
      } else {
         int var5 = var1.indexOf(40);
         int var3;
         int var4;
         if (var5 >= 0) {
            this.q(var1.charAt(var1.length() - 1) == ')');
            String var6 = var1.substring(0, var5);
            var3 = this.oW(var6);
            String var7 = var1.substring(var5 + 1, var1.length() - 1);
            var4 = this.Dw(var7);
         } else {
            var3 = this.oW(var1);
            var4 = 0;
         }

         return 2 | var4 << 3 | var3 << 8;
      }
   }

   int Dw(String var1) {
      this.q(var1.charAt(0) == 'a');
      int var2 = this.Uv(var1.substring(1));
      this.q(var2 < 32);
      return var2;
   }

   int Uv(String var1) {
      try {
         return Integer.parseInt(var1);
      } catch (NumberFormatException var2) {
         this.q("Not a number: " + var1);
         throw new RuntimeException();
      }
   }

   int oW(String var1) {
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
            this.q("Unknown helper: " + var1);
            throw new RuntimeException();
      }
   }

   static int q(String var0, int var1) {
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

   static int q(String var0, int var1, char var2) {
      while (var1 < var0.length()) {
         if (var0.charAt(var1) == var2) {
            return var1;
         }

         var1++;
      }

      return -1;
   }

   static int q(String var0, int var1, char var2, char var3) {
      while (var1 < var0.length()) {
         char var4 = var0.charAt(var1);
         if (var4 == var2 || var4 == var3) {
            return var1;
         }

         var1++;
      }

      return -1;
   }
}
