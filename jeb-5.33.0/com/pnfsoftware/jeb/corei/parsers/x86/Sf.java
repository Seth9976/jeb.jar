package com.pnfsoftware.jeb.corei.parsers.x86;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ConverterInstructionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jebglobal.ajr;
import java.util.ArrayList;
import java.util.List;

public class Sf {
   Yd pC;
   IERoutineContext A;

   Sf(Yd var1) {
      this.pC = var1;
   }

   void pC(ConverterInstructionEntry var1) {
      var1.r.add(this.A.createNop());
   }

   void A(ConverterInstructionEntry var1) {
      IECompose var2 = this.A.createCompose(this.pC.Er, this.pC.OI, this.pC.FE, this.pC.Bc, this.pC.Aj, this.pC.Bc, this.pC.EX, this.pC.LM);
      var1.r.add(this.A.createAssign(this.pC.Sc.slice(8, 16), var2));
   }

   void kS(ConverterInstructionEntry var1) {
      var1.r.add(this.A.createAssign(this.pC.Er, this.pC.Sc.bit(8)));
      var1.r.add(this.A.createAssign(this.pC.FE, this.pC.Sc.bit(10)));
      var1.r.add(this.A.createAssign(this.pC.Aj, this.pC.Sc.bit(12)));
      var1.r.add(this.A.createAssign(this.pC.EX, this.pC.Sc.bit(14)));
      var1.r.add(this.A.createAssign(this.pC.LM, this.pC.Sc.bit(15)));
   }

   void wS(ConverterInstructionEntry var1) {
      this.pC(var1, 0);
   }

   void UT(ConverterInstructionEntry var1) {
      this.pC(var1, 1);
   }

   void E(ConverterInstructionEntry var1) {
      this.pC(var1, 2);
   }

   void sY(ConverterInstructionEntry var1) {
      this.pC(var1, 3);
   }

   private void pC(ConverterInstructionEntry var1, int var2) {
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.pC.pC((vh)var1.insn, 1, 0, var1.address);
      int var5 = var3.getBitsize();
      IEOperation var10 = EUtil.remU(var4, this.A.createImm(var5, var4.getBitsize()));
      IEGeneric var6 = EUtil.shr(var3, var10).bit(0);
      var1.r.add(this.A.createAssign(this.pC.Er, var6));
      Object var7;
      switch (var2) {
         case 1:
            var7 = EUtil.notB(this.pC.Er);
            break;
         case 2:
            var7 = this.pC.Bc;
            break;
         case 3:
            var7 = this.pC.OI;
            break;
         default:
            return;
      }

      IEOperation var8 = EUtil.andB(var3, EUtil.notB(EUtil.shl(this.pC.A(var3), var10)));
      IEOperation var9 = EUtil.shl(((IEGeneric)var7).zeroExtend(var3.getBitsize()), var10);
      var1.r.add(this.A.createAssign(var3, EUtil.orB(var8, var9)));
   }

   void ys(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.Sc.part(8);
      int var3 = ((vh)var1.insn).ys();
      IEGeneric var4 = this.pC.UO.part(var3);
      IEGeneric var5 = var2.zeroExtend(var3);
      int var6 = MG.pC(((vh)var1.insn).pC(0));
      IEMem var7 = this.pC.pC(var6, EUtil.add(var4, var5), 8);
      var1.r.add(this.A.createAssign(var2, var7));
   }

   void ld(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.Sc.part(8);
      IECond var3 = this.A.createCond(this.pC.Er, this.pC.xM, this.pC.RW);
      var1.r.add(this.A.createAssign(var2, var3));
   }

   void gp(ConverterInstructionEntry var1) {
      IEUntranslatedInstruction var2 = this.pC.A(var1);
      var2.addSideEffectDefinedVariable(this.pC.EX);
   }

   void oT(ConverterInstructionEntry var1) {
      this.pC.pC(var1, "read_IO", 2);
   }

   void fI(ConverterInstructionEntry var1) {
      this.pC.pC(var1, "write_io", 0);
   }

   void WR(ConverterInstructionEntry var1) {
      int var2 = ((vh)var1.insn).ys();
      int var3 = this.pC.pC((vh)var1.insn, 0, 0, var1.address).getBitsize() / 8;
      IECond var4 = this.A.createCond(this.pC.os, this.A.createImm(-var3, var2), this.A.createImm(var3, var2));
      IEGeneric var5 = this.pC.Ek.part(var2);
      this.pC.pC(var1, "read_IO", 2);
      this.pC.pC(var5, this.A.createOperation(OperationType.ADD, var5, var4), var1);
   }

   void NS(ConverterInstructionEntry var1) {
      if (!eS.pC((vh)var1.insn)) {
         this.WR(var1);
      } else {
         int var2 = ((vh)var1.insn).ys();
         IEGeneric var3 = this.pC.ah.part(var2);
         var1.r.add(this.A.createJump(var1.irAddress + 5, this.A.createOperation(OperationType.LOG_EQ, var3, this.pC.pC(var3))));
         this.WR(var1);
         this.pC.pC(var3, this.A.createOperation(OperationType.SUB, var3, this.A.createImm(1L, var3.getBitsize())), var1);
         var1.r.add(this.A.createJump(var1.irAddress, null));
      }
   }

   void vP(ConverterInstructionEntry var1) {
      int var2 = ((vh)var1.insn).ys();
      int var3 = this.pC.pC((vh)var1.insn, 1, 0, var1.address).getBitsize() / 8;
      IECond var4 = this.A.createCond(this.pC.os, this.A.createImm(-var3, var2), this.A.createImm(var3, var2));
      IEGeneric var5 = this.pC.z.part(var2);
      this.pC.pC(var1, "write_IO", 0);
      this.pC.pC(var5, this.A.createOperation(OperationType.ADD, var5, var4), var1);
   }

