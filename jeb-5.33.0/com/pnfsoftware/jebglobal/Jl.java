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

public class Jl {
   private static final ILogger pC = GlobalLog.getLogger(Jl.class);
   private final com.pnfsoftware.jeb.corei.parsers.arm.Av A;
   private final a kS;
   private final Tw wS;
   private final Nd UT;
   private final IReferenceManager E;
   private final int sY = 1500;
   private boolean ys = false;
   private final boolean ld;

   public Jl(com.pnfsoftware.jeb.corei.parsers.arm.Av var1, a var2, Nd var3) {
      this(var1, var2, var3, false);
   }

   public Jl(com.pnfsoftware.jeb.corei.parsers.arm.Av var1, a var2, Nd var3, boolean var4) {
      this.A = var1;
      this.kS = var2;
      this.UT = var3;
      this.wS = var2.ys();
      this.E = this.wS.gp();
      this.ld = var4;
   }

   public boolean pC() {
      Jl.Sv var1 = this.A();
      if (var1.pC()) {
         return true;
      } else {
         var1 = this.pC(var1);
         var1 = this.A(var1);
         return this.kS(var1);
      }
   }

   public Jl.Sv A() {
      Jl.Sv var1 = new Jl.Sv();

      for (long var3 : this.wS.getRoutineAddresses()) {
         auu var5 = this.wS.E(var3);
         CFG var6 = var5.getData().getCFG();
         if (((com.pnfsoftware.jeb.corei.parsers.arm.rQ)var6.getEntryBlock().get(0)).getProcessorMode() == 16) {
            for (AddressableInstruction var8 : var6.addressableInstructions()) {
               HashSet var9 = new HashSet();
               if (var8.getProcessorMode() == 16 && PU.UT((com.pnfsoftware.jeb.corei.parsers.arm.rQ)var8.getInstruction())) {
                  var9.add(var8.getOffset());

                  for (IReference var12 : this.E.getReferencesFrom(var8.getOffset())) {
                     Long var13 = var12.getTo().getInternalAddress();
                     if (var13 != null && Math.abs(var13 - var8.getOffset()) > 1500L) {
                        Set var14 = this.E.getReferencesTo(var13);
                        Set var15 = (Set)var14.stream()
                           .filter(
                              var2 -> var2.getType().isCode()
                                 && (var2.getType() == ReferenceType.BRANCH || var2.getType() == ReferenceType.COND_BRANCH)
                                 && this.wS.getItemAt(var2.getFrom().getInternalAddress()) != null
                                 && this.wS.getItemAt(var2.getFrom().getInternalAddress()).getMemorySize() == 2L
                                 && this.wS.E(var2.getFrom().getInternalAddress()) == null
                                 && this.wS.getPreviousItem(var2.getFrom().getInternalAddress()) instanceof INativeInstructionItem
                                 && !((INativeInstructionItem)this.wS.getPreviousItem(var2.getFrom().getInternalAddress()))
                                    .getInstruction()
                                    .getMnemonic()
                                    .equalsIgnoreCase("POP")
                                 && (
                                    this.wS.kS(var2.getFrom().getInternalAddress(), false) == null
                                       || !((Long)Lists.getFirst(this.wS.getContainedRoutineAddresses(var2.getFrom().getInternalAddress()))).equals(var13)
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
                              var1.pC(var28, var31);
                           }
                        } else if (var14.size() == 1) {
                           boolean var16 = false;
                           if (var13 > var6.getFirstAddress()) {
                              if (var13 < var6.getEndAddress()) {
                                 var16 = true;
                              } else if (var13 == var6.getEndAddress()) {
                                 com.pnfsoftware.jeb.corei.parsers.arm.rQ var17 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var6.getInstruction(
                                    var6.getEndAddress() - 2L
                                 );
                                 if (var17 == null) {
                                    var17 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var6.getInstruction(var6.getEndAddress() - 4L);
                                    if (var17.getSize() != 4) {
                                       var17 = null;
                                    }
                                 }

                                 if (var17 != null && (!var17.UT().E() || !PU.pC(var17))) {
                                    var16 = true;
                                 }
                              }

                              if (!var16) {
                                 long var25 = var8.getOffset();
                                 boolean var19 = false;

                                 while (var25 < var13) {
                                    auu var20 = this.wS.UT(var25);
                                    if (this.UT.pC(var20)) {
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
                              var1.pC(var26, var29);
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      if (this.ld) {
         for (Jl.Av var23 : var1) {
            Object[] var10000 = new Object[]{var23.getBegin(), var23.getEnd()};
         }
      }

      return var1;
   }

   public Jl.Sv pC(Jl.Sv var1) {
      if (this.ld) {
         Object[] var10000 = new Object[0];
      }

      Jl.Sv var2 = new Jl.Sv();

      for (Jl.Av var4 : var1) {
         auu var5 = this.wS.E(var4.getBegin());
         if (var5 == null || !this.UT.pC(var5)) {
            boolean var6 = false;

            for (long var7 = var4.getBegin(); var7 >= 0L; var7 -= 2L) {
               if (var7 < var4.getBegin() && var1.pC.getSegmentContaining(var7) != null) {
                  var2.pC(var7, var4.getEnd());
                  var6 = true;
                  if (this.ld) {
                     Object[] var27 = new Object[]{var4.getBegin(), var4.getEnd(), var7};
                  }
                  break;
               }

               auu var9 = this.wS.E(var7);
               if (var9 != null) {
                  if (this.pC(var9, var7, var4, true)) {
                     var2.pC(var7, var4.getEnd());
                     var6 = true;
                     if (this.ld) {
                        Object[] var26 = new Object[]{var4.getBegin(), var4.getEnd(), var7};
                     }
                     break;
                  }

                  this.wS.A(var7, true);
                  if (this.ld) {
                     Object[] var25 = new Object[]{var7};
                  }
               }
            }

            if (!var6 && this.ld) {
               Object[] var28 = new Object[]{var4.getBegin(), var4.getEnd()};
            }
         }
      }

      var1 = var2;
      if (this.ld) {
         Object[] var29 = new Object[0];
      }

      Jl.Sv var16 = new Jl.Sv();
      ArrayList var17 = new ArrayList();

      for (Jl.Av var20 : var2) {
         if (!var17.contains(var20.getBegin())) {
            boolean var22 = false;
            long var8 = this.pC(var20.getEnd());

            for (long var10 = var20.getEnd(); var10 <= var8; var10 += 2L) {
               if (var10 > var20.getEnd() && var1.pC.getSegmentContaining(var10) != null) {
                  auu var12 = this.wS.E(var10);
                  if (var12 != null && this.pC(var12, var8, var20, false)) {
                     long var24 = var10 - 1L;
                     var16.pC(var20.getBegin(), var24);
                     var22 = true;
                     if (this.ld) {
                        Object[] var33 = new Object[]{var20.getBegin(), var20.getEnd(), var24};
                     }
                     break;
                  }

                  if (this.ld) {
                     Object[] var30 = new Object[]{var20.getBegin(), var20.getEnd(), var10};
                  }

                  var10 = ((Jl.Av)var1.pC.getSegmentContaining(var10)).getEnd();
                  var17.add(var10);
               }

               auu var23 = this.wS.E(var10);
               if (var23 != null) {
                  long var13 = var10 - 1L;
                  if (this.pC(var23, var8, var20, false)) {
                     var16.pC(var20.getBegin(), var13);
                     var22 = true;
                     if (this.ld) {
                        Object[] var32 = new Object[]{var20.getBegin(), var20.getEnd(), var10 - 1L};
                     }
                     break;
                  }

                  this.wS.A(var10, true);
                  if (this.ld) {
                     Object[] var31 = new Object[]{var10};
                  }
               }
            }

            if (!var22 && this.ld) {
               Object[] var34 = new Object[]{var20.getBegin(), var20.getEnd()};
            }
         }
      }

      if (this.ld) {
         Object[] var35 = new Object[0];

         for (Jl.Av var21 : var16) {
            var35 = new Object[]{var21.getBegin(), var21.getEnd()};
         }
      }

      return var16;
   }

   private long pC(Long var1) {
      long var2 = var1 - 1L;

      auu var4;
      do {
         var4 = this.wS.UT(var2);
         if (var4 == null) {
            return var2 + 4097L;
         }

         var2 = var4.getMemoryAddress();
      } while (!this.UT.pC(var4));

      return var2;
   }

   private boolean pC(INativeMethodItem var1, long var2, Jl.Av var4, boolean var5) {
      if (this.UT.pC(var1)) {
         return true;
      } else {
         Set var6 = this.E.getReferencesTo(var1.getMemoryAddress());
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

   private Jl.Sv A(Jl.Sv var1) {
      if (this.ld) {
         Object[] var10000 = new Object[0];
      }

      Jl.Sv var2 = new Jl.Sv();

      for (Jl.Av var4 : var1) {
         INativeContinuousItem var5 = this.wS.getItemOver(var4.getEnd());
         if (var5 == null || var5 instanceof INativeDataItem) {
            var2.pC(var4);
         } else if (var5 instanceof INativeInstructionItem && PU.ld((com.pnfsoftware.jeb.corei.parsers.arm.rQ)((INativeInstructionItem)var5).getInstruction())) {
            var2.pC(var4);
         } else if (this.E.getReferencesTo(var5.getMemoryAddress()).stream().filter(var0 -> var0.getType().isData()).count() != 0L
            || this.E
                  .getReferencesTo(this.wS.getPreviousItem(var5.getMemoryAddress()).getMemoryAddress())
                  .stream()
                  .filter(var0 -> var0.getType().isData())
                  .count()
               != 0L) {
            var2.pC(var4);
         } else if (this.pC(var5)) {
            var2.pC(var4);
         } else {
            if (!this.ys) {
               JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("invalid range found"));
               this.ys = true;
            }

            if (this.ld) {
               Object[] var6 = new Object[]{var4.getBegin(), var4.getEnd()};
            }
         }
      }

      return var2;
   }

   private boolean pC(INativeContinuousItem var1) {
      if (var1 instanceof INativeInstructionItem) {
         IInstruction var2 = ((INativeInstructionItem)var1).getInstruction();
         IFlowInformation var3 = var2.getRoutineCall(var1.getMemoryAddress());
         if (var3.isBrokenKnown() && var3.getTargets().size() == 1) {
            auu var4 = this.wS.E(((ICodePointer)var3.getTargets().get(0)).getAddress());
            if (var4 == null || Booleans.isTrue(var4.getNonReturning())) {
               INativeContinuousItem var5 = this.wS.getNextItem(var1);
               auu var6 = this.wS.E(var5.getMemoryAddress());
               if (var6 != null) {
                  return this.UT.pC(var6);
               }

               return this.UT.pC(var5.getMemoryAddress(), 0) != null;
            }
         }
      }

      return false;
   }

   private boolean kS(Jl.Sv var1) {
      ArrayList var2 = new ArrayList();

      for (Jl.Av var4 : var1) {
         for (INativeContinuousItem var7 : this.wS
            .getItemsInRange(
               var4.getBegin(),
               true,
               var4.getEnd() + 1L,
               true,
               var0 -> var0 instanceof INativeInstructionItem var1x && var1x.getInstruction().getMnemonic().equalsIgnoreCase("BL")
            )
            .values()) {
            if (this.E
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
               INativeContinuousItem var9 = this.wS.getItemAt(var7.getMemoryAddressEnd());

               for (int var10 = 0; var9 != null && var10 <= 256 && var9 instanceof INativeInstructionItem; var10++) {
                  Set var11 = this.E.getReferencesTo(var9.getMemoryAddress());
                  if (var11.stream().filter(var0 -> var0.getType().isCode()).count() != 0L) {
                     break;
                  }

                  this.wS.pC(var9, false);
                  var9 = this.wS.getItemAt(var9.getMemoryAddressEnd());
               }
            }
         }
      }

      for (long var14 : var2) {
         this.E.unrecordAllReferencesFrom(var14);
      }

      List var13 = this.wS.getRoutineAddresses();

      for (Jl.Av var18 : var1) {
         for (Long var25 : (Set)var13.stream().filter(var1x -> var1x >= var18.getBegin() && var1x <= var18.getEnd()).collect(Collectors.toSet())) {
            this.wS.A(var25, true);
            if (this.ld) {
               Object[] var10000 = new Object[]{var25};
            }
         }
      }

      for (Jl.Av var19 : var1) {
         this.wS.A(var19.getBegin(), true);
         this.A.A(var19.getBegin(), var19.getEnd());
         this.kS.enqueuePointerForAnalysis(this.kS.getProcessor().createEntryPoint(var19.getBegin(), 16), 1);
      }

      if (this.kS.needsAnalysis()) {
         this.kS.WR().pC();
         this.kS.analyze(true, true);
      }

      boolean var17 = true;

      for (Jl.Av var22 : var1) {
         if (!var17) {
            break;
         }

         auu var24 = this.wS.E(var22.getBegin());
         if (var24 == null) {
            var17 = false;
            break;
         }

         for (long var26 = var22.getBegin() + 2L; var26 <= var22.getEnd(); var26 += 2L) {
            if (this.wS.getItemAt(var26) instanceof INativeInstructionItem) {
               auu var27 = this.wS.kS(var26, false);
               if (var27 != var24) {
                  var17 = false;
                  break;
               }
            }
         }
      }

      return var17;
   }

   @SerDisabled
   private static class Av extends LongSegment {
      public Av(long var1, long var3) {
         super(var1, var3 - var1);
      }

      public static Jl.Av pC(long var0, long var2, List var4) {
         return new Jl.Av(var0, var2);
      }

      @Override
      public String toString() {
         return Strings.ff("[%xh-%xh]", this.getBegin(), this.getEnd());
      }
   }

   private static class Sv implements Iterable {
      SegmentMap pC = new SegmentMap();

      public Jl.Av pC(Jl.Av var1) {
         return (Jl.Av)this.pC.addAndMerge(var1, (var0, var1x) -> Jl.Av.pC((Long)var0.getFirst(), (Long)var0.getSecond(), var1x));
      }

      public Jl.Av pC(long var1, long var3, AddressableInstruction var5) {
         Jl.Av var6 = new Jl.Av(var1, var3);
         return this.pC(var6);
      }

      public boolean pC() {
         return this.pC.isEmpty();
      }

      public Jl.Av pC(long var1, long var3) {
         return this.pC(var1, var3, null);
      }

      @Override
      public Iterator iterator() {
         return this.pC.values().iterator();
      }
   }
}
