package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IBranchTarget;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFGFormatter;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.IFormattingContextFactory;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.IVariableProvider;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class ECFGFormatter extends CFGFormatter {
   private IERoutineContext ectx;
   private boolean hasStackDeltas;

   public ECFGFormatter(CFG var1) {
      super(var1);
   }

   public ECFGFormatter(CFG var1, IVariableProvider var2, boolean var3) {
      super(var1, var2, var3);
   }

   public void setContext(IERoutineContext var1) {
      this.ectx = var1;
   }

   @Override
   public String format(boolean var1, boolean var2, boolean var3, IDFA var4, IFormattingContextFactory var5) {
      this.hasStackDeltas = false;

      for (IEStatement var7 : this.getCfg().instructions()) {
         Integer var8 = var7.getSPDelta();
         if (var8 != null) {
            this.hasStackDeltas = true;
            break;
         }
      }

      return super.format(var1, var2, var3, var4, var5);
   }

   protected void genPreAddress(StringBuilder var1, long var2, IEStatement var4) {
      if (this.ectx != null) {
         Collection var5 = var4.getLowerLevelAddresses();
         if (!var5.isEmpty()) {
            long var7 = (Long)var5.iterator().next();
            char var6;
            if (var5.size() == 1) {
               var6 = '<';
            } else {
               var6 = '*';
            }

            List var10 = this.ectx.getIntermediateOffsetsMappingToNativeAddresses(var5);
            Assert.a(var10.contains(var2));
            char var9;
            if (var10.size() == 1) {
               var9 = '>';
            } else {
               var9 = '*';
            }

            Strings.ff(var1, "%08X%c%c", var7, var6, var9);
         }
      }
   }

   protected void genAddress(StringBuilder var1, long var2, IEStatement var4) {
      Strings.ff(var1, "%04X/%X", var2, var4.getSize());
   }

   protected void genPostAddress(StringBuilder var1, long var2, IEStatement var4) {
      if (this.hasStackDeltas) {
         Integer var5 = var4.getSPDelta();
         if (var5 == null) {
            var1.append(" [?]");
         } else if (var5 <= 0) {
            Strings.ff(var1, " [%X]", -var5);
         } else {
            Strings.ff(var1, " [-%X]", var5);
         }
      }

      super.genPostAddress(var1, var2, var4);
   }

   protected void genPostInstruction(StringBuilder var1, long var2, IEStatement var4) {
      int var5 = var4.getFlags();
      if (var5 != 0) {
         var1.append("  [");
         if ((var5 & 4) != 0) {
            var1.append("PROLOGUE ");
         }

         if ((var5 & 8) != 0) {
            var1.append("EPILOGUE ");
         }

         if ((var5 & 1) != 0) {
            var1.append("NO_PROP ");
         }

         if ((var5 & 2) != 0) {
            var1.append("NO_SUBST ");
         }

         var1.append("]");
      }

      if (var4 instanceof IEAssign && ((IEAssign)var4).isRoutineCall()) {
         var1.append("  [SUB]");
      }

      super.genPostInstruction(var1, var2, var4);
   }

   protected void genEOL(StringBuilder var1, long var2, IEStatement var4) {
      IFlowInformation var5 = var4.getBreakingFlow(var2);
      if (var5 != null && var5.isBroken()) {
         if (var5.isBrokenKnown()) {
            Strings.ff(var1, "[BRK] ");
         } else if (var5.isBrokenKnown()) {
            Strings.ff(var1, "[BRK-UNK] ");
         } else {
            Strings.ff(var1, "[BRK-MIX] ");
         }
      }

      var5 = var4.getRoutineCall(var2);
      if (var5 != null && var5.isBroken()) {
         if (var5.isBrokenKnown()) {
            Strings.ff(var1, "[SUB] ");
         } else if (var5.isBrokenKnown()) {
            Strings.ff(var1, "[SUB-UNK] ");
         } else {
            Strings.ff(var1, "[SUB-MIX] ");
         }
      }

      BasicBlock var6 = this.getCfg().getBlockByLastAddress(var2);
      if (var6 != null) {
         Strings.ff(var1, "blk_ouputs={%s} ", Longs.formatHexCollection(var6.getOutputOffsets()));
      }

      if (var4 instanceof IEAssign && ((IEAssign)var4).isBranching()) {
         IEBranchDetails var15 = ((IEAssign)var4).getBranchDetails();
         if (var15 != null) {
            var1.append("potential_targets={ ");
            int var21 = 0;

            for (IBranchTarget var31 : var15.getDynamicTargetCandidates()) {
               if (var21 >= 1) {
                  var1.append(", ");
               }

               var1.append(var31.toString());
               var21++;
            }

            if (!var15.isIncludeUnknownTarget()) {
               if (var21 >= 1) {
                  var1.append(", ");
               }

               var1.append("unknown");
            }

            var1.append(" }");
         }
      } else if (var4 instanceof IEJumpFar) {
         List var12 = ((IEJumpFar)var4).getPossibleTargets();
         if (var12 != null && !var12.isEmpty()) {
            var1.append("potential_targets={ ");
            int var17 = 0;

            for (ICodePointer var8 : var12) {
               if (var17 >= 1) {
                  var1.append(", ");
               }

               var1.append(var8.toString());
               var17++;
            }

            var1.append(" }");
         }
      } else if (var4 instanceof IECall) {
         List var13 = ((IECall)var4).getSpoiledExpressions();
         if (var13 != null && !var13.isEmpty()) {
            var1.append("spoiled={ ");
            int var18 = 0;

            for (IEGeneric var27 : var13) {
               if (var18 >= 1) {
                  var1.append(", ");
               }

               var1.append(var27.format(var4, var2));
               var18++;
            }

            var1.append(" }");
         }
      } else if (var4 instanceof IEUntranslatedInstruction) {
         Set var14 = ((IEUntranslatedInstruction)var4).getSideEffectDefinedVariables();
         if (var14 != null && !var14.isEmpty()) {
            var1.append("idef={ ");
            int var19 = 0;

            for (IEGeneric var28 : var14) {
               if (var19 >= 1) {
                  var1.append(", ");
               }

               var1.append(var28.format(var4, var2));
               var19++;
            }

            var1.append(" }");
         }

         Set var20 = ((IEUntranslatedInstruction)var4).getSideEffectUsedVariables();
         if (var20 != null && !var20.isEmpty()) {
            var1.append("iuse={ ");
            int var24 = 0;

            for (IEGeneric var9 : var20) {
               if (var24 >= 1) {
                  var1.append(", ");
               }

               var1.append(var9.format(var4, var2));
               var24++;
            }

            var1.append(" }");
         }

         Set var25 = ((IEUntranslatedInstruction)var4).getSideEffectSpoiledVariables();
         if (var25 != null && !var25.isEmpty()) {
            var1.append("spoiled={ ");
            int var30 = 0;

            for (IEGeneric var10 : var25) {
               if (var30 >= 1) {
                  var1.append(", ");
               }

               var1.append(var10.format(var4, var2));
               var30++;
            }

            var1.append(" }");
         }
      }

      Collection var16 = var4.getLowerLevelAddresses();
      if (var16.size() >= 2) {
         Strings.ff(var1, "  ADD.LLA[%s]", Longs.formatHexCollection(new ArrayList(var16).subList(1, var16.size())));
      }

      super.genEOL(var1, var2, var4);
   }
}