   void xC(ConverterInstructionEntry var1) {
      if (!eS.pC((vh)var1.insn)) {
         this.vP(var1);
      } else {
         int var2 = ((vh)var1.insn).ys();
         IEGeneric var3 = this.pC.ah.part(var2);
         var1.r.add(this.A.createJump(var1.irAddress + 5, this.A.createOperation(OperationType.LOG_EQ, var3, this.pC.pC(var3))));
         this.vP(var1);
         this.pC.pC(var3, this.A.createOperation(OperationType.SUB, var3, this.A.createImm(1L, var3.getBitsize())), var1);
         var1.r.add(this.A.createJump(var1.irAddress, null));
      }
   }

   void pC(ConverterInstructionEntry var1, IEVar var2, String var3) {
      IEUntranslatedInstruction var4 = this.pC.pC(var1, var3, 2);
      var4.addSideEffectDefinedVariable(var2);
   }

   void pC(ConverterInstructionEntry var1, String var2) {
      IEUntranslatedInstruction var3 = this.pC.pC(var1, var2, 0);
      if (var3.getParameterExpressions().isEmpty()) {
         if (((vh)var1.insn).pC() == 204) {
            var3.setParameterExpressions(ajr.pC(3L, 8));
         } else if (((vh)var1.insn).pC() == 241) {
            var3.setParameterExpressions(ajr.pC(1L, 8));
         } else {
            Assert.fail("unexpected interrupt instruction");
         }
      }
   }

   void ED(ConverterInstructionEntry var1) {
      var1.r.add(this.A.createJump(var1.irAddress + 2, EUtil.eq(this.pC.Cu, this.pC.Bc)));
      this.pC.pC(var1, "overflow_trap", 0);
   }

   void Sc(ConverterInstructionEntry var1) {
      this.pC.pC(var1, "interrupt_return", 0);
   }

   void ah(ConverterInstructionEntry var1) {
      IEUntranslatedInstruction var2 = this.pC.A(var1);
      var2.addSideEffectDefinedVariable(this.pC.EX);
   }

   void eP(ConverterInstructionEntry var1) {
      IEUntranslatedInstruction var2 = this.pC.A(var1);
      var2.setParameterExpressions(this.pC.ah);
      var2.setReturnExpressions(this.pC.Sc, this.pC.eP);
   }

   void UO(ConverterInstructionEntry var1) {
      IEUntranslatedInstruction var2 = this.pC.A(var1);
      var2.setParameterExpressions(this.pC.ah, this.pC.Sc, this.pC.eP);
   }

   void Ab(ConverterInstructionEntry var1) {
      IEUntranslatedInstruction var2 = this.pC.A(var1);
      var2.setReturnExpressions(this.pC.Sc, this.pC.eP);
   }

   void rl(ConverterInstructionEntry var1) {
      IEUntranslatedInstruction var2 = this.pC.A(var1);
      var2.setReturnExpressions(this.pC.Sc, this.pC.eP);
   }

   void z(ConverterInstructionEntry var1) {
      IEUntranslatedInstruction var2 = this.pC.A(var1);
      var2.setParameterExpressions(this.pC.Sc, this.pC.ah);
      var2.setReturnExpressions(this.pC.Sc, this.pC.UO, this.pC.ah, this.pC.eP);
   }

   void Ek(ConverterInstructionEntry var1) {
      this.pC.A(var1);
   }

   void hK(ConverterInstructionEntry var1) {
      IEUntranslatedInstruction var2 = this.pC.A(var1);
      var2.setParameterExpressions(this.pC.ah);
      var2.setReturnExpressions(this.pC.Sc, this.pC.eP);
   }

   void Er(ConverterInstructionEntry var1) {
      IEUntranslatedInstruction var2 = this.pC.A(var1);
      var2.setParameterExpressions(this.pC.ah, this.pC.Sc, this.pC.eP);
   }

   void FE(ConverterInstructionEntry var1) {
      IEUntranslatedInstruction var2 = this.pC.A(var1);
      var2.setReturnExpression(this.pC.ED);
   }

   void Aj(ConverterInstructionEntry var1) {
      IEUntranslatedInstruction var2 = this.pC.pC(var1, null, 2);
      var2.addSideEffectDefinedVariable(this.pC.EX);
   }

   void EX(ConverterInstructionEntry var1) {
      IEUntranslatedInstruction var2 = this.pC.pC(var1, null, 2);
      var2.addSideEffectDefinedVariable(this.pC.EX);
   }

   void LM(ConverterInstructionEntry var1) {
      IEUntranslatedInstruction var2 = this.pC.pC(var1, null, 1);
      ArrayList var3 = new ArrayList(var2.getParameterExpressions());
      var3.add(this.pC.LA[0].part(64));
      var2.setParameterExpressions(var3);
   }

   void mv(ConverterInstructionEntry var1) {
      IEUntranslatedInstruction var2 = this.pC.A(var1);
      List var3 = var2.getParameterExpressions();
      ArrayList var4 = new ArrayList();
      var4.add((IEGeneric)var3.get(1));
      var4.add((IEGeneric)var3.get(0));
      ArrayList var5 = new ArrayList();
      var5.add((IEGeneric)var3.get(2));
      var5.add(this.pC.eP.part(((IEGeneric)var3.get(2)).getBitsize()));
      var2.setParameterExpressions(var5);
      var2.setReturnExpressions(var4);
   }
}
