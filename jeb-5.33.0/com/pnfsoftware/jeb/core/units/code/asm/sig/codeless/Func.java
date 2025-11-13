package com.pnfsoftware.jeb.core.units.code.asm.sig.codeless;

import com.pnfsoftware.jeb.util.base.Triple;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class Func {
   @SerId(1)
   private final String name;
   @SerId(2)
   private final ModuleId moduleId;
   @SerId(3)
   private Triple addresses;
   @SerId(4)
   private Func trampolineTarget;

   private Func(String var1, ModuleId var2) {
      this.name = var1;
      this.moduleId = var2;
   }

   public static Func createFrom(String var0, ModuleId var1) {
      return new Func(var0, var1);
   }

   public static Func createUnknownFrom(String var0) {
      return new Func(var0, ModuleId.UNKNOWN_MODULE_ID);
   }

   public String getName() {
      return this.name;
   }

   public ModuleId getModuleId() {
      return this.moduleId;
   }

   public void setAddresses(long var1, long var3, long var5) {
      this.addresses = new Triple(var1, var3, var5);
   }

   public boolean isMapped() {
      return this.addresses != null;
   }

   public Func getTrampolineTarget() {
      return this.trampolineTarget;
   }

   public void setTrampolineTarget(Func var1) {
      this.trampolineTarget = var1;
   }

   public Long getEntryPoint() {
      return this.addresses == null ? null : (Long)this.addresses.getFirst();
   }

   public Long getLowestAddress() {
      return this.addresses == null ? null : (Long)this.addresses.getSecond();
   }

   public Long getHighestAddress() {
      return this.addresses == null ? null : (Long)this.addresses.getThird();
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.addresses == null ? 0 : this.addresses.hashCode());
      var1 = 31 * var1 + (this.moduleId == null ? 0 : this.moduleId.hashCode());
      var1 = 31 * var1 + (this.name == null ? 0 : this.name.hashCode());
      return 31 * var1 + (this.trampolineTarget == null ? 0 : this.trampolineTarget.hashCode());
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
         Func var2 = (Func)var1;
         if (this.addresses == null) {
            if (var2.addresses != null) {
               return false;
            }
         } else if (!this.addresses.equals(var2.addresses)) {
            return false;
         }

         if (this.moduleId == null) {
            if (var2.moduleId != null) {
               return false;
            }
         } else if (!this.moduleId.equals(var2.moduleId)) {
            return false;
         }

         if (this.name == null) {
            if (var2.name != null) {
               return false;
            }
         } else if (!this.name.equals(var2.name)) {
            return false;
         }

         if (this.trampolineTarget == null) {
            if (var2.trampolineTarget != null) {
               return false;
            }
         } else if (!this.trampolineTarget.equals(var2.trampolineTarget)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return "(" + this.name + ":" + this.moduleId.getFileName() + ")";
   }
}
