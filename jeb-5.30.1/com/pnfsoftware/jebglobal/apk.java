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
import com.pnfsoftware.jeb.util.format.Strings;
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

public class apk {
   private static final StructuredLogger za = aeg.q(apk.class);
   public static final int q = 1;
   public static final int RF = 2;
   public static final int xK = 4;
   public static final int Dw = 8;
   public static final String Uv = "SP0";
   private static final String lm = "preExecs";
   private static final String zz = "preCertainties";
   private static final String JY = "stkDeltas";
   IEConverter oW;
   IERoutineContext gO;
   CFG nf;
   CFG gP;
   private int HF;
   private IEVar LK;
   private int io;
   private int qa;
   private final long Hk;
   private Long Me;
   private Long PV;
   private Long oQ;
   private int xW;
   private Collection KT = new LinkedHashSet();
   private Collection Gf = new LinkedHashSet();
   private Collection Ef = new LinkedHashSet();
   private boolean cC;
   private Integer sH;

   public apk(IERoutineContext var1, boolean var2) {
      this.oW = var1.getConverter();
      this.gO = var1;
      this.nf = var1.getCfg();
      this.HF = var1.getProgramCounterId();
      this.LK = var1.getStackPointer();
      this.io = this.LK.getId();
      this.qa = this.LK.getBitsize();
      this.Hk = 2113929216L;
      this.Me = this.Hk;
      this.PV = this.Me;
      if (var2) {
         this.oQ = this.Me;
      }
   }

   public apk(IERoutineContext var1) {
      this(var1, false);
   }

   public SPDC q() {
      this.zz();
      return this.PV == null ? SPDC.Unknown : new SPDC((int)(this.PV - this.Hk), this.xW, 20);
   }

   public SPDC RF() {
      this.zz();
      return this.oQ == null ? SPDC.Unknown : new SPDC((int)(this.oQ - this.Hk), this.xW, 20);
   }

   public Collection xK() {
      this.zz();
      return this.KT;
   }

   public Collection Dw() {
      this.zz();
      return this.Gf;
   }

   public boolean Uv() {
      this.zz();
      return this.cC;
   }

   public Collection oW() {
      this.zz();
      return this.Ef;
   }

   public int gO() {
      this.zz();
      return this.sH;
   }

   private void zz() {
      if (this.sH == null) {
         throw new IllegalStateException("Stack analysis has not been performed yet!");
      }
   }

   private Map q(Map var1, int var2, int var3) {
      Boolean var4 = (Boolean)var1.get(var3);
      if (var4 == null) {
         var4 = false;
      }

      HashMap var5 = new HashMap(var1);
      var5.put(var2, var4);
      return var5;
   }

   private Map q(Map var1, int var2, boolean var3) {
      Boolean var4 = (Boolean)var1.get(var2);
      if (var4 == null) {
         var4 = false;
      }

      var4 = var4 && var3;
      HashMap var5 = new HashMap(var1);
      var5.put(var2, var4);
      return var5;
   }

