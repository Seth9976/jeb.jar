package com.pnfsoftware.jeb.corei.parsers.arm.converter;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jebglobal.pY;
import java.util.List;

public class yt {
   HE pC;
   IERoutineContext A;

   public yt(HE var1) {
      this.pC = var1;
   }

   public void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, int var3) {
      pY var4 = this.pC.pC(var1, var1.pC(0));
      IEGeneric var5 = this.pC.pC(var1, (IInstructionOperand)var1.pC(1));
      IEMem var6 = this.pC.pC(var1, var1.pC(2), -1L, var3);
      IEVar var7 = this.pC.E(var3);
      this.pC.pC(var1, var2, var7, var6);
      IECond var8 = this.A.createCond(EUtil.eq(var7, var4.pC().part(var3)), var5.part(var3), var7);
      this.pC.pC(var1, var2, var6, var8);
      this.pC.pC(var1, var2, var4, var7);
   }

   public void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2) {
      int var3 = var1.pC(0).getOperandBitsize();
      pY var4 = this.pC.pC(var1, var1.pC(0));
      pY var5 = this.pC.pC(var1, var1.pC(1));
      IEGeneric var6 = this.pC.pC(var1, (IInstructionOperand)var1.pC(2));
      IEGeneric var7 = this.pC.pC(var1, (IInstructionOperand)var1.pC(3));
      IEMem var8 = this.pC.pC(var1, var1.pC(4), -1L, var3);
      IEMem var9 = this.A.createMem(EUtil.add(var8.getReference(), this.A.createImm(var3 / 8, var8.getReference().getBitsize())), var3);
      IEVar var10 = this.pC.E(var3);
      this.pC.pC(var1, var2, var10, var8);
      IEVar var11 = this.pC.sY(var3);
      this.pC.pC(var1, var2, var11, var9);
      IEOperation var12 = EUtil.andL(EUtil.eq(var10, var4.A().part(var3)), EUtil.eq(var11, var5.A().part(var3)));
      IECond var13 = this.A.createCond(var12, var6.part(var3), var10);
      this.pC.pC(var1, var2, var8, var13);
      IECond var14 = this.A.createCond(var12.duplicate(), var7.part(var3), var11);
      this.pC.pC(var1, var2, var9, var14);
      this.pC.pC(var1, var2, var4, var10);
      this.pC.pC(var1, var2, var5, var11);
   }

   public void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, int var3) {
      IEGeneric var4 = this.pC.pC(var1, (IInstructionOperand)var1.pC(0));
      pY var5 = this.pC.pC(var1, var1.pC(1));
      IEMem var6 = this.pC.pC(var1, var1.pC(2), -1L, var3);
      IEVar var7 = this.pC.E(var3);
      this.pC.pC(var1, var2, var7, var6);
      this.pC.pC(var1, var2, var6, var4);
      this.pC.pC(var1, var2, var5, var7);
   }
}
