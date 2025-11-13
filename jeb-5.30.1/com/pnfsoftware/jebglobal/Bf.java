package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class Bf extends jD {
   public static final String q = "LDR instruction try to write back to PC.";
   private final rR za;
   private final IEncodedMemoryArea lm;
   private final IEncodedMemoryArea zz;
   private final IEncodedMemoryArea JY;
   private final rR HF;
   private final IEncodedMemoryArea LK;
   private final IEncodedMemoryArea io;
   private final IEncodedMemoryArea qa;
   public static final jD.eo RF = jD.eo.oW;
   public static final jD.eo xK = jD.eo.gO;
   public static final jD.eo Dw = jD.eo.nf;
   public static final jD.eo Uv = jD.eo.gP;
   public static final Bf oW = q(DirectEncodedMemoryArea.get(0, 8).shift(2));
   public static final Bf gO = q(DirectEncodedMemoryArea.get(0, 8).shift(2), RF);
   public static final Bf nf = q(DirectEncodedMemoryArea.get(0, 8).shift(1));
   public static final Bf gP = q(DirectEncodedMemoryArea.get(0, 8).shift(1), RF);

   public Bf(
      rR var1,
      IEncodedMemoryArea var2,
      IEncodedMemoryArea var3,
      IEncodedMemoryArea var4,
      rR var5,
      IEncodedMemoryArea var6,
      IEncodedMemoryArea var7,
      IEncodedMemoryArea var8,
      jD.eo... var9
   ) {
      super(var9);
      this.za = var1;
      this.lm = var2;
      this.zz = var4;
      this.JY = var3;
      this.HF = var5;
      this.LK = var6;
      this.io = var7;
      this.qa = var8;
   }

   @Override
   public CW buildOperand(byte[] var1, int var2) throws ProcessorException {
      boolean var3 = this.JY.decode(var1) != 0L;
      boolean var4 = this.zz.decode(var1) != 0L;
      boolean var5 = this.lm.decode(var1) != 0L;
      int var6 = 0;
      if (this.io != null) {
         if (this.q(Dw)) {
            var6 = k.q(var1, this.io);
         } else {
            var6 = k.RF(var1, this.io);
         }

         if (this.q(xK) && !var5 && !var4 && var3) {
            return this.q(CW.q(this.io.getLength(), this.q(var3), var6 >> 2, 2), var1, var4, var5, var2);
         }
      }

      if (this.HF == null || this.HF.RF(var1) != null && !this.q(this.HF.RF(var1).intValue())) {
         if (this.HF == null && this.LK == null) {
            return this.q(RF) && this.za.oW(var1) && var5 && !var4
               ? this.q(var1, var3, var2, var6)
               : this.q(this.q(var3, var6, var4, var5), var1, var4, var5, var2);
         } else if (this.LK == null) {
            return this.q(this.q(var1, var2, var3), var1, var4, var5, var2);
         } else {
            int var7 = this.LK.decodeInt(var1);
            short var8 = 512;
            DH.eo var9 = this.q(Uv) ? cn.RF(var7) : cn.q(var7);
            if (var9 != DH.eo.q && this.qa.decode(var1) != 0L) {
               var8 |= 2048;
            }

            return this.q(cn.q(this.q(var1, var2, var3), var9, var6, var2, var8), var1, var4, var5, var2);
         }
      } else {
         return this.q(null, var1, var4, var5, var2);
      }
   }

   private CW q(boolean var1, int var2, boolean var3, boolean var4) {
      if (var2 == 0 && var1 && var4 && !var3) {
         return null;
      } else {
         return this.io == null ? null : CW.q(this.io.getLength(), this.q(var1), var2, 65536);
      }
   }

   private CW q(byte[] var1, int var2, boolean var3) throws ProcessorException {
      return var3 ? this.HF.buildOperand(var1, var2) : GC.q(this.HF.Uv(var1), this.HF.RF(var1), var2, this.q(var3) == CW.CU.xK ? 262144 : 0);
   }

   private CW q(CW var1, byte[] var2, boolean var3, boolean var4, int var5) throws ProcessorException {
      return new wh(this.RF(var2, var5, var4), var1, var3, var4, var5);
   }

   private CW RF(byte[] var1, int var2, boolean var3) throws ProcessorException {
      return var3 ? this.za.buildOperand(var1, var2) : GC.q(this.za.Uv(var1), this.za.RF(var1), var2, 1);
   }

   private CW q(byte[] var1, boolean var2, int var3, int var4) throws ProcessorException {
      boolean var5 = this.zz.decode(var1) != 0L;
      boolean var6 = this.lm.decode(var1) != 0L;
      return this.q(this.q(var2, var4, var5, var6), var1, var5, var6, var3);
   }

   private CW.CU q(boolean var1) {
      return var1 ? CW.CU.q : CW.CU.xK;
   }

   public static Bf q(rR var0, IEncodedMemoryArea var1, IEncodedMemoryArea var2, jD.eo... var3) {
      return new Bf(
         Pc.lm,
         DirectEncodedMemoryArea.get(24, 1),
         DirectEncodedMemoryArea.get(23, 1),
         DirectEncodedMemoryArea.get(21, 1),
         var0,
         var1,
         var2,
         VirtualEncodedMemoryArea._0,
         var3
      );
   }

   public static Bf q(IEncodedMemoryArea var0, jD.eo... var1) {
      return q(null, null, var0, var1);
   }

   public static Bf q(rR var0, rR var1, IEncodedMemoryArea var2, IEncodedMemoryArea var3, jD.eo... var4) {
      return new Bf(
         var0, VirtualEncodedMemoryArea._1, VirtualEncodedMemoryArea._1, VirtualEncodedMemoryArea._0, var1, var2, var3, VirtualEncodedMemoryArea._0, var4
      );
   }

   public static Bf q(rR var0, rR var1, IEncodedMemoryArea var2, IEncodedMemoryArea var3, IEncodedMemoryArea var4, jD.eo... var5) {
      return new Bf(var0, VirtualEncodedMemoryArea._1, VirtualEncodedMemoryArea._1, VirtualEncodedMemoryArea._0, var1, var2, var3, var4, var5);
   }

   public static Bf q(rR var0, rR var1) {
      return new Bf(var0, VirtualEncodedMemoryArea._1, VirtualEncodedMemoryArea._1, VirtualEncodedMemoryArea._0, var1, null, VirtualEncodedMemoryArea._0, null);
   }

   public static Bf q(rR var0, IEncodedMemoryArea var1, jD.eo... var2) {
      return new Bf(
         var0, VirtualEncodedMemoryArea._1, VirtualEncodedMemoryArea._1, VirtualEncodedMemoryArea._0, null, null, var1, VirtualEncodedMemoryArea._0, var2
      );
   }

   public static Bf q(rR var0, IEncodedMemoryArea var1, IEncodedMemoryArea var2, IEncodedMemoryArea var3, IEncodedMemoryArea var4, jD.eo... var5) {
      return new Bf(var0, var1, var2, var3, null, null, var4, VirtualEncodedMemoryArea._0, var5);
   }

   public static Bf q(rR var0) {
      return q(var0, null);
   }

   public static Bf q(rR var0, rR var1, IEncodedMemoryArea var2, IEncodedMemoryArea var3) {
      return new Bf(var0, VirtualEncodedMemoryArea._0, VirtualEncodedMemoryArea._1, VirtualEncodedMemoryArea._0, var1, var2, VirtualEncodedMemoryArea._0, var3);
   }

   public static Bf RF(rR var0, IEncodedMemoryArea var1, jD.eo... var2) {
      return new Bf(
         var0, VirtualEncodedMemoryArea._0, VirtualEncodedMemoryArea._1, VirtualEncodedMemoryArea._0, null, null, var1, VirtualEncodedMemoryArea._0, var2
      );
   }

   public static Bf RF(rR var0, rR var1) {
      return q(var0, var1, null, null);
   }

   public static Bf xK(rR var0, IEncodedMemoryArea var1, jD.eo... var2) {
      return new Bf(
         var0, VirtualEncodedMemoryArea._1, VirtualEncodedMemoryArea._1, VirtualEncodedMemoryArea._1, null, null, var1, VirtualEncodedMemoryArea._0, var2
      );
   }

   public static Bf RF(rR var0) {
      return xK(var0, null);
   }

   public rR RF() {
      return this.za;
   }

   public rR xK() {
      return this.HF;
   }

   public boolean q(byte[] var1) {
      return this.lm.decodeInt(var1) == 0 || this.zz.decodeInt(var1) != 0;
   }

   public boolean RF(byte[] var1) {
      return this.za != null && this.za.oW(var1) && this.q(var1);
   }

   @Override
   public String xK(byte[] var1) {
      return this.RF(var1) ? "LDR instruction try to write back to PC." : super.xK(var1);
   }
}
