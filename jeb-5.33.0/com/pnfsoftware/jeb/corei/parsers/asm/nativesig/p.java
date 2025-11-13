package com.pnfsoftware.jeb.corei.parsers.asm.nativesig;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.sig.ISigningStrategy;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeAttributeSignerID;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeFeatureSignerID;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jebglobal.auu;
import java.util.HashSet;
import java.util.Set;

@Ser
public class p implements ISigningStrategy {
   private static final Set pC = new HashSet();
   private static final Set A = new HashSet();

   @Override
   public String getName() {
      return "STRICT";
   }

   @Override
   public Set getFeatureSigners(INativeCodeAnalyzer var1, INativeItem var2) {
      Assert.a(var2 instanceof auu, "only routines can be checked with this strategy");
      auu var3 = (auu)var2;
      Assert.a(var3.E() != null, "only internal routines can be checked with this strategy");
      return pC;
   }

   @Override
   public Set getAttributeSigners(INativeCodeAnalyzer var1, INativeItem var2) {
      Assert.a(var2 instanceof auu, "only routines can be checked with this strategy");
      auu var3 = (auu)var2;
      Assert.a(var3.E() != null, "only internal routines can be checked with this strategy");
      return A;
   }

   static {
      pC.add(NativeFeatureSignerID.ROUTINE_CODE_HASH);
      pC.add(NativeFeatureSignerID.ROUTINE_SIZE);
      pC.add(NativeFeatureSignerID.CALLED_ROUTINE_NAME_ONLY_EXTERN);
      A.add(NativeAttributeSignerID.COMMENT);
      A.add(NativeAttributeSignerID.LABEL);
   }
}
