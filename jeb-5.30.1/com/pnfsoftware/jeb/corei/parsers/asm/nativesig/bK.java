package com.pnfsoftware.jeb.corei.parsers.asm.nativesig;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.sig.ISigningStrategy;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Set;

@Ser
public class bK implements ISigningStrategy {
   public static final String q = "SIZE_BASED";

   @Override
   public String getName() {
      return "SIZE_BASED";
   }

   @Override
   public Set getFeatureSigners(INativeCodeAnalyzer var1, INativeItem var2) {
      return null;
   }

   @Override
   public Set getAttributeSigners(INativeCodeAnalyzer var1, INativeItem var2) {
      return null;
   }
}
