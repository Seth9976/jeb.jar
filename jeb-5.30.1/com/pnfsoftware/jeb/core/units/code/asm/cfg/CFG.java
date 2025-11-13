package com.pnfsoftware.jeb.core.units.code.asm.cfg;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.DFA4;
import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IControlFlowGraph;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.InstructionFlags;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BranchTarget;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IBranchResolution;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IInstructionAugmenter;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.concurrent.TimedOperationVerifier;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeSet;

@Ser
public class CFG implements IControlFlowGraph, Iterable {
   private static final ILogger logger = GlobalLog.getLogger(CFG.class);
   public static final int FLAG_SUBROUTINE_CALL_NOT_BREAKING = 1;
   public static final int FLAG_ALLOW_ARTIFICIAL_BLOCK_END = 2;
   public static final int FLAG_ALLOW_UNREACHABLE_BLOCKS = 4;
   @SerId(1)
   List bblist;
   @SerId(2)
   long entry;
   @SerId(3)
   int flags;
   @SerId(4)
   WeakReference varInfoProvider;
   @SerId(5)
   private int defaultCollectionFlags = 3;
   @SerId(6)
   private boolean defaultIntegrateInputs = true;
   @SerTransient
   TimedOperationVerifier tov;
   @SerTransient
   int dfaVersion;
   @SerTransient
   List dfaObjects;

   private CFG(long var1, int var3) {
      this.entry = var1;
      this.flags = var3;
   }

   public CFG(long var1, List var3) {
      this.entry = var1;
      this.flags = 0;
      this.bblist = var3;
      HashMap var4 = new HashMap();

      for (BasicBlock var6 : var3) {
         long var7 = var6.base;

         for (IInstruction var10 : var6) {
            var4.put(var7, var6);
            var7 += var10.getSize();
         }
      }

      this.buildGraphSecondPass(var4);
   }

   public CFG shallowCopy(boolean var1) {
      CFG var2 = new CFG(this.entry, this.flags);
      var2.bblist = new ArrayList(this.bblist.size());
      if (var1) {
         var2.bblist.addAll(this.bblist);
      }

      return var2;
   }

   public int getFlags() {
      return this.flags;
   }

   public BasicBlock get(int var1) {
      return (BasicBlock)this.bblist.get(var1);
   }

   public int indexOf(BasicBlock var1) {
      return this.bblist.indexOf(var1);
   }

   @Override
   public int size() {
      return this.bblist.size();
   }

   @Override
   public long getEntryAddress() {
      return this.entry;
   }

   @Override
   public long getFirstAddress() {
      return ((BasicBlock)this.bblist.get(0)).getFirstAddress();
   }

   @Override
   public long getLastAddress() {
      return ((BasicBlock)this.bblist.get(this.bblist.size() - 1)).getLastAddress();
   }

   @Override
   public long getEndAddress() {
      return ((BasicBlock)this.bblist.get(this.bblist.size() - 1)).getEndAddress();
   }

   @Override
   public int getEffectiveSize() {
      int var1 = 0;

      for (BasicBlock var3 : this.bblist) {
         var1 += (int)(var3.getEndAddress() - var3.getFirstAddress());
      }

      return var1;
   }

   public List getGaps() {
      ArrayList var1 = new ArrayList();
      long var2 = ((BasicBlock)this.bblist.get(0)).getEndAddress();

      for (int var4 = 1; var4 < this.bblist.size(); var4++) {
         BasicBlock var5 = (BasicBlock)this.bblist.get(var4);
         long var6 = var5.getFirstAddress() - var2;
         if (var6 > 0L) {
            var1.add(new Couple(var2, var2 + var6));
         }

         var2 = var5.getEndAddress();
      }

      return var1;
   }

   public BasicBlock getBlock(int var1) {
      return (BasicBlock)this.bblist.get(var1);
   }

   @Override
   public List getBlocks() {
      return new ArrayList(this.bblist);
   }

   @Override
   public List getBlocksView() {
      return Collections.unmodifiableList(this.bblist);
   }

   public BasicBlock getBlockAt(long var1) {
      int var3 = 0;
      int var4 = this.bblist.size();

      while (var4 > var3) {
         int var5 = var3 + (var4 - var3) / 2;
         BasicBlock var6 = (BasicBlock)this.bblist.get(var5);
         if (var1 < var6.getFirstAddress()) {
            var4 = var5;
         } else {
            if (var1 < var6.getEndAddress()) {
               return var1 == var6.getFirstAddress() ? var6 : null;
            }

            var3 = var5 + 1;
         }
      }

      return null;
   }

   BasicBlock getBlockAt_slow(long var1) {
      for (BasicBlock var4 : this.bblist) {
         if (var4.base > var1) {
            return null;
         }

         if (var4.base == var1) {
            return var4;
         }
      }

      return null;
   }

   public BasicBlock getBlockEndingAt(long var1) {
      int var3 = 0;
      int var4 = this.bblist.size();

      while (var4 > var3) {
         int var5 = var3 + (var4 - var3) / 2;
         BasicBlock var6 = (BasicBlock)this.bblist.get(var5);
         if (var1 <= var6.getFirstAddress()) {
            var4 = var5;
         } else {
            if (var1 <= var6.getEndAddress()) {
               return var1 == var6.getEndAddress() ? var6 : null;
            }

            var3 = var5 + 1;
         }
      }

      return null;
   }

