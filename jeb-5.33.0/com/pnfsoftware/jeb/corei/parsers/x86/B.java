package com.pnfsoftware.jeb.corei.parsers.x86;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ConverterInstructionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;

public class B {
   Yd pC;
   IERoutineContext A;

   B(Yd var1) {
      this.pC = var1;
   }

   private IEGeneric pC(IEGeneric var1, IEGeneric var2) {
      IEOperation var3;
      if (var1.getBitsize() == 64) {
         var3 = this.A.createOperation(OperationType.AND, var2, this.A.createImm(63L, var2.getBitsize()));
      } else {
         var3 = this.A.createOperation(OperationType.AND, var2, this.A.createImm(31L, var2.getBitsize()));
      }

      return var3;
   }

   private IEGeneric pC(IEGeneric var1) {
      IEOperation var2;
      if (var1.getBitsize() == 64) {
         var2 = this.A.createOperation(OperationType.AND, this.pC.ah, this.A.createImm(63L, this.pC.ah.getBitsize()));
      } else {
         var2 = this.A.createOperation(OperationType.AND, this.pC.ah, this.A.createImm(31L, this.pC.ah.getBitsize()));
      }

      return var2;
   }

   void pC(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 1, 0, var1.address);
      IEGeneric var4;
      if (((vh)var1.insn).gp.A != 54048 && ((vh)var1.insn).gp.A != 53792) {
         var4 = this.pC(var2, var3);
      } else {
         var4 = this.pC(var2);
      }

