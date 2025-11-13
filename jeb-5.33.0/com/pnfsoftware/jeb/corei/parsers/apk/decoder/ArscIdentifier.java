package com.pnfsoftware.jeb.corei.parsers.apk.decoder;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.units.AbstractUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Map;

public class ArscIdentifier extends AbstractUnitIdentifier {
   private static final ILogger pC = GlobalLog.getLogger(ArscIdentifier.class);

   public ArscIdentifier() {
      super("arsc", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(
         S.L("Android arsc decoder"), S.L("Decoder for Android binary resources (APK's resources.arsc, binary XML)"), "PNF Software", Version.create(1, 1, 0)
      );
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      if (var1 == null) {
         return false;
      } else {
         ByteBuffer var5 = var1.getHeader();
         if (var5.remaining() < 8) {
            return false;
         } else {
            var5.order(ByteOrder.LITTLE_ENDIAN);
            short var6 = var5.getShort();
            short var7 = var5.getShort();
            int var8 = var5.getInt();
            if (var8 == var1.getCurrentSize()) {
               if (var7 == 12 && var6 == 2) {
                  return true;
               }

               if (var7 == 8 && (var6 == 3 || var6 == 0 && var3 != null && var3.startsWith("AndroidManifest"))) {
                  if (var6 == 0) {
                     var4.put("forcedType", 3);
                  }

                  return true;
               }
            }

            return false;
         }
      }
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      DH var6 = new DH(var1, var2, var3, var4, this.pdm);
      Integer var7 = (Integer)var5.get("forcedType");
      if (var7 != null) {
         var6.pC = var7;
      }

      return var6;
   }
}
