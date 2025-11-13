package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jebglobal.aeg;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

public class EPatternMatcher {
   private static final StructuredLogger logger = aeg.q(EPatternMatcher.class);
   private final EPatternCompiler.EPattern pattern;
   private final IERoutineContext ctx;
   private final CFG cfg;
   private final IEGeneric exp0;
   private boolean allowDeepAssociativity = true;
   private Map IRDepthsMap;

   public EPatternMatcher(EPatternCompiler.EPattern var1, CFG var2, IERoutineContext var3) {
      this.pattern = var1;
      this.cfg = var2;
      this.ctx = var3;
      this.exp0 = null;
   }

   public EPatternMatcher(EPatternCompiler.EPattern var1, IEGeneric var2, IERoutineContext var3) {
      this.pattern = var1;
      this.ctx = var3;
      this.exp0 = var2;
      this.cfg = null;
   }

   public EPatternCompiler.EPattern getPattern() {
      return this.pattern;
   }

   public IERoutineContext getContext() {
      return this.ctx;
   }

   public CFG getInputCfg() {
      return this.cfg;
   }

   public IEGeneric getInputExpression() {
      return this.exp0;
   }

   public boolean isAllowDeepAssociativity() {
      return this.allowDeepAssociativity;
   }

   public void setAllowDeepAssociativity(boolean var1) {
      this.allowDeepAssociativity = var1;
   }

   public void setIRDepthsMap(Map var1) {
      this.IRDepthsMap = var1;
   }

   public Map getIRDepthsMap() {
      return this.IRDepthsMap;
   }

   @Override
   public String toString() {
      return Strings.ff("Matcher for: pattern=%s, CFG=%s", this.pattern, this.cfg);
   }

   public EPatternMatcher.Result search() {
      return this.search(null);
   }

   private int restore(AtomicInteger var1) {
      if (var1 != null) {
         int var2 = var1.intValue();
         if (var2 >= 0) {
            var1.set(-1);
            return var2;
         }
      }

      return 0;
   }

