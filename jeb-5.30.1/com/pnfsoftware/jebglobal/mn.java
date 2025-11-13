package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import java.util.List;
import java.util.Random;

public class mn {
   uq q;
   IERoutineContext RF;

   public mn(uq var1) {
      this.q = var1;
   }

   public void q(fA var1, List var2, long var3, char var5) {
      IEGeneric var6 = this.q.q(var1, var1.RF()[0]).q();
      var2.add(this.RF.createAssign(var6, this.q(var6, var5)));
   }

   public void RF(fA var1, List var2, long var3, char var5) {
      IEGeneric var6 = this.q.q(var1, var1.RF()[0]).q();
      var2.add(this.RF.createAssign(var6, this.q(var6, var5)));
   }

   public void xK(fA var1, List var2, long var3, char var5) {
      IEGeneric var6 = this.q.getRegisterVariableFromNativeRegisterId(var1.Dw().q().endsWith("1716") ? 17L : 30L);
      var2.add(this.RF.createAssign(var6, this.q(var6, var5)));
   }

   public void Dw(fA var1, List var2, long var3, char var5) {
      IEGeneric var6 = this.q.q(var1, var1.RF()[0]).q();
      var2.add(this.RF.createAssign(var6, this.RF(var6, var5)));
   }

   public void Uv(fA var1, List var2, long var3, char var5) {
      IEGeneric var6 = this.q.q(var1, var1.RF()[0]).q();
      var2.add(this.RF.createAssign(var6, this.RF(var6, var5)));
   }

   public void oW(fA var1, List var2, long var3, char var5) {
      IEGeneric var6 = this.q.getRegisterVariableFromNativeRegisterId(var1.Dw().q().endsWith("1716") ? 17L : 30L);
      var2.add(this.RF.createAssign(var6, this.RF(var6, var5)));
   }

   public void gO(fA var1, List var2, long var3, char var5) {
      IEGeneric var6 = this.q.q(var1, var1.RF()[0]).q();
      var2.add(this.q.es.q(var1, var2, var3, this.RF(var6, var5)));
   }

   public void nf(fA var1, List var2, long var3, char var5) {
      IEGeneric var6 = this.q.q(var1, var1.RF()[0]).q();
      IEImm var7 = this.RF.createImm(var3 + var1.getSize(), this.q.q().getBitsize());
      IEGeneric var8 = this.q(var1, var2, var3, var7, this.RF(var6, var5));
      var2.add(this.q.es.q(var1, var2, var3, var8));
   }

   public void gP(fA var1, List var2, long var3, char var5) {
      IEVar var6 = this.q.q();
      var2.add(this.q.es.q(var1, var2, var3, this.RF(var6, var5)));
   }

   private IEGeneric q(fA var1, List var2, long var3, IEImm var5, IEGeneric var6) {
      CW var7 = var1.RF()[0];
      if (GC.q(var7, this.q.JF)) {
         IEVar var8 = this.q.gO(var5.getBitsize());
         var2.add(this.RF.createAssign(var8, var6));
         var2.add(this.RF.createAssign(this.q.q(), var5));
         return var8;
      } else {
         var2.add(this.RF.createAssign(this.q.q(), var5));
         return var6;
      }
   }

   public IEGeneric q(IEGeneric var1, char var2) {
      int var3 = this.q();
      Random var4 = new Random(125L);
      return this.RF.createCompose(var1.part(var3), this.RF.createImm(var4.nextInt() & 0xFF, var1.getBitsize() - var3));
   }

   public IEGeneric RF(IEGeneric var1, char var2) {
      int var3 = this.q();
      return this.RF.createCompose(var1.part(var3), this.RF.createImm(0L, var1.getBitsize() - var3));
   }

   private int q() {
      int var1 = 0;
      boolean var2 = true;
      boolean var3 = false;
      int var4 = !var3 ? 39 : (var2 ? 47 : 48);
      if (var1 > var4) {
         var1 = var4;
      }

      int var5 = this.RF();
      if (var1 < var5) {
         var1 = var5;
      }

      return 64 - var1;
   }

   private int RF() {
      if (this.q.getNativeContext() != null && this.q.getNativeContext().getMemory() != null) {
         for (Long var3 : this.q.getNativeContext().getMemory().getAllocatedPageBases()) {
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
