package com.pnfsoftware.jeb.core.units.code.asm.sig;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Set;

@Ser
public interface ISigningStrategy {
   String getName();

   Set getFeatureSigners(INativeCodeAnalyzer var1, INativeItem var2);

   Set getAttributeSigners(INativeCodeAnalyzer var1, INativeItem var2);
}
