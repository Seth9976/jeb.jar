package com.pnfsoftware.jeb.corei.parsers.tar;

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

public class TarIdentifier extends AbstractUnitIdentifier {
   public static final String TYPE = "tar";

   public TarIdentifier() {
      super("tar", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(
         S.L("Tar archive handler"), S.L("Tar file inflater based on Apache commons-compress"), "PNF Software", Version.create(0, 1, 0)
      );
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      if (var1 == null) {
         return false;
      } else if (var3 != null && var3.endsWith(".tar")) {
         return true;
      } else {
         ByteBuffer var5 = var1.getHeader();
         if (var5.limit() < 512) {
            return false;
         } else {
            byte[] var6 = convert(var5);
            if (var6[99] != 0) {
               return false;
            } else {
               return !isAsciiOctal(var6, 100, 8) || !isAsciiOctal(var6, 108, 8) || !isAsciiOctal(var6, 116, 8) ? false : isAsciiOctal(var6, 136, 12);
            }
         }
      }
   }

   private static boolean isAsciiOctal(byte[] var0, int var1, int var2) {
      for (int var3 = 0; var3 < var2 - 1; var3++) {
         if (var0[var1 + var3] < 48 || var0[var1 + var3] > 55) {
            return false;
         }
      }

      return var0[var1 + var2 - 1] == 0;
   }

   private static byte[] convert(ByteBuffer var0) {
      byte[] var1 = new byte[var0.limit()];
      var0.get(var1);
      return var1;
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new eo(var1, var2, var3, var4, this.pdm);
   }
}