   private EPatternMatcher.Result searchFull(EPatternMatcher.Result var1) {
      AtomicInteger var2 = new AtomicInteger();
      AtomicInteger var3 = new AtomicInteger();
      AtomicInteger var4 = new AtomicInteger();
      if (var1 != null) {
         var2 = new AtomicInteger(var1.ipattern0);
         var3 = new AtomicInteger(var1.iblk0);
         var4 = new AtomicInteger(var1.iinsn0 + 1);
      }

      for (int var5 = this.restore(var2); var5 < this.pattern.getInputs().size(); var5++) {
         EPatternCompiler.EPattern.P var6 = (EPatternCompiler.EPattern.P)this.pattern.getInputs().get(var5);
         EPatternCompiler.EPattern.P.E var7 = var6.getTriggerLine();
         INode var8 = var7.expr;
         INode var9 = var7.rhs;

         for (int var10 = this.restore(var3); var10 < this.cfg.size(); var10++) {
            BasicBlock var11 = this.cfg.get(var10);
            if (var11.size() >= var6.getCountOfLines()) {
               int var12 = var6.getTriggerIndex();
               if (var12 >= 0) {
                  int var13 = var11.size() - var6.getCountOfLines() + var12;
                  if (var13 >= 0) {
                     for (int var14 = var12 + this.restore(var4); var14 <= var13; var14++) {
                        IEStatement var15 = (IEStatement)var11.get(var14);
                        int var16 = var14 - var12;
                        HashMap var18 = new HashMap();
                        IEGeneric[] var19 = new IEGeneric[2];
                        boolean var17;
                        if (!this.pattern.isPureInputExpression() && !this.pattern.isPureOuputExpression()) {
                           if (!(var15 instanceof IEAssign)) {
                              continue;
                           }

                           IEGeneric var30 = ((IEAssign)var15).getDstOperand();
                           IEGeneric var33 = ((IEAssign)var15).getSrcOperand();
                           EExpressionMatcher var22 = new EExpressionMatcher(var8, var18);
                           var22.setAllowDeepAssociativity(this.allowDeepAssociativity);
                           var22.setIRDepthsMap(this.IRDepthsMap);
                           if (!var22.isMatch(var30)) {
                              continue;
                           }

                           var22 = new EExpressionMatcher(var9, var18);
                           var22.setAllowDeepAssociativity(this.allowDeepAssociativity);
                           var22.setIRDepthsMap(this.IRDepthsMap);
                           if (!var22.isMatch(var33)) {
                              continue;
                           }

                           var17 = true;
                        } else {
                           EVisitResults var20 = new EVisitResults(1);
                           var20.setVisitResult(false);
                           EExpressionMatcher var21 = new EExpressionMatcher(var8, var18);
                           var21.setAllowDeepAssociativity(this.allowDeepAssociativity);
                           var21.setIRDepthsMap(this.IRDepthsMap);
                           var17 = var15.visitDepthPost(new EPatternMatcher$1(this, var21, var19), null, var20);
                        }

                        if (var17 && !this.pattern.isPureInputExpression()) {
                           int var31 = var16 + var6.getCountOfLines();
                           int var34 = var16;

                           for (int var36 = 0; var34 < var31; var36++) {
                              if (var36 != var12) {
                                 IEStatement var23 = (IEStatement)var11.get(var34);
                                 if (!(var23 instanceof IEAssign)) {
                                    var17 = false;
                                    break;
                                 }

                                 IEGeneric var24 = ((IEAssign)var23).getDstOperand();
                                 IEGeneric var25 = ((IEAssign)var23).getSrcOperand();
                                 EPatternCompiler.EPattern.P.E var26 = var6.getLine(var36);
                                 INode var27 = var26.expr;
                                 INode var28 = var26.rhs;
                                 EExpressionMatcher var29 = new EExpressionMatcher(var27, var18);
                                 var29.setAllowDeepAssociativity(this.allowDeepAssociativity);
                                 var29.setIRDepthsMap(this.IRDepthsMap);
                                 if (!var29.isMatch(var24)) {
                                    var17 = false;
                                    break;
                                 }

                                 var29 = new EExpressionMatcher(var28, var18);
                                 var29.setAllowDeepAssociativity(this.allowDeepAssociativity);
                                 var29.setIRDepthsMap(this.IRDepthsMap);
                                 if (!var29.isMatch(var25)) {
                                    var17 = false;
                                    break;
                                 }
                              }

                              var34++;
                           }
                        }

                        if (var17
                           && ((var6.getFlags() & 1) == 0 || !this.hasOverlappingVars(var18))
                           && ((var6.getFlags() & 2) == 0 || this.hasSameLeafSize(var18))) {
                           EPatternMatcher.Result var32 = new EPatternMatcher.Result(this.ctx, this.cfg, this.exp0, this.pattern, var5, var18);
                           var32.matchAddress = var11.getAddressOfInstruction(var16);
                           var32.iblk0 = var10;
                           var32.ipattern0 = var5;
                           var32.iinsn0 = var14 - var12;
                           var32.matchIRE = var19[0];
                           var32.matchParentIRE = var19[1];
                           if (this.pattern.getVerifier() == null || this.pattern.getVerifier().verify(this, var32)) {
                              return var32;
                           }
                        }
                     }
                     continue;
                  }
               }
            }
         }
      }

      return null;
   }

   private EPatternMatcher.Result searchExpression(EPatternMatcher.Result var1) {
      if (!this.pattern.isPureInputExpression()) {
         return null;
      } else {
         AtomicInteger var2 = new AtomicInteger();
         if (var1 != null) {
            var2 = new AtomicInteger(var1.ipattern0);
         }

         IEGeneric var3 = this.exp0;
         if (var1 != null) {
            var3 = var1.optimizedExpression;
         }

         for (int var4 = this.restore(var2); var4 < this.pattern.getInputs().size(); var4++) {
            EPatternCompiler.EPattern.P var5 = (EPatternCompiler.EPattern.P)this.pattern.getInputs().get(var4);
            EPatternCompiler.EPattern.P.E var6 = var5.getTriggerLine();
            INode var7 = var6.expr;
            HashMap var8 = new HashMap();
            IEGeneric[] var9 = new IEGeneric[2];
            EVisitResults var10 = new EVisitResults(1);
            var10.setVisitResult(false);
            EExpressionMatcher var11 = new EExpressionMatcher(var7, var8);
            var11.setAllowDeepAssociativity(this.allowDeepAssociativity);
            var11.setIRDepthsMap(this.IRDepthsMap);
            if (var3.visitDepthPost(new EPatternMatcher$2(this, var11, var9), null, var10)
               && ((var5.getFlags() & 1) == 0 || !this.hasOverlappingVars(var8))
               && ((var5.getFlags() & 2) == 0 || this.hasSameLeafSize(var8))) {
               EPatternMatcher.Result var12 = new EPatternMatcher.Result(this.ctx, this.cfg, this.exp0, this.pattern, var4, var8);
               var12.ipattern0 = var4;
               var12.matchIRE = var9[0];
               var12.matchParentIRE = var9[1];
               if (this.pattern.getVerifier() == null || this.pattern.getVerifier().verify(this, var12)) {
                  return var12;
               }
            }
         }

         return null;
      }
   }

