package com.pnfsoftware.jeb.corei.parsers.x86;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ConverterInstructionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.util.base.Assert;
import java.util.List;

public class co {
   Yd pC;
   IERoutineContext A;

   co(Yd var1) {
      this.pC = var1;
   }

   void pC(ConverterInstructionEntry var1) {
      int var2 = ((vh)var1.insn).pC;
      Assert.a(this.pC.Ab.getBitsize() == var2);
      int var3 = ((vh)var1.insn).sY();
      IEGeneric var4 = this.pC.pC((vh)var1.insn, 0, -var3, var1.address);
      this.pC(var2, var3, var4, var1.r);
   }

   void pC(int var1, int var2, IEGeneric var3, List var4) {
      Assert.a(var3.getBitsize() == var2);
      IEGeneric var5 = var3;
      if (EUtil.getUsedVarIds(var3).contains(256)) {
         var5 = this.pC.A(var2, var4);
         var4.add(this.A.createAssign(var5, var3));
      }

      IEGeneric var6 = this.pC.Ab.part(var1);
      IEImm var7 = this.A.createImm(var2 / 8, var1);
      var4.add(this.A.createAssign(var6, this.A.createOperation(OperationType.SUB, var6, var7)));
      var4.add(this.A.createAssign(this.pC.createStackMemoryAccess(var6, var2), var5));
   }

   void A(ConverterInstructionEntry var1) {
      int var2 = ((vh)var1.insn).pC;
      Assert.a(this.pC.Ab.getBitsize() == var2);
      int var3 = ((vh)var1.insn).sY();
      IEGeneric var4 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      this.A(var2, var3, var4, var1.r);
   }

   void A(int var1, int var2, IEGeneric var3, List var4) {
      int var5 = var3.getBitsize();
      IEGeneric var6 = this.pC.Ab.part(var1);
      if (var3 instanceof IEMem && EUtil.getUsedVarIds(var3).contains(256)) {
         IEGeneric var9 = this.pC.A(var5, var4);
         var4.add(this.A.createAssign(var9, this.pC.createStackMemoryAccess(var6, var5)));
         IEImm var8 = this.A.createImm(var2 / 8, var1);
         var4.add(this.A.createAssign(var6, this.A.createOperation(OperationType.ADD, var6, var8)));
         var4.add(this.A.createAssign(var3, var9));
      } else {
         var4.add(this.A.createAssign(var3, this.pC.createStackMemoryAccess(var6, var5)));
         IEImm var7 = this.A.createImm(var2 / 8, var1);
         var4.add(this.A.createAssign(var6, this.A.createOperation(OperationType.ADD, var6, var7)));
      }
   }

   void kS(ConverterInstructionEntry var1) {
      int var2 = ((vh)var1.insn).sY();
      if (var2 != 16 && var2 != 32) {
         throw new RuntimeException();
      } else {
         int var3 = ((vh)var1.insn).pC;
         Assert.a(this.pC.Ab.getBitsize() == var3);
         IEImm var4 = this.A.createImm(var2 / 8, var3);
         IEVar var5 = this.pC.pC(var3);
         var1.r.add(this.A.createAssign(var5, this.pC.Ab));
         var1.r.add(this.A.createAssign(this.pC.Ab, this.A.createOperation(OperationType.SUB, this.pC.Ab, var4)));
         var1.r.add(this.A.createAssign(this.pC.createStackMemoryAccess(this.pC.Ab, var2), this.pC.Sc.part(var2)));
         var1.r.add(this.A.createAssign(this.pC.Ab, this.A.createOperation(OperationType.SUB, this.pC.Ab, var4)));
         var1.r.add(this.A.createAssign(this.pC.createStackMemoryAccess(this.pC.Ab, var2), this.pC.ah.part(var2)));
         var1.r.add(this.A.createAssign(this.pC.Ab, this.A.createOperation(OperationType.SUB, this.pC.Ab, var4)));
         var1.r.add(this.A.createAssign(this.pC.createStackMemoryAccess(this.pC.Ab, var2), this.pC.eP.part(var2)));
         var1.r.add(this.A.createAssign(this.pC.Ab, this.A.createOperation(OperationType.SUB, this.pC.Ab, var4)));
         var1.r.add(this.A.createAssign(this.pC.createStackMemoryAccess(this.pC.Ab, var2), this.pC.UO.part(var2)));
         var1.r.add(this.A.createAssign(this.pC.Ab, this.A.createOperation(OperationType.SUB, this.pC.Ab, var4)));
         var1.r.add(this.A.createAssign(this.pC.createStackMemoryAccess(this.pC.Ab, var2), var5.part(var2)));
         var1.r.add(this.A.createAssign(this.pC.Ab, this.A.createOperation(OperationType.SUB, this.pC.Ab, var4)));
         var1.r.add(this.A.createAssign(this.pC.createStackMemoryAccess(this.pC.Ab, var2), this.pC.rl.part(var2)));
         var1.r.add(this.A.createAssign(this.pC.Ab, this.A.createOperation(OperationType.SUB, this.pC.Ab, var4)));
         var1.r.add(this.A.createAssign(this.pC.createStackMemoryAccess(this.pC.Ab, var2), this.pC.z.part(var2)));
         var1.r.add(this.A.createAssign(this.pC.Ab, this.A.createOperation(OperationType.SUB, this.pC.Ab, var4)));
         var1.r.add(this.A.createAssign(this.pC.createStackMemoryAccess(this.pC.Ab, var2), this.pC.Ek.part(var2)));
      }
   }

