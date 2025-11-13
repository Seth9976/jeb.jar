package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.IdRanges;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Ser
public class MemoryAccessInfo {
   public static final MemoryAccessInfo ACCESSES_NONE = new MemoryAccessInfo(false, false, false, false);
   public static final MemoryAccessInfo ACCESSES_STACK = new MemoryAccessInfo(true, true, false, false);
   public static final MemoryAccessInfo ACCESSES_GLOBALS = new MemoryAccessInfo(false, false, true, true);
   public static final MemoryAccessInfo ACCESSES_ALL = new MemoryAccessInfo(true, true, true, true);
   @SerId(1)
   private boolean mutable;
   @SerId(2)
   private IdRanges globalsReads;
   @SerId(3)
   private IdRanges globalsWrites;
   @SerId(4)
   private IdRanges stackReads;
   @SerId(5)
   private IdRanges stackWrites;
   @SerId(6)
   private IdRanges stackSpoiled;

   public MemoryAccessInfo() {
      this(true);
   }

   public MemoryAccessInfo(boolean var1) {
      this.mutable = true;
      if (!var1) {
         this.setGlobalsAccessRanges(new IdRanges(), new IdRanges());
         this.setStackAccessRanges(new IdRanges(), new IdRanges());
      }
   }

   public MemoryAccessInfo(boolean var1, boolean var2, boolean var3, boolean var4) {
      if (!var3) {
         this.globalsReads = new IdRanges();
      }

      if (!var4) {
         this.globalsWrites = new IdRanges();
      }

      if (!var1) {
         this.stackReads = new IdRanges();
      }

      if (!var2) {
         this.stackWrites = new IdRanges();
      }
   }

   public boolean isMutable() {
      return this.mutable;
   }

   private void verifyCustom() {
      if (!this.isMutable()) {
         throw new IllegalStateException("This MAI cannot be customized!");
      }
   }

   public boolean isNoAccess() {
      return !this.isAccessMemory();
   }

   public boolean isAccessMemory() {
      return this.stackReads == null
         || !this.stackReads.isEmpty()
         || this.stackWrites == null
         || !this.stackWrites.isEmpty()
         || this.globalsReads == null
         || !this.globalsReads.isEmpty()
         || this.globalsWrites == null
         || !this.globalsWrites.isEmpty();
   }

   public boolean isReadMemory() {
      return this.stackReads == null || !this.stackReads.isEmpty() || this.globalsReads == null || !this.globalsReads.isEmpty();
   }

   public boolean isWriteMemory() {
      return this.stackWrites == null || !this.stackWrites.isEmpty() || this.globalsWrites == null || !this.globalsWrites.isEmpty();
   }

   public boolean isAccessStack() {
      return this.stackReads == null || !this.stackReads.isEmpty() || this.stackWrites == null || !this.stackWrites.isEmpty();
   }

   public boolean isReadStack() {
      return this.stackReads == null || !this.stackReads.isEmpty();
   }

   public boolean isWriteStack() {
      return this.stackWrites == null || !this.stackWrites.isEmpty();
   }

   public boolean isAccessGlobals() {
      return this.globalsReads == null || !this.globalsReads.isEmpty() || this.globalsWrites == null || !this.globalsWrites.isEmpty();
   }

   public boolean isReadGlobals() {
      return this.globalsReads == null || !this.globalsReads.isEmpty();
   }

   public boolean isWriteGlobals() {
      return this.globalsWrites == null || !this.globalsWrites.isEmpty();
   }

   public boolean hasSpoiledStack() {
      return this.stackSpoiled != null && !this.stackSpoiled.isEmpty();
   }

   public void setAccessRanges(IdRanges var1, IdRanges var2, IdRanges var3, IdRanges var4) {
      this.verifyCustom();
      this.stackReads = var1;
      this.stackWrites = var2;
      this.globalsReads = var3;
      this.globalsWrites = var4;
   }

   public void setStackAccessRanges(IdRanges var1, IdRanges var2) {
      this.verifyCustom();
      this.stackReads = var1;
      this.stackWrites = var2;
   }

   public IdRanges getStackReads() {
      this.verifyCustom();
      return this.stackReads;
   }

   public IdRanges getStackWrites() {
      this.verifyCustom();
      return this.stackWrites;
   }

   public void setGlobalsAccessRanges(IdRanges var1, IdRanges var2) {
      this.verifyCustom();
      this.globalsReads = var1;
      this.globalsWrites = var2;
   }

