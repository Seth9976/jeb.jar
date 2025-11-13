package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.EPatternCompiler;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.EPatternMatcher;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler.IEPatternReplacer;
import java.util.Collection;
import java.util.HashMap;

public class alz {
   static EPatternCompiler.EPattern pC = EPatternCompiler.EPattern.create("Dual-slot addition 1")
      .addInput("($0[H1] + $1[H1]) .=. ((($0[H1] _CARRY $1[H1]) ... 0) + $0[H2] + $1[H2])")
      .addInput("($0 + $1)[H1] .=. ((($0[H1] _CARRY $1[H1]) ... 0) + $0[H2] + $1[H2])")
      .addInput("($0[H1] + $1[H1]) .=. (((($0[H1] + $1[H1]) <u $0[H1]) ... 0) + $0[H2] + $1[H2])")
      .addInput("($0[H1] + $1[H1]) .=. (((($0[H1] + $1[H1]) <u $1[H1]) ... 0) + $0[H2] + $1[H2])")
      .addInput("($0[H1] + $1[H1]) .=. ((((($0 + $1)[H1]) <u $0[H1]) ... 0) + $0[H2] + $1[H2])")
      .addInput("($0[H1] + $1[H1]) .=. ((((($0 + $1)[H1]) <u $1[H1]) ... 0) + $0[H2] + $1[H2])")
      .setOutput("$0 + $1")
      .compile();
   static EPatternCompiler.EPattern A = EPatternCompiler.EPattern.create("Dual-slot addition imm")
      .setInput("($0[H1] + #2) .=. ((($0[H1] _CARRY #2) ... 0) + $0[H2])")
      .addInput("($0 + #2)[H1] .=. ((($0[H1] _CARRY #2) ... 0) + $0[H2])")
      .addInput("($0[H1] + #2) .=. (((($0[H1] + #2) <u $0[H1]) ... 0) + $0[H2])")
      .addInput("($0[H1] + #2) .=. (((($0[H1] + #2) <u #2) ... 0) + $0[H2])")
      .addInput("($0[H1] + #2) .=. ((((($0 + $1)[H1]) <u $0[H1]) ... 0) + $0[H2])")
      .addInput("($0[H1] + #2) .=. ((((($0 + $1)[H1]) <u #2) ... 0) + $0[H2])")
      .setOutput("$0 + (#2 .=. 0)")
      .compile();
   static EPatternCompiler.EPattern kS = EPatternCompiler.EPattern.create("Dual-slot addition undetermined")
      .addInput("($0[H1] + $1) .=. (((($0[H1] + $1) <u $0[H1]) ... 0) + $0[H2] + $2)")
      .addInput("($0[H1] + $1) .=. (((($0[H1] + $1) <u $1) ... 0) + $0[H2] + $2)")
      .setOutput("$0 + ($1 .=. $2)")
      .compile();
   static EPatternCompiler.EPattern wS = EPatternCompiler.EPattern.create("Dual-slot addition 3")
      .setInput("($0[H1] + #2) .=. ((($0[H1] >=u #4) ... 0) + $0[H2])")
      .setOutput("$0 + (#2 .=. 0)")
      .setVerifier((var0, var1) -> ((IEImm)var1.getMatchedLeaf(2, IEImm.class))._add((IEImm)var1.getMatchedLeaf(4, IEImm.class)).isZero())
      .compile();
   static EPatternCompiler.EPattern UT = EPatternCompiler.EPattern.create("Dual-slot addition 4")
      .setInput("($0[H1] + #2) .=. ((($0[H1] _CARRY #2) ... 0) + $0[H2] + #3)")
      .setOutput("$0 + (#2 .=. #3)")
      .compile();
   static EPatternCompiler.EPattern E = EPatternCompiler.EPattern.create("Dual-slot addition 5")
      .setInput("($0[H1] + #2) .=. ((($0[H1] >=u #4) ... 0) + $0[H2] + #3)")
      .setOutput("$0 + (#2 .=. #3)")
      .setVerifier((var0, var1) -> ((IEImm)var1.getMatchedLeaf(2, IEImm.class))._add((IEImm)var1.getMatchedLeaf(4, IEImm.class)).isZero())
      .compile();
   static EPatternCompiler.EPattern sY = EPatternCompiler.EPattern.create("Dual-slot addition 6")
      .addInput("x V3 = ($0[H1] + $1[H1])", "  $2[H1] = V3", "> $2[H2] = ( (($0[H1] >u V3) ... 0) + $0[H2] + $1[H2])")
      .addInput("x V3 = ($0[H1] + $1[H1])", "  $2[H1] = V3", "> $2[H2] = ( (($1[H1] >u V3) ... 0) + $0[H2] + $1[H2])")
      .setOutput("$2 = $0 + $1")
      .compile();
   static EPatternCompiler.EPattern ys = EPatternCompiler.EPattern.create("Dual-slot addition 6")
      .addInput("  V2 = ($0 + V2)", "> V3 = ((($0 >u V2) ... 0) + $1 + V3)")
      .addInput("x V4 = V2", "  V2 = ($0 + V2)", "> V3 = (((V4 >u V2) ... 0) + $1 + V3)")
      .addInput("x V4 = V2", "  V2 = ($0 + V2)", "> V3 = (((V4 _CARRY $0) ... 0) + $1 + V3)")
      .setOutput("V12 = V10 + V11")
      .setCustomReplacer(new alz.Av())
      .compile(3);
   static EPatternCompiler.EPattern ld = EPatternCompiler.EPattern.create("Dual-slot addition 7")
      .addInput("> V4 = ($0[H1] + V2[H1])", "  V5 = ((($0[H1] >u V4) ... 0) + $0[H2] + V2[H2])")
      .addInput("> V4 = ($0[H1] + V2[H1])", "  V5 = ((($0[H1] _CARRY V2[H1]) ... 0) + $0[H2] + V2[H2])")
      .setOutput("V1 = $0 + V2")
      .setCustomReplacer(new alz.Sv())
      .compile(1);
   static EPatternCompiler.EPattern gp = EPatternCompiler.EPattern.create("Dual-slot addition 8")
      .addInput("  V4 = (V0 + #2)", "> V5 = (((V0 _CARRY #2) ... 0) + V1)")
      .addInput("  V4 = (V0 + #2)", "> V5 = (((V0 >u V4) ... 0) + V1)")
      .addInput("  V4 = (V0 + #2)", "> V5 = (((V0 _CARRY #2) ... 0) + V1 + #3)")
      .addInput("  V4 = (V0 + #2)", "> V5 = (((V0 >u V4) ... 0) + V1 + #3)")
      .setOutput("V12 = V10 + #11")
      .setCustomReplacer(new alz.K())
      .compile(3);
   static EPatternCompiler.EPattern oT = EPatternCompiler.EPattern.create("Dual-slot addition 9")
      .addInput("  V4 = (V0[H1] + #2)", "> V5 = (((V0[H1] _CARRY #2) ... 0) + V0[H2])")
      .addInput("  V4 = (V0[H1] + #2)", "> V5 = (((V0[H1] >u V4) ... 0) + V0[H2])")
      .addInput("  V4 = (V0[H1] + #2)", "> V5 = (((V0[H1] _CARRY #2) ... 0) + V0[H2] + #3)")
      .addInput("  V4 = (V0[H1] + #2)", "> V5 = (((V0[H1] >u V4) ... 0) + V0[H2] + #3)")
      .setOutput("V12 = V0 + #11")
      .setCustomReplacer(new alz.Ws())
      .compile(1);
   static EPatternCompiler.EPattern fI = EPatternCompiler.EPattern.create("Dual-slot addition 10")
      .addInput("  V4 = (V0 - #9)", "> V5 = (((V0 _CARRY #2) ... 0) + V1)")
      .addInput("  V4 = (V0 - #9)", "> V5 = (((V0 _CARRY #2) ... 0) + V1 + #3)")
      .setOutput("V12 = V10 + #11")
      .setVerifier((var0, var1) -> {
         IEImm var2 = (IEImm)var1.getMatchedLeaf(9, IEImm.class);
         IEImm var3 = (IEImm)var1.getMatchedLeaf(2, IEImm.class);
         return var2._neg().equals(var3);
      })
      .setCustomReplacer(new alz.K())
      .compile(3);

