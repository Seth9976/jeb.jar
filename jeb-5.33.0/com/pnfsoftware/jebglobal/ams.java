package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESwitch;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class ams {
   private static final StructuredLogger kS = aco.pC(ams.class);
   IERoutineContext pC;
   CFG A;

   public ams(IERoutineContext var1) {
      this.pC = var1;
      this.A = var1.getCfg();
   }

   public ams.Av pC() {
      IDFA var1 = this.A.doDataFlowAnalysis();
      TreeMap var2 = new TreeMap();

      for (BasicBlock var4 : this.A) {
         var2.put(var4.getBase(), new ams.Sv(var4.getBase()));
      }

      long var21 = this.A.getEntryAddress();
      ((ams.Sv)var2.get(var21)).A = new TreeMap();
      ArrayDeque var5 = new ArrayDeque();
      var5.add(var21);

      while (!var5.isEmpty()) {
         long var6 = (Long)var5.remove();
         BasicBlock var8 = this.A.getBlockAt(var6);
         ams.Sv var9 = (ams.Sv)var2.get(var6);
         HashMap var10 = new HashMap(var9.A);

         for (int var11 = 0; var11 < var8.size(); var11++) {
            IEStatement var12 = (IEStatement)var8.get(var11);
            if (var12 instanceof IEAssign && ((IEAssign)var12).getDstOperand() instanceof IEVar && ((IEAssign)var12).getSrcOperand() instanceof IEImm) {
               IEVar var24 = (IEVar)((IEAssign)var12).getDstOperand();
               IEImm var14 = (IEImm)((IEAssign)var12).getSrcOperand();
               var10.put(var24.getId(), var14);
            } else {
               Collection var13 = var1.getInstructionAllDefs(var6);
               Maps.removeAll(var10, var13);
            }

            var6 += var12.getSize();
         }

         IEVar var22 = null;
         LinkedHashMap var23 = new LinkedHashMap();
         IEStatement var25 = (IEStatement)var8.getLast();
         if (var25 instanceof IEJump) {
            IEJump var27 = var25.asJump();
            IEGeneric var29 = var27.getCondition();
            if (var29 != null) {
               IEImm var31 = null;
               boolean var33 = false;
               if (var29 instanceof IEVar) {
                  var22 = (IEVar)var29;
                  var31 = EUtil.zero(var22.getBitsize());
                  var33 = true;
               } else if (var29 instanceof IEOperation var34) {
                  OperationType var37 = var34.getOperationType();
                  if (var37 != OperationType.LOG_EQ && var37 != OperationType.LOG_NEQ) {
                     if (var37 == OperationType.LOG_NOT && var34.getOperand1() instanceof IEVar) {
                        var22 = (IEVar)var34.getOperand1();
                        var31 = EUtil.zero(var22.getBitsize());
                     }
                  } else if (var34.getOperand1() instanceof IEVar && var34.getOperand2() instanceof IEImm) {
                     var22 = (IEVar)var34.getOperand1();
                     var31 = (IEImm)var34.getOperand2();
                     var33 = var37 == OperationType.LOG_NEQ;
                  }
               }

               if (var22 != null) {
                  int var35 = var33 ? (int)var8.getEndAddress() : var27.getBranchAddress();
                  var23.put(var35, var31);
               }
            }
         } else if (var25 instanceof IESwitch) {
            IESwitch var26 = var25.asSwitch();
            IEGeneric var15 = var26.getControlExpression();
            if (var15 instanceof IEVar) {
               var22 = (IEVar)var15;
               HashSet var16 = new HashSet();

               for (Couple var18 : var26.getCases()) {
                  if (var18.getFirst() instanceof IEImm) {
                     IEImm var19 = ((IEGeneric)var18.getFirst()).asImm();
                     int var20 = (Integer)var18.getSecond();
                     if (!var16.contains(var20) && var23.put(var20, var19) != null) {
                        var23.remove(var20);
                        var16.add(var20);
                     }
                  }
               }
            }
         }

         for (BasicBlock var30 : var8.getOutputs()) {
            long var32 = var30.getBase();
            ams.Sv var36 = (ams.Sv)var2.get(var32);
            HashMap var38 = var10;
            IEImm var39 = (IEImm)var23.get((int)var32);
            if (var39 != null) {
               var38 = new HashMap(var10);
               var38.put(var22.getId(), var39);
            }

            if (var36.pC(var38)) {
               var5.add(var32);
            }
         }
      }

      return new ams.Av(var2);
   }

   public static class Av {
      SortedMap pC = new TreeMap();

      Av(SortedMap var1) {
         this.pC = var1;
      }

      public ams.Sv pC(long var1) {
         return (ams.Sv)this.pC.get(var1);
      }
   }

   public static class Sv {
      long pC;
      SortedMap A;

      Sv(long var1) {
         this.pC = var1;
      }

      public Map pC() {
         return (Map)(this.A == null ? Collections.emptyMap() : this.A);
      }

      boolean pC(Map var1) {
         if (this.A == null) {
            this.A = new TreeMap(var1);
            return true;
         } else {
            int var2 = 0;

            for (Integer var4 : var1.keySet()) {
               if (this.A.containsKey(var4) && !((IEImm)this.A.get(var4)).equalsEx(var1.get(var4), false)) {
                  this.A.remove(var4);
                  var2++;
               }
            }

            if (Maps.retainAll(this.A, var1.keySet())) {
               var2++;
            }

            return var2 > 0;
         }
      }
   }
}
