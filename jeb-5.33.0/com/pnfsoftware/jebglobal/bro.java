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

public class bro {
   private IDMethodContext pC;
   private Set A = new HashSet();
   private int kS = 100;
   private int wS = 6;

   public static boolean pC() {
      return cdb.pC();
   }

   public bro(IDMethodContext var1) {
      Assert.a(var1 != null);
      this.pC = var1;
   }

   public void pC(Set var1) {
      this.A = var1;
   }

   public IDImm pC(IDExpression var1, IDInstruction var2) {
      if (var1.hasSideEffects(this.pC, true)) {
         return null;
      } else {
         if (var2 != null) {
            boolean var3 = false;
            long var4 = var2.getOffset();
            Set var6 = var1.getVarIds();
            ArrayDeque var7 = new ArrayDeque();
            var7.add(new bro.Av(var4, var6));
            HashSet var8 = new HashSet();
            HashSet var9 = new HashSet();
            int var10 = 0;
            CFG var11 = this.pC.getCfg();
            IDFA var12 = var11.doDataFlowAnalysis();

            while (!var7.isEmpty() && var10 < this.wS) {
               bro.Av var13 = (bro.Av)var7.remove();
               if (var8.add(var13.pC)) {
                  var4 = var13.pC;

                  for (int var15 : var13.A) {
                     if (var9.add(var15)) {
                        Long var16 = var12.checkSingleDef(var4, var15);
                        if (var16 != null && var16 >= 0L) {
                           IDInstruction var17 = (IDInstruction)var11.getInstruction(var16);
                           if (var17.isAssignToVar(var15)) {
                              IDExpression var18 = var17.getAssignSource();
                              if (!var18.hasSideEffects(this.pC, true) && !((bph)var18).pC() && !var18.canThrow(this.pC)) {
                                 if (!var3) {
                                    var1 = var1.duplicate();
                                    var3 = true;
                                 }

                                 if (var1.replaceVariable(this.pC.getVar(var15), var18) == 0) {
                                    return null;
                                 }

                                 var7.add(new bro.Av(var16, var18.getVarIds()));
                                 var10++;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         if (this.A.contains(var1.toString())) {
            return null;
         } else if (!this.wS(var1)) {
            return null;
         } else if (!cdb.A(var1)) {
            return null;
         } else {
            Boolean var20 = null;

            try {
               var20 = this.kS(var1);
            } catch (Exception var19) {
               bzi.logger.catchingSilent(var19);
            }

            if (var20 == null) {
               String var22 = var1.toString();
               this.A.add(var22);
               return null;
            } else {
               return this.pC.getGlobalContext().createBoolean(var20);
            }
         }
      }
   }

   private Boolean kS(IDExpression var1) throws Exception {
      try {
         Boolean var4;
         try (cdb var2 = new cdb()) {
            var2.pC(this.kS);
            var2.pC(var1);
            Boolean var8 = var2.A();
            if (var8 == null) {
               return null;
            }

            if (Boolean.FALSE.equals(var8)) {
               return false;
            }

            var8 = var2.kS();
            if (!Boolean.FALSE.equals(var8)) {
               return null;
            }

            var4 = true;
         }

         return var4;
      } catch (Z3Exception | asz var7) {
         HashMap var3 = Maps.toMap("ir-expression", var1.toString());
         com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(var7, this.pC.getMethodSignature(), -1, var3);
         return null;
      }
   }

   private boolean wS(IDExpression var1) {
      boolean var2 = false;
      if (var1 instanceof IDOperation) {
         for (IDExpression var6 : ((bqa)var1).kS()) {
            if (var6.findByType(IDOperation.class) != null) {
               var2 = true;
               break;
            }
         }
      }

      if (!var2) {
         return false;
      } else {
         brq var8 = new brq(this);
         var1.visitDepthPost(var8);
         if (var8.pC < 2) {
            return false;
         } else {
            if (var1 instanceof IDOperation var9 && pC(var9)) {
               IDExpression var10 = var9.getOperand1();
               IDExpression var7 = var9.getOperand2();
               if (pC(var10) && pC(var7)) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   static boolean pC(IDOperation var0) {
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

   static boolean A(IDOperation var0) {
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

   static boolean pC(IDExpression var0) {
      if (A(var0)) {
         return true;
      } else if (var0 instanceof IDOperation var1 && A(var1)) {
         IDExpression var2 = var1.getOperand1();
         IDExpression var3 = var1.getOperand2();
         return A(var2) && A(var3);
      } else {
         return false;
      }
   }

   static boolean A(IDExpression var0) {
      return var0 instanceof IDImm || var0 instanceof IDVar;
   }

   static class Av {
      long pC;
      Set A;

      Av(long var1, Set var3) {
         this.pC = var1;
         this.A = var3;
      }
   }
}
