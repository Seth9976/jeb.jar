package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDAllocObjectInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDEmuFrame;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTarget;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

public class bwd {
   private static final ILogger q = GlobalLog.getLogger(bwd.class);
   private IDGlobalContext RF;
   private IDMethodContext xK;
   private CFG Dw;
   private boolean Uv = true;

   public bwd(IDMethodContext var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.RF = var1.getGlobalContext();
         this.xK = var1;
         this.Dw = var1.getCfg();
      }
   }

   public void q(boolean var1) {
      this.Uv = var1;
   }

   public boolean q() {
      return this.Uv;
   }

   public bwd.nI RF() {
      IDState var1 = this.Uv ? this.xK() : null;

      bwd.nI var2;
      try {
         var2 = this.q(var1);
      } finally {
         if (var1 != null) {
            var1.dispose();
         }
      }

      return var2;
   }

   private bwd.nI q(IDState var1) {
      boolean var2 = var1 != null;
      IDFA var3 = this.Dw.doDataFlowAnalysis();
      TreeMap var4 = new TreeMap();

      for (BasicBlock var6 : this.Dw) {
         var4.put(var6.getBase(), new bwd.ej(var6.getBase()));
      }

      long var33 = this.Dw.getEntryAddress();
      bwd.ej var7 = (bwd.ej)var4.get(var33);
      var7.RF = new TreeMap();
      var7.xK = new TreeSet();
      if (!this.xK.isStaticMethod()) {
         IDVar var8 = (IDVar)this.xK.getParameterVariables().get(0);
         var7.xK.add(var8.getId());
      }

      ArrayDeque var34 = new ArrayDeque();
      var34.add(var33);
      IDImm var9 = this.xK.createInt(-559038737);

      while (!var34.isEmpty()) {
         long var10 = (Long)var34.remove();
         BasicBlock var12 = this.Dw.getBlockAt(var10);
         bwd.ej var13 = (bwd.ej)var4.get(var10);
         TreeMap var14 = new TreeMap(var13.RF);
         TreeMap var15 = null;
         TreeSet var16 = new TreeSet(var13.xK);
         TreeSet var17 = null;
         boolean var18 = var12.irroutsize() > 0;

         for (int var19 = 0; var19 < var12.size(); var19++) {
            IDInstruction var20 = (IDInstruction)var12.get(var19);
            if (var20.isJcond()) {
               Assert.a(var19 + 1 == var12.size());
               var13.oW = new TreeMap((SortedMap)var14);
               var13.gO = new TreeSet((SortedSet)var16);
            }

            if (var18 && var15 == null && var20.canThrow()) {
               var15 = new TreeMap((SortedMap)var14);
               var17 = new TreeSet((SortedSet)var16);
            }

            Set var21 = new bwd.CU().q(var20);
            IDVar var22 = null;
            IDImm var23 = null;
            if (var20.isStoreException()) {
               var22 = var20.getStoredExceptionVariable();
               var23 = var9;
            } else if (var20.isAssignToVar()) {
               var22 = (IDVar)var20.getAssignDestination();
               IDExpression var24 = var20.getAssignSource();
               if (var24 instanceof IDNewArrayInfo || var24 instanceof IDNewInfo || var24 instanceof IDAllocObjectInfo) {
                  var23 = var9;
               }

               if (var23 == null) {
                  if (var24 instanceof IDImm var25) {
                     var23 = var25;
                  } else if (var2) {
                     try {
                        IDImm var26 = this.q(var1, var24, var14);
                        if (!var26.isRef()) {
                           var23 = var26;
                        }
                     } catch (DexDecEvaluationException var31) {
                     }
                  }
               }
            }

            if (!var21.isEmpty()) {
               var16.addAll(var21);
               Maps.removeAll(var14, var21);
            }

            if (var22 != null && var23 != null) {
               int var47 = var22.getId();
               if (var23 == var9) {
                  var14.remove(var47);
                  var16.add(var47);
                  if (var17 != null) {
                     var15.remove(var47);
                     var17.remove(var47);
                  }
               } else {
                  var14.put(var47, var23);
                  var16.remove(var47);
                  if (var15 != null) {
                     IDImm var52 = (IDImm)var15.get(var47);
                     if (var52 != null && !var52.equals(var23)) {
                        var15.remove(var47);
                     }
                  }
               }
            } else {
               Collection var46 = var3.getInstructionAllDefs(var10);
               Maps.removeAll(var14, var46);
               var16.removeAll(var46);
               if (var15 != null) {
                  Maps.removeAll(var15, var46);
                  var17.removeAll(var46);
               }
            }

            var10 += var20.getSize();
         }

         var13.Dw = var14;
         var13.Uv = var16;
         IDVar var35 = null;
         LinkedHashMap var36 = new LinkedHashMap();
         IDInstruction var37 = (IDInstruction)var12.getLast();
         if (var37.isJcond()) {
            if (var37.getJcondCondition() instanceof IDOperation var39
               && var39.getOperator().isAnyOf(JavaOperatorType.EQ, JavaOperatorType.NE)
               && DUtil.collectUniqueVarIds(var39).size() == 1) {
               IDExpression var62 = var39.getLeft();
               IDExpression var63 = var39.getRight();
               bwd.eo var66 = new bwd.eo(var62, var63);
               if (var66.xK()) {
                  var35 = var66.q();
                  boolean var68 = var39.getOperator().is(JavaOperatorType.NE);
                  int var30 = var68 ? (int)var12.getEndAddress() : var37.getBranchTarget();
                  var36.put(var30, var66.RF());
               }
            } else if (var37.getJcondCondition() instanceof IDOperation var42
               && var42.getOperatorType() == JavaOperatorType.LOG_NOT
               && var42.getOperand2() instanceof IDVar var49) {
               var35 = var49;
               var36.put((int)var12.getEndAddress(), this.xK.createBoolean(true));
               var36.put(var37.getBranchTarget(), this.xK.createBoolean(false));
            } else if (var37.getJcondCondition() instanceof IDVar var54) {
               var35 = var54;
               var36.put((int)var12.getEndAddress(), this.xK.createBoolean(false));
               var36.put(var37.getBranchTarget(), this.xK.createBoolean(true));
            }
         } else if (var37.isSwitchOnInt()) {
            IDExpression var38 = var37.getSwitchExpression();
            if (DUtil.collectUniqueVarIds(var38).size() == 1) {
               HashSet var41 = new HashSet();

               for (int var53 : var37.getSwitchData().getCasesAsInt()) {
                  IDTarget var57 = var37.getSwitchData().getTargetForCase(var53);
                  int var27 = var57.getOffset();
                  if (!var41.contains(var27)) {
                     IDImm var28 = this.xK.createImm(var53, this.xK.getTypeFactory().getInt());
                     bwd.eo var29 = new bwd.eo(var38, var28);
                     if (var29.xK()) {
                        var35 = var29.q();
                        if (var36.put(var27, var29.RF()) != null) {
                           var36.remove(var27);
                           var41.add(var27);
                        }
                     }
                  }
               }
            }
         }

         List var40 = var12.getOutputBlocks();
         if (var2 && (var37.isJcond() || var37.isSwitch())) {
            try {
               Integer var43 = this.q(var1, var37, var14);
               if (var43 != null) {
                  var40.clear();
                  var40.add(this.Dw.getBlockAt(var43.longValue()));
               }
            } catch (DexDecEvaluationException var32) {
            }
         }

         for (BasicBlock var50 : var40) {
            long var55 = var50.getBase();
            Object var64 = var14;
            IDImm var67 = (IDImm)var36.get((int)var55);
            if (var67 != null) {
               var64 = new HashMap(var14);
               var64.put(var35.getId(), var67);
            }

            bwd.ej var69 = (bwd.ej)var4.get(var55);
            if (var69.q((Map)var64, var16)) {
               var34.add(var55);
            }
         }

         if (var15 != null) {
            for (BasicBlock var51 : var12.getIrregularOutputs()) {
               long var56 = var51.getBase();
               bwd.ej var65 = (bwd.ej)var4.get(var56);
               if (var65.q(var15, var17)) {
                  var34.add(var56);
               }
            }
         }
      }

      return new bwd.nI(var4);
   }

   private IDState xK() {
      IDState var1 = this.RF.createState();
      var1.setSubRoutineInvocationPolicy(0);
      var1.setSubRoutineInvocationPolicy(0);
      var1.setStrictClassInit(true);
      var1.enableEmulator(true);
      var1.enableSandbox(false);
      return var1;
   }

   private Integer q(IDState var1, IDInstruction var2, Map var3) throws DexDecEvaluationException {
      ((bye)var1).Uv();
      var1.setMaxIterationCount(1);
      var1.pushContext("context");
      IDEmuFrame var4 = var1.pushFrame("frame");
      var4.getVarMap().putAll(var3);
      var4.setPC((int)var2.getOffset());

      Integer var5;
      try {
         var2.evaluate(var1);
         var5 = var4.getNextPC();
      } finally {
         var1.deleteTopContext();
      }

      return var5;
   }

   private IDImm q(IDState var1, IDExpression var2, Map var3) throws DexDecEvaluationException {
      ((bye)var1).Uv();
      var1.setMaxIterationCount(1);
      var1.pushContext("context");
      IDEmuFrame var4 = var1.pushFrame("frame");
      var4.getVarMap().putAll(var3);

      IDImm var5;
      try {
         var5 = var2.evaluate(var1);
      } finally {
         var1.deleteTopContext();
      }

      return var5;
   }

   public static class CU {
      private Set q;

      public Set q(IDExpression var1) {
         if (this.q != null) {
            throw new IllegalStateException();
         } else {
            this.q = new HashSet();
            this.RF(var1);
            return this.q;
         }
      }

      private void RF(IDExpression var1) {
         if (var1 instanceof IDCallInfo var2) {
            if (var2.hasThis() && var2.getArgument(0) instanceof IDVar var5) {
               this.q.add(var5.getId());
            }
         } else if (var1 instanceof IDArrayElt var3 && var3.getArray() instanceof IDVar var13) {
            this.q.add(var13.getId());
         }

         if (var1 instanceof IDOperation var6) {
            IDExpression var8 = null;
            JavaOperatorType var11 = var6.getOperatorType();
            if (var11 == JavaOperatorType.COND_EXP) {
               var8 = var6.getCondPredicate();
            } else if (var11 == JavaOperatorType.LOG_AND || var11 == JavaOperatorType.LOG_OR) {
               var8 = var6.getLeft();
            }

            if (var8 != null) {
               this.RF(var8);
               return;
            }
         }

         ArrayList var7 = new ArrayList();
         var1.collectSubExpressions(var7);

         for (IDExpression var12 : var7) {
            this.RF(var12);
         }
      }
   }

   public static class ej {
      long q;
      SortedMap RF;
      SortedSet xK;
      SortedMap Dw;
      SortedSet Uv;
      SortedMap oW;
      SortedSet gO;

      ej(long var1) {
         this.q = var1;
      }

      public long q() {
         return this.q;
      }

      public Map RF() {
         return (Map)(this.RF == null ? Collections.emptyMap() : this.RF);
      }

      public IDImm q(int var1) {
         return (IDImm)this.RF().get(var1);
      }

      public Set xK() {
         return (Set)(this.xK == null ? Collections.emptySet() : this.xK);
      }

      public boolean RF(int var1) {
         return this.xK().contains(var1);
      }

      public Map Dw() {
         return (Map)(this.Dw == null ? Collections.emptyMap() : this.Dw);
      }

      public IDImm xK(int var1) {
         return (IDImm)this.Dw().get(var1);
      }

      public Set Uv() {
         return (Set)(this.Uv == null ? Collections.emptySet() : this.Uv);
      }

      public boolean Dw(int var1) {
         return this.Uv().contains(var1);
      }

      public Map oW() {
         return (Map)(this.oW == null ? Collections.emptyMap() : this.oW);
      }

      public IDImm Uv(int var1) {
         return (IDImm)this.oW().get(var1);
      }

      public Set gO() {
         return (Set)(this.gO == null ? Collections.emptySet() : this.gO);
      }

      public boolean oW(int var1) {
         return this.gO().contains(var1);
      }

      boolean q(Map var1, Set var2) {
         if (this.RF == null) {
            this.RF = new TreeMap(var1);
            this.xK = new TreeSet(var2);
            return true;
         } else {
            boolean var3 = false;

            for (Integer var5 : var1.keySet()) {
               if (this.RF.containsKey(var5) && !((IDImm)this.RF.get(var5)).equalsEx(var1.get(var5), false)) {
                  this.RF.remove(var5);
                  var3 = true;
               }
            }

            if (Maps.retainAll(this.RF, var1.keySet())) {
               var3 = true;
            }

            if (this.xK.retainAll(var2)) {
               var3 = true;
            }

            return var3;
         }
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         Strings.ff(var1, "Block 0x%X:", this.q);

         for (Entry var3 : this.RF().entrySet()) {
            Strings.ff(var1, " IN:%d=%s", var3.getKey(), var3.getValue());
         }

         for (Entry var7 : this.Dw().entrySet()) {
            Strings.ff(var1, " OUT:%d=%s", var7.getKey(), var7.getValue());
         }

         for (int var8 : this.xK()) {
            Strings.ff(var1, " IN-REF:%d", var8);
         }

         for (int var9 : this.Uv()) {
            Strings.ff(var1, " OUT-REF:%d", var9);
         }

         return var1.toString();
      }
   }

   public class eo {
      private IDExpression RF;
      private IDExpression xK;
      private IDVar Dw;
      private IDImm Uv;

      eo(IDExpression var2, IDExpression var3) {
         this.RF = var2;
         this.xK = var3;
      }

      public IDVar q() {
         return this.Dw;
      }

      public IDImm RF() {
         return this.Uv;
      }

      public boolean xK() {
         int var1 = DUtil.collectUniqueVarIds(this.RF).size();
         if (var1 > 1) {
            return false;
         } else {
            var1 += DUtil.collectUniqueVarIds(this.xK).size();
            if (var1 != 1) {
               return false;
            } else {
               IDImm var2;
               IDExpression var3;
               if (this.xK instanceof IDImm var5) {
                  var2 = var5;
                  var3 = this.RF;
               } else {
                  if (!(this.RF instanceof IDImm var4)) {
                     return false;
                  }

                  var2 = var4;
                  var3 = this.xK;
               }

               if (var3 instanceof IDVar var18) {
                  this.Dw = var18;
                  this.Uv = var2.asImm();
                  return true;
               } else {
                  if (var3 instanceof IDArrayElt var16 && var16.getIndex() instanceof IDVar var20) {
                     this.Dw = var20;
                     if (var16.getArray() instanceof IDNewArrayInfo var22 && var22.areSubExpsAllImms()) {
                        int var24 = -1;
                        int var9 = 0;

                        for (IDExpression var11 : var22.getInitialValues()) {
                           if (var11.asImm().equalsEx(var2, false)) {
                              if (var24 >= 0) {
                                 var24 = -1;
                                 break;
                              }

                              var24 = var9;
                           }

                           var9++;
                        }

                        if (var24 >= 0) {
                           this.Uv = bwd.this.xK.createImm(var24, this.Dw.getType());
                           return true;
                        }
                     }
                  }

                  if (var3 instanceof IDOperation var17) {
                     JavaOperatorType var21 = var17.getOperatorType();
                     if (var21 == JavaOperatorType.ADD || var21 == JavaOperatorType.SUB) {
                        boolean var23 = var21 == JavaOperatorType.ADD;
                        IDExpression var25 = var17.getLeft();
                        IDExpression var26 = var17.getRight();
                        Long var27 = null;
                        if (var25 instanceof IDVar var28 && var26 instanceof IDImm var12) {
                           this.Dw = var28;
                           if (var23) {
                              var27 = var2.getRawValue() - var12.getRawValue();
                           } else {
                              var27 = var2.getRawValue() + var12.getRawValue();
                           }
                        } else if (var25 instanceof IDImm var13 && var26 instanceof IDVar var14) {
                           this.Dw = var14;
                           if (var23) {
                              var27 = var2.getRawValue() - var13.getRawValue();
                           } else {
                              var27 = var13.getRawValue() - var2.getRawValue();
                           }
                        }

                        if (var27 != null) {
                           this.Uv = bwd.this.xK.createImm(var27, this.Dw.getType());
                           return true;
                        }
                     }
                  }

                  return false;
               }
            }
         }
      }
   }

   public static class nI {
      SortedMap q = new TreeMap();

      nI(SortedMap var1) {
         this.q = var1;
      }

      public bwd.ej q(long var1) {
         return (bwd.ej)this.q.get(var1);
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();

         for (long var3 : this.q.keySet()) {
            Strings.ff(var1, "%s\n", this.q.get(var3));
         }

         return var1.toString();
      }
   }
}
