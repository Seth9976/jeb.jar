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

public class alk extends alf {
   private static final StructuredLogger kS = aco.pC(alk.class);

   public alk(IERoutineContext var1) {
      super(var1);
   }

   @Override
   public int A() {
      int var1 = 0;
      var1 += this.wS();
      return var1 + this.kS();
   }

   private int kS() {
      int var1 = 0;
      CFG var2 = this.A.getCfg();

      for (BasicBlock var4 : var2) {
         boolean var5 = false;
         int var6 = -1;
         INativeType var7 = null;
         IntGauge var8 = null;
         alk.Av var9 = null;
         TreeMap var10 = null;
         IEMem var11 = null;

         for (int var12 = 0; var12 < var4.size(); var12++) {
            IEStatement var13 = (IEStatement)var4.get(var12);
            if (var13 instanceof IEAssign && ((IEAssign)var13).getDstOperand() instanceof IEMem) {
               IEAssign var14 = (IEAssign)var13;
               IEMem var15 = (IEMem)var14.getDstOperand();
               IEGeneric var16 = var14.getSrcOperand();
               alo var17 = new alo(this.A, var15);
               IWildcardType var18 = var17.E();
               Object[] var10000 = new Object[]{var17};
               if (var18 == null && var17.kS() != null) {
                  boolean var19 = false;
                  int var20 = 0;
                  int var21 = 0;
                  if (!var5) {
                     var5 = true;
                     var6 = var12;
                     var7 = var17.kS();
                     var20 = var17.A();
                     var21 = var15.getBitsize() / 8;
                     var9 = this.pC(var17.wS(), var20);
                     var8 = new IntGauge(var7.getSize());
                     var10 = new TreeMap();
                  } else if (var7.equals(var17.kS())) {
                     var20 = var17.A();
                     var21 = var15.getBitsize() / 8;
                     alk.Av var22 = this.pC(var17.wS(), var20);
                     if (!this.pC(var9, var22)) {
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
                        var32.setType(this.A.getWildcardTypeManager().createPointer(var23));
                        Object var24 = var10.values();
                        if (this.A.getGlobalContext().isBigEndian()) {
                           ArrayList var25 = new ArrayList((Collection)var24);
                           Collections.reverse(var25);
                           var24 = var25;
                        }

                        IEAssign var33 = this.A.createAssign(this.A.createMem(var32, var23), this.A.createCompose((Collection)var24));
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

   private alk.Av pC(List var1, long var2) {
      alk.Av var4 = new alk.Av();
      var4.A -= var2;

      for (alo.Ws var6 : var1) {
         IEGeneric var7 = var6.A();
         if (var7 instanceof IEImm) {
            var4.A = var4.A + ((IEImm)var7).getValueAsLong() * var6.pC();
         } else {
            Long var8 = (Long)var4.pC.get(var7);
            if (var8 == null) {
               var8 = 0L;
            }

            var4.pC.put(var6.A(), var8 + var6.pC());
         }
      }

      return var4;
   }

   private boolean pC(alk.Av var1, alk.Av var2) {
      return var1.A == var2.A && var1.pC.equals(var2.pC);
   }

   private int wS() {
      AtomicInteger var1 = new AtomicInteger();
      CFG var2 = this.A.getCfg();

      for (BasicBlock var4 : var2) {
         for (IEStatement var6 : var4) {
            var6.visitDepthPost(new all(this, var1));
         }
      }

      int var7 = var1.get();
      if (var7 > 0) {
         var2.invalidateDataFlowAnalysis();
      }

      return var7;
   }

   private boolean pC(IEMem var1) {
      alo var2 = new alo(this.A, var1);
      IWildcardType var3 = var2.E();
      if (var3 == null && var2.UT()) {
         List var4 = var2.wS();
         alo.Ws var5 = alo.Ws.pC(var4);
         if (var5 == null && this.pC(var4)) {
            return true;
         }
      }

      return false;
   }

   private boolean pC(List var1) {
      for (alo.Ws var3 : var1) {
         if (!(var3.A() instanceof IEImm) && var3.pC() == 1L) {
            IWildcardType var4 = var3.A().getType();
            if (var4 != null && var4.isPointer()) {
               return false;
            }
         }
      }

      for (alo.Ws var15 : var1) {
         if (var15.A() instanceof IEImm && var15.pC() == 1L) {
            IEImm var16 = (IEImm)var15.A();
            if (var16.canReadAsAddress()) {
               long var5 = var16.getValueAsAddress();
               INativeContinuousItem var7 = this.A.getNativeContext().getNativeItemOver(var5);
               if (var7 instanceof INativeDataItem && var7.getMemoryAddress() != 0L) {
                  INativeDataItem var8 = (INativeDataItem)var7;
                  INativeType var9 = var8.getType();
                  if (var9 != null) {
                     int var10 = (int)(var5 - var8.getMemoryAddress());
                     IEVar var11 = this.A.createSymbolForGlobalVariable(var8);
                     Object var12 = var10 == 0 ? var11 : EUtil.add(var11, this.A.createImm(var10, var11.getBitsize()));
                     boolean var13 = var15.kS().replaceSubExpression(var15.A(), (IEGeneric)var12);
                     Assert.a(var13);
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   private static class Av {
      Map pC = new HashMap();
      long A;
   }
}
