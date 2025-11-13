package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IReference;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IReferenceManager;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ReferenceLocation;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeSignatureMatchResult;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.primitives.Booleans;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class E implements YX {
   private static final int q = 100;
   private static final int RF = 1000;
   private final aae xK;
   private final aaf Dw;
   private final IReferenceManager Uv;

   public E(aae var1) {
      this.xK = var1;
      this.Dw = var1.za();
      this.Uv = this.Dw.HF();
   }

   public boolean q(long var1, fA var3, TreeSet var4, TreeSet var5) {
      axp var6 = this.Dw.oW(var1);
      CFG var7 = var6.getData().getCFG();
      Boolean var8 = this.q(var4, var5, var1, var7);
      if (var8 != null) {
         return !var8;
      } else if (this.q(var6)) {
         return false;
      } else if (Booleans.isTrue(var6.getNonReturning())) {
         return true;
      } else {
         fA var9 = (fA)var7.getEntryBlock().get(0);
         if (var9.getProcessorMode() == 64
            && var7.getEntryBlock().size() > 1
            && var9.Uv().oW()
            && Strings.isContainedIn(var9.Dw().q(), "LDR", "LDRB", "LDUR")
            && var9.q(0).Uv(8)) {
            var9 = (fA)var7.getEntryBlock().get(1);
         }

         if (var9.Uv().oW() && var9.Dw().q().equals("MOV")) {
            if (var9.getProcessorMode() != 64) {
               if (!var9.q(0).Uv(4) && !var9.q(0).Uv(5)) {
                  return false;
               }
            } else if (!var9.q(0).Uv(19) && !var9.q(0).Uv(20) && !var9.q(0).Uv(21)) {
               return false;
            }

            for (BasicBlock var11 : var7.getExitBlocks()) {
               AddressableInstruction var12 = var11.getBranchingInstruction2();
               if (var12 != null && (OC.gO((fA)var12.getInstruction()) || this.q(var12.getOffset(), (fA)var12.getInstruction()))) {
                  return false;
               }
            }

            return true;
         } else {
            return false;
         }
      }
   }

   private Boolean q(TreeSet var1, TreeSet var2, Long var3, CFG var4) {
      Long var5 = this.q(var1, var2, var3);
      Long var6 = this.RF(var1, var2, var3);
      if (var6 == null) {
         return true;
      } else {
         int var7 = 0;
         int var8 = 0;
         long var9 = var4.getEndAddress() - 1L;

         while ((var5 == null || var9 < var5) && var7 < 100 && var7 + var8 < 1000) {
            INativeContinuousItem var11 = this.Dw.getNextItem(var9);
            if (var11 == null) {
               break;
            }

            if (var11 instanceof INativeDataItem) {
               Set var12 = this.Uv.getReferencesTo(var11.getMemoryAddress());
               if (!var12.isEmpty() && var12.size() >= 1) {
                  boolean var13 = false;

                  for (IReference var15 : var12) {
                     ReferenceLocation var16 = var15.getFrom();
                     if (var16.isInternalAddress()) {
                        if (var6 <= var16.getInternalAddress() && var16.getInternalAddress() < var3) {
                           return false;
                        }

                        List var17 = this.Dw.getContainedRoutineAddresses(var16.getInternalAddress());
                        if (var17.size() > 0) {
                           for (Long var19 : var17) {
                              if (var19 == var3) {
                                 var13 = true;
                              }
                           }
                        }
                     }
                  }

                  if (var13) {
                     return true;
                  }
               }
            } else if (var11 instanceof INativeInstructionItem) {
               var9 = var11.getMemoryAddress();
               var8++;
               continue;
            }

            var9 = var11.getMemoryAddress();
            var7++;
         }

         return null;
      }
   }

   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private boolean q(INativeMethodItem var1) {
      if (this.xK.gP() == null) {
         return false;
      } else {
         boolean var6 = false /* VF: Semaphore variable */;

         boolean var9;
         label48: {
            label47: {
               try {
                  var6 = true;
                  List var2 = this.xK.gP().match(this.xK, Arrays.asList(var1.getData()), false, true, false);
                  if (var2.isEmpty()) {
                     var9 = false;
                     var6 = false;
                     break label48;
                  }

                  if (var2.size() == 1) {
                     if (((NativeSignatureMatchResult)var2.get(0)).getSignatures().isEmpty()) {
                        var9 = false;
                        var6 = false;
                        break label47;
                     }

                     var6 = false;
                  } else {
                     var6 = false;
                  }
               } finally {
                  if (var6) {
                     ((axp)var1).q(null);
                  }
               }

               var9 = true;
               ((axp)var1).q(null);
               return var9;
            }

            ((axp)var1).q(null);
            return var9;
         }

         ((axp)var1).q(null);
         return var9;
      }
   }

   private Long q(TreeSet var1, TreeSet var2, Long var3) {
      Long var4;
      for (var4 = (Long)var1.higher(var3); var4 != null; var4 = (Long)var1.higher(var4)) {
         if (!var2.contains(var4)) {
            return var4;
         }
      }

      return var4;
   }

   private Long RF(TreeSet var1, TreeSet var2, Long var3) {
      Long var4 = null;
      Long var5 = (Long)var1.lower(var3);

      for (int var6 = 0; var5 != null && var6 < 2; var5 = (Long)var1.lower(var5)) {
         var4 = var5;
         if (!var2.contains(var5)) {
            var6++;
         }
      }

      return var4;
   }

   public boolean q(long var1, fA var3) {
      if (OC.oW(var3)) {
         try {
            Long var4 = var3.q().q(var3, var1, null);
            if (var4 != null) {
               axp var5 = this.Dw.oW(var4);
               if (var5 != null && Booleans.isTrue(var5.getNonReturning())) {
                  return false;
               }
            }
         } catch (ProcessorException var6) {
         }

         return true;
      } else {
         return false;
      }
   }
}
