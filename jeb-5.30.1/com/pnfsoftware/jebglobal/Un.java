package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import java.util.List;

public class Un {
   uq q;
   IERoutineContext RF;

   public Un(uq var1) {
      this.q = var1;
   }

   public void q(fA var1, List var2, int var3) {
      CZ var4 = this.q.q(var1, var1.q(0));
      IEGeneric var5 = this.q.q(var1, (IInstructionOperand)var1.q(1));
      IEMem var6 = this.q.q(var1, var1.q(2), -1L, var3);
      IEVar var7 = this.q.gO(var3);
      this.q.q(var1, var2, var7, var6);
      IECond var8 = this.RF.createCond(EUtil.eq(var7, var4.q().part(var3)), var5.part(var3), var7);
      this.q.q(var1, var2, var6, var8);
      this.q.q(var1, var2, var4, var7);
   }

   public void q(fA var1, List var2) {
      int var3 = var1.q(0).getOperandBitsize();
      CZ var4 = this.q.q(var1, var1.q(0));
      CZ var5 = this.q.q(var1, var1.q(1));
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)var1.q(2));
      IEGeneric var7 = this.q.q(var1, (IInstructionOperand)var1.q(3));
      IEMem var8 = this.q.q(var1, var1.q(4), -1L, var3);
      IEMem var9 = this.RF.createMem(EUtil.add(var8.getReference(), this.RF.createImm(var3 / 8, var8.getReference().getBitsize())), var3);
      IEVar var10 = this.q.gO(var3);
      this.q.q(var1, var2, var10, var8);
      IEVar var11 = this.q.nf(var3);
      this.q.q(var1, var2, var11, var9);
      IEOperation var12 = EUtil.andL(EUtil.eq(var10, var4.RF().part(var3)), EUtil.eq(var11, var5.RF().part(var3)));
      IECond var13 = this.RF.createCond(var12, var6.part(var3), var10);
      this.q.q(var1, var2, var8, var13);
      IECond var14 = this.RF.createCond(var12.duplicate(), var7.part(var3), var11);
      this.q.q(var1, var2, var9, var14);
      this.q.q(var1, var2, var4, var10);
      this.q.q(var1, var2, var5, var11);
   }

   public void RF(fA var1, List var2, int var3) {
      IEGeneric var4 = this.q.q(var1, (IInstructionOperand)var1.q(0));
      CZ var5 = this.q.q(var1, var1.q(1));
      IEMem var6 = this.q.q(var1, var1.q(2), -1L, var3);
      IEVar var7 = this.q.gO(var3);
      this.q.q(var1, var2, var7, var6);
      this.q.q(var1, var2, var6, var4);
      this.q.q(var1, var2, var5, var7);
   }
}
