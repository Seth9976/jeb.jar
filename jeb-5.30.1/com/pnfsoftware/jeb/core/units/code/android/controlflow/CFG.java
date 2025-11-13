package com.pnfsoftware.jeb.core.units.code.android.controlflow;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.DFA4;
import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IControlFlowGraph;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.ILocatedInstruction;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

@Ser
public class CFG implements IControlFlowGraph, Iterable {
   public static final AtomicInteger cfgfwccnt = new AtomicInteger();
   private static final long entry = 0L;
   private static final boolean allowUnreachableBlocks = false;
   private static int cacheType = 2;
   @SerId(1)
   private List bblist = null;
   @SerId(5)
   private int defaultCollectionFlags = 3;
   @SerId(6)
   private boolean defaultIntegrateInputs = true;
   @SerTransient
   IVariableInformationProvider varInfoProvider;
   @SerTransient
   List dfaObjects;

   public CFG(Collection var1) {
      this.bblist = new ArrayList(var1.size());
      HashMap var2 = new HashMap();

      for (BasicBlockBuilder var4 : var1) {
         BasicBlock var5 = new BasicBlock();
         var5.insns = var4.insns;
         var5.dst_offsets = var4.dst_offsets;
         var5.irrdst_offsets = var4.irrdst_offsets;
         this.bblist.add(var5);

         for (ILocatedInstruction var7 : var5.insns) {
            var2.put(var7.getOffset(), var5);
         }
      }

      this.buildGraphSecondPass(var2);
   }

   public CFG(List var1, List var2) {
      this.buildGraph(var1, var2);
   }

   public CFG(CFG var1) {
      this.defaultCollectionFlags = var1.defaultCollectionFlags;
      this.defaultIntegrateInputs = var1.defaultIntegrateInputs;
      this.varInfoProvider = null;
      this.dfaObjects = null;
      IdentityHashMap var2 = new IdentityHashMap();
      this.bblist = new ArrayList(var1.bblist.size());

      for (BasicBlock var4 : var1.bblist) {
         BasicBlock var5 = new BasicBlock(var4.insns);
         this.bblist.add(var5);
         var2.put(var4, var5);
      }

      for (BasicBlock var10 : var1.bblist) {
         BasicBlock var11 = (BasicBlock)var2.get(var10);

         for (BasicBlock var7 : var10.dst) {
            BasicBlock var8 = (BasicBlock)var2.get(var7);
            var11.dst.add(var8);
         }

         for (BasicBlock var15 : var10.src) {
            BasicBlock var18 = (BasicBlock)var2.get(var15);
            var11.src.add(var18);
         }

         for (BasicBlock var16 : var10.irrdst) {
            BasicBlock var19 = (BasicBlock)var2.get(var16);
            var11.irrdst.add(var19);
         }

         for (BasicBlock var17 : var10.irrsrc) {
            BasicBlock var20 = (BasicBlock)var2.get(var17);
            var11.irrsrc.add(var20);
         }

         var11.dst_offsets = null;
         var11.irrdst_offsets = null;
      }
   }

   public BasicBlock get(int var1) {
      return (BasicBlock)this.bblist.get(var1);
   }

   public int indexOf(BasicBlock var1) {
      return this.bblist.indexOf(var1);
   }

   public BasicBlock getLast() {
      return (BasicBlock)this.bblist.get(this.bblist.size() - 1);
   }

   @Override
   public int size() {
      return this.bblist.size();
   }

   @Override
   public long getEntryAddress() {
      return 0L;
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
      int var3 = this.getBlockIndex(var1);
      return var3 < 0 ? null : (BasicBlock)this.bblist.get(var3);
   }

