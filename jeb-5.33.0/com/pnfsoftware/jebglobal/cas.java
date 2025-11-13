package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.HashMap;
import java.util.Map;

public class cas extends AbstractDOptimizer {
   static Map pC = new HashMap();

   @Override
   public int perform() {
      int var1 = 0;

      for (IDInstruction var3 : this.cfg.instructions()) {
         if (var3.isInvoke() && this.pC(var3.getInvokeData())) {
            var3.transformToNop();
            this.cfg.invalidateDataFlowAnalysis();
            var1++;
         }
      }

      for (BasicBlock var13 : this.cfg) {
         for (int var4 = 0; var4 < var13.size(); var4++) {
            IDInstruction var5 = (IDInstruction)var13.get(var4);
            if (var5.isAssignToVar() && var5.getAssignSource() instanceof IDNewInfo var6 && this.pC(var6)) {
               IDVar var14 = (IDVar)var5.getAssignDestination();

               int var8;
               for (var8 = var4 + 1; var8 < var13.size(); var8++) {
                  IDInstruction var9 = (IDInstruction)var13.get(var8);
                  if (!var9.isInvoke()
                     || !(var9.getInvokeData() instanceof IDCallInfo var10)
                     || var10.getCountOfArguments() != 2
                     || !var10.getMethodName().equals("add")
                     || var10.getArgument(0) != var14
                     || !(var10.getArgument(1) instanceof IDImm)) {
                     break;
                  }
               }

               this.analyzeChains();
               if (this.dfa.getLiveChains(var13, var8, var14.getId(), 1).isEmpty()) {
                  for (int var15 = var4; var15 < var8; var15++) {
                     ((IDInstruction)var13.get(var15)).transformToNop();
                  }

                  this.cfg.invalidateDataFlowAnalysis();
                  var1++;
               }
            }
         }
      }

      return var1;
   }

   private boolean pC(IDNewInfo var1) {
      String var2 = var1.getMethodSignature();
      if (var2.equals("Ljava/util/ArrayList;-><init>()V")) {
         return true;
      } else if (var2.startsWith("Ljava/util/") && (var2.endsWith("-><init>()V") || var2.endsWith("-><init>(I)V"))) {
         String var3 = JvmMethodSig.parse(var2).csig;
         if (!this.ctx.getTypeInfoProvider().isChildOf(var3, "Ljava/util/Collection;")) {
            return false;
         } else {
            return !var2.endsWith("-><init>(I)V") ? true : var1.getArgument(0) instanceof IDImm var4 && var4.getRawValue() < 100L;
         }
      } else {
         return false;
      }
   }

   boolean pC(IDExpression var1) {
      IDCallInfo var2 = null;
      cas.Sv var3 = null;
      if (var1 instanceof IDCallInfo) {
         var2 = (IDCallInfo)var1;
         String var6 = var2.getMethodSignature();
         var3 = (cas.Sv)pC.get(var6);
         if (var3 == null) {
            return false;
         }
      } else if (!(var1 instanceof IDImm)) {
         if (var1 instanceof IDOperation var5) {
            if (var5.getOperatorType().isCast()) {
               return false;
            }
         } else {
            if (!(var1 instanceof IDNewArrayInfo var4)) {
               return false;
            }

            if (!(var4.getSize() instanceof IDImm)) {
               return false;
            }

            int var18 = (int)var4.getSize().asImm().getValueAsLong();
            if (var18 < 0) {
               return false;
            }
         }
      }

      for (IDExpression var19 : var1.getSubExpressions()) {
         if (!this.pC(var19)) {
            return false;
         }
      }

      if (var3 != null) {
         Assert.a(var2 != null);
         switch (var3) {
            case pC:
               IDExpression var17 = var2.getArgument(0);
               IDExpression var21 = var2.getArgument(1);
               IDExpression var22 = var2.getArgument(2);
               IDExpression var23 = var2.getArgument(3);
               IDExpression var9 = var2.getArgument(4);
               Integer var10 = this.A(var21);
               if (var10 == null) {
                  return false;
               } else {
                  Integer var11 = this.A(var23);
                  if (var11 == null) {
                     return false;
                  } else {
                     Integer var12 = this.A(var9);
                     if (var12 == null) {
                        return false;
                     } else {
                        cas.Av var13 = this.wS(var17);
                        if (var13 == null) {
                           return false;
                        } else {
                           cas.Av var14 = this.wS(var22);
                           if (var14 == null) {
                              return false;
                           } else if (var13.pC != var14.pC) {
                              return false;
                           } else {
                              if (var10 >= 0 && var11 >= 0 && var12 >= 0) {
                                 if (var10 <= var13.A && var11 <= var14.A) {
                                    if (var10 + var12 <= var13.A && var11 + var12 <= var14.A) {
                                       return true;
                                    }

                                    return false;
                                 }

                                 return false;
                              }

                              return false;
                           }
                        }
                     }
                  }
               }
            case A:
               IDExpression var16 = var2.getArgument(0);
               String var20 = this.kS(var16);
               if (var20 == null) {
                  return false;
               } else {
                  for (int var7 = 0; var7 < var20.length(); var7++) {
                     char var8 = var20.charAt(var7);
                     if (var8 >= 127) {
                        return false;
                     }
                  }

                  return true;
               }
            default:
               return false;
         }
      } else {
         return true;
      }
   }

   Integer A(IDExpression var1) {
      return var1 instanceof IDImm var2 && var2.canReadAsLong() ? (int)var2.getValueAsLong() : null;
   }

   String kS(IDExpression var1) {
      return var1.isStringImm() ? var1.asImm().getStringValue(this.g) : null;
   }

   cas.Av wS(IDExpression var1) {
      if (var1 instanceof IDCallInfo var2 && var2.getMethodSignature().equals("Ljava/lang/String;->getBytes()[B")) {
         IDExpression var3 = var2.getArgument(0);
         if (var3.isStringImm()) {
            String var9 = var3.asImm().getStringValue(this.g);
            byte[] var5 = Strings.encodeUTF8(var9);
            return new cas.Av(this.tf.createArrayType(this.tf.getByte(), 1), var5.length, var5);
         }
      }

      if (var1 instanceof IDNewArrayInfo var6 && var6.getSize() instanceof IDImm var7) {
         int var8 = (int)var7.getValueAsLong();
         return new cas.Av(var6.getType(), var8);
      } else {
         return null;
      }
   }

   static {
      pC.put("Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V", cas.Sv.pC);
      pC.put("Ljava/lang/String;->getBytes()[B", cas.Sv.A);
   }

   static class Av {
      IJavaType pC;
      int A;
      Object kS;

      Av(IJavaType var1, int var2) {
         this(var1, var2, null);
      }

      Av(IJavaType var1, int var2, Object var3) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
      }
   }

   static enum Sv {
      pC,
      A;
   }
}
