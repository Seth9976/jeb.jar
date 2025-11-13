package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ConverterInstructionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;

public class cru {
   crx q;
   IERoutineContext RF;

   cru(crx var1) {
      this.q = var1;
   }

   private IEGeneric q(IEGeneric var1, IEGeneric var2) {
      IEOperation var3;
      if (var1.getBitsize() == 64) {
         var3 = this.RF.createOperation(OperationType.AND, var2, this.RF.createImm(63L, var2.getBitsize()));
      } else {
         var3 = this.RF.createOperation(OperationType.AND, var2, this.RF.createImm(31L, var2.getBitsize()));
      }

      return var3;
   }

   private IEGeneric q(IEGeneric var1) {
      IEOperation var2;
      if (var1.getBitsize() == 64) {
         var2 = this.RF.createOperation(OperationType.AND, this.q.iu, this.RF.createImm(63L, this.q.iu.getBitsize()));
      } else {
         var2 = this.RF.createOperation(OperationType.AND, this.q.iu, this.RF.createImm(31L, this.q.iu.getBitsize()));
      }

      return var2;
   }

   void q(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      IEGeneric var4;
      if (((ctc)var1.insn).io.RF != 54048 && ((ctc)var1.insn).io.RF != 53792) {
         var4 = this.q(var2, var3);
      } else {
         var4 = this.q(var2);
      }

      IEGeneric var5 = this.q.RF(var2.getBitsize(), var1.r);
      this.q.q(var5, var2, var1, false);
      IEOperation var6 = this.RF.createOperation(OperationType.SHL, var2, var4);
      this.q.q(var2, var6, var1);
      IEGeneric var7 = this.RF
         .createOperation(OperationType.SHL, var5, this.RF.createOperation(OperationType.SUB, var4.duplicate(), this.RF.createImm(1L, var4.getBitsize())))
         .msb();
      IEOperation var8 = this.RF.createOperation(OperationType.LT_U, var4.duplicate(), this.RF.createImm(var5.getBitsize(), var4.getBitsize()));
      var1.r.add(this.RF.createAssign(this.q.WX, this.RF.createCond(this.RF.createOperation(OperationType.LOG_AND, var8, var4.duplicate()), var7, this.q.WX)));
      IEOperation var9 = this.RF
         .createOperation(OperationType.XOR, var5.msb(), this.RF.createOperation(OperationType.SHL, var5, this.RF.createImm(1L, var5.getBitsize())).msb());
      var1.r
         .add(
            this.RF
               .createAssign(
                  this.q.Cl,
                  this.RF
                     .createCond(this.RF.createOperation(OperationType.LOG_EQ, var4.duplicate(), this.RF.createImm(1L, var4.getBitsize())), var9, this.q.Cl)
               )
         );
      this.q.Yw.q(var2, var4, var1.r);
   }

   void RF(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      IEGeneric var4;
      if (((ctc)var1.insn).io.RF != 54056 && ((ctc)var1.insn).io.RF != 53800) {
         var4 = this.q(var2, var3);
      } else {
         var4 = this.q(var2);
      }

      IEOperation var5 = this.RF.createOperation(OperationType.SHR, var2, var4);
      IEGeneric var6 = this.q.RF(var2.getBitsize(), var1.r);
      this.q.q(var6, var2, var1, false);
      this.q.q(var2, var5, var1);
      IEGeneric var7 = this.RF
         .createOperation(OperationType.SHR, var6, this.RF.createOperation(OperationType.SUB, var4.duplicate(), this.RF.createImm(1L, var4.getBitsize())))
         .lsb();
      IEOperation var8 = this.RF.createOperation(OperationType.LT_U, var4.duplicate(), this.RF.createImm(var6.getBitsize(), var4.getBitsize()));
      var1.r.add(this.RF.createAssign(this.q.WX, this.RF.createCond(this.RF.createOperation(OperationType.LOG_AND, var8, var4.duplicate()), var7, this.q.WX)));
      var1.r
         .add(
            this.RF
               .createAssign(
                  this.q.Cl,
                  this.RF.createCond(this.RF.createOperation(OperationType.LOG_EQ, var4, this.RF.createImm(1L, var4.getBitsize())), var6.msb(), this.q.Cl)
               )
         );
      this.q.Yw.q(var2, var4, var1.r);
   }