   BasicBlock getBlockEndingAt_slow(long var1) {
      for (BasicBlock var4 : this.bblist) {
         if (var4.getEndAddress() == var1) {
            return var4;
         }
      }

      return null;
   }

   public BasicBlock getBlockContaining(long var1) {
      int var3 = 0;
      int var4 = this.bblist.size();

      while (var4 > var3) {
         int var5 = var3 + (var4 - var3) / 2;
         BasicBlock var6 = (BasicBlock)this.bblist.get(var5);
         if (var1 < var6.getFirstAddress()) {
            var4 = var5;
         } else {
            if (var1 < var6.getEndAddress()) {
               return var6;
            }

            var3 = var5 + 1;
         }
      }

      return null;
   }

   BasicBlock getBlockContaining_slow(long var1) {
      for (BasicBlock var4 : this.bblist) {
         if (var4.getFirstAddress() > var1) {
            return null;
         }

         if (var4.getEndAddress() > var1) {
            return var4;
         }
      }

      return null;
   }

   public BasicBlock getEntryBlock() {
      BasicBlock var1 = this.getBlockAt(this.entry);
      if (var1 == null) {
         throw new IllegalStateException("Cannot find the entry block");
      } else {
         return var1;
      }
   }

   @Override
   public List getExitBlocks() {
      ArrayList var1 = new ArrayList();

      for (BasicBlock var3 : this.bblist) {
         List var4 = var3.getOutputBlocks();
         if (var4.isEmpty()) {
            var1.add(var3);
         } else {
            AddressableInstruction var5 = var3.getBranchingInstruction2();
            if (var5 != null && var5.getInstructionFlags().contains(InstructionFlags.CONDITIONAL_EXEC) && var4.size() == 1) {
               IFlowInformation var6 = var5.getInstruction().getBreakingFlow(var5.getOffset());
               if (var6.isBroken()) {
                  for (ICodePointer var8 : var6.getTargets()) {
                     if (var8.isUnknownAddress() || this.getBlockAt(var8.getAddress()) == null) {
                        var1.add(var3);
                        break;
                     }
                  }
               }
            }
         }
      }

      return var1;
   }

   public BasicBlock getBlockByLastAddress(long var1) {
      BasicBlock var3 = this.getBlockContaining(var1);
      return var3 != null && var3.getLastAddress() == var1 ? var3 : null;
   }

   BasicBlock getBlockByLastAddress_slow(long var1) {
      for (BasicBlock var4 : this.bblist) {
         if (var4.getLastAddress() == var1) {
            return var4;
         }
      }

      return null;
   }

   public boolean hasExit() {
      for (BasicBlock var2 : this.bblist) {
         if (var2.getOutputBlocks().isEmpty()) {
            return true;
         }
      }

      return false;
   }

   public boolean hasNoExit() {
      for (BasicBlock var2 : this.bblist) {
         if (var2.getOutputBlocks().isEmpty()) {
            return false;
         }
      }

      return true;
   }

   public Map getAddressBlockMap() {
      HashMap var1 = new HashMap();

      for (BasicBlock var3 : this.bblist) {
         var1.put(var3.base, var3);
      }

      return var1;
   }

   @Override
   public List getInstructions() {
      ArrayList var1 = new ArrayList();

      for (BasicBlock var3 : this.bblist) {
         var1.addAll(var3.insns);
      }

      var1.trimToSize();
      return var1;
   }

   public Map getInstructionsMap() {
      HashMap var1 = new HashMap();

      for (BasicBlock var3 : this.bblist) {
         for (AddressableInstruction var5 : var3.addressableInstructions()) {
            var1.put(var5.getOffset(), var5.getInstruction());
         }
      }

      return var1;
   }

   @Override
   public int getInstructionCount() {
      int var1 = 0;

      for (BasicBlock var3 : this.bblist) {
         var1 += var3.size();
      }

      return var1;
   }

   @Override
   public IInstruction getInstruction(long var1) {
      BasicBlock var3 = this.getBlockContaining(var1);
      return var3 != null ? var3.getInstruction(var1) : null;
   }

   public AddressableInstruction getAddressableInstruction(long var1) {
      IInstruction var3 = this.getInstruction(var1);
      return var3 == null ? null : new AddressableInstruction(var1, var3);
   }

   public AddressableInstruction findInstruction(IInstruction var1) {
      for (BasicBlock var3 : this.bblist) {
         int var4 = var3.getIndexOfInstruction(var1);
         if (var4 >= 0) {
            long var5 = var3.getAddressOfInstruction(var4);
            return new AddressableInstruction(var5, var1);
         }
      }

      return null;
   }

   public Couple getInstructionLocation(IInstruction var1) {
      for (BasicBlock var3 : this.bblist) {
         int var4 = var3.getIndexOfInstruction(var1);
         if (var4 >= 0) {
            return new Couple(var3, var4);
         }
      }

      return null;
   }

   @Override
   public Couple getInstructionLocation(long var1) {
      BasicBlock var3 = this.getBlockContaining(var1);
      if (var3 == null) {
         return null;
      } else {
         int var4 = var3.getIndexOfInstruction(var1);
         return var4 < 0 ? null : new Couple(var3, var4);
      }
   }

   Couple getInstructionLocation_slow(long var1) {
      for (BasicBlock var4 : this.bblist) {
         if (var1 >= var4.getFirstAddress() && var1 < var4.getEndAddress()) {
            int var5 = var4.getIndexOfInstruction(var1);
            if (var5 >= 0) {
               return new Couple(var4, var5);
            }
            break;
         }
      }

      return null;
   }

