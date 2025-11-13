package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.IOperandBuilder;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;

public class cmf implements cma {
   public static final cmf q = new cmf("UD");
   private static final clw[] qa = new clw[0];
   private String Hk;
   private ckh Me;
   private clt PV = clt.oW;
   private cmb oQ;
   private clw[] xW;
   private clg[] KT;
   public static final clg[] RF = new clg[]{null, clg.gP};
   public static final clg[] xK = new clg[]{clg.za, null};
   public static final clg[] Dw = new clg[]{clg.nf, null};
   public static final clg[] Uv = new clg[]{clg.nf, null};
   public static final clg[] oW = new clg[]{clg.nf, clg.gP};
   public static final clg[] gO = new clg[]{clg.RF, null};
   public static final clg[] nf = new clg[]{clg.RF, clg.gP};
   public static final clg[] gP = new clg[]{clg.Dw, clg.gP};
   public static final clg[] za = new clg[]{clg.xK, clg.Dw, clg.Uv, clg.gO, clg.gP, clg.lm};
   public static final clg[] lm = new clg[]{clg.xK, clg.Dw, clg.Uv, clg.gO, clg.gP};
   public static final clg[] zz = new clg[]{clg.Uv, clg.gO, clg.gP};
   public static final clg[] JY = new clg[]{clg.gO, clg.gP, clg.lm};
   public static final clg[] HF = new clg[]{clg.lm, null};
   private static final clw[] Gf = new clw[]{clz.nf, clz.xK, clz.q};
   private static final clw[] Ef = new clw[]{clz.q, clz.xK, clx.PV};
   private static final clw[] cC = new clw[]{clz.xK, clz.q, clx.gP};
   private static final clw[] sH = new clw[]{clz.xK, clz.q, clx.xW};
   private static final clw[] CE = new clw[]{clz.LK, clz.io, clx.Gf};
   private static final clw[] wF = new clw[]{clz.LK, clz.io, clx.Ef};
   public static final clw LK = new cly(clx.gP, clz.q);
   private static final clw[] If = new clw[]{clz.xK, LK};
   private static final clw[] Dz = new clw[]{clz.Uv, LK};
   public static final clw io = new cly(clx.Gf, clz.io);
   private static final clw[] mI = new clw[]{clz.LK, io};

   public cmf(String var1, clw... var2) {
      this(var1, null, clt.oW, var2);
   }

   public cmf(String var1, clg[] var2, clw... var3) {
      this(var1, null, clt.oW, var3);
      this.KT = var2;
   }

   public cmf(String var1, ckh var2, clg[] var3, clw... var4) {
      this(var1, var2, clt.oW, var4);
      this.KT = var3;
   }

   public cmf(String var1, clt var2, clw... var3) {
      this(var1, null, var2, var3);
   }

   public cmf(String var1, clt var2, clg[] var3, clw... var4) {
      this(var1, null, var2, var4);
      this.KT = var3;
   }

   public cmf(String var1, ckh var2, clt var3, clg[] var4, clw... var5) {
      this(var1, var2, var3, var5);
      this.KT = var4;
   }

   public cmf(String var1, clt var2, cmb var3, clg[] var4, clw... var5) {
      this(var1, null, var2, var5);
      this.KT = var4;
      this.oQ = var3;
   }

   public cmf(String var1, ckh var2, clt var3, cmb var4, clg[] var5, clw... var6) {
      this(var1, var2, var3, var6);
      this.KT = var5;
      this.oQ = var4;
   }

   protected cmf(String var1, ckh var2, clt var3, clw... var4) {
      this.Hk = var1;
      this.Me = var2;
      this.PV = var3;
      this.xW = var4;
      if (var4 == null) {
         this.xW = qa;
      }
   }

   public boolean q(clg var1) {
      if (this.KT != null && this.KT.length != 0) {
         if (this.KT.length > 2) {
            for (clg var5 : this.KT) {
               if (var5 != null && var5.q() == var1.q()) {
                  return true;
               }
            }
         }

         if (this.KT[0] != null && var1.q() < this.KT[0].q()) {
            return false;
         }

         if (this.KT[1] != null && var1.q() > this.KT[1].q()) {
            return false;
         }
      }

      return true;
   }

   public boolean RF() {
      if (this.KT != null && this.KT.length != 0) {
         if (this.KT.length > 2) {
            for (clg var8 : this.KT) {
               if (!var8.xK()) {
                  return false;
               }
            }

            return true;
         } else {
            int var1 = this.KT[0] != null ? this.KT[0].q() : 0;
            int var2 = this.KT[1] != null ? this.KT[1].q() : 160;

            for (int var3 = var1; var3 <= var2; var3 += 16) {
               clg var4 = clg.q(var3);
               if (var4 == null) {
                  return false;
               }

               if (!var4.xK()) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public String q(byte[] var1) {
      CharSequence var2 = null;
      if (this.Me != null) {
         var2 = this.Me.q(var1);
      }

      if (var2 == null) {
         return this.Hk;
      } else {
         StringBuilder var3 = new StringBuilder(this.Hk);
         var3.append(this.Me.q(var1));
         return var3.toString();
      }
   }

   public IOperandBuilder[] xK() {
      return this.xW;
   }

   public clv[] RF(byte[] var1, int var2) throws ProcessorException {
      ArrayList var3 = new ArrayList(this.xW.length);

      for (clw var7 : this.xW) {
         clv var8 = (clv)var7.buildOperand(var1, var2);
         if (var8 != null) {
            var3.add(var8);
         }
      }

      return (clv[])var3.toArray(new clv[var3.size()]);
   }

   @Override
   public clt q() {
      return this.PV;
   }

   public cmb Dw() {
      return this.oQ;
   }

   public static cmf q(byte[] var0, String var1) throws ProcessorException {
      throw new ProcessorException(Strings.ff("Instruction %s is unallocated. See section [%s]", Formatter.byteArrayToHex(var0), var1));
   }

   public static cmf RF(byte[] var0, String var1) throws ProcessorException {
      throw new ProcessorException(Strings.ff("Instruction %s is reserved. See section [%s]", Formatter.byteArrayToHex(var0), var1));
   }

   public static cmf q(String var0, clg... var1) {
      return new cmf(var0, var1, clz.Me);
   }

   public static cmf RF(String var0, clg... var1) {
      return new cmf(var0, var1, clz.PV);
   }

   public static cmf q(String var0) {
      return new cmf(var0, Gf);
   }

   public static cmf xK(String var0, clg... var1) {
      return new cmf(var0, var1, Ef);
   }

   public static cmf q(String var0, boolean var1, clg... var2) {
      return new cmf(var0, var2, var1 ? cC : sH);
   }

   public static cmf RF(String var0, boolean var1, clg... var2) {
      return new cmf(var0, var2, var1 ? CE : wF);
   }

   public static cmf Dw(String var0, clg... var1) {
      return new cmf(var0, var1, If);
   }

   public static cmf Uv(String var0, clg... var1) {
      return new cmf(var0, var1, Dz);
   }

   public static cmf oW(String var0, clg... var1) {
      return new cmf(var0, var1, mI);
   }

   public static cmf q(String var0, clt var1, clg[] var2) {
      return new cmf(var0, var1, var2, clx.q);
   }

   public boolean Uv() {
      return this.Hk.equals("UD");
   }
}
