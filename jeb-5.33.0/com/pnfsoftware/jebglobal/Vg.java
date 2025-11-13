package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Vg {
   public static boolean pC(int var0, int var1) {
      return var0 == -721215457 && var1 == 64 || var0 == -484380672 && var1 != 64 || var0 == -2147421265 && var1 != 64 || var0 == 0 && var1 != 64;
   }

   public static List pC(INativeCodeAnalyzer var0, Map var1, int var2) {
      ArrayList var3 = new ArrayList();
      var3.add(new iI(var0, var1));
      var3.add(new XU(var0, var1, 8, 4));
      var3.add(new aC(var0, var1, 4, 2));
      return var3;
   }
}
