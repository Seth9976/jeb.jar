package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class AL implements iD {
   private static final ILogger gO = GlobalLog.getLogger(AL.class);
   @SerId(6)
   int q = -2;
   @SerId(8)
   long RF;
   @SerId(9)
   fA xK;
   @SerId(10)
   boolean Dw;
   @SerId(14)
   IEGeneric Uv;
   @SerId(16)
   IEGeneric oW;

   private AL(IEGeneric var1, IEGeneric var2) {
      this.Uv = var1;
      this.oW = var2;
   }

   public AL(AL var1, long var2, fA var4, boolean var5) {
      if (var1.Uv != null) {
         this.Uv = var1.Uv.duplicate();
      }

      if (var1.oW != null) {
         this.oW = var1.oW.duplicate();
      }

      this.q = var1.q == -1 ? (var4.getProcessorMode() == 16 ? 1 : 0) : var1.q;
      this.RF = var2;
      this.xK = var4;
      this.Dw = var5;
   }

   @Override
   public boolean q() {
      return this.Uv != null;
   }

   @Override
   public boolean RF() {
      return this.xK != null;
   }

   @Override
   public iD q(bR var1, long var2, fA var4, boolean var5) {
      return new AL(this, var2, var4, var5);
   }

   @Override
   public BH q(INativeCodeAnalyzer var1, FS var2, bR var3) {
      return new jp(var1, var2, var3, this);
   }

   public static iD q(INativeCodeAnalyzer var0, bR var1, long var2) {
      long var4 = var2;
      fA var6 = OC.q(var0, var2);
      if (var6 == null) {
         return null;
      } else {
         ArrayList var7 = new ArrayList();

         int var8;
         for (var8 = 0; !var6.q().isPCUpdated() && var8 < 50; var8++) {
            var7.add(var6);
            var4 += var6.getSize();
            var6 = OC.q(var0, var4);
            if (var6 == null) {
               return null;
            }
         }

         if (var8 == 50) {
            return null;
         } else {
            var7.add(var6);
            Ia.nI var9 = new Ia.nI();
            var9.q(var1.getProgramCounter(), null);
            var9.q = var1.oW();

            for (int var10 = var7.size() - 1; var10 >= 0; var4 -= var10 >= 0 ? ((fA)var7.get(var10)).getSize() : 0L) {
               if (var9.q.equals(var1.oW()) && var10 < var7.size() - 1) {
                  var9.q = EUtil.imm(((fA)var7.get(var7.size() - 1)).getProcessorMode() == 16 ? 1L : 0L, 1);
               }

               if (!var1.q((fA)var7.get(var10), var4, var9)) {
                  return null;
               }

               if (!var9.xK()) {
                  return null;
               }

               var10--;
            }

            if (var9.q.equals(var1.oW())) {
               var9.q = EUtil.imm(((fA)var7.get(var7.size() - 1)).getProcessorMode() == 16 ? 1L : 0L, 1);
            }

            tx var12 = new tx(var1.getGlobalContext());
            boolean var11 = var12.q(var9.q(), var9.q, var1.xK());
            if (!var11) {
               return null;
            } else if (var6.Dw().q().equals("BX") && GC.q(var6.RF()[0], var0.getProcessor().getMode())) {
               return new AL(var9.q(), var9.q);
            } else {
               return var6.Dw().q().equals("MOV") && var6.RF()[0].RF(var0.getProcessor().getMode()) && GC.q(var6.RF()[1], var0.getProcessor().getMode())
                  ? new AL(var9.q(), var9.q)
                  : null;
            }
         }
      }
   }

   private static void q(long var0, List var2) {
   }

   public IEGeneric xK() {
      return (IEGeneric)(this.oW != null ? this.oW : EUtil.imm(this.q, 1));
   }
}
