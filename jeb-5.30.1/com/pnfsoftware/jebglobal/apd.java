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

public class apd extends AbstractEOptimizer {
   private static final StructuredLogger q = aeg.q(apd.class);
   private boolean RF;
   private IDFA xK;
   private Map Dw = new HashMap();
   private Map Uv = new HashMap();
   private MultiMap oW;
   private int gO;
   private Map nf;
   private static final int gP = 0;
   private static final int za = 1;
   private static final int lm = 2;
   private static final int zz = 3;
   private static final int JY = 4;
   private static final int HF = 5;
   private static final int LK = 9;
   private static final int io = 6;
   private static final int qa = 7;
   private static final int Hk = 8;

   public apd() {
      super(DataChainsUpdatePolicy.UPDATE_PERFORMED_INTERNALLY);
   }

   @Override
   public int perform() {
      this.q();
      long var1 = System.currentTimeMillis();

      int var4;
      try {
         int var3 = this.RF();
         Object[] var12 = new Object[]{var3};
         var4 = this.xK();
      } finally {
         long var8 = System.currentTimeMillis() - var1;
         if (var8 >= 1000L) {
            Object[] var10000 = new Object[]{aeb.q(var8)};
         }
      }

      return var4;
   }

   private void q() {
      this.RF = false;
      this.xK = null;
      this.Dw = new HashMap();
      this.Uv = new HashMap();
      this.oW = null;
      this.gO = 0;
      this.nf = new HashMap();
   }

   private Collection q(long var1, int var3) {
      return this.xK.getDefUses(var1, var3);
   }

   private Collection RF(long var1, int var3) {
      return this.xK.getUseDefs(var1, var3);
   }

   private Collection q(int var1) {
      Collection var2 = (Collection)this.Uv.get(var1);
      if (var2 == null) {
         var2 = this.xK.getInputMap(var1);
         this.Uv.put(var1, var2);
      }

      return var2;
   }

   private Collection RF(int var1) {
      Collection var2 = (Collection)this.Dw.get(var1);
      if (var2 == null) {
         var2 = this.xK.getOutputMap(var1);
         this.Dw.put(var1, var2);
      }

      return var2;
   }

