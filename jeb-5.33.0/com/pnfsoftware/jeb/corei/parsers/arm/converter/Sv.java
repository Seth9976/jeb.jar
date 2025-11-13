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
import com.pnfsoftware.jebglobal.Yg;
import com.pnfsoftware.jebglobal.Z;
import com.pnfsoftware.jebglobal.pY;
import java.util.ArrayList;
import java.util.List;

public class Sv {
   HE pC;
   IERoutineContext A;

   public Sv(HE var1) {
      this.pC = var1;
   }

   public void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      Yg var5 = var1.A()[0];
      pY var6 = this.pC.pC(var1, var5);
      nA var7 = this.pC.pC(var1, var1.A()[1], var3, var5.getOperandBitsize(), this.pC.Er);
      this.pC(var1, var2, var6, var7);
   }

   public void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      Yg var5 = var1.A()[0];
      pY var6 = this.pC.pC(var1, var5);
      nA var7 = this.pC.pC(var1, var1.A()[1], var3, var5.getOperandBitsize(), this.pC.Er);
      var7 = new nA(EUtil.notB(var7.pC()), var7.A());
      this.pC(var1, var2, var6, var7);
   }

   public void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2) {
      pY var3 = this.pC.pC(var1, var1.A()[0]);
      Yg[] var4 = var1.A()[1].E();
      int var5;
      int var6;
      if (var1.A()[1].getOperandType() == 7 && var4.length >= 2) {
         var5 = (int)var4[0].getOperandValue();
         var6 = (int)var4[1].getOperandValue();
      } else {
         var5 = (int)var1.A()[1].getOperandValue();
         var6 = 0;
      }

      IEGeneric[] var7 = new IEGeneric[var1.A()[0].getOperandBitsize() >>> 4];

      for (int var8 = 0; var8 < var7.length; var8++) {
         if (var8 * 16 == var6) {
            var7[var8] = this.A.createImm(var5, 16);
         } else {
            var7[var8] = var3.A().slice(var8 * 16, (var8 + 1) * 16);
         }
      }

      this.pC.pC(var1, var2, var3, EUtil.compose(this.A, var7));
   }

   public void kS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      pY var5 = this.pC.pC(var1, var1.A()[0]);
      IEGeneric var6;
      IEGeneric var7;
      if (var1.A().length == 2) {
         var6 = this.pC.pC(var1, (IInstructionOperand)var1.A()[0], var3);
         var7 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
      } else {
         var6 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
         var7 = this.pC.pC(var1, (IInstructionOperand)var1.A()[2], var3);
      }

      Z.Av var8 = Z.Av.valueOf(var1.wS().pC());
      nA var9 = this.pC.pC(var8, var6, var7, var5.sY(), this.pC.Er, var1.getProcessorMode());
      this.pC(var1, var2, var5, var9);
   }

   public void wS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      pY var5 = this.pC.pC(var1, var1.A()[0]);
      IEGeneric var6;
      if (var1.A().length == 1) {
         var6 = this.pC.pC(var1, (IInstructionOperand)var1.A()[0], var3);
      } else {
         var6 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
      }

      nA var7 = this.pC.pC(Z.Av.valueOf(var1.wS().pC()), var6, null, var5.sY(), this.pC.Er, var1.getProcessorMode());
      this.pC(var1, var2, var5, var7);
   }

   protected void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, pY var3, nA var4) {
      if (var4.kS()) {
         IEVar var5 = this.pC.Er;
         var2.add(this.A.createAssign(var5, this.pC.A()));
      } else {
         this.pC.A();
      }

      if (var1.wS().A()) {
         this.pC.RW.pC(var4.pC(), var2);
         if (var4.A() != null) {
            var2.add(this.A.createAssign(this.pC.A(), var4.A()));
         }

         this.pC.pC(var1, var2, var3, var4.pC());
      } else {
         this.pC.pC(var1, var2, var3, var4.pC(), true);
      }
   }

   public void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2) {
      pY var3 = this.pC.pC(var1, var1.A()[0]);
      int var4 = (int)var1.A()[1].getOperandValue();
      this.pC.pC(var1, var2, var3, this.A.createCompose(var3.A().part(16), this.A.createImm(var4, 16)));
   }

   public void UT(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.K.A);
   }

   public void E(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.K.pC);
   }

   public void sY(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.K.kS);
   }

   public void ys(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.K.wS);
   }

   public void ld(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.K.UT);
   }

   public void gp(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.K.E);
   }

   public void oT(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.K.sY);
   }

   public void fI(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.K.ys);
   }

   public void WR(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.K.ld);
   }

   public void NS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.K.gp);
   }

   public void vP(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.K.oT);
   }

   public void xC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.K.fI);
   }

   public void ED(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.K.WR);
   }

   public void Sc(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.K.NS);
   }

   private void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.K var5) {
      pY var6 = this.pC.pC(var1, var1.A()[0]);
      IEGeneric var7;
      nA var8;
      if (var1.A().length == 2) {
         Yg var9 = var1.A()[1];
         var7 = this.pC.pC(var1, (IInstructionOperand)var1.A()[0], var3);
         var8 = this.pC.pC(var1, var9, var3, var7.getBitsize(), this.pC.Er);
      } else {
         Yg var10 = var1.A()[2];
         var7 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
         var8 = this.pC.pC(var1, var10, var3, var7.getBitsize(), this.pC.Er);
      }

      this.pC(var1, var2, var5, var6, var7, var8, false);
   }

   public void ah(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.K.oT);
   }

   public void eP(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.K.pC);
   }

   public void UO(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.K.UT);
   }

   public void Ab(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.K.kS);
   }

   private void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.K var5) {
      IEGeneric var6 = this.pC.pC(var1, (IInstructionOperand)var1.pC(0), var3);
      nA var7 = this.pC.pC(var1, var1.pC(1), var3, var6.getBitsize(), this.pC.A());
      this.pC(var1, var2, var5, null, var6, var7, true);
   }

   public void pC(
      com.pnfsoftware.jeb.corei.parsers.arm.rQ var1,
      List var2,
      com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.K var3,
      pY var4,
      IEGeneric var5,
      nA var6,
      boolean var7
   ) {
      boolean var9 = var7 || var1.wS().A();
      IEVar var8;
      if (!var6.kS() && (var3.xC.NS != com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Av.kS || !var9 || var4 == null)) {
         var8 = this.pC.A();
      } else {
         var8 = this.pC.Er;
         var2.add(this.A.createAssign(var8, this.pC.A()));
      }

      var5 = var3.pC(var5);
      IEGeneric var10 = var6.pC();
      var10 = var3.A(var10);

      m var11 = switch (var3.pC()) {
         case SUB -> throw new UnsupportedConversionException();
         case ADD -> this.pC(var5, var10, var3.kS(var8));
         case AND, OR, XOR -> {
            IEOperation var12 = this.A.createOperation(var3.pC(), var5, var10);
            IEGeneric var13 = var12.duplicate().msb();
            IECond var14 = this.A.createCond(var12.duplicate(), this.pC.wS(1), this.pC.kS(1));
            yield new m(var12, var13, var14, var6.A(), null);
         }
         default -> throw new IllegalConversionException("setflags is not managed for OperationType " + var3.pC());
      };
      if (var11 == null) {
         throw new UnsupportedConversionException("Can not process operation type " + var3.pC());
      } else {
         ArrayList var17 = new ArrayList();
         IEGeneric var18 = null;
         if (var9) {
            var18 = this.pC.RW.pC(this.pC, var17, var4, var11);
         }

         if (var18 != null) {
            this.pC.pC(var1, var2, var18, var4.A());
         }

         if (var4 != null) {
            this.pC.pC(var1, var2, var4, var11.pC(), true);
         }

         if (!var17.isEmpty()) {
            var2.addAll(var17);
         }
      }
   }

   public m pC(List var1, IEGeneric var2, IEGeneric var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Ws var4) {
      var2 = var4.pC(var2);
      var3 = var4.A(var3);
      IEVar var5 = null;
      if (var4.NS == com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Av.kS) {
         var5 = this.pC.E(1);
         var1.add(this.A.createAssign(var5, this.pC.A()));
      }

      return this.pC(var2, var3, var4.kS(var5));
   }

   public m pC(IEGeneric var1, IEGeneric var2, IEGeneric var3) {
      boolean var5 = var3 != null && EUtil.isImmValue(var3, 1L) && var2.isOperation(OperationType.NOT);
      Object var4;
      if (var5) {
         var4 = EUtil.sub(var1, var2.asOperation().getOperand1());
      } else if (EUtil.isImmZero(var1)) {
         var4 = var3 == null ? var2 : EUtil.add(var2, var3.zeroExtend(var1.getBitsize()));
      } else {
         var4 = var3 == null ? EUtil.add(var1, var2) : EUtil.add(var1, var2, var3.zeroExtend(var1.getBitsize()));
      }

      IEGeneric var6 = ((IEGeneric)var4).duplicate().msb();
      IECond var7 = this.A.createCond(((IEGeneric)var4).duplicate(), this.pC.wS(1), this.pC.kS(1));
      if (var3 == null) {
         IEGeneric var15 = EUtil.buildCarryFlag(var1, var2, (IEGeneric)var4, true, true);
         IEGeneric var17 = EUtil.buildOverflowFlag(var1, var2, (IEGeneric)var4, true);
         return new m((IEGeneric)var4, var6, var7, var15, var17);
      } else if (var5) {
         IEOperation var14 = EUtil.geU(var1, var2.asOperation().getOperand1());
         IEGeneric var16 = EUtil.buildOverflowFlag(var1, var2.asOperation().getOperand1(), (IEGeneric)var4, false);
         return new m((IEGeneric)var4, var6, var7, var14, var16);
      } else {
         int var8 = var1.getBitsize() * 2;
         IEOperation var9 = EUtil.add(var1.zeroExtend(var8), var2.zeroExtend(var8), var3.zeroExtend(var8));
         IEOperation var10 = EUtil.add(var1.signExtend(var8), var2.signExtend(var8), var3.zeroExtend(var8));
         IEGeneric var11 = var9.duplicate().part(var1.getBitsize());
         IECond var12 = this.A.createCond(EUtil.eq(var11.duplicate().zeroExtend(var8), var9), this.pC.wS(1), this.pC.kS(1));
         IECond var13 = this.A.createCond(EUtil.eq(var11.duplicate().signExtend(var8), var10), this.pC.wS(1), this.pC.kS(1));
         return new m((IEGeneric)var4, var6, var7, var12, var13);
      }
   }

   public void rl(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, null, false);
   }

   public void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, OperationType var5) {
      this.pC(var1, var2, var3, var5, false);
   }

   public void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, OperationType var5, boolean var6) {
      pY var7 = this.pC.pC(var1, var1.A()[0]);
      Object var10 = null;
      IEGeneric var8;
      IEGeneric var9;
      if (var5 != null) {
         var8 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
         var9 = this.pC.pC(var1, var1.A()[2], var8.getBitsize(), var3);
         if (var6) {
            var10 = this.pC.wS(var8.getBitsize());
         } else {
            var10 = this.pC.pC(var1, var1.A()[3], var8.getBitsize(), var3);
         }
      } else if (var1.A().length == 2) {
         var8 = this.pC.pC(var1, (IInstructionOperand)var1.A()[0], var3);
         var9 = this.pC.pC(var1, var1.A()[1], var8.getBitsize(), var3);
      } else {
         var8 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
         var9 = this.pC.pC(var1, var1.A()[2], var8.getBitsize(), var3);
      }

      IEOperation var11 = this.A.createOperation(OperationType.MUL, var8, var9);
      if (var5 != null) {
         var11 = this.A.createOperation(var5, (IEGeneric)var10, var11);
      }

      if (var1.wS().A()) {
         this.pC.RW.pC(var11, var2);
      }

      this.pC.pC(var1, var2, var7, var11);
   }

   public void kS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2) {
      pY var3 = this.pC.pC(var1, var1.A()[0]);
      int var4 = (int)var1.A()[1].getOperandValue();
      int var5 = (int)var1.A()[2].getOperandValue();
      ArrayList var6 = new ArrayList();
      if (var4 > 0) {
         var6.add(var3.A().part(var4));
      }

      var6.add(this.pC.wS(var5));
      if (var4 + var5 < var1.A()[0].getOperandBitsize()) {
         var6.add(var3.A().slice(var4 + var5, var1.A()[0].getOperandBitsize()));
      }

      IEGeneric var7 = EUtil.compose(this.A, var6);
      this.pC.pC(var1, var2, var3, var7);
   }

   public void z(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      pY var5 = this.pC.pC(var1, var1.A()[0]);
      IEGeneric var6 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
      int var7 = (int)var1.A()[2].getOperandValue();
      int var8 = (int)var1.A()[3].getOperandValue();
      ArrayList var9 = new ArrayList();
      if (var7 > 0) {
         var9.add(var5.A().part(var7));
      }

      var9.add(var6.part(var8));
      if (var7 + var8 < var1.A()[0].getOperandBitsize()) {
         var9.add(var5.A().slice(var7 + var8, var1.A()[0].getOperandBitsize()));
      }

      IEGeneric var10 = EUtil.compose(this.A, var9);
      this.pC.pC(var1, var2, var5, var10);
   }

   public void Ek(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      pY var5 = this.pC.pC(var1, var1.A()[0]);
      IEGeneric var6 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
      int var7 = (int)var1.A()[2].getOperandValue();
      int var8 = (int)var1.A()[3].getOperandValue();
      ArrayList var9 = new ArrayList();
      if (var8 > 0) {
         var9.add(var6.slice(var7, var7 + var8));
      }

      if (var8 < var1.A()[0].getOperandBitsize()) {
         var9.add(var5.A().slice(var8, var1.A()[0].getOperandBitsize()));
      }

      IEGeneric var10 = EUtil.compose(this.A, var9);
      this.pC.pC(var1, var2, var5, var10);
   }

   public void hK(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      pY var5 = this.pC.pC(var1, var1.A()[0]);
      IEGeneric var6 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
      this.pC.pC(var1, var2, var5, var6.countSuccessiveBits(false, true, var5.sY()));
   }

   public void Er(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      pY var5 = this.pC.pC(var1, var1.A()[0]);
      IEGeneric var6 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
      this.pC
         .pC(var1, var2, var5, EUtil.xorB(var6.slice(1, var6.getBitsize()), var6.slice(0, var6.getBitsize() - 1)).countSuccessiveBits(false, true, var5.sY()));
   }

   public void FE(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, false);
   }

   public void Aj(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, true);
   }

   private void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, boolean var5) {
      pY var6 = this.pC.pC(var1, var1.A()[0]);
      IEGeneric var7;
      IEGeneric var8;
      if (var1.A().length > 2) {
         var8 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
         var7 = this.pC.pC(var1, var1.A()[2], var8.getBitsize(), var3);
      } else {
         var8 = this.pC.pC(var1, (IInstructionOperand)var1.A()[0], var3);
         var7 = this.pC.pC(var1, var1.A()[1], var8.getBitsize(), var3);
      }

      if (var5) {
         this.pC.pC(var1, var2, var6, this.A.createCompose(var7.part(16), var8.slice(16, 32)));
      } else {
         this.pC.pC(var1, var2, var6, this.A.createCompose(var8.part(16), var7.slice(16, 32)));
      }
   }

   public void EX(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      pY var5 = this.pC.pC(var1, var1.A()[0]);
      IEGeneric var6 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
      int var7 = var1.A()[0].getOperandBitsize();
      this.pC.pC(var1, var2, var5, this.pC(var6, var7));
   }

   public IEGeneric pC(IEGeneric var1) {
      return this.pC(var1, var1.getBitsize());
   }

   public IEGeneric pC(IEGeneric var1, int var2) {
      IEGeneric[] var3 = new IEGeneric[var2];

      for (int var4 = 0; var4 < var2; var4++) {
         var3[var2 - 1 - var4] = var1.bit(var4);
      }

      return EUtil.compose(this.A, var3);
   }

   public void LM(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      pY var5 = this.pC.pC(var1, var1.A()[0]);
      IEGeneric var6 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
      this.pC.pC(var1, var2, var5, EUtil.compose(this.A, this.A(var6, 8)));
   }

   public void mv(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, 16);
   }

   public void sO(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, 32);
   }

   public void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, int var5) {
      pY var6 = this.pC.pC(var1, var1.A()[0]);
      IEGeneric var7 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
      int var8 = var1.A()[0].getOperandBitsize() / var5;
      ArrayList var9 = new ArrayList();

      for (int var10 = 0; var10 < var8; var10++) {
         var9.addAll(this.A(var7.slice(var5 * var10, var5 * (var10 + 1)), 8));
      }

      this.pC.pC(var1, var2, var6, EUtil.compose(this.A, var9));
   }

   public List A(IEGeneric var1, int var2) {
      int var3 = var1.getBitsize() / var2;
      ArrayList var4 = new ArrayList();

      for (int var5 = 0; var5 < var3; var5++) {
         var4.add(var1.slice((var3 - 1 - var5) * var2, (var3 - var5) * var2));
      }

      return var4;
   }

   IEGeneric A(IEGeneric var1) {
      IEImm var2 = this.pC.kS(var1.getBitsize());
      IEImm var3 = this.pC.wS(var1.getBitsize());
      Object var4 = this.A.createCond(var1.bit(0), var2, var3);

      for (int var5 = 1; var5 < var1.getBitsize(); var5++) {
         var4 = EUtil.add((IEGeneric)var4, this.A.createCond(var1.bit(var5), var2, var3));
      }

      return (IEGeneric)var4;
   }

   public void os(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      pY var5 = this.pC.pC(var1, var1.A()[0]);
      IEGeneric var6 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
      IEGeneric[] var7 = new IEGeneric[]{var6.slice(8, 16), var6.slice(0, 8).signExtend(24)};
      this.pC.pC(var1, var2, var5, EUtil.compose(this.A, var7));
   }

   public void Cu(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, true);
   }

   public void hZ(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, false);
   }

   private void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, boolean var5) {
      pY var6 = this.pC.pC(var1, var1.A()[0]);
      IEGeneric var7 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
      int var8 = this.pC.pC(var1, var1.A()[2], var3);
      int var9 = this.pC.pC(var1, var1.A()[3], var3);
      int var10 = var8 + var9 - 1;
      if (var1.getProcessorMode() == 64) {
         this.pC.kS.pC(var1, var2, var3, var8, var8 + var9 - 1, true, var5);
      } else if (var10 < var1.A()[0].getOperandBitsize()) {
         IEGeneric var11 = var7.slice(var8, var10 + 1);
         this.pC.pC(var1, var2, var6, EUtil.extend(var11, var6.sY(), var5));
      } else {
         var2.add(this.pC.pC(var1, var3, true, var6.pC()));
      }
   }

   public void UW(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, OperationType.DIV_S);
   }

   public void PR(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, OperationType.DIV_U);
   }

   public void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, OperationType var5) {
      pY var6 = this.pC.pC(var1, var1.A()[0]);
      IEGeneric var7;
      IEGeneric var8;
      if (var1.A().length > 2) {
         var8 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
         var7 = this.pC.pC(var1, var1.A()[2], var8.getBitsize(), var3);
      } else {
         var8 = this.pC.pC(var1, (IInstructionOperand)var1.A()[0], var3);
         var7 = this.pC.pC(var1, var1.A()[1], var8.getBitsize(), var3);
      }

      IEGeneric var9 = this.A.createOperation(var5, var8, var7).zeroExtend(var6.sY());
      IEOperation var10 = EUtil.eq(var7.duplicate(), this.pC.wS(var7.getBitsize()));
      this.pC.pC(var1, var2, var6, this.A.createCond(var10, this.pC.wS(var6.sY()), var9));
   }

   public void cX(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.bO.pC, true);
   }

   public void DQ(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.bO.A, true);
   }

   public void ZN(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.bO.kS, true);
   }

   public void OB(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.bO.wS, true);
   }

   public void pF(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.bO.pC, false);
   }

   public void Bc(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.bO.A, false);
   }

   public void OI(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.bO.kS, false);
   }

   private void pC(
      com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.bO var5, boolean var6
   ) {
      Yg var7 = var1.A()[0];
      pY var8 = this.pC.pC(var1, var7);
      IEGeneric var9;
      if (var1.A().length > 1) {
         var9 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
      } else {
         var9 = this.pC.pC(var1, (IInstructionOperand)var1.A()[0], var3);
         if (var1.A()[0].getOperandType() == 7) {
            var9 = this.pC.pC(var1, var1.A()[0].E()[0]).A();
         }
      }

      IEGeneric var10 = pC(this.A, null, var9, var5, var6, var7.getOperandBitsize());
      this.pC.pC(var1, var2, var8, var10);
   }

   static IEGeneric pC(
      IERoutineContext var0, IEGeneric var1, IEGeneric var2, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.bO var3, boolean var4, int var5
   ) {
      switch (var3) {
         case pC:
            IEGeneric var10 = var2.part(8);
            return pC(var1, EUtil.extend(var10, var5, var4));
         case A:
            IEGeneric var9 = EUtil.extend(var2.duplicate().part(8), 16, var4);
            IEGeneric var7 = EUtil.extend(var2.slice(16, 24), 16, var4);
            if (var1 != null) {
               return var0.createCompose(EUtil.add(var1.part(16), var9), EUtil.add(var1.slice(16, 32), var7));
            }

            return var0.createCompose(var9, var7);
         case kS:
            IEGeneric var8 = var2.part(16);
            return pC(var1, EUtil.extend(var8, var5, var4));
         case wS:
            IEGeneric var6 = var2.part(32);
            return pC(var1, EUtil.extend(var6, var5, var4));
         default:
            return null;
      }
   }

   static IEGeneric pC(IEGeneric var0, IEGeneric var1) {
      if (var0 == null && var1 == null) {
         return null;
      } else if (var0 == null) {
         return var1;
      } else {
         return (IEGeneric)(var1 == null ? var0 : EUtil.add(var0, var1));
      }
   }

   private static enum Av {
      pC,
      A,
      kS;
   }

   static enum K {
      pC(OperationType.ADD, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Ws.wS),
      A(OperationType.ADD, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Ws.UT),
      kS(OperationType.AND, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Ws.pC),
      wS(OperationType.AND, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Ws.A),
      UT(OperationType.XOR, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Ws.pC),
      E(OperationType.XOR, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Ws.A),
      sY(OperationType.OR, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Ws.A),
      ys(OperationType.OR, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Ws.pC),
      ld(OperationType.ADD, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Ws.ld),
      gp(OperationType.ADD, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Ws.gp),
      oT(OperationType.ADD, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Ws.E),
      fI(OperationType.ADD, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Ws.oT),
      WR(OperationType.ADD, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Ws.sY),
      NS(OperationType.ADD, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Ws.ys);

      private final OperationType vP;
      private final com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Ws xC;

      private K(OperationType var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Ws var4) {
         this.vP = var3;
         this.xC = var4;
      }

      OperationType pC() {
         return this.vP;
      }

      IEGeneric pC(IEGeneric var1) {
         return this.xC.pC(var1);
      }

      IEGeneric A(IEGeneric var1) {
         return this.xC.A(var1);
      }

      IEGeneric kS(IEGeneric var1) {
         return this.xC.kS(var1);
      }
   }

   private static enum Sv {
      pC,
      A,
      kS;
   }

   static enum Ws {
      pC(
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Sv.pC,
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Sv.pC,
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Av.pC
      ),
      A(
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Sv.pC,
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Sv.A,
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Av.pC
      ),
      kS(
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Sv.A,
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Sv.pC,
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Av.pC
      ),
      wS(
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Sv.pC,
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Sv.pC,
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Av.pC
      ),
      UT(
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Sv.pC,
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Sv.pC,
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Av.kS
      ),
      E(
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Sv.pC,
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Sv.A,
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Av.A
      ),
      sY(
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Sv.pC,
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Sv.A,
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Av.kS
      ),
      ys(
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Sv.kS,
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Sv.A,
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Av.kS
      ),
      ld(
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Sv.A,
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Sv.pC,
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Av.A
      ),
      gp(
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Sv.A,
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Sv.pC,
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Av.kS
      ),
      oT(
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Sv.kS,
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Sv.A,
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Av.A
      );

      private final com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Sv fI;
      private final com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Sv WR;
      private final com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Av NS;

      private Ws(
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Sv var3,
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Sv var4,
         com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Av var5
      ) {
         this.fI = var3;
         this.WR = var4;
         this.NS = var5;
      }

      IEGeneric pC(IEGeneric var1) {
         return this.pC(var1, this.fI);
      }

      IEGeneric A(IEGeneric var1) {
         return this.pC(var1, this.WR);
      }

      public IEGeneric kS(IEGeneric var1) {
         switch (this.NS) {
            case A:
               return EUtil.one(1);
            case kS:
               return var1;
            case pC:
            default:
               return null;
         }
      }

      private IEGeneric pC(IEGeneric var1, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.Sv var2) {
         switch (var2) {
            case A:
               return EUtil.notB(var1);
            case kS:
               return EUtil.zero(var1.getBitsize());
            case pC:
               return var1;
            default:
               throw new UnsupportedConversionException();
         }
      }
   }

   public static enum bO {
      pC,
      A,
      kS,
      wS;
   }
}
