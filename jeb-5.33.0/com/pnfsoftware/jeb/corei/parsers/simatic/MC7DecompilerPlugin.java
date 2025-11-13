package com.pnfsoftware.jeb.corei.parsers.simatic;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.AbstractNativeDecompilerPlugin;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerUnit;

public class MC7DecompilerPlugin extends AbstractNativeDecompilerPlugin {
   public MC7DecompilerPlugin() {
      super("dcmp_simatic_mc7", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(
         S.L("S7 Decompiler"), S.L("S7 PLC block decompiler (for Simatic S7 blocks, MC7 bytecode)"), "PNF Software", Version.create(0, 1)
      );
   }

   @Override
   public void setupCustomProperties(IPropertyDefinitionManager var1) {
   }

   @Override
   public IEConverter getConverter(INativeCodeUnit var1) {
      if (var1.getParent() instanceof gb) {
         gb var2 = (gb)var1.getParent();
         return new Sv(var2, var1);
      } else {
         throw new RuntimeException("Unexected parent type");
      }
   }

   @Override
   public INativeDecompilerExtension getPrimaryExtension(INativeDecompilerUnit var1) {
      return new Ws();
   }
}
