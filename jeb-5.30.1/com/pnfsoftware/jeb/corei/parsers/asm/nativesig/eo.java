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
public class eo implements ISigningStrategy {
   public static final String q = "CUSTOM";
   @SerId(1)
   private Set RF = new HashSet();
   @SerId(2)
   private Set xK = new HashSet();

   @Override
   public String getName() {
      return "CUSTOM";
   }

   public boolean q(NativeFeatureSignerID var1) {
      return this.RF.add(var1);
   }

   public boolean q(NativeAttributeSignerID var1) {
      return this.xK.add(var1);
   }

   @Override
   public Set getFeatureSigners(INativeCodeAnalyzer var1, INativeItem var2) {
      return this.RF;
   }

   @Override
   public Set getAttributeSigners(INativeCodeAnalyzer var1, INativeItem var2) {
      return this.xK;
   }
}
