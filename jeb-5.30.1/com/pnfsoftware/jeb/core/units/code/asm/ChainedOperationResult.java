package com.pnfsoftware.jeb.core.units.code.asm;

import com.pnfsoftware.jeb.util.format.Strings;
import javax.annotation.concurrent.Immutable;

@Immutable
public class ChainedOperationResult {
   public static final ChainedOperationResult TRUE_STOP = new ChainedOperationResult(true, ChainedOperationResult.ContinuationStatus.STOP);
   public static final ChainedOperationResult TRUE_CONTINUE = new ChainedOperationResult(true, ChainedOperationResult.ContinuationStatus.CONTINUE);
   public static final ChainedOperationResult FALSE_STOP = new ChainedOperationResult(false, ChainedOperationResult.ContinuationStatus.STOP);
   public static final ChainedOperationResult FALSE_CONTINUE = new ChainedOperationResult(false, ChainedOperationResult.ContinuationStatus.CONTINUE);
   public static final ChainedOperationResult FALSE_IGNORE = new ChainedOperationResult(false, ChainedOperationResult.ContinuationStatus.IGNORE);
   public static final ChainedOperationResult ZEROL_CONTINUE = new ChainedOperationResult(0L, ChainedOperationResult.ContinuationStatus.CONTINUE);
   private static final ChainedOperationResult NULL_STOP = new ChainedOperationResult(null, ChainedOperationResult.ContinuationStatus.STOP);
   private static final ChainedOperationResult NULL_CONTINUE = new ChainedOperationResult(null, ChainedOperationResult.ContinuationStatus.CONTINUE);
   private static final ChainedOperationResult NULL_IGNORE = new ChainedOperationResult(null, ChainedOperationResult.ContinuationStatus.IGNORE);
   private Object result;
   private ChainedOperationResult.ContinuationStatus continutationStatus;

   public ChainedOperationResult(Object var1) {
      this.result = var1;
      this.continutationStatus = ChainedOperationResult.ContinuationStatus.STOP;
   }

   public ChainedOperationResult(Object var1, ChainedOperationResult.ContinuationStatus var2) {
      this.result = var1;
      this.continutationStatus = var2;
   }

   public Object getResult() {
      return this.result;
   }

   public ChainedOperationResult.ContinuationStatus getContinuationStatus() {
      return this.continutationStatus;
   }

   @Override
   public String toString() {
      return Strings.ff("%s(%s)", this.result, this.continutationStatus);
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.continutationStatus == null ? 0 : this.continutationStatus.hashCode());
      return 31 * var1 + (this.result == null ? 0 : this.result.hashCode());
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
         ChainedOperationResult var2 = (ChainedOperationResult)var1;
         if (this.continutationStatus != var2.continutationStatus) {
            return false;
         } else {
            if (this.result == null) {
               if (var2.result != null) {
                  return false;
               }
            } else if (!this.result.equals(var2.result)) {
               return false;
            }

            return true;
         }
      }
   }

   public static ChainedOperationResult stop(boolean var0) {
      return var0 ? TRUE_STOP : FALSE_STOP;
   }

   public static ChainedOperationResult stop() {
      return NULL_STOP;
   }

   public static ChainedOperationResult continue_() {
      return NULL_CONTINUE;
   }

   public static ChainedOperationResult ignore() {
      return NULL_IGNORE;
   }

   public static enum ContinuationStatus {
      CONTINUE,
      IGNORE,
      STOP;

      public boolean isIgnore() {
         return this == IGNORE;
      }

      public boolean isContinue() {
         return this == CONTINUE;
      }

      public boolean isStop() {
         return this == STOP;
      }
   }
}
