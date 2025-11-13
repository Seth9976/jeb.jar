package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import java.util.List;

public class cfa {
   cfb pC;
   IERoutineContext A;

   public cfa(cfb var1) {
      this.pC = var1;
   }

   public void pC(cfq var1, List var2, boolean var3) {
      IEGeneric var4 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[0]);
      IEGeneric var5 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[1]).part(32);
      IEGeneric var6 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[2]).part(32);
      IEOperation var7 = this.A.createOperation(var3 ? OperationType.LT_S : OperationType.LT_U, var5, var6);
      var2.add(this.A.createAssign(var4, var7.zeroExtend(var4.getBitsize())));
   }

   public void A(cfq var1, List var2, boolean var3) {
      IEGeneric var4 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[0]);
      IEGeneric var5 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[1]).part(32);
      IEGeneric var6 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[2]).signExtend(32);
      IEOperation var7 = this.A.createOperation(var3 ? OperationType.LT_S : OperationType.LT_U, var5, var6);
      var2.add(this.A.createAssign(var4, var7.zeroExtend(var4.getBitsize())));
   }

   public void pC(cfq var1, List var2) {
      this.kS(var1, var2, true);
   }

   public void A(cfq var1, List var2) {
      this.kS(var1, var2, false);
   }

   private void kS(cfq var1, List var2, boolean var3) {
      IEGeneric var4 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[0]);
      IEGeneric var5 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[1]);
      IEGeneric var6 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[2]);
      IEOperation var7 = this.A.createOperation(var3 ? OperationType.LOG_EQ : OperationType.LOG_NEQ, var6, this.pC.A(var6.getBitsize()));
      var2.add(this.A.createAssign(var4, this.A.createCond(var7, var5, this.pC.A(var5.getBitsize()))));
   }
}
