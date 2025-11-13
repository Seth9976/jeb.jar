package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Arrays;

public abstract class blh {
   public int q;
   public long RF;
   public blh.Nt xK;

   private static blh.Nt q(blh.Nt var0, blh.Nt... var1) {
      for (blh.Nt var5 : var1) {
         if (var5 == var0) {
            return var5;
         }
      }

      throw new IllegalArgumentException("Unsuitable record type");
   }

   public blh(int var1, long var2, blh.Nt var4) {
      this.q = var1;
      this.RF = var2;
      this.xK = var4;
   }

   @Override
   public abstract String toString();

   public static class CU extends blh {
      public String Dw;
      public String Uv;
      public String[] oW;

      public CU(blh.Nt var1, int var2, String var3, String var4, String... var5) {
         super(var2, -1L, blh.q(var1, blh.Nt.nf, blh.Nt.gP, blh.Nt.za, blh.Nt.lm));
         this.Dw = var3;
         this.Uv = var4;
         this.oW = var5;
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         Strings.ff(var1, "j#%d %s: %s", this.q, this.xK, this.q(this.Dw));
         if (this.Uv != null) {
            var1.append(' ').append(this.Uv);
         }

         if (this.oW != null) {
            var1.append(' ').append(Arrays.toString((Object[])this.oW));
         }

         return var1.toString();
      }

      private String q(String var1) {
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

   public static enum Nt {
      q,
      RF,
      xK,
      Dw,
      Uv,
      oW,
      gO,
      nf,
      gP,
      za,
      lm;
   }

   public static class ej extends blh {
      public long Dw;
      public int Uv;
      public byte[] oW;
      public String gO;

      public ej(int var1, long var2, blh.Nt var4, long var5, int var7, byte[] var8, String var9) {
         super(var1, var2, blh.q(var4, blh.Nt.q, blh.Nt.RF));
         this.Dw = var5;
         this.Uv = var7;
         this.oW = var8;
         this.gO = var9;
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         Strings.ff(var1, "n#%d %s: PC=0x%X: addr=0x%X size=0x%X", this.q, this.xK, this.RF, this.Dw, this.Uv);
         if (this.oW != null) {
            Strings.ff(var1, ": %s(\"%s\")", Formatter.formatBinaryLine(this.oW), Formatter.escapeBytes(this.oW));
         }

         if (this.gO != null) {
            Strings.ff(var1, " [%s]", this.gO);
         }

         return var1.toString();
      }
   }

   public static class eo extends blh {
      public String Dw;
      public int Uv;

      public eo(int var1, long var2, blh.Nt var4, String var5, int var6) {
         super(var1, var2, blh.q(var4, blh.Nt.xK));
         this.Dw = var5;
         this.Uv = var6;
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         Strings.ff(var1, "n#%d %s: PC=0x%X: path=%s flags=0x%X", this.q, this.xK, this.RF, this.Dw, this.Uv);
         return var1.toString();
      }
   }

   public static class nI extends blh {
      public String Dw;
      public long Uv;

      public nI(int var1, long var2, blh.Nt var4, String var5, long var6) {
         super(var1, var2, blh.q(var4, blh.Nt.Dw, blh.Nt.Uv));
         this.Dw = var5;
         this.Uv = var6;
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         Strings.ff(var1, "n#%d %s: PC=0x%X: msig=%s @0x%X", this.q, this.xK, this.RF, this.Dw, this.Uv);
         return var1.toString();
      }
   }

   public static class oM extends blh {
      public String Dw;

      public oM(int var1, long var2, blh.Nt var4, String var5) {
         super(var1, var2, blh.q(var4, blh.Nt.oW, blh.Nt.gO));
         this.Dw = var5;
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         Strings.ff(var1, "n#%d %s: PC=0x%X: name=%s", this.q, this.xK, this.RF, this.Dw);
         return var1.toString();
      }
   }
}
