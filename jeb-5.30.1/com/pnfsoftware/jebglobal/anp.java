package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.IntGauge;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class anp extends ank {
   private static final StructuredLogger xK = aeg.q(anp.class);

   public anp(IERoutineContext var1) {
      super(var1);
   }

   @Override
   public int Dw() {
      int var1 = 0;
      var1 += this.oW();
      return var1 + this.Uv();
   }

   private int Uv() {
      int var1 = 0;
      CFG var2 = this.RF.getCfg();

      for (BasicBlock var4 : var2) {
         boolean var5 = false;
         int var6 = -1;
         INativeType var7 = null;
         IntGauge var8 = null;
         anp.eo var9 = null;
         TreeMap var10 = null;
         IEMem var11 = null;

         for (int var12 = 0; var12 < var4.size(); var12++) {
            IEStatement var13 = (IEStatement)var4.get(var12);
            if (var13 instanceof IEAssign && ((IEAssign)var13).getDstOperand() instanceof IEMem) {
               IEAssign var14 = (IEAssign)var13;
               IEMem var15 = (IEMem)var14.getDstOperand();
               IEGeneric var16 = var14.getSrcOperand();
               ant var17 = new ant(this.RF, var15);
               IWildcardType var18 = var17.nf();
               Object[] var10000 = new Object[]{var17};
               if (var18 == null && var17.Uv() != null) {
                  boolean var19 = false;
                  int var20 = 0;
                  int var21 = 0;
                  if (!var5) {
                     var5 = true;
                     var6 = var12;
                     var7 = var17.Uv();
                     var20 = var17.Dw();
                     var21 = var15.getBitsize() / 8;
                     var9 = this.q(var17.oW(), var20);
                     var8 = new IntGauge(var7.getSize());
                     var10 = new TreeMap();
                  } else if (var7.equals(var17.Uv())) {
                     var20 = var17.Dw();
                     var21 = var15.getBitsize() / 8;
                     anp.eo var22 = this.q(var17.oW(), var20);
                     if (!this.q(var9, var22)) {
                        var19 = true;
                     }
                  } else {
                     var19 = true;
                  }

                  if (!var19 && var8.record(var20, var20 + var21)) {
                     if (var20 == 0) {
                        var11 = var15;
                     }

                     var10.put(var20, var16);
                     if (var8.isComplete()) {
                        IEGeneric var32 = var11.getReference();
                        int var23 = var7.getSize() * 8;
                        var32.setType(this.RF.getWildcardTypeManager().createPointer(var23));
                        Object var24 = var10.values();
                        if (this.RF.getGlobalContext().isBigEndian()) {
                           ArrayList var25 = new ArrayList((Collection)var24);
                           Collections.reverse(var25);
                           var24 = var25;
                        }

                        IEAssign var33 = this.RF.createAssign(this.RF.createMem(var32, var23), this.RF.createCompose((Collection)var24));
                        long var26 = var4.getAddressOfInstruction(var6);
                        long var28 = var4.getAddressOfInstruction(var12) + var14.getSize();
                        int var30 = (int)(var28 - var26);
                        var33.setSize(var30);

                        for (int var31 = var12 + 1 - var6; var31 >= 1; var31--) {
                           var33.copyLowerLevelAddresses((IEStatement)var4.get(var6));
                           var4.remove(var6);
                        }

                        var4.add(var6, var33);
                        var1++;
                     }
                  } else {
                     var5 = false;
                     var12++;
                  }
               }
            } else if (var5) {
               var5 = false;
            }
         }
      }

      if (var1 > 0) {
         var2.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   private anp.eo q(List var1, long var2) {
      anp.eo var4 = new anp.eo();
      var4.RF -= var2;

      for (ant.ej var6 : var1) {
         IEGeneric var7 = var6.RF();
         if (var7 instanceof IEImm) {
            var4.RF = var4.RF + ((IEImm)var7).getValueAsLong() * var6.q();
         } else {
            Long var8 = (Long)var4.q.get(var7);
            if (var8 == null) {
               var8 = 0L;
            }

            var4.q.put(var6.RF(), var8 + var6.q());
         }
      }

      return var4;
   }

   private boolean q(anp.eo var1, anp.eo var2) {
      return var1.RF == var2.RF && var1.q.equals(var2.q);
   }

   private int oW() {
      AtomicInteger var1 = new AtomicInteger();
      CFG var2 = this.RF.getCfg();

      for (BasicBlock var4 : var2) {
         for (IEStatement var6 : var4) {
            var6.visitDepthPost(new anq(this, var1));
         }
      }

      int var7 = var1.get();
      if (var7 > 0) {
         var2.invalidateDataFlowAnalysis();
      }

      return var7;
   }

   private boolean q(IEMem var1) {
      ant var2 = new ant(this.RF, var1);
      IWildcardType var3 = var2.nf();
      if (var3 == null && var2.gO()) {
         List var4 = var2.oW();
         ant.ej var5 = ant.ej.q(var4);
         if (var5 == null && this.q(var4)) {
            return true;
         }
      }

      return false;
   }

   private boolean q(List var1) {
      for (ant.ej var3 : var1) {
         if (!(var3.RF() instanceof IEImm) && var3.q() == 1L) {
            IWildcardType var4 = var3.RF().getType();
            if (var4 != null && var4.isPointer()) {
               return false;
            }
         }
      }

      for (ant.ej var15 : var1) {
         if (var15.RF() instanceof IEImm && var15.q() == 1L) {
            IEImm var16 = (IEImm)var15.RF();
            if (var16.canReadAsAddress()) {
               long var5 = var16.getValueAsAddress();
               INativeContinuousItem var7 = this.RF.getNativeContext().getNativeItemOver(var5);
               if (var7 instanceof INativeDataItem && var7.getMemoryAddress() != 0L) {
                  INativeDataItem var8 = (INativeDataItem)var7;
                  INativeType var9 = var8.getType();
                  if (var9 != null) {
                     int var10 = (int)(var5 - var8.getMemoryAddress());
                     IEVar var11 = this.RF.createSymbolForGlobalVariable(var8);
                     Object var12 = var10 == 0 ? var11 : EUtil.add(var11, this.RF.createImm(var10, var11.getBitsize()));
                     boolean var13 = var15.xK().replaceSubExpression(var15.RF(), (IEGeneric)var12);
                     Assert.a(var13);
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   private static class eo {
      Map q = new HashMap();
      long RF;
   }
}
