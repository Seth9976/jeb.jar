package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.UnsupportedConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import java.util.List;

public class A {
   uq q;
   IERoutineContext RF;

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   IEGeneric q(IEGeneric var1, A.eo var2) {
      var1 = var1.duplicate();
      switch (var2) {
         case q:
            return EUtil.add(var1, this.q.Dw(var1.getBitsize()));
         case RF:
            return EUtil.notB(var1);
         case xK:
            return var1;
         case Dw:
            return this.q.q(var1);
         default:
            throw new UnsupportedConversionException("Not implemented Conditional Return " + this);
      }
   }

   public A(uq var1) {
      this.q = var1;
   }

   void q(fA var1, List var2) {
      this.q(var1, var2, A.eo.RF);
   }

   void RF(fA var1, List var2) {
      this.q(var1, var2, A.eo.q);
   }

   void xK(fA var1, List var2) {
      this.q(var1, var2, A.eo.xK);
   }

   void Dw(fA var1, List var2) {
      this.q(var1, var2, A.eo.Dw);
   }

   private void q(fA var1, List var2, A.eo var3) {
      CZ var4 = this.q.q(var1, var1.q(0));
      IEGeneric var5 = this.q.q(var1, (IInstructionOperand)var1.q(1));
      IEGeneric var6 = this.q.q(var1, var1.q(2), var5.getBitsize());
      IEGeneric var7 = this.q(var6, var3);
      int var8 = (int)var1.q(3).getOperandValue();
      IECond var9 = this.RF.createCond(this.q.Of.q(var8), var5, var7);
      this.q.q(var1, var2, var4, var9);
   }

   void Uv(fA var1, List var2) {
      this.RF(var1, var2, A.eo.RF);
   }

   void oW(fA var1, List var2) {
      this.RF(var1, var2, A.eo.q);
   }

   void gO(fA var1, List var2) {
      this.RF(var1, var2, A.eo.Dw);
   }

   private void RF(fA var1, List var2, A.eo var3) {
      CZ var4 = this.q.q(var1, var1.q(0));
      IEGeneric var5 = this.q.q(var1, (IInstructionOperand)var1.q(1));
      IEGeneric var6 = this.q(var5, var3);
      int var7 = (int)var1.q(2).getOperandValue();
      IECond var8 = this.RF.createCond(this.q.Of.q(var7), var6, var5);
      this.q.q(var1, var2, var4, var8);
   }

   public void nf(fA var1, List var2) {
      CZ var3 = this.q.q(var1, var1.q(0));
      int var4 = (int)var1.q(1).getOperandValue();
      IECond var5 = this.RF.createCond(this.q.Of.q(var4), this.q.Dw(var3.gO()), this.q.Uv(var3.gO()));
      this.q.q(var1, var2, var3, var5);
   }

   public void gP(fA var1, List var2) {
      CZ var3 = this.q.q(var1, var1.q(0));
      int var4 = var1.q(0).getOperandBitsize();
      int var5 = (int)var1.q(1).getOperandValue();
      IECond var6 = this.RF.createCond(this.q.Of.q(var5), this.q.oW(var4).zeroExtend(var3.gO()), this.q.Uv(var3.gO()));
      this.q.q(var1, var2, var3, var6);
   }

   void za(fA var1, List var2) {
      this.q(var1, var2, Lb.ej.oW);
   }

   void lm(fA var1, List var2) {
      this.q(var1, var2, Lb.ej.Dw);
   }

   private void q(fA var1, List var2, Lb.ej var3) {
      IEGeneric var4 = this.q.q(var1, (IInstructionOperand)var1.q(0));
      IEGeneric var5 = this.q.q(var1, var1.q(1), var4.getBitsize());
      int var6 = (int)var1.q(2).getOperandValue();
      int var7 = (int)var1.q(3).getOperandValue();
      IEVar var8 = this.q.gO(1);
      var2.add(this.RF.createAssign(var8, this.q.Of.q(var7)));
      YO var9 = this.q.wS.q(var2, var4, var5, var3);
      if (var9 != null && var9.Uv() != null) {
         var2.add(this.RF.createAssign(this.q.Of.Dw, this.RF.createCond(var8, var9.Uv(), this.RF.createImm(var6 & 1, 1))));
         if (var9.Dw() != null) {
            var2.add(this.RF.createAssign(this.q.RF(), this.RF.createCond(var8, var9.Dw(), this.RF.createImm((var6 & 2) >>> 1, 1))));
            var2.add(this.RF.createAssign(this.q.Of.RF, this.RF.createCond(var8, var9.xK(), this.RF.createImm((var6 & 4) >>> 2, 1))));
            var2.add(this.RF.createAssign(this.q.Of.q, this.RF.createCond(var8, var9.RF(), this.RF.createImm((var6 & 8) >>> 3, 1))));
         } else {
            throw new IllegalConversionException("CCMP/CCMN should set C and V  flags");
         }
      } else {
         throw new IllegalConversionException("CCMP/CCMN should set C and V  flags");
      }
   }

