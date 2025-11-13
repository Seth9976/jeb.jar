package com.pnfsoftware.jeb.corei.parsers.mips;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.AbstractNativeDecompilerPlugin;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerUnit;
import com.pnfsoftware.jebglobal.cfb;

public class Mips64DecompilerPlugin extends AbstractNativeDecompilerPlugin {
   public Mips64DecompilerPlugin() {
      super("dcmp_mips64", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("MIPS64 Decompiler"), S.L("MIPS 64-bit decompiler"), "PNF Software", Version.create(0, 0, 1));
   }

   @Override
   public IEConverter getConverter(INativeCodeUnit var1) {
      return new cfb((p)var1.getProcessor());
   }

   @Override
   public INativeDecompilerExtension getPrimaryExtension(INativeDecompilerUnit var1) {
      return new bO();
   }
}
