package com.pnfsoftware.jeb.core.units.code.asm.sig;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.base.Assert;
import java.util.List;

public class NativeSignatureMatchResult {
   private final INativeMethodItem target;
   private final List signatures;
   private final boolean completeMatch;
   private final INativeSignature.ConfidenceLevel confidenceLevel;

   public NativeSignatureMatchResult(INativeMethodItem var1, List var2, boolean var3, INativeSignature.ConfidenceLevel var4) {
      Assert.a(var1 != null && var2 != null && var4 != null);
      this.target = var1;
      this.signatures = var2;
      this.completeMatch = var3;
      this.confidenceLevel = var4;
   }

   public List getSignatures() {
      return this.signatures;
   }

   public INativeMethodItem getTarget() {
      return this.target;
   }

   public boolean isComplete() {
      return this.completeMatch;
   }

   public INativeSignature.ConfidenceLevel getConfidenceLevel() {
      return this.confidenceLevel;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.completeMatch ? 1231 : 1237);
      var1 = 31 * var1 + (this.confidenceLevel == null ? 0 : this.confidenceLevel.hashCode());
      var1 = 31 * var1 + (this.signatures == null ? 0 : this.signatures.hashCode());
      return 31 * var1 + (this.target == null ? 0 : this.target.hashCode());
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
         NativeSignatureMatchResult var2 = (NativeSignatureMatchResult)var1;
         if (this.completeMatch != var2.completeMatch) {
            return false;
         } else if (this.confidenceLevel != var2.confidenceLevel) {
            return false;
         } else {
            if (this.signatures == null) {
               if (var2.signatures != null) {
                  return false;
               }
            } else if (!this.signatures.equals(var2.signatures)) {
               return false;
            }

            if (this.target == null) {
               if (var2.target != null) {
                  return false;
               }
            } else if (!this.target.equals(var2.target)) {
               return false;
            }

            return true;
         }
      }
   }

   @Override
   public String toString() {
      return "NativeSignatureMatchResult [target="
         + this.target
         + ", signatures="
         + this.signatures
         + ", completeMatch="
         + this.completeMatch
         + ", confidenceLevel="
         + this.confidenceLevel
         + "]";
   }
}