   @Override
   public void getGraphRepresentation(List var1, List var2) {
      var1.clear();
      var2.clear();
      LinkedHashMap var3 = new LinkedHashMap();
      int var4 = 1;

      for (BasicBlock var6 : this.bblist) {
         var3.put(var6, var4);
         var4++;
      }

      for (BasicBlock var10 : this.bblist) {
         for (int var7 = var10.outsize() - 1; var7 >= 0; var7--) {
            BasicBlock var8 = var10.getOutputBlock(var7);
            var1.add(new int[]{(Integer)var3.get(var10), (Integer)var3.get(var8)});
         }

         for (int var11 = var10.irroutsize() - 1; var11 >= 0; var11--) {
            BasicBlock var12 = var10.getIrregularOutputBlock(var11);
            var2.add(new int[]{(Integer)var3.get(var10), (Integer)var3.get(var12)});
         }
      }
   }

   public CFG(List var1, List var2) {
      this(var1, var2, null, 0L, 0L, 0);
   }

   public CFG(List var1, List var2, IInstructionAugmenter var3, long var4, long var6, int var8) {
      if (var6 < var4) {
         throw new NullPointerException(Strings.ff("Illegal entry-point (%Xh), cannot be less than base (%Xh)", var6, var4));
      } else {
         HashMap var9 = new HashMap();
         long var10 = var4;

         for (IInstruction var13 : var1) {
            var9.put(var10, var13);
            var10 += var13.getSize();
         }

         this.buildCFG(var9, var2, var3, var6, var8);
      }
   }

   public CFG(Map var1, List var2, IInstructionAugmenter var3, long var4, int var6) {
      this.buildCFG(var1, var2, var3, var4, var6);
   }

   private void buildCFG(Map var1, List var2, IInstructionAugmenter var3, long var4, int var6) {
      if (var1 == null) {
         throw new NullPointerException("No instructions were provided");
      } else {
         boolean var7 = (var6 & 1) != 0;
         boolean var8 = (var6 & 2) != 0;
         boolean var9 = (var6 & 4) != 0;
         this.flags = var6;
         this.entry = var4;
         if (!var1.containsKey(var4)) {
            throw new RuntimeException(Strings.ff("The entry-point instruction (%Xh) is not present!", var4));
         } else {
            long var10 = 0L;

            for (long var13 : var1.keySet()) {
               if (var13 > var10) {
                  var10 = var13;
               }
            }

            TreeSet var32 = null;
            if (var9) {
               var32 = new TreeSet(var1.keySet());
            }

            this.bblist = new ArrayList();
            HashMap var33 = new HashMap();
            ArrayDeque var14 = new ArrayDeque();
            var14.push(var4);
            ArrayDeque var15 = var14;
            int var16 = 0;

            while (true) {
               label222:
               while (!var14.isEmpty()) {
                  long var17 = (Long)var14.pop();
                  BasicBlock var19 = (BasicBlock)var33.get(var17);
                  if (var19 != null) {
                     if (var19.getFirstAddress() != var17) {
                        BasicBlock var41 = var19.splitBlock(var17);
                        if (var41 == null) {
                           throw new RuntimeException();
                        }

                        this.bblist.add(var41);
                        long var44 = var17;

                        for (int var46 = 0; var46 < var41.insns.size(); var46++) {
                           IInstruction var50 = (IInstruction)var41.insns.get(var46);
                           var33.put(var44, var41);
                           var44 += var50.getSize();
                        }
                     }
                  } else {
                     var19 = new BasicBlock(var17);
                     this.bblist.add(var19);

                     while (true) {
                        BasicBlock var20 = (BasicBlock)var33.get(var17);
                        if (var20 != null) {
                           if (var19.isEmpty()) {
                              throw new RuntimeException("Unexpected empty basic block");
                           }

                           var19.dst_offsets.add(var20.getFirstAddress());
                           break;
                        }

                        IInstruction var21 = (IInstruction)var1.get(var17);
                        if (var32 != null) {
                           var32.remove(var17);
                        }

                        if (var21 == null) {
                           if (!var8) {
                              throw new RuntimeException(Strings.ff("No instruction at offset %Xh", var17));
                           }

                           if (var19.isEmpty()) {
                              this.bblist.remove(var19);
                           }
                           break;
                        }

                        var19.insns.add(var21);
                        var33.put(var17, var19);
                        if (var3 != null && var3.isArtificialEndOfBlock(var17)) {
                           break;
                        }

                        IFlowInformation var22 = var21.getBreakingFlow(var17);
                        IFlowInformation var23 = null;
                        if (var3 != null && var3.isReversedBranchSemantic(var17)) {
                           IFlowInformation var24 = var21.getRoutineCall(var17);
                           var22 = var24;
                           var23 = var24;
                        }

                        if (var22.isBroken()) {
                           Assert.a(var22.getDelaySlotCount() == 0, "Instructions with delay-slot are not supported by this CFG constructor");
                           if (var22.mustComputeFallThrough()) {
                              long var48 = var17 + var21.getSize();
                              var19.dst_offsets.add(var48);
                              var15.push(var48);
                           }

                           if (var22.isBrokenUnknown()) {
                              var19.unknownDst = true;
                           }

                           boolean var49 = false;
                           Iterator var25 = var22.getTargets().iterator();

                           while (true) {
                              if (!var25.hasNext()) {
                                 continue label222;
                              }

                              ICodePointer var26 = (ICodePointer)var25.next();
                              if (var26.isUnknownAddress() && !var49) {
                                 var49 = true;
                                 if (var3 != null) {
                                    IBranchResolution var29 = var3.getDynamicBranchResolution(var17);
                                    if (var29 != null) {
                                       for (BranchTarget var31 : var29.getTargets()) {
                                          if (var31.isInternal()) {
                                             long var51 = var31.getInternalAddress().getAddress();
                                             if (!var19.dst_offsets.isEmpty() && var51 >= var17 && var51 <= (Long)var19.dst_offsets.get(0)) {
                                                var19.dst_offsets.add(0, var51);
                                             } else {
                                                var19.dst_offsets.add(var51);
                                             }

                                             var15.push(var51);
                                          }
                                       }
                                    }
                                 }

                                 var19.unknownDst = true;
                              } else {
                                 long var27 = var26.getAddress();
                                 var19.dst_offsets.add(var27);
                                 var15.push(var27);
                              }
                           }
                        }

                        if (var23 == null) {
                           var23 = var21.getRoutineCall(var17);
                        }

                        if (var23.isBroken()) {
                           Assert.a(var23.getDelaySlotCount() == 0, "Instructions with delay-slot are not supported by this CFG constructor");
                           if (var23.noFallThrough()) {
                              break;
                           }

                           if (!var7) {
                              long var47 = var17 + var21.getSize();
                              var19.dst_offsets.add(var47);
                              var15.push(var47);
                              break;
                           }
                        }

                        var17 += var21.getSize();
                     }
                  }
               }

               if (var2 == null || var16 > 0) {
                  if (var32 == null || var32.isEmpty()) {
                     if (var2 != null) {
                        for (IrregularFlowData var37 : var2) {
                           for (BasicBlock var43 : this.bblist) {
                              long var45 = var43.getFirstAddress();
                              if (var45 >= var37.first && var45 <= var37.last && !var43.irrdst_offsets.contains(var37.target)) {
                                 var43.irrdst_offsets.add(var37.target);
                              }
                           }
                        }
                     }

                     this.buildGraphSecondPass(var33);
                     return;
                  }

                  long var35 = (Long)var32.iterator().next();
                  var14.add(var35);
               } else {
                  for (IrregularFlowData var18 : var2) {
                     var15.push(var18.target);
                     var15.push(var18.first);
                     IInstruction var39 = (IInstruction)var1.get(var18.last);
                     long var42 = var18.last + var39.getSize();
                     if (var42 < var10) {
                        var15.push(var42);
                     }
                  }

                  var16++;
               }
            }
         }
      }
   }