   public int getBlockIndex(long var1) {
      int var3 = 0;
      int var4 = this.bblist.size();

      while (var4 > var3) {
         int var5 = var3 + (var4 - var3) / 2;
         BasicBlock var6 = (BasicBlock)this.bblist.get(var5);
         if (var1 < var6.getFirstAddress()) {
            var4 = var5;
         } else {
            if (var1 < var6.getEndAddress()) {
               return var1 == var6.getFirstAddress() ? var5 : -1;
            }

            var3 = var5 + 1;
         }
      }

      return -1;
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

   public BasicBlock getEntryBlock() {
      BasicBlock var1 = this.getBlockAt(0L);
      if (var1 == null) {
         throw new RuntimeException("Cannot find the entry block");
      } else {
         return var1;
      }
   }

   @Override
   public List getExitBlocks() {
      ArrayList var1 = new ArrayList(1);

      for (BasicBlock var3 : this.bblist) {
         if (var3.getOutputBlocks().isEmpty()) {
            var1.add(var3);
         }
      }

      return var1;
   }

   public BasicBlock getBlockByLastAddress(long var1) {
      BasicBlock var3 = this.getBlockContaining(var1);
      return var3 != null && var3.getLastAddress() == var1 ? var3 : null;
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

   public TreeMap getAddressBlockMap() {
      TreeMap var1 = new TreeMap();

      for (BasicBlock var3 : this.bblist) {
         var1.put(var3.get(0).getOffset(), var3);
      }

      return var1;
   }

   public BasicBlock getBlockFor(ILocatedInstruction var1) {
      for (BasicBlock var3 : this.bblist) {
         if (var3.insns.contains(var1)) {
            return var3;
         }
      }

      return null;
   }

   @Override
   public int getInstructionCount() {
      int var1 = 0;

      for (BasicBlock var3 : this.bblist) {
         var1 += var3.size();
      }

      return var1;
   }

   public ILocatedInstruction getInstructionAt(long var1) {
      return this.getInstruction(var1);
   }

   public ILocatedInstruction getInstruction(long var1) {
      BasicBlock var3 = this.getBlockContaining(var1);
      return var3 != null ? var3.getInstruction(var1) : null;
   }

   @Override
   public Couple getInstructionLocation(long var1) {
      return this.getInstructionLocation(var1, true);
   }

   public Couple getInstructionLocation(long var1, boolean var3) {
      BasicBlock var4 = this.getBlockContaining(var1);
      if (var4 == null) {
         return null;
      } else {
         int var5 = var4.getIndexOfInstruction(var1, var3);
         return var5 < 0 ? null : new Couple(var4, var5);
      }
   }

   public boolean containsInstruction(ILocatedInstruction var1) {
      Couple var2 = this.getInstructionLocation(var1.getOffset());
      return var2 == null ? false : ((BasicBlock)var2.getFirst()).get((Integer)var2.getSecond()) == var1;
   }

   @Override
   public List getInstructions() {
      ArrayList var1 = new ArrayList(this.getInstructionCount());
      this.bblist.forEach(var1x -> var1.addAll(var1x.insns));
      return var1;
   }

   public TreeMap getInstructionSet() {
      TreeMap var1 = new TreeMap();

      for (BasicBlock var3 : this.bblist) {
         for (ILocatedInstruction var5 : var3.insns) {
            var1.put(var5.getOffset(), var5);
         }
      }

      return var1;
   }

   @Override
   public Iterator iterator() {
      return this.bblist.iterator();
   }

   @Override
   public Iterable instructions() {
      return new CFG$1(this);
   }

   public Iterable instructions(long var1) {
      return new CFG$2(this, var1);
   }

   @Override
   public Iterable addressableInstructions() {
      return new CFG$3(this);
   }

   public Iterable handlers() {
      return new CFG$4(this);
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
         for (int var7 = var10.dst.size() - 1; var7 >= 0; var7--) {
            BasicBlock var8 = (BasicBlock)var10.dst.get(var7);
            var1.add(new int[]{(Integer)var3.get(var10), (Integer)var3.get(var8)});
         }

         for (int var11 = var10.irrdst.size() - 1; var11 >= 0; var11--) {
            BasicBlock var12 = (BasicBlock)var10.irrdst.get(var11);
            var2.add(new int[]{(Integer)var3.get(var10), (Integer)var3.get(var12)});
         }
      }
   }

   private void buildGraph(List var1, List var2) {
      if (var1.size() == 0) {
         throw new IllegalArgumentException("No instructions");
      } else if (((ILocatedInstruction)var1.get(0)).getOffset() != 0L) {
         throw new IllegalArgumentException("First instruction must have offset 0");
      } else {
         Collections.sort(var1, new CFG$5(this));
         ILocatedInstruction var3 = (ILocatedInstruction)var1.get(var1.size() - 1);
         long var4 = var3.getOffset() + var3.getSize();
         HashMap var6 = new HashMap();

         for (int var7 = 0; var7 < var1.size(); var7++) {
            var6.put(((ILocatedInstruction)var1.get(var7)).getOffset(), var7);
         }

         Object var20 = null;
         this.bblist = new ArrayList();
         HashMap var8 = new HashMap();
         ArrayDeque var9 = new ArrayDeque();
         var9.push(0L);
         int var10 = 0;

         label145:
         while (true) {
            if (!var9.isEmpty()) {
               long var23 = (Long)var9.pop();
               BasicBlock var26 = (BasicBlock)var8.get(var23);
               if (var26 == null) {
                  var26 = new BasicBlock();
                  this.bblist.add(var26);

                  while (true) {
                     BasicBlock var30 = (BasicBlock)var8.get(var23);
                     if (var30 != null) {
                        if (var26.insns.isEmpty()) {
                           throw new RuntimeException("Unexpected empty basic block");
                        }

                        var26.dst_offsets.add(((ILocatedInstruction)var30.insns.get(0)).getOffset());
                        break;
                     }

                     Integer var34 = (Integer)var6.get(var23);
                     if (var34 == null) {
                        throw new RuntimeException(Strings.ff("Failed converting index to offset: offset=0x%X", var23));
                     }

                     ILocatedInstruction var36 = (ILocatedInstruction)var1.get(var34);
                     if (var20 != null) {
                        var20.remove(var23);
                     }

                     var26.insns.add(var36);
                     var8.put(var23, var26);
                     IFlowInformation var39 = var36.getBreakingFlow();
                     if (var39.isBroken()) {
                        Iterator var18 = var39.getTargets().iterator();

                        while (true) {
                           if (!var18.hasNext()) {
                              continue label145;
                           }

                           ICodePointer var19 = (ICodePointer)var18.next();
                           if (var19.isUnknownAddress()) {
                              throw new RuntimeException("TBI: unknown address");
                           }

                           var26.dst_offsets.add(var19.getAddress());
                           var9.push(var19.getAddress());
                        }
                     }

                     var23 += var36.getSize();
                  }
               } else if (((ILocatedInstruction)var26.insns.get(0)).getOffset() != var23) {
                  int var29 = -1;
                  int var31 = 0;

                  for (ILocatedInstruction var17 : var26.insns) {
                     if (var17.getOffset() == var23) {
                        var29 = var31;
                        break;
                     }

                     var31++;
                  }

                  if (var29 < 0) {
                     throw new RuntimeException("Cannot split basic block");
                  }

                  BasicBlock var35 = new BasicBlock();
                  this.bblist.add(var35);

                  for (int var32 = var29; var32 < var26.insns.size(); var32++) {
                     ILocatedInstruction var37 = (ILocatedInstruction)var26.insns.get(var32);
                     var35.insns.add(var37);
                     var8.put(var37.getOffset(), var35);
                  }

                  var35.dst_offsets = new ArrayList(var26.dst_offsets);
                  int var38 = var26.insns.size() - var29;

                  for (int var33 = 0; var33 < var38; var33++) {
                     var26.insns.remove(var29);
                  }

                  var26.dst_offsets.clear();
                  var26.dst_offsets.add(var23);
               }
            } else if (var2 != null && var10 <= 0) {
               for (IrregularFlowData var24 : var2) {
                  var9.push(var24.target);
                  var9.push(var24.first);
                  ILocatedInstruction var25 = (ILocatedInstruction)var1.get((Integer)var6.get(var24.last));
                  long var28 = var25.getOffset() + var25.getSize();
                  if (var28 < var4) {
                     var9.push(var28);
                  }
               }

               var10++;
            } else {
               if (var20 == null || var20.isEmpty()) {
                  if (var2 != null) {
                     for (IrregularFlowData var12 : var2) {
                        for (BasicBlock var14 : this.bblist) {
                           long var15 = ((ILocatedInstruction)var14.insns.get(0)).getOffset();
                           if (var15 >= var12.first && var15 <= var12.last) {
                              var14.irrdst_offsets.add(var12.target);
                           }
                        }
                     }
                  }

                  this.buildGraphSecondPass(var8);
                  return;
               }

               long var11 = (Long)var20.iterator().next();
               var9.add(var11);
            }
         }
      }
   }

   public List generateIrregularFlowDataObjects() {
      ArrayList var1 = new ArrayList();

      for (BasicBlock var3 : this.bblist) {
         if (var3.irrdst != null) {
            for (BasicBlock var5 : var3.irrdst) {
               var1.add(new IrregularFlowData(var3.getFirstAddress(), var3.getLastAddress(), var5.getFirstAddress()));
            }
         }
      }

      return var1;
   }

   private void buildGraphSecondPass(Map var1) {
      for (BasicBlock var3 : this.bblist) {
         for (long var5 : var3.dst_offsets) {
            BasicBlock var7 = (BasicBlock)var1.get(var5);
            var3.dst.add(var7);
            var7.src.add(var3);
         }

         for (long var9 : var3.irrdst_offsets) {
            BasicBlock var10 = (BasicBlock)var1.get(var9);
            var3.irrdst.add(var10);
            var10.irrsrc.add(var3);
         }

         var3.dst_offsets = null;
         var3.irrdst_offsets = null;
      }

      Collections.sort(this.bblist, new CFG$6(this));
   }

   public int simplify() {
      int var1 = 0;
      int var2 = 0;

      while (var2 < this.bblist.size() - 1) {
         BasicBlock var3 = (BasicBlock)this.bblist.get(var2);
         if (var3.outsize() == 1 && var3.irroutsize() == 0 && !var3.getLast().getBreakingFlow().isBroken()) {
            BasicBlock var4 = (BasicBlock)this.bblist.get(var2 + 1);
            if (var4.insize() == 1 && var4.irrinsize() == 0 && var4.irroutsize() == 0 && var4.getInputBlock(0) == var3) {
               var3.insns.addAll(var4.insns);
               this.removeBlock(var4);
               var1++;
               continue;
            }
         }

         var2++;
      }

      return var1;
   }

   public int simplifyIrregularFlows() {
      int var1 = 0;
      int var2 = 0;

      while (var2 < this.bblist.size()) {
         BasicBlock var3 = (BasicBlock)this.bblist.get(var2);
         if (var3.outsize() == 1 && var3.size() >= 1 && !var3.getLast().getBreakingFlow().isBroken()) {
            ILocatedInstruction var4 = var3.getLast();
            long var5 = var4.getOffset() + var4.getSize();
            BasicBlock var7 = var3.getOutputBlock(0);
            if (var7.get(0).getOffset() == var5 && var7.insize() == 1 && var7.irrinsize() == 0 && var7.irroutsize() == 0) {
               boolean var8 = true;

               for (ILocatedInstruction var10 : var7.insns) {
                  if (var10.canThrow()) {
                     var8 = false;
                     break;
                  }
               }

               if (var8) {
                  for (ILocatedInstruction var12 : var7.insns) {
                     var3.insns.add(var12);
                  }

                  this.removeBlock(var7);
                  var1++;
                  continue;
               }
            }
         }

         var2++;
      }

      return var1;
   }

   public int reorganizeInputs() {
      int var1 = 0;

      for (BasicBlock var3 : this.bblist) {
         if (var3.src.size() >= 2) {
            for (int var4 = 0; var4 < var3.src.size(); var4++) {
               BasicBlock var5 = (BasicBlock)var3.src.get(var4);
               if (var5.getEndAddress() == var3.getAddress()) {
                  if (var4 > 0) {
                     var3.src.remove(var4);
                     var3.src.add(0, var5);
                     var1++;
                  }
                  break;
               }
            }
         }
      }

      return var1;
   }

   public boolean removeBlockSafe(BasicBlock var1) {
      try {
         this.removeBlock(var1);
         return true;
      } catch (IllegalStateException var4) {
         String var3 = var4.getMessage();
         if (var3 != null && var3.startsWith("Cannot remove block: ")) {
            return false;
         } else {
            throw var4;
         }
      }
   }

   public void removeBlock(BasicBlock var1) {
      if (var1.irrinsize() != 0) {
         throw new IllegalStateException("Cannot remove block: Has irregular input");
      } else {
         if (var1.outsize() == 1) {
            BasicBlock var2 = (BasicBlock)var1.dst.get(0);
            if (var2 == var1) {
               throw new IllegalStateException("Cannot remove block: Would lead to an infinite loop");
            }

            int var3 = 0;

            while (var3 < var2.src.size()) {
               if (var2.src.get(var3) == var1) {
                  var2.src.remove(var3);
               } else {
                  var3++;
               }
            }

            for (BasicBlock var5 : var1.src) {
               for (int var8 = 0; var8 < var5.dst.size(); var8++) {
                  if (var5.dst.get(var8) == var1) {
                     var5.dst.set(var8, var2);
                     var2.src.add(var5);
                  }
               }
            }
         } else if (var1.insize() == 1) {
            BasicBlock var6 = (BasicBlock)var1.src.get(0);
            if (var6 == var1) {
               throw new IllegalStateException("Cannot remove block: Would lead to an infinite loop");
            }

            int var9 = 0;

            while (var9 < var6.dst.size()) {
               if (var6.dst.get(var9) == var1) {
                  var6.dst.remove(var9);
               } else {
                  var9++;
               }
            }

            for (BasicBlock var14 : var1.dst) {
               for (int var10 = 0; var10 < var14.src.size(); var10++) {
                  if (var14.src.get(var10) == var1) {
                     var14.src.set(var10, var6);
                     var6.dst.add(var14);
                  }
               }
            }
         }

         for (BasicBlock var11 : var1.irrdst) {
            int var13 = 0;

            while (var13 < var11.irrsrc.size()) {
               if (var11.irrsrc.get(var13) == var1) {
                  var11.irrsrc.remove(var13);
               } else {
                  var13++;
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
            BasicBlock var4 = new BasicBlock();

            while (var2 < var1.insns.size()) {
               var4.insns.add((ILocatedInstruction)var1.insns.remove(var2));
            }

            var4.dst.addAll(var1.dst);
            var1.dst.clear();

            for (BasicBlock var6 : var4.dst) {
               var6.src.replaceAll(var2x -> var2x == var1 ? var4 : var2x);
            }

            var4.irrdst.addAll(var1.irrdst);

            for (BasicBlock var8 : var4.irrdst) {
               var8.irrsrc.add(var4);
            }

            var1.dst.add(var4);
            var4.src.add(var1);
            this.bblist.add(var3 + 1, var4);
            return var4;
         }
      }
   }

   public void addBlock(int var1, BasicBlock var2) {
      if (var1 < 0) {
         var1 += this.bblist.size() + 1;
      }

      this.bblist.add(var1, var2);
   }

   public void addBlock(BasicBlock var1) {
      this.bblist.add(var1);
   }

   public boolean addEdge(BasicBlock var1, BasicBlock var2) {
      if (var1.dst.contains(var2)) {
         return false;
      } else {
         var1.dst.add(var2);
         var2.src.add(var1);
         return true;
      }
   }

   public int addEdge(BasicBlock var1, BasicBlock var2, int var3) {
      if (var3 < 0) {
         var3 += var1.dst.size() + 1;
      }

      int var4 = CollectionUtil.identityCount(var1.dst, var2);
      var1.dst.add(var3, var2);
      var2.src.add(var1);
      return var4 + 1;
   }

   public boolean addIrregularEdge(BasicBlock var1, BasicBlock var2) {
      if (var1.irrdst.contains(var2)) {
         return false;
      } else {
         var1.irrdst.add(var2);
         var2.irrsrc.add(var1);
         return true;
      }
   }

   public int addIrregularEdge(BasicBlock var1, BasicBlock var2, int var3) {
      if (var3 < 0) {
         var3 += var1.irrdst.size() + 1;
      }

      int var4 = CollectionUtil.identityCount(var1.irrdst, var2);
      var1.irrdst.add(var3, var2);
      var2.irrsrc.add(var1);
      return var4 + 1;
   }

   public int reconnectEdges(BasicBlock var1, BasicBlock var2, BasicBlock var3) {
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
   }

   public int removeDuplicateEdges(BasicBlock var1, BasicBlock var2) {
      int var3 = -1;
      LinkedList var4 = null;

      for (int var5 = 0; var5 < var1.dst.size(); var5++) {
         BasicBlock var6 = (BasicBlock)var1.dst.get(var5);
         if (var6 == var2) {
            if (var3 < 0) {
               var3 = var5;
            } else {
               if (var4 == null) {
                  var4 = new LinkedList();
               }

               var4.add(0, var5);
            }
         }
      }

      if (var4 == null) {
         return 0;
      } else {
         for (int var8 : var4) {
            var1.dst.remove(var8);
            Assert.a(var2.src.remove(var1));
         }

         return var4.size();
      }
   }

   public int removeDuplicateIrregularEdges(BasicBlock var1, BasicBlock var2) {
      int var3 = -1;
      LinkedList var4 = null;

      for (int var5 = 0; var5 < var1.irrdst.size(); var5++) {
         BasicBlock var6 = (BasicBlock)var1.irrdst.get(var5);
         if (var6 == var2) {
            if (var3 < 0) {
               var3 = var5;
            } else {
               if (var4 == null) {
                  var4 = new LinkedList();
               }

               var4.add(0, var5);
            }
         }
      }

      if (var4 == null) {
         return 0;
      } else {
         for (int var8 : var4) {
            var1.irrdst.remove(var8);
            Assert.a(var2.irrsrc.remove(var1));
         }

         return var4.size();
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
      int var5 = -1;
      if (var4 != null && var4 < 0) {
         int var9 = -1;

         for (int var10 = var1.irrdst.size() - 1; var10 >= 0; var10--) {
            BasicBlock var11 = (BasicBlock)var1.irrdst.get(var10);
            if (var11 == var2) {
               if (var4 == var9) {
                  var5 = var10;
                  break;
               }

               var9--;
            }
         }
      } else {
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
      for (BasicBlock var3 : var1.dst) {
         var3.src.remove(var1);
      }

      var1.dst.clear();
   }

   public void deleteIrregularOutEdges(BasicBlock var1) {
      for (BasicBlock var3 : var1.irrdst) {
         var3.irrsrc.remove(var1);
      }

      var1.irrdst.clear();
   }

   public ILocatedInstruction replaceInstruction(long var1, ILocatedInstruction var3) {
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
                  ILocatedInstruction var10 = (ILocatedInstruction)var5.insns.get(var9);
                  var8 += var10.getSize();
               }

               int var12 = 0;

               for (ILocatedInstruction var11 : var4) {
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

   public synchronized IVariableInformationProvider setVariableInformationProvider(IVariableInformationProvider var1) {
      IVariableInformationProvider var2 = this.varInfoProvider;
      this.varInfoProvider = var1;
      return var2;
   }

   public synchronized IVariableInformationProvider getVariableInformationProvider() {
      return this.varInfoProvider;
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

   public List getCurrentDFAs() {
      return this.dfaObjects == null ? Collections.emptyList() : Collections.unmodifiableList(this.dfaObjects);
   }

   public IDFA doDataFlowAnalysis() {
      return this.doDataFlowAnalysis(false);
   }

   public IDFA doDataFlowAnalysis(boolean var1) {
      return this.doDataFlowAnalysis(var1, this.defaultCollectionFlags, this.defaultIntegrateInputs);
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

      DFA4 var6 = new DFA4(this);
      var6.setCacheConfiguration(cacheType);
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
      var1.setCacheConfiguration(cacheType);
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

   public String format() {
      return this.format(true, true, null);
   }

   public String format(boolean var1, boolean var2, Object var3) {
      StringBuilder var4 = new StringBuilder();

      for (BasicBlock var6 : this.bblist) {
         int var7 = 0;

         for (ILocatedInstruction var9 : var6.insns) {
            if (var1) {
               int var10 = var7 >= 1 ? 58 : (var6.irrinsize() == 0 ? 43 : 42);
               int var11 = var6.irroutsize() > 0 ? 80 : 32;
               Strings.ff(var4, "%04X/%X%c%c ", var9.getOffset(), var9.getSize(), Character.valueOf((char)var10), Character.valueOf((char)var11));
            }

            Strings.ff(var4, "%-100s  ", var9.format(var3));
            var4.append('\n');
            var7++;
         }
      }

      if (var2) {
         var4.append(this.formatEdges());
      }

      return var4.toString();
   }

   public String formatEdges() {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (BasicBlock var4 : this.bblist) {
         for (int var5 = 0; var5 < var4.dst.size(); var5++) {
            if (var2 == 0) {
               var1.append("  (EDGES: ");
            } else if (var2 >= 1) {
               var1.append(", ");
            }

            BasicBlock var6 = (BasicBlock)var4.dst.get(var5);
            Strings.ff(var1, "%04X->%04X", var4.getAddress(), var6.getAddress());
            var2++;
         }
      }

      if (var2 > 0) {
         var1.append(")");
      }

      int var8 = 0;

      for (BasicBlock var10 : this.bblist) {
         for (int var11 = 0; var11 < var10.irrdst.size(); var11++) {
            if (var8 == 0) {
               var1.append("\n  (IRR.E: ");
            } else if (var8 >= 1) {
               var1.append(", ");
            }

            BasicBlock var7 = (BasicBlock)var10.irrdst.get(var11);
            Strings.ff(var1, "%04X->%04X", var10.getAddress(), var7.getAddress());
            var8++;
         }
      }

      if (var8 > 0) {
         var1.append(")");
      }

      return var1.toString();
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

   private class AddressableInstructionsIterator implements Iterator {
      private int blkIndex;
      private int insnIndex;
      private BasicBlock blk;

      private AddressableInstructionsIterator() {
         if (CFG.this.bblist.size() > 0) {
            this.blk = (BasicBlock)CFG.this.bblist.get(0);
         }
      }

      @Override
      public boolean hasNext() {
         return this.blk != null;
      }

      public AddressableInstruction next() {
         if (this.blk == null) {
            throw new NoSuchElementException("No more instructions");
         } else {
            ILocatedInstruction var1 = this.blk.get(this.insnIndex);
            this.insnIndex++;
            if (this.insnIndex >= this.blk.size()) {
               this.insnIndex = 0;
               this.blkIndex++;
               this.blk = this.blkIndex >= CFG.this.bblist.size() ? null : (BasicBlock)CFG.this.bblist.get(this.blkIndex);
            }

            return new AddressableInstruction(var1.getOffset(), var1);
         }
      }

      @Override
      public void remove() {
         throw new UnsupportedOperationException("Instruction removal not supported in iterator");
      }
   }

   class HandlersIterator implements Iterator {
      int idx;
      BasicBlock h;

      @Override
      public boolean hasNext() {
         if (this.h != null) {
            return true;
         } else {
            while (this.idx < CFG.this.bblist.size()) {
               BasicBlock var1 = (BasicBlock)CFG.this.bblist.get(this.idx);
               if (var1.irrinsize() > 0) {
                  this.h = var1;
                  return true;
               }

               this.idx++;
            }

            return false;
         }
      }

      public BasicBlock next() {
         BasicBlock var1 = this.h;
         this.h = null;
         this.idx++;
         return var1;
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

      private InstructionsIterator(BasicBlock var2, int var3) {
         if (var2 != null) {
            this.blk = var2;
            this.insnIndex = var3;
            this.blkIndex = CFG.this.bblist.indexOf(var2);
         }
      }

      @Override
      public boolean hasNext() {
         return this.blk != null;
      }

      public ILocatedInstruction next() {
         if (this.blk == null) {
            throw new NoSuchElementException("No more instructions");
         } else {
            ILocatedInstruction var1 = this.blk.get(this.insnIndex);
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
         throw new UnsupportedOperationException("Instruction removal not supported in iterator");
      }
   }
}