   void xK(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      IEGeneric var4;
      if (((ctc)var1.insn).io.RF != 54072 && ((ctc)var1.insn).io.RF != 53816) {
         var4 = this.q(var2, var3);
      } else {
         var4 = this.q(var2);
      }

      IEOperation var5 = this.RF.createOperation(OperationType.SAR, var2, var4);
      IEGeneric var6 = this.q.RF(var2.getBitsize(), var1.r);
      this.q.q(var6, var2, var1, false);
      this.q.q(var2, var5, var1);
      IEGeneric var7 = this.RF
         .createOperation(OperationType.SAR, var6, this.RF.createOperation(OperationType.SUB, var4.duplicate(), this.RF.createImm(1L, var4.getBitsize())))
         .lsb();
      IEOperation var8 = this.RF.createOperation(OperationType.LT_U, var4.duplicate(), this.RF.createImm(var6.getBitsize(), var4.getBitsize()));
      var1.r.add(this.RF.createAssign(this.q.WX, this.RF.createCond(this.RF.createOperation(OperationType.LOG_AND, var8, var4.duplicate()), var7, this.q.WX)));
      var1.r
         .add(
            this.RF
               .createAssign(
                  this.q.Cl,
                  this.RF.createCond(this.RF.createOperation(OperationType.LOG_EQ, var4, this.RF.createImm(1L, var4.getBitsize())), this.q.Eq, this.q.Cl)
               )
         );
      this.q.Yw.q(var2, var4, var1.r);
   }

   void Dw(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      IEGeneric var4;
      if (((ctc)var1.insn).io.RF != 54016 && ((ctc)var1.insn).io.RF != 53760) {
         var4 = this.q(var2, var3);
      } else {
         var4 = this.q(var2);
      }

      IEOperation var5 = this.RF.createOperation(OperationType.ROL, var2, var4);
      this.q.q(var2, var5, var1);
      IEGeneric var6 = var2.lsb();
      var1.r.add(this.RF.createAssign(this.q.WX, this.RF.createCond(var4, var6, this.q.WX)));
      IEOperation var7 = this.RF.createOperation(OperationType.XOR, var2.msb(), var2.lsb());
      var1.r
         .add(
            this.RF
               .createAssign(
                  this.q.Cl, this.RF.createCond(this.RF.createOperation(OperationType.LOG_EQ, var4, this.RF.createImm(1L, var4.getBitsize())), var7, this.q.Cl)
               )
         );
   }

   void Uv(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      IEGeneric var4;
      if (((ctc)var1.insn).io.RF != 54032 && ((ctc)var1.insn).io.RF != 53776) {
         var4 = this.q(var2, var3);
      } else {
         var4 = this.q(var2);
      }

      IEGeneric var5 = this.q.RF(var2.getBitsize() + 1, var1.r);
      var1.r.add(this.RF.createAssign(var5, this.RF.createCompose(var2, this.q.WX)));
      var1.r.add(this.RF.createAssign(var5, this.RF.createOperation(OperationType.ROL, var5, var4)));
      this.q.q(var2, var5.part(var5.getBitsize() - 1), var1);
      var1.r.add(this.RF.createAssign(this.q.WX, var5.msb()));
      IEOperation var6 = this.RF.createOperation(OperationType.XOR, var2.msb(), this.q.WX);
      var1.r
         .add(
            this.RF
               .createAssign(
                  this.q.Cl, this.RF.createCond(this.RF.createOperation(OperationType.LOG_EQ, var4, this.RF.createImm(1L, var4.getBitsize())), var6, this.q.Cl)
               )
         );
   }

   void oW(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      IEGeneric var4;
      if (((ctc)var1.insn).io.RF != 54024 && ((ctc)var1.insn).io.RF != 53768) {
         var4 = this.q(var2, var3);
      } else {
         var4 = this.q(var2);
      }

      IEOperation var5 = this.RF.createOperation(OperationType.ROR, var2, var4);
      this.q.q(var2, var5, var1);
      IEGeneric var6 = var2.msb();
      var1.r.add(this.RF.createAssign(this.q.WX, this.RF.createCond(var4, var6, this.q.WX)));
      IEOperation var7 = this.RF.createOperation(OperationType.XOR, var2.msb(), this.RF.createOperation(OperationType.SHL, var2, this.q.hP).msb());
      var1.r
         .add(
            this.RF
               .createAssign(
                  this.q.Cl, this.RF.createCond(this.RF.createOperation(OperationType.LOG_EQ, var4, this.RF.createImm(1L, var4.getBitsize())), var7, this.q.Cl)
               )
         );
   }

   void gO(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      IEGeneric var4;
      if (((ctc)var1.insn).io.RF != 54040 && ((ctc)var1.insn).io.RF != 53784) {
         var4 = this.q(var2, var3);
      } else {
         var4 = this.q(var2);
      }

      IEGeneric var5 = this.q.RF(var2.getBitsize() + 1, var1.r);
      var1.r.add(this.RF.createAssign(var5, this.RF.createCompose(var2, this.q.WX)));
      var1.r.add(this.RF.createAssign(var5, this.RF.createOperation(OperationType.ROR, var5, var4)));
      this.q.q(var2, var5.part(var5.getBitsize() - 1), var1);
      var1.r.add(this.RF.createAssign(this.q.WX, var5.msb()));
      IEOperation var6 = this.RF.createOperation(OperationType.XOR, var2.msb(), this.RF.createOperation(OperationType.SHL, var2, this.q.hP).msb());
      var1.r
         .add(
            this.RF
               .createAssign(
                  this.q.Cl, this.RF.createCond(this.RF.createOperation(OperationType.LOG_EQ, var4, this.RF.createImm(1L, var4.getBitsize())), var6, this.q.Cl)
               )
         );
   }

