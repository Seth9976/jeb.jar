package com.pnfsoftware.jeb.corei.parsers.avr;

import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ConverterInstructionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;

class Ws {
   cq pC;
   IERoutineContext A;

   Ws(cq var1) {
      this.pC = var1;
   }

   void pC(ConverterInstructionEntry var1) {
      this.pC.A.A(var1.r, this.pC.Ab);
      this.pC.UT(var1.r, this.pC.Ab);
   }

   void pC(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3;
      if (var2) {
         var3 = this.pC.E();
      } else {
         var3 = this.pC.pC((KD)var1.insn, 0, var1.address);
      }

      IEImm var4 = this.A.createImm(var1.address + ((KD)var1.insn).getSize(), this.pC.UT.getBitsize());
      this.pC.A.pC(var1.r, var4);
      if (var3.equals(var4)) {
         this.pC.UT(var1.r, var3);
      } else {
         this.pC.E(var1.r, var3);
      }
   }

   void A(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3;
      if (var2) {
         var3 = this.pC.E();
      } else {
         var3 = this.pC.pC((KD)var1.insn, 0, var1.address);
      }

      this.pC.UT(var1.r, var3);
   }

   void pC(ConverterInstructionEntry var1, String var2) {
      IEGeneric var3 = this.pC.pC((KD)var1.insn, 0, var1.address);
      IEImm var4 = this.A.createImm(var1.address + ((KD)var1.insn).getSize(), this.pC.UT.getBitsize());
      boolean var5 = false;
      IEVar var6;
      switch (var2) {
         case "breq":
            var6 = this.pC.WR;
            break;
         case "brne":
            var6 = this.pC.WR;
            var5 = true;
            break;
         case "brcs":
         case "brlo":
            var6 = this.pC.fI;
            break;
         case "brcc":
         case "brsh":
            var6 = this.pC.fI;
            var5 = true;
            break;
         case "brmi":
            var6 = this.pC.NS;
            break;
         case "brpl":
            var6 = this.pC.NS;
            var5 = true;
            break;
         case "brge":
            var6 = this.pC.xC;
            var5 = true;
            break;
         case "brlt":
            var6 = this.pC.xC;
            break;
         case "brhs":
            var6 = this.pC.ED;
            break;
         case "brhc":
            var6 = this.pC.ED;
            var5 = true;
            break;
         case "brts":
            var6 = this.pC.Sc;
            break;
         case "brtc":
            var6 = this.pC.Sc;
            var5 = true;
            break;
         case "brvs":
            var6 = this.pC.vP;
            break;
         case "brvc":
            var6 = this.pC.vP;
            var5 = true;
            break;
         case "brie":
            var6 = this.pC.ah;
            break;
         case "brid":
            var6 = this.pC.ah;
            var5 = true;
            break;
         default:
            throw new RuntimeException();
      }

      IECond var7 = !var5 ? this.A.createCond(var6, var3, var4) : this.A.createCond(var6, var4, var3);
      this.pC.UT(var1.r, var7);
   }

   void A(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((KD)var1.insn, 0, var1.address);
      IEGeneric var3 = this.pC.pC((KD)var1.insn, 1, var1.address);
      IEOperation var4 = this.A.createOperation(OperationType.LOG_EQ, var2, var3);
      long var5 = var1.address + ((KD)var1.insn).getSize();
      long var7 = ((ICodePointer)((KD)var1.insn).getBreakingFlow(var1.address).getTargets().get(1)).getAddress();
      this.pC.UT(var1.r, this.A.createCond(var4, this.pC.pC(var7), this.pC.pC(var5)));
   }

   void kS(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.pC.pC((KD)var1.insn, 0, var1.address);
      IEGeneric var4 = this.pC.pC((KD)var1.insn, 1, var1.address);
      IEOperation var5 = this.A.createOperation(OperationType.AND, var3, this.A.createOperation(OperationType.SHL, this.pC.z, var4));
      var5 = this.A.createOperation(var2 ? OperationType.LOG_EQ : OperationType.LOG_NEQ, var5, this.pC.rl);
      long var6 = var1.address + ((KD)var1.insn).getSize();
      long var8 = ((ICodePointer)((KD)var1.insn).getBreakingFlow(var1.address).getTargets().get(1)).getAddress();
      this.pC.UT(var1.r, this.A.createCond(var5, this.pC.pC(var8), this.pC.pC(var6)));
   }
}