   private int RF() {
      IDFA var1 = this.cfg.doDataFlowAnalysis();
      int var2 = 0;

      for (AddressableInstruction var4 : this.cfg.addressableInstructions()) {
         IEStatement var5 = (IEStatement)var4.getInstruction();
         long var6 = var4.getOffset();
         if (var5 instanceof IEAssign && ((IEAssign)var5).getLeftOperand() instanceof IEVar) {
            IEVar var8 = (IEVar)((IEAssign)var5).getLeftOperand();
            IEGeneric var9 = ((IEAssign)var5).getRightOperand();
            if (var9 instanceof IECompose && ((IECompose)var9).getPartsCount() == 2) {
               IECompose var10 = (IECompose)var9;
               if (var10.getHighPart() instanceof IESlice) {
                  IESlice var11 = (IESlice)var10.getHighPart();
                  if (var11.getWholeExpression() == var8
                     && var11.getBitStart() > 0
                     && var11.getBitEnd() == var8.getBitsize()
                     && EUtil.getUsedVarIds(var10.getLowPart()).contains(var8.getId())) {
                     IEGeneric var12 = var10.getLowPart();
                     IEGeneric var13 = this.q(var6, var12, var8, var1);
                     if (var13 != null) {
                        var2 += var12.replaceVar(var8, var13);
                     }
                  }
               }
            }
         }
      }

      if (var2 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var2;
   }

   private IEGeneric q(long var1, IEGeneric var3, IEVar var4, IDFA var5) {
      Collection var6 = var5.getUseDefs(var1, var4.getId());
      if (var6.size() == 1) {
         long var7 = (Long)var6.iterator().next();
         if (var7 != -1L) {
            IEStatement var9 = (IEStatement)this.cfg.getInstruction(var7);
            if (var7 + var9.getSize() == var1 && var9 instanceof IEAssign && ((IEAssign)var9).getDstOperand() == var4) {
               IEGeneric var10 = ((IEAssign)var9).getSrcOperand();
               if (!EUtil.getUsedVarIds(var10).contains(var4.getId())) {
                  return var10;
               }
            }
         }
      }

      return null;
   }

   private int xK() {
      long var2 = 0L;

      boolean var1;
      do {
         this.cfg = this.ectx.getCfg();
         var1 = false;

         for (AddressableInstruction var5 : this.cfg.addressableInstructions()) {
            IEStatement var6 = (IEStatement)var5.getInstruction();
            long var7 = var5.getOffset();
            if (var7 >= var2) {
               boolean var9 = var6.visitDepthPre(new ape(this, var7));
               if (!var9) {
                  Object[] var10000 = new Object[0];
                  var1 = true;
                  var2 = var7;
                  break;
               }
            }
         }
      } while (var1);

      return this.gO;
   }

   private void Dw() {
      if (!this.RF) {
         this.xK = this.cfg.doDataFlowAnalysis();
         if (this.xK instanceof DFA4) {
            ((DFA4)this.xK).setCacheConfiguration(2);
         }

         this.Dw.clear();
         this.Uv.clear();
         this.oW = new MultiMap();
         this.RF = true;
      }
   }

   private boolean q(long var1, IEVar var3, int var4, IWildcardType var5) {
      if (!MathUtil.isPowerOfTwo(var4)) {
         return false;
      } else {
         IEStatement var10000 = (IEStatement)this.cfg.getInstruction(var1);
         boolean var7 = false;
         this.Dw();
         boolean var8 = false;
         TreeMap var9 = new TreeMap();
         int var10 = 0;
         ArrayDeque var11 = new ArrayDeque();
         HashSet var12 = new HashSet();
         ArrayList var13 = new ArrayList();
         apd.CU var14 = new apd.CU(var1, var7);
         var11.add(var14);
         var12.add(var14);
         int var15 = var3.getId();
         Collection var16 = null;

         label162:
         while (!var11.isEmpty()) {
            var14 = (apd.CU)var11.pop();
            long var17 = var14.q;
            var7 = var14.RF;
            IEStatement var6 = (IEStatement)this.cfg.getInstruction(var17);

            for (Couple var20 : this.oW.getSafe(var14)) {
               if (var20.getFirst() == var3 && ((Integer)var20.getSecond()).equals(var4)) {
                  var8 = true;
                  break label162;
               }
            }

            boolean var32 = var7;
            boolean var34 = !var7;
            apd.nI var21 = null;
            if (!var7 && var6 instanceof IEAssign && var6.asAssign().getDstOperand() == var3) {
               var32 = true;
               var34 = true;
            } else {
               var21 = (apd.nI)var9.get((int)var17);
               var21 = this.q(var17, var3, var4, var7, var21);
               var13.add(var14);
               if (var21 == null) {
                  var8 = true;
                  break;
               }
            }

            if (var21 != null) {
               if (var21.RF == 0 && var21.Dw == null) {
                  apd.CU var22 = new apd.CU(var17, !var7);
                  var11.remove(var22);
                  var12.add(var22);
                  var32 = true;
                  var34 = true;
               }

               if (var21.RF >= 0) {
                  var9.put((int)var17, var21);
                  var10 += var21.q();
               }
            }

            if (var32) {
               Collection var37;
               if (var17 == -1L) {
                  var37 = this.q(var15);
               } else {
                  var37 = this.q(var17, var15);
               }

               if (var37 != null) {
                  for (long var24 : var37) {
                     apd.CU var26 = new apd.CU(var24, false);
                     if (!var12.contains(var26)) {
                        var11.add(var26);
                        var12.add(var26);
                     }
                  }
               }

               if (var16 == null) {
                  var16 = this.RF(var15);
               }

               if (var16 != null && var16.contains(var17)) {
                  for (long var41 : var16) {
                     apd.CU var43 = new apd.CU(var41, true);
                     if (!var12.contains(var43)) {
                        var11.add(var43);
                        var12.add(var43);
                     }
                  }
               }
            }

            if (var34) {
               Collection var38 = this.RF(var17, var15);
               if (var38 != null) {
                  for (long var42 : var38) {
                     apd.CU var44 = new apd.CU(var42, true);
                     if (!var12.contains(var44)) {
                        var11.add(var44);
                        var12.add(var44);
                     }
                  }
               }
            }
         }

         if (var8) {
            for (apd.CU var31 : var13) {
               this.oW.put(var31, new Couple(var3, var4));
            }

            return false;
         } else if (var9.isEmpty()) {
            Object[] var49 = new Object[0];
            return false;
         } else {
            Object[] var45 = new Object[]{var9, var10};
            if (var10 <= 0) {
               Object[] var48 = new Object[0];
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
                  Object[] var46 = new Object[]{var3, var18, var33};
                  this.nf.put(var3, var29);
                  apd.eo var35 = new apd.eo(this.ectx, var18, var33, var9);
                  if (!var35.process(false, false, true)) {
                     return false;
                  } else {
                     this.RF = false;
                     this.gO++;
                     Object[] var47 = new Object[]{var1, var3};
                     return true;
                  }
               }
            }
         }
      }
   }

