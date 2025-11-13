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
import org.apache.commons.collections4.CollectionUtils;

public class aph {
   private static final StructuredLogger za = aeg.q(aph.class);
   public static final int q = 1;
   public static final int RF = 2;
   public static final int xK = 4;
   public static final int Dw = 8;
   public static final String Uv = "SP0";
   private static final String lm = "preExecs";
   private static final String zz = "stkDeltas";
   IEConverter oW;
   IERoutineContext gO;
   CFG nf;
   CFG gP;
   private int JY;
   private IEVar HF;
   private int LK;
   private int io;
   private final long qa;
   private Long Hk;
   private Long Me;
   private Long PV;
   private int oQ;
   private Collection xW = new LinkedHashSet();
   private Collection KT = new LinkedHashSet();
   private Collection Gf = new LinkedHashSet();
   private boolean Ef;
   private Integer cC;

   public aph(IERoutineContext var1, boolean var2) {
      this.oW = var1.getConverter();
      this.gO = var1;
      this.nf = var1.getCfg();
      this.JY = var1.getProgramCounterId();
      this.HF = var1.getStackPointer();
      this.LK = this.HF.getId();
      this.io = this.HF.getBitsize();
      this.qa = 2113929216L;
      this.Hk = this.qa;
      this.Me = this.Hk;
      if (var2) {
         this.PV = this.Hk;
      }
   }

   public aph(IERoutineContext var1) {
      this(var1, false);
   }

   public SPDC q() {
      this.zz();
      return this.Me == null ? SPDC.Unknown : new SPDC((int)(this.Me - this.qa), this.oQ, 20);
   }

   public SPDC RF() {
      this.zz();
      return this.PV == null ? SPDC.Unknown : new SPDC((int)(this.PV - this.qa), this.oQ, 20);
   }

   public Collection xK() {
      this.zz();
      return this.xW;
   }

   public Collection Dw() {
      this.zz();
      return this.KT;
   }

   public boolean Uv() {
      this.zz();
      return this.Ef;
   }

   public Collection oW() {
      this.zz();
      return this.Gf;
   }

   public int gO() {
      this.zz();
      return this.cC;
   }

   private void zz() {
      if (this.cC == null) {
         throw new IllegalStateException("Stack analysis has not been performed yet!");
      }
   }

