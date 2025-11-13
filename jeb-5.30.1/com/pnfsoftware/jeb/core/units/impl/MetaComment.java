package com.pnfsoftware.jeb.core.units.impl;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class MetaComment {
   @SerId(1)
   private String value;
   @SerId(2)
   private int flags;

   public MetaComment(String var1, int var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.value = var1;
         this.flags = var2;
      }
   }

   public MetaComment(MetaComment var1) {
      this.value = var1.value;
      this.flags = var1.flags;
   }

   public MetaComment(String var1) {
      this(var1, 0);
   }

   public String getValue() {
      return this.value;
   }

   public int getFlags() {
      return this.flags;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.value == null ? 0 : this.value.hashCode());
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
         MetaComment var2 = (MetaComment)var1;
         if (this.value == null) {
            if (var2.value != null) {
               return false;
            }
         } else if (!this.value.equals(var2.value)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return this.value + (this.flags == 0 ? "" : " (" + formatFlags(this.flags) + ")");
   }

   public static String formatFlags(int var0) {
      return var0 == 4096 ? "FAVORITE" : Strings.ff("0x%X", var0);
   }
}
