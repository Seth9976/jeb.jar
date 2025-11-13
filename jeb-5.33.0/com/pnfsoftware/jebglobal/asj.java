package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.units.code.IdRanges;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IENop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.core.units.code.asm.type.IAliasType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IArrayType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IEnumerationType;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrimitiveType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureTypeField;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class asj extends AbstractEBlockOptimizer {
   private static final StructuredLogger pC = aco.pC(asj.class);

   public asj() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   protected int optimizeBlock(BasicBlock var1) {
      int var2 = 0;
      int var3 = 0;

      for (IEStatement var5 : var1) {
         asj.Av var6 = this.pC(var5);
         if (var6 != null) {
            int var7 = this.pC(var6);
            if (var7 > 1) {
               Map var8 = this.pC(var1, var6, var3, var7);
               if (var8.size() > 1 && this.pC(var8) == var7 && var5 instanceof IEAssign) {
                  IEAssign var9 = this.pC((IEAssign)var5, var8.values());
                  if (var9 != null) {
                     var1.set(var3, var9);
                     var2++;

                     for (int var11 : var8.keySet()) {
                        if (var11 != var3) {
                           IEStatement var12 = (IEStatement)var1.get(var11);
                           Object[] var10000 = new Object[]{var1.getAddressOfInstruction(var11), var12};
                           IENop var13 = this.ectx.createNop(var12);
                           var1.set(var11, var13);
                           var2++;
                        }
                     }
                  }
               }
            }
         }

         var3++;
      }

      return var2;
   }

   private asj.Av pC(IEStatement var1) {
      if (var1 instanceof IEAssign) {
         asj.Av var2 = new asj.Av();
         var2.E = (IEAssign)var1;
         IEGeneric var3 = ((IEAssign)var1).getLeftOperand();
         var2.UT = ((IEAssign)var1).getRightOperand();
         if (var3 instanceof IEMem) {
            var2.kS = var3.getBitsize();
            if (var2.kS != 8) {
               return null;
            }

            IEGeneric var4 = ((IEMem)var3).getReference();
            if (var4 instanceof IEVar) {
               var2.pC = (IEVar)var4;
               var2.A = 0;
            } else if (EUtil.isOperation(var4, OperationType.ADD, OperationType.SUB)) {
               IEGeneric var5 = ((IEOperation)var4).getOperand1();
               IEGeneric var6 = ((IEOperation)var4).getOperand2();
               if (!(var5 instanceof IEVar)) {
                  return null;
               }

               if (var6 == null || !(var6 instanceof IEImm)) {
                  return null;
               }

               var2.pC = (IEVar)var5;
               BigInteger var7 = ((IEImm)var6).getValue();
               if (var7.signum() == -1 || var7.bitLength() > 32) {
                  return null;
               }

               var2.A = ((IEOperation)var4).getOperationType() == OperationType.ADD ? var7.intValue() : -var7.intValue();
            }

            if (var2.kS == 8 && var2.pC != null && ((aku)var2.pC).getType() != null) {
               INativeType var8 = ((aku)var2.pC).getType().getNativeType();
               if (var8 instanceof IReferenceType var9) {
                  var2.wS = var9.getMainType();
                  return var2;
               }

               if (var8 == null && this.getMasterOptimizerSafe().getMode().isAggressive()) {
                  return var2;
               }
            }
         }
      }

      return null;
   }

   private boolean pC(IEStatement var1, asj.Av var2) {
      if (var1 instanceof IEAssign) {
         IEGeneric var3 = ((IEAssign)var1).getLeftOperand();
         if (var3 instanceof IEMem) {
            IEGeneric var4 = ((IEMem)var3).getReference();
            if (var4 instanceof IEVar) {
               return var2.pC.equals(var4);
            }

            if (var4 instanceof IEOperation && ((IEOperation)var4).getOperationType() == OperationType.ADD) {
               IEGeneric var5 = ((IEOperation)var4).getOperand1();
               IEGeneric var6 = ((IEOperation)var4).getOperand2();
               if (!(var5 instanceof IEVar)) {
                  return false;
               }

               if (var6 != null && var6 instanceof IEImm) {
                  return var2.pC.equals(var5);
               }

               return false;
            }
         }
      }

      return false;
   }

   private int pC(asj.Av var1) {
      return var1.wS != null && var1.wS.getSize() != 0 ? this.pC(var1.wS, var1.A, var1.kS) : this.ectx.getGlobalContext().getAddressBitsize() / 8;
   }

   private int pC(INativeType var1, int var2, int var3) {
      if (var1 instanceof IStructureType) {
         if (var2 >= var1.getSize()) {
            var2 %= var1.getSize();
         }

         IStructureType var7 = (IStructureType)var1;
         IStructureTypeField var5 = var7.getFieldOver(var2);
         return var5 == null ? -1 : this.pC(var5.getType(), var2 - var5.getOffset(), var3);
      } else if (var1 instanceof IArrayType var6) {
         return this.pC(var6.getElementType(), var2 % var6.getElementType().getSize(), var3);
      } else if (var1 instanceof IAliasType var4) {
         return this.pC(var4.getAliasedType(), var2, var3);
      } else {
         if ((var1 instanceof IPrimitiveType || var1 instanceof IReferenceType || var1 instanceof IEnumerationType) && var2 == 0) {
            if (var3 < var1.getSize() * 8) {
               return var1.getSize();
            }

            if (var3 > var1.getSize() * 8) {
               pC.catchingSilent(new JebRuntimeException("typed data is less that written data for " + this.ectx.getRoutine()));
            }
         }

         return -1;
      }
   }

   private int pC(Map var1) {
      int var2 = 0;

      for (asj.Av var4 : var1.values()) {
         var2 += var4.UT.getBitsize() / 8;
      }

      return var2;
   }

   private Map pC(BasicBlock var1, asj.Av var2, int var3, int var4) {
      HashMap var5 = new HashMap();
      ArrayList var6 = new ArrayList();
      var5.put(var3, var2);

      for (int var7 = var3 + 1; this.pC(var5) < var4 && var7 < var1.size(); var7++) {
         boolean var8 = this.pC(var1, var2, var3, var4, var7, var5, var6);
         if (!var8 || this.pC(var5) == var4) {
            break;
         }
      }

      if (this.pC(var5) != var4) {
         for (int var9 = var3 - 1; this.pC(var5) < var4 && var9 >= 0; var9--) {
            boolean var10 = this.pC(var1, var2, var3, var4, var9, var5, var6);
            if (!var10 || this.pC(var5) == var4) {
               break;
            }
         }
      }

      return var5;
   }

   private boolean pC(BasicBlock var1, asj.Av var2, int var3, int var4, int var5, Map var6, List var7) {
      asj.Av var8 = this.A((IEStatement)var1.get(var5), var2);
      if (var8 != null) {
         if (var8.A > var2.A && var8.A < var2.A + var4) {
            var6.put(var5, var8);
         }
      } else if (!(var1.get(var5) instanceof IENop) && !this.pC((IEStatement)var1.get(var5), var2)) {
         ArrayList var9 = new ArrayList();
         ((IEStatement)var1.get(var5)).getDefUse(var9, new ArrayList());
         if (var7.isEmpty()) {
            ((IEStatement)var1.get(var3)).getDefUse(var7, var7);
            if (var7.isEmpty()) {
               var7.add(null);
            }
         }

         if (!CollectionUtil.intersect(var7, var9).isEmpty()) {
            return false;
         }

         if (!(var1.get(var5) instanceof IEAssign)) {
            return false;
         }

         if (((IEAssign)var1.get(var5)).getLeftOperand() instanceof IEMem) {
            return false;
         }
      }

      return true;
   }

   private asj.Av A(IEStatement var1, asj.Av var2) {
      asj.Av var3 = this.pC(var1);
      return var3 != null && var2.pC.equals(var3.pC) ? var3 : null;
   }

   private IEAssign pC(IEAssign var1, Collection var2) {
      TreeMap var3 = new TreeMap();

      for (asj.Av var5 : var2) {
         if (var3.get(var5.A) != null) {
            return null;
         }

         var3.put(var5.A, var5);
      }

      IEGeneric var11 = var1.getLeftOperand();
      var1.getRightOperand();
      if (!(var11 instanceof IEMem)) {
         return null;
      } else {
         IEMem var12 = this.ectx.createMem(((IEMem)var11).getReference(), this.pC(var3) * 8);
         ArrayList var6 = new ArrayList();
         IdRanges var7 = var12.getUsed();

         for (Entry var9 : var3.entrySet()) {
            IdRanges var10 = ((asj.Av)var9.getValue()).UT.getUsed();
            if (var7.hasIntersection(var10)) {
               return null;
            }

            if (!this.ectx.getGlobalContext().isBigEndian()) {
               var6.add(((asj.Av)var9.getValue()).UT);
            } else {
               var6.add(0, ((asj.Av)var9.getValue()).UT);
            }
         }

         IECompose var13 = this.ectx.createCompose(var6);
         IEAssign var14 = this.ectx.createAssign(var12.duplicate(), var13.duplicate());

         for (Entry var16 : var3.entrySet()) {
            var14.copyLowerLevelAddresses(((asj.Av)var16.getValue()).E);
         }

         var14.adjustSize(var1.getSize() - var14.getSize());
         return var14;
      }
   }

   private static class Av {
      private IEVar pC;
      private int A = 0;
      private int kS;
      private INativeType wS;
      private IEGeneric UT;
      private IEAssign E;
   }
}
