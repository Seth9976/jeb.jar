package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.collect.Sets;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class bso {
   private static final ILogger A = GlobalLog.getLogger(bso.class);
   public static final bso pC = new bso();
   private int kS;
   private static Set wS = Sets.createNonNulls('@', 'T', '$', 'V', '#', 'N');
   private static Set UT = Sets.createNonNulls('+', '-', '*', '/', '%', '&', '|', '^', '~', '<', '>', '=', '!', '.', '_', '[', '?', ':');

   public bso(int var1) {
      this.kS = var1;
   }

   public bso() {
      this(0);
   }

   public bso.Av pC(bso.Av var1) {
      if (var1.A) {
         throw new IllegalStateException("Pattern is already compiled");
      } else if (var1.kS.isEmpty()) {
         throw new IllegalArgumentException("A pattern needs at least one input");
      } else {
         for (bso.Av.Av var3 : var1.kS) {
            var3.UT = var3.UT | this.kS & 3;

            try {
               this.pC(var3);
            } catch (Exception var6) {
               A.catching(var6, S.L("Error compiling input pattern"));
               throw var6;
            }
         }

         bso.Av.Av var7 = var1.wS;
         if (var7 != null) {
            var7.UT = var7.UT | this.kS & 256;

            try {
               this.pC(var7);
            } catch (Exception var5) {
               A.catching(var5, S.L("Error compiling ouput pattern"));
               throw var5;
            }
         }

         var1.A = true;
         return var1;
      }
   }

   private void pC(bso.Av.Av var1) {
      int var2 = 0;
      int var3 = -1;
      int var4 = 0;
      int var5 = 0;
      int var6 = 0;
      int var7 = 0;

      for (String var11 : var1.E) {
         byte var12 = 0;
         boolean var13 = false;
         int var14 = 0;

         while (var14 < var11.length()) {
            char var15 = Character.toUpperCase(var11.charAt(var14));
            if (!bso.Av.ld.contains(var15)) {
               break;
            }

            if (var15 == '>') {
               var3 = var2;
               var14++;
            } else if (var15 == 'X') {
               var13 = true;
               var14++;
            } else {
               pC("Unsupported special char", var11, var14);
            }
         }

         if (var13) {
            if (var5 == 0) {
               var4++;
            }
         } else {
            var5++;
         }

         bso.Sv var20 = new bso.Sv(var11);
         bso.K var16 = var20.pC(var14);
         bso.K var17 = new bso.K();
         if (var16.wS) {
            var17 = var20.pC(var16.A);
         }

         int var18 = var16.kS + var17.kS;
         if (var18 > var7) {
            var6 = var2;
            var7 = var18;
         }

         bso.Av.Av.Av var19 = new bso.Av.Av.Av(var12 | var1.UT, var16.pC, var17.pC);
         var1.wS.add(var19);
         var2++;
      }

      if (var3 < 0) {
         var3 = var6;
      }

      var1.pC = var3;
      var1.A = var4;
      var1.kS = var4 + var5;
   }

   private static void pC(String var0, String var1, int var2) {
      var0 = Strings.ff("Illegal input pattern: %s", var0);
      if (var2 >= 0) {
         var0 = var0 + Strings.ff(":\n  %s\n  %s^", var1, Strings.generate(' ', var2));
      }

      throw new IllegalStateException(var0);
   }

   public static class Av {
      private static final Set ld = Sets.createNonNulls('>', 'X');
      String pC;
      boolean A;
      List kS = new ArrayList();
      bso.Av.Av wS;
      bst UT;
      bsu E;
      Boolean sY;
      Boolean ys;

      public static bso.Av pC(String var0) {
         return new bso.Av(var0);
      }

      private Av(String var1) {
         this.pC = var1;
      }

      public bso.Av pC(String... var1) {
         return this.pC(0, var1);
      }

      public bso.Av pC(int var1, String... var2) {
         this.kS.add(new bso.Av.Av(var1, var2));
         return this;
      }

      public boolean pC() {
         return this.sY;
      }

      public boolean A() {
         return this.ys;
      }

      public bso.Av.Av pC(int var1) {
         return (bso.Av.Av)this.kS.get(var1);
      }

      public List kS() {
         return this.kS;
      }

      public bso.Av A(String... var1) {
         return this.A(0, var1);
      }

      public bso.Av A(int var1, String... var2) {
         this.wS = new bso.Av.Av(var1, var2);
         return this;
      }

      public bso.Av.Av wS() {
         return this.wS;
      }

      public bso.Av pC(bst var1) {
         this.UT = var1;
         return this;
      }

      public bst UT() {
         return this.UT;
      }

      private Boolean sY() {
         if (this.sY != null) {
            throw new IllegalStateException();
         } else {
            for (bso.Av.Av var2 : this.kS) {
               boolean var3 = var2.wS.size() == 1 && ((bso.Av.Av.Av)var2.wS.get(0)).kS == null;
               if (this.sY == null) {
                  this.sY = var3;
               } else if (this.sY != var3) {
                  return null;
               }
            }

            this.ys = this.wS != null && this.wS.wS.size() == 1 && ((bso.Av.Av.Av)this.wS.wS.get(0)).kS == null;
            return this.sY;
         }
      }

      public bso.Av pC(bso var1) {
         if (var1 == null) {
            var1 = bso.pC;
         }

         bso.Av var2 = var1.pC(this);
         if (this.sY() == null) {
            throw new RuntimeException("Inconsistent inputs!");
         } else {
            return var2;
         }
      }

      public bso.Av A(int var1) {
         return this.pC(new bso(var1));
      }

      public boolean E() {
         return this.A;
      }

      @Override
      public String toString() {
         if (!this.E()) {
            return "(Not compiled)";
         } else {
            StringBuilder var1 = new StringBuilder();
            if (this.pC != null) {
               var1.append("Pattern: ").append(this.pC).append("\n");
            }

            int var2 = 0;

            for (bso.Av.Av var4 : this.kS) {
               if (this.kS.size() == 1 && this.wS != null) {
                  var1.append("Input:\n");
               } else if (this.kS.size() > 1) {
                  Strings.ff(var1, "Input #%d:\n", var2 + 1);
               }

               var1.append(var4).append("\n");
               var2++;
            }

            if (this.wS != null) {
               Strings.ff(var1, "Output:\n%s\n", this.wS);
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

      public static class Av {
         private int UT;
         private String[] E;
         int pC;
         int A;
         int kS;
         List wS = new ArrayList();

         Av(int var1, String... var2) {
            this.UT = var1;
            this.E = var2;
         }

         public int pC() {
            return this.UT;
         }

         public bso.Av.Av.Av pC(int var1) {
            return (bso.Av.Av.Av)this.wS.get(var1);
         }

         public int A() {
            return this.wS.size();
         }

         public bso.Av.Av.Av kS() {
            return (bso.Av.Av.Av)this.wS.get(this.pC);
         }

         public int wS() {
            return this.pC;
         }

         public int UT() {
            return this.A;
         }

         public int E() {
            return this.kS - this.A;
         }

         @Override
         public String toString() {
            if (this.wS.isEmpty()) {
               return "(Pattern is not compiled)";
            } else {
               StringBuilder var1 = new StringBuilder();
               int var2 = 0;

               for (bso.Av.Av.Av var4 : this.wS) {
                  if (var2 >= 1) {
                     var1.append("\n");
                  }

                  if (this.wS.size() >= 2) {
                     StringBuilder var5 = new StringBuilder();
                     if (this.pC == var2) {
                        var5.append('>');
                     }

                     if (var2 < this.A || var2 >= this.kS) {
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

         static class Av {
            int pC;
            bsv A;
            bsv kS;

            Av(int var1, bsv var2, bsv var3) {
               this.pC = var1;
               this.A = var2;
               this.kS = var3;
            }

            @Override
            public String toString() {
               StringBuilder var1 = new StringBuilder();
               var1.append(this.A);
               if (this.kS != null) {
                  var1.append(" = ").append(this.kS);
               }

               return var1.toString();
            }
         }
      }
   }

   private static class K {
      bsv pC;
      int A;
      int kS;
      boolean wS;

      public K() {
      }

      public K(bsv var1, int var2) {
         this.pC = var1;
         this.A = var2;
      }

      @Override
      public String toString() {
         return this.pC == null ? "?" : this.pC.toString();
      }
   }

   private static class Sv {
      private final String pC;
      private int A;

      Sv(String var1) {
         this.pC = var1;
      }

      bso.K pC(int var1) {
         bso.K var2 = this.pC(var1, 0, '\u0000');
         var2.kS = this.A;
         return var2;
      }

      private bso.K pC(int var1, int var2, char var3) {
         char var4 = 0;
         bsz var5 = null;
         ArrayList var6 = new ArrayList();
         boolean var7 = false;

         while (var1 < this.pC.length()) {
            char var8 = this.pC.charAt(var1++);
            if (!Character.isSpaceChar(var8)) {
               if (var8 == '(') {
                  bso.K var15 = this.pC(var1, var2 + 1, ')');
                  this.pC(var15);
                  var6.add(var15.pC);
                  var1 = var15.A;
               } else {
                  if (var8 == ')' || var8 == ']' || var8 == '}') {
                     var4 = var8;
                     break;
                  }

                  int var9 = 0;
                  if (var5 == null && var6.isEmpty() && var1 < this.pC.length() && (var8 == '-' || var8 == '+') && Character.isDigit(this.pC.charAt(var1))) {
                     var9 = var8 == '-' ? -1 : 1;
                     var8 = this.pC.charAt(var1++);
                  }

                  if (var9 == 0 && bso.UT.contains(var8)) {
                     if (var5 == bsz.pC) {
                        if (var8 != ':') {
                           this.wS("Illegal ternary operator", var1);
                        }
                     } else if (var5 != null) {
                        switch (var5) {
                           case wS:
                           case E:
                           case oT:
                           case fI:
                           case WR:
                              break;
                           default:
                              this.wS("Operator already present: " + var5, var1);
                        }
                     }

                     int var17 = this.pC(this.pC, var1);
                     String var11 = this.pC.substring(var1 - 1, var17);
                     if (var11.equals("=")) {
                        if (var2 != 0) {
                           this.wS("Illegal assignment", var1 - 1);
                        }

                        var7 = true;
                        break;
                     }

                     bsz var12 = this.kS(var11, var1);
                     if (var5 != null) {
                        if (var5 == bsz.wS && var12 != bsz.wS) {
                           this.wS("Illegal additive sequence", var1);
                        }

                        if (var5 == bsz.E && var12 != bsz.E) {
                           this.wS("Illegal multiplicative sequence", var1);
                        }

                        if (var5 == bsz.oT && var12 != bsz.oT) {
                           this.wS("Illegal bitwise-and sequence", var1);
                        }

                        if (var5 == bsz.fI && var12 != bsz.fI) {
                           this.wS("Illegal bitwise-or sequence", var1);
                        }

                        if (var5 == bsz.WR && var12 != bsz.WR) {
                           this.wS("Illegal bitwise-xor sequence", var1);
                        }
                     }

                     var5 = var12;
                     var1 = var17;
                  } else if (Character.isDigit(var8)) {
                     bso.K var10 = this.pC(var1 - 1, var9 < 0 ? -1 : 1);
                     this.pC(var10);
                     var6.add(var10.pC);
                     var1 = var10.A;
                     this.A++;
                  } else {
                     var8 = Character.toUpperCase(var8);
                     if (bso.wS.contains(var8)) {
                        bso.K var16 = this.A(var1 - 1);
                        this.pC(var16);
                        var6.add(var16.pC);
                        var1 = var16.A;
                        this.A += 2;
                     } else {
                        this.wS("Character not parsed: " + var8, var1 - 1);
                     }
                  }
               }
            }
         }

         if (var4 != var3) {
            this.wS("Illegal closing parenthesis", var1 - 1);
         }

         bso.K var14 = new bso.K();
         var14.A = var1;
         var14.wS = var7;
         if (!var7 && var5 != null) {
            if (var6.isEmpty()) {
               this.pC("Missing operand(s)");
            }

            if (var5 == bsz.UT && var6.size() == 1) {
               var5 = bsz.ld;
            }

            var14.pC = new bsy(var5, (bsv[])var6.toArray(new bsv[var6.size()]));
         } else {
            if (var6.size() != 1) {
               this.pC("Missing operand");
            }

            var14.pC = (bsv)var6.get(0);
         }

         return var14;
      }

      private void pC(bso.K var1) {
         int var2 = var1.A;
         if (var2 < this.pC.length() && this.pC.charAt(var2) == '[') {
            throw new RuntimeException("Slices not supported by dexdec IR");
         }
      }

      int pC(String var1, int var2) {
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

      int A(String var1, int var2) {
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

      bso.K pC(int var1, int var2) {
         int var3 = this.pC(this.pC, var1);
         String var4 = this.pC.substring(var1, var3);
         long var5 = Conversion.stringToLong(var4, -1L);
         if (var5 < 0L) {
            this.wS("Illegal immediate: " + var4, var1 - 1);
         }

         var1 = var3;
         int var7 = -1;
         if (var3 < this.pC.length() && this.pC.charAt(var3) == '{') {
            var1 = var3 + 1;
            var3 = this.pC(this.pC, var1);
            if (var3 >= this.pC.length() || this.pC.charAt(var3) != '}') {
               this.wS("", var3);
            }

            var7 = Conversion.stringToInt(this.pC.substring(var1, var3), -1);
            var1 = var3 + 1;
         }

         return new bso.K(new bsx(var2 * var5, 0, var7), var1);
      }

      bso.K A(int var1) {
         char var2 = this.pC.charAt(var1++);
         int var3 = this.pC(this.pC, var1);
         if (var3 == var1) {
            this.wS("Missing leaf identifier", var1);
         }

         byte var4;
         if (var2 == '$') {
            var4 = 7;
         } else if (var2 == '@') {
            if (!this.pC.substring(var1, var3).startsWith("LASTBIT")) {
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
         char var6 = this.pC.charAt(var1);
         if (Character.isDigit(var6)) {
            var3 = this.A(this.pC, var1);
            var5 = Integer.parseInt(this.pC.substring(var1, var3));
            var1 = var3;
         } else {
            this.wS("Missing leaf id", var1);
         }

         return new bso.K(new bsx(var5, 0, var4), var1);
      }

      bsz kS(String var1, int var2) {
         String var3 = var1.toLowerCase();
         switch (var3) {
            case "+":
               return bsz.wS;
            case "-":
               return bsz.UT;
            case "*":
               return bsz.E;
            case "/":
               return bsz.sY;
            case "%":
               return bsz.ys;
            case "~":
               return bsz.gp;
            case "&":
               return bsz.oT;
            case "|":
               return bsz.fI;
            case "^":
               return bsz.WR;
            case "!":
               return bsz.xC;
            case "&&":
               return bsz.vP;
            case "||":
               return bsz.NS;
            case "==":
               return bsz.ED;
            case "!=":
               return bsz.Sc;
            case "<":
               return bsz.UO;
            case "<=":
               return bsz.Ab;
            case ">":
               return bsz.ah;
            case ">=":
               return bsz.eP;
            case "<<":
               return bsz.rl;
            case ">>":
               return bsz.Ek;
            case ">>>":
            case ">>u":
               return bsz.z;
            case "_ncadd":
               return bsz.A;
            case "_ncsub":
               return bsz.kS;
            case "?":
            case ":":
               return bsz.pC;
            default:
               throw new RuntimeException("TBI: Operator: " + var1);
         }
      }

      void pC(String var1) {
         this.wS(var1, -1);
      }

      void wS(String var1, int var2) {
         bso.pC(var1, this.pC, var2);
      }
   }
}
