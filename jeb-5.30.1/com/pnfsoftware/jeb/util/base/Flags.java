package com.pnfsoftware.jeb.util.base;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.HashMap;
import java.util.Map;

@Ser
public class Flags {
   @SerId(1)
   private int flags;
   @SerId(2)
   private int allowed;
   @SerId(3)
   private Map namemap;

   public Flags() {
      this(0);
   }

   public Flags(int var1) {
      this(var1, -1, null);
   }

   public Flags(int var1, int var2, Map var3) {
      this.flags = var1;
      this.allowed = var2;
      if (var3 != null) {
         for (int var5 : var3.keySet()) {
            if (this.isValidBit(var5)) {
               String var6 = (String)var3.get(var5);
               if (!Strings.isBlank(var6)) {
                  if (this.namemap == null) {
                     this.namemap = new HashMap();
                  }

                  this.namemap.put(var5, var6);
               }
            }
         }
      }
   }

   public void verify(int var1) {
      if ((var1 | this.allowed) != this.allowed) {
         throw new IllegalStateException(Strings.ff("Illegal flags: 0x%X", var1 & ~this.allowed));
      }
   }

   public int get() {
      return this.flags;
   }

   public boolean set(int var1) {
      this.verify(var1);
      if (var1 == this.flags) {
         return false;
      } else {
         this.flags = var1;
         return true;
      }
   }

   public boolean isValidBit(int var1) {
      return (this.allowed & 1 << var1) != 0;
   }

   public boolean testBit(int var1) {
      if (var1 >= 0 && var1 <= 31) {
         return (this.flags & 1 << var1) != 0;
      } else {
         throw new IllegalArgumentException("Bit index out of range: " + var1);
      }
   }

   public boolean has(int var1) {
      return (this.flags & var1) == var1;
   }

   public boolean hasSome(int var1) {
      return (this.flags & var1) != 0;
   }

   public boolean hasNone(int var1) {
      return (this.flags & var1) == 0;
   }

   public boolean addTo(int var1) {
      this.verify(var1);
      if (this.has(var1)) {
         return false;
      } else {
         this.flags |= var1;
         return true;
      }
   }

   public boolean removeFrom(int var1) {
      if (this.hasNone(var1)) {
         return false;
      } else {
         this.flags &= ~var1;
         return true;
      }
   }

   public boolean update(int var1, boolean var2) {
      return var2 ? this.addTo(var1) : this.removeFrom(var1);
   }

   @Override
   public String toString() {
      return Strings.ff("0x%08X", this.flags);
   }

   public static int set(int var0, int var1, boolean var2) {
      return var2 ? var0 | var1 : var0 & ~var1;
   }

   public static boolean has(int var0, int var1) {
      return (var0 & var1) == var1;
   }
}
