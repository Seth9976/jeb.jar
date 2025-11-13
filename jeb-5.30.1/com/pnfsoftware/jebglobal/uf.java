package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.base.Couple;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class uf {
   private final aaf q;
   private final Jc RF;

   public uf(aae var1, Jc var2) {
      this.RF = var2;
      this.q = var1.za();
   }

   public Set q() {
      TreeSet var1 = new TreeSet((var0, var1x) -> Long.compare(var0.getAddress(), var1x.getAddress()));
      TreeSet var2 = new TreeSet(this.q.getRoutineAddresses());

      for (long var4 : var2) {
         axp var6 = this.q.oW(var4);
         CFG var7 = var6.getData().getCFG();
         CodePointer var8 = this.q(var4, var6, var2);
         if (var8 != null) {
            var1.add(var8);
         }

         List var9 = this.q(var7);

         for (CodePointer var12 : this.q(var4, var6, var2, var9)) {
            var1.add(var12);
         }

         if (var6.getData().getCFG().size() > 1) {
            CodePointer var20 = this.RF.RF().RF(var6, true);
            if (var20 != null) {
               BasicBlock var21 = var7.getEntryBlock();
               long var13 = var6.getMemoryAddress();
               long var15 = var21.getEndAddress();
               if (((fA)var21.get(0)).Dw().q().equals("BX")) {
                  var15 = ((BasicBlock)var21.getAllOutputBlocks().get(0)).getEndAddress();
               }

               boolean var17 = false;

               for (Couple var19 : var9) {
                  if ((
                        var13 < var20.getAddress() && (Long)var19.getFirst() == var15 && (Long)var19.getSecond() == var20.getAddress()
                           || var13 > var20.getAddress() && (Long)var19.getSecond() == var13
                     )
                     && (Long)var19.getSecond() - (Long)var19.getFirst() > 64L) {
                     var17 = true;
                     break;
                  }
               }

               if (var17) {
                  var1.add(var20);
               }
            }
         }
      }

      return var1;
   }

   private List q(CFG var1) {
      List var2 = var1.getGaps();
      ArrayList var3 = new ArrayList();

      for (Couple var5 : var2) {
         boolean var6 = true;
         long var7 = (Long)var5.getFirst();

         while (var6 && var7 < var5.getSecond()) {
            var6 = false;

            for (abk var11 : this.q.HF().getReferencesTo(var7)) {
               if (var11.getFrom().isInternalAddress()) {
                  long var12 = var11.getFrom().getInternalAddress();
                  if (var7 - 4L <= var12 && var12 < var7) {
                     INativeContinuousItem var14 = this.q.getItemAt(var11.getFrom().getInternalAddress());
                     if (var14 != null && var14.getMemoryAddressEnd() == var7) {
                        axp var15 = this.q.oW(var7);
                        if (var15 != null) {
                           var7 = var15.oW().getCFG().getEndAddress();
                           var6 = true;
                           break;
                        }
                     }
                  }
               }
            }
         }

         if (var7 == (Long)var5.getFirst()) {
            var3.add(var5);
         } else if (var7 < (Long)var5.getSecond()) {
            var3.add(new Couple(var7, (Long)var5.getSecond()));
         }
      }

      return var3;
   }

   private CodePointer q(long var1, INativeMethodItem var3, TreeSet var4) {
      CodePointer var5 = null;
      long var6 = var3.getData().getCFG().getFirstAddress();
      List var9 = null;
      if (var3.getData().getCFG().getEntryAddress() > var6) {
         INativeContinuousItem var10 = this.q.getItemAt(var6);
         if (var10 instanceof INativeInstructionItem) {
            int var8 = ((INativeInstructionItem)var10).getInstruction().getProcessorMode();
            var5 = this.RF.q(var6, var8);
            if (var5 == null) {
               var9 = this.q.getContainedRoutineAddresses(var6);
               if (var9.size() >= 1) {
                  var5 = new CodePointer(var6, var8);
               }
            }
         }
      }

      return this.q(var3, var6, var5, var4, var9) ? var5 : null;
   }

   private List q(long var1, INativeMethodItem var3, TreeSet var4, List var5) {
      ArrayList var6 = new ArrayList();

      for (Couple var8 : var5) {
         boolean var9 = false;
         boolean var10 = false;
         long var11 = (Long)var8.getFirst();

         while (true) {
            Long var13 = (Long)var4.ceiling(var11);
            if (var13 == null || var13 >= (Long)var8.getSecond()) {
               break;
            }

            var9 = true;
            if (this.RF.q(this.q.oW(var13))) {
               var10 = true;
               break;
            }

            var11 = var13 + 2L;
         }

         if (!var9 && (Long)var8.getSecond() - (Long)var8.getFirst() > 16L) {
            var9 = true;
         }

         if (var9 || var10) {
            INativeContinuousItem var16 = this.q.getItemAt((Long)var8.getSecond());
            if (var16 instanceof INativeInstructionItem) {
               int var14 = ((INativeInstructionItem)var16).getInstruction().getProcessorMode();
               CodePointer var15 = var10 ? new CodePointer((Long)var8.getSecond(), var14) : this.RF.q((Long)var8.getSecond(), var14);
               if (var15 != null && this.q(var3, (Long)var8.getSecond(), var15, var4, null)) {
                  var6.add(var15);
               }
            }
         }
      }

      return var6;
   }

   private boolean q(INativeMethodItem var1, long var2, CodePointer var4, TreeSet var5, List var6) {
      if (var4 != null) {
         if (var4.getAddress() == var1.getMemoryAddress()) {
            return false;
         }

         CFG var7 = var1.getData().getCFG();

         for (BasicBlock var9 : var7) {
            if (var9.getFirstAddress() >= var2) {
               for (BasicBlock var11 : var9.getAllOutputBlocks()) {
                  if (var11.getFirstAddress() < var2 && var11.getFirstAddress() > var1.getMemoryAddress()) {
                     return false;
                  }
               }
            }
         }

         for (BasicBlock var18 : var7.getExitBlocks()) {
            if (var18.getFirstAddress() >= var2) {
               AddressableInstruction var20 = var18.getBranchingInstruction2();
               if (var20 != null) {
                  IFlowInformation var22 = var20.getBreakingFlow(var20.getOffset());
                  if (!var22.isBroken()) {
                     var22 = var20.getRoutineCall(var20.getOffset());
                  }

                  if (var22.isBroken()) {
                     for (ICodePointer var13 : var22.getTargets()) {
                        if (!var13.isUnknownAddress()
                           && var13.getAddress() < var2
                           && var13.getAddress() > var1.getMemoryAddress()
                           && !this.q(var5, var13.getAddress())) {
                           return false;
                        }
                     }
                  }
               }
            }
         }

         Long var17 = var2;

         label113:
         for (int var19 = 0; var19 < 8; var19++) {
            var17 = (Long)var5.lower(var17);
            if (var17 == null) {
               break;
            }

            axp var21 = this.q.oW(var17);
            CFG var23 = var21.oW().getCFG();
            if (var23.getEndAddress() > var2) {
               for (BasicBlock var26 : var23) {
                  if (var26.getFirstAddress() < var2) {
                     for (BasicBlock var15 : var26.getAllOutputBlocks()) {
                        if (var15.getFirstAddress() > var2) {
                           return false;
                        }

                        if (var15.getEndAddress() == var2 && var6 != null && var6.contains(var17)) {
                           return false;
                        }
                     }
                  }
               }

               BasicBlock var25 = var23.getBlockContaining(var2);
               if (var25 == null) {
                  return false;
               }

               if (var25.getFirstAddress() != var2) {
                  return false;
               }

               Iterator var27 = var25.getInputBlocks().iterator();

               BasicBlock var28;
               do {
                  if (!var27.hasNext()) {
                     continue label113;
                  }

                  var28 = (BasicBlock)var27.next();
               } while (var28.getEndAddress() != var2 || OC.Dw((fA)var28.getLast()));

               return false;
            }
         }
      }

      return true;
   }

   private boolean q(TreeSet var1, long var2) {
      return !var1.contains(var2) ? false : this.RF.q(this.q.oW(var2));
   }
}
