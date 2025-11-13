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

public abstract class zv implements aap {
   private static final ILogger Uv = GlobalLog.getLogger(zv.class);
   protected aap.eo q = aap.eo.RF;
   protected aae RF;
   protected aaf xK;
   protected abl Dw;
   private Set oW = new HashSet();
   private boolean gO = false;
   private Set nf = new HashSet();

   public zv(INativeCodeAnalyzer var1, aap.eo var2) {
      this.RF = (aae)var1;
      this.xK = this.RF.za();
      this.Dw = this.xK.HF();
      this.q = var2;
   }

   @Override
   public aap.eo q() {
      return this.q;
   }

   @Override
   public boolean RF() {
      this.xK.q.verifyLocked();
      boolean var1 = false;

      for (axp var3 : this.xK.gP()) {
         Integer var4 = var3.EB();
         if (var4 == null || var4 != 1) {
            try {
               for (AddressableInstruction var6 : var3.oW().getCFG().addressableInstructions()) {
                  INativeContinuousItem var7 = this.xK.getItemAt(var6.getOffset());
                  if (!this.RF(var7)) {
                     IFlowInformation var8 = var6.getBreakingFlow();
                     if (var8 != null && var8.isBrokenKnown()) {
                        List var9 = var8.getTargets();
                        if (var9.size() == 1) {
                           long var10 = ((ICodePointer)var9.get(0)).getAddress();
                           if (this.xK.oW(var10) == null) {
                              BasicBlock var13 = var3.oW().getCFG().getBlockContaining(var10);
                              if (var13 != null) {
                                 boolean var12 = this.q(var6, var13, var10);
                                 if (!this.q(var10) && var12) {
                                    this.nf.add(var10);
                                    this.q(var7, false);

                                    for (abk var16 : (Set)this.Dw
                                       .getReferencesTo(var10)
                                       .stream()
                                       .filter(
                                          var1x -> var1x.getFrom().isInternalAddress()
                                             && var1x.getFrom().getInternalAddress() != var6.getOffset()
                                             && (var1x.getType() == ReferenceType.BRANCH || var1x.getType() == ReferenceType.COND_BRANCH)
                                       )
                                       .collect(Collectors.toSet())) {
                                       INativeContinuousItem var17 = this.xK.getItemAt(var16.getFrom().getInternalAddress());
                                       if (var16.getType() != ReferenceType.COND_BRANCH || (Long)var17.getEnd() != var10) {
                                          this.q(var17, var16.getType() == ReferenceType.COND_BRANCH);
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
               var3.RF(1);
            }
         }
      }

      return var1;
   }

   private boolean q(long var1) {
      List var3 = this.xK.getContainedRoutineAddresses(var1);
      if (var3.size() == 1) {
         INativeContinuousItem var4 = this.xK.getPreviousItem(var1);
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
                  axp var10 = this.xK.oW(((ICodePointer)var6.getTargets().get(0)).getAddress());
                  if (var10 == null || !Booleans.isTrue(var10.getNonReturning())) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   private void q(INativeContinuousItem var1, boolean var2) {
      if (var1 instanceof axn) {
         this.oW.addAll(this.xK.getContainedRoutineAddresses(var1.getMemoryAddress()));
         InstructionHints var3 = ((axn)var1).getHints(true);
         if (!var2) {
            var3.setTailCall(true);
         } else {
            var3.setCondTailCall(true);
         }
      }
   }

   private void q(INativeContinuousItem var1) {
      if (var1 instanceof axn) {
         InstructionHints var2 = ((axn)var1).getHints(false);
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

   private boolean RF(INativeContinuousItem var1) {
      if (var1 instanceof axn) {
         InstructionHints var2 = ((axn)var1).getHints(false);
         if (var2 != null) {
            return var2.isTailCall();
         }
      }

      return false;
   }

   protected abstract boolean q(AddressableInstruction var1, BasicBlock var2, long var3);

   @Override
   public boolean xK() {
      this.xK.q.verifyLocked();
      if (this.oW != null && !this.oW.isEmpty()) {
         MultiMap var1 = new MultiMap();

         for (long var3 : this.oW) {
            axp var5 = this.xK.oW(var3);
            if (var5 == null) {
               JebCoreService.notifySilentExceptionToClient(new RuntimeException("Unexpected null routine"));
            } else {
               int var6 = 0;
               INativeContinuousItem var7 = this.xK.getItemAt(var3);
               if (var7 instanceof INativeInstructionItem) {
                  var6 = ((INativeInstructionItem)var7).getInstruction().getProcessorMode();
               }

               for (INativeItemListener var9 : var5.xW()) {
                  if (var9 instanceof axu) {
                     var1.put(var3, (axu)var9);
                  }
               }

               String var19 = var5.mI();
               this.xK.RF(var3, true);
               if (var19 != null) {
                  this.xK.q(var3, var19);
               }

               this.RF.enqueuePointerForAnalysis(this.RF.getProcessor().createEntryPoint(var3, var6));
            }
         }

         if (!this.RF.needsAnalysis()) {
            return false;
         } else {
            this.RF.analyze(true, true);

            for (long var12 : this.oW) {
               axp var14 = this.xK.oW(var12);
               if (var14 != null) {
                  var14.RF(1);
                  List var15 = var1.get(var12);
                  if (var15 != null && !var15.isEmpty()) {
                     for (axu var20 : var15) {
                        this.RF.q(var20.getMemoryAddress(), var20.getName(true), var14, null);
                     }
                  }
               }
            }

            this.oW = new HashSet();
            this.RF.LK();
            if (!this.nf.isEmpty()) {
               boolean var11 = false;

               for (long var4 : this.nf) {
                  if (this.xK.oW(var4) == null) {
                     var11 = true;

                     for (abk var18 : this.Dw.getReferencesTo(var4)) {
                        this.q(this.xK.getItemAt(var18.getFrom().getInternalAddress()));
                     }
                  }
               }

               if (var11 && !this.gO) {
                  this.gO = true;
                  JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("tail call re-analysis failed"));
               }

               this.nf = new HashSet();
            }

            return true;
         }
      } else {
         return false;
      }
   }

   protected boolean q(long var1, String... var3) {
      long var4 = var1;

      for (String var9 : var3) {
         INativeContinuousItem var10 = this.xK.getItemAt(var4);
         if (!(var10 instanceof axn)) {
            return false;
         }

         IInstruction var11 = ((axn)var10).getInstruction();
         if (!var11.toString().equalsIgnoreCase(var9)) {
            return false;
         }

         var4 += var11.getSize();
      }

      return true;
   }
}
