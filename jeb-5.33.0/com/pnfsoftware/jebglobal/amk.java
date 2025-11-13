package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.util.collect.IdentityHashSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.List;

public class amk extends alf {
   private IdentityHashSet kS = new IdentityHashSet();
   private IdentityHashSet wS = new IdentityHashSet();
   private IdentityHashMap UT = new IdentityHashMap();
   private IdentityHashMap E = new IdentityHashMap();

   public amk(IERoutineContext var1) {
      super(var1);
   }

   @Override
   protected int A() {
      int var1 = 0;

      for (BasicBlock var4 : this.A.getCfg()) {
         this.pC(var4);
      }

      for (IEMem var8 : this.wS) {
         amk.Av var5 = (amk.Av)this.UT.get(var8.getReference());
         if (var5 != null) {
            Integer var6 = (Integer)this.E.get(var5.pC);
            if (var6 != null && (var6 & 1) == 1) {
               Object[] var10000 = new Object[]{var8};
               var8.addFlags(256);
               var1++;
            }
         }
      }

      if (var1 > 0) {
         this.A.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   void pC(BasicBlock var1) {
      long var2 = var1.getBase();

      for (IEStatement var5 : var1) {
         this.pC(var2, var5);
         var2 += var5.getSize();
      }
   }

   void pC(long var1, IEStatement var3) {
      var3.visitDepthPost(new aml(this, var1));
   }

   private void pC(long var1, IEMem var3) {
      this.wS.add(var3);
      IEGeneric var4 = this.A(var1, var3.getReference());
      if (var4 != null) {
         this.kS.clear();
         this.pC(var1, var4);
      }
   }

   private int pC(long var1, IEGeneric var3) {
      if (!this.kS.add(var3)) {
         return -1;
      } else if (!(var3 instanceof IEVar)) {
         if (var3 instanceof IEMem) {
            amk.Av var14 = (amk.Av)this.UT.get(var3.asMem().getReference());
            if (var14 == null) {
               return -1;
            } else {
               Integer var16 = (Integer)this.E.get(var14.pC);
               if (var16 != null && var16 != 0) {
                  this.pC(var3);
                  return 1;
               } else {
                  return -1;
               }
            }
         } else {
            IEGeneric var13 = this.A(var1, var3);
            if (var13 == null) {
               return -1;
            } else {
               int var15 = this.pC(var1, var13);
               if (var15 < 0) {
                  return -1;
               } else {
                  this.pC(var3);
                  return 1;
               }
            }
         }
      } else {
         IEVar var4 = var3.asVar();
         IDFA var5 = this.A.getCfg().doDataFlowAnalysis();
         Collection var6 = var5.getUseDefs(var1, var4.getId());
         if (var6 == null || var6.isEmpty()) {
            return -1;
         } else if (!var6.contains(-1L)) {
            long var7 = -1L;

            for (long var10 : var6) {
               IEStatement var12 = (IEStatement)this.A.getCfg().getInstruction(var10);
               if (var12.isAssignTo(var4)) {
                  var7 = var10;
                  break;
               }
            }

            if (var7 < 0L) {
               return -1;
            } else {
               IEStatement var17 = (IEStatement)this.A.getCfg().getInstruction(var7);
               IEGeneric var18 = var17.asAssign().getSrcOperand();
               IEGeneric var11 = this.A(var7, var18);
               if (var11 == null) {
                  return -1;
               } else {
                  int var19 = this.pC(var7, var18);
                  if (var19 < 0) {
                     return -1;
                  } else {
                     this.pC(var3);
                     return 1;
                  }
               }
            }
         } else if (var4.isStackReference()) {
            return -1;
         } else {
            var4.isGlobalReference();
            this.pC(var3);
            return 1;
         }
      }
   }

   private void pC(IEGeneric var1) {
      int var2 = (Integer)this.E.getOrDefault(var1, 0);
      var2 |= 1;
      this.E.put(var1, var2);
   }

   private IEGeneric A(long var1, IEGeneric var3) {
      amk.Av var4 = (amk.Av)this.UT.get(var3);
      if (var4 != null) {
         return var4.pC;
      } else {
         ArrayList var5 = new ArrayList();
         IEGeneric var6 = this.pC(var3, var5);
         if (var6 != null) {
            var4 = new amk.Av(var6);
            this.UT.put(var3, var4);
         }

         return var6;
      }
   }

   private IEGeneric pC(IEGeneric var1, List var2) {
      if (var2.size() >= 5) {
         return null;
      } else {
         var2.add(var1);
         if (var1 instanceof IEOperation) {
            IEOperation var3 = var1.asOperation();
            if (var3.isOperation(OperationType.ADD, OperationType.SUB)) {
               IEGeneric var4 = this.pC(var3.getOperand1(), var2);
               if (var4 != null) {
                  return var4;
               }

               if (var3.getOperationType() == OperationType.ADD) {
                  var4 = this.pC(var3.getOperand2(), var2);
                  if (var4 != null) {
                     return var4;
                  }
               }
            }
         }

         if (var1.getType() != null && var1.getType().isPointer()) {
            return var1;
         } else {
            var2.remove(var2.size() - 1);
            return null;
         }
      }
   }

   private static class Av {
      IEGeneric pC;

      Av(IEGeneric var1) {
         if (var1 == null) {
            throw new IllegalArgumentException();
         } else {
            this.pC = var1;
         }
      }
   }
}
