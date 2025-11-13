package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEBranchDetails;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IMergeController;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.SPDC;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemoryShim;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.collections4.CollectionUtils;

public class ana {
   private static final StructuredLogger pC = aco.pC(ana.class);
   private IEConverter A;
   private IERoutineContext kS;
   private CFG wS;
   private int UT;
   private IEVar E;
   private int sY;
   private int ys;
   private final long ld;
   private Long gp;
   private Long oT;
   private Long fI;
   private int WR;
   private Collection NS = new LinkedHashSet();
   private Collection vP = new LinkedHashSet();
   private Collection xC = new LinkedHashSet();
   private boolean ED;
   private Integer Sc;

   public ana(IERoutineContext var1, boolean var2) {
      this.A = var1.getConverter();
      this.kS = var1;
      this.wS = var1.getCfg();
      this.UT = var1.getProgramCounterId();
      this.E = var1.getStackPointer();
      this.sY = this.E.getId();
      this.ys = this.E.getBitsize();
      this.ld = 2113929216L;
      this.gp = this.ld;
      this.oT = this.gp;
      if (var2) {
         this.fI = this.gp;
      }
   }

   public SPDC pC() {
      this.UT();
      return this.oT == null ? SPDC.Unknown : new SPDC((int)(this.oT - this.ld), this.WR, 20);
   }

   public SPDC A() {
      this.UT();
      return this.fI == null ? SPDC.Unknown : new SPDC((int)(this.fI - this.ld), this.WR, 20);
   }

   private void UT() {
      if (this.Sc == null) {
         throw new IllegalStateException("Stack analysis has not been performed yet!");
      }
   }

   private Map pC(Map var1, int var2, int var3) {
      Boolean var4 = (Boolean)var1.get(var3);
      if (var4 == null) {
         var4 = false;
      }

      HashMap var5 = new HashMap(var1);
      var5.put(var2, var4);
      return var5;
   }

   private Map pC(Map var1, int var2, boolean var3) {
      Boolean var4 = (Boolean)var1.get(var2);
      if (var4 == null) {
         var4 = false;
      }

      var4 = var4 && var3;
      HashMap var5 = new HashMap(var1);
      var5.put(var2, var4);
      return var5;
   }