   public int nf() {
      if (this.cC != null) {
         throw new IllegalStateException("Already performed");
      } else {
         if (this.gO.usesCopyVars()) {
            for (IEStatement var2 : this.nf.instructions()) {
               if (var2 instanceof IEAssign && ((IEAssign)var2).isRoutineCall()) {
                  throw new IllegalStateException("Illegal! CFG contains PC-assign-call-to-sub AND uses EVar-copy-regs");
               }
            }
         }

         IdentityHashMap var31 = new IdentityHashMap(this.nf.size());

         for (BasicBlock var3 : this.nf.getBlocks()) {
            aph.eo var4 = new aph.eo(this.nf, var3);
            var31.put(var3, var4);
         }

         BasicBlock var33 = this.nf.getEntryBlock();
         Assert.a(var33.getFirstAddress() == 0L);
         IVirtualMemoryShim var35 = VirtualMemoryUtil.getCopyOnWriteShim(this.gO.getGlobalContext().getNativeMemory());
         EState var36 = this.gO.buildEmptyState();
         var36.setMemory(var35);
         long var5 = this.gO.getRoutine().getMemoryAddress();
         this.gO.getConverter().initializeStateRegisters(var36, var5);
         var36.getVariables();
         var36.setValue(this.HF, this.Hk);
         VirtualMemoryUtil.allocateFillGaps(var35, (this.Hk & -4096L) - 65536L, 131072, 3);
         Set var8 = new HashSet();
         var8.add(this.LK);
         aph.eo var9 = (aph.eo)var31.get(var33);
         var9.xK = var36;
         var9.oW = var8;
         ArrayList var10 = new ArrayList();
         var10.add(var33);
         ArrayList var11 = new ArrayList();
         ArrayList var12 = new ArrayList();
         ArrayList var13 = new ArrayList();
         this.oQ = 30;

         while (!var10.isEmpty()) {
            aeb.q();
            var33 = (BasicBlock)var10.remove(0);
            aph.eo var14 = (aph.eo)var31.get(var33);
            if (var14.xK == null) {
               throw new RuntimeException("Illegal input tracker for block: " + var33);
            }

            var36 = new EState(var14.xK, false);
            Object var7 = var36.getVariables();
            var8 = var14.oW;
            boolean var15 = false;
            boolean var16 = var14.gO == null;
            ArrayList var17 = new ArrayList();
            ArrayList var18 = new ArrayList();

            for (int var19 = 0; var19 < var33.size(); var19++) {
               var18.add(var8);
               var17.add(var7);
               IEStatement var20 = (IEStatement)var33.get(var19);
               var11.clear();
               var12.clear();
               var20.getDefUse(var11, var12);
               if (this.PV != null) {
                  var20.visitDepthPost(new api(this, var8, var36));
               }

               Integer var24 = null;
               Integer var25 = null;
               boolean var26 = false;
               if (!(var20 instanceof IECall) && (!(var20 instanceof IEAssign) || !((IEAssign)var20).isRoutineCall())) {
                  if (var20 instanceof IEAssign var46 && var46.getDstOperand() instanceof IEVar) {
                     int var50 = ((IEVar)var46.getDstOperand()).getId();
                     if (var46.getSrcOperand() instanceof IEVar && var8.contains(((IEVar)var46.getSrcOperand()).getId())) {
                        var24 = var50;
                     } else if (EUtil.getOperation(var46.getSrcOperand(), OperationType.ADD, OperationType.SUB) != null) {
                        IEOperation var53 = (IEOperation)var46.getSrcOperand();
                        if (var53.getOperand1() instanceof IEVar && var8.contains(((IEVar)var53.getOperand1()).getId())) {
                           var24 = var50;
                        }
                     }

                     if (var24 == null && var50 != this.LK && var8.contains(var50)) {
                        var25 = var50;
                     }
                  }
               } else {
                  int var27;
                  if (var20 instanceof IECall var28) {
                     INativeMethodItem var29 = var28.getStaticCallsite();
                     if (var29 != null && var29.getPrototype() != null && var29.getPrototype().isNoReturn()) {
                        var15 = true;
                     }

                     var27 = var28.getStackPointerDeltaAfterExecution();
                  } else {
                     IEBranchDetails var48 = ((IEAssign)var20).getBranchDetails();
                     if (var48 == null) {
                        var27 = this.gO.getConverter().getDefaultBranchToRoutineSideEffects(null).getStackPointerDeltaValue();
                        this.oQ = 0;
                     } else {
                        SPDC var52 = var48.getStackPointerDelta();
                        var27 = var52.getValue();
                        this.oQ = Math.min(this.oQ, var52.getGuarantee());
                     }
                  }

                  var7 = new HashMap((Map)var7);
                  var36.setVariables((Map)var7);
                  if (!var36.hasValue(this.LK)) {
                     this.KT.add(var33.getAddressOfInstruction(var19));
                  } else {
                     IEImm var49 = var36.getValue(this.HF);
                     var36.setValue(this.HF, var49._add(EUtil.imm(var27, this.io)));
                  }

                  var26 = true;
               }

               boolean var21;
               if (!var26 && (var16 || var24 != null)) {
                  var7 = new HashMap((Map)var7);
                  var36.setVariables((Map)var7);

                  try {
                     var20.evaluate(var36);
                     var21 = true;
                  } catch (Exception var30) {
                     this.oQ = Math.min(this.oQ, 10);
                     var21 = false;
                     if (var24 != null) {
                        this.xW.add(var33.getAddressOfInstruction(var19));
                     }
                  }
               } else {
                  if (var20 instanceof IEUntranslatedInstruction) {
                     this.oQ = Math.min(this.oQ, 10);
                  }

                  var21 = false;
               }

               if (!var21 && !CollectionUtils.intersection(var8, var11).isEmpty()) {
                  var8 = new HashSet(var8);

                  for (int var51 : var11) {
                     if (var51 != this.LK && var51 != this.JY) {
                        var8.remove(var51);
                        var36.setValue(var51, 3735929054L);
                     }
                  }
               }

               if (var24 != null && !var8.contains(var24)) {
                  var8 = new HashSet(var8);
                  var8.add(var24);
               } else if (var25 != null && var8.contains(var25)) {
                  var8 = new HashSet(var8);
                  var8.remove(var25);
               }
            }

            var33.setData("preExecs", var17);
            var33.setData("stkDeltas", var18);
            var14.Dw = new EState(var36, false);
            var14.gO = new HashSet(var8);
            if (!var15) {
               for (BasicBlock var44 : var33.getOutputBlocks()) {
                  aph.eo var45 = (aph.eo)var31.get(var44);
                  if (var45.xK == null) {
                     var45.xK = new EState(var14.Dw, false);
                     var45.oW = var14.gO;
                     if (!var10.contains(var44)) {
                        var10.add(var44);
                     }
                  } else {
                     var13.clear();
                     int var22 = var45.xK.mergeWithOld(var14.Dw, var14.Uv, false, var45.Uv, var13);
                     if (var22 > 0) {
                        var10.remove(var44);
                        var10.add(0, var44);
                     }

                     if (!var13.isEmpty()) {
                        if (CollectionUtil.hasIntersection(var8, var13)) {
                           this.Gf.add(var44.getFirstAddress());
                        }

                        var45.q(var13);
                        var45.oW.removeAll(var13);
                     }
                  }
               }
            }
         }

         this.Me = null;

         for (BasicBlock var40 : this.nf.getExitBlocks()) {
            EState var41 = ((aph.eo)var31.get(var40)).Dw;
            if (!var41.hasValue(this.LK)) {
               this.Ef = true;
            } else {
               long var42 = var41.getValue(this.HF).getValueAsLong();
               if (this.Me == null) {
                  this.Me = var42;
               } else if (this.Me != var42) {
                  this.Ef = true;
                  if (var42 > this.Me) {
                     this.Me = var42;
                  }
               }
            }
         }

         this.cC = 0;
         if (!this.xW.isEmpty()) {
            this.cC = this.cC | 1;
         }

         if (!this.KT.isEmpty()) {
            this.cC = this.cC | 2;
         }

         if (!this.Gf.isEmpty()) {
            this.cC = this.cC | 4;
         }

         if (this.Ef) {
            this.cC = this.cC | 8;
         }

         return this.cC;
      }
   }

