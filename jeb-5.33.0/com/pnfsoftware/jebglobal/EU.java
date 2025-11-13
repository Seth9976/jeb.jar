package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.NativeAnalyzerException;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItemListener;
import com.pnfsoftware.jeb.core.units.code.asm.items.InstructionHints;
import com.pnfsoftware.jeb.util.collect.MultiMap;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Booleans;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class EU implements iL {
   private static final ILogger UT = GlobalLog.getLogger(EU.class);
   protected iL.Av pC = iL.Av.A;
   protected a A;
   protected Tw kS;
   protected ey wS;
   private Set E = new HashSet();
   private boolean sY = false;
   private Set ys = new HashSet();

   public EU(INativeCodeAnalyzer var1, iL.Av var2) {
      this.A = (a)var1;
      this.kS = this.A.ys();
      this.wS = this.kS.gp();
      this.pC = var2;
   }

   public boolean pC() {
      this.kS.pC.verifyLocked();
      boolean var1 = false;

      for (auu var3 : this.kS.sY()) {
         Integer var4 = var3.mv();
         if (var4 == null || var4 != 1) {
            try {
               for (AddressableInstruction var6 : var3.E().getCFG().addressableInstructions()) {
                  INativeContinuousItem var7 = this.kS.getItemAt(var6.getOffset());
                  if (!this.A(var7)) {
                     IFlowInformation var8 = var6.getBreakingFlow();
                     if (var8 != null && var8.isBrokenKnown()) {
                        List var9 = var8.getTargets();
                        if (var9.size() == 1) {
                           long var10 = ((ICodePointer)var9.get(0)).getAddress();
                           if (this.kS.E(var10) == null) {
                              BasicBlock var13 = var3.E().getCFG().getBlockContaining(var10);
                              if (var13 != null) {
                                 boolean var12 = this.pC(var6, var13, var10);
                                 if (!this.pC(var10) && var12) {
                                    this.ys.add(var10);
                                    this.pC(var7, false);

                                    for (xy var16 : (Set)this.wS
                                       .getReferencesTo(var10)
                                       .stream()
                                       .filter(
                                          var1x -> var1x.getFrom().isInternalAddress()
                                             && var1x.getFrom().getInternalAddress() != var6.getOffset()
                                             && (var1x.getType() == ReferenceType.BRANCH || var1x.getType() == ReferenceType.COND_BRANCH)
                                       )
                                       .collect(Collectors.toSet())) {
                                       INativeContinuousItem var17 = this.kS.getItemAt(var16.getFrom().getInternalAddress());
                                       if (var16.getType() != ReferenceType.COND_BRANCH || (Long)var17.getEnd() != var10) {
                                          this.pC(var17, var16.getType() == ReferenceType.COND_BRANCH);
                                       }
                                    }

                                    var1 = true;
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            } finally {
               var3.A(1);
            }
         }
      }

      return var1;
   }

   private boolean pC(long var1) {
      List var3 = this.kS.getContainedRoutineAddresses(var1);
      if (var3.size() == 1) {
         INativeContinuousItem var4 = this.kS.getPreviousItem(var1);
         if (var4 instanceof INativeInstructionItem && var4.getMemoryAddressEnd() == var1) {
            IInstruction var5 = ((INativeInstructionItem)var4).getInstruction();
            IFlowInformation var6 = var5.getBreakingFlow(var4.getMemoryAddress());
            if (var6.isBroken()) {
               if (var6.mustComputeFallThrough()) {
                  return true;
               }

               if (var6.getTargets().size() > 1) {
                  for (ICodePointer var8 : var6.getTargets()) {
                     if (var8.getAddress() == var1) {
                        return true;
                     }
                  }
               }
            } else {
               var6 = var5.getRoutineCall(var4.getMemoryAddress());
               if (!var6.isBroken()) {
                  return true;
               }

               if (var6.getTargets().size() >= 1) {
                  auu var10 = this.kS.E(((ICodePointer)var6.getTargets().get(0)).getAddress());
                  if (var10 == null || !Booleans.isTrue(var10.getNonReturning())) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   private void pC(INativeContinuousItem var1, boolean var2) {
      if (var1 instanceof aus) {
         this.E.addAll(this.kS.getContainedRoutineAddresses(var1.getMemoryAddress()));
         InstructionHints var3 = ((aus)var1).getHints(true);
         if (!var2) {
            var3.setTailCall(true);
         } else {
            var3.setCondTailCall(true);
         }
      }
   }

   private void pC(INativeContinuousItem var1) {
      if (var1 instanceof aus) {
         InstructionHints var2 = ((aus)var1).getHints(false);
         if (var2 != null) {
            if (var2.isTailCall()) {
               var2.setTailCall(false);
            }

            if (var2.isCondTailCall()) {
               var2.setCondTailCall(false);
            }
         }
      }
   }

   private boolean A(INativeContinuousItem var1) {
      if (var1 instanceof aus) {
         InstructionHints var2 = ((aus)var1).getHints(false);
         if (var2 != null) {
            return var2.isTailCall();
         }
      }

      return false;
   }

   protected abstract boolean pC(AddressableInstruction var1, BasicBlock var2, long var3);

   public boolean A() {
      this.kS.pC.verifyLocked();
      if (this.E != null && !this.E.isEmpty()) {
         MultiMap var1 = new MultiMap();

         for (long var3 : this.E) {
            auu var5 = this.kS.E(var3);
            if (var5 == null) {
               JebCoreService.notifySilentExceptionToClient(new RuntimeException("Unexpected null routine"));
            } else {
               int var6 = 0;
               INativeContinuousItem var7 = this.kS.getItemAt(var3);
               if (var7 instanceof INativeInstructionItem) {
                  var6 = ((INativeInstructionItem)var7).getInstruction().getProcessorMode();
               }

               for (INativeItemListener var9 : var5.Sc()) {
                  if (var9 instanceof auz) {
                     var1.put(var3, (auz)var9);
                  }
               }

               String var19 = var5.Er();
               this.kS.A(var3, true);
               if (var19 != null) {
                  this.kS.pC(var3, var19);
               }

               this.A.enqueuePointerForAnalysis(this.A.getProcessor().createEntryPoint(var3, var6));
            }
         }

         if (!this.A.needsAnalysis()) {
            return false;
         } else {
            this.A.analyze(true, true);

            for (long var12 : this.E) {
               auu var14 = this.kS.E(var12);
               if (var14 != null) {
                  var14.A(1);
                  List var15 = var1.get(var12);
                  if (var15 != null && !var15.isEmpty()) {
                     for (auz var20 : var15) {
                        this.A.pC(var20.getMemoryAddress(), var20.getName(true), var14, null);
                     }
                  }
               }
            }

            this.E = new HashSet();
            this.A.fI();
            if (!this.ys.isEmpty()) {
               boolean var11 = false;

               for (long var4 : this.ys) {
                  if (this.kS.E(var4) == null) {
                     var11 = true;

                     for (xy var18 : this.wS.getReferencesTo(var4)) {
                        this.pC(this.kS.getItemAt(var18.getFrom().getInternalAddress()));
                     }
                  }
               }

               if (var11 && !this.sY) {
                  this.sY = true;
                  JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("tail call re-analysis failed"));
               }

               this.ys = new HashSet();
            }

            return true;
         }
      } else {
         return false;
      }
   }

   protected boolean pC(long var1, String... var3) {
      long var4 = var1;

      for (String var9 : var3) {
         INativeContinuousItem var10 = this.kS.getItemAt(var4);
         if (!(var10 instanceof aus)) {
            return false;
         }

         IInstruction var11 = ((aus)var10).getInstruction();
         if (!var11.toString().equalsIgnoreCase(var9)) {
            return false;
         }

         var4 += var11.getSize();
      }

      return true;
   }
}
