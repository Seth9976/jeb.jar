package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ConverterInstructionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;

class bcv {
   bcx q;
   IERoutineContext RF;

   bcv(bcx var1) {
      this.q = var1;
   }

   void q(ConverterInstructionEntry var1) {
      this.q.qa.RF(var1.r, this.q.EB);
      this.q.Uv(var1.r, this.q.EB);
   }

   void RF(ConverterInstructionEntry var1) {
      throw new RuntimeException();
   }

   void q(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3;
      if (var2) {
         var3 = this.q.oW();
      } else {
         var3 = this.q.q((bdc)var1.insn, 0, var1.address);
      }

      IEImm var4 = this.RF.createImm(var1.address + ((bdc)var1.insn).getSize(), this.q.PV.getBitsize());
      this.q.qa.q(var1.r, var4);
      if (var3.equals(var4)) {
         this.q.Uv(var1.r, var3);
      } else {
         this.q.oW(var1.r, var3);
      }
   }

   void RF(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3;
      if (var2) {
         var3 = this.q.oW();
      } else {
         var3 = this.q.q((bdc)var1.insn, 0, var1.address);
      }

      this.q.Uv(var1.r, var3);
   }

   void q(ConverterInstructionEntry var1, String var2) {
      IEGeneric var3 = this.q.q((bdc)var1.insn, 0, var1.address);
      IEImm var4 = this.RF.createImm(var1.address + ((bdc)var1.insn).getSize(), this.q.PV.getBitsize());
      boolean var5 = false;
      IEVar var6;
      switch (var2) {
         case "breq":
            var6 = this.q.CE;
            break;
         case "brne":
            var6 = this.q.CE;
            var5 = true;
            break;
         case "brcs":
         case "brlo":
            var6 = this.q.sH;
            break;
         case "brcc":
         case "brsh":
            var6 = this.q.sH;
            var5 = true;
            break;
         case "brmi":
            var6 = this.q.wF;
            break;
         case "brpl":
            var6 = this.q.wF;
            var5 = true;
            break;
         case "brge":
            var6 = this.q.Dz;
            var5 = true;
            break;
         case "brlt":
            var6 = this.q.Dz;
            break;
         case "brhs":
            var6 = this.q.mI;
            break;
         case "brhc":
            var6 = this.q.mI;
            var5 = true;
            break;
         case "brts":
            var6 = this.q.jq;
            break;
         case "brtc":
            var6 = this.q.jq;
            var5 = true;
            break;
         case "brvs":
            var6 = this.q.If;
            break;
         case "brvc":
            var6 = this.q.If;
            var5 = true;
            break;
         case "brie":
            var6 = this.q.ui;
            break;
         case "brid":
            var6 = this.q.ui;
            var5 = true;
            break;
         default:
            throw new RuntimeException();
      }

      IECond var7 = !var5 ? this.RF.createCond(var6, var3, var4) : this.RF.createCond(var6, var4, var3);
      this.q.Uv(var1.r, var7);
   }

   void xK(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((bdc)var1.insn, 0, var1.address);
      IEGeneric var3 = this.q.q((bdc)var1.insn, 1, var1.address);
      IEOperation var4 = this.RF.createOperation(OperationType.LOG_EQ, var2, var3);
      long var5 = var1.address + ((bdc)var1.insn).getSize();
      long var7 = ((ICodePointer)((bdc)var1.insn).getBreakingFlow(var1.address).getTargets().get(1)).getAddress();
      this.q.Uv(var1.r, this.RF.createCond(var4, this.q.q(var7), this.q.q(var5)));
   }

   void xK(ConverterInstructionEntry var1, boolean var2) {
      IEGeneric var3 = this.q.q((bdc)var1.insn, 0, var1.address);
      IEGeneric var4 = this.q.q((bdc)var1.insn, 1, var1.address);
      IEOperation var5 = this.RF.createOperation(OperationType.AND, var3, this.RF.createOperation(OperationType.SHL, this.q.Bu, var4));
      var5 = this.RF.createOperation(var2 ? OperationType.LOG_EQ : OperationType.LOG_NEQ, var5, this.q.Xo);
      long var6 = var1.address + ((bdc)var1.insn).getSize();
      long var8 = ((ICodePointer)((bdc)var1.insn).getBreakingFlow(var1.address).getTargets().get(1)).getAddress();
      this.q.Uv(var1.r, this.RF.createCond(var5, this.q.q(var8), this.q.q(var6)));
   }
}
