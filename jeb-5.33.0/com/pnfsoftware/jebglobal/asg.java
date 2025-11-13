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

public abstract class asg extends AbstractEOptimizer {
   public asg() {
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

                        for (asg.Av var10 : this.pC()) {
                           if (var10.pC.equals(var8.getName()) || var10.A != null && var10.A.equals(var8.getName())) {
                              int var11 = this.pC(var10, var4, var5, var7, var8);
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

   private int pC(asg.Av var1, BasicBlock var2, int var3, IECall var4, IEVar var5) {
      Object var6 = var4;
      if (var1.pC(this.ectx, var2)) {
         return -1;
      } else {
         Long var7 = var4.getPrimaryLowerLevelAddress();
         List var8 = var1.pC(this.ectx, var4);
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
               if (akt.pC(this.ectx, var2, var3 + 1, var10)) {
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

   public abstract List pC();

   public static class Av {
      private final String pC;
      private final String A;
      private final asg.Ws kS;
      private final asg.Ws wS;

      public Av(String var1, asg.Ws var2) {
         this(var1, null, var2, null);
      }

      public Av(String var1, asg.Ws var2, asg.Ws var3) {
         this(var1, null, var2, var3);
      }

      public Av(String var1, String var2, asg.Ws var3) {
         this(var1, var2, var3, null);
      }

      public Av(String var1, String var2, asg.Ws var3, asg.Ws var4) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
         this.wS = var4;
      }

      private int pC() {
         return this.wS == null ? 1 : 2;
      }

      private asg.Ws pC(int var1) {
         return var1 == 0 ? this.kS : this.wS;
      }

      private boolean pC(asg.Ws var1, IEGeneric var2) {
         return var1.pC() && var2 instanceof IEVar;
      }

      boolean pC(IERoutineContext var1, BasicBlock var2) {
         int var3 = -1;
         if (this.wS == null) {
            IEGeneric var4 = this.kS.pC(var1);
            var3 += this.pC(this.kS, var4) ? 3 : 1;
         } else {
            for (int var7 = 0; var7 < this.pC(); var7++) {
               asg.Ws var5 = this.pC(var7);
               IEGeneric var6 = var5.pC(var1);
               var3 += this.pC(var5, var6) ? 3 : 2;
            }
         }

         if (!akt.pC(var2, var3)) {
            akt.pC(var1, var2, 0, var3);
            return true;
         } else {
            return false;
         }
      }

      private List pC(IERoutineContext var1, IECall var2) {
         ArrayList var3 = new ArrayList();
         if (this.wS == null) {
            IEGeneric var10 = this.kS.pC(var1, var2, true);
            if (var10 == null) {
               return null;
            } else {
               IEGeneric var12 = this.kS.pC(var1);
               if (this.pC(this.kS, var12)) {
                  IEVar var14 = var1.createVirtualVar("rAbi", var10.getBitsize());
                  var3.add(var1.createAssign(var14, var10));
                  IEGeneric var15 = this.kS.pC(var1, 0, true);
                  IEGeneric var16 = this.kS.pC(var1, 1, true);
                  var3.add(var1.createAssign(var15, var14.part(var12.getBitsize() / 2)));
                  var3.add(var1.createAssign(var16, var14.slice(var12.getBitsize() / 2, var12.getBitsize())));
               } else {
                  var3.add(var1.createAssign(var12, var10));
               }

               return var3;
            }
         } else {
            IEVar[] var4 = new IEVar[this.pC()];

            for (int var5 = 0; var5 < this.pC(); var5++) {
               IEGeneric var6 = this.pC(var5).pC(var1, var2, true);
               if (var6 == null) {
                  return null;
               }

               var4[var5] = var1.createVirtualVar("r" + var5 + "Abi", var6.getBitsize());
               var3.add(var1.createAssign(var4[var5], var6));
            }

            for (int var11 = 0; var11 < this.pC(); var11++) {
               asg.Ws var13 = this.pC(var11);
               IEGeneric var7 = var13.pC(var1);
               if (this.pC(var13, var7)) {
                  IEGeneric var8 = var13.pC(var1, 0, true);
                  IEGeneric var9 = var13.pC(var1, 1, true);
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

   public static class K {
      final int pC;
      final boolean A;

      public K(int var1, boolean var2) {
         this.pC = var1;
         this.A = var2;
      }

      public IEGeneric pC(IEConverter var1) {
         return var1.getRegisterVariableFromNativeRegisterId(this.pC);
      }

      public asg.K pC() {
         return new asg.K(this.pC + 1, this.A);
      }
   }

   public static class Sv implements asg.Ws {
      final asg.K[] pC;
      final OperationType A;
      final asg.K kS;

      public Sv(asg.K[] var1, OperationType var2, asg.K var3) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
      }

      private int A() {
         return this.pC.length;
      }

      @Override
      public IEGeneric pC(IERoutineContext var1, IECall var2, boolean var3) {
         if (this.pC != null && this.pC.length != 0) {
            if (this.pC[0].A) {
               if (this.A() == 1) {
                  IEGeneric var6 = this.pC(0, var2, var1, this.pC[0], true, !var3);
                  if (var6 == null) {
                     return null;
                  }

                  return this.pC(var1, var6.duplicate());
               }

               if (this.A() == 2) {
                  IEGeneric var4 = this.pC(0, var2, var1, this.pC[0], true, !var3);
                  IEGeneric var5 = this.pC(2, var2, var1, this.pC[1], true, !var3);
                  if (var4 != null && var5 != null) {
                     return this.pC(var1, var4.duplicate(), var5.duplicate());
                  }

                  return null;
               }
            } else {
               if (this.A() == 1) {
                  IEGeneric var8 = this.pC(0, var2, var1, this.pC[0]);
                  return this.pC(var1, var8.duplicate());
               }

               if (this.A() == 2) {
                  IEGeneric var7 = this.pC(0, var2, var1, this.pC[0]);
                  IEGeneric var9 = this.pC(1, var2, var1, this.pC[1]);
                  return this.pC(var1, var7.duplicate(), var9.duplicate());
               }
            }

            return null;
         } else {
            return null;
         }
      }

      private IEGeneric pC(IERoutineContext var1, IEGeneric var2) {
         return this.A.isConversion() ? var1.createConversionOperation(this.A, var2, this.kS.A ? 64 : 32) : var1.createOperation(this.A, var2);
      }

      private IEGeneric pC(IERoutineContext var1, IEGeneric var2, IEGeneric var3) {
         IEOperation var4 = var1.createOperation(this.A, var2, var3);
         return (IEGeneric)(this.A.isFloatComparison() ? var4.zeroExtend(this.kS.A ? 64 : 32) : var4);
      }

      private IEGeneric pC(int var1, IECall var2, IERoutineContext var3, asg.K var4, boolean var5, boolean var6) {
         IEGeneric var7 = this.pC(var1, var2, var3, var4, var5);
         IEGeneric var8 = this.pC(var1 + 1, var2, var3, var4.pC(), var5);
         if (var3.getGlobalContext().isBigEndian()) {
            IEGeneric var9 = var7;
            var7 = var8;
            var8 = var9;
         }

         if (var6 && var7 instanceof IEVar && var8 instanceof IEVar) {
            return this.pC(var3, (IEVar)var7, (IEVar)var8);
         } else {
            if (var7 instanceof IEMem && var8 instanceof IEMem) {
               IEMem var10 = akt.pC(var3, (IEMem)var7, (IEMem)var8);
               if (var10 != null) {
                  return var10;
               }
            }

            return var3.createCompose(var7, var8);
         }
      }

      private IEGeneric pC(IERoutineContext var1, IEVar var2, IEVar var3) {
         IEVar var4 = var1.getVariableByName("$" + var2.getName() + "_" + var3.getName());
         return var4 != null ? var4 : var1.copyPairOfVariables(var2, var3);
      }

      private IEGeneric pC(int var1, IECall var2, IERoutineContext var3, asg.K var4) {
         return this.pC(var1, var2, var3, var4, true);
      }

      private IEGeneric pC(int var1, IECall var2, IERoutineContext var3, asg.K var4, boolean var5) {
         if (var5 && var1 < var2.getCountOfArguments()) {
            return var2.getArgument(var1);
         } else {
            IEConverter var6 = var3.getConverter();
            return var4.pC(var6);
         }
      }

      @Override
      public IEGeneric pC(IERoutineContext var1) {
         return this.pC(var1, -1, false);
      }

      @Override
      public IEGeneric pC(IERoutineContext var1, int var2, boolean var3) {
         IEConverter var4 = var1.getConverter();
         if (this.kS.A) {
            if (var2 == -1) {
               return this.pC(-1, null, var1, this.kS, false, !var3);
            } else {
               asg.K var5 = this.kS;
               if (var1.getGlobalContext().isBigEndian() && var2 == 0 || !var1.getGlobalContext().isBigEndian() && var2 != 0) {
                  var5 = this.kS.pC();
               }

               return this.pC(-1, null, var1, var5, false);
            }
         } else {
            return this.kS.pC(var4);
         }
      }

      @Override
      public boolean pC() {
         return this.kS.A;
      }
   }

   public interface Ws {
      IEGeneric pC(IERoutineContext var1, IECall var2, boolean var3);

      IEGeneric pC(IERoutineContext var1);

      IEGeneric pC(IERoutineContext var1, int var2, boolean var3);

      boolean pC();
   }
}
