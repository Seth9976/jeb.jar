package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import java.util.List;

public class clm {
   cln q;
   IERoutineContext RF;

   public clm(cln var1) {
      this.q = var1;
   }

   public void q(cmd var1, List var2, boolean var3) {
      IEGeneric var4 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]);
      IEGeneric var5 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1]).part(32);
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[2]).part(32);
      IEOperation var7 = this.RF.createOperation(var3 ? OperationType.LT_S : OperationType.LT_U, var5, var6);
      var2.add(this.RF.createAssign(var4, var7.zeroExtend(var4.getBitsize())));
   }

   public void RF(cmd var1, List var2, boolean var3) {
      IEGeneric var4 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]);
      IEGeneric var5 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1]).part(32);
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[2]).signExtend(32);
      IEOperation var7 = this.RF.createOperation(var3 ? OperationType.LT_S : OperationType.LT_U, var5, var6);
      var2.add(this.RF.createAssign(var4, var7.zeroExtend(var4.getBitsize())));
   }

   public void q(cmd var1, List var2) {
      this.xK(var1, var2, true);
   }

   public void RF(cmd var1, List var2) {
      this.xK(var1, var2, false);
   }

   private void xK(cmd var1, List var2, boolean var3) {
      IEGeneric var4 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]);
      IEGeneric var5 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1]);
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[2]);
      IEOperation var7 = this.RF.createOperation(var3 ? OperationType.LOG_EQ : OperationType.LOG_NEQ, var6, this.q.xK(var6.getBitsize()));
      var2.add(this.RF.createAssign(var4, this.RF.createCond(var7, var5, this.q.xK(var5.getBitsize()))));
   }
}
