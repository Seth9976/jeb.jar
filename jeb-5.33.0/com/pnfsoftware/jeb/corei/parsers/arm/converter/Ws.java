package com.pnfsoftware.jeb.corei.parsers.arm.converter;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jebglobal.Yg;
import com.pnfsoftware.jebglobal.pY;
import java.util.ArrayList;
import java.util.List;

public class Ws {
   HE pC;
   IERoutineContext A;

   public Ws(HE var1) {
      this.pC = var1;
   }

   public void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, OperationType.ADD, false);
   }

   public void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, OperationType.SUB, false);
   }

   public void kS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, OperationType.ADD, true);
   }

   public void wS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, OperationType.SUB, true);
   }

   public void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, OperationType var5, boolean var6) {
      pY var7 = this.pC.pC(var1, var1.A()[0]);
      IEGeneric var8;
      IEGeneric var9;
      if (var1.A().length > 2) {
         var8 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
         var9 = this.pC.pC(var1, var1.A()[2], var8.getBitsize(), var3);
      } else {
         var8 = this.pC.pC(var1, (IInstructionOperand)var1.A()[0], var3);
         var9 = this.pC.pC(var1, var1.A()[1], var8.getBitsize(), var3);
      }

      IEGeneric var10 = var9;
      IEGeneric var11 = null;
      if (var6) {
         nA var12 = this.A(var9.signExtend(64).leftShift(1), 32);
         var10 = var12.pC();
         var11 = var12.A().duplicate();
      }

      IEGeneric var16 = var8.signExtend(64);
      IEGeneric var13 = var10.signExtend(64);
      nA var14 = this.A(this.A.createOperation(var5, var16, var13), 32);
      Object var15 = var14.A();
      if (var11 != null) {
         var15 = EUtil.orL((IEGeneric)var15, var11);
      }

      var2.add(this.A.createAssign(this.pC.z, this.A.createCond((IEGeneric)var15, this.pC.Aj, this.pC.z)));
      this.pC.pC(var1, var2, var7, var14.pC());
   }

   public void UT(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, true);
   }

   public void E(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, false);
   }

   public void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, boolean var5) {
      pY var6 = this.pC.pC(var1, var1.A()[0]);
      int var7 = (int)var1.A()[1].getOperandValue();
      IEGeneric var8 = this.pC.pC(var1, (IInstructionOperand)var1.A()[2], var3);
      nA var9 = this.pC(var8, var7, var5);
      IEGeneric var10 = var9.A();
      var2.add(this.A.createAssign(this.pC.z, this.A.createCond(var10, this.pC.Aj, this.pC.z)));
      this.pC.pC(var1, var2, var6, EUtil.extend(var9.pC(), this.pC.fI, var5));
   }

   public void sY(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, true);
   }

   public void ys(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, false);
   }

   public void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, boolean var5) {
      pY var6 = this.pC.pC(var1, var1.A()[0]);
      int var7 = (int)var1.A()[1].getOperandValue();
      IEGeneric var8 = this.pC.pC(var1, (IInstructionOperand)var1.A()[2], var3);
      nA var9 = this.pC(var8.slice(0, 16), var7, var5);
      nA var10 = this.pC(var8.slice(16, 32), var7, var5);
      IEOperation var11 = EUtil.orL(var9.A(), var10.A());
      var2.add(this.A.createAssign(this.pC.z, this.A.createCond(var11, this.pC.Aj, this.pC.z)));
      IEGeneric var12 = EUtil.extend(var9.pC(), 16, var5);
      IEGeneric var13 = EUtil.extend(var10.pC(), 16, var5);
      this.pC.pC(var1, var2, var6, this.A.createCompose(var12, var13));
   }

   public void ld(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, Ws.Sv.pC, 16, true);
   }

   public void gp(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, Ws.Sv.pC, 8, true);
   }

   public void oT(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, Ws.Sv.A, 16, true);
   }

   public void fI(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, Ws.Sv.A, 8, true);
   }

   public void WR(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, Ws.Sv.kS, 16, true);
   }

   public void NS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, Ws.Sv.wS, 16, true);
   }

   public void vP(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, Ws.Sv.pC, 16, false);
   }

   public void xC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, Ws.Sv.pC, 8, false);
   }

   public void ED(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, Ws.Sv.A, 16, false);
   }

   public void Sc(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, Ws.Sv.A, 8, false);
   }

   public void ah(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, Ws.Sv.kS, 16, false);
   }

   public void eP(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, Ws.Sv.wS, 16, false);
   }

   private void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, Ws.Sv var5, int var6, boolean var7) {
      pY var8 = this.pC.pC(var1, var1.A()[0]);
      IEGeneric var9;
      IEGeneric var10;
      if (var1.A().length > 2) {
         var10 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
         var9 = this.pC.pC(var1, var1.A()[2], var10.getBitsize(), var3);
      } else {
         var10 = this.pC.pC(var1, (IInstructionOperand)var1.A()[0], var3);
         var9 = this.pC.pC(var1, var1.A()[1], var10.getBitsize(), var3);
      }

      IEGeneric[] var11 = this.pC(var5, var10, var9, var6, var7);
      this.pC.pC(var1, var2, var8, EUtil.compose(this.A, var11));
   }

   public void UO(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, Ws.Sv.pC, 16);
   }

   public void Ab(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, Ws.Sv.pC, 8);
   }

   public void rl(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, Ws.Sv.A, 16);
   }

   public void z(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, Ws.Sv.A, 8);
   }

   public void Ek(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, Ws.Sv.kS, 16);
   }

   public void hK(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, Ws.Sv.wS, 16);
   }

   private void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, Ws.Sv var5, int var6) {
      pY var7 = this.pC.pC(var1, var1.A()[0]);
      IEGeneric var8;
      IEGeneric var9;
      if (var1.A().length > 2) {
         var9 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
         var8 = this.pC.pC(var1, var1.A()[2], var9.getBitsize(), var3);
      } else {
         var9 = this.pC.pC(var1, (IInstructionOperand)var1.A()[0], var3);
         var8 = this.pC.pC(var1, var1.A()[1], var9.getBitsize(), var3);
      }

      IEGeneric[] var10 = this.A(var5, var9, var8, var6, true);
      IEImm var11 = this.pC.wS(var10[0].getBitsize());
      IEImm var12 = this.A.createImm((var6 >>> 2) - 1, var6 >>> 3);
      IEImm var13 = this.pC.wS(var6 >>> 3);
      IEGeneric[] var14 = new IEGeneric[var10.length];

      for (int var15 = 0; var15 < var10.length; var15++) {
         IEOperation var16 = EUtil.geS(var10[var15], var11);
         var14[var15] = this.A.createCond(var16, var12, var13);
      }

      var2.add(this.A.createAssign(this.pC.Ek, EUtil.compose(this.A, var14)));

      for (int var17 = 0; var17 < var10.length; var17++) {
         var10[var17] = var10[var17].part(var6);
      }

      this.pC.pC(var1, var2, var7, EUtil.compose(this.A, var10));
   }

   public void Er(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, Ws.Sv.pC, 16);
   }

   public void FE(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, Ws.Sv.pC, 8);
   }

   public void Aj(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, Ws.Sv.A, 16);
   }

   public void EX(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, Ws.Sv.A, 8);
   }

   public void LM(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, Ws.Sv.kS, 16);
   }

   public void mv(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, Ws.Sv.wS, 16);
   }

   private void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, Ws.Sv var5, int var6) {
      pY var7 = this.pC.pC(var1, var1.A()[0]);
      IEGeneric var8;
      IEGeneric var9;
      if (var1.A().length > 2) {
         var9 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
         var8 = this.pC.pC(var1, var1.A()[2], var9.getBitsize(), var3);
      } else {
         var9 = this.pC.pC(var1, (IInstructionOperand)var1.A()[0], var3);
         var8 = this.pC.pC(var1, var1.A()[1], var9.getBitsize(), var3);
      }

      IEGeneric[] var10 = this.A(var5, var9, var8, var6, false);
      IEImm var11 = this.A.createImm(1 << var6, var10[0].getBitsize());
      IEImm var12 = this.pC.wS(var10[0].getBitsize());
      IEImm var13 = this.A.createImm((var6 >>> 2) - 1, var6 >>> 3);
      IEImm var14 = this.pC.wS(var6 >>> 3);
      IEGeneric[] var15 = new IEGeneric[var10.length];
      switch (var5) {
         case kS:
            var15[0] = this.A.createCond(EUtil.geS(var10[0], var12), var13, var14);
            var15[1] = this.A.createCond(EUtil.geS(var10[1], var11), var13, var14);
            break;
         case wS:
            var15[0] = this.A.createCond(EUtil.geS(var10[0], var11), var13, var14);
            var15[1] = this.A.createCond(EUtil.geS(var10[1], var12), var13, var14);
            break;
         default:
            for (int var16 = 0; var16 < var10.length; var16++) {
               IEOperation var17 = EUtil.geS(var10[var16], var5 == Ws.Sv.pC ? var11 : var12);
               var15[var16] = this.A.createCond(var17, var13, var14);
            }
      }

      var2.add(this.A.createAssign(this.pC.Ek, EUtil.compose(this.A, var15)));

      for (int var18 = 0; var18 < var10.length; var18++) {
         var10[var18] = var10[var18].part(var6);
      }

      this.pC.pC(var1, var2, var7, EUtil.compose(this.A, var10));
   }

   public void sO(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, Ws.Sv.pC, 16, true);
   }

   public void os(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, Ws.Sv.pC, 8, true);
   }

   public void Cu(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, Ws.Sv.A, 16, true);
   }

   public void hZ(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, Ws.Sv.A, 8, true);
   }

   public void UW(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, Ws.Sv.kS, 16, true);
   }

   public void PR(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, Ws.Sv.wS, 16, true);
   }

   public void cX(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, Ws.Sv.pC, 16, false);
   }

   public void DQ(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, Ws.Sv.pC, 8, false);
   }

   public void ZN(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, Ws.Sv.A, 16, false);
   }

   public void OB(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, Ws.Sv.A, 8, false);
   }

   public void pF(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, Ws.Sv.kS, 16, false);
   }

   public void Bc(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, Ws.Sv.wS, 16, false);
   }

   private void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, Ws.Sv var5, int var6, boolean var7) {
      pY var8 = this.pC.pC(var1, var1.A()[0]);
      IEGeneric var9;
      IEGeneric var10;
      if (var1.A().length > 2) {
         var10 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
         var9 = this.pC.pC(var1, var1.A()[2], var10.getBitsize(), var3);
      } else {
         var10 = this.pC.pC(var1, (IInstructionOperand)var1.A()[0], var3);
         var9 = this.pC.pC(var1, var1.A()[1], var10.getBitsize(), var3);
      }

      IEGeneric[] var11 = this.A(var5, var10, var9, var6, var7);

      for (int var12 = 0; var12 < var11.length; var12++) {
         var11[var12] = var11[var12].slice(1, var6 + 1);
      }

      this.pC.pC(var1, var2, var8, EUtil.compose(this.A, var11));
   }

   public IEGeneric[] pC(Ws.Sv var1, IEGeneric var2, IEGeneric var3, int var4, boolean var5) {
      IEGeneric[] var6 = this.A(var1, var2, var3, var4, var5);
      if (var6 != null) {
         for (int var7 = 0; var7 < var6.length; var7++) {
            var6[var7] = var5 ? this.pC(var6[var7], var4) : this.kS(var6[var7], var4);
         }
      }

      return var6;
   }

   public IEGeneric[] A(Ws.Sv var1, IEGeneric var2, IEGeneric var3, int var4, boolean var5) {
      switch (var1) {
         case kS:
            return new IEGeneric[]{
               EUtil.sub(EUtil.extend(var2.slice(0, 16), 32, var5), EUtil.extend(var3.slice(16, 32), 32, var5)),
               EUtil.add(EUtil.extend(var2.slice(16, 32), 32, var5), EUtil.extend(var3.slice(0, 16), 32, var5))
            };
         case wS:
            return new IEGeneric[]{
               EUtil.add(EUtil.extend(var2.slice(0, 16), 32, var5), EUtil.extend(var3.slice(16, 32), 32, var5)),
               EUtil.sub(EUtil.extend(var2.slice(16, 32), 32, var5), EUtil.extend(var3.slice(0, 16), 32, var5))
            };
         case pC:
         case A:
            int var6 = 32 / var4;
            IEGeneric[] var7 = new IEGeneric[var6];
            OperationType var8 = var1 == Ws.Sv.pC ? OperationType.ADD : OperationType.SUB;

            for (int var9 = 0; var9 < var6; var9++) {
               var7[var9] = this.A
                  .createOperation(
                     var8,
                     EUtil.extend(var2.slice(var9 * var4, var9 * var4 + var4), 32, var5),
                     EUtil.extend(var3.slice(var9 * var4, var9 * var4 + var4), 32, var5)
                  );
            }

            return var7;
         default:
            return null;
      }
   }

   private nA pC(IEGeneric var1, int var2, boolean var3) {
      return var3 ? this.A(var1, var2) : this.wS(var1, var2);
   }

   private IEGeneric pC(IEGeneric var1, int var2) {
      return this.A(var1, var2).pC();
   }

   private nA A(IEGeneric var1, int var2) {
      if (var2 <= 32 && var2 > 0) {
         IEImm var3 = this.A.createImm((1L << var2 - 1) - 1L, var1.getBitsize());
         IEImm var4 = this.A.createImm(-(1L << var2 - 1), var1.getBitsize());
         IEOperation var5 = EUtil.gtS(var1, var3);
         IEOperation var6 = EUtil.ltS(var1.duplicate(), var4);
         IECond var7 = this.A.createCond(var6, var4, var1.duplicate());
         IEGeneric var8 = this.A.createCond(var5, var3, var7).part(var2);
         IEOperation var9 = EUtil.orL(var5, var6);
         return new nA(var8, var9);
      } else {
         throw new IllegalConversionException("Allowed value for saturate_to are in range [1,32]");
      }
   }

   private IEGeneric kS(IEGeneric var1, int var2) {
      return this.wS(var1, var2).pC();
   }

   private nA wS(IEGeneric var1, int var2) {
      if (var2 <= 32 && var2 >= 0) {
         IEImm var3 = this.A.createImm((1L << var2) - 1L, var1.getBitsize());
         IEImm var4 = this.pC.wS(var1.getBitsize());
         if (var2 == 0) {
            IEOperation var10 = EUtil.ne(var1, var4);
            return new nA(this.pC.FE, var10);
         } else {
            IEOperation var5 = EUtil.gtS(var1, var3);
            IEOperation var6 = EUtil.ltS(var1.duplicate(), var4);
            IEOperation var7 = EUtil.orL(var5, var6);
            IECond var8 = this.A.createCond(var6, var4, var1.duplicate());
            IEGeneric var9 = this.A.createCond(var5, var3, var8).part(var2);
            return new nA(var9, var7);
         }
      } else {
         throw new IllegalConversionException("Allowed value for saturate_to are in range [0,32]");
      }
   }

   public void OI(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, 8);
   }

   private void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, int var5) {
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

      IEGeneric var9 = this.kS(Ws.Sv.A, var8, var7, var5, false);
      this.pC.pC(var1, var2, var6, var9);
   }

   public void Bf(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, 8);
   }

   private void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, int var5) {
      pY var6 = this.pC.pC(var1, var1.A()[0]);
      IEGeneric var8 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
      IEGeneric var7 = this.pC.pC(var1, var1.A()[2], var8.getBitsize(), var3);
      IEGeneric var9 = this.pC.pC(var1, var1.A()[3], var8.getBitsize(), var3);
      IEGeneric var10 = this.kS(Ws.Sv.A, var8, var7, var5, false);
      this.pC.pC(var1, var2, var6, EUtil.add(var9, var10));
   }

   public IEGeneric kS(Ws.Sv var1, IEGeneric var2, IEGeneric var3, int var4, boolean var5) {
      IEGeneric[] var6 = this.A(var1, var2, var3, var4, var5);
      IEGeneric var7 = null;
      if (var6 != null) {
         IEImm var8 = this.pC.wS(var6[0].getBitsize());

         for (int var9 = 0; var9 < var6.length; var9++) {
            var6[var9] = this.A.createCond(EUtil.geS(var6[var9], var8), var6[var9].duplicate(), EUtil.sub(var8, var6[var9].duplicate()));
            var7 = com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.pC(var7, var6[var9]);
         }
      }

      return var7;
   }

   public void Pe(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      pY var5 = this.pC.pC(var1, var1.A()[0]);
      IEGeneric var6;
      IEGeneric var7;
      if (var1.A().length > 2) {
         var6 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
         var7 = this.pC.pC(var1, (IInstructionOperand)var1.A()[2], var3);
      } else {
         var6 = this.pC.pC(var1, (IInstructionOperand)var1.A()[0], var3);
         var7 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
      }

      ArrayList var8 = new ArrayList();

      for (int var9 = 0; var9 < 4; var9++) {
         var8.add(this.A.createCond(this.pC.Ek.bit(var9), var6.slice(var9 * 8, (var9 + 1) * 8), var7.slice(var9 * 8, (var9 + 1) * 8)));
      }

      this.pC.pC(var1, var2, var5, EUtil.compose(this.A, var8));
   }

   public void ck(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.bO.pC, true);
   }

   public void RW(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.bO.A, true);
   }

   public void e(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.bO.kS, true);
   }

   public void xM(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.bO.pC, false);
   }

   public void kU(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.bO.A, false);
   }

   public void Kq(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.bO.kS, false);
   }

   private void pC(
      com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.bO var5, boolean var6
   ) {
      Yg var7 = var1.A()[0];
      pY var8 = this.pC.pC(var1, var1.A()[0]);
      IEGeneric var9;
      IEGeneric var10;
      if (var1.A().length > 2) {
         var10 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
         var9 = this.pC.pC(var1, var1.A()[2], var10.getBitsize(), var3);
      } else {
         var10 = this.pC.pC(var1, (IInstructionOperand)var1.A()[0], var3);
         var9 = this.pC.pC(var1, var1.A()[1], var10.getBitsize(), var3);
      }

      IEGeneric var11 = com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.pC(this.A, var10, var9, var5, var6, var7.getOperandBitsize());
      this.pC.pC(var1, var2, var8, var11);
   }

   public void go(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, false, false);
   }

   public void JF(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, false, true);
   }

   public void Nq(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, true, false);
   }

   public void pg(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, true, true);
   }

   public void gj(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, false, false);
   }

   public void ZD(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, false, true);
   }

   public void DL(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, true, false);
   }

   public void UH(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, true, true);
   }

   public void VD(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.kS(var1, var2, var3, false, false);
   }

   public void Xs(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.kS(var1, var2, var3, false, true);
   }

   public void KV(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.kS(var1, var2, var3, true, false);
   }

   public void FK(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.kS(var1, var2, var3, true, true);
   }

   private void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, boolean var5, boolean var6) {
      pY var7 = this.pC.pC(var1, var1.A()[0]);
      IEGeneric var8 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
      IEGeneric var9 = this.pC.pC(var1, var1.A()[2], var8.getBitsize(), var3);
      IEGeneric var10 = this.pC.pC(var1, var1.A()[3], var8.getBitsize(), var3);
      IEGeneric var11 = var5 ? var8.slice(16, 32) : var8.part(16);
      IEGeneric var12 = var6 ? var9.slice(16, 32) : var9.part(16);
      IEOperation var13 = EUtil.mul(var11.signExtend(this.pC.fI), var12.signExtend(this.pC.fI));
      IEOperation var14 = EUtil.add(var13.signExtend(this.pC.fI + 1), var10.signExtend(this.pC.fI + 1));
      IEOperation var15 = EUtil.ne(var14, var14.duplicate().part(this.pC.fI).signExtend(this.pC.fI + 1));
      var2.add(this.A.createAssign(this.pC.z, this.A.createCond(var15, this.pC.Aj, this.pC.z)));
      this.pC.pC(var1, var2, var7, var14.part(this.pC.fI));
   }

   private void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, boolean var5, boolean var6) {
      pY var7 = this.pC.pC(var1, var1.A()[0]);
      IEGeneric var8;
      IEGeneric var9;
      if (var1.A().length > 2) {
         var9 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
         var8 = this.pC.pC(var1, var1.A()[2], var9.getBitsize(), var3);
      } else {
         var9 = this.pC.pC(var1, (IInstructionOperand)var1.A()[0], var3);
         var8 = this.pC.pC(var1, var1.A()[1], var9.getBitsize(), var3);
      }

      IEGeneric var10 = var5 ? var9.slice(16, 32) : var9.part(16);
      IEGeneric var11 = var6 ? var8.slice(16, 32) : var8.part(16);
      IEOperation var12 = EUtil.mul(var10.signExtend(this.pC.fI), var11.signExtend(this.pC.fI));
      this.pC.pC(var1, var2, var7, var12.part(this.pC.fI));
   }

   private void kS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, boolean var5, boolean var6) {
      pY var7 = this.pC.pC(var1, var1.A()[0]);
      pY var8 = this.pC.pC(var1, var1.A()[1]);
      IEGeneric var9 = this.pC.pC(var1, (IInstructionOperand)var1.A()[2], var3);
      IEGeneric var10 = this.pC.pC(var1, var1.A()[3], var9.getBitsize(), var3);
      IEGeneric var11 = var5 ? var9.slice(16, 32) : var9.part(16);
      IEGeneric var12 = var6 ? var10.slice(16, 32) : var10.part(16);
      IEOperation var13 = EUtil.mul(var11.signExtend(64), var12.signExtend(64));
      IEOperation var14 = EUtil.add(var13, this.A.createCompose(var7.A(), var8.A()));
      IEVar var15 = this.pC.E(64);
      var2.add(this.A.createAssign(var15, var14));
      this.pC.pC(var1, var2, var8, var15.slice(this.pC.fI, this.pC.fI * 2));
      this.pC.pC(var1, var2, var7, var15.part(this.pC.fI));
   }

   public void Bi(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, true, Ws.Av.A);
   }

   public void wQ(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, true, Ws.Av.wS);
   }

   public void PZ(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, true, Ws.Av.pC);
   }

   private void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, boolean var5, Ws.Av var6) {
      pY var7 = this.pC.pC(var1, var1.A()[0]);
      IEGeneric var8;
      IEGeneric var9;
      if (var1.A().length > 2) {
         var8 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
         var9 = this.pC.pC(var1, var1.A()[2], var8.getBitsize(), var3);
      } else {
         var8 = this.pC.pC(var1, (IInstructionOperand)var1.A()[0], var3);
         var9 = this.pC.pC(var1, var1.A()[1], var8.getBitsize(), var3);
      }

      IEImm var10 = null;
      IEGeneric var11 = null;
      if (var1.A().length == 4) {
         var10 = this.pC.wS(this.pC.fI);
         var11 = this.pC.pC(var1, var1.A()[3], var10.getBitsize(), var3);
      }

      Object var12 = this.pC(var6, var5, var10, var11, var8, var9);
      if (var1.wS().pC().endsWith("R")) {
         var12 = EUtil.add((IEGeneric)var12, this.A.createImm(2147483648L, 64));
      }

      this.pC.pC(var1, var2, var7, ((IEGeneric)var12).slice(this.pC.fI, 64));
   }

   public void Ip(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, false, Ws.Av.kS);
   }

   public void Fm(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, false, Ws.Av.A);
   }

   public void FM(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, true, Ws.Av.A);
   }

   public void Wn(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, false, Ws.Av.pC);
   }

   public void gy(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, true, Ws.Av.pC);
   }

   private void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, boolean var5, Ws.Av var6) {
      pY var7 = this.pC.pC(var1, var1.A()[0]);
      pY var8 = this.pC.pC(var1, var1.A()[1]);
      IEGeneric var9 = this.pC.pC(var1, (IInstructionOperand)var1.A()[2], var3);
      IEGeneric var10 = this.pC.pC(var1, var1.A()[3], var9.getBitsize(), var3);
      IEGeneric var11 = this.pC(var6, var5, var7.A(), var8.A(), var9, var10);
      IEVar var12 = this.pC.E(64);
      var2.add(this.A.createAssign(var12, var11));
      if (var1.wS().A()) {
         this.pC.RW.pC(var11, var2);
      }

      this.pC.pC(var1, var2, var8, var12.slice(this.pC.fI, this.pC.fI * 2));
      this.pC.pC(var1, var2, var7, var12.part(this.pC.fI));
   }

   public void pt(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.kS(var1, var2, var3, false);
   }

   public void uE(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.kS(var1, var2, var3, true);
   }

   private void kS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, boolean var5) {
      pY var6 = this.pC.pC(var1, var1.A()[0]);
      IEGeneric var7;
      IEGeneric var8;
      if (var1.A().length > 2) {
         var7 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
         var8 = this.pC.pC(var1, var1.A()[2], var7.getBitsize(), var3);
      } else {
         var7 = this.pC.pC(var1, (IInstructionOperand)var1.A()[0], var3);
         var8 = this.pC.pC(var1, var1.A()[1], var7.getBitsize(), var3);
      }

      boolean var9 = var1.wS().pC().endsWith("T");
      IEGeneric var10 = var9 ? var8.slice(16, 32) : var8.part(16);
      Object var11 = this.pC(Ws.Av.pC, true, null, null, var7, var10);
      if (var5 && var1.A().length == 4) {
         IEGeneric var12 = this.pC.pC(var1, var1.A()[3], var7.getBitsize(), var3);
         var11 = EUtil.add((IEGeneric)var11, var12.signExtend(64).leftShift(16));
         IEOperation var13 = EUtil.ne(((IEGeneric)var11).slice(16, 64), ((IEGeneric)var11).duplicate().slice(16, 48).signExtend(48));
         var2.add(this.A.createAssign(this.pC.z, this.A.createCond(var13, this.pC.Aj, this.pC.z)));
      }

      this.pC.pC(var1, var2, var6, ((IEGeneric)var11).slice(16, 48));
   }

   private IEGeneric pC(Ws.Av var1, boolean var2, IEGeneric var3, IEGeneric var4, IEGeneric var5, IEGeneric var6) {
      switch (var1) {
         case pC:
            return EUtil.mul(EUtil.extend(var5, 64, var2), EUtil.extend(var6, 64, var2));
         case A:
            return EUtil.add(this.pC(Ws.Av.pC, var2, null, null, var5, var6), this.A.createCompose(var3, var4));
         case kS:
            IEGeneric var7 = this.pC(Ws.Av.pC, var2, null, null, var5, var6);
            return EUtil.add(var7, EUtil.extend(var4, 64, var2), EUtil.extend(var3, 64, var2));
         case wS:
            return EUtil.sub(this.A.createCompose(var3, var4), this.pC(Ws.Av.pC, var2, null, null, var5, var6));
         default:
            return null;
      }
   }

   public void Um(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, Ws.Av.A);
   }

   public void Ta(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, Ws.Av.wS);
   }

   public void So(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, Ws.Av.pC);
   }

   public void tH(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.pC(var1, var2, var3, Ws.Av.UT);
   }

   private void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, Ws.Av var5) {
      pY var6 = this.pC.pC(var1, var1.A()[0]);
      IEGeneric var7;
      IEGeneric var8;
      if (var1.A().length > 2) {
         var7 = this.pC.pC(var1, (IInstructionOperand)var1.A()[1], var3);
         var8 = this.pC.pC(var1, var1.A()[2], var7.getBitsize(), var3);
      } else {
         var7 = this.pC.pC(var1, (IInstructionOperand)var1.A()[0], var3);
         var8 = this.pC.pC(var1, var1.A()[1], var7.getBitsize(), var3);
      }

      IEGeneric var9 = null;
      if (var1.A().length == 4) {
         var9 = this.pC.pC(var1, var1.A()[3], var7.getBitsize(), var3);
      }

      boolean var10 = var1.wS().pC().endsWith("X");
      Object var11 = var10 ? this.A.createCompose(var8.slice(16, 32), var8.part(16)) : var8;
      IEGeneric var12 = this.pC(var5, var7, (IEGeneric)var11, var9);
      if (var5 != Ws.Av.UT) {
         IEOperation var13 = EUtil.ne(var12, var12.duplicate().part(this.pC.fI).signExtend(64));
         var2.add(this.A.createAssign(this.pC.z, this.A.createCond(var13, this.pC.Aj, this.pC.z)));
      }

      this.pC.pC(var1, var2, var6, var12.part(this.pC.fI));
   }

   public void Gm(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, Ws.Av.A);
   }

   public void Br(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      this.A(var1, var2, var3, Ws.Av.wS);
   }

   private void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, Ws.Av var5) {
      pY var6 = this.pC.pC(var1, var1.A()[0]);
      pY var7 = this.pC.pC(var1, var1.A()[1]);
      IEGeneric var8 = this.pC.pC(var1, (IInstructionOperand)var1.A()[2], var3);
      IEGeneric var9 = this.pC.pC(var1, var1.A()[3], var8.getBitsize(), var3);
      boolean var10 = var1.wS().pC().endsWith("X");
      Object var11 = var10 ? this.A.createCompose(var9.slice(16, 32), var9.part(16)) : var9;
      IEGeneric var12 = this.pC(var5, var8, (IEGeneric)var11, this.A.createCompose(var6.A(), var7.A()));
      IEVar var13 = this.pC.E(64);
      var2.add(this.A.createAssign(var13, var12));
      this.pC.pC(var1, var2, var7, var13.slice(this.pC.fI, this.pC.fI * 2));
      this.pC.pC(var1, var2, var6, var13.part(this.pC.fI));
   }

   private IEGeneric pC(Ws.Av var1, IEGeneric var2, IEGeneric var3, IEGeneric var4) {
      IEOperation var5 = EUtil.mul(var2.part(16).signExtend(64), var3.part(16).signExtend(64));
      IEOperation var6 = EUtil.mul(var2.slice(16, 32).signExtend(64), var3.duplicate().slice(16, 32).signExtend(64));
      switch (var1) {
         case pC:
            return EUtil.add(var5, var6);
         case A:
            return EUtil.add(EUtil.add(var5, var6), var4.signExtend(64));
         case kS:
         default:
            return null;
         case wS:
            return EUtil.add(EUtil.sub(var5, var6), var4.signExtend(64));
         case UT:
            return EUtil.sub(var5, var6);
      }
   }

   public static enum Av {
      pC,
      A,
      kS,
      wS,
      UT;
   }

   public static enum Sv {
      pC,
      A,
      kS,
      wS;
   }
}
