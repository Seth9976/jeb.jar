package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeBoolean;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.AbstractNativeDecompilerPlugin;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IGlobalAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ISourceCustomizer;

public class EvmDecompilerPlugin extends AbstractNativeDecompilerPlugin {
   public EvmDecompilerPlugin() {
      super("dcmp_evmbc", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("EVM Decompiler"), S.L("Ethereum Contract bytecode decompiler"), "PNF Software", Version.create(0, 3));
   }

   @Override
   public void setupCustomProperties(IPropertyDefinitionManager var1) {
      var1.addDefinition("UseFriendlyVariableNames", PropertyTypeBoolean.create(false), null);
   }

   @Override
   public IEConverter getConverter(INativeCodeUnit var1) {
      if (var1.getParent() instanceof yt) {
         yt var2 = (yt)var1.getParent();
         return new oP(var2, var1);
      } else {
         throw new JebRuntimeException("Parent of dcmp_evmbc must be of type eth");
      }
   }

   @Override
   public INativeDecompilerExtension getPrimaryExtension(INativeDecompilerUnit var1) {
      return new vi();
   }

   @Override
   public IGlobalAnalyzer getGlobalAnalyzer(INativeDecompilerUnit var1) {
      return new Sv(var1);
   }

   @Override
   public ISourceCustomizer getSourceCustomizer(INativeDecompilerUnit var1) {
      return new Sf(var1);
   }
}