   private boolean hasOverlappingVars(Map var1) {
      HashSet var2 = new HashSet();

      for (Entry var4 : var1.entrySet()) {
         if (var4.getValue() instanceof IEVar var6 && !var2.add(var6.getId())) {
            return true;
         }
      }

      return false;
   }

   private boolean hasSameLeafSize(Map var1) {
      int var2 = -1;

      for (Entry var4 : var1.entrySet()) {
         Object var5 = var4.getValue();
         if (var5 instanceof IEGeneric) {
            int var6 = ((IEGeneric)var5).getBitsize();
            if (var2 < 0) {
               var2 = var6;
            } else if (var6 != var2) {
               return false;
            }
         }
      }

      return true;
   }

   public EPatternMatcher.Result search(EPatternMatcher.Result var1) {
      if (!this.pattern.isCompiled()) {
         throw new IllegalArgumentException("Pattern is not compiled");
      } else if (this.cfg != null) {
         return this.searchFull(var1);
      } else if (this.exp0 != null) {
         return this.searchExpression(var1);
      } else {
         throw new RuntimeException();
      }
   }

   public EPatternMatcher.Result searchAndReplace() {
      return this.searchAndReplace(null);
   }

   public EPatternMatcher.Result searchAndReplace(EPatternMatcher.Result var1) {
      EPatternMatcher.Result var2 = this.search(var1);
      if (var2 == null) {
         return null;
      } else {
         var2.replaced = this.replace(var2, true);
         return var2;
      }
   }

   public boolean replace(EPatternMatcher.Result var1, boolean var2) {
      if (!var2 && this.pattern.replacer != null) {
         Boolean var3 = this.pattern.replacer.replace(this, var1);
         if (var3 != null) {
            if (!var3) {
               return false;
            }

            return true;
         }
      }

      EPatternCompiler.EPattern.P var20 = this.pattern.getOutput();
      if (var20 == null) {
         throw new IllegalArgumentException("No replacement pattern provided");
      } else if (!this.pattern.isPureInputExpression() && !this.pattern.isPureOuputExpression()) {
         EPatternCompiler.EPattern.P var21 = this.pattern.getInput(var1.getInputIndex());
         int var22 = var21.getCountOfReplacedLines();
         int var6 = 0;
         long var7 = this.getMatchAddress(var1, var21.getFirstReplacedLineIndex());
         Couple var9 = this.cfg.getInstructionLocation(var7);
         BasicBlock var10 = (BasicBlock)var9.getFirst();
         int var11 = (Integer)var9.getSecond();
         int var12 = var11 + var22;

         for (int var13 = var11; var13 < var12; var13++) {
            var6 += ((IEStatement)var10.get(var13)).getSize();
         }

         int var23 = var20.getCountOfLines();
         if (var6 < var23) {
            return false;
         } else {
            ArrayList var14 = new ArrayList();

            for (int var15 = 0; var15 < var23; var15++) {
               EPatternCompiler.EPattern.P.E var16 = var20.getLine(var15);
               IEGeneric var17 = new EExpressionGenerator(this.ctx, var16.rhs).generate(var1.matchMap);
               IEGeneric var18 = new EExpressionGenerator(this.ctx, var16.expr).generate(var1.matchMap);
               IEAssign var19 = this.ctx.createAssign(var18, var17);
               var14.add(var19);
            }

            int var24 = var14.size() - 1;
            ((IEStatement)var14.get(var24)).setSize(var6 - var24);

            for (IEStatement var26 : var14) {
               for (int var27 = var11; var27 < var12; var27++) {
                  var26.copyLowerLevelAddresses((IEStatement)var10.get(var27));
               }
            }

            if (!this.cfg.replaceInstructionsInBlock(var7, var22, var14)) {
               return false;
            } else {
               this.cfg.invalidateDataFlowAnalysis();
               return true;
            }
         }
      } else {
         INode var4 = ((EPatternCompiler.EPattern.P.E)var20.clist.get(0)).expr;
         IEGeneric var5 = new EExpressionGenerator(this.ctx, var4).generate(var1.matchMap, var1.matchIRE.getBitsize());
         if (var1.matchIRE.getType() != null) {
            var5.setType(var1.matchIRE.getType());
         }

         if (var1.matchParentIRE == null) {
            var1.optimizedExpression = var5;
            return true;
         } else if (var1.matchParentIRE.replaceSubExpression(var1.matchIRE, var5)) {
            var1.optimizedExpression = var1.exp0;
            return true;
         } else {
            return false;
         }
      }
   }

