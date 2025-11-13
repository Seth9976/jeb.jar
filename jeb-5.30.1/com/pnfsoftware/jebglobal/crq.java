package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ConverterInstructionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import java.util.List;

class crq {
   crx q;
   IERoutineContext RF;

   crq(crx var1) {
      this.q = var1;
   }

   void q(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.q.RF((ctc)var1.insn, 1, var3.getBitsize(), var1.address);
      IEOperation var5 = this.RF.createOperation(OperationType.AND, var3, var4);
      this.q.q(var3, var5, var1);
      if (!var2) {
         this.q(var3, var1.r);
      }
   }

   void RF(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.q.RF((ctc)var1.insn, 1, var3.getBitsize(), var1.address);
      IEOperation var5 = this.RF.createOperation(OperationType.OR, var3, var4);
      this.q.q(var3, var5, var1);
      if (!var2) {
         this.q(var3, var1.r);
      }
   }

   void xK(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.q.RF((ctc)var1.insn, 1, var3.getBitsize(), var1.address);
      IEOperation var5 = this.RF.createOperation(OperationType.XOR, var3, var4);
      this.q.q(var3, var5, var1);
      if (!var2) {
         this.q(var3, var1.r);
      }
   }

   void q(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEOperation var3 = this.RF.createOperation(OperationType.NOT, var2);
      this.q.q(var2, var3, var1);
   }

   void Dw(ConverterInstructionEntry var1, boolean var2) {
      if (var2) {
         var1.r.add(this.RF.createNop());
      } else {
         IEGeneric var3 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
         IEGeneric var4 = this.q.RF((ctc)var1.insn, 1, var3.getBitsize(), var1.address);
         IEOperation var5 = this.RF.createOperation(OperationType.AND, var3, var4);
         this.q(var5, var1.r);
      }
   }

   private void q(IEGeneric var1, List var2) {
      this.q.q(var1, var2);
      var2.add(this.RF.createAssign(this.q.Cl, this.q.Eq));
      var2.add(this.RF.createAssign(this.q.WX, this.q.Eq));
   }
}
