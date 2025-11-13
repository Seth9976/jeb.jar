package com.pnfsoftware.jeb.corei.parsers.winpe;

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
import java.nio.ByteOrder;
import java.util.Map;

public class PEIdentifier extends AbstractUnitIdentifier {
   public PEIdentifier() {
      super("winpe", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("PE loader"), S.L("Windows Portable Executable (PE) code object parser"), "PNF Software", Version.create(0, 0, 1));
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      return pC(var1);
   }

   public static boolean pC(IInput var0) {
      if (!checkBytes(var0, 0, 77, 90)) {
         return false;
      } else {
         ByteBuffer var1 = var0.getHeader();
         if (var1.limit() < 64) {
            return false;
         } else {
            var1.order(ByteOrder.LITTLE_ENDIAN);
            int var2 = var1.getInt(60);
            return var2 >= 0 && var2 + 4 <= var0.getCurrentSize() ? checkBytes(var0, var2, 80, 69, 0, 0) : false;
         }
      }
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new yt(var1, var2, var3, var4, this.pdm);
   }
}
