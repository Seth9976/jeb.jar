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

public class aps {
   private static final StructuredLogger RF = aeg.q(aps.class);
   IERoutineContext q;

   public aps(IERoutineContext var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.q = var1;
      }
   }

   public int q() {
      AtomicInteger var1 = new AtomicInteger();
      CFG var2 = this.q.getCfg();

      for (BasicBlock var4 : var2.getBlocks()) {
         for (IEStatement var6 : var4) {
            var6.visitDepthPost(new apt(this, var1));
         }
      }

      return var1.get();
   }

   public int RF() {
      CFG var1 = this.q.getCfg();
      IEVar var2 = this.q.getStackPointer();
      IEVar var3 = this.q.getRegisterMirror(var2);
      IWildcardTypeManager var4 = this.q.getWildcardTypeManager();
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

   public ETypeInfo xK() {
      return this.q(true, true, false);
   }

   public ETypeInfo Dw() {
      return this.q(false, false, true);
   }

   private ETypeInfo q(boolean var1, boolean var2, boolean var3) {
      CFG var4 = this.q.getCfg();
      ETypeInfo var5 = new ETypeInfo(this.q);
      if (var1) {
         for (IEStatement var7 : var4.instructions()) {
            var7.preUpdateTypes(var5);
         }

         if (var5.getConflictsCount() > 0) {
            RF.iH("Pre-propagation typing conflicts:\n%s", var5);
         }
      }

      if (var2) {
         for (int var9 = 0; var9 < 100; var9++) {
            Object[] var10000 = new Object[]{var5.getChangedCounter()};
            var10000 = new Object[]{var9};
            var5.resetCounters();

            for (IEStatement var8 : var4.instructions()) {
               var8.updateTypes(var5);
            }

            if (var5.getChangedCounter() == 0) {
               break;
            }

            aeb.q();
         }
      }

      if (var3) {
         for (IEStatement var12 : var4.instructions()) {
            var12.postUpdateTypes(var5);
         }

         if (var5.getConflictsCount() > 0) {
            RF.iH("Post-propagation typing conflicts:\n%s", var5);
         }
      }

      return var5;
   }

   public int Uv() {
      int var1 = 0;
      ETypeInfo var2 = new ETypeInfo(this.q);
      HashMap var3 = new HashMap();
      CFG var4 = this.q.getCfg();

      for (IEStatement var6 : var4.instructions()) {
         var6.visitDepthPost(new apu(this, var3));
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

   public int oW() {
      int var1 = 0;
      ETypeInfo var2 = new ETypeInfo(this.q);
      HashMap var3 = new HashMap();
      CFG var4 = this.q.getCfg();

      for (IEStatement var6 : var4.instructions()) {
         var6.visitDepthPost(new apv(this, var3));
      }

      IWildcardTypeManager var11 = this.q.getWildcardTypeManager();

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
