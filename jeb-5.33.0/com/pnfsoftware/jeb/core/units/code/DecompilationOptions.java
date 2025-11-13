package com.pnfsoftware.jeb.core.units.code;

import java.util.function.Consumer;

public class DecompilationOptions {
   public static final DecompilationOptions DEFAULT = new DecompilationOptions(0, null, null, null);
   private final int flags;
   private final Long maxTimePerMethod;
   private final Long maxTimeTotal;
   private final Consumer postDecompilationCallback;

   public static DecompilationOptions safe(DecompilationOptions var0) {
      return var0 != null ? var0 : DEFAULT;
   }

   DecompilationOptions(int var1, Long var2, Long var3, Consumer var4) {
      this.flags = var1;
      this.maxTimePerMethod = var2;
      this.maxTimeTotal = var3;
      this.postDecompilationCallback = var4;
   }

   public int getFlags() {
      return this.flags;
   }

   public Long getMaxTimePerMethod() {
      return this.maxTimePerMethod;
   }

   public Long getMaxTimeTotal() {
      return this.maxTimeTotal;
   }

   public Consumer getPostDecompilationCallback() {
      return this.postDecompilationCallback;
   }

   public DecompilationOptions.Builder asBuilder() {
      DecompilationOptions.Builder var1 = new DecompilationOptions.Builder();
      var1.flags = this.flags;
      var1.maxTimePerMethod = this.maxTimePerMethod;
      var1.maxTimeTotal = this.maxTimeTotal;
      var1.postDecompilationCallback = this.postDecompilationCallback;
      return var1;
   }

   public static class Builder {
      private int flags;
      private Long maxTimePerMethod;
      private Long maxTimeTotal;
      private Consumer postDecompilationCallback;

      public static DecompilationOptions.Builder newInstance() {
         return new DecompilationOptions.Builder();
      }

      private Builder() {
      }

      public DecompilationOptions.Builder flags(int var1) {
         this.flags = var1;
         return this;
      }

      public DecompilationOptions.Builder maxTimePerMethod(Long var1) {
         this.maxTimePerMethod = var1;
         return this;
      }

      public DecompilationOptions.Builder maxTimeTotal(Long var1) {
         this.maxTimeTotal = var1;
         return this;
      }

      public DecompilationOptions.Builder postDecompilationCallback(Consumer var1) {
         this.postDecompilationCallback = var1;
         return this;
      }

      public DecompilationOptions build() {
         return new DecompilationOptions(this.flags, this.maxTimePerMethod, this.maxTimeTotal, this.postDecompilationCallback);
      }
   }
}
