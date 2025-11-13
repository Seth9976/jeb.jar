package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IReference;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IReferenceManager;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.NativeAnalyzerException;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.InstructionHints;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.collect.LongSegment;
import com.pnfsoftware.jeb.util.collect.SegmentMap;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Booleans;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Uk {
   private static final ILogger q = GlobalLog.getLogger(Uk.class);
   private final FS RF;
   private final aae xK;
   private final aaf Dw;
   private final Jc Uv;
   private final IReferenceManager oW;
   private final int gO = 1500;
   private boolean nf = false;
   private final boolean gP;

   public Uk(FS var1, aae var2, Jc var3) {
      this(var1, var2, var3, false);
   }

   public Uk(FS var1, aae var2, Jc var3, boolean var4) {
      this.RF = var1;
      this.xK = var2;
      this.Uv = var3;
      this.Dw = var2.za();
      this.oW = this.Dw.HF();
      this.gP = var4;
   }

   public boolean q() {
      Uk.CU var1 = this.RF();
      if (var1.q()) {
         return true;
      } else {
         var1 = this.q(var1);
         var1 = this.RF(var1);
         return this.xK(var1);
      }
   }

   public Uk.CU RF() {
      Uk.CU var1 = new Uk.CU();

      for (long var3 : this.Dw.getRoutineAddresses()) {
         axp var5 = this.Dw.oW(var3);
         CFG var6 = var5.getData().getCFG();
         if (((fA)var6.getEntryBlock().get(0)).getProcessorMode() == 16) {
            for (AddressableInstruction var8 : var6.addressableInstructions()) {
               HashSet var9 = new HashSet();
               if (var8.getProcessorMode() == 16 && OC.Uv((fA)var8.getInstruction())) {
                  var9.add(var8.getOffset());

                  for (IReference var12 : this.oW.getReferencesFrom(var8.getOffset())) {
                     Long var13 = var12.getTo().getInternalAddress();
                     if (var13 != null && Math.abs(var13 - var8.getOffset()) > 1500L) {
                        Set var14 = this.oW.getReferencesTo(var13);
                        Set var15 = (Set)var14.stream()
                           .filter(
                              var2 -> var2.getType().isCode()
                                 && (var2.getType() == ReferenceType.BRANCH || var2.getType() == ReferenceType.COND_BRANCH)
                                 && this.Dw.getItemAt(var2.getFrom().getInternalAddress()) != null
                                 && this.Dw.getItemAt(var2.getFrom().getInternalAddress()).getMemorySize() == 2L
                                 && this.Dw.oW(var2.getFrom().getInternalAddress()) == null
                                 && this.Dw.getPreviousItem(var2.getFrom().getInternalAddress()) instanceof INativeInstructionItem
                                 && !((INativeInstructionItem)this.Dw.getPreviousItem(var2.getFrom().getInternalAddress()))
                                    .getInstruction()
                                    .getMnemonic()
                                    .equalsIgnoreCase("POP")
                                 && (
                                    this.Dw.xK(var2.getFrom().getInternalAddress(), false) == null
                                       || !((Long)Lists.getFirst(this.Dw.getContainedRoutineAddresses(var2.getFrom().getInternalAddress()))).equals(var13)
                                 )
                           )
                           .collect(Collectors.toSet());
                        if (!var15.isEmpty()) {
                           var9.add(var13);
                           boolean var24 = true;

                           for (IReference var30 : (Set)var14.stream()
                              .filter(var1x -> !var1x.equals(var12) && var1x.getType() == ReferenceType.ROUTINE_CALL)
                              .collect(Collectors.toSet())) {
                              if (Math.abs(var30.getFrom().getInternalAddress() - var13) <= 1500L) {
                                 var24 = false;
                                 break;
                              }
                           }

                           if (var24) {
                              var9.addAll((Collection)var15.stream().map(var0 -> var0.getFrom().getInternalAddress()).collect(Collectors.toSet()));
                              long var28 = (Long)Collections.min(var9);
                              long var31 = (Long)Collections.max(var9);
                              var1.q(var28, var31);
                           }
                        } else if (var14.size() == 1) {
                           boolean var16 = false;
                           if (var13 > var6.getFirstAddress()) {
                              if (var13 < var6.getEndAddress()) {
                                 var16 = true;
                              } else if (var13 == var6.getEndAddress()) {
                                 fA var17 = (fA)var6.getInstruction(var6.getEndAddress() - 2L);
                                 if (var17 == null) {
                                    var17 = (fA)var6.getInstruction(var6.getEndAddress() - 4L);
                                    if (var17.getSize() != 4) {
                                       var17 = null;
                                    }
                                 }

                                 if (var17 != null && (!var17.Uv().oW() || !OC.q(var17))) {
                                    var16 = true;
                                 }
                              }

                              if (!var16) {
                                 long var25 = var8.getOffset();
                                 boolean var19 = false;

                                 while (var25 < var13) {
                                    axp var20 = this.Dw.Uv(var25);
                                    if (this.Uv.q(var20)) {
                                       var19 = true;
                                       break;
                                    }

                                    var25 = var20.getMemoryAddress();
                                 }

                                 if (!var19) {
                                    var16 = true;
                                 }
                              }
                           }

                           if (var16) {
                              var9.add(var13);
                              long var26 = (Long)Collections.min(var9);
                              long var29 = (Long)Collections.max(var9);
                              var1.q(var26, var29);
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      if (this.gP) {
         for (Uk.eo var23 : var1) {
            Object[] var10000 = new Object[]{var23.getBegin(), var23.getEnd()};
         }
      }

      return var1;
   }

   public Uk.CU q(Uk.CU var1) {
      if (this.gP) {
         Object[] var10000 = new Object[0];
      }

      Uk.CU var2 = new Uk.CU();

      for (Uk.eo var4 : var1) {
         axp var5 = this.Dw.oW(var4.getBegin());
         if (var5 == null || !this.Uv.q(var5)) {
            boolean var6 = false;

            for (long var7 = var4.getBegin(); var7 >= 0L; var7 -= 2L) {
               if (var7 < var4.getBegin() && var1.q.getSegmentContaining(var7) != null) {
                  var2.q(var7, var4.getEnd());
                  var6 = true;
                  if (this.gP) {
                     Object[] var27 = new Object[]{var4.getBegin(), var4.getEnd(), var7};
                  }
                  break;
               }

               axp var9 = this.Dw.oW(var7);
               if (var9 != null) {
                  if (this.q(var9, var7, var4, true)) {
                     var2.q(var7, var4.getEnd());
                     var6 = true;
                     if (this.gP) {
                        Object[] var26 = new Object[]{var4.getBegin(), var4.getEnd(), var7};
                     }
                     break;
                  }

                  this.Dw.RF(var7, true);
                  if (this.gP) {
                     Object[] var25 = new Object[]{var7};
                  }
               }
            }

            if (!var6 && this.gP) {
               Object[] var28 = new Object[]{var4.getBegin(), var4.getEnd()};
            }
         }
      }

      var1 = var2;
      if (this.gP) {
         Object[] var29 = new Object[0];
      }

      Uk.CU var16 = new Uk.CU();
      ArrayList var17 = new ArrayList();

      for (Uk.eo var20 : var2) {
         if (!var17.contains(var20.getBegin())) {
            boolean var22 = false;
            long var8 = this.q(var20.getEnd());

            for (long var10 = var20.getEnd(); var10 <= var8; var10 += 2L) {
               if (var10 > var20.getEnd() && var1.q.getSegmentContaining(var10) != null) {
                  axp var12 = this.Dw.oW(var10);
                  if (var12 != null && this.q(var12, var8, var20, false)) {
                     long var24 = var10 - 1L;
                     var16.q(var20.getBegin(), var24);
                     var22 = true;
                     if (this.gP) {
                        Object[] var33 = new Object[]{var20.getBegin(), var20.getEnd(), var24};
                     }
                     break;
                  }

                  if (this.gP) {
                     Object[] var30 = new Object[]{var20.getBegin(), var20.getEnd(), var10};
                  }

                  var10 = ((Uk.eo)var1.q.getSegmentContaining(var10)).getEnd();
                  var17.add(var10);
               }

               axp var23 = this.Dw.oW(var10);
               if (var23 != null) {
                  long var13 = var10 - 1L;
                  if (this.q(var23, var8, var20, false)) {
                     var16.q(var20.getBegin(), var13);
                     var22 = true;
                     if (this.gP) {
                        Object[] var32 = new Object[]{var20.getBegin(), var20.getEnd(), var10 - 1L};
                     }
                     break;
                  }

                  this.Dw.RF(var10, true);
                  if (this.gP) {
                     Object[] var31 = new Object[]{var10};
                  }
               }
            }

            if (!var22 && this.gP) {
               Object[] var34 = new Object[]{var20.getBegin(), var20.getEnd()};
            }
         }
      }

      if (this.gP) {
         Object[] var35 = new Object[0];

         for (Uk.eo var21 : var16) {
            var35 = new Object[]{var21.getBegin(), var21.getEnd()};
         }
      }

      return var16;
   }

   private long q(Long var1) {
      long var2 = var1 - 1L;

      axp var4;
      do {
         var4 = this.Dw.Uv(var2);
         if (var4 == null) {
            return var2 + 4097L;
         }

         var2 = var4.getMemoryAddress();
      } while (!this.Uv.q(var4));

      return var2;
   }

   private boolean q(INativeMethodItem var1, long var2, Uk.eo var4, boolean var5) {
      if (this.Uv.q(var1)) {
         return true;
      } else {
         Set var6 = this.oW.getReferencesTo(var1.getMemoryAddress());
         if (var6.isEmpty()) {
            return false;
         } else {
            Predicate var7;
            if (var5) {
               var7 = var3 -> var3.getType() == ReferenceType.ROUTINE_CALL
                  && (var3.getFrom().getInternalAddress() < var2 || var3.getFrom().getInternalAddress() > var4.getEnd());
            } else {
               var7 = var3 -> var3.getFrom().getInternalAddress() < var4.getBegin() || var3.getFrom().getInternalAddress() > var2;
            }

            return var6.stream().filter(var7).count() != 0L;
         }
      }
   }

   private Uk.CU RF(Uk.CU var1) {
      if (this.gP) {
         Object[] var10000 = new Object[0];
      }

      Uk.CU var2 = new Uk.CU();

      for (Uk.eo var4 : var1) {
         INativeContinuousItem var5 = this.Dw.getItemOver(var4.getEnd());
         if (var5 == null || var5 instanceof INativeDataItem) {
            var2.q(var4);
         } else if (var5 instanceof INativeInstructionItem && OC.nf((fA)((INativeInstructionItem)var5).getInstruction())) {
            var2.q(var4);
         } else if (this.oW.getReferencesTo(var5.getMemoryAddress()).stream().filter(var0 -> var0.getType().isData()).count() != 0L
            || this.oW
                  .getReferencesTo(this.Dw.getPreviousItem(var5.getMemoryAddress()).getMemoryAddress())
                  .stream()
                  .filter(var0 -> var0.getType().isData())
                  .count()
               != 0L) {
            var2.q(var4);
         } else if (this.q(var5)) {
            var2.q(var4);
         } else {
            if (!this.nf) {
               JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("invalid range found"));
               this.nf = true;
            }

            if (this.gP) {
               Object[] var6 = new Object[]{var4.getBegin(), var4.getEnd()};
            }
         }
      }

      return var2;
   }

   private boolean q(INativeContinuousItem var1) {
      if (var1 instanceof INativeInstructionItem) {
         IInstruction var2 = ((INativeInstructionItem)var1).getInstruction();
         IFlowInformation var3 = var2.getRoutineCall(var1.getMemoryAddress());
         if (var3.isBrokenKnown() && var3.getTargets().size() == 1) {
            axp var4 = this.Dw.oW(((ICodePointer)var3.getTargets().get(0)).getAddress());
            if (var4 == null || Booleans.isTrue(var4.getNonReturning())) {
               INativeContinuousItem var5 = this.Dw.getNextItem(var1);
               axp var6 = this.Dw.oW(var5.getMemoryAddress());
               if (var6 != null) {
                  return this.Uv.q(var6);
               }

               return this.Uv.q(var5.getMemoryAddress(), 0) != null;
            }
         }
      }

      return false;
   }

   private boolean xK(Uk.CU var1) {
      ArrayList var2 = new ArrayList();

      for (Uk.eo var4 : var1) {
         for (INativeContinuousItem var7 : this.Dw
            .getItemsInRange(
               var4.getBegin(),
               true,
               var4.getEnd() + 1L,
               true,
               var0 -> var0 instanceof INativeInstructionItem var1x && var1x.getInstruction().getMnemonic().equalsIgnoreCase("BL")
            )
            .values()) {
            if (this.oW
                  .getReferencesFrom(var7.getMemoryAddress())
                  .stream()
                  .filter(
                     var1x -> var1x.getTo().isInternalAddress()
                        && var1x.getTo().getInternalAddress() > var4.getBegin()
                        && var1x.getTo().getInternalAddress() <= var4.getEnd()
                  )
                  .count()
               == 1L) {
               var2.add(var7.getMemoryAddress());
               InstructionHints var8 = ((INativeInstructionItem)var7).getHints(true);
               var8.setFakeCall(true);
               INativeContinuousItem var9 = this.Dw.getItemAt(var7.getMemoryAddressEnd());

               for (int var10 = 0; var9 != null && var10 <= 256 && var9 instanceof INativeInstructionItem; var10++) {
                  Set var11 = this.oW.getReferencesTo(var9.getMemoryAddress());
                  if (var11.stream().filter(var0 -> var0.getType().isCode()).count() != 0L) {
                     break;
                  }

                  this.Dw.q(var9, false);
                  var9 = this.Dw.getItemAt(var9.getMemoryAddressEnd());
               }
            }
         }
      }

      for (long var14 : var2) {
         this.oW.unrecordAllReferencesFrom(var14);
      }

      List var13 = this.Dw.getRoutineAddresses();

      for (Uk.eo var18 : var1) {
         for (Long var25 : (Set)var13.stream().filter(var1x -> var1x >= var18.getBegin() && var1x <= var18.getEnd()).collect(Collectors.toSet())) {
            this.Dw.RF(var25, true);
            if (this.gP) {
               Object[] var10000 = new Object[]{var25};
            }
         }
      }

      for (Uk.eo var19 : var1) {
         this.Dw.RF(var19.getBegin(), true);
         this.RF.RF(var19.getBegin(), var19.getEnd());
         this.xK.enqueuePointerForAnalysis(this.xK.getProcessor().createEntryPoint(var19.getBegin(), 16), 1);
      }

      if (this.xK.needsAnalysis()) {
         this.xK.io().q();
         this.xK.analyze(true, true);
      }

      boolean var17 = true;

      for (Uk.eo var22 : var1) {
         if (!var17) {
            break;
         }

         axp var24 = this.Dw.oW(var22.getBegin());
         if (var24 == null) {
            var17 = false;
            break;
         }

         for (long var26 = var22.getBegin() + 2L; var26 <= var22.getEnd(); var26 += 2L) {
            if (this.Dw.getItemAt(var26) instanceof INativeInstructionItem) {
               axp var27 = this.Dw.xK(var26, false);
               if (var27 != var24) {
                  var17 = false;
                  break;
               }
            }
         }
      }

      return var17;
   }

   private static class CU implements Iterable {
      SegmentMap q = new SegmentMap();

      public Uk.eo q(Uk.eo var1) {
         return (Uk.eo)this.q.addAndMerge(var1, (var0, var1x) -> Uk.eo.q((Long)var0.getFirst(), (Long)var0.getSecond(), var1x));
      }

      public Uk.eo q(long var1, long var3, AddressableInstruction var5) {
         Uk.eo var6 = new Uk.eo(var1, var3);
         return this.q(var6);
      }

      public boolean q() {
         return this.q.isEmpty();
      }

      public Uk.eo q(long var1, long var3) {
         return this.q(var1, var3, null);
      }

      @Override
      public Iterator iterator() {
         return this.q.values().iterator();
      }
   }

   @SerDisabled
   private static class eo extends LongSegment {
      public eo(long var1, long var3) {
         super(var1, var3 - var1);
      }

      public static Uk.eo q(long var0, long var2, List var4) {
         return new Uk.eo(var0, var2);
      }

      @Override
      public String toString() {
         return Strings.ff("[%xh-%xh]", this.getBegin(), this.getEnd());
      }
   }
}
