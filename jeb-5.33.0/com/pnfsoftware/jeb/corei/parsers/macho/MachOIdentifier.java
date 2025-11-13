package com.pnfsoftware.jeb.corei.parsers.macho;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.units.AbstractUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import java.nio.ByteBuffer;
import java.util.Map;

public class MachOIdentifier extends AbstractUnitIdentifier {
   public MachOIdentifier() {
      super("macho", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("Mach-O loader"), S.L("Apple Mach-O code object parser"), "PNF Software", Version.create(0, 0, 1));
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      if (var1 == null) {
         return false;
      } else {
         ByteBuffer var5 = var1.getHeader();
         if (var5.limit() <= 4) {
            return false;
         } else {
            int var6 = var5.getInt();
            return var6 == -17958194 || var6 == -822415874 || var6 == -17958193 || var6 == -805638658;
         }
      }
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new zp(var1, var2, var3, var4, this.pdm);
   }
}