   static class Av implements IEPatternReplacer {
      @Override
      public Boolean replace(EPatternMatcher var1, EPatternMatcher.Result var2) {
         IERoutineContext var3 = var2.getContext();
         CFG var4 = var2.getCfg();
         IEGeneric var5 = (IEGeneric)var2.getMatchedLeaf(0, IEGeneric.class);
         IEGeneric var6 = (IEGeneric)var2.getMatchedLeaf(1, IEGeneric.class);
         IEGeneric var7 = (IEGeneric)var2.getMatchedLeaf(2, IEGeneric.class);
         IEGeneric var8 = (IEGeneric)var2.getMatchedLeaf(3, IEGeneric.class);
         if (var5 instanceof IEImm ^ var6 instanceof IEImm) {
            return false;
         } else {
            long var9 = var1.getMatchAddress(var2, -2);
            long var11 = var1.getMatchAddress(var2, -1);
            alr var13 = new alr(var3, var4);
            HashMap var14 = new HashMap();
            if (var5 instanceof IEVar) {
               int var15 = ((IEVar)var5).getId();
               var13.pC(var9, var15, false);
               var14.put(0, var13.pC());
            }

            if (var6 instanceof IEVar) {
               int var23 = ((IEVar)var6).getId();
               var13.pC(var11, var23, false);
               var14.put(1, var13.pC());
            }

            int var24 = ((IEVar)var7).getId();
            var13.pC(var9, var24, true);
            var13.pC(var9, var24, false);
            var14.put(2, var13.pC());
            int var16 = ((IEVar)var8).getId();
            var13.pC(var11, var16, true);
            var13.pC(var11, var16, false);
            var14.put(3, var13.pC());
            IEGeneric var17 = amf.pC(var3, var7, var8);
            if (var17 == null) {
               return false;
            } else {
               IEGeneric var18 = amf.pC(var3, var5, var6);
               if (var18 == null) {
                  return false;
               } else {
                  var2.getMatchMap().put(10, var18);
                  var2.getMatchMap().put(11, var17);
                  var2.getMatchMap().put(12, var17);
                  if (!var1.replace(var2, true)) {
                     return false;
                  } else {
                     int var19 = var5.getBitsize();
                     IEVar var20 = (IEVar)var7;
                     alr.pC(var4, (Collection)var14.get(2), var20, var17.part(var19));
                     IEVar var21 = (IEVar)var8;
                     alr.pC(var4, (Collection)var14.get(3), var21, var17.slice(var19));
                     if (var5 instanceof IEVar var22) {
                        alr.pC(var4, (Collection)var14.get(0), var22, var18.part(var19));
                     }

                     if (var6 instanceof IEVar var25) {
                        alr.pC(var4, (Collection)var14.get(1), var25, var18.slice(var19));
                     }

                     var4.invalidateDataFlowAnalysis();
                     return true;
                  }
               }
            }
         }
      }
   }

