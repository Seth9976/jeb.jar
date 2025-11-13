package com.pnfsoftware.jeb.corei.parsers.gzip;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.units.AbstractUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import java.util.Map;

public class GzipIdentifier extends AbstractUnitIdentifier {
   public GzipIdentifier() {
      super("gzip", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("Gzip archive handler"), S.L("Gzip file inflater"), "PNF Software", Version.create(0, 1, 0));
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      if (!checkBytes(var1, 0, 31, -117)) {
         return false;
      } else {
         int var5 = readHeaderByte(var1, 2);
         if (var5 >= 0 && var5 <= 8) {
            int var6 = readHeaderByte(var1, 3);
            return var6 >= 0 && (var6 & -32) == 0;
         } else {
            return false;
         }
      }
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new Av(var1, var2, var3, var4, this.pdm);
   }
}
