package com.pnfsoftware.jeb.core.units.code.asm.sig;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class NativeSignatureFlags {
   @SerId(1)
   private SignatureTargetType type;
   @SerId(2)
   private boolean meaningfulName;

   public NativeSignatureFlags(SignatureTargetType var1) {
      this.type = var1;
      this.meaningfulName = true;
   }

   public NativeSignatureFlags(SignatureTargetType var1, boolean var2) {
      this.type = var1;
      this.meaningfulName = var2;
   }

   public SignatureTargetType getTargetType() {
      return this.type;
   }

   public boolean hasMeaningfulTargetName() {
      return this.meaningfulName;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.type == null ? 0 : this.type.hashCode());
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
         NativeSignatureFlags var2 = (NativeSignatureFlags)var1;
         return this.type == var2.type;
      }
   }
}
