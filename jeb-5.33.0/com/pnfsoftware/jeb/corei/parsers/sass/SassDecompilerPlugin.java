package com.pnfsoftware.jeb.corei.parsers.sass;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.AbstractNativeDecompilerPlugin;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerUnit;

public class SassDecompilerPlugin extends AbstractNativeDecompilerPlugin {
   public SassDecompilerPlugin() {
      super("dcmp_sass_visa", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(
         S.L("SASS Decompiler"),
         S.L("NVidia SASS (Streaming Assembly) decompiler for sm_70+, referred to as 'sass_visa' (Volta+ ISA)"),
         "PNF Software",
         Version.create(0, 0, 1)
      );
   }

   @Override
   public IEConverter getConverter(INativeCodeUnit var1) {
      return new rQ((vi)var1.getProcessor());
   }

   @Override
   public INativeDecompilerExtension getPrimaryExtension(INativeDecompilerUnit var1) {
      return new zp();
   }
}
