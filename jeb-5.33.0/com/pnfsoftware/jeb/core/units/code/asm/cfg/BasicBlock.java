package com.pnfsoftware.jeb.core.units.code.asm.cfg;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.IBasicBlock;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Ser
public class BasicBlock implements IBasicBlock, Comparable {
   private static final ILogger logger = GlobalLog.getLogger(BasicBlock.class);
   @SerId(1)
   long base;
   @SerId(2)
   List insns;
   @SerTransient
   List dst_offsets;
   @SerTransient
   List irrdst_offsets;
   @SerId(3)
   List src;
   @SerId(4)
   List dst;
   @SerId(5)
   List irrsrc;
   @SerId(6)
   List irrdst;
   @SerId(7)
   boolean unknownDst;
   @SerTransient
   Map datamap;

   public BasicBlock(long var1) {
      this.base = var1;
      this.insns = new ArrayList();
      this.dst_offsets = new ArrayList();
      this.irrdst_offsets = new ArrayList();
      this.src = new ArrayList();
      this.dst = new ArrayList();
      this.irrsrc = new ArrayList();
      this.irrdst = new ArrayList();
   }

   public BasicBlock(long var1, List var3, List var4, List var5, boolean var6) {
      this.base = var1;
      this.insns = var3;
      this.dst_offsets = var4;
      this.irrdst_offsets = var5;
      this.unknownDst = var6;
      this.src = new ArrayList();
      this.dst = new ArrayList();
      this.irrsrc = new ArrayList();
      this.irrdst = new ArrayList();
   }

   public BasicBlock shallowCopy(boolean var1) {
      BasicBlock var2 = new BasicBlock(this.base);
      var2.insns.addAll(this.insns);
      if (var1) {
         var2.src.addAll(this.src);
         var2.dst.addAll(this.dst);
         var2.irrsrc.addAll(this.irrsrc);
         var2.irrdst.addAll(this.irrdst);
      }

      var2.dst_offsets = this.dst_offsets == null ? null : new ArrayList(this.dst_offsets);
      var2.irrdst_offsets = this.irrdst_offsets == null ? null : new ArrayList(this.irrdst_offsets);
      var2.unknownDst = this.unknownDst;
      return var2;
   }

   @Override
   public long getBase() {
      return this.base;
   }

   public long getAddress() {
      return this.base;
   }

   @Override
   public long getFirstAddress() {
      return this.base;
   }

   @Override
   public long getLastAddress() {
      long var1 = this.base;

      for (int var3 = 0; var3 < this.insns.size() - 1; var3++) {
         var1 += ((IInstruction)this.insns.get(var3)).getSize();
      }

      return var1;
   }

   @Override
   public long getEndAddress() {
      long var1 = this.base;

      for (IInstruction var4 : this.insns) {
         var1 += var4.getSize();
      }

      return var1;
   }

   @Override
   public long getAddressOfInstruction(int var1) {
      if (var1 >= 0 && var1 < this.insns.size()) {
         long var2 = this.base;

         for (int var4 = 0; var4 < var1; var4++) {
            var2 += ((IInstruction)this.insns.get(var4)).getSize();
         }

         return var2;
      } else {
         throw new ArrayIndexOutOfBoundsException();
      }
   }

   public long[] getAddresses() {
      long[] var1 = new long[this.insns.size()];
      int var2 = 0;
      long var3 = this.base;

      for (IInstruction var6 : this.insns) {
         var1[var2] = var3;
         var2++;
         var3 += var6.getSize();
      }

      return var1;
   }

   @Override
   public int getIndexOfInstruction(long var1) {
      long var3 = this.base;

      for (int var5 = 0; var5 < this.insns.size(); var5++) {
         if (var3 >= var1) {
            return var3 == var1 ? var5 : -1;
         }

         var3 += ((IInstruction)this.insns.get(var5)).getSize();
      }

      return -1;
   }

   public int getIndexOfInstruction(IInstruction var1) {
      int var2 = 0;

      for (IInstruction var4 : this.insns) {
         if (var4 == var1) {
            return var2;
         }

         var2++;
      }

      return -1;
   }

   @Override
   public boolean canThrow() {
      for (IInstruction var2 : this.insns) {
         if (var2.canThrow()) {
            return true;
         }
      }

      return false;
   }

   @Override
   public int size() {
      return this.insns.size();
   }

   @Override
   public boolean isEmpty() {
      return this.insns.isEmpty();
   }

   @Override
   public IInstruction get(int var1) {
      if (var1 < 0) {
         var1 += this.insns.size();
      }

      return (IInstruction)this.insns.get(var1);
   }

