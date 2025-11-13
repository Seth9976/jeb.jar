package com.pnfsoftware.jeb.corei.parsers.avr;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ConverterInstructionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import java.util.List;

class K {
   cq pC;
   IERoutineContext A;

   K(cq var1) {
      this.pC = var1;
   }

   private void pC(List var1, IEGeneric var2, IEGeneric var3, boolean var4, boolean var5, boolean var6) {
      IEGeneric var7 = this.pC.wS(var1, var2);
      IEGeneric var8 = var3.equals(var2) ? var7 : var3;
      IEOperation var9 = this.A.createOperation(OperationType.ADD, var2, var3);
      if (var4) {
         var9 = this.A.createOperation(OperationType.ADD, var9, this.pC.fI.zeroExtend(var2.getBitsize()));
      }

      IEGeneric var10 = this.pC.pC(var1, var2, var9);
      this.pC.pC(var1, var10);
      this.pC.A(var1, var10);
      this.pC.pC(var1, var7, var8, var10);
      this.pC.pC(var1, var7, var8, var10, var4, var5, var6);
      this.pC.kS(var1, var10);
   }

   private void pC(List var1, IEGeneric var2, IEGeneric var3, boolean var4, boolean var5, boolean var6, boolean var7) {
      IEGeneric var8 = var2;
      IEGeneric var9 = var3;
      if (var4) {
         var8 = this.pC.wS(var1, var2);
         var9 = var3.equals(var2) ? var8 : var3;
      }

      IEOperation var10 = this.A.createOperation(OperationType.SUB, var2, var3);
      if (var5) {
         var10 = this.A.createOperation(OperationType.SUB, var10, this.pC.fI.zeroExtend(var2.getBitsize()));
      }

      IEGeneric var11;
      if (var4) {
         var11 = this.pC.pC(var1, var2, var10);
      } else {
         var11 = this.pC.wS(var1, var10);
      }

      this.pC.pC(var1, var11);
      this.pC.A(var1, var11);
      this.pC.A(var1, var8, var9, var11);
      this.pC.A(var1, var8, var9, var11, var5, var6, var7);
      if (var5) {
         this.pC.kS(var1, var11);
      } else {
         var1.add(this.A.createAssign(this.pC.xC, this.A.createOperation(OperationType.LT_S, var8, var9)));
      }
   }

   void pC(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.pC.pC((KD)var1.insn, 0, var1.address);
      IEGeneric var4 = this.pC.pC((KD)var1.insn, 1, var1.address);
      this.pC(var1.r, var3, var4, var2, true, true);
   }

   void A(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.pC.pC((KD)var1.insn, 0, var1.address);
      IEGeneric var4 = this.pC.pC((KD)var1.insn, 1, var1.address);
      this.pC(var1.r, var3, var4, true, var2, true, true);
   }

   void pC(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((KD)var1.insn, 0, var1.address);
      IEImm var3 = this.pC.z;
      this.pC(var1.r, var2, var3, false, false, false);
   }

   void A(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((KD)var1.insn, 0, var1.address);
      IEImm var3 = this.pC.z;
      this.pC(var1.r, var2, var3, true, false, false, false);
   }

   void kS(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((KD)var1.insn, 0, var1.address);
      IEGeneric var3 = this.pC.pC((KD)var1.insn, 1, var1.address);
      this.pC(var1.r, var2, var3.zeroExtend(16), false, true, false);
   }

   void wS(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((KD)var1.insn, 0, var1.address);
      IEGeneric var3 = this.pC.pC((KD)var1.insn, 1, var1.address);
      this.pC(var1.r, var2, var3.zeroExtend(16), true, false, true, false);
   }

