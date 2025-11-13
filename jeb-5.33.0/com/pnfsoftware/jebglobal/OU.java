package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IReference;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.InstructionHints;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.dart.IDartAotUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class OU {
   private static final ILogger pC = GlobalLog.getLogger(OU.class);
   private final com.pnfsoftware.jeb.corei.parsers.arm.Av A;
   private final a kS;
   private final Tw wS;
   private final Nd UT;
   private List E = new ArrayList();
   private List sY = new ArrayList();

   public OU(com.pnfsoftware.jeb.corei.parsers.arm.Av var1, a var2, Nd var3) {
      this.A = var1;
      this.kS = var2;
      this.UT = var3;
      this.wS = var2.ys();
      this.sY.add(new pA());
      this.sY.add(new kp(this.wS));
      this.sY.add(new ng(this.wS));
      this.sY.add(new Nt(this.wS));
      if (!this.wS()) {
         this.sY.add(new Hk(this.wS, var3));
      }

      this.sY.add(new TA(var2));
   }

   public void pC() {
      this.sY.add(new ps(this.wS));
   }

   private boolean wS() {
      ICodeObjectUnit var1 = this.kS.getContainer();
      return var1 instanceof IUnit ? UnitUtil.findFirstChildByType(var1, IDartAotUnit.class, false) != null : false;
   }

   public void A() {
      TreeSet var1 = new TreeSet();
      TreeSet var2 = null;

      while (this.pC(var1)) {
         for (CodePointer var5 : this.kS()) {
            if (this.wS.isRoutineHeader(var5.getAddress())) {
               this.wS.A(var5.getAddress(), true);
            }

            this.kS.enqueuePointerForAnalysis(var5);
         }

         if (this.kS.needsAnalysis()) {
            this.kS.analyze(true, true);
         }

         if (var2 == null) {
            var2 = var1;
            var1 = new TreeSet();
         } else {
            if (CollectionUtil.hasIntersection(var1, var2)) {
               Object[] var10000 = new Object[]{Strings.join(", ", CollectionUtil.intersect(var1, var2), Long::toHexString)};
               break;
            }

            var2.addAll(var1);
            var1 = new TreeSet();
         }
      }
   }

   boolean pC(TreeSet var1) {
      this.E.clear();
      TreeSet var2 = new TreeSet(this.wS.getRoutineAddresses());
      TreeSet var3 = this.pC((Collection)var2);

      for (Long var5 : var3) {
         INativeInstructionItem var6 = (INativeInstructionItem)this.wS.getItemAt(var5);
         if (var6 == null) {
            pC.error(S.L("Cannot retrieve item at %Xh"), var5);
         } else {
            com.pnfsoftware.jeb.corei.parsers.arm.rQ var7 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var6.getInstruction();

            for (zz var9 : this.sY) {
               if (var9.pC(var5, var7, var2, var3)) {
                  Object[] var10000 = new Object[]{var5, var9.getClass().getSimpleName()};
                  this.pC(var5, var2, var1, true, new iU(this.wS).pC(var5, var7, var2, var3));
                  break;
               }
            }
         }
      }

      var3.removeAll(var1);
      this.wS(var1);
      this.wS(var1);
      if (var1.isEmpty()) {
         HashSet var10 = new HashSet();
         boolean var11 = false;

         while (!var11) {
            var11 = true;
            TreeSet var12 = this.A(var2);

            for (Long var14 : var12) {
               if (!var10.contains(var14)) {
                  var11 = false;
                  Object[] var15 = new Object[]{var14, "Erroneous BB"};
                  this.pC(var14, var2, var1, true, true);
               }
            }

            var10.addAll(var12);
         }
      }

      return !var1.isEmpty();
   }

   private void pC(long var1, TreeSet var3, TreeSet var4, boolean var5, boolean var6) {
      if (var5) {
         Long var7 = (Long)var3.lower(var1);
         if (var7 != null) {
            auu var8 = this.wS.E(var7);
            CFG var9 = var8.getData().getCFG();
            if (var9 != null && var9.getEndAddress() > var1) {
               var5 = false;
            }
         }
      }

      auu var23 = this.wS.E(var1);
      CFG var24 = var23.getData().getCFG();
      ArrayList var25 = new ArrayList();
      if (var5) {
         for (BasicBlock var11 : var24.getBlocks()) {
            if (this.wS.getContainedRoutineAddresses(var11.getBase()).size() <= 1) {
               if (var25 != null) {
                  var25.add(var11);
                  AddressableInstruction var12 = var11.getBranchingInstruction2();
                  if (var12 != null && PU.pC((com.pnfsoftware.jeb.corei.parsers.arm.rQ)var12.getInstruction())) {
                     com.pnfsoftware.jeb.corei.parsers.arm.rQ var13 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var12.getInstruction();
                     if (aK.pC(this.kS, var12.getOffset(), var13)) {
                        try {
                           Long var14 = var13.pC().pC(var13, var12.getOffset(), null);
                           if (var14 != null && var3.contains(var14)) {
                              var25 = null;
                           }
                        } catch (ProcessorException var22) {
                        }
                     }
                  }
               }

               if (var11.getBase() != var24.getEntryAddress() && !var6) {
                  int var30 = ((com.pnfsoftware.jeb.corei.parsers.arm.rQ)var11.getInstruction(var11.getBase())).getProcessorMode();
                  CodePointer var34 = this.UT.pC(var11.getBase(), var30, Nd.Av.A);
                  if (var34 != null) {
                     return;
                  }
               }
            }
         }
      }

      ArrayList var26 = new ArrayList();
      if (var6 && var25 != null) {
         for (BasicBlock var31 : var25) {
            for (AddressableInstruction var38 : var31.addressableInstructions()) {
               if (this.wS.getContainedRoutineAddresses(var38.getOffset()).size() > 1) {
                  break;
               }

               var26.add(var38.getOffset());
            }
         }
      }

      this.wS.A(var1, true);
      var3.remove(var1);
      var4.add(var1);
      this.A.UT(var1);

      for (Long var32 : var26) {
         INativeContinuousItem var36 = this.wS.getItemAt(var32);
         if (var36 != null) {
            this.wS.pC(var36, true);
         }
      }

      boolean var29 = this.pC(var1);
      List var33 = this.wS.getContainedRoutineAddresses(var1);
      Set var37 = this.wS.gp().getReferencesTo(var1);

      for (xy var15 : var37) {
         if (var15.getFrom().isInternalAddress()) {
            INativeContinuousItem var16 = this.wS.getItemAt(var15.getFrom().getInternalAddress());
            IInstruction var17 = ((INativeInstructionItem)var16).getInstruction();
            if (var16 instanceof INativeInstructionItem) {
               InstructionHints var18 = ((aus)var16).getHints(false);
               if (var18 != null && var29) {
                  var18.setCondTailCall(false);
                  var18.setTailCall(false);
               }

               if (PU.UT((com.pnfsoftware.jeb.corei.parsers.arm.rQ)var17)) {
                  if (var18 == null) {
                     var18 = ((aus)var16).getHints(true);
                  }

                  var18.setFakeCall(true);
               }

               for (Long var21 : this.wS.getContainedRoutineAddresses(var16.getMemoryAddress())) {
                  if (var21 != var1 && !var33.contains(var21)) {
                     this.E.add(new CodePointer(var21, var17.getProcessorMode()));
                  }
               }
            }
         }
      }

      if (var29 && var37.isEmpty()) {
         INativeContinuousItem var40 = this.wS.getPreviousItem(var1);
         IInstruction var41 = ((INativeInstructionItem)var40).getInstruction();

         for (Long var44 : this.wS.getContainedRoutineAddresses(var40.getMemoryAddress())) {
            if (var44 != var1 && !var33.contains(var44)) {
               this.E.add(new CodePointer(var44, var41.getProcessorMode()));
            }
         }
      }
   }

   public List kS() {
      return this.E;
   }

   protected boolean pC(long var1) {
      INativeContinuousItem var3 = this.wS.getPreviousItem(var1);
      if (!(var3 instanceof INativeInstructionItem)) {
         return false;
      } else {
         com.pnfsoftware.jeb.corei.parsers.arm.rQ var4 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)((INativeInstructionItem)var3).getInstruction();
         if (PU.wS(var4)) {
            return false;
         } else if (var3.getMemoryAddressEnd() == var1) {
            return true;
         } else {
            if (var3.getMemoryAddressEnd() + 2L == var1) {
               com.pnfsoftware.jeb.corei.parsers.arm.rQ var5 = PU.pC(
                  this.kS.getProcessor(), this.kS.getMemory(), var3.getMemoryAddressEnd(), var4.getProcessorMode()
               );
               if (var5 != null) {
                  return true;
               }
            }

            return false;
         }
      }
   }

   private TreeSet A(TreeSet var1) {
      TreeSet var2 = this.kS(var1);
      TreeSet var3 = new TreeSet();

      for (Long var5 : var1) {
         if (var2.contains(var5)) {
            auu var6 = this.wS.E(var5);
            if (this.UT.A(var6)) {
               pC.catching(new JebException("Trying to remove named routine " + var6));
            } else {
               var3.add(var5);
            }
         }
      }

      return var3;
   }

   private TreeSet kS(TreeSet var1) {
      TreeSet var2 = new TreeSet();

      for (long var4 : var1) {
         auu var6 = this.wS.E(var4);
         CFG var7 = var6.getData().getCFG();

         for (BasicBlock var9 : var7.getExitBlocks()) {
            com.pnfsoftware.jeb.corei.parsers.arm.rQ var10 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var9.getLast();
            if (PU.pC(var10)) {
               if (aK.pC(this.kS, var9.getLastAddress(), var10)) {
                  continue;
               }
            } else {
               if (var10.ld() || PU.kS(var10)) {
                  continue;
               }

               INativeContinuousItem var11 = this.wS.getItemAt(var9.getEndAddress());
               if (var11 instanceof INativeInstructionItem) {
                  continue;
               }
            }

            this.pC(var2, var9.getBase());
         }
      }

      return var2;
   }

   private void pC(TreeSet var1, long var2) {
      ArrayList var4 = new ArrayList();
      var4.add(var2);

      while (!var4.isEmpty()) {
         long var5 = (Long)var4.remove(0);
         if (!var1.contains(var5)) {
            var1.add(var5);
            LinkedHashSet var7 = new LinkedHashSet();

            for (xy var9 : this.wS.gp().getReferencesTo(var5)) {
               if (var9.getFrom().isInternalAddress()) {
                  Long var10 = var9.getFrom().getInternalAddress();
                  var7.add(var10);
               }
            }

            for (Long var18 : this.wS.getContainedRoutineAddresses(var5)) {
               auu var20 = this.wS.E(var18);
               CFG var11 = var20.getData().getCFG();
               BasicBlock var12 = var11.getBlockContaining(var5);

               for (BasicBlock var14 : var12.getInputs()) {
                  var7.add(var14.getLastAddress());
               }
            }

            for (Long var19 : var7) {
               for (Long var22 : this.wS.getContainedRoutineAddresses(var19)) {
                  auu var23 = this.wS.E(var22);
                  CFG var24 = var23.getData().getCFG();
                  BasicBlock var25 = var24.getBlockContaining(var19);
                  if (var25 != null) {
                     if (!PU.A((com.pnfsoftware.jeb.corei.parsers.arm.rQ)var25.getLast())) {
                        var4.add(var25.getBase());
                     } else if (!((com.pnfsoftware.jeb.corei.parsers.arm.rQ)var25.getLast()).UT().E()) {
                        var4.add(var25.getBase());
                     } else if (PU.UT((com.pnfsoftware.jeb.corei.parsers.arm.rQ)var25.getLast()) && var5 == var25.getEndAddress()) {
                        this.A.pC(var25.getLastAddress() + ((com.pnfsoftware.jeb.corei.parsers.arm.rQ)var25.getLast()).getSize());
                     } else {
                        INativeContinuousItem var15 = this.kS.ys().getItemAt(var25.getLastAddress());
                        if (!(var15 instanceof aus) || ((aus)var15).UT() < 1) {
                           var4.add(var25.getBase());
                        }
                     }
                     break;
                  }

                  pC.catchingSilent(new Exception(Strings.ff("no origin bb at %xh", var19)));
               }
            }
         }
      }
   }

   public TreeSet pC(Collection var1) {
      TreeSet var2 = new TreeSet();
      TreeSet var3 = new TreeSet();

      for (long var5 : var1) {
         auu var7 = this.wS.E(var5);
         if (this.UT.A(var7)) {
            var3.add(var5);
         } else if (this.UT.kS(var7) && !this.pC(var5)) {
            var3.add(var5);
         } else if (this.pC(var5, var3)) {
            var2.add(var7.getMemoryAddress());
         } else {
            var3.add(var5);
         }
      }

      return var2;
   }

   public boolean pC(long var1, TreeSet var3) {
      Set var4 = this.wS.gp().getReferencesTo(var1);
      return var4.isEmpty() || this.pC(var4, var3);
   }

   private boolean pC(Set var1, TreeSet var2) {
      long var3 = 0L;
      Object var5 = null;

      for (IReference var7 : var1) {
         if (!var7.getFrom().isInternalAddress()) {
            return false;
         }

         long var8 = var7.getFrom().getInternalAddress();
         INativeContinuousItem var10 = this.wS.getItemAt(var8);
         if (!(var10 instanceof INativeInstructionItem)) {
            return false;
         }

         List var11 = this.wS.getContainedRoutineAddresses(var8);
         if (!var11.isEmpty()) {
            if (var5 == null) {
               var3 = var7.getTo().getInternalAddress();
               var5 = new ArrayList();

               for (Long var13 : var11) {
                  if (var2.contains(var13)) {
                     var5.add(var13);
                  } else {
                     auu var14 = this.wS.E(var13);
                     if (var3 > var13 && var3 < var14.getData().getCFG().getEndAddress()) {
                        var5.add(var13);
                     }
                  }
               }

               if (var5.isEmpty()) {
                  return false;
               }
            } else {
               var5 = var5.stream().filter(var11::contains).toList();
               if (var5.isEmpty()) {
                  return false;
               }
            }
         }
      }

      if (var5 == null) {
         return true;
      } else {
         for (Long var16 : var5) {
            auu var17 = this.wS.E(var16);
            if (var3 > var16 && var3 < var17.getData().getCFG().getEndAddress()) {
               return true;
            }
         }

         return false;
      }
   }

   private void wS(TreeSet var1) {
      for (Long var3 : var1) {
         String var4 = this.wS.oT().getLabel(var3, 0L, null);
         if (var4 != null && var4.startsWith("→")) {
            this.wS.oT().removeLabel(var3);
         }
      }
   }
}
