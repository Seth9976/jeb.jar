package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AbstractAnalyzerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BinaryPattern;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BinaryPatternVerifier;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class crm extends AbstractAnalyzerExtension {
   @Override
   protected void initializePaddingPatterns(BinaryPatternVerifier var1) {
      var1.addPatterns(
         new BinaryPattern(new byte[]{-112}),
         new BinaryPattern(new byte[]{102, -112}),
         new BinaryPattern(new byte[]{15, 31, 0}),
         new BinaryPattern(new byte[]{15, 31, 64, 0}),
         new BinaryPattern(new byte[]{15, 31, 68, 0, 0}),
         new BinaryPattern(new byte[]{102, 15, 31, 68, 0, 0}),
         new BinaryPattern(new byte[]{15, 31, -128, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{15, 31, -124, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{102, 15, 31, -124, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{102, 46, 15, 31, -124, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{102, 102, 46, 15, 31, -124, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{102, 102, 102, 46, 15, 31, -124, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{102, 102, 102, 102, 46, 15, 31, -124, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{102, 102, 102, 102, 102, 46, 15, 31, -124, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{102, 102, 102, 102, 102, 102, 46, 15, 31, -124, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{-115, 118, 0}),
         new BinaryPattern(new byte[]{-115, 116, 38, 0}),
         new BinaryPattern(new byte[]{-112, -115, 116, 38, 0}),
         new BinaryPattern(new byte[]{-115, -74, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{-115, -76, 38, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{0}),
         new BinaryPattern(new byte[]{0, 0}),
         new BinaryPattern(new byte[]{0, 0, 0}),
         new BinaryPattern(new byte[]{0, 0, 0, 0}),
         new BinaryPattern(new byte[]{0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{0, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{0, 0, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{0, 0, 0, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0})
      );
   }
}
