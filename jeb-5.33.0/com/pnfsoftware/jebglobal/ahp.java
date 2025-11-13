package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CIdentifierClass;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICAssignment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConditionalStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDecl;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifier;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLeftExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICSwitchStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ahp extends AbstractCBlockOptimizer {
   private static final StructuredLogger pC = aco.pC(ahp.class);
   private boolean A;

   public ahp() {
      this(false);
   }

   public ahp(boolean var1) {
      this.A = var1;
   }

   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         ICStatement var4 = var1.get(var3);
         ICExpression var5 = this.pC(var4);
         if (var5 != null) {
            AtomicBoolean var6 = new AtomicBoolean(false);
            AtomicInteger var7 = new AtomicInteger(0);
            boolean var8 = this.pC(var5, var1, var3 + 1, null, var6, var7) && this.pC(var5);
            if (var8 && !var6.get() && var1 != this.m.getBody()) {
               var8 = this.pC(var5, this.m.getBody(), 0, var1, var6, var7);
               var6.set(true);
            }

            if (var8 && (var6.get() || var1 == this.m.getBody()) && this.pC(var1, var3)) {
               var3--;
               var2++;
            }

            var2 += var7.get();
         }
      }

      return var2;
   }

   private boolean pC(ICExpression var1, ICBlock var2, int var3, ICBlock var4, AtomicBoolean var5, AtomicInteger var6) {
      ICExpression var7 = null;
      if (var4 == null && var2.get(var3 - 1) instanceof ICAssignment && var1 instanceof ICIdentifier && ((ICAssignment)var2.get(var3 - 1)).isSimpleAssignment()
         )
       {
         var7 = ((ICAssignment)var2.get(var3 - 1)).getRight();
      }

      for (int var8 = var3; var8 < var2.size(); var8++) {
         ICStatement var9 = var2.get(var8);
         ArrayList var10 = new ArrayList();
         ArrayList var11 = new ArrayList();
         if (!this.pC(var9, var10, var11, var5, var4)) {
            return false;
         }

         if (var7 != null) {
            if (var9 instanceof ICAssignment var12
               && var12.isSimpleAssignment()
               && (var1.equals(var12.getLeft()) && var7.equals(var12.getRight()) || var1.equals(var12.getRight()) && var7.equals(var12.getLeft()))) {
               Object[] var10000 = new Object[]{var12};
               var2.remove(var8);
               var8--;
               var6.incrementAndGet();
               continue;
            }

            if (!CUtil.hasNoCall(var9)) {
               var7 = null;
            }
         }

         for (ICLeftExpression var13 : var10) {
            if (!Boolean.FALSE.equals(ahm.pC(var13, var1, true))) {
               return false;
            }
         }

         if (var7 != null && var9 instanceof ICAssignment && var1.equals(((ICAssignment)var9).getLeft())) {
            this.pC(var2, var3 - 1);
            var6.incrementAndGet();
            return false;
         }

         if (var7 != null && var7 instanceof ICIdentifier) {
            for (ICLeftExpression var16 : var11) {
               if (!Boolean.FALSE.equals(ahm.pC(var16, var7, true))) {
                  var7 = null;
               }
            }
         }

         if (var5.get()) {
            break;
         }
      }

      return true;
   }

   private boolean pC(ICStatement var1, List var2, List var3, AtomicBoolean var4, ICBlock var5) {
      if (var1 == var5) {
         return true;
      } else {
         ArrayList var6 = new ArrayList();
         if (var1 instanceof ICAssignment) {
            if (!ahm.pC(var1, var2, var6)) {
               return false;
            } else {
               ICLeftExpression var14 = ahm.pC(var1);
               if (var14 == null) {
                  return false;
               } else {
                  var3.add(var14);
                  return true;
               }
            }
         } else if (var1 instanceof ICReturn) {
            var4.set(true);
            return ahm.pC(var1, var2, var6);
         } else if (var1 instanceof ICBlock) {
            for (ICStatement var15 : (ICBlock)var1) {
               boolean var16 = this.pC(var15, var2, var3, var4, var5);
               if (!var16) {
                  return var16;
               }
            }

            return true;
         } else if (!(var1 instanceof ICConditionalStatement var7)) {
            return false;
         } else {
            boolean var8 = var7 instanceof ICIfStm;
            boolean var9 = var7.hasDefaultBlock();
            List var10 = var7.getBlocks();

            for (int var11 = 0; var11 < var10.size(); var11++) {
               if (var8) {
                  if ((var11 < var10.size() - 1 || !var7.hasDefaultBlock()) && !ahm.pC(((ICIfStm)var7).getBranchPredicate(var11), var2, var6)) {
                     return false;
                  }
               } else if (var11 == 0) {
                  if (!(var7 instanceof ICSwitchStm)) {
                     return false;
                  }

                  if (!ahm.pC(((ICSwitchStm)var7).getSwitchedExpression(), var2, var6)) {
                     return false;
                  }
               }

               AtomicBoolean var12 = new AtomicBoolean(false);
               if (!this.pC((ICStatement)var10.get(var11), var2, var3, var12, var5)) {
                  return false;
               }

               if (!var12.get()) {
                  var9 = false;
               }
            }

            if (var9) {
               var4.set(true);
            }

            return true;
         }
      }
   }

   private boolean pC(ICExpression var1) {
      if (var1 instanceof ICIdentifier var2) {
         if (var2.getIdentifierClass() == CIdentifierClass.SYNTHETIC) {
            return true;
         }

         if (this.A && var2.getIdentifierClass() == CIdentifierClass.LOCAL) {
            return true;
         }
      }

      return false;
   }

   private ICExpression pC(ICElement var1) {
      if (var1 instanceof ICAssignment) {
         ICLeftExpression var2 = ((ICAssignment)var1).getLeft();
         return (ICExpression)(var2 instanceof ICDecl ? ((ICDecl)((ICAssignment)var1).getLeft()).getIdentifier() : var2);
      } else {
         return null;
      }
   }

   private boolean pC(ICBlock var1, int var2) {
      ICStatement var3 = var1.get(var2);
      if (!(var3 instanceof ICAssignment var4)) {
         return false;
      } else {
         if (!var4.isUnaryOperatorAssignment() && !CUtil.hasNoSideEffects(var4.getRight())) {
            if (!(var4.getRight() instanceof ICStatement)) {
               return false;
            }

            Object[] var5 = new Object[]{var3};
            var1.set(var2, (ICStatement)var4.getRight());
         } else {
            Object[] var10000 = new Object[]{var3};
            var1.remove(var2);
         }

         return true;
      }
   }
}
