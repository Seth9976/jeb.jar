package com.pnfsoftware.jeb.corei.parsers.avr;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ConverterInstructionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import java.util.List;

class bO {
   cq pC;
   IERoutineContext A;

   bO(cq var1) {
      this.pC = var1;
   }

   void pC(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((KD)var1.insn, 0, var1.address);
      IEGeneric var3 = this.pC.pC((KD)var1.insn, 1, var1.address);
      this.pC.pC(var1.r, var2, var3);
   }

   void A(ConverterInstructionEntry var1) {
      bO.Av var2 = new bO.Av();
      IEGeneric var3 = this.pC.pC((KD)var1.insn, 0, var1.address);
      IEGeneric var4 = this.pC.pC((KD)var1.insn, 1, var1.address, var2);
      if (var2.pC == -1) {
         this.pC(var1.r, var2.A, false);
      }

      this.pC.pC(var1.r, var3, var4);
      if (var2.pC == 1) {
         this.pC(var1.r, var2.A, true);
      }
   }

   void kS(ConverterInstructionEntry var1) {
      bO.Av var2 = new bO.Av();
      IEGeneric var3 = this.pC.pC((KD)var1.insn, 0, var1.address, var2);
      IEGeneric var4 = this.pC.pC((KD)var1.insn, 1, var1.address);
      if (var2.pC == -1) {
         this.pC(var1.r, var2.A, false);
      }

      this.pC.pC(var1.r, var3, var4);
      if (var2.pC == 1) {
         this.pC(var1.r, var2.A, true);
      }
   }

   void pC(List var1, int var2, boolean var3) {
      if (var3) {
         this.pC.pC(var1, this.pC.gp[var2], this.A.createOperation(OperationType.ADD, this.pC.gp[var2], this.pC.z));
         this.pC
            .pC(
               var1,
               this.pC.gp[var2 + 1],
               this.A
                  .createCond(
                     this.A.createOperation(OperationType.LOG_NEQ, this.pC.gp[var2], this.pC.rl),
                     this.pC.gp[var2 + 1],
                     this.A.createOperation(OperationType.ADD, this.pC.gp[var2 + 1], this.pC.z)
                  )
            );
      } else {
         this.pC.pC(var1, this.pC.gp[var2], this.A.createOperation(OperationType.SUB, this.pC.gp[var2], this.pC.z));
         this.pC
            .pC(
               var1,
               this.pC.gp[var2 + 1],
               this.A
                  .createCond(
                     this.A.createOperation(OperationType.LOG_NEQ, this.pC.gp[var2], this.pC.Ek),
                     this.pC.gp[var2 + 1],
                     this.A.createOperation(OperationType.SUB, this.pC.gp[var2 + 1], this.pC.z)
                  )
            );
      }
   }

   void wS(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((KD)var1.insn, 0, var1.address);
      this.pC(var1.r, var2);
   }

   void pC(List var1, IEGeneric var2) {
      IEVar var3 = this.pC.getStackPointer();
      int var4 = var2.getBitsize() / 8;
      this.pC.pC(var1, var3, this.A.createOperation(OperationType.SUB, var3, this.A.createImm(var4, var3.getBitsize())));
      this.pC.pC(var1, this.A.createMem(var3, var2.getBitsize()), var2);
   }

   void UT(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((KD)var1.insn, 0, var1.address);
      this.A(var1.r, var2);
   }

   void A(List var1, IEGeneric var2) {
      IEVar var3 = this.pC.getStackPointer();
      int var4 = var2.getBitsize() / 8;
      this.pC.pC(var1, var2, this.A.createMem(var3, var2.getBitsize()));
      this.pC.pC(var1, var3, this.A.createOperation(OperationType.ADD, var3, this.A.createImm(var4, var3.getBitsize())));
   }

   static class Av {
      int pC;
      int A;
   }
}
