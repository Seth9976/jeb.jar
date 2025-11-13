package com.pnfsoftware.jeb.corei.parsers.ihex;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeBoolean;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeString;
import com.pnfsoftware.jeb.core.units.AbstractUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;

public class IntelHexIdentifier extends AbstractUnitIdentifier {
   public IntelHexIdentifier() {
      super("ihex", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("Intel HEX"), S.L("Intel HEX binary blob parser"), "PNF Software", Version.create(0, 1));
   }

   @Override
   public void initialize(IPropertyDefinitionManager var1) {
      super.initialize(var1);
      this.pdm
         .addDefinition(
            "Processor",
            PropertyTypeString.create("x86"),
            S.L("Target processor/microcontroller code; refer to processor plugin type names. The default is x86"),
            32
         );
      this.pdm
         .addDefinition(
            "BigEndian",
            PropertyTypeBoolean.create(),
            S.L("Set to true to specify big-endian. The default is little-endian, unless a specific processor setting overrides it."),
            32
         );
      this.pdm
         .addDefinition(
            "WantedWordsize",
            PropertyTypeString.create(),
            S.L("Desired wordsize in bits: 8, 16, 32, etc. Leave empty to let the plugin decide by heuristics"),
            32
         );
      this.pdm.addDefinition("WantedImageBase", PropertyTypeString.create(), S.L("Base address hint. Leave empty to let the plugin decide."), 32);
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      if (var1 == null) {
         return false;
      } else {
         ByteBuffer var5 = var1.getHeader();
         if (var5.remaining() >= 12 && var5.get() == 58) {
            try {
               new K(var5).pC(true);
               return true;
            } catch (IOException var6) {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      Av var6 = new Av(var1, var2, var3, var4, this.pdm);
      if (var1 != null && var1.contains(".ino.")) {
         var6.getPropertyManager().setString("Processor", "avr");
         var6.getPropertyManager().setBoolean("BigEndian", false);
      }

      return var6;
   }
}