   private apd.nI q(long var1, IEVar var3, int var4, boolean var5, apd.nI var6) {
      if (var6 == null) {
         var6 = new apd.nI(-1, var1);
      }

      if (var1 == -1L) {
         return var6;
      } else {
         IEStatement var7 = (IEStatement)this.cfg.getInstruction(var1);
         if (var5) {
            if (var7 instanceof IEAssign && ((IEAssign)var7).getLeftOperand() == var3) {
               IEGeneric var8 = ((IEAssign)var7).getRightOperand();
               if (var8 instanceof IECompose && ((IECompose)var8).getPartsCount() == 2) {
                  IECompose var9 = (IECompose)var8;
                  boolean var10 = false;
                  IEVar var11 = null;
                  if (var9.getHighPart() instanceof IESlice) {
                     IESlice var12 = (IESlice)var9.getHighPart();
                     if (var12.getBitStart() == var4 && var12.getBitEnd() == var3.getBitsize() && var12.getWholeExpression() instanceof IEVar) {
                        var11 = (IEVar)var12.getWholeExpression();
                        var10 = this.q(var11, var3);
                     }
                  } else if (var9.getHighPart() instanceof IEVar) {
                     IEVar var28 = var9.getHighPart().asVar();
                     var10 = this.q(var28, var3, var4);
                  }

                  if (var10) {
                     IEGeneric var29 = var9.getLowPart();
                     if (!EUtil.getUsedVarIds(var29).contains(var3.getId())) {
                        var10 = true;
                     } else {
                        var10 = this.q(var29, var6, var3, var4);
                     }

                     if (var10) {
                        var6.RF = 0;
                        var6.xK = var9.getLowPart();
                        if (var11 == var3) {
                           var6.Dw = null;
                        } else {
                           var6.Dw = var9.getHighPart();
                        }

                        return var6;
                     }
                  }
               }
            }

            if (var7 instanceof IECall var15) {
               if (var15.getCountOfReturns() == 1) {
                  IEGeneric var20 = var15.getReturnExpression(0);
                  if (var20 == var3) {
                     IWildcardType var24 = var20.getType();
                     if (var24 != null && var24.getEffectiveBitsize() == var4) {
                        var6.RF = 9;
                     } else {
                        var6.RF = 5;
                     }

                     var6.xK = var7;
                     return var6;
                  }
               }

               return null;
            }

            if (!(var7 instanceof IEAssign) || ((IEAssign)var7).getLeftOperand() != var3) {
               return null;
            }

            IEGeneric var14 = ((IEAssign)var7).getRightOperand();
            if (var14 instanceof IEImm) {
               var6.RF = 1;
               var6.xK = var14;
               if (EUtil.isZero(var14.slice(var4))) {
                  var6.oW = 1;
               }

               return var6;
            }

            if (var14 instanceof IECond var19) {
               if (var19.getExpressionTrue() instanceof IEImm
                  && var19.getExpressionFalse() instanceof IEImm
                  && !EUtil.getUsedVarIds(var19.getCondition()).contains(var3.getId())) {
                  var6.RF = 1;
                  var6.xK = var14;
                  return var6;
               }

               return null;
            }

            if (var14 instanceof IEMem var18) {
               if (!EUtil.getUsedVarIds(var18.getReference()).contains(var3.getId())) {
                  var6.RF = 1;
                  var6.xK = var14;
                  return var6;
               }

               return null;
            }

            if (this.q(var14, var3, var4, var6)) {
               return var6;
            }

            if (var14 instanceof IEOperation && ((IEOperation)var14).getOperationType().isAnyOf(OperationType.ADD, OperationType.SUB, OperationType.MUL)) {
               IEGeneric var17 = ((IEOperation)var14).getOperand1();
               if (this.q(var17, var3, var4, var6)) {
                  IEGeneric var23 = ((IEOperation)var14).getOperand2();
                  if (this.q(var23, var3, var4)) {
                     var6.RF = 6;
                     return var6;
                  }
               }

               return null;
            }
         } else {
            if (var7 instanceof IEAssign) {
               IEAssign var16 = var7.asAssign();
               IEGeneric var21 = var16.getRightOperand();
               IEGeneric var25 = var16.getLeftOperand();
               if (var21 == var3 && !EUtil.getUsedVarIds(var25).contains(var3.getId())) {
                  var6.RF = 2;
                  var6.Dw = var25;
                  return var6;
               }

               if (var21 instanceof IECompose) {
                  IECompose var26 = var21.asCompose();
                  if (var26.getPartsCount() == 2 && var26.getHighPart() instanceof IESlice) {
                     IESlice var30 = (IESlice)var26.getHighPart();
                     if (var30.getWholeExpression() == var3 && var30.getBitStart() == var4 && var30.getBitEnd() == var3.getBitsize()) {
                        IEGeneric var13 = var26.getLowPart();
                        if (!EUtil.getUsedVarIds(var13).contains(var3.getId())) {
                           var6.RF = 7;
                           if (var25 == var3) {
                              var6.oW = 1;
                           }

                           return var6;
                        }
                     }
                  }
               }

               if (var21 instanceof IESlice) {
                  IESlice var27 = var21.asSlice();
                  if (var27.getWholeExpression() == var3
                     && var27.getBitStart() == var4
                     && var27.getBitEnd() == var3.getBitsize()
                     && !EUtil.getUsedVarIds(var25).contains(var3.getId())) {
                     var6.RF = 8;
                     return var6;
                  }
               }
            }

            if (this.q(var7, var6, var3, var4)) {
               if (var6.RF < 0) {
                  var6.RF = 3;
               }

               return var6;
            }
         }

         return null;
      }
   }