   public int kS() {
      if (this.Sc != null) {
         throw new IllegalStateException("Already performed");
      } else {
         boolean var1 = false;

         for (IEStatement var3 : this.wS.instructions()) {
            if (var3.examine(var1x -> var1x == this.E)) {
               var1 = true;
               break;
            }
         }

         if (!var1) {
            this.Sc = 0;
            return this.Sc;
         } else {
            if (this.kS.usesCopyVars()) {
               for (IEStatement var39 : this.wS.instructions()) {
                  if (var39 instanceof IEAssign && ((IEAssign)var39).isRoutineCall()) {
                     throw new IllegalStateException("Illegal! CFG contains PC-assign-call-to-sub AND uses EVar-copy-regs");
                  }
               }
            }

            IdentityHashMap var38 = new IdentityHashMap(this.wS.size());

            for (BasicBlock var4 : this.wS.getBlocks()) {
               ana.Sv var5 = new ana.Sv(this.wS, var4);
               var38.put(var4, var5);
            }

            BasicBlock var41 = this.wS.getEntryBlock();
            Assert.a(var41.getFirstAddress() == 0L);
            IVirtualMemoryShim var43 = VirtualMemoryUtil.getCopyOnWriteShim(this.kS.getGlobalContext().getNativeMemory());
            EState var44 = this.kS.buildEmptyState();
            var44.setMemory(var43);
            long var6 = this.kS.getRoutine().getMemoryAddress();
            this.kS.getConverter().initializeStateRegisters(var44, var6);
            var44.getVariables();
            var44.setValue(this.E, this.gp);
            VirtualMemoryUtil.allocateFillGaps(var43, (this.gp & -4096L) - 65536L, 131072, 3);
            Set var9 = new HashSet();
            var9.add(this.sY);
            Map var10 = new HashMap();
            var10.put(this.sY, true);
            ana.Sv var11 = (ana.Sv)var38.get(var41);
            var11.kS = var44;
            var11.UT = var9;
            var11.sY = var10;
            ArrayList var12 = new ArrayList();
            var12.add(var41);
            ArrayList var13 = new ArrayList();
            ArrayList var14 = new ArrayList();
            TreeSet var15 = new TreeSet();
            TreeSet var16 = new TreeSet();
            this.WR = 30;
            int var17 = this.wS.size() * 50;
            int var18 = 0;

            while (!var12.isEmpty()) {
               acj.pC();
               if (++var18 > var17) {
                  break;
               }

               var41 = (BasicBlock)var12.remove(0);
               ana.Sv var19 = (ana.Sv)var38.get(var41);
               if (var19.kS == null) {
                  throw new RuntimeException("Illegal input tracker for block: " + var41);
               }

               var44 = new EState(var19.kS, false);
               Object var8 = var44.getVariables();
               var9 = var19.UT;
               var10 = var19.sY;
               boolean var20 = false;
               boolean var21 = var19.E == null;
               ArrayList var22 = new ArrayList();
               ArrayList var23 = new ArrayList();
               ArrayList var24 = new ArrayList();

               for (int var25 = 0; var25 < var41.size(); var25++) {
                  var22.add(var9);
                  var23.add(var8);
                  var24.add(var10);
                  IEStatement var26 = (IEStatement)var41.get(var25);
                  var13.clear();
                  var14.clear();
                  var26.getDefUse(var13, var14);
                  this.pC(var26, var9, var44);
                  Integer var28 = null;
                  Integer var29 = null;
                  Integer var30 = null;
                  boolean var31 = false;
                  if (var26 instanceof IECall || var26 instanceof IEAssign && ((IEAssign)var26).isRoutineCall()) {
                     int var57;
                     boolean var59;
                     if (var26 instanceof IECall var61) {
                        if (Boolean.TRUE.equals(var61.getNonReturning())) {
                           var20 = true;
                        } else {
                           INativeMethodItem var35 = var61.getStaticCallsite();
                           if (var35 != null && var35.getPrototype() != null && var35.getPrototype().isNoReturn()) {
                              var20 = true;
                           }
                        }

                        var57 = var61.getStackPointerDeltaAfterExecution();
                        var59 = var61.isStaticCallsite() && !var61.isFailsafePrototype();
                     } else {
                        IEBranchDetails var62 = ((IEAssign)var26).getBranchDetails();
                        if (var62 == null) {
                           var57 = this.kS.getConverter().getDefaultBranchToRoutineSideEffects(null).getStackPointerDeltaValue();
                           this.WR = 0;
                        } else {
                           SPDC var64 = var62.getStackPointerDelta();
                           var57 = var64.getValue();
                           this.WR = Math.min(this.WR, var64.getGuarantee());
                        }

                        var59 = this.WR == 30;
                     }

                     var8 = new HashMap((Map)var8);
                     var44.setVariables((Map)var8);
                     if (!var44.hasValue(this.sY)) {
                        this.vP.add(var41.getAddressOfInstruction(var25));
                     } else {
                        IEImm var63 = var44.getValue(this.E);
                        var44.setValue(this.E, var63._add(EUtil.imm(var57, this.ys)));
                     }

                     var31 = true;
                     var10 = this.pC(var10, this.sY, var59);
                  } else if (var26 instanceof IEAssign var32 && var32.getDstOperand() instanceof IEVar) {
                     int var33 = ((IEVar)var32.getDstOperand()).getId();
                     if (var32.getSrcOperand() instanceof IEVar && var9.contains(((IEVar)var32.getSrcOperand()).getId())) {
                        var29 = ((IEVar)var32.getSrcOperand()).getId();
                        var28 = var33;
                     } else if (EUtil.getOperation(var32.getSrcOperand(), OperationType.ADD, OperationType.SUB) != null) {
                        IEOperation var34 = (IEOperation)var32.getSrcOperand();
                        if (var34.getOperand1() instanceof IEVar && var9.contains(((IEVar)var34.getOperand1()).getId())) {
                           var29 = ((IEVar)var34.getOperand1()).getId();
                           var28 = var33;
                        }
                     }

                     if (var28 == null && var33 != this.sY && var9.contains(var33)) {
                        var30 = var33;
                     }
                  }

                  boolean var27;
                  if (!var31 && (var21 || var28 != null)) {
                     var8 = new HashMap((Map)var8);
                     var44.setVariables((Map)var8);

                     try {
                        var26.evaluate(var44);
                        var27 = true;
                        if (var28 != null) {
                           var10 = this.pC(var10, var28, var29);
                        }
                     } catch (Exception var36) {
                        this.WR = Math.min(this.WR, 10);
                        var27 = false;
                        if (var28 != null) {
                           this.NS.add(var41.getAddressOfInstruction(var25));
                        }
                     }
                  } else {
                     if (var26 instanceof IEUntranslatedInstruction) {
                        this.WR = Math.min(this.WR, 10);
                     }

                     var27 = false;
                  }

                  if (!var27 && !CollectionUtils.intersection(var9, var13).isEmpty()) {
                     var8 = new HashMap((Map)var8);
                     var44.setVariables((Map)var8);
                     var9 = new HashSet(var9);
                     var10 = new HashMap(var10);

                     for (int var60 : var13) {
                        if (var60 != this.sY && var60 != this.UT) {
                           var9.remove(var60);
                           var10.remove(var60);
                           var44.setValue(var60, 3735929054L);
                        }
                     }
                  }

                  if (var28 != null && !var9.contains(var28)) {
                     var9 = new HashSet(var9);
                     var9.add(var28);
                  } else if (var30 != null && var9.contains(var30)) {
                     var9 = new HashSet(var9);
                     var9.remove(var30);
                  }
               }

               var41.setData("preExecs", var23);
               var41.setData("preCertainties", var24);
               var41.setData("stkDeltas", var22);
               var19.wS = new EState(var44, false);
               var19.wS.removeValue(this.UT);
               var19.wS.setRoutineContext(var19.kS.getRoutineContext());
               var19.E = new HashSet(var9);
               var19.ys = new HashMap(var10);
               if (var20) {
                  Object[] var10000 = new Object[0];
               } else {
                  for (BasicBlock var53 : var41.getOutputs()) {
                     ana.Sv var54 = (ana.Sv)var38.get(var53);
                     if (var54.kS == null) {
                        var54.kS = new EState(var19.wS, false);
                        Long var55 = ((IEStatement)var53.get(0)).getPrimaryLowerLevelAddress();
                        if (var55 != null) {
                           var54.kS.setValue(this.UT, var55);
                        }

                        var54.UT = new HashSet(var19.E);
                        var54.sY = new HashMap(var19.ys);
                        if (!var12.contains(var53)) {
                           var12.add(var53);
                        }
                     } else {
                        var15.clear();
                        var16.clear();
                        int var56 = var54.kS.mergeWith(var19.wS, new ana.Av(var19, var54), var15, var16);
                        if (var56 > 0) {
                           var12.remove(var53);
                           var12.add(0, var53);
                        }

                        if (!var15.isEmpty() && CollectionUtil.hasIntersection(var9, var15)) {
                           this.xC.add(var53.getFirstAddress());
                        }
                     }
                  }
               }
            }

            this.oT = null;

            for (BasicBlock var49 : this.wS.getExitBlocks()) {
               EState var50 = ((ana.Sv)var38.get(var49)).wS;
               if (var50 != null && var50.hasValue(this.sY)) {
                  long var51 = var50.getValue(this.E).getValueAsLong();
                  if (this.oT == null) {
                     this.oT = var51;
                  } else if (this.oT != var51) {
                     this.ED = true;
                     if (var51 > this.oT) {
                        this.oT = var51;
                     }
                  }
               } else {
                  this.ED = true;
               }
            }

            this.Sc = 0;
            if (!this.NS.isEmpty()) {
               this.Sc = this.Sc | 1;
            }

            if (!this.vP.isEmpty()) {
               this.Sc = this.Sc | 2;
            }

            if (!this.xC.isEmpty()) {
               this.Sc = this.Sc | 4;
            }

            if (this.ED) {
               this.Sc = this.Sc | 8;
            }

            return this.Sc;
         }
      }
   }

