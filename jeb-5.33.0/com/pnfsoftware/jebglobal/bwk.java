package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.IDexDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexFieldData;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexValue;
import com.pnfsoftware.jeb.core.units.code.android.ir.DCopyOptions;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDStaticField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.ConcurrentHashSet;
import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class bwk {
   private static final ILogger A = GlobalLog.getLogger(bwk.class);
   final int pC = 1;
   private int kS;
   private IDGlobalContext wS;
   private String UT;
   private List E;
   private boolean sY;
   private boolean ys;
   private boolean ld;
   private boolean gp;
   private boolean oT;
   private IDStaticField fI;
   private IDStaticField WR;
   private Map NS = new HashMap();

   public bwk(IDGlobalContext var1, String var2, List var3) {
      this.wS = var1;
      this.UT = var2;
      this.E = var3;
   }

   public void pC(boolean var1) {
      this.sY = var1;
   }

   public void A(boolean var1) {
      this.ys = var1;
   }

   public void kS(boolean var1) {
      this.ld = var1;
   }

   public void wS(boolean var1) {
      this.oT = var1;
   }

   public int pC() {
      if (!this.kS()) {
         return 0;
      } else {
         Object[] var10000 = new Object[]{this.fI, this.WR};
         return !this.wS() ? 0 : this.UT();
      }
   }

   public Map A() {
      return this.NS;
   }

   boolean kS() {
      for (IDMethodContext var2 : this.E) {
         if (this.pC(var2)) {
            if (this.fI != null && this.WR != null) {
               return true;
            }

            return false;
         }
      }

      return false;
   }

   private boolean pC(IDMethodContext var1) {
      for (BasicBlock var4 : var1.getCfg()) {
         for (int var5 = 0; var5 < var4.size(); var5++) {
            IDInstruction var6 = (IDInstruction)var4.get(var5);
            if (var6.isAssign()) {
               IDExpression var7 = var6.getAssignSource();
               if (var7.isOperation(JavaOperatorType.REM)) {
                  IDExpression var8 = var6.getAssignDestination();
                  IDStaticField var9 = null;
                  if (var8 instanceof IDStaticField var10) {
                     var9 = var10;
                  } else {
                     IDInstruction var12;
                     if (var8 instanceof IDVar var11
                        && var5 + 1 < var4.size()
                        && (var12 = (IDInstruction)var4.get(var5 + 1)).isAssignFromVar(var11.getId())
                        && var12.getAssignDestination() instanceof IDStaticField var13) {
                        var9 = var13;
                     }
                  }

                  if (var9 != null) {
                     IDOperation var26 = var7.asOperation();
                     if (var26.getRight() instanceof IDImm var27 && var27.getRawValue() == 128L) {
                        IDExpression var29 = var26.getLeft();
                        if (var29.isOperation(JavaOperatorType.ADD)) {
                           IDExpression var30 = var29.asOperation().getOperand1();
                           IDExpression var32 = var29.asOperation().getOperand2();
                           if (var30 instanceof IDStaticField var15 && var32 instanceof IDImm var16) {
                              if (this.pC(var1, var15, var16, var9) && this.E()) {
                                 return true;
                              }
                           } else if (var32 instanceof IDStaticField var17 && var30 instanceof IDImm var18) {
                              if (this.pC(var1, var17, var18, var9) && this.E()) {
                                 return true;
                              }
                           } else {
                              IDVar var19 = null;
                              IDImm var20 = null;
                              if (var30 instanceof IDVar var21 && var32 instanceof IDImm var22) {
                                 var19 = var21;
                                 var20 = var22;
                              } else if (var32 instanceof IDVar var23 && var30 instanceof IDImm var24) {
                                 var19 = var23;
                                 var20 = var24;
                              }

                              IDInstruction var2;
                              if (var19 != null && var20 != null && var5 >= 1 && (var2 = (IDInstruction)var4.get(var5 - 1)).isAssign()) {
                                 IDExpression var40 = var2.getAssignDestination();
                                 IDExpression var41 = var2.getAssignSource();
                                 if (var40 == var19 && var41 instanceof IDStaticField var42) {
                                    if (this.pC(var1, var42, var20, var9) && this.E()) {
                                       return true;
                                    }
                                 } else if (var41 == var19 && var40 instanceof IDStaticField var43 && this.pC(var1, var43, var20, var9) && this.E()) {
                                    return true;
                                 }
                              }
                           }
                        } else {
                           IDInstruction var25;
                           if (var29 instanceof IDVar && var5 >= 1 && (var25 = (IDInstruction)var4.get(var5 - 1)).isAssign()) {
                              IDExpression var31 = var25.getAssignDestination();
                              IDExpression var33 = var25.getAssignSource();
                              if (var31.equals(var29) && var33.isOperation(JavaOperatorType.ADD)) {
                                 IDExpression var34 = var33.asOperation().getOperand1();
                                 IDExpression var35 = var33.asOperation().getOperand2();
                                 if (var34 instanceof IDStaticField var36 && var35 instanceof IDImm var37) {
                                    if (this.pC(var1, var36, var37, var9) && this.E()) {
                                       return true;
                                    }
                                 } else if (var35 instanceof IDStaticField var38
                                    && var34 instanceof IDImm var39
                                    && this.pC(var1, var38, var39, var9)
                                    && this.E()) {
                                    return true;
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      return false;
   }

   private boolean E() {
      this.kS++;
      return this.kS >= 1;
   }

   private boolean pC(IDMethodContext var1, IDStaticField var2, IDImm var3, IDStaticField var4) {
      int var5 = (int)var3.getRawValue();
      if (var5 >= 0 && var5 < 128 && (var5 & 1) == 1 && !var4.equalsEx(var2, false)) {
         String var6 = var4.getClassSignature();
         String var7 = var2.getClassSignature();
         if (var6.equals(var7) && (!this.sY || var6.equals(this.UT))) {
            IDStaticField[] var8 = new IDStaticField[]{var4, var2};
            if (this.pC(var1, var8)) {
               return true;
            }
         }
      }

      return false;
   }

   private boolean pC(IDMethodContext var1, IDStaticField[] var2) {
      if (this.fI != null && this.WR != null) {
         return this.fI.equalsEx(var2[0], false) && this.WR.equalsEx(var2[1], false)
            ? true
            : this.fI.equalsEx(var2[1], false) && this.WR.equalsEx(var2[0], false);
      } else {
         IDexField var3 = this.pC(var2[0]);
         if (var3 == null) {
            return false;
         } else {
            IDexField var4 = this.pC(var2[1]);
            if (var4 == null) {
               return false;
            } else if (var3.getClassType() != var4.getClassType()) {
               return false;
            } else {
               int[] var5 = new int[2];
               IDexValue var6;
               if ((var6 = var3.getStaticInitializer()) != null) {
                  var5[0] = var6.getInt();
               }

               if ((var6 = var4.getStaticInitializer()) != null) {
                  var5[1] = var6.getInt();
               }

               String var7 = var4.getClassTypeSignature(false);
               String var8 = var7 + "-><clinit>()V";
               IDMethodContext var9 = this.pC(var8);
               if (var9 == null && this.ys) {
                  var9 = this.pC(var8, var1);
               }

               if (var9 != null) {
                  this.pC(var9, var2, var5);
               }

               int var10 = var5[0];
               int var11 = var5[1];
               if ((var10 + var11 & 1) == 0) {
                  return false;
               } else {
                  if ((var10 & 1) == 0) {
                     this.fI = var2[0];
                     this.WR = var2[1];
                  } else {
                     this.fI = var2[1];
                     this.WR = var2[0];
                  }

                  return true;
               }
            }
         }
      }
   }

   private IDMethodContext pC(String var1) {
      for (IDMethodContext var3 : this.E) {
         if (var3.getMethodSignature().equals(var1)) {
            return var3;
         }
      }

      return null;
   }

   private IDexField pC(IDStaticField var1) {
      IDexField var2 = var1.getNativeField(this.wS);
      if (var2 != null && var2.isInternal() && var2.getFieldTypeSignature(false).equals("I")) {
         IDexFieldData var3 = var2.getData();
         if (!var3.isStatic()) {
            return null;
         } else {
            return var3.isPublic() ? null : var2;
         }
      } else {
         return null;
      }
   }

   private IDMethodContext pC(String var1, IDMethodContext var2) {
      IDexMethod var3 = var2.getDex().getMethod(var1);
      if (var3 == null) {
         return null;
      } else {
         bpx var4 = ((com.pnfsoftware.jeb.corei.parsers.dexdec.Ws)var2.getGlobalContext().getDecompiler()).pC(var3);
         var4.A(false);
         var4.pC(false);
         var4.pC(var2.getWatchdog());
         var4.pC(var2.getDecompilationFlags());
         var4.pC(var2);

         try {
            var4.kS();
         } catch (Exception var6) {
            com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(var6, var3.getSignature(false));
            return null;
         }

         new bzu().perform(var4);
         return var4;
      }
   }

   private void pC(IDMethodContext var1, IDStaticField[] var2, int[] var3) {
      for (IDInstruction var5 : var1.getCfg().instructions()) {
         if (var5.isAssign() && var5.getAssignDestination() instanceof IDStaticField var6 && var5.getAssignSource() instanceof IDImm var7) {
            if (var6.equalsEx(var2[0], false)) {
               var3[0] = (int)var7.getRawValue();
            } else if (var6.equalsEx(var2[1], false)) {
               var3[1] = (int)var7.getRawValue();
            }
         }
      }
   }

   boolean wS() {
      for (IDMethodContext var2 : this.E) {
         if (!this.A(var2)) {
            com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(
               new RuntimeException("Unexpected failure on OEG-cfo examination"), null, -1, Maps.toMap("csig", this.UT), true
            );
            return false;
         }
      }

      return true;
   }

   private boolean A(IDMethodContext var1) {
      for (IDInstruction var3 : var1.getCfg().instructions()) {
         if (var3.isAssign()) {
            IDExpression var4 = var3.getAssignDestination();
            if (var4.equalsEx(this.fI, false)) {
               int var5 = this.pC(var1, var3, var3.getAssignSource());
               if (var5 != 0) {
                  Object[] var10000 = new Object[]{var3};
                  return false;
               }
            } else if (var4.equalsEx(this.WR, false)) {
               int var19 = this.pC(var1, var3, var3.getAssignSource());
               if (var19 != 1) {
                  Object[] var22 = new Object[]{var3};
                  return false;
               }
            }
         }
      }

      LinkedHashSet var14 = new LinkedHashSet();
      bwk.K var15 = new bwk.K(null, var14);

      for (IDInstruction var20 : var1.getCfg().instructions()) {
         if (!var20.visitInstruction(var15)) {
            Object[] var23 = new Object[]{var20};
            return false;
         }
      }

      if (!var14.isEmpty()) {
         IDFA var18 = var1.getCfg().doDataFlowAnalysis();
         HashSet var21 = new HashSet();

         while (!var14.isEmpty()) {
            LinkedHashSet var6 = new LinkedHashSet();

            for (IDInstruction var8 : var14) {
               if (var21.add(var8.getOffset())) {
                  IDVar var9 = (IDVar)var8.getAssignDestination();
                  var15 = new bwk.K(var9, var6);

                  for (long var11 : var18.getDefUses(var8.getOffset(), var9.getId())) {
                     IDInstruction var13 = (IDInstruction)var1.getCfg().getInstructionAt(var11);
                     if (!var13.visitInstruction(var15, true)) {
                        Object[] var24 = new Object[]{var8};
                        return false;
                     }
                  }
               }
            }

            var14 = var6;
         }
      }

      return true;
   }

   private boolean pC(IDExpression var1) {
      return var1 != null && (var1.equalsEx(this.fI, false) || var1.equalsEx(this.WR, false));
   }

   private boolean pC(IDExpression var1, IDExpression var2) {
      if (var1 instanceof IDOperation var3) {
         if (var3.getOperatorType() == JavaOperatorType.REM
            && var3.getOperand1().equalsEx(var2, false)
            && var3.getOperand2().isConstantImm()
            && var3.getOperand2().asImm().getRawValue() == 2L) {
            return true;
         }

         if (var3.getOperatorType() == JavaOperatorType.AND
            && var3.getOperand1().equalsEx(var2, false)
            && var3.getOperand2().isConstantImm()
            && var3.getOperand2().asImm().getRawValue() == 1L) {
            return true;
         }
      }

      return false;
   }

   private int pC(IDMethodContext var1, IDInstruction var2, IDExpression var3) {
      bwk.Av var4 = new bwk.Av();
      if (!var3.visitDepthPost(var4)) {
         Object[] var26 = new Object[0];
         return -1;
      } else {
         if (!(var3 instanceof IDImm)) {
            DCopyOptions var5 = new DCopyOptions();
            if (var4.pC != null) {
               IDFA var6 = var1.getCfg().doDataFlowAnalysis(true);

               for (IDVar var8 : var4.pC) {
                  Integer var9 = null;
                  Collection var10 = var6.getUseDefs(var2.getOffset(), var8.getId());

                  for (long var12 : var10) {
                     if (var12 < 0L) {
                        Object[] var10000 = new Object[]{var8, var2};
                        if (var10.size() == 1) {
                           var10000 = new Object[]{var8, var2};
                           return -1;
                        }
                     } else {
                        IDInstruction var14 = (IDInstruction)var1.getCfg().getInstruction(var12);
                        if (!var14.isAssignToVar(var8.getId())) {
                           Object[] var23 = new Object[]{var8, var2};
                           return -1;
                        }

                        IDExpression var15 = var14.getAssignSource();
                        int var16 = this.pC(var1, var14, var15);
                        if (var16 == -1) {
                           Object[] var22 = new Object[]{var8, var2};
                           return -1;
                        }

                        if (var9 == null) {
                           var9 = var16;
                        } else if (var9 != var16) {
                           if (var4.pC.size() >= 2) {
                              int var17 = this.pC(var1, var2, var3, var4.pC);
                              if (var17 < 0) {
                                 Object[] var21 = new Object[]{var8, var2};
                                 return -1;
                              }

                              return var17;
                           }

                           Object[] var20 = new Object[]{var8, var2};
                           return -1;
                        }
                     }
                  }

                  if (var9 == null) {
                     Object[] var24 = new Object[]{var8, var2};
                     return -1;
                  }

                  var5.replmap_id.put(var8, this.wS.createInt(var9));
               }
            }

            var5.replmap_eq.put(this.fI, this.wS.createInt(0));
            var5.replmap_eq.put(this.WR, this.wS.createInt(1));
            var3 = var3.copy(var5);
         }

         try {
            return (int)(var3.evaluate(var1).toLong() & 1L);
         } catch (Exception var18) {
            Object[] var25 = new Object[0];
            return -1;
         }
      }
   }

   private int pC(IDMethodContext var1, IDInstruction var2, IDExpression var3, List var4) {
      if (var4.size() < 2) {
         return -1;
      } else {
         CFG var5 = var1.getCfg();
         Couple var6 = var5.getInstructionLocation(var2.getOffset());
         if (var6 != null && (Integer)var6.getSecond() == 0) {
            BasicBlock var7 = (BasicBlock)var6.getFirst();
            if (var7.insize() < 2) {
               return -1;
            } else {
               IDFA var8 = var5.doDataFlowAnalysis();
               Integer var9 = null;

               for (BasicBlock var11 : var7.getInputs()) {
                  DCopyOptions var12 = new DCopyOptions();

                  for (IDVar var14 : var4) {
                     Collection var15 = var8.getReachChains(var11, var11.size(), var14.getId(), 2);
                     if (var15.size() != 1) {
                        return -1;
                     }

                     long var16 = (Long)var15.iterator().next();
                     IDInstruction var18 = (IDInstruction)var1.getCfg().getInstruction(var16);
                     if (!var18.isAssignToVar(var14.getId())) {
                        return -1;
                     }

                     IDExpression var19 = var18.getAssignSource();
                     int var20 = this.pC(var1, var18, var19);
                     if (var20 == -1) {
                        return -1;
                     }

                     var12.replmap_id.put(var14, this.wS.createInt(var20));
                  }

                  var12.replmap_eq.put(this.fI, this.wS.createInt(0));
                  var12.replmap_eq.put(this.WR, this.wS.createInt(1));
                  var3 = var3.copy(var12);

                  try {
                     int var22 = (int)(var3.evaluate(var1).toLong() & 1L);
                     if (var9 == null) {
                        var9 = var22;
                     } else if (var22 != var9) {
                        return -1;
                     }
                  } catch (Exception var21) {
                     return -1;
                  }
               }

               return var9 == null ? -1 : var9;
            }
         } else {
            return -1;
         }
      }
   }

   private int pC(IDOperation var1) {
      switch (var1.getOperatorType()) {
         case ADD:
         case SUB:
         case AND:
         case OR:
         case XOR:
         case NOT:
         case SHL:
         case NEG:
            return 1;
         case REM:
            if (var1.getOperand2().isConstantImm()) {
               int var3 = (int)var1.getOperand2().asImm().getRawValue();
               switch (var3) {
                  case 2:
                  case 4:
                  case 8:
                  case 16:
                  case 32:
                  case 64:
                  case 128:
                     return 1;
               }
            }
            break;
         case EQ:
         case NE:
            if (var1.getOperand2().isConstantImm()) {
               int var2 = (int)var1.getOperand2().asImm().getRawValue();
               if (var2 == 0 || var2 == 1) {
                  return 2;
               }
            }
      }

      return 0;
   }

   int UT() {
      if (this.gp) {
         this.fI.getNativeField(this.wS).setName(this.fI.getFieldname() + bwt.UT);
         this.WR.getNativeField(this.wS).setName(this.WR.getFieldname() + bwt.E);
      }

      if (this.ld) {
         bwk.Sv var1 = pC(this.wS.getDecompiler());
         IDexField var2 = this.fI.getNativeField(this.wS);
         var1.pC.add(var2.getSignature(false));
         bpl.pC(this.wS, var2, S.L("Used for control-flow obfuscation"));
         IDexField var3 = this.WR.getNativeField(this.wS);
         var1.A.add(var3.getSignature(false));
         bpl.pC(this.wS, var3, S.L("Used for control-flow obfuscation"));
      }

      int var5 = 0;

      for (IDMethodContext var7 : this.E) {
         int var4 = this.kS(var7);
         if (var4 > 0) {
            this.NS.put(var7, var4);
         }

         var5 += var4;
      }

      return var5;
   }

   private int kS(IDMethodContext var1) {
      int var2 = 0;
      bwm var3 = new bwm(this);

      for (IDInstruction var5 : var1.getCfg().instructions()) {
         var5.visitInstructionPostOrder(var3, true);
      }

      var2 += var3.pC;
      if (this.oT && var3.pC > 0) {
         IDMasterOptimizer var11 = this.wS.createMasterOptimizer(var1);
         var11.setSafeMode(true);
         var11.setPolicyForOptimizerTag("inliner", false);
         var11.setPolicyForOptimizerTag("slow", false);
         var2 += var11.perform();
      }

      int var12 = 0;

      for (IDInstruction var6 : var1.getCfg().instructions()) {
         if (var6.isAssign()
            && var6.getAssignDestination() instanceof IDStaticField var7
            && this.pC((IDExpression)var7)
            && !var6.getAssignSource().hasSideEffects(var1, true)) {
            var6.transformToNop();
            var12++;
         }
      }

      var2 += var12;
      if (this.oT && var12 > 0) {
         IDMasterOptimizer var14 = this.wS.createMasterOptimizer(var1);
         var14.setSafeMode(true);
         var14.setPolicyForOptimizerTag("inliner", false);
         var14.setPolicyForOptimizerTag("slow", false);
         var2 += var14.perform();
      }

      if (var2 > 0) {
         var1.getCfg().invalidateDataFlowAnalysis();
      }

      return var2;
   }

   public static bwk.Sv pC(IDexDecompilerUnit var0) {
      return pC(var0, true);
   }

   public static bwk.Sv pC(IDexDecompilerUnit var0, boolean var1) {
      String var2 = ckx.pC(new byte[]{40, 10, 9, 58, 52, 38, 40, 12, 16, 101, 94, 6, 71, 103, 68, 88, 75, 87, 95, 111, 80, 90, 87, 86, 88, 18}, 2, 21);
      Object var3 = var0.getData(var2);
      if (var3 == null && var1) {
         synchronized (var0) {
            if (var3 == null) {
               var3 = new Object[]{new ConcurrentHashSet(), new ConcurrentHashSet()};
               var0.setData(var2, var3, true);
            }
         }
      }

      return var3 == null ? null : new bwk.Sv(var3);
   }

   private class Av implements IDVisitor {
      List pC;

      public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 instanceof IDVar var4) {
            if (this.pC == null) {
               this.pC = new ArrayList();
            }

            if (!this.pC.contains(var4)) {
               this.pC.add(var4);
            }
         } else if (var1 instanceof IDStaticField) {
            if (!bwk.this.pC(var1)) {
               Object[] var10000 = new Object[]{var1};
               var3.interrupt(false);
               return;
            }
         } else if (var1 instanceof IDOperation var5) {
            if (bwk.this.pC(var5) == 0) {
               Object[] var6 = new Object[]{var1};
               var3.interrupt(false);
               return;
            }
         } else if (!(var1 instanceof IDImm)) {
            Object[] var7 = new Object[]{var1};
            var3.interrupt(false);
            return;
         }
      }
   }

   private class K implements IDVisitor {
      IDExpression pC;
      Set A;

      K(IDExpression var2, Set var3) {
         this.pC = var2;
         this.A = var3;
      }

      public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (this.pC == null && bwk.this.pC(var1) || this.pC != null && this.pC.equalsEx(var1, false)) {
            if (bwk.this.pC(var2, var1)) {
               return;
            }

            Iterator var4 = var3.parentsIterator();
            IDExpression var5 = var1;

            while (var4.hasNext()) {
               IDExpression var6 = var5;
               var5 = (IDExpression)var4.next();
               if (var5 instanceof IDOperation var8) {
                  if (var8.isConditional() && var8.getCondPredicate() == var6) {
                     break;
                  }

                  int var9 = bwk.this.pC(var8);
                  if (var9 == 0) {
                     var3.interrupt(false);
                     Object[] var10000 = new Object[]{var1};
                     return;
                  }

                  if (var9 != 1) {
                     Assert.a(var9 == 2);
                     break;
                  }
               } else {
                  if (!(var5 instanceof IDInstruction var7)) {
                     var3.interrupt(false);
                     Object[] var13 = new Object[]{var1};
                     return;
                  }

                  switch (var7.getOpcode()) {
                     case IR_ASSIGN:
                        IDExpression var10 = var7.getAssignDestination();
                        if (!bwk.this.pC(var10)) {
                           if (!(var10 instanceof IDVar)) {
                              var3.interrupt(false);
                              Object[] var12 = new Object[]{var1};
                              return;
                           }

                           this.A.add(var7);
                        }
                     case IR_JCOND:
                        break;
                     default:
                        var3.interrupt(false);
                        Object[] var11 = new Object[]{var1};
                        return;
                  }
               }
            }
         }
      }
   }

   public static class Sv {
      final Set pC;
      final Set A;

      Sv() {
         this.pC = new ConcurrentHashSet();
         this.A = new ConcurrentHashSet();
      }

      Sv(Object var1) {
         this.pC = (Set)((Object[])var1)[0];
         this.A = (Set)((Object[])var1)[1];
      }
   }
}