   public AddressableInstruction get2(int var1) {
      if (var1 >= 0 && var1 < this.insns.size()) {
         long var2 = this.base;
         IInstruction var4 = (IInstruction)this.insns.get(0);

         for (int var5 = 0; var5 < var1; var5++) {
            var2 += var4.getSize();
            var4 = (IInstruction)this.insns.get(var5 + 1);
         }

         return new AddressableInstruction(var2, var4);
      } else {
         throw new ArrayIndexOutOfBoundsException();
      }
   }

   @Override
   public IInstruction getLast() {
      return this.get(this.insns.size() - 1);
   }

   @Override
   public int getSizeOfInstructions() {
      int var1 = 0;

      for (IInstruction var3 : this.insns) {
         var1 += var3.getSize();
      }

      return var1;
   }

   public AddressableInstruction getLast2() {
      return this.get2(this.insns.size() - 1);
   }

   @Override
   public IInstruction getInstruction(long var1) {
      long var3 = this.base;

      for (IInstruction var6 : this.insns) {
         if (var3 >= var1) {
            return var3 == var1 ? var6 : null;
         }

         var3 += var6.getSize();
      }

      return null;
   }

   @Override
   public List getInstructions() {
      return new ArrayList(this.insns);
   }

   public AddressableInstruction getBranchingInstruction2(boolean var1, boolean var2) {
      long var3 = this.getEndAddress();

      for (int var5 = this.insns.size() - 1; var5 >= 0; var5--) {
         IInstruction var6 = (IInstruction)this.insns.get(var5);
         var3 -= var6.getSize();
         if (var1 && var6.getBreakingFlow(var3).isBroken()) {
            return new AddressableInstruction(var3, var6);
         }

         if (var2 && var6.getRoutineCall(var3).isBroken()) {
            return new AddressableInstruction(var3, var6);
         }
      }

      return null;
   }

   public AddressableInstruction getBranchingInstruction2() {
      return this.getBranchingInstruction2(true, true);
   }

   public boolean remove(int var1) {
      this.insns.remove(var1);
      return this.insns.isEmpty();
   }

   public void add(IInstruction var1) {
      this.insns.add(var1);
   }

   public void add(int var1, IInstruction var2) {
      this.insns.add(var1, var2);
   }

   public void addAll(Collection var1) {
      this.insns.addAll(var1);
   }

   public void addAll(int var1, Collection var2) {
      this.insns.addAll(var1, var2);
   }

   public IInstruction set(int var1, IInstruction var2) {
      if (((IInstruction)this.insns.get(var1)).getSize() != var2.getSize()) {
         throw new IllegalArgumentException("Replacement of instruction with different sizes");
      } else {
         return (IInstruction)this.insns.set(var1, var2);
      }
   }

   public IInstruction setLast(IInstruction var1) {
      return (IInstruction)this.insns.set(this.insns.size() - 1, var1);
   }

   @Override
   public int insize() {
      return this.src.size();
   }

   @Override
   public int irrinsize() {
      return this.irrsrc.size();
   }

   @Override
   public int allinsize() {
      return this.src.size() + this.irrsrc.size();
   }

   public BasicBlock getInputBlock(int var1) {
      return (BasicBlock)this.src.get(var1);
   }

   @Override
   public List getInputBlocks() {
      return new ArrayList(this.src);
   }

   public BasicBlock getIrregularInputBlock(int var1) {
      return (BasicBlock)this.irrsrc.get(var1);
   }

   @Override
   public List getIrregularInputBlocks() {
      return new ArrayList(this.irrsrc);
   }

   @Override
   public List getAllInputBlocks() {
      ArrayList var1 = new ArrayList(this.src);
      var1.addAll(this.irrsrc);
      return var1;
   }

   @Override
   public int outsize() {
      return this.dst.size();
   }

   @Override
   public int irroutsize() {
      return this.irrdst.size();
   }

   @Override
   public int alloutsize() {
      return this.dst.size() + this.irrdst.size();
   }

   public BasicBlock getOutputBlock(int var1) {
      return (BasicBlock)this.dst.get(var1);
   }

   @Override
   public List getOutputBlocks() {
      return new ArrayList(this.dst);
   }

   public List getOutputOffsets() {
      ArrayList var1 = new ArrayList(this.dst.size());
      this.dst.forEach(var1x -> var1.add(var1x.getFirstAddress()));
      return var1;
   }

