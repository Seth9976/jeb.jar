package com.pnfsoftware.jeb.core.util;

import com.pnfsoftware.jeb.core.IEnginesContext;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.code.ICodeUnit;
import com.pnfsoftware.jeb.core.units.code.IDecompilerUnit;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class DecompilerHelper {
   private static final String btJebPro = "JEB Pro";
   private static final String btJebAndroid = "JEB Android";
   private static final String btJebDemo = "JEB Demo";
   private static final String btJebCE = "JEB Community Edition";
   private static final Map supportmap = new HashMap();

   public static String typeName(String var0) {
      return "dcmp_" + var0;
   }

   public static boolean hasDecompilerFor(ICodeUnit var0) {
      if (getDecompiler(var0, false) != null) {
         return true;
      } else {
         String var1 = typeName(var0.getFormatType());
         return var0.getUnitProcessor().getUnitIdentifier(var1) != null;
      }
   }

   public static List getAvailableDecompilers(IEnginesContext var0) {
      ArrayList var1 = new ArrayList();

      for (IUnitIdentifier var3 : var0.getUnitIdentifiers()) {
         if (Strings.startsWith(var3.getFormatType(), "dcmp_")) {
            var1.add(var3);
         }
      }

      return var1;
   }

   public static boolean isDexDecompilerAvailable(IEnginesContext var0) {
      String var1 = typeName("dex");
      return var0.getUnitIdentifier(var1) != null;
   }

   public static List getAvailableDecompilerNames(IEnginesContext var0) {
      ArrayList var1 = new ArrayList();

      for (IUnitIdentifier var3 : var0.getUnitIdentifiers()) {
         String var4 = var3.getFormatType();
         if (Strings.startsWith(var4, "dcmp_")) {
            var1.add(var4.substring("dcmp_".length()));
         }
      }

      return var1;
   }

   public static IDecompilerUnit getDecompiler(IUnit var0) {
      return getDecompiler(var0, true);
   }

   public static IDecompilerUnit getDecompiler(IUnit var0, boolean var1) {
      for (IUnit var3 : var0.getChildren()) {
         if (var3 instanceof IDecompilerUnit) {
            return (IDecompilerUnit)var3;
         }
      }

      if (!var1) {
         return null;
      } else {
         IUnitProcessor var4 = var0.getUnitProcessor();
         return var4.createDecompiler("decompiler", var0);
      }
   }

   public static IDecompilerUnit getRelatedDecompiler(IUnit var0) {
      for (IUnit var2 : var0.getChildren()) {
         if (var2 instanceof IDecompilerUnit) {
            return (IDecompilerUnit)var2;
         }
      }

      IUnitCreator var3 = var0.getParent();
      return var3 instanceof IDecompilerUnit ? (IDecompilerUnit)var3 : null;
   }

   public static ICodeUnit getRelatedCodeUnit(IUnit var0) {
      if (var0 instanceof IDecompilerUnit) {
         if (var0.getParent() instanceof ICodeUnit) {
            return (ICodeUnit)var0.getParent();
         }
      } else if (var0.getParent() instanceof IDecompilerUnit) {
         IDecompilerUnit var1 = (IDecompilerUnit)var0.getParent();
         if (var1.getParent() instanceof ICodeUnit) {
            return (ICodeUnit)var1.getParent();
         }
      }

      return null;
   }

   public static List getBuildTypesWithDecompilationSupport(String var0) {
      List var1 = (List)supportmap.get(var0);
      return var1 == null ? Collections.emptyList() : var1;
   }

   public static Collection parsePluginNamesListProperty(String var0) {
      LinkedHashSet var1 = new LinkedHashSet();
      if (var0 != null) {
         for (String var5 : var0.split(",")) {
            var5 = var5.trim();
            if (!var5.isEmpty()) {
               var1.add(var5);
            }
         }
      }

      return var1;
   }

   public static String createPluginNamesListProperty(Collection var0) {
      return var0 == null ? "" : Strings.join(",", var0);
   }

   static {
      supportmap.put("dex", Arrays.asList("JEB Pro", "JEB Android", "JEB Demo"));
      supportmap.put("arm", Arrays.asList("JEB Pro", "JEB Android", "JEB Demo"));
      supportmap.put("arm64", Arrays.asList("JEB Pro", "JEB Android", "JEB Demo"));
      supportmap.put("x86", Arrays.asList("JEB Pro", "JEB Community Edition", "JEB Demo"));
      supportmap.put("x86_64", Arrays.asList("JEB Pro", "JEB Community Edition", "JEB Demo"));
      supportmap.put("mips", Arrays.asList("JEB Pro", "JEB Demo"));
      supportmap.put("mips64", Arrays.asList("JEB Pro", "JEB Demo"));
      supportmap.put("wasmbc", Arrays.asList("JEB Pro", "JEB Demo"));
      supportmap.put("evmbc", Arrays.asList("JEB Pro", "JEB Demo"));
   }
}
