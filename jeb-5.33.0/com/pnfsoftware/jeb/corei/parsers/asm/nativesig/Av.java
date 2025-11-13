package com.pnfsoftware.jeb.corei.parsers.asm.nativesig;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.sig.ISigningStrategy;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeAttributeSignerID;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeFeatureSignerID;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.HashSet;
import java.util.Set;

@Ser
public class Av implements ISigningStrategy {
   @SerId(1)
   private Set pC = new HashSet();
   @SerId(2)
   private Set A = new HashSet();

   @Override
   public String getName() {
      return "CUSTOM";
   }

   public boolean pC(NativeFeatureSignerID var1) {
      return this.pC.add(var1);
   }

   public boolean pC(NativeAttributeSignerID var1) {
      return this.A.add(var1);
   }

   @Override
   public Set getFeatureSigners(INativeCodeAnalyzer var1, INativeItem var2) {
      return this.pC;
   }

   @Override
   public Set getAttributeSigners(INativeCodeAnalyzer var1, INativeItem var2) {
      return this.A;
   }
}