   public int nf() {
      if (this.sH != null) {
         throw new IllegalStateException("Already performed");
      } else {
         boolean var1 = false;

         for (IEStatement var3 : this.nf.instructions()) {
            if (var3.examine(var1x -> var1x == this.LK)) {
               var1 = true;
               break;
            }
         }

         if (!var1) {
            this.sH = 0;
            return this.sH;
         } else {
            if (this.gO.usesCopyVars()) {
               for (IEStatement var39 : this.nf.instructions()) {
                  if (var39 instanceof IEAssign && ((IEAssign)var39).isRoutineCall()) {
                     throw new IllegalStateException("Illegal! CFG contains PC-assign-call-to-sub AND uses EVar-copy-regs");
                  }
               }
            }

            IdentityHashMap var38 = new IdentityHashMap(this.nf.size());

            for (BasicBlock var4 : this.nf.getBlocks()) {
               apk.CU var5 = new apk.CU(this.nf, var4);
               var38.put(var4, var5);
            }

            BasicBlock var41 = this.nf.getEntryBlock();
            Assert.a(var41.getFirstAddress() == 0L);
            IVirtualMemoryShim var43 = VirtualMemoryUtil.getCopyOnWriteShim(this.gO.getGlobalContext().getNativeMemory());
            EState var44 = this.gO.buildEmptyState();
            var44.setMemory(var43);
            long var6 = this.gO.getRoutine().getMemoryAddress();
            this.gO.getConverter().initializeStateRegisters(var44, var6);
            var44.getVariables();
            var44.setValue(this.LK, this.Me);
            VirtualMemoryUtil.allocateFillGaps(var43, (this.Me & -4096L) - 65536L, 131072, 3);
            Set var9 = new HashSet();
            var9.add(this.io);
            Map var10 = new HashMap();
            var10.put(this.io, true);
            apk.CU var11 = (apk.CU)var38.get(var41);
            var11.xK = var44;
            var11.Uv = var9;
            var11.gO = var10;
            ArrayList var12 = new ArrayList();
            var12.add(var41);
            ArrayList var13 = new ArrayList();
            ArrayList var14 = new ArrayList();
            TreeSet var15 = new TreeSet();
            TreeSet var16 = new TreeSet();
            this.xW = 30;
            int var17 = this.nf.size() * 50;
            int var18 = 0;

            while (!var12.isEmpty()) {
               aeb.q();
               if (++var18 > var17) {
                  break;
               }

               var41 = (BasicBlock)var12.remove(0);
               apk.CU var19 = (apk.CU)var38.get(var41);
               if (var19.xK == null) {
                  throw new RuntimeException("Illegal input tracker for block: " + var41);
               }

               var44 = new EState(var19.xK, false);
               Object var8 = var44.getVariables();
               var9 = var19.Uv;
               var10 = var19.gO;
               boolean var20 = false;
               boolean var21 = var19.oW == null;
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
                  this.q(var26, var9, var44);
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
                           var57 = this.gO.getConverter().getDefaultBranchToRoutineSideEffects(null).getStackPointerDeltaValue();
                           this.xW = 0;
                        } else {
                           SPDC var64 = var62.getStackPointerDelta();
                           var57 = var64.getValue();
                           this.xW = Math.min(this.xW, var64.getGuarantee());
                        }

                        var59 = this.xW == 30;
                     }

                     var8 = new HashMap((Map)var8);
                     var44.setVariables((Map)var8);
                     if (!var44.hasValue(this.io)) {
                        this.Gf.add(var41.getAddressOfInstruction(var25));
                     } else {
                        IEImm var63 = var44.getValue(this.LK);
                        var44.setValue(this.LK, var63._add(EUtil.imm(var57, this.qa)));
                     }

                     var31 = true;
                     var10 = this.q(var10, this.io, var59);
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

                     if (var28 == null && var33 != this.io && var9.contains(var33)) {
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
                           var10 = this.q(var10, var28, var29);
                        }
                     } catch (Exception var36) {
                        this.xW = Math.min(this.xW, 10);
                        var27 = false;
                        if (var28 != null) {
                           this.KT.add(var41.getAddressOfInstruction(var25));
                        }
                     }
                  } else {
                     if (var26 instanceof IEUntranslatedInstruction) {
                        this.xW = Math.min(this.xW, 10);
                     }

                     var27 = false;
                  }

