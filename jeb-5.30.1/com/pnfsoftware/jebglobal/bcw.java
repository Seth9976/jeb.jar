package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ConverterInstructionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import java.util.List;

class bcw {
   bcx q;
   IERoutineContext RF;

   bcw(bcx var1) {
      this.q = var1;
   }

   void q(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((bdc)var1.insn, 0, var1.address);
      IEGeneric var3 = this.q.q((bdc)var1.insn, 1, var1.address);
      this.q.q(var1.r, var2, var3);
   }

   void RF(ConverterInstructionEntry var1) {
      bcw.eo var2 = new bcw.eo();
      IEGeneric var3 = this.q.q((bdc)var1.insn, 0, var1.address);
      IEGeneric var4 = this.q.q((bdc)var1.insn, 1, var1.address, var2);
      if (var2.q == -1) {
         this.q(var1.r, var2.RF, false);
      }

      this.q.q(var1.r, var3, var4);
      if (var2.q == 1) {
         this.q(var1.r, var2.RF, true);
      }
   }

   void xK(ConverterInstructionEntry var1) {
      bcw.eo var2 = new bcw.eo();
      IEGeneric var3 = this.q.q((bdc)var1.insn, 0, var1.address, var2);
      IEGeneric var4 = this.q.q((bdc)var1.insn, 1, var1.address);
      if (var2.q == -1) {
         this.q(var1.r, var2.RF, false);
      }

      this.q.q(var1.r, var3, var4);
      if (var2.q == 1) {
         this.q(var1.r, var2.RF, true);
      }
   }

   void q(List var1, int var2, boolean var3) {
      if (var3) {
         this.q.q(var1, this.q.Ef[var2], this.RF.createOperation(OperationType.ADD, this.q.Ef[var2], this.q.Bu));
         this.q
            .q(
               var1,
               this.q.Ef[var2 + 1],
               this.RF
                  .createCond(
                     this.RF.createOperation(OperationType.LOG_NEQ, this.q.Ef[var2], this.q.Xo),
                     this.q.Ef[var2 + 1],
                     this.RF.createOperation(OperationType.ADD, this.q.Ef[var2 + 1], this.q.Bu)
                  )
            );
      } else {
         this.q.q(var1, this.q.Ef[var2], this.RF.createOperation(OperationType.SUB, this.q.Ef[var2], this.q.Bu));
         this.q
            .q(
               var1,
               this.q.Ef[var2 + 1],
               this.RF
                  .createCond(
                     this.RF.createOperation(OperationType.LOG_NEQ, this.q.Ef[var2], this.q.IN),
                     this.q.Ef[var2 + 1],
                     this.RF.createOperation(OperationType.SUB, this.q.Ef[var2 + 1], this.q.Bu)
                  )
            );
      }
   }

   void Dw(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((bdc)var1.insn, 0, var1.address);
      this.q(var1.r, var2);
   }

   void q(List var1, IEGeneric var2) {
      IEVar var3 = this.q.getStackPointer();
      int var4 = var2.getBitsize() / 8;
      this.q.q(var1, var3, this.RF.createOperation(OperationType.SUB, var3, this.RF.createImm(var4, var3.getBitsize())));
      this.q.q(var1, this.RF.createMem(var3, var2.getBitsize()), var2);
   }

   void Uv(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((bdc)var1.insn, 0, var1.address);
      this.RF(var1.r, var2);
   }

   void RF(List var1, IEGeneric var2) {
      IEVar var3 = this.q.getStackPointer();
      int var4 = var2.getBitsize() / 8;
      this.q.q(var1, var2, this.RF.createMem(var3, var2.getBitsize()));
      this.q.q(var1, var3, this.RF.createOperation(OperationType.ADD, var3, this.RF.createImm(var4, var3.getBitsize())));
   }

   static class eo {
      int q;
      int RF;
   }
}
