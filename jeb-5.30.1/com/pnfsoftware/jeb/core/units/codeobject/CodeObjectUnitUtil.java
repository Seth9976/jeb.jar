package com.pnfsoftware.jeb.core.units.codeobject;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.List;

public class CodeObjectUnitUtil {
   public static ISymbolInformation findSymbolByName(ICodeObjectUnit var0, String var1) {
      for (ISymbolInformation var3 : var0.getSymbols()) {
         if (var1.equals(var3.getName())) {
            return var3;
         }
      }

      return null;
   }

   public static List findAllSymbolsByName(ICodeObjectUnit var0, String var1) {
      ArrayList var2 = new ArrayList();

      for (ISymbolInformation var4 : var0.getSymbols()) {
         if (var1.equals(var4.getName())) {
            var2.add(var4);
         }
      }

      return var2;
   }

   public static List findAllSymbolsByRelativeAddress(ICodeObjectUnit var0, long var1) {
      ArrayList var3 = new ArrayList();

      for (ISymbolInformation var5 : var0.getSymbols()) {
         if (var5.getSymbolRelativeAddress() == var1) {
            var3.add(var5);
         }
      }

      return var3;
   }

   public static boolean hasSymbolsAtRelativeAddress(ICodeObjectUnit var0, long var1) {
      for (ISymbolInformation var4 : var0.getSymbols()) {
         if (var4.getSymbolRelativeAddress() == var1) {
            return true;
         }
      }

      return false;
   }

   public static ISymbolInformation findExportedSymbolByName(ICodeObjectUnit var0, String var1) {
      for (ISymbolInformation var3 : var0.getExportedSymbols()) {
         if (var1.equals(var3.getName())) {
            return var3;
         }
      }

      return null;
   }

   public static ISymbolInformation findImportedSymbolByName(ICodeObjectUnit var0, String var1) {
      for (ISymbolInformation var3 : var0.getImportedSymbols()) {
         if (var1.equals(var3.getName())) {
            return var3;
         }
      }

      return null;
   }

   public static ISegmentInformation findSectionByName(ICodeObjectUnit var0, String var1) {
      for (ISegmentInformation var3 : var0.getSections()) {
         if (var1.equals(var3.getName())) {
            return var3;
         }
      }

      return null;
   }

   public static ISegmentInformation findSegmentByName(ICodeObjectUnit var0, String var1) {
      for (ISegmentInformation var3 : var0.getSegments()) {
         if (var1.equals(var3.getName())) {
            return var3;
         }
      }

      return null;
   }

   public static ISegmentInformation findSectionByRelativeAddress(ICodeObjectUnit var0, long var1) {
      for (ISegmentInformation var4 : var0.getSections()) {
         long var5 = var4.getOffsetInMemory();
         long var7 = var5 + var4.getSizeInMemory();
         if (Longs.compareUnsigned(var1, var5) >= 0 && Longs.compareUnsigned(var1, var7) < 0) {
            return var4;
         }
      }

      return null;
   }

   public static ISegmentInformation findSegmentByRelativeAddress(ICodeObjectUnit var0, long var1) {
      for (ISegmentInformation var4 : var0.getSegments()) {
         long var5 = var4.getOffsetInMemory();
         long var7 = var5 + var4.getSizeInMemory();
         if (Longs.compareUnsigned(var1, var5) >= 0 && Longs.compareUnsigned(var1, var7) < 0) {
            return var4;
         }
      }

      return null;
   }

   public static INativeCodeUnit getUniqueNativeCodeUnitChild(ICodeObjectUnit var0) {
      List var1 = UnitUtil.findChildrenByType(var0, INativeCodeUnit.class, false);
      return var1 != null && var1.size() == 1 ? (INativeCodeUnit)var1.get(0) : null;
   }
}
