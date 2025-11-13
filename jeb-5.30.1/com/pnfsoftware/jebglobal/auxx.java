package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import java.util.ArrayList;
import java.util.List;

public abstract class auxx extends AbstractEOptimizer {
   private static final boolean q = true;
   private static final boolean RF = true;

   public auxx() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
      this.setPriority(2.0);
   }

   @Override
   protected int perform() {
      if (this.ectx.usesCopyVars()) {
         return 0;
      } else {
         int var1 = 0;
         boolean var2 = true;

         label70:
         while (var2) {
            var2 = false;

            for (BasicBlock var4 : this.cfg) {
               for (int var5 = 0; var5 < var4.size(); var5++) {
                  IEStatement var6 = (IEStatement)var4.get(var5);
                  if (var6 instanceof IECall && (var5 != var4.size() - 1 || var4.outsize() == 1)) {
                     IECall var7 = (IECall)var6;
                     if (var7.getCallSite() instanceof IEVar) {
                        IEVar var8 = (IEVar)var7.getCallSite();

                        for (auxx.eo var10 : this.q()) {
                           if (var10.q.equals(var8.getName()) || var10.RF != null && var10.RF.equals(var8.getName())) {
                              int var11 = this.q(var10, var4, var5, var7, var8);
                              if (var11 < 0) {
                                 this.cfg = this.ectx.getCfg();
                                 var2 = true;
                                 continue label70;
                              }

                              var1 += var11;
                              break;
                           }
                        }
                     }
                  }
               }
            }
         }

         return this.postPerform(var1);
      }
   }

   private int q(auxx.eo var1, BasicBlock var2, int var3, IECall var4, IEVar var5) {
      Object var6 = var4;
      if (var1.q(this.ectx, var2)) {
         return -1;
      } else {
         Long var7 = var4.getPrimaryLowerLevelAddress();
         List var8 = var1.q(this.ectx, var4);
         boolean var9 = var8 != null && var8.size() == 1;
         if (!var9 && var8 != null && var8.size() > 1) {
            ArrayList var10 = new ArrayList();

            for (int var11 = 1; var11 < var8.size(); var11++) {
               IEStatement var12 = (IEStatement)var8.get(var11);
               var12.copyProperties(var4);
               var12.setSize(1);
               var12.setPrimaryLowerLevelAddress(var7);
               var10.add(var12);
            }

            try {
               if (amw.q(this.ectx, var2, var3 + 1, var10)) {
                  var9 = true;
                  var6 = (IEStatement)var2.get(var3);
               }
            } catch (JebException var13) {
               return -1;
            }
         }

         if (var9) {
            IEStatement var14 = (IEStatement)var8.get(0);
            var14.copyProperties((IEStatement)var6);
            var2.set(var3, var14);
            Object[] var10000 = new Object[]{var5.getName(), var6, var14};
            return 1;
         } else {
            return 0;
         }
      }
   }

   public abstract List q();

   public static class CU implements auxx.ej {
      final auxx.nI[] q;
      final OperationType RF;
      final auxx.nI xK;

      public CU(auxx.nI[] var1, OperationType var2, auxx.nI var3) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
      }

      private int xK() {
         return this.q.length;
      }

      @Override
      public IEGeneric q(IERoutineContext var1, IECall var2, boolean var3) {
         if (this.q != null && this.q.length != 0) {
            if (this.q[0].RF) {
               if (this.xK() == 1) {
                  IEGeneric var6 = this.q(0, var2, var1, this.q[0], true, !var3);
                  if (var6 == null) {
                     return null;
                  }

                  return this.q(var1, var6.duplicate());
               }

               if (this.xK() == 2) {
                  IEGeneric var4 = this.q(0, var2, var1, this.q[0], true, !var3);
                  IEGeneric var5 = this.q(2, var2, var1, this.q[1], true, !var3);
                  if (var4 != null && var5 != null) {
                     return this.q(var1, var4.duplicate(), var5.duplicate());
                  }

                  return null;
               }
            } else {
               if (this.xK() == 1) {
                  IEGeneric var8 = this.q(0, var2, var1, this.q[0]);
                  return this.q(var1, var8.duplicate());
               }

               if (this.xK() == 2) {
                  IEGeneric var7 = this.q(0, var2, var1, this.q[0]);
                  IEGeneric var9 = this.q(1, var2, var1, this.q[1]);
                  return this.q(var1, var7.duplicate(), var9.duplicate());
               }
            }

            return null;
         } else {
            return null;
         }
      }

      private IEGeneric q(IERoutineContext var1, IEGeneric var2) {
         return this.RF.isConversion() ? var1.createConversionOperation(this.RF, var2, this.xK.RF ? 64 : 32) : var1.createOperation(this.RF, var2);
      }

      private IEGeneric q(IERoutineContext var1, IEGeneric var2, IEGeneric var3) {
         IEOperation var4 = var1.createOperation(this.RF, var2, var3);
         return (IEGeneric)(this.RF.isFloatComparison() ? var4.zeroExtend(this.xK.RF ? 64 : 32) : var4);
      }

      private IEGeneric q(int var1, IECall var2, IERoutineContext var3, auxx.nI var4, boolean var5, boolean var6) {
         IEGeneric var7 = this.q(var1, var2, var3, var4, var5);
         IEGeneric var8 = this.q(var1 + 1, var2, var3, var4.q(), var5);
         if (var3.getGlobalContext().isBigEndian()) {
            IEGeneric var9 = var7;
            var7 = var8;
            var8 = var9;
         }

         if (var6 && var7 instanceof IEVar && var8 instanceof IEVar) {
            return this.q(var3, (IEVar)var7, (IEVar)var8);
         } else {
            if (var7 instanceof IEMem && var8 instanceof IEMem) {
               IEMem var10 = amw.q(var3, (IEMem)var7, (IEMem)var8);
               if (var10 != null) {
                  return var10;
               }
            }

            return var3.createCompose(var7, var8);
         }
      }

      private IEGeneric q(IERoutineContext var1, IEVar var2, IEVar var3) {
         IEVar var4 = var1.getVariableByName("$" + var2.getName() + "_" + var3.getName());
         return var4 != null ? var4 : var1.copyPairOfVariables(var2, var3);
      }

      private IEGeneric q(int var1, IECall var2, IERoutineContext var3, auxx.nI var4) {
         return this.q(var1, var2, var3, var4, true);
      }

      private IEGeneric q(int var1, IECall var2, IERoutineContext var3, auxx.nI var4, boolean var5) {
         if (var5 && var1 < var2.getCountOfArguments()) {
            return var2.getArgument(var1);
         } else {
            IEConverter var6 = var3.getConverter();
            return var4.q(var6);
         }
      }

      @Override
      public IEGeneric q(IERoutineContext var1) {
         return this.q(var1, -1, false);
      }

      @Override
      public IEGeneric q(IERoutineContext var1, int var2, boolean var3) {
         IEConverter var4 = var1.getConverter();
         if (this.xK.RF) {
            if (var2 == -1) {
               return this.q(-1, null, var1, this.xK, false, !var3);
            } else {
               auxx.nI var5 = this.xK;
               if (var1.getGlobalContext().isBigEndian() && var2 == 0 || !var1.getGlobalContext().isBigEndian() && var2 != 0) {
                  var5 = this.xK.q();
               }

               return this.q(-1, null, var1, var5, false);
            }
         } else {
            return this.xK.q(var4);
         }
      }

      @Deprecated
      @Override
      public boolean q() {
         return this.q != null && this.q.length != 0 ? this.q[0].RF : false;
      }

      @Override
      public boolean RF() {
         return this.xK.RF;
      }
   }

   public interface ej {
      IEGeneric q(IERoutineContext var1, IECall var2, boolean var3);

      IEGeneric q(IERoutineContext var1);

      IEGeneric q(IERoutineContext var1, int var2, boolean var3);

      boolean q();

      boolean RF();
   }

   public static class eo {
      private final String q;
      private final String RF;
      private final auxx.ej xK;
      private final auxx.ej Dw;

      public eo(String var1, auxx.ej var2) {
         this(var1, null, var2, null);
      }

      public eo(String var1, auxx.ej var2, auxx.ej var3) {
         this(var1, null, var2, var3);
      }

      public eo(String var1, String var2, auxx.ej var3) {
         this(var1, var2, var3, null);
      }

      public eo(String var1, String var2, auxx.ej var3, auxx.ej var4) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
         this.Dw = var4;
      }

      private int q() {
         return this.Dw == null ? 1 : 2;
      }

      private auxx.ej q(int var1) {
         return var1 == 0 ? this.xK : this.Dw;
      }

      private boolean q(auxx.ej var1, IEGeneric var2) {
         return var1.RF() && var2 instanceof IEVar;
      }

      boolean q(IERoutineContext var1, BasicBlock var2) {
         int var3 = -1;
         if (this.Dw == null) {
            IEGeneric var4 = this.xK.q(var1);
            var3 += this.q(this.xK, var4) ? 3 : 1;
         } else {
            for (int var7 = 0; var7 < this.q(); var7++) {
               auxx.ej var5 = this.q(var7);
               IEGeneric var6 = var5.q(var1);
               var3 += this.q(var5, var6) ? 3 : 2;
            }
         }

         if (!amw.q(var2, var3)) {
            amw.q(var1, var2, 0, var3);
            return true;
         } else {
            return false;
         }
      }

      private List q(IERoutineContext var1, IECall var2) {
         ArrayList var3 = new ArrayList();
         if (this.Dw == null) {
            IEGeneric var10 = this.xK.q(var1, var2, true);
            if (var10 == null) {
               return null;
            } else {
               IEGeneric var12 = this.xK.q(var1);
               if (this.q(this.xK, var12)) {
                  IEVar var14 = var1.createVirtualVar("rAbi", var10.getBitsize());
                  var3.add(var1.createAssign(var14, var10));
                  IEGeneric var15 = this.xK.q(var1, 0, true);
                  IEGeneric var16 = this.xK.q(var1, 1, true);
                  var3.add(var1.createAssign(var15, var14.part(var12.getBitsize() / 2)));
                  var3.add(var1.createAssign(var16, var14.slice(var12.getBitsize() / 2, var12.getBitsize())));
               } else {
                  var3.add(var1.createAssign(var12, var10));
               }

               return var3;
            }
         } else {
            IEVar[] var4 = new IEVar[this.q()];

            for (int var5 = 0; var5 < this.q(); var5++) {
               IEGeneric var6 = this.q(var5).q(var1, var2, true);
               if (var6 == null) {
                  return null;
               }

               var4[var5] = var1.createVirtualVar("r" + var5 + "Abi", var6.getBitsize());
               var3.add(var1.createAssign(var4[var5], var6));
            }

            for (int var11 = 0; var11 < this.q(); var11++) {
               auxx.ej var13 = this.q(var11);
               IEGeneric var7 = var13.q(var1);
               if (this.q(var13, var7)) {
                  IEGeneric var8 = var13.q(var1, 0, true);
                  IEGeneric var9 = var13.q(var1, 1, true);
                  var3.add(var1.createAssign(var8, var4[var11].part(var7.getBitsize() / 2)));
                  var3.add(var1.createAssign(var9, var4[var11].slice(var7.getBitsize() / 2, var7.getBitsize())));
               } else {
                  var3.add(var1.createAssign(var7, var4[var11]));
               }
            }

            return var3;
         }
      }
   }

   public static class nI {
      final int q;
      final boolean RF;

      public nI(int var1, boolean var2) {
         this.q = var1;
         this.RF = var2;
      }

      public IEGeneric q(IEConverter var1) {
         return var1.getRegisterVariableFromNativeRegisterId(this.q);
      }

      public auxx.nI q() {
         return new auxx.nI(this.q + 1, this.RF);
      }
   }
}