   void wS(ConverterInstructionEntry var1) {
      int var2 = ((vh)var1.insn).sY();
      if (var2 != 16 && var2 != 32) {
         throw new RuntimeException();
      } else {
         int var3 = ((vh)var1.insn).pC;
         Assert.a(this.pC.Ab.getBitsize() == var3);
         IEImm var4 = this.A.createImm(var2 / 8, var3);
         var1.r.add(this.A.createAssign(this.pC.Ek.part(var2), this.pC.createStackMemoryAccess(this.pC.Ab, var2)));
         var1.r.add(this.A.createAssign(this.pC.Ab, this.A.createOperation(OperationType.ADD, this.pC.Ab, var4)));
         var1.r.add(this.A.createAssign(this.pC.z.part(var2), this.pC.createStackMemoryAccess(this.pC.Ab, var2)));
         var1.r.add(this.A.createAssign(this.pC.Ab, this.A.createOperation(OperationType.ADD, this.pC.Ab, var4)));
         var1.r.add(this.A.createAssign(this.pC.rl.part(var2), this.pC.createStackMemoryAccess(this.pC.Ab, var2)));
         var1.r.add(this.A.createAssign(this.pC.Ab, this.A.createOperation(OperationType.ADD, this.pC.Ab, var4)));
         var1.r.add(this.A.createAssign(this.pC.Ab, this.A.createOperation(OperationType.ADD, this.pC.Ab, var4)));
         var1.r.add(this.A.createAssign(this.pC.UO.part(var2), this.pC.createStackMemoryAccess(this.pC.Ab, var2)));
         var1.r.add(this.A.createAssign(this.pC.Ab, this.A.createOperation(OperationType.ADD, this.pC.Ab, var4)));
         var1.r.add(this.A.createAssign(this.pC.eP.part(var2), this.pC.createStackMemoryAccess(this.pC.Ab, var2)));
         var1.r.add(this.A.createAssign(this.pC.Ab, this.A.createOperation(OperationType.ADD, this.pC.Ab, var4)));
         var1.r.add(this.A.createAssign(this.pC.ah.part(var2), this.pC.createStackMemoryAccess(this.pC.Ab, var2)));
         var1.r.add(this.A.createAssign(this.pC.Ab, this.A.createOperation(OperationType.ADD, this.pC.Ab, var4)));
         var1.r.add(this.A.createAssign(this.pC.Sc.part(var2), this.pC.createStackMemoryAccess(this.pC.Ab, var2)));
         var1.r.add(this.A.createAssign(this.pC.Ab, this.A.createOperation(OperationType.ADD, this.pC.Ab, var4)));
      }
   }

   void UT(ConverterInstructionEntry var1) {
      int var2 = ((vh)var1.insn).pC;
      Assert.a(this.pC.Ab.getBitsize() == var2);
      int var3 = ((vh)var1.insn).sY();
      IEImm var4 = this.A.createImm(var3 / 8, var2);
      var1.r.add(this.A.createAssign(this.pC.Ab, this.A.createOperation(OperationType.SUB, this.pC.Ab, var4)));
      IECompose var5 = this.A
         .createCompose(
            this.pC.Er,
            this.pC.OI,
            this.pC.FE,
            this.pC.Bc,
            this.pC.Aj,
            this.pC.Bc,
            this.pC.EX,
            this.pC.LM,
            this.pC.mv,
            this.pC.sO,
            this.pC.os,
            this.pC.Cu,
            this.pC.hZ,
            this.pC.UW,
            this.pC.Bc
         );
      if (var3 == 32 || var3 == 64) {
         var5 = this.A.createCompose(var5, this.pC.Bc, this.pC.Bc, this.pC.DQ, this.pC.ZN, this.pC.OB, this.pC.pF);
      }

      var1.r.add(this.A.createAssign(this.pC.createStackMemoryAccess(this.pC.Ab, var3), var5.zeroExtend(var3)));
   }

