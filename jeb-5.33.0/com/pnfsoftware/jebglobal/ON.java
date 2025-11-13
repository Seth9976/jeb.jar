package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IBasicBlockSkeleton;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Ser
public class ON implements IBasicBlockSkeleton {
   @SerId(1)
   long pC;
   @SerId(2)
   List A = new ArrayList();
   @SerId(3)
   List kS = new ArrayList();
   @SerId(4)
   List wS = new ArrayList();
   @SerId(5)
   List UT = new CopyOnWriteArrayList();
   @SerId(6)
   BasicBlock E;
   @SerId(7)
   long sY;
   @SerId(8)
   boolean ys;

   public ON() {
   }

   public ON(long var1) {
      this.pC = var1;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (int)(this.pC ^ this.pC >>> 32);
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         ON var2 = (ON)var1;
         return this.pC == var2.pC;
      }
   }

   public void pC(BasicBlock var1) {
      this.E = var1;
   }

   public int pC(long var1) {
      long var3 = this.pC;

      for (int var5 = 0; var5 < this.A.size(); var5++) {
         if (var3 == var1) {
            return var5;
         }

         var3 += ((IInstruction)this.A.get(var5)).getSize();
      }

      return -1;
   }

   public long pC(int var1) {
      if (var1 >= 0 && var1 < this.A.size()) {
         long var2 = this.pC;

         for (int var4 = 0; var4 < var1; var4++) {
            var2 += ((IInstruction)this.A.get(var4)).getSize();
         }

         return var2;
      } else {
         throw new ArrayIndexOutOfBoundsException();
      }
   }

   public BasicBlock pC() {
      return this.A.isEmpty() ? null : new BasicBlock(this.pC, this.A, this.kS, this.wS, this.ys);
   }

   @Override
   public long getFirstAddress() {
      return this.pC;
   }

   @Override
   public long getLastAddress() {
      long var1 = this.pC;

      for (int var3 = 0; var3 < this.A.size() - 1; var3++) {
         var1 += ((IInstruction)this.A.get(var3)).getSize();
      }

      return var1;
   }

   @Override
   public long getEndAddress() {
      long var1 = this.pC;

      for (IInstruction var4 : this.A) {
         var1 += var4.getSize();
      }

      return var1;
   }

   @Override
   public List getInsntructions() {
      return this.A;
   }

   void A(long var1) {
      this.kS.add(var1);
   }

   boolean pC(Long var1) {
      return this.kS.remove(var1);
   }

   Long A(int var1) {
      return (Long)this.kS.remove(var1);
   }

   boolean kS(long var1) {
      return this.kS.contains(var1);
   }

   @Override
   public List getDstOffsets() {
      return this.kS;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("base=").append(Long.toHexString(this.pC).toUpperCase()).append("h");
      var1.append(",cnt=").append(this.A.size());
      return var1.toString();
   }

   ON pC(long var1, boolean var3) {
      int var4 = this.pC(var1);
      if (var4 < 0) {
         return null;
      } else {
         ON var5 = new ON(var1);

         for (int var6 = var4; var6 < this.A.size(); var6++) {
            var5.A.add((IInstruction)this.A.get(var6));
         }

         var5.kS = new ArrayList(this.kS);
         var5.UT.addAll(this.UT);
         var5.ys = this.ys;
         if (var3) {
            return var5;
         } else {
            for (Ss var7 : this.UT) {
               var7.A.add(var5);
            }

            int var9 = this.A.size() - var4;

            for (int var10 = 0; var10 < var9; var10++) {
               this.A.remove(var4);
            }

            this.kS.clear();
            this.kS.add(var1);
            this.ys = false;
            return var5;
         }
      }
   }

   @Override
   public Iterator iterator() {
      return this.A.iterator();
   }
}
