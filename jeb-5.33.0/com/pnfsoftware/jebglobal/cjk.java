package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.code.IBasicBlockSkeleton;
import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AbstractAnalyzerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BinaryPattern;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BinaryPatternVerifier;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.NativeAnalyzerException;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.SwitchInformation;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Ser
public class cjk extends AbstractAnalyzerExtension {
   private static final ILogger wS = GlobalLog.getLogger(cjk.class);
   @SerTransient
   abs pC;
   @SerTransient
   com.pnfsoftware.jeb.corei.parsers.x86.wn A;
   @SerTransient
   cjm kS;
   @SerTransient
   private Map UT = new HashMap();

   @Override
   protected void initializePaddingPatterns(BinaryPatternVerifier var1) {
      var1.addPatterns(
         new BinaryPattern(new byte[]{-112}),
         new BinaryPattern(new byte[]{89}),
         new BinaryPattern(new byte[]{-61}),
         new BinaryPattern(new byte[]{-117, -1}),
         new BinaryPattern(new byte[]{-119, -1}),
         new BinaryPattern(new byte[]{102, -112}),
         new BinaryPattern(new byte[]{15, 11}),
         new BinaryPattern(new byte[]{-115, 73, 0}),
         new BinaryPattern(new byte[]{15, 31, 0}),
         new BinaryPattern(new byte[]{102, 102, -112}),
         new BinaryPattern(new byte[]{-125, -60, 4}),
         new BinaryPattern(new byte[]{-125, -60, 12}),
         new BinaryPattern(new byte[]{-115, 100, 36, 0}),
         new BinaryPattern(new byte[]{15, 31, 64, 0}),
         new BinaryPattern(new byte[]{102, 102, 102, -112}),
         new BinaryPattern(new byte[]{15, 31, 64, 0}),
         new BinaryPattern(new byte[]{-72, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{-21, 3, -115, 73, 0}),
         new BinaryPattern(new byte[]{15, 31, 68, 0, 0}),
         new BinaryPattern(new byte[]{-115, -101, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{102, 15, 31, 68, 0, 0}),
         new BinaryPattern(new byte[]{-115, -92, 36, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{15, 31, -128, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{15, 31, -124, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{-21, 6, -115, -101, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{102, 15, 31, -124, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{-21, 7, -115, -92, 36, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{102, 46, 15, 31, -124, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{102, 102, 15, 31, -124, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{-21, 8, -115, -92, 36, 0, 0, 0, 0, -112}),
         new BinaryPattern(new byte[]{102, 102, 102, 15, 31, -124, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{-21, 9, -115, -92, 36, 0, 0, 0, 0, -117, -1}),
         new BinaryPattern(new byte[]{102, 102, 102, 102, 15, 31, -124, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{15, 31, 64, 0, 15, 31, -124, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{-21, 10, -115, -92, 36, 0, 0, 0, 0, -115, 73, 0}),
         new BinaryPattern(new byte[]{102, 102, 102, 102, 102, 15, 31, -124, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{15, 31, 64, 0, 102, 15, 31, -124, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{-21, 11, -115, -92, 36, 0, 0, 0, 0, -115, 100, 36, 0}),
         new BinaryPattern(new byte[]{102, 102, 102, 102, 102, 102, 15, 31, -124, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{15, 31, 64, 0, 102, 102, 15, 31, -124, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{-21, 12, -115, -92, 36, 0, 0, 0, 0, 5, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{102, 102, 102, 102, 102, 102, 102, 15, 31, -124, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{15, 31, 64, 0, 102, 102, 102, 15, 31, -124, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{-21, 13, -115, -92, 36, 0, 0, 0, 0, -115, -101, 0, 0, 0, 0})
      );
   }

   @Override
   public void initializeProloguePatterns(BinaryPatternVerifier var1) {
      var1.addPatterns(
         new BinaryPattern(new byte[]{-119, -1, 85, -117, -20}),
         new BinaryPattern(new byte[]{-119, -1, 85, -119, -27}),
         new BinaryPattern(new byte[]{-117, -1, 85, -117, -20}),
         new BinaryPattern(new byte[]{-117, -1, 85, -119, -27}),
         new BinaryPattern(new byte[]{-119, -1, 86}),
         new BinaryPattern(new byte[]{-117, -1, 86}),
         new BinaryPattern(new byte[]{106, 0, 104, 0, 0, 0, 0, -24, 0, 0, 0, 0}, new byte[]{-1, 0, -1, 0, 0, 0, 0, -1, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{106, 0, 106, 0, -24, 0, 0, 0, 0}, new byte[]{-1, 0, -1, 0, -1, 0, 0, 0, 0})
      );
   }

   public ChainedOperationResult pC(long var1, com.pnfsoftware.jeb.corei.parsers.x86.vh var3, IBasicBlockSkeleton var4) {
      if (this.A == null) {
         this.A = cjh.pC(this.gca);
      }

      return this.A != null && this.A.pC(var1, var3, var4) ? ChainedOperationResult.TRUE_STOP : ChainedOperationResult.FALSE_CONTINUE;
   }

   @Override
   public ChainedOperationResult determineSwitchInformation(long var1, IBasicBlockSkeleton var3, List var4) {
      if (this.A != null) {
         SwitchInformation var5 = this.A.pC(var1, var3, var4);
         if (var5 != null) {
            return new ChainedOperationResult(var5);
         }
      }

      return ChainedOperationResult.ignore();
   }

   @Override
   public ChainedOperationResult postprocessImage(int var1) {
      try {
         if (this.kS == null) {
            this.kS = new cjm(this.gca, ((a)this.gca).wS().ld());
         }

         if (this.kS != null && this.kS.pC()) {
            this.kS.A();
            return ChainedOperationResult.TRUE_CONTINUE;
         }
      } catch (Exception var3) {
         wS.error(S.L("Tail call analyzer failed"));
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("tail call analyzer failed", var3));
      }

      return ChainedOperationResult.FALSE_CONTINUE;
   }

   @Override
   public ChainedOperationResult getPreferredAdvancedAnalysisStage(INativeMethodItem var1) {
      return ChainedOperationResult.ignore();
   }

   @Override
   public ChainedOperationResult sigMatchingPostProcess(int var1) {
      ChainedOperationResult var2 = ChainedOperationResult.FALSE_CONTINUE;

      try {
         if (this.pC == null) {
            this.pC = abr.pC(this.gca);
         }

         if (this.pC != null && this.pC.pC(false, true, true)) {
            return ChainedOperationResult.TRUE_CONTINUE;
         }
      } catch (Exception var4) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("SEH parsing", var4));
      }

      return var2;
   }
}
