package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.collect.Sets;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class bxc {
   private static final ILogger Uv = GlobalLog.getLogger(bxc.class);
   public static final int q = 1;
   public static final int RF = 2;
   public static final int xK = 256;
   private static final int oW = 3;
   private static final int gO = 256;
   public static final bxc Dw = new bxc();
   private int nf;
   private static Set gP = Sets.createNonNulls('@', 'T', '$', 'V', '#', 'N');
   private static Set za = Sets.createNonNulls('+', '-', '*', '/', '%', '&', '|', '^', '~', '<', '>', '=', '!', '.', '_', '[', '?', ':');

   public bxc(int var1) {
      this.nf = var1;
   }

   public bxc() {
      this(0);
   }

   public int q() {
      return this.nf;
   }

   public bxc.eo q(bxc.eo var1) {
      if (var1.Dw) {
         throw new IllegalStateException("Pattern is already compiled");
      } else if (var1.Uv.isEmpty()) {
         throw new IllegalArgumentException("A pattern needs at least one input");
      } else {
         for (bxc.eo.eo var3 : var1.Uv) {
            var3.Uv = var3.Uv | this.nf & 3;

            try {
               this.q(var3);
            } catch (Exception var6) {
               Uv.catching(var6, S.L("Error compiling input pattern"));
               throw var6;
            }
         }

         bxc.eo.eo var7 = var1.oW;
         if (var7 != null) {
            var7.Uv = var7.Uv | this.nf & 256;

            try {
               this.q(var7);
            } catch (Exception var5) {
               Uv.catching(var5, S.L("Error compiling ouput pattern"));
               throw var5;
            }
         }

         var1.Dw = true;
         return var1;
      }
   }

   private void q(bxc.eo.eo var1) {
      int var2 = 0;
      int var3 = -1;
      int var4 = 0;
      int var5 = 0;
      int var6 = 0;
      int var7 = 0;

      for (String var11 : var1.oW) {
         byte var12 = 0;
         boolean var13 = false;
         int var14 = 0;

         while (var14 < var11.length()) {
            char var15 = Character.toUpperCase(var11.charAt(var14));
            if (!bxc.eo.lm.contains(var15)) {
               break;
            }

            if (var15 == '>') {
               var3 = var2;
               var14++;
            } else if (var15 == 'X') {
               var13 = true;
               var14++;
            } else {
               q("Unsupported special char", var11, var14);
            }
         }

         if (var13) {
            if (var5 == 0) {
               var4++;
            }
         } else {
            var5++;
         }

         bxc.CU var20 = new bxc.CU(var11);
         bxc.nI var16 = var20.q(var14);
         bxc.nI var17 = new bxc.nI();
         if (var16.Dw) {
            var17 = var20.q(var16.RF);
         }

         int var18 = var16.xK + var17.xK;
         if (var18 > var7) {
            var6 = var2;
            var7 = var18;
         }

         bxc.eo.eo.eo var19 = new bxc.eo.eo.eo(var12 | var1.Uv, var16.q, var17.q);
         var1.Dw.add(var19);
         var2++;
      }

      if (var3 < 0) {
         var3 = var6;
      }

      var1.q = var3;
      var1.RF = var4;
      var1.xK = var4 + var5;
   }

   private static void q(String var0, String var1, int var2) {
      var0 = Strings.ff("Illegal input pattern: %s", var0);
      if (var2 >= 0) {
         var0 = var0 + Strings.ff(":\n  %s\n  %s^", var1, Strings.generate(' ', var2));
      }

      throw new IllegalStateException(var0);
   }

   private static class CU {
      private final String q;
      private int RF;

      CU(String var1) {
         this.q = var1;
      }

      bxc.nI q() {
         return this.q(0);
      }

      bxc.nI q(int var1) {
         bxc.nI var2 = this.q(var1, 0, '\u0000');
         var2.xK = this.RF;
         return var2;
      }

      private bxc.nI q(int var1, int var2, char var3) {
         char var4 = 0;
         bxn var5 = null;
         ArrayList var6 = new ArrayList();
         boolean var7 = false;

         while (var1 < this.q.length()) {
            char var8 = this.q.charAt(var1++);
            if (!Character.isSpaceChar(var8)) {
               if (var8 == '(') {
                  bxc.nI var15 = this.q(var1, var2 + 1, ')');
                  this.q(var15);
                  var6.add(var15.q);
                  var1 = var15.RF;
               } else {
                  if (var8 == ')' || var8 == ']' || var8 == '}') {
                     var4 = var8;
                     break;
                  }

                  int var9 = 0;
                  if (var5 == null && var6.isEmpty() && var1 < this.q.length() && (var8 == '-' || var8 == '+') && Character.isDigit(this.q.charAt(var1))) {
                     var9 = var8 == '-' ? -1 : 1;
                     var8 = this.q.charAt(var1++);
                  }

                  if (var9 == 0 && bxc.za.contains(var8)) {
                     if (var5 == bxn.q) {
                        if (var8 != ':') {
                           this.Dw("Illegal ternary operator", var1);
                        }
                     } else if (var5 != null) {
                        switch (var5) {
                           case Dw:
                           case oW:
                           case lm:
                           case zz:
                           case JY:
                              break;
                           default:
                              this.Dw("Operator already present: " + var5, var1);
                        }
                     }

                     int var17 = this.q(this.q, var1);
                     String var11 = this.q.substring(var1 - 1, var17);
                     if (var11.equals("=")) {
                        if (var2 != 0) {
                           this.Dw("Illegal assignment", var1 - 1);
                        }

                        var7 = true;
                        break;
                     }

                     bxn var12 = this.xK(var11, var1);
                     if (var5 != null) {
                        if (var5 == bxn.Dw && var12 != bxn.Dw) {
                           this.Dw("Illegal additive sequence", var1);
                        }

                        if (var5 == bxn.oW && var12 != bxn.oW) {
                           this.Dw("Illegal multiplicative sequence", var1);
                        }

                        if (var5 == bxn.lm && var12 != bxn.lm) {
                           this.Dw("Illegal bitwise-and sequence", var1);
                        }

                        if (var5 == bxn.zz && var12 != bxn.zz) {
                           this.Dw("Illegal bitwise-or sequence", var1);
                        }

                        if (var5 == bxn.JY && var12 != bxn.JY) {
                           this.Dw("Illegal bitwise-xor sequence", var1);
                        }
                     }

                     var5 = var12;
                     var1 = var17;
                  } else if (Character.isDigit(var8)) {
                     bxc.nI var10 = this.q(var1 - 1, var9 < 0 ? -1 : 1);
                     this.q(var10);
                     var6.add(var10.q);
                     var1 = var10.RF;
                     this.RF++;
                  } else {
                     var8 = Character.toUpperCase(var8);
                     if (bxc.gP.contains(var8)) {
                        bxc.nI var16 = this.RF(var1 - 1);
                        this.q(var16);
                        var6.add(var16.q);
                        var1 = var16.RF;
                        this.RF += 2;
                     } else {
                        this.Dw("Character not parsed: " + var8, var1 - 1);
                     }
                  }
               }
            }
         }

         if (var4 != var3) {
            this.Dw("Illegal closing parenthesis", var1 - 1);
         }

         bxc.nI var14 = new bxc.nI();
         var14.RF = var1;
         var14.Dw = var7;
         if (!var7 && var5 != null) {
            if (var6.isEmpty()) {
               this.q("Missing operand(s)");
            }

            if (var5 == bxn.Uv && var6.size() == 1) {
               var5 = bxn.gP;
            }

            var14.q = new bxm(var5, (bxj[])var6.toArray(new bxj[var6.size()]));
         } else {
            if (var6.size() != 1) {
               this.q("Missing operand");
            }

            var14.q = (bxj)var6.get(0);
         }

         return var14;
      }

      private void q(bxc.nI var1) {
         int var2 = var1.RF;
         if (var2 < this.q.length() && this.q.charAt(var2) == '[') {
            throw new RuntimeException("Slices not supported by dexdec IR");
         }
      }

      int q(String var1, int var2) {
         while (var2 < var1.length()) {
            char var3 = var1.charAt(var2);
            if (var3 != '(' && var3 != ')' && !Character.isSpaceChar((int)var3) && var3 != '[' && var3 != ']' && var3 != '{' && var3 != '}') {
               var2++;
               continue;
            }
            break;
         }

         return var2;
      }

      int RF(String var1, int var2) {
         while (var2 < var1.length()) {
            char var3 = var1.charAt(var2);
            if (Character.isDigit((int)var3)) {
               var2++;
               continue;
            }
            break;
         }

         return var2;
      }

      bxc.nI q(int var1, int var2) {
         int var3 = this.q(this.q, var1);
         String var4 = this.q.substring(var1, var3);
         long var5 = Conversion.stringToLong(var4, -1L);
         if (var5 < 0L) {
            this.Dw("Illegal immediate: " + var4, var1 - 1);
         }

         var1 = var3;
         int var7 = -1;
         if (var3 < this.q.length() && this.q.charAt(var3) == '{') {
            var1 = var3 + 1;
            var3 = this.q(this.q, var1);
            if (var3 >= this.q.length() || this.q.charAt(var3) != '}') {
               this.Dw("", var3);
            }

            var7 = Conversion.stringToInt(this.q.substring(var1, var3), -1);
            var1 = var3 + 1;
         }

         return new bxc.nI(new bxl(var2 * var5, 0, var7), var1);
      }

      bxc.nI RF(int var1) {
         char var2 = this.q.charAt(var1++);
         int var3 = this.q(this.q, var1);
         if (var3 == var1) {
            this.Dw("Missing leaf identifier", var1);
         }

         byte var4;
         if (var2 == '$') {
            var4 = 7;
         } else if (var2 == '@') {
            if (!this.q.substring(var1, var3).startsWith("LASTBIT")) {
               throw new RuntimeException("Unknown @ marker for leaf");
            }

            var4 = 32;
            var1 += 7;
         } else if (var2 == 'T') {
            var4 = 3;
         } else if (var2 == 'N') {
            var4 = 4;
         } else if (var2 == 'V') {
            var4 = 2;
         } else {
            if (var2 != '#') {
               throw new RuntimeException("Unknown leaf identifier: " + var2);
            }

            var4 = 1;
         }

         int var5 = 0;
         char var6 = this.q.charAt(var1);
         if (Character.isDigit(var6)) {
            var3 = this.RF(this.q, var1);
            var5 = Integer.parseInt(this.q.substring(var1, var3));
            var1 = var3;
         } else {
            this.Dw("Missing leaf id", var1);
         }

         return new bxc.nI(new bxl(var5, 0, var4), var1);
      }

      bxn xK(String var1, int var2) {
         String var3 = var1.toLowerCase();
         switch (var3) {
            case "+":
               return bxn.Dw;
            case "-":
               return bxn.Uv;
            case "*":
               return bxn.oW;
            case "/":
               return bxn.gO;
            case "%":
               return bxn.nf;
            case "~":
               return bxn.za;
            case "&":
               return bxn.lm;
            case "|":
               return bxn.zz;
            case "^":
               return bxn.JY;
            case "!":
               return bxn.io;
            case "&&":
               return bxn.LK;
            case "||":
               return bxn.HF;
            case "==":
               return bxn.qa;
            case "!=":
               return bxn.Hk;
            case "<":
               return bxn.oQ;
            case "<=":
               return bxn.xW;
            case ">":
               return bxn.Me;
            case ">=":
               return bxn.PV;
            case "<<":
               return bxn.KT;
            case ">>":
               return bxn.Ef;
            case ">>>":
            case ">>u":
               return bxn.Gf;
            case "_ncadd":
               return bxn.RF;
            case "_ncsub":
               return bxn.xK;
            case "?":
            case ":":
               return bxn.q;
            default:
               throw new RuntimeException("TBI: Operator: " + var1);
         }
      }

      void q(String var1) {
         this.Dw(var1, -1);
      }

      void Dw(String var1, int var2) {
         bxc.q(var1, this.q, var2);
      }
   }

   public static class eo {
      static final char q = '>';
      static final char RF = 'X';
      private static final Set lm = Sets.createNonNulls('>', 'X');
      String xK;
      boolean Dw;
      List Uv = new ArrayList();
      bxc.eo.eo oW;
      bxh gO;
      bxi nf;
      Boolean gP;
      Boolean za;

      public static bxc.eo q(String... var0) {
         return q(0, var0);
      }

      public static bxc.eo q(int var0, String... var1) {
         return q().RF(var0, var1).za();
      }

      public static bxc.eo q() {
         return q(null);
      }

      public static bxc.eo q(String var0) {
         return new bxc.eo(var0);
      }

      private eo(String var1) {
         this.xK = var1;
      }

      public String RF() {
         return this.xK;
      }

      public bxc.eo RF(String var1) {
         return this.q(0, var1);
      }

      public bxc.eo q(int var1, String var2) {
         if (!this.Uv.isEmpty()) {
            bxc.Uv.warn(S.L("An IR input pattern is being overwritten! Was this intended?"));
            if (Licensing.isDebugBuild()) {
               throw new RuntimeException("IR pattern already set!");
            }
         }

         this.Uv.clear();
         return this.RF(0, var2);
      }

      public bxc.eo RF(String... var1) {
         return this.RF(0, var1);
      }

      public bxc.eo RF(int var1, String... var2) {
         this.Uv.add(new bxc.eo.eo(var1, var2));
         return this;
      }

      public boolean xK() {
         return this.gP;
      }

      public boolean Dw() {
         return this.za;
      }

      public bxc.eo.eo q(int var1) {
         return (bxc.eo.eo)this.Uv.get(var1);
      }

      public List Uv() {
         return this.Uv;
      }

      public bxc.eo xK(String... var1) {
         return this.xK(0, var1);
      }

      public bxc.eo xK(int var1, String... var2) {
         this.oW = new bxc.eo.eo(var1, var2);
         return this;
      }

      public boolean oW() {
         return this.oW != null;
      }

      public bxc.eo.eo gO() {
         return this.oW;
      }

      public bxc.eo q(bxh var1) {
         this.gO = var1;
         return this;
      }

      public bxh nf() {
         return this.gO;
      }

      public bxc.eo q(bxi var1) {
         this.nf = var1;
         return this;
      }

      public bxi gP() {
         return this.nf;
      }

      private Boolean zz() {
         if (this.gP != null) {
            throw new IllegalStateException();
         } else {
            for (bxc.eo.eo var2 : this.Uv) {
               boolean var3 = var2.Dw.size() == 1 && ((bxc.eo.eo.eo)var2.Dw.get(0)).xK == null;
               if (this.gP == null) {
                  this.gP = var3;
               } else if (this.gP != var3) {
                  return null;
               }
            }

            this.za = this.oW != null && this.oW.Dw.size() == 1 && ((bxc.eo.eo.eo)this.oW.Dw.get(0)).xK == null;
            return this.gP;
         }
      }

      public bxc.eo q(bxc var1) {
         if (var1 == null) {
            var1 = bxc.Dw;
         }

         bxc.eo var2 = var1.q(this);
         if (this.zz() == null) {
            throw new RuntimeException("Inconsistent inputs!");
         } else {
            return var2;
         }
      }

      public bxc.eo za() {
         return this.q(null);
      }

      public bxc.eo RF(int var1) {
         return this.q(new bxc(var1));
      }

      public boolean lm() {
         return this.Dw;
      }

      @Override
      public String toString() {
         if (!this.lm()) {
            return "(Not compiled)";
         } else {
            StringBuilder var1 = new StringBuilder();
            if (this.xK != null) {
               var1.append("Pattern: ").append(this.xK).append("\n");
            }

            int var2 = 0;

            for (bxc.eo.eo var4 : this.Uv) {
               if (this.Uv.size() == 1 && this.oW != null) {
                  var1.append("Input:\n");
               } else if (this.Uv.size() > 1) {
                  Strings.ff(var1, "Input #%d:\n", var2 + 1);
               }

               var1.append(var4).append("\n");
               var2++;
            }

            if (this.oW != null) {
               Strings.ff(var1, "Output:\n%s\n", this.oW);
            }

            if (var1.length() > 0) {
               int var5 = var1.length() - 1;
               if (var1.charAt(var5) == '\n') {
                  var1.deleteCharAt(var5);
               }
            }

            return var1.toString();
         }
      }

      public static class eo {
         private int Uv;
         private String[] oW;
         int q;
         int RF;
         int xK;
         List Dw = new ArrayList();

         eo(int var1, String... var2) {
            this.Uv = var1;
            this.oW = var2;
         }

         public int q() {
            return this.Uv;
         }

         public List RF() {
            return this.Dw;
         }

         public bxc.eo.eo.eo q(int var1) {
            return (bxc.eo.eo.eo)this.Dw.get(var1);
         }

         public int xK() {
            return this.Dw.size();
         }

         public bxc.eo.eo.eo Dw() {
            return (bxc.eo.eo.eo)this.Dw.get(this.q);
         }

         public int Uv() {
            return this.q;
         }

         public int oW() {
            return this.RF;
         }

         public int gO() {
            return this.xK - 1;
         }

         public int nf() {
            return this.xK - this.RF;
         }

         @Override
         public String toString() {
            if (this.Dw.isEmpty()) {
               return "(Pattern is not compiled)";
            } else {
               StringBuilder var1 = new StringBuilder();
               int var2 = 0;

               for (bxc.eo.eo.eo var4 : this.Dw) {
                  if (var2 >= 1) {
                     var1.append("\n");
                  }

                  if (this.Dw.size() >= 2) {
                     StringBuilder var5 = new StringBuilder();
                     if (this.q == var2) {
                        var5.append('>');
                     }

                     if (var2 < this.RF || var2 >= this.xK) {
                        var5.append('X');
                     }

                     var5.append(Strings.generate(' ', 3 - var5.length()));
                     var1.append((CharSequence)var5);
                  }

                  var1.append(var4);
                  var2++;
               }

               return var1.toString();
            }
         }

         static class eo {
            int q;
            bxj RF;
            bxj xK;

            eo(int var1, bxj var2) {
               this.q = var1;
               this.RF = var2;
               this.xK = null;
            }

            eo(int var1, bxj var2, bxj var3) {
               this.q = var1;
               this.RF = var2;
               this.xK = var3;
            }

            public int q() {
               return this.q;
            }

            public bxj RF() {
               return this.RF;
            }

            public bxj xK() {
               return this.xK;
            }

            @Override
            public String toString() {
               StringBuilder var1 = new StringBuilder();
               var1.append(this.RF);
               if (this.xK != null) {
                  var1.append(" = ").append(this.xK);
               }

               return var1.toString();
            }
         }
      }
   }

   private static class nI {
      bxj q;
      int RF;
      int xK;
      boolean Dw;

      public nI() {
      }

      public nI(bxj var1) {
         this.q = var1;
         this.RF = -1;
      }

      public nI(bxj var1, int var2) {
         this.q = var1;
         this.RF = var2;
      }

      @Override
      public String toString() {
         return this.q == null ? "?" : this.q.toString();
      }
   }
}
