package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.ETypeInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class ani {
   private static final StructuredLogger A = aco.pC(ani.class);
   IERoutineContext pC;

   public ani(IERoutineContext var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.pC = var1;
      }
   }

   public int pC() {
      AtomicInteger var1 = new AtomicInteger();
      CFG var2 = this.pC.getCfg();

      for (BasicBlock var4 : var2.getBlocks()) {
         for (IEStatement var6 : var4) {
            var6.visitDepthPost(new anj(this, var1));
         }
      }

      return var1.get();
   }

   public int A() {
      CFG var1 = this.pC.getCfg();
      IEVar var2 = this.pC.getStackPointer();
      IEVar var3 = this.pC.getRegisterMirror(var2);
      IWildcardTypeManager var4 = this.pC.getWildcardTypeManager();
      IWildcardType var5 = var4.createPointer(var4.getSlotBitsize());
      var3.setType(var5);
      int var6 = 0;

      for (IEStatement var8 : var1.instructions()) {
         var6 += var8.replaceVar(var2, var3, false);
      }

      if (var6 > 0) {
         var1.invalidateDataFlowAnalysis();
      }

      return var6;
   }

   public ETypeInfo kS() {
      return this.pC(true, true, false);
   }

   public ETypeInfo wS() {
      return this.pC(false, false, true);
   }

   private ETypeInfo pC(boolean var1, boolean var2, boolean var3) {
      CFG var4 = this.pC.getCfg();
      ETypeInfo var5 = new ETypeInfo(this.pC);
      if (var1) {
         for (IEStatement var7 : var4.instructions()) {
            var7.preUpdateTypes(var5);
         }

         if (var5.getConflictsCount() > 0) {
            A.iH("Pre-propagation typing conflicts:\n%s", var5);
         }
      }

      if (var2) {
         for (int var9 = 0; var9 < 10; var9++) {
            Object[] var10000 = new Object[]{var5.getChangedCounter()};
            var10000 = new Object[]{var9};
            var5.resetCounters();

            for (IEStatement var8 : var4.instructions()) {
               var8.updateTypes(var5);
            }

            if (var5.getChangedCounter() == 0) {
               break;
            }

            acj.pC();
         }
      }

      if (var3) {
         for (IEStatement var12 : var4.instructions()) {
            var12.postUpdateTypes(var5);
         }

         if (var5.getConflictsCount() > 0) {
            A.iH("Post-propagation typing conflicts:\n%s", var5);
         }
      }

      return var5;
   }

   public int UT() {
      int var1 = 0;
      ETypeInfo var2 = new ETypeInfo(this.pC);
      HashMap var3 = new HashMap();
      CFG var4 = this.pC.getCfg();

      for (IEStatement var6 : var4.instructions()) {
         var6.visitDepthPost(new ank(this, var3));
      }

      for (IEVar var11 : var3.keySet()) {
         Set var7 = (Set)var3.get(var11);
         if (var7.size() == 1) {
            int var8 = (Integer)var7.iterator().next();
            IWildcardType var9 = var11.getType();
            if (var11.setType(var9.updatePointedBitsize(var8), var2)) {
               var1++;
            }
         }
      }

      return var1;
   }

   public int E() {
      int var1 = 0;
      ETypeInfo var2 = new ETypeInfo(this.pC);
      HashMap var3 = new HashMap();
      CFG var4 = this.pC.getCfg();

      for (IEStatement var6 : var4.instructions()) {
         var6.visitDepthPost(new anl(this, var3));
      }

      IWildcardTypeManager var11 = this.pC.getWildcardTypeManager();

      for (IEVar var7 : var3.keySet()) {
         int var8 = (Integer)var3.get(var7);
         if (var8 != 0) {
            IWildcardType.Group var9 = var8 == 1 ? IWildcardType.Group.UINT : IWildcardType.Group.INTEGER;
            IWildcardType var10 = var11.createWithBitsizes(var7.getBitsize(), var7.getBitsize());
            if (var7.setType(var10.updateGroup(var9), var2)) {
               var1++;
            }
         }
      }

      return var1;
   }
}
