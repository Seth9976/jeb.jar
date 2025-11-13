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

public class crz extends AbstractNativeDecompilerPlugin {
   public static final String q = "dcmp_x86";

   public crz() {
      super("dcmp_x86", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("x86 Decompiler"), S.L("Intel/AMD x86 32-bit decompiler"), "PNF Software", Version.create(0, 0, 1));
   }

   @Override
   public IEConverter getConverter(INativeCodeUnit var1) {
      return new crx((cti)var1.getProcessor());
   }

   @Override
   public INativeDecompilerExtension getPrimaryExtension(INativeDecompilerUnit var1) {
      return new cry();
   }
}
