package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.IVariableInformationProvider;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class amt {
   private static final StructuredLogger pC = aco.pC(amt.class);
   private boolean A = true;
   private boolean kS = true;
   private boolean wS = true;
   private IERoutineContext UT;
   private CFG E;
   private boolean sY;
   private IDFA ys;

   public amt(IERoutineContext var1) {
      this.UT = var1;
   }

   public int pC() {
      if (this.E != null) {
         throw new IllegalStateException();
      } else {
         this.E = this.UT.getCfg();
         int var1 = 0;
         var1 += this.A();
         if (this.kS) {
            var1 += this.wS();
         }

         return var1;
      }
   }

   private int A() {
      int var1 = 0;
      IVariableInformationProvider var2 = this.E.setVariableInformationProvider(null);

      try {
         for (AddressableInstruction var4 : this.E.addressableInstructions()) {
            IEStatement var5 = (IEStatement)var4.getInstruction();
            ArrayList var6 = new ArrayList();
            ArrayList var7 = new ArrayList();
            var5.getDefUse(var6, var7, null);

            for (int var9 : var6) {
               if (this.pC(var9) && this.pC(var4.getOffset(), var9)) {
                  var1++;
               }
            }
         }
      } finally {
         this.E.setVariableInformationProvider(var2);
      }

      if (var1 > 0) {
         this.E.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   private void kS() {
      if (!this.sY) {
         this.ys = this.E.doDataFlowAnalysis();
         this.sY = true;
      }
   }

   private boolean pC(long var1, int var3) {
      IEVar var4 = this.UT.getVariableById(var3);
      if (!var4.isRegister()) {
         return false;
      } else {
         this.kS();
         ArrayList var5 = new ArrayList();
         ArrayDeque var6 = new ArrayDeque();
         HashSet var7 = new HashSet();
         amt.Av var8 = new amt.Av(var1, true);
         var6.add(var8);
         var7.add(var8);
         Collection var9 = null;

         while (!var6.isEmpty()) {
            var8 = (amt.Av)var6.pop();
            long var10 = var8.pC;
            boolean var12 = var8.A;
            IEStatement var13 = (IEStatement)this.E.getInstruction(var10);
            boolean var14 = var12;
            boolean var15 = !var12;
            boolean var16 = false;
            if (!this.wS && var13 instanceof IEAssign && ((IEAssign)var13).getLeftOperand() == var4) {
               IEGeneric var17 = ((IEAssign)var13).getRightOperand();
               if (var17 instanceof IECompose && ((IECompose)var17).getPartsCount() == 2) {
                  IECompose var18 = (IECompose)var17;
                  if (var18.getHighPart() instanceof IESlice) {
                     IESlice var19 = (IESlice)var18.getHighPart();
                     if (var19.getWholeExpression() == var4 && var19.getBitStart() > 0 && var19.getBitEnd() == var4.getBitsize()) {
                        var16 = true;
                     }
                  }
               }
            }

            byte var27 = 0;
            if (var16) {
               var27 = -1;
               var14 = true;
               var15 = true;
            } else if (var12) {
               if (var13 instanceof ajg) {
                  IEGeneric var28 = ((ajg)var13).getDstOperand();
                  if (!(var28 instanceof aku)) {
                     return false;
                  }

                  if (((aku)var28).getId() != var3) {
                     return false;
                  }

                  var27 = 1;
               } else if (var13 instanceof ajk) {
                  ArrayList var29 = new ArrayList();
                  ArrayList var33 = new ArrayList();
                  var13.getDefUse(var29, var33, null);
                  if (!var29.contains(var3)) {
                     return false;
                  }

                  var27 = 2;
               } else if (var13 instanceof aks) {
                  ArrayList var30 = new ArrayList();
                  ArrayList var34 = new ArrayList();
                  var13.getDefUse(var30, var34, null);
                  if (!var30.contains(var3)) {
                     return false;
                  }

                  var27 = 3;
               }
            }

            var5.add(new amt.Sv(var27, var10));
            if (var14) {
               Collection var31;
               if (var10 == -1L) {
                  var31 = this.ys.getInputMap(var3);
               } else {
                  var31 = this.ys.getDefUses(var10, var3);
               }

               if (var31 != null) {
                  for (long var20 : var31) {
                     amt.Av var22 = new amt.Av(var20, false);
                     if (!var7.contains(var22)) {
                        var6.add(var22);
                        var7.add(var22);
                     }
                  }
               }

               if (var9 == null) {
                  var9 = this.ys.getOutputMap(var3);
               }

               if (var9 != null && var9.contains(var10)) {
                  for (long var38 : var9) {
                     amt.Av var40 = new amt.Av(var38, true);
                     if (!var7.contains(var40)) {
                        var6.add(var40);
                        var7.add(var40);
                     }
                  }
               }
            }

            if (var15) {
               Collection var32 = this.ys.getUseDefs(var10, var3);
               if (var32 != null) {
                  for (long var39 : var32) {
                     amt.Av var41 = new amt.Av(var39, true);
                     if (!var7.contains(var41)) {
                        var6.add(var41);
                        var7.add(var41);
                     }
                  }
               }
            }
         }

         if (var5.isEmpty()) {
            return false;
         } else if (!this.A && var5.size() == 1) {
            return false;
         } else {
            IEVar var24 = this.UT.copyVariable(var4);

            for (amt.Sv var25 : var5) {
               IEStatement var26 = (IEStatement)this.E.getInstruction(var25.pC);
               if (var26 != null) {
                  if (var25.A == -1) {
                     var26.replaceVar(var4, var24);
                  } else if (var25.A == 0) {
                     var26.replaceVar(var4, var24, true);
                  } else {
                     var26.replaceDefinedVar(var4, var24);
                  }
               }
            }

            this.sY = false;
            return true;
         }
      }
   }

   private int wS() {
      int var1 = 0;
      HashMap var2 = new HashMap();
      HashSet var3 = new HashSet();

      for (BasicBlock var5 : this.E.getBlocks()) {
         for (IEStatement var7 : var5) {
            var3.clear();
            var7.visitDepthPost(new amu(this, var3));

            for (Integer var9 : var3) {
               IEVar var10 = this.UT.getVariableById(var9);
               IEVar var11 = (IEVar)var2.get(var9);
               if (var11 == null) {
                  var11 = this.UT.copyVariable(var10);
                  var2.put(var9, var11);
               }

               int var12 = var7.replaceVar(var10, var11, false);
               Assert.a(var12 >= 1);
               var1 += var12;
            }
         }
      }

      if (var1 > 0) {
         this.E.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   private boolean pC(int var1) {
      if (var1 != this.UT.getProgramCounterId() && var1 != this.UT.getStackPointerId()) {
         IEVar var2 = this.UT.getVariableById(var1);
         return (var2.getFlags() & 16) == 0;
      } else {
         return false;
      }
   }

   private static class Av {
      long pC;
      boolean A;

      public Av(long var1, boolean var3) {
         this.pC = var1;
         this.A = var3;
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + (int)(this.pC ^ this.pC >>> 32);
         return 31 * var1 + (this.A ? 1231 : 1237);
      }

      @Override
      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (var1 == null) {
            return false;
         } else if (this.getClass() != var1.getClass()) {
            return false;
         } else {
            amt.Av var2 = (amt.Av)var1;
            return this.pC != var2.pC ? false : this.A == var2.A;
         }
      }

      @Override
      public String toString() {
         return Strings.ff("%Xh[def=%b]", this.pC, this.A);
      }
   }

   private static class Sv {
      long pC;
      int A;

      Sv(int var1, long var2) {
         if (var1 >= -1 && var1 <= 4) {
            this.A = var1;
            this.pC = var2;
         } else {
            throw new IllegalArgumentException();
         }
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         Strings.ff(var1, "@%Xh:type=%d", this.pC, this.A);
         return var1.toString();
      }
   }
}
