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

public class caz {
   private static final ILogger RF = GlobalLog.getLogger(caz.class);
   private static final boolean xK = false;
   final int q = 1;
   private int Dw;
   private IDGlobalContext Uv;
   private String oW;
   private List gO;
   private boolean nf;
   private boolean gP;
   private boolean za;
   private boolean lm;
   private boolean zz;
   private IDStaticField JY;
   private IDStaticField HF;
   private Map LK = new HashMap();

   private static void q(String var0, Object... var1) {
   }

   private static void q(String var0, IDMethodContext var1) {
   }

   public caz(IDGlobalContext var1, String var2, List var3) {
      this.Uv = var1;
      this.oW = var2;
      this.gO = var3;
   }

   public void q(boolean var1) {
      this.nf = var1;
   }

   public boolean q() {
      return this.nf;
   }

   public void RF(boolean var1) {
      this.gP = var1;
   }

   public boolean RF() {
      return this.gP;
   }

   public void xK(boolean var1) {
      this.za = var1;
   }

   public boolean xK() {
      return this.lm;
   }

   public void Dw(boolean var1) {
      this.zz = var1;
   }

   public boolean Dw() {
      return this.zz;
   }

   public int Uv() {
      if (!this.gO()) {
         return 0;
      } else {
         Object[] var10000 = new Object[]{this.JY, this.HF};
         return !this.nf() ? 0 : this.gP();
      }
   }

   public Map oW() {
      return this.LK;
   }

   boolean gO() {
      for (IDMethodContext var2 : this.gO) {
         if (this.q(var2)) {
            if (this.JY != null && this.HF != null) {
               return true;
            }

            return false;
         }
      }

      return false;
   }