      IEGeneric var5 = this.pC.A(var2.getBitsize(), var1.r);
      this.pC.pC(var5, var2, var1, false);
      IEOperation var6 = this.A.createOperation(OperationType.SHL, var2, var4);
      this.pC.pC(var2, var6, var1);
      IEGeneric var7 = this.A
         .createOperation(OperationType.SHL, var5, this.A.createOperation(OperationType.SUB, var4.duplicate(), this.A.createImm(1L, var4.getBitsize())))
         .msb();
      IEOperation var8 = this.A.createOperation(OperationType.LT_U, var4.duplicate(), this.A.createImm(var5.getBitsize(), var4.getBitsize()));
      var1.r.add(this.A.createAssign(this.pC.Er, this.A.createCond(this.A.createOperation(OperationType.LOG_AND, var8, var4.duplicate()), var7, this.pC.Er)));
      IEOperation var9 = this.A
         .createOperation(OperationType.XOR, var5.msb(), this.A.createOperation(OperationType.SHL, var5, this.A.createImm(1L, var5.getBitsize())).msb());
      var1.r
         .add(
            this.A
               .createAssign(
                  this.pC.Cu,
                  this.A.createCond(this.A.createOperation(OperationType.LOG_EQ, var4.duplicate(), this.A.createImm(1L, var4.getBitsize())), var9, this.pC.Cu)
               )
         );
      this.pC.A.pC(var2, var4, var1.r);
   }

   void A(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 1, 0, var1.address);
      IEGeneric var4;
      if (((vh)var1.insn).gp.A != 54056 && ((vh)var1.insn).gp.A != 53800) {
         var4 = this.pC(var2, var3);
      } else {
         var4 = this.pC(var2);
      }

      IEOperation var5 = this.A.createOperation(OperationType.SHR, var2, var4);
      IEGeneric var6 = this.pC.A(var2.getBitsize(), var1.r);
      this.pC.pC(var6, var2, var1, false);
      this.pC.pC(var2, var5, var1);
      IEGeneric var7 = this.A
         .createOperation(OperationType.SHR, var6, this.A.createOperation(OperationType.SUB, var4.duplicate(), this.A.createImm(1L, var4.getBitsize())))
         .lsb();
      IEOperation var8 = this.A.createOperation(OperationType.LT_U, var4.duplicate(), this.A.createImm(var6.getBitsize(), var4.getBitsize()));
      var1.r.add(this.A.createAssign(this.pC.Er, this.A.createCond(this.A.createOperation(OperationType.LOG_AND, var8, var4.duplicate()), var7, this.pC.Er)));
      var1.r
         .add(
            this.A
               .createAssign(
                  this.pC.Cu,
                  this.A.createCond(this.A.createOperation(OperationType.LOG_EQ, var4, this.A.createImm(1L, var4.getBitsize())), var6.msb(), this.pC.Cu)
               )
         );
      this.pC.A.pC(var2, var4, var1.r);
   }

   void kS(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 1, 0, var1.address);
      IEGeneric var4;
      if (((vh)var1.insn).gp.A != 54072 && ((vh)var1.insn).gp.A != 53816) {
         var4 = this.pC(var2, var3);
      } else {
         var4 = this.pC(var2);
      }

      IEOperation var5 = this.A.createOperation(OperationType.SAR, var2, var4);
      IEGeneric var6 = this.pC.A(var2.getBitsize(), var1.r);
      this.pC.pC(var6, var2, var1, false);
      this.pC.pC(var2, var5, var1);
      IEGeneric var7 = this.A
         .createOperation(OperationType.SAR, var6, this.A.createOperation(OperationType.SUB, var4.duplicate(), this.A.createImm(1L, var4.getBitsize())))
         .lsb();
      IEOperation var8 = this.A.createOperation(OperationType.LT_U, var4.duplicate(), this.A.createImm(var6.getBitsize(), var4.getBitsize()));
      var1.r.add(this.A.createAssign(this.pC.Er, this.A.createCond(this.A.createOperation(OperationType.LOG_AND, var8, var4.duplicate()), var7, this.pC.Er)));
      var1.r
         .add(
            this.A
               .createAssign(
                  this.pC.Cu,
                  this.A.createCond(this.A.createOperation(OperationType.LOG_EQ, var4, this.A.createImm(1L, var4.getBitsize())), this.pC.Bc, this.pC.Cu)
               )
         );
      this.pC.A.pC(var2, var4, var1.r);
   }

   void wS(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 1, 0, var1.address);
      IEGeneric var4;
      if (((vh)var1.insn).gp.A != 54016 && ((vh)var1.insn).gp.A != 53760) {
         var4 = this.pC(var2, var3);
      } else {
         var4 = this.pC(var2);
      }

      IEOperation var5 = this.A.createOperation(OperationType.ROL, var2, var4);
      this.pC.pC(var2, var5, var1);
      IEGeneric var6 = var2.lsb();
      var1.r.add(this.A.createAssign(this.pC.Er, this.A.createCond(var4, var6, this.pC.Er)));
      IEOperation var7 = this.A.createOperation(OperationType.XOR, var2.msb(), var2.lsb());
      var1.r
         .add(
            this.A
               .createAssign(
                  this.pC.Cu, this.A.createCond(this.A.createOperation(OperationType.LOG_EQ, var4, this.A.createImm(1L, var4.getBitsize())), var7, this.pC.Cu)
               )
         );
   }

   void UT(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 1, 0, var1.address);
      IEGeneric var4;
      if (((vh)var1.insn).gp.A != 54032 && ((vh)var1.insn).gp.A != 53776) {
         var4 = this.pC(var2, var3);
      } else {
         var4 = this.pC(var2);
      }

      IEGeneric var5 = this.pC.A(var2.getBitsize() + 1, var1.r);
      var1.r.add(this.A.createAssign(var5, this.A.createCompose(var2, this.pC.Er)));
      var1.r.add(this.A.createAssign(var5, this.A.createOperation(OperationType.ROL, var5, var4)));
      this.pC.pC(var2, var5.part(var5.getBitsize() - 1), var1);
      var1.r.add(this.A.createAssign(this.pC.Er, var5.msb()));
      IEOperation var6 = this.A.createOperation(OperationType.XOR, var2.msb(), this.pC.Er);
      var1.r
         .add(
            this.A
               .createAssign(
                  this.pC.Cu, this.A.createCond(this.A.createOperation(OperationType.LOG_EQ, var4, this.A.createImm(1L, var4.getBitsize())), var6, this.pC.Cu)
               )
         );
   }

   void E(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 1, 0, var1.address);
      IEGeneric var4;
      if (((vh)var1.insn).gp.A != 54024 && ((vh)var1.insn).gp.A != 53768) {
         var4 = this.pC(var2, var3);
      } else {
         var4 = this.pC(var2);
      }

      IEOperation var5 = this.A.createOperation(OperationType.ROR, var2, var4);
      this.pC.pC(var2, var5, var1);
      IEGeneric var6 = var2.msb();
      var1.r.add(this.A.createAssign(this.pC.Er, this.A.createCond(var4, var6, this.pC.Er)));
      IEOperation var7 = this.A.createOperation(OperationType.XOR, var2.msb(), this.A.createOperation(OperationType.SHL, var2, this.pC.OI).msb());
      var1.r
         .add(
            this.A
               .createAssign(
                  this.pC.Cu, this.A.createCond(this.A.createOperation(OperationType.LOG_EQ, var4, this.A.createImm(1L, var4.getBitsize())), var7, this.pC.Cu)
               )
         );
   }

   void sY(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 1, 0, var1.address);
      IEGeneric var4;
      if (((vh)var1.insn).gp.A != 54040 && ((vh)var1.insn).gp.A != 53784) {
         var4 = this.pC(var2, var3);
      } else {
         var4 = this.pC(var2);
      }

      IEGeneric var5 = this.pC.A(var2.getBitsize() + 1, var1.r);
      var1.r.add(this.A.createAssign(var5, this.A.createCompose(var2, this.pC.Er)));
      var1.r.add(this.A.createAssign(var5, this.A.createOperation(OperationType.ROR, var5, var4)));
      this.pC.pC(var2, var5.part(var5.getBitsize() - 1), var1);
      var1.r.add(this.A.createAssign(this.pC.Er, var5.msb()));
      IEOperation var6 = this.A.createOperation(OperationType.XOR, var2.msb(), this.A.createOperation(OperationType.SHL, var2, this.pC.OI).msb());
      var1.r
         .add(
            this.A
               .createAssign(
                  this.pC.Cu, this.A.createCond(this.A.createOperation(OperationType.LOG_EQ, var4, this.A.createImm(1L, var4.getBitsize())), var6, this.pC.Cu)
               )
         );
   }

   void ys(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 1, 0, var1.address);
      IEGeneric var4;
      if (((vh)var1.insn).gp.A == 4005) {
         var4 = this.pC(var2);
      } else {
         var4 = this.pC.pC((vh)var1.insn, 2, 0, var1.address);
         var4 = this.pC(var2, var4);
      }

      IEGeneric var5 = this.pC.A(var2.getBitsize(), var1.r);
      this.pC.pC(var5, var2, var1, false);
      IEGeneric var6 = this.pC.A(var2.getBitsize() * 2, var1.r);
      var1.r.add(this.A.createAssign(var6, this.A.createCompose(var3, var2)));
      IEOperation var7 = this.A.createOperation(OperationType.SHL, var6, var4);
      var1.r.add(this.A.createAssign(var6, var7));
      this.pC.pC(var2, var6.slice(var2.getBitsize(), 2 * var2.getBitsize()), var1);
      IEGeneric var8 = this.A
         .createOperation(OperationType.SHL, var5, this.A.createOperation(OperationType.SUB, var4.duplicate(), this.A.createImm(1L, var4.getBitsize())))
         .msb();
      IEOperation var9 = this.A.createOperation(OperationType.LT_U, var4.duplicate(), this.A.createImm(var5.getBitsize(), var4.getBitsize()));
      var1.r.add(this.A.createAssign(this.pC.Er, this.A.createCond(this.A.createOperation(OperationType.LOG_AND, var9, var4.duplicate()), var8, this.pC.Er)));
      IEOperation var10 = this.A
         .createOperation(OperationType.XOR, var5.msb(), this.A.createOperation(OperationType.SHL, var5, this.A.createImm(1L, var5.getBitsize())).msb());
      var1.r
         .add(
            this.A
               .createAssign(
                  this.pC.Cu,
                  this.A.createCond(this.A.createOperation(OperationType.LOG_EQ, var4.duplicate(), this.A.createImm(1L, var4.getBitsize())), var10, this.pC.Cu)
               )
         );
      this.pC.A.pC(var2, var4, var1.r);
   }

   void ld(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.pC.pC((vh)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.pC.pC((vh)var1.insn, 1, 0, var1.address);
      IEGeneric var4;
      if (((vh)var1.insn).gp.A == 4013) {
         var4 = this.pC(var2);
      } else {
         var4 = this.pC.pC((vh)var1.insn, 2, 0, var1.address);
         var4 = this.pC(var2, var4);
      }

      IEGeneric var5 = this.pC.A(var2.getBitsize(), var1.r);
      this.pC.pC(var5, var2, var1, false);
      IEGeneric var6 = this.pC.A(var2.getBitsize() * 2, var1.r);
      var1.r.add(this.A.createAssign(var6, this.A.createCompose(var2, var3)));
      IEOperation var7 = this.A.createOperation(OperationType.SHR, var6, var4);
      var1.r.add(this.A.createAssign(var6, var7));
      this.pC.pC(var2, var6.part(var2.getBitsize()), var1);
      IEGeneric var8 = this.A
         .createOperation(OperationType.SHR, var5, this.A.createOperation(OperationType.SUB, var4.duplicate(), this.A.createImm(1L, var4.getBitsize())))
         .lsb();
      IEOperation var9 = this.A.createOperation(OperationType.LT_U, var4.duplicate(), this.A.createImm(var5.getBitsize(), var4.getBitsize()));
      var1.r.add(this.A.createAssign(this.pC.Er, this.A.createCond(this.A.createOperation(OperationType.LOG_AND, var9, var4.duplicate()), var8, this.pC.Er)));
      IEOperation var10 = this.A
         .createOperation(OperationType.XOR, var5.msb(), this.A.createOperation(OperationType.SHL, var5, this.A.createImm(1L, var5.getBitsize())).msb());
      var1.r
         .add(
            this.A
               .createAssign(
                  this.pC.Cu,
                  this.A.createCond(this.A.createOperation(OperationType.LOG_EQ, var4.duplicate(), this.A.createImm(1L, var4.getBitsize())), var10, this.pC.Cu)
               )
         );
      this.pC.A.pC(var2, var4, var1.r);
   }
}