   public int searchAndReplaceAll() {
      return this.searchAndReplaceAll(null);
   }

   public int searchAndReplaceAll(EPatternMatcher.Result[] var1) {
      int var2 = 0;
      EPatternMatcher.Result var3 = null;

      while (true) {
         var3 = this.searchAndReplace(var3);
         if (var3 == null) {
            return var2;
         }

         if (var1 != null) {
            var1[0] = var3;
         }

         var2++;
      }
   }

   public long getMatchAddress(EPatternMatcher.Result var1, int var2) {
      EPatternCompiler.EPattern.P var3 = this.pattern.getInput(var1.getInputIndex());
      if (var2 < 0) {
         var2 += var3.getCountOfLines();
      }

      Couple var4 = this.cfg.getInstructionLocation(var1.getMatchAddress());
      int var5 = (Integer)var4.getSecond() + var2;
      return ((BasicBlock)var4.getFirst()).getAddressOfInstruction(var5);
   }

   public static class Result {
      private IERoutineContext ctx;
      private CFG cfg;
      private IEGeneric exp0;
      private EPatternCompiler.EPattern pattern;
      private int ipattern0;
      private int iblk0;
      private int iinsn0;
      private int inputIndex;
      private long matchAddress;
      private IEGeneric matchIRE;
      private IEGeneric matchParentIRE;
      private Map matchMap;
      private boolean replaced;
      private IEGeneric optimizedExpression;

      Result(IERoutineContext var1, CFG var2, IEGeneric var3, EPatternCompiler.EPattern var4, int var5, Map var6) {
         this.ctx = var1;
         this.cfg = var2;
         this.exp0 = var3;
         this.pattern = var4;
         this.inputIndex = var5;
         this.matchMap = var6;
      }

      public IERoutineContext getContext() {
         return this.ctx;
      }

      public CFG getCfg() {
         return this.cfg;
      }

      public IEGeneric getInputExpression() {
         return this.exp0;
      }

      public EPatternCompiler.EPattern getPattern() {
         return this.pattern;
      }

      public int getInputIndex() {
         return this.inputIndex;
      }

      public IEGeneric getMatchedExpression() {
         return this.matchIRE;
      }

      public IEGeneric getMatchedParent() {
         return this.matchParentIRE;
      }

      public long getMatchAddress() {
         return this.matchAddress;
      }

      public Map getMatchMap() {
         return this.matchMap;
      }

      public Object getMatchedLeaf(int var1, Class var2) {
         return this.matchMap.get(var1);
      }

      public IEGeneric getMatchedLeaf(int var1) {
         return (IEGeneric)this.matchMap.get(var1);
      }

      public boolean isReplaced() {
         return this.replaced;
      }

      public IEGeneric getOptimizedExpression() {
         return this.optimizedExpression;
      }

      @Override
      public String toString() {
         return Strings.ff("Input #%d found at address 0x%X, matchMap: %s", this.inputIndex, this.matchAddress, this.matchMap);
      }
   }
}