   static class K implements IEPatternReplacer {
      @Override
      public Boolean replace(EPatternMatcher var1, EPatternMatcher.Result var2) {
         IERoutineContext var3 = var2.getContext();
         CFG var4 = var2.getCfg();
         IEVar var5 = (IEVar)var2.getMatchedLeaf(4, IEVar.class);
         IEVar var6 = (IEVar)var2.getMatchedLeaf(5, IEVar.class);
         IEImm var7 = (IEImm)var2.getMatchedLeaf(2, IEImm.class);
         IEImm var8 = (IEImm)var2.getMatchedLeaf(3, IEImm.class);
         IEVar var9 = (IEVar)var2.getMatchedLeaf(0, IEVar.class);
         IEVar var10 = (IEVar)var2.getMatchedLeaf(1, IEVar.class);
         long var11 = var1.getMatchAddress(var2, 0);
         long var13 = var1.getMatchAddress(var2, 1);
         alr var15 = new alr(var3, var4);
         HashMap var16 = new HashMap();
         var15.pC(var11, var5.getId(), true);
         var16.put(4, var15.pC());
         var15.pC(var13, var6.getId(), true);
         var16.put(5, var15.pC());
         var15.pC(var11, var9.getId(), false);
         var16.put(0, var15.pC());
         var15.pC(var13, var10.getId(), false);
         var16.put(1, var15.pC());
         IEGeneric var17 = amf.pC(var3, var5, var6);
         if (var17 == null) {
            return false;
         } else {
            var2.getMatchMap().put(12, var17);
            boolean var18 = true;
            Object var19 = amf.pC(var3, var9, var10);
            if (var19 == null) {
               var18 = false;
               var19 = var3.createCompose(var9, var10);
            }

            var2.getMatchMap().put(10, var19);
            IEImm var20;
            if (var8 == null) {
               var20 = var7.zeroExtend(((IEGeneric)var19).getBitsize());
            } else {
               try {
                  var20 = var3.createCompose(var7, var8).evaluate(null);
               } catch (Exception var22) {
                  return false;
               }
            }

            var2.getMatchMap().put(11, var20);
            if (!var1.replace(var2, true)) {
               return false;
            } else {
               int var21 = var5.getBitsize();
               alr.pC(var4, (Collection)var16.get(4), var5, var17.part(var21));
               alr.pC(var4, (Collection)var16.get(5), var6, var17.slice(var21));
               if (var18) {
                  alr.pC(var4, (Collection)var16.get(0), var9, ((IEGeneric)var19).part(var21));
                  alr.pC(var4, (Collection)var16.get(1), var10, ((IEGeneric)var19).slice(var21));
               }

               var4.invalidateDataFlowAnalysis();
               return true;
            }
         }
      }
   }

