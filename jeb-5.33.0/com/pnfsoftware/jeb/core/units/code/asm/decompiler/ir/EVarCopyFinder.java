package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.type.StorageEntry;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.io.Endianness;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public class EVarCopyFinder {
   private IERoutineContext ctx;
   private StorageEntry entry;
   private Collection filters;
   private ELocation loc;
   private boolean done;
   private boolean created;
   private IEVar[] svars;

   public static IEGeneric retrieveVariableForRegister(IERoutineContext var0, IEGeneric var1, Collection var2, boolean var3, Long var4) {
      EVarCopyFinder var5 = new EVarCopyFinder(var0, null, var2, var4);
      return var5.retrieveVariableForRegister(var1, var3);
   }

   public EVarCopyFinder(IERoutineContext var1, StorageEntry var2, Collection var3, Long var4) {
      this.ctx = var1;
      this.entry = var2;
      this.filters = Lists.reverse(new ArrayList(var3));
      this.loc = var4 == null ? null : new ELocation(var1, var4);
   }

   EVarCopyFinder(IERoutineContext var1, StorageEntry var2, Set var3) {
      this(var1, var2, var3, null);
   }

   private void verifyDone(boolean var1) {
      if (this.done != var1) {
         throw new IllegalStateException();
      }
   }

   private void flipDone(boolean var1) {
      this.verifyDone(var1);
      this.done = !var1;
   }

   public IEGeneric getIRForSlicedReg(boolean var1) {
      this.flipDone(false);
      IEGeneric var2 = this.ctx.getConverter().getRegisterVariableFromNativeRegisterId(this.entry.getValue(), this.loc);
      return var2 != null ? this.retrieveVariableForRegister(var2, var1) : null;
   }

   public IEVar getVarForRegPair(boolean var1) {
      this.flipDone(false);
      Endianness var2 = this.ctx.getGlobalContext().isBigEndian() ? Endianness.BIG_ENDIAN : Endianness.LITTLE_ENDIAN;
      IEGeneric var3 = this.ctx.getConverter().getRegisterVariableFromNativeRegisterId(this.entry.getValue(var2), this.loc);
      if (var3 instanceof IEVar) {
         IEGeneric var4 = this.ctx.getConverter().getRegisterVariableFromNativeRegisterId(this.entry.getValue2(var2), this.loc);
         if (var4 instanceof IEVar) {
            return this.retrieveVariableForRegisterPair((IEVar)var3, (IEVar)var4, var1);
         }
      }

      return null;
   }

   private Integer getSlicedVarId(IEGeneric var1) {
      if (var1 instanceof IEVar) {
         return ((IEVar)var1).getId();
      } else {
         return var1 instanceof IESlice && ((IESlice)var1).getWholeExpression() instanceof IEVar ? ((IEVar)((IESlice)var1).getWholeExpression()).getId() : null;
      }
   }

   private IEGeneric genSlicedVar(int var1, IEGeneric var2) {
      IEVar var3 = this.ctx.getVariableById(var1);
      if (var2 instanceof IEVar) {
         return var3;
      } else if (var2 instanceof IESlice) {
         IERange var4 = ((IESlice)var2).getRange();
         return var3.slice(var4);
      } else {
         return null;
      }
   }

   private IEVar copySlicedVariable(IEGeneric var1) {
      if (var1 instanceof IEVar) {
         return this.ctx.copyVariable((IEVar)var1);
      } else {
         IESlice var2 = (IESlice)var1;
         if (var2.getBitStart() != 0) {
            throw new RuntimeException();
         } else {
            return (IEVar)this.ctx.copyTruncatedVariable((IEVar)var2.getWholeExpression(), var2.getBitEnd()).getFirst();
         }
      }
   }

   private IEGeneric retrieveVariableForRegister(IEGeneric var1, boolean var2) {
      Integer var3 = this.getSlicedVarId(var1);
      if (var3 == null) {
         return var1 instanceof IEGroupElt ? var1 : null;
      } else {
         int var4 = var3;
         if (this.filters != null) {
            if (this.filters.contains(var4)) {
               return var1;
            }

            if (var1 instanceof IESlice) {
               IERange var5 = ((IESlice)var1).getRange();

               for (int var7 : this.filters) {
                  VarSrc var8 = this.ctx.getSourceForVariable(var7);
                  if (var8 != null && var8.isSlice()) {
                     Couple var9 = var8.getAsSlice();
                     if ((Integer)var9.getFirst() == var4
                        && (Integer)((Couple)var9.getSecond()).getFirst() == var5.getBegin()
                        && (Integer)((Couple)var9.getSecond()).getSecond() == var5.getEnd()) {
                        return this.ctx.getVariableById(var7);
                     }
                  }
               }
            }

            for (int var11 : this.filters) {
               VarSrc var12 = this.ctx.getSourceForVariable(var11);
               if (var12 != null && var12.containsSourceId(var4)) {
                  if (var12.isDuplicate() && var12.getAsDuplicate() == var4) {
                     return this.genSlicedVar(var11, var1);
                  }

                  if (!var12.isSlice()) {
                     if (var12.isPair()) {
                        int var13 = (Integer)var12.getAsPair().getFirst();
                        if (var13 == var4 && var1 instanceof IEVar) {
                           IEVar var16 = this.ctx.getVariableById(var11);
                           return var16.part(var16.getBitsize() / 2);
                        }

                        var13 = (Integer)var12.getAsPair().getSecond();
                        if (var13 == var4) {
                           IEVar var15 = this.ctx.getVariableById(var11);
                           return var15.slice(var15.getBitsize() / 2);
                        }
                     }

                     return null;
                  }

                  if (var12.isTruncated() && (Integer)var12.getAsTruncated().getFirst() == var4) {
                     return this.genSlicedVar(var11, var1);
                  }
               }
            }
         }

         if (var2) {
            this.created = true;
            return this.copySlicedVariable(var1);
         } else {
            return null;
         }
      }
   }

   private IEVar retrieveVariableForRegisterPair(IEVar var1xx, IEVar var2xx, boolean var3) {
      if (var1xx != null && var2xx != null) {
         if (this.filters != null) {
            int var4 = var1xx.getId();
            int var5 = var2xx.getId();

            for (int var7 : this.filters) {
               VarSrc var8 = this.ctx.getSourceForVariable(var7);
               if (var8 != null) {
                  if (var8.isPair()) {
                     Couple var9 = var8.getAsPair();
                     if ((Integer)var9.getFirst() == var4 && (Integer)var9.getSecond() == var5) {
                        this.svars = new IEVar[]{var1xx, var2xx};
                        return this.ctx.getVariableById(var7);
                     }
                  } else if (CollectionUtil.hasIntersection(var8.getSourceIds(), Arrays.asList(var4, var5))) {
                     break;
                  }
               }
            }

            IEGeneric var10 = this.retrieveVariableForRegister(var1xx, false);
            if (var10 != null && !(var10 instanceof IEVar var1xx)) {
               return null;
            }

            IEGeneric var11 = this.retrieveVariableForRegister(var2xx, false);
            if (var11 != null && !(var11 instanceof IEVar var2xx)) {
               return null;
            }
         }

         if (var3) {
            this.created = true;
            this.svars = new IEVar[]{var1xx, var2xx};
            return this.ctx.copyPairOfVariables(var1xx, var2xx);
         } else {
            return null;
         }
      } else {
         throw new IllegalArgumentException("Need 2x EVar");
      }
   }

   public boolean wasCreated() {
      this.verifyDone(true);
      return this.created;
   }

   public IEVar[] getVarsToSubstitute() {
      this.verifyDone(true);
      return this.svars;
   }

   public IEVar getVarToSubstitute(int var1) {
      return this.getVarsToSubstitute()[var1];
   }
}
