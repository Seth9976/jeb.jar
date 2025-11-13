package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class cn implements Ef {
   public static final int q = 0;
   public static final int RF = 1;
   public static final int xK = 2;
   public static final int Dw = 3;
   public static final int Uv = 2;
   public static final int oW = 3;
   public static final int gO = 6;
   public static final int nf = 7;
   public static final IEncodedMemoryArea gP = VirtualEncodedMemoryArea._00;
   public static final int za = 256;
   public static final int lm = 512;
   public static final int zz = 1024;
   public static final int JY = 2048;
   private final Ef KT;
   private final IEncodedMemoryArea Gf;
   private final IEncodedMemoryArea Ef;
   private final Ef cC;
   private int sH;
   private static final IEncodedMemoryArea CE = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(12, 3), DirectEncodedMemoryArea.get(6, 2));
   public static final cn HF = new cn(Pc.Dw, DirectEncodedMemoryArea.get(5, 2), DirectEncodedMemoryArea.get(7, 5), 512);
   public static final cn LK = new cn(Pc.Dw, DirectEncodedMemoryArea.get(5, 2), Pc.gO);
   public static final cn io = new cn(Pc.Dw, DirectEncodedMemoryArea.get(4, 2), CE, 512);
   public static final cn qa = new cn(iv.Uv, DirectEncodedMemoryArea.get(11, 2), DirectEncodedMemoryArea.get(6, 5));
   public static final cn Hk = new cn(Pc.Dw, DirectEncodedMemoryArea.get(5, 2), DirectEncodedMemoryArea.get(7, 5));
   public static final Ef Me = new cn(Pc.Dw, gP, DirectEncodedMemoryArea.get(4, 1));
   public static final Ef PV = new cn(Pc.Dw, VirtualEncodedMemoryArea.get(3, 2), DirectEncodedMemoryArea.get(4, 2).shift(3));
   public static final Ef oQ = new cn(Pc.lm, DirectEncodedMemoryArea.getThumb2(4, 1, 2), CE, 512);
   public static final Ef xW = new cn(YH.ui, DirectEncodedMemoryArea.get(22, 2), DirectEncodedMemoryArea.get(10, 6), 1280);

   protected static DH.eo q(int var0) {
      switch (var0) {
         case 0:
            return DH.eo.q;
         case 1:
            return DH.eo.RF;
         case 2:
            return DH.eo.xK;
         case 3:
            return DH.eo.Dw;
         default:
            return var0 < DH.eo.values().length ? DH.eo.values()[var0] : null;
      }
   }

   protected static DH.eo RF(int var0) {
      switch (var0) {
         case 2:
            return DH.eo.gP;
         case 3:
            return DH.eo.q;
         case 4:
         case 5:
         default:
            return null;
         case 6:
            return DH.eo.JY;
         case 7:
            return DH.eo.HF;
      }
   }

   public static CW q(CW var0, DH.eo var1, int var2, int var3, int var4) {
      if ((var4 & 512) != 0 && var1 == DH.eo.Dw && var2 == 0) {
         var1 = DH.eo.Uv;
      }

      if (var2 == 0 && (var4 & 2048) == 0) {
         if (var1 == DH.eo.q || var1 == DH.eo.Dw && (var4 & 1024) == 0) {
            return var0;
         }

         if ((var4 & 256) == 0 && (var1 == DH.eo.RF || var1 == DH.eo.xK)) {
            var2 = 32;
         }

         if (var1 == DH.eo.JY || var1 == DH.eo.HF || var1 == DH.eo.gP) {
            return ZD.q(var0, var1);
         }
      }

      return ZD.q(var0, var1, var2);
   }

   public cn(Ef var1, IEncodedMemoryArea var2, IEncodedMemoryArea var3) {
      this(var1, var2, var3, 0);
   }

   public cn(Ef var1, IEncodedMemoryArea var2, IEncodedMemoryArea var3, int var4) {
      this.KT = var1;
      this.Gf = var2;
      this.Ef = var3;
      this.cC = null;
      this.sH = var4;
   }

   public cn(Ef var1, IEncodedMemoryArea var2, Ef var3) {
      this.KT = var1;
      this.Gf = var2;
      this.cC = var3;
      this.Ef = null;
   }

   @Override
   public CW buildOperand(byte[] var1, int var2) throws ProcessorException {
      CW var3 = this.KT.buildOperand(var1, var2);
      DH.eo var4 = q(this.Gf.decodeInt(var1));
      if (this.Ef != null) {
         int var6 = k.RF(var1, this.Ef);
         return q(var3, var4, var6, var2, this.sH);
      } else {
         CW var5 = this.cC.buildOperand(var1, var2);
         return ZD.q(var3, var4, var5);
      }
   }
}
