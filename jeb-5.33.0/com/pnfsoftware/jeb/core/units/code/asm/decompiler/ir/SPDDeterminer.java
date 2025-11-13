package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.TreeSet;

@Ser
public class SPDDeterminer {
   @SerId(1)
   private TreeSet entries = new TreeSet();

   public SPDDeterminer clone() {
      SPDDeterminer var1 = new SPDDeterminer();

      for (SPDC var3 : this.entries) {
         var1.entries.add(var3.clone());
      }

      return var1;
   }

   public boolean add(int var1, int var2, int var3) {
      SPDC var4 = new SPDC(var1, var2, var3);
      return this.entries.add(var4);
   }

   public Collection getCandidates() {
      return Collections.unmodifiableCollection(this.entries);
   }

   public SPDC getBestCandidate() {
      if (this.entries.isEmpty()) {
         throw new NoSuchElementException();
      } else {
         return (SPDC)this.entries.last();
      }
   }

   public int getBestValue() {
      if (this.entries.isEmpty()) {
         throw new NoSuchElementException();
      } else {
         return ((SPDC)this.entries.last()).getValue();
      }
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.entries == null ? 0 : this.entries.hashCode());
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
         SPDDeterminer var2 = (SPDDeterminer)var1;
         if (this.entries == null) {
            if (var2.entries != null) {
               return false;
            }
         } else if (!this.entries.equals(var2.entries)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return this.entries.toString();
   }
}