   void nf(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      IEGeneric var4;
      if (((ctc)var1.insn).io.RF == 4005) {
         var4 = this.q(var2);
      } else {
         var4 = this.q.q((ctc)var1.insn, 2, 0, var1.address);
         var4 = this.q(var2, var4);
      }

      IEGeneric var5 = this.q.RF(var2.getBitsize(), var1.r);
      this.q.q(var5, var2, var1, false);
      IEGeneric var6 = this.q.RF(var2.getBitsize() * 2, var1.r);
      var1.r.add(this.RF.createAssign(var6, this.RF.createCompose(var3, var2)));
      IEOperation var7 = this.RF.createOperation(OperationType.SHL, var6, var4);
      var1.r.add(this.RF.createAssign(var6, var7));
      this.q.q(var2, var6.slice(var2.getBitsize(), 2 * var2.getBitsize()), var1);
      IEGeneric var8 = this.RF
         .createOperation(OperationType.SHL, var5, this.RF.createOperation(OperationType.SUB, var4.duplicate(), this.RF.createImm(1L, var4.getBitsize())))
         .msb();
      IEOperation var9 = this.RF.createOperation(OperationType.LT_U, var4.duplicate(), this.RF.createImm(var5.getBitsize(), var4.getBitsize()));
      var1.r.add(this.RF.createAssign(this.q.WX, this.RF.createCond(this.RF.createOperation(OperationType.LOG_AND, var9, var4.duplicate()), var8, this.q.WX)));
      IEOperation var10 = this.RF
         .createOperation(OperationType.XOR, var5.msb(), this.RF.createOperation(OperationType.SHL, var5, this.RF.createImm(1L, var5.getBitsize())).msb());
      var1.r
         .add(
            this.RF
               .createAssign(
                  this.q.Cl,
                  this.RF
                     .createCond(this.RF.createOperation(OperationType.LOG_EQ, var4.duplicate(), this.RF.createImm(1L, var4.getBitsize())), var10, this.q.Cl)
               )
         );
      this.q.Yw.q(var2, var4, var1.r);
   }

   void gP(ConverterInstructionEntry var1) {
      IEGeneric var2 = this.q.q((ctc)var1.insn, 0, 0, var1.address);
      IEGeneric var3 = this.q.q((ctc)var1.insn, 1, 0, var1.address);
      IEGeneric var4;
      if (((ctc)var1.insn).io.RF == 4013) {
         var4 = this.q(var2);
      } else {
         var4 = this.q.q((ctc)var1.insn, 2, 0, var1.address);
         var4 = this.q(var2, var4);
      }

      IEGeneric var5 = this.q.RF(var2.getBitsize(), var1.r);
      this.q.q(var5, var2, var1, false);
      IEGeneric var6 = this.q.RF(var2.getBitsize() * 2, var1.r);
      var1.r.add(this.RF.createAssign(var6, this.RF.createCompose(var2, var3)));
      IEOperation var7 = this.RF.createOperation(OperationType.SHR, var6, var4);
      var1.r.add(this.RF.createAssign(var6, var7));
      this.q.q(var2, var6.part(var2.getBitsize()), var1);
      IEGeneric var8 = this.RF
         .createOperation(OperationType.SHR, var5, this.RF.createOperation(OperationType.SUB, var4.duplicate(), this.RF.createImm(1L, var4.getBitsize())))
         .lsb();
      IEOperation var9 = this.RF.createOperation(OperationType.LT_U, var4.duplicate(), this.RF.createImm(var5.getBitsize(), var4.getBitsize()));
      var1.r.add(this.RF.createAssign(this.q.WX, this.RF.createCond(this.RF.createOperation(OperationType.LOG_AND, var9, var4.duplicate()), var8, this.q.WX)));
      IEOperation var10 = this.RF
         .createOperation(OperationType.XOR, var5.msb(), this.RF.createOperation(OperationType.SHL, var5, this.RF.createImm(1L, var5.getBitsize())).msb());
      var1.r
         .add(
            this.RF
               .createAssign(
                  this.q.Cl,
                  this.RF
                     .createCond(this.RF.createOperation(OperationType.LOG_EQ, var4.duplicate(), this.RF.createImm(1L, var4.getBitsize())), var10, this.q.Cl)
               )
         );
      this.q.Yw.q(var2, var4, var1.r);
   }
}
