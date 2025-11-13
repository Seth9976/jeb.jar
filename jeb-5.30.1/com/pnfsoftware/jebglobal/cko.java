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

public class cko extends AbstractNativeDecompilerPlugin {
   public static final String q = "dcmp_mips";

   public cko() {
      super("dcmp_mips", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("MIPS Decompiler"), S.L("MIPS 32-bit decompiler"), "PNF Software", Version.create(0, 0, 1));
   }

   @Override
   public IEConverter getConverter(INativeCodeUnit var1) {
      return new cln((clc)var1.getProcessor());
   }

   @Override
   public INativeDecompilerExtension getPrimaryExtension(INativeDecompilerUnit var1) {
      return new ckn();
   }
}
