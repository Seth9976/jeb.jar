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
import java.util.Map;

public class ArscMetaIdentifier extends AbstractUnitIdentifier {
   private static final ILogger logger = GlobalLog.getLogger(ArscMetaIdentifier.class);
   public static final String TYPE = "arsc_meta";

   public ArscMetaIdentifier() {
      super("arsc_meta", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(
         S.L("Android meta-arsc decoder"),
         S.L("Decoder for Google 'meta'-binary resources used to generate localized resources.arsc"),
         "PNF Software",
         Version.create(1, 0, 0)
      );
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      if (var1 == null) {
         return false;
      } else {
         ByteBuffer var5 = var1.getHeader();
         if (var5.remaining() < 4) {
            return false;
         } else {
            int var6 = var5.getInt();
            if ((var6 & -256) != 0) {
               return false;
            } else {
               int var7 = 4 + var6 * 2;
               if (var7 + 4 > var5.limit()) {
                  return false;
               } else {
                  int var8 = var5.getInt(var7);
                  return var8 == 1296389185;
               }
            }
         }
      }
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new Nt(var1, var2, var3, var4, this.pdm);
   }
}
