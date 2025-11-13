package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import javax.annotation.concurrent.Immutable;

@Ser
@Immutable
public class BranchTarget implements IBranchTarget {
   @SerId(1)
   private CodePointer codeptr;
   @SerId(2)
   private INativeMethodItem routine;

   public BranchTarget() {
   }

   public BranchTarget(CodePointer var1) {
      if (var1 == null) {
         throw new NullPointerException("Null Entry Point");
      } else if (var1.isUnknownAddress()) {
         throw new IllegalArgumentException("Entry point is an unknown address");
      } else {
         this.codeptr = var1;
      }
   }

   public BranchTarget(INativeMethodItem var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         if (var1.getData() != null) {
            this.codeptr = CodePointer.createFrom(var1);
         }

         this.routine = var1;
      }
   }

   @Override
   public boolean isInternal() {
      return this.codeptr != null;
   }

   public CodePointer getInternalAddress() {
      return this.codeptr;
   }

   @Override
   public INativeMethodItem getRoutine() {
      return this.routine;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.routine == null ? 0 : this.routine.hashCode());
      return 31 * var1 + (this.codeptr == null ? 0 : this.codeptr.hashCode());
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
         BranchTarget var2 = (BranchTarget)var1;
         if (this.routine != var2.routine) {
            return false;
         } else {
            if (this.codeptr == null) {
               if (var2.codeptr != null) {
                  return false;
               }
            } else if (!this.codeptr.equals(var2.codeptr)) {
               return false;
            }

            return true;
         }
      }
   }

   @Override
   public String toString() {
      return this.routine != null ? this.routine.getAddress() : this.codeptr.toString();
   }
}
