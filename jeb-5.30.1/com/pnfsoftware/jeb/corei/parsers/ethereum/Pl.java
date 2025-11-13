package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.DecompilationContext;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDecompiledMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.NativeDecompilationStage;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.EExpressionMatcher;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.Node;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.O;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.Util;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.ISegment;
import com.pnfsoftware.jeb.util.collect.IdentityHashSet;
import com.pnfsoftware.jeb.util.collect.SegmentMap;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Pl {
   private static final ILogger xK = GlobalLog.getLogger(Pl.class);
   private INativeCodeUnit Dw;
   private INativeDecompilerUnit Uv;
   private ITypeManager oW;
   private IWildcardTypeManager gO;
   List q = new ArrayList();
   Map RF = new HashMap();
   private TreeMap nf = new TreeMap();
   private List gP = new ArrayList();
   private List za = new ArrayList();

   public Pl(INativeCodeUnit var1, INativeDecompilerUnit var2) {
      this.Dw = var1;
      this.Uv = var2;
      this.oW = var1.getTypeManager();
      this.gO = var2.getWildcardTypeManager();
   }

   public boolean q(INativeMethodItem var1) {
      boolean var3;
      try (ACLock var2 = this.Dw.getLock().a()) {
         var3 = this.RF(var1);
      }

      return var3;
   }

   private boolean RF(INativeMethodItem var1) {
      IDecompiledMethod var2 = this.xK(var1);
      if (var2 != null && !var2.isFailure()) {
         CFG var3 = var2.getIRContext().getCfg();
         IDFA var4 = var3.doDataFlowAnalysis();

         for (BasicBlock var6 : var3) {
            for (int var7 = 0; var7 < var6.size(); var7++) {
               IEStatement var8 = (IEStatement)var6.get(var7);
               Integer var9 = tl.q(var8, 32, 85, 84);
               if (var9 != null) {
                  IEUntranslatedInstruction var10 = (IEUntranslatedInstruction)var8;
                  if (var9 != 32) {
                     IEGeneric var11 = var10.getParameterExpression(0);
                     if (var11 instanceof IEImm) {
                        BigInteger var12 = ((IEImm)var11).getValue();
                        this.RF.put(var12, 0);
                        if (var9 == 85) {
                           IEGeneric var13 = var10.getParameterExpression(1);
                           IEGeneric var14 = null;
                           Couple var15 = null;
                           IEImm var16 = null;
                           if (!(var13 instanceof IEImm) && !(var13 instanceof IEVar)) {
                              Node var17 = Util.N(O.OR, Util.L(0), Util.N(O.AND, Util.LV(1), Util.LI(2)));
                              EExpressionMatcher var18 = new EExpressionMatcher(var17);
                              boolean var19 = var18.isMatch(var13);
                              if (!var19) {
                                 Node var20 = Util.N(O.OR, Util.N(O.AND, Util.LV(1), Util.LI(2)), Util.L(0));
                                 var18 = new EExpressionMatcher(var20);
                                 var19 = var18.isMatch(var13);
                              }

                              if (var19) {
                                 Map var30 = var18.getMatchMap();
                                 IEVar var21 = (IEVar)var30.get(1);
                                 int var22 = 0;

                                 for (long var24 : var4.getUseDefs(var6.getAddressOfInstruction(var7), var21.getId())) {
                                    IEStatement var26 = (IEStatement)var3.getInstruction(var24);
                                    if (!this.q(var26, var11, var21)) {
                                       var22 = 0;
                                       break;
                                    }

                                    var22++;
                                 }

                                 if (var22 >= 0) {
                                    IEImm var39 = (IEImm)var30.get(2);
                                    var15 = q(var39);
                                    if (var15 != null) {
                                       var16 = var39;
                                       var14 = (IEGeneric)var30.get(0);
                                    }
                                 }
                              }

                              if (var14 == null) {
                                 var14 = var13;
                                 var15 = new Couple(0, 32);
                              }
                           } else {
                              var14 = var13;
                              var15 = new Couple(0, 32);
                           }

                           Object[] var10000 = new Object[]{var15};
                           int var27 = (Integer)var15.getFirst();
                           int var28 = (Integer)var15.getSecond() - var27;
                           int var29 = var28 * 8;
                           if (var16 == null) {
                              Pl.nI var34 = this.q(var12, var27, var28);
                              if (var34 != null) {
                                 this.q(var2, var6, var7, var34, var14);
                              }
                           } else if (var14 instanceof IEImm && this.q((IEImm)var14, var16)) {
                              Pl.nI var33 = this.q(var12, var27, var28);
                              if (var33 != null) {
                                 BigInteger var36 = ((IEImm)var14)
                                    .getValue()
                                    .shiftRight(var27 * 8)
                                    .and(BigInteger.ONE.shiftLeft(var28 * 8).subtract(BigInteger.ONE));
                                 IEImm var38 = EUtil.imm(var36, var28 * 8);
                                 this.q(var2, var6, var7, var33, var38);
                              }
                           } else if (var14 instanceof IEVar && var27 == 0 && ((IEVar)var14).getSafeType(this.gO).getEffectiveBitsize() == var29) {
                              Pl.nI var32 = this.q(var12, var27, var28);
                              if (var32 != null) {
                                 this.q(var2, var6, var7, var32, var14.part(var29));
                              }
                           } else {
                              Node var31 = Util.N(O.MUL, Util.N(O.AND, Util.LV(0), Util.LI(1)), Util.LI(2));
                              EExpressionMatcher var35 = new EExpressionMatcher(var31);
                              if (var35.isMatch(var14)) {
                                 IEGeneric var37 = (IEGeneric)var35.getMatchMap().get(0);
                                 IEImm var40 = (IEImm)var35.getMatchMap().get(1);
                                 IEImm var41 = (IEImm)var35.getMatchMap().get(2);
                                 if (q(var40, var41, var16)) {
                                    Pl.nI var25 = this.q(var12, var27, var28);
                                    if (var25 != null) {
                                       IEGeneric var42 = var37.part(var28 * 8);
                                       this.q(var2, var6, var7, var25, var42);
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

         return true;
      } else {
         return false;
      }
   }

   public void q() {
      IEGlobalContext var1 = this.Uv.getConverter().getGlobalContext();
      int var2 = 0;

      for (Pl.ej var4 : this.nf.values()) {
         for (Pl.nI var6 : var4.RF.values()) {
            String var7 = Strings.ff("g%d_%d", var2, var6.RF);
            long var8 = 4294967296L + 32 * var2 + var6.RF;
            INativeType var10;
            if (var6.xK == 20) {
               var10 = this.oW.getType("address");
            } else {
               var10 = this.oW.getExactInteger(var6.xK, false);
            }

            if (var10 == null) {
               var10 = TypeUtil.buildArrayType(this.oW, "unsigned char", var6.xK);
            }

            this.Dw.setDataAt(var8, var10, var7);
            INativeContinuousItem var11 = this.Dw.getNativeItemAt(var8);
            IEVar var12 = var1.createGlobalVariable(var8, var6.xK * 8);
            var6.Dw = var11;
            var6.Uv = var12;
            this.q.add(var11);
         }

         var2++;
      }

      IdentityHashSet var16 = new IdentityHashSet();

      for (Pl.eo var20 : this.gP) {
         IERoutineContext var23 = var20.q.getIRContext();
         var20.RF.set(var20.xK, var23.createAssign(var20.Dw.Uv, var20.Uv));
         var16.add(var23.getCfg());
      }

      for (Pl.CU var21 : this.za) {
         IERoutineContext var24 = var21.q.getIRContext();
         CFG var25 = var24.getCfg();
         var21.RF.set(var21.xK, var24.createNop());

         for (long var9 : var21.oW) {
            IEStatement var27 = (IEStatement)var25.getInstruction(var9);
            int var28 = tl.q(var27, 85, 84);
            IEUntranslatedInstruction var13 = (IEUntranslatedInstruction)var27;
            IEUntranslatedInstruction var14;
            if (var28 == 85) {
               IEGeneric var15 = var13.getParameterExpression(1);
               var14 = tl.q(var24, var13.getNativeAddress(), -2147483647, var21.Dw.Uv, var21.Uv, var15);
            } else {
               IEGeneric var29 = var13.getReturnExpression();
               var14 = tl.q(var24, var13.getNativeAddress(), -2147483646, var21.Dw.Uv, var21.Uv);
               var14.setReturnExpression(var29);
            }

            var14.setSize(var13.getSize());
            var25.replaceInstruction(var9, var14);
         }

         var16.add(var25);
      }

      for (CFG var22 : var16) {
         var22.invalidateDataFlowAnalysis();
      }
   }

   private static Couple q(IEImm var0) {
      if (var0.getBitsize() != 256) {
         return null;
      } else {
         BigInteger var1 = var0.getValue();
         return q(var1);
      }
   }

   private static Couple q(BigInteger var0) {
      BigInteger var1 = BigInteger.valueOf(255L);
      BigInteger var2 = BigInteger.ZERO;
      int var3 = 0;
      int var4 = 0;
      int var5 = 0;

      for (int var6 = 0; var6 < 32; var6++) {
         BigInteger var7 = var0.and(var1);
         if (var7.equals(var1)) {
            if (var5 == 1) {
               var5++;
               var4 = var6;
            }
         } else {
            if (!var7.equals(var2)) {
               return null;
            }

            if (var5 == 0) {
               var5++;
               var3 = var6;
            } else if (var5 != 1) {
               return null;
            }
         }

         var0 = var0.shiftRight(8);
      }

      return new Couple(var3, var4);
   }

   private static boolean q(IEImm var0, IEImm var1, IEImm var2) {
      return var0.getBitsize() == 256 && var1.getBitsize() == 256 ? var0._mul(var1)._not().equals(var2) : false;
   }

   private void q(IEGeneric var1, int var2, int var3) {
   }

   private boolean q(IEStatement var1, IEGeneric var2, IEVar var3) {
      if (!(var1 instanceof IEUntranslatedInstruction var4)) {
         return false;
      } else if (!"SLOAD".equals(var4.getNativeMnemonic())) {
         return false;
      } else {
         return !var2.equals(var4.getParameterExpression(0)) ? false : var3.equals(var4.getReturnExpression());
      }
   }

   private boolean q(IEImm var1, IEImm var2) {
      if (var1.getBitsize() == 256 && var2.getBitsize() == 256) {
         BigInteger var3 = var1.getValue();
         BigInteger var4 = var2.getValue();
         return var3.and(var4).equals(BigInteger.ZERO);
      } else {
         return false;
      }
   }

   private Pl.nI q(BigInteger var1, boolean var2) {
      return this.q(var1, 0, 32);
   }

   private Pl.nI q(BigInteger var1, int var2, int var3) {
      Pl.ej var4 = (Pl.ej)this.nf.get(var1);
      if (var4 == null) {
         var4 = new Pl.ej(var1);
         this.nf.put(var1, var4);
      }

      return var4.q(var2, var3);
   }

   private void q(IDecompiledMethod var1, BasicBlock var2, int var3, Pl.nI var4, IEGeneric var5) {
      Pl.eo var6 = new Pl.eo();
      var6.q = var1;
      var6.RF = var2;
      var6.xK = var3;
      var6.Dw = var4;
      var6.Uv = var5;
      this.gP.add(var6);
   }

   private IDecompiledMethod xK(INativeMethodItem var1) {
      return this.q(var1, NativeDecompilationStage.LIFTING);
   }

   private IDecompiledMethod q(INativeMethodItem var1, NativeDecompilationStage var2) {
      try {
         DecompilationContext var3 = new DecompilationContext(64);
         return this.Uv.decompileMethodEx(var1, var3, var2);
      } catch (Exception var4) {
         xK.catchingSilent(var4);
         return null;
      }
   }

   boolean q(IDecompiledMethod var1, CFG var2, BasicBlock var3, int var4, IEUntranslatedInstruction var5) {
      if (!EUtil.isImmZero(var5.getParameterExpression(0))) {
         return false;
      } else if (!EUtil.isImmValue(var5.getParameterExpression(1), 64L)) {
         return false;
      } else if (!(var5.getReturnExpression() instanceof IEVar)) {
         return false;
      } else {
         IEVar var6 = (IEVar)var5.getReturnExpression();
         List var7 = this.q(var2, var3, var4, var6);
         if (var7 != null && !var7.isEmpty()) {
            BigInteger var8 = null;
            IEGeneric var9 = null;
            int var10 = var4 - 1;

            while (var10 >= 0 && (var8 == null || var9 == null)) {
               IEStatement var11 = (IEStatement)var3.get(var10--);
               IEGeneric var12 = this.q(var11, EUtil.imm(32L, 256));
               if (var12 instanceof IEImm) {
                  var8 = ((IEImm)var12).getValue();
               } else {
                  var12 = this.q(var11, EUtil.zero(256));
                  if (var12 != null) {
                     var9 = var12;
                  }
               }
            }

            if (var8 != null && var9 != null) {
               Pl.nI var13 = this.q(var8, true);
               Pl.CU var15 = new Pl.CU();
               var15.q = var1;
               var15.RF = var3;
               var15.xK = var4;
               var15.Dw = var13;
               var15.Uv = var9;
               var15.oW = var7;
               this.za.add(var15);
               return true;
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   private IEGeneric q(IEStatement var1, IEGeneric var2) {
      if (var1 instanceof IEAssign && ((IEAssign)var1).getLeftOperand() instanceof IEMem) {
         IEMem var3 = (IEMem)((IEAssign)var1).getLeftOperand();
         if (var3.getReference().equalsEx(var2, false)) {
            return ((IEAssign)var1).getRightOperand();
         }
      }

      return null;
   }

   private List q(CFG var1, BasicBlock var2, int var3, IEVar var4) {
      IDFA var5 = var1.doDataFlowAnalysis();
      ArrayList var6 = new ArrayList();

      for (Long var8 : var5.getDefUses(var2.getAddressOfInstruction(var3), var4.getId())) {
         IEStatement var9 = (IEStatement)var1.getInstruction(var8);
         Integer var10 = tl.q(var9, 85, 84);
         if (var10 == null) {
            return null;
         }

         if (((IEUntranslatedInstruction)var9).getParameterExpression(0) != var4) {
            return null;
         }

         var6.add(var8);
      }

      return var6;
   }

   private static class CU {
      IDecompiledMethod q;
      BasicBlock RF;
      int xK;
      Pl.nI Dw;
      IEGeneric Uv;
      List oW;
   }

   private static class ej {
      BigInteger q;
      SegmentMap RF = new SegmentMap();

      public ej(BigInteger var1) {
         this.q = var1;
      }

      Pl.nI q(int var1, int var2) {
         Pl.nI var3 = (Pl.nI)this.RF.get(var1);
         if (var3 != null && var3.xK == var2) {
            return var3;
         } else {
            return !this.RF.isEmptyRange(var1, var1 + var2) ? null : (Pl.nI)this.RF.add(new Pl.nI(this.q, var1, var2));
         }
      }

      @Override
      public String toString() {
         return "key:" + this.q + ":" + this.RF;
      }
   }

   private static class eo {
      IDecompiledMethod q;
      BasicBlock RF;
      int xK;
      Pl.nI Dw;
      IEGeneric Uv;
   }

   @SerDisabled
   static class nI implements ISegment, Comparable {
      BigInteger q;
      int RF;
      int xK;
      INativeContinuousItem Dw;
      IEVar Uv;

      public nI(BigInteger var1, int var2, int var3) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
      }

      public int q(Pl.nI var1) {
         return Integer.compare(this.RF, var1.RF);
      }

      public Integer q() {
         return this.RF;
      }

      public Integer RF() {
         return this.RF + this.xK;
      }

      public int xK() {
         return this.xK;
      }

      @Override
      public String toString() {
         return Strings.ff("%s:[%d-%d)", this.q, this.q(), this.RF());
      }
   }
}