   void UT(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((KD)var1.insn, 0, var1.address);
      IEGeneric var3 = this.pC.pC((KD)var1.insn, 1, var1.address);
      IEOperation var4 = this.A.createOperation(OperationType.MUL, var2, var3);
      IEGeneric var5 = this.A.createOperation(OperationType.MUL, var2.zeroExtend(16), var3.zeroExtend(16)).slice(8, 16);
      IEVar var6 = this.pC.gp[0];
      IEVar var7 = this.pC.gp[1];
      if (!var2.equals(var6) && !var3.equals(var6)) {
         this.pC.pC(var1.r, var6, var4);
         this.pC.pC(var1.r, var7, var5);
      } else {
         this.pC.pC(var1.r, this.pC.UO, var5);
         this.pC.pC(var1.r, var6, var4);
         this.pC.pC(var1.r, var7, this.pC.UO);
      }

      this.pC.pC(var1.r, this.A.createOperation(OperationType.OR, var6, var7));
      this.pC.pC(var1.r, this.pC.fI, var7.msb());
   }

   void pC(ConverterInstructionEntry var1, OperationType var2) {
      IEGeneric var3 = this.pC.pC((KD)var1.insn, 0, var1.address);
      IEGeneric var4 = this.pC.pC((KD)var1.insn, 1, var1.address);
      IEOperation var5 = this.A.createOperation(var2, var3, var4);
      IEGeneric var6 = this.pC.pC(var1.r, var3, var5);
      this.pC.pC(var1.r, var6);
      this.pC.A(var1.r, var6);
      this.pC.pC(var1.r, this.pC.vP, this.pC.hK);
      this.pC.kS(var1.r, var6);
   }

   void E(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((KD)var1.insn, 0, var1.address);
      IEGeneric var3 = this.pC.pC((KD)var1.insn, 1, var1.address);
      IEOperation var4 = this.A.createOperation(OperationType.AND, var2, var3);
      this.pC.pC(var1.r, var4);
      this.pC.A(var1.r, var4);
      this.pC.pC(var1.r, this.pC.vP, this.pC.hK);
      this.pC.kS(var1.r, var4);
   }

   void kS(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.pC.pC((KD)var1.insn, 0, var1.address);
      IEGeneric var4 = this.pC.pC((KD)var1.insn, 1, var1.address);
      this.pC(var1.r, var3, var4, false, var2, true, true);
   }

   void sY(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((KD)var1.insn, 0, var1.address);
      this.pC.pC(var1.r, var2, this.A.createOperation(OperationType.XOR, var2, var2));
      this.pC.pC(var1.r, this.pC.WR, this.pC.Er);
      this.pC.pC(var1.r, this.pC.NS, this.pC.hK);
      this.pC.pC(var1.r, this.pC.vP, this.pC.hK);
      this.pC.pC(var1.r, this.pC.xC, this.pC.hK);
   }

   void ys(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((KD)var1.insn, 0, var1.address);
      this.pC.pC(var1.r, var2, this.pC.Ek);
   }

   void ld(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((KD)var1.insn, 0, var1.address);
      IEOperation var3 = this.A.createOperation(OperationType.NOT, var2);
      IEGeneric var4 = this.pC.pC(var1.r, var2, var3);
      this.pC.pC(var1.r, var4);
      this.pC.A(var1.r, var4);
      this.pC.pC(var1.r, this.pC.vP, this.pC.hK);
      this.pC.pC(var1.r, this.pC.fI, this.pC.Er);
      this.pC.kS(var1.r, var4);
   }

   void gp(ConverterInstructionEntry var1) {
      IEImm var2 = this.A.createImm(0L, 8);
      IEGeneric var3 = this.pC.pC((KD)var1.insn, 0, var1.address);
      IEGeneric var5 = this.pC.wS(var1.r, var3);
      IEOperation var6 = this.A.createOperation(OperationType.SUB, var2, var3);
      IEGeneric var7 = this.pC.pC(var1.r, var3, var6);
      this.pC.pC(var1.r, var7);
      this.pC.A(var1.r, var7);
      this.pC.A(var1.r, var2, var5, var7);
      this.pC.A(var1.r, var2, var5, var7, false, true, true);
      this.pC.kS(var1.r, var7);
   }

   void oT(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((KD)var1.insn, 0, var1.address);
      IEGeneric var3 = this.pC.pC((KD)var1.insn, 1, var1.address);
      IEOperation var4 = this.A.createOperation(OperationType.OR, var2, this.A.createOperation(OperationType.SHL, this.pC.z, var3));
      this.pC.pC(var1.r, var2, var4);
   }

