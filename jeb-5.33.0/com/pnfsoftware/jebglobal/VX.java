package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.List;
import java.util.Map;

@SerDisabled
class VX implements zx {
   boolean pC = false;
   private CW A;
   private INativeCodeAnalyzer kS;
   private com.pnfsoftware.jeb.corei.parsers.arm.Av wS;
   private IVirtualMemory UT;
   private uj E;
   private Sp sY;
   private IEGlobalContext ys;

   public VX(INativeCodeAnalyzer var1, com.pnfsoftware.jeb.corei.parsers.arm.Av var2, Sp var3, uj var4) {
      this.kS = var1;
      this.wS = var2;
      this.UT = var1.getMemory();
      this.sY = var3;
      this.ys = var3.getGlobalContext();
      this.E = var4;
   }

   @Override
   public boolean pC(long var1, long var3, Map var5, Map var6) {
      if (var5 != null && !var5.isEmpty()) {
         List var7 = (List)var5.get(var3);
         os var8 = new os(var3, var5, var6);
         Dx var9 = new Dx(this.UT, this.E, this.sY, this.ys);
         this.A = var9.pC(var1, var7, var5, var8);
         if (this.A == null) {
            return false;
         } else if (this.A.pC()) {
            return true;
         } else if (this.wS != null && this.wS.kS != null && this.wS.kS != com.pnfsoftware.jeb.corei.parsers.x86.wn.Av.kS) {
            return false;
         } else if (this.A.A().A != null && EUtil.countVariableUse(this.A.A().A) <= 1) {
            try {
               this.pC = true;
               int var10 = ((com.pnfsoftware.jeb.corei.parsers.arm.rQ)var7.get(0)).getProcessorMode();
               if (var10 != 64) {
                  com.pnfsoftware.jeb.corei.parsers.arm.rQ var11 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var7.get(var7.size() - 1);
                  if (var11.pC().pC()) {
                     var10 = 0;
                  }
               }

               i var13 = new i(this.A, this.kS, this.wS, var10);
               return var13.pC(var1, var3);
            } catch (Exception var12) {
               return false;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   @Override
   public int pC() {
      return this.A.kS().pC();
   }

   @Override
   public long A() {
      return -1L;
   }

   @Override
   public int kS() {
      return -1;
   }

   @Override
   public Long pC(int var1) {
      return this.A.kS().pC(this.UT, var1);
   }

   @Override
   public int A(int var1) {
      return this.A.A().pC();
   }

   @Override
   public long kS(int var1) {
      return this.A.kS().A(this.UT, var1);
   }

   @Override
   public boolean wS() {
      return this.A.A().A();
   }

   @Override
   public int UT() {
      return this.A.A().kS();
   }

   @Override
   public Long pC(int var1, int var2) {
      return this.A.A().pC(this.UT, var1, var2);
   }

   @Override
   public int A(int var1, int var2) {
      return this.A.A().pC(var1);
   }
}