   void q(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, true, A.CU.q);
   }

   void RF(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, true, A.CU.RF);
   }

   void xK(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, true, A.CU.xK);
   }

   void Dw(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, true, A.CU.Dw);
   }

   void Uv(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, true, A.CU.Uv);
   }

   void oW(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, false, A.CU.q);
   }

   void gO(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, false, A.CU.RF);
   }

   void nf(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, false, A.CU.xK);
   }

   void gP(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, false, A.CU.Dw);
   }

   void za(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, false, A.CU.Uv);
   }

   private void q(fA var1, List var2, long var3, boolean var5, A.CU var6) {
      CZ var7 = this.q.q(var1, var1.q(0));
      IEGeneric var8 = this.q.q(var1, (IInstructionOperand)var1.q(1));
      IEGeneric var9 = this.q.q(var1, (IInstructionOperand)var1.q(2));
      IEGeneric var10 = null;
      if (var1.RF().length >= 4) {
         var10 = this.q.q(var1, (IInstructionOperand)var1.q(3));
      }

      IEGeneric var11 = this.q(var6, var5, var7.RF(), var8, var9, var10);
      if (var11 != null) {
         this.q.q(var1, var2, var7, var11);
      } else {
         var2.add(this.q.q(var1, var3, true));
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private IEGeneric q(A.CU var1, boolean var2, IEGeneric var3, IEGeneric var4, IEGeneric var5, IEGeneric var6) {
      switch (var1) {
         case q:
            return EUtil.mul(EUtil.extend(var4, 64, var2), EUtil.extend(var5, 64, var2));
         case RF:
            return EUtil.add(var6, this.q(A.CU.q, var2, var3, var4, var5, var6));
         case Dw:
            return EUtil.sub(var6, this.q(A.CU.q, var2, var3, var4, var5, var6));
         case xK:
            return EUtil.sub(this.q.Uv(64), this.q(A.CU.q, var2, var3, var4, var5, var6));
         case Uv:
            return EUtil.mul(EUtil.extend(var4, 128, var2), EUtil.extend(var5, 128, var2)).slice(64, 128);
         default:
            return null;
      }
   }

   void lm(fA var1, List var2, long var3) {
      int var5 = (int)var1.q(2).getOperandValue();
      int var6 = (int)var1.q(3).getOperandValue();
      int var7 = var1.q(0).getOperandBitsize();
      this.q(var1, var2, var3, (var7 - var5) % var7, var6 - 1);
   }

   void zz(fA var1, List var2, long var3) {
      int var5 = (int)var1.q(2).getOperandValue();
      int var6 = (int)var1.q(3).getOperandValue();
      this.q(var1, var2, var3, var5, var6);
   }

   void q(fA var1, List var2, long var3, int var5, int var6) {
      this.q(var1, var2, var3, var5, var6, true, true);
   }

   void JY(fA var1, List var2, long var3) {
      int var5 = (int)var1.q(2).getOperandValue();
      int var6 = (int)var1.q(3).getOperandValue();
      int var7 = var1.q(0).getOperandBitsize();
      this.RF(var1, var2, var3, (var7 - var5) % var7, var6 - 1);
   }

   void HF(fA var1, List var2, long var3) {
      int var5 = (int)var1.q(2).getOperandValue();
      int var6 = (int)var1.q(3).getOperandValue();
      this.RF(var1, var2, var3, var5, var6);
   }

   void RF(fA var1, List var2, long var3, int var5, int var6) {
      this.q(var1, var2, var3, var5, var6, true, false);
   }

   void q(fA var1, List var2, long var3, int var5, int var6, boolean var7, boolean var8) {
      CZ var9 = this.q.q(var1, var1.q(0));
      IEGeneric var10 = this.q.q(var1, (IInstructionOperand)var1.q(1));
      int var11 = var1.q(0).getOperandBitsize();
      int var12 = var11 == 32 ? 0 : 1;

      xB.eo var13;
      try {
         var13 = xB.RF(var12, var6, var5, var11);
      } catch (ProcessorException var20) {
         var2.add(this.q.q(var1, var3, false));
         return;
      }

      IEImm var14 = this.RF.createImm(var13.q, var11).zeroExtend(var11);
      IEImm var15 = this.RF.createImm(var13.RF, var11).zeroExtend(var9.gO());
      Object var16 = var7 ? this.RF.createImm(0L, var9.gO()) : var9.RF();
      IEOperation var18 = EUtil.orB(
         EUtil.andB((IEGeneric)var16, EUtil.notB(var14.zeroExtend(var9.gO()))),
         EUtil.andB(this.RF.createOperation(OperationType.ROR, var10, this.RF.createImm(var5, var11)), var14).zeroExtend(var9.gO())
      );
      Object var19 = var8 ? this.RF.createCond(var10.bit(var6), this.RF.createImm(-1L, var9.gO()), this.RF.createImm(0L, var9.gO())) : var16;
      this.q.q(var1, var2, var9, EUtil.orB(EUtil.andB((IEGeneric)var19, EUtil.notB(var15)), EUtil.andB(var18, var15)).zeroExtend(var9.gO()));
   }

   public void zz(fA var1, List var2) {
      CZ var3 = this.q.q(var1, var1.q(0));
      IEGeneric var4 = this.q.q(var1, (IInstructionOperand)var1.q(1));
      IEGeneric var5 = this.q.q(var1, (IInstructionOperand)var1.q(2));
      int var6 = (int)var1.q(3).getOperandValue();
      IEGeneric var7 = this.RF.createCompose(var5, var4).slice(var6, var6 + var1.q(0).getOperandBitsize());
      this.q.q(var1, var2, var3, var7);
   }

   public static enum CU {
      q,
      RF,
      xK,
      Dw,
      Uv;
   }

   private static enum eo {
      q,
      RF,
      xK,
      Dw;
   }
}
