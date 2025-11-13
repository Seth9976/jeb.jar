package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.DFA4;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.VarSrc;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.MultiMap;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.math.MathUtil;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class amw extends AbstractEOptimizer {
   private static final StructuredLogger pC = aco.pC(amw.class);
   private boolean A;
   private IDFA kS;
   private Map wS = new HashMap();
   private Map UT = new HashMap();
   private MultiMap E;
   private int sY;
   private Map ys;

   public amw() {
      super(DataChainsUpdatePolicy.UPDATE_PERFORMED_INTERNALLY);
   }

   @Override
   public int perform() {
      this.pC();
      long var1 = System.currentTimeMillis();

      int var4;
      try {
         int var3 = this.A();
         Object[] var12 = new Object[]{var3};
         var4 = this.kS();
      } finally {
         long var8 = System.currentTimeMillis() - var1;
         if (var8 >= 1000L) {
            Object[] var10000 = new Object[]{acj.pC(var8)};
         }
      }

      return var4;
   }

   private void pC() {
      this.A = false;
      this.kS = null;
      this.wS = new HashMap();
      this.UT = new HashMap();
      this.E = null;
      this.sY = 0;
      this.ys = new HashMap();
   }

   private Collection pC(int var1) {
      Collection var2 = (Collection)this.UT.get(var1);
      if (var2 == null) {
         var2 = this.kS.getInputMap(var1);
         this.UT.put(var1, var2);
      }

      return var2;
   }

   private Collection A(int var1) {
      Collection var2 = (Collection)this.wS.get(var1);
      if (var2 == null) {
         var2 = this.kS.getOutputMap(var1);
         this.wS.put(var1, var2);
      }

      return var2;
   }

   private int A() {
      IDFA var1 = this.cfg.doDataFlowAnalysis();
      int var2 = 0;

      for (AddressableInstruction var4 : this.cfg.addressableInstructions()) {
         IEStatement var5 = (IEStatement)var4.getInstruction();
         long var6 = var4.getOffset();
         if (var5 instanceof IEAssign var8
            && var8.getDstOperand() instanceof IEVar var9
            && var8.getSrcOperand() instanceof IECompose var10
            && var10.getPartsCount() == 2
            && var10.getHighPart() instanceof IESlice var11
            && var11.getWholeExpression() == var9
            && var11.getBitStart() > 0
            && var11.getBitEnd() == var9.getBitsize()
            && EUtil.getUsedVarIds(var10.getLowPart()).contains(var9.getId())) {
            IEGeneric var16 = var10.getLowPart();
            IEGeneric var13 = this.pC(var6, var16, var9, var1);
            if (var13 != null) {
               var2 += var16.replaceVar(var9, var13);
            }
         }
      }

      if (var2 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var2;
   }

   private IEGeneric pC(long var1, IEGeneric var3, IEVar var4, IDFA var5) {
      Collection var6 = var5.getUseDefs(var1, var4.getId());
      if (var6.size() == 1) {
         long var7 = (Long)var6.iterator().next();
         if (var7 != -1L) {
            IEStatement var9 = (IEStatement)this.cfg.getInstruction(var7);
            if (var7 + var9.getSize() == var1 && var9 instanceof IEAssign var10 && var10.getDstOperand() == var4) {
               IEGeneric var11 = var10.getSrcOperand();
               if (!EUtil.getUsedVarIds(var11).contains(var4.getId())) {
                  return var11;
               }
            }
         }
      }

      return null;
   }

   private int kS() {
      long var2 = 0L;

      boolean var1;
      do {
         this.cfg = this.ectx.getCfg();
         var1 = false;

         for (AddressableInstruction var5 : this.cfg.addressableInstructions()) {
            IEStatement var6 = (IEStatement)var5.getInstruction();
            long var7 = var5.getOffset();
            if (var7 >= var2) {
               boolean var9 = var6.visitDepthPre(new amx(this, var7));
               if (!var9) {
                  Object[] var10000 = new Object[0];
                  var1 = true;
                  var2 = var7;
                  break;
               }
            }
         }
      } while (var1);

      return this.sY;
   }

   private void wS() {
      if (!this.A) {
         this.kS = this.cfg.doDataFlowAnalysis();
         if (this.kS instanceof DFA4 var1) {
            var1.setCacheConfiguration(2);
         }

         this.wS.clear();
         this.UT.clear();
         this.E = new MultiMap();
         this.A = true;
      }
   }

   private boolean pC(long var1, IEVar var3, int var4, IWildcardType var5) {
      if (!MathUtil.isPowerOfTwo(var4)) {
         return false;
      } else {
         IEStatement var10000 = (IEStatement)this.cfg.getInstruction(var1);
         boolean var7 = false;
         this.wS();
         boolean var8 = false;
         TreeMap var9 = new TreeMap();
         int var10 = 0;
         ArrayDeque var11 = new ArrayDeque();
         HashSet var12 = new HashSet();
         ArrayList var13 = new ArrayList();
         amw.Sv var14 = new amw.Sv(var1, var7);
         var11.add(var14);
         var12.add(var14);
         int var15 = var3.getId();
         Collection var16 = null;

         label162:
         while (!var11.isEmpty()) {
            var14 = (amw.Sv)var11.pop();
            long var17 = var14.pC;
            var7 = var14.A;
            IEStatement var6 = (IEStatement)this.cfg.getInstruction(var17);

            for (Couple var20 : this.E.getSafe(var14)) {
               if (var20.getFirst() == var3 && ((Integer)var20.getSecond()).equals(var4)) {
                  var8 = true;
                  break label162;
               }
            }

            boolean var32 = var7;
            boolean var34 = !var7;
            amw.K var21 = null;
            if (!var7 && var6 instanceof IEAssign var22 && var22.getDstOperand() == var3) {
               var32 = true;
               var34 = true;
            } else {
               var21 = (amw.K)var9.get((int)var17);
               var21 = this.pC(var17, var3, var4, var7, var21);
               var13.add(var14);
               if (var21 == null) {
                  var8 = true;
                  break;
               }
            }

            if (var21 != null) {
               if (var21.A == 0 && var21.wS == null) {
                  amw.Sv var37 = new amw.Sv(var17, !var7);
                  var11.remove(var37);
                  var12.add(var37);
                  var32 = true;
                  var34 = true;
               }

               if (var21.A >= 0) {
                  var9.put((int)var17, var21);
                  var10 += var21.pC();
               }
            }

            if (var32) {
               Collection var38;
               if (var17 == -1L) {
                  var38 = this.pC(var15);
               } else {
                  var38 = this.kS.getDefUses(var17, var15);
               }

               if (var38 != null) {
                  for (long var24 : var38) {
                     amw.Sv var26 = new amw.Sv(var24, false);
                     if (!var12.contains(var26)) {
                        var11.add(var26);
                        var12.add(var26);
                     }
                  }
               }

               if (var16 == null) {
                  var16 = this.A(var15);
               }

               if (var16 != null && var16.contains(var17)) {
                  for (long var42 : var16) {
                     amw.Sv var44 = new amw.Sv(var42, true);
                     if (!var12.contains(var44)) {
                        var11.add(var44);
                        var12.add(var44);
                     }
                  }
               }
            }

            if (var34) {
               Collection var39 = this.kS.getUseDefs(var17, var15);
               if (var39 != null) {
                  for (long var43 : var39) {
                     amw.Sv var45 = new amw.Sv(var43, true);
                     if (!var12.contains(var45)) {
                        var11.add(var45);
                        var12.add(var45);
                     }
                  }
               }
            }
         }

         if (var8) {
            for (amw.Sv var31 : var13) {
               this.E.put(var31, new Couple(var3, var4));
            }

            return false;
         } else if (var9.isEmpty()) {
            Object[] var50 = new Object[0];
            return false;
         } else {
            Object[] var46 = new Object[]{var9, var10};
            if (var10 <= 0) {
               Object[] var49 = new Object[0];
               return false;
            } else {
               Couple var29 = this.ectx.copyTruncatedVariable(var3, var4);
               if (var29 == null) {
                  return false;
               } else {
                  IEVar var18 = (IEVar)var29.getFirst();
                  if (var3.getType() != null && var3.getType().getBitsize() == var18.getBitsize()) {
                     var18.setType(var3.getType());
                  } else if (var5 != null) {
                     var18.setType(var5);
                  }

                  IEVar var33 = (IEVar)var29.getSecond();
                  Object[] var47 = new Object[]{var3, var18, var33};
                  this.ys.put(var3, var29);
                  amw.Av var35 = new amw.Av(this.ectx, var18, var33, var9);
                  if (!var35.process(false, false, true)) {
                     return false;
                  } else {
                     this.A = false;
                     this.sY++;
                     Object[] var48 = new Object[]{var1, var3};
                     return true;
                  }
               }
            }
         }
      }
   }

   private amw.K pC(long var1, IEVar var3, int var4, boolean var5, amw.K var6) {
      if (var6 == null) {
         var6 = new amw.K(-1, var1);
      }

      if (var1 == -1L) {
         return var6;
      } else {
         IEStatement var7 = (IEStatement)this.cfg.getInstruction(var1);
         if (var5) {
            if (var7 instanceof IEAssign var8 && var8.getDstOperand() == var3 && var8.getSrcOperand() instanceof IECompose var10 && var10.getPartsCount() == 2) {
               boolean var11 = false;
               IEVar var12 = null;
               if (var10.getHighPart() instanceof IESlice var13) {
                  if (var13.getBitStart() == var4 && var13.getBitEnd() == var3.getBitsize() && var13.getWholeExpression() instanceof IEVar var38) {
                     var12 = var38;
                     var11 = this.pC(var38, var3);
                  }
               } else if (var10.getHighPart() instanceof IEVar var14) {
                  var11 = this.pC(var14, var3, var4);
               }

               if (var11) {
                  IEGeneric var35 = var10.getLowPart();
                  if (!EUtil.getUsedVarIds(var35).contains(var3.getId())) {
                     var11 = true;
                  } else {
                     var11 = this.pC(var35, var6, var3, var4);
                  }

                  if (var11) {
                     var6.A = 0;
                     var6.kS = var10.getLowPart();
                     if (var12 == var3) {
                        var6.wS = null;
                     } else {
                        var6.wS = var10.getHighPart();
                     }

                     return var6;
                  }
               }
            }

            if (var7 instanceof IECall var18) {
               if (var18.getCountOfReturns() == 1) {
                  IEGeneric var21 = var18.getReturnExpression(0);
                  if (var21 == var3) {
                     IWildcardType var25 = var21.getType();
                     if (var25 != null && var25.getEffectiveBitsize() == var4) {
                        var6.A = 9;
                     } else {
                        var6.A = 5;
                     }

                     var6.kS = var7;
                     return var6;
                  }
               }

               return null;
            }

            if (!(var7 instanceof IEAssign var17) || var17.getDstOperand() != var3) {
               return null;
            }

            IEGeneric var20 = var17.getSrcOperand();
            if (var20 instanceof IEImm var24) {
               var6.A = 1;
               var6.kS = var20;
               if (EUtil.isZero(var24.slice(var4))) {
                  var6.E = 1;
               }

               return var6;
            }

            if (var20 instanceof IECond var29) {
               if (var29.getExpressionTrue() instanceof IEImm
                  && var29.getExpressionFalse() instanceof IEImm
                  && !EUtil.getUsedVarIds(var29.getCondition()).contains(var3.getId())) {
                  var6.A = 1;
                  var6.kS = var20;
                  return var6;
               }

               return null;
            }

            if (var20 instanceof IEMem var33) {
               if (!EUtil.getUsedVarIds(var33.getReference()).contains(var3.getId())) {
                  var6.A = 1;
                  var6.kS = var20;
                  return var6;
               }

               return null;
            }

            if (this.pC(var20, var3, var4, var6)) {
               return var6;
            }

            if (var20 instanceof IEOperation var23 && var23.getOperationType().isAnyOf(OperationType.ADD, OperationType.SUB, OperationType.MUL)) {
               IEGeneric var28 = var23.getOperand1();
               if (this.pC(var28, var3, var4, var6)) {
                  IEGeneric var32 = var23.getOperand2();
                  if (this.pC(var32, var3, var4)) {
                     var6.A = 6;
                     return var6;
                  }
               }

               return null;
            }
         } else {
            if (var7 instanceof IEAssign var19) {
               IEGeneric var22 = var19.getSrcOperand();
               IEGeneric var26 = var19.getDstOperand();
               if (var22 == var3 && !EUtil.getUsedVarIds(var26).contains(var3.getId())) {
                  var6.A = 2;
                  var6.wS = var26;
                  return var6;
               }

               if (var22 instanceof IECompose var30
                  && var30.getPartsCount() == 2
                  && var30.getHighPart() instanceof IESlice var34
                  && var34.getWholeExpression() == var3
                  && var34.getBitStart() == var4
                  && var34.getBitEnd() == var3.getBitsize()) {
                  IEGeneric var37 = var30.getLowPart();
                  if (!EUtil.getUsedVarIds(var37).contains(var3.getId())) {
                     var6.A = 7;
                     if (var26 == var3) {
                        var6.E = 1;
                     }

                     return var6;
                  }
               }

               if (var22 instanceof IESlice var31
                  && var31.getWholeExpression() == var3
                  && var31.getBitStart() == var4
                  && var31.getBitEnd() == var3.getBitsize()
                  && !EUtil.getUsedVarIds(var26).contains(var3.getId())) {
                  var6.A = 8;
                  return var6;
               }
            }

            if (this.pC(var7, var6, var3, var4)) {
               if (var6.A < 0) {
                  var6.A = 3;
               }

               return var6;
            }
         }

         return null;
      }
   }

   private boolean pC(IEVar var1, IEVar var2) {
      if (var1 == var2) {
         return true;
      } else {
         if (var1.getBitsize() == var2.getBitsize()) {
            VarSrc var3 = this.ectx.getSourceForVariable(var2.getId());
            VarSrc var4 = this.ectx.getSourceForVariable(var1.getId());
            if (var3 != null && var3.isDuplicate() && var4 != null && var4.isDuplicate() && var3.getAsDuplicate() == var4.getAsDuplicate()) {
               return true;
            }
         }

         return false;
      }
   }

   private boolean pC(IEVar var1, IEVar var2, int var3) {
      VarSrc var4 = this.ectx.getSourceForVariable(var2.getId());
      VarSrc var5 = this.ectx.getSourceForVariable(var1.getId());
      if (var4 != null && var5 != null && var4.isDuplicate() && var5.isSlice()) {
         int var6 = var4.getAsDuplicate();
         Couple var7 = var5.getAsSlice();
         if (var6 == (Integer)var7.getFirst()) {
            Couple var8 = (Couple)var7.getSecond();
            if ((Integer)var8.getFirst() == var3 && var8.getSecond() == null) {
               return true;
            }
         }
      }

      return false;
   }

   private boolean pC(IEGeneric var1, IEVar var2, int var3, amw.K var4) {
      if (var1 instanceof IECompose var5
         && var5.getPartsCount() == 2
         && var5.getLowPart().getBitsize() == var3
         && EUtil.isImmZero(var5.getHighPart())
         && this.pC(var5.getLowPart(), var2, var3)) {
         var4.A = 4;
         var4.kS = var5.getLowPart();
         return true;
      } else {
         return false;
      }
   }

   private boolean pC(IEGeneric var1, IEVar var2, int var3) {
      return var1.visitDepthPre(new amy(this, var2, var3));
   }

   private boolean pC(IEGeneric var1, amw.K var2, IEVar var3, int var4) {
      EVisitResults var5 = new EVisitResults(2);
      return var1.visitDepthPre(new amz(this, var3, var4, var2), null, var5);
   }

   private static class Av extends alx {
      IEVar pC;
      IEVar A;
      Map kS;

      public Av(IERoutineContext var1, IEVar var2, IEVar var3, Map var4) {
         super(var1);
         this.pC = var2;
         this.A = var3;
         this.kS = var4;
      }

      @Override
      public IEStatement[] processStatement(IEStatement var1, int var2) {
         amw.K var3 = (amw.K)this.kS.get(var2);
         if (var3 == null) {
            return new IEStatement[]{var1};
         } else {
            for (Couple var5 : var3.UT) {
               IESlice var6 = (IESlice)var5.getSecond();
               if (!((IEGeneric)var5.getFirst()).replaceSubExpression(var6, this.pC.slice(var6.getRange()))) {
                  throw new RuntimeException("Replacement by small var failed");
               }
            }

            IERoutineContext var11 = this.getContext();
            switch (var3.A) {
               case 0:
                  IEAssign var20 = var11.createAssign(this.pC, var3.kS);
                  var20.copyProperties(var1);
                  if (var3.wS == null) {
                     return new IEStatement[]{var20};
                  }

                  IEAssign var26 = var11.createAssign(this.A, var3.wS);
                  var26.copyProperties(var1);
                  return new IEStatement[]{var20, var26};
               case 1:
                  IEAssign var19 = var11.createAssign(this.pC, var3.kS.slice(0, this.pC.getBitsize()));
                  var19.copyProperties(var1);
                  IEAssign var25 = var11.createAssign(this.A, var3.kS.duplicate().slice(this.pC.getBitsize(), var3.kS.getBitsize()));
                  var25.copyProperties(var1);
                  return new IEStatement[]{var19, var25};
               case 2:
                  IEAssign var18 = var11.createAssign(var3.wS, var11.createCompose(this.pC, this.A));
                  var18.copyProperties(var1);
                  return new IEStatement[]{var18};
               case 3:
                  return new IEStatement[]{var1};
               case 4:
                  IEAssign var17 = var11.createAssign(this.pC, var3.kS);
                  var17.copyProperties(var1);
                  IEAssign var24 = var11.createAssign(this.A, EUtil.zero(this.A.getBitsize()));
                  var24.copyProperties(var1);
                  return new IEStatement[]{var17, var24};
               case 5:
                  IECall var16 = (IECall)var1;
                  IEGeneric var23 = var16.getReturnExpression(0);
                  IEVar var28 = var11.createVar("tmp@" + var2, var23.getBitsize());
                  var16.setReturnExpression(0, var28);
                  IEAssign var29 = var11.createAssign(this.pC, var28.slice(0, this.pC.getBitsize()));
                  var29.copyProperties(var1);
                  var29.setFlags(1);
                  IEAssign var30 = var11.createAssign(this.A, var28.duplicate().slice(this.pC.getBitsize(), var28.getBitsize()));
                  var30.copyProperties(var1);
                  var30.setFlags(1);
                  return new IEStatement[]{var16, var29, var30};
               case 6:
                  IEAssign var15 = (IEAssign)var1;
                  IEOperation var22 = (IEOperation)var15.getSrcOperand();
                  OperationType var27 = var22.getOperationType();
                  int var8 = var3.kS.getBitsize();
                  IEAssign var9 = var11.createAssign(this.pC, var11.createOperation(var27, var3.kS, var22.getOperand2().part(var8)));
                  var9.copyProperties(var1);
                  IEAssign var10 = var11.createAssign(this.A, var22.duplicate().slice(var8));
                  var10.copyProperties(var1);
                  return new IEStatement[]{var9, var10};
               case 7:
                  IECompose var14 = ((IEAssign)var1).getSrcOperand().asCompose();
                  var14.replacePart(1, this.A);
                  return new IEStatement[]{var1};
               case 8:
                  IEAssign var13 = (IEAssign)var1;
                  var13.replaceSubExpression(var13.getSrcOperand(), this.A);
                  return new IEStatement[]{var1};
               case 9:
                  IECall var12 = (IECall)var1;
                  IEGeneric var21 = var12.getReturnExpression(0);
                  if (var21.getType() != null && var21.getType().getEffectiveBitsize() != 0) {
                     int var7 = var21.getType().getEffectiveBitsize();
                     if (var7 == this.pC.getBitsize()) {
                        var12.setReturnExpression(0, this.pC);
                        return new IEStatement[]{var12};
                     }
                  }

                  throw new RuntimeException();
               default:
                  throw new RuntimeException("Unknown record type for SPO: " + var3.A);
            }
         }
      }
   }

   private static class K {
      long pC;
      int A;
      IEGeneric kS;
      IEGeneric wS;
      List UT = new ArrayList();
      int E;

      K(int var1, long var2) {
         this.A = var1;
         this.pC = var2;
      }

      int pC() {
         int var1 = this.UT.size() + this.E;
         switch (this.A) {
            case 0:
               var1 += 2;
               break;
            case 1:
            case 2:
               var1--;
               break;
            case 3:
            case 4:
            case 6:
            case 7:
            case 8:
               var1 += 0;
               break;
            case 5:
               var1 -= 2;
               break;
            case 9:
               var1++;
               break;
            default:
               var1 += 0;
         }

         return var1;
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         Strings.ff(var1, "0x%X:type=%d", this.pC, this.A);
         if (!this.UT.isEmpty()) {
            Strings.ff(var1, "(entries:%d)", this.UT.size());
         }

         if (this.E != 0) {
            Strings.ff(var1, "(boost:%d)", this.E);
         }

         return var1.toString();
      }
   }

   private static class Sv {
      long pC;
      boolean A;

      public Sv(long var1, boolean var3) {
         this.pC = var1;
         this.A = var3;
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + (int)(this.pC ^ this.pC >>> 32);
         return 31 * var1 + (this.A ? 1231 : 1237);
      }

      @Override
      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (var1 == null) {
            return false;
         } else if (this.getClass() != var1.getClass()) {
            return false;
         } else {
            amw.Sv var2 = (amw.Sv)var1;
            return this.pC != var2.pC ? false : this.A == var2.A;
         }
      }

      @Override
      public String toString() {
         return Strings.ff("%Xh[def=%b]", this.pC, this.A);
      }
   }
}
