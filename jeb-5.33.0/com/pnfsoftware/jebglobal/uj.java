package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;

@Ser
public class uj implements QW {
   private static final ILogger sY = GlobalLog.getLogger(uj.class);
   @SerId(6)
   int pC = -2;
   @SerId(8)
   long A;
   @SerId(9)
   com.pnfsoftware.jeb.corei.parsers.arm.rQ kS;
   @SerId(10)
   boolean wS;
   @SerId(14)
   IEGeneric UT;
   @SerId(16)
   IEGeneric E;

   private uj(IEGeneric var1, IEGeneric var2) {
      this.UT = var1;
      this.E = var2;
   }

   public uj(uj var1, long var2, com.pnfsoftware.jeb.corei.parsers.arm.rQ var4, boolean var5) {
      if (var1.UT != null) {
         this.UT = var1.UT.duplicate();
      }

      if (var1.E != null) {
         this.E = var1.E.duplicate();
      }

      this.pC = var1.pC == -1 ? (var4.getProcessorMode() == 16 ? 1 : 0) : var1.pC;
      this.A = var2;
      this.kS = var4;
      this.wS = var5;
   }

   @Override
   public boolean pC() {
      return this.UT != null;
   }

   @Override
   public boolean A() {
      return this.kS != null;
   }

   @Override
   public QW pC(Sp var1, long var2, com.pnfsoftware.jeb.corei.parsers.arm.rQ var4, boolean var5) {
      return new uj(this, var2, var4, var5);
   }

   @Override
   public zx pC(INativeCodeAnalyzer var1, com.pnfsoftware.jeb.corei.parsers.arm.Av var2, Sp var3) {
      return new VX(var1, var2, var3, this);
   }

   public static QW pC(INativeCodeAnalyzer var0, Sp var1, long var2) {
      long var4 = var2;
      com.pnfsoftware.jeb.corei.parsers.arm.rQ var6 = PU.pC(var0, var2);
      if (var6 == null) {
         return null;
      } else {
         ArrayList var7 = new ArrayList();

         int var8;
         for (var8 = 0; !var6.pC().isPCUpdated() && var8 < 50; var8++) {
            var7.add(var6);
            var4 += var6.getSize();
            var6 = PU.pC(var0, var4);
            if (var6 == null) {
               return null;
            }
         }

         if (var8 == 50) {
            return null;
         } else {
            var7.add(var6);
            Ro.K var9 = new Ro.K();
            var9.pC(var1.getProgramCounter(), null);
            var9.pC = var1.E();

            for (int var10 = var7.size() - 1; var10 >= 0; var4 -= var10 >= 0 ? ((com.pnfsoftware.jeb.corei.parsers.arm.rQ)var7.get(var10)).getSize() : 0L) {
               if (var9.pC.equals(var1.E()) && var10 < var7.size() - 1) {
                  var9.pC = EUtil.imm(((com.pnfsoftware.jeb.corei.parsers.arm.rQ)var7.get(var7.size() - 1)).getProcessorMode() == 16 ? 1L : 0L, 1);
               }

               if (!var1.pC((com.pnfsoftware.jeb.corei.parsers.arm.rQ)var7.get(var10), var4, var9)) {
                  return null;
               }

               if (!var9.kS()) {
                  return null;
               }

               var10--;
            }

            if (var9.pC.equals(var1.E())) {
               var9.pC = EUtil.imm(((com.pnfsoftware.jeb.corei.parsers.arm.rQ)var7.get(var7.size() - 1)).getProcessorMode() == 16 ? 1L : 0L, 1);
            }

            Ei var12 = new Ei(var1.getGlobalContext());
            boolean var11 = var12.pC(var9.pC(), var9.pC, var1.kS());
            if (!var11) {
               return null;
            } else if (var6.wS().pC().equals("BX") && LC.pC(var6.A()[0], var0.getProcessor().getMode())) {
               return new uj(var9.pC(), var9.pC);
            } else {
               return var6.wS().pC().equals("MOV") && var6.A()[0].A(var0.getProcessor().getMode()) && LC.pC(var6.A()[1], var0.getProcessor().getMode())
                  ? new uj(var9.pC(), var9.pC)
                  : null;
            }
         }
      }
   }

   public IEGeneric kS() {
      return (IEGeneric)(this.E != null ? this.E : EUtil.imm(this.pC, 1));
   }
}