   private void buildGraphSecondPass(Map var1) {
      boolean var2 = (this.flags & 2) != 0;
      BasicBlock var3 = null;

      for (BasicBlock var5 : this.bblist) {
         if (var5.isEmpty()) {
            throw new RuntimeException(Strings.ff("Illegal graph: the block at address %Xh is empty!", var5.base));
         }

         for (long var7 : var5.dst_offsets) {
            BasicBlock var9 = (BasicBlock)var1.get(var7);
            if (var9 == null) {
               if (!var2) {
                  throw new RuntimeException(Strings.ff("block@%X was not found", var7));
               }
            } else {
               var5.dst.add(var9);
               var9.src.add(var5);
            }
         }

         for (long var11 : var5.irrdst_offsets) {
            BasicBlock var12 = (BasicBlock)var1.get(var11);
            var5.irrdst.add(var12);
            var12.irrsrc.add(var5);
         }

         var5.dst_offsets = null;
         var5.irrdst_offsets = null;
         if (var3 == null && var5.base == this.entry) {
            var3 = var5;
         }
      }

      if (var3 == null) {
         throw new RuntimeException(Strings.ff("Invalid entry address %Xh (does not match any block)", this.entry));
      } else {
         Collections.sort(this.bblist, new CFG$1(this));
      }
   }

   public int simplify() {
      return this.simplify(false, false, false);
   }

   public int simplify(boolean var1, boolean var2, boolean var3) {
      int var4 = 0;
      int var5 = 0;

      while (var5 < this.bblist.size()) {
         BasicBlock var6 = (BasicBlock)this.bblist.get(var5);
         boolean var7 = !var6.hasUnknownDst() && (var6.outsize() == 1 || var6.outsize() == 2 && var6.getOutputBlock(0) == var6.getOutputBlock(1));
         if (var7
            && var6.irroutsize() == 0
            && var6.size() >= 1
            && (var2 || var6.getBranchingInstruction2(true, false) == null)
            && (var1 || var6.getBranchingInstruction2(false, true) == null)) {
            BasicBlock var8 = var6.getOutputBlock(0);
            boolean var9 = var8.insize() == 1 || var8.insize() == 2 && var8.getInputBlock(0) == var8.getInputBlock(1);
            if (var9 && var8.irrinsize() == 0 && var8.irroutsize() == 0) {
               boolean var10 = var8.base == var6.getEndAddress();
               if (!var10 && var3 && var8.outsize() == 0 && var6.base < var8.base) {
                  boolean var11 = false;

                  for (BasicBlock var13 : this.bblist) {
                     if (var13 != var6 && var13 != var8 && var13.base > var6.base && var13.base < var8.base) {
                        var11 = true;
                     }
                  }

                  if (!var11) {
                     var10 = true;
                  }
               }

               if (var10) {
                  if (var8.insize() == 2 && var8.getInputBlock(0) == var8.getInputBlock(1) && !this.deleteDuplicateEdge(var6, var8)) {
                     Object[] var10000 = new Object[0];
                     throw new RuntimeException();
                  }

                  for (IInstruction var15 : var8) {
                     var6.insns.add(var15);
                  }

                  this.removeBlock(var8);
                  this.invalidateDataFlowAnalysis();
                  var4++;
                  continue;
               }
            }
         }

         var5++;
      }

      return var4;
   }