                  if (!var27 && !CollectionUtils.intersection(var9, var13).isEmpty()) {
                     var8 = new HashMap((Map)var8);
                     var44.setVariables((Map)var8);
                     var9 = new HashSet(var9);
                     var10 = new HashMap(var10);

                     for (int var60 : var13) {
                        if (var60 != this.io && var60 != this.HF) {
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
               var19.Dw = new EState(var44, false);
               var19.Dw.removeValue(this.HF);
               var19.Dw.setRoutineContext(var19.xK.getRoutineContext());
               var19.oW = new HashSet(var9);
               var19.nf = new HashMap(var10);
               if (var20) {
                  Object[] var10000 = new Object[0];
               } else {
                  for (BasicBlock var53 : var41.getOutputBlocks()) {
                     apk.CU var54 = (apk.CU)var38.get(var53);
                     if (var54.xK == null) {
                        var54.xK = new EState(var19.Dw, false);
                        Long var55 = ((IEStatement)var53.get(0)).getPrimaryLowerLevelAddress();
                        if (var55 != null) {
                           var54.xK.setValue(this.HF, var55);
                        }

                        var54.Uv = new HashSet(var19.oW);
                        var54.gO = new HashMap(var19.nf);
                        if (!var12.contains(var53)) {
                           var12.add(var53);
                        }
                     } else {
                        var15.clear();
                        var16.clear();
                        int var56 = var54.xK.mergeWith(var19.Dw, new apk.eo(var19, var54), var15, var16);
                        if (var56 > 0) {
                           var12.remove(var53);
                           var12.add(0, var53);
                        }

                        if (!var15.isEmpty() && CollectionUtil.hasIntersection(var9, var15)) {
                           this.Ef.add(var53.getFirstAddress());
                        }
                     }
                  }
               }
            }

            this.PV = null;

            for (BasicBlock var49 : this.nf.getExitBlocks()) {
               EState var50 = ((apk.CU)var38.get(var49)).Dw;
               if (var50 != null && var50.hasValue(this.io)) {
                  long var51 = var50.getValue(this.LK).getValueAsLong();
                  if (this.PV == null) {
                     this.PV = var51;
                  } else if (this.PV != var51) {
                     this.cC = true;
                     if (var51 > this.PV) {
                        this.PV = var51;
                     }
                  }
               } else {
                  this.cC = true;
               }
            }

            this.sH = 0;
            if (!this.KT.isEmpty()) {
               this.sH = this.sH | 1;
            }

            if (!this.Gf.isEmpty()) {
               this.sH = this.sH | 2;
            }

            if (!this.Ef.isEmpty()) {
               this.sH = this.sH | 4;
            }

            if (this.cC) {
               this.sH = this.sH | 8;
            }

            return this.sH;
         }
      }
   }

   public String gP() {
      this.zz();
      if (this.sH == 0) {
         return "";
      } else {
         StringBuilder var1 = new StringBuilder();
         Strings.ff(var1, "Error Code: 0x%X\n", this.sH);
         if (!this.KT.isEmpty()) {
            Strings.ff(var1, "- %d bad instruction eval()\n", this.KT.size());
         }

         if (!this.Gf.isEmpty()) {
            Strings.ff(var1, "- %d missing SP after call-to-sub\n", this.Gf.size());
         }

         if (!this.Ef.isEmpty()) {
            Strings.ff(var1, "- %d SP-merger discrepancies\n", this.Ef.size());
         }

         if (this.cC) {
            var1.append("- SP-OUT discrepancy!\n");
         }

         return var1.toString();
      }
   }

   private void q(IEStatement var1, Set var2, EState var3) {
      if (this.oQ != null) {
         var1.visitDepthPost(new apl(this, var2, var3));
      }
   }

   public int za() {
      this.zz();
      int var1 = 0;

      for (BasicBlock var3 : this.nf.getBlocks()) {
         List var4 = (List)var3.getData("stkDeltas");
         if (var4 != null) {
            List var5 = (List)var3.getData("preExecs");
            if (var5 != null) {
               int var6 = 0;

               for (IEStatement var8 : var3) {
                  Set var9 = (Set)var4.get(var6);
                  Map var10 = (Map)var5.get(var6);
                  int var11 = this.io;
                  if (var9.contains(var11) && var10.get(var11) != null) {
                     int var12 = (int)(((IEImm)var10.get(var11)).getValueAsLong() - this.Hk);
                     var8.setSPDelta(var12);
                     var1++;
                  }

                  var6++;
               }
            }
         }
      }

      return var1;
   }

   public int lm() {
      this.zz();
      int var1 = 0;
      IEVar var2 = this.gO.getStackPointer();
      IEVar var3 = this.gO.createVar("SP0", var2.getBitsize());
      Assert.a(var3 != null);

      for (BasicBlock var5 : this.nf.getBlocks()) {
         List var6 = (List)var5.getData("stkDeltas");
         if (var6 != null) {
            List var7 = (List)var5.getData("preExecs");
            if (var7 != null) {
               int var8 = 0;

               for (IEStatement var10 : var5) {
                  Set var11 = (Set)var6.get(var8);
                  Map var12 = (Map)var7.get(var8);

                  for (int var14 : var11) {
                     IEVar var15 = this.gO.getVariableById(var14);
                     if (var15.getBitsize() == var3.getBitsize() && var12.get(var14) != null) {
                        long var16 = ((IEImm)var12.get(var14)).getValueAsLong();
                        var16 -= this.Hk;
                        Object var18;
                        if (var16 == 0L) {
                           var18 = var3;
                        } else if (var16 > 0L) {
                           IEImm var19 = this.gO.createImm(var16, var3.getBitsize());
                           var18 = this.gO.createOperation(OperationType.ADD, var3, var19);
                        } else {
                           IEImm var21 = this.gO.createImm(-var16, var3.getBitsize());
                           var18 = this.gO.createOperation(OperationType.SUB, var3, var21);
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
         this.nf.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   private static class CU extends aot {
      Set Uv;
      Set oW;
      Map gO;
      Map nf;

      CU(CFG var1, BasicBlock var2) {
         super(var1, var2);
      }

      boolean q(int var1) {
         Boolean var2 = (Boolean)this.gO.get(var1);
         return var2 == null ? false : var2;
      }

      boolean RF(int var1) {
         Boolean var2 = (Boolean)this.nf.get(var1);
         return var2 == null ? false : var2;
      }
   }

   private class eo implements IMergeController {
      apk.CU q;
      apk.CU RF;

      public eo(apk.CU var2, apk.CU var3) {
         this.q = var2;
         this.RF = var3;
      }

      @Override
      public void onMatch(int var1) {
         boolean var2 = this.RF.q(var1);
         boolean var3 = this.q.RF(var1);
         if ((!var2 || !var3) && (var2 || var3)) {
            this.RF.gO.put(var1, true);
            Object[] var10000 = new Object[]{apk.this.gO.getVariableById(var1)};
         }
      }

      @Override
      public boolean onMismatch(int var1) {
         boolean var2 = this.RF.q(var1);
         boolean var3 = this.q.RF(var1);
         if ((!var2 || !var3) && (var2 || var3)) {
            this.RF.gO.remove(var1);
            boolean var4 = false;
            if (!var2) {
               this.RF.xK.setValue(apk.this.gO.getVariableById(var1), this.q.Dw.getValue(var1));
               var4 = true;
            }

            Object[] var5 = new Object[]{apk.this.gO.getVariableById(var1), var4};
            return false;
         } else {
            this.RF.gO.remove(var1);
            this.RF.Uv.remove(var1);
            Object[] var10000 = new Object[]{apk.this.gO.getVariableById(var1)};
            return true;
         }
      }

      @Override
      public boolean onOutputOnly(int var1) {
         return false;
      }

      @Override
      public boolean onInputOnly(int var1) {
         this.RF.gO.remove(var1);
         this.RF.Uv.remove(var1);
         return true;
      }
   }
}
