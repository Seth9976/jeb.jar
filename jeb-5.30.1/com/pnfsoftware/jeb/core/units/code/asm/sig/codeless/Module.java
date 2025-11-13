package com.pnfsoftware.jeb.core.units.code.asm.sig.codeless;

import com.pnfsoftware.jeb.util.collect.ISegment;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class Module implements ISegment {
   private static final Module UNKNOWN_MODULE = new Module(ModuleId.UNKNOWN_MODULE_ID);
   @SerId(1)
   private final ModuleId id;
   @SerId(2)
   private Long begin;
   @SerId(3)
   private Long end;

   private Module(ModuleId var1) {
      this.id = var1;
   }

   private Module(ModuleId var1, long var2, long var4) {
      this(var1);
      this.begin = var2;
      this.end = var4;
   }

   public Long getBegin() {
      return this.begin;
   }

   public Long getEnd() {
      return this.end;
   }

   public static Module createKnownModule(ModuleId var0) {
      return new Module(var0);
   }

   public static Module createKnownModule(ModuleId var0, long var1, long var3) {
      return new Module(var0, var1, var3);
   }

   public static Module createUnknownModule() {
      return UNKNOWN_MODULE;
   }

   public boolean isUnknown() {
      return this.id.isUnknown();
   }

   public String getName() {
      return this.id.getFileName();
   }

   public ModuleId getId() {
      return this.id;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.begin == null ? 0 : this.begin.hashCode());
      var1 = 31 * var1 + (this.end == null ? 0 : this.end.hashCode());
      return 31 * var1 + (this.id == null ? 0 : this.id.hashCode());
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
         Module var2 = (Module)var1;
         if (this.begin == null) {
            if (var2.begin != null) {
               return false;
            }
         } else if (!this.begin.equals(var2.begin)) {
            return false;
         }

         if (this.end == null) {
            if (var2.end != null) {
               return false;
            }
         } else if (!this.end.equals(var2.end)) {
            return false;
         }

         if (this.id == null) {
            if (var2.id != null) {
               return false;
            }
         } else if (!this.id.equals(var2.id)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("Module [id=%s - begin=%08x - end=%08x]", this.id, this.begin != null ? this.begin : 0L, this.end != null ? this.end : 0L);
   }
}