   private boolean q(IDMethodContext var1) {
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
                              if (this.q(var1, var15, var16, var9) && this.za()) {
                                 return true;
                              }
                           } else if (var32 instanceof IDStaticField var17 && var30 instanceof IDImm var18) {
                              if (this.q(var1, var17, var18, var9) && this.za()) {
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
                                    if (this.q(var1, var42, var20, var9) && this.za()) {
                                       return true;
                                    }
                                 } else if (var41 == var19 && var40 instanceof IDStaticField var43 && this.q(var1, var43, var20, var9) && this.za()) {
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
                                    if (this.q(var1, var36, var37, var9) && this.za()) {
                                       return true;
                                    }
                                 } else if (var35 instanceof IDStaticField var38
                                    && var34 instanceof IDImm var39
                                    && this.q(var1, var38, var39, var9)
                                    && this.za()) {
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

   private boolean za() {
      this.Dw++;
      return this.Dw >= 1;
   }

   private boolean q(IDMethodContext var1, IDStaticField var2, IDImm var3, IDStaticField var4) {
      int var5 = (int)var3.getRawValue();
      if (var5 >= 0 && var5 < 128 && (var5 & 1) == 1 && !var4.equalsEx(var2, false)) {
         String var6 = var4.getClassSignature();
         String var7 = var2.getClassSignature();
         if (var6.equals(var7) && (!this.nf || var6.equals(this.oW))) {
            IDStaticField[] var8 = new IDStaticField[]{var4, var2};
            if (this.q(var1, var8)) {
               return true;
            }
         }
      }

      return false;
   }

   private boolean q(IDMethodContext var1, IDStaticField[] var2) {
      if (this.JY != null && this.HF != null) {
         return this.JY.equalsEx(var2[0], false) && this.HF.equalsEx(var2[1], false)
            ? true
            : this.JY.equalsEx(var2[1], false) && this.HF.equalsEx(var2[0], false);
      } else {
         IDexField var3 = this.q(var2[0]);
         if (var3 == null) {
            return false;
         } else {
            IDexField var4 = this.q(var2[1]);
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
               IDMethodContext var9 = this.q(var8);
               if (var9 == null && this.gP) {
                  var9 = this.RF(var8, var1);
               }

               if (var9 != null) {
                  this.q(var9, var2, var5);
               }

               int var10 = var5[0];
               int var11 = var5[1];
               if ((var10 + var11 & 1) == 0) {
                  return false;
               } else {
                  if ((var10 & 1) == 0) {
                     this.JY = var2[0];
                     this.HF = var2[1];
                  } else {
                     this.JY = var2[1];
                     this.HF = var2[0];
                  }

                  return true;
               }
            }
         }
      }
   }

   private IDMethodContext q(String var1) {
      for (IDMethodContext var3 : this.gO) {
         if (var3.getMethodSignature().equals(var1)) {
            return var3;
         }
      }

      return null;
   }

   private IDexField q(IDStaticField var1) {
      IDexField var2 = var1.getNativeField(this.Uv);
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

   private IDMethodContext RF(String var1, IDMethodContext var2) {
      IDexMethod var3 = var2.getDex().getMethod(var1);
      if (var3 == null) {
         return null;
      } else {
         bud var4 = ((com.pnfsoftware.jeb.corei.parsers.dexdec.ej)var2.getGlobalContext().getDecompiler()).q(var3);
         var4.RF(false);
         var4.q(false);
         var4.q(var2.getWatchdog());
         var4.q(var2.getDecompilationFlags());
         var4.q(var2);

         try {
            var4.Uv();
         } catch (Exception var6) {
            com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(var6, var3.getSignature(false));
            return null;
         }

         new cen().perform(var4);
         return var4;
      }
   }

   private void q(IDMethodContext var1, IDStaticField[] var2, int[] var3) {
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

   boolean nf() {
      for (IDMethodContext var2 : this.gO) {
         if (!this.RF(var2)) {
            com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(
               new RuntimeException("Unexpected failure on OEG-cfo examination"), null, -1, Maps.toMap("csig", this.oW), true
            );
            return false;
         }
      }

      return true;
   }

   private boolean RF(IDMethodContext var1) {
      for (IDInstruction var3 : var1.getCfg().instructions()) {
         if (var3.isAssign()) {
            IDExpression var4 = var3.getAssignDestination();
            if (var4.equalsEx(this.JY, false)) {
               int var5 = this.q(var1, var3, var3.getAssignSource());
               if (var5 != 0) {
                  Object[] var10000 = new Object[]{var3};
                  return false;
               }
            } else if (var4.equalsEx(this.HF, false)) {
               int var19 = this.q(var1, var3, var3.getAssignSource());
               if (var19 != 1) {
                  Object[] var22 = new Object[]{var3};
                  return false;
               }
            }
         }
      }

      LinkedHashSet var14 = new LinkedHashSet();
      caz.nI var15 = new caz.nI(null, var14);

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
                  var15 = new caz.nI(var9, var6);

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

   private boolean q(IDExpression var1) {
      return var1 != null && (var1.equalsEx(this.JY, false) || var1.equalsEx(this.HF, false));
   }

   private boolean q(IDExpression var1, IDExpression var2) {
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

   private int q(IDMethodContext var1, IDInstruction var2, IDExpression var3) {
      caz.eo var4 = new caz.eo();
      if (!var3.visitDepthPost(var4)) {
         Object[] var26 = new Object[0];
         return -1;
      } else {
         if (!(var3 instanceof IDImm)) {
            DCopyOptions var5 = new DCopyOptions();
            if (var4.q != null) {
               IDFA var6 = var1.getCfg().doDataFlowAnalysis(true);

               for (IDVar var8 : var4.q) {
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
                        int var16 = this.q(var1, var14, var15);
                        if (var16 == -1) {
                           Object[] var22 = new Object[]{var8, var2};
                           return -1;
                        }

                        if (var9 == null) {
                           var9 = var16;
                        } else if (var9 != var16) {
                           if (var4.q.size() >= 2) {
                              int var17 = this.q(var1, var2, var3, var4.q);
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

                  var5.replmap_id.put(var8, this.Uv.createInt(var9));
               }
            }

            var5.replmap_eq.put(this.JY, this.Uv.createInt(0));
            var5.replmap_eq.put(this.HF, this.Uv.createInt(1));
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

   private int q(IDMethodContext var1, IDInstruction var2, IDExpression var3, List var4) {
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
                     int var20 = this.q(var1, var18, var19);
                     if (var20 == -1) {
                        return -1;
                     }

                     var12.replmap_id.put(var14, this.Uv.createInt(var20));
                  }

                  var12.replmap_eq.put(this.JY, this.Uv.createInt(0));
                  var12.replmap_eq.put(this.HF, this.Uv.createInt(1));
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

   private int q(IDOperation var1) {
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

   int gP() {
      if (this.lm) {
         this.JY.getNativeField(this.Uv).setName(this.JY.getFieldname() + cbi.Uv);
         this.HF.getNativeField(this.Uv).setName(this.HF.getFieldname() + cbi.oW);
      }

      if (this.za) {
         caz.CU var1 = q(this.Uv.getDecompiler());
         IDexField var2 = this.JY.getNativeField(this.Uv);
         var1.q.add(var2.getSignature(false));
         bto.q(this.Uv, var2, S.L("Used for control-flow obfuscation"));
         IDexField var3 = this.HF.getNativeField(this.Uv);
         var1.RF.add(var3.getSignature(false));
         bto.q(this.Uv, var3, S.L("Used for control-flow obfuscation"));
      }

      int var5 = 0;

      for (IDMethodContext var7 : this.gO) {
         int var4 = this.xK(var7);
         if (var4 > 0) {
            this.LK.put(var7, var4);
         }

         var5 += var4;
      }

      return var5;
   }

   private int xK(IDMethodContext var1) {
      int var2 = 0;
      cbb var3 = new cbb(this);

      for (IDInstruction var5 : var1.getCfg().instructions()) {
         var5.visitInstructionPostOrder(var3, true);
      }

      var2 += var3.q;
      if (this.zz && var3.q > 0) {
         IDMasterOptimizer var11 = this.Uv.createMasterOptimizer(var1);
         var11.setSafeMode(true);
         var11.setPolicyForOptimizerTag("inliner", false);
         var11.setPolicyForOptimizerTag("slow", false);
         var2 += var11.perform();
      }

      int var12 = 0;

      for (IDInstruction var6 : var1.getCfg().instructions()) {
         if (var6.isAssign()
            && var6.getAssignDestination() instanceof IDStaticField var7
            && this.q((IDExpression)var7)
            && !var6.getAssignSource().hasSideEffects(var1, true)) {
            var6.transformToNop();
            var12++;
         }
      }

      var2 += var12;
      if (this.zz && var12 > 0) {
         IDMasterOptimizer var14 = this.Uv.createMasterOptimizer(var1);
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

   public static caz.CU q(IDexDecompilerUnit var0) {
      return q(var0, true);
   }

   public static caz.CU q(IDexDecompilerUnit var0, boolean var1) {
      String var2 = cvm.q(new byte[]{40, 10, 9, 58, 52, 38, 40, 12, 16, 101, 94, 6, 71, 103, 68, 88, 75, 87, 95, 111, 80, 90, 87, 87, 88, 18}, 2, 157);
      Object var3 = var0.getData(var2);
      if (var3 == null && var1) {
         synchronized (var0) {
            if (var3 == null) {
               var3 = new Object[]{new ConcurrentHashSet(), new ConcurrentHashSet()};
               var0.setData(var2, var3, true);
            }
         }
      }

      return var3 == null ? null : new caz.CU(var3);
   }

   public static class CU {
      final Set q;
      final Set RF;

      CU() {
         this.q = new ConcurrentHashSet();
         this.RF = new ConcurrentHashSet();
      }

      CU(Object var1) {
         this.q = (Set)((Object[])var1)[0];
         this.RF = (Set)((Object[])var1)[1];
      }
   }

   private class eo implements IDVisitor {
      List q;

      public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 instanceof IDVar var4) {
            if (this.q == null) {
               this.q = new ArrayList();
            }

            if (!this.q.contains(var4)) {
               this.q.add(var4);
            }
         } else if (var1 instanceof IDStaticField) {
            if (!caz.this.q(var1)) {
               Object[] var10000 = new Object[]{var1};
               var3.interrupt(false);
               return;
            }
         } else if (var1 instanceof IDOperation var5) {
            if (caz.this.q(var5) == 0) {
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

   private class nI implements IDVisitor {
      IDExpression q;
      Set RF;

      nI(IDExpression var2, Set var3) {
         this.q = var2;
         this.RF = var3;
      }

      public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (this.q == null && caz.this.q(var1) || this.q != null && this.q.equalsEx(var1, false)) {
            if (caz.this.q(var2, var1)) {
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

                  int var9 = caz.this.q(var8);
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
                        if (!caz.this.q(var10)) {
                           if (!(var10 instanceof IDVar)) {
                              var3.interrupt(false);
                              Object[] var12 = new Object[]{var1};
                              return;
                           }

                           this.RF.add(var7);
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
}
