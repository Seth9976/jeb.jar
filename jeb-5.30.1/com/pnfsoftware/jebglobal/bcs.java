package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AbstractAnalyzerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BinaryPattern;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BinaryPatternVerifier;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrimitiveSizes;
import com.pnfsoftware.jeb.core.units.code.asm.type.PrimitiveSizes;
import com.pnfsoftware.jeb.core.units.codeobject.CompilerType;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.SubsystemType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public class bcs extends AbstractAnalyzerExtension {
   @Override
   public IPrimitiveSizes getPrimitiveSizes(SubsystemType var1, CompilerType var2) {
      return new PrimitiveSizes(1, 2, 2, 4, 8, 4, 4, 8);
   }

   @Override
   public ChainedOperationResult preprocessImage(int var1) {
      if (var1 == 0) {
         ICodeObjectUnit var2 = this.gca.getContainer();
         if (var2 != null && !(var2 instanceof IELFUnit) && var2.getLoaderInformation().getImageBase() == 0L) {
            long var3 = 0L;

            for (int var5 = 1; var5 <= 26; var5++) {
               this.gca.enqueuePointerForAnalysis(new CodePointer(var3));
               var3 += 4L;
            }
         }
      }

      return super.preprocessImage(var1);
   }

   @Override
   protected void initializeProloguePatterns(BinaryPatternVerifier var1) {
      var1.addPatterns(new BinaryPattern(new byte[]{-49, -109, -33, -109}));
   }

   public ChainedOperationResult q(long var1, bdc var3, List var4) {
      return this.gca.getContainer() != null && this.gca.getContainer().getLoaderInformation().getImageBase() != 0L
         ? super.determinePotentialPointers(var1, var3, var4)
         : ChainedOperationResult.TRUE_STOP;
   }
}
