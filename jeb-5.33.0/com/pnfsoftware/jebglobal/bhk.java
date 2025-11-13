package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Arrays;

public abstract class bhk {
   public int pC;
   public long A;
   public bhk.cq kS;

   private static bhk.cq pC(bhk.cq var0, bhk.cq... var1) {
      for (bhk.cq var5 : var1) {
         if (var5 == var0) {
            return var5;
         }
      }

      throw new IllegalArgumentException("Unsuitable record type");
   }

   public bhk(int var1, long var2, bhk.cq var4) {
      this.pC = var1;
      this.A = var2;
      this.kS = var4;
   }

   public static class Av extends bhk {
      public String wS;
      public int UT;

      public Av(int var1, long var2, bhk.cq var4, String var5, int var6) {
         super(var1, var2, bhk.pC(var4, bhk.cq.kS));
         this.wS = var5;
         this.UT = var6;
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         Strings.ff(var1, "n#%d %s: PC=0x%X: path=%s flags=0x%X", this.pC, this.kS, this.A, this.wS, this.UT);
         return var1.toString();
      }
   }

   public static class K extends bhk {
      public String wS;
      public long UT;

      public K(int var1, long var2, bhk.cq var4, String var5, long var6) {
         super(var1, var2, bhk.pC(var4, bhk.cq.wS, bhk.cq.UT));
         this.wS = var5;
         this.UT = var6;
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         Strings.ff(var1, "n#%d %s: PC=0x%X: msig=%s @0x%X", this.pC, this.kS, this.A, this.wS, this.UT);
         return var1.toString();
      }
   }

   public static class Sv extends bhk {
      public String wS;
      public String UT;
      public String[] E;

      public Sv(bhk.cq var1, int var2, String var3, String var4, String... var5) {
         super(var2, -1L, bhk.pC(var1, bhk.cq.ys, bhk.cq.ld, bhk.cq.gp, bhk.cq.oT));
         this.wS = var3;
         this.UT = var4;
         this.E = var5;
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         Strings.ff(var1, "j#%d %s: %s", this.pC, this.kS, this.pC(this.wS));
         if (this.UT != null) {
            var1.append(' ').append(this.UT);
         }

         if (this.E != null) {
            var1.append(' ').append(Arrays.toString((Object[])this.E));
         }

         return var1.toString();
      }

      private String pC(String var1) {
         int var2 = var1.indexOf("->");
         if (var2 < 0) {
            return var1;
         } else {
            String var3 = var1.substring(1, var2 - 1).replace('/', '.');
            String var4 = var1.substring(var2 + 2);
            var2 = var4.indexOf("(");
            if (var2 < 0) {
               var2 = var4.indexOf(":");
               if (var2 < 0) {
                  return null;
               }
            }

            String var5 = var4.substring(0, var2);
            return var5.equals("<init>") ? var3 : var3 + "." + var5;
         }
      }
   }

   public static class Ws extends bhk {
      public long wS;
      public int UT;
      public byte[] E;
      public String sY;

      public Ws(int var1, long var2, bhk.cq var4, long var5, int var7, byte[] var8, String var9) {
         super(var1, var2, bhk.pC(var4, bhk.cq.pC, bhk.cq.A));
         this.wS = var5;
         this.UT = var7;
         this.E = var8;
         this.sY = var9;
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         Strings.ff(var1, "n#%d %s: PC=0x%X: addr=0x%X size=0x%X", this.pC, this.kS, this.A, this.wS, this.UT);
         if (this.E != null) {
            Strings.ff(var1, ": %s(\"%s\")", Formatter.formatBinaryLine(this.E), Formatter.escapeBytes(this.E));
         }

         if (this.sY != null) {
            Strings.ff(var1, " [%s]", this.sY);
         }

         return var1.toString();
      }
   }

   public static class bO extends bhk {
      public String wS;

      public bO(int var1, long var2, bhk.cq var4, String var5) {
         super(var1, var2, bhk.pC(var4, bhk.cq.E, bhk.cq.sY));
         this.wS = var5;
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         Strings.ff(var1, "n#%d %s: PC=0x%X: name=%s", this.pC, this.kS, this.A, this.wS);
         return var1.toString();
      }
   }

   public static enum cq {
      pC,
      A,
      kS,
      wS,
      UT,
      E,
      sY,
      ys,
      ld,
      gp,
      oT;
   }
}
