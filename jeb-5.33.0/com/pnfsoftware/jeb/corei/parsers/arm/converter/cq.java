package com.pnfsoftware.jeb.corei.parsers.arm.converter;

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
import com.pnfsoftware.jebglobal.pY;
import com.pnfsoftware.jebglobal.u;
import java.util.List;

public class cq {
   HE pC;
   IERoutineContext A;

   IEGeneric pC(IEGeneric var1, cq.Av var2) {
      var1 = var1.duplicate();
      switch (var2) {
         case pC:
            return EUtil.add(var1, this.pC.kS(var1.getBitsize()));
         case A:
            return EUtil.notB(var1);
         case kS:
            return var1;
         case wS:
            return this.pC.pC(var1);
         default:
            throw new UnsupportedConversionException("Not implemented Conditional Return " + this);
      }
   }

   public cq(HE var1) {
      this.pC = var1;
   }

   void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2) {
      this.pC(var1, var2, cq.Av.A);
   }

   void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2) {
      this.pC(var1, var2, cq.Av.pC);
   }

   void kS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2) {
      this.pC(var1, var2, cq.Av.kS);
   }

   void wS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2) {
      this.pC(var1, var2, cq.Av.wS);
   }

   private void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, cq.Av var3) {
      pY var4 = this.pC.pC(var1, var1.pC(0));
      IEGeneric var5 = this.pC.pC(var1, (IInstructionOperand)var1.pC(1));
      IEGeneric var6 = this.pC.pC(var1, var1.pC(2), var5.getBitsize());
      IEGeneric var7 = this.pC(var6, var3);
      int var8 = (int)var1.pC(3).getOperandValue();
      IECond var9 = this.A.createCond(this.pC.RW.pC(var8), var5, var7);
      this.pC.pC(var1, var2, var4, var9);
   }

   void UT(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2) {
      this.A(var1, var2, cq.Av.A);
   }

   void E(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2) {
      this.A(var1, var2, cq.Av.pC);
   }

   void sY(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2) {
      this.A(var1, var2, cq.Av.wS);
   }

   private void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, cq.Av var3) {
      pY var4 = this.pC.pC(var1, var1.pC(0));
      IEGeneric var5 = this.pC.pC(var1, (IInstructionOperand)var1.pC(1));
      IEGeneric var6 = this.pC(var5, var3);
      int var7 = (int)var1.pC(2).getOperandValue();
      IECond var8 = this.A.createCond(this.pC.RW.pC(var7), var6, var5);
      this.pC.pC(var1, var2, var4, var8);
   }

   public void ys(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2) {
      pY var3 = this.pC.pC(var1, var1.pC(0));
      int var4 = (int)var1.pC(1).getOperandValue();
      IECond var5 = this.A.createCond(this.pC.RW.pC(var4), this.pC.kS(var3.sY()), this.pC.wS(var3.sY()));
      this.pC.pC(var1, var2, var3, var5);
   }

   public void ld(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2) {
      pY var3 = this.pC.pC(var1, var1.pC(0));
      int var4 = var1.pC(0).getOperandBitsize();
      int var5 = (int)var1.pC(1).getOperandValue();
      IECond var6 = this.A.createCond(this.pC.RW.pC(var5), this.pC.UT(var4).zeroExtend(var3.sY()), this.pC.wS(var3.sY()));
      this.pC.pC(var1, var2, var3, var6);
   }

   void gp(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2) {
      this.pC(var1, var2, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Ws.E);
   }

   void oT(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2) {
      this.pC(var1, var2, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Ws.wS);
   }

   private void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Ws var3) {
      IEGeneric var4 = this.pC.pC(var1, (IInstructionOperand)var1.pC(0));
      IEGeneric var5 = this.pC.pC(var1, var1.pC(1), var4.getBitsize());
      int var6 = (int)var1.pC(2).getOperandValue();
      int var7 = (int)var1.pC(3).getOperandValue();
      IEVar var8 = this.pC.E(1);
      var2.add(this.A.createAssign(var8, this.pC.RW.pC(var7)));
      m var9 = this.pC.pC.pC(var2, var4, var5, var3);
      if (var9 != null && var9.UT() != null) {
         var2.add(this.A.createAssign(this.pC.RW.wS, this.A.createCond(var8, var9.UT(), this.A.createImm(var6 & 1, 1))));
         if (var9.wS() != null) {
            var2.add(this.A.createAssign(this.pC.A(), this.A.createCond(var8, var9.wS(), this.A.createImm((var6 & 2) >>> 1, 1))));
            var2.add(this.A.createAssign(this.pC.RW.A, this.A.createCond(var8, var9.kS(), this.A.createImm((var6 & 4) >>> 2, 1))));
            var2.add(this.A.createAssign(this.pC.RW.pC, this.A.createCond(var8, var9.A(), this.A.createImm((var6 & 8) >>> 3, 1))));
         } else {
            throw new IllegalConversionException("CCMP/CCMN should set C and V  flags");
         }
      } else {
         throw new IllegalConversionException("CCMP/CCMN should set C and V  flags");
      }
   }

   void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, true, cq.Sv.pC);
   }

   void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, true, cq.Sv.A);
   }

   void kS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, true, cq.Sv.kS);
   }

   void wS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, true, cq.Sv.wS);
   }

   void UT(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, true, cq.Sv.UT);
   }

   void E(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, false, cq.Sv.pC);
   }

   void sY(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, false, cq.Sv.A);
   }

   void ys(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, false, cq.Sv.kS);
   }

   void ld(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, false, cq.Sv.wS);
   }

   void gp(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, false, cq.Sv.UT);
   }

   private void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, boolean var5, cq.Sv var6) {
      pY var7 = this.pC.pC(var1, var1.pC(0));
      IEGeneric var8 = this.pC.pC(var1, (IInstructionOperand)var1.pC(1));
      IEGeneric var9 = this.pC.pC(var1, (IInstructionOperand)var1.pC(2));
      IEGeneric var10 = null;
      if (var1.A().length >= 4) {
         var10 = this.pC.pC(var1, (IInstructionOperand)var1.pC(3));
      }

      IEGeneric var11 = this.pC(var6, var5, var7.A(), var8, var9, var10);
      if (var11 != null) {
         this.pC.pC(var1, var2, var7, var11);
      } else {
         var2.add(this.pC.pC(var1, var3, true));
      }
   }

   private IEGeneric pC(cq.Sv var1, boolean var2, IEGeneric var3, IEGeneric var4, IEGeneric var5, IEGeneric var6) {
      switch (var1) {
         case pC:
            return EUtil.mul(EUtil.extend(var4, 64, var2), EUtil.extend(var5, 64, var2));
         case A:
            return EUtil.add(var6, this.pC(cq.Sv.pC, var2, var3, var4, var5, var6));
         case wS:
            return EUtil.sub(var6, this.pC(cq.Sv.pC, var2, var3, var4, var5, var6));
         case kS:
            return EUtil.sub(this.pC.wS(64), this.pC(cq.Sv.pC, var2, var3, var4, var5, var6));
         case UT:
            return EUtil.mul(EUtil.extend(var4, 128, var2), EUtil.extend(var5, 128, var2)).slice(64, 128);
         default:
            return null;
      }
   }

   void oT(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      int var5 = (int)var1.pC(2).getOperandValue();
      int var6 = (int)var1.pC(3).getOperandValue();
      int var7 = var1.pC(0).getOperandBitsize();
      this.pC(var1, var2, var3, (var7 - var5) % var7, var6 - 1);
   }

   void fI(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      int var5 = (int)var1.pC(2).getOperandValue();
      int var6 = (int)var1.pC(3).getOperandValue();
      this.pC(var1, var2, var3, var5, var6);
   }

   void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, int var5, int var6) {
      this.pC(var1, var2, var3, var5, var6, true, true);
   }

   void WR(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      int var5 = (int)var1.pC(2).getOperandValue();
      int var6 = (int)var1.pC(3).getOperandValue();
      int var7 = var1.pC(0).getOperandBitsize();
      this.A(var1, var2, var3, (var7 - var5) % var7, var6 - 1);
   }

   void NS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      int var5 = (int)var1.pC(2).getOperandValue();
      int var6 = (int)var1.pC(3).getOperandValue();
      this.A(var1, var2, var3, var5, var6);
   }

   void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, int var5, int var6) {
      this.pC(var1, var2, var3, var5, var6, true, false);
   }

   void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, int var5, int var6, boolean var7, boolean var8) {
      pY var9 = this.pC.pC(var1, var1.pC(0));
      IEGeneric var10 = this.pC.pC(var1, (IInstructionOperand)var1.pC(1));
      int var11 = var1.pC(0).getOperandBitsize();
      int var12 = var11 == 32 ? 0 : 1;

      u.Av var13;
      try {
         var13 = u.A(var12, var6, var5, var11);
      } catch (ProcessorException var20) {
         var2.add(this.pC.pC(var1, var3, false));
         return;
      }

      IEImm var14 = this.A.createImm(var13.pC, var11);
      IEImm var15 = this.A.createImm(var13.A, var11);
      Object var16 = var7 ? this.A.createImm(0L, var11) : var9.A();
      IEOperation var18 = EUtil.orB(
         EUtil.andB((IEGeneric)var16, EUtil.notB(var14)), EUtil.andB(this.A.createOperation(OperationType.ROR, var10, this.A.createImm(var5, var11)), var14)
      );
      Object var19 = var8 ? this.A.createCond(var10.bit(var6), this.A.createImm(-1L, var11), this.A.createImm(0L, var11)) : var16;
      this.pC.pC(var1, var2, var9, EUtil.orB(EUtil.andB((IEGeneric)var19, EUtil.notB(var15)), EUtil.andB(var18, var15)).zeroExtend(var9.sY()));
   }

   public void fI(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2) {
      pY var3 = this.pC.pC(var1, var1.pC(0));
      IEGeneric var4 = this.pC.pC(var1, (IInstructionOperand)var1.pC(1));
      IEGeneric var5 = this.pC.pC(var1, (IInstructionOperand)var1.pC(2));
      int var6 = (int)var1.pC(3).getOperandValue();
      IEGeneric var7 = this.A.createCompose(var5, var4).slice(var6, var6 + var1.pC(0).getOperandBitsize());
      this.pC.pC(var1, var2, var3, var7);
   }

   public void pC(List var1) {
      IEVar var2 = this.pC.E(1);
      IEVar var3 = this.pC.ys(1);
      var1.add(this.A.createAssign(var2, EUtil.orB(this.pC.RW.A, this.pC.RW.wS)));
      var1.add(this.A.createAssign(var3, EUtil.andB(this.pC.RW.kS, EUtil.notB(this.pC.RW.wS))));
      this.pC.RW.pC(this.pC, var1, null, new m(null, this.pC.wS(1), var2, var3, this.pC.wS(1)));
   }

   public void A(List var1) {
      IEVar var2 = this.pC.E(1);
      IEVar var3 = this.pC.ys(1);
      var1.add(this.A.createAssign(var2, this.pC.RW.A));
      var1.add(this.A.createAssign(var3, this.pC.RW.kS));
      IEOperation var4 = EUtil.andB(EUtil.notB(var3), EUtil.notB(var2));
      IEOperation var5 = EUtil.andB(var2, var3);
      IEOperation var6 = EUtil.orB(var3, var2);
      IEOperation var7 = EUtil.andB(EUtil.notB(var3), var2);
      this.pC.RW.pC(this.pC, var1, null, new m(null, var4, var5, var6, var7));
   }

   public void WR(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2) {
      pY var3 = this.pC.pC(var1, var1.pC(0));
      IEGeneric var4 = this.pC.pC(var1, (IInstructionOperand)var1.pC(1));
      this.pC.pC(var1, var2, var3, this.pC.pC.A(var4));
   }

   private static enum Av {
      pC,
      A,
      kS,
      wS;
   }

   public static enum Sv {
      pC,
      A,
      kS,
      wS,
      UT;
   }
}