   void E(ConverterInstructionEntry var1) {
      int var2 = ((vh)var1.insn).pC;
      Assert.a(this.pC.Ab.getBitsize() == var2);
      int var3 = ((vh)var1.insn).sY();
      IEImm var4 = this.A.createImm(var3 / 8, var2);
      IEGeneric var5 = this.pC.A(var3, var1.r);
      var1.r.add(this.A.createAssign(var5, this.pC.createStackMemoryAccess(this.pC.Ab, var3)));
      var1.r.add(this.A.createAssign(this.pC.Er, var5.bit(0)));
      var1.r.add(this.A.createAssign(this.pC.FE, var5.bit(2)));
      var1.r.add(this.A.createAssign(this.pC.Aj, var5.bit(4)));
      var1.r.add(this.A.createAssign(this.pC.EX, var5.bit(6)));
      var1.r.add(this.A.createAssign(this.pC.LM, var5.bit(7)));
      var1.r.add(this.A.createAssign(this.pC.mv, var5.bit(8)));
      var1.r.add(this.A.createAssign(this.pC.sO, var5.bit(9)));
      var1.r.add(this.A.createAssign(this.pC.os, var5.bit(10)));
      var1.r.add(this.A.createAssign(this.pC.Cu, var5.bit(11)));
      var1.r.add(this.A.createAssign(this.pC.UW, var5.bit(14)));
      if (var3 == 32 || var3 == 64) {
         var1.r.add(this.A.createAssign(this.pC.PR, this.pC.Bc));
         var1.r.add(this.A.createAssign(this.pC.DQ, var5.bit(18)));
         var1.r.add(this.A.createAssign(this.pC.pF, var5.bit(21)));
      }

      var1.r.add(this.A.createAssign(this.pC.Ab, this.A.createOperation(OperationType.ADD, this.pC.Ab, var4)));
   }

   void sY(ConverterInstructionEntry var1) {
      int var2 = ((vh)var1.insn).pC;
      Assert.a(this.pC.Ab.getBitsize() == var2);
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      Assert.a(var3.getBitsize() == 16 && var3 instanceof IEImm);
      int var4 = (int)EUtil.evaluateUnsignedLong_preVerified(var3);
      IEGeneric var5 = this.pC.pC((vh)var1.insn, 1, 0, var1.address);
      Assert.a(var5.getBitsize() == 8 && var5 instanceof IEImm);
      int var6 = (int)EUtil.evaluate_preVerified(var5)._remU(this.A.createImm(32L, 8)).getValueAsUnsignedLong();
      int var7 = ((vh)var1.insn).sY();
      IEGeneric var8 = this.pC.rl.part(var7);
      IEGeneric var9 = this.pC.Ab.part(var7);
      this.pC(var2, var7, var8, var1.r);
      IEGeneric var10 = this.pC.A(var7, var1.r);
      var1.r.add(this.A.createAssign(var10, this.pC.Ab.part(var7)));
      if (var6 != 0) {
         for (int var11 = 1; var11 < var6; var11++) {
            IEGeneric var12 = this.pC.rl.part(var2);
            var1.r.add(this.A.createAssign(var12, EUtil.sub(var12, this.A.createImm(var7 / 8, var7))));
            this.pC(var2, var7, this.pC.createStackMemoryAccess(this.pC.rl.part(var2), var7), var1.r);
         }

         this.pC(var2, var7, var10, var1.r);
      }

      var1.r.add(this.A.createAssign(var8, var10));
      var1.r.add(this.A.createAssign(var9, EUtil.sub(var9, this.A.createImm(var4, var9.getBitsize()))));
   }

   void ys(ConverterInstructionEntry var1) {
      int var2 = ((vh)var1.insn).pC;
      Assert.a(this.pC.Ab.getBitsize() == var2);
      int var3 = ((vh)var1.insn).sY();
      IEGeneric var4 = this.pC.rl.part(var2);
      IEGeneric var5 = this.pC.Ab.part(var2);
      var1.r.add(this.A.createAssign(var5, var4));
      IEGeneric var6 = this.pC.rl.part(var3);
      this.A(var2, var3, var6, var1.r);
   }
}
