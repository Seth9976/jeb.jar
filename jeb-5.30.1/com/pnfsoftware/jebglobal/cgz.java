package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class cgz extends AbstractDOptimizer {
   private static final Map q = new HashMap();

   @Override
   public int perform() {
      int var1 = 0;
      int var2 = this.q();
      if (var2 > 0) {
         var1 += var2;
         this.cfg.invalidateDataFlowAnalysis();
      }

      int var3 = this.RF();
      if (var3 > 0) {
         var1 += var3;
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   private int q() {
      int var1 = 0;

      for (int var2 = 0; var2 < this.cfg.size(); var2++) {
         BasicBlock var3 = this.cfg.get(var2);

         label134:
         for (int var4 = 0; var4 < var3.size(); var4++) {
            IDInstruction var5 = (IDInstruction)var3.get(var4);
            if (var5.getOpcode() == DOpcodeType.IR_ASSIGN && var5.getOperand2() instanceof IDNewInfo && var5.getOperand1() instanceof IDVar) {
               IDVar var6 = (IDVar)var5.getOperand1();
               IDNewInfo var7 = (IDNewInfo)var5.getOperand2();
               String var8 = var7.getMethodSignature();
               Boolean var9 = (Boolean)q.get(var8);
               if (var9 != null) {
                  ArrayList var10 = new ArrayList();
                  if (var9) {
                     var10.add(var7.getArgument(0));
                  }

                  this.analyzeChains(false);
                  Collection var11 = this.dfa.getBlockDefUses(var5.getOffset(), var6.getId());
                  if (var11 != null && !var11.isEmpty() && !var11.contains(-2L)) {
                     ArrayList var12 = new ArrayList(var11);
                     int var13 = var12.size();

                     for (long var15 : var12.subList(0, var13 - 1)) {
                        IDInstruction var17 = (IDInstruction)this.cfg.getInstruction(var15);
                        if (var17 == null || var17.getOpcode() != DOpcodeType.IR_INVOKE || !(var17.getOperand2() instanceof IDCallInfo)) {
                           continue label134;
                        }

                        IDCallInfo var18 = (IDCallInfo)var17.getOperand2();
                        if (var18.getInvokeType() != DInvokeType.VIRTUAL
                           || var18.getCountOfArguments() != 2
                           || !var6.equals(var18.getArgument(0))
                           || !"append".equals(var18.getMethodName())) {
                           continue label134;
                        }

                        var10.add(var18.getArgument(1));
                     }

                     if (var10.size() != 1 || this.tf.getJavaLangString().equals(((IDExpression)var10.get(0)).getType())) {
                        IDInstruction var21 = (IDInstruction)this.cfg.getInstruction((Long)var12.get(var13 - 1));
                        if (var21.countUsedVariable(var6) == 1) {
                           List var22 = q(var21, var6, true);
                           if (var22 != null && var22.size() >= 2 && var22.get(0) instanceof IDCallInfo) {
                              IDCallInfo var16 = (IDCallInfo)var22.get(0);
                              if (var16.getInvokeType() != DInvokeType.VIRTUAL
                                 || var16.getCountOfArguments() != 1
                                 || !var6.equals(var16.getArgument(0))
                                 || !"toString".equals(var16.getMethodName())) {
                                 break;
                              }

                              Object var23;
                              if (var10.isEmpty()) {
                                 var23 = this.g.createString("");
                              } else {
                                 var23 = (IDExpression)var10.get(0);

                                 for (int var24 = 1; var24 < var10.size(); var24++) {
                                    IDExpression var19 = (IDExpression)var10.get(var24);
                                    var23 = this.g.createOperation(null, JavaOperatorType.CONCAT, (IDExpression)var23, var19);
                                 }
                              }

                              var5.transformToNop();

                              for (long var27 : var12.subList(0, var13 - 1)) {
                                 ((IDInstruction)this.cfg.getInstruction(var27)).transformToNop();
                              }

                              IDExpression var26 = (IDExpression)var22.get(1);
                              var26.replaceSubExpression(var16, (IDExpression)var23);
                              var1++;
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      return var1;
   }

   private static List q(IDInstruction var0, IDExpression var1, boolean var2) {
      ArrayList var3 = new ArrayList();
      var0.visitDepthPost(new cha(var1, var2, var3));
      return var3.isEmpty() ? null : var3;
   }

   private int RF() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg) {
         for (int var4 = 0; var4 < var3.size(); var4++) {
            IDInstruction var5 = (IDInstruction)var3.get(var4);
            if (!var5.visitInstruction(new chb(this), true)) {
               var1++;
               var4--;
            }
         }
      }

      return var1;
   }

   private IDExpression q(IDCallInfo var1) {
      IDExpression var2 = var1.getArgument(0);
      ArrayList var3 = new ArrayList();

      while (var2 instanceof IDCallInfo) {
         IDCallInfo var4 = (IDCallInfo)var2;
         if (!var4.getMethodName().equals("append") || var4.getCountOfArguments() != 2) {
            break;
         }

         var2 = var4.getArgument(0);
         var3.add(0, var4.getArgument(1));
      }

      if (!(var2 instanceof IDNewInfo var7)) {
         return null;
      } else {
         Boolean var5 = (Boolean)q.get(var7.getMethodSignature());
         if (var5 == null) {
            return null;
         } else {
            if (var5) {
               var3.add(0, var7.getArgument(0));
            }

            if (var3.isEmpty()) {
               return this.g.createString("");
            } else {
               Object var8 = (IDExpression)var3.get(0);

               for (int var9 = 1; var9 < var3.size(); var9++) {
                  IDExpression var6 = (IDExpression)var3.get(var9);
                  var8 = this.g.createOperation(null, JavaOperatorType.CONCAT, (IDExpression)var8, var6);
               }

               return (IDExpression)var8;
            }
         }
      }
   }

   static {
      q.put("Ljava/lang/StringBuilder;-><init>()V", false);
      q.put("Ljava/lang/StringBuilder;-><init>(I)V", false);
      q.put("Ljava/lang/StringBuilder;-><init>(Ljava/lang/CharSequence;)V", true);
      q.put("Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V", true);
      q.put("Ljava/lang/StringBuffer;-><init>()V", false);
      q.put("Ljava/lang/StringBuffer;-><init>(I)V", false);
      q.put("Ljava/lang/StringBuffer;-><init>(Ljava/lang/CharSequence;)V", true);
      q.put("Ljava/lang/StringBuffer;-><init>(Ljava/lang/String;)V", true);
   }
}
