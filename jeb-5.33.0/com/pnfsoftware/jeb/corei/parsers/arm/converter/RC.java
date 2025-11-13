package com.pnfsoftware.jeb.corei.parsers.arm.converter;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jebglobal.LC;
import com.pnfsoftware.jebglobal.Yg;
import java.util.List;
import java.util.Random;

public class RC {
   HE pC;
   IERoutineContext A;

   public RC(HE var1) {
      this.pC = var1;
   }

   public void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, char var5) {
      this.kS(var1, var2, var3, var5);
   }

   public void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, char var5) {
      this.kS(var1, var2, var3, var5);
   }

   public void kS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, char var5) {
      IEGeneric var6 = this.pC.pC(var1, var1.A()[0]).pC();
      var2.add(this.A.createAssign(var6, this.pC(var6, var5)));
   }

   public void wS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, char var5) {
      IEGeneric var6 = this.pC.getRegisterVariableFromNativeRegisterId(var1.wS().pC().endsWith("1716") ? 17L : 30L);
      var2.add(this.A.createAssign(var6, this.pC(var6, var5)));
   }

   public void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      IEGeneric var5 = this.pC.pC(var1, var1.A()[0]).pC();
      var2.add(this.A.createAssign(var5, this.pC(var5, '0').leftShift(32)));
   }

   public void UT(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, char var5) {
      this.sY(var1, var2, var3, var5);
   }

   public void E(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, char var5) {
      this.sY(var1, var2, var3, var5);
   }

   public void sY(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, char var5) {
      IEGeneric var6 = this.pC.pC(var1, var1.A()[0]).pC();
      var2.add(this.A.createAssign(var6, this.A(var6, var5)));
   }

   public void ys(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, char var5) {
      IEGeneric var6 = this.pC.getRegisterVariableFromNativeRegisterId(var1.wS().pC().endsWith("1716") ? 17L : 30L);
      var2.add(this.A.createAssign(var6, this.A(var6, var5)));
   }

   public void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      char var5 = '0';
      if (var1.getCountOfOperands() > 0) {
         this.sY(var1, var2, var3, var5);
      } else {
         IEVar var6 = this.pC.pC();
         var2.add(this.A.createAssign(var6, this.A(var6, var5)));
      }
   }

   public void ld(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, char var5) {
      IEGeneric var6 = this.pC.pC(var1, var1.A()[0]).pC();
      var2.add(this.pC.wS.pC(var1, var2, var3, this.A(var6, var5)));
   }

   public void gp(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, char var5) {
      IEGeneric var6 = this.pC.pC(var1, var1.A()[0]).pC();
      IEImm var7 = this.A.createImm(var3 + var1.getSize(), this.pC.pC().getBitsize());
      IEGeneric var8 = this.pC(var1, var2, var3, var7, this.A(var6, var5));
      var2.add(this.pC.wS.pC(var1, var2, var3, var8));
   }

   public void oT(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, char var5) {
      IEVar var6 = this.pC.pC();
      var2.add(this.pC.wS.pC(var1, var2, var3, this.A(var6, var5)));
   }

   private IEGeneric pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3, IEImm var5, IEGeneric var6) {
      Yg var7 = var1.A()[0];
      if (LC.pC(var7, this.pC.fI)) {
         IEVar var8 = this.pC.E(var5.getBitsize());
         var2.add(this.A.createAssign(var8, var6));
         var2.add(this.A.createAssign(this.pC.pC(), var5));
         return var8;
      } else {
         var2.add(this.A.createAssign(this.pC.pC(), var5));
         return var6;
      }
   }

   public IEGeneric pC(IEGeneric var1, char var2) {
      int var3 = this.pC();
      Random var4 = new Random(125L);
      return this.A.createCompose(var1.part(var3), this.A.createImm(var4.nextInt() & 0xFF, var1.getBitsize() - var3));
   }

   public IEGeneric A(IEGeneric var1, char var2) {
      int var3 = this.pC();
      return this.A.createCompose(var1.part(var3), this.A.createImm(0L, var1.getBitsize() - var3));
   }

   private int pC() {
      int var1 = 0;
      boolean var2 = true;
      boolean var3 = false;
      int var4 = !var3 ? 39 : (var2 ? 47 : 48);
      if (var1 > var4) {
         var1 = var4;
      }

      int var5 = this.A();
      if (var1 < var5) {
         var1 = var5;
      }

      return 64 - var1;
   }

   private int A() {
      if (this.pC.getNativeContext() != null && this.pC.getNativeContext().getMemory() != null) {
         for (Long var3 : this.pC.getNativeContext().getMemory().getAllocatedPageBases()) {
            if ((var3 & 9218868437227405312L) != 0L) {
               return 12;
            }
         }

         return 16;
      } else {
         return 12;
      }
   }
}
