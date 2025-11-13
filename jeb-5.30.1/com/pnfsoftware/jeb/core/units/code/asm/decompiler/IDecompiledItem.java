package com.pnfsoftware.jeb.core.units.code.asm.decompiler;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDecompilableElement;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IDecompiledItem {
   INativeDecompilerContext getDecompiler();

   INativeItem getNativeItem();

   ICDecompilableElement getASTItem();

   DecompilationStatus getStatus();

   int getCompletionCount();

   long getCreationTimestamp();

   long getLastModificationTimestampMs();

   int getFlags();

   void setFlags(int var1);

   IErrorDescription getErrorDescription();

   default boolean isDone() {
      return this.getStatus() == DecompilationStatus.COMPLETED;
   }

   default boolean isSuccess() {
      return this.getStatus() == DecompilationStatus.COMPLETED && this.getErrorDescription() == null;
   }

   default boolean isFailure() {
      return this.getStatus() == DecompilationStatus.COMPLETED && this.getErrorDescription() != null;
   }
}
