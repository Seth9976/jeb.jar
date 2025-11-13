package com.pnfsoftware.jebglobal;

import com.microsoft.z3.Z3Exception;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.Maps;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class bvy {
   public static final int q = 100;
   public static final int RF = 6;
   private IDMethodContext xK;
   private Set Dw = new HashSet();
   private int Uv = 100;
   private int oW = 6;

   public static boolean q() {
      return cij.q();
   }

   public bvy(IDMethodContext var1) {
      Assert.a(var1 != null);
      this.xK = var1;
   }

   public void q(Set var1) {
      this.Dw = var1;
   }

   public Set RF() {
      return this.Dw;
   }

   public void q(int var1) {
      this.Uv = var1;
   }

   public int xK() {
      return this.Uv;
   }

   public IDImm q(IDExpression var1, IDInstruction var2) {
      if (var1.hasSideEffects(this.xK, true)) {
         return null;
      } else {
         if (var2 != null) {
            boolean var3 = false;
            long var4 = var2.getOffset();
            Set var6 = var1.getVarIds();
            ArrayDeque var7 = new ArrayDeque();
            var7.add(new bvy.eo(var4, var6));
            HashSet var8 = new HashSet();
            HashSet var9 = new HashSet();
            int var10 = 0;
            CFG var11 = this.xK.getCfg();
            IDFA var12 = var11.doDataFlowAnalysis();

            while (!var7.isEmpty() && var10 < this.oW) {
               bvy.eo var13 = (bvy.eo)var7.remove();
               if (var8.add(var13.q)) {
                  var4 = var13.q;

                  for (int var15 : var13.RF) {
                     if (var9.add(var15)) {
                        Long var16 = var12.checkSingleDef(var4, var15);
                        if (var16 != null && var16 >= 0L) {
                           IDInstruction var17 = (IDInstruction)var11.getInstruction(var16);
                           if (var17.isAssignToVar(var15)) {
                              IDExpression var18 = var17.getAssignSource();
                              if (!var18.hasSideEffects(this.xK, true) && !((btk)var18).RF() && !var18.canThrow(this.xK)) {
                                 if (!var3) {
                                    var1 = var1.duplicate();
                                    var3 = true;
                                 }

                                 if (var1.replaceVariable(this.xK.getVar(var15), var18) == 0) {
                                    return null;
                                 }

                                 var7.add(new bvy.eo(var16, var18.getVarIds()));
                                 var10++;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         if (this.Dw.contains(var1.toString())) {
            return null;
         } else if (!this.Dw(var1)) {
            return null;
         } else if (!cij.RF(var1)) {
            return null;
         } else {
            Boolean var20 = null;

            try {
               var20 = this.xK(var1);
            } catch (Exception var19) {
               cdz.logger.catchingSilent(var19);
            }

            if (var20 == null) {
               String var22 = var1.toString();
               this.Dw.add(var22);
               return null;
            } else {
               return this.xK.getGlobalContext().createBoolean(var20);
            }
         }
      }
   }

   private Boolean xK(IDExpression var1) throws Exception {
      try {
         Boolean var4;
         try (cij var2 = new cij()) {
            var2.q(this.Uv);
            var2.q(var1);
            Boolean var8 = var2.Dw();
            if (var8 == null) {
               return null;
            }

            if (Boolean.FALSE.equals(var8)) {
               return false;
            }

            var8 = var2.Uv();
            if (!Boolean.FALSE.equals(var8)) {
               return null;
            }

            var4 = true;
         }

         return var4;
      } catch (Z3Exception | cig var7) {
         HashMap var3 = Maps.toMap("ir-expression", var1.toString());
         com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(var7, this.xK.getMethodSignature(), -1, var3);
         return null;
      }
   }

   private boolean Dw(IDExpression var1) {
      boolean var2 = false;
      if (var1 instanceof IDOperation) {
         for (IDExpression var6 : ((bug)var1).oW()) {
            if (var6.findByType(IDOperation.class) != null) {
               var2 = true;
               break;
            }
         }
      }

      if (!var2) {
         return false;
      } else {
         bwa var8 = new bwa(this);
         var1.visitDepthPost(var8);
         if (var8.q < 2) {
            return false;
         } else {
            if (var1 instanceof IDOperation var9 && q(var9)) {
               IDExpression var10 = var9.getOperand1();
               IDExpression var7 = var9.getOperand2();
               if (q(var10) && q(var7)) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   static boolean q(IDOperation var0) {
      switch (var0.getOperatorType()) {
         case EQ:
         case NE:
         case GE:
         case GT:
         case LE:
         case LT:
            return true;
         default:
            return false;
      }
   }

   static boolean RF(IDOperation var0) {
      switch (var0.getOperatorType()) {
         case ADD:
         case SUB:
         case MUL:
         case DIV:
         case REM:
            return true;
         default:
            return false;
      }
   }

   static boolean q(IDExpression var0) {
      if (RF(var0)) {
         return true;
      } else if (var0 instanceof IDOperation var1 && RF(var1)) {
         IDExpression var2 = var1.getOperand1();
         IDExpression var3 = var1.getOperand2();
         return RF(var2) && RF(var3);
      } else {
         return false;
      }
   }

   static boolean RF(IDExpression var0) {
      return var0 instanceof IDImm || var0 instanceof IDVar;
   }

   static class eo {
      long q;
      Set RF;

      eo(long var1, Set var3) {
         this.q = var1;
         this.RF = var3;
      }
   }
}
