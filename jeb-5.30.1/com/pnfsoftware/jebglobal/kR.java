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
public class kR implements IBasicBlockSkeleton {
   @SerId(1)
   long q;
   @SerId(2)
   List RF = new ArrayList();
   @SerId(3)
   List xK = new ArrayList();
   @SerId(4)
   List Dw = new ArrayList();
   @SerId(5)
   List Uv = new CopyOnWriteArrayList();
   @SerId(6)
   BasicBlock oW;
   @SerId(7)
   long gO;
   @SerId(8)
   boolean nf;

   public kR() {
   }

   public kR(long var1) {
      this.q = var1;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (int)(this.q ^ this.q >>> 32);
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
         kR var2 = (kR)var1;
         return this.q == var2.q;
      }
   }

   public void q(BasicBlock var1) {
      this.oW = var1;
   }

   public BasicBlock q() {
      return this.oW;
   }

   public int q(long var1) {
      long var3 = this.q;

      for (int var5 = 0; var5 < this.RF.size(); var5++) {
         if (var3 == var1) {
            return var5;
         }

         var3 += ((IInstruction)this.RF.get(var5)).getSize();
      }

      return -1;
   }

   public long q(int var1) {
      if (var1 >= 0 && var1 < this.RF.size()) {
         long var2 = this.q;

         for (int var4 = 0; var4 < var1; var4++) {
            var2 += ((IInstruction)this.RF.get(var4)).getSize();
         }

         return var2;
      } else {
         throw new ArrayIndexOutOfBoundsException();
      }
   }

   public BasicBlock RF() {
      return this.RF.isEmpty() ? null : new BasicBlock(this.q, this.RF, this.xK, this.Dw, this.nf);
   }

   @Override
   public long getFirstAddress() {
      return this.q;
   }

   @Override
   public long getLastAddress() {
      long var1 = this.q;

      for (int var3 = 0; var3 < this.RF.size() - 1; var3++) {
         var1 += ((IInstruction)this.RF.get(var3)).getSize();
      }

      return var1;
   }

   @Override
   public long getEndAddress() {
      long var1 = this.q;

      for (IInstruction var4 : this.RF) {
         var1 += var4.getSize();
      }

      return var1;
   }

   @Override
   public List getInsntructions() {
      return this.RF;
   }

   void RF(long var1) {
      this.xK.add(var1);
   }

   boolean q(Long var1) {
      return this.xK.remove(var1);
   }

   Long RF(int var1) {
      return (Long)this.xK.remove(var1);
   }

   boolean xK(long var1) {
      return this.xK.contains(var1);
   }

   @Override
   public List getDstOffsets() {
      return this.xK;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("base=").append(Long.toHexString(this.q).toUpperCase()).append("h");
      var1.append(",cnt=").append(this.RF.size());
      return var1.toString();
   }

   kR q(long var1, boolean var3) {
      int var4 = this.q(var1);
      if (var4 < 0) {
         return null;
      } else {
         kR var5 = new kR(var1);

         for (int var6 = var4; var6 < this.RF.size(); var6++) {
            var5.RF.add((IInstruction)this.RF.get(var6));
         }

         var5.xK = new ArrayList(this.xK);
         var5.Uv.addAll(this.Uv);
         var5.nf = this.nf;
         if (var3) {
            return var5;
         } else {
            for (Xg var7 : this.Uv) {
               var7.RF.add(var5);
            }

            int var9 = this.RF.size() - var4;

            for (int var10 = 0; var10 < var9; var10++) {
               this.RF.remove(var4);
            }

            this.xK.clear();
            this.xK.add(var1);
            this.nf = false;
            return var5;
         }
      }
   }

   @Override
   public Iterator iterator() {
      return this.RF.iterator();
   }
}
