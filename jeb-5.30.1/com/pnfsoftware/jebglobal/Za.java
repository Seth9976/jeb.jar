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

public class Za {
   private static final ILogger q = GlobalLog.getLogger(Za.class);
   private final FS RF;
   private final aae xK;
   private final aaf Dw;
   private final Jc Uv;
   private List oW = new ArrayList();
   private List gO = new ArrayList();

   public Za(FS var1, aae var2, Jc var3) {
      this.RF = var1;
      this.xK = var2;
      this.Uv = var3;
      this.Dw = var2.za();
      this.gO.add(new GZ());
      this.gO.add(new lU(this.Dw));
      this.gO.add(new WE(this.Dw));
      this.gO.add(new WW(this.Dw));
      if (!this.Dw()) {
         this.gO.add(new AN(this.Dw, var3));
      }

      this.gO.add(new E(var2));
   }

   public void q() {
      this.gO.add(new Au(this.Dw));
   }

   private boolean Dw() {
      ICodeObjectUnit var1 = this.xK.getContainer();
      return var1 instanceof IUnit ? UnitUtil.findFirstChildByType(var1, IDartAotUnit.class, false) != null : false;
   }

   public void RF() {
      TreeSet var1 = new TreeSet();
      TreeSet var2 = null;

      while (this.q(var1)) {
         for (CodePointer var5 : this.xK()) {
            if (this.Dw.isRoutineHeader(var5.getAddress())) {
               this.Dw.RF(var5.getAddress(), true);
            }

            this.xK.enqueuePointerForAnalysis(var5);
         }

         if (this.xK.needsAnalysis()) {
            this.xK.analyze(true, true);
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

   boolean q(TreeSet var1) {
      this.oW.clear();
      TreeSet var2 = new TreeSet(this.Dw.getRoutineAddresses());
      TreeSet var3 = this.q((Collection)var2);

      for (Long var5 : var3) {
         INativeInstructionItem var6 = (INativeInstructionItem)this.Dw.getItemAt(var5);
         if (var6 == null) {
            q.error(S.L("Cannot retrieve item at %Xh"), var5);
         } else {
            fA var7 = (fA)var6.getInstruction();

            for (YX var9 : this.gO) {
               if (var9.q(var5, var7, var2, var3)) {
                  Object[] var10000 = new Object[]{var5, var9.getClass().getSimpleName()};
                  this.q(var5, var2, var1, true, new eR(this.Dw).q(var5, var7, var2, var3));
                  break;
               }
            }
         }
      }

      var3.removeAll(var1);
      this.Dw(var1);
      this.Dw(var1);
      if (var1.isEmpty()) {
         HashSet var10 = new HashSet();
         boolean var11 = false;

         while (!var11) {
            var11 = true;
            TreeSet var12 = this.RF(var2);

            for (Long var14 : var12) {
               if (!var10.contains(var14)) {
                  var11 = false;
                  Object[] var15 = new Object[]{var14, "Erroneous BB"};
                  this.q(var14, var2, var1, true, true);
               }
            }

            var10.addAll(var12);
         }
      }

      return !var1.isEmpty();
   }

   private void q(long var1, TreeSet var3, TreeSet var4, boolean var5, boolean var6) {
      if (var5) {
         Long var7 = (Long)var3.lower(var1);
         if (var7 != null) {
            axp var8 = this.Dw.oW(var7);
            CFG var9 = var8.getData().getCFG();
            if (var9 != null && var9.getEndAddress() > var1) {
               var5 = false;
            }
         }
      }

      axp var23 = this.Dw.oW(var1);
      CFG var24 = var23.getData().getCFG();
      ArrayList var25 = new ArrayList();
      if (var5) {
         for (BasicBlock var11 : var24.getBlocks()) {
            if (this.Dw.getContainedRoutineAddresses(var11.getBase()).size() <= 1) {
               if (var25 != null) {
                  var25.add(var11);
                  AddressableInstruction var12 = var11.getBranchingInstruction2();
                  if (var12 != null && OC.q((fA)var12.getInstruction())) {
                     fA var13 = (fA)var12.getInstruction();
                     if (BN.q(this.xK, var12.getOffset(), var13)) {
                        try {
                           Long var14 = var13.q().q(var13, var12.getOffset(), null);
                           if (var14 != null && var3.contains(var14)) {
                              var25 = null;
                           }
                        } catch (ProcessorException var22) {
                        }
                     }
                  }
               }

               if (var11.getBase() != var24.getEntryAddress() && !var6) {
                  int var30 = ((fA)var11.getInstruction(var11.getBase())).getProcessorMode();
                  CodePointer var34 = this.Uv.q(var11.getBase(), var30, Jc.eo.RF);
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
               if (this.Dw.getContainedRoutineAddresses(var38.getOffset()).size() > 1) {
                  break;
               }

               var26.add(var38.getOffset());
            }
         }
      }

      this.Dw.RF(var1, true);
      var3.remove(var1);
      var4.add(var1);
      this.RF.oW(var1);

      for (Long var32 : var26) {
         INativeContinuousItem var36 = this.Dw.getItemAt(var32);
         if (var36 != null) {
            this.Dw.q(var36, true);
         }
      }

      boolean var29 = this.q(var1);
      List var33 = this.Dw.getContainedRoutineAddresses(var1);
      Set var37 = this.Dw.HF().getReferencesTo(var1);

      for (abk var15 : var37) {
         if (var15.getFrom().isInternalAddress()) {
            INativeContinuousItem var16 = this.Dw.getItemAt(var15.getFrom().getInternalAddress());
            IInstruction var17 = ((INativeInstructionItem)var16).getInstruction();
            if (var16 instanceof INativeInstructionItem) {
               InstructionHints var18 = ((axn)var16).getHints(false);
               if (var18 != null && var29) {
                  var18.setCondTailCall(false);
                  var18.setTailCall(false);
               }

               if (OC.Uv((fA)var17)) {
                  if (var18 == null) {
                     var18 = ((axn)var16).getHints(true);
                  }

                  var18.setFakeCall(true);
               }

               for (Long var21 : this.Dw.getContainedRoutineAddresses(var16.getMemoryAddress())) {
                  if (var21 != var1 && !var33.contains(var21)) {
                     this.oW.add(new CodePointer(var21, var17.getProcessorMode()));
                  }
               }
            }
         }
      }

      if (var29 && var37.isEmpty()) {
         INativeContinuousItem var40 = this.Dw.getPreviousItem(var1);
         IInstruction var41 = ((INativeInstructionItem)var40).getInstruction();

         for (Long var44 : this.Dw.getContainedRoutineAddresses(var40.getMemoryAddress())) {
            if (var44 != var1 && !var33.contains(var44)) {
               this.oW.add(new CodePointer(var44, var41.getProcessorMode()));
            }
         }
      }
   }

   public List xK() {
      return this.oW;
   }

   protected boolean q(long var1) {
      INativeContinuousItem var3 = this.Dw.getPreviousItem(var1);
      if (!(var3 instanceof INativeInstructionItem)) {
         return false;
      } else {
         fA var4 = (fA)((INativeInstructionItem)var3).getInstruction();
         if (OC.Dw(var4)) {
            return false;
         } else if (var3.getMemoryAddressEnd() == var1) {
            return true;
         } else {
            if (var3.getMemoryAddressEnd() + 2L == var1) {
               fA var5 = OC.q(this.xK.getProcessor(), this.xK.getMemory(), var3.getMemoryAddressEnd(), var4.getProcessorMode());
               if (var5 != null) {
                  return true;
               }
            }

            return false;
         }
      }
   }

   private TreeSet RF(TreeSet var1) {
      TreeSet var2 = this.xK(var1);
      TreeSet var3 = new TreeSet();

      for (Long var5 : var1) {
         if (var2.contains(var5)) {
            axp var6 = this.Dw.oW(var5);
            if (this.Uv.RF(var6)) {
               q.catching(new JebException("Trying to remove named routine " + var6));
            } else {
               var3.add(var5);
            }
         }
      }

      return var3;
   }

   private TreeSet xK(TreeSet var1) {
      TreeSet var2 = new TreeSet();

      for (long var4 : var1) {
         axp var6 = this.Dw.oW(var4);
         CFG var7 = var6.getData().getCFG();

         for (BasicBlock var9 : var7.getExitBlocks()) {
            fA var10 = (fA)var9.getLast();
            if (OC.q(var10)) {
               if (BN.q(this.xK, var9.getLastAddress(), var10)) {
                  continue;
               }
            } else {
               if (var10.gP() || OC.xK(var10)) {
                  continue;
               }

               INativeContinuousItem var11 = this.Dw.getItemAt(var9.getEndAddress());
               if (var11 instanceof INativeInstructionItem) {
                  continue;
               }
            }

            this.q(var2, var9.getBase());
         }
      }

      return var2;
   }

   private void q(TreeSet var1, long var2) {
      ArrayList var4 = new ArrayList();
      var4.add(var2);

      while (!var4.isEmpty()) {
         long var5 = (Long)var4.remove(0);
         if (!var1.contains(var5)) {
            var1.add(var5);
            LinkedHashSet var7 = new LinkedHashSet();

            for (abk var9 : this.Dw.HF().getReferencesTo(var5)) {
               if (var9.getFrom().isInternalAddress()) {
                  Long var10 = var9.getFrom().getInternalAddress();
                  var7.add(var10);
               }
            }

            for (Long var18 : this.Dw.getContainedRoutineAddresses(var5)) {
               axp var20 = this.Dw.oW(var18);
               CFG var11 = var20.getData().getCFG();
               BasicBlock var12 = var11.getBlockContaining(var5);

               for (BasicBlock var14 : var12.getInputBlocks()) {
                  var7.add(var14.getLastAddress());
               }
            }

            for (Long var19 : var7) {
               for (Long var22 : this.Dw.getContainedRoutineAddresses(var19)) {
                  axp var23 = this.Dw.oW(var22);
                  CFG var24 = var23.getData().getCFG();
                  BasicBlock var25 = var24.getBlockContaining(var19);
                  if (var25 != null) {
                     if (!OC.RF((fA)var25.getLast())) {
                        var4.add(var25.getBase());
                     } else if (!((fA)var25.getLast()).Uv().oW()) {
                        var4.add(var25.getBase());
                     } else if (OC.Uv((fA)var25.getLast()) && var5 == var25.getEndAddress()) {
                        this.RF.q(var25.getLastAddress() + ((fA)var25.getLast()).getSize());
                     } else {
                        INativeContinuousItem var15 = this.xK.za().getItemAt(var25.getLastAddress());
                        if (!(var15 instanceof axn) || ((axn)var15).Uv() < 1) {
                           var4.add(var25.getBase());
                        }
                     }
                     break;
                  }

                  q.catchingSilent(new Exception(Strings.ff("no origin bb at %xh", var19)));
               }
            }
         }
      }
   }

   public TreeSet q(Collection var1) {
      TreeSet var2 = new TreeSet();
      TreeSet var3 = new TreeSet();

      for (long var5 : var1) {
         axp var7 = this.Dw.oW(var5);
         if (this.Uv.RF(var7)) {
            var3.add(var5);
         } else if (this.Uv.xK(var7) && !this.q(var5)) {
            var3.add(var5);
         } else if (this.q(var5, var3)) {
            var2.add(var7.getMemoryAddress());
         } else {
            var3.add(var5);
         }
      }

      return var2;
   }

   public boolean q(long var1, TreeSet var3) {
      Set var4 = this.Dw.HF().getReferencesTo(var1);
      return var4.isEmpty() || this.q(var4, var3);
   }

   private boolean q(Set var1, TreeSet var2) {
      long var3 = 0L;
      Object var5 = null;

      for (IReference var7 : var1) {
         if (!var7.getFrom().isInternalAddress()) {
            return false;
         }

         long var8 = var7.getFrom().getInternalAddress();
         INativeContinuousItem var10 = this.Dw.getItemAt(var8);
         if (!(var10 instanceof INativeInstructionItem)) {
            return false;
         }

         List var11 = this.Dw.getContainedRoutineAddresses(var8);
         if (!var11.isEmpty()) {
            if (var5 == null) {
               var3 = var7.getTo().getInternalAddress();
               var5 = new ArrayList();

               for (Long var13 : var11) {
                  if (var2.contains(var13)) {
                     var5.add(var13);
                  } else {
                     axp var14 = this.Dw.oW(var13);
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
            axp var17 = this.Dw.oW(var16);
            if (var3 > var16 && var3 < var17.getData().getCFG().getEndAddress()) {
               return true;
            }
         }

         return false;
      }
   }

   private void Dw(TreeSet var1) {
      for (Long var3 : var1) {
         String var4 = this.Dw.LK().getLabel(var3, 0L, null);
         if (var4 != null && var4.startsWith("→")) {
            this.Dw.LK().removeLabel(var3);
         }
      }
   }
}
