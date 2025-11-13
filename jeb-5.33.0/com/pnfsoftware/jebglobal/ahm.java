package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICArrayElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICAssignment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConditionalStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstant;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDecl;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifier;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICInstanceField;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLeftExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICSwitchStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ahm extends AbstractCBlockOptimizer {
   private static final StructuredLogger pC = aco.pC(ahm.class);

   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;
      int var3 = 0;

      while (var3 < var1.size()) {
         ICStatement var4 = var1.get(var3);
         ICLeftExpression var5 = pC(var4);
         if (var5 != null && var5 instanceof ICIdentifier && var4 instanceof ICAssignment && ((ICAssignment)var4).isSimpleAssignment()) {
            ICExpression var6 = ((ICAssignment)var4).getRight();
            if (var5 == var6) {
               Object[] var10000 = new Object[]{var4};
               var1.remove(var3);
               var2++;
            } else if (!(var6 instanceof ICIdentifier) && !(var6 instanceof ICConstant)) {
               var3++;
            } else {
               ArrayList var7 = new ArrayList();
               ArrayList var8 = new ArrayList();
               if (!pC(var6, var7, var8)) {
                  var3++;
               } else {
                  AtomicBoolean var9 = new AtomicBoolean(false);
                  boolean var10 = this.pC(var1, var3 + 1, var5, var6, var7, var9, new AtomicBoolean());
                  if (var10) {
                     var2++;
                  }

                  var3++;
               }
            }
         } else {
            var3++;
         }
      }

      return var2;
   }

   private boolean pC(ICBlock var1, int var2, ICExpression var3, ICExpression var4, List var5, AtomicBoolean var6, AtomicBoolean var7) {
      boolean var8 = false;

      for (int var9 = var2; var9 < var1.size(); var9++) {
         ICStatement var10 = var1.get(var9);
         if (!(var10 instanceof ICAssignment) && !(var10 instanceof ICReturn)) {
            if (var10 instanceof ICBlock) {
               AtomicBoolean var19 = new AtomicBoolean(false);
               var8 |= this.pC((ICBlock)var10, 0, var3, var4, var5, new AtomicBoolean(), var19);
               if (!var19.get()) {
                  break;
               }
               continue;
            }

            if (!(var10 instanceof ICConditionalStatement var11)) {
               break;
            }

            boolean var12 = var11 instanceof ICIfStm;
            AtomicBoolean var13 = new AtomicBoolean(false);
            boolean var14 = true;
            List var15 = var11.getBlocks();

            for (int var16 = 0; var16 < var15.size(); var16++) {
               if (var12) {
                  if (var16 < var15.size() - 1 || !var11.hasDefaultBlock()) {
                     var8 |= this.pC(((ICIfStm)var11).getBranchPredicate(var16), var3, var4, var5, var13);
                     if (var13.get()) {
                        break;
                     }
                  }
               } else if (var16 == 0) {
                  if (!(var11 instanceof ICSwitchStm)) {
                     var13.set(true);
                     break;
                  }

                  ICExpression var17 = ((ICSwitchStm)var11).getSwitchedExpression();
                  if (var17 == var3) {
                     Object[] var10000 = new Object[]{var3, var4, var11};
                     ((ICSwitchStm)var11).setSwitchedExpression(var4.duplicate());
                     var8 = true;
                  } else {
                     var8 |= this.pC(var17, var3, var4, var5, var13);
                     if (var13.get()) {
                        break;
                     }
                  }
               }

               AtomicBoolean var20 = new AtomicBoolean(false);
               var8 |= this.pC((ICBlock)var15.get(var16), 0, var3, var4, var5, new AtomicBoolean(), var20);
               if (!var20.get()) {
                  var14 = false;
               }
            }

            if (var13.get() || !var14) {
               break;
            }
         }

         AtomicBoolean var18 = new AtomicBoolean(false);
         var8 |= this.pC(var10, var3, var4, var5, var18);
         if (var18.get()) {
            break;
         }

         if (var9 == var1.size() - 1) {
            var7.set(true);
         }

         if (var10 instanceof ICReturn) {
            var6.set(true);
            break;
         }
      }

      return var8;
   }

   private boolean pC(ICElement var1, ICExpression var2, ICExpression var3, List var4, AtomicBoolean var5) {
      boolean var6 = false;
      ICLeftExpression var7 = pC(var1);
      if (var7 != null) {
         if (var7.equals(var2)) {
            var5.set(true);
            return false;
         }

         if (!Boolean.FALSE.equals(pC(var2, var7, false))) {
            var5.set(true);
            return false;
         }

         if (var4.contains(var7)) {
            var5.set(true);
            return false;
         }
      }

      ArrayList var8 = new ArrayList();
      ArrayList var9 = new ArrayList();
      if (!pC(var1, var8, var9)) {
         var5.set(true);
         return false;
      } else {
         if (var8.contains(var2) && !var9.contains(var2)) {
            Object[] var10000 = new Object[]{var2, var3, var1};
            if (CUtil.replaceSubElementRecurse(var1, var2, var3) == 0) {
               var5.set(true);
               return false;
            }

            var6 = true;
         }

         return var6;
      }
   }

   static Boolean pC(ICExpression var0, ICExpression var1, boolean var2) {
      if (var0 instanceof ICInstanceField) {
         return pC(((ICInstanceField)var0).getInstance(), var1, true);
      } else if (var0 instanceof ICIdentifier) {
         return var0.equals(var1) ? var2 : false;
      } else if (var0 instanceof ICConstant) {
         return false;
      } else if (var0 instanceof ICArrayElement) {
         Boolean var3 = false;

         for (ICElement var6 : var0.getSubElements()) {
            if (!(var6 instanceof ICExpression)) {
               return false;
            }

            Boolean var7 = pC((ICExpression)var6, var1, true);
            if (var7 == null) {
               return null;
            }

            var3 = var3 | var7;
            if (var3) {
               return true;
            }
         }

         return false;
      } else {
         return var0 instanceof ICOperation && ((ICOperation)var0).getOperatorType() == COperatorType.PTR
            ? pC(((ICOperation)var0).getFirstOperand(), var1, true)
            : null;
      }
   }

   static ICLeftExpression pC(ICElement var0) {
      if (var0 instanceof ICAssignment) {
         ICLeftExpression var1 = ((ICAssignment)var0).getLeft();
         return (ICLeftExpression)(var1 instanceof ICDecl ? ((ICDecl)((ICAssignment)var0).getLeft()).getIdentifier() : var1);
      } else {
         return null;
      }
   }

   static boolean pC(ICElement var0, List var1, List var2) {
      CVisitResults var3 = new CVisitResults(2);
      ahn var4 = new ahn(var1, var2);
      return pC(var4, var0, var3);
   }

   public static boolean pC(ICVisitor var0, ICElement var1, CVisitResults var2) {
      if (var2 == null) {
         var2 = new CVisitResults();
      }

      pC(var0, var1, null, var2);
      return var2.isVisitedSuccessfully();
   }

   private static void pC(ICVisitor var0, ICElement var1, ICElement var2, CVisitResults var3) {
      List var4 = var1.getSubElements();
      if (var1 instanceof ICAssignment) {
         boolean var5 = ((ICAssignment)var1).isCombinedOperatorAssignment() || ((ICAssignment)var1).isUnaryOperatorAssignment();
         ICLeftExpression var6 = ((ICAssignment)var1).getLeft();
         if (!var5 && !(var6 instanceof ICArrayElement) && !(var6 instanceof ICOperation) && !(var6 instanceof ICInstanceField)) {
            var4 = afm.pC(((ICAssignment)var1).getRight());
         }
      }

      var3.pushParent(var1);

      for (ICElement var8 : var4) {
         if (!CUtil.isClassMethodField(var8)) {
            pC(var0, var8, var1, var3);
            if (var3.isInterruptedVisit()) {
               return;
            }
         }
      }

      var3.popParent();
      var3.setReplacedNode(var1);
      var0.process(var1, var2, var3);
   }
}
