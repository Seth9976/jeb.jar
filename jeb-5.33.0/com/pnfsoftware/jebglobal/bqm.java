package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.IrregularFlowData;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExceptionHandler;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.concurrent.Watchdog;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class bqm {
   private static final ILogger pC = GlobalLog.getLogger(bqm.class);
   private IDMethodContext A;
   private CFG kS;
   private int wS;
   private List UT;
   private LinkedHashSet E;

   public bqm(IDMethodContext var1, int var2) {
      this.A = var1;
      this.kS = var1.getCfg();
      this.wS = var2;
   }

   public boolean pC(int var1) {
      if (this.UT != null) {
         throw new IllegalStateException();
      } else {
         this.UT = new ArrayList();
         this.E = new LinkedHashSet();
         boolean var2 = false;
         if (this.wS >= 0) {
            int var3 = 0;

            for (BasicBlock var5 : this.kS) {
               var3 += var5.irroutsize();
               if (var3 >= this.wS) {
                  var2 = true;
                  break;
               }
            }
         }

         Set var27 = new bsf(this.A).pC(var1);
         Object[] var10000 = new Object[]{DUtil.formatVarIds(var27)};
         if (var27.size() > 8) {
            return false;
         } else {
            ArrayDeque var28 = new ArrayDeque();
            bqm.Av var29 = new bqm.Av(this.kS.get(0));
            this.UT.add(var29);
            this.E.add(var29);
            var28.add(var29);
            int var6 = 0;

            while (!var28.isEmpty()) {
               if (++var6 > 10000) {
                  var10000 = new Object[0];
                  com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(
                     new RuntimeException("Unrolling: exceeded max rounds count"), this.A.getMethodSignature(), -1, null, true
                  );
                  return false;
               }

               if ((var6 & 4095) == 0) {
                  Watchdog.verify(this.A.getWatchdog());
               }

               bqm.Av var7 = (bqm.Av)var28.remove();
               var10000 = new Object[]{var6, var7, var28.size()};
               BasicBlock var8 = var7.pC;
               Map var9 = var7.pC();
               long var12 = -1L;
               IDInstruction var14 = (IDInstruction)var8.getLast();

               for (int var15 = 0; var15 < var8.size(); var15++) {
                  IDInstruction var16 = (IDInstruction)var8.get(var15);
                  Integer var17 = null;
                  if (var16.isAssignToVar()) {
                     var17 = var16.getAssignDestination().asVar().getId();
                  }

                  boolean var18 = pC(var16, var9.keySet());
                  if (var18 && var17 != null) {
                     IDImm var19 = null;

                     try {
                        var19 = var16.getAssignSource().evaluate(this.A.getGlobalContext(), var9);
                     } catch (DexDecEvaluationException var26) {
                     }

                     if (var19 == null) {
                        var10000 = new Object[0];
                        var10000 = new Object[]{var16.getOffset(), var16};
                        return false;
                     }

                     var9.put(var17, var19);
                  }

                  if (var17 != null && !var18) {
                     if (var17 != var1 && !var9.containsKey(var17) && !var27.contains(var17)) {
                        var9.remove(var17);
                     } else {
                        IDImm var54 = null;

                        try {
                           var54 = var16.getAssignSource().evaluate(this.A.getGlobalContext(), var9);
                        } catch (DexDecEvaluationException var25) {
                        }

                        if (var54 == null) {
                           var10000 = new Object[]{var17};
                           var10000 = new Object[]{var16.getOffset(), var16};
                           return false;
                        }

                        var9.put(var17, var54);
                     }
                  }

                  if (var16 == var14 && var16.getBreakingFlow().isBroken()) {
                     Integer var55 = null;

                     try {
                        var55 = var16.evaluate(var9);
                     } catch (DexDecEvaluationException var24) {
                     }

                     if (var55 != null) {
                        var12 = var55.intValue();
                     }
                  }
               }

               var7.wS = var12;

               for (IDInstruction var42 : var8) {
                  var7.kS.add(var42.duplicateWithOffsetAndSize(-1L, 1));
               }

               boolean var40 = false;
               switch (var14.getOpcode()) {
                  case IR_JUMP:
                  case IR_THROW:
                  case IR_RETURN:
                     break;
                  case IR_JCOND:
                  case IR_SWITCH:
                     var40 = true;
                  default:
                     int var43 = (int)var8.getEndAddress();
                     var7.kS.add(this.A.createJump(var43).withOffset(-1L));
               }

               HashMap var44 = new HashMap();
               if (var12 != -1L) {
                  if (var40) {
                     var7.kS.remove(var7.kS.size() - 2);
                     var40 = false;
                  }

                  IDInstruction var47 = (IDInstruction)var7.kS.get(var7.kS.size() - 1);
                  var47.setBranchTarget((int)var12);
                  BasicBlock var50 = this.kS.getBlockAt(var12);
                  bqm.Av var57 = new bqm.Av(var50, this.pC(var50, var9));
                  int var61;
                  if (this.E.contains(var57)) {
                     var61 = this.UT.indexOf(var57);
                     Assert.a(var61 >= 0);
                  } else {
                     var61 = this.UT.size();
                     this.UT.add(var57);
                     this.E.add(var57);
                     var28.add(var57);
                  }

                  var44.put((int)var12, -var61);
               } else {
                  for (BasicBlock var49 : var8.getOutputs()) {
                     bqm.Av var56 = new bqm.Av(var49, this.pC(var49, var9));
                     int var20;
                     if (this.E.contains(var56)) {
                        var20 = this.UT.indexOf(var56);
                        Assert.a(var20 >= 0);
                     } else {
                        var20 = this.UT.size();
                        this.UT.add(var56);
                        this.E.add(var56);
                        var28.add(var56);
                     }

                     var44.put((int)var49.getBase(), -var20);
                  }
               }

               int var48 = var7.kS.size();
               ((IDInstruction)var7.kS.get(var48 - 1)).updateTargets(var44);
               if (var40) {
                  ((IDInstruction)var7.kS.get(var48 - 2)).updateTargets(var44);
               }

               var7.UT = var40;
               if (!var2 && var8.canThrow()) {
                  for (BasicBlock var58 : var8.getIrregularOutputs()) {
                     bqm.Av var62 = new bqm.Av(var58, this.pC(var58, var9));
                     int var21;
                     if (this.E.contains(var62)) {
                        var21 = this.UT.indexOf(var62);
                        Assert.a(var21 >= 0);
                     } else {
                        var21 = this.UT.size();
                        this.UT.add(var62);
                        this.E.add(var62);
                        var28.add(var62);
                     }

                     var44.put((int)var58.getBase(), -var21);
                     var7.E.add(-var21);
                  }

                  for (IDExceptionHandler var63 : this.A.getExceptionData().getBlockHandlers((int)var8.getBase())) {
                     bfa var64 = new bfa(var63.getTypeIndex(), (Integer)var44.get(var63.getAddress()));
                     var7.sY.add(var64);
                  }

                  Assert.a(var7.sY.size() == var7.E.size());
               }
            }

            var10000 = new Object[]{this.UT.size(), var6};
            var10000 = new Object[]{this.kS.size()};
            HashMap var30 = new HashMap();
            ArrayList var31 = new ArrayList();
            int var32 = 0;
            int var10 = 0;

            for (bqm.Av var34 : this.UT) {
               var30.put(-var10, var32);
               var32 += var34.kS.size();
               var31.addAll(var34.kS);
               var10++;
            }

            int var33 = 0;

            for (IDInstruction var13 : var31) {
               var13.setOffset(var33);
               var13.updateTargets(var30);
               var33++;
            }

            ArrayList var36 = new ArrayList();
            bpk var37 = new bpk();

            for (bqm.Av var41 : this.UT) {
               Assert.a(var41.sY.size() == var41.E.size());
               if (!var41.E.isEmpty()) {
                  long var45 = ((IDInstruction)var41.kS.get(0)).getOffset();
                  int var53 = var41.kS.size() - (var41.UT ? 1 : 0);
                  long var60 = ((IDInstruction)var41.kS.get(var53 - 1)).getOffset();

                  for (int var22 : var41.E) {
                     int var23 = (Integer)var30.get(var22);
                     var36.add(new IrregularFlowData(var45, var60, var23));
                  }

                  for (bfa var67 : var41.sY) {
                     int var68 = (Integer)var30.get(var67.getAddress());
                     var37.pC((int)var45, var67.A(), var68);
                  }
               }
            }

            this.kS = new CFG(var31, var36);
            DUtil.removeGaps(this.kS);
            this.A.replace(this.kS, var37);
            DUtil.cleanGraph(this.A);
            return true;
         }
      }
   }

   private Map pC(BasicBlock var1, Map var2) {
      HashMap var3 = new HashMap(var2);
      IDFA var4 = this.kS.doDataFlowAnalysis();
      Iterator var5 = var3.keySet().iterator();

      while (var5.hasNext()) {
         int var6 = (Integer)var5.next();
         if (!var4.isAlive(var1, 0, var6)) {
            var5.remove();
         }
      }

      return var3;
   }

   private static boolean pC(IDInstruction var0, Set var1) {
      return !var0.visitInstruction(new bqn(var1), true);
   }

   static class Av {
      BasicBlock pC;
      Map A;
      List kS = new ArrayList();
      long wS = -1L;
      boolean UT;
      List E = new ArrayList();
      List sY = new ArrayList();

      Av(BasicBlock var1) {
         if (var1 == null) {
            throw new IllegalArgumentException();
         } else {
            this.pC = var1;
            this.A = new HashMap();
         }
      }

      Av(BasicBlock var1, Map var2) {
         if (var1 != null && var2 != null) {
            this.pC = var1;
            this.A = var2;
         } else {
            throw new IllegalArgumentException();
         }
      }

      Map pC() {
         return new HashMap(this.A);
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
         return 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
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
            bqm.Av var2 = (bqm.Av)var1;
            if (this.pC == null) {
               if (var2.pC != null) {
                  return false;
               }
            } else if (!this.pC.equals(var2.pC)) {
               return false;
            }

            if (this.A == null) {
               if (var2.A != null) {
                  return false;
               }
            } else if (!this.A.equals(var2.A)) {
               return false;
            }

            return true;
         }
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         Strings.ff(var1, "0x%04X", this.pC.getBase());
         if (this.wS != -1L) {
            Strings.ff(var1, " ->0x%04X", this.wS);
         }

         if (!this.A.isEmpty()) {
            var1.append(" [");
            int var2 = 0;

            for (int var4 : this.A.keySet()) {
               if (var2 > 0) {
                  var1.append(", ");
               }

               Strings.ff(var1, "%s:%s", DUtil.formatVarId(var4), this.A.get(var4));
               var2++;
            }

            var1.append("]");
         }

         return var1.toString();
      }
   }
}
