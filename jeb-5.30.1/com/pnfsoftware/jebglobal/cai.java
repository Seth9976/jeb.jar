package com.pnfsoftware.jebglobal;

import java.lang.reflect.Method;

public class cai {
   public static void q(Class var0) throws Exception {
      Module var1 = var0.getClassLoader().getUnnamedModule();
      String var2 = cvm.q(new byte[]{42, 2, 0, 21, 51, 13, 3, 45, 12, 80, 71, 17, 93, 83, 126, 75, 118, 67, 73, 78, 65}, 2, 198);
      Method var3 = Module.class.getDeclaredMethod(var2, String.class, Module.class, boolean.class, boolean.class);
      var3.setAccessible(true);

      for (Module var5 : ModuleLayer.boot().modules()) {
         for (String var8 : var5.getPackages()) {
            var3.invoke(var5, var8, var1, true, true);
         }
      }
   }
}
