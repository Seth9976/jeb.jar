package com.pnfsoftware.jeb.core.units.code;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Collections;
import java.util.List;

public class DefUseInfo {
   public static final int FLAG_INCLUDE_POTENTIALS = 1;
   public static final int FLAG_INCLUDE_SPOILED = 2;
   public static final int CONSERVATIVE = 3;
   public Long insnAddress;
   public IdRanges def = new IdRanges();
   public IdRanges use = new IdRanges();
   public IdRanges defpot;
   public IdRanges usepot;
   public IdRanges spoiled;

   public DefUseInfo() {
      this(0);
   }

   public DefUseInfo(int var1) {
      if ((var1 & 1) != 0) {
         this.defpot = new IdRanges();
         this.usepot = new IdRanges();
      }

      if ((var1 & 2) != 0) {
         this.spoiled = new IdRanges();
      }
   }

   public Long getInstructionAddress() {
      return this.insnAddress;
   }

   public void setInstructionAddress(long var1) {
      if (this.insnAddress != null) {
         if (this.insnAddress != var1) {
            throw new IllegalStateException();
         }
      } else {
         this.insnAddress = var1;
      }
   }

   public void clear() {
      this.insnAddress = null;
      this.def.clear();
      this.use.clear();
      if (this.defpot != null) {
         this.defpot.clear();
      }

      if (this.usepot != null) {
         this.usepot.clear();
      }

      if (this.spoiled != null) {
         this.spoiled.clear();
      }
   }

   public boolean shouldCollectPotentials() {
      return this.defpot != null && this.usepot != null;
   }

   public boolean shouldCollectSpoiled() {
      return this.spoiled != null;
   }

   public IdRanges getDef() {
      return this.def;
   }

   public IdRanges getUse() {
      return this.use;
   }

   public List getDefinedVarIds() {
      return this.def.getVarIds();
   }

   public List getUsedVarIds() {
      return this.use.getVarIds();
   }

   public List getPotentiallyDefinedVarIds() {
      return this.defpot == null ? Collections.emptyList() : this.defpot.getVarIds();
   }

   public List getPotentiallyUsedVarIds() {
      return this.usepot == null ? Collections.emptyList() : this.usepot.getVarIds();
   }

   public List getSpoiledVarIds() {
      return this.spoiled == null ? Collections.emptyList() : this.spoiled.getVarIds();
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "@%s:", this.insnAddress == null ? "?" : Long.toHexString(this.insnAddress));
      Strings.ff(var1, " def=%s use=%s", this.def, this.use);
      if (this.defpot != null) {
         Strings.ff(var1, " def-pot=%s", this.defpot);
      }

      if (this.usepot != null) {
         Strings.ff(var1, " use-pot=%s", this.usepot);
      }

      if (this.spoiled != null) {
         Strings.ff(var1, " spoiled=%s", this.spoiled);
      }

      return var1.toString();
   }
}
