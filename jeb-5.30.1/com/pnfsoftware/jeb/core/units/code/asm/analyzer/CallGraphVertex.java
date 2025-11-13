package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import javax.annotation.concurrent.Immutable;

@Ser
@Immutable
public class CallGraphVertex extends BranchTarget {
   @SerId(1)
   private long dereferencedAddress = -1L;

   private CallGraphVertex(CodePointer var1) {
      super(var1);
   }

   public static CallGraphVertex buildFromInternalTarget(CodePointer var0) {
      return new CallGraphVertex(var0);
   }

   private CallGraphVertex(INativeMethodItem var1) {
      super(var1);
   }

   public static CallGraphVertex buildFromExternalTarget(INativeMethodItem var0) {
      Assert.a(var0.getData() == null, "Only external routines can be given to this constructor");
      return new CallGraphVertex(var0);
   }

   private CallGraphVertex(long var1) {
      this.dereferencedAddress = var1;
   }

   public static CallGraphVertex buildFromUnresolvedTarget(long var0) {
      return new CallGraphVertex(var0);
   }

   public boolean isResolved() {
      return this.dereferencedAddress == -1L;
   }

   public long getDereferencedAddress() {
      return this.dereferencedAddress;
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      return 31 * var1 + (int)(this.dereferencedAddress ^ this.dereferencedAddress >>> 32);
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!super.equals(var1)) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         CallGraphVertex var2 = (CallGraphVertex)var1;
         return this.dereferencedAddress == var2.dereferencedAddress;
      }
   }
}
