package com.pnfsoftware.jeb.corei.parsers.x86;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ConverterInstructionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.util.base.Assert;

public class Hv {
   Yd pC;
   IERoutineContext A;

   Hv(Yd var1) {
      this.pC = var1;
   }

   void pC(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 1, -var2.getBitsize(), var1.address);
      Assert.a(var2.getBitsize() == var3.getBitsize());
      this.pC.pC(var2, var3, var1);
   }

   void A(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 1, 0, var1.address);
      Assert.a(var3 instanceof IEMem);
      IEGeneric var4 = ((IEMem)var3).getReference();
      int var5 = var2.getBitsize();
      if (var5 < var4.getBitsize()) {
         var4 = var4.part(var5);
      } else if (var5 > var4.getBitsize()) {
         var4 = var4.zeroExtend(var5);
      }

      this.pC.pC(var2, var4, var1);
   }

   void kS(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 1, -var2.getBitsize(), var1.address);
      this.pC.pC(var2, var3, var1);
   }

   void wS(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 1, var2.getBitsize(), var1.address);
      this.pC.pC(var2, var3, var1);
   }

   void UT(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 1, 0, var1.address);
      Assert.a(var2.getBitsize() == var3.getBitsize());
      IEGeneric var4 = this.pC.A(var2.getBitsize(), var1.r);
      var1.r.add(this.A.createAssign(var4, var2));
      this.pC.pC(var2, var3, var1);
      this.pC.pC(var3, var4, var1);
   }

   void E(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.Sc.part(16);
      IECond var3 = this.A.createCond(var2.msb(), this.pC.go, this.pC.kU);
      var1.r.add(this.A.createAssign(this.pC.eP.part(16), var3));
   }

   void sY(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.Sc.part(32);
      IECond var3 = this.A.createCond(var2.msb(), this.pC.pg, this.pC.JF);
      this.pC.pC(this.pC.eP.part(32), var3, var1);
   }

   void ys(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.Sc.part(64);
      IECond var3 = this.A.createCond(var2.msb(), this.pC.DL, this.pC.gj);
      var1.r.add(this.A.createAssign(this.pC.eP.part(64), var3));
   }

   void ld(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.Sc.part(8);
      IECond var3 = this.A.createCond(var2.msb(), this.pC.xM, this.pC.RW);
      var1.r.add(this.A.createAssign(this.pC.Sc.slice(8, 16), var3));
   }

   void gp(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.Sc.part(16);
      IECond var3 = this.A.createCond(var2.msb(), this.pC.go, this.pC.kU);
      IECompose var4 = this.A.createCompose(var2, var3);
      this.pC.pC(this.pC.Sc.part(32), var4, var1);
   }

   void oT(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.Sc.part(32);
      IECond var3 = this.A.createCond(var2.msb(), this.pC.pg, this.pC.JF);
      var1.r.add(this.A.createAssign(this.pC.Sc.slice(32, 64), var3));
   }

   void fI(ConverterInstructionEntry var1) {
      var1.r.add(this.A.createAssign(this.pC.Er, this.pC.Bc));
   }

   void WR(ConverterInstructionEntry var1) {
      var1.r.add(this.A.createAssign(this.pC.Er, this.pC.OI));
   }

   void NS(ConverterInstructionEntry var1) {
      var1.r.add(this.A.createAssign(this.pC.os, this.pC.Bc));
   }

   void vP(ConverterInstructionEntry var1) {
      var1.r.add(this.A.createAssign(this.pC.os, this.pC.OI));
   }

   void xC(ConverterInstructionEntry var1) {
      var1.r.add(this.A.createAssign(this.pC.sO, this.pC.Bc));
   }

   void ED(ConverterInstructionEntry var1) {
      var1.r.add(this.A.createAssign(this.pC.sO, this.pC.OI));
   }

   void Sc(ConverterInstructionEntry var1) {
      var1.r.add(this.A.createAssign(this.pC.Er, EUtil.notB(this.pC.Er)));
   }

   void ah(ConverterInstructionEntry var1) {
      if (!eS.pC((vh)var1.insn)) {
         this.Aj(var1);
      } else {
         int var2 = ((vh)var1.insn).ys();
         IEGeneric var3 = this.pC.ah.part(var2);
         var1.r.add(this.A.createJump(var1.irAddress + 6, this.A.createOperation(OperationType.LOG_EQ, var3, this.pC.pC(var3))));
         this.Aj(var1);
         this.pC.pC(var3, this.A.createOperation(OperationType.SUB, var3, this.A.createImm(1L, var3.getBitsize())), var1);
         var1.r.add(this.A.createJump(var1.irAddress, null));
      }
   }

   private void Aj(ConverterInstructionEntry var1) {
      int var2 = ((vh)var1.insn).ys();
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.pC.pC((vh)var1.insn, 1, 0, var1.address);
      Assert.a(var3.getBitsize() == var4.getBitsize());
      int var5 = var3.getBitsize() / 8;
      IECond var6 = this.A.createCond(this.pC.os, this.A.createImm(-var5, var2), this.A.createImm(var5, var2));
      IEGeneric var7 = this.pC.z.part(var2);
      IEGeneric var8 = this.pC.Ek.part(var2);
      this.pC.pC(var3, var4, var1);
      this.pC.pC(var7, this.A.createOperation(OperationType.ADD, var7, var6), var1);
      this.pC.pC(var8, this.A.createOperation(OperationType.ADD, var8, var6), var1);
   }

   void eP(ConverterInstructionEntry var1) {
      if (!eS.pC((vh)var1.insn)) {
         this.EX(var1);
      } else {
         int var2 = ((vh)var1.insn).ys();
         IEGeneric var3 = this.pC.ah.part(var2);
         var1.r.add(this.A.createJump(var1.irAddress + 5, this.A.createOperation(OperationType.LOG_EQ, var3, this.pC.pC(var3))));
         this.EX(var1);
         this.pC.pC(var3, this.A.createOperation(OperationType.SUB, var3, this.A.createImm(1L, var3.getBitsize())), var1);
         var1.r.add(this.A.createJump(var1.irAddress, null));
      }
   }

   private void EX(ConverterInstructionEntry var1) {
      int var2 = ((vh)var1.insn).ys();
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.pC.pC((vh)var1.insn, 1, 0, var1.address);
      Assert.a(var3.getBitsize() == var4.getBitsize());
      int var5 = var3.getBitsize() / 8;
      IECond var6 = this.A.createCond(this.pC.os, this.A.createImm(-var5, var2), this.A.createImm(var5, var2));
      IEGeneric var7 = this.pC.z.part(var2);
      this.pC.pC(var3, var4, var1);
      this.pC.pC(var7, this.A.createOperation(OperationType.ADD, var7, var6), var1);
   }

   void UO(ConverterInstructionEntry var1) {
      if (!eS.pC((vh)var1.insn)) {
         this.LM(var1);
      } else {
         int var2 = ((vh)var1.insn).ys();
         IEGeneric var3 = this.pC.ah.part(var2);
         var1.r.add(this.A.createJump(var1.irAddress + 5, this.A.createOperation(OperationType.LOG_EQ, var3, this.pC.pC(var3))));
         this.LM(var1);
         this.pC.pC(var3, this.A.createOperation(OperationType.SUB, var3, this.A.createImm(1L, var3.getBitsize())), var1);
         var1.r.add(this.A.createJump(var1.irAddress, null));
      }
   }

   private void LM(ConverterInstructionEntry var1) {
      int var2 = ((vh)var1.insn).ys();
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.pC.pC((vh)var1.insn, 1, 0, var1.address);
      Assert.a(var3.getBitsize() == var4.getBitsize());
      int var5 = var3.getBitsize() / 8;
      IECond var6 = this.A.createCond(this.pC.os, this.A.createImm(-var5, var2), this.A.createImm(var5, var2));
      IEGeneric var7 = this.pC.Ek.part(var2);
      this.pC.pC(var3, var4, var1);
      this.pC.pC(var7, this.A.createOperation(OperationType.ADD, var7, var6), var1);
   }

   void Ab(ConverterInstructionEntry var1) {
      int var2 = ((vh)var1.insn).wS[0];
      if (var2 == 243) {
         int var3 = ((vh)var1.insn).ys();
         IEGeneric var4 = this.pC.ah.part(var3);
         var1.r.add(this.A.createJump(var1.irAddress + 11, this.A.createOperation(OperationType.LOG_EQ, var4, this.pC.pC(var4))));
         this.mv(var1);
         this.pC.pC(var4, this.A.createOperation(OperationType.SUB, var4, this.A.createImm(1L, var4.getBitsize())), var1);
         var1.r.add(this.A.createJump(var1.irAddress, this.pC.EX));
      } else if (var2 == 242) {
         int var5 = ((vh)var1.insn).ys();
         IEGeneric var6 = this.pC.ah.part(var5);
         var1.r.add(this.A.createJump(var1.irAddress + 11, this.A.createOperation(OperationType.LOG_EQ, var6, this.pC.pC(var6))));
         this.mv(var1);
         this.pC.pC(var6, this.A.createOperation(OperationType.SUB, var6, this.A.createImm(1L, var6.getBitsize())), var1);
         var1.r.add(this.A.createJump(var1.irAddress, this.A.createOperation(OperationType.NOT, this.pC.EX)));
      } else {
         this.mv(var1);
      }
   }

   private void mv(ConverterInstructionEntry var1) {
      int var2 = ((vh)var1.insn).ys();
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.pC.pC((vh)var1.insn, 1, 0, var1.address);
      Assert.a(var3.getBitsize() == var4.getBitsize());
      int var5 = var3.getBitsize() / 8;
      IECond var6 = this.A.createCond(this.pC.os, this.A.createImm(-var5, var2), this.A.createImm(var5, var2));
      IEGeneric var7 = this.pC.z.part(var2);
      IEGeneric var8 = this.pC.Ek.part(var2);
      this.pC.A.A(var3, var4, var1.r);
      this.pC.pC(var7, this.A.createOperation(OperationType.ADD, var7, var6), var1);
      this.pC.pC(var8, this.A.createOperation(OperationType.ADD, var8, var6), var1);
   }

   void rl(ConverterInstructionEntry var1) {
      int var2 = ((vh)var1.insn).wS[0];
      if (var2 == 243) {
         int var3 = ((vh)var1.insn).ys();
         IEGeneric var4 = this.pC.ah.part(var3);
         var1.r.add(this.A.createJump(var1.irAddress + 10, this.A.createOperation(OperationType.LOG_EQ, var4, this.pC.pC(var4))));
         this.sO(var1);
         this.pC.pC(var4, this.A.createOperation(OperationType.SUB, var4, this.A.createImm(1L, var4.getBitsize())), var1);
         var1.r.add(this.A.createJump(var1.irAddress, this.pC.EX));
      } else if (var2 == 242) {
         int var5 = ((vh)var1.insn).ys();
         IEGeneric var6 = this.pC.ah.part(var5);
         var1.r.add(this.A.createJump(var1.irAddress + 10, this.A.createOperation(OperationType.LOG_EQ, var6, this.pC.pC(var6))));
         this.sO(var1);
         this.pC.pC(var6, this.A.createOperation(OperationType.SUB, var6, this.A.createImm(1L, var6.getBitsize())), var1);
         var1.r.add(this.A.createJump(var1.irAddress, this.A.createOperation(OperationType.NOT, this.pC.EX)));
      } else {
         this.sO(var1);
      }
   }

   private void sO(ConverterInstructionEntry var1) {
      int var2 = ((vh)var1.insn).ys();
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.pC.pC((vh)var1.insn, 1, 0, var1.address);
      Assert.a(var3.getBitsize() == var4.getBitsize());
      int var5 = var3.getBitsize() / 8;
      IECond var6 = this.A.createCond(this.pC.os, this.A.createImm(-var5, var2), this.A.createImm(var5, var2));
      IEGeneric var7 = this.pC.Ek.part(var2);
      this.pC.A.A(var3, var4, var1.r);
      this.pC.pC(var7, this.A.createOperation(OperationType.ADD, var7, var6), var1);
   }

   void z(ConverterInstructionEntry var1) {
      this.pC(var1, true);
   }

   void Ek(ConverterInstructionEntry var1) {
      this.pC(var1, false);
   }

   private void pC(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var4 = this.pC.pC((vh)var1.insn, 1, 0, var1.address);
      int var5 = var3.getBitsize();
      Assert.a(var3.getBitsize() == var4.getBitsize());
      IEGeneric var6 = this.pC.pC(var4);
      IEGeneric var7 = this.pC.A(var4);
      IEVar var8 = this.pC.pC(var5);
      int var9 = var1.irAddress;
      int var10 = var9 + 3;
      int var11 = var9 + 6;
      int var12 = var9 + 8;
      int var13 = var9 + 9;
      if (var2) {
         var1.r.add(this.A.createJump(var12, EUtil.eq(var4, var6)));
         var1.r.add(this.A.createAssign(this.pC.EX, this.pC.Bc));
         var1.r.add(this.A.createAssign(var8, var6));
         var1.r.add(this.A.createJump(var11, EUtil.andB(EUtil.shr(var4, var8), var7)));
         var1.r.add(this.A.createAssign(var8, EUtil.add(var8, var7)));
         var1.r.add(this.A.createJump(var10));
         var1.r.add(this.A.createAssign(var3, var8));
         var1.r.add(this.A.createJump(var13));
         var1.r.add(this.A.createAssign(this.pC.EX, this.pC.OI));
      } else {
         var1.r.add(this.A.createJump(var12, EUtil.eq(var4, var6)));
         var1.r.add(this.A.createAssign(this.pC.EX, this.pC.Bc));
         var1.r.add(this.A.createAssign(var8, EUtil.imm(var5 - 1, var5)));
         var1.r.add(this.A.createJump(var11, EUtil.shr(var4, var8)));
         var1.r.add(this.A.createAssign(var8, EUtil.sub(var8, var7)));
         var1.r.add(this.A.createJump(var10));
         var1.r.add(this.A.createAssign(var3, var8));
         var1.r.add(this.A.createJump(var13));
         var1.r.add(this.A.createAssign(this.pC.EX, this.pC.OI));
      }
   }

   void hK(ConverterInstructionEntry var1) {
      int var2 = ((vh)var1.insn).sY();
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      Assert.a(var3.getBitsize() == var2);
      IEGeneric var4 = this.pC.A(var2, var1.r);
      var1.r.add(this.A.createAssign(var4, var3));
      if (var2 == 64) {
         var1.r.add(this.A.createAssign(var3.slice(0, 8), var4.slice(56, 64)));
         var1.r.add(this.A.createAssign(var3.slice(8, 16), var4.slice(48, 56)));
         var1.r.add(this.A.createAssign(var3.slice(16, 24), var4.slice(40, 48)));
         var1.r.add(this.A.createAssign(var3.slice(24, 32), var4.slice(32, 40)));
         var1.r.add(this.A.createAssign(var3.slice(32, 40), var4.slice(24, 32)));
         var1.r.add(this.A.createAssign(var3.slice(40, 48), var4.slice(16, 24)));
         var1.r.add(this.A.createAssign(var3.slice(48, 56), var4.slice(8, 16)));
         var1.r.add(this.A.createAssign(var3.slice(56, 64), var4.slice(0, 8)));
      } else if (var2 == 32) {
         var1.r.add(this.A.createAssign(var3.slice(0, 8), var4.slice(24, 32)));
         var1.r.add(this.A.createAssign(var3.slice(8, 16), var4.slice(16, 24)));
         var1.r.add(this.A.createAssign(var3.slice(16, 24), var4.slice(8, 16)));
         var1.r.add(this.A.createAssign(var3.slice(24, 32), var4.slice(0, 8)));
         if (((vh)var1.insn).pC == 64
            && var3 instanceof IESlice
            && ((IESlice)var3).getBitStart() == 0
            && var3.getBitsize() == 32
            && ((IESlice)var3).getWholeExpression() instanceof IEVar) {
            IEVar var5 = (IEVar)((IESlice)var3).getWholeExpression();
            if (var5.getId() >= 0 && var5.getId() < 1024) {
               var1.r.add(this.A.createAssign(var5.slice(32, 64), this.pC.JF));
            }
         }
      }
   }

   void Er(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      Assert.a(var2.getBitsize() == 8);
      int var4 = ((vh)var1.insn).gp.A & 14;
      boolean var5 = (((vh)var1.insn).gp.A & 1) != 0;

      Object var3 = switch (var4) {
         case 0 -> this.pC.Cu;
         default -> throw new RuntimeException();
         case 2 -> this.pC.Er;
         case 4 -> this.pC.EX;
         case 6 -> this.A.createOperation(OperationType.LOG_OR, this.pC.Er, this.pC.EX);
         case 8 -> this.pC.LM;
         case 10 -> this.pC.FE;
         case 12 -> this.A.createOperation(OperationType.LOG_NEQ, this.pC.LM, this.pC.Cu);
         case 14 -> this.A.createOperation(OperationType.LOG_OR, this.pC.EX, this.A.createOperation(OperationType.LOG_NEQ, this.pC.LM, this.pC.Cu));
      };
      IECond var6 = !var5 ? this.A.createCond((IEGeneric)var3, this.pC.e, this.pC.RW) : this.A.createCond((IEGeneric)var3, this.pC.RW, this.pC.e);
      var1.r.add(this.A.createAssign(var2, var6));
   }

   void FE(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 1, 0, var1.address);
      Assert.a(var2.getBitsize() == var3.getBitsize());
      int var5 = ((vh)var1.insn).gp.A & 14;
      boolean var6 = (((vh)var1.insn).gp.A & 1) != 0;

      Object var4 = switch (var5) {
         case 0 -> this.pC.Cu;
         default -> throw new RuntimeException();
         case 2 -> this.pC.Er;
         case 4 -> this.pC.EX;
         case 6 -> this.A.createOperation(OperationType.LOG_OR, this.pC.Er, this.pC.EX);
         case 8 -> this.pC.LM;
         case 10 -> this.pC.FE;
         case 12 -> this.A.createOperation(OperationType.LOG_NEQ, this.pC.LM, this.pC.Cu);
         case 14 -> this.A.createOperation(OperationType.LOG_OR, this.pC.EX, this.A.createOperation(OperationType.LOG_NEQ, this.pC.LM, this.pC.Cu));
      };
      IECond var7 = !var6 ? this.A.createCond((IEGeneric)var4, var3, var2) : this.A.createCond((IEGeneric)var4, var2, var3);
      this.pC.pC(var2, var7, var1);
   }
}
