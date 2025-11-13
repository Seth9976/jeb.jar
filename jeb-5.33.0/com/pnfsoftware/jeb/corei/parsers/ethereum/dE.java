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
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
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

public class dE {
   private static final ILogger kS = GlobalLog.getLogger(dE.class);
   private INativeCodeUnit wS;
   private INativeDecompilerUnit UT;
   private ITypeManager E;
   private IWildcardTypeManager sY;
   List pC = new ArrayList();
   Map A = new HashMap();
   private TreeMap ys = new TreeMap();
   private List ld = new ArrayList();
   private List gp = new ArrayList();

   public dE(INativeCodeUnit var1, INativeDecompilerUnit var2) {
      this.wS = var1;
      this.UT = var2;
      this.E = var1.getTypeManager();
      this.sY = var2.getWildcardTypeManager();
   }

   public boolean pC(INativeMethodItem var1) {
      boolean var3;
      try (ACLock var2 = this.wS.getLock().a()) {
         var3 = this.A(var1);
      }

      return var3;
   }

   private boolean A(INativeMethodItem var1) {
      IDecompiledMethod var2 = this.kS(var1);
      if (var2 != null && !var2.isFailure()) {
         CFG var3 = var2.getIRContext().getCfg();
         IDFA var4 = var3.doDataFlowAnalysis();

         for (BasicBlock var6 : var3) {
            for (int var7 = 0; var7 < var6.size(); var7++) {
               IEStatement var8 = (IEStatement)var6.get(var7);
               Integer var9 = oP.pC(var8, 32, 85, 84);
               if (var9 != null) {
                  IEUntranslatedInstruction var10 = (IEUntranslatedInstruction)var8;
                  if (var9 != 32) {
                     IEGeneric var11 = var10.getParameterExpression(0);
                     if (var11 instanceof IEImm) {
                        BigInteger var12 = ((IEImm)var11).getValue();
                        this.A.put(var12, 0);
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
                                    if (!this.pC(var26, var11, var21)) {
                                       var22 = 0;
                                       break;
                                    }

                                    var22++;
                                 }

                                 if (var22 >= 0) {
                                    IEImm var39 = (IEImm)var30.get(2);
                                    var15 = pC(var39);
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
                              dE.K var34 = this.pC(var12, var27, var28);
                              if (var34 != null) {
                                 this.pC(var2, var6, var7, var34, var14);
                              }
                           } else if (var14 instanceof IEImm && this.pC((IEImm)var14, var16)) {
                              dE.K var33 = this.pC(var12, var27, var28);
                              if (var33 != null) {
                                 BigInteger var36 = ((IEImm)var14)
                                    .getValue()
                                    .shiftRight(var27 * 8)
                                    .and(BigInteger.ONE.shiftLeft(var28 * 8).subtract(BigInteger.ONE));
                                 IEImm var38 = EUtil.imm(var36, var28 * 8);
                                 this.pC(var2, var6, var7, var33, var38);
                              }
                           } else if (var14 instanceof IEVar && var27 == 0 && ((IEVar)var14).getSafeType(this.sY).getEffectiveBitsize() == var29) {
                              dE.K var32 = this.pC(var12, var27, var28);
                              if (var32 != null) {
                                 this.pC(var2, var6, var7, var32, var14.part(var29));
                              }
                           } else {
                              Node var31 = Util.N(O.MUL, Util.N(O.AND, Util.LV(0), Util.LI(1)), Util.LI(2));
                              EExpressionMatcher var35 = new EExpressionMatcher(var31);
                              if (var35.isMatch(var14)) {
                                 IEGeneric var37 = (IEGeneric)var35.getMatchMap().get(0);
                                 IEImm var40 = (IEImm)var35.getMatchMap().get(1);
                                 IEImm var41 = (IEImm)var35.getMatchMap().get(2);
                                 if (pC(var40, var41, var16)) {
                                    dE.K var25 = this.pC(var12, var27, var28);
                                    if (var25 != null) {
                                       IEGeneric var42 = var37.part(var28 * 8);
                                       this.pC(var2, var6, var7, var25, var42);
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

   public void pC() {
      IEGlobalContext var1 = this.UT.getConverter().getGlobalContext();
      int var2 = 0;

      for (dE.Ws var4 : this.ys.values()) {
         for (dE.K var6 : var4.A.values()) {
            String var7 = Strings.ff("g%d_%d", var2, var6.A);
            long var8 = 4294967296L + 32 * var2 + var6.A;
            INativeType var10;
            if (var6.kS == 20) {
               var10 = this.E.getType("address");
            } else {
               var10 = this.E.getExactInteger(var6.kS, false);
            }

            if (var10 == null) {
               var10 = TypeUtil.buildArrayType(this.E, "unsigned char", var6.kS);
            }

            this.wS.setDataAt(var8, var10, var7);
            INativeContinuousItem var11 = this.wS.getNativeItemAt(var8);
            IEVar var12 = var1.createGlobalVariable(var8, var6.kS * 8);
            var6.wS = var11;
            var6.UT = var12;
            this.pC.add(var11);
         }

         var2++;
      }

      IdentityHashSet var16 = new IdentityHashSet();

      for (dE.Av var20 : this.ld) {
         IERoutineContext var23 = var20.pC.getIRContext();
         var20.A.set(var20.kS, var23.createAssign(var20.wS.UT, var20.UT));
         var16.add(var23.getCfg());
      }

      for (dE.Sv var21 : this.gp) {
         IERoutineContext var24 = var21.pC.getIRContext();
         CFG var25 = var24.getCfg();
         var21.A.set(var21.kS, var24.createNop());

         for (long var9 : var21.E) {
            IEStatement var27 = (IEStatement)var25.getInstruction(var9);
            int var28 = oP.pC(var27, 85, 84);
            IEUntranslatedInstruction var13 = (IEUntranslatedInstruction)var27;
            IEUntranslatedInstruction var14;
            if (var28 == 85) {
               IEGeneric var15 = var13.getParameterExpression(1);
               var14 = oP.pC(var24, var13.getNativeAddress(), -2147483647, var21.wS.UT, var21.UT, var15);
            } else {
               IEGeneric var29 = var13.getReturnExpression();
               var14 = oP.pC(var24, var13.getNativeAddress(), -2147483646, var21.wS.UT, var21.UT);
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

   private static Couple pC(IEImm var0) {
      if (var0.getBitsize() != 256) {
         return null;
      } else {
         BigInteger var1 = var0.getValue();
         return pC(var1);
      }
   }

   private static Couple pC(BigInteger var0) {
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

   private static boolean pC(IEImm var0, IEImm var1, IEImm var2) {
      return var0.getBitsize() == 256 && var1.getBitsize() == 256 ? var0._mul(var1)._not().equals(var2) : false;
   }

   private boolean pC(IEStatement var1, IEGeneric var2, IEVar var3) {
      if (!(var1 instanceof IEUntranslatedInstruction var4)) {
         return false;
      } else if (!"SLOAD".equals(var4.getNativeMnemonic())) {
         return false;
      } else {
         return !var2.equals(var4.getParameterExpression(0)) ? false : var3.equals(var4.getReturnExpression());
      }
   }

   private boolean pC(IEImm var1, IEImm var2) {
      if (var1.getBitsize() == 256 && var2.getBitsize() == 256) {
         BigInteger var3 = var1.getValue();
         BigInteger var4 = var2.getValue();
         return var3.and(var4).equals(BigInteger.ZERO);
      } else {
         return false;
      }
   }

   private dE.K pC(BigInteger var1, int var2, int var3) {
      dE.Ws var4 = (dE.Ws)this.ys.get(var1);
      if (var4 == null) {
         var4 = new dE.Ws(var1);
         this.ys.put(var1, var4);
      }

      return var4.pC(var2, var3);
   }

   private void pC(IDecompiledMethod var1, BasicBlock var2, int var3, dE.K var4, IEGeneric var5) {
      dE.Av var6 = new dE.Av();
      var6.pC = var1;
      var6.A = var2;
      var6.kS = var3;
      var6.wS = var4;
      var6.UT = var5;
      this.ld.add(var6);
   }

   private IDecompiledMethod kS(INativeMethodItem var1) {
      return this.pC(var1, NativeDecompilationStage.LIFTING);
   }

   private IDecompiledMethod pC(INativeMethodItem var1, NativeDecompilationStage var2) {
      try {
         DecompilationContext var3 = new DecompilationContext(64);
         return this.UT.decompileMethodEx(var1, var3, var2);
      } catch (Exception var4) {
         kS.catchingSilent(var4);
         return null;
      }
   }

   private static class Av {
      IDecompiledMethod pC;
      BasicBlock A;
      int kS;
      dE.K wS;
      IEGeneric UT;
   }

   @SerDisabled
   static class K implements ISegment, Comparable {
      BigInteger pC;
      int A;
      int kS;
      INativeContinuousItem wS;
      IEVar UT;

      public K(BigInteger var1, int var2, int var3) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
      }

      public int pC(dE.K var1) {
         return Integer.compare(this.A, var1.A);
      }

      public Integer pC() {
         return this.A;
      }

      public Integer A() {
         return this.A + this.kS;
      }

      @Override
      public String toString() {
         return Strings.ff("%s:[%d-%d)", this.pC, this.pC(), this.A());
      }
   }

   private static class Sv {
      IDecompiledMethod pC;
      BasicBlock A;
      int kS;
      dE.K wS;
      IEGeneric UT;
      List E;
   }

   private static class Ws {
      BigInteger pC;
      SegmentMap A = new SegmentMap();

      public Ws(BigInteger var1) {
         this.pC = var1;
      }

      dE.K pC(int var1, int var2) {
         dE.K var3 = (dE.K)this.A.get(var1);
         if (var3 != null && var3.kS == var2) {
            return var3;
         } else {
            return !this.A.isEmptyRange(var1, var1 + var2) ? null : (dE.K)this.A.add(new dE.K(this.pC, var1, var2));
         }
      }

      @Override
      public String toString() {
         return "key:" + this.pC + ":" + this.A;
      }
   }
}
