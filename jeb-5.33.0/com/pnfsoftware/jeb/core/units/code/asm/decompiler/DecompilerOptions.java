package com.pnfsoftware.jeb.core.units.code.asm.decompiler;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.CMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.EMasterOptimizer;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class DecompilerOptions {
   @SerId(1)
   public long methodDecompTimeout = 0L;
   @SerId(value = 2, deprecated = true)
   public boolean irOptAggrDisabled = false;
   @SerId(value = 3, deprecated = true)
   public boolean useSSAForm = true;
   @SerId(4)
   public int memoryResolutionPolicy = 3;
   @SerId(5)
   public int reconversionMaxCount = 5;
   @SerId(6)
   public int irOptMaxRunCount = EMasterOptimizer.defaultMaxRunCount;
   @SerId(7)
   public int structurerUseVersion = 3;
   @SerId(8)
   public int astOptMaxRunCount = CMasterOptimizer.defaultMaxRunCount;
   @SerId(9)
   public boolean astFriendlyNames = true;
   @SerId(10)
   public boolean enableUnsafeOptimizers = true;
   @SerId(11)
   public boolean enableDeobfuscators = true;
   @SerId(12)
   public boolean astReplaceConstantsByWellKnownLiterals = true;
   @SerId(13)
   public boolean enableExternalPlugins = true;
   @SerId(14)
   public String listOfDisabledExternalPlugins = null;
   @SerId(15)
   public int decryptorSupport = 1;
}
