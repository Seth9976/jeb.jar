package com.pnfsoftware.jeb.corei.parsers.json;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.units.AbstractUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.util.encoding.json.parser.JSONParser;
import com.pnfsoftware.jeb.util.encoding.json.parser.ParseException;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.primitives.Characters;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class JsonIdentifier extends AbstractUnitIdentifier {
   public JsonIdentifier() {
      super("json", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("JSON parser"), S.L("JSON parser and pretty-printn formatter"), "PNF Software", Version.create(0, 1, 0));
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      if (var1 == null) {
         return false;
      } else if (var3 != null && var3.toLowerCase().endsWith(".json")) {
         for (int var17 = 4; var17 < 8; var17++) {
            int var18 = readHeaderByte(var1, var17);
            if (!Characters.isAsciiCharOrCommonFormat(var18)) {
               return false;
            }
         }

         return true;
      } else {
         byte[] var5 = getNonWhitespaceHeader(var1, 1, true);
         if (var5 != null && var5.length != 0) {
            byte var7 = var5[0];
            boolean var6;
            if (var7 == 123) {
               var6 = true;
            } else {
               if (var7 != 91) {
                  return false;
               }

               var6 = false;
            }

            try {
               try (InputStream var8 = var1.getStream()) {
                  byte[] var9 = IO.readInputStream(var8);
                  if (pC(var9, var6)) {
                     JSONParser var10 = new JSONParser();

                     try {
                        var10.parse(Strings.decodeUTF8(var9));
                        return true;
                     } catch (ParseException var14) {
                        boolean var12;
                        return var12;
                     }
                  }
               }

               return false;
            } catch (IOException var16) {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   private static boolean pC(byte[] var0, boolean var1) {
      int var2 = var0.length - 1;

      while (var2 > 0) {
         byte var3 = var0[var2--];
         if (!Strings.isAsciiWhitespace(var3)) {
            if ((!var1 || var3 != 125) && (var1 || var3 != 93)) {
               return false;
            }

            return true;
         }
      }

      return false;
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new K(var1, var2, var3, var4, this.pdm);
   }
}