   private void pC(IEStatement var1, Set var2, EState var3) {
      if (this.fI != null) {
         var1.visitDepthPost(new anb(this, var2, var3));
      }
   }

   public int wS() {
      this.UT();
      int var1 = 0;
      IEVar var2 = this.kS.getStackPointer();
      IEVar var3 = this.kS.createVar("SP0", var2.getBitsize());
      Assert.a(var3 != null);

      for (BasicBlock var5 : this.wS.getBlocks()) {
         List var6 = (List)var5.getData("stkDeltas");
         if (var6 != null) {
            List var7 = (List)var5.getData("preExecs");
            if (var7 != null) {
               int var8 = 0;

               for (IEStatement var10 : var5) {
                  Set var11 = (Set)var6.get(var8);
                  Map var12 = (Map)var7.get(var8);

                  for (int var14 : var11) {
                     IEVar var15 = this.kS.getVariableById(var14);
                     if (var15.getBitsize() == var3.getBitsize() && var12.get(var14) != null) {
                        long var16 = ((IEImm)var12.get(var14)).getValueAsLong();
                        var16 -= this.ld;
                        Object var18;
                        if (var16 == 0L) {
                           var18 = var3;
                        } else if (var16 > 0L) {
                           IEImm var19 = this.kS.createImm(var16, var3.getBitsize());
                           var18 = this.kS.createOperation(OperationType.ADD, var3, var19);
                        } else {
                           IEImm var21 = this.kS.createImm(-var16, var3.getBitsize());
                           var18 = this.kS.createOperation(OperationType.SUB, var3, var21);
                        }

                        int var22 = var10.replaceVar(var15, (IEGeneric)var18, true);
                        if (var22 > 0) {
                           var1 += var22;
                        }
                     }
                  }

                  var8++;
               }
            }
         }
      }

      if (var1 > 0) {
         this.wS.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   private class Av implements IMergeController {
      ana.Sv pC;
      ana.Sv A;

      public Av(ana.Sv var2, ana.Sv var3) {
         this.pC = var2;
         this.A = var3;
      }

      @Override
      public void onMatch(int var1) {
         boolean var2 = this.A.pC(var1);
         boolean var3 = this.pC.A(var1);
         if ((!var2 || !var3) && (var2 || var3)) {
            this.A.sY.put(var1, true);
            Object[] var10000 = new Object[]{ana.this.kS.getVariableById(var1)};
         }
      }

      @Override
      public boolean onMismatch(int var1) {
         boolean var2 = this.A.pC(var1);
         boolean var3 = this.pC.A(var1);
         if ((!var2 || !var3) && (var2 || var3)) {
            this.A.sY.remove(var1);
            boolean var4 = false;
            if (!var2) {
               this.A.kS.setValue(ana.this.kS.getVariableById(var1), this.pC.wS.getValue(var1));
               var4 = true;
            }

            Object[] var5 = new Object[]{ana.this.kS.getVariableById(var1), var4};
            return false;
         } else {
            this.A.sY.remove(var1);
            this.A.UT.remove(var1);
            Object[] var10000 = new Object[]{ana.this.kS.getVariableById(var1)};
            return true;
         }
      }

      @Override
      public boolean onOutputOnly(int var1) {
         return false;
      }

      @Override
      public boolean onInputOnly(int var1) {
         this.A.sY.remove(var1);
         this.A.UT.remove(var1);
         return true;
      }
   }

   private static class Sv extends amm {
      Set UT;
      Set E;
      Map sY;
      Map ys;

      Sv(CFG var1, BasicBlock var2) {
         super(var1, var2);
      }

      boolean pC(int var1) {
         Boolean var2 = (Boolean)this.sY.get(var1);
         return var2 == null ? false : var2;
      }

      boolean A(int var1) {
         Boolean var2 = (Boolean)this.ys.get(var1);
         return var2 == null ? false : var2;
      }
   }
}
