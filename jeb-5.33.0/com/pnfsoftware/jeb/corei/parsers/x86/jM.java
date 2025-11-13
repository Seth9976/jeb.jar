package com.pnfsoftware.jeb.corei.parsers.x86;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ConverterInstructionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import java.util.List;

class jM {
   Yd pC;
   IERoutineContext A;

   jM(Yd var1) {
      this.pC = var1;
   }

   void pC(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.pC.A((vh)var1.insn, 1, var3.getBitsize(), var1.address);
      IEOperation var5 = this.A.createOperation(OperationType.AND, var3, var4);
      this.pC.pC(var3, var5, var1);
      if (!var2) {
         this.pC(var3, var1.r);
      }
   }

   void A(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.pC.A((vh)var1.insn, 1, var3.getBitsize(), var1.address);
      IEOperation var5 = this.A.createOperation(OperationType.OR, var3, var4);
      this.pC.pC(var3, var5, var1);
      if (!var2) {
         this.pC(var3, var1.r);
      }
   }

   void kS(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.pC.A((vh)var1.insn, 1, var3.getBitsize(), var1.address);
      IEOperation var5 = this.A.createOperation(OperationType.XOR, var3, var4);
      this.pC.pC(var3, var5, var1);
      if (!var2) {
         this.pC(var3, var1.r);
      }
   }

   void pC(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEOperation var3 = this.A.createOperation(OperationType.NOT, var2);
      this.pC.pC(var2, var3, var1);
   }

   void wS(ConverterInstructionEntry var1, boolean var2) {
      if (var2) {
         var1.r.add(this.A.createNop());
      } else {
         IEGeneric var3 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
         IEGeneric var4 = this.pC.A((vh)var1.insn, 1, var3.getBitsize(), var1.address);
         IEOperation var5 = this.A.createOperation(OperationType.AND, var3, var4);
         this.pC(var5, var1.r);
      }
   }

   private void pC(IEGeneric var1, List var2) {
      this.pC.pC(var1, var2);
      var2.add(this.A.createAssign(this.pC.Cu, this.pC.Bc));
      var2.add(this.A.createAssign(this.pC.Er, this.pC.Bc));
   }
}