   void fI(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((KD)var1.insn, 0, var1.address);
      IEGeneric var3 = this.pC.pC((KD)var1.insn, 1, var1.address);
      IEOperation var4 = this.A
         .createOperation(OperationType.AND, var2, this.A.createOperation(OperationType.NOT, this.A.createOperation(OperationType.SHL, this.pC.z, var3)));
      this.pC.pC(var1.r, var2, var4);
   }

   void WR(ConverterInstructionEntry var1) {
      this.A(var1, 0);
   }

   void NS(ConverterInstructionEntry var1) {
      this.A(var1, 1);
   }

   private void A(ConverterInstructionEntry var1, int var2) {
      IEGeneric var3 = this.pC.pC((KD)var1.insn, 0, var1.address);
      IEGeneric var4 = this.pC.wS(var1.r, var3);
      IEOperation var5 = this.A.createOperation(OperationType.SHL, var3, this.pC.z);
      if (var2 == 1) {
         var5 = this.A.createOperation(OperationType.OR, var5, this.pC.fI.zeroExtend(8));
      }

      this.pC.pC(var1.r, var3, var5);
      this.pC.pC(var1.r, var3);
      this.pC.A(var1.r, var3);
      this.pC.pC(var1.r, this.pC.fI, var4.bit(7));
      this.pC.pC(var1.r, this.pC.ED, var4.bit(3));
      this.pC.pC(var1.r, this.pC.vP, this.A.createOperation(OperationType.XOR, this.pC.NS, this.pC.fI));
      this.pC.kS(var1.r, var3);
   }

   void vP(ConverterInstructionEntry var1) {
      this.kS(var1, 0);
   }

   void xC(ConverterInstructionEntry var1) {
      this.kS(var1, 1);
   }

   void ED(ConverterInstructionEntry var1) {
      this.kS(var1, 2);
   }

   private void kS(ConverterInstructionEntry var1, int var2) {
      IEGeneric var3 = this.pC.pC((KD)var1.insn, 0, var1.address);
      IEGeneric var4 = this.pC.wS(var1.r, var3);
      OperationType var5 = var2 != 2 ? OperationType.SHR : OperationType.SAR;
      IEOperation var6 = this.A.createOperation(var5, var3, this.pC.z);
      if (var2 == 1) {
         var6 = this.A.createOperation(OperationType.OR, var6, this.pC.fI.zeroExtend(8).leftShift(7));
      }

      this.pC.pC(var1.r, var3, var6);
      this.pC.pC(var1.r, var3);
      this.pC.A(var1.r, var3);
      this.pC.pC(var1.r, this.pC.fI, var4.bit(0));
      this.pC.pC(var1.r, this.pC.vP, this.A.createOperation(OperationType.XOR, this.pC.NS, this.pC.fI));
      this.pC.kS(var1.r, var3);
   }

   void Sc(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((KD)var1.insn, 0, var1.address);
      IECompose var3 = this.A.createCompose(var2.slice(4, 8), var2.slice(0, 4));
      this.pC.pC(var1.r, var2, var3);
   }

   void pC(ConverterInstructionEntry var1, int var2) {
      IEVar var3 = this.pC.eP[var2];
      this.pC.pC(var1.r, var3, this.pC.Er);
   }

   void ah(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((KD)var1.insn, 0, var1.address);
      IEGeneric var3 = this.pC.pC((KD)var1.insn, 1, var1.address);
      this.pC.pC(var1.r, this.pC.Sc, this.A.createOperation(OperationType.SHR, var2, var3).bit(0));
   }

   void eP(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((KD)var1.insn, 0, var1.address);
      IEGeneric var3 = this.pC.pC((KD)var1.insn, 1, var1.address);
      IEOperation var4 = this.A.createOperation(OperationType.NOT, this.A.createOperation(OperationType.SHL, this.pC.z, var3));
      var4 = this.A.createOperation(OperationType.AND, var2, var4);
      var4 = this.A.createOperation(OperationType.OR, var4, this.A.createOperation(OperationType.SHL, this.pC.Sc.zeroExtend(8), var3));
      this.pC.pC(var1.r, var2, var4);
   }
}