   public String gP() {
      this.zz();
      if (this.cC == 0) {
         return "";
      } else {
         StringBuilder var1 = new StringBuilder();
         Strings.ff(var1, "Error Code: 0x%X\n", this.cC);
         if (!this.xW.isEmpty()) {
            Strings.ff(var1, "- %d bad instruction eval()\n", this.xW.size());
         }

         if (!this.KT.isEmpty()) {
            Strings.ff(var1, "- %d missing SP after call-to-sub\n", this.KT.size());
         }

         if (!this.Gf.isEmpty()) {
            Strings.ff(var1, "- %d SP-merger discrepancies\n", this.Gf.size());
         }

         if (this.Ef) {
            var1.append("- SP-OUT discrepancy!\n");
         }

         return var1.toString();
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
                  int var11 = this.LK;
                  if (var9.contains(var11) && var10.get(var11) != null) {
                     int var12 = (int)(((IEImm)var10.get(var11)).getValueAsLong() - this.qa);
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
                        var16 -= this.qa;
                        Object var18;
                        if (var16 == 0L) {
                           var18 = var3;
                        } else {
                           var18 = this.gO.createOperation(OperationType.ADD, var3, this.gO.createImm(var16, var3.getBitsize()));
                        }

                        int var19 = var10.replaceVar(var15, (IEGeneric)var18, true);
                        if (var19 > 0) {
                           var1 += var19;
                        }
                     }
                  }

                  var8++;
               }
            }
         }
      }

      return var1;
   }

   private static class eo extends aot {
      Set Uv;
      Set oW;
      Set gO;

      eo(CFG var1, BasicBlock var2) {
         super(var1, var2);
      }

      void q(List var1) {
         if (this.Uv == null) {
            this.Uv = new HashSet();
         }

         this.Uv.addAll(var1);
      }
   }
}
