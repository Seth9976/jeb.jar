package com.pnfsoftware.jeb.core.units.code.android.dex;

public enum DexMethodHandleType {
   STATIC_PUT(0),
   STATIC_GET(1),
   INSTANCE_PUT(2),
   INSTANCE_GET(3),
   INVOKE_STATIC(4),
   INVOKE_INSTANCE(5),
   INVOKE_CONSTRUCTOR(6),
   INVOKE_DIRECT(7),
   INVOKE_INTERFACE(8);

   private final int id;

   private DexMethodHandleType(int var3) {
      this.id = var3;
   }

   public int getId() {
      return this.id;
   }

   @Override
   public String toString() {
      return super.toString().toLowerCase().replace('_', '-');
   }

   public boolean isMethodInvoker() {
      return this == INVOKE_STATIC || this == INVOKE_INSTANCE || this == INVOKE_CONSTRUCTOR || this == INVOKE_DIRECT || this == INVOKE_INTERFACE;
   }

   public boolean isFieldSetter() {
      return this == STATIC_PUT || this == INSTANCE_PUT;
   }

   public boolean isFieldGetter() {
      return this == STATIC_GET || this == INSTANCE_GET;
   }

   public boolean isFieldAccessor() {
      return this.isFieldSetter() || this.isFieldGetter();
   }

   public static DexMethodHandleType get(int var0) {
      for (DexMethodHandleType var4 : values()) {
         if (var4.id == var0) {
            return var4;
         }
      }

      throw new RuntimeException("Unknown MethodHandle type id: " + var0);
   }
}
