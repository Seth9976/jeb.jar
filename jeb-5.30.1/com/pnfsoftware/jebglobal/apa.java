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

public class apa {
   private static final StructuredLogger q = aeg.q(apa.class);
   private boolean RF = true;
   private boolean xK = true;
   private boolean Dw = true;
   private IERoutineContext Uv;
   private CFG oW;
   private boolean gO;
   private IDFA nf;

   public apa(IERoutineContext var1) {
      this.Uv = var1;
   }

   public void q(boolean var1) {
      this.RF = var1;
   }

   public void RF(boolean var1) {
      this.xK = var1;
   }

   public void xK(boolean var1) {
      this.Dw = var1;
   }

   public int q() {
      if (this.oW != null) {
         throw new IllegalStateException();
      } else {
         this.oW = this.Uv.getCfg();
         int var1 = 0;
         var1 += this.RF();
         if (this.xK) {
            var1 += this.Dw();
         }

         return var1;
      }
   }

   private int RF() {
      int var1 = 0;
      IVariableInformationProvider var2 = this.oW.setVariableInformationProvider(null);

      try {
         for (AddressableInstruction var4 : this.oW.addressableInstructions()) {
            IEStatement var5 = (IEStatement)var4.getInstruction();
            ArrayList var6 = new ArrayList();
            ArrayList var7 = new ArrayList();
            var5.getDefUse(var6, var7, null);

            for (int var9 : var6) {
               if (this.q(var9) && this.q(var4.getOffset(), var9)) {
                  var1++;
               }
            }
         }
      } finally {
         this.oW.setVariableInformationProvider(var2);
      }

      if (var1 > 0) {
         this.oW.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   private void xK() {
      if (!this.gO) {
         this.nf = this.oW.doDataFlowAnalysis();
         this.gO = true;
      }
   }

   private boolean q(long var1, int var3) {
      IEVar var4 = this.Uv.getVariableById(var3);
      if (!var4.isRegister()) {
         return false;
      } else {
         this.xK();
         ArrayList var5 = new ArrayList();
         ArrayDeque var6 = new ArrayDeque();
         HashSet var7 = new HashSet();
         apa.eo var8 = new apa.eo(var1, true);
         var6.add(var8);
         var7.add(var8);
         Collection var9 = null;

         while (!var6.isEmpty()) {
            var8 = (apa.eo)var6.pop();
            long var10 = var8.q;
            boolean var12 = var8.RF;
            IEStatement var13 = (IEStatement)this.oW.getInstruction(var10);
            boolean var14 = var12;
            boolean var15 = !var12;
            boolean var16 = false;
            if (!this.Dw && var13 instanceof IEAssign && ((IEAssign)var13).getLeftOperand() == var4) {
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
               if (var13 instanceof alj) {
                  IEGeneric var28 = ((alj)var13).getDstOperand();
                  if (!(var28 instanceof amy)) {
                     return false;
                  }

                  if (((amy)var28).getId() != var3) {
                     return false;
                  }

                  var27 = 1;
               } else if (var13 instanceof aln) {
                  ArrayList var29 = new ArrayList();
                  ArrayList var33 = new ArrayList();
                  var13.getDefUse(var29, var33, null);
                  if (!var29.contains(var3)) {
                     return false;
                  }

                  var27 = 2;
               } else if (var13 instanceof amv) {
                  ArrayList var30 = new ArrayList();
                  ArrayList var34 = new ArrayList();
                  var13.getDefUse(var30, var34, null);
                  if (!var30.contains(var3)) {
                     return false;
                  }

                  var27 = 3;
               }
            }

            var5.add(new apa.CU(var27, var10));
            if (var14) {
               Collection var31;
               if (var10 == -1L) {
                  var31 = this.nf.getInputMap(var3);
               } else {
                  var31 = this.nf.getDefUses(var10, var3);
               }

               if (var31 != null) {
                  for (long var20 : var31) {
                     apa.eo var22 = new apa.eo(var20, false);
                     if (!var7.contains(var22)) {
                        var6.add(var22);
                        var7.add(var22);
                     }
                  }
               }

               if (var9 == null) {
                  var9 = this.nf.getOutputMap(var3);
               }

               if (var9 != null && var9.contains(var10)) {
                  for (long var38 : var9) {
                     apa.eo var40 = new apa.eo(var38, true);
                     if (!var7.contains(var40)) {
                        var6.add(var40);
                        var7.add(var40);
                     }
                  }
               }
            }

            if (var15) {
               Collection var32 = this.nf.getUseDefs(var10, var3);
               if (var32 != null) {
                  for (long var39 : var32) {
                     apa.eo var41 = new apa.eo(var39, true);
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
         } else if (!this.RF && var5.size() == 1) {
            return false;
         } else {
            IEVar var24 = this.Uv.copyVariable(var4);

            for (apa.CU var25 : var5) {
               IEStatement var26 = (IEStatement)this.oW.getInstruction(var25.q);
               if (var26 != null) {
                  if (var25.RF == -1) {
                     var26.replaceVar(var4, var24);
                  } else if (var25.RF == 0) {
                     var26.replaceVar(var4, var24, true);
                  } else {
                     var26.replaceDefinedVar(var4, var24);
                  }
               }
            }

            this.gO = false;
            return true;
         }
      }
   }

   private int Dw() {
      int var1 = 0;
      HashMap var2 = new HashMap();
      HashSet var3 = new HashSet();

      for (BasicBlock var5 : this.oW.getBlocks()) {
         for (IEStatement var7 : var5) {
            var3.clear();
            var7.visitDepthPost(new apb(this, var3));

            for (Integer var9 : var3) {
               IEVar var10 = this.Uv.getVariableById(var9);
               IEVar var11 = (IEVar)var2.get(var9);
               if (var11 == null) {
                  var11 = this.Uv.copyVariable(var10);
                  var2.put(var9, var11);
               }

               int var12 = var7.replaceVar(var10, var11, false);
               Assert.a(var12 >= 1);
               var1 += var12;
            }
         }
      }

      if (var1 > 0) {
         this.oW.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   private boolean q(int var1) {
      if (var1 != this.Uv.getProgramCounterId() && var1 != this.Uv.getStackPointerId()) {
         IEVar var2 = this.Uv.getVariableById(var1);
         return (var2.getFlags() & 16) == 0;
      } else {
         return false;
      }
   }

   private static class CU {
      long q;
      int RF;

      CU(int var1, long var2) {
         if (var1 >= -1 && var1 <= 4) {
            this.RF = var1;
            this.q = var2;
         } else {
            throw new IllegalArgumentException();
         }
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         Strings.ff(var1, "@%Xh:type=%d", this.q, this.RF);
         return var1.toString();
      }
   }

   private static class eo {
      long q;
      boolean RF;

      public eo(long var1, boolean var3) {
         this.q = var1;
         this.RF = var3;
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + (int)(this.q ^ this.q >>> 32);
         return 31 * var1 + (this.RF ? 1231 : 1237);
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
            apa.eo var2 = (apa.eo)var1;
            return this.q != var2.q ? false : this.RF == var2.RF;
         }
      }

      @Override
      public String toString() {
         return Strings.ff("%Xh[def=%b]", this.q, this.RF);
      }
   }
}