   public BasicBlock getIrregularOutputBlock(int var1) {
      return (BasicBlock)this.irrdst.get(var1);
   }

   @Override
   public List getIrregularOutputBlocks() {
      return new ArrayList(this.irrdst);
   }

   public List getIrregularOutputOffsets() {
      ArrayList var1 = new ArrayList(this.irrdst.size());
      this.irrdst.forEach(var1x -> var1.add(var1x.getFirstAddress()));
      return var1;
   }

   @Override
   public List getAllOutputBlocks() {
      ArrayList var1 = new ArrayList(this.dst);
      var1.addAll(this.irrdst);
      return var1;
   }

   @Override
   public List getInputs() {
      return Collections.unmodifiableList(this.src);
   }

   public List getInputOffsets() {
      ArrayList var1 = new ArrayList(this.src.size());
      this.src.forEach(var1x -> var1.add(var1x.getFirstAddress()));
      return var1;
   }

   @Override
   public List getIrregularInputs() {
      return Collections.unmodifiableList(this.irrsrc);
   }

   public List getIrregularInputOffsets() {
      ArrayList var1 = new ArrayList(this.irrsrc.size());
      this.irrsrc.forEach(var1x -> var1.add(var1x.getFirstAddress()));
      return var1;
   }

   @Override
   public List getOutputs() {
      return Collections.unmodifiableList(this.dst);
   }

   @Override
   public List getIrregularOutputs() {
      return Collections.unmodifiableList(this.irrdst);
   }

   @Override
   public Iterable getAllInputs() {
      return CollectionUtil.doubleCollectionIterable(this.src, this.irrsrc);
   }

   @Override
   public Iterable getAllOutputs() {
      return CollectionUtil.doubleCollectionIterable(this.dst, this.irrdst);
   }

   public void setUnknownDst(boolean var1) {
      this.unknownDst = var1;
   }

   public boolean hasUnknownDst() {
      return this.unknownDst;
   }

   public boolean isSelfReferencing() {
      if (this.dst.size() > 0 && this.src.size() > 0) {
         for (BasicBlock var2 : this.dst) {
            if (this.src.contains(var2)) {
               return true;
            }
         }
      }

      return false;
   }

   public boolean isInfiniteLoop() {
      return this.dst.size() == 1 && this.isSelfReferencing();
   }

   @Override
   public String toString() {
      return Strings.ff("%Xh(%d)", this.base, this.insns.size());
   }

   BasicBlock splitBlock(long var1) {
      int var3 = this.getSplitIndex(var1);
      BasicBlock var4 = new BasicBlock(var1);

      for (int var5 = var3; var5 < this.insns.size(); var5++) {
         var4.insns.add((IInstruction)this.insns.get(var5));
      }

      var4.dst_offsets = new ArrayList(this.dst_offsets);
      var4.unknownDst = this.unknownDst;
      int var7 = this.insns.size() - var3;

      for (int var6 = 0; var6 < var7; var6++) {
         this.insns.remove(var3);
      }

      this.dst_offsets.clear();
      this.dst_offsets.add(var1);
      this.unknownDst = false;
      return var4;
   }

   private int getSplitIndex(long var1) {
      long var3 = this.getFirstAddress();
      int var5 = 0;

      for (IInstruction var7 : this.insns) {
         if (var3 == var1) {
            return var5;
         }

         var3 += var7.getSize();
         var5++;
      }

      return -1;
   }

   public void setData(String var1, Object var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         if (this.datamap == null) {
            this.datamap = new HashMap();
         }

         this.datamap.put(var1, var2);
      }
   }

   public Object getData(String var1) {
      return this.datamap == null ? null : this.datamap.get(var1);
   }

   public boolean removeData(String var1) {
      return this.datamap == null ? false : this.datamap.remove(var1) != null;
   }

   @Override
   public Iterator iterator() {
      return this.insns.iterator();
   }

   public Iterable addressableInstructions() {
      return new BasicBlock$1(this);
   }

   public int compareTo(BasicBlock var1) {
      return Long.compareUnsigned(this.base, var1.base);
   }

   private class AddressableInstructionsIterator implements Iterator {
      private long address = BasicBlock.this.base;
      private Iterator iter = BasicBlock.this.insns.iterator();

      @Override
      public boolean hasNext() {
         return this.iter.hasNext();
      }

      public AddressableInstruction next() {
         IInstruction var1 = (IInstruction)this.iter.next();
         AddressableInstruction var2 = new AddressableInstruction(this.address, var1);
         this.address = this.address + var1.getSize();
         return var2;
      }

      @Override
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }
}
