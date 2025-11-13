package com.pnfsoftware.jeb.util.interpreter;

import com.pnfsoftware.jeb.util.format.Strings;

public class ExecutionResult {
   public static final int CODE_SUCCESS = 0;
   public static final int CODE_ERROR = -1;
   public static final int CODE_NOT_SUPPORTED = -2;
   public static final int CODE_NOT_IMPLEMENTED = -3;
   public static final ExecutionResult GENERIC_SUCCESS = new ExecutionResult(0);
   public static final ExecutionResult GENERIC_ERROR = new ExecutionResult(-1);
   public static final ExecutionResult NOT_SUPPORTED = new ExecutionResult(-2);
   public static final ExecutionResult NOT_IMPLEMENTED = new ExecutionResult(-3);
   private int code;
   private String message;

   public static ExecutionResult success(String var0) {
      return new ExecutionResult(0, var0);
   }

   public static ExecutionResult error(String var0) {
      return new ExecutionResult(-1, var0);
   }

   public static ExecutionResult error(Throwable var0) {
      String var1 = var0.getMessage();
      return !Strings.isBlank(var1) ? new ExecutionResult(-1, var1) : new ExecutionResult(-1, var0.toString());
   }

   public static ExecutionResult failure(String var0) {
      return error(var0);
   }

   public static ExecutionResult fromBoolean(boolean var0) {
      return var0 ? GENERIC_SUCCESS : GENERIC_ERROR;
   }

   public static ExecutionResult fromObject(Object var0) {
      return var0 == null ? GENERIC_ERROR : new ExecutionResult(0, var0.toString());
   }

   private ExecutionResult(int var1) {
      this(var1, null);
   }

   private ExecutionResult(String var1) {
      this(0, var1);
   }

   private ExecutionResult(int var1, String var2) {
      this.code = var1;
      this.message = Strings.safe(var2);
   }

   public int getCode() {
      return this.code;
   }

   public boolean isSuccess() {
      return this.getCode() == 0;
   }

   public boolean isError() {
      return this.getCode() != 0;
   }

   public String getMessage() {
      return this.message;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.code;
      return 31 * var1 + (this.message == null ? 0 : this.message.hashCode());
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
         ExecutionResult var2 = (ExecutionResult)var1;
         if (this.code != var2.code) {
            return false;
         } else {
            if (this.message == null) {
               if (var2.message != null) {
                  return false;
               }
            } else if (!this.message.equals(var2.message)) {
               return false;
            }

            return true;
         }
      }
   }

   @Override
   public String toString() {
      return Strings.ff("code=%d,message={%s}", this.getCode(), this.getMessage());
   }
}
