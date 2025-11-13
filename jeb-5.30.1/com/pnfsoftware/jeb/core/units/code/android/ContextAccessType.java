package com.pnfsoftware.jeb.core.units.code.android;

public enum ContextAccessType {
   NONE_SAFE(true, true, true),
   NONE(true, true),
   READ_ONLY(false, true),
   WRITE_ONLY(true, false),
   READ_WRITE(false, false),
   UNKNOWN(false, false);

   private final boolean ci;
   private final boolean sef;
   private final boolean nothrow;

   private ContextAccessType(boolean var3, boolean var4, boolean var5) {
      this.ci = var3;
      this.sef = var4;
      this.nothrow = var5;
   }

   private ContextAccessType(boolean var3, boolean var4) {
      this(var3, var4, false);
   }

   public boolean isCI() {
      return this.ci;
   }

   public boolean reads() {
      return !this.ci;
   }

   public boolean isSEF() {
      return this.sef;
   }

   public boolean writes() {
      return !this.sef;
   }

   public boolean isNothrow() {
      return this.nothrow;
   }

   public static ContextAccessType get(boolean var0, boolean var1) {
      if (var1) {
         return var0 ? NONE : READ_ONLY;
      } else {
         return var0 ? WRITE_ONLY : READ_WRITE;
      }
   }

   public ContextAccessType addAccess(ContextAccessType var1) {
      return var1 != null && this.ordinal() < var1.ordinal() ? var1 : this;
   }

   public boolean isAllAccess() {
      return !this.ci && !this.sef;
   }

   public boolean isNoneLike() {
      return this == NONE || this == NONE_SAFE;
   }
}