   public IdRanges getGlobalsReads() {
      this.verifyCustom();
      return this.globalsReads;
   }

   public IdRanges getGlobalsWrites() {
      this.verifyCustom();
      return this.globalsWrites;
   }

   public void setStackSpoiledRanges(IdRanges var1) {
      this.verifyCustom();
      this.stackSpoiled = var1;
   }

   public IdRanges getStackSpoiledRanges() {
      this.verifyCustom();
      return this.stackSpoiled;
   }

   public MemoryAccessInfo clone() {
      if (!this.mutable) {
         return this;
      } else {
         MemoryAccessInfo var1 = new MemoryAccessInfo();
         var1.mutable = this.mutable;
         var1.globalsReads = this.globalsReads == null ? null : new IdRanges(this.globalsReads);
         var1.globalsWrites = this.globalsWrites == null ? null : new IdRanges(this.globalsWrites);
         var1.stackReads = this.stackReads == null ? null : new IdRanges(this.stackReads);
         var1.stackWrites = this.stackWrites == null ? null : new IdRanges(this.stackWrites);
         var1.stackSpoiled = this.stackSpoiled == null ? null : new IdRanges(this.stackSpoiled);
         return var1;
      }
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.globalsReads == null ? 0 : this.globalsReads.hashCode());
      var1 = 31 * var1 + (this.globalsWrites == null ? 0 : this.globalsWrites.hashCode());
      var1 = 31 * var1 + (this.stackReads == null ? 0 : this.stackReads.hashCode());
      var1 = 31 * var1 + (this.stackWrites == null ? 0 : this.stackWrites.hashCode());
      return 31 * var1 + (this.stackSpoiled == null ? 0 : this.stackSpoiled.hashCode());
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
         MemoryAccessInfo var2 = (MemoryAccessInfo)var1;
         if (this.globalsReads == null) {
            if (var2.globalsReads != null) {
               return false;
            }
         } else if (!this.globalsReads.equals(var2.globalsReads)) {
            return false;
         }

         if (this.globalsWrites == null) {
            if (var2.globalsWrites != null) {
               return false;
            }
         } else if (!this.globalsWrites.equals(var2.globalsWrites)) {
            return false;
         }

         if (this.stackReads == null) {
            if (var2.stackReads != null) {
               return false;
            }
         } else if (!this.stackReads.equals(var2.stackReads)) {
            return false;
         }

         if (this.stackWrites == null) {
            if (var2.stackWrites != null) {
               return false;
            }
         } else if (!this.stackWrites.equals(var2.stackWrites)) {
            return false;
         }

         if (this.stackSpoiled == null) {
            if (var2.stackSpoiled != null) {
               return false;
            }
         } else if (!this.stackSpoiled.equals(var2.stackSpoiled)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      if (this.isNoAccess()) {
         var1.append("NO_ACCESS");
      } else {
         if (this.isReadGlobals()) {
            var1.append("GLOBALS_R,");
         }

         if (this.isWriteGlobals()) {
            var1.append("GLOBALS_W,");
         }

         if (this.isReadStack()) {
            var1.append("STK_R,");
         }

         if (this.isWriteStack()) {
            var1.append("STK_W,");
         }

         if (var1.length() != 0) {
            var1.deleteCharAt(var1.length() - 1);
         }
      }

      return var1.toString();
   }

   public Collection filterGlobalsReads(Collection var1) {
      return this.filter(this.globalsReads, var1);
   }

   public Collection filterGlobalsWrites(Collection var1) {
      return this.filter(this.globalsWrites, var1);
   }

   public Collection filterStackReads(Collection var1) {
      return this.filter(this.stackReads, var1);
   }

   public Collection filterStackWrites(Collection var1) {
      return this.filter(this.stackWrites, var1);
   }

   public Collection filterStackSpoiled(Collection var1) {
      return this.filter(this.stackSpoiled, var1);
   }

   private Collection filter(IdRanges var1, Collection var2) {
      if (var1 == null) {
         return var2;
      } else if (var1.isEmpty()) {
         return Collections.emptyList();
      } else {
         ArrayList var3 = new ArrayList();

         for (IEVar var5 : var2) {
            if (var1.containsVarPart(var5)) {
               var3.add(var5);
            }
         }

         return var3;
      }
   }
}