   public void removeBlock(BasicBlock var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Null block");
      } else if (var1.irrinsize() != 0) {
         Object[] var19 = new Object[0];
         throw new RuntimeException();
      } else {
         if (var1.outsize() == 1) {
            BasicBlock var2 = var1.getOutputBlock(0);
            if (var2 == var1) {
               Object[] var10000 = new Object[0];
               throw new RuntimeException();
            }

            int var3 = 0;

            while (var3 < var2.insize()) {
               if (var2.getInputBlock(var3) == var1) {
                  var2.src.remove(var3);
               } else {
                  var3++;
               }
            }

            for (BasicBlock var5 : var1.getInputBlocks()) {
               for (int var9 = 0; var9 < var5.outsize(); var9++) {
                  if (var5.getOutputBlock(var9) == var1) {
                     var5.dst.set(var9, var2);
                     var2.src.add(var5);
                  }
               }
            }
         } else if (var1.insize() == 1) {
            BasicBlock var6 = var1.getInputBlock(0);
            if (var6 == var1) {
               Object[] var18 = new Object[0];
               throw new RuntimeException();
            }

            int var10 = 0;

            while (var10 < var6.outsize()) {
               if (var6.getOutputBlock(var10) == var1) {
                  var6.dst.remove(var10);
               } else {
                  var10++;
               }
            }

            for (BasicBlock var17 : var1.getOutputBlocks()) {
               for (int var11 = 0; var11 < var17.insize(); var11++) {
                  if (var17.getInputBlock(var11) == var1) {
                     var17.src.set(var11, var6);
                     var6.dst.add(var17);
                  }
               }
            }
         } else if (var1.insize() == 0) {
            for (BasicBlock var12 : var1.getOutputBlocks()) {
               int var15 = 0;

               while (var15 < var12.insize()) {
                  if (var12.getInputBlock(var15) == var1) {
                     var12.src.remove(var15);
                  } else {
                     var15++;
                  }
               }
            }
         }

         for (BasicBlock var13 : var1.getIrregularOutputBlocks()) {
            int var16 = 0;

            while (var16 < var13.irrinsize()) {
               if (var13.getIrregularInputBlock(var16) == var1) {
                  var13.irrsrc.remove(var16);
               } else {
                  var16++;
               }
            }
         }

         this.bblist.remove(var1);
      }
   }

   public BasicBlock splitBlock(BasicBlock var1, int var2) {
      if (var1 == null) {
         throw new IllegalArgumentException("Null block");
      } else {
         int var3 = this.bblist.indexOf(var1);
         if (var3 < 0) {
            throw new IllegalArgumentException("The input block does not belong to this CFG");
         } else {
            long var4 = var1.getAddressOfInstruction(var2);
            BasicBlock var6 = new BasicBlock(var4);

            while (var2 < var1.insns.size()) {
               var6.insns.add((IInstruction)var1.insns.remove(var2));
            }

            var6.dst.addAll(var1.dst);
            var1.dst.clear();

            for (BasicBlock var8 : var6.dst) {
               var8.src.replaceAll(var2x -> var2x == var1 ? var6 : var2x);
            }

            var6.irrdst.addAll(var1.irrdst);

            for (BasicBlock var10 : var6.irrdst) {
               var10.irrsrc.add(var6);
            }

            var1.dst.add(var6);
            var6.src.add(var1);
            this.bblist.add(var3 + 1, var6);
            return var6;
         }
      }
   }

   public void addBlock(int var1, BasicBlock var2) {
      if (var2 == null) {
         throw new IllegalArgumentException("Null block");
      } else {
         if (var1 < 0) {
            var1 += this.bblist.size() + 1;
         }

         this.bblist.add(var1, var2);
      }
   }

   public void addBlock(BasicBlock var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Null block");
      } else {
         this.bblist.add(var1);
      }
   }

   public boolean addEdge(BasicBlock var1, BasicBlock var2) {
      if (var1 == null || var2 == null) {
         throw new IllegalArgumentException("Null block");
      } else if (var1.dst.contains(var2)) {
         return false;
      } else {
         var1.dst.add(var2);
         var2.src.add(var1);
         return true;
      }
   }

   public int addEdge(BasicBlock var1, BasicBlock var2, int var3) {
      if (var1 != null && var2 != null) {
         if (var3 < 0) {
            var3 += var1.dst.size() + 1;
         }

         int var4 = CollectionUtil.identityCount(var1.dst, var2);
         var1.dst.add(var3, var2);
         var2.src.add(var1);
         return var4 + 1;
      } else {
         throw new IllegalArgumentException("Null block");
      }
   }

   public boolean addIrregularEdge(BasicBlock var1, BasicBlock var2) {
      if (var1 == null || var2 == null) {
         throw new IllegalArgumentException("Null block");
      } else if (var1.irrdst.contains(var2)) {
         return false;
      } else {
         var1.irrdst.add(var2);
         var2.irrsrc.add(var1);
         return true;
      }
   }

   public int addIrregularEdge(BasicBlock var1, BasicBlock var2, int var3) {
      if (var1 != null && var2 != null) {
         if (var3 < 0) {
            var3 += var1.irrdst.size() + 1;
         }

         int var4 = CollectionUtil.identityCount(var1.irrdst, var2);
         var1.irrdst.add(var3, var2);
         var2.irrsrc.add(var1);
         return var4 + 1;
      } else {
         throw new IllegalArgumentException("Null block");
      }
   }

   private int reconnectEdges(BasicBlock var1, BasicBlock var2, BasicBlock var3) {
      int var4 = 0;

      while (true) {
         int var5 = this.reconnectEdge(var1, var2, var3, 0);
         if (var5 == 0) {
            return var4;
         }

         var4++;
      }
   }

   public int reconnectEdge(BasicBlock var1, BasicBlock var2, BasicBlock var3) {
      return this.reconnectEdge(var1, var2, var3, null);
   }

   public int reconnectEdge(BasicBlock var1, BasicBlock var2, BasicBlock var3, Integer var4) {
      if (var1 != null && var2 != null) {
         int var5 = -1;
         int var6 = 0;

         for (int var7 = 0; var7 < var1.dst.size(); var7++) {
            BasicBlock var8 = (BasicBlock)var1.dst.get(var7);
            if (var8 == var2) {
               if (var4 == null) {
                  if (var5 != -1) {
                     return -2;
                  }

                  var5 = var7;
               } else if (var4 == var6) {
                  var5 = var7;
                  break;
               }

               var6++;
            } else if (var3 != null && var8 == var3 && var4 == null) {
               return -1;
            }
         }

         if (var5 < 0) {
            return 0;
         } else {
            if (var3 == null) {
               var1.dst.remove(var5);
               var2.src.remove(var1);
            } else {
               var1.dst.set(var5, var3);
               var2.src.remove(var1);
               var3.src.add(var1);
            }

            return 1;
         }
      } else {
         throw new IllegalArgumentException("Null block");
      }
   }

   public int reconnectIrregularEdges(BasicBlock var1, BasicBlock var2, BasicBlock var3) {
      int var4 = 0;

      while (true) {
         int var5 = this.reconnectIrregularEdge(var1, var2, var3, 0);
         if (var5 == 0) {
            return var4;
         }

         var4++;
      }
   }

   public int reconnectIrregularEdge(BasicBlock var1, BasicBlock var2, BasicBlock var3) {
      return this.reconnectIrregularEdge(var1, var2, var3, null);
   }

   public int reconnectIrregularEdge(BasicBlock var1, BasicBlock var2, BasicBlock var3, Integer var4) {
      if (var1 != null && var2 != null) {
         int var5 = -1;
         int var6 = 0;

         for (int var7 = 0; var7 < var1.irrdst.size(); var7++) {
            BasicBlock var8 = (BasicBlock)var1.irrdst.get(var7);
            if (var8 == var2) {
               if (var4 == null) {
                  if (var5 != -1) {
                     return -2;
                  }

                  var5 = var7;
               } else if (var4 == var6) {
                  var5 = var7;
                  break;
               }

               var6++;
            } else if (var3 != null && var8 == var3 && var4 == null) {
               return -1;
            }
         }

         if (var5 < 0) {
            return 0;
         } else {
            if (var3 == null) {
               var1.irrdst.remove(var5);
               var2.irrsrc.remove(var1);
            } else {
               var1.irrdst.set(var5, var3);
               var2.irrsrc.remove(var1);
               var3.irrsrc.add(var1);
            }

            return 1;
         }
      } else {
         throw new IllegalArgumentException("Null block");
      }
   }

   public int deleteEdges(BasicBlock var1, BasicBlock var2) {
      return this.reconnectEdges(var1, var2, null);
   }

   public boolean deleteEdge(BasicBlock var1, BasicBlock var2) {
      return this.reconnectEdge(var1, var2, null, 0) == 1;
   }

   public int deleteEdge(BasicBlock var1, BasicBlock var2, int var3) {
      return this.reconnectEdge(var1, var2, null, var3);
   }

   public int deleteIrregularEdges(BasicBlock var1, BasicBlock var2) {
      return this.reconnectIrregularEdges(var1, var2, null);
   }

   public boolean deleteIrregularEdge(BasicBlock var1, BasicBlock var2) {
      return this.reconnectIrregularEdge(var1, var2, null, 0) == 1;
   }

   public int deleteIrregularEdge(BasicBlock var1, BasicBlock var2, int var3) {
      return this.reconnectIrregularEdge(var1, var2, null, var3);
   }

   public void deleteOutEdges(BasicBlock var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Null block");
      } else {
         for (BasicBlock var3 : var1.dst) {
            var3.src.remove(var1);
         }

         var1.dst.clear();
      }
   }

   public void deleteIrregularOutEdges(BasicBlock var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Null block");
      } else {
         for (BasicBlock var3 : var1.irrdst) {
            var3.irrsrc.remove(var1);
         }

         var1.irrdst.clear();
      }
   }

   public boolean deleteDuplicateEdge(BasicBlock var1, BasicBlock var2) {
      if (var1 != null && var2 != null) {
         int var3 = -1;
         boolean var4 = false;

         for (int var5 = 0; var5 < var1.outsize(); var5++) {
            BasicBlock var6 = var1.getOutputBlock(var5);
            if (var6 == var2) {
               if (var3 != -1) {
                  var4 = true;
                  break;
               }

               var3 = var5;
            }
         }

         if (!var4) {
            return false;
         } else if (var3 < 0) {
            return false;
         } else {
            var1.dst.remove(var3);
            var2.src.remove(var1);
            return true;
         }
      } else {
         throw new IllegalArgumentException("Null block");
      }
   }

   public void deleteAllEdges() {
      for (BasicBlock var2 : this.bblist) {
         var2.src.clear();
         var2.dst.clear();
         var2.irrsrc.clear();
         var2.irrdst.clear();
      }
   }

   public int removeUnreachableBlocks() {
      return this.removeUnreachableBlocks(true);
   }

   public int removeUnreachableBlocks(boolean var1) {
      return !var1 && (this.flags & 4) != 0 ? 0 : CFGUtil.removeUnreachableBlocks(this);
   }

   public IInstruction replaceInstruction(long var1, IInstruction var3) {
      if (var3 == null) {
         throw new IllegalArgumentException("Null instruction");
      } else {
         BasicBlock var4 = this.getBlockContaining(var1);
         if (var4 == null) {
            return null;
         } else {
            int var5 = var4.getIndexOfInstruction(var1);
            return var5 < 0 ? null : var4.set(var5, var3);
         }
      }
   }

   public boolean replaceInstructionsInBlock(long var1, int var3, Collection var4) {
      BasicBlock var5 = this.getBlockContaining(var1);
      if (var5 == null) {
         return false;
      } else {
         int var6 = var5.getIndexOfInstruction(var1);
         if (var6 < 0) {
            return false;
         } else {
            int var7 = var6 + var3;
            if (var7 > var5.size()) {
               return false;
            } else {
               int var8 = 0;

               for (int var9 = var6; var9 < var7; var9++) {
                  IInstruction var10 = (IInstruction)var5.insns.get(var9);
                  var8 += var10.getSize();
               }

               int var12 = 0;

               for (IInstruction var11 : var4) {
                  var12 += var11.getSize();
               }

               if (var12 != var8) {
                  return false;
               } else {
                  while (var3-- > 0) {
                     var5.insns.remove(var6);
                  }

                  var5.insns.addAll(var6, var4);
                  return true;
               }
            }
         }
      }
   }

   public IVariableInformationProvider setVariableInformationProvider(IVariableInformationProvider var1) {
      IVariableInformationProvider var2 = this.getVariableInformationProvider();
      this.varInfoProvider = new WeakReference(var1);
      return var2;
   }

   public IVariableInformationProvider getVariableInformationProvider() {
      return this.varInfoProvider == null ? null : (IVariableInformationProvider)this.varInfoProvider.get();
   }

   public void setTimedOperationVerifier(TimedOperationVerifier var1) {
      this.tov = var1;
   }

   public TimedOperationVerifier getTimedOperationVerifier() {
      return this.tov;
   }

   public List getCurrentDFAs() {
      return this.dfaObjects == null ? Collections.emptyList() : Collections.unmodifiableList(this.dfaObjects);
   }

   public IDFA doDataFlowAnalysis() {
      return this.doDataFlowAnalysis(false);
   }

   public int setDFADefaultCollectionFlags(int var1) {
      int var2 = this.defaultCollectionFlags;
      this.defaultCollectionFlags = var1;
      return var2;
   }

   public int getDFADefaultCollectionFlags() {
      return this.defaultCollectionFlags;
   }

   public boolean setDFADefaultIntegrateInputs(boolean var1) {
      boolean var2 = this.defaultIntegrateInputs;
      this.defaultIntegrateInputs = var1;
      return var2;
   }

   public boolean isDFADefaultIntegrateInputs() {
      return this.defaultIntegrateInputs;
   }

   public IDFA doDataFlowAnalysis(boolean var1) {
      return this.doDataFlowAnalysis(var1, this.defaultCollectionFlags);
   }

   public IDFA doDataFlowAnalysis(boolean var1, int var2) {
      return this.doDataFlowAnalysis(var1, var2, this.defaultIntegrateInputs);
   }

   public IDFA doDataFlowAnalysis(boolean var1, int var2, boolean var3) {
      this.dfaPrep(var1);

      for (IDFA var5 : this.dfaObjects) {
         if (var5.isValid()
            && var5.getVariableCollectionFlags() == var2
            && var5.isIntegrateCalculatedInputRegisters() == var3
            && var5.getVariableInformationProvider() == this.getVariableInformationProvider()) {
            return var5;
         }
      }

      IDFA var6 = this.createDataFlowAnalysisHelperObject();
      var6.setVariableCollectionFlags(var2);
      var6.setIntegrateCalculatedInputRegisters(var3);
      var6.setVariableInformationProvider(this.getVariableInformationProvider());
      var6.perform();
      this.dfaObjects.add(var6);
      return var6;
   }

   private void dfaPrep(boolean var1) {
      if (this.dfaObjects == null) {
         this.dfaObjects = new ArrayList();
      } else {
         int var2 = 0;

         while (var2 < this.dfaObjects.size()) {
            if (!var1 && ((IDFA)this.dfaObjects.get(var2)).isValid()) {
               var2++;
            } else {
               this.dfaObjects.remove(var2);
            }
         }
      }
   }

   public IDFA getDataFlowAnalysis() {
      return this.getDataFlowAnalysis(this.defaultCollectionFlags, this.defaultIntegrateInputs);
   }

   public IDFA getDataFlowAnalysis(int var1, boolean var2) {
      this.dfaPrep(false);

      for (IDFA var4 : this.dfaObjects) {
         if (var4.isValid()
            && var4.getVariableCollectionFlags() == var1
            && var4.isIntegrateCalculatedInputRegisters() == var2
            && var4.getVariableInformationProvider() == this.getVariableInformationProvider()) {
            return var4;
         }
      }

      return null;
   }

   public IDFA createDataFlowAnalysisHelperObject() {
      DFA4 var1 = new DFA4(this);
      var1.setVariableCollectionFlags(this.getDFADefaultCollectionFlags());
      var1.setIntegrateCalculatedInputRegisters(this.isDFADefaultIntegrateInputs());
      var1.setVariableInformationProvider(this.getVariableInformationProvider());
      var1.perform();
      return var1;
   }

   public void invalidateDataFlowAnalysis() {
      if (this.dfaObjects != null) {
         for (IDFA var2 : this.dfaObjects) {
            var2.invalidate();
         }

         this.dfaObjects.clear();
      }
   }

   public void invalidateDataFlowAnalysis(long var1) {
      if (this.dfaObjects != null) {
         for (IDFA var4 : this.dfaObjects) {
            var4.notifyInstructionUpdate(var1);
         }
      }
   }

   public String formatInstructions() {
      return this.format(false);
   }

   public String format() {
      return this.format(true);
   }

   private String format(boolean var1) {
      return this.format(var1, null);
   }

   private String format(boolean var1, IFormattingContextFactory var2) {
      StringBuilder var3 = new StringBuilder();

      for (BasicBlock var5 : this.getBlocks()) {
         int var6 = 0;

         for (IInstruction var8 : var5) {
            long var9 = var5.getAddressOfInstruction(var6);
            if (var1) {
               int var11 = var6 >= 1 ? 58 : (var5.getFirstAddress() == this.entry ? 62 : (var5.irrinsize() == 0 ? 43 : 42));
               Strings.ff(var3, "%04X/%X%c  ", var9, var8.getSize(), Character.valueOf((char)var11));
            }

            Object var12 = var2 == null ? var9 : var2.createFormattingContext(var8);
            var3.append(var8.format(var12));
            var3.append("\n");
            var6++;
         }
      }

      return var3.toString();
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder(Strings.ff("CFG(%d): ", this.bblist.size()));
      int var2 = 0;

      for (BasicBlock var4 : this.bblist) {
         if (var2 > 0) {
            var1.append(", ");
         }

         var1.append(var4);
         var2++;
      }

      return var1.toString();
   }

   @Override
   public Iterator iterator() {
      return this.bblist.iterator();
   }

   @Override
   public Iterable instructions() {
      return new CFG$2(this);
   }

   @Override
   public Iterable addressableInstructions() {
      return new CFG$3(this);
   }

   public Iterable indexedBlocks() {
      return new CFG$4(this);
   }

   private class AddressableInstructionsIterator implements Iterator {
      private int blkIndex;
      private int insnIndex;
      private BasicBlock blk;
      private long address;

      private AddressableInstructionsIterator() {
         if (CFG.this.bblist.size() > 0) {
            this.blk = (BasicBlock)CFG.this.bblist.get(0);
            this.address = this.blk.base;
         }
      }

      @Override
      public boolean hasNext() {
         return this.blk != null;
      }

      public AddressableInstruction next() {
         if (this.blk == null) {
            throw new NoSuchElementException();
         } else {
            IInstruction var1 = this.blk.get(this.insnIndex);
            AddressableInstruction var2 = new AddressableInstruction(this.address, var1);
            this.insnIndex++;
            if (this.insnIndex >= this.blk.size()) {
               this.insnIndex = 0;
               this.blkIndex++;
               if (this.blkIndex >= CFG.this.bblist.size()) {
                  this.blk = null;
               } else {
                  this.blk = (BasicBlock)CFG.this.bblist.get(this.blkIndex);
                  this.address = this.blk.base;
               }
            } else {
               this.address = this.address + var1.getSize();
            }

            return var2;
         }
      }

      @Override
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }

   private class IndexedBlocksIterator implements Iterator {
      private int i = 0;
      private int end = CFG.this.bblist.size();
      private boolean canRemove;

      @Override
      public boolean hasNext() {
         return this.i < this.end;
      }

      public Couple next() {
         Couple var1 = new Couple(this.i, (BasicBlock)CFG.this.bblist.get(this.i));
         this.i++;
         this.canRemove = true;
         return var1;
      }

      @Override
      public void remove() {
         if (!this.canRemove) {
            throw new IllegalStateException();
         } else {
            this.i--;
            CFG.this.bblist.remove(this.i);
            this.end--;
            this.canRemove = false;
         }
      }
   }

   private class InstructionsIterator implements Iterator {
      private int blkIndex;
      private int insnIndex;
      private BasicBlock blk;

      private InstructionsIterator() {
         if (CFG.this.bblist.size() > 0) {
            this.blk = (BasicBlock)CFG.this.bblist.get(0);
         }
      }

      @Override
      public boolean hasNext() {
         return this.blk != null;
      }

      public IInstruction next() {
         if (this.blk == null) {
            throw new NoSuchElementException();
         } else {
            IInstruction var1 = this.blk.get(this.insnIndex);
            this.insnIndex++;
            if (this.insnIndex >= this.blk.size()) {
               this.insnIndex = 0;
               this.blkIndex++;
               this.blk = this.blkIndex >= CFG.this.bblist.size() ? null : (BasicBlock)CFG.this.bblist.get(this.blkIndex);
            }

            return var1;
         }
      }

      @Override
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }
}
