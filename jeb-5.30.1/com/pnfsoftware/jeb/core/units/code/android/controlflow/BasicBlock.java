package com.pnfsoftware.jeb.core.units.code.android.controlflow;

import com.pnfsoftware.jeb.core.units.code.IBasicBlock;
import com.pnfsoftware.jeb.core.units.code.ILocatedInstruction;
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
import java.util.Iterator;
import java.util.List;

@Ser
public class BasicBlock implements IBasicBlock {
   private static final ILogger logger = GlobalLog.getLogger(BasicBlock.class);
   @SerId(1)
   List insns = new ArrayList();
   @SerTransient
   List dst_offsets = new ArrayList();
   @SerTransient
   List irrdst_offsets = new ArrayList();
   @SerId(4)
   List src = new ArrayList();
   @SerId(5)
   List dst = new ArrayList();
   @SerId(6)
   List irrsrc = new ArrayList();
   @SerId(7)
   List irrdst = new ArrayList();

   public BasicBlock() {
   }

   public BasicBlock(Collection var1) {
      this.insns.addAll(var1);
   }

   @Override
   public int size() {
      return this.insns.size();
   }

   @Override
   public boolean isEmpty() {
      return this.insns.isEmpty();
   }

   public ILocatedInstruction get(int var1) {
      if (var1 < 0) {
         var1 += this.insns.size();
      }

      return (ILocatedInstruction)this.insns.get(var1);
   }

   public ILocatedInstruction getLast() {
      return (ILocatedInstruction)this.insns.get(this.insns.size() - 1);
   }

   @Override
   public List getInstructions() {
      return new ArrayList(this.insns);
   }

   @Override
   public int getSizeOfInstructions() {
      int var1 = 0;

      for (ILocatedInstruction var3 : this.insns) {
         var1 += var3.getSize();
      }

      return var1;
   }

   @Override
   public long getBase() {
      return this.getFirstAddress();
   }

   public long getAddress() {
      return this.getFirstAddress();
   }

   @Override
   public long getFirstAddress() {
      return ((ILocatedInstruction)this.insns.get(0)).getOffset();
   }

   @Override
   public long getLastAddress() {
      return ((ILocatedInstruction)this.insns.get(this.insns.size() - 1)).getOffset();
   }

   @Override
   public long getEndAddress() {
      ILocatedInstruction var1 = (ILocatedInstruction)this.insns.get(this.insns.size() - 1);
      return var1.getOffset() + var1.getSize();
   }

   @Override
   public long getAddressOfInstruction(int var1) {
      if (var1 >= 0 && var1 < this.insns.size()) {
         return ((ILocatedInstruction)this.insns.get(var1)).getOffset();
      } else {
         throw new ArrayIndexOutOfBoundsException();
      }
   }

   @Override
   public int getIndexOfInstruction(long var1) {
      int var3 = 0;

      for (ILocatedInstruction var5 : this.insns) {
         long var6 = var5.getOffset();
         if (var6 >= var1) {
            return var1 == var6 ? var3 : -1;
         }

         var3++;
      }

      return -1;
   }

   public int getIndexOfInstruction(long var1, boolean var3) {
      if (var3) {
         return this.getIndexOfInstruction(var1);
      } else {
         int var4 = 0;

         for (ILocatedInstruction var6 : this.insns) {
            long var7 = var6.getOffset();
            if (var7 > var1) {
               return -1;
            }

            if (var7 <= var1 && var1 < var6.getOffsetEnd()) {
               return var4;
            }

            var4++;
         }

         return -1;
      }
   }

   public ILocatedInstruction getInstruction(long var1) {
      for (ILocatedInstruction var4 : this.insns) {
         long var5 = var4.getOffset();
         if (var5 >= var1) {
            return var1 == var5 ? var4 : null;
         }
      }

      return null;
   }

   @Override
   public boolean canThrow() {
      for (ILocatedInstruction var2 : this.insns) {
         if (var2.canThrow()) {
            return true;
         }
      }

      return false;
   }

   public ILocatedInstruction remove(int var1) {
      return (ILocatedInstruction)this.insns.remove(var1);
   }

   public void removeAll() {
      this.insns.clear();
   }

   public void add(ILocatedInstruction var1) {
      this.insns.add(var1);
   }

   public void addAll(Collection var1) {
      this.insns.addAll(var1);
   }

   public void add(int var1, ILocatedInstruction var2) {
      this.insns.add(var1, var2);
   }

   public ILocatedInstruction set(int var1, ILocatedInstruction var2) {
      return this.set(var1, var2, true, false);
   }

   public ILocatedInstruction set(int var1, ILocatedInstruction var2, boolean var3, boolean var4) {
      if (var3 || var4) {
         ILocatedInstruction var5 = (ILocatedInstruction)this.insns.get(var1);
         if (var3 && var5.getSize() != var2.getSize()) {
            throw new IllegalArgumentException("Replacement of instruction with different sizes");
         }

         if (var4 && var5.getOffset() != var2.getOffset()) {
            throw new IllegalArgumentException("Replacement of instruction with different sizes");
         }
      }

      return (ILocatedInstruction)this.insns.set(var1, var2);
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

   public List getInputOffsets() {
      ArrayList var1 = new ArrayList(this.src.size());
      this.src.forEach(var1x -> var1.add(var1x.getFirstAddress()));
      return var1;
   }

   public BasicBlock getIrregularInputBlock(int var1) {
      return (BasicBlock)this.irrsrc.get(var1);
   }

   @Override
   public List getIrregularInputBlocks() {
      return new ArrayList(this.irrsrc);
   }

   public List getIrregularInputOffsets() {
      ArrayList var1 = new ArrayList(this.irrsrc.size());
      this.irrsrc.forEach(var1x -> var1.add(var1x.getFirstAddress()));
      return var1;
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

   @Override
   public List getIrregularInputs() {
      return Collections.unmodifiableList(this.irrsrc);
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

   @Override
   public Iterator iterator() {
      return this.insns.iterator();
   }

   @Override
   public String toString() {
      if (this.insns.size() == 0) {
         return "(empty)";
      } else {
         long var1 = ((ILocatedInstruction)this.insns.get(0)).getOffset();
         long var3 = ((ILocatedInstruction)this.insns.get(this.insns.size() - 1)).getOffset();
         return Strings.ff("(%X-%X,%d)", var1, var3, this.insns.size());
      }
   }
}
