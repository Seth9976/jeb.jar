package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.AbstractNativeDecompilerPlugin;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerUnit;

public class bcz extends AbstractNativeDecompilerPlugin {
   public static final String q = "dcmp_avr";

   public bcz() {
      super("dcmp_avr", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("AVR Decompiler"), S.L("Atmel AVR decompiler"), "PNF Software", Version.create(0, 0, 1));
   }

   @Override
   public IEConverter getConverter(INativeCodeUnit var1) {
      return new bcx((bdf)var1.getProcessor());
   }

   @Override
   public INativeDecompilerExtension getPrimaryExtension(INativeDecompilerUnit var1) {
      return new bcy();
   }
}
