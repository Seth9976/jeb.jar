package com.pnfsoftware.jeb.core.units.code.android.dex;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public enum DexReferenceType {
   UNKNOWN(0),
   PURE(1),
   META(2),
   GET(3),
   SET(4),
   INVOKE(5),
   GETTER(6),
   SETTER(7),
   INVOKER(8),
   ALLOC(9),
   REFLECTED(10),
   GET_REFLECTED(11),
   SET_REFLECTED(12),
   INVOKE_REFLECTED(13);

   private final int id;

   private DexReferenceType(int var3) {
      if (var3 >= 0 && var3 <= 255) {
         this.id = var3;
      } else {
         throw new RuntimeException();
      }
   }

   public int getId() {
      return this.id;
   }

   public static DexReferenceType fromId(int var0) {
      for (DexReferenceType var4 : values()) {
         if (var4.id == var0) {
            return var4;
         }
      }

      return UNKNOWN;
   }
}
