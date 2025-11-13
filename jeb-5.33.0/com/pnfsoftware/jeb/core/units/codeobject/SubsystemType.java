package com.pnfsoftware.jeb.core.units.codeobject;

import com.pnfsoftware.jeb.util.base.DynamicEnum;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;
import java.util.LinkedHashMap;

@Ser
public class SubsystemType extends DynamicEnum {
   protected static LinkedHashMap map = new LinkedHashMap();
   public static final SubsystemType UNKNOWN = register(0, "UNKNOWN");
   public static final SubsystemType UNIX = register(100, "UNIX");
   public static final SubsystemType LINUX = register(200, "LINUX");
   public static final SubsystemType MAC = register(300, "MAC");
   public static final SubsystemType WINDOWS = register(400, "WINDOWS");
   public static final SubsystemType WINDOWS_KERNEL = register(401, "WINDOWS_KERNEL");
   public static final SubsystemType WINDOWS_USER = register(402, "WINDOWS_USER");
   public static final SubsystemType EFI = register(500, "EFI");
   public static final int builtinCount = map.size();

   protected SubsystemType(int var1, String var2) {
      super(var1, var2);
   }

   @Override
   public int ordinal() {
      return ordinal(map, this);
   }

   public static int count() {
      return map.size();
   }

   public static Collection values() {
      return values(map);
   }

   public static SubsystemType valueOf(String var0) {
      return (SubsystemType)valueOf(map, var0, UNKNOWN);
   }

   public static SubsystemType valueOf(int var0) {
      return (SubsystemType)valueOf(map, var0, UNKNOWN);
   }

   public static SubsystemType register(int var0, String var1) {
      return (SubsystemType)register(map, new SubsystemType(var0, var1));
   }

   public static boolean unregister(String var0) {
      return unregister(map, builtinCount, var0);
   }

   public boolean isUnixLike() {
      return this == UNIX || this == LINUX || this == MAC;
   }

   public boolean isWindowsLike() {
      return this == WINDOWS || this == WINDOWS_KERNEL || this == WINDOWS_USER;
   }
}