   static class Sv implements IEPatternReplacer {
      @Override
      public Boolean replace(EPatternMatcher var1, EPatternMatcher.Result var2) {
         IERoutineContext var3 = var2.getContext();
         CFG var4 = var2.getCfg();
         IEVar var5 = (IEVar)var2.getMatchedLeaf(4, IEVar.class);
         IEVar var6 = (IEVar)var2.getMatchedLeaf(5, IEVar.class);
         long var7 = var1.getMatchAddress(var2, -2);
         long var9 = var1.getMatchAddress(var2, -1);
         alr var11 = new alr(var3, var4);
         HashMap var12 = new HashMap();
         var11.pC(var7, var5.getId(), true);
         var12.put(4, var11.pC());
         var11.pC(var9, var6.getId(), true);
         var12.put(5, var11.pC());
         IEGeneric var13 = amf.pC(var3, var5, var6);
         if (var13 == null) {
            return false;
         } else {
            var2.getMatchMap().put(1, var13);
            if (!var1.replace(var2, true)) {
               return false;
            } else {
               int var14 = var5.getBitsize();
               alr.pC(var4, (Collection)var12.get(4), var5, var13.part(var14));
               alr.pC(var4, (Collection)var12.get(5), var6, var13.slice(var14));
               var4.invalidateDataFlowAnalysis();
               return true;
            }
         }
      }
   }

   static class Ws implements IEPatternReplacer {
      @Override
      public Boolean replace(EPatternMatcher var1, EPatternMatcher.Result var2) {
         IERoutineContext var3 = var2.getContext();
         CFG var4 = var2.getCfg();
         IEVar var5 = (IEVar)var2.getMatchedLeaf(4, IEVar.class);
         IEVar var6 = (IEVar)var2.getMatchedLeaf(5, IEVar.class);
         if (var5.getBitsize() != var6.getBitsize()) {
            return false;
         } else {
            IEImm var7 = (IEImm)var2.getMatchedLeaf(2, IEImm.class);
            IEImm var8 = (IEImm)var2.getMatchedLeaf(3, IEImm.class);
            long var9 = var1.getMatchAddress(var2, 0);
            long var11 = var1.getMatchAddress(var2, 1);
            alr var13 = new alr(var3, var4);
            HashMap var14 = new HashMap();
            var13.pC(var9, var5.getId(), true);
            var14.put(4, var13.pC());
            var13.pC(var11, var6.getId(), true);
            var14.put(5, var13.pC());
            IEGeneric var15 = amf.pC(var3, var5, var6);
            if (var15 == null) {
               return false;
            } else {
               var2.getMatchMap().put(12, var15);
               IEImm var16;
               if (var8 == null) {
                  var16 = var7.zeroExtend(var15.getBitsize());
               } else {
                  try {
                     var16 = var3.createCompose(var7, var8).evaluate(null);
                  } catch (Exception var18) {
                     return false;
                  }
               }

               var2.getMatchMap().put(11, var16);
               if (!var1.replace(var2, true)) {
                  return false;
               } else {
                  int var17 = var5.getBitsize();
                  alr.pC(var4, (Collection)var14.get(4), var5, var15.part(var17));
                  alr.pC(var4, (Collection)var14.get(5), var6, var15.slice(var17));
                  var4.invalidateDataFlowAnalysis();
                  return true;
               }
            }
         }
      }
   }
}