   private boolean q(IEVar var1, IEVar var2) {
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

   private boolean q(IEVar var1, IEVar var2, int var3) {
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

   private boolean q(IEGeneric var1, IEVar var2, int var3, apd.nI var4) {
      if (var1 instanceof IECompose && ((IECompose)var1).getPartsCount() == 2) {
         IECompose var5 = (IECompose)var1;
         if (var5.getLowPart().getBitsize() == var3 && EUtil.isImmZero(var5.getHighPart()) && this.q(var5.getLowPart(), var2, var3)) {
            var4.RF = 4;
            var4.xK = var5.getLowPart();
            return true;
         }
      }

      return false;
   }

   private boolean q(IEGeneric var1, IEVar var2, int var3) {
      return var1.visitDepthPre(new apf(this, var2, var3));
   }

   private boolean q(IEGeneric var1, apd.nI var2, IEVar var3, int var4) {
      EVisitResults var5 = new EVisitResults(2);
      return var1.visitDepthPre(new apg(this, var3, var4, var2), null, var5);
   }

   private static class CU {
      long q;
      boolean RF;

      public CU(long var1, boolean var3) {
         this.q = var1;
         this.RF = var3;
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + (int)(this.q ^ this.q >>> 32);
         return 31 * var1 + (this.RF ? 1231 : 1237);
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
            apd.CU var2 = (apd.CU)var1;
            return this.q != var2.q ? false : this.RF == var2.RF;
         }
      }

      @Override
      public String toString() {
         return Strings.ff("%Xh[def=%b]", this.q, this.RF);
      }
   }

   private static class eo extends aoe {
      IEVar q;
      IEVar RF;
      Map xK;

      public eo(IERoutineContext var1, IEVar var2, IEVar var3, Map var4) {
         super(var1);
         this.q = var2;
         this.RF = var3;
         this.xK = var4;
      }

      @Override
      public IEStatement[] processStatement(IEStatement var1, int var2) {
         apd.nI var3 = (apd.nI)this.xK.get(var2);
         if (var3 == null) {
            return new IEStatement[]{var1};
         } else {
            for (Couple var5 : var3.Uv) {
               IESlice var6 = (IESlice)var5.getSecond();
               if (!((IEGeneric)var5.getFirst()).replaceSubExpression(var6, this.q.slice(var6.getRange()))) {
                  throw new RuntimeException("Replacement by small var failed");
               }
            }

            IERoutineContext var11 = this.getContext();
            switch (var3.RF) {
               case 0:
                  IEAssign var20 = var11.createAssign(this.q, var3.xK);
                  var20.copyProperties(var1);
                  if (var3.Dw == null) {
                     return new IEStatement[]{var20};
                  }

                  IEAssign var26 = var11.createAssign(this.RF, var3.Dw);
                  var26.copyProperties(var1);
                  return new IEStatement[]{var20, var26};
               case 1:
                  IEAssign var19 = var11.createAssign(this.q, var3.xK.slice(0, this.q.getBitsize()));
                  var19.copyProperties(var1);
                  IEAssign var25 = var11.createAssign(this.RF, var3.xK.duplicate().slice(this.q.getBitsize(), var3.xK.getBitsize()));
                  var25.copyProperties(var1);
                  return new IEStatement[]{var19, var25};
               case 2:
                  IEAssign var18 = var11.createAssign(var3.Dw, var11.createCompose(this.q, this.RF));
                  var18.copyProperties(var1);
                  return new IEStatement[]{var18};
               case 3:
                  return new IEStatement[]{var1};
               case 4:
                  IEAssign var17 = var11.createAssign(this.q, var3.xK);
                  var17.copyProperties(var1);
                  IEAssign var24 = var11.createAssign(this.RF, EUtil.zero(this.RF.getBitsize()));
                  var24.copyProperties(var1);
                  return new IEStatement[]{var17, var24};
               case 5:
                  IECall var16 = (IECall)var1;
                  IEGeneric var23 = var16.getReturnExpression(0);
                  IEVar var28 = var11.createVar("tmp@" + var2, var23.getBitsize());
                  var16.setReturnExpression(0, var28);
                  IEAssign var29 = var11.createAssign(this.q, var28.slice(0, this.q.getBitsize()));
                  var29.copyProperties(var1);
                  var29.setFlags(1);
                  IEAssign var30 = var11.createAssign(this.RF, var28.duplicate().slice(this.q.getBitsize(), var28.getBitsize()));
                  var30.copyProperties(var1);
                  var30.setFlags(1);
                  return new IEStatement[]{var16, var29, var30};
               case 6:
                  IEAssign var15 = (IEAssign)var1;
                  IEOperation var22 = (IEOperation)var15.getSrcOperand();
                  OperationType var27 = var22.getOperationType();
                  int var8 = var3.xK.getBitsize();
                  IEAssign var9 = var11.createAssign(this.q, var11.createOperation(var27, var3.xK, var22.getOperand2().part(var8)));
                  var9.copyProperties(var1);
                  IEAssign var10 = var11.createAssign(this.RF, var22.duplicate().slice(var8));
                  var10.copyProperties(var1);
                  return new IEStatement[]{var9, var10};
               case 7:
                  IECompose var14 = var1.asAssign().getRightOperand().asCompose();
                  var14.replacePart(1, this.RF);
                  return new IEStatement[]{var1};
               case 8:
                  IEAssign var13 = var1.asAssign();
                  var13.replaceSubExpression(var13.getRightOperand(), this.RF);
                  return new IEStatement[]{var1};
               case 9:
                  IECall var12 = (IECall)var1;
                  IEGeneric var21 = var12.getReturnExpression(0);
                  if (var21.getType() != null && var21.getType().getEffectiveBitsize() != 0) {
                     int var7 = var21.getType().getEffectiveBitsize();
                     if (var7 == this.q.getBitsize()) {
                        var12.setReturnExpression(0, this.q);
                        return new IEStatement[]{var12};
                     }
                  }

                  throw new RuntimeException();
               default:
                  throw new RuntimeException("Unknown record type for SPO: " + var3.RF);
            }
         }
      }
   }

   private static class nI {
      long q;
      int RF;
      IEGeneric xK;
      IEGeneric Dw;
      List Uv = new ArrayList();
      int oW;

      nI(int var1, long var2) {
         this.RF = var1;
         this.q = var2;
      }

      int q() {
         int var1 = this.Uv.size() + this.oW;
         switch (this.RF) {
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
         Strings.ff(var1, "0x%X:type=%d", this.q, this.RF);
         if (!this.Uv.isEmpty()) {
            Strings.ff(var1, "(entries:%d)", this.Uv.size());
         }

         if (this.oW != 0) {
            Strings.ff(var1, "(boost:%d)", this.oW);
         }

         return var1.toString();
      }
   }
}
